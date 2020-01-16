import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRehabilitationCenter, RehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';
import { RehabilitationCenterService } from './rehabilitation-center.service';
import { RehabilitationCenterComponent } from './rehabilitation-center.component';
import { RehabilitationCenterDetailComponent } from './rehabilitation-center-detail.component';
import { RehabilitationCenterUpdateComponent } from './rehabilitation-center-update.component';

@Injectable({ providedIn: 'root' })
export class RehabilitationCenterResolve implements Resolve<IRehabilitationCenter> {
  constructor(private service: RehabilitationCenterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRehabilitationCenter> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rehabilitationCenter: HttpResponse<RehabilitationCenter>) => {
          if (rehabilitationCenter.body) {
            return of(rehabilitationCenter.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RehabilitationCenter());
  }
}

export const rehabilitationCenterRoute: Routes = [
  {
    path: '',
    component: RehabilitationCenterComponent,
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.rehabilitationCenter.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RehabilitationCenterDetailComponent,
    resolve: {
      rehabilitationCenter: RehabilitationCenterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.rehabilitationCenter.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RehabilitationCenterUpdateComponent,
    resolve: {
      rehabilitationCenter: RehabilitationCenterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.rehabilitationCenter.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RehabilitationCenterUpdateComponent,
    resolve: {
      rehabilitationCenter: RehabilitationCenterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.rehabilitationCenter.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const rehabilitationCenterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: RehabilitationCenterDeletePopupComponent,
    resolve: {
      rehabilitationCenter: RehabilitationCenterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.rehabilitationCenter.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
