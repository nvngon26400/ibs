export class IfaForeignMarginTradeOrderCancelConfirmFormModel {
  constructor() {
    this.title = '米株信用取引注文取消確認' // タイトル
    this.warningMessage = '' // 取消未完了
    this.tradeCd = '' // 取引種別
    this.marginDueDate = '' // 信用期日
    this.foreignQuantity = '0' // 数量 【初期値】0
    this.repaymentMethodRadio = '' // 返済建玉指定方法
    this.sortOrderRadio = '' // 返済選択順序
    this.orderPriceKindList = '' // 価格条件
    this.stopOrderPrice2 = '0' // 逆指値発火条件価格 【初期値】0
    this.periodRadio = '' // 期間条件 【初期値】空白
    this.period = '' // 期間 【初期値】空白
    this.depositType = '' // 預り区分 【初期値】空白
    this.currencyTypeRadio = '' // 決済方法
    this.orderSubNumber = '' // 注文Sub番号
    this.kanyuKbn = '' // 勧誘区分
    this.jutyuKbn = '' // 受注方法区分
    this.warningApplyTrade = '' // ワーニング申請取引
    this.importantMatterTypeClass = '' // 重要事項説明区分
    this.solicitationEtfType = '' // 乗換え勧誘(ETF)区分
    this.engPubType = '' // 英文開示銘柄説明区分
    this.orderNumber = '' // 注文番号
    this.currencyCode = '' // 取引通貨
    this.tradeKbn = '' // 売買区分
    this.hiddenOrderPrice = '0' // 注文単価
    this.orderDate = '' // 注文日時
    this.domesticTradeDate = '' // 国内約定日
    this.foreignTradeDate = '' // 現地約定日
    this.stockTradeType = '' // 株取引区分
    this.brandInfo = {} // 銘柄情報オブジェクト
    this.marketInfo = {} // 市場情報オブジェクト
  }
}
