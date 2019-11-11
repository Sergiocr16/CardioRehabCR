import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PatientService } from 'app/entities/patient/patient.service';
import { IPatient, Patient } from 'app/shared/model/patient.model';

describe('Service Tests', () => {
  describe('Patient Service', () => {
    let injector: TestBed;
    let service: PatientService;
    let httpMock: HttpTestingController;
    let elemDefault: IPatient;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(PatientService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Patient(0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', currentDate, false, false, false, 0, 0, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            lastEventOcurred: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Patient', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            lastEventOcurred: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            lastEventOcurred: currentDate
          },
          returnedFromService
        );
        service
          .create(new Patient(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Patient', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            age: 1,
            sex: 'BBBBBB',
            ocupation: 'BBBBBB',
            lastEventOcurred: currentDate.format(DATE_TIME_FORMAT),
            deceased: true,
            abandonment: true,
            abandonmentMedicCause: true,
            rehabStatus: 1,
            sessionNumber: 1,
            deleted: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            lastEventOcurred: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Patient', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            age: 1,
            sex: 'BBBBBB',
            ocupation: 'BBBBBB',
            lastEventOcurred: currentDate.format(DATE_TIME_FORMAT),
            deceased: true,
            abandonment: true,
            abandonmentMedicCause: true,
            rehabStatus: 1,
            sessionNumber: 1,
            deleted: true
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            lastEventOcurred: currentDate
          },
          returnedFromService
        );
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

      it('should delete a Patient', () => {
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
