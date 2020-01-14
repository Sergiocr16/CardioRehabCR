import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INonSpecificPain } from 'app/shared/model/non-specific-pain.model';
import { NonSpecificPainService } from './non-specific-pain.service';

@Component({
  templateUrl: './non-specific-pain-delete-dialog.component.html'
})
export class NonSpecificPainDeleteDialogComponent {
  nonSpecificPain?: INonSpecificPain;

  constructor(
    protected nonSpecificPainService: NonSpecificPainService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.nonSpecificPainService.delete(id).subscribe(() => {
      this.eventManager.broadcast('nonSpecificPainListModification');
      this.activeModal.close();
    });
  }
}
