import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { DepressiveSymptomsSessionComponent } from './depressive-symptoms-session.component';
import { DepressiveSymptomsSessionDetailComponent } from './depressive-symptoms-session-detail.component';
import { DepressiveSymptomsSessionUpdateComponent } from './depressive-symptoms-session-update.component';
import {
  DepressiveSymptomsSessionDeletePopupComponent,
  DepressiveSymptomsSessionDeleteDialogComponent
} from './depressive-symptoms-session-delete-dialog.component';
import { depressiveSymptomsSessionRoute, depressiveSymptomsSessionPopupRoute } from './depressive-symptoms-session.route';

const ENTITY_STATES = [...depressiveSymptomsSessionRoute, ...depressiveSymptomsSessionPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DepressiveSymptomsSessionComponent,
    DepressiveSymptomsSessionDetailComponent,
    DepressiveSymptomsSessionUpdateComponent,
    DepressiveSymptomsSessionDeleteDialogComponent,
    DepressiveSymptomsSessionDeletePopupComponent
  ],
  entryComponents: [DepressiveSymptomsSessionDeleteDialogComponent]
})
export class CardioRehabCrDepressiveSymptomsSessionModule {}
