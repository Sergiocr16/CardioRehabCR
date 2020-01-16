import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMayorEvent } from 'app/shared/model/mayor-event.model';

@Component({
  selector: 'jhi-mayor-event-detail',
  templateUrl: './mayor-event-detail.component.html'
})
export class MayorEventDetailComponent implements OnInit {
  mayorEvent: IMayorEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mayorEvent }) => {
      this.mayorEvent = mayorEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
