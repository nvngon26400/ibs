import Logger from '@/utils/ifaLog.js'
export class IfaWithdrawInputA001RequestModel {
  constructor(minPayDate) {
    Logger.debug(minPayDate)
    this.minPayDate = minPayDate // 出金日として指定可能な直近の日付
  }
}
