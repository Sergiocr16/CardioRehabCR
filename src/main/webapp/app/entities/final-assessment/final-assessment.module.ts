import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { FinalAssessmentComponent } from './final-assessment.component';
import { FinalAssessmentDetailComponent } from './final-assessment-detail.component';
import { FinalAssessmentUpdateComponent } from './final-assessment-update.component';
import { FinalAssessmentDeleteDialogComponent } from './final-assessment-delete-dialog.component';
import { finalAssessmentRoute } from './final-assessment.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(finalAssessmentRoute)],
  declarations: [
    FinalAssessmentComponent,
    FinalAssessmentDetailComponent,
    FinalAssessmentUpdateComponent,
    FinalAssessmentDeleteDialogComponent
  ],
  entryComponents: [FinalAssessmentDeleteDialogComponent]
})
export class CardioRehabCrFinalAssessmentModule {}
