import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
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
  isSaving: boolean;

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

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ rehabilitationCenter }) => {
      this.updateForm(rehabilitationCenter);
    });
  }

  updateForm(rehabilitationCenter: IRehabilitationCenter) {
    this.editForm.patchValue({
      id: rehabilitationCenter.id,
      name: rehabilitationCenter.name,
      telephone: rehabilitationCenter.telephone,
      deleted: rehabilitationCenter.deleted,
      status: rehabilitationCenter.status
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
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
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      telephone: this.editForm.get(['telephone']).value,
      deleted: this.editForm.get(['deleted']).value,
      status: this.editForm.get(['status']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRehabilitationCenter>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
