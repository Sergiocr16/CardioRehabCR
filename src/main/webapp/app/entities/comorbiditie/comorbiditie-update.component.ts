import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IComorbiditie, Comorbiditie } from 'app/shared/model/comorbiditie.model';
import { ComorbiditieService } from './comorbiditie.service';
import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from 'app/entities/rehabilitation-center/rehabilitation-center.service';

@Component({
  selector: 'jhi-comorbiditie-update',
  templateUrl: './comorbiditie-update.component.html'
})
export class ComorbiditieUpdateComponent implements OnInit {
  isSaving: boolean;

  rehabilitationcenters: IRehabilitationCenter[];

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.required]],
    deleted: [],
    rehabilitationCenterId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected comorbiditieService: ComorbiditieService,
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ comorbiditie }) => {
      this.updateForm(comorbiditie);
    });
    this.rehabilitationCenterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IRehabilitationCenter[]>) => mayBeOk.ok),
        map((response: HttpResponse<IRehabilitationCenter[]>) => response.body)
      )
      .subscribe(
        (res: IRehabilitationCenter[]) => (this.rehabilitationcenters = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(comorbiditie: IComorbiditie) {
    this.editForm.patchValue({
      id: comorbiditie.id,
      description: comorbiditie.description,
      deleted: comorbiditie.deleted,
      rehabilitationCenterId: comorbiditie.rehabilitationCenterId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
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
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      deleted: this.editForm.get(['deleted']).value,
      rehabilitationCenterId: this.editForm.get(['rehabilitationCenterId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComorbiditie>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackRehabilitationCenterById(index: number, item: IRehabilitationCenter) {
    return item.id;
  }
}
