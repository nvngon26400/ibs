import Logger from '@/utils/ifaLog.js'
export class IfaJointContractMasterA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
    this.jointContract = obj.jointContract ? obj.jointContract : '' // 共募契約
  }
}

