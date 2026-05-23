import Logger from '@/utils/ifaLog.js'
export class IfaPersonalInfoManageLedgerListA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.processDayTimeFrom = obj.processTargetPeriod[0] ? obj.processTargetPeriod[0] : '' // 処理日時（From）
    this.processDayTimeTo = obj.processTargetPeriod[1] ? obj.processTargetPeriod[1] : '' // 処理日時（To）
  }
}
