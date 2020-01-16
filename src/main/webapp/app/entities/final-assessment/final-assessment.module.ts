import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { FinalAssessmentComponent } from './final-assessment.component';
import { FinalAssessmentDetailComponent } from './final-assessment-detail.component';
import { FinalAssessmentUpdateComponent } from './final-assessment-update.component';
import { FinalAssessmentDeletePopupComponent, FinalAssessmentDeleteDialogComponent } from './final-assessment-delete-dialog.component';
import { finalAssessmentRoute, finalAssessmentPopupRoute } from './final-assessment.route';

const ENTITY_STATES = [...finalAssessmentRoute, ...finalAssessmentPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    FinalAssessmentComponent,
    FinalAssessmentDetailComponent,
    FinalAssessmentUpdateComponent,
    FinalAssessmentDeleteDialogComponent,
    FinalAssessmentDeletePopupComponent
  ],
  entryComponents: [FinalAssessmentDeleteDialogComponent]
})
export class CardioRehabCrFinalAssessmentModule {}
