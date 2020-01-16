import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { InitialAssessmentComponent } from './initial-assessment.component';
import { InitialAssessmentDetailComponent } from './initial-assessment-detail.component';
import { InitialAssessmentUpdateComponent } from './initial-assessment-update.component';
import {
  InitialAssessmentDeletePopupComponent,
  InitialAssessmentDeleteDialogComponent
} from './initial-assessment-delete-dialog.component';
import { initialAssessmentRoute, initialAssessmentPopupRoute } from './initial-assessment.route';

const ENTITY_STATES = [...initialAssessmentRoute, ...initialAssessmentPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    InitialAssessmentComponent,
    InitialAssessmentDetailComponent,
    InitialAssessmentUpdateComponent,
    InitialAssessmentDeleteDialogComponent,
    InitialAssessmentDeletePopupComponent
  ],
  entryComponents: [InitialAssessmentDeleteDialogComponent]
})
export class CardioRehabCrInitialAssessmentModule {}
