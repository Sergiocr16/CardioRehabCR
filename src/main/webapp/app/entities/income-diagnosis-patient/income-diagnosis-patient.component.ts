import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IIncomeDiagnosisPatient } from 'app/shared/model/income-diagnosis-patient.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IncomeDiagnosisPatientService } from './income-diagnosis-patient.service';
import { IncomeDiagnosisPatientDeleteDialogComponent } from './income-diagnosis-patient-delete-dialog.component';

@Component({
  selector: 'jhi-income-diagnosis-patient',
  templateUrl: './income-diagnosis-patient.component.html'
})
export class IncomeDiagnosisPatientComponent implements OnInit, OnDestroy {
  incomeDiagnosisPatients: IIncomeDiagnosisPatient[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected incomeDiagnosisPatientService: IncomeDiagnosisPatientService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.incomeDiagnosisPatients = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.incomeDiagnosisPatientService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IIncomeDiagnosisPatient[]>) => this.paginateIncomeDiagnosisPatients(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.incomeDiagnosisPatients = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInIncomeDiagnosisPatients();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IIncomeDiagnosisPatient): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInIncomeDiagnosisPatients(): void {
    this.eventSubscriber = this.eventManager.subscribe('incomeDiagnosisPatientListModification', () => this.reset());
  }

  delete(incomeDiagnosisPatient: IIncomeDiagnosisPatient): void {
    const modalRef = this.modalService.open(IncomeDiagnosisPatientDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.incomeDiagnosisPatient = incomeDiagnosisPatient;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateIncomeDiagnosisPatients(data: IIncomeDiagnosisPatient[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.incomeDiagnosisPatients.push(data[i]);
      }
    }
  }
}
