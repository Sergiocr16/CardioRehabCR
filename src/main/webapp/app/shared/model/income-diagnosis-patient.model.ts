export interface IIncomeDiagnosisPatient {
  id?: number;
  description?: string;
  incomeDiagnosisId?: number;
  exist?: boolean;
  initialAssessmentId?: number;
}

export class IncomeDiagnosisPatient implements IIncomeDiagnosisPatient {
  constructor(
    public id?: number,
    public description?: string,
    public incomeDiagnosisId?: number,
    public exist?: boolean,
    public initialAssessmentId?: number
  ) {
    this.exist = this.exist || false;
  }
}
