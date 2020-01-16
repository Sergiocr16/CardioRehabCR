import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRehabilitationCenter, RehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from './rehabilitation-center.service';

@Component({
  selector: 'jhi-rehabilitation-center-update',
  templateUrl: './rehabilitation-center-update.component.html'
})
export class RehabilitationCenterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    telephone: [],
    deleted: [],
    status: []
  });

  constructor(
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rehabilitationCenter }) => {
      this.updateForm(rehabilitationCenter);
    });
  }

  updateForm(rehabilitationCenter: IRehabilitationCenter): void {
    this.editForm.patchValue({
      id: rehabilitationCenter.id,
      name: rehabilitationCenter.name,
      telephone: rehabilitationCenter.telephone,
      deleted: rehabilitationCenter.deleted,
      status: rehabilitationCenter.status
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rehabilitationCenter = this.createFromForm();
    if (rehabilitationCenter.id !== undefined) {
      this.subscribeToSaveResponse(this.rehabilitationCenterService.update(rehabilitationCenter));
    } else {
      this.subscribeToSaveResponse(this.rehabilitationCenterService.create(rehabilitationCenter));
    }
  }

  private createFromForm(): IRehabilitationCenter {
    return {
      ...new RehabilitationCenter(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      status: this.editForm.get(['status'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRehabilitationCenter>>): void {
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
}
