import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRehabilitationGroup } from 'app/shared/model/rehabilitation-group.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RehabilitationGroupService } from './rehabilitation-group.service';
import { ModalService } from 'app/shared/util/modal.service';
import { GlobalVariablesService } from 'app/shared/util/global-variables.service';

@Component({
  selector: 'jhi-rehabilitation-group',
  templateUrl: './rehabilitation-group.component.html'
})
export class RehabilitationGroupComponent implements OnInit, OnDestroy {
  rehabilitationGroups: IRehabilitationGroup[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;
  ready;
  constructor(
    protected rehabilitationGroupService: RehabilitationGroupService,
    protected eventManager: JhiEventManager,
    protected parseLinks: JhiParseLinks,
    protected accountService: AccountService,
    protected modal: ModalService,
    private global: GlobalVariablesService
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
        sort: this.sort(),
        rehabilitationId: this.global.rehabCenter
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

  delete(rehabGroup) {
    this.modal.confirmDialog('delete', () => {
      this.rehabilitationGroupService.delete(rehabGroup.id).subscribe(response => {
        this.rehabilitationGroups.splice(this.rehabilitationGroups.indexOf(rehabGroup), 1);
        this.modal.message('Se ha eliminado el grupo correctamente');
      });
    });
  }

  protected paginateRehabilitationGroups(data: IRehabilitationGroup[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.rehabilitationGroups.push(data[i]);
    }
    this.ready = true;
  }
}
