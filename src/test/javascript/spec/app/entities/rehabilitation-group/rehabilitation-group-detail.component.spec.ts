import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CardioRehabCrTestModule } from '../../../test.module';
import { RehabilitationGroupDetailComponent } from 'app/entities/rehabilitation-group/rehabilitation-group-detail.component';
import { RehabilitationGroup } from 'app/shared/model/rehabilitation-group.model';

describe('Component Tests', () => {
  describe('RehabilitationGroup Management Detail Component', () => {
    let comp: RehabilitationGroupDetailComponent;
    let fixture: ComponentFixture<RehabilitationGroupDetailComponent>;
    const route = ({ data: of({ rehabilitationGroup: new RehabilitationGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CardioRehabCrTestModule],
        declarations: [RehabilitationGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RehabilitationGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RehabilitationGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rehabilitationGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
