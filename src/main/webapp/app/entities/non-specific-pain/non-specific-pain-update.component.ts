import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { INonSpecificPain, NonSpecificPain } from 'app/shared/model/non-specific-pain.model';
import { NonSpecificPainService } from './non-specific-pain.service';
import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from 'app/entities/rehabilitation-center/rehabilitation-center.service';

@Component({
  selector: 'jhi-non-specific-pain-update',
  templateUrl: './non-specific-pain-update.component.html'
})
export class NonSpecificPainUpdateComponent implements OnInit {
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
    protected nonSpecificPainService: NonSpecificPainService,
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nonSpecificPain }) => {
      this.updateForm(nonSpecificPain);

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

  updateForm(nonSpecificPain: INonSpecificPain): void {
    this.editForm.patchValue({
      id: nonSpecificPain.id,
      description: nonSpecificPain.description,
      code: nonSpecificPain.code,
      deleted: nonSpecificPain.deleted,
      rehabilitationCenterId: nonSpecificPain.rehabilitationCenterId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nonSpecificPain = this.createFromForm();
    if (nonSpecificPain.id !== undefined) {
      this.subscribeToSaveResponse(this.nonSpecificPainService.update(nonSpecificPain));
    } else {
      this.subscribeToSaveResponse(this.nonSpecificPainService.create(nonSpecificPain));
    }
  }

  private createFromForm(): INonSpecificPain {
    return {
      ...new NonSpecificPain(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      code: this.editForm.get(['code'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      rehabilitationCenterId: this.editForm.get(['rehabilitationCenterId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INonSpecificPain>>): void {
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
