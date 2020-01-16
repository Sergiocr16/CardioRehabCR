import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRehabilitationGroup } from 'app/shared/model/rehabilitation-group.model';
import { RehabilitationGroupService } from './rehabilitation-group.service';

@Component({
  templateUrl: './rehabilitation-group-delete-dialog.component.html'
})
export class RehabilitationGroupDeleteDialogComponent {
  rehabilitationGroup?: IRehabilitationGroup;

  constructor(
    protected rehabilitationGroupService: RehabilitationGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rehabilitationGroupService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rehabilitationGroupListModification');
      this.activeModal.close();
    });
  }
}
