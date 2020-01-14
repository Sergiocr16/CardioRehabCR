import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IMayorEventsSession, MayorEventsSession } from 'app/shared/model/mayor-events-session.model';
import { MayorEventsSessionService } from './mayor-events-session.service';
import { ISession } from 'app/shared/model/session.model';
import { SessionService } from 'app/entities/session/session.service';

@Component({
  selector: 'jhi-mayor-events-session-update',
  templateUrl: './mayor-events-session-update.component.html'
})
export class MayorEventsSessionUpdateComponent implements OnInit {
  isSaving = false;

  sessions: ISession[] = [];

  editForm = this.fb.group({
    id: [],
    description: [],
    mayorEventId: [null, [Validators.required]],
    exist: [null, [Validators.required]],
    sessionId: []
  });

  constructor(
    protected mayorEventsSessionService: MayorEventsSessionService,
    protected sessionService: SessionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mayorEventsSession }) => {
      this.updateForm(mayorEventsSession);

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

  updateForm(mayorEventsSession: IMayorEventsSession): void {
    this.editForm.patchValue({
      id: mayorEventsSession.id,
      description: mayorEventsSession.description,
      mayorEventId: mayorEventsSession.mayorEventId,
      exist: mayorEventsSession.exist,
      sessionId: mayorEventsSession.sessionId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
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
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      mayorEventId: this.editForm.get(['mayorEventId'])!.value,
      exist: this.editForm.get(['exist'])!.value,
      sessionId: this.editForm.get(['sessionId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMayorEventsSession>>): void {
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
