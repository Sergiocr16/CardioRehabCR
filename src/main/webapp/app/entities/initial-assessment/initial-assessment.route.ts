import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInitialAssessment, InitialAssessment } from 'app/shared/model/initial-assessment.model';
import { InitialAssessmentService } from './initial-assessment.service';
import { InitialAssessmentComponent } from './initial-assessment.component';
import { InitialAssessmentDetailComponent } from './initial-assessment-detail.component';
import { InitialAssessmentUpdateComponent } from './initial-assessment-update.component';

@Injectable({ providedIn: 'root' })
export class InitialAssessmentResolve implements Resolve<IInitialAssessment> {
  constructor(private service: InitialAssessmentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInitialAssessment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((initialAssessment: HttpResponse<InitialAssessment>) => {
          if (initialAssessment.body) {
            return of(initialAssessment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InitialAssessment());
  }
}

export const initialAssessmentRoute: Routes = [
  {
    path: '',
    component: InitialAssessmentComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.initialAssessment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InitialAssessmentDetailComponent,
    resolve: {
      initialAssessment: InitialAssessmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.initialAssessment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InitialAssessmentUpdateComponent,
    resolve: {
      initialAssessment: InitialAssessmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.initialAssessment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InitialAssessmentUpdateComponent,
    resolve: {
      initialAssessment: InitialAssessmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.initialAssessment.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
