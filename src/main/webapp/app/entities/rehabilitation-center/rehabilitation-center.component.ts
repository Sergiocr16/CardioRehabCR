import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RehabilitationCenterService } from './rehabilitation-center.service';
import { RehabilitationCenterDeleteDialogComponent } from './rehabilitation-center-delete-dialog.component';

@Component({
  selector: 'jhi-rehabilitation-center',
  templateUrl: './rehabilitation-center.component.html'
})
export class RehabilitationCenterComponent implements OnInit, OnDestroy {
  rehabilitationCenters: IRehabilitationCenter[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected rehabilitationCenterService: RehabilitationCenterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.rehabilitationCenters = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.rehabilitationCenterService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IRehabilitationCenter[]>) => this.paginateRehabilitationCenters(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.rehabilitationCenters = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRehabilitationCenters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRehabilitationCenter): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRehabilitationCenters(): void {
    this.eventSubscriber = this.eventManager.subscribe('rehabilitationCenterListModification', () => this.reset());
  }

  delete(rehabilitationCenter: IRehabilitationCenter): void {
    const modalRef = this.modalService.open(RehabilitationCenterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.rehabilitationCenter = rehabilitationCenter;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateRehabilitationCenters(data: IRehabilitationCenter[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.rehabilitationCenters.push(data[i]);
      }
    }
  }
}
