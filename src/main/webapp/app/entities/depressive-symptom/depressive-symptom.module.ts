import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { DepressiveSymptomComponent } from './depressive-symptom.component';
import { DepressiveSymptomDetailComponent } from './depressive-symptom-detail.component';
import { DepressiveSymptomUpdateComponent } from './depressive-symptom-update.component';
import {
  DepressiveSymptomDeletePopupComponent,
  DepressiveSymptomDeleteDialogComponent
} from './depressive-symptom-delete-dialog.component';
import { depressiveSymptomRoute, depressiveSymptomPopupRoute } from './depressive-symptom.route';

const ENTITY_STATES = [...depressiveSymptomRoute, ...depressiveSymptomPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DepressiveSymptomComponent,
    DepressiveSymptomDetailComponent,
    DepressiveSymptomUpdateComponent,
    DepressiveSymptomDeleteDialogComponent,
    DepressiveSymptomDeletePopupComponent
  ],
  entryComponents: [DepressiveSymptomDeleteDialogComponent]
})
export class CardioRehabCrDepressiveSymptomModule {}
