import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDepressiveSymptom } from 'app/shared/model/depressive-symptom.model';

@Component({
  selector: 'jhi-depressive-symptom-detail',
  templateUrl: './depressive-symptom-detail.component.html'
})
export class DepressiveSymptomDetailComponent implements OnInit {
  depressiveSymptom: IDepressiveSymptom | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ depressiveSymptom }) => {
      this.depressiveSymptom = depressiveSymptom;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
