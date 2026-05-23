export class IfaSelfInspectBlotterDetailFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB0506_01-02',
      name: '自己点検記録簿兼個人情報の安全管理に関する報告（確認）'
    }
    this.registerDate = '' // 登録年月
    this.brokerName = '' // 仲介業者名
    this.brokerCode = '' // 仲介業者コード
    // 自己点検記録簿詳細一覧
    this.selfAssessmentList = [
      // {
      //   selfInspectItemName: '', // 自己点検項目名
      //   confirmation: '', // 確認
      //   answerResult: '', // 回答結果
      //   answerReason: '', // 回答理由
      // }
    ]
    this.memo = '' // メモ
  }
}
