import { IRehabilitationGroup } from 'app/shared/model/rehabilitation-group.model';
import { IIncomeDiagnosis } from 'app/shared/model/income-diagnosis.model';
import { IComorbiditie } from 'app/shared/model/comorbiditie.model';
import { IMinorEvent } from 'app/shared/model/minor-event.model';
import { IMayorEvent } from 'app/shared/model/mayor-event.model';
import { IDepressiveSymptom } from 'app/shared/model/depressive-symptom.model';
import { INonSpecificPain } from 'app/shared/model/non-specific-pain.model';
import { IAppUser } from 'app/shared/model/app-user.model';

export interface IRehabilitationCenter {
  id?: number;
  name?: string;
  telephone?: string;
  deleted?: boolean;
  status?: number;
  rehabilitationGroups?: IRehabilitationGroup[];
  incomeDiagnoses?: IIncomeDiagnosis[];
  comorbidities?: IComorbiditie[];
  minorEvents?: IMinorEvent[];
  mayorEvents?: IMayorEvent[];
  depressiveSymptoms?: IDepressiveSymptom[];
  nonSpecificPains?: INonSpecificPain[];
  appUsers?: IAppUser[];
}

export class RehabilitationCenter implements IRehabilitationCenter {
  constructor(
    public id?: number,
    public name?: string,
    public telephone?: string,
    public deleted?: boolean,
    public status?: number,
    public rehabilitationGroups?: IRehabilitationGroup[],
    public incomeDiagnoses?: IIncomeDiagnosis[],
    public comorbidities?: IComorbiditie[],
    public minorEvents?: IMinorEvent[],
    public mayorEvents?: IMayorEvent[],
    public depressiveSymptoms?: IDepressiveSymptom[],
    public nonSpecificPains?: INonSpecificPain[],
    public appUsers?: IAppUser[]
  ) {
    this.deleted = this.deleted || false;
  }
}
