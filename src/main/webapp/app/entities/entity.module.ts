import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'app-user',
        loadChildren: () => import('./app-user/app-user.module').then(m => m.CardioRehabCrAppUserModule)
      },
      {
        path: 'comorbiditie',
        loadChildren: () => import('./comorbiditie/comorbiditie.module').then(m => m.CardioRehabCrComorbiditieModule)
      },
      {
        path: 'comorbidities-patient',
        loadChildren: () =>
          import('./comorbidities-patient/comorbidities-patient.module').then(m => m.CardioRehabCrComorbiditiesPatientModule)
      },
      {
        path: 'depressive-symptom',
        loadChildren: () => import('./depressive-symptom/depressive-symptom.module').then(m => m.CardioRehabCrDepressiveSymptomModule)
      },
      {
        path: 'depressive-symptoms-session',
        loadChildren: () =>
          import('./depressive-symptoms-session/depressive-symptoms-session.module').then(
            m => m.CardioRehabCrDepressiveSymptomsSessionModule
          )
      },
      {
        path: 'final-assessment',
        loadChildren: () => import('./final-assessment/final-assessment.module').then(m => m.CardioRehabCrFinalAssessmentModule)
      },
      {
        path: 'income-diagnosis',
        loadChildren: () => import('./income-diagnosis/income-diagnosis.module').then(m => m.CardioRehabCrIncomeDiagnosisModule)
      },
      {
        path: 'income-diagnosis-patient',
        loadChildren: () =>
          import('./income-diagnosis-patient/income-diagnosis-patient.module').then(m => m.CardioRehabCrIncomeDiagnosisPatientModule)
      },
      {
        path: 'initial-assessment',
        loadChildren: () => import('./initial-assessment/initial-assessment.module').then(m => m.CardioRehabCrInitialAssessmentModule)
      },
      {
        path: 'mayor-event',
        loadChildren: () => import('./mayor-event/mayor-event.module').then(m => m.CardioRehabCrMayorEventModule)
      },
      {
        path: 'mayor-events-session',
        loadChildren: () => import('./mayor-events-session/mayor-events-session.module').then(m => m.CardioRehabCrMayorEventsSessionModule)
      },
      {
        path: 'minor-event',
        loadChildren: () => import('./minor-event/minor-event.module').then(m => m.CardioRehabCrMinorEventModule)
      },
      {
        path: 'minor-events-session',
        loadChildren: () => import('./minor-events-session/minor-events-session.module').then(m => m.CardioRehabCrMinorEventsSessionModule)
      },
      {
        path: 'non-specific-pain',
        loadChildren: () => import('./non-specific-pain/non-specific-pain.module').then(m => m.CardioRehabCrNonSpecificPainModule)
      },
      {
        path: 'non-specific-pains-session',
        loadChildren: () =>
          import('./non-specific-pains-session/non-specific-pains-session.module').then(m => m.CardioRehabCrNonSpecificPainsSessionModule)
      },
      {
        path: 'patient',
        loadChildren: () => import('./patient/patient.module').then(m => m.CardioRehabCrPatientModule)
      },
      {
        path: 'rehabilitation-center',
        loadChildren: () =>
          import('./rehabilitation-center/rehabilitation-center.module').then(m => m.CardioRehabCrRehabilitationCenterModule)
      },
      {
        path: 'rehabilitation-group',
        loadChildren: () => import('./rehabilitation-group/rehabilitation-group.module').then(m => m.CardioRehabCrRehabilitationGroupModule)
      },
      {
        path: 'session',
        loadChildren: () => import('./session/session.module').then(m => m.CardioRehabCrSessionModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class CardioRehabCrEntityModule {}
