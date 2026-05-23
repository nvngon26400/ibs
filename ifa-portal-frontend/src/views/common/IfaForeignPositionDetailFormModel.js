export class IfaForeignPositionDetailFormModel {
  constructor() {
    this.title = '建玉詳細' // 画面名
    this.brandCode = '' // ティッカー
    this.brandName = '' // 銘柄
    this.foreignMarket = '' // 市場
    this.tradeKbn = '' // 建区分
    this.marginDueDate = '' // 期限
    this.openTradeDate = '' // 建日
    this.lastTradeDate = '' // 返済期限
    this.depositType = '' // 預り区分
    this.positionRestQuantity = '' // 建数量
    this.previousDayValue = '' // 建単価
    this.foreignNewPositionAmount = '' // 建代金
    this.foreignNewComm = '' // 新規建手数料（税込）
    this.interestForeign = '' // 金利
    this.stockLendingPriceForeign = '' // 貸株料
    this.managePriceForeign = '' // 管理料（税込）
    this.transferPriceForeign = '' // 名義書換料（税込）
    this.costForeignLink = '' // 合計
    this.batchIndividualDisplayFlag = '' // 一括個別表示フラグ
  }
}
