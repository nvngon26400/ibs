// SUB0202_0110-01_2 その他余力拘束注文確認のフォームモデル
export class IfaOtherRemainPowerRestrainInputConfirmFormModel {
  constructor() {
    this.screenId = 'SUB0202_0110-01_2'
    this.screenTitle = 'その他余力拘束注文確認'
    this.jrNisageneralAccountFlag = '' // ジュニアNISA口座フラグ
    this.accountType = '' // 口座区分
    this.restrainType = '' // 拘束種別
    this.netAmount = '' // 拘束金額（買付余力）
    this.isaSeityoLimitAmount = '' // 拘束金額（NISA成長投資枠）
    this.isaTsumitateLimitAmount = '' // 拘束金額（NISAつみたて投資枠）
    this.restrainDateTo = '' // 拘束期限
    this.restrainReason = '' // 拘束理由
    this.buyingPowerTotalAfter = '' // 注文後の買付可能金額
    this.isaSeityoLimitAmountAfter = '' // 注文後NISA成長投資枠
    this.isaTsumitateLimitAmountAfter = '' // 注文後NISAつみたて投資枠
  }
}
