import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMayorEvent } from 'app/shared/model/mayor-event.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { MayorEventService } from './mayor-event.service';
import { MayorEventDeleteDialogComponent } from './mayor-event-delete-dialog.component';

@Component({
  selector: 'jhi-mayor-event',
  templateUrl: './mayor-event.component.html'
})
export class MayorEventComponent implements OnInit, OnDestroy {
  mayorEvents: IMayorEvent[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected mayorEventService: MayorEventService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.mayorEvents = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.mayorEventService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IMayorEvent[]>) => this.paginateMayorEvents(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.mayorEvents = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMayorEvents();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMayorEvent): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMayorEvents(): void {
    this.eventSubscriber = this.eventManager.subscribe('mayorEventListModification', () => this.reset());
  }

  delete(mayorEvent: IMayorEvent): void {
    const modalRef = this.modalService.open(MayorEventDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mayorEvent = mayorEvent;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateMayorEvents(data: IMayorEvent[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.mayorEvents.push(data[i]);
      }
    }
  }
}
