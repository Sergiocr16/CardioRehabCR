export interface INonSpecificPainsSession {
  id?: number;
  description?: string;
  nonSpecificPainId?: number;
  exist?: boolean;
  sessionId?: number;
}

export class NonSpecificPainsSession implements INonSpecificPainsSession {
  constructor(
    public id?: number,
    public description?: string,
    public nonSpecificPainId?: number,
    public exist?: boolean,
    public sessionId?: number
  ) {
    this.exist = this.exist || false;
  }
}
