import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFinalAssessment } from 'app/shared/model/final-assessment.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FinalAssessmentService } from './final-assessment.service';
import { FinalAssessmentDeleteDialogComponent } from './final-assessment-delete-dialog.component';

@Component({
  selector: 'jhi-final-assessment',
  templateUrl: './final-assessment.component.html'
})
export class FinalAssessmentComponent implements OnInit, OnDestroy {
  finalAssessments: IFinalAssessment[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected finalAssessmentService: FinalAssessmentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.finalAssessments = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.finalAssessmentService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IFinalAssessment[]>) => this.paginateFinalAssessments(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.finalAssessments = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFinalAssessments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFinalAssessment): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFinalAssessments(): void {
    this.eventSubscriber = this.eventManager.subscribe('finalAssessmentListModification', () => this.reset());
  }

  delete(finalAssessment: IFinalAssessment): void {
    const modalRef = this.modalService.open(FinalAssessmentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.finalAssessment = finalAssessment;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateFinalAssessments(data: IFinalAssessment[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.finalAssessments.push(data[i]);
      }
    }
  }
}
