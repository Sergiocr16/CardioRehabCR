import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INonSpecificPainsSession, NonSpecificPainsSession } from 'app/shared/model/non-specific-pains-session.model';
import { NonSpecificPainsSessionService } from './non-specific-pains-session.service';
import { NonSpecificPainsSessionComponent } from './non-specific-pains-session.component';
import { NonSpecificPainsSessionDetailComponent } from './non-specific-pains-session-detail.component';
import { NonSpecificPainsSessionUpdateComponent } from './non-specific-pains-session-update.component';

@Injectable({ providedIn: 'root' })
export class NonSpecificPainsSessionResolve implements Resolve<INonSpecificPainsSession> {
  constructor(private service: NonSpecificPainsSessionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INonSpecificPainsSession> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((nonSpecificPainsSession: HttpResponse<NonSpecificPainsSession>) => {
          if (nonSpecificPainsSession.body) {
            return of(nonSpecificPainsSession.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NonSpecificPainsSession());
  }
}

export const nonSpecificPainsSessionRoute: Routes = [
  {
    path: '',
    component: NonSpecificPainsSessionComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.nonSpecificPainsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NonSpecificPainsSessionDetailComponent,
    resolve: {
      nonSpecificPainsSession: NonSpecificPainsSessionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.nonSpecificPainsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NonSpecificPainsSessionUpdateComponent,
    resolve: {
      nonSpecificPainsSession: NonSpecificPainsSessionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.nonSpecificPainsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NonSpecificPainsSessionUpdateComponent,
    resolve: {
      nonSpecificPainsSession: NonSpecificPainsSessionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.nonSpecificPainsSession.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
