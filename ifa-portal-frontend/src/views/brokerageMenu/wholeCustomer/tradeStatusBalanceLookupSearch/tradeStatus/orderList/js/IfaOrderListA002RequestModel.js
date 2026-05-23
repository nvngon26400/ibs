import Logger from '@/utils/ifaLog.js'
export class IfaOrderListA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? obj.chkBrokerCodeExclude : '' // 仲介業者除外
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 支店コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.customerNameKanjiKana = obj.customerNameKanjiKana ? obj.customerNameKanjiKana : '' // 顧客名(漢字/カナ)
    this.customerNameKanjiKanaTerms = obj.customerNameKanjiKanaTerms ? obj.customerNameKanjiKanaTerms : '' // 顧客名(漢字/カナ)_条件
    this.course = obj.course ? obj.course : '' // 取引コース
    this.orderSelected = obj.orderSelected ? obj.orderSelected : '' // 商品
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.periodYmFrom = obj.periodYmFrom ? obj.periodYmFrom : '' // 期間指定（From)
    this.periodYmTo = obj.periodYmTo ? obj.periodYmTo : '' // 期間指定（To)
  }
}
