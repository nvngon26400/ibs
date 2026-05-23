export class IfaComplianceReportInfoUpdateFormModel {
  constructor() {
    this.corLecId = '' // 画面リンク情報
    this.yearMonth = '' // 通信年月
    this.title = '' // タイトル
    this.overview = ''// 概要
    this.isUpdateMode = true
    this.file1 = {
      file: null,
      comment: ''
    }
    this.file2 = {
      file: null,
      comment: ''
    }
    this.file3 = {
      file: null,
      comment: ''
    }
    this.contentFile = {
      file: null,
      comment: ''
    }
    this.corFileName1BeforeChange = ''
    this.corFileName1Delete = ''
    this.corFileName2BeforeChange = ''
    this.corFileName2Delete = ''
    this.corFileName3BeforeChange = ''
    this.corFileName3Delete = ''
    this.corContentsFileNameBeforeChange = ''
    this.corContentsFileNameDelete = ''
  }
}
