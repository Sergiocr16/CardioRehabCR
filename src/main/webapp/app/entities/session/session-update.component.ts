import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ISession, Session } from 'app/shared/model/session.model';
import { SessionService } from './session.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';
import { ModalService } from 'app/shared/util/modal.service';
import { GlobalVariablesService } from 'app/shared/util/global-variables.service';
import { IIncomeDiagnosis } from 'app/shared/model/income-diagnosis.model';
import { NonSpecificPainService } from 'app/entities/non-specific-pain/non-specific-pain.service';
import { MinorEventService } from 'app/entities/minor-event/minor-event.service';
import { MayorEventService } from 'app/entities/mayor-event/mayor-event.service';
import { DepressiveSymptomService } from 'app/entities/depressive-symptom/depressive-symptom.service';

@Component({
  selector: 'jhi-session-update',
  templateUrl: './session-update.component.html'
})
export class SessionUpdateComponent implements OnInit {
  isSaving: boolean;

  patients: IPatient[];
  title;
  modalSuccessMessage;
  modalConfirm;

  nonSpecificPains = [];
  minorEvents = [];
  mayorEvents = [];
  depressiveSymptoms = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    executionDate: [null, [Validators.required]],
    abscence: [],
    hospitalization: [],
    status: [],
    deleted: [],
    patientId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected sessionService: SessionService,
    protected patientService: PatientService,
    protected activatedRoute: ActivatedRoute,
    protected modal: ModalService,
    private global: GlobalVariablesService,
    protected nonSpecificPainService: NonSpecificPainService,
    protected minorEventService: MinorEventService,
    protected mayorEventService: MayorEventService,
    protected depressiveSymptomService: DepressiveSymptomService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ session }) => {
      session.executionDate = moment(new Date());
      this.updateForm(session);
      this.title = session.id == null ? 'Crear evaluación' : 'Editar evaluación';
      this.modalConfirm = session.id == null ? 'new' : 'update';
      this.modalSuccessMessage = session.id == null ? 'Evaluación creada correctamente.' : 'Evaluación editada correctamente.';
      this.loadNonSpecificPain();
      this.loadMinorEvents();
      this.loadMayorEvents();
      this.loadDepressiveSymptoms();
    });

    this.patientService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPatient[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPatient[]>) => response.body)
      )
      .subscribe((res: IPatient[]) => (this.patients = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  loadMinorEvents() {
    this.minorEventService
      .query({
        rehabilitationId: this.global.rehabCenter
      })
      .pipe(
        filter((mayBeOk: HttpResponse<IIncomeDiagnosis[]>) => mayBeOk.ok),
        map((response: HttpResponse<IIncomeDiagnosis[]>) => response.body)
      )
      .subscribe(
        (res: IIncomeDiagnosis[]) => this.formatCheckBoxArray(res, this.minorEvents),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadMayorEvents() {
    this.mayorEventService
      .query({
        rehabilitationId: this.global.rehabCenter
      })
      .pipe(
        filter((mayBeOk: HttpResponse<IIncomeDiagnosis[]>) => mayBeOk.ok),
        map((response: HttpResponse<IIncomeDiagnosis[]>) => response.body)
      )
      .subscribe(
        (res: IIncomeDiagnosis[]) => this.formatCheckBoxArray(res, this.mayorEvents),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadDepressiveSymptoms() {
    this.depressiveSymptomService
      .query({
        rehabilitationId: this.global.rehabCenter
      })
      .pipe(
        filter((mayBeOk: HttpResponse<IIncomeDiagnosis[]>) => mayBeOk.ok),
        map((response: HttpResponse<IIncomeDiagnosis[]>) => response.body)
      )
      .subscribe(
        (res: IIncomeDiagnosis[]) => this.formatCheckBoxArray(res, this.depressiveSymptoms),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadNonSpecificPain() {
    this.nonSpecificPainService
      .query({
        rehabilitationId: this.global.rehabCenter
      })
      .pipe(
        filter((mayBeOk: HttpResponse<IIncomeDiagnosis[]>) => mayBeOk.ok),
        map((response: HttpResponse<IIncomeDiagnosis[]>) => response.body)
      )
      .subscribe(
        (res: IIncomeDiagnosis[]) => this.formatCheckBoxArray(res, this.nonSpecificPains),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  formatCheckBoxArray(res, array) {
    for (const o of res) {
      o.checked = false;
      array.push(o);
    }
  }

  valueChange(array, i, $event) {
    array[i].checked = $event.checked;
  }

  setInvalidForm(isSaving) {
    this.global.setFormStatus(isSaving);
  }

  updateForm(session: ISession) {
    this.editForm.patchValue({
      id: session.id,
      code: session.code,
      executionDate: session.executionDate != null ? new Date(session.executionDate.toDate()) : null,
      abscence: session.abscence,
      hospitalization: session.hospitalization,
      status: session.status,
      deleted: session.deleted,
      patientId: session.patientId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const session = this.createFromForm();
    if (session.id !== undefined) {
      this.subscribeToSaveResponse(this.sessionService.update(session));
    } else {
      this.subscribeToSaveResponse(this.sessionService.create(session));
    }
  }

  private createFromForm(): ISession {
    return {
      ...new Session(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      executionDate:
        this.editForm.get(['executionDate']).value != null
          ? moment(this.editForm.get(['executionDate']).value, DATE_TIME_FORMAT)
          : undefined,
      abscence: this.editForm.get(['abscence']).value,
      hospitalization: this.editForm.get(['hospitalization']).value,
      status: this.editForm.get(['status']).value,
      deleted: this.editForm.get(['deleted']).value,
      patientId: this.editForm.get(['patientId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISession>>) {
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
