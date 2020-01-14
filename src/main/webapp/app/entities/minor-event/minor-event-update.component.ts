import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IMinorEvent, MinorEvent } from 'app/shared/model/minor-event.model';
import { MinorEventService } from './minor-event.service';
import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from 'app/entities/rehabilitation-center/rehabilitation-center.service';

@Component({
  selector: 'jhi-minor-event-update',
  templateUrl: './minor-event-update.component.html'
})
export class MinorEventUpdateComponent implements OnInit {
  isSaving = false;

  rehabilitationcenters: IRehabilitationCenter[] = [];

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.required]],
    code: [],
    deleted: [],
    rehabilitationCenterId: []
  });

  constructor(
    protected minorEventService: MinorEventService,
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ minorEvent }) => {
      this.updateForm(minorEvent);

      this.rehabilitationCenterService
        .query()
        .pipe(
          map((res: HttpResponse<IRehabilitationCenter[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IRehabilitationCenter[]) => (this.rehabilitationcenters = resBody));
    });
  }

  updateForm(minorEvent: IMinorEvent): void {
    this.editForm.patchValue({
      id: minorEvent.id,
      description: minorEvent.description,
      code: minorEvent.code,
      deleted: minorEvent.deleted,
      rehabilitationCenterId: minorEvent.rehabilitationCenterId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const minorEvent = this.createFromForm();
    if (minorEvent.id !== undefined) {
      this.subscribeToSaveResponse(this.minorEventService.update(minorEvent));
    } else {
      this.subscribeToSaveResponse(this.minorEventService.create(minorEvent));
    }
  }

  private createFromForm(): IMinorEvent {
    return {
      ...new MinorEvent(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      code: this.editForm.get(['code'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      rehabilitationCenterId: this.editForm.get(['rehabilitationCenterId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMinorEvent>>): void {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IRehabilitationCenter): any {
    return item.id;
  }
}
