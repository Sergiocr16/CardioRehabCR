import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMayorEventsSession, MayorEventsSession } from 'app/shared/model/mayor-events-session.model';
import { MayorEventsSessionService } from './mayor-events-session.service';
import { MayorEventsSessionComponent } from './mayor-events-session.component';
import { MayorEventsSessionDetailComponent } from './mayor-events-session-detail.component';
import { MayorEventsSessionUpdateComponent } from './mayor-events-session-update.component';

@Injectable({ providedIn: 'root' })
export class MayorEventsSessionResolve implements Resolve<IMayorEventsSession> {
  constructor(private service: MayorEventsSessionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMayorEventsSession> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mayorEventsSession: HttpResponse<MayorEventsSession>) => {
          if (mayorEventsSession.body) {
            return of(mayorEventsSession.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MayorEventsSession());
  }
}

export const mayorEventsSessionRoute: Routes = [
  {
    path: '',
    component: MayorEventsSessionComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.mayorEventsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MayorEventsSessionDetailComponent,
    resolve: {
      mayorEventsSession: MayorEventsSessionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.mayorEventsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MayorEventsSessionUpdateComponent,
    resolve: {
      mayorEventsSession: MayorEventsSessionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.mayorEventsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MayorEventsSessionUpdateComponent,
    resolve: {
      mayorEventsSession: MayorEventsSessionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.mayorEventsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
