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
import { IPatient, Patient } from 'app/shared/model/patient.model';
import { PatientService } from './patient.service';
import { IRehabilitationGroup } from 'app/shared/model/rehabilitation-group.model';
import { RehabilitationGroupService } from 'app/entities/rehabilitation-group/rehabilitation-group.service';

@Component({
  selector: 'jhi-patient-update',
  templateUrl: './patient-update.component.html'
})
export class PatientUpdateComponent implements OnInit {
  isSaving: boolean;

  rehabilitationgroups: IRehabilitationGroup[];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    age: [null, [Validators.required]],
    sex: [null, [Validators.required]],
    ocupation: [null, [Validators.required]],
    lastEventOcurred: [null, [Validators.required]],
    deceased: [],
    abandonment: [],
    abandonmentMedicCause: [],
    rehabStatus: [],
    sessionNumber: [],
    deleted: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected patientService: PatientService,
    protected rehabilitationGroupService: RehabilitationGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ patient }) => {
      this.updateForm(patient);
    });
    this.rehabilitationGroupService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IRehabilitationGroup[]>) => mayBeOk.ok),
        map((response: HttpResponse<IRehabilitationGroup[]>) => response.body)
      )
      .subscribe((res: IRehabilitationGroup[]) => (this.rehabilitationgroups = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(patient: IPatient) {
    this.editForm.patchValue({
      id: patient.id,
      code: patient.code,
      age: patient.age,
      sex: patient.sex,
      ocupation: patient.ocupation,
      lastEventOcurred: patient.lastEventOcurred != null ? patient.lastEventOcurred.format(DATE_TIME_FORMAT) : null,
      deceased: patient.deceased,
      abandonment: patient.abandonment,
      abandonmentMedicCause: patient.abandonmentMedicCause,
      rehabStatus: patient.rehabStatus,
      sessionNumber: patient.sessionNumber,
      deleted: patient.deleted
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const patient = this.createFromForm();
    if (patient.id !== undefined) {
      this.subscribeToSaveResponse(this.patientService.update(patient));
    } else {
      this.subscribeToSaveResponse(this.patientService.create(patient));
    }
  }

  private createFromForm(): IPatient {
    return {
      ...new Patient(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      age: this.editForm.get(['age']).value,
      sex: this.editForm.get(['sex']).value,
      ocupation: this.editForm.get(['ocupation']).value,
      lastEventOcurred:
        this.editForm.get(['lastEventOcurred']).value != null
          ? moment(this.editForm.get(['lastEventOcurred']).value, DATE_TIME_FORMAT)
          : undefined,
      deceased: this.editForm.get(['deceased']).value,
      abandonment: this.editForm.get(['abandonment']).value,
      abandonmentMedicCause: this.editForm.get(['abandonmentMedicCause']).value,
      rehabStatus: this.editForm.get(['rehabStatus']).value,
      sessionNumber: this.editForm.get(['sessionNumber']).value,
      deleted: this.editForm.get(['deleted']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPatient>>) {
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

  trackRehabilitationGroupById(index: number, item: IRehabilitationGroup) {
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
