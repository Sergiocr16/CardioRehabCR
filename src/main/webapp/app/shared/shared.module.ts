import { NgModule } from '@angular/core';
import { CardioRehabCrSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { JhiAlertComponent } from './alert/alert.component';
import { JhiAlertErrorComponent } from './alert/alert-error.component';
import { JhiLoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { MaterialModule } from 'app/shared/util/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NoContentComponent } from 'app/layouts/no-content/no-content.component';
import { ChartsModule } from 'ng2-charts';

@NgModule({
  imports: [CardioRehabCrSharedLibsModule, MaterialModule, FlexLayoutModule, ChartsModule],
  declarations: [
    FindLanguageFromKeyPipe,
    JhiAlertComponent,
    JhiAlertErrorComponent,
    JhiLoginModalComponent,
    HasAnyAuthorityDirective,
    NoContentComponent
  ],
  entryComponents: [JhiLoginModalComponent],
  exports: [
    CardioRehabCrSharedLibsModule,
    FindLanguageFromKeyPipe,
    JhiAlertComponent,
    JhiAlertErrorComponent,
    JhiLoginModalComponent,
    HasAnyAuthorityDirective,
    MaterialModule,
    FlexLayoutModule,
    NoContentComponent
  ]
})
export class CardioRehabCrSharedModule {}
