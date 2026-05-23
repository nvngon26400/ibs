export class IfaNoticeInfoFormModel {
  constructor() {
    // 注文情報一覧
    this.noticeInfoList = [
      {
        cautionKind: '', // 注意情報一覧.分類
        memo: '', // 注意情報一覧.メモ
        accuralDate: '', // 注意情報一覧.発生日
        dealtPerson: '' // 注意情報一覧.取扱者
      }
    ]
    // 取引制限一覧
    this.tradeRestrictionList = [
      {
        restrictionCode: '', // 取引制限一覧.制限番号
        restrictionContents: '', // 取引制限一覧.制限内容
        confirmDeadline: '', // 取引制限一覧.確認期限
        warningMessage: '' // 確認期限注意書き
      }
    ]
  }
}
