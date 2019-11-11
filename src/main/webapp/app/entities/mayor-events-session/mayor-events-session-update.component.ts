import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMayorEventsSession, MayorEventsSession } from 'app/shared/model/mayor-events-session.model';
import { MayorEventsSessionService } from './mayor-events-session.service';
import { ISession } from 'app/shared/model/session.model';
import { SessionService } from 'app/entities/session/session.service';

@Component({
  selector: 'jhi-mayor-events-session-update',
  templateUrl: './mayor-events-session-update.component.html'
})
export class MayorEventsSessionUpdateComponent implements OnInit {
  isSaving: boolean;

  sessions: ISession[];

  editForm = this.fb.group({
    id: [],
    description: [],
    mayorEventId: [null, [Validators.required]],
    exist: [null, [Validators.required]],
    sessionId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mayorEventsSessionService: MayorEventsSessionService,
    protected sessionService: SessionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mayorEventsSession }) => {
      this.updateForm(mayorEventsSession);
    });
    this.sessionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ISession[]>) => mayBeOk.ok),
        map((response: HttpResponse<ISession[]>) => response.body)
      )
      .subscribe((res: ISession[]) => (this.sessions = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mayorEventsSession: IMayorEventsSession) {
    this.editForm.patchValue({
      id: mayorEventsSession.id,
      description: mayorEventsSession.description,
      mayorEventId: mayorEventsSession.mayorEventId,
      exist: mayorEventsSession.exist,
      sessionId: mayorEventsSession.sessionId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mayorEventsSession = this.createFromForm();
    if (mayorEventsSession.id !== undefined) {
      this.subscribeToSaveResponse(this.mayorEventsSessionService.update(mayorEventsSession));
    } else {
      this.subscribeToSaveResponse(this.mayorEventsSessionService.create(mayorEventsSession));
    }
  }

  private createFromForm(): IMayorEventsSession {
    return {
      ...new MayorEventsSession(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      mayorEventId: this.editForm.get(['mayorEventId']).value,
      exist: this.editForm.get(['exist']).value,
      sessionId: this.editForm.get(['sessionId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMayorEventsSession>>) {
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
