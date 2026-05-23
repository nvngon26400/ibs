import Logger from '@/utils/ifaLog.js'
export class IfaMarginRepayOrderInputA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.openTradeKbn = obj.openTradeKbn ? obj.openTradeKbn : '' // 新規売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    if (obj.repayMethod === '1' || obj.repayMethod === '2') {
      this.repayPositionDetail = [] // 返済建玉明細
      let detailIndex = 0
      for (let i = 0; i < obj.positionDetailList.length; i++) {
        // 注文株数が入力されている場合のみ返済建玉明細にセット
        if (obj.positionDetailList[i].quantity !== null && obj.positionDetailList[i].quantity !== '' && obj.positionDetailList[i].quantity !== '0') {
          this.repayPositionDetail.push({
            parentStockTradeDate: obj.positionDetailList[i].parentStockTradeDate, // 返済建玉明細.親株新規約定日
            constructionDate: obj.positionDetailList[i].constructionDate, // 返済建玉明細.新規約定日
            newPrice: obj.positionDetailList[i].newPrice, // 返済建玉明細.新規単価
            builtMarket: obj.positionDetailList[i].market // 返済建玉明細.建市場
          })
          if (obj.repayMethod === '1') {
            this.repayPositionDetail[detailIndex].orderQuantity = obj.positionDetailList[i].quantity // 返済建玉明細.株数
          }
          detailIndex += 1
        }
      }
    }
    this.repayMethod = obj.repayMethod ? obj.repayMethod : '' // 返済方法
    if (obj.repayMethod === '0' || obj.repayMethod === '1') {
      this.repaymentOrder = obj.repaymentOrder ? obj.repaymentOrder : '' // 返済順序
      if (obj.repayMethod === '0') {
        this.totalQuantity = obj.totalQuantity // 合計数量
      }
    }
  }
}
