export class IfaComplianceReportViewStatusLookupManagerFormModel {
  constructor() {
    this.titleModel = {
      id: 'SUB0505_02-01',
      name: 'コンプライアンス通信閲覧状況照会(管理者用)'
    }
    this.complianceReportList = []// タイトル 【初期値】未選択
    this.brokerName = '' // 仲介業者名
    this.brokerChargeName = '' // 営業員名
    this.viewStatus = '' // 閲覧状況 【初期値】全て
    this.viewNecessity = '' // 閲覧要否 【初期値】全て
    this.titleThisMonth = '' // タイトル（当月）
    this.titleLastMonth = '' // タイトル（過去月）
    this.confirmationDate = '' // 確認日
    this.brokerCode = '' // 仲介業者コード
    this.brokerName = '' // 仲介業者名
    this.empCode = '' // 営業員コード
    this.brokerChargeName = '' // 営業員名
    this.complianceReportTitle = '' // タイトル
    this.complianceReportStateName = '' // 状態
    this.userId = '' // ユーザーID
    this.corBrowseFlag = '' // 閲覧要否フラグ
    this.lectureId = '' // LECTURE_ID
    this.viewExcludeSetting = ''// 閲覧不要設定
    this.viewReportFlag = ''// 閲覧報告フラグ
  }
}
