import Logger from '@/utils/ifaLog.js'
export class IfaLevelFeeA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude === true ? '1' : '0' // 仲介業者除外(true: '1', false: '0')
    this.branchCode = ''  // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名
    this.customerNameKanjiKanaTerms = '' // 顧客名検索オプション

    if (obj.brokerChargeBranchCountingUnit !== '0') { // 集計単位(仲介業者/営業員/顧客) != 仲介業者
      this.branchCode = obj.branchCode ? obj.branchCode : ''
      this.empCode = obj.empCode ? obj.empCode : ''
      if (obj.brokerChargeBranchCountingUnit === '2') { // 集計単位(仲介業者/営業員/顧客) = 顧客
        this.butenCode = obj.butenCode ? obj.butenCode : ''
        this.accountNumber = obj.accountNumber ? obj.accountNumber : ''
        this.customerNameKanjiKana = obj.customerNameKanjiKana ? obj.customerNameKanjiKana : ''
        this.customerNameKanjiKanaTerms = obj.customerNameKanjiKanaTerms ? obj.customerNameKanjiKanaTerms : ''
      }
    }

    this.dailyMonthlyCountingUnit = obj.dailyMonthlyCountingUnit ? obj.dailyMonthlyCountingUnit : '' // 集計単位(日次/月次)
    this.brokerChargeBranchCountingUnit = obj.brokerChargeBranchCountingUnit ? obj.brokerChargeBranchCountingUnit : '' // 集計単位(仲介業者/営業員/顧客)
    
    if (obj.dailyMonthlyCountingUnit === '0') { // 集計単位(日次/月次) = 日次
      this.periodYmInput = obj.periodDate ? obj.periodDate : '' // 期間指定_入力
    } else { // 集計単位(日次/月次) = 月次
      this.periodYmInput = obj.yearMonth ? obj.yearMonth : '' // 期間指定_入力
    }
  }
}
