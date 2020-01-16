import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { MinorEventsSessionComponent } from './minor-events-session.component';
import { MinorEventsSessionDetailComponent } from './minor-events-session-detail.component';
import { MinorEventsSessionUpdateComponent } from './minor-events-session-update.component';
import { MinorEventsSessionDeleteDialogComponent } from './minor-events-session-delete-dialog.component';
import { minorEventsSessionRoute } from './minor-events-session.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(minorEventsSessionRoute)],
  declarations: [
    MinorEventsSessionComponent,
    MinorEventsSessionDetailComponent,
    MinorEventsSessionUpdateComponent,
    MinorEventsSessionDeleteDialogComponent
  ],
  entryComponents: [MinorEventsSessionDeleteDialogComponent]
})
export class CardioRehabCrMinorEventsSessionModule {}
