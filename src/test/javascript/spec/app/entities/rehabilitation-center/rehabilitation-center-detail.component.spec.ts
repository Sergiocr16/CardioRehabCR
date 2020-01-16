import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CardioRehabCrTestModule } from '../../../test.module';
import { RehabilitationCenterDetailComponent } from 'app/entities/rehabilitation-center/rehabilitation-center-detail.component';
import { RehabilitationCenter } from 'app/shared/model/rehabilitation-center.model';

describe('Component Tests', () => {
  describe('RehabilitationCenter Management Detail Component', () => {
    let comp: RehabilitationCenterDetailComponent;
    let fixture: ComponentFixture<RehabilitationCenterDetailComponent>;
    const route = ({ data: of({ rehabilitationCenter: new RehabilitationCenter(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardioRehabCrTestModule],
        declarations: [RehabilitationCenterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RehabilitationCenterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RehabilitationCenterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load rehabilitationCenter on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rehabilitationCenter).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
