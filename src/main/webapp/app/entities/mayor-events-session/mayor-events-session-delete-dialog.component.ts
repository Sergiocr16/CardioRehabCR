import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMayorEventsSession } from 'app/shared/model/mayor-events-session.model';
import { MayorEventsSessionService } from './mayor-events-session.service';

@Component({
  templateUrl: './mayor-events-session-delete-dialog.component.html'
})
export class MayorEventsSessionDeleteDialogComponent {
  mayorEventsSession?: IMayorEventsSession;

  constructor(
    protected mayorEventsSessionService: MayorEventsSessionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mayorEventsSessionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mayorEventsSessionListModification');
      this.activeModal.close();
    });
  }
}
