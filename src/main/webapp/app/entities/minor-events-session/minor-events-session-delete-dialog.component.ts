import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMinorEventsSession } from 'app/shared/model/minor-events-session.model';
import { MinorEventsSessionService } from './minor-events-session.service';

@Component({
  templateUrl: './minor-events-session-delete-dialog.component.html'
})
export class MinorEventsSessionDeleteDialogComponent {
  minorEventsSession?: IMinorEventsSession;

  constructor(
    protected minorEventsSessionService: MinorEventsSessionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.minorEventsSessionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('minorEventsSessionListModification');
      this.activeModal.close();
    });
  }
}
