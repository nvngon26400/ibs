export class IfaSugBoxPersonalDetailFromBrokerFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB0511_01-02',
      name: '仲介業者からの要望詳細'
    }
    this.sbpNo = '' // あなたの要望No
    this.title = '' // タイトル
    this.status = '' // ステータス
    this.suggestion = '' // 要望内容
    this.attachFile1 = '' // 添付ファイル1
    this.attachFile2 = '' // 添付ファイル2
    this.attachFile3 = '' // 添付ファイル3
    this.registerDate = '' // 登録日
    this.registeredAnswerList = [] // 登録済回答一覧
    this.newAnswerList = [] // 新規回答一覧
  }
}
