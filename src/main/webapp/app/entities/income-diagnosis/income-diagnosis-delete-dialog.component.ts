import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIncomeDiagnosis } from 'app/shared/model/income-diagnosis.model';
import { IncomeDiagnosisService } from './income-diagnosis.service';

@Component({
  templateUrl: './income-diagnosis-delete-dialog.component.html'
})
export class IncomeDiagnosisDeleteDialogComponent {
  incomeDiagnosis?: IIncomeDiagnosis;

  constructor(
    protected incomeDiagnosisService: IncomeDiagnosisService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.incomeDiagnosisService.delete(id).subscribe(() => {
      this.eventManager.broadcast('incomeDiagnosisListModification');
      this.activeModal.close();
    });
  }
}
