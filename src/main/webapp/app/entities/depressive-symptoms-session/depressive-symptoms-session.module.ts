import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { DepressiveSymptomsSessionComponent } from './depressive-symptoms-session.component';
import { DepressiveSymptomsSessionDetailComponent } from './depressive-symptoms-session-detail.component';
import { DepressiveSymptomsSessionUpdateComponent } from './depressive-symptoms-session-update.component';
import { DepressiveSymptomsSessionDeleteDialogComponent } from './depressive-symptoms-session-delete-dialog.component';
import { depressiveSymptomsSessionRoute } from './depressive-symptoms-session.route';

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(depressiveSymptomsSessionRoute)],
  declarations: [
    DepressiveSymptomsSessionComponent,
    DepressiveSymptomsSessionDetailComponent,
    DepressiveSymptomsSessionUpdateComponent,
    DepressiveSymptomsSessionDeleteDialogComponent
  ],
  entryComponents: [DepressiveSymptomsSessionDeleteDialogComponent]
})
export class CardioRehabCrDepressiveSymptomsSessionModule {}
