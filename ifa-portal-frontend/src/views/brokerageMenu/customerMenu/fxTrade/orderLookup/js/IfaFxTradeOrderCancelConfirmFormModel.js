export class IfaFxTradeOrderCancelConfirmFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0501-02',
      name: '為替取引注文取消確認'
    } // タイトル
    this.warningMessage = '' // 注文取消未完了
    // this.customerNameKanji = '' // 顧客名（漢字）
    // this.customerNameKana = '' // 顧客名（カナ）
    // this.butenCode = '' // 部店コード
    // this.accountNumber = '' // 口座番号
    // this.buySellTypeName = '' // 売買区分
    // this.currency = '' // 通貨名（元）
    // this.currency = '' // 通貨名（先）
    this.cancelContentLabel = '' // 取消内容
    this.orderTypeName = '' // 取引種別
    this.currencyLabel = '' // 通貨の見出し
    this.currency = '' // 通貨の通貨名
    this.quantityLabel = '' // 数量の見出し
    this.foreignCurrency = '' // 数量の値
    this.currencyCode = '' // 数量の通貨コード
    this.tradingAccountLabel = '' // 取引口座の見出し
    this.tradingAccount = '' // 取引口座
    this.orderNumber = '' // 注文番号
    this.cycleNumber = ''// サイクル番号
    this.orderEventId = ''// 注文イベントID
    this.tradeTiming = '' // 為替取引
    this.businessDay = '' // 営業日
  }
}
