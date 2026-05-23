export class IfaDomesticPositionDetailFormModel {
  constructor() {
    this.title = '建玉詳細' // 画面名
    this.brandCode = '' // 銘柄コード
    this.brand = '' // 銘柄
    this.market = '' // 市場
    this.newMarket = '' // 売／買建
    this.limit = '' // 期限
    this.positionDetailDeadLine = '' // 親株新規約定日
    this.openTradeDate = '' // 建日
    this.lastTradeDate = '' // 返済期限
    this.depositType = '' // 預り区分
    this.stockNumTotal = '' // 建株数
    this.positionPriceLabel = '' // 建単価
    this.constructionPriceTotal = '' // 建代金
    this.costLabel = '' // 諸経費等ラベル
    this.domesticNewComm = '' // 新規建手数料（税込）
    this.managePrice = '' // 管理料
    this.rightProcessingCharge = '' // 権利処理等手数料
    this.dailyInterest = '' // 日歩
    this.reverseDailyInterest = '' // 逆日歩および貸株料
    this.total = '' // 合計
    this.newDepositRate = '' // 新規建保証金率
    this.cashDepositRate = '' // 現金保証金率
    this.cashBuyDepositRate = '' // 現物買付保証金率
    this.withdrawTransferDepositRate = '' // 出金・振替保証金率
    this.additionalCollateralRegulations = '' // 増担保規制
    this.batchIndividualDisplayFlag = '' // 一括個別表示フラグ
    this.repayPeriodShorterFlag = '' // 期日短縮フラグ
  }
}
