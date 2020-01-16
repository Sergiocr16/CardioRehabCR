import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMayorEventsSession } from 'app/shared/model/mayor-events-session.model';

@Component({
  selector: 'jhi-mayor-events-session-detail',
  templateUrl: './mayor-events-session-detail.component.html'
})
export class MayorEventsSessionDetailComponent implements OnInit {
  mayorEventsSession: IMayorEventsSession | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mayorEventsSession }) => {
      this.mayorEventsSession = mayorEventsSession;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
