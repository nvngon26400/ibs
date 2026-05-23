export class IfaLinkRequestModel {
  constructor(obj) {
    this.urlId = obj.urlId ? obj.urlId : ''
    this.patternId = obj.patternId ? obj.patternId : ''
    this.httpMethod = obj.httpMethod ? obj.httpMethod : ''
  }
}