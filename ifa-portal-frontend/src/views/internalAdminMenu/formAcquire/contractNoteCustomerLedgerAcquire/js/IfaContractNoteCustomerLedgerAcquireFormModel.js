export class IfaContractNoteCustomerLedgerAcquireFormModel {
  constructor() {
    this.title = {
      name: '取引日記帳・顧客勘定元帳取得',
      id: 'SUB0402_02-01'
    }

    // form: 入力項目
    this.brokerCode = '' // 仲介業者コード 【初期値】""
    this.chkBrokerCodeExclude = false // 仲介業者除外 【初期値】false
    this.createDate = [] // 作成日 【初期値】Fromは当日の3ヶ月前の日付 Toは前日の日付
    this.ledgerClass = '' // 帳簿種別 【初期値】""

    // A003: ダウンロードのリクエストで送信する項目
    // 取引日記帳・顧客勘定元帳一覧
    this.tradeDiaryCustomerLedger = {
      fileDirectory: '', // ファイルディレクトリ
      ledgerName: '', // 帳票名
      ledgerClass: '', // 帳票種別
      createDate: '', // 作成日
      downloadFileName: '' // ダウンロードファイル名
    }

    // A001: 初期化で受信した内容: Hidden 項目
    // 帳票種別リスト
    this.ledgerClassList = [
      // A001 で次の形式のリストをサーバから受診する
      // {
      //   codeID: '', // 帳票種別リスト.コードID
      //   codeName: '' // 帳票種別リスト.コード名称
      // }
    ]
    this.fileDirectory = '' // ファイルディレクトリ
  }
}
