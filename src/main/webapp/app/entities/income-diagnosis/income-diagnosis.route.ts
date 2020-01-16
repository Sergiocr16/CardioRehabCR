import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IIncomeDiagnosis, IncomeDiagnosis } from 'app/shared/model/income-diagnosis.model';
import { IncomeDiagnosisService } from './income-diagnosis.service';
import { IncomeDiagnosisComponent } from './income-diagnosis.component';
import { IncomeDiagnosisDetailComponent } from './income-diagnosis-detail.component';
import { IncomeDiagnosisUpdateComponent } from './income-diagnosis-update.component';

@Injectable({ providedIn: 'root' })
export class IncomeDiagnosisResolve implements Resolve<IIncomeDiagnosis> {
  constructor(private service: IncomeDiagnosisService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IIncomeDiagnosis> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((incomeDiagnosis: HttpResponse<IncomeDiagnosis>) => {
          if (incomeDiagnosis.body) {
            return of(incomeDiagnosis.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new IncomeDiagnosis());
  }
}

export const incomeDiagnosisRoute: Routes = [
  {
    path: '',
    component: IncomeDiagnosisComponent,
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.incomeDiagnosis.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: IncomeDiagnosisDetailComponent,
    resolve: {
      incomeDiagnosis: IncomeDiagnosisResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.incomeDiagnosis.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: IncomeDiagnosisUpdateComponent,
    resolve: {
      incomeDiagnosis: IncomeDiagnosisResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.incomeDiagnosis.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: IncomeDiagnosisUpdateComponent,
    resolve: {
      incomeDiagnosis: IncomeDiagnosisResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.incomeDiagnosis.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const incomeDiagnosisPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: IncomeDiagnosisDeletePopupComponent,
    resolve: {
      incomeDiagnosis: IncomeDiagnosisResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.incomeDiagnosis.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
