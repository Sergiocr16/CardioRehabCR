import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { MinorEventComponent } from './minor-event.component';
import { MinorEventDetailComponent } from './minor-event-detail.component';
import { MinorEventUpdateComponent } from './minor-event-update.component';
import { MinorEventDeleteDialogComponent } from './minor-event-delete-dialog.component';
import { minorEventRoute } from './minor-event.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(minorEventRoute)],
  declarations: [MinorEventComponent, MinorEventDetailComponent, MinorEventUpdateComponent, MinorEventDeleteDialogComponent],
  entryComponents: [MinorEventDeleteDialogComponent]
})
export class CardioRehabCrMinorEventModule {}
