import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IComorbiditie, Comorbiditie } from 'app/shared/model/comorbiditie.model';
import { ComorbiditieService } from './comorbiditie.service';
import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from 'app/entities/rehabilitation-center/rehabilitation-center.service';

@Component({
  selector: 'jhi-comorbiditie-update',
  templateUrl: './comorbiditie-update.component.html'
})
export class ComorbiditieUpdateComponent implements OnInit {
  isSaving = false;

  rehabilitationcenters: IRehabilitationCenter[] = [];

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.required]],
    deleted: [],
    rehabilitationCenterId: []
  });

  constructor(
    protected comorbiditieService: ComorbiditieService,
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comorbiditie }) => {
      this.updateForm(comorbiditie);

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

  updateForm(comorbiditie: IComorbiditie): void {
    this.editForm.patchValue({
      id: comorbiditie.id,
      description: comorbiditie.description,
      deleted: comorbiditie.deleted,
      rehabilitationCenterId: comorbiditie.rehabilitationCenterId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const comorbiditie = this.createFromForm();
    if (comorbiditie.id !== undefined) {
      this.subscribeToSaveResponse(this.comorbiditieService.update(comorbiditie));
    } else {
      this.subscribeToSaveResponse(this.comorbiditieService.create(comorbiditie));
    }
  }

  private createFromForm(): IComorbiditie {
    return {
      ...new Comorbiditie(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      rehabilitationCenterId: this.editForm.get(['rehabilitationCenterId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComorbiditie>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IRehabilitationCenter): any {
    return item.id;
  }
}
