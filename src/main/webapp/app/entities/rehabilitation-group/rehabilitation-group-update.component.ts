import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IRehabilitationGroup, RehabilitationGroup } from 'app/shared/model/rehabilitation-group.model';
import { RehabilitationGroupService } from './rehabilitation-group.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';
import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from 'app/entities/rehabilitation-center/rehabilitation-center.service';

@Component({
  selector: 'jhi-rehabilitation-group-update',
  templateUrl: './rehabilitation-group-update.component.html'
})
export class RehabilitationGroupUpdateComponent implements OnInit {
  isSaving: boolean;

  patients: IPatient[];

  rehabilitationcenters: IRehabilitationCenter[];

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
    protected jhiAlertService: JhiAlertService,
    protected rehabilitationGroupService: RehabilitationGroupService,
    protected patientService: PatientService,
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ rehabilitationGroup }) => {
      this.updateForm(rehabilitationGroup);
    });
    this.patientService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPatient[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPatient[]>) => response.body)
      )
      .subscribe((res: IPatient[]) => (this.patients = res), (res: HttpErrorResponse) => this.onError(res.message));
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

  updateForm(rehabilitationGroup: IRehabilitationGroup) {
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

  previousState() {
    window.history.back();
  }

  save() {
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
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      creationDate:
        this.editForm.get(['creationDate']).value != null ? moment(this.editForm.get(['creationDate']).value, DATE_TIME_FORMAT) : undefined,
      programStatus: this.editForm.get(['programStatus']).value,
      deleted: this.editForm.get(['deleted']).value,
      patients: this.editForm.get(['patients']).value,
      rehabilitationCenterId: this.editForm.get(['rehabilitationCenterId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRehabilitationGroup>>) {
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

  trackPatientById(index: number, item: IPatient) {
    return item.id;
  }

  trackRehabilitationCenterById(index: number, item: IRehabilitationCenter) {
    return item.id;
  }

  getSelected(selectedVals: any[], option: any) {
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
