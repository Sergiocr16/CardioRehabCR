export interface IMinorEventsSession {
  id?: number;
  description?: string;
  minorEventId?: number;
  exist?: boolean;
  sessionId?: number;
}

export class MinorEventsSession implements IMinorEventsSession {
  constructor(
    public id?: number,
    public description?: string,
    public minorEventId?: number,
    public exist?: boolean,
    public sessionId?: number
  ) {
    this.exist = this.exist || false;
  }
}
