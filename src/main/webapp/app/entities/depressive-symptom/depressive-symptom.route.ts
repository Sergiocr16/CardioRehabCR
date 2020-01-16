import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDepressiveSymptom, DepressiveSymptom } from 'app/shared/model/depressive-symptom.model';
import { DepressiveSymptomService } from './depressive-symptom.service';
import { DepressiveSymptomComponent } from './depressive-symptom.component';
import { DepressiveSymptomDetailComponent } from './depressive-symptom-detail.component';
import { DepressiveSymptomUpdateComponent } from './depressive-symptom-update.component';

@Injectable({ providedIn: 'root' })
export class DepressiveSymptomResolve implements Resolve<IDepressiveSymptom> {
  constructor(private service: DepressiveSymptomService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDepressiveSymptom> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((depressiveSymptom: HttpResponse<DepressiveSymptom>) => {
          if (depressiveSymptom.body) {
            return of(depressiveSymptom.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DepressiveSymptom());
  }
}

export const depressiveSymptomRoute: Routes = [
  {
    path: '',
    component: DepressiveSymptomComponent,
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.depressiveSymptom.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DepressiveSymptomDetailComponent,
    resolve: {
      depressiveSymptom: DepressiveSymptomResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.depressiveSymptom.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DepressiveSymptomUpdateComponent,
    resolve: {
      depressiveSymptom: DepressiveSymptomResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.depressiveSymptom.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DepressiveSymptomUpdateComponent,
    resolve: {
      depressiveSymptom: DepressiveSymptomResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.depressiveSymptom.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const depressiveSymptomPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DepressiveSymptomDeletePopupComponent,
    resolve: {
      depressiveSymptom: DepressiveSymptomResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.depressiveSymptom.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
