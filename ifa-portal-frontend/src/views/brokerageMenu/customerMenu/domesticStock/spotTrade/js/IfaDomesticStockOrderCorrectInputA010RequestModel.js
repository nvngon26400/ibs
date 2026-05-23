import Logger from '@/utils/ifaLog.js'
export class IfaDomesticStockOrderCorrectInputA010RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.ecOrderNo = obj.ecOrderNo ? obj.ecOrderNo : '' // EC受注番号
    this.market = obj.market ? obj.market : '' // 市場
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード

    // 訂正後市場
    // 注文種別=通常/逆指値　かつ　執行方法（通常/逆指値）≠逆指値　かつ　直近市場＝"0"：東証　の場合のみセット
    const setAfterCorrectMarket = obj.orderKind === '1' && obj.sasinariHouhou !== '3' && obj.latestMarketId === '0'
    this.afterCorrectMarket = setAfterCorrectMarket ? obj.afterCorrectMarket : ''
    
    this.quantity = obj.orderQuantity ? obj.orderQuantity : '' // 数量
    this.unTradeQuantity = obj.unTradeQuantity ? obj.unTradeQuantity : '' // 未約定数量
    this.orderKind = obj.orderKind ? obj.orderKind : '' // 注文種別
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.period = obj.period ? obj.period : '' // 期間
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.workingStatus = obj.workingStatus ? obj.workingStatus : '' // 発火状態
    this.doneState = obj.doneState ? obj.doneState : '' // DONE状態
    this.rbeOrderStatus = obj.rbeOrderStatus ? obj.rbeOrderStatus : '' // RBE注文ステータス

    // 注文種別=通常/逆指値の場合のみセット
    const setNormal = obj.orderKind === '1' // 注文種別=通常/逆指値
    this.sasinariHouhou = setNormal && obj.sasinariHouhou ? obj.sasinariHouhou : '' // 通常/逆指値執行方法
    this.sasinariJyouken = setNormal && obj.sasinariJyouken ? obj.sasinariJyouken : '' // 通常/逆指値執行条件
    this.triggerPrice = setNormal && obj.sasinariHouhou === '3' && obj.triggerPrice ? obj.triggerPrice : '' // 通常/逆指値発火条件価格（逆指値）
    this.gyakusasiHouhou = setNormal && obj.sasinariHouhou === '3' && obj.gyakusasiHouhou ? obj.gyakusasiHouhou : '' // 通常/逆指値執行方法（逆指値）
    // 通常/逆指値注文単価
    if (setNormal) {
      if (obj.sasinariHouhou === '1') {
        this.price = obj.price ? obj.price : '' // 通常/逆指値.執行方法=指値　の場合
      } else if (obj.sasinariHouhou === '3' && obj.gyakusasiHouhou === '1') {
        this.price = obj.price ? obj.price : '' // 通常/逆指値.執行方法=逆指値 かつ 通常/逆指値.執行方法（逆指値）=指値　の場合
      } else {
        this.price = '' // それ以外
      }
    } else {
      this.price = ''
    }

    // 注文種別=OCO または IFDOCOの場合のみセット
    const setOcoOrIfdoco = obj.orderKind === '2' || obj.orderKind === '4'
    this.oco1SasinariHouhou = setOcoOrIfdoco && obj.oco1SasinariHouhou ? obj.oco1SasinariHouhou : '' // OCO1執行方法
    this.oco1SasinariJyouken = setOcoOrIfdoco && obj.oco1SasinariJyouken ? obj.oco1SasinariJyouken : '' // OCO1執行条件
    this.oco1Price = setOcoOrIfdoco && obj.oco1Price ? obj.oco1Price : '' // OCO1注文単価
    this.oco2TriggerPrice = setOcoOrIfdoco && obj.oco2TriggerPrice ? obj.oco2TriggerPrice : '' // OCO2発火条件価格（逆指値）
    this.oco2GyakusasiHouhou = setOcoOrIfdoco && obj.oco2GyakusasiHouhou ? obj.oco2GyakusasiHouhou : '' // OCO2執行方法（逆指値）
    this.oco2GyakusasiJyouken = setOcoOrIfdoco && obj.oco2GyakusasiJyouken ? obj.oco2GyakusasiJyouken : '' // OCO2執行条件（逆指値）
    this.oco2Price = setOcoOrIfdoco && obj.oco2GyakusasiHouhou === '1' && obj.oco2Price ? obj.oco2Price : '' // OCO2注文単価

    // 注文種別=IFD または IFDOCOの場合のみセット
    const setIfdOrIfdoco = obj.orderKind === '3' || obj.orderKind === '4'
    this.ifd1SasinariHouhou = setIfdOrIfdoco && obj.ifd1SasinariHouhou ? obj.ifd1SasinariHouhou : '' // IFD1執行方法
    this.ifd1SasinariJyouken = setIfdOrIfdoco && obj.ifd1SasinariJyouken ? obj.ifd1SasinariJyouken : '' // IFD1執行条件
    this.ifd1TriggerPrice = setIfdOrIfdoco && obj.ifd1SasinariHouhou === '3' && obj.ifd1TriggerPrice ? obj.ifd1TriggerPrice : '' // IFD1発火条件価格（逆指値）
    this.ifd1GyakusasiHouhou = setIfdOrIfdoco && obj.ifd1SasinariHouhou === '3' && obj.ifd1GyakusasiHouhou ? obj.ifd1GyakusasiHouhou : '' // IFD1執行方法（逆指値）
    // IFD1注文単価
    if (
      setIfdOrIfdoco &&
      (
        obj.ifd1SasinariHouhou === '1' ||
        obj.ifd1SasinariHouhou === '3' && obj.ifd1GyakusasiHouhou === '1'
      )
    ) {
      this.ifd1Price = setIfdOrIfdoco && obj.ifd1Price ? obj.ifd1Price : ''
    } else {
      this.ifd1Price = ''
    }

    this.ifd2Limit = setIfdOrIfdoco && obj.ifd2Limit ? obj.ifd2Limit : '' // IFD2期間日付

    // 注文種別=IFDの場合のみセット
    const setIfd = obj.orderKind === '3'
    this.ifd2SasinariHouhou = setIfd && obj.ifd2SasinariHouhou ? obj.ifd2SasinariHouhou : '' // IFD2執行方法
    this.ifd2SasinariJyouken = setIfd && obj.ifd2SasinariJyouken ? obj.ifd2SasinariJyouken : '' // IFD2執行条件
    this.ifd2TriggerPrice = setIfd && obj.ifd2SasinariHouhou === '3' && obj.ifd2TriggerPrice ? obj.ifd2TriggerPrice : '' // IFD2発火条件価格（逆指値）
    this.ifd2GyakusasiHouhou = setIfd && obj.ifd2SasinariHouhou === '3' && obj.ifd2GyakusasiHouhou ? obj.ifd2GyakusasiHouhou : '' // IFD2執行方法（逆指値）
    // IFD2注文単価
    if (
      setIfd &&
      (
        obj.ifd2SasinariHouhou === '1' ||
        obj.ifd2SasinariHouhou === '3' && obj.ifd2GyakusasiHouhou === '1'
      )
    ) {
      this.ifd2Price = setIfd && obj.ifd2Price ? obj.ifd2Price : ''
    } else {
      this.ifd2Price = ''
    }

    this.beforeCorrectPrice = [
      {
        // 注文種別=通常/逆指値の場合のみセット
        sasinariHouhou: setNormal && obj.beforeCorrectPrice.sasinariHouhou ? obj.beforeCorrectPrice.sasinariHouhou : '', // 訂正前価格通常/逆指値執行方法
        sasinariJyouken: setNormal && obj.beforeCorrectPrice.sasinariJyouken ? obj.beforeCorrectPrice.sasinariJyouken : '', // 訂正前価格通常/逆指値執行条件,
        triggerPrice: setNormal && obj.beforeCorrectPrice.triggerPrice ? obj.beforeCorrectPrice.triggerPrice : '', // 訂正前価格通常/逆指値発火条件価格（逆指値）
        stopOrderPriceText2: setNormal && obj.beforeCorrectPrice.stopOrderPriceText2 ? obj.beforeCorrectPrice.stopOrderPriceText2 : '', // 訂正前価格通常/逆指値発火条件価格（逆指値）ゾーン
        gyakusasiHouhou: setNormal && obj.beforeCorrectPrice.gyakusasiHouhou ? obj.beforeCorrectPrice.gyakusasiHouhou : '', // 訂正前価格通常/逆指値執行方法（逆指値）
        price: setNormal && obj.beforeCorrectPrice.price ? obj.beforeCorrectPrice.price : '', // 訂正前価格通常/逆指値注文単価

        // 注文種別=OCO または IFDOCOの場合のみセット
        oco1SasinariHouhou: setOcoOrIfdoco && obj.beforeCorrectPrice.oco1SasinariHouhou ? obj.beforeCorrectPrice.oco1SasinariHouhou : '', // 訂正前価格OCO1執行方法
        oco1SasinariJyouken: setOcoOrIfdoco && obj.beforeCorrectPrice.oco1SasinariJyouken ? obj.beforeCorrectPrice.oco1SasinariJyouken : '', // 訂正前価格OCO1執行条件
        oco1Price: setOcoOrIfdoco && obj.beforeCorrectPrice.oco1Price ? obj.beforeCorrectPrice.oco1Price : '', // 訂正前価格OCO1注文単価
        oco2TriggerPrice: setOcoOrIfdoco && obj.beforeCorrectPrice.oco2TriggerPrice ? obj.beforeCorrectPrice.oco2TriggerPrice : '', // 訂正前価格OCO2発火条件価格（逆指値）
        oco2StopOrderPriceText2: setOcoOrIfdoco && obj.beforeCorrectPrice.oco2StopOrderPriceText2 ? obj.beforeCorrectPrice.oco2StopOrderPriceText2 : '', // 訂正前価格OCO2発火条件価格（逆指値）ゾーン
        oco2GyakusasiHouhou: setOcoOrIfdoco && obj.beforeCorrectPrice.oco2GyakusasiHouhou ? obj.beforeCorrectPrice.oco2GyakusasiHouhou : '', // 訂正前価格OCO2執行方法（逆指値）
        oco2GyakusasiJyouken: setOcoOrIfdoco && obj.beforeCorrectPrice.oco2GyakusasiJyouken ? obj.beforeCorrectPrice.oco2GyakusasiJyouken : '', // 訂正前価格OCO2執行条件（逆指値）
        oco2Price: setOcoOrIfdoco && obj.beforeCorrectPrice.oco2Price ? obj.beforeCorrectPrice.oco2Price : '', // 訂正前価格OCO2注文単価

        // 注文種別=IFD または IFDOCOの場合のみセット
        ifd1SasinariHouhou: setIfdOrIfdoco && obj.beforeCorrectPrice.ifd1SasinariHouhou ? obj.beforeCorrectPrice.ifd1SasinariHouhou : '', // 訂正前価格IFD1執行方法
        ifd1SasinariJyouken: setIfdOrIfdoco && obj.beforeCorrectPrice.ifd1SasinariJyouken ? obj.beforeCorrectPrice.ifd1SasinariJyouken : '', // 訂正前価格IFD1執行条件
        ifd1TriggerPrice: setIfdOrIfdoco && obj.beforeCorrectPrice.ifd1TriggerPrice ? obj.beforeCorrectPrice.ifd1TriggerPrice : '', // 訂正前価格IFD1発火条件価格（逆指値）
        ifd1StopOrderPriceText2: setIfdOrIfdoco && obj.beforeCorrectPrice.ifd1StopOrderPriceText2 ? obj.beforeCorrectPrice.ifd1StopOrderPriceText2 : '', // 訂正前価格IFD1発火条件価格（逆指値）ゾーン
        ifd1GyakusasiHouhou: setIfdOrIfdoco && obj.beforeCorrectPrice.ifd1GyakusasiHouhou ? obj.beforeCorrectPrice.ifd1GyakusasiHouhou : '', // 訂正前価格IFD1執行方法（逆指値）
        ifd1Price: setIfdOrIfdoco && obj.beforeCorrectPrice.ifd1Price ? obj.beforeCorrectPrice.ifd1Price : '', // 訂正前価格IFD1注文単価
        ifd2Limit: setIfdOrIfdoco && obj.beforeCorrectPrice.ifd2Limit ? obj.beforeCorrectPrice.ifd2Limit : '', // 訂正前価格IFD2期間日付

        // 注文種別=IFDの場合のみセット
        ifd2SasinariHouhou: setIfd && obj.beforeCorrectPrice.ifd2SasinariHouhou ? obj.beforeCorrectPrice.ifd2SasinariHouhou : '', // 訂正前価格IFD2執行方法
        ifd2SasinariJyouken: setIfd && obj.beforeCorrectPrice.ifd2SasinariJyouken ? obj.beforeCorrectPrice.ifd2SasinariJyouken : '', // 訂正前価格IFD2執行条件
        ifd2TriggerPrice: setIfd && obj.beforeCorrectPrice.ifd2TriggerPrice ? obj.beforeCorrectPrice.ifd2TriggerPrice : '', // 訂正前価格IFD2発火条件価格（逆指値）
        ifd2StopOrderPriceText2: setIfd && obj.beforeCorrectPrice.ifd2StopOrderPriceText2 ? obj.beforeCorrectPrice.ifd2StopOrderPriceText2 : '', // 訂正前価格IFD2発火条件価格（逆指値）ゾーン
        ifd2GyakusasiHouhou: setIfd && obj.beforeCorrectPrice.ifd2GyakusasiHouhou ? obj.beforeCorrectPrice.ifd2GyakusasiHouhou : '', // 訂正前価格IFD2執行方法（逆指値）
        ifd2Price: setIfd && obj.beforeCorrectPrice.ifd2Price ? obj.beforeCorrectPrice.ifd2Price : '' // 訂正前価格IFD2注文単価
      }
    ]
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.receiveOrderType = obj.receiveOrderType ? obj.receiveOrderType : '' // 受注方法
    this.checkInsider = obj.checkInsider ? obj.checkInsider : '' // 確認項目インサイダー確認

    // 訂正後市場＝当社優先市場/SORの場合のみセット
    this.checkSor = obj.afterCorrectMarket === 'A' ? obj.checkSor : '' // 確認項目.SOR確認

    this.tesuuryouKbn = obj.tesuuryouKbn ? obj.tesuuryouKbn : '' // 手数料区分
    this.orderDate = obj.orderDate ? obj.orderDate : '' // 受注日
    
    // 訂正SOR注文区分
    // 市場.訂正後市場が「当社優先市場／SOR」に変更された場合 "1"：訂正SOR
    if (obj.afterCorrectMarket === 'A') {
      this.correctSorOrderClassification = '1'
    // 上記以外 "△"：通常訂正
    } else {
      this.correctSorOrderClassification = ' '
    }
  }
}
