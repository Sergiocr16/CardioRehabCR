import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { RehabilitationGroupComponent } from './rehabilitation-group.component';
import { RehabilitationGroupDetailComponent } from './rehabilitation-group-detail.component';
import { RehabilitationGroupUpdateComponent } from './rehabilitation-group-update.component';
import { RehabilitationGroupDeleteDialogComponent } from './rehabilitation-group-delete-dialog.component';
import { rehabilitationGroupRoute } from './rehabilitation-group.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(rehabilitationGroupRoute)],
  declarations: [
    RehabilitationGroupComponent,
    RehabilitationGroupDetailComponent,
    RehabilitationGroupUpdateComponent,
    RehabilitationGroupDeleteDialogComponent
  ],
  entryComponents: [RehabilitationGroupDeleteDialogComponent]
})
export class CardioRehabCrRehabilitationGroupModule {}
