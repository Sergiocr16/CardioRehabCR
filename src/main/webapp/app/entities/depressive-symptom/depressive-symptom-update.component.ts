import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IDepressiveSymptom, DepressiveSymptom } from 'app/shared/model/depressive-symptom.model';
import { DepressiveSymptomService } from './depressive-symptom.service';
import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from 'app/entities/rehabilitation-center/rehabilitation-center.service';

@Component({
  selector: 'jhi-depressive-symptom-update',
  templateUrl: './depressive-symptom-update.component.html'
})
export class DepressiveSymptomUpdateComponent implements OnInit {
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
    protected depressiveSymptomService: DepressiveSymptomService,
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ depressiveSymptom }) => {
      this.updateForm(depressiveSymptom);

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

  updateForm(depressiveSymptom: IDepressiveSymptom): void {
    this.editForm.patchValue({
      id: depressiveSymptom.id,
      description: depressiveSymptom.description,
      code: depressiveSymptom.code,
      deleted: depressiveSymptom.deleted,
      rehabilitationCenterId: depressiveSymptom.rehabilitationCenterId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const depressiveSymptom = this.createFromForm();
    if (depressiveSymptom.id !== undefined) {
      this.subscribeToSaveResponse(this.depressiveSymptomService.update(depressiveSymptom));
    } else {
      this.subscribeToSaveResponse(this.depressiveSymptomService.create(depressiveSymptom));
    }
  }

  private createFromForm(): IDepressiveSymptom {
    return {
      ...new DepressiveSymptom(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      code: this.editForm.get(['code'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      rehabilitationCenterId: this.editForm.get(['rehabilitationCenterId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepressiveSymptom>>): void {
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
