export class IfaDomesticStockOrderCorrectInputFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0208-03_1',
      name: '国内株式注文訂正入力'
    }

    this.ecOrderNo = '' // EC受注番号
    this.settlementDateAfterBusinessDayT2 = '' // 買付余力.受渡日(T+2)
    this.settlementDateAfterBusinessDayT3 = '' // 買付余力.受渡日(T+3)
    this.yenBuyingPowerGeneralAccountT2 = '' // 買付余力.総合口座（T+2）
    this.yenBuyingPowerGeneralAccountT3 = '' // 買付余力.総合口座（T+3）
    this.yenBuyingPowerJrNisaT2 = '' // 買付余力.JrNISA口座（T+2）
    this.yenBuyingPowerJrNisaT3 = '' // 買付余力.JrNISA口座（T+3）
    this.nisaInvestableQuotaMessage = '' // 買付余力.NISA買付可能枠
    this.brandCode = '' // 銘柄コード
    this.brandName = '' // 銘柄名
    this.market = '' // 市場
    this.tradeCd = '' // 取引種別
    this.orderQuantity = '' // 注文数量
    this.unTradeQuantity = '' // 未約定数量
    this.period = '' // 期間
    this.depositType = '' // 預り区分
    this.orderKind = '' // 注文種別
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
    this.workingStatus = '' // 発火状態
    this.doneState = '' // DONE状態
    this.rbeOrderStatus = '' // RBE注文ステータス
    this.tesuuryouKbn = '' // 手数料区分
    this.kanyuKbn = '' // 勧誘区分
    this.orderDate = '' // 受注日
    this.checkInsider = '' // インサイダー確認
    this.receiveOrderType = '' // 受注方法
    this.tradeStatus = '' // 約定ステータス
    this.latestMarketId = '' // 直近市場
    this.sorOrderClassification = '' // SOR注文区分
    this.correctSorOrderResultClassification = '' // 訂正SOR注文結果区分
    this.afterCorrectMarket = '' // 訂正後市場
    this.checkSor = '' // 確認項目.SOR確認
    this.correctSorOrderClassification = '' // 訂正SOR注文区分
    this.sorServiceKbn = '' // SOR取扱区分

    // 訂正前価格
    this.beforeCorrectPrice = {
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
    }

    // CC014
    this.cc014 = {
      selectedMarket: '', // 選択市場
      brandCode: '' // 銘柄コード
    }
  }
}
