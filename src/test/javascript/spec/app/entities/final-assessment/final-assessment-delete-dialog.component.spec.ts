import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CardioRehabCrTestModule } from '../../../test.module';
import { FinalAssessmentDeleteDialogComponent } from 'app/entities/final-assessment/final-assessment-delete-dialog.component';
import { FinalAssessmentService } from 'app/entities/final-assessment/final-assessment.service';

describe('Component Tests', () => {
  describe('FinalAssessment Management Delete Component', () => {
    let comp: FinalAssessmentDeleteDialogComponent;
    let fixture: ComponentFixture<FinalAssessmentDeleteDialogComponent>;
    let service: FinalAssessmentService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardioRehabCrTestModule],
        declarations: [FinalAssessmentDeleteDialogComponent]
      })
        .overrideTemplate(FinalAssessmentDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FinalAssessmentDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FinalAssessmentService);
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
