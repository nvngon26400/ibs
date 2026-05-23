import Logger from '@/utils/ifaLog.js'
export class IfaMarginRepayOrderInputA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.orderMarket = obj.selectedMarket ? obj.selectedMarket : '' // 発注市場
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    if (Array.isArray(obj.repayPositionDetailList) && obj.repayPositionDetailList.length) {
      this.repayPositionDetail = [] // 返済建玉明細
      for (let i = 0; i < obj.repayPositionDetailList.length; i++) {
        this.repayPositionDetail.push({
          parentStockTradeDate: obj.repayPositionDetailList[i].parentStockTradeDate, // 返済建玉明細.親株新規約定日
          constructionDate: obj.repayPositionDetailList[i].constructionDate, // 返済建玉明細.新規約定日
          newPrice: obj.repayPositionDetailList[i].newPrice, // 返済建玉明細.新規単価
          orderQuantity: obj.repayPositionDetailList[i].orderQuantity, // 返済建玉明細.株数
          builtMarket: obj.repayPositionDetailList[i].builtMarket, // 返済建玉明細.建市場
          positionNo: obj.repayPositionDetailList[i].positionNo, // 返済建玉明細.建玉No
          contPosition: obj.repayPositionDetailList[i].contPosition, // 返済建玉明細.残高数量
          cost: obj.repayPositionDetailList[i].cost, // 返済建玉明細.諸経費
          rightType: obj.repayPositionDetailList[i].rightType // 返済建玉明細.権利区分
        })
      }
    }
    this.ctNightBatchFinishFlag = obj.ctNightBatchFinishFlag ? obj.ctNightBatchFinishFlag : '' // CT夜間バッチ終了フラグ
  }
}
