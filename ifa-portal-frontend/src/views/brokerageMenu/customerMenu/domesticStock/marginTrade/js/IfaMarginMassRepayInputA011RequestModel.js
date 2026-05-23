import Logger from '@/utils/ifaLog.js'
export class IfaMarginMassRepayInputA011RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.newCreditOrderType = obj.newCreditOrderType ? obj.newCreditOrderType : '' // 新規建売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    if (obj.repayMethod === '1') {
      this.positionDetailList = [] // 返済建玉明細
      for (let i = 0; i < obj.positionDetailList.length; i++) {
        this.positionDetailList.push({
          parentStockTradeDate: obj.positionDetailList[i].parentStockTradeDate, // 返済建玉明細.親株新規約定日
          constructionDate: obj.positionDetailList[i].constructionDate, // 返済建玉明細.新規約定日
          newPrice: obj.positionDetailList[i].newPrice, // 返済建玉明細.新規単価
          quantity: obj.positionDetailList[i].quantity, // 返済建玉明細.注文株数
          market: obj.positionDetailList[i].market // 返済建玉明細.建市場
        })
      }
    }
    this.repayMethod = obj.repayMethod ? obj.repayMethod : '' // 返済方法
    if (obj.repayMethod === '0' || obj.repayMethod === '1') this.repaymentOrder = obj.repaymentOrder ? obj.repaymentOrder : '' // 返済順序
    if (obj.repayMethod === '0') this.totalQuantity = obj.totalQuantity ? obj.totalQuantity : '' // 合計数量
  }
}
