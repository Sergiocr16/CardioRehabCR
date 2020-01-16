import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CardioRehabCrTestModule } from '../../../test.module';
import { IncomeDiagnosisDetailComponent } from 'app/entities/income-diagnosis/income-diagnosis-detail.component';
import { IncomeDiagnosis } from 'app/shared/model/income-diagnosis.model';

describe('Component Tests', () => {
  describe('IncomeDiagnosis Management Detail Component', () => {
    let comp: IncomeDiagnosisDetailComponent;
    let fixture: ComponentFixture<IncomeDiagnosisDetailComponent>;
    const route = ({ data: of({ incomeDiagnosis: new IncomeDiagnosis(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardioRehabCrTestModule],
        declarations: [IncomeDiagnosisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(IncomeDiagnosisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(IncomeDiagnosisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load incomeDiagnosis on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.incomeDiagnosis).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
