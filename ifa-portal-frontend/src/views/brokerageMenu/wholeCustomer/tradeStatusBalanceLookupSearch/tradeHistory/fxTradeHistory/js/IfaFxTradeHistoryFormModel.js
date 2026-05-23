export class IfaFxTradeHistoryFormModel {
  constructor() {
    this.title = {
      id: 'SUB020302_0202-01',
      name: '為替取引履歴'
    }
    this.chkBrokerCodeExclude = '' // 仲介業者除外
    this.brokerCode = '' // 仲介業者コード
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名（漢字／カナ）
    this.customerNameKanjiKanaTerms = '3' // 顧客名（漢字／カナ）_条件
    this.courseSelected = [] // selected取引コース

    this.periodDate = [] // 期間指定_入力 【初期値】前営業日の日付（From,Toどちらも）
    this.periodAlert = '' // 期間指定_メッセージ
    this.currency = '' // 通貨_入力 【初期値】""："全通貨"
    this.fxTradeHistoryList = [{ // 為替取引履歴一覧
      brokerCode: '', // 為替取引履歴一覧.仲介業者コード
      brokerName: '', // 為替取引履歴一覧.仲介業者名
      empCode: '', // 為替取引履歴一覧.営業員コード
      brokerChargeName: '', // 為替取引履歴一覧.営業員名
      buten: '', // 為替取引履歴一覧.部店
      accountNumber: '', // 為替取引履歴一覧.口座番号
      dealerNumber: '', // 為替取引履歴一覧.扱者コード
      courseName: '', // 為替取引履歴一覧.取引コース
      customerNameKanji: '', // 為替取引履歴一覧.顧客名(漢字)
      customerNameKana: '', // 為替取引履歴一覧.顧客名(カナ)
      tradeDate: '', // 為替取引履歴一覧.約定日
      settlementDate: '', // 為替取引履歴一覧.受渡日
      tradeTypeName: '', // 為替取引履歴一覧.取引種別
      currency: '', // 為替取引履歴一覧.通貨
      fxRate: '', // 為替取引履歴一覧.為替レート
      exchangeSpread: '', // 為替取引履歴一覧.為替スプレッド
      yenAmount: '', // 為替取引履歴一覧.円額
      foreignAmount: '', // 為替取引履歴一覧.外貨額
      exchangeFee: '', // 為替取引履歴一覧.為替手数料
      brokerageBranchCode: '', // 為替取引履歴一覧.支店コード
      brokerBranchName: '' // 為替取引履歴一覧.支店名
    }]

    this.fxTradeHistoryComment = '' // 為替取引履歴コメント
    this.currencyCodeList = [] // 通貨コードリスト
  }
}
