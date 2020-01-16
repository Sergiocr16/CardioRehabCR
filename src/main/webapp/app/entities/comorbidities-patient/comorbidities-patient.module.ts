import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { ComorbiditiesPatientComponent } from './comorbidities-patient.component';
import { ComorbiditiesPatientDetailComponent } from './comorbidities-patient-detail.component';
import { ComorbiditiesPatientUpdateComponent } from './comorbidities-patient-update.component';
import {
  ComorbiditiesPatientDeletePopupComponent,
  ComorbiditiesPatientDeleteDialogComponent
} from './comorbidities-patient-delete-dialog.component';
import { comorbiditiesPatientRoute, comorbiditiesPatientPopupRoute } from './comorbidities-patient.route';

const ENTITY_STATES = [...comorbiditiesPatientRoute, ...comorbiditiesPatientPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ComorbiditiesPatientComponent,
    ComorbiditiesPatientDetailComponent,
    ComorbiditiesPatientUpdateComponent,
    ComorbiditiesPatientDeleteDialogComponent,
    ComorbiditiesPatientDeletePopupComponent
  ],
  entryComponents: [ComorbiditiesPatientDeleteDialogComponent]
})
export class CardioRehabCrComorbiditiesPatientModule {}
