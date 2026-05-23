import Logger from '@/utils/ifaLog.js'
export class IfaInquirySearchForManagerA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.toiawaseDCd = obj.toiawaseDCd ? obj.toiawaseDCd : '' // 分類（大）
    this.toiawaseDMei = obj.toiawaseDMei ? obj.toiawaseDMei : '' // 分類名（大）
    this.toiawaseCd = obj.toiawaseCd ? obj.toiawaseCd : '' // 分類（中）
    this.toiawaseMei = obj.toiawaseMei ? obj.toiawaseMei : '' // 分類名（中）
  }
}
