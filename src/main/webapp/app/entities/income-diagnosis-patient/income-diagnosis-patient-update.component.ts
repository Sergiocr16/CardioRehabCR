import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IIncomeDiagnosisPatient, IncomeDiagnosisPatient } from 'app/shared/model/income-diagnosis-patient.model';
import { IncomeDiagnosisPatientService } from './income-diagnosis-patient.service';
import { IInitialAssessment } from 'app/shared/model/initial-assessment.model';
import { InitialAssessmentService } from 'app/entities/initial-assessment/initial-assessment.service';

@Component({
  selector: 'jhi-income-diagnosis-patient-update',
  templateUrl: './income-diagnosis-patient-update.component.html'
})
export class IncomeDiagnosisPatientUpdateComponent implements OnInit {
  isSaving: boolean;

  initialassessments: IInitialAssessment[];

  editForm = this.fb.group({
    id: [],
    description: [],
    incomeDiagnosisId: [null, [Validators.required]],
    exist: [null, [Validators.required]],
    initialAssessmentId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected incomeDiagnosisPatientService: IncomeDiagnosisPatientService,
    protected initialAssessmentService: InitialAssessmentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ incomeDiagnosisPatient }) => {
      this.updateForm(incomeDiagnosisPatient);
    });
    this.initialAssessmentService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IInitialAssessment[]>) => mayBeOk.ok),
        map((response: HttpResponse<IInitialAssessment[]>) => response.body)
      )
      .subscribe((res: IInitialAssessment[]) => (this.initialassessments = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(incomeDiagnosisPatient: IIncomeDiagnosisPatient) {
    this.editForm.patchValue({
      id: incomeDiagnosisPatient.id,
      description: incomeDiagnosisPatient.description,
      incomeDiagnosisId: incomeDiagnosisPatient.incomeDiagnosisId,
      exist: incomeDiagnosisPatient.exist,
      initialAssessmentId: incomeDiagnosisPatient.initialAssessmentId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const incomeDiagnosisPatient = this.createFromForm();
    if (incomeDiagnosisPatient.id !== undefined) {
      this.subscribeToSaveResponse(this.incomeDiagnosisPatientService.update(incomeDiagnosisPatient));
    } else {
      this.subscribeToSaveResponse(this.incomeDiagnosisPatientService.create(incomeDiagnosisPatient));
    }
  }

  private createFromForm(): IIncomeDiagnosisPatient {
    return {
      ...new IncomeDiagnosisPatient(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      incomeDiagnosisId: this.editForm.get(['incomeDiagnosisId']).value,
      exist: this.editForm.get(['exist']).value,
      initialAssessmentId: this.editForm.get(['initialAssessmentId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIncomeDiagnosisPatient>>) {
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

  trackInitialAssessmentById(index: number, item: IInitialAssessment) {
    return item.id;
  }
}
