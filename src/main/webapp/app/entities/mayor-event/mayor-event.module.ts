import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { MayorEventComponent } from './mayor-event.component';
import { MayorEventDetailComponent } from './mayor-event-detail.component';
import { MayorEventUpdateComponent } from './mayor-event-update.component';
import { MayorEventDeleteDialogComponent } from './mayor-event-delete-dialog.component';
import { mayorEventRoute } from './mayor-event.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(mayorEventRoute)],
  declarations: [MayorEventComponent, MayorEventDetailComponent, MayorEventUpdateComponent, MayorEventDeleteDialogComponent],
  entryComponents: [MayorEventDeleteDialogComponent]
})
export class CardioRehabCrMayorEventModule {}
