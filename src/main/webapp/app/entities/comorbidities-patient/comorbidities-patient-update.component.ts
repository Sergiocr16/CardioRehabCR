import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IComorbiditiesPatient, ComorbiditiesPatient } from 'app/shared/model/comorbidities-patient.model';
import { ComorbiditiesPatientService } from './comorbidities-patient.service';
import { IInitialAssessment } from 'app/shared/model/initial-assessment.model';
import { InitialAssessmentService } from 'app/entities/initial-assessment/initial-assessment.service';

@Component({
  selector: 'jhi-comorbidities-patient-update',
  templateUrl: './comorbidities-patient-update.component.html'
})
export class ComorbiditiesPatientUpdateComponent implements OnInit {
  isSaving: boolean;

  initialassessments: IInitialAssessment[];

  editForm = this.fb.group({
    id: [],
    description: [],
    comorbiditietId: [null, [Validators.required]],
    exist: [null, [Validators.required]],
    initialAssessmentId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected comorbiditiesPatientService: ComorbiditiesPatientService,
    protected initialAssessmentService: InitialAssessmentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ comorbiditiesPatient }) => {
      this.updateForm(comorbiditiesPatient);
    });
    this.initialAssessmentService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IInitialAssessment[]>) => mayBeOk.ok),
        map((response: HttpResponse<IInitialAssessment[]>) => response.body)
      )
      .subscribe(
        (res: IInitialAssessment[]) => (this.initialassessments = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(comorbiditiesPatient: IComorbiditiesPatient) {
    this.editForm.patchValue({
      id: comorbiditiesPatient.id,
      description: comorbiditiesPatient.description,
      comorbiditietId: comorbiditiesPatient.comorbiditietId,
      exist: comorbiditiesPatient.exist,
      initialAssessmentId: comorbiditiesPatient.initialAssessmentId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
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
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      comorbiditietId: this.editForm.get(['comorbiditietId']).value,
      exist: this.editForm.get(['exist']).value,
      initialAssessmentId: this.editForm.get(['initialAssessmentId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComorbiditiesPatient>>) {
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

  trackInitialAssessmentById(index: number, item: IInitialAssessment) {
    return item.id;
  }
}
