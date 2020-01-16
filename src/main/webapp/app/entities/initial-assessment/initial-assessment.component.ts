import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInitialAssessment } from 'app/shared/model/initial-assessment.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { InitialAssessmentService } from './initial-assessment.service';
import { InitialAssessmentDeleteDialogComponent } from './initial-assessment-delete-dialog.component';

@Component({
  selector: 'jhi-initial-assessment',
  templateUrl: './initial-assessment.component.html'
})
export class InitialAssessmentComponent implements OnInit, OnDestroy {
  initialAssessments: IInitialAssessment[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected initialAssessmentService: InitialAssessmentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.initialAssessments = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.initialAssessmentService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IInitialAssessment[]>) => this.paginateInitialAssessments(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.initialAssessments = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInitialAssessments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInitialAssessment): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInitialAssessments(): void {
    this.eventSubscriber = this.eventManager.subscribe('initialAssessmentListModification', () => this.reset());
  }

  delete(initialAssessment: IInitialAssessment): void {
    const modalRef = this.modalService.open(InitialAssessmentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.initialAssessment = initialAssessment;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateInitialAssessments(data: IInitialAssessment[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.initialAssessments.push(data[i]);
      }
    }
  }
}
