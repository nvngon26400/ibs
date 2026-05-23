import Logger from '@/utils/ifaLog.js'
export class IfaSubscriptPeriodMasterRegisterInputA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode12 = obj.brandCode12 ? obj.brandCode12 : '' // 銘柄コード
    this.bbPresentationFrom = obj.bbPresentationFrom ? obj.bbPresentationFrom : '' // ブックビルディング申込期間（開始）
  }
}
