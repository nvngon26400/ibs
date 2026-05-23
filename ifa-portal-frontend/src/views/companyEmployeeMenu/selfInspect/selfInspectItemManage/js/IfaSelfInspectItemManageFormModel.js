export class IfaSelfInspectItemManageFormModel {
  constructor() {
    this.title = {
      name: '自己点検項目管理',
      id: 'SUB0506_02-01'
    }
    this.registerDateList = [ // 登録年月リスト
      {
        'dateId': '', // 年月ID
        'dateName': '' // 年月名
      }
    ]
    this.registerDateListSelectBox = [] // 登録年月リストセレクトボックス用
    this.brokerTypeList = [ // 業者区分リスト
      {
        'brokerType': '', // 業者区分リスト.業者区分
        'classificationName': '', // 業者区分リスト.区分名称
        'registerStatus': '' // 業者区分リスト.登録状況
      }
    ]
    this.brokerTypeListSelectBox = [] // セレクトボックス用
    // 自己点検項目リスト（グリッドテーブル用）
    // 更新、削除後の値を保持する
    this.selfAssessmentItemList = [
      {
        'selfCheckItemId': '', // 自己点検項目リスト.自己点検項目ID
        'registerDate': '', // 自己点検項目リスト.登録年月
        'sortNumber': '', // 自己点検項目リスト.ソート番号
        'selfAssessmentItemName': '', // 自己点検項目リスト.自己点検項目名
        'answer': '', // 自己点検項目リスト.回答
        'reasonRequiredFlag': '' // 自己点検項目リスト.理由必須フラグ
      }
    ]

    // 自己点検項目リスト（Excelファイル用）
    this.selfAssessmentItemListExcel = [
      {
        'registerDate': '', // 登録年月
        'brokerType': '', // 業者区分
        'sortNumber': '', // ソート番号
        'selfAssessmentItemName': '', // 自己点検項目名
        'answer': '', // 回答
        'reasonRequiredFlag': '' // 理由必須フラグ
      }
    ]

    this.assignMonthList = '' // 検索条件部.登録年月
    this.brokerType = '' // 検索条件部.業者区分
    this.attachFile = null // アップロードファイル
  }
}
