import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { NonSpecificPainsSessionComponent } from './non-specific-pains-session.component';
import { NonSpecificPainsSessionDetailComponent } from './non-specific-pains-session-detail.component';
import { NonSpecificPainsSessionUpdateComponent } from './non-specific-pains-session-update.component';
import {
  NonSpecificPainsSessionDeletePopupComponent,
  NonSpecificPainsSessionDeleteDialogComponent
} from './non-specific-pains-session-delete-dialog.component';
import { nonSpecificPainsSessionRoute, nonSpecificPainsSessionPopupRoute } from './non-specific-pains-session.route';

const ENTITY_STATES = [...nonSpecificPainsSessionRoute, ...nonSpecificPainsSessionPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    NonSpecificPainsSessionComponent,
    NonSpecificPainsSessionDetailComponent,
    NonSpecificPainsSessionUpdateComponent,
    NonSpecificPainsSessionDeleteDialogComponent,
    NonSpecificPainsSessionDeletePopupComponent
  ],
  entryComponents: [NonSpecificPainsSessionDeleteDialogComponent]
})
export class CardioRehabCrNonSpecificPainsSessionModule {}
