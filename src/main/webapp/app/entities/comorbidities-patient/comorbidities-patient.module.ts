import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { ComorbiditiesPatientComponent } from './comorbidities-patient.component';
import { ComorbiditiesPatientDetailComponent } from './comorbidities-patient-detail.component';
import { ComorbiditiesPatientUpdateComponent } from './comorbidities-patient-update.component';
import { ComorbiditiesPatientDeleteDialogComponent } from './comorbidities-patient-delete-dialog.component';
import { comorbiditiesPatientRoute } from './comorbidities-patient.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(comorbiditiesPatientRoute)],
  declarations: [
    ComorbiditiesPatientComponent,
    ComorbiditiesPatientDetailComponent,
    ComorbiditiesPatientUpdateComponent,
    ComorbiditiesPatientDeleteDialogComponent
  ],
  entryComponents: [ComorbiditiesPatientDeleteDialogComponent]
})
export class CardioRehabCrComorbiditiesPatientModule {}
