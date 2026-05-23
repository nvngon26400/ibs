import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMutualFundOrderOtherInfoA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.ifaOrderNo = obj.ifaOrderNo ? obj.ifaOrderNo : '' // IFA注文番号
    this.ifaOrderSubNo = obj.ifaOrderSubNo ? obj.ifaOrderSubNo : '' // IFA注文サブ番号
  }
}
