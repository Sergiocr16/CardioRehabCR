import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CardioRehabCrTestModule } from '../../../test.module';
import { DepressiveSymptomDetailComponent } from 'app/entities/depressive-symptom/depressive-symptom-detail.component';
import { DepressiveSymptom } from 'app/shared/model/depressive-symptom.model';

describe('Component Tests', () => {
  describe('DepressiveSymptom Management Detail Component', () => {
    let comp: DepressiveSymptomDetailComponent;
    let fixture: ComponentFixture<DepressiveSymptomDetailComponent>;
    const route = ({ data: of({ depressiveSymptom: new DepressiveSymptom(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardioRehabCrTestModule],
        declarations: [DepressiveSymptomDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DepressiveSymptomDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DepressiveSymptomDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.depressiveSymptom).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
