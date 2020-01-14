import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IComorbiditiesPatient, ComorbiditiesPatient } from 'app/shared/model/comorbidities-patient.model';
import { ComorbiditiesPatientService } from './comorbidities-patient.service';
import { IInitialAssessment } from 'app/shared/model/initial-assessment.model';
import { InitialAssessmentService } from 'app/entities/initial-assessment/initial-assessment.service';

@Component({
  selector: 'jhi-comorbidities-patient-update',
  templateUrl: './comorbidities-patient-update.component.html'
})
export class ComorbiditiesPatientUpdateComponent implements OnInit {
  isSaving = false;

  initialassessments: IInitialAssessment[] = [];

  editForm = this.fb.group({
    id: [],
    description: [],
    comorbiditietId: [null, [Validators.required]],
    exist: [null, [Validators.required]],
    initialAssessmentId: []
  });

  constructor(
    protected comorbiditiesPatientService: ComorbiditiesPatientService,
    protected initialAssessmentService: InitialAssessmentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comorbiditiesPatient }) => {
      this.updateForm(comorbiditiesPatient);

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

  updateForm(comorbiditiesPatient: IComorbiditiesPatient): void {
    this.editForm.patchValue({
      id: comorbiditiesPatient.id,
      description: comorbiditiesPatient.description,
      comorbiditietId: comorbiditiesPatient.comorbiditietId,
      exist: comorbiditiesPatient.exist,
      initialAssessmentId: comorbiditiesPatient.initialAssessmentId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const comorbiditiesPatient = this.createFromForm();
    if (comorbiditiesPatient.id !== undefined) {
      this.subscribeToSaveResponse(this.comorbiditiesPatientService.update(comorbiditiesPatient));
    } else {
      this.subscribeToSaveResponse(this.comorbiditiesPatientService.create(comorbiditiesPatient));
    }
  }

  private createFromForm(): IComorbiditiesPatient {
    return {
      ...new ComorbiditiesPatient(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      comorbiditietId: this.editForm.get(['comorbiditietId'])!.value,
      exist: this.editForm.get(['exist'])!.value,
      initialAssessmentId: this.editForm.get(['initialAssessmentId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComorbiditiesPatient>>): void {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
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
