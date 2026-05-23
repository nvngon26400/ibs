import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginDepositTransferConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.entrustDepositTransferClassification = obj.accountSelect ? obj.accountSelect : '' // 委託保証金振替区分
    this.destinationAmount = obj.destinationAmount ? obj.destinationAmount : '' // 指示金額
  }
}
