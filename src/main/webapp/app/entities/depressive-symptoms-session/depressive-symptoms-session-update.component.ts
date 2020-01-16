import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IDepressiveSymptomsSession, DepressiveSymptomsSession } from 'app/shared/model/depressive-symptoms-session.model';
import { DepressiveSymptomsSessionService } from './depressive-symptoms-session.service';
import { ISession } from 'app/shared/model/session.model';
import { SessionService } from 'app/entities/session/session.service';

@Component({
  selector: 'jhi-depressive-symptoms-session-update',
  templateUrl: './depressive-symptoms-session-update.component.html'
})
export class DepressiveSymptomsSessionUpdateComponent implements OnInit {
  isSaving = false;

  sessions: ISession[] = [];

  editForm = this.fb.group({
    id: [],
    description: [],
    depressiveSymptomId: [null, [Validators.required]],
    exist: [null, [Validators.required]],
    sessionId: []
  });

  constructor(
    protected depressiveSymptomsSessionService: DepressiveSymptomsSessionService,
    protected sessionService: SessionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ depressiveSymptomsSession }) => {
      this.updateForm(depressiveSymptomsSession);

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

  updateForm(depressiveSymptomsSession: IDepressiveSymptomsSession): void {
    this.editForm.patchValue({
      id: depressiveSymptomsSession.id,
      description: depressiveSymptomsSession.description,
      depressiveSymptomId: depressiveSymptomsSession.depressiveSymptomId,
      exist: depressiveSymptomsSession.exist,
      sessionId: depressiveSymptomsSession.sessionId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
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
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      depressiveSymptomId: this.editForm.get(['depressiveSymptomId'])!.value,
      exist: this.editForm.get(['exist'])!.value,
      sessionId: this.editForm.get(['sessionId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepressiveSymptomsSession>>): void {
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

  trackById(index: number, item: ISession): any {
    return item.id;
  }
}
