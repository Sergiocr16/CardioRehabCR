import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRehabilitationGroup, RehabilitationGroup } from 'app/shared/model/rehabilitation-group.model';
import { RehabilitationGroupService } from './rehabilitation-group.service';
import { RehabilitationGroupComponent } from './rehabilitation-group.component';
import { RehabilitationGroupDetailComponent } from './rehabilitation-group-detail.component';
import { RehabilitationGroupUpdateComponent } from './rehabilitation-group-update.component';

@Injectable({ providedIn: 'root' })
export class RehabilitationGroupResolve implements Resolve<IRehabilitationGroup> {
  constructor(private service: RehabilitationGroupService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRehabilitationGroup> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rehabilitationGroup: HttpResponse<RehabilitationGroup>) => {
          if (rehabilitationGroup.body) {
            return of(rehabilitationGroup.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RehabilitationGroup());
  }
}

export const rehabilitationGroupRoute: Routes = [
  {
    path: '',
    component: RehabilitationGroupComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.rehabilitationGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RehabilitationGroupDetailComponent,
    resolve: {
      rehabilitationGroup: RehabilitationGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.rehabilitationGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RehabilitationGroupUpdateComponent,
    resolve: {
      rehabilitationGroup: RehabilitationGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.rehabilitationGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RehabilitationGroupUpdateComponent,
    resolve: {
      rehabilitationGroup: RehabilitationGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.rehabilitationGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
