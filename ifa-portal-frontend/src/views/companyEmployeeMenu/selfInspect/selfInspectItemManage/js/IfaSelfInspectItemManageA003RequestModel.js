import Logger from '@/utils/ifaLog.js'
export class IfaSelfInspectItemManageA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.assignMonthList = obj.assignMonthList ? obj.assignMonthList : '' // 登録年月
    this.brokerType = obj.brokerType ? obj.brokerType : '' // 業者区分
  }
}
