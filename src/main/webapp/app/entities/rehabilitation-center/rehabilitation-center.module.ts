import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { RehabilitationCenterComponent } from './rehabilitation-center.component';
import { RehabilitationCenterDetailComponent } from './rehabilitation-center-detail.component';
import { RehabilitationCenterUpdateComponent } from './rehabilitation-center-update.component';
import { RehabilitationCenterDeleteDialogComponent } from './rehabilitation-center-delete-dialog.component';
import { rehabilitationCenterRoute } from './rehabilitation-center.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(rehabilitationCenterRoute)],
  declarations: [
    RehabilitationCenterComponent,
    RehabilitationCenterDetailComponent,
    RehabilitationCenterUpdateComponent,
    RehabilitationCenterDeleteDialogComponent
  ],
  entryComponents: [RehabilitationCenterDeleteDialogComponent]
})
export class CardioRehabCrRehabilitationCenterModule {}
