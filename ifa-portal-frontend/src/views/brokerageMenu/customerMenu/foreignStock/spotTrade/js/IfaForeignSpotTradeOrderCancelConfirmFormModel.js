export class IfaForeignSpotTradeOrderCancelConfirmFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0301-03_1',
      name: '外国現物取引注文取消確認'
    }
    /** 国籍コード（全角半角）. */
    this.countryCd = ''
    /** 注文番号（数字）. */
    this.orderNumber = ''
    /** 注文Sub番号（数字）. */
    this.orderSubNumber = ''
    /** 銘柄情報. */
    this.brandInformationList = {
      /** 銘柄名（全角半角）. */
      brandName: '',
      /** 銘柄コード（半角英数字）. */
      brandCode: ''
    }
    /** 取引通貨. */
    this.limitPriceText = ''
    /** 市場情報. */
    this.marketInformationList = {
      /** 市場略名. */
      marketName: '',
      /** 国コード（全角半角）. */
      countryCode: '',
      /** 市場コード（全角半角）. */
      marketCode: ''
    }
    /** 取引種別（全角半角）. */
    this.tradeCd = ''
    /** 注文数量. */
    this.foreignQuantity = ''
    /** 価格条件. */
    this.priceConditionsType = ''
    /** 注文単価（数値(小数)）. */
    this.hiddenOrderPrice = ''
    /** 発火条件価格. */
    this.stopOrderPrice = ''
    /** 期間条件. */
    this.periodRadio = ''
    /** 期間. */
    this.period = ''
    /** 預り区分（全角半角）. */
    this.depositTypeName = ''
    /** 決済方法（半角英数字）. */
    this.kessaiHoho = ''
    /** 注文日時. */
    this.orderDate = ''
    /** 国内約定日. */
    this.domesticTradeDate = ''
    /** 現地約定日. */
    this.foreignTradeDate = ''
    /** 売買区分（全角半角）. */
    this.tradeKbn = ''
    /** 銘柄上場ステータス. */
    this.brandListedStatus = ''
  }
}
