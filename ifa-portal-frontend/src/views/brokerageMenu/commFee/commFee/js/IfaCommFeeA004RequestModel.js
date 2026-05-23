import Logger from '@/utils/ifaLog.js'
export class IfaCommFeeA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude === true ? '1' : '0' // 仲介業者除外(true: '1', false: '0')
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 支店コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.displayContent = obj.displayContent ? obj.displayContent : '' // 表示内容
    this.dailyMonthlyCountingUnit = obj.dailyMonthlyCountingUnit ? obj.dailyMonthlyCountingUnit : '' // 集計単位(日次/月次)
    this.brokerChargeBranchCountingUnit = obj.brokerChargeBranchCountingUnit ? obj.brokerChargeBranchCountingUnit : '' // 集計単位(仲介業者/営業員/支店)
    this.aggregatorHandlerCountingUnit = obj.aggregatorHandlerCountingUnit ? obj.aggregatorHandlerCountingUnit : '' // 集計単位(集計/扱者)
    if (obj.dailyMonthlyCountingUnit === '0') {
      // 日次
      this.periodYmInput = obj.periodDate ? obj.periodDate : '' // 期間指定_入力
    } else if (obj.dailyMonthlyCountingUnit === '1') {
      // 月次
      this.periodYmInput = obj.yearMonth ? obj.yearMonth : '' // 期間指定_入力
    }
  }
}
