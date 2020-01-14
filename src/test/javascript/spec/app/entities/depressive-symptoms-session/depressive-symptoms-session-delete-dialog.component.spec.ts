import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CardioRehabCrTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { DepressiveSymptomsSessionDeleteDialogComponent } from 'app/entities/depressive-symptoms-session/depressive-symptoms-session-delete-dialog.component';
import { DepressiveSymptomsSessionService } from 'app/entities/depressive-symptoms-session/depressive-symptoms-session.service';

describe('Component Tests', () => {
  describe('DepressiveSymptomsSession Management Delete Component', () => {
    let comp: DepressiveSymptomsSessionDeleteDialogComponent;
    let fixture: ComponentFixture<DepressiveSymptomsSessionDeleteDialogComponent>;
    let service: DepressiveSymptomsSessionService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardioRehabCrTestModule],
        declarations: [DepressiveSymptomsSessionDeleteDialogComponent]
      })
        .overrideTemplate(DepressiveSymptomsSessionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DepressiveSymptomsSessionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DepressiveSymptomsSessionService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
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
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.clear();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
