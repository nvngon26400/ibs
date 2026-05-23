export class IfaBrokerageSubLedgerAcquireFormModel {
  constructor() {
    this.brokerCode = '' // 仲介業者コード 【初期値】""
    this.chkBrokerCodeExclude = '' // 仲介業者除外 【初期値】false
    this.createDate = [] // 作成日
    this.createDateFrom = '' // 作成日From 【初期値】Fromは当日の3ヶ月前の日付 Toは前日の日付
    this.createDateTo = '' // 作成日To
    this.periodAlert = '' // 期間指定メッセージ
    this.brokerageSubLedgerList = [{ // 仲介業補助簿一覧
      createDate: '', // 作成日
      brokerCode: '', // 仲介業者コード
      brokerName: '', // 仲介業者名
      targetSecurity: '', // 対象商品
      dl: '', // DL
      fileName: '' // ファイル名
    }]
    this.fileDirectory = '' // ファイルディレクトリ
  }
}
