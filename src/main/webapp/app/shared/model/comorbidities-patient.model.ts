export interface IComorbiditiesPatient {
  id?: number;
  description?: string;
  comorbiditietId?: number;
  exist?: boolean;
  initialAssessmentId?: number;
}

export class ComorbiditiesPatient implements IComorbiditiesPatient {
  constructor(
    public id?: number,
    public description?: string,
    public comorbiditietId?: number,
    public exist?: boolean,
    public initialAssessmentId?: number
  ) {
    this.exist = this.exist || false;
  }
}
