export class IfaMarginNewOrderCorrectInputFormModel {
  constructor() {
    this.titleModel = {
      id: 'SUB0202_0212-02_1',
      name: '信用新規注文訂正入力'
    }
    this.ecOrderNo = '' // EC受注番号
    this.market = '' // 市場
    this.brandCode = '' // 銘柄コード
    this.quantity = '' // 数量
    this.unTradeQuantity = '' // 未約定数量
    this.settlementDate0 = '' // 新規建余力（T+0）［ラベル］
    this.settlementDate1 = '' // 新規建余力（T+1）［ラベル］
    this.marginCapacity0 = '' // 新規建余力（T+0）
    this.marginCapacity1 = '' // 新規建余力（T+1）
    this.actualGrntRate0 = '' // 維持率（T+0）
    this.actualGrntRate1 = '' // 維持率（T+1）
    this.maintenanceRateJpyAmountDescriptionMessage = '' // 維持率(円貨)説明文言
    this.orderKind = '' // 注文種別
    this.tradeCd = '' // 取引種別
    this.period = '' // 期間
    this.marginTradeTypeText = '' // 信用取引区分
    this.marginTradeTypeTextCalculation = ''
    this.workingStatus = '' // 発火状態
    this.doneState = '' // DONE状態
    this.paymentDeadline = '' // 弁済期限
    this.rbeOrderStatus = '' // RBE注文ステータス
    this.sasinariHouhou = '' // 通常/逆指値.執行方法
    this.sasinariJyouken = '' // 通常/逆指値.執行条件
    this.triggerPrice = '' // 通常/逆指値.発火条件価格（逆指値）
    this.triggerPriceZone = '' // 通常/逆指値.発火条件価格（逆指値）ゾーン
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
    this.ifd2Limit = '' // IFD2.期間.日付
    this.ifd2SasinariHouhou = '' // IFD2.執行方法
    this.ifd2SasinariJyouken = '' // IFD2.執行条件
    this.ifd2TriggerPrice = '' // IFD2.発火条件価格（逆指値）
    this.ifd2GyakusasiHouhou = '' // IFD2.執行方法（逆指値）
    this.ifd2Price = '' // IFD2.注文単価
    this.correctBeforePriceSasinariHouhou = '' // 訂正前価格.通常/逆指値.執行方法
    this.correctBeforePriceSasinariJyouken = '' // 訂正前価格.通常/逆指値.執行条件
    this.correctBeforePriceTriggerPrice = '' // 訂正前価格.通常/逆指値.発火条件価格（逆指値）
    this.correctBeforePriceTriggerPriceZone = ''// 訂正前価格.通常/逆指値.発火条件価格（逆指値）ゾーン
    this.correctBeforePriceGyakusasiHouhou = ''// 訂正前価格.通常/逆指値.執行方法（逆指値）
    this.correctBeforePricePrice = ''// 訂正前価格.通常/逆指値.注文単価
    this.correctBeforePriceOco1SasinariHouhou = ''// 訂正前価格.OCO1.執行方法
    this.correctBeforePriceOco1SasinariJyouken = ''// 訂正前価格.OCO1.執行条件
    this.correctBeforePriceOco1Price = ''// 訂正前価格.OCO1.注文単価
    this.correctBeforePriceOco2TriggerPrice = ''// 訂正前価格.OCO2.発火条件価格（逆指値）
    this.correctBeforePriceOco2TriggerPriceZone = '' // 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン
    this.correctBeforePriceOco2GyakusasiHouhou = ''// 訂正前価格.OCO2.執行方法（逆指値）
    this.correctBeforePriceOco2GyakusasiJyouken = ''// 訂正前価格.OCO2.執行条件（逆指値）
    this.correctBeforePriceOco2Price = ''// 訂正前価格.OCO2.注文単価
    this.correctBeforePriceIfd1SasinariHouhou = ''// 訂正前価格.IFD1.執行方法
    this.correctBeforePriceIfd1SasinariJyouken = ''// 訂正前価格.IFD1.執行条件
    this.correctBeforePriceIfd1TriggerPrice = ''// 訂正前価格.IFD1.発火条件価格（逆指値）
    this.correctBeforePriceIfd1TriggerPriceZone = ''// 訂正前価格.IFD1.発火条件価格（逆指値）ゾーン
    this.correctBeforePriceIfd1GyakusasiHouhou = ''// 訂正前価格.IFD1.執行方法（逆指値）
    this.correctBeforePriceIfd1Price = ''// 訂正前価格.IFD1.注文単価
    this.correctBeforePriceIfd2Limit = ''// 訂正前価格.IFD2.期間.日付
    this.correctBeforePriceIfd2SasinariHouhou = ''// 訂正前価格.IFD2.執行方法
    this.correctBeforePriceIfd2SasinariJyouken = ''// 訂正前価格.IFD2.執行条件
    this.correctBeforePriceIfd2TriggerPrice = ''// 訂正前価格.IFD2.発火条件価格（逆指値）
    this.correctBeforePriceIfd2TriggerPriceZone = ''// 訂正前価格.IFD2.発火条件価格（逆指値）ゾーン
    this.correctBeforePriceIfd2GyakusasiHouhou = ''// 訂正前価格.IFD2.執行方法（逆指値）
    this.correctBeforePriceIfd2Price = ''// 訂正前価格.IFD2.注文単価
    this.kanyuKbn = '' // 勧誘区分
    this.orderMethod = '' // 受注方法
    this.checkInsiderConfirmCheckBox = '' // 確認項目.契約締結前交付書面確認
    this.comId = '' // 手数料区分
    this.inputDate = '' // 受注日
    this.paymentDeadlineDate = '' // 弁済期限年月日数
    this.dateKbn = ''// 年月日区分
    this.tradeStatus = '' // 約定ステータス
    this.latestMarketId = '' // 直近市場
    this.sorOrderClassification = '' // SOR注文区分
    this.correctSorOrderResultClassification = '' // 訂正SOR注文結果区分
    this.afterCorrectMarket = '' // 訂正後市場
    this.checkSor = '' // 確認項目.SOR確認
    this.correctSorOrderClassification = '' // 訂正SOR注文区分
    this.cc014 = {
      brandCode: '', // 銘柄コード
      market: '' // 選択市場
    }
  }
}
