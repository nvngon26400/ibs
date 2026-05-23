export class IfaFxTradeOrderLookupFormModel {
  constructor() {
    this.totalSize = ''
    this.maxRownum = ''
    this.overMaxRownum = ''
    this.title = '' // タイトル
    this.infoMsg = '' // メッセージ
    this.tradeSuspendFlag = '' // 取引停止フラグ
    this.refinementPeriod = [] // 絞込期間
    this.orderStatus = '0' // 注文状況 【初期値】”すべて”
    this.currencyCode = '0' // 通貨 【初期値】”すべて”
    this.tradeKbn = '0' // 取引種別 【初期値】”すべて”
    this.fxOrderInfoList = [] // 為替取引一覧
    // orderDate: '', // 為替取引一覧.注文・締切・約定日時の注文日時
    // fxOrderStatus: '', // 為替取引一覧.為替注文ステータス
    // deadlineDatetime: '', // 為替取引一覧.注文・締切・約定日時の未約定
    // tradeDateTime: '', // 為替取引一覧.注文・締切・約定日時の約定済
    // currency: '', // 為替取引一覧.通貨の通貨名
    // tradeKbn: '', // 為替取引一覧.取引種別の売買
    // tradeCd: '', // 為替取引一覧.取引種別
    // quantity: '', // 為替取引一覧.数量・受渡金額の数量
    // currencyCode: '', // 為替取引一覧.数量・受渡金額の数量の通貨コード
    // deliveryAmount: '', // 為替取引一覧.数量・受渡金額の受渡金額
    // contractRate: '', // 為替取引一覧.約定レートの約定レート
    // orderStatus: '', // 為替取引一覧.注文状況
    // fxTradeStatus: '', // 為替取引一覧.為替処理ステータス
    // accountType: '', // 為替取引一覧.口座区分
    // orderNumber: '', // 為替取引一覧.注文番号
    // cycleNumber: '', // 為替取引一覧.サイクル番号
    // orderEventId: '', // 為替取引一覧.注文イベントID
    // businessDay: '' // 為替取引一覧.営業日
    this.currencyList = [] // 通貨リスト
    this.currencyList.currencyCode = '' // 通貨リスト.通貨コード
    this.currencyList.currency = '' // 通貨リスト.通貨名
    this.currencyList.decimalLength = '' // 通貨リスト.小数部有効桁数
    this.fxTradeMediateProprietyList = [] // 為替取引媒介可否リスト
    this.fxTradeMediateProprietyList.currencyCode = '' // 為替取引媒介可否リスト.通貨コード
    this.fxTradeMediateProprietyList.mediatePropriety = '' // 為替取引媒介可否リスト.媒介可否
    this.fxTradeMediateProprietyList.tradeCd = '' // 為替取引媒介可否リスト.取引種別
    this.message = ''
    this.errorLevel = ''
    this.requestedTime = ''
    this.title = {
      id: 'SUB0202_0501-01',
      name: '為替取引注文照会'
    }
  }
}
