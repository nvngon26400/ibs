export class IfaIfaFxOrderInputFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0503-02_1',
      name: '【IFA】為替注文入力'
    }
    this.tradeKbn = '' // 売買区分
    this.currencyCodeSelected = '' // 選択行通貨コード
    this.updateTime = '' // 更新日時
    this.referenceRate = '' // 参考レート
    this.denominator = '' // デノミ
    this.currencyCode = '' // 通貨コード
    this.adjustPrice = '' // スプレッド
    this.maxOrderableQuantity = '' // 注文可能数量
    this.currency = '' // 通貨名（全角）
    this.maxOrderableQuantityLabel = '' // 注文可能数量ラベル
    this.approximatePurchaseAmount = '' // 概算買付可能金額
    this.buySellTypeName = '' // 取引種別
    this.saleMethod = '' // 売却方法 【初期値】"指定した数量を売却"
    this.quantityDesignationMethod = '' // 数量の指定方法 【初期値】"外貨で指定"
    this.foreignButtonRange = '' // 外貨スピンボタン増減幅
    this.salesVolumeLimit = '' // 売却数量上限
    this.closableQuantity = '' // 取引下限
    this.tradeLimitMax = '' // 取引上限
    this.tradeUnit = '' // 取引単位
    this.volumeLabel = '' // 数量入力欄ラベル
    this.foreignVolume = '' // 数量入力（外貨）
    this.domesticVolume = '' // 数量入力（円）
    this.approximateForeignCount = '' // 概算外貨数量 【初期値】"-"
    this.approximateDeliveryAmount = '' // 概算受渡金額 【初期値】"-円"
    this.purchasForeignCurrencyAnnotation = '' // 注釈（買付-外貨指定）
    this.purchasYenAnnotation = '' // 注釈（買付-円指定）
    this.saleForeignCurrencyAnnotation = '' // 注釈（売却-外貨指定）
    this.saleYenAnnotation = '' // 注釈（売却-円指定）
    this.noticeNote1 = '' // 注意事項_1
    this.noticeNote2 = '' // 注意事項_2
    this.noticeNote3 = '' // 注意事項_3
    this.noticeNote4 = '' // 注意事項_4
    this.noticeNote5 = '' // 注意事項_5
    this.noticeNote6 = '' // 注意事項_6
    this.noticeNote7 = '' // 注意事項_7
    this.exchangeGroup = '' // 為替グループ
    this.computeExchangeRate = '' // 概算用レート
    this.decimalLength = '' // 小数部有効桁数
    this.foreignDesignationMaxValue = '' // 外貨指定最大値
    this.yenDesignationMinValue = '' // 円指定最小値
    this.yenDesignationMaxValue = '' // 円指定最大値
    this.warningThreshold = '' // 注文ワーニングしきい値（数値(整数)）
    this.sellAbleQuantity // 売却可能数量
  }
}
