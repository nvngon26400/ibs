import Logger from '@/utils/ifaLog.js'
export class IfaNewAccountOpenImcompleteStatusA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? obj.chkBrokerCodeExclude : '' // 仲介業者除外フラグ
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.dispatchScheduleDateFrom = obj.dispatchScheduleDateFrom ? obj.dispatchScheduleDateFrom : '' // 発送予定日From
    this.dispatchScheduleDateTo = obj.dispatchScheduleDateTo ? obj.dispatchScheduleDateTo : '' // 発送予定日To
  }
}
