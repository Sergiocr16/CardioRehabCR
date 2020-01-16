import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { MinorEventComponent } from './minor-event.component';
import { MinorEventDetailComponent } from './minor-event-detail.component';
import { MinorEventUpdateComponent } from './minor-event-update.component';
import { MinorEventDeletePopupComponent, MinorEventDeleteDialogComponent } from './minor-event-delete-dialog.component';
import { minorEventRoute, minorEventPopupRoute } from './minor-event.route';

const ENTITY_STATES = [...minorEventRoute, ...minorEventPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MinorEventComponent,
    MinorEventDetailComponent,
    MinorEventUpdateComponent,
    MinorEventDeleteDialogComponent,
    MinorEventDeletePopupComponent
  ],
  entryComponents: [MinorEventDeleteDialogComponent]
})
export class CardioRehabCrMinorEventModule {}
