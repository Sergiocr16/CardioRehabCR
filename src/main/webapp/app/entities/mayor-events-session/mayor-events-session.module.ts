import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { MayorEventsSessionComponent } from './mayor-events-session.component';
import { MayorEventsSessionDetailComponent } from './mayor-events-session-detail.component';
import { MayorEventsSessionUpdateComponent } from './mayor-events-session-update.component';
import {
  MayorEventsSessionDeletePopupComponent,
  MayorEventsSessionDeleteDialogComponent
} from './mayor-events-session-delete-dialog.component';
import { mayorEventsSessionRoute, mayorEventsSessionPopupRoute } from './mayor-events-session.route';

const ENTITY_STATES = [...mayorEventsSessionRoute, ...mayorEventsSessionPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MayorEventsSessionComponent,
    MayorEventsSessionDetailComponent,
    MayorEventsSessionUpdateComponent,
    MayorEventsSessionDeleteDialogComponent,
    MayorEventsSessionDeletePopupComponent
  ],
  entryComponents: [MayorEventsSessionDeleteDialogComponent]
})
export class CardioRehabCrMayorEventsSessionModule {}
