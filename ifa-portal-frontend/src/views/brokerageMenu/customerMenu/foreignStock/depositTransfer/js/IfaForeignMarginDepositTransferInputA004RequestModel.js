import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginDepositTransferInputA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.accountSelect = obj.accountSelect ? obj.accountSelect : '' // 口座選択
    this.destinationAmount = obj.destinationAmount ? obj.destinationAmount : '' // 指示金額
  }
}
