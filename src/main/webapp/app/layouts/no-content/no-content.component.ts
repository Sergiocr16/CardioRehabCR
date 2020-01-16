import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-no-content',
  templateUrl: './no-content.component.html',
  styleUrls: ['./no-content.component.scss']
})
export class NoContentComponent implements OnInit {
  @Input()
  icon: string;

  @Input()
  text: string;

  @Input()
  text2: string;

  constructor() {}

  ngOnInit() {}
}
