import Logger from '@/utils/ifaLog.js'
export class IfaMarginRepayOrderCorrectConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    /** EC受注番号 */
    this.ecOrderNo = obj.request.ecOrderNo ? obj.request.ecOrderNo : ''
    /** 市場（訂正前） */
    this.orderMarket = obj.request.orderMarket ? obj.request.orderMarket : ''
    /** 市場（訂正後） */
    this.afterCorrecMarket = obj.request.afterCorrecMarket ? obj.request.afterCorrecMarket : ''
    /** 銘柄コード */
    this.brandCode = obj.request.brandCode ? obj.request.brandCode : ''
    /** 数量 */
    this.quantity = obj.request.quantity ? obj.request.quantity : ''
    /** 未約定数量 */
    this.unTradeQuantity = obj.request.unTradeQuantity ? obj.request.unTradeQuantity : ''
    /** 注文種別 */
    this.orderKind = obj.request.orderKind ? obj.request.orderKind : ''
    /** 取引種別 */
    this.tradeCd = obj.request.tradeCd ? obj.request.tradeCd : ''
    /** 期間 */
    this.period = obj.request.period ? obj.request.period : ''
    /** 信用取引区分 */
    this.marginTradeTypeText = obj.request.marginTradeTypeText ? obj.request.marginTradeTypeText : ''
    // 注文種別=通常/逆指値の場合のみセット
    if (obj.request.orderKind === '1') {
      /** 通常/逆指値.執行方法 */
      this.sasinariHouhou = obj.request.sasinariHouhou ? obj.request.sasinariHouhou : ''
      /** 通常/逆指値.執行条件 */
      this.sasinariJyouken = obj.request.sasinariJyouken ? obj.request.sasinariJyouken : ''
      // 通常/逆指値.執行方法=逆指値
      if (obj.request.sasinariHouhou === '3') {
        /** 通常/逆指値.発火条件価格（逆指値） */
        this.triggerPrice = obj.request.triggerPrice ? obj.request.triggerPrice : ''
        /** 通常/逆指値.執行方法（逆指値） */
        this.gyakusasiHouhou = obj.request.gyakusasiHouhou ? obj.request.gyakusasiHouhou : ''
        // 通常/逆指値.執行方法（逆指値）=指値
        if (obj.request.gyakusasiHouhou === '1') {
          /** 通常/逆指値.注文単価 */
          this.price = obj.request.price ? obj.request.price : ''
        }
      }
      // 通常/逆指値.執行方法=指値
      if (obj.request.sasinariHouhou === '1') {
        this.price = obj.request.price ? obj.request.price : ''
      }
      /** 訂正前価格.通常/逆指値.執行方法 */
      this.correctBeforePriceSasinariHouhou = obj.request.correctBeforePriceSasinariHouhou ? obj.request.correctBeforePriceSasinariHouhou : ''
      /** 訂正前価格.通常/逆指値.執行条件 */
      this.correctBeforePriceSasinariJyouken = obj.request.correctBeforePriceSasinariJyouken ? obj.request.correctBeforePriceSasinariJyouken : ''
      /** 訂正前価格.通常/逆指値.発火条件価格（逆指値） */
      this.correctBeforePriceTriggerPrice = obj.request.correctBeforePriceTriggerPrice ? obj.request.correctBeforePriceTriggerPrice : ''
      /** 訂正前価格.通常/逆指値.発火条件価格（逆指値）ゾーン */
      this.correctBeforePriceStopOrderPriceText2 = obj.request.correctBeforePriceStopOrderPriceText2 ? obj.request.correctBeforePriceStopOrderPriceText2 : ''
      /** 訂正前価格.通常/逆指値.執行方法（逆指値） */
      this.correctBeforePriceGyakusasiHouhou = obj.request.correctBeforePriceGyakusasiHouhou ? obj.request.correctBeforePriceGyakusasiHouhou : ''
      /** 訂正前価格.通常/逆指値.注文単価 */
      this.correctBeforePricePrice = obj.request.correctBeforePricePrice ? obj.request.correctBeforePricePrice : ''
    }
    if (obj.request.orderKind === '2') {
      /** OCO1.執行方法 */
      this.oco1SasinariHouhou = obj.request.oco1SasinariHouhou ? obj.request.oco1SasinariHouhou : ''
      /** OCO1.執行条件 */
      this.oco1SasinariJyouken = obj.request.oco1SasinariJyouken ? obj.request.oco1SasinariJyouken : ''
      /** OCO1.注文単価 */
      this.oco1Price = obj.request.oco1Price ? obj.request.oco1Price : ''
      /** OCO2.発火条件価格（逆指値） */
      this.oco2TriggerPrice = obj.request.oco2TriggerPrice ? obj.request.oco2TriggerPrice : ''
      /** OCO2.執行方法（逆指値） */
      this.oco2GyakusasiHouhou = obj.request.oco2GyakusasiHouhou ? obj.request.oco2GyakusasiHouhou : ''
      /** OCO2.執行条件（逆指値） */
      this.oco2GyakusasiJyouken = obj.request.oco2GyakusasiJyouken ? obj.request.oco2GyakusasiJyouken : ''
      // OCO2.執行方法（逆指値）=指値　の場合
      this.oco2Price = obj.request.oco2Price ? obj.request.oco2Price : ''
      /** 訂正前価格.OCO1.執行方法 */
      this.correctBeforePriceOco1SasinariHouhou = obj.request.correctBeforePriceOco1SasinariHouhou ? obj.request.correctBeforePriceOco1SasinariHouhou : ''
      /** 訂正前価格.OCO1.執行条件 */
      this.correctBeforePriceOco1SasinariJyouken = obj.request.correctBeforePriceOco1SasinariJyouken ? obj.request.correctBeforePriceOco1SasinariJyouken : ''
      /** 訂正前価格.OCO1.注文単価 */
      this.correctBeforePriceOco1Price = obj.request.correctBeforePriceOco1Price ? obj.request.correctBeforePriceOco1Price : ''
      /** 訂正前価格.OCO2.発火条件価格（逆指値） */
      this.correctBeforePriceOco2TriggerPrice = obj.request.correctBeforePriceOco2TriggerPrice ? obj.request.correctBeforePriceOco2TriggerPrice : ''
      /** 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン */
      this.correctBeforePriceOco2StopOrderPriceText2 = obj.request.correctBeforePriceOco2StopOrderPriceText2 ? obj.request.correctBeforePriceOco2StopOrderPriceText2 : ''
      /** 訂正前価格.OCO2.執行方法（逆指値） */
      this.correctBeforePriceOco2GyakusasiHouhou = obj.request.correctBeforePriceOco2GyakusasiHouhou ? obj.request.correctBeforePriceOco2GyakusasiHouhou : ''
      /** 訂正前価格.OCO2.執行条件（逆指値） */
      this.correctBeforePriceOco2GyakusasiJyouken = obj.request.correctBeforePriceOco2GyakusasiJyouken ? obj.request.correctBeforePriceOco2GyakusasiJyouken : ''
      /** 訂正前価格.OCO2.注文単価 */
      this.correctBeforePriceOco2Price = obj.request.correctBeforePriceOco2Price ? obj.request.correctBeforePriceOco2Price : ''
    }
    /** 勧誘区分 */
    this.kanyuKbn = obj.request.kanyuKbn ? obj.request.kanyuKbn : ''
    /** 受注方法 */
    this.orderMethod = obj.request.orderMethod ? obj.request.orderMethod : ''
    /** 確認項目.契約締結前交付書面確認 */
    this.checkInsider = obj.request.checkInsider ? obj.request.checkInsider : ''
    /** 確認項目.SOR確認 */
    this.checkSor = obj.request.checkSor ? obj.request.checkSor : ''
    /** アラート内容確認.取引注意情報（銘柄）確認. */
    this.tradeNoticeInfoBrandConfirm = obj.tradeNoticeInfoBrandConfirm ? obj.tradeNoticeInfoBrandConfirm : ''
    /** アラート内容確認.注意情報アラート確認. */
    this.noticeInfoAlertConfirm = obj.noticeInfoAlertConfirm ? obj.noticeInfoAlertConfirm : ''
    /** アラート内容確認.お知らせアラート確認. */
    this.noticeAlertConfirm = obj.noticeAlertConfirm ? obj.noticeAlertConfirm : ''
    /** 注意情報アラート. */
    this.noticeInfoAlert = obj.noticeInfoAlert ? obj.noticeInfoAlert : ''
    /** お知らせアラート. */
    this.noticeAlert = obj.noticeAlert ? obj.noticeAlert : ''
    /** 取引注意情報（銘柄）メッセージ. */
    this.tradeNoticeInfoBrandMsg = obj.tradeNoticeInfoBrandMsg ? obj.tradeNoticeInfoBrandMsg : ''
    /** 銘柄名 */
    this.brandName = obj.brandName ? obj.brandName : ''
    /** 発火状態 */
    this.workingStatus = obj.request.workingStatus ? obj.request.workingStatus : ''
    /** 弁済期限 */
    this.paymentDeadline = obj.request.paymentDeadline ? obj.request.paymentDeadline : ''
    /** RBE注文ステータス */
    this.rbeOrderStatus = obj.request.rbeOrderStatus ? obj.request.rbeOrderStatus : ''
    /** 手数料区分 */
    this.tesuuryouKbn = obj.request.tesuuryouKbn ? obj.request.tesuuryouKbn : ''
    /** 受注日 */
    this.orderDate = obj.request.orderDate ? obj.request.orderDate : ''
    // 訂正SOR注文区分
    // 市場（訂正後）が「当社優先市場／SOR」に変更された場合 "1"：訂正SOR
    if (obj.request.afterCorrecMarket === 'A') {
      this.correctSorOrderClassification = '1'
    // 上記以外 "△"：通常訂正
    } else {
      this.correctSorOrderClassification = ' '
    }
    /** 弁済期限年月日数. */
    this.paymentDeadlineDate = obj.request.paymentDeadlineDate ? obj.request.paymentDeadlineDate : ''
    /** 年月日区分. */
    this.dateKbn = obj.request.dateKbn ? obj.request.dateKbn : ''
  }
}
