// SUB0202_0110-01_3 その他余力拘束注文完了のフォームモデル
export class IfaOtherRemainPowerRestrainInputCompleteFormModel {
  constructor() {
    this.screenId = 'SUB0202_0110-01_3'
    this.screenTitle = 'その他余力拘束注文完了'
    this.accountType = '' // 口座区分
    this.restrainType = '' // 拘束種別
    this.netAmount = '' // 拘束金額（買付余力）
    this.isaSeityoLimitAmount = '' // 拘束金額（NISA成長投資枠）
    this.isaTsumitateLimitAmount = '' // 拘束金額（NISAつみたて投資枠）
    this.restrainDateTo = '' // 拘束期限
    this.restrainReason = '' // 拘束理由
    this.acceptDateTime = '' // 受注日時
    this.buyingPowerTotalAfter = '' // 注文後の買付可能金額
    this.orderNo = '' // EC受注番号
    this.jrNisageneralAccountFlag = '' // ジュニアNISA口座フラグ
    this.isaSeityoLimitAmountAfter = '' // 注文後NISA成長投資枠
    this.isaTsumitateLimitAmountAfter = '' // 注文後NISAつみたて投資枠
  }
}
