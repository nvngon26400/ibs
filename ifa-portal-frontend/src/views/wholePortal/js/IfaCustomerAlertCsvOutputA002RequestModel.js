import Logger from '@/utils/ifaLog.js'
export class IfaCustomerAlertCsvOutputA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCodeList = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コードリスト
    this.tradeCourseList = obj.tradeCourseList ? obj.tradeCourseList : '' // 取引コースリスト
    this.alertClassList = obj.alertClassList ? obj.alertClassList : '' // アラート分類リスト
  }
}
