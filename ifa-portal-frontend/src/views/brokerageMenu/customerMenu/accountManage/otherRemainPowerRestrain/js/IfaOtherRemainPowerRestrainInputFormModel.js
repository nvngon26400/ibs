// SUB0202_0110-01_1 その他余力拘束注文入力のフォームモデル
export class IfaOtherRemainPowerRestrainInputFormModel {
  constructor() {
    this.screenId = 'SUB0202_0110-01_1' // 画面ID
    this.inputTitle = 'その他余力拘束注文入力' // 注文入力タイトル
    this.listTitle = 'その他余力拘束注文一覧' // 一覧タイトル
    this.jrNisageneralAccountFlag = '' // ジュニアNISA口座フラグ
    this.accountType = '' // 口座区分
    this.restrainType = '' // 拘束種別
    this.netAmount = '' // 拘束金額（買付余力）
    this.buyingPowerTotal = '' // 買付余力
    this.buyingPowerTotalJrnisa = '' // 買付余力(JrNISA)
    this.isaSeityoLimitAmount = '' // 拘束金額（NISA成長投資枠）
    this.isaSeityoBuyLimit = '' // 総合NISA(成長投資枠）買付可能枠(当年)
    this.isaTsumitateLimitAmount = '' // 拘束金額（NISAつみたて投資枠）
    this.isaTsumitateBuyLimit = '' // 総合NISA(つみたて）買付可能枠(当年)
    this.restrainDateTo = '' // 拘束期限
    this.restrainReason = '' // 拘束理由
    this.confirmItem = '' // 確認項目
    this.orderData = [ // その他余力拘束注文一覧
      {
        orderNo: '', // EC受注番号
        accountType: '', // 口座区分
        restrainType: '', // 拘束区分
        netAmount: '', // 金額（買付余力）
        isaSeityoLimitAmount: '', // 金額（NISA成長投資枠）
        isaTsumitateLimitAmount: '', // 金額（NISAつみたて投資枠）
        restrainDateFrom: '', // 拘束開始日
        restrainDateTo: '', // 拘束期限
        restrainReason: '', // 拘束理由
        acceptDateTime: '', // 受注日時
        orderDate: '', // 発注日
        secId: '', // 商品区分
        callcenterId: '', // 受付経路区分
        validId: '', // 有効区分
        orderId: '', // 取消区分
        torikeshiKbn: '' // 取消区分(DB)
      }
    ]
  }
}
