import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { ComorbiditieComponent } from './comorbiditie.component';
import { ComorbiditieDetailComponent } from './comorbiditie-detail.component';
import { ComorbiditieUpdateComponent } from './comorbiditie-update.component';
import { ComorbiditieDeletePopupComponent, ComorbiditieDeleteDialogComponent } from './comorbiditie-delete-dialog.component';
import { comorbiditieRoute, comorbiditiePopupRoute } from './comorbiditie.route';

const ENTITY_STATES = [...comorbiditieRoute, ...comorbiditiePopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ComorbiditieComponent,
    ComorbiditieDetailComponent,
    ComorbiditieUpdateComponent,
    ComorbiditieDeleteDialogComponent,
    ComorbiditieDeletePopupComponent
  ],
  entryComponents: [ComorbiditieDeleteDialogComponent]
})
export class CardioRehabCrComorbiditieModule {}
