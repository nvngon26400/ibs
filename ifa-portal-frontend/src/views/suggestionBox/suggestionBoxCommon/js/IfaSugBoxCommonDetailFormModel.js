export class IfaSugBoxCommonDetailFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB00_02-06_2',
      name: '皆様からの要望詳細'
    }
    this.title = '' // タイトル
    this.registerDate = '' // 登録日
    this.status = '' // ステータス
    this.suggestion = '' // 要望内容
    this.sbcNo = '' // 皆様からの要望No
    this.attachFile1 = '' // 添付ファイル1
    this.attachFile2 = '' // 添付ファイル2
    this.attachFile3 = '' // 添付ファイル3
    this.answerList = [] // 回答一覧
  }
}
