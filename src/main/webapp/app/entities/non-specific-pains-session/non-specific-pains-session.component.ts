import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INonSpecificPainsSession } from 'app/shared/model/non-specific-pains-session.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { NonSpecificPainsSessionService } from './non-specific-pains-session.service';
import { NonSpecificPainsSessionDeleteDialogComponent } from './non-specific-pains-session-delete-dialog.component';

@Component({
  selector: 'jhi-non-specific-pains-session',
  templateUrl: './non-specific-pains-session.component.html'
})
export class NonSpecificPainsSessionComponent implements OnInit, OnDestroy {
  nonSpecificPainsSessions: INonSpecificPainsSession[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected nonSpecificPainsSessionService: NonSpecificPainsSessionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.nonSpecificPainsSessions = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.nonSpecificPainsSessionService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<INonSpecificPainsSession[]>) => this.paginateNonSpecificPainsSessions(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.nonSpecificPainsSessions = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNonSpecificPainsSessions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INonSpecificPainsSession): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNonSpecificPainsSessions(): void {
    this.eventSubscriber = this.eventManager.subscribe('nonSpecificPainsSessionListModification', () => this.reset());
  }

  delete(nonSpecificPainsSession: INonSpecificPainsSession): void {
    const modalRef = this.modalService.open(NonSpecificPainsSessionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.nonSpecificPainsSession = nonSpecificPainsSession;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateNonSpecificPainsSessions(data: INonSpecificPainsSession[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.nonSpecificPainsSessions.push(data[i]);
      }
    }
  }
}
