import Logger from '@/utils/ifaLog.js'
export class IfaSelfInspectItemManageA010RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.selfCheckItemId = obj.selfCheckItemId ? obj.selfCheckItemId : '' // 自己点検項目ID
    this.brokerType = obj.brokerType ? obj.brokerType : '' // 業者区分
  }
}
