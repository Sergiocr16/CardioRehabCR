import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { RehabilitationGroupComponent } from './rehabilitation-group.component';
import { RehabilitationGroupDetailComponent } from './rehabilitation-group-detail.component';
import { RehabilitationGroupUpdateComponent } from './rehabilitation-group-update.component';
import { ChartsModule } from 'ng2-charts';

import {
  RehabilitationGroupDeletePopupComponent,
  RehabilitationGroupDeleteDialogComponent
} from './rehabilitation-group-delete-dialog.component';
import { rehabilitationGroupRoute, rehabilitationGroupPopupRoute } from './rehabilitation-group.route';
import { RehabilitationGroupPanelComponent } from 'app/entities/rehabilitation-group/rehabilitation-group-panel.component';
import { RehabilitationGroupClinicalCharacteristicsComponent } from 'app/entities/rehabilitation-group/rehabilitation-group-clinical-characteristics.component';

const ENTITY_STATES = [...rehabilitationGroupRoute, ...rehabilitationGroupPopupRoute];

@NgModule({
  imports: [CardioRehabCrSharedModule, RouterModule.forChild(ENTITY_STATES), ChartsModule],
  declarations: [
    RehabilitationGroupComponent,
    RehabilitationGroupDetailComponent,
    RehabilitationGroupUpdateComponent,
    RehabilitationGroupDeleteDialogComponent,
    RehabilitationGroupDeletePopupComponent,
    RehabilitationGroupPanelComponent,
    RehabilitationGroupClinicalCharacteristicsComponent
  ],
  entryComponents: [RehabilitationGroupDeleteDialogComponent]
})
export class CardioRehabCrRehabilitationGroupModule {}
