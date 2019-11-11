import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { INonSpecificPainsSession, NonSpecificPainsSession } from 'app/shared/model/non-specific-pains-session.model';
import { NonSpecificPainsSessionService } from './non-specific-pains-session.service';
import { ISession } from 'app/shared/model/session.model';
import { SessionService } from 'app/entities/session/session.service';

@Component({
  selector: 'jhi-non-specific-pains-session-update',
  templateUrl: './non-specific-pains-session-update.component.html'
})
export class NonSpecificPainsSessionUpdateComponent implements OnInit {
  isSaving: boolean;

  sessions: ISession[];

  editForm = this.fb.group({
    id: [],
    description: [],
    nonSpecificPainId: [null, [Validators.required]],
    exist: [null, [Validators.required]],
    sessionId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected nonSpecificPainsSessionService: NonSpecificPainsSessionService,
    protected sessionService: SessionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ nonSpecificPainsSession }) => {
      this.updateForm(nonSpecificPainsSession);
    });
    this.sessionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ISession[]>) => mayBeOk.ok),
        map((response: HttpResponse<ISession[]>) => response.body)
      )
      .subscribe((res: ISession[]) => (this.sessions = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(nonSpecificPainsSession: INonSpecificPainsSession) {
    this.editForm.patchValue({
      id: nonSpecificPainsSession.id,
      description: nonSpecificPainsSession.description,
      nonSpecificPainId: nonSpecificPainsSession.nonSpecificPainId,
      exist: nonSpecificPainsSession.exist,
      sessionId: nonSpecificPainsSession.sessionId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
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
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      nonSpecificPainId: this.editForm.get(['nonSpecificPainId']).value,
      exist: this.editForm.get(['exist']).value,
      sessionId: this.editForm.get(['sessionId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INonSpecificPainsSession>>) {
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
