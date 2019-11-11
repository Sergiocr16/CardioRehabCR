import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IIncomeDiagnosis, IncomeDiagnosis } from 'app/shared/model/income-diagnosis.model';
import { IncomeDiagnosisService } from './income-diagnosis.service';
import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from 'app/entities/rehabilitation-center/rehabilitation-center.service';

@Component({
  selector: 'jhi-income-diagnosis-update',
  templateUrl: './income-diagnosis-update.component.html'
})
export class IncomeDiagnosisUpdateComponent implements OnInit {
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
    protected incomeDiagnosisService: IncomeDiagnosisService,
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ incomeDiagnosis }) => {
      this.updateForm(incomeDiagnosis);
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

  updateForm(incomeDiagnosis: IIncomeDiagnosis) {
    this.editForm.patchValue({
      id: incomeDiagnosis.id,
      description: incomeDiagnosis.description,
      deleted: incomeDiagnosis.deleted,
      rehabilitationCenterId: incomeDiagnosis.rehabilitationCenterId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const incomeDiagnosis = this.createFromForm();
    if (incomeDiagnosis.id !== undefined) {
      this.subscribeToSaveResponse(this.incomeDiagnosisService.update(incomeDiagnosis));
    } else {
      this.subscribeToSaveResponse(this.incomeDiagnosisService.create(incomeDiagnosis));
    }
  }

  private createFromForm(): IIncomeDiagnosis {
    return {
      ...new IncomeDiagnosis(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      deleted: this.editForm.get(['deleted']).value,
      rehabilitationCenterId: this.editForm.get(['rehabilitationCenterId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIncomeDiagnosis>>) {
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
