export class IfaSelfInspectBlotterFormModel {
  constructor() {
    this.title = {
      id: 'SUB0401_02-01',
      name: '自己点検記録簿'
    }
    this.assignMonth // 表示年月 【初期値】当月
    this.confirmationDate = '' // 確認日(最終更新日時)
    this.registerDateList = [] // 登録年月リスト
    this.selfAssessmentList = [ // 自己点検記録簿一覧
      // {
      //   selfAssessmentItemName: '', // 自己点検項目ID
      //   confirmation: '', // 確認 【初期値】理由必須フラグ ＝ "1"（理由必須）の場合、"-" 上記以外の場合、"未選択"
      //   answerReason: '', // 回答理由
      //   selfCheckItemId: '', // 自己点検項目ID
      //   answer: '', // 回答
      //   reasonRequiredFlag: '', // 理由必須フラグ
      //   answerCount: '', // 回答回数
      //   answerResult: '' // 回答結果
      //   nextSelfCheckId: '' // 自己点検ID
      // }, ...
    ]
  }
}
