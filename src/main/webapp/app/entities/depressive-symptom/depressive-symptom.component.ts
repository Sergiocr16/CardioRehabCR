import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDepressiveSymptom } from 'app/shared/model/depressive-symptom.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DepressiveSymptomService } from './depressive-symptom.service';
import { DepressiveSymptomDeleteDialogComponent } from './depressive-symptom-delete-dialog.component';

@Component({
  selector: 'jhi-depressive-symptom',
  templateUrl: './depressive-symptom.component.html'
})
export class DepressiveSymptomComponent implements OnInit, OnDestroy {
  depressiveSymptoms: IDepressiveSymptom[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected depressiveSymptomService: DepressiveSymptomService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.depressiveSymptoms = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.depressiveSymptomService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IDepressiveSymptom[]>) => this.paginateDepressiveSymptoms(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.depressiveSymptoms = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDepressiveSymptoms();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDepressiveSymptom): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDepressiveSymptoms(): void {
    this.eventSubscriber = this.eventManager.subscribe('depressiveSymptomListModification', () => this.reset());
  }

  delete(depressiveSymptom: IDepressiveSymptom): void {
    const modalRef = this.modalService.open(DepressiveSymptomDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.depressiveSymptom = depressiveSymptom;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateDepressiveSymptoms(data: IDepressiveSymptom[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.depressiveSymptoms.push(data[i]);
      }
    }
  }
}
