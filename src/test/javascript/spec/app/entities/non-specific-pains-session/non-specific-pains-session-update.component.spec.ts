import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CardioRehabCrTestModule } from '../../../test.module';
import { NonSpecificPainsSessionUpdateComponent } from 'app/entities/non-specific-pains-session/non-specific-pains-session-update.component';
import { NonSpecificPainsSessionService } from 'app/entities/non-specific-pains-session/non-specific-pains-session.service';
import { NonSpecificPainsSession } from 'app/shared/model/non-specific-pains-session.model';

describe('Component Tests', () => {
  describe('NonSpecificPainsSession Management Update Component', () => {
    let comp: NonSpecificPainsSessionUpdateComponent;
    let fixture: ComponentFixture<NonSpecificPainsSessionUpdateComponent>;
    let service: NonSpecificPainsSessionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardioRehabCrTestModule],
        declarations: [NonSpecificPainsSessionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NonSpecificPainsSessionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NonSpecificPainsSessionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NonSpecificPainsSessionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NonSpecificPainsSession(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new NonSpecificPainsSession();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
