import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMinorEvent } from 'app/shared/model/minor-event.model';
import { MinorEventService } from './minor-event.service';

@Component({
  templateUrl: './minor-event-delete-dialog.component.html'
})
export class MinorEventDeleteDialogComponent {
  minorEvent?: IMinorEvent;

  constructor(
    protected minorEventService: MinorEventService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.minorEventService.delete(id).subscribe(() => {
      this.eventManager.broadcast('minorEventListModification');
      this.activeModal.close();
    });
  }
}
