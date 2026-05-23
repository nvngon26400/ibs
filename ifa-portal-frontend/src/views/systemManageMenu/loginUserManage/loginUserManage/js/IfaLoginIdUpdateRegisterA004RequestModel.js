import Logger from '@/utils/ifaLog.js'
export class IfaLoginIdUpdateRegisterA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.privId = obj.privId ? obj.privId : '' // 所属権限コード
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    // this.subBrokerId = obj.subBrokerId ? obj.subBrokerId : '' // 仲介業者支店コード
  }
}
