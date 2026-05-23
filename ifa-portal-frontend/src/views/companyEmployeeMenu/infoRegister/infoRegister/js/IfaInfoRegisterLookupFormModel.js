export class IfaInfoRegisterLookupFormModel {
  constructor() {
    this.screenTitle = {
      id: 'SUB0501_01-01',
      name: 'インフォメーション照会'
    }
    this.title = '' // タイトル
    this.documentKindInput = '' // カテゴリ 【初期値】カテゴリが開いている状態
    this.t9nInfoCat = '' // カテゴリID
    this.requiredFlag = '' // 必須フラグ
    this.infoList = [{ // 情報一覧
      t9nName: '', // カテゴリ
      lastUpdateDate: '', // 最終更新日
      author: '', // 作成・更新者
      createUpdateTime: '', // 作成・更新日時
      notificationId: '', // お知らせID
      corReferenceCondition: '', // 参照範囲
      registerDayTime: '' // 登録日時
    }]

    this.selectedNotificationCategoryList = [
      {
        t9nInfoCat: '0',
        t9nName: '全て',
        requiredFlag: '1'
      }
    ]
  }
}
