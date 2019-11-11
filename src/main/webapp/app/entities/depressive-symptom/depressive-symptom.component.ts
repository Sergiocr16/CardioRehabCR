import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IDepressiveSymptom } from 'app/shared/model/depressive-symptom.model';
import { AccountService } from 'app/core/auth/account.service';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DepressiveSymptomService } from './depressive-symptom.service';

@Component({
  selector: 'jhi-depressive-symptom',
  templateUrl: './depressive-symptom.component.html'
})
export class DepressiveSymptomComponent implements OnInit, OnDestroy {
  depressiveSymptoms: IDepressiveSymptom[];
  currentAccount: any;
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;

  constructor(
    protected depressiveSymptomService: DepressiveSymptomService,
    protected eventManager: JhiEventManager,
    protected parseLinks: JhiParseLinks,
    protected accountService: AccountService
  ) {
    this.depressiveSymptoms = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;
  }

  loadAll() {
    this.depressiveSymptomService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IDepressiveSymptom[]>) => this.paginateDepressiveSymptoms(res.body, res.headers));
  }

  reset() {
    this.page = 0;
    this.depressiveSymptoms = [];
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
    this.registerChangeInDepressiveSymptoms();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDepressiveSymptom) {
    return item.id;
  }

  registerChangeInDepressiveSymptoms() {
    this.eventSubscriber = this.eventManager.subscribe('depressiveSymptomListModification', response => this.reset());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateDepressiveSymptoms(data: IDepressiveSymptom[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.depressiveSymptoms.push(data[i]);
    }
  }
}
