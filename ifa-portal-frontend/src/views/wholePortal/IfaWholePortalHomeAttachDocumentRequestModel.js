export class IfaWholePortalHomeAttachDocumentRequestModel {
  constructor(obj) {
    this.disclaimer = obj.disclaimer ?? ''
    this.fileDirectory = obj.fileDirectory ?? ''
    this.attachFile = obj.attachFile ?? ''
  }
}
