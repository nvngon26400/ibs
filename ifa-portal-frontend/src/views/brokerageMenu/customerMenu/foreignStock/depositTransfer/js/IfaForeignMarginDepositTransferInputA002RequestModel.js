import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginDepositTransferInputA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.accountSelect = obj.accountSelect ? obj.accountSelect : '' // 口座選択
  }
}
