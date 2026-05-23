import Logger from '@/utils/ifaLog.js'
export class IfaJointContractMasterNewRegisterA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.jointContract = obj.jointContract ? '1' : '0' // 共募契約 0:未契約、1:契約
  }
}
