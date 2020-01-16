import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDepressiveSymptomsSession } from 'app/shared/model/depressive-symptoms-session.model';
import { DepressiveSymptomsSessionService } from './depressive-symptoms-session.service';

@Component({
  templateUrl: './depressive-symptoms-session-delete-dialog.component.html'
})
export class DepressiveSymptomsSessionDeleteDialogComponent {
  depressiveSymptomsSession?: IDepressiveSymptomsSession;

  constructor(
    protected depressiveSymptomsSessionService: DepressiveSymptomsSessionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.depressiveSymptomsSessionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('depressiveSymptomsSessionListModification');
      this.activeModal.close();
    });
  }
}
