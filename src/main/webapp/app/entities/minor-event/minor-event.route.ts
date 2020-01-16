import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMinorEvent, MinorEvent } from 'app/shared/model/minor-event.model';
import { MinorEventService } from './minor-event.service';
import { MinorEventComponent } from './minor-event.component';
import { MinorEventDetailComponent } from './minor-event-detail.component';
import { MinorEventUpdateComponent } from './minor-event-update.component';

@Injectable({ providedIn: 'root' })
export class MinorEventResolve implements Resolve<IMinorEvent> {
  constructor(private service: MinorEventService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMinorEvent> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((minorEvent: HttpResponse<MinorEvent>) => {
          if (minorEvent.body) {
            return of(minorEvent.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MinorEvent());
  }
}

export const minorEventRoute: Routes = [
  {
    path: '',
    component: MinorEventComponent,
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.minorEvent.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MinorEventDetailComponent,
    resolve: {
      minorEvent: MinorEventResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.minorEvent.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MinorEventUpdateComponent,
    resolve: {
      minorEvent: MinorEventResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.minorEvent.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MinorEventUpdateComponent,
    resolve: {
      minorEvent: MinorEventResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.minorEvent.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const minorEventPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MinorEventDeletePopupComponent,
    resolve: {
      minorEvent: MinorEventResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.minorEvent.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
