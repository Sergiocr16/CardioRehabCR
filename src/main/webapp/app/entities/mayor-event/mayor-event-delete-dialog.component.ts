import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMayorEvent } from 'app/shared/model/mayor-event.model';
import { MayorEventService } from './mayor-event.service';

@Component({
  templateUrl: './mayor-event-delete-dialog.component.html'
})
export class MayorEventDeleteDialogComponent {
  mayorEvent?: IMayorEvent;

  constructor(
    protected mayorEventService: MayorEventService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mayorEventService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mayorEventListModification');
      this.activeModal.close();
    });
  }
}
