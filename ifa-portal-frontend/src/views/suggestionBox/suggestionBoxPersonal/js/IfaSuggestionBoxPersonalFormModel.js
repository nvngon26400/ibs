export class IfaSuggestionBoxPersonalFormModel {
  constructor() {
    this.titleModel = {
      id: 'SUB00_01-06_1',
      name: 'あなたの要望'
    }
    this.screenComment = ''// 画面コメント
    this.brokerCode = '' // 仲介業者コード
    this.brokerName = '' // 仲介業者名
    this.status = '' // ステータス
    this.registerDate = '' // 登録日
    this.registerDateFrom = '' // 登録日_開始
    this.registerDateTo = '' // 登録日_終了
    this.title = '' // タイトル
    this.requestList = []
  }
}
