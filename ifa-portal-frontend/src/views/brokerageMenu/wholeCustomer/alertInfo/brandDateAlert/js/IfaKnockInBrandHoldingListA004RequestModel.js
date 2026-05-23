import Logger from '@/utils/ifaLog.js'
export class IfaKnockInBrandHoldingListA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.pdfNoticeUrl = obj.pdfNoticeUrl ? obj.pdfNoticeUrl : '' // PDF通知URL
  }
}
