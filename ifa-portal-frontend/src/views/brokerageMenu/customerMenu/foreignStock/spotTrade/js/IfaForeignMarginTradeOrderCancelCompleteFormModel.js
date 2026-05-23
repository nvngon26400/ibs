export class IfaForeignMarginTradeOrderCancelCompleteFormModel {
  constructor() {
    this.title = '米株信用取引注文取消完了' // タイトル
    this.finishMassage = '' // 取消完了
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    this.butenCode = '' // 部店
    this.accountNumber = '' // 口座番号
    this.orderDayTime = '' // 国内注文日時
    this.tradeNameText = '' // 取引種別
    this.marginDueDate = '' // 信用期日
    this.foreignQuantity = '0' // 数量 【初期値】0
    this.repaymentMethodRadio = '' // 返済建玉指定方法
    this.sortOrderRadio = '' // 返済選択順序
    this.orderPriceKindList = '' // 価格条件
    this.currencyCode = '' // 指値注文単価_通貨
    this.stopOrderPrice2 = '0' // 逆指値発火条件価格 【初期値】0
    this.periodRadio = '' // 期間条件 【初期値】空白
    this.period = '' // 期間 【初期値】空白
    this.depositType = '' // 預り区分 【初期値】空白
    this.currencyTypeRadio = '' // 決済方法 【初期値】空白
    this.orderSubNumber = '' // 注文Sub番号
    this.brandInfo = {} // 銘柄情報オブジェクト
    this.marketInfo = {} // 市場情報オブジェクト
    this.stockTradeType = '' // 株取引区分
    this.orderNumber = '' // 注文番号
    this.hiddenOrderPrice = '' // 注文単価
    this.tradeKbn = '' // 売買区分
    this.kanyuKbn = '' // 勧誘区分
    this.jutyuKbn = '' // 受注方法区分
    this.warningApplyTrade = '' // ワーニング申請取引
    this.importantMatterTypeClass = '' // 重要事項説明区分
    this.solicitationEtfType = '' // 乗換え勧誘(ETF)区分
    this.engPubType = '' // 英文開示銘柄説明区分
    this.domesticTradeDate = '' // 国内約定日
    this.foreignTradeDate = '' // 現地約定日
  }
}
