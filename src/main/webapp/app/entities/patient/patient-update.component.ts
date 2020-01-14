import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPatient, Patient } from 'app/shared/model/patient.model';
import { PatientService } from './patient.service';

@Component({
  selector: 'jhi-patient-update',
  templateUrl: './patient-update.component.html'
})
export class PatientUpdateComponent implements OnInit {
  isSaving = false;

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
    deleted: [],
    scholarship: []
  });

  constructor(protected patientService: PatientService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patient }) => {
      this.updateForm(patient);
    });
  }

  updateForm(patient: IPatient): void {
    this.editForm.patchValue({
      id: patient.id,
      code: patient.code,
      age: patient.age,
      sex: patient.sex,
      ocupation: patient.ocupation,
      lastEventOcurred: patient.lastEventOcurred,
      deceased: patient.deceased,
      abandonment: patient.abandonment,
      abandonmentMedicCause: patient.abandonmentMedicCause,
      rehabStatus: patient.rehabStatus,
      sessionNumber: patient.sessionNumber,
      deleted: patient.deleted,
      scholarship: patient.scholarship
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
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
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      age: this.editForm.get(['age'])!.value,
      sex: this.editForm.get(['sex'])!.value,
      ocupation: this.editForm.get(['ocupation'])!.value,
      lastEventOcurred: this.editForm.get(['lastEventOcurred'])!.value,
      deceased: this.editForm.get(['deceased'])!.value,
      abandonment: this.editForm.get(['abandonment'])!.value,
      abandonmentMedicCause: this.editForm.get(['abandonmentMedicCause'])!.value,
      rehabStatus: this.editForm.get(['rehabStatus'])!.value,
      sessionNumber: this.editForm.get(['sessionNumber'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      scholarship: this.editForm.get(['scholarship'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPatient>>): void {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
