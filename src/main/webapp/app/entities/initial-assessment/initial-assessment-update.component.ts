import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IInitialAssessment, InitialAssessment } from 'app/shared/model/initial-assessment.model';
import { InitialAssessmentService } from './initial-assessment.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';

@Component({
  selector: 'jhi-initial-assessment-update',
  templateUrl: './initial-assessment-update.component.html'
})
export class InitialAssessmentUpdateComponent implements OnInit {
  isSaving = false;

  patients: IPatient[] = [];

  editForm = this.fb.group({
    id: [],
    smoking: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    size: [null, [Validators.required]],
    iMC: [null, [Validators.required]],
    hbiac: [],
    baselineFunctionalCapacity: [],
    lDL: [],
    hDL: [],
    cardiovascularRisk: [],
    deleted: [],
    patientId: []
  });

  constructor(
    protected initialAssessmentService: InitialAssessmentService,
    protected patientService: PatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ initialAssessment }) => {
      this.updateForm(initialAssessment);

      this.patientService
        .query()
        .pipe(
          map((res: HttpResponse<IPatient[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IPatient[]) => (this.patients = resBody));
    });
  }

  updateForm(initialAssessment: IInitialAssessment): void {
    this.editForm.patchValue({
      id: initialAssessment.id,
      smoking: initialAssessment.smoking,
      weight: initialAssessment.weight,
      size: initialAssessment.size,
      iMC: initialAssessment.iMC,
      hbiac: initialAssessment.hbiac,
      baselineFunctionalCapacity: initialAssessment.baselineFunctionalCapacity,
      lDL: initialAssessment.lDL,
      hDL: initialAssessment.hDL,
      cardiovascularRisk: initialAssessment.cardiovascularRisk,
      deleted: initialAssessment.deleted,
      patientId: initialAssessment.patientId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const initialAssessment = this.createFromForm();
    if (initialAssessment.id !== undefined) {
      this.subscribeToSaveResponse(this.initialAssessmentService.update(initialAssessment));
    } else {
      this.subscribeToSaveResponse(this.initialAssessmentService.create(initialAssessment));
    }
  }

  private createFromForm(): IInitialAssessment {
    return {
      ...new InitialAssessment(),
      id: this.editForm.get(['id'])!.value,
      smoking: this.editForm.get(['smoking'])!.value,
      weight: this.editForm.get(['weight'])!.value,
      size: this.editForm.get(['size'])!.value,
      iMC: this.editForm.get(['iMC'])!.value,
      hbiac: this.editForm.get(['hbiac'])!.value,
      baselineFunctionalCapacity: this.editForm.get(['baselineFunctionalCapacity'])!.value,
      lDL: this.editForm.get(['lDL'])!.value,
      hDL: this.editForm.get(['hDL'])!.value,
      cardiovascularRisk: this.editForm.get(['cardiovascularRisk'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      patientId: this.editForm.get(['patientId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInitialAssessment>>): void {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IPatient): any {
    return item.id;
  }
}
