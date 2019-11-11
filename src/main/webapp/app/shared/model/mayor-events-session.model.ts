export interface IMayorEventsSession {
  id?: number;
  description?: string;
  mayorEventId?: number;
  exist?: boolean;
  sessionId?: number;
}

export class MayorEventsSession implements IMayorEventsSession {
  constructor(
    public id?: number,
    public description?: string,
    public mayorEventId?: number,
    public exist?: boolean,
    public sessionId?: number
  ) {
    this.exist = this.exist || false;
  }
}
