import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IIncomeDiagnosisPatient, IncomeDiagnosisPatient } from 'app/shared/model/income-diagnosis-patient.model';
import { IncomeDiagnosisPatientService } from './income-diagnosis-patient.service';
import { IncomeDiagnosisPatientComponent } from './income-diagnosis-patient.component';
import { IncomeDiagnosisPatientDetailComponent } from './income-diagnosis-patient-detail.component';
import { IncomeDiagnosisPatientUpdateComponent } from './income-diagnosis-patient-update.component';

@Injectable({ providedIn: 'root' })
export class IncomeDiagnosisPatientResolve implements Resolve<IIncomeDiagnosisPatient> {
  constructor(private service: IncomeDiagnosisPatientService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IIncomeDiagnosisPatient> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((incomeDiagnosisPatient: HttpResponse<IncomeDiagnosisPatient>) => {
          if (incomeDiagnosisPatient.body) {
            return of(incomeDiagnosisPatient.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new IncomeDiagnosisPatient());
  }
}

export const incomeDiagnosisPatientRoute: Routes = [
  {
    path: '',
    component: IncomeDiagnosisPatientComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.incomeDiagnosisPatient.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: IncomeDiagnosisPatientDetailComponent,
    resolve: {
      incomeDiagnosisPatient: IncomeDiagnosisPatientResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.incomeDiagnosisPatient.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: IncomeDiagnosisPatientUpdateComponent,
    resolve: {
      incomeDiagnosisPatient: IncomeDiagnosisPatientResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.incomeDiagnosisPatient.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: IncomeDiagnosisPatientUpdateComponent,
    resolve: {
      incomeDiagnosisPatient: IncomeDiagnosisPatientResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.incomeDiagnosisPatient.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
