import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { MayorEventsSessionComponent } from './mayor-events-session.component';
import { MayorEventsSessionDetailComponent } from './mayor-events-session-detail.component';
import { MayorEventsSessionUpdateComponent } from './mayor-events-session-update.component';
import { MayorEventsSessionDeleteDialogComponent } from './mayor-events-session-delete-dialog.component';
import { mayorEventsSessionRoute } from './mayor-events-session.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(mayorEventsSessionRoute)],
  declarations: [
    MayorEventsSessionComponent,
    MayorEventsSessionDetailComponent,
    MayorEventsSessionUpdateComponent,
    MayorEventsSessionDeleteDialogComponent
  ],
  entryComponents: [MayorEventsSessionDeleteDialogComponent]
})
export class CardioRehabCrMayorEventsSessionModule {}
