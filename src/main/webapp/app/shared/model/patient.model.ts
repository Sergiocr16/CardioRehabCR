import { Moment } from 'moment';
import { IInitialAssessment } from 'app/shared/model/initial-assessment.model';
import { IFinalAssessment } from 'app/shared/model/final-assessment.model';
import { ISession } from 'app/shared/model/session.model';
import { IRehabilitationGroup } from 'app/shared/model/rehabilitation-group.model';

export interface IPatient {
  id?: number;
  code?: string;
  age?: number;
  sex?: string;
  ocupation?: string;
  scholarship?: string;
  lastEventOcurred?: Moment;
  deceased?: boolean;
  abandonment?: boolean;
  abandonmentMedicCause?: boolean;
  rehabStatus?: number;
  sessionNumber?: number;
  deleted?: boolean;
  initialAssessments?: IInitialAssessment[];
  finalAssessments?: IFinalAssessment[];
  sessions?: ISession[];
  rehabilitationGroups?: IRehabilitationGroup[];
}

export class Patient implements IPatient {
  constructor(
    public id?: number,
    public code?: string,
    public age?: number,
    public sex?: string,
    public ocupation?: string,
    public scholarship?: string,
    public lastEventOcurred?: Moment,
    public deceased?: boolean,
    public abandonment?: boolean,
    public abandonmentMedicCause?: boolean,
    public rehabStatus?: number,
    public sessionNumber?: number,
    public deleted?: boolean,
    public initialAssessments?: IInitialAssessment[],
    public finalAssessments?: IFinalAssessment[],
    public sessions?: ISession[],
    public rehabilitationGroups?: IRehabilitationGroup[]
  ) {
    this.deceased = this.deceased || false;
    this.abandonment = this.abandonment || false;
    this.abandonmentMedicCause = this.abandonmentMedicCause || false;
    this.deleted = this.deleted || false;
  }
}
