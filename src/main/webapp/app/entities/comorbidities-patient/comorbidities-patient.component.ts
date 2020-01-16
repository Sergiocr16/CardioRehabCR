import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IComorbiditiesPatient } from 'app/shared/model/comorbidities-patient.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ComorbiditiesPatientService } from './comorbidities-patient.service';
import { ComorbiditiesPatientDeleteDialogComponent } from './comorbidities-patient-delete-dialog.component';

@Component({
  selector: 'jhi-comorbidities-patient',
  templateUrl: './comorbidities-patient.component.html'
})
export class ComorbiditiesPatientComponent implements OnInit, OnDestroy {
  comorbiditiesPatients: IComorbiditiesPatient[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected comorbiditiesPatientService: ComorbiditiesPatientService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.comorbiditiesPatients = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.comorbiditiesPatientService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IComorbiditiesPatient[]>) => this.paginateComorbiditiesPatients(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.comorbiditiesPatients = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInComorbiditiesPatients();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IComorbiditiesPatient): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInComorbiditiesPatients(): void {
    this.eventSubscriber = this.eventManager.subscribe('comorbiditiesPatientListModification', () => this.reset());
  }

  delete(comorbiditiesPatient: IComorbiditiesPatient): void {
    const modalRef = this.modalService.open(ComorbiditiesPatientDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.comorbiditiesPatient = comorbiditiesPatient;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateComorbiditiesPatients(data: IComorbiditiesPatient[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.comorbiditiesPatients.push(data[i]);
      }
    }
  }
}
