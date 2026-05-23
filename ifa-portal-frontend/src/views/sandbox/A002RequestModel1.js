import Logger from '@/utils/ifaLog.js'
export class A002RequestModel1 {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.customerNameKanjiKana = obj.customerNameKanjiKana ? obj.customerNameKanjiKana : '' // 顧客名（漢字／カナ）
    this.customerNameKanjiKanaTerms = obj.customerNameKanjiKanaTerms ? obj.customerNameKanjiKanaTerms : '' // 顧客名（漢字／カナ）_条件
    this.course = obj.course ? obj.course : '' // 取引コース
    this.domesticMarginPositionListBrandCode = obj.domesticMarginPositionListBrandCode ? obj.domesticMarginPositionListBrandCode : '' // 銘柄コード
  }
}
