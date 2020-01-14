import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDepressiveSymptomsSession, DepressiveSymptomsSession } from 'app/shared/model/depressive-symptoms-session.model';
import { DepressiveSymptomsSessionService } from './depressive-symptoms-session.service';
import { DepressiveSymptomsSessionComponent } from './depressive-symptoms-session.component';
import { DepressiveSymptomsSessionDetailComponent } from './depressive-symptoms-session-detail.component';
import { DepressiveSymptomsSessionUpdateComponent } from './depressive-symptoms-session-update.component';

@Injectable({ providedIn: 'root' })
export class DepressiveSymptomsSessionResolve implements Resolve<IDepressiveSymptomsSession> {
  constructor(private service: DepressiveSymptomsSessionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDepressiveSymptomsSession> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((depressiveSymptomsSession: HttpResponse<DepressiveSymptomsSession>) => {
          if (depressiveSymptomsSession.body) {
            return of(depressiveSymptomsSession.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DepressiveSymptomsSession());
  }
}

export const depressiveSymptomsSessionRoute: Routes = [
  {
    path: '',
    component: DepressiveSymptomsSessionComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.depressiveSymptomsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DepressiveSymptomsSessionDetailComponent,
    resolve: {
      depressiveSymptomsSession: DepressiveSymptomsSessionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.depressiveSymptomsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DepressiveSymptomsSessionUpdateComponent,
    resolve: {
      depressiveSymptomsSession: DepressiveSymptomsSessionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.depressiveSymptomsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DepressiveSymptomsSessionUpdateComponent,
    resolve: {
      depressiveSymptomsSession: DepressiveSymptomsSessionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.depressiveSymptomsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
