import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INonSpecificPain } from 'app/shared/model/non-specific-pain.model';

@Component({
  selector: 'jhi-non-specific-pain-detail',
  templateUrl: './non-specific-pain-detail.component.html'
})
export class NonSpecificPainDetailComponent implements OnInit {
  nonSpecificPain: INonSpecificPain | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nonSpecificPain }) => {
      this.nonSpecificPain = nonSpecificPain;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
