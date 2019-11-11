export interface IDepressiveSymptomsSession {
  id?: number;
  description?: string;
  depressiveSymptomId?: number;
  exist?: boolean;
  sessionId?: number;
}

export class DepressiveSymptomsSession implements IDepressiveSymptomsSession {
  constructor(
    public id?: number,
    public description?: string,
    public depressiveSymptomId?: number,
    public exist?: boolean,
    public sessionId?: number
  ) {
    this.exist = this.exist || false;
  }
}
