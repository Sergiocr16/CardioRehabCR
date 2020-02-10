import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRehabilitationGroup } from 'app/shared/model/rehabilitation-group.model';

type EntityResponseType = HttpResponse<IRehabilitationGroup>;
type EntityArrayResponseType = HttpResponse<IRehabilitationGroup[]>;

@Injectable({ providedIn: 'root' })
export class RehabilitationGroupService {
  public resourceUrl = SERVER_API_URL + 'api/rehabilitation-groups';

  constructor(protected http: HttpClient) {}

  create(rehabilitationGroup: IRehabilitationGroup): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rehabilitationGroup);
    return this.http
      .post<IRehabilitationGroup>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(rehabilitationGroup: IRehabilitationGroup): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rehabilitationGroup);
    return this.http
      .put<IRehabilitationGroup>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRehabilitationGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findClinicalCharasteristics(id: number): Observable<EntityResponseType> {
    return this.http
      .get<{}>(`${this.resourceUrl}/clinical-characteristics/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findCharacteristics(id: number): Observable<EntityResponseType> {
    return this.http
      .get<{}>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRehabilitationGroup[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(rehabilitationGroup: IRehabilitationGroup): IRehabilitationGroup {
    const copy: IRehabilitationGroup = Object.assign({}, rehabilitationGroup, {
      creationDate:
        rehabilitationGroup.creationDate != null && rehabilitationGroup.creationDate.isValid()
          ? rehabilitationGroup.creationDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.creationDate = res.body.creationDate != null ? moment(res.body.creationDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((rehabilitationGroup: IRehabilitationGroup) => {
        rehabilitationGroup.creationDate = rehabilitationGroup.creationDate != null ? moment(rehabilitationGroup.creationDate) : null;
      });
    }
    return res;
  }
}
