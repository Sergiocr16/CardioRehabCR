import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INonSpecificPain, NonSpecificPain } from 'app/shared/model/non-specific-pain.model';
import { NonSpecificPainService } from './non-specific-pain.service';
import { NonSpecificPainComponent } from './non-specific-pain.component';
import { NonSpecificPainDetailComponent } from './non-specific-pain-detail.component';
import { NonSpecificPainUpdateComponent } from './non-specific-pain-update.component';

@Injectable({ providedIn: 'root' })
export class NonSpecificPainResolve implements Resolve<INonSpecificPain> {
  constructor(private service: NonSpecificPainService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INonSpecificPain> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((nonSpecificPain: HttpResponse<NonSpecificPain>) => {
          if (nonSpecificPain.body) {
            return of(nonSpecificPain.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NonSpecificPain());
  }
}

export const nonSpecificPainRoute: Routes = [
  {
    path: '',
    component: NonSpecificPainComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.nonSpecificPain.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NonSpecificPainDetailComponent,
    resolve: {
      nonSpecificPain: NonSpecificPainResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.nonSpecificPain.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NonSpecificPainUpdateComponent,
    resolve: {
      nonSpecificPain: NonSpecificPainResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.nonSpecificPain.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NonSpecificPainUpdateComponent,
    resolve: {
      nonSpecificPain: NonSpecificPainResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.nonSpecificPain.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
