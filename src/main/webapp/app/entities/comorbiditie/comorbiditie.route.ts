import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IComorbiditie, Comorbiditie } from 'app/shared/model/comorbiditie.model';
import { ComorbiditieService } from './comorbiditie.service';
import { ComorbiditieComponent } from './comorbiditie.component';
import { ComorbiditieDetailComponent } from './comorbiditie-detail.component';
import { ComorbiditieUpdateComponent } from './comorbiditie-update.component';

@Injectable({ providedIn: 'root' })
export class ComorbiditieResolve implements Resolve<IComorbiditie> {
  constructor(private service: ComorbiditieService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IComorbiditie> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((comorbiditie: HttpResponse<Comorbiditie>) => {
          if (comorbiditie.body) {
            return of(comorbiditie.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Comorbiditie());
  }
}

export const comorbiditieRoute: Routes = [
  {
    path: '',
    component: ComorbiditieComponent,
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.comorbiditie.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ComorbiditieDetailComponent,
    resolve: {
      comorbiditie: ComorbiditieResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.comorbiditie.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ComorbiditieUpdateComponent,
    resolve: {
      comorbiditie: ComorbiditieResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.comorbiditie.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ComorbiditieUpdateComponent,
    resolve: {
      comorbiditie: ComorbiditieResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.comorbiditie.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const comorbiditiePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ComorbiditieDeletePopupComponent,
    resolve: {
      comorbiditie: ComorbiditieResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'cardioRehabCrApp.comorbiditie.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
