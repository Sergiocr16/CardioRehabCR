import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIncomeDiagnosis } from 'app/shared/model/income-diagnosis.model';

@Component({
  selector: 'jhi-income-diagnosis-detail',
  templateUrl: './income-diagnosis-detail.component.html'
})
export class IncomeDiagnosisDetailComponent implements OnInit {
  incomeDiagnosis: IIncomeDiagnosis | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ incomeDiagnosis }) => {
      this.incomeDiagnosis = incomeDiagnosis;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
