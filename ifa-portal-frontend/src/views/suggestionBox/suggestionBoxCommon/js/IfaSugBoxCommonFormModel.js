export class IfaSugBoxCommonFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB00_02-06_1',
      name: '皆様からの要望'
    }
    this.screenComment = '' // 画面コメント
    this.sbcNo = '' // 皆様からの要望No
    this.updateDate = '' // 更新日
    this.createDate = '' // 登録日
    this.title = '' // タイトル
    this.category = '' // カテゴリ
    this.status = ' ' // ステータス
    this.registerDate = [] // 登録日
    this.gridTableList = [] // 皆様からの要望一覧（明細部）
  }
}
