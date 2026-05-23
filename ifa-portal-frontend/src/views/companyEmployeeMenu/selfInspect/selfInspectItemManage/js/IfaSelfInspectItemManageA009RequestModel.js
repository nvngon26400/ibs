import Logger from '@/utils/ifaLog.js'
export class IfaSelfInspectItemManageA009RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    // 自己点検項目リスト
    this.selfAssessmentItemList = obj.selfAssessmentItemList ? obj.selfAssessmentItemList : [
      // selfCheckItemId 自己点検項目リスト.自己点検項目ID
      // sortNumber 自己点検項目リスト.表示順
      // selfAssessmentItemName  自己点検項目リスト.チェック項目
      // answer 自己点検項目リスト.回答
      // reasonRequiredFlag 自己点検項目リスト.理由必須
    ]
    this.assignMonthList = obj.assignMonthList ? obj.assignMonthList : '' // 登録年月
    this.brokerType = obj.brokerType ? obj.brokerType : '' // 業者区分
    this.brokerTypeList = obj.brokerTypeList ? obj.brokerTypeList : [] // 業者区分リスト
  }
}
