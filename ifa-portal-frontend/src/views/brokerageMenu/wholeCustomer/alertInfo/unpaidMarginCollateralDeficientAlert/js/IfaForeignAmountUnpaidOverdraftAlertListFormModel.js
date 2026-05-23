export class IfaForeignAmountUnpaidOverdraftAlertListFormModel {
  constructor() {
    this.warningMessage = '' // 注意文言_メッセージ
    // 外貨未入金・赤残アラート一覧
    this.foreignAmountUnpaidOverdraftAlertList = [
      {
        accuralDate: '', // 発生日
        brokerCode: '', // 仲介業者コード
        brokerName: '', // 仲介業者名
        branchCode: '', // 支店コード
        branchName: '', // 支店名
        empCode: '', // 営業員コード
        brokerChargeName: '', // 営業員名
        butenCode: '', // 部店
        accountNumber: '', // 口座番号
        course: '', // 取引コース
        customerNameKanji: '', // 顧客名(漢字)
        customerNameKana: '', // 顧客名(カナ)
        currencyCode: '', // 通貨
        foreignCurrencyUndepositedNotPaymentInfo: '' // 外貨未入金・赤残
      }
    ]
  }
}
