import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IComorbiditiesPatient } from 'app/shared/model/comorbidities-patient.model';
import { AccountService } from 'app/core/auth/account.service';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ComorbiditiesPatientService } from './comorbidities-patient.service';

@Component({
  selector: 'jhi-comorbidities-patient',
  templateUrl: './comorbidities-patient.component.html'
})
export class ComorbiditiesPatientComponent implements OnInit, OnDestroy {
  comorbiditiesPatients: IComorbiditiesPatient[];
  currentAccount: any;
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;

  constructor(
    protected comorbiditiesPatientService: ComorbiditiesPatientService,
    protected eventManager: JhiEventManager,
    protected parseLinks: JhiParseLinks,
    protected accountService: AccountService
  ) {
    this.comorbiditiesPatients = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;
  }

  loadAll() {
    this.comorbiditiesPatientService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IComorbiditiesPatient[]>) => this.paginateComorbiditiesPatients(res.body, res.headers));
  }

  reset() {
    this.page = 0;
    this.comorbiditiesPatients = [];
    this.loadAll();
  }

  loadPage(page) {
    this.page = page;
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().subscribe(account => {
      this.currentAccount = account;
    });
    this.registerChangeInComorbiditiesPatients();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IComorbiditiesPatient) {
    return item.id;
  }

  registerChangeInComorbiditiesPatients() {
    this.eventSubscriber = this.eventManager.subscribe('comorbiditiesPatientListModification', response => this.reset());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateComorbiditiesPatients(data: IComorbiditiesPatient[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.comorbiditiesPatients.push(data[i]);
    }
  }
}
