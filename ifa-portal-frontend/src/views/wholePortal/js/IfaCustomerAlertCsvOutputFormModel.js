export class IfaCustomerAlertCsvOutputFormModel {
  constructor() {
    this.title = {
      id: 'SUB01-02',
      name: '顧客アラート'
    }
    this.csvFileName = '顧客アラート通知'
    this.brokerCode = '' // 仲介業者コード
    this.courseSelected = [] // 取引コース 【初期値】未選択
    this.tradeCourseList = '' // 取引コースA002リクエスト用
    this.alertSelected = [] // アラート分類 【初期値】全選択
    this.alertClassList = '' // アラート分類A002リクエスト用
  }
}
