export class IfaForeignSpotTradeOrderCancelCompleteFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0301-03_2',
      name: '外国現物取引注文取消完了'
    }
    /** 注文番号（数字）. */
    this.orderNumber = ''
    /** 注文Sub番号（数字）. */
    this.orderSubNumber = ''
    /** 銘柄情報. */
    this.brandInformationList = {
    /** 銘柄コード（半角英数字）. */
      brandCode: '',
      /** 銘柄名（全角半角）. */
      brandName: ''
    }
    /** 取引通貨. */
    this.limitPriceText = ''
    /** 市場情報. */
    this.marketInformationList = {
      /** 市場略名. */
      marketName: '',
      /** 市場コード（全角半角）. */
      marketCode: '',
      /** 国コード（全角半角）. */
      countryCode: ''
    }
    /** 売買区分（全角半角）. */
    this.tradeKbn = ''
    /** 注文数量. */
    this.foreignQuantity = ''
    /** 価格条件. */
    this.priceConditionsType = ''
    /** 注文単価（数値(小数)）. */
    this.hiddenOrderPrice = ''
    /** 発火条件価格. */
    this.stopOrderPrice2 = ''
    /** 期間条件. */
    this.periodRadio = ''
    /** 期間. */
    this.period = ''
    /** 預り区分（全角半角）. */
    this.depositType = ''
    /** 決済方法（半角英数字）. */
    this.kessaiHoho = ''
    /** 注文日時. */
    this.orderDate = ''
    /** リクエスト内容. */
    this.request = {
      /** 国籍コード（全角半角）. */
      countryCd: '',
      /** 注文Sub番号（数字）. */
      orderSubNumber: '',
      /** 取引種別（全角半角）. */
      tradeCd: '',
      /** 注文番号（数字）. */
      orderNumber: '',
      /** 銘柄情報. */
      brandInformationList: {
        /** 銘柄コード（半角英数字）. */
        brandCode: '',
        /** 銘柄名（全角半角）. */
        brandName: ''
      },
      /** 取引通貨. */
      limitPriceText: '',
      /** 市場情報. */
      marketInformationList: {
        /** 市場略名. */
        marketName: '',
        /** 市場コード（全角半角）. */
        marketCode: '',
        /** 国コード（全角半角）. */
        countryCode: ''
      },
      /** 売買区分（全角半角）. */
      tradeKbn: '',
      /** 注文数量. */
      foreignQuantity: '',
      /** 価格条件. */
      priceConditionsType: '',
      /** 注文単価（数値(小数)）. */
      hiddenOrderPrice: '',
      /** 発火条件価格. */
      stopOrderPrice2: '',
      /** 期間条件. */
      periodRadio: '',
      /** 期間. */
      period: '',
      /** 預り区分（全角半角）. */
      depositType: '',
      /** 決済方法（半角英数字）. */
      kessaiHoho: '',
      /** 注文日時. */
      orderDate: '',
      /** 国内約定日. */
      domesticTradeDate: '',
      /** 現地約定日. */
      foreignTradeDate: ''
    }
  }
}
