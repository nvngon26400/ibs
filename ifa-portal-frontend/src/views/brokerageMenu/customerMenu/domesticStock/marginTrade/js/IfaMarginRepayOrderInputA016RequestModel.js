import Logger from '@/utils/ifaLog.js'
export class IfaMarginRepayOrderInputA016RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.orderMarket = obj.selectedMarket ? obj.selectedMarket : '' // 発注市場
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.periodTerms = obj.periodTerms ? obj.periodTerms : '' // 期間.期間条件
    this.limit = obj.limit ? obj.limit : '' // 期間.日付
    this.repayMethod = obj.repayMethod ? obj.repayMethod : '' // 返済方法
    this.repaymentOrder = obj.repaymentOrder ? obj.repaymentOrder : '' // 返済順序
    if (obj.repayMethod === '1' || obj.repayMethod === '2') {
      this.repayPositionCount = obj.repayMethod === '2' ? '1' : (obj.repayPositionDetailList.length ? String(obj.repayPositionDetailList.length) : []) // 返済建玉カウント
      this.repayPositionDetail = [] // 返済建玉明細
      for (let i = 0; i < obj.repayPositionDetailList.length; i++) {
        this.repayPositionDetail.push({
          parentStockTradeDate: obj.repayPositionDetailList[i].parentStockTradeDate, // 返済建玉明細.親株新規約定日
          constructionDate: obj.repayPositionDetailList[i].constructionDate, // 返済建玉明細.新規約定日
          newPrice: obj.repayPositionDetailList[i].newPrice, // 返済建玉明細.新規単価
          orderQuantity: obj.repayMethod === '2' ? (obj.quantity ? obj.quantity : '') : obj.repayPositionDetailList[i].orderQuantity, // 返済建玉明細.株数
          builtMarket: obj.repayPositionDetailList[i].builtMarket, // 返済建玉明細.建市場
          positionNo: obj.repayPositionDetailList[i].positionNo, // 返済建玉明細.建玉No
          contPosition: obj.repayPositionDetailList[i].contPosition, // 返済建玉明細.残高数量
          cost: obj.repayPositionDetailList[i].cost, // 返済建玉明細.諸経費
          unactualQuantity: obj.repayPositionDetailList[i].unactualQuantity, // 返済建玉明細.返済注文済未出来数量
          specificPositionType: obj.repayPositionDetailList[i].specificPositionType // 返済建玉明細.特定建玉区分
        })
      }
    }
    if (obj.repayMethod === '2') {
      this.quantity = obj.quantity ? obj.quantity : ''
    } else if (obj.repayMethod === '1') {
      this.quantity = String(this.repayPositionDetail.map(obj => Number(obj.orderQuantity)).reduce((prev, currentValue) => prev + currentValue, 0))
    } else {
      this.quantity = obj.totalQuantity ? obj.totalQuantity : ''
    }

    this.orderKind = obj.orderKind ? obj.orderKind : '' // 注文種別
    if (obj.orderKind === '2') {
      this.oco1SasinariHouhou = obj.oco1SasinariHouhou ? obj.oco1SasinariHouhou : '' // OCO1.執行方法
      this.oco1SasinariJyouken = obj.oco1SasinariJyouken ? obj.oco1SasinariJyouken : '' // OCO1.執行条件
      this.oco1Price = obj.oco1Price ? obj.oco1Price : '' // OCO1.注文単価
      this.oco2TriggerPrice = obj.oco2TriggerPrice ? obj.oco2TriggerPrice : '' // OCO2.発火条件価格（逆指値）
      this.oco2GyakusasiHouhou = obj.oco2GyakusasiHouhou ? obj.oco2GyakusasiHouhou : '' // OCO2.執行方法（逆指値）
      this.oco2GyakusasiJyouken = obj.oco2GyakusasiJyouken ? obj.oco2GyakusasiJyouken : '' // OCO2.執行条件（逆指値）
      this.oco2Price = obj.oco2Price && obj.oco2GyakusasiHouhou === '1' ? obj.oco2Price : '' // OCO2.注文単価
    } else {
      this.sasinariHouhou = obj.sasinariHouhou ? obj.sasinariHouhou : '' // 通常/逆指値.執行方法
      this.sasinariJyouken = obj.sasinariJyouken ? obj.sasinariJyouken : '' // 通常/逆指値.執行条件
      if (obj.sasinariHouhou === '3') {
        this.triggerPrice = obj.triggerPrice ? obj.triggerPrice : '' // 通常/逆指値.発火条件価格（逆指値）
        this.gyakusasiHouhou = obj.gyakusasiHouhou ? obj.gyakusasiHouhou : '' // 通常/逆指値.執行方法（逆指値）
      }
      if (obj.sasinariHouhou === '1') {
        this.price = obj.price ? obj.price : '' // 通常/逆指値.注文単価
      } else if (obj.sasinariHouhou === '3' && obj.gyakusasiHouhou === '1') {
        this.price = obj.price ? obj.price : '' // 通常/逆指値.注文単価
      }
    }
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.orderMethod = obj.orderMethod ? obj.orderMethod : '' // 受注方法
    this.checkInsider = obj.checkInsider ? obj.checkInsider : '' // 確認項目.インサイダー確認
    this.checkSor = obj.checkSor ? obj.checkSor : '' // 確認項目.SOR確認
    this.openTradeKbn = obj.tradeCd === '5' ? '1' : '0' // 新規売買区分
    this.paymentDeadlineCalculation = obj.paymentDeadlineCalculation ? obj.paymentDeadlineCalculation : '' // 弁済期限（算出）
  }
}
