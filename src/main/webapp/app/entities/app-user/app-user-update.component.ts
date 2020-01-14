import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IAppUser, AppUser } from 'app/shared/model/app-user.model';
import { AppUserService } from './app-user.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from 'app/entities/rehabilitation-center/rehabilitation-center.service';

type SelectableEntity = IUser | IRehabilitationCenter;

@Component({
  selector: 'jhi-app-user-update',
  templateUrl: './app-user-update.component.html'
})
export class AppUserUpdateComponent implements OnInit {
  isSaving = false;

  users: IUser[] = [];

  rehabilitationcenters: IRehabilitationCenter[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    lastName: [],
    authorityType: [null, [Validators.required]],
    status: [],
    userId: [],
    rehabilitationCenterId: []
  });

  constructor(
    protected appUserService: AppUserService,
    protected userService: UserService,
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ appUser }) => {
      this.updateForm(appUser);

      this.userService
        .query()
        .pipe(
          map((res: HttpResponse<IUser[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IUser[]) => (this.users = resBody));

      this.rehabilitationCenterService
        .query()
        .pipe(
          map((res: HttpResponse<IRehabilitationCenter[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IRehabilitationCenter[]) => (this.rehabilitationcenters = resBody));
    });
  }

  updateForm(appUser: IAppUser): void {
    this.editForm.patchValue({
      id: appUser.id,
      name: appUser.name,
      lastName: appUser.lastName,
      authorityType: appUser.authorityType,
      status: appUser.status,
      userId: appUser.userId,
      rehabilitationCenterId: appUser.rehabilitationCenterId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const appUser = this.createFromForm();
    if (appUser.id !== undefined) {
      this.subscribeToSaveResponse(this.appUserService.update(appUser));
    } else {
      this.subscribeToSaveResponse(this.appUserService.create(appUser));
    }
  }

  private createFromForm(): IAppUser {
    return {
      ...new AppUser(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      authorityType: this.editForm.get(['authorityType'])!.value,
      status: this.editForm.get(['status'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      rehabilitationCenterId: this.editForm.get(['rehabilitationCenterId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAppUser>>): void {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
