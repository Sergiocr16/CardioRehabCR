import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CardioRehabCrTestModule } from '../../../test.module';
import { NonSpecificPainsSessionDetailComponent } from 'app/entities/non-specific-pains-session/non-specific-pains-session-detail.component';
import { NonSpecificPainsSession } from 'app/shared/model/non-specific-pains-session.model';

describe('Component Tests', () => {
  describe('NonSpecificPainsSession Management Detail Component', () => {
    let comp: NonSpecificPainsSessionDetailComponent;
    let fixture: ComponentFixture<NonSpecificPainsSessionDetailComponent>;
    const route = ({ data: of({ nonSpecificPainsSession: new NonSpecificPainsSession(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardioRehabCrTestModule],
        declarations: [NonSpecificPainsSessionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NonSpecificPainsSessionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NonSpecificPainsSessionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load nonSpecificPainsSession on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.nonSpecificPainsSession).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
