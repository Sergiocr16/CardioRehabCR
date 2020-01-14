import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IMinorEventsSession, MinorEventsSession } from 'app/shared/model/minor-events-session.model';
import { MinorEventsSessionService } from './minor-events-session.service';
import { ISession } from 'app/shared/model/session.model';
import { SessionService } from 'app/entities/session/session.service';

@Component({
  selector: 'jhi-minor-events-session-update',
  templateUrl: './minor-events-session-update.component.html'
})
export class MinorEventsSessionUpdateComponent implements OnInit {
  isSaving = false;

  sessions: ISession[] = [];

  editForm = this.fb.group({
    id: [],
    description: [],
    minorEventId: [null, [Validators.required]],
    exist: [null, [Validators.required]],
    sessionId: []
  });

  constructor(
    protected minorEventsSessionService: MinorEventsSessionService,
    protected sessionService: SessionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ minorEventsSession }) => {
      this.updateForm(minorEventsSession);

      this.sessionService
        .query()
        .pipe(
          map((res: HttpResponse<ISession[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ISession[]) => (this.sessions = resBody));
    });
  }

  updateForm(minorEventsSession: IMinorEventsSession): void {
    this.editForm.patchValue({
      id: minorEventsSession.id,
      description: minorEventsSession.description,
      minorEventId: minorEventsSession.minorEventId,
      exist: minorEventsSession.exist,
      sessionId: minorEventsSession.sessionId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
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
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      minorEventId: this.editForm.get(['minorEventId'])!.value,
      exist: this.editForm.get(['exist'])!.value,
      sessionId: this.editForm.get(['sessionId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMinorEventsSession>>): void {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ISession): any {
    return item.id;
  }
}
