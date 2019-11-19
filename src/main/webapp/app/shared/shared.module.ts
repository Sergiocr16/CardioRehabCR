import { NgModule } from '@angular/core';
import { CardioRehabCrSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { JhiAlertComponent } from './alert/alert.component';
import { JhiAlertErrorComponent } from './alert/alert-error.component';
import { JhiLoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { MaterialModule } from 'app/shared/util/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  imports: [CardioRehabCrSharedLibsModule, MaterialModule, FlexLayoutModule],
  declarations: [FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent, JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [
    CardioRehabCrSharedLibsModule,
    FindLanguageFromKeyPipe,
    JhiAlertComponent,
    JhiAlertErrorComponent,
    JhiLoginModalComponent,
    HasAnyAuthorityDirective,
    MaterialModule,
    FlexLayoutModule
  ]
})
export class CardioRehabCrSharedModule {}
