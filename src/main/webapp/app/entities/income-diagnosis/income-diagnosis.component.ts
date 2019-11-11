import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IIncomeDiagnosis } from 'app/shared/model/income-diagnosis.model';
import { AccountService } from 'app/core/auth/account.service';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IncomeDiagnosisService } from './income-diagnosis.service';

@Component({
  selector: 'jhi-income-diagnosis',
  templateUrl: './income-diagnosis.component.html'
})
export class IncomeDiagnosisComponent implements OnInit, OnDestroy {
  incomeDiagnoses: IIncomeDiagnosis[];
  currentAccount: any;
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;

  constructor(
    protected incomeDiagnosisService: IncomeDiagnosisService,
    protected eventManager: JhiEventManager,
    protected parseLinks: JhiParseLinks,
    protected accountService: AccountService
  ) {
    this.incomeDiagnoses = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;
  }

  loadAll() {
    this.incomeDiagnosisService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IIncomeDiagnosis[]>) => this.paginateIncomeDiagnoses(res.body, res.headers));
  }

  reset() {
    this.page = 0;
    this.incomeDiagnoses = [];
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
    this.registerChangeInIncomeDiagnoses();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IIncomeDiagnosis) {
    return item.id;
  }

  registerChangeInIncomeDiagnoses() {
    this.eventSubscriber = this.eventManager.subscribe('incomeDiagnosisListModification', response => this.reset());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateIncomeDiagnoses(data: IIncomeDiagnosis[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.incomeDiagnoses.push(data[i]);
    }
  }
}
