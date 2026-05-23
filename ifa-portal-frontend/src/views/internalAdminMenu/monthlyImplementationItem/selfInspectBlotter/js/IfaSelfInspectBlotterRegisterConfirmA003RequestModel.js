import Logger from '@/utils/ifaLog.js'
export class IfaSelfInspectBlotterRegisterConfirmA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.registerDate = obj.assignMonth ? obj.assignMonth : '' // 登録年月
    this.selfAssessmentList = obj.selfAssessmentList ? obj.selfAssessmentList : [] // 自己点検記録簿一覧

    // 自己点検記録簿一覧.確認が"-"の場合NULLに置換する
    this.selfAssessmentList = this.selfAssessmentList.map(val => ({
      ...val,
      confirmation: val.confirmation === '-' ? null : val.confirmation
    }))
  }
}
