export class IfaPriceViewLookupForeignStockBrandListFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0302-01',
      name: '単価表照会'
    }
    this.tickerSelectFlag = '0' // 検索条件 【初期値】ティッカー
    this.brandCodeTicker = '' // ティッカーキー
    this.brandName = '' // 名称キー
    this.notification = '' // お知らせ
    this.updateTime = '' // 更新時間
    this.priceList = [{ // 単価表
      ticker: '', // 単価表.ティッカ―
      name: '', // 単価表.名称
      basePrice8: '', // 単価表.基準価格
      last: '', // 単価表.前日終値
      diff: '', // 単価表.前日比（%）
      tradeSuspendPriceChangeReason: '' // 単価表.取引停止/価格変更理由
    }]
    this.noticeNote = '' // 注意事項

    // リンクURL
    this.foreignSecuritiesInfoUpdateHistoryUrl = ''
    this.domesticOverTheCounterTradingManualUrl = ''
    this.latestForeignSecuritiesInfoListUrl = ''
  }
}
