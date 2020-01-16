import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIncomeDiagnosisPatient } from 'app/shared/model/income-diagnosis-patient.model';
import { IncomeDiagnosisPatientService } from './income-diagnosis-patient.service';

@Component({
  templateUrl: './income-diagnosis-patient-delete-dialog.component.html'
})
export class IncomeDiagnosisPatientDeleteDialogComponent {
  incomeDiagnosisPatient?: IIncomeDiagnosisPatient;

  constructor(
    protected incomeDiagnosisPatientService: IncomeDiagnosisPatientService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.incomeDiagnosisPatientService.delete(id).subscribe(() => {
      this.eventManager.broadcast('incomeDiagnosisPatientListModification');
      this.activeModal.close();
    });
  }
}
