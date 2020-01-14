import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAppUser } from 'app/shared/model/app-user.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AppUserService } from './app-user.service';
import { AppUserDeleteDialogComponent } from './app-user-delete-dialog.component';

@Component({
  selector: 'jhi-app-user',
  templateUrl: './app-user.component.html'
})
export class AppUserComponent implements OnInit, OnDestroy {
  appUsers: IAppUser[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected appUserService: AppUserService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.appUsers = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.appUserService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IAppUser[]>) => this.paginateAppUsers(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.appUsers = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAppUsers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAppUser): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAppUsers(): void {
    this.eventSubscriber = this.eventManager.subscribe('appUserListModification', () => this.reset());
  }

  delete(appUser: IAppUser): void {
    const modalRef = this.modalService.open(AppUserDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.appUser = appUser;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateAppUsers(data: IAppUser[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.appUsers.push(data[i]);
      }
    }
  }
}
