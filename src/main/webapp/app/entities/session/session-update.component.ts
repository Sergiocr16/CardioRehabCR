import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISession, Session } from 'app/shared/model/session.model';
import { SessionService } from './session.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';

@Component({
  selector: 'jhi-session-update',
  templateUrl: './session-update.component.html'
})
export class SessionUpdateComponent implements OnInit {
  isSaving = false;

  patients: IPatient[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    executionDate: [null, [Validators.required]],
    abscence: [],
    hospitalization: [],
    status: [],
    deleted: [],
    currentlyWorking: [],
    patientId: []
  });

  constructor(
    protected sessionService: SessionService,
    protected patientService: PatientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ session }) => {
      this.updateForm(session);

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

  updateForm(session: ISession): void {
    this.editForm.patchValue({
      id: session.id,
      code: session.code,
      executionDate: session.executionDate != null ? session.executionDate.format(DATE_TIME_FORMAT) : null,
      abscence: session.abscence,
      hospitalization: session.hospitalization,
      status: session.status,
      deleted: session.deleted,
      currentlyWorking: session.currentlyWorking,
      patientId: session.patientId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
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
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      executionDate:
        this.editForm.get(['executionDate'])!.value != null
          ? moment(this.editForm.get(['executionDate'])!.value, DATE_TIME_FORMAT)
          : undefined,
      abscence: this.editForm.get(['abscence'])!.value,
      hospitalization: this.editForm.get(['hospitalization'])!.value,
      status: this.editForm.get(['status'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      currentlyWorking: this.editForm.get(['currentlyWorking'])!.value,
      patientId: this.editForm.get(['patientId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISession>>): void {
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
