import Logger from '@/utils/ifaLog.js'
export class IfaBrokerCodeClosingMonthLoginA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.settingClosingMonth = obj.settingClosingMonth ? obj.settingClosingMonth : '' // 設定する決算月
  }
}
