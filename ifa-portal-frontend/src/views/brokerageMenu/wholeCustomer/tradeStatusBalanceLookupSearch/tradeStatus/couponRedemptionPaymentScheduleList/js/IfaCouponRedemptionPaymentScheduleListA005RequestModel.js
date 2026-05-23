import Logger from '@/utils/ifaLog.js'
export class IfaCouponRedemptionPaymentScheduleListA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
  }
}
