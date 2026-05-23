// SUB0202_0110-02_1 その他余力拘束注文取消確認のA002リクエストモデル
import Logger from '@/utils/ifaLog.js'
export class IfaOtherRemainPowerRestrainCancelConfirmA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)

    this.accountType = obj.accountType ? obj.accountType : '' // 口座区分
    this.restrainType = obj.restrainType ? obj.restrainType : '' // 拘束種別
    this.netAmount = obj.netAmount ? obj.netAmount : '' // 拘束金額（買付余力）
    this.isaSeityoLimitAmount = obj.isaSeityoLimitAmount ? obj.isaSeityoLimitAmount : '' // 拘束金額（NISA成長投資枠）
    this.isaTsumitateLimitAmount = obj.isaTsumitateLimitAmount ? obj.isaTsumitateLimitAmount : '' // 拘束金額（NISAつみたて投資枠）
    this.restrainDateTo = obj.restrainDateTo ? obj.restrainDateTo : '' // 拘束期限
    this.restrainReason = obj.restrainReason ? obj.restrainReason : '' // 拘束理由
    this.acceptDateTime = obj.acceptDateTime ? obj.acceptDateTime : ''// 受注日時
    this.jrNisageneralAccountFlag = obj.jrNisageneralAccountFlag ? obj.jrNisageneralAccountFlag : '' // ジュニアNISA口座開設フラグ
    this.orderNo = obj.orderNo ? obj.orderNo : '' // EC受注番号
    this.isaSeityoLimitAmountAfter = obj.isaSeityoLimitAmountAfter ? obj.isaSeityoLimitAmountAfter : '' // 注文後NISA成長投資枠
    this.isaTsumitateLimitAmountAfter = obj.isaTsumitateLimitAmountAfter ? obj.isaTsumitateLimitAmountAfter : '' // 注文後NISAつみたて投資枠
  }
}
