import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMayorEvent, MayorEvent } from 'app/shared/model/mayor-event.model';
import { MayorEventService } from './mayor-event.service';
import { MayorEventComponent } from './mayor-event.component';
import { MayorEventDetailComponent } from './mayor-event-detail.component';
import { MayorEventUpdateComponent } from './mayor-event-update.component';

@Injectable({ providedIn: 'root' })
export class MayorEventResolve implements Resolve<IMayorEvent> {
  constructor(private service: MayorEventService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMayorEvent> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mayorEvent: HttpResponse<MayorEvent>) => {
          if (mayorEvent.body) {
            return of(mayorEvent.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MayorEvent());
  }
}

export const mayorEventRoute: Routes = [
  {
    path: '',
    component: MayorEventComponent,
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.mayorEvent.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MayorEventDetailComponent,
    resolve: {
      mayorEvent: MayorEventResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.mayorEvent.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MayorEventUpdateComponent,
    resolve: {
      mayorEvent: MayorEventResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.mayorEvent.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MayorEventUpdateComponent,
    resolve: {
      mayorEvent: MayorEventResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.mayorEvent.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mayorEventPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MayorEventDeletePopupComponent,
    resolve: {
      mayorEvent: MayorEventResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.mayorEvent.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
