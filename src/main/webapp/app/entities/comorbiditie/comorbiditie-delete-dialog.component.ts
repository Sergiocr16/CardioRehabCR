import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComorbiditie } from 'app/shared/model/comorbiditie.model';
import { ComorbiditieService } from './comorbiditie.service';

@Component({
  templateUrl: './comorbiditie-delete-dialog.component.html'
})
export class ComorbiditieDeleteDialogComponent {
  comorbiditie?: IComorbiditie;

  constructor(
    protected comorbiditieService: ComorbiditieService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.comorbiditieService.delete(id).subscribe(() => {
      this.eventManager.broadcast('comorbiditieListModification');
      this.activeModal.close();
    });
  }
}
