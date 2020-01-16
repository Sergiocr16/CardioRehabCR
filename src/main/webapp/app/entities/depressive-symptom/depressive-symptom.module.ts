import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { DepressiveSymptomComponent } from './depressive-symptom.component';
import { DepressiveSymptomDetailComponent } from './depressive-symptom-detail.component';
import { DepressiveSymptomUpdateComponent } from './depressive-symptom-update.component';
import { DepressiveSymptomDeleteDialogComponent } from './depressive-symptom-delete-dialog.component';
import { depressiveSymptomRoute } from './depressive-symptom.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(depressiveSymptomRoute)],
  declarations: [
    DepressiveSymptomComponent,
    DepressiveSymptomDetailComponent,
    DepressiveSymptomUpdateComponent,
    DepressiveSymptomDeleteDialogComponent
  ],
  entryComponents: [DepressiveSymptomDeleteDialogComponent]
})
export class CardioRehabCrDepressiveSymptomModule {}
