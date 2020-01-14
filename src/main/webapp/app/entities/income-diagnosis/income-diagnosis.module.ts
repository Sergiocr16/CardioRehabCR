import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { IncomeDiagnosisComponent } from './income-diagnosis.component';
import { IncomeDiagnosisDetailComponent } from './income-diagnosis-detail.component';
import { IncomeDiagnosisUpdateComponent } from './income-diagnosis-update.component';
import { IncomeDiagnosisDeleteDialogComponent } from './income-diagnosis-delete-dialog.component';
import { incomeDiagnosisRoute } from './income-diagnosis.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(incomeDiagnosisRoute)],
  declarations: [
    IncomeDiagnosisComponent,
    IncomeDiagnosisDetailComponent,
    IncomeDiagnosisUpdateComponent,
    IncomeDiagnosisDeleteDialogComponent
  ],
  entryComponents: [IncomeDiagnosisDeleteDialogComponent]
})
export class CardioRehabCrIncomeDiagnosisModule {}
