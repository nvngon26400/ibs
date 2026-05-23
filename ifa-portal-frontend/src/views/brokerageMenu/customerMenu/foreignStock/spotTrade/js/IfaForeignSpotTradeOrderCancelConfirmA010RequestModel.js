import Logger from '@/utils/ifaLog.js'
export class IfaForeignSpotTradeOrderCancelConfirmA010RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    /** 国籍コード（全角半角）. */
    this.countryCd = obj.countryCd ? obj.countryCd : ''
    /** 注文Sub番号（数字）. */
    this.orderSubNumber = obj.orderSubNumber ? obj.orderSubNumber : ''
    /** 取引種別（全角半角）. */
    this.tradeCd = obj.tradeCd ? obj.tradeCd : ''
    /** 注文番号（数字）. */
    this.orderNumber = obj.orderNumber ? obj.orderNumber : ''
    /** 銘柄情報. */
    this.brandInformationList = obj.brandInformationList ? obj.brandInformationList : ''
    /** 取引通貨. */
    this.limitPriceText = obj.limitPriceText ? obj.limitPriceText : ''
    /** 市場情報. */
    this.marketInformationList = obj.marketInformationList ? obj.marketInformationList : ''
    /** 売買区分（全角半角）. */
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : ''
    /** 注文数量. */
    this.foreignQuantity = obj.foreignQuantity ? obj.foreignQuantity : ''
    /** 価格条件. */
    this.priceConditionsType = obj.priceConditionsType ? obj.priceConditionsType : ''
    /** 注文単価（数値(小数)）. */
    this.hiddenOrderPrice = obj.hiddenOrderPrice ? obj.hiddenOrderPrice : ''
    /** 発火条件価格. */
    this.stopOrderPrice2 = obj.stopOrderPrice2 ? obj.stopOrderPrice2 : ''
    /** 期間条件. */
    this.periodRadio = obj.periodRadio ? obj.periodRadio : ''
    /** 期間. */
    this.period = obj.period ? obj.period : ''
    /** 預り区分（全角半角）. */
    this.depositType = obj.depositType ? obj.depositType : ''
    /** 決済方法（半角英数字）. */
    this.kessaiHoho = obj.kessaiHoho ? obj.kessaiHoho : ''
    /** 注文日時. */
    this.orderDate = obj.orderDate ? obj.orderDate : ''
    /** 国内約定日. */
    this.domesticTradeDate = obj.domesticTradeDate ? obj.domesticTradeDate : ''
    /** 現地約定日. */
    this.foreignTradeDate = obj.foreignTradeDate ? obj.foreignTradeDate : ''
  }
}
