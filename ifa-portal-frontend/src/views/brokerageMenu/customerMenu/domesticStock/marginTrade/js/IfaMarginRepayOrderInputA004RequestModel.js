import Logger from '@/utils/ifaLog.js'
export class IfaMarginRepayOrderInputA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.openTradeKbn = obj.tradeCd === '5' ? '1' : '0' // 新規売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    if (obj.repayMethod === '1' || obj.repayMethod === '2') {
      this.repayPositionDetail = [] // 返済建玉明細
      for (let i = 0; i < obj.repayPositionDetailList.length; i++) {
        if (obj.repayMethod === '1' || obj.repayMethod === '2') {
          this.repayPositionDetail.push({
            parentStockTradeDate: obj.repayPositionDetailList[i].parentStockTradeDate, // 返済建玉明細.親株新規約定日
            constructionDate: obj.repayPositionDetailList[i].constructionDate, // 返済建玉明細.新規約定日
            newPrice: obj.repayPositionDetailList[i].newPrice, // 返済建玉明細.新規単価
            orderQuantity: obj.repayMethod === '1' ? obj.repayPositionDetailList[i].orderQuantity : '', // 返済建玉明細.株数
            builtMarket: obj.repayPositionDetailList[i].builtMarket, // 返済建玉明細.建市場
            positionNo: obj.repayPositionDetailList[i].positionNo // 返済建玉明細.建玉No
          })
        }
      }
    }
    this.repayMethod = obj.repayMethod ? obj.repayMethod : '' // 返済方法
    if (obj.repayMethod === '0' || obj.repayMethod === '1') {
      this.repaymentOrder = obj.repaymentOrder ? obj.repaymentOrder : '' // 返済順序
      this.totalQuantity = obj.repayMethod === '0' ? (obj.totalQuantity ? obj.totalQuantity : '') : '' // 合計数量
    }
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.orderMarket = obj.selectedMarket ? obj.selectedMarket : '' // 発注市場
  }
}
