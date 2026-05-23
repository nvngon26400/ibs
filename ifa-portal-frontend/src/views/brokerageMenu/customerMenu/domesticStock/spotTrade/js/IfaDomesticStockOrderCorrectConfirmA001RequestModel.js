import Logger from '@/utils/ifaLog.js'
export class IfaDomesticStockOrderCorrectConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.ecOrderNo = obj.ecOrderNo ? obj.ecOrderNo : '' // EC受注番号
    this.market = obj.market ? obj.market : '' // 市場（訂正前）
    this.afterCorrectMarket = obj.afterCorrectMarket ? obj.afterCorrectMarket : '' // 市場（訂正後）
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.unTradeQuantity = obj.unTradeQuantity ? obj.unTradeQuantity : '' // 未約定数量
    this.orderKind = obj.orderKind ? obj.orderKind : '' // 注文種別
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.period = obj.period ? obj.period : '' // 期間
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分

    this.sasinariHouhou = '' // 通常/逆指値.執行方法
    this.sasinariJyouken = '' // 通常/逆指値.執行条件
    this.triggerPrice = '' // 通常/逆指値.発火条件価格（逆指値）
    this.gyakusasiHouhou = '' // 通常/逆指値.執行方法（逆指値）
    this.price = '' // 通常/逆指値.注文単価
    this.oco1SasinariHouhou = '' // OCO1.執行方法
    this.oco1SasinariJyouken = '' // OCO1.執行条件
    this.oco1Price = '' // OCO1.注文単価
    this.oco2TriggerPrice = '' // OCO2.発火条件価格（逆指値）
    this.oco2GyakusasiHouhou = '' // OCO2.執行方法（逆指値）
    this.oco2GyakusasiJyouken = '' // OCO2.執行条件（逆指値）
    this.oco2Price = '' // OCO2.注文単価
    this.ifd1SasinariHouhou = '' // IFD1.執行方法
    this.ifd1SasinariJyouken = '' // IFD1.執行条件
    this.ifd1TriggerPrice = '' // IFD1.発火条件価格（逆指値）
    this.ifd1GyakusasiHouhou = '' // IFD1.執行方法（逆指値）
    this.ifd1Price = '' // IFD1.注文単価
    this.ifd2PeriodTerms = '' // IFD2.期間.期間条件
    this.ifd2Limit = '' // IFD2.期間.日付
    this.ifd2SasinariHouhou = '' // IFD2.執行方法
    this.ifd2SasinariJyouken = '' // IFD2.執行条件
    this.ifd2TriggerPrice = '' // IFD2.発火条件価格（逆指値）
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
      // this.ifd2SasinariHouhou = obj.ifd2SasinariHouhou ? obj.ifd2SasinariHouhou : '' // IFD2.執行方法
      if (obj.ifd2SasinariHouhou === '1') {
        // IFD2.執行方法=指値
        this.ifd2SasinariHouhou = obj.ifd2SasinariHouhou ? obj.ifd2SasinariHouhou : '' // IFD2.執行方法
        this.ifd2SasinariJyouken = obj.ifd2SasinariJyouken ? obj.ifd2SasinariJyouken : '' // IFD2.執行条件
        this.ifd2Price = obj.ifd2Price ? obj.ifd2Price : '' // IFD2.注文単価
      }
      if (obj.ifd2SasinariHouhou === '3' && (obj.ifd2GyakusasiHouhou === '1' || obj.ifd2GyakusasiHouhou === '2')) {
        // IFD2.執行方法=逆指値　かつ　IFD2.執行方法（逆指値）=指値　または　成行
        this.ifd2SasinariHouhou = obj.ifd2SasinariHouhou ? obj.ifd2SasinariHouhou : '' // IFD2.執行方法
        this.ifd2SasinariJyouken = obj.ifd2SasinariJyouken ? obj.ifd2SasinariJyouken : '' // IFD2.執行条件
      }
      if (obj.ifd2SasinariHouhou === '3') {
        // IFD2.執行方法=逆指値
        this.ifd2TriggerPrice = obj.ifd2TriggerPrice ? obj.ifd2TriggerPrice : '' // IFD2.発火条件価格（逆指値）
        this.ifd2GyakusasiHouhou = obj.ifd2GyakusasiHouhou ? obj.ifd2GyakusasiHouhou : '' // IFD2.執行方法（逆指値）
        if (obj.ifd2GyakusasiHouhou === '1') {
          // IFD2.執行方法（逆指値）=指値
          this.ifd2Price = obj.ifd2Price ? obj.ifd2Price : '' // IFD2.注文単価
        }
      }
    }

    this.beforeCorrectPrice = [{
      sasinariHouhou: '', // 訂正前価格.通常/逆指値.執行方法
      sasinariJyouken: '', // 訂正前価格.通常/逆指値.執行条件
      triggerPrice: '', // 訂正前価格.通常/逆指値.発火条件価格（逆指値）
      stopOrderPriceText2: '', // 訂正前価格.通常/逆指値.発火条件価格（逆指値）ゾーン
      gyakusasiHouhou: '', // 訂正前価格.通常/逆指値.執行方法（逆指値）
      price: '', // 訂正前価格.通常/逆指値.注文単価
      oco1SasinariHouhou: '', // 訂正前価格.OCO1.執行方法
      oco1SasinariJyouken: '', // 訂正前価格.OCO1.執行条件
      oco1Price: '', // 訂正前価格.OCO1.注文単価
      oco2TriggerPrice: '', // 訂正前価格.OCO2.発火条件価格（逆指値）
      oco2StopOrderPriceText2: '', // 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン
      oco2GyakusasiHouhou: '', // 訂正前価格.OCO2.執行方法（逆指値）
      oco2GyakusasiJyouken: '', // 訂正前価格.OCO2.執行条件（逆指値）
      oco2Price: '', // 訂正前価格.OCO2.注文単価
      ifd1SasinariHouhou: '', // 訂正前価格.IFD1.執行方法
      ifd1SasinariJyouken: '', // 訂正前価格.IFD1.執行条件
      ifd1TriggerPrice: '', // 訂正前価格.IFD1.発火条件価格（逆指値）
      ifd1StopOrderPriceText2: '', // 訂正前価格.IFD1.発火条件価格（逆指値）ゾーン
      ifd1GyakusasiHouhou: '', // 訂正前価格.IFD1.執行方法（逆指値）
      ifd1Price: '', // 訂正前価格.IFD1.注文単価
      ifd2Limit: '', // 訂正前価格.IFD2.期間.日付
      ifd2SasinariHouhou: '', // 訂正前価格.IFD2.執行方法
      ifd2SasinariJyouken: '', // 訂正前価格.IFD2.執行条件
      ifd2TriggerPrice: '', // 訂正前価格.IFD2.発火条件価格（逆指値）
      ifd2StopOrderPriceText2: '', // 訂正前価格.IFD2.発火条件価格（逆指値）ゾーン
      ifd2GyakusasiHouhou: '', // 訂正前価格.IFD2.執行方法（逆指値）
      ifd2Price: '' // 訂正前価格.IFD2.注文単価
    }]

    // 条件に一致する時のみ値をセット
    if (obj.orderKind === '1') {
      // 注文種別=通常/逆指値
      this.beforeCorrectPrice[0].sasinariHouhou = obj.beforeCorrectPrice[0].sasinariHouhou ? obj.beforeCorrectPrice[0].sasinariHouhou : '' // 訂正前価格.通常/逆指値.執行方法
      this.beforeCorrectPrice[0].sasinariJyouken = obj.beforeCorrectPrice[0].sasinariJyouken ? obj.beforeCorrectPrice[0].sasinariJyouken : '' // 訂正前価格.通常/逆指値.執行条件
      this.beforeCorrectPrice[0].triggerPrice = obj.beforeCorrectPrice[0].triggerPrice ? obj.beforeCorrectPrice[0].triggerPrice : '' // 訂正前価格.通常/逆指値.発火条件価格（逆指値）
      this.beforeCorrectPrice[0].stopOrderPriceText2 = obj.beforeCorrectPrice[0].stopOrderPriceText2 ? obj.beforeCorrectPrice[0].stopOrderPriceText2 : '' // 訂正前価格.通常/逆指値.発火条件価格（逆指値）ゾーン
      this.beforeCorrectPrice[0].gyakusasiHouhou = obj.beforeCorrectPrice[0].gyakusasiHouhou ? obj.beforeCorrectPrice[0].gyakusasiHouhou : '' // 訂正前価格.通常/逆指値.執行方法（逆指値）
      this.beforeCorrectPrice[0].price = obj.beforeCorrectPrice[0].price ? obj.beforeCorrectPrice[0].price : '' // 訂正前価格.通常/逆指値.注文単価
    }

    if (obj.orderKind === '2' || obj.orderKind === '4') {
      // 注文種別=OCO　または　IFDOCO
      this.beforeCorrectPrice[0].oco1SasinariHouhou = obj.beforeCorrectPrice[0].oco1SasinariHouhou ? obj.beforeCorrectPrice[0].oco1SasinariHouhou : '' // 訂正前価格.OCO1.執行方法
      this.beforeCorrectPrice[0].oco1SasinariJyouken = obj.beforeCorrectPrice[0].oco1SasinariJyouken ? obj.beforeCorrectPrice[0].oco1SasinariJyouken : '' // 訂正前価格.OCO1.執行条件
      this.beforeCorrectPrice[0].oco1Price = obj.beforeCorrectPrice[0].oco1Price ? obj.beforeCorrectPrice[0].oco1Price : '' // 訂正前価格.OCO1.注文単価
      this.beforeCorrectPrice[0].oco2TriggerPrice = obj.beforeCorrectPrice[0].oco2TriggerPrice ? obj.beforeCorrectPrice[0].oco2TriggerPrice : '' // 訂正前価格.OCO2.発火条件価格（逆指値）
      this.beforeCorrectPrice[0].oco2StopOrderPriceText2 = obj.beforeCorrectPrice[0].oco2StopOrderPriceText2 ? obj.beforeCorrectPrice[0].oco2StopOrderPriceText2 : '' // 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン
      this.beforeCorrectPrice[0].oco2GyakusasiHouhou = obj.beforeCorrectPrice[0].oco2GyakusasiHouhou ? obj.beforeCorrectPrice[0].oco2GyakusasiHouhou : '' // 訂正前価格.OCO2.執行方法（逆指値）
      this.beforeCorrectPrice[0].oco2GyakusasiJyouken = obj.beforeCorrectPrice[0].oco2GyakusasiJyouken ? obj.beforeCorrectPrice[0].oco2GyakusasiJyouken : '' // 訂正前価格.OCO2.執行条件（逆指値）
      this.beforeCorrectPrice[0].oco2Price = obj.beforeCorrectPrice[0].oco2Price ? obj.beforeCorrectPrice[0].oco2Price : '' // 訂正前価格.OCO2.注文単価
    }

    if (obj.orderKind === '3' || obj.orderKind === '4') {
      // 注文種別=IFD　または　IFDOCO
      this.beforeCorrectPrice[0].ifd1SasinariHouhou = obj.beforeCorrectPrice[0].ifd1SasinariHouhou ? obj.beforeCorrectPrice[0].ifd1SasinariHouhou : '' // 訂正前価格.IFD1.執行方法
      this.beforeCorrectPrice[0].ifd1SasinariJyouken = obj.beforeCorrectPrice[0].ifd1SasinariJyouken ? obj.beforeCorrectPrice[0].ifd1SasinariJyouken : '' // 訂正前価格.IFD1.執行条件
      this.beforeCorrectPrice[0].ifd1TriggerPrice = obj.beforeCorrectPrice[0].ifd1TriggerPrice ? obj.beforeCorrectPrice[0].ifd1TriggerPrice : '' // 訂正前価格.IFD1.発火条件価格（逆指値）
      this.beforeCorrectPrice[0].ifd1StopOrderPriceText2 = obj.beforeCorrectPrice[0].ifd1StopOrderPriceText2 ? obj.beforeCorrectPrice[0].ifd1StopOrderPriceText2 : '' // 訂正前価格.IFD1.発火条件価格（逆指値）ゾーン
      this.beforeCorrectPrice[0].ifd1GyakusasiHouhou = obj.beforeCorrectPrice[0].ifd1GyakusasiHouhou ? obj.beforeCorrectPrice[0].ifd1GyakusasiHouhou : '' // 訂正前価格.IFD1.執行方法（逆指値）
      this.beforeCorrectPrice[0].ifd1Price = obj.beforeCorrectPrice[0].ifd1Price ? obj.beforeCorrectPrice[0].ifd1Price : '' // 訂正前価格.IFD1.注文単価
      this.beforeCorrectPrice[0].ifd2Limit = obj.beforeCorrectPrice[0].ifd2Limit ? obj.beforeCorrectPrice[0].ifd2Limit : '' // 訂正前価格.IFD2.期間.日付
    }

    if (obj.orderKind === '3') {
      // 注文種別=IFD
      this.beforeCorrectPrice[0].ifd2SasinariHouhou = obj.beforeCorrectPrice[0].ifd2SasinariHouhou ? obj.beforeCorrectPrice[0].ifd2SasinariHouhou : '' // 訂正前価格.IFD2.執行方法
      this.beforeCorrectPrice[0].ifd2SasinariJyouken = obj.beforeCorrectPrice[0].ifd2SasinariJyouken ? obj.beforeCorrectPrice[0].ifd2SasinariJyouken : '' // 訂正前価格.IFD2.執行条件
      this.beforeCorrectPrice[0].ifd2TriggerPrice = obj.beforeCorrectPrice[0].ifd2TriggerPrice ? obj.beforeCorrectPrice[0].ifd2TriggerPrice : '' // 訂正前価格.IFD2.発火条件価格（逆指値）
      this.beforeCorrectPrice[0].ifd2StopOrderPriceText2 = obj.beforeCorrectPrice[0].ifd2StopOrderPriceText2 ? obj.beforeCorrectPrice[0].ifd2StopOrderPriceText2 : '' // 訂正前価格.IFD2.発火条件価格（逆指値）ゾーン
      this.beforeCorrectPrice[0].ifd2GyakusasiHouhou = obj.beforeCorrectPrice[0].ifd2GyakusasiHouhou ? obj.beforeCorrectPrice[0].ifd2GyakusasiHouhou : '' // 訂正前価格.IFD2.執行方法（逆指値）
      this.beforeCorrectPrice[0].ifd2Price = obj.beforeCorrectPrice[0].ifd2Price ? obj.beforeCorrectPrice[0].ifd2Price : '' // 訂正前価格.IFD2.注文単価
    }

    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.receiveOrderType = obj.receiveOrderType ? obj.receiveOrderType : '' // 受注方法
    this.checkInsider = obj.checkInsider ? obj.checkInsider : '' // 確認項目.インサイダー確認
    this.checkSor = obj.checkSor ? obj.checkSor : '' // 確認項目.SOR確認

    this.tradingCautionInformation = '' // アラート内容確認.取引注意情報（銘柄）確認
    this.invitationCheck = '' // アラート内容確認.コンプラランクチェック確認
    this.noticeInfoAlertCheck = '' // アラート内容確認.注意情報アラート確認
    this.noticeAlertCheck = '' // アラート内容確認.お知らせアラート確認
    this.noticeInfoAlert = '' // 注意情報アラート
    this.noticeAlert = '' // お知らせアラート
    this.messageId = '' // コンプラランクチェック.メッセージ
    this.chkBoxLabel = '' // コンプラランクチェック.チェックボックス文言
    this.regKbn = '' // 取引注意情報（銘柄）メッセージ

    if (obj.regKbn && obj.regKbn.length > 0) {
      // 取引注意情報（銘柄）メッセージ=あり
      this.tradingCautionInformation = obj.tradingCautionInformation ? obj.tradingCautionInformation : '' // アラート内容確認.取引注意情報（銘柄）確認
      this.regKbn = obj.regKbn ? obj.regKbn : '' // 取引注意情報（銘柄）メッセージ
    }
    if (obj.messageId && obj.messageId.length > 0) {
      // コンプラランクチェック.メッセージ=あり
      this.invitationCheck = obj.invitationCheck ? obj.invitationCheck : '' // アラート内容確認.コンプラランクチェック確認
      this.messageId = obj.messageId ? obj.messageId : '' // コンプラランクチェック.メッセージ
      this.chkBoxLabel = obj.chkBoxLabel ? obj.chkBoxLabel : '' // コンプラランクチェック.チェックボックス文言
    }
    if (obj.noticeInfoAlert && obj.noticeInfoAlert.length > 0) {
      // 注意情報アラート=あり
      this.noticeInfoAlertCheck = obj.noticeInfoAlertCheck ? obj.noticeInfoAlertCheck : '' // アラート内容確認.注意情報アラート確認
      this.noticeInfoAlert = obj.noticeInfoAlert ? obj.noticeInfoAlert : '' // 注意情報アラート
    }
    if (obj.noticeAlert && obj.noticeAlert.length > 0) {
      // お知らせアラート=あり
      this.noticeAlertCheck = obj.noticeAlertCheck ? obj.noticeAlertCheck : '' // アラート内容確認.お知らせアラート確認
      this.noticeAlert = obj.noticeAlert ? obj.noticeAlert : '' // お知らせアラート
    }

    this.fundComplianceCheck = obj.fundComplianceCheck ? obj.fundComplianceCheck : '' // コンプラランクチェック.コンプラチェック用資金性格

    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.doneState = obj.doneState ? obj.doneState : '' // DONE状態
    this.workingStatus = obj.workingStatus ? obj.workingStatus : '' // 発火状態
    this.rbeOrderStatus = obj.rbeOrderStatus ? obj.rbeOrderStatus : '' // RBE注文ステータス
    this.tesuuryouKbn = obj.tesuuryouKbn ? obj.tesuuryouKbn : '' // 手数料区分
    this.orderDate = obj.orderDate ? obj.orderDate : '' // 受注日
    
    // 訂正SOR注文区分
    // 市場（訂正後）が「当社優先市場／SOR」に変更された場合 "1"：訂正SOR
    if (obj.afterCorrectMarket === 'A') {
      this.correctSorOrderClassification = '1'
    // 上記以外 "△"：通常訂正
    } else {
      this.correctSorOrderClassification = ' '
    }
  }
}
