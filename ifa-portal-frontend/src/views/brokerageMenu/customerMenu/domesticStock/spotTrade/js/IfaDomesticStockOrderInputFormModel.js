export class IfaDomesticStockOrderInputFormModel {
  constructor() {
    this.screenId = 'SUB0202_0208-01_1' // 画面ID
    this.title = '国内株式注文入力' // 画面名
    this.selectedMarket = '' // A004RequestModel用市場
    this.tradeCd = '' // 取引種別
    this.brandCode = '' // 銘柄コード
    this.depositType = '' // 預り区分
    this.acPosition = '' // 売却可能数量
    this.buyingPower = {
      deliveryDateT2: '', // 買付余力.受渡日(T+2)
      deliveryDateT3: '', // 買付余力.受渡日(T+3)
      wholeAccountT2: '', // 買付余力.総合口座（T+2）
      wholeAccountT3: '', // 買付余力.総合口座（T+3
      jrNisaAccountStatusT2: '', // 買付余力.JrNISA口座（T+2）
      jrNisaAccountStatusT3: '', // 買付余力.JrNISA口座（T+3
      nisaBuy: '' // 買付余力.NISA買付可能枠
    } // 買付余力
    this.businessDayList = [] // 営業日リスト
    this.market = '0' // 市場
    this.brandCode = '' // 銘柄コード
    this.account = '' // 口座
    this.orderKind = '1' // 注文種別
    this.quantity = '' // 数量
    this.normalPriceLimitReverse = {
      sasinariHouhou: '1', // 通常/逆指値.執行方法
      sasinariJyouken: ' ', // 通常/逆指値.執行条件
      triggerPrice: '', // 通常/逆指値.発火条件価格（逆指値）
      gyakusasiHouhou: '1', // 通常/逆指値.執行方法（逆指値）
      price: '' // 通常/逆指値.注文単価
    } // 通常/逆指値
    this.oco1 = {
      sasinariHouhou: '1', // OCO1.執行方法
      sasinariJyouken: ' ', // OCO1.執行条件
      price: '' // OCO1.注文単価
    } // OCO1
    this.oco2 = {
      sasinariHouhou: '3', // OCO2.執行方法
      triggerPrice: '', // OCO2.発火条件価格（逆指値）
      gyakusasiHouhou: '1', // OCO2.執行方法（逆指値）
      gyakusasiJyouken: ' ', // OCO2.執行条件（逆指値）
      price: '' // OCO2.注文単価
    } // OCO2
    this.ifd1 = {
      sasinariHouhou: '1', // IFD1.執行方法
      sasinariJyouken: ' ', // IFD1.執行条件
      triggerPrice: '', // IFD1.発火条件価格（逆指値）
      gyakusasiHouhou: '1', // IFD1.執行方法（逆指値）
      price: '' // IFD1.注文単価
    } // IFD1
    this.ifd2 = {
      period: {
        periodTerms: null, // IFD2.期間.期間条件
        limit: '' // IFD2.期間.日付
      }, // 期間
      sasinariHouhou: '1', // IFD2.執行方法
      sasinariJyouken: ' ', // IFD2.執行条件
      triggerPrice: '', // IFD2.発火条件価格（逆指値）
      gyakusasiHouhou: '1', // IFD2.執行方法（逆指値）
      price: '' // IFD2.注文単価
    } // IFD2
    this.kanyuKbn = '' // 勧誘区分
    this.receiveOrderType = '' // 受注方法
    this.confirmItem = {
      insider: false, // 確認項目.インサイダー確認
      sor: false // 確認項目.SOR確認
    } // 確認項目
    this.period = {
      periodTerms: null, // 期間.期間条件
      limit: '' // 期間.日付
    } // 期間
  }
}
