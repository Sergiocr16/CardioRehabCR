import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { INonSpecificPainsSession, NonSpecificPainsSession } from 'app/shared/model/non-specific-pains-session.model';
import { NonSpecificPainsSessionService } from './non-specific-pains-session.service';
import { ISession } from 'app/shared/model/session.model';
import { SessionService } from 'app/entities/session/session.service';

@Component({
  selector: 'jhi-non-specific-pains-session-update',
  templateUrl: './non-specific-pains-session-update.component.html'
})
export class NonSpecificPainsSessionUpdateComponent implements OnInit {
  isSaving = false;

  sessions: ISession[] = [];

  editForm = this.fb.group({
    id: [],
    description: [],
    nonSpecificPainId: [null, [Validators.required]],
    exist: [null, [Validators.required]],
    sessionId: []
  });

  constructor(
    protected nonSpecificPainsSessionService: NonSpecificPainsSessionService,
    protected sessionService: SessionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nonSpecificPainsSession }) => {
      this.updateForm(nonSpecificPainsSession);

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

  updateForm(nonSpecificPainsSession: INonSpecificPainsSession): void {
    this.editForm.patchValue({
      id: nonSpecificPainsSession.id,
      description: nonSpecificPainsSession.description,
      nonSpecificPainId: nonSpecificPainsSession.nonSpecificPainId,
      exist: nonSpecificPainsSession.exist,
      sessionId: nonSpecificPainsSession.sessionId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nonSpecificPainsSession = this.createFromForm();
    if (nonSpecificPainsSession.id !== undefined) {
      this.subscribeToSaveResponse(this.nonSpecificPainsSessionService.update(nonSpecificPainsSession));
    } else {
      this.subscribeToSaveResponse(this.nonSpecificPainsSessionService.create(nonSpecificPainsSession));
    }
  }

  private createFromForm(): INonSpecificPainsSession {
    return {
      ...new NonSpecificPainsSession(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      nonSpecificPainId: this.editForm.get(['nonSpecificPainId'])!.value,
      exist: this.editForm.get(['exist'])!.value,
      sessionId: this.editForm.get(['sessionId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INonSpecificPainsSession>>): void {
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
