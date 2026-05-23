import Logger from '@/utils/ifaLog.js'
export class IfaMarginRepayOrderCorrectInputA010RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    /** EC受注番号 */
    this.ecOrderNo = obj.ecOrderNo ? obj.ecOrderNo : ''
    /** 市場 */
    this.orderMarket = obj.latestMarketId ? obj.latestMarketId : ''
    /** 銘柄コード */
    this.brandCode = obj.brandCode ? obj.brandCode : ''
    // 訂正後市場
    // 注文種別=通常/逆指値　かつ　執行方法（通常/逆指値）≠逆指値　かつ　直近市場＝"0"：東証　の場合のみセット
    const setAfterCorrecMarket = obj.orderKind === '1' && obj.sasinariHouhou !== '3' && obj.latestMarketId === '0'
    this.afterCorrecMarket = setAfterCorrecMarket ? obj.afterCorrecMarket : ''
    /** 数量 */
    this.quantity = obj.quantity ? obj.quantity : ''
    /** 未約定数量 */
    this.unTradeQuantity = obj.unTradeQuantity ? obj.unTradeQuantity : ''
    /** 注文種別 */
    this.orderKind = obj.orderKind ? obj.orderKind : ''
    /** 取引種別 */
    this.tradeCd = obj.tradeCd ? obj.tradeCd : ''
    /** 期間 */
    this.period = obj.period ? obj.period : ''
    /** 信用取引区分 */
    this.marginTradeTypeText = obj.marginTradeTypeText ? obj.marginTradeTypeText : ''
    /** 発火状態 */
    this.workingStatus = obj.workingStatus ? obj.workingStatus : ''
    /** 弁済期限 */
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : ''
    /** RBE注文ステータス */
    this.rbeOrderStatus = obj.rbeOrderStatus ? obj.rbeOrderStatus : ''
    /* 初期値に空文字をセット */
    /** 通常/逆指値.執行方法 */
    this.sasinariHouhou = ''
    /** 通常/逆指値.執行条件 */
    this.sasinariJyouken = ''
    /** 通常/逆指値.注文単価 */
    this.price = ''
    /** 通常/逆指値.発火条件価格（逆指値） */
    this.triggerPrice = ''
    /** 通常/逆指値.執行方法（逆指値） */
    this.gyakusasiHouhou = ''
    /** 訂正前価格.通常/逆指値.執行方法 */
    this.correctBeforePriceSasinariHouhou = ''
    /** 訂正前価格.通常/逆指値.執行条件 */
    this.correctBeforePriceSasinariJyouken = ''
    /** 訂正前価格.通常/逆指値.発火条件価格（逆指値） */
    this.correctBeforePriceTriggerPrice = ''
    /** 訂正前価格.通常/逆指値.発火条件価格（逆指値）ゾーン */
    this.correctBeforePriceStopOrderPriceText2 = ''
    /** 訂正前価格.通常/逆指値.執行方法（逆指値） */
    this.correctBeforePriceGyakusasiHouhou = ''
    /** 訂正前価格.通常/逆指値.注文単価 */
    this.correctBeforePricePrice = ''
    /** OCO1.執行方法 */
    this.oco1SasinariHouhou = ''
    /** OCO1.執行条件 */
    this.oco1SasinariJyouken = ''
    /** OCO1.注文単価 */
    this.oco1Price = ''
    /** OCO2.発火条件価格（逆指値） */
    this.oco2TriggerPrice = ''
    /** OCO2.執行方法（逆指値） */
    this.oco2GyakusasiHouhou = ''
    /** OCO2.執行条件（逆指値） */
    this.oco2GyakusasiJyouken = ''
    /** OCO2.注文単価 */
    this.oco2Price = ''
    /** 訂正前価格.OCO1.執行方法 */
    this.correctBeforePriceOco1SasinariHouhou = ''
    /** 訂正前価格.OCO1.執行条件 */
    this.correctBeforePriceOco1SasinariJyouken = ''
    /** 訂正前価格.OCO1.注文単価 */
    this.correctBeforePriceOco1Price = ''
    /** 訂正前価格.OCO2.発火条件価格（逆指値） */
    this.correctBeforePriceOco2TriggerPrice = ''
    /** 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン */
    this.correctBeforePriceOco2StopOrderPriceText2 = ''
    /** 訂正前価格.OCO2.執行方法（逆指値） */
    this.correctBeforePriceOco2GyakusasiHouhou = ''
    /** 訂正前価格.OCO2.執行条件（逆指値） */
    this.correctBeforePriceOco2GyakusasiJyouken = ''
    /** 訂正前価格.OCO2.注文単価 */
    this.correctBeforePriceOco2Price = ''
    // 注文種別=通常/逆指値の場合のみセット
    if (obj.orderKind === '1') {
      /** 通常/逆指値.執行方法 */
      this.sasinariHouhou = obj.sasinariHouhou ? obj.sasinariHouhou : ''
      /** 通常/逆指値.執行条件 */
      this.sasinariJyouken = obj.sasinariJyouken ? obj.sasinariJyouken : ''
      /** 通常/逆指値.注文単価 */
      this.price = ''
      // 通常/逆指値.執行方法=指値
      if (obj.sasinariHouhou === '1') {
        /** 通常/逆指値.注文単価 */
        this.price = obj.price ? obj.price : ''
      // 通常/逆指値.執行方法=逆指値
      } else if (obj.sasinariHouhou === '3') {
        /** 通常/逆指値.発火条件価格（逆指値） */
        this.triggerPrice = obj.triggerPrice ? obj.triggerPrice : ''
        /** 通常/逆指値.執行方法（逆指値） */
        this.gyakusasiHouhou = obj.gyakusasiHouhou ? obj.gyakusasiHouhou : ''
        if (obj.gyakusasiHouhou === '1') {
          /** 通常/逆指値.注文単価 */
          this.price = obj.price ? obj.price : ''
        }
      }
      /** 訂正前価格.通常/逆指値.執行方法 */
      this.correctBeforePriceSasinariHouhou = obj.beforeCorrectPrice.sasinariHouhou ? obj.beforeCorrectPrice.sasinariHouhou : ''
      /** 訂正前価格.通常/逆指値.執行条件 */
      this.correctBeforePriceSasinariJyouken = obj.beforeCorrectPrice.sasinariJyouken ? obj.beforeCorrectPrice.sasinariJyouken : ''
      /** 訂正前価格.通常/逆指値.発火条件価格（逆指値） */
      this.correctBeforePriceTriggerPrice = obj.beforeCorrectPrice.triggerPrice ? obj.beforeCorrectPrice.triggerPrice : ''
      /** 訂正前価格.通常/逆指値.発火条件価格（逆指値）ゾーン */
      this.correctBeforePriceStopOrderPriceText2 = obj.beforeCorrectPrice.triggerPriceText ? obj.beforeCorrectPrice.triggerPriceText : ''
      /** 訂正前価格.通常/逆指値.執行方法（逆指値） */
      this.correctBeforePriceGyakusasiHouhou = obj.beforeCorrectPrice.gyakusasiHouhou ? obj.beforeCorrectPrice.gyakusasiHouhou : ''
      /** 訂正前価格.通常/逆指値.注文単価 */
      this.correctBeforePricePrice = obj.beforeCorrectPrice.price ? obj.beforeCorrectPrice.price : ''
    // 注文種別=OCO　の場合のみセット
    } else if (obj.orderKind === '2') {
      /** OCO1.執行方法 */
      this.oco1SasinariHouhou = obj.oco1SasinariHouhou ? obj.oco1SasinariHouhou : ''
      /** OCO1.執行条件 */
      this.oco1SasinariJyouken = obj.oco1SasinariJyouken ? obj.oco1SasinariJyouken : ''
      /** OCO1.注文単価 */
      this.oco1Price = obj.oco1Price ? obj.oco1Price : ''
      /** OCO2.発火条件価格（逆指値） */
      this.oco2TriggerPrice = obj.oco2TriggerPrice ? obj.oco2TriggerPrice : ''
      /** OCO2.執行方法（逆指値） */
      this.oco2GyakusasiHouhou = obj.oco2GyakusasiHouhou ? obj.oco2GyakusasiHouhou : ''
      /** OCO2.執行条件（逆指値） */
      this.oco2GyakusasiJyouken = obj.oco2GyakusasiJyouken ? obj.oco2GyakusasiJyouken : ''
      /** OCO2.注文単価 */
      this.oco2Price = obj.oco2Price && obj.oco2GyakusasiHouhou === '1' ? obj.oco2Price : ''
      /** 訂正前価格.OCO1.執行方法 */
      this.correctBeforePriceOco1SasinariHouhou = obj.beforeCorrectPrice.oco1SasinariHouhou ? obj.beforeCorrectPrice.oco1SasinariHouhou : ''
      /** 訂正前価格.OCO1.執行条件 */
      this.correctBeforePriceOco1SasinariJyouken = obj.beforeCorrectPrice.oco1SasinariJyouken ? obj.beforeCorrectPrice.oco1SasinariJyouken : ''
      /** 訂正前価格.OCO1.注文単価 */
      this.correctBeforePriceOco1Price = obj.beforeCorrectPrice.oco1Price ? obj.beforeCorrectPrice.oco1Price : ''
      /** 訂正前価格.OCO2.発火条件価格（逆指値） */
      this.correctBeforePriceOco2TriggerPrice = obj.beforeCorrectPrice.oco2TriggerPrice ? obj.beforeCorrectPrice.oco2TriggerPrice : ''
      /** 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン */
      this.correctBeforePriceOco2StopOrderPriceText2 = obj.beforeCorrectPrice.oco2TriggerPriceText ? obj.beforeCorrectPrice.oco2TriggerPriceText : ''
      /** 訂正前価格.OCO2.執行方法（逆指値） */
      this.correctBeforePriceOco2GyakusasiHouhou = obj.beforeCorrectPrice.oco2GyakusasiHouhou ? obj.beforeCorrectPrice.oco2GyakusasiHouhou : ''
      /** 訂正前価格.OCO2.執行条件（逆指値） */
      this.correctBeforePriceOco2GyakusasiJyouken = obj.beforeCorrectPrice.oco2GyakusasiJyouken ? obj.beforeCorrectPrice.oco2GyakusasiJyouken : ''
      /** 訂正前価格.OCO2.注文単価 */
      this.correctBeforePriceOco2Price = obj.beforeCorrectPrice.oco2Price ? obj.beforeCorrectPrice.oco2Price : ''
    }
    /** 勧誘区分 */
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : ''
    /** 受注方法 */
    this.orderMethod = obj.orderMethod ? obj.orderMethod : ''
    /** 確認項目.契約締結前交付書面確認 */
    this.checkInsider = obj.checkInsider ? obj.checkInsider : ''
    // 訂正後市場＝当社優先市場/SORの場合のみセット
    this.checkSor = obj.afterCorrecMarket === 'A' ? obj.checkSor : '' // 確認項目.SOR確認
    /** 手数料区分 */
    this.tesuuryouKbn = obj.tesuuryouKbn ? obj.tesuuryouKbn : ''
    /** 受注日 */
    this.orderDate = obj.orderDate ? obj.orderDate : ''
    // 訂正SOR注文区分
    // 市場.訂正後市場が「当社優先市場／SOR」に変更された場合 "1"：訂正SOR
    if (obj.afterCorrecMarket === 'A') {
      this.correctSorOrderClassification = '1'
      // 上記以外 "△"：通常訂正
    } else {
      this.correctSorOrderClassification = ' '
    }
    /** 弁済期限年月日数. */
    this.paymentDeadlineDate = obj.paymentDeadlineDate ? obj.paymentDeadlineDate : ''
    /** 年月日区分. */
    this.dateKbn = obj.dateKbn ? obj.dateKbn : ''
  }
}
