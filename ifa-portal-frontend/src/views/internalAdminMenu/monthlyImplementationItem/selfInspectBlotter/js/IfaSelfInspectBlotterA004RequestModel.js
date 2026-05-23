import Logger from '@/utils/ifaLog.js'
export class IfaSelfInspectBlotterA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.registerDate = obj.assignMonth ? obj.assignMonth : '' // 登録年月
    this.selfAssessmentList = obj.selfAssessmentList ? obj.selfAssessmentList : [] // 自己点検リスト
  }
}
