export class IfaComplianceReportInfoUpdateA003RequestModel {
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
    this.corFileName1BeforeChange = obj ? obj.corFileName1BeforeChange : ''
    this.corFileName1Delete = obj ? obj.corFileName1Delete : ''
    this.corFileName2BeforeChange = obj ? obj.corFileName2BeforeChange : ''
    this.corFileName2Delete = obj ? obj.corFileName2Delete : ''
    this.corFileName3BeforeChange = obj ? obj.corFileName3BeforeChange : ''
    this.corFileName3Delete = obj ? obj.corFileName3Delete : ''
    this.corContentsFileNameBeforeChange = obj ? obj.corContentsFileNameBeforeChange : ''
    this.corContentsFileNameDelete = obj ? obj.corContentsFileNameDelete : ''
  }
}
