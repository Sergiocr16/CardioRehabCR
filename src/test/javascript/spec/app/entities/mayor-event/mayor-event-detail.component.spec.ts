import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CardioRehabCrTestModule } from '../../../test.module';
import { MayorEventDetailComponent } from 'app/entities/mayor-event/mayor-event-detail.component';
import { MayorEvent } from 'app/shared/model/mayor-event.model';

describe('Component Tests', () => {
  describe('MayorEvent Management Detail Component', () => {
    let comp: MayorEventDetailComponent;
    let fixture: ComponentFixture<MayorEventDetailComponent>;
    const route = ({ data: of({ mayorEvent: new MayorEvent(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardioRehabCrTestModule],
        declarations: [MayorEventDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MayorEventDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MayorEventDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load mayorEvent on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mayorEvent).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
