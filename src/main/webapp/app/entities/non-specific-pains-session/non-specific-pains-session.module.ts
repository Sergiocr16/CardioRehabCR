import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { NonSpecificPainsSessionComponent } from './non-specific-pains-session.component';
import { NonSpecificPainsSessionDetailComponent } from './non-specific-pains-session-detail.component';
import { NonSpecificPainsSessionUpdateComponent } from './non-specific-pains-session-update.component';
import { NonSpecificPainsSessionDeleteDialogComponent } from './non-specific-pains-session-delete-dialog.component';
import { nonSpecificPainsSessionRoute } from './non-specific-pains-session.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(nonSpecificPainsSessionRoute)],
  declarations: [
    NonSpecificPainsSessionComponent,
    NonSpecificPainsSessionDetailComponent,
    NonSpecificPainsSessionUpdateComponent,
    NonSpecificPainsSessionDeleteDialogComponent
  ],
  entryComponents: [NonSpecificPainsSessionDeleteDialogComponent]
})
export class CardioRehabCrNonSpecificPainsSessionModule {}
