import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFinalAssessment, FinalAssessment } from 'app/shared/model/final-assessment.model';
import { FinalAssessmentService } from './final-assessment.service';
import { FinalAssessmentComponent } from './final-assessment.component';
import { FinalAssessmentDetailComponent } from './final-assessment-detail.component';
import { FinalAssessmentUpdateComponent } from './final-assessment-update.component';

@Injectable({ providedIn: 'root' })
export class FinalAssessmentResolve implements Resolve<IFinalAssessment> {
  constructor(private service: FinalAssessmentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFinalAssessment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((finalAssessment: HttpResponse<FinalAssessment>) => {
          if (finalAssessment.body) {
            return of(finalAssessment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FinalAssessment());
  }
}

export const finalAssessmentRoute: Routes = [
  {
    path: '',
    component: FinalAssessmentComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.finalAssessment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FinalAssessmentDetailComponent,
    resolve: {
      finalAssessment: FinalAssessmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.finalAssessment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':patientId/new-reevaluation',
    component: FinalAssessmentUpdateComponent,
    resolve: {
      finalAssessment: FinalAssessmentResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_MANAGER'],
      pageTitle: 'cardioRehabCrApp.finalAssessment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':patientId/new',
    component: FinalAssessmentUpdateComponent,
    resolve: {
      finalAssessment: FinalAssessmentResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_MANAGER'],
      pageTitle: 'cardioRehabCrApp.finalAssessment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FinalAssessmentUpdateComponent,
    resolve: {
      finalAssessment: FinalAssessmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'cardioRehabCrApp.finalAssessment.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
