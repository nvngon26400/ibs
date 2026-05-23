import Logger from '@/utils/ifaLog.js'
export class IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.registerDate = obj.registerDate ? obj.registerDate : '' // 登録年月
    this.selfAssessmentList = obj.requestModelA002 ? obj.requestModelA002 : [] // 自己点検リスト
  }
}
