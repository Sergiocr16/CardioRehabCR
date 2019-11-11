import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMayorEvent, MayorEvent } from 'app/shared/model/mayor-event.model';
import { MayorEventService } from './mayor-event.service';
import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from 'app/entities/rehabilitation-center/rehabilitation-center.service';

@Component({
  selector: 'jhi-mayor-event-update',
  templateUrl: './mayor-event-update.component.html'
})
export class MayorEventUpdateComponent implements OnInit {
  isSaving: boolean;

  rehabilitationcenters: IRehabilitationCenter[];

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.required]],
    code: [],
    deleted: [],
    rehabilitationCenterId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mayorEventService: MayorEventService,
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mayorEvent }) => {
      this.updateForm(mayorEvent);
    });
    this.rehabilitationCenterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IRehabilitationCenter[]>) => mayBeOk.ok),
        map((response: HttpResponse<IRehabilitationCenter[]>) => response.body)
      )
      .subscribe(
        (res: IRehabilitationCenter[]) => (this.rehabilitationcenters = res),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(mayorEvent: IMayorEvent) {
    this.editForm.patchValue({
      id: mayorEvent.id,
      description: mayorEvent.description,
      code: mayorEvent.code,
      deleted: mayorEvent.deleted,
      rehabilitationCenterId: mayorEvent.rehabilitationCenterId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mayorEvent = this.createFromForm();
    if (mayorEvent.id !== undefined) {
      this.subscribeToSaveResponse(this.mayorEventService.update(mayorEvent));
    } else {
      this.subscribeToSaveResponse(this.mayorEventService.create(mayorEvent));
    }
  }

  private createFromForm(): IMayorEvent {
    return {
      ...new MayorEvent(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      code: this.editForm.get(['code']).value,
      deleted: this.editForm.get(['deleted']).value,
      rehabilitationCenterId: this.editForm.get(['rehabilitationCenterId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMayorEvent>>) {
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

  trackRehabilitationCenterById(index: number, item: IRehabilitationCenter) {
    return item.id;
  }
}
