import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { CardioRehabCrSharedModule } from 'app/shared/shared.module';
import { CardioRehabCrCoreModule } from 'app/core/core.module';
import { CardioRehabCrAppRoutingModule } from './app-routing.module';
import { CardioRehabCrHomeModule } from './home/home.module';
import { CardioRehabCrEntityModule } from './entities/entity.module';
import { GlobalVariablesService } from 'app/shared/util/global-variables.service';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import { FlexLayoutModule } from '@angular/flex-layout';
import { JhiMainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { LoginComponent } from './shared/login/login-cardio.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
// Angular material
import { JhMaterialModule } from './shared/util/jhi-material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ConfirmDialogComponent } from './shared/util/confirm-dialog/confirm-dialog.component';
@NgModule({
  imports: [
    BrowserModule,
    CardioRehabCrSharedModule,
    CardioRehabCrCoreModule,
    CardioRehabCrHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    CardioRehabCrEntityModule,
    CardioRehabCrAppRoutingModule,
    FlexLayoutModule,
    JhMaterialModule,
    BrowserAnimationsModule
  ],
  exports: [JhMaterialModule],
  declarations: [
    JhiMainComponent,
    LoginComponent,
    NavbarComponent,
    ErrorComponent,
    PageRibbonComponent,
    ActiveMenuDirective,
    FooterComponent,
    ConfirmDialogComponent
  ],
  bootstrap: [JhiMainComponent],
  providers: [GlobalVariablesService],
  entryComponents: [ConfirmDialogComponent]
})
export class CardioRehabCrAppModule {}
