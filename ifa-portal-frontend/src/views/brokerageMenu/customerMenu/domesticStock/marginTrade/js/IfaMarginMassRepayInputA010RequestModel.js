import Logger from '@/utils/ifaLog.js'
export class IfaMarginMassRepayInputA010RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.newCreditOrderType = obj.tradeCd === '5' ? '1' : '0' // 新規建売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    if (obj.repayMethod === '1') {
      this.positionDetailList = [] // 返済建玉明細
      for (let i = 0; i < obj.repayPositionDetailList.length; i++) {
        this.positionDetailList.push({
          parentStockTradeDate: obj.repayPositionDetailList[i].parentStockTradeDate, // 返済建玉明細.親株新規約定日
          constructionDate: obj.repayPositionDetailList[i].constructionDate, // 返済建玉明細.新規約定日
          newPrice: obj.repayPositionDetailList[i].newPrice, // 返済建玉明細.新規単価
          quantity: obj.repayPositionDetailList[i].orderQuantity, // 返済建玉明細.注文株数
          market: obj.repayPositionDetailList[i].builtMarket // 返済建玉明細.建市場
        })
      }
    }
    this.repayMethod = obj.repayMethod ? obj.repayMethod : '' // 返済方法
    if (obj.repayMethod === '0' || obj.repayMethod === '1') this.repaymentOrder = obj.repaymentOrder ? obj.repaymentOrder : '' // 返済順序
    if (obj.repayMethod === '0') this.totalQuantity = obj.totalQuantity ? obj.totalQuantity : '' // 合計数量
  }
}
