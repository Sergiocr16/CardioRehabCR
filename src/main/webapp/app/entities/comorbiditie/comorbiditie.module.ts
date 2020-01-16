import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { ComorbiditieComponent } from './comorbiditie.component';
import { ComorbiditieDetailComponent } from './comorbiditie-detail.component';
import { ComorbiditieUpdateComponent } from './comorbiditie-update.component';
import { ComorbiditieDeleteDialogComponent } from './comorbiditie-delete-dialog.component';
import { comorbiditieRoute } from './comorbiditie.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(comorbiditieRoute)],
  declarations: [ComorbiditieComponent, ComorbiditieDetailComponent, ComorbiditieUpdateComponent, ComorbiditieDeleteDialogComponent],
  entryComponents: [ComorbiditieDeleteDialogComponent]
})
export class CardioRehabCrComorbiditieModule {}
