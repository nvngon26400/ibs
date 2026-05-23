export class IfaDomesticStockOrderCancelConfirmFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0208-04_1',
      name: '国内株式注文取消確認'
    }
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    this.butenCode = '' // 部店
    this.accountNumber = '' // 口座番号
    this.brandCode = '' // 銘柄コード
    this.brandName = '' // 銘柄名
    this.orderKind = '' // 注文種別
    this.tradeCd = '' // 取引種別
    this.market = '' // 市場
    this.unTradeQuantity = '' // 数量
    this.sasinariHouhou = '' // 執行方法
    this.sasinariJyouken = '' // 執行条件（指値）
    this.price = '' // 注文単価（指値）
    this.marketOrderSasinariJyouken = '' // 執行条件（成行）
    this.stopOrderPriceText1 = '' // 発火条件価格（逆指値）前文言
    this.triggerPrice = '' // 発火条件価格（逆指値）
    this.stopOrderPriceText2 = '' // 発火条件価格（逆指値）後文言
    this.stopOrderLimitExecutionConditionText = '' // 執行条件（逆指値/指値）
    this.reversePriceLimitPrice = '' // 注文単価（逆指値/指値）
    this.reversePriceLimitSasinariJyouken = '' // 執行条件（逆指値/成行）
    this.stopOrderExecutePriceText2 = '' // 注文単価（逆指値）文言
    this.period = '' // 期間
    this.depositType = '' // 預り区分
    this.tradingCoursText = '' // 手数料区分
    this.orderDayTime = '' // 受注日時
    this.oco1SasinariHouhou = '' // 執行方法
    this.oco1SasinariJyouken = '' // 執行条件
    this.oco1Price = '' // 注文単価
    this.oco2StopOrderPriceText1 = '' // 発火条件価格（逆指値）前文言
    this.oco2TriggerPrice = '' // 発火条件価格（逆指値）
    this.oco2StopOrderPriceText2 = '' // 発火条件価格（逆指値）後文言
    this.oco2Oco1SasinariJyouken = '' // 執行条件（OCO1）
    this.oco2Price = '' // 注文単価（逆指値/指値）
    this.oco2GyakusasiHouhou = '' // 執行条件（逆指値/成行）
    this.oco2StopOrderExecutePriceText2 = '' // 注文単価（逆指値）文言
    this.ifd2TradeCd = '' // 取引種別
    this.ecOrderNo = '' // EC受注番号
    this.autoOrderShubetsu = '' // 自動注文種別
    this.rbeChumonShubetsu = '' // RBE注文種別
    this.sashinariKbn = '' // 指成区分
    this.sashine = '' // 指値
    this.triggerZone = '' // トリガ発動ゾーン
    this.triggerNedan = '' // トリガ値段
    this.ocoSasinariKbn = '' // OCO指成区分
    this.ocoSashine = '' // OCO指値
    this.doneSasinariKbn = '' // DONE指成区分
    this.doneSashine = '' // DONE指値
    this.doneRbeOrderKind = '' // DONERBE注文種別
    this.doneTriggerZone = '' // DONEトリガ発動ゾーン
    this.doneTriggerNedan = '' // DONEトリガ値段
    this.doneOcoSasinariKbn = '' // DONEOCO指成区分
    this.doneOcoSashine = '' // DONEOCO指値
    this.doneYuukouKigen = '' // DONE有効期限
    this.rbeOrderStatus = '' // RBE注文ステータス
    this.workingStatus = '' // 発火状態
    this.comIdR = '' // 手数料区分（採用）
  }
}
