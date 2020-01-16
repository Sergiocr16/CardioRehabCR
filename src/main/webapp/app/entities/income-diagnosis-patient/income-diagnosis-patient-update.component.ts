import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IIncomeDiagnosisPatient, IncomeDiagnosisPatient } from 'app/shared/model/income-diagnosis-patient.model';
import { IncomeDiagnosisPatientService } from './income-diagnosis-patient.service';
import { IInitialAssessment } from 'app/shared/model/initial-assessment.model';
import { InitialAssessmentService } from 'app/entities/initial-assessment/initial-assessment.service';

@Component({
  selector: 'jhi-income-diagnosis-patient-update',
  templateUrl: './income-diagnosis-patient-update.component.html'
})
export class IncomeDiagnosisPatientUpdateComponent implements OnInit {
  isSaving = false;

  initialassessments: IInitialAssessment[] = [];

  editForm = this.fb.group({
    id: [],
    description: [],
    incomeDiagnosisId: [null, [Validators.required]],
    exist: [null, [Validators.required]],
    initialAssessmentId: []
  });

  constructor(
    protected incomeDiagnosisPatientService: IncomeDiagnosisPatientService,
    protected initialAssessmentService: InitialAssessmentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ incomeDiagnosisPatient }) => {
      this.updateForm(incomeDiagnosisPatient);

      this.initialAssessmentService
        .query()
        .pipe(
          map((res: HttpResponse<IInitialAssessment[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IInitialAssessment[]) => (this.initialassessments = resBody));
    });
  }

  updateForm(incomeDiagnosisPatient: IIncomeDiagnosisPatient): void {
    this.editForm.patchValue({
      id: incomeDiagnosisPatient.id,
      description: incomeDiagnosisPatient.description,
      incomeDiagnosisId: incomeDiagnosisPatient.incomeDiagnosisId,
      exist: incomeDiagnosisPatient.exist,
      initialAssessmentId: incomeDiagnosisPatient.initialAssessmentId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
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
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      incomeDiagnosisId: this.editForm.get(['incomeDiagnosisId'])!.value,
      exist: this.editForm.get(['exist'])!.value,
      initialAssessmentId: this.editForm.get(['initialAssessmentId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIncomeDiagnosisPatient>>): void {
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

  trackById(index: number, item: IInitialAssessment): any {
    return item.id;
  }
}
