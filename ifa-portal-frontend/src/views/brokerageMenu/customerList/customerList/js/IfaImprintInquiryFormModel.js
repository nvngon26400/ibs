export class IfaImprintInquiryFormModel {
  constructor() {
    this.errMessage = '' // 帳票取得APIエラーメッセージ
    this.code = '' // 帳票取得APIエラーコード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    this.ledgerCode = '' // ID-書類名.帳票コード
    this.ledgerName = '' // ID-書類名.帳票名
    this.acceptStandardDate = '' // 受入基準日
    this.ledgerFile = '' // 帳票ファイル
  }
}
