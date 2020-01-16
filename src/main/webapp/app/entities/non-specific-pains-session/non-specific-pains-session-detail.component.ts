import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INonSpecificPainsSession } from 'app/shared/model/non-specific-pains-session.model';

@Component({
  selector: 'jhi-non-specific-pains-session-detail',
  templateUrl: './non-specific-pains-session-detail.component.html'
})
export class NonSpecificPainsSessionDetailComponent implements OnInit {
  nonSpecificPainsSession: INonSpecificPainsSession | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nonSpecificPainsSession }) => {
      this.nonSpecificPainsSession = nonSpecificPainsSession;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
