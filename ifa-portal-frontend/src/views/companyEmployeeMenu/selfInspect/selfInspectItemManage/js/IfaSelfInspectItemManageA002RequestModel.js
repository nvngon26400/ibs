import Logger from '@/utils/ifaLog.js'
export class IfaSelfInspectItemManageA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.assignMonthList = obj.assignMonthList ? obj.assignMonthList : '' // 登録年月
    this.brokerTypeList = obj.brokerTypeList ? obj.brokerTypeList : [] // 業者区分リスト
  }
}
