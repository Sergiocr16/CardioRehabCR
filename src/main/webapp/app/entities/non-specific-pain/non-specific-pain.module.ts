import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { NonSpecificPainComponent } from './non-specific-pain.component';
import { NonSpecificPainDetailComponent } from './non-specific-pain-detail.component';
import { NonSpecificPainUpdateComponent } from './non-specific-pain-update.component';
import { NonSpecificPainDeleteDialogComponent } from './non-specific-pain-delete-dialog.component';
import { nonSpecificPainRoute } from './non-specific-pain.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(nonSpecificPainRoute)],
  declarations: [
    NonSpecificPainComponent,
    NonSpecificPainDetailComponent,
    NonSpecificPainUpdateComponent,
    NonSpecificPainDeleteDialogComponent
  ],
  entryComponents: [NonSpecificPainDeleteDialogComponent]
})
export class CardioRehabCrNonSpecificPainModule {}
