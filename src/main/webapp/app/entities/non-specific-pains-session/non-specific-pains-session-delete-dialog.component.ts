import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INonSpecificPainsSession } from 'app/shared/model/non-specific-pains-session.model';
import { NonSpecificPainsSessionService } from './non-specific-pains-session.service';

@Component({
  templateUrl: './non-specific-pains-session-delete-dialog.component.html'
})
export class NonSpecificPainsSessionDeleteDialogComponent {
  nonSpecificPainsSession?: INonSpecificPainsSession;

  constructor(
    protected nonSpecificPainsSessionService: NonSpecificPainsSessionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.nonSpecificPainsSessionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('nonSpecificPainsSessionListModification');
      this.activeModal.close();
    });
  }
}
