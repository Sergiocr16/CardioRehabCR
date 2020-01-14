import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { MinorEventsSessionComponent } from './minor-events-session.component';
import { MinorEventsSessionDetailComponent } from './minor-events-session-detail.component';
import { MinorEventsSessionUpdateComponent } from './minor-events-session-update.component';
import {
  MinorEventsSessionDeletePopupComponent,
  MinorEventsSessionDeleteDialogComponent
} from './minor-events-session-delete-dialog.component';
import { minorEventsSessionRoute, minorEventsSessionPopupRoute } from './minor-events-session.route';

const ENTITY_STATES = [...minorEventsSessionRoute, ...minorEventsSessionPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MinorEventsSessionComponent,
    MinorEventsSessionDetailComponent,
    MinorEventsSessionUpdateComponent,
    MinorEventsSessionDeleteDialogComponent,
    MinorEventsSessionDeletePopupComponent
  ],
  entryComponents: [MinorEventsSessionDeleteDialogComponent]
})
export class CardioRehabCrMinorEventsSessionModule {}
