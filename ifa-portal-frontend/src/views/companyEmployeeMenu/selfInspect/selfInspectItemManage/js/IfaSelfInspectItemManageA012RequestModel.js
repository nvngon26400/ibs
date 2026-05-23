import Logger from '@/utils/ifaLog.js'
export class IfaSelfInspectItemManageA012RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    // 自己点検項目リスト
    this.selfAssessmentItemList = obj.selfAssessmentItemListExcel ? obj.selfAssessmentItemListExcel : [
      // registerDate 自己点検項目リスト.登録年月
      // brokerType 自己点検項目リスト.業者区分
      // sortNumber 自己点検項目リスト.表示順
      // selfAssessmentItemName  自己点検項目リスト.チェック項目
      // answer 自己点検項目リスト.回答
      // reasonRequiredFlag 自己点検項目リスト.理由必須
    ]
  }
}
