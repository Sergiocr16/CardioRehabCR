import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IIncomeDiagnosis } from 'app/shared/model/income-diagnosis.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IncomeDiagnosisService } from './income-diagnosis.service';
import { IncomeDiagnosisDeleteDialogComponent } from './income-diagnosis-delete-dialog.component';

@Component({
  selector: 'jhi-income-diagnosis',
  templateUrl: './income-diagnosis.component.html'
})
export class IncomeDiagnosisComponent implements OnInit, OnDestroy {
  incomeDiagnoses: IIncomeDiagnosis[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected incomeDiagnosisService: IncomeDiagnosisService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.incomeDiagnoses = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.incomeDiagnosisService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IIncomeDiagnosis[]>) => this.paginateIncomeDiagnoses(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.incomeDiagnoses = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInIncomeDiagnoses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IIncomeDiagnosis): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInIncomeDiagnoses(): void {
    this.eventSubscriber = this.eventManager.subscribe('incomeDiagnosisListModification', () => this.reset());
  }

  delete(incomeDiagnosis: IIncomeDiagnosis): void {
    const modalRef = this.modalService.open(IncomeDiagnosisDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.incomeDiagnosis = incomeDiagnosis;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateIncomeDiagnoses(data: IIncomeDiagnosis[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.incomeDiagnoses.push(data[i]);
      }
    }
  }
}
