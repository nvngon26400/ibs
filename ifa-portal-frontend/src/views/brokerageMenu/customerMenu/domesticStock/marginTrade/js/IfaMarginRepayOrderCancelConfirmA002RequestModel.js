import Logger from '@/utils/ifaLog.js'
export class IfaMarginRepayOrderCancelConfirmA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    /** EC受注番号（半角英数字）. */
    this.ecOrderNo = obj.ecOrderNo ? obj.ecOrderNo : ''
    /** 市場（全角）. */
    this.market = obj.market ? obj.market : ''
    /** 銘柄コード（半角英数字）. */
    this.brandCode = obj.brandCode ? obj.brandCode : ''
    /** 数量（数値(整数)）. */
    this.quantity = obj.unTradeQuantity ? obj.unTradeQuantity : ''
    /** 注文種別（半角英数字）. */
    this.orderKind = obj.orderKind ? obj.orderKind : ''
    /** 取引種別（全角半角）. */
    this.tradeCd = obj.tradeCd ? obj.tradeCd : ''
    /** 期間. */
    this.period = obj.period ? obj.period : ''
    /** 信用取引区分（全角半角）. */
    this.marginTradeTypeText = obj.marginTradeTypeTextCalculation ? obj.marginTradeTypeTextCalculation : ''
    /** 弁済期限（全角半角）. */
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : ''
    /** 銘柄名（全角半角）. */
    this.brandName = obj.brandName ? obj.brandName : ''
    /** RBE注文種別（全角半角）. */
    this.rbeChumonShubetsu = obj.rbeChumonShubetsu ? obj.rbeChumonShubetsu : ''
    /** 指成区分（半角英数字）. */
    this.sashinariKbn = obj.sashinariKbn ? obj.sashinariKbn : ''
    /** 指値（数値(小数)）. */
    this.sashine = obj.sashine ? obj.sashine : ''
    /** トリガ発動ゾーン（半角英数字）. */
    this.triggerZone = obj.triggerZone ? obj.triggerZone : ''
    /** トリガ値段（数値(小数)）. */
    this.triggerNedan = obj.triggerNedan ? obj.triggerNedan : ''
    /** OCO指成区分（半角英数字）. */
    this.ocoSasinariKbn = obj.ocoSasinariKbn ? obj.ocoSasinariKbn : ''
    /** OCO指値（数値(小数)）. */
    this.ocoSashine = obj.ocoSashine ? obj.ocoSashine : ''
    /** RBE注文ステータス（半角英数字）. */
    this.rbeOrderStatus = obj.rbeOrderStatus ? obj.rbeOrderStatus : ''
    /** 受注日時. */
    this.orderDayTime = obj.orderDayTime ? obj.orderDayTime : ''
    /** 発火状態. */
    this.workingStatus = obj.workingStatus ? obj.workingStatus : ''
    /** 弁済期限年月日数. */
    this.paymentDeadlineDate = obj.paymentDeadlineDate ? obj.paymentDeadlineDate : ''
    /** 年月日区分. */
    this.dateKbn = obj.dateKbn ? obj.dateKbn : ''
    /**  手数料区分（採用）. */
    this.comIdR = obj.comIdR ? obj.comIdR : ''
    // 条件に一致する時のみ値をセット
    // 注文種別=通常/逆指値
    if (obj.orderKind === '1') {
      /** 通常/逆指値.執行方法（半角英数字）. */
      this.sasinariHouhou = obj.sasinariHouhou ? obj.sasinariHouhou : ''
      /** 通常/逆指値.執行条件（半角英数字）. */
      this.sasinariJyouken = obj.sasinariJyouken ? obj.sasinariJyouken : ''
      // 通常/逆指値.執行方法=逆指値
      if (obj.sasinariHouhou === '3') {
        /** 通常/逆指値.発火条件価格（逆指値）（数値(整数)）. */
        this.triggerPrice = obj.triggerPrice ? obj.triggerPrice : ''
        /** 通常/逆指値.発火条件価格（逆指値）ゾーン. */
        this.triggerPriceZone = obj.triggerPriceZone ? obj.triggerPriceZone : ''
        /** 通常/逆指値.執行方法（逆指値）（半角英数字）. */
        this.gyakusasiHouhou = obj.gyakusasiHouhou ? obj.gyakusasiHouhou : ''
        // 通常/逆指値.執行方法（逆指値）=指値
        if (obj.gyakusasiHouhou === '1') {
          /** 通常/逆指値.注文単価（数値(整数)）. */
          this.price = obj.price ? obj.price : ''
        }
        // 通常/逆指値.執行方法=指値
      } else if (obj.sasinariHouhou === '1') {
        /** 通常/逆指値.注文単価（数値(整数)）. */
        this.price = obj.price ? obj.price : ''
      }
    }

    // 注文種別=OCO　または　IFDOCO
    if (obj.orderKind === '2' || obj.orderKind === '4') {
      /** OCO1.執行方法（半角英数字）. */
      this.oco1SasinariHouhou = obj.oco1SasinariHouhou ? obj.oco1SasinariHouhou : ''
      /** OCO1.執行条件（半角英数字）. */
      this.oco1SasinariJyouken = obj.oco1SasinariJyouken ? obj.oco1SasinariJyouken : ''
      /** OCO1.注文単価（数値(整数)）. */
      this.oco1Price = obj.oco1Price ? obj.oco1Price : ''
      /** OCO2.発火条件価格（逆指値）（数値(整数)）. */
      this.oco2TriggerPrice = obj.oco2TriggerPrice ? obj.oco2TriggerPrice : ''
      /** OCO2.発火条件価格（逆指値）ゾーン. */
      this.oco2TriggerPriceZone = obj.oco2TriggerPriceZone ? obj.oco2TriggerPriceZone : ''
      /** OCO2.執行方法（逆指値）（半角英数字）. */
      this.oco2GyakusasiHouhou = obj.oco2GyakusasiHouhou ? obj.oco2GyakusasiHouhou : ''
      // OCO2.執行方法（逆指値）=指値　または　成行
      if (obj.oco2GyakusasiHouhou === '1' || obj.oco2GyakusasiHouhou === '2') {
        /** OCO2.執行条件（逆指値）（半角英数字）. */
        this.oco2GyakusasiJyouken = obj.oco2GyakusasiJyouken ? obj.oco2GyakusasiJyouken : ''
      }
      // OCO2.執行方法（逆指値）=指値
      if (obj.oco2GyakusasiHouhou === '1') {
        /** OCO2.注文単価（数値(整数)）. */
        this.oco2Price = obj.oco2Price ? obj.oco2Price : ''
      }
    }

    // 注文種別=IFD　または　IFDOCO
    if (obj.orderKind === '3' || obj.orderKind === '4') {
      /** IFD1.執行方法（半角英数字）. */
      this.ifd1SasinariHouhou = obj.ifd1SasinariHouhou ? obj.ifd1SasinariHouhou : ''
      // IFD1.執行方法=指値　または　成行
      if (obj.ifd1SasinariHouhou === '1' || obj.ifd1SasinariHouhou === '2') {
        /** IFD1.執行条件（半角英数字）. */
        this.ifd1SasinariJyouken = obj.ifd1SasinariJyouken ? obj.ifd1SasinariJyouken : ''
      }
      // IFD1.執行方法=逆指値　かつ　IFD1.執行方法（逆指値）=指値　または　成行
      if (obj.ifd1SasinariHouhou === '3' && (obj.ifd1GyakusasiHouhou === '1' || obj.ifd1GyakusasiHouhou === '2')) {
        /** IFD1.執行条件（半角英数字）. */
        this.ifd1SasinariJyouken = obj.ifd1SasinariJyouken ? obj.ifd1SasinariJyouken : ''
      }
      // IFD1.執行方法=逆指値
      if (obj.ifd1SasinariHouhou === '3') {
        /** IFD1.発火条件価格（逆指値）（数値(整数)）. */
        this.ifd1TriggerPrice = obj.ifd1TriggerPrice ? obj.ifd1TriggerPrice : ''
        /** IFD1.発火条件価格（逆指値）ゾーン. */
        this.ifd1TriggerPriceZone = obj.ifd1TriggerPriceZone ? obj.ifd1TriggerPriceZone : ''
        /** IFD1.執行方法（逆指値）（半角英数字）. */
        this.ifd1GyakusasiHouhou = obj.ifd1GyakusasiHouhou ? obj.ifd1GyakusasiHouhou : ''
        // IFD1.執行方法（逆指値）=指値
        if (obj.ifd1GyakusasiHouhou === '1') {
          /** IFD1.注文単価（数値(整数)）. */
          this.ifd1Price = obj.ifd1Price ? obj.ifd1Price : ''
        }
        // IFD1.執行方法=指値
      } else if (obj.ifd1SasinariHouhou === '1') {
        /** IFD1.注文単価（数値(整数)）. */
        this.ifd1Price = obj.ifd1Price ? obj.ifd1Price : ''
      }
      /** IFD2.期間.期間条件. */
      this.ifd2PeriodTerm = obj.ifd2Limit ? obj.ifd2Limit : ''
      /** IFD2.期間.日付（全角半角）. */
      this.ifd2Limit = obj.ifd2Limit ? obj.ifd2Limit : ''
    }

    // 注文種別=IFD
    if (obj.orderKind === '3') {
      // IFD2.執行方法=指値
      if (obj.ifd2SasinariHouhou === '1') {
        /** IFD2.執行方法（半角英数字）. */
        this.ifd2SasinariHouhou = obj.ifd2SasinariHouhou ? obj.ifd2SasinariHouhou : ''
        /** IFD2.執行条件（半角英数字）. */
        this.ifd2SasinariJyouken = obj.ifd2SasinariJyouken ? obj.ifd2SasinariJyouken : ''
        /** IFD2.注文単価（数値(整数)）. */
        this.ifd2Price = obj.ifd2Price ? obj.ifd2Price : ''
      }
      // IFD2.執行方法=逆指値　かつ　IFD2.執行方法（逆指値）=指値　または　成行
      if (obj.ifd2SasinariHouhou === '3' && (obj.ifd2GyakusasiHouhou === '1' || obj.ifd2GyakusasiHouhou === '2')) {
        /** IFD2.執行方法（半角英数字）. */
        this.ifd2SasinariHouhou = obj.ifd2SasinariHouhou ? obj.ifd2SasinariHouhou : ''
        /** IFD2.執行条件（半角英数字）. */
        this.ifd2SasinariJyouken = obj.ifd2SasinariJyouken ? obj.ifd2SasinariJyouken : ''
      }
      // IFD2.執行方法=逆指値
      if (obj.ifd2SasinariHouhou === '3') {
        /** IFD2.発火条件価格（逆指値）（数値(整数)）. */
        this.ifd2TriggerPrice = obj.ifd2TriggerPrice ? obj.ifd2TriggerPrice : ''
        /** IFD2.発火条件価格（逆指値）ゾーン. */
        this.ifd2TriggerPriceZone = obj.ifd2TriggerPriceZone ? obj.ifd2TriggerPriceZone : ''
        /** IFD2.執行方法（逆指値）（半角英数字）. */
        this.ifd2GyakusasiHouhou = obj.ifd2GyakusasiHouhou ? obj.ifd2GyakusasiHouhou : ''
        // IFD2.執行方法（逆指値）=指値
        if (obj.ifd2GyakusasiHouhou === '1') {
          /** IFD2.注文単価（数値(整数)）. */
          this.ifd2Price = obj.ifd2Price ? obj.ifd2Price : ''
        }
      }
    }
  }
}

