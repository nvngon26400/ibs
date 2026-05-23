import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeOrderCancelConfirmA010RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.orderSubNumber = obj.orderSubNumber ? obj.orderSubNumber : '' // 注文Sub番号
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.currencyTypeRadio = obj.currencyTypeRadio ? obj.currencyTypeRadio : '' // 決済方法
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.jutyuKbn = obj.jutyuKbn ? obj.jutyuKbn : '' // 受注方法区分
    this.warningApplyTrade = obj.warningApplyTrade ? obj.warningApplyTrade : '' // ワーニング申請取引
    this.importantMatterTypeClass = obj.importantMatterTypeClass ? obj.importantMatterTypeClass : '' // 重要事項説明区分
    this.solicitationEtfType = obj.solicitationEtfType ? obj.solicitationEtfType : '' // 乗換え勧誘(ETF)区分
    this.engPubType = obj.engPubType ? obj.engPubType : '' // 英文開示銘柄説明区分
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.orderNumber = obj.orderNumber ? obj.orderNumber : '' // 注文番号
    this.brandCode = obj.brandInfo.brandCode ? obj.brandInfo.brandCode : '' // 銘柄情報.銘柄コード
    this.brandName = obj.brandInfo.brandName ? obj.brandInfo.brandName : '' // 銘柄情報.銘柄名
    this.currencyCode = obj.currencyCode ? obj.currencyCode : '' // 取引通貨
    this.marketName = obj.marketInfo.marketName ? obj.marketInfo.marketName : '' // 市場情報.市場略名
    this.marketCode = obj.marketInfo.marketCode ? obj.marketInfo.marketCode : '' // 市場情報.市場コード
    this.countryCode = obj.marketInfo.countryCode ? obj.marketInfo.countryCode : '' // 市場情報.国コード
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
    this.foreignQuantity = obj.foreignQuantity ? obj.foreignQuantity : '' // 注文数量
    this.orderPriceKindList = obj.orderPriceKindList ? obj.orderPriceKindList : '' // 価格条件
    this.hiddenOrderPrice = obj.hiddenOrderPrice ? obj.hiddenOrderPrice : '' // 注文単価
    this.stopOrderPrice2 = obj.stopOrderPrice2 ? obj.stopOrderPrice2 : '' // 発火条件価格
    this.periodRadio = obj.periodRadio ? obj.periodRadio : '' // 期間条件
    this.period = obj.period ? obj.period : '' // 期間
    this.orderDate = obj.orderDate ? obj.orderDate : '' // 注文日時
    this.domesticTradeDate = obj.domesticTradeDate ? obj.domesticTradeDate : '' // 国内約定日
    this.foreignTradeDate = obj.foreignTradeDate ? obj.foreignTradeDate : '' // 現地約定日
    this.repaymentMethodRadio = obj.repaymentMethodRadio ? obj.repaymentMethodRadio : '' // 返済建玉指定方法
    this.sortOrderRadio = obj.sortOrderRadio ? obj.sortOrderRadio : '' // 返済選択順序
    this.marginDueDate = obj.marginDueDate ? obj.marginDueDate : '' // 信用期日
  }
}
