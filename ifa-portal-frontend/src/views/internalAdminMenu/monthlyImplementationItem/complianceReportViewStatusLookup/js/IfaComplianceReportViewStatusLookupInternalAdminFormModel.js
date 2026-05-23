export class IfaComplianceReportViewStatusLookupInternalAdminFormModel {
  constructor() {
    this.titleModel = {
      id: 'SUB0401_01-01',
      name: 'コンプライアンス通信閲覧状況照会(内部管理責任者用)'
    }
    this.titleList = []
    this.title = '' // タイトル 【初期値】未選択
    this.brokerName = '' // 仲介業者名
    this.partialSearch = '' // 仲介業者名（部分検索） 【初期値】（部分検索）
    // this.employeeName = '' // 営業員名
    this.partialSearch = '' // 営業員名（部分検索） 【初期値】（部分検索）
    this.viewStatusType = '' // 閲覧状況 【初期値】全て
    this.viewTarget = '' // 閲覧対象 【初期値】全て
    this.titleThisMonth = '' // タイトル（当月）
    this.titleLastMonth = '' // タイトル（過去月）
    // エラー: 「コンプライアンス通信状況一覧」が、項目辞書に存在しません。
    this.confirmationDate = '' // 確認日
    this.brokerCode = '' // 仲介業者コード
    this.brokerName = '' // 仲介業者名
    this.employeeId = '' // 営業員コード
    this.employeeName = '' // 営業員名
    // this.title = '' // タイトル
    this.confirmationStatus = '' // 状態
  }
}
