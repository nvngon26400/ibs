export class IfaSelfInspectBlotterConfirmManagerFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB0506_01-01',
      name: '自己点検記録簿確認（管理者用）'
    }
    this.brokerCode = '' // 仲介業者コード
    this.brokerName = '' // 仲介業者名
    this.registerDateList = [] // 登録年月 【初期値】当月
    this.registerDate = ''
    this.answerStatus = '0' // 回答状況 【初期値】全て
    this.answerResult = '0' // 回答結果 【初期値】全て

    // 自己点検記録簿一覧
    this.selfAssessmentList = [
    //   {
    //     brokerCode: '', // 仲介業者コード
    //     brokerName: '', // 仲介業者名
    //     registerDate: '', // 登録年月
    //     confirmationDate: '', // 最新更新日 ・ 確認日
    //     confirm1: '', // 項目1_回答内容
    //     answerReason1: '', // 項目1_回答理由
    //     answerResult1: '', // 項目1_回答結果
    //     confirm2: '', // 項目2_回答内容
    //     answerReason2: '', // 項目2_回答理由
    //     answerResult2: '', // 項目2_回答結果
    //     confirm3: '', // 項目3_回答内容
    //     answerReason3: '', // 項目3_回答理由
    //     answerResult3: '', // 項目3_回答結果
    //     confirm4: '', // 項目4_回答内容
    //     answerReason4: '', // 項目4_回答理由
    //     answerResult4: '', // 項目4_回答結果
    //     confirm5: '', // 項目5_回答内容
    //     answerReason5: '', // 項目5_回答理由
    //     answerResult5: '', // 項目5_回答結果
    //     confirm6: '', // 項目6_回答内容
    //     answerReason6: '', // 項目6_回答理由
    //     answerResult6: '', // 項目6_回答結果
    //     confirm7: '', // 項目7_回答内容
    //     answerReason7: '', // 項目7_回答理由
    //     answerResult7: '', // 項目7_回答結果
    //     confirm8: '', // 項目8_回答内容
    //     answerReason8: '', // 項目8_回答理由
    //     answerResult8: '', // 項目8_回答結果
    //     confirm9: '', // 項目9_回答内容
    //     answerReason9: '', // 項目9_回答理由
    //     answerResult9: '', // 項目9_回答結果
    //     confirm10: '', // 項目10_回答内容
    //     answerReason10: '', // 項目10_回答理由
    //     answerResult10: '', // 項目10_回答結果
    //     confirm11: '', // 項目11_回答内容
    //     answerReason11: '', // 項目11_回答理由
    //     answerResult11: '', // 項目11_回答結果
    //     confirm12: '', // 項目12_回答内容
    //     answerReason12: '', // 項目12_回答理由
    //     answerResult12: '', // 項目12_回答結果
    //     confirm13: '', // 項目13_回答内容
    //     answerReason13: '', // 項目13_回答理由
    //     answerResult13: '', // 項目13_回答結果
    //     confirm14: '', // 項目14_回答内容
    //     answerReason14: '', // 項目14_回答理由
    //     answerResult14: '', // 項目14_回答結果
    //     confirm15: '', // 項目15_回答内容
    //     answerReason15: '', // 項目15_回答理由
    //     answerResult15: '', // 項目15_回答結果
    //     confirm16: '', // 項目16_回答内容
    //     answerReason16: '', // 項目16_回答理由
    //     answerResult16: '', // 項目16_回答結果
    //     confirm17: '', // 項目17_回答内容
    //     answerReason17: '', // 項目17_回答理由
    //     answerResult17: '', // 項目17_回答結果
    //     confirm18: '', // 項目18_回答内容
    //     answerReason18: '', // 項目18_回答理由
    //     answerResult18: '', // 項目18_回答結果
    //     confirm19: '', // 項目19_回答内容
    //     answerReason19: '', // 項目19_回答理由
    //     answerResult19: '', // 項目19_回答結果
    //     confirm20: '', // 項目20_回答内容
    //     answerReason20: '', // 項目20_回答理由
    //     answerResult20: '' // 項目20_回答結果
    //   }
    ]
  }
}
