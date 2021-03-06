import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { RehabilitationCenterComponent } from './rehabilitation-center.component';
import { RehabilitationCenterDetailComponent } from './rehabilitation-center-detail.component';
import { RehabilitationCenterUpdateComponent } from './rehabilitation-center-update.component';
import {
  RehabilitationCenterDeletePopupComponent,
  RehabilitationCenterDeleteDialogComponent
} from './rehabilitation-center-delete-dialog.component';
import { rehabilitationCenterRoute, rehabilitationCenterPopupRoute } from './rehabilitation-center.route';
import { FlexLayoutModule } from '@angular/flex-layout';

const ENTITY_STATES = [...rehabilitationCenterRoute, ...rehabilitationCenterPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES), FlexLayoutModule],
  declarations: [
    RehabilitationCenterComponent,
    RehabilitationCenterDetailComponent,
    RehabilitationCenterUpdateComponent,
    RehabilitationCenterDeleteDialogComponent,
    RehabilitationCenterDeletePopupComponent
  ],
  exports: [],
  entryComponents: [RehabilitationCenterDeleteDialogComponent]
})
export class CardioRehabCrRehabilitationCenterModule {}
