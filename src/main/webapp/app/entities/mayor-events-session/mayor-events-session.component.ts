import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMayorEventsSession } from 'app/shared/model/mayor-events-session.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { MayorEventsSessionService } from './mayor-events-session.service';
import { MayorEventsSessionDeleteDialogComponent } from './mayor-events-session-delete-dialog.component';

@Component({
  selector: 'jhi-mayor-events-session',
  templateUrl: './mayor-events-session.component.html'
})
export class MayorEventsSessionComponent implements OnInit, OnDestroy {
  mayorEventsSessions: IMayorEventsSession[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected mayorEventsSessionService: MayorEventsSessionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.mayorEventsSessions = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.mayorEventsSessionService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IMayorEventsSession[]>) => this.paginateMayorEventsSessions(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.mayorEventsSessions = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMayorEventsSessions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMayorEventsSession): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMayorEventsSessions(): void {
    this.eventSubscriber = this.eventManager.subscribe('mayorEventsSessionListModification', () => this.reset());
  }

  delete(mayorEventsSession: IMayorEventsSession): void {
    const modalRef = this.modalService.open(MayorEventsSessionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mayorEventsSession = mayorEventsSession;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateMayorEventsSessions(data: IMayorEventsSession[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.mayorEventsSessions.push(data[i]);
      }
    }
  }
}
