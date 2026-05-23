export class IfaComplianceReportInfoRegisterFormModel {
  constructor() {
    this.yearMonth = '' // 通信年月
    this.title = '' // タイトル
    this.overview = ''// 概要
    this.isUpdateMode = false
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
  }
}
