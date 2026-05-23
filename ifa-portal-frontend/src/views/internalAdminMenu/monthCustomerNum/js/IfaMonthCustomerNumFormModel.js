export class IfaMonthCustomerNumFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB0407_01',
      name: '月末口座数'
    }
    this.comment = ''
    this.brokerCode = '' //  仲介業者コード
    this.chkBrokerCodeExclude = '' // 仲介業者除外
    this.dateYmRange = [] // 対象年月 【初期値】Fromは前月の年月、 Toは前月の年月
    this.periodAlert = '※過去5年間の履歴を照会いただけます。（2025年7月以前は口座数のみ照会可能です）' // 期間指定メッセージ
  }
}
