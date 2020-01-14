import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FinalAssessmentService } from 'app/entities/final-assessment/final-assessment.service';
import { IFinalAssessment, FinalAssessment } from 'app/shared/model/final-assessment.model';

describe('Service Tests', () => {
  describe('FinalAssessment Service', () => {
    let injector: TestBed;
    let service: FinalAssessmentService;
    let httpMock: HttpTestingController;
    let elemDefault: IFinalAssessment;
    let expectedResult: IFinalAssessment | IFinalAssessment[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FinalAssessmentService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new FinalAssessment(
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
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            executionDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FinalAssessment', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            executionDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            executionDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new FinalAssessment())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FinalAssessment', () => {
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
            isWorking: true,
            deceased: true,
            abandonment: true,
            abandonmentMedicCause: true,
            hospitalized: true,
            deleted: true,
            reevaluation: true,
            executionDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            executionDate: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FinalAssessment', () => {
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
            isWorking: true,
            deceased: true,
            abandonment: true,
            abandonmentMedicCause: true,
            hospitalized: true,
            deleted: true,
            reevaluation: true,
            executionDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            executionDate: currentDate
          },
          returnedFromService
        );
        service
          .query()
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

      it('should delete a FinalAssessment', () => {
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
