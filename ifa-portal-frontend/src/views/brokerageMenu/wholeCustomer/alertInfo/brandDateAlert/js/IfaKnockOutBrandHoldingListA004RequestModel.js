import Logger from '@/utils/ifaLog.js'
export class IfaKnockOutBrandHoldingListA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.pdfNoticeUrl = obj.pdfNoticeUrl ? obj.pdfNoticeUrl : '' // PDF通知URL
  }
}
