export class IfaDomesticTradeStatusListFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0105-01',
      name: '国内株当日約定状況（一覧）'
    } // タイトル
    this.accountTypeRadio = '2' // 口座区分 【初期値】全て
    this.noDetailMsg = '約定明細はありません。' // 約定明細なしメッセージ
    this.spotMarginTradeCount = '' // 現物信用約定件数
    this.spotMarginTradeList = [] // 現物信用約定状況一覧
    // this.brandCode = '' // 現物信用約定状況一覧.銘柄コード
    // this.brandName = '' // 現物信用約定状況一覧.銘柄名
    // this.tradeClassification = '' // 現物信用約定状況一覧.取引種別
    // this.marginTradeTypeText = '' // 現物信用約定状況一覧.信用取引区分
    // this.depositType = '' // 現物信用約定状況一覧.預り区分
    // this.tradeDate = '' // 現物信用約定状況一覧.約定日
    // this.settlementDate = '' // 現物信用約定状況一覧.受渡日
    // this.domesticQuantityInput = '' // 現物信用約定状況一覧.数量
    // this.averagePrice = '' // 現物信用約定状況一覧.平均単価
    // this.taxAmountCapitalGain = '' // 現物信用約定状況一覧.課税額/譲渡益税
    // this.commCost = '' // 現物信用約定状況一覧.手数料/諸経費
    // this.settlementAmount = '' // 現物信用約定状況一覧.精算金額
    // this.dayTrade = '' // 現物信用約定状況一覧.日計り
    // this.collateralEligibleType = '' // 現物信用約定状況一覧.代用適格

    this.receiptDeliveryTradeCount = '' // 現引現渡約定件数
    this.receiptDeliveryTradeList = [] // 現引現渡約定状況一覧
    // this.ecOrderNo = '' // 現引現渡約定状況一覧.EC受注番号
    // this.brandCode = '' // 現引現渡約定状況一覧.銘柄コード
    // this.brandName = '' // 現引現渡約定状況一覧.銘柄名
    // this.tradeCd = '' // 現引現渡約定状況一覧.取引種別
    // this.marginTradeTypeText = '' // 現引現渡約定状況一覧.信用取引区分
    // this.coercionType = '' // 現引現渡約定状況一覧.強制決済
    // this.depositType = '' // 現引現渡約定状況一覧.預り区分
    // this.orderDayTime = '' // 現引現渡約定状況一覧.受注日時
    // this.tradeDate = '' // 現引現渡約定状況一覧.約定日
    // this.domesticQuantityInput = '' // 現引現渡約定状況一覧.数量
    // this.cost = '' // 現引現渡約定状況一覧.諸経費
    // this.settlementAmount = '' // 現引現渡約定状況一覧.精算金額
    // this.constructionDate = '' // 現引現渡約定状況一覧.新規建日
    // this.newPrice = '' // 現引現渡約定状況一覧.新規単価
  }
}
