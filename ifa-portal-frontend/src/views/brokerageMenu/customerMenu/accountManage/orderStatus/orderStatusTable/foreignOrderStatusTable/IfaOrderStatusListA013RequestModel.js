import Logger from '@/utils/ifaLog.js'
export class IfaOrderStatusListA013RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.countryCd = obj.countryCd ? obj.countryCd : '' // 国籍コード
    this.orderSubNumber = obj.orderSubNumber ? obj.orderSubNumber : '' // 注文Sub番号
  }
}
