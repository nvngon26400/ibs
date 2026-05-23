import Logger from '@/utils/ifaLog.js'
export class IfaSbiWrapManageFeeA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.registeredDateFrom = obj.period[0] ? obj.period[0] : '' // 登録日From
    this.registeredDateTo = obj.period[1] ? obj.period[1] : '' // 登録日To
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? obj.chkBrokerCodeExclude : false // 仲介業者除外
  }
}
