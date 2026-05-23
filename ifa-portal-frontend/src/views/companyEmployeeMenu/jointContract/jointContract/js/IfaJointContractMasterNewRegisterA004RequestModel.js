import Logger from '@/utils/ifaLog.js'
export class IfaJointContractMasterNewRegisterA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
  }
}
