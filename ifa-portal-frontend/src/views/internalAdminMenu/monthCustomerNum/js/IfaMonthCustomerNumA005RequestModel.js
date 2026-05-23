import Logger from '@/utils/ifaLog.js'
export class IfaMonthCustomerNumA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.dateYm = obj.dateYm ? obj.dateYm.replace(/\//g, '') : '' // 年月
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
  }
}
