import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInitialAssessment } from 'app/shared/model/initial-assessment.model';
import { InitialAssessmentService } from './initial-assessment.service';

@Component({
  templateUrl: './initial-assessment-delete-dialog.component.html'
})
export class InitialAssessmentDeleteDialogComponent {
  initialAssessment?: IInitialAssessment;

  constructor(
    protected initialAssessmentService: InitialAssessmentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.initialAssessmentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('initialAssessmentListModification');
      this.activeModal.close();
    });
  }
}
