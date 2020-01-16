import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from './rehabilitation-center.service';

@Component({
  templateUrl: './rehabilitation-center-delete-dialog.component.html'
})
export class RehabilitationCenterDeleteDialogComponent {
  rehabilitationCenter?: IRehabilitationCenter;

  constructor(
    protected rehabilitationCenterService: RehabilitationCenterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rehabilitationCenterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rehabilitationCenterListModification');
      this.activeModal.close();
    });
  }
}
