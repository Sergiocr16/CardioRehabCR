import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMinorEventsSession, MinorEventsSession } from 'app/shared/model/minor-events-session.model';
import { MinorEventsSessionService } from './minor-events-session.service';
import { ISession } from 'app/shared/model/session.model';
import { SessionService } from 'app/entities/session/session.service';

@Component({
  selector: 'jhi-minor-events-session-update',
  templateUrl: './minor-events-session-update.component.html'
})
export class MinorEventsSessionUpdateComponent implements OnInit {
  isSaving: boolean;

  sessions: ISession[];

  editForm = this.fb.group({
    id: [],
    description: [],
    minorEventId: [null, [Validators.required]],
    exist: [null, [Validators.required]],
    sessionId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected minorEventsSessionService: MinorEventsSessionService,
    protected sessionService: SessionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ minorEventsSession }) => {
      this.updateForm(minorEventsSession);
    });
    this.sessionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ISession[]>) => mayBeOk.ok),
        map((response: HttpResponse<ISession[]>) => response.body)
      )
      .subscribe(
        (res: ISession[]) => (this.sessions = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(minorEventsSession: IMinorEventsSession) {
    this.editForm.patchValue({
      id: minorEventsSession.id,
      description: minorEventsSession.description,
      minorEventId: minorEventsSession.minorEventId,
      exist: minorEventsSession.exist,
      sessionId: minorEventsSession.sessionId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const minorEventsSession = this.createFromForm();
    if (minorEventsSession.id !== undefined) {
      this.subscribeToSaveResponse(this.minorEventsSessionService.update(minorEventsSession));
    } else {
      this.subscribeToSaveResponse(this.minorEventsSessionService.create(minorEventsSession));
    }
  }

  private createFromForm(): IMinorEventsSession {
    return {
      ...new MinorEventsSession(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      minorEventId: this.editForm.get(['minorEventId']).value,
      exist: this.editForm.get(['exist']).value,
      sessionId: this.editForm.get(['sessionId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMinorEventsSession>>) {
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

  trackSessionById(index: number, item: ISession) {
    return item.id;
  }
}
