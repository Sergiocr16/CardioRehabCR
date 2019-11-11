import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { InitialAssessmentService } from 'app/entities/initial-assessment/initial-assessment.service';
import { IInitialAssessment, InitialAssessment } from 'app/shared/model/initial-assessment.model';

describe('Service Tests', () => {
  describe('InitialAssessment Service', () => {
    let injector: TestBed;
    let service: InitialAssessmentService;
    let httpMock: HttpTestingController;
    let elemDefault: IInitialAssessment;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(InitialAssessmentService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new InitialAssessment(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a InitialAssessment', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new InitialAssessment(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a InitialAssessment', () => {
        const returnedFromService = Object.assign(
          {
            smoking: 'BBBBBB',
            weight: 'BBBBBB',
            size: 'BBBBBB',
            iMC: 'BBBBBB',
            hbiac: 'BBBBBB',
            baselineFunctionalCapacity: 'BBBBBB',
            lDL: 'BBBBBB',
            hDL: 'BBBBBB',
            cardiovascularRisk: 'BBBBBB',
            deleted: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of InitialAssessment', () => {
        const returnedFromService = Object.assign(
          {
            smoking: 'BBBBBB',
            weight: 'BBBBBB',
            size: 'BBBBBB',
            iMC: 'BBBBBB',
            hbiac: 'BBBBBB',
            baselineFunctionalCapacity: 'BBBBBB',
            lDL: 'BBBBBB',
            hDL: 'BBBBBB',
            cardiovascularRisk: 'BBBBBB',
            deleted: true
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a InitialAssessment', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
