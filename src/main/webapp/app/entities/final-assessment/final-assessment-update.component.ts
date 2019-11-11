import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IFinalAssessment, FinalAssessment } from 'app/shared/model/final-assessment.model';
import { FinalAssessmentService } from './final-assessment.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';

@Component({
  selector: 'jhi-final-assessment-update',
  templateUrl: './final-assessment-update.component.html'
})
export class FinalAssessmentUpdateComponent implements OnInit {
  isSaving: boolean;

  patients: IPatient[];

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
    patientId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected finalAssessmentService: FinalAssessmentService,
    protected patientService: PatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ finalAssessment }) => {
      this.updateForm(finalAssessment);
    });
    this.patientService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPatient[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPatient[]>) => response.body)
      )
      .subscribe((res: IPatient[]) => (this.patients = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(finalAssessment: IFinalAssessment) {
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
      patientId: finalAssessment.patientId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
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
      id: this.editForm.get(['id']).value,
      smoking: this.editForm.get(['smoking']).value,
      weight: this.editForm.get(['weight']).value,
      size: this.editForm.get(['size']).value,
      iMC: this.editForm.get(['iMC']).value,
      hbiac: this.editForm.get(['hbiac']).value,
      baselineFunctionalCapacity: this.editForm.get(['baselineFunctionalCapacity']).value,
      lDL: this.editForm.get(['lDL']).value,
      hDL: this.editForm.get(['hDL']).value,
      cardiovascularRisk: this.editForm.get(['cardiovascularRisk']).value,
      isWorking: this.editForm.get(['isWorking']).value,
      deceased: this.editForm.get(['deceased']).value,
      abandonment: this.editForm.get(['abandonment']).value,
      abandonmentMedicCause: this.editForm.get(['abandonmentMedicCause']).value,
      hospitalized: this.editForm.get(['hospitalized']).value,
      deleted: this.editForm.get(['deleted']).value,
      patientId: this.editForm.get(['patientId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFinalAssessment>>) {
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

  trackPatientById(index: number, item: IPatient) {
    return item.id;
  }
}
