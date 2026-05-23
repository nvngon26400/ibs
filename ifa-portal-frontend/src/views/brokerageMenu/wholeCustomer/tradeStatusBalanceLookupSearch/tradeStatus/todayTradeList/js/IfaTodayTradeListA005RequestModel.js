import Logger from '@/utils/ifaLog.js'
export class IfaTodayTradeListA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.csvData = obj.csvData ? obj.csvData : [] // csvData
  }
}
