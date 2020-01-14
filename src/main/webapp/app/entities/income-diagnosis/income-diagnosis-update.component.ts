import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IIncomeDiagnosis, IncomeDiagnosis } from 'app/shared/model/income-diagnosis.model';
import { IncomeDiagnosisService } from './income-diagnosis.service';
import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from 'app/entities/rehabilitation-center/rehabilitation-center.service';

@Component({
  selector: 'jhi-income-diagnosis-update',
  templateUrl: './income-diagnosis-update.component.html'
})
export class IncomeDiagnosisUpdateComponent implements OnInit {
  isSaving = false;

  rehabilitationcenters: IRehabilitationCenter[] = [];

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.required]],
    deleted: [],
    rehabilitationCenterId: []
  });

  constructor(
    protected incomeDiagnosisService: IncomeDiagnosisService,
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ incomeDiagnosis }) => {
      this.updateForm(incomeDiagnosis);

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

  updateForm(incomeDiagnosis: IIncomeDiagnosis): void {
    this.editForm.patchValue({
      id: incomeDiagnosis.id,
      description: incomeDiagnosis.description,
      deleted: incomeDiagnosis.deleted,
      rehabilitationCenterId: incomeDiagnosis.rehabilitationCenterId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const incomeDiagnosis = this.createFromForm();
    if (incomeDiagnosis.id !== undefined) {
      this.subscribeToSaveResponse(this.incomeDiagnosisService.update(incomeDiagnosis));
    } else {
      this.subscribeToSaveResponse(this.incomeDiagnosisService.create(incomeDiagnosis));
    }
  }

  private createFromForm(): IIncomeDiagnosis {
    return {
      ...new IncomeDiagnosis(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      rehabilitationCenterId: this.editForm.get(['rehabilitationCenterId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIncomeDiagnosis>>): void {
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
