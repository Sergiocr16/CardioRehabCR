import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IInitialAssessment, InitialAssessment } from 'app/shared/model/initial-assessment.model';
import { InitialAssessmentService } from './initial-assessment.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';

@Component({
  selector: 'jhi-initial-assessment-update',
  templateUrl: './initial-assessment-update.component.html'
})
export class InitialAssessmentUpdateComponent implements OnInit {
  isSaving: boolean;

  patients: IPatient[];

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
    protected jhiAlertService: JhiAlertService,
    protected initialAssessmentService: InitialAssessmentService,
    protected patientService: PatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ initialAssessment }) => {
      this.updateForm(initialAssessment);
    });
    this.patientService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPatient[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPatient[]>) => response.body)
      )
      .subscribe(
        (res: IPatient[]) => (this.patients = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(initialAssessment: IInitialAssessment) {
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

  previousState() {
    window.history.back();
  }

  save() {
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
      deleted: this.editForm.get(['deleted']).value,
      patientId: this.editForm.get(['patientId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInitialAssessment>>) {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
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
