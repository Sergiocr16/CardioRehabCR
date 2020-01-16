import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRehabilitationGroup } from 'app/shared/model/rehabilitation-group.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RehabilitationGroupService } from './rehabilitation-group.service';
import { RehabilitationGroupDeleteDialogComponent } from './rehabilitation-group-delete-dialog.component';

@Component({
  selector: 'jhi-rehabilitation-group',
  templateUrl: './rehabilitation-group.component.html'
})
export class RehabilitationGroupComponent implements OnInit, OnDestroy {
  rehabilitationGroups: IRehabilitationGroup[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected rehabilitationGroupService: RehabilitationGroupService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.rehabilitationGroups = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.rehabilitationGroupService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IRehabilitationGroup[]>) => this.paginateRehabilitationGroups(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.rehabilitationGroups = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRehabilitationGroups();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRehabilitationGroup): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRehabilitationGroups(): void {
    this.eventSubscriber = this.eventManager.subscribe('rehabilitationGroupListModification', () => this.reset());
  }

  delete(rehabilitationGroup: IRehabilitationGroup): void {
    const modalRef = this.modalService.open(RehabilitationGroupDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.rehabilitationGroup = rehabilitationGroup;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateRehabilitationGroups(data: IRehabilitationGroup[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.rehabilitationGroups.push(data[i]);
      }
    }
  }
}
