import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { IncomeDiagnosisPatientComponent } from './income-diagnosis-patient.component';
import { IncomeDiagnosisPatientDetailComponent } from './income-diagnosis-patient-detail.component';
import { IncomeDiagnosisPatientUpdateComponent } from './income-diagnosis-patient-update.component';
import { IncomeDiagnosisPatientDeleteDialogComponent } from './income-diagnosis-patient-delete-dialog.component';
import { incomeDiagnosisPatientRoute } from './income-diagnosis-patient.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(incomeDiagnosisPatientRoute)],
  declarations: [
    IncomeDiagnosisPatientComponent,
    IncomeDiagnosisPatientDetailComponent,
    IncomeDiagnosisPatientUpdateComponent,
    IncomeDiagnosisPatientDeleteDialogComponent
  ],
  entryComponents: [IncomeDiagnosisPatientDeleteDialogComponent]
})
export class CardioRehabCrIncomeDiagnosisPatientModule {}
