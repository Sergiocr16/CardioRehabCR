import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CardioRehabCrTestModule } from '../../../test.module';
import { IncomeDiagnosisPatientDetailComponent } from 'app/entities/income-diagnosis-patient/income-diagnosis-patient-detail.component';
import { IncomeDiagnosisPatient } from 'app/shared/model/income-diagnosis-patient.model';

describe('Component Tests', () => {
  describe('IncomeDiagnosisPatient Management Detail Component', () => {
    let comp: IncomeDiagnosisPatientDetailComponent;
    let fixture: ComponentFixture<IncomeDiagnosisPatientDetailComponent>;
    const route = ({ data: of({ incomeDiagnosisPatient: new IncomeDiagnosisPatient(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardioRehabCrTestModule],
        declarations: [IncomeDiagnosisPatientDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(IncomeDiagnosisPatientDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(IncomeDiagnosisPatientDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load incomeDiagnosisPatient on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.incomeDiagnosisPatient).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
