export class IfaComplianceReportInfoRegisterA002RequestModel {
  constructor(obj) {
    this.corCommunicationDate = obj ? obj.yearMonth : ''
    this.corTitle = obj ? obj.title : ''
    this.corBrief = obj ? obj.overview : ''
    this.corFileName1 = obj ? obj.file1.file?.name : ''
    this.corFileDesc1 = obj ? obj.file1.comment : ''
    this.corFileName2 = obj ? obj.file2.file?.name : ''
    this.corFileDesc2 = obj ? obj.file2.comment : ''
    this.corFileName3 = obj ? obj.file3.file?.name : ''
    this.corFileDesc3 = obj ? obj.file3.comment : ''
    this.corContentsFileName = obj ? obj.contentFile.file?.name : ''
    this.corContents = obj ? obj.contentFile.comment : ''
  }
}
