export class IfaOtherFeeDetailFormModel {
  constructor() {
    // this.brokerName = '' // 仲介業者名
    this.brokerNameCode = '' // 仲介業者コード
    this.targetDateYm = '' // 対象年月
    // this.feeAmountTotal = '' // 合計金額
    // その他報酬詳細一覧
    this.otherFeeDetailList = [
      {
        brokerName: '', // 仲介業者名
        targetDateYm: '', // 対象年月
        feeAmountTotal: '', // 合計金額
        dealerNumber: '', // その他報酬詳細一覧.扱者コード
        sequentialNumber: '', // その他報酬詳細一覧.連番
        feeAmount: '', // その他報酬詳細一覧.金額
        otherFeeMessage: '' // その他報酬詳細一覧.内容
      }
    ]
  }
}
