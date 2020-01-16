import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComorbiditie } from 'app/shared/model/comorbiditie.model';

@Component({
  selector: 'jhi-comorbiditie-detail',
  templateUrl: './comorbiditie-detail.component.html'
})
export class ComorbiditieDetailComponent implements OnInit {
  comorbiditie: IComorbiditie | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comorbiditie }) => {
      this.comorbiditie = comorbiditie;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
