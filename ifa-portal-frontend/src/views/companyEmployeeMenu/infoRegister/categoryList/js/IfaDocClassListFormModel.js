export class IfaDocClassListFormModel {
  constructor() {
    this.screenTitle = '資料種別一覧'
    this.infoCategoryList = [
      {
        t9nInfoCat: '',
        t9nName: ''
      }
    ] // お知らせカテゴリリスト
    this.category = '' // カテゴリ
    this.t9nInfoCat = '' // 新規登録.カテゴリID
    this.checkedDocumentId = [] // カテゴリIDリスト（チェック付き）
    this.checkedDocumentCategory = [] // カテゴリリスト（チェック付き）
    this.registerCategoryList = [{
      infoCat: '', // 登録済カテゴリ.カテゴリID
      name: '' // 登録済カテゴリ.カテゴリ
    }]
    this.updateCategoryList = [
      {
        infoCat: '',
        name: ''
      }
    ]
  }
}
