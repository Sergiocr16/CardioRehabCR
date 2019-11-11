import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { NonSpecificPainComponent } from './non-specific-pain.component';
import { NonSpecificPainDetailComponent } from './non-specific-pain-detail.component';
import { NonSpecificPainUpdateComponent } from './non-specific-pain-update.component';
import { NonSpecificPainDeletePopupComponent, NonSpecificPainDeleteDialogComponent } from './non-specific-pain-delete-dialog.component';
import { nonSpecificPainRoute, nonSpecificPainPopupRoute } from './non-specific-pain.route';

const ENTITY_STATES = [...nonSpecificPainRoute, ...nonSpecificPainPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    NonSpecificPainComponent,
    NonSpecificPainDetailComponent,
    NonSpecificPainUpdateComponent,
    NonSpecificPainDeleteDialogComponent,
    NonSpecificPainDeletePopupComponent
  ],
  entryComponents: [NonSpecificPainDeleteDialogComponent]
})
export class CardioRehabCrNonSpecificPainModule {}
