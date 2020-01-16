import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IComorbiditie } from 'app/shared/model/comorbiditie.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ComorbiditieService } from './comorbiditie.service';
import { ComorbiditieDeleteDialogComponent } from './comorbiditie-delete-dialog.component';

@Component({
  selector: 'jhi-comorbiditie',
  templateUrl: './comorbiditie.component.html'
})
export class ComorbiditieComponent implements OnInit, OnDestroy {
  comorbidities: IComorbiditie[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected comorbiditieService: ComorbiditieService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.comorbidities = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.comorbiditieService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IComorbiditie[]>) => this.paginateComorbidities(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.comorbidities = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInComorbidities();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IComorbiditie): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInComorbidities(): void {
    this.eventSubscriber = this.eventManager.subscribe('comorbiditieListModification', () => this.reset());
  }

  delete(comorbiditie: IComorbiditie): void {
    const modalRef = this.modalService.open(ComorbiditieDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.comorbiditie = comorbiditie;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateComorbidities(data: IComorbiditie[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.comorbidities.push(data[i]);
      }
    }
  }
}
