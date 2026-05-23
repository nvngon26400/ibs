export class IfaNewAccountOpenImcompleteStatusFormModel {
  constructor() {
    this.title = {
      id: 'SUB020305-01',
      name: '新規口座開設不備状況'
    }
    // 検索項目
    this.brokerCode = '' // 仲介業者コード
    this.chkBrokerCodeExclude = '' // 仲介業者除外フラグ
    this.empCode = '' // 営業員コード
    this.dispatchScheduleDateRange = [] // 発送予定日範囲
    this.dispatchScheduleDateFrom = '' // 発送予定日From
    this.dispatchScheduleDateTo = '' // 発送予定日To
    // 新規口座開設不備状況一覧
    this.gridTable = [
      {
        brokerCode: '', // 新規口座開設不備状況一覧.仲介業者コード
        brokerName: '', // 新規口座開設不備状況一覧.仲介業者名
        empCode: '', // 新規口座開設不備状況一覧.営業員コード
        brokerChargeName: '', // 新規口座開設不備状況一覧.営業員名
        documentName: '', // 新規口座開設不備状況一覧.書類名
        content: '', // 新規口座開設不備状況一覧.内容
        shippingScheduleDate: '' // 新規口座開設不備状況一覧.発送予定日
      }
    ]
  }
}
