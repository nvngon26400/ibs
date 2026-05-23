import Logger from '@/utils/ifaLog.js'
export class IfaContactInputA007RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.toiawaseDCd = obj.toiawaseDCd ? obj.toiawaseDCd : '' // 分類（大）
    this.toiawaseDMei = obj.toiawaseDMei ? obj.toiawaseDMei : '' // 分類名（大）
  }
}
