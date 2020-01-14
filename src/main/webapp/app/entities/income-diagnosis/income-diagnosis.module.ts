import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { IncomeDiagnosisComponent } from './income-diagnosis.component';
import { IncomeDiagnosisDetailComponent } from './income-diagnosis-detail.component';
import { IncomeDiagnosisUpdateComponent } from './income-diagnosis-update.component';
import { IncomeDiagnosisDeletePopupComponent, IncomeDiagnosisDeleteDialogComponent } from './income-diagnosis-delete-dialog.component';
import { incomeDiagnosisRoute, incomeDiagnosisPopupRoute } from './income-diagnosis.route';

const ENTITY_STATES = [...incomeDiagnosisRoute, ...incomeDiagnosisPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    IncomeDiagnosisComponent,
    IncomeDiagnosisDetailComponent,
    IncomeDiagnosisUpdateComponent,
    IncomeDiagnosisDeleteDialogComponent,
    IncomeDiagnosisDeletePopupComponent
  ],
  entryComponents: [IncomeDiagnosisDeleteDialogComponent]
})
export class CardioRehabCrIncomeDiagnosisModule {}
