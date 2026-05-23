import Logger from '@/utils/ifaLog.js'
export class IfaMarginNewOrderCorrectInputA010RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.ecOrderNo = obj.ecOrderNo ? obj.ecOrderNo : '' // EC受注番号
    this.market = obj.market ? obj.market : '' // 市場
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    // 訂正後市場
    // 注文種別=通常/逆指値　かつ　執行方法（通常/逆指値）≠逆指値　かつ　直近市場＝"0"：東京　の場合のみセット
    const setAfterCorrectMarket = obj.orderKind === '1' && obj.sasinariHouhou !== '3' && obj.latestMarketId === '0'
    this.afterCorrectMarket = setAfterCorrectMarket ? obj.afterCorrectMarket : ''
    this.quantity = obj.orderQuantity ? obj.orderQuantity : '' // 数量
    this.unTradeQuantity = obj.unTradeQuantity ? obj.unTradeQuantity : '' // 未約定数量
    this.orderKind = obj.orderKind ? obj.orderKind : '' // 注文種別
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.period = obj.period ? obj.period : '' // 期間
    this.marginTradeTypeText = obj.marginTradeTypeText ? obj.marginTradeTypeText : '' // 信用取引区分
    this.workingStatus = obj.workingStatus // 発火状態
    this.doneState = obj.doneState ? obj.doneState : '' // DONE状態
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.rbeOrderStatus = obj.rbeOrderStatus ? obj.rbeOrderStatus : '' // RBE注文ステータス
    const setNormal = obj.orderKind === '1' // 注文種別=通常/逆指値
    this.sasinariHouhou = setNormal && obj.sasinariHouhou ? obj.sasinariHouhou : '' // 通常/逆指値.執行方法
    this.sasinariJyouken = setNormal && obj.sasinariJyouken ? obj.sasinariJyouken : '' // 通常/逆指値.執行条件
    this.triggerPrice = setNormal && obj.sasinariHouhou === '3' && obj.triggerPrice ? obj.triggerPrice : '' // 通常/逆指値.発火条件価格（逆指値）
    this.gyakusasiHouhou = setNormal && obj.gyakusasiHouhou ? obj.gyakusasiHouhou : '' // 通常/逆指値.執行方法（逆指値）
    this.price = setNormal && obj.price ? obj.price : '' // 通常/逆指値.注文単価
    const setOcoOrIfdoco = obj.orderKind === '2' || obj.orderKind === '4'
    this.oco1SasinariHouhou = setOcoOrIfdoco && obj.oco1SasinariHouhou ? obj.oco1SasinariHouhou : '' // OCO1.執行方法
    this.oco1SasinariJyouken = setOcoOrIfdoco && obj.oco1SasinariJyouken ? obj.oco1SasinariJyouken : '' // OCO1.執行条件
    this.oco1Price = setOcoOrIfdoco && obj.oco1Price ? obj.oco1Price : '' // OCO1.注文単価
    this.oco2TriggerPrice = setOcoOrIfdoco && obj.oco2TriggerPrice ? obj.oco2TriggerPrice : '' // OCO2.発火条件価格（逆指値）
    this.oco2GyakusasiHouhou = setOcoOrIfdoco && obj.oco2GyakusasiHouhou ? obj.oco2GyakusasiHouhou : '' // OCO2.執行方法（逆指値）
    this.oco2GyakusasiJyouken = setOcoOrIfdoco && obj.oco2GyakusasiJyouken ? obj.oco2GyakusasiJyouken : '' // OCO2.執行条件（逆指値）
    this.oco2Price = setOcoOrIfdoco && obj.oco2Price ? obj.oco2Price : '' // OCO2.注文単価
    const setIfdOrIfdoco = obj.orderKind === '3' || obj.orderKind === '4'
    this.ifd1SasinariHouhou = setIfdOrIfdoco && obj.ifd1SasinariHouhou ? obj.ifd1SasinariHouhou : '' // IFD1.執行方法
    this.ifd1SasinariJyouken = setIfdOrIfdoco && obj.ifd1SasinariJyouken ? obj.ifd1SasinariJyouken : '' // IFD1.執行条件
    this.ifd1TriggerPrice = setIfdOrIfdoco && obj.ifd1SasinariHouhou === '3' && obj.ifd1TriggerPrice ? obj.ifd1TriggerPrice : '' // IFD1.発火条件価格（逆指値）
    this.ifd1GyakusasiHouhou = setIfdOrIfdoco && obj.ifd1GyakusasiHouhou ? obj.ifd1GyakusasiHouhou : '' // IFD1.執行方法（逆指値）
    this.ifd1Price = setIfdOrIfdoco && obj.ifd1Price ? obj.ifd1Price : '' // IFD1.注文単価
    const setIfd = obj.orderKind === '3'
    this.ifd2Limit = setIfd && obj.ifd2Limit ? obj.ifd2Limit : '' // IFD2.期間.日付
    this.ifd2SasinariHouhou = setIfd && obj.ifd2SasinariHouhou ? obj.ifd2SasinariHouhou : '' // IFD2.執行方法
    this.ifd2SasinariJyouken = setIfd && obj.ifd2SasinariJyouken ? obj.ifd2SasinariJyouken : '' // IFD2.執行条件
    this.ifd2TriggerPrice = setIfd && obj.ifd2SasinariHouhou === '3' && obj.ifd2TriggerPrice ? obj.ifd2TriggerPrice : '' // IFD2.発火条件価格（逆指値）
    this.ifd2GyakusasiHouhou = setIfd && obj.ifd2GyakusasiHouhou ? obj.ifd2GyakusasiHouhou : '' // IFD2.執行方法（逆指値）
    this.ifd2Price = setIfd && obj.ifd2Price ? obj.ifd2Price : '' // IFD2.注文単価
    this.correctBeforePriceSasinariHouhou = obj.correctBeforePriceSasinariHouhou ? obj.correctBeforePriceSasinariHouhou : '' // 訂正前価格.通常/逆指値.執行方法
    this.correctBeforePriceSasinariJyouken = obj.correctBeforePriceSasinariJyouken ? obj.correctBeforePriceSasinariJyouken : '' // 訂正前価格.通常/逆指値.執行条件
    this.correctBeforePriceTriggerPrice = obj.correctBeforePriceTriggerPrice ? obj.correctBeforePriceTriggerPrice : '' // 訂正前価格.通常/逆指値.発火条件価格（逆指値）
    this.correctBeforePriceTriggerPriceZone = obj.correctBeforePriceTriggerPriceZone ? obj.correctBeforePriceTriggerPriceZone : '' // 訂正前価格.通常/逆指値.発火条件価格（逆指値）ゾーン
    this.correctBeforePriceGyakusasiHouhou = obj.correctBeforePriceGyakusasiHouhou ? obj.correctBeforePriceGyakusasiHouhou : '' // 訂正前価格.通常/逆指値.執行方法（逆指値）
    this.correctBeforePricePrice = obj.correctBeforePricePrice ? obj.correctBeforePricePrice : '' // 訂正前価格.通常/逆指値.注文単価
    this.correctBeforePriceOco1SasinariHouhou = obj.correctBeforePriceOco1SasinariHouhou ? obj.correctBeforePriceOco1SasinariHouhou : '' // 訂正前価格.OCO1.執行方法
    this.correctBeforePriceOco1SasinariJyouken = obj.correctBeforePriceOco1SasinariJyouken ? obj.correctBeforePriceOco1SasinariJyouken : '' // 訂正前価格.OCO1.執行条件
    this.correctBeforePriceOco1Price = obj.correctBeforePriceOco1Price ? obj.correctBeforePriceOco1Price : '' // 訂正前価格.OCO1.注文単価
    this.correctBeforePriceOco2TriggerPrice = obj.correctBeforePriceOco2TriggerPrice ? obj.correctBeforePriceOco2TriggerPrice : '' // 訂正前価格.OCO2.発火条件価格（逆指値）
    this.correctBeforePriceOco2TriggerPriceZone = obj.correctBeforePriceOco2TriggerPriceZone ? obj.correctBeforePriceOco2TriggerPriceZone : '' // 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン
    this.correctBeforePriceOco2GyakusasiHouhou = obj.correctBeforePriceOco2GyakusasiHouhou ? obj.correctBeforePriceOco2GyakusasiHouhou : '' // 訂正前価格.OCO2.執行方法（逆指値）
    this.correctBeforePriceOco2GyakusasiJyouken = obj.correctBeforePriceOco2GyakusasiJyouken ? obj.correctBeforePriceOco2GyakusasiJyouken : '' // 訂正前価格.OCO2.執行条件（逆指値）
    this.correctBeforePriceOco2Price = obj.correctBeforePriceOco2Price ? obj.correctBeforePriceOco2Price : '' // 訂正前価格.OCO2.注文単価
    this.correctBeforePriceIfd1SasinariHouhou = obj.correctBeforePriceIfd1SasinariHouhou ? obj.correctBeforePriceIfd1SasinariHouhou : '' // 訂正前価格.IFD1.執行方法
    this.correctBeforePriceIfd1SasinariJyouken = obj.correctBeforePriceIfd1SasinariJyouken ? obj.correctBeforePriceIfd1SasinariJyouken : '' // 訂正前価格.IFD1.執行条件
    this.correctBeforePriceIfd1TriggerPrice = obj.correctBeforePriceIfd1TriggerPrice ? obj.correctBeforePriceIfd1TriggerPrice : '' // 訂正前価格.IFD1.発火条件価格（逆指値）
    this.correctBeforePriceIfd1TriggerPriceZone = obj.correctBeforePriceIfd1TriggerPriceZone ? obj.correctBeforePriceIfd1TriggerPriceZone : '' // 訂正前価格.IFD1.発火条件価格（逆指値）ゾーン
    this.correctBeforePriceIfd1GyakusasiHouhou = obj.correctBeforePriceIfd1GyakusasiHouhou ? obj.correctBeforePriceIfd1GyakusasiHouhou : '' // 訂正前価格.IFD1.執行方法（逆指値）
    this.correctBeforePriceIfd1Price = obj.correctBeforePriceIfd1Price ? obj.correctBeforePriceIfd1Price : '' // 訂正前価格.IFD1.注文単価
    this.correctBeforePriceIfd2Limit = obj.correctBeforePriceIfd2Limit ? obj.correctBeforePriceIfd2Limit : '' // 訂正前価格.IFD2.期間.日付
    this.correctBeforePriceIfd2SasinariHouhou = obj.correctBeforePriceIfd2SasinariHouhou ? obj.correctBeforePriceIfd2SasinariHouhou : '' // 訂正前価格.IFD2.執行方法
    this.correctBeforePriceIfd2SasinariJyouken = obj.correctBeforePriceIfd2SasinariJyouken ? obj.correctBeforePriceIfd2SasinariJyouken : '' // 訂正前価格.IFD2.執行条件
    this.correctBeforePriceIfd2TriggerPrice = obj.correctBeforePriceIfd2TriggerPrice ? obj.correctBeforePriceIfd2TriggerPrice : '' // 訂正前価格.IFD2.発火条件価格（逆指値）
    this.correctBeforePriceIfd2TriggerPriceZone = obj.correctBeforePriceIfd2TriggerPriceZone ? obj.correctBeforePriceIfd2TriggerPriceZone : '' // 訂正前価格.IFD2.発火条件価格（逆指値）ゾーン
    this.correctBeforePriceIfd2GyakusasiHouhou = obj.correctBeforePriceIfd2GyakusasiHouhou ? obj.correctBeforePriceIfd2GyakusasiHouhou : '' // 訂正前価格.IFD2.執行方法（逆指値）
    this.correctBeforePriceIfd2Price = obj.correctBeforePriceIfd2Price ? obj.correctBeforePriceIfd2Price : '' // 訂正前価格.IFD2.注文単価
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.orderMethod = obj.orderMethod ? obj.orderMethod : '' // 受注方法
    this.checkInsiderConfirmCheckBox = obj.checkInsiderConfirmCheckBox ? obj.checkInsiderConfirmCheckBox : '' // 確認項目.契約締結前交付書面確認
    this.checkSor = obj.afterCorrectMarket === 'A' ? obj.checkSor : '' // 確認項目.SOR確認
    this.comId = obj.comId ? obj.comId : '' // 手数料区分
    this.inputDate = obj.inputDate ? obj.inputDate : '' // 受注日
    // 訂正SOR注文区分
    // 市場.訂正後市場が「当社優先市場／SOR」に変更された場合 "1"：訂正SOR
    if (obj.afterCorrectMarket === 'A') {
      this.correctSorOrderClassification = '1'
    // 上記以外 "△"：通常訂正
    } else {
      this.correctSorOrderClassification = ' '
    }
    this.paymentDeadlineDate = obj.paymentDeadlineDate ? obj.paymentDeadlineDate : '' // 弁済期限年月日数
    this.dateKbn = obj.dateKbn ? obj.dateKbn : '' // 年月日区分
  }
}
