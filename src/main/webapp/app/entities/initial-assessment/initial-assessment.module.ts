import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { InitialAssessmentComponent } from './initial-assessment.component';
import { InitialAssessmentDetailComponent } from './initial-assessment-detail.component';
import { InitialAssessmentUpdateComponent } from './initial-assessment-update.component';
import { InitialAssessmentDeleteDialogComponent } from './initial-assessment-delete-dialog.component';
import { initialAssessmentRoute } from './initial-assessment.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(initialAssessmentRoute)],
  declarations: [
    InitialAssessmentComponent,
    InitialAssessmentDetailComponent,
    InitialAssessmentUpdateComponent,
    InitialAssessmentDeleteDialogComponent
  ],
  entryComponents: [InitialAssessmentDeleteDialogComponent]
})
export class CardioRehabCrInitialAssessmentModule {}
