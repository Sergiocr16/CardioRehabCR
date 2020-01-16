import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFinalAssessment, FinalAssessment } from 'app/shared/model/final-assessment.model';
import { FinalAssessmentService } from './final-assessment.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';

@Component({
  selector: 'jhi-final-assessment-update',
  templateUrl: './final-assessment-update.component.html'
})
export class FinalAssessmentUpdateComponent implements OnInit {
  isSaving = false;

  patients: IPatient[] = [];

  editForm = this.fb.group({
    id: [],
    smoking: [],
    weight: [],
    size: [],
    iMC: [],
    hbiac: [],
    baselineFunctionalCapacity: [],
    lDL: [],
    hDL: [],
    cardiovascularRisk: [],
    isWorking: [],
    deceased: [],
    abandonment: [],
    abandonmentMedicCause: [],
    hospitalized: [],
    deleted: [],
    reevaluation: [],
    executionDate: [],
    patientId: []
  });

  constructor(
    protected finalAssessmentService: FinalAssessmentService,
    protected patientService: PatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ finalAssessment }) => {
      this.updateForm(finalAssessment);

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

  updateForm(finalAssessment: IFinalAssessment): void {
    this.editForm.patchValue({
      id: finalAssessment.id,
      smoking: finalAssessment.smoking,
      weight: finalAssessment.weight,
      size: finalAssessment.size,
      iMC: finalAssessment.iMC,
      hbiac: finalAssessment.hbiac,
      baselineFunctionalCapacity: finalAssessment.baselineFunctionalCapacity,
      lDL: finalAssessment.lDL,
      hDL: finalAssessment.hDL,
      cardiovascularRisk: finalAssessment.cardiovascularRisk,
      isWorking: finalAssessment.isWorking,
      deceased: finalAssessment.deceased,
      abandonment: finalAssessment.abandonment,
      abandonmentMedicCause: finalAssessment.abandonmentMedicCause,
      hospitalized: finalAssessment.hospitalized,
      deleted: finalAssessment.deleted,
      reevaluation: finalAssessment.reevaluation,
      executionDate: finalAssessment.executionDate != null ? finalAssessment.executionDate.format(DATE_TIME_FORMAT) : null,
      patientId: finalAssessment.patientId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const finalAssessment = this.createFromForm();
    if (finalAssessment.id !== undefined) {
      this.subscribeToSaveResponse(this.finalAssessmentService.update(finalAssessment));
    } else {
      this.subscribeToSaveResponse(this.finalAssessmentService.create(finalAssessment));
    }
  }

  private createFromForm(): IFinalAssessment {
    return {
      ...new FinalAssessment(),
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
      isWorking: this.editForm.get(['isWorking'])!.value,
      deceased: this.editForm.get(['deceased'])!.value,
      abandonment: this.editForm.get(['abandonment'])!.value,
      abandonmentMedicCause: this.editForm.get(['abandonmentMedicCause'])!.value,
      hospitalized: this.editForm.get(['hospitalized'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      reevaluation: this.editForm.get(['reevaluation'])!.value,
      executionDate:
        this.editForm.get(['executionDate'])!.value != null
          ? moment(this.editForm.get(['executionDate'])!.value, DATE_TIME_FORMAT)
          : undefined,
      patientId: this.editForm.get(['patientId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFinalAssessment>>): void {
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

  trackById(index: number, item: IPatient): any {
    return item.id;
  }
}
