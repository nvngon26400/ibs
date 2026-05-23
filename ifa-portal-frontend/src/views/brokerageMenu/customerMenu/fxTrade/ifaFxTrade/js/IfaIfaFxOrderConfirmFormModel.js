export class IfaIfaFxOrderConfirmFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0503-02_2',
      name: '【IFA】為替注文確認'
    }
    this.warningThreshold = '' // 注文ワーニングしきい値（数値(整数)）
    this.computeExchangeRate = '' // 概算用レート
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.trade = '' // 売買区分
    // this.currency = '' // 通貨名（元）
    // this.currency = '' // 通貨名（先）
    this.buySellTypeName = '' // 取引種別
    this.currency = '' // 通貨名
    this.volumeLabel = '' // 買付数量／売却数量のラベル
    this.exchangeOrderAmount = '' // 買付数量／売却数量
    this.currencyCode = '' // 買付数量／売却数量の通貨コード
    this.approximateRateLabel = '' // 概算用レートのラベル
    this.approximateRate = '' // 概算用レートの概算用レート
    // this.currencyCode = '' // 概算用レートの通貨コード
    this.approximateRateExchangeRateDateTime = '' // 概算用レートの為替レート日時
    // this.selectedCurrencyInfo = '' // 概算用レート行の適用スプレッドのタイトル
    this.selectedCurrencyInfo = '' // 概算用レート行の適用スプレッドの値
    // this.currencyCode = '' // 概算用レート行の適用スプレッドの通貨コード
    this.beforeAdditionalPriceWording = '' // 概算用レート行の上乗せに係る文言の上乗せ金額の前迄
    this.additionalPrice = '' // 概算用レート行の上乗せに係る文言の上乗せ金額
    this.additionalPriceUnit = '' // 概算用レート行の上乗せに係る文言の上乗せ金額の単位
    this.afterAdditionalPriceWording = '' // 概算用レート行の上乗せに係る文言の上乗せ金額の後
    this.approximateDeliveryAmount = '' // 概算受渡金額
    this.tradeTiming = '' // 約定タイミング
    this.settlementDate = '' // 受渡日
    this.buySellAccountLabel = '' // 買付口座／売却口座のラベル
    this.buySellAccountGeneralAccountLabel = '' // 買付口座／売却口座
    this.exceedingOrderAmountLimitCheckbox = '' // 注文金額上限超過のチェックボックス
    this.noticeNote = '' // 注意事項_1
    // this.noticeNote = '' // 注意事項_2
    // this.noticeNote = '' // 注意事項_3
    // this.noticeNote = '' // 注意事項_4
    // this.noticeNote = '' // 注意事項_5
    // this.noticeNote = '' // 注意事項_6
    // this.noticeNote = '' // 注意事項_7
    this.fxRateAdditionalType = '' // 上乗せ区分
    this.saleMethod = '' // 売却方法
    this.quantityDesignationMethod = '' // 数量の指定方法
    this.decimalLength = '' // 小数部有効桁数
  }
}
