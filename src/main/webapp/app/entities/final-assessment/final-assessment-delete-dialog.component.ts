import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFinalAssessment } from 'app/shared/model/final-assessment.model';
import { FinalAssessmentService } from './final-assessment.service';

@Component({
  templateUrl: './final-assessment-delete-dialog.component.html'
})
export class FinalAssessmentDeleteDialogComponent {
  finalAssessment?: IFinalAssessment;

  constructor(
    protected finalAssessmentService: FinalAssessmentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.finalAssessmentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('finalAssessmentListModification');
      this.activeModal.close();
    });
  }
}
