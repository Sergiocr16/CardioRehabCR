import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMinorEvent } from 'app/shared/model/minor-event.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { MinorEventService } from './minor-event.service';
import { MinorEventDeleteDialogComponent } from './minor-event-delete-dialog.component';

@Component({
  selector: 'jhi-minor-event',
  templateUrl: './minor-event.component.html'
})
export class MinorEventComponent implements OnInit, OnDestroy {
  minorEvents: IMinorEvent[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected minorEventService: MinorEventService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.minorEvents = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.minorEventService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IMinorEvent[]>) => this.paginateMinorEvents(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.minorEvents = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMinorEvents();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMinorEvent): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMinorEvents(): void {
    this.eventSubscriber = this.eventManager.subscribe('minorEventListModification', () => this.reset());
  }

  delete(minorEvent: IMinorEvent): void {
    const modalRef = this.modalService.open(MinorEventDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.minorEvent = minorEvent;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateMinorEvents(data: IMinorEvent[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.minorEvents.push(data[i]);
      }
    }
  }
}
