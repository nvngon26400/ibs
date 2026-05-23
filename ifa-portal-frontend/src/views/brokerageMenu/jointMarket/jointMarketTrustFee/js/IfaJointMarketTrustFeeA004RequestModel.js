import Logger from '@/utils/ifaLog.js'
export class IfaJointMarketTrustFeeA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? '1' : '0'
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 支店コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.customerNameKanjiKana = obj.customerNameKanjiKana ? obj.customerNameKanjiKana : '' // 顧客名(漢字/カナ)
    this.customerNameKanjiKanaTerms = obj.customerNameKanjiKanaTerms ? obj.customerNameKanjiKanaTerms : '' // 顧客名(漢字/カナ)_条件
    this.course = obj.courseSelected ? obj.courseSelected : '' // 取引コース
    this.dailyMonthlyCountingUnitTotal = obj.dailyMonthlyCountingUnit ? obj.dailyMonthlyCountingUnit : '' // 集計単位(日次/月次累計)
    this.detailCustomerCurrencyCountingUnit = obj.detailCustomerCurrencyCountingUnit ? obj.detailCustomerCurrencyCountingUnit : '' // 集計単位(明細/顧客/通貨毎)
    if (obj.dailyMonthlyCountingUnit === '0') {
      // 日次
      this.period = obj.periodDate ? obj.periodDate : '' // 期間指定
    } else if (obj.dailyMonthlyCountingUnit === '1') {
      // 月次
      this.period = obj.periodMonth ? obj.periodMonth : '' // 期間指定
    }
    this.securityClass = obj.securityClassList ? obj.securityClassList : '' // 証券種別
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
  }
}
