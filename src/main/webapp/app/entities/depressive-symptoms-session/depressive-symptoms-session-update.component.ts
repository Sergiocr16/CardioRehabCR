import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IDepressiveSymptomsSession, DepressiveSymptomsSession } from 'app/shared/model/depressive-symptoms-session.model';
import { DepressiveSymptomsSessionService } from './depressive-symptoms-session.service';
import { ISession } from 'app/shared/model/session.model';
import { SessionService } from 'app/entities/session/session.service';

@Component({
  selector: 'jhi-depressive-symptoms-session-update',
  templateUrl: './depressive-symptoms-session-update.component.html'
})
export class DepressiveSymptomsSessionUpdateComponent implements OnInit {
  isSaving: boolean;

  sessions: ISession[];

  editForm = this.fb.group({
    id: [],
    description: [],
    depressiveSymptomId: [null, [Validators.required]],
    exist: [null, [Validators.required]],
    sessionId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected depressiveSymptomsSessionService: DepressiveSymptomsSessionService,
    protected sessionService: SessionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ depressiveSymptomsSession }) => {
      this.updateForm(depressiveSymptomsSession);
    });
    this.sessionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ISession[]>) => mayBeOk.ok),
        map((response: HttpResponse<ISession[]>) => response.body)
      )
      .subscribe((res: ISession[]) => (this.sessions = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(depressiveSymptomsSession: IDepressiveSymptomsSession) {
    this.editForm.patchValue({
      id: depressiveSymptomsSession.id,
      description: depressiveSymptomsSession.description,
      depressiveSymptomId: depressiveSymptomsSession.depressiveSymptomId,
      exist: depressiveSymptomsSession.exist,
      sessionId: depressiveSymptomsSession.sessionId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const depressiveSymptomsSession = this.createFromForm();
    if (depressiveSymptomsSession.id !== undefined) {
      this.subscribeToSaveResponse(this.depressiveSymptomsSessionService.update(depressiveSymptomsSession));
    } else {
      this.subscribeToSaveResponse(this.depressiveSymptomsSessionService.create(depressiveSymptomsSession));
    }
  }

  private createFromForm(): IDepressiveSymptomsSession {
    return {
      ...new DepressiveSymptomsSession(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      depressiveSymptomId: this.editForm.get(['depressiveSymptomId']).value,
      exist: this.editForm.get(['exist']).value,
      sessionId: this.editForm.get(['sessionId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepressiveSymptomsSession>>) {
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

  trackSessionById(index: number, item: ISession) {
    return item.id;
  }
}
