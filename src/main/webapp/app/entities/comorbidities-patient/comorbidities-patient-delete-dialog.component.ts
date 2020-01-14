import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComorbiditiesPatient } from 'app/shared/model/comorbidities-patient.model';
import { ComorbiditiesPatientService } from './comorbidities-patient.service';

@Component({
  templateUrl: './comorbidities-patient-delete-dialog.component.html'
})
export class ComorbiditiesPatientDeleteDialogComponent {
  comorbiditiesPatient?: IComorbiditiesPatient;

  constructor(
    protected comorbiditiesPatientService: ComorbiditiesPatientService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.comorbiditiesPatientService.delete(id).subscribe(() => {
      this.eventManager.broadcast('comorbiditiesPatientListModification');
      this.activeModal.close();
    });
  }
}
