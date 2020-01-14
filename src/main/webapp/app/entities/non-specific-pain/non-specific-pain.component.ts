import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INonSpecificPain } from 'app/shared/model/non-specific-pain.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { NonSpecificPainService } from './non-specific-pain.service';
import { NonSpecificPainDeleteDialogComponent } from './non-specific-pain-delete-dialog.component';

@Component({
  selector: 'jhi-non-specific-pain',
  templateUrl: './non-specific-pain.component.html'
})
export class NonSpecificPainComponent implements OnInit, OnDestroy {
  nonSpecificPains: INonSpecificPain[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected nonSpecificPainService: NonSpecificPainService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.nonSpecificPains = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.nonSpecificPainService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<INonSpecificPain[]>) => this.paginateNonSpecificPains(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.nonSpecificPains = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNonSpecificPains();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INonSpecificPain): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNonSpecificPains(): void {
    this.eventSubscriber = this.eventManager.subscribe('nonSpecificPainListModification', () => this.reset());
  }

  delete(nonSpecificPain: INonSpecificPain): void {
    const modalRef = this.modalService.open(NonSpecificPainDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.nonSpecificPain = nonSpecificPain;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateNonSpecificPains(data: INonSpecificPain[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.nonSpecificPains.push(data[i]);
      }
    }
  }
}
