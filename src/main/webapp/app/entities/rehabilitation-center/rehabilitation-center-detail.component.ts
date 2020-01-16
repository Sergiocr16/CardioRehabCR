import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';

@Component({
  selector: 'jhi-rehabilitation-center-detail',
  templateUrl: './rehabilitation-center-detail.component.html'
})
export class RehabilitationCenterDetailComponent implements OnInit {
  rehabilitationCenter: IRehabilitationCenter | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rehabilitationCenter }) => {
      this.rehabilitationCenter = rehabilitationCenter;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
