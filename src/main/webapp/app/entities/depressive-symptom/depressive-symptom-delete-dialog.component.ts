import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDepressiveSymptom } from 'app/shared/model/depressive-symptom.model';
import { DepressiveSymptomService } from './depressive-symptom.service';

@Component({
  templateUrl: './depressive-symptom-delete-dialog.component.html'
})
export class DepressiveSymptomDeleteDialogComponent {
  depressiveSymptom?: IDepressiveSymptom;

  constructor(
    protected depressiveSymptomService: DepressiveSymptomService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.depressiveSymptomService.delete(id).subscribe(() => {
      this.eventManager.broadcast('depressiveSymptomListModification');
      this.activeModal.close();
    });
  }
}
