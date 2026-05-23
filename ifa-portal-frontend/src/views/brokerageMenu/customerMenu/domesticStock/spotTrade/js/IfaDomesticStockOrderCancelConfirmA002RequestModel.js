import Logger from '@/utils/ifaLog.js'
export class IfaDomesticStockOrderCancelConfirmA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.ecOrderNo = obj.ecOrderNo ? obj.ecOrderNo : '' // EC受注番号
    this.market = obj.market ? obj.market : '' // 市場
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.quantity = obj.unTradeQuantity ? obj.unTradeQuantity : '' // 数量
    this.orderKind = obj.orderKind ? obj.orderKind : '' // 注文種別
    this.tradeCd = obj.tradeKbn ? obj.tradeKbn : '' // 取引種別
    this.period = obj.yukoKigen ? obj.yukoKigen : '' // 期間
    this.depositType = obj.notSpecificDepositTradeType ? obj.notSpecificDepositTradeType : '' // 預り区分

    this.sasinariHouhou = '' // 通常/逆指値.執行方法
    this.sasinariJyouken = '' // 通常/逆指値.執行条件
    this.triggerPrice = '' // 通常/逆指値.発火条件価格（逆指値）
    this.stopOrderPriceText2 = '' // 通常/逆指値.発火条件価格（逆指値）ゾーン
    this.gyakusasiHouhou = '' // 通常/逆指値.執行方法（逆指値）
    this.price = '' // 通常/逆指値.注文単価
    this.oco1SasinariHouhou = '' // OCO1.執行方法
    this.oco1SasinariJyouken = '' // OCO1.執行条件
    this.oco1Price = '' // OCO1.注文単価
    this.oco2TriggerPrice = '' // OCO2.発火条件価格（逆指値）
    this.oco2StopOrderPriceText2 = '' // OCO2.発火条件価格（逆指値）ゾーン
    this.oco2GyakusasiHouhou = '' // OCO2.執行方法（逆指値）
    this.oco2GyakusasiJyouken = '' // OCO2.執行条件（逆指値）
    this.oco2Price = '' // OCO2.注文単価
    this.ifd1SasinariHouhou = '' // IFD1.執行方法
    this.ifd1SasinariJyouken = '' // IFD1.執行条件
    this.ifd1TriggerPrice = '' // IFD1.発火条件価格（逆指値）
    this.ifd1StopOrderPriceText2 = '' // IFD1.発火条件価格（逆指値）ゾーン
    this.ifd1GyakusasiHouhou = '' // IFD1.執行方法（逆指値）
    this.ifd1Price = '' // IFD1.注文単価
    this.ifd2Limit = '' // IFD2.期間.日付
    this.ifd2SasinariHouhou = '' // IFD2.執行方法
    this.ifd2SasinariJyouken = '' // IFD2.執行条件
    this.ifd2TriggerPrice = '' // IFD2.発火条件価格（逆指値）
    this.ifd2StopOrderPriceText2 = '' // IFD2.発火条件価格（逆指値）ゾーン
    this.ifd2GyakusasiHouhou = '' // IFD2.執行方法（逆指値）
    this.ifd2Price = '' // IFD2.注文単価

    // 条件に一致する時のみ値をセット
    if (obj.orderKind === '1') {
      // 注文種別=通常/逆指値
      this.sasinariHouhou = obj.sasinariHouhou ? obj.sasinariHouhou : '' // 通常/逆指値.執行方法
      this.sasinariJyouken = obj.sasinariJyouken ? obj.sasinariJyouken : '' // 通常/逆指値.執行条件
      if (obj.sasinariHouhou === '3') {
        // 通常/逆指値.執行方法=逆指値
        this.triggerPrice = obj.triggerPrice ? obj.triggerPrice : '' // 通常/逆指値.発火条件価格（逆指値）
        this.stopOrderPriceText2 = obj.stopOrderPriceText2 ? obj.stopOrderPriceText2 : '' // 通常/逆指値.発火条件価格（逆指値）ゾーン
        this.gyakusasiHouhou = obj.gyakusasiHouhou ? obj.gyakusasiHouhou : '' // 通常/逆指値.執行方法（逆指値）
        if (obj.gyakusasiHouhou === '1') {
          // 通常/逆指値.執行方法（逆指値）=指値
          this.price = obj.price ? obj.price : '' // 通常/逆指値.注文単価
        }
      } else if (obj.sasinariHouhou === '1') {
        // 通常/逆指値.執行方法=指値
        this.price = obj.price ? obj.price : '' // 通常/逆指値.注文単価
      }
    }

    if (obj.orderKind === '2' || obj.orderKind === '4') {
      // 注文種別=OCO　または　IFDOCO
      this.oco1SasinariHouhou = obj.oco1SasinariHouhou ? obj.oco1SasinariHouhou : '' // OCO1.執行方法
      this.oco1SasinariJyouken = obj.oco1SasinariJyouken ? obj.oco1SasinariJyouken : '' // OCO1.執行条件
      this.oco1Price = obj.oco1Price ? obj.oco1Price : '' // OCO1.注文単価
      this.oco2TriggerPrice = obj.oco2TriggerPrice ? obj.oco2TriggerPrice : '' // OCO2.発火条件価格（逆指値）
      this.oco2StopOrderPriceText2 = obj.oco2StopOrderPriceText2 ? obj.oco2StopOrderPriceText2 : '' // OCO2.発火条件価格（逆指値）ゾーン
      this.oco2GyakusasiHouhou = obj.oco2GyakusasiHouhou ? obj.oco2GyakusasiHouhou : '' // OCO2.執行方法（逆指値）
      if (obj.oco2GyakusasiHouhou === '1' || obj.oco2GyakusasiHouhou === '2') {
        // OCO2.執行方法（逆指値）=指値　または　成行
        this.oco2GyakusasiJyouken = obj.oco2GyakusasiJyouken ? obj.oco2GyakusasiJyouken : '' // OCO2.執行条件（逆指値）
      }
      if (obj.oco2GyakusasiHouhou === '1') {
        // OCO2.執行方法（逆指値）=指値
        this.oco2Price = obj.oco2Price ? obj.oco2Price : '' // OCO2.注文単価
      }
    }

    if (obj.orderKind === '3' || obj.orderKind === '4') {
      // 注文種別=IFD　または　IFDOCO
      this.ifd1SasinariHouhou = obj.ifd1SasinariHouhou ? obj.ifd1SasinariHouhou : '' // IFD1.執行方法
      if (obj.ifd1SasinariHouhou === '1' || obj.ifd1SasinariHouhou === '2') {
        // IFD1.執行方法=指値　または　成行
        this.ifd1SasinariJyouken = obj.ifd1SasinariJyouken ? obj.ifd1SasinariJyouken : '' // IFD1.執行条件
      }
      if (obj.ifd1SasinariHouhou === '3' && (obj.ifd1GyakusasiHouhou === '1' || obj.ifd1GyakusasiHouhou === '2')) {
        // IFD1.執行方法=逆指値　かつ　IFD1.執行方法（逆指値）=指値　または　成行
        this.ifd1SasinariJyouken = obj.ifd1SasinariJyouken ? obj.ifd1SasinariJyouken : '' // IFD1.執行条件
      }
      if (obj.ifd1SasinariHouhou === '3') {
        // IFD1.執行方法=逆指値
        this.ifd1TriggerPrice = obj.ifd1TriggerPrice ? obj.ifd1TriggerPrice : '' // IFD1.発火条件価格（逆指値）
        this.ifd1StopOrderPriceText2 = obj.ifd1StopOrderPriceText2 ? obj.ifd1StopOrderPriceText2 : '' // IFD1.発火条件価格（逆指値）ゾーン
        this.ifd1GyakusasiHouhou = obj.ifd1GyakusasiHouhou ? obj.ifd1GyakusasiHouhou : '' // IFD1.執行方法（逆指値）
        if (obj.ifd1GyakusasiHouhou === '1') {
          // IFD1.執行方法（逆指値）=指値
          this.ifd1Price = obj.ifd1Price ? obj.ifd1Price : '' // IFD1.注文単価
        }
      } else if (obj.ifd1SasinariHouhou === '1') {
        // IFD1.執行方法=指値
        this.ifd1Price = obj.ifd1Price ? obj.ifd1Price : '' // IFD1.注文単価
      }
      this.ifd2Limit = obj.ifd2Limit ? obj.ifd2Limit : '' // IFD2.期間.日付
    }

    if (obj.orderKind === '3') {
      // 注文種別=IFD
      this.ifd2SasinariHouhou = obj.ifd2SasinariHouhou ? obj.ifd2SasinariHouhou : '' // IFD2.執行方法
      if (obj.ifd2SasinariHouhou === '1') {
        // IFD2.執行方法=指値
        this.ifd2SasinariJyouken = obj.ifd2SasinariJyouken ? obj.ifd2SasinariJyouken : '' // IFD2.執行条件
        this.ifd2Price = obj.ifd2Price ? obj.ifd2Price : '' // IFD2.注文単価
      }
      if (obj.ifd2SasinariHouhou === '3' && (obj.ifd2GyakusasiHouhou === '1' || obj.ifd2GyakusasiHouhou === '2')) {
        // IFD2.執行方法=逆指値　かつ　IFD2.執行方法（逆指値）=指値　または　成行
        this.ifd2SasinariJyouken = obj.ifd2SasinariJyouken ? obj.ifd2SasinariJyouken : '' // IFD2.執行条件
      }
      if (obj.ifd2SasinariHouhou === '3') {
        // IFD2.執行方法=逆指値
        this.ifd2TriggerPrice = obj.ifd2TriggerPrice ? obj.ifd2TriggerPrice : '' // IFD2.発火条件価格（逆指値）
        this.ifd2StopOrderPriceText2 = obj.ifd2StopOrderPriceText2 ? obj.ifd2StopOrderPriceText2 : '' // IFD2.発火条件価格（逆指値）ゾーン
        this.ifd2GyakusasiHouhou = obj.ifd2GyakusasiHouhou ? obj.ifd2GyakusasiHouhou : '' // IFD2.執行方法（逆指値）
        if (obj.ifd2GyakusasiHouhou === '1') {
          // IFD2.執行方法（逆指値）=指値
          this.ifd2Price = obj.ifd2Price ? obj.ifd2Price : '' // IFD2.注文単価
        }
      }
    }

    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.autoOrderShubetsu = obj.autoOrderShubetsu ? obj.autoOrderShubetsu : '' // 自動注文種別
    this.rbeChumonShubetsu = obj.rbeChumonShubetsu ? obj.rbeChumonShubetsu : '' // RBE注文種別
    this.sashinariKbn = obj.sashinariKbn ? obj.sashinariKbn : '' // 指成区分
    this.sashine = obj.sashine ? obj.sashine : '' // 指値
    this.triggerZone = obj.triggerZone ? obj.triggerZone : '' // トリガ発動ゾーン
    this.triggerNedan = obj.triggerNedan ? obj.triggerNedan : '' // トリガ値段
    this.ocoSasinariKbn = obj.ocoSasinariKbn ? obj.ocoSasinariKbn : '' // OCO指成区分
    this.ocoSashine = obj.ocoSashine ? obj.ocoSashine : '' // OCO指値
    this.doneSasinariKbn = obj.doneSasinariKbn ? obj.doneSasinariKbn : '' // DONE指成区分
    this.doneSashine = obj.doneSashine ? obj.doneSashine : '' // DONE指値
    this.doneRbeOrderKind = obj.doneRbeOrderKind ? obj.doneRbeOrderKind : '' // DONERBE注文種別
    this.doneTriggerZone = obj.doneTriggerZone ? obj.doneTriggerZone : '' // DONEトリガ発動ゾーン
    this.doneTriggerNedan = obj.doneTriggerNedan ? obj.doneTriggerNedan : '' // DONEトリガ値段
    this.doneOcoSasinariKbn = obj.doneOcoSasinariKbn ? obj.doneOcoSasinariKbn : '' // DONEOCO指成区分
    this.doneOcoSashine = obj.doneOcoSashine ? obj.doneOcoSashine : '' // DONEOCO指値
    this.doneYuukouKigen = obj.doneYuukouKigen ? obj.doneYuukouKigen : '' // DONE有効期限
    this.rbeOrderStatus = obj.rbeOrderStatus ? obj.rbeOrderStatus : '' // RBE注文ステータス
    this.workingStatus = obj.workingStatus ? obj.workingStatus : '' // 発火状態
    this.comIdR = obj.comIdR ? obj.comIdR : '' // 手数料区分（採用）
    this.orderDayTime = obj.orderDayTime ? obj.orderDayTime : '' // 受注日時
  }
}
