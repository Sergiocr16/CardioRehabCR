import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRehabilitationGroup, RehabilitationGroup } from 'app/shared/model/rehabilitation-group.model';
import { RehabilitationGroupService } from './rehabilitation-group.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';
import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from 'app/entities/rehabilitation-center/rehabilitation-center.service';

type SelectableEntity = IPatient | IRehabilitationCenter;

@Component({
  selector: 'jhi-rehabilitation-group-update',
  templateUrl: './rehabilitation-group-update.component.html'
})
export class RehabilitationGroupUpdateComponent implements OnInit {
  isSaving = false;

  patients: IPatient[] = [];

  rehabilitationcenters: IRehabilitationCenter[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    creationDate: [],
    programStatus: [],
    deleted: [],
    patients: [],
    rehabilitationCenterId: []
  });

  constructor(
    protected rehabilitationGroupService: RehabilitationGroupService,
    protected patientService: PatientService,
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rehabilitationGroup }) => {
      this.updateForm(rehabilitationGroup);

      this.patientService
        .query()
        .pipe(
          map((res: HttpResponse<IPatient[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IPatient[]) => (this.patients = resBody));

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

  updateForm(rehabilitationGroup: IRehabilitationGroup): void {
    this.editForm.patchValue({
      id: rehabilitationGroup.id,
      name: rehabilitationGroup.name,
      creationDate: rehabilitationGroup.creationDate != null ? rehabilitationGroup.creationDate.format(DATE_TIME_FORMAT) : null,
      programStatus: rehabilitationGroup.programStatus,
      deleted: rehabilitationGroup.deleted,
      patients: rehabilitationGroup.patients,
      rehabilitationCenterId: rehabilitationGroup.rehabilitationCenterId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rehabilitationGroup = this.createFromForm();
    if (rehabilitationGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.rehabilitationGroupService.update(rehabilitationGroup));
    } else {
      this.subscribeToSaveResponse(this.rehabilitationGroupService.create(rehabilitationGroup));
    }
  }

  private createFromForm(): IRehabilitationGroup {
    return {
      ...new RehabilitationGroup(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      creationDate:
        this.editForm.get(['creationDate'])!.value != null
          ? moment(this.editForm.get(['creationDate'])!.value, DATE_TIME_FORMAT)
          : undefined,
      programStatus: this.editForm.get(['programStatus'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      patients: this.editForm.get(['patients'])!.value,
      rehabilitationCenterId: this.editForm.get(['rehabilitationCenterId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRehabilitationGroup>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IPatient[], option: IPatient): IPatient {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
