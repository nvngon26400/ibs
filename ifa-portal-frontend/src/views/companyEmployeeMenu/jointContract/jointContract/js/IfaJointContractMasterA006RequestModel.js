import Logger from '@/utils/ifaLog.js'
export class IfaJointContractMasterA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCodeParam = obj.brokerCodeParam ? obj.brokerCodeParam : '' // 一覧.仲介業者コード
  }
}

