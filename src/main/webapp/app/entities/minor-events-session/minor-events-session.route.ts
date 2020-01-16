import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMinorEventsSession, MinorEventsSession } from 'app/shared/model/minor-events-session.model';
import { MinorEventsSessionService } from './minor-events-session.service';
import { MinorEventsSessionComponent } from './minor-events-session.component';
import { MinorEventsSessionDetailComponent } from './minor-events-session-detail.component';
import { MinorEventsSessionUpdateComponent } from './minor-events-session-update.component';

@Injectable({ providedIn: 'root' })
export class MinorEventsSessionResolve implements Resolve<IMinorEventsSession> {
  constructor(private service: MinorEventsSessionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMinorEventsSession> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((minorEventsSession: HttpResponse<MinorEventsSession>) => {
          if (minorEventsSession.body) {
            return of(minorEventsSession.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MinorEventsSession());
  }
}

export const minorEventsSessionRoute: Routes = [
  {
    path: '',
    component: MinorEventsSessionComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.minorEventsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MinorEventsSessionDetailComponent,
    resolve: {
      minorEventsSession: MinorEventsSessionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.minorEventsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MinorEventsSessionUpdateComponent,
    resolve: {
      minorEventsSession: MinorEventsSessionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.minorEventsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MinorEventsSessionUpdateComponent,
    resolve: {
      minorEventsSession: MinorEventsSessionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.minorEventsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
