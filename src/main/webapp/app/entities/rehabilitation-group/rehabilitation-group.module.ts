import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { RehabilitationGroupComponent } from './rehabilitation-group.component';
import { RehabilitationGroupDetailComponent } from './rehabilitation-group-detail.component';
import { RehabilitationGroupUpdateComponent } from './rehabilitation-group-update.component';
import {
  RehabilitationGroupDeletePopupComponent,
  RehabilitationGroupDeleteDialogComponent
} from './rehabilitation-group-delete-dialog.component';
import { rehabilitationGroupRoute, rehabilitationGroupPopupRoute } from './rehabilitation-group.route';

const ENTITY_STATES = [...rehabilitationGroupRoute, ...rehabilitationGroupPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    RehabilitationGroupComponent,
    RehabilitationGroupDetailComponent,
    RehabilitationGroupUpdateComponent,
    RehabilitationGroupDeleteDialogComponent,
    RehabilitationGroupDeletePopupComponent
  ],
  entryComponents: [RehabilitationGroupDeleteDialogComponent]
})
export class CardioRehabCrRehabilitationGroupModule {}
