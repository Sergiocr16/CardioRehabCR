import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { IncomeDiagnosisPatientComponent } from './income-diagnosis-patient.component';
import { IncomeDiagnosisPatientDetailComponent } from './income-diagnosis-patient-detail.component';
import { IncomeDiagnosisPatientUpdateComponent } from './income-diagnosis-patient-update.component';
import {
  IncomeDiagnosisPatientDeletePopupComponent,
  IncomeDiagnosisPatientDeleteDialogComponent
} from './income-diagnosis-patient-delete-dialog.component';
import { incomeDiagnosisPatientRoute, incomeDiagnosisPatientPopupRoute } from './income-diagnosis-patient.route';

const ENTITY_STATES = [...incomeDiagnosisPatientRoute, ...incomeDiagnosisPatientPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    IncomeDiagnosisPatientComponent,
    IncomeDiagnosisPatientDetailComponent,
    IncomeDiagnosisPatientUpdateComponent,
    IncomeDiagnosisPatientDeleteDialogComponent,
    IncomeDiagnosisPatientDeletePopupComponent
  ],
  entryComponents: [IncomeDiagnosisPatientDeleteDialogComponent]
})
export class CardioRehabCrIncomeDiagnosisPatientModule {}
