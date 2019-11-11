import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFinalAssessment } from 'app/shared/model/final-assessment.model';

type EntityResponseType = HttpResponse<IFinalAssessment>;
type EntityArrayResponseType = HttpResponse<IFinalAssessment[]>;

@Injectable({ providedIn: 'root' })
export class FinalAssessmentService {
  public resourceUrl = SERVER_API_URL + 'api/final-assessments';

  constructor(protected http: HttpClient) {}

  create(finalAssessment: IFinalAssessment): Observable<EntityResponseType> {
    return this.http.post<IFinalAssessment>(this.resourceUrl, finalAssessment, { observe: 'response' });
  }

  update(finalAssessment: IFinalAssessment): Observable<EntityResponseType> {
    return this.http.put<IFinalAssessment>(this.resourceUrl, finalAssessment, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFinalAssessment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFinalAssessment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
