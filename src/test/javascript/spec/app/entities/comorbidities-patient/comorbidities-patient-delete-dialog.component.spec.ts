import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CardioRehabCrTestModule } from '../../../test.module';
import { ComorbiditiesPatientDeleteDialogComponent } from 'app/entities/comorbidities-patient/comorbidities-patient-delete-dialog.component';
import { ComorbiditiesPatientService } from 'app/entities/comorbidities-patient/comorbidities-patient.service';

describe('Component Tests', () => {
  describe('ComorbiditiesPatient Management Delete Component', () => {
    let comp: ComorbiditiesPatientDeleteDialogComponent;
    let fixture: ComponentFixture<ComorbiditiesPatientDeleteDialogComponent>;
    let service: ComorbiditiesPatientService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardioRehabCrTestModule],
        declarations: [ComorbiditiesPatientDeleteDialogComponent]
      })
        .overrideTemplate(ComorbiditiesPatientDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ComorbiditiesPatientDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ComorbiditiesPatientService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
