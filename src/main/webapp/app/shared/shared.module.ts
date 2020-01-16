import { NgModule } from '@angular/core';
import { CardioRehabCrSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { MaterialModule } from 'app/shared/util/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NoContentComponent } from 'app/layouts/no-content/no-content.component';
import { ChartsModule } from 'ng2-charts';

@NgModule({
  imports: [CardioRehabCrSharedLibsModule, MaterialModule, FlexLayoutModule, ChartsModule],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    NoContentComponent
  ],
  entryComponents: [LoginModalComponent],
  exports: [
    CardioRehabCrSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    MaterialModule,
    FlexLayoutModule,
    NoContentComponent
  ]
})
export class CardioRehabCrSharedModule {}
