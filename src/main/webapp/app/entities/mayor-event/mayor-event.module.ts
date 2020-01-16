import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { MayorEventComponent } from './mayor-event.component';
import { MayorEventDetailComponent } from './mayor-event-detail.component';
import { MayorEventUpdateComponent } from './mayor-event-update.component';
import { MayorEventDeletePopupComponent, MayorEventDeleteDialogComponent } from './mayor-event-delete-dialog.component';
import { mayorEventRoute, mayorEventPopupRoute } from './mayor-event.route';

const ENTITY_STATES = [...mayorEventRoute, ...mayorEventPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MayorEventComponent,
    MayorEventDetailComponent,
    MayorEventUpdateComponent,
    MayorEventDeleteDialogComponent,
    MayorEventDeletePopupComponent
  ],
  entryComponents: [MayorEventDeleteDialogComponent]
})
export class CardioRehabCrMayorEventModule {}
