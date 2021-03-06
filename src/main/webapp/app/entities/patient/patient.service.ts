import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPatient } from 'app/shared/model/patient.model';

type EntityResponseType = HttpResponse<IPatient>;
type EntityArrayResponseType = HttpResponse<IPatient[]>;

@Injectable({ providedIn: 'root' })
export class PatientService {
  public resourceUrl = SERVER_API_URL + 'api/patients';

  constructor(protected http: HttpClient) {}

  create(patient: IPatient): Observable<EntityResponseType> {
    // const copy = this.convertDateFromClient(patient);
    return this.http.post<IPatient>(this.resourceUrl, patient, { observe: 'response' }).pipe(map((res: EntityResponseType) => res));
  }

  update(patient: IPatient): Observable<EntityResponseType> {
    // const copy = this.convertDateFromClient(patient);
    return this.http.put<IPatient>(this.resourceUrl, patient, { observe: 'response' }).pipe(map((res: EntityResponseType) => res));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPatient>(`${this.resourceUrl}/${id}`, { observe: 'response' }).pipe(map((res: EntityResponseType) => res));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPatient[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => res));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(patient: IPatient): IPatient {
    const copy: IPatient = Object.assign({}, patient, {
      lastEventOcurred: patient.lastEventOcurred != null && patient.lastEventOcurred.isValid() ? patient.lastEventOcurred.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.lastEventOcurred = res.body.lastEventOcurred != null ? moment(res.body.lastEventOcurred) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((patient: IPatient) => {
        patient.lastEventOcurred = patient.lastEventOcurred != null ? moment(patient.lastEventOcurred) : null;
      });
    }
    return res;
  }
}
