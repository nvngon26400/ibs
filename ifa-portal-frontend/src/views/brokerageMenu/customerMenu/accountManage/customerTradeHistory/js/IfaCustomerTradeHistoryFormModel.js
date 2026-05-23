export class IfaCustomerTradeHistoryFormModel {
  constructor() {
    this.screenTitle = {
      id: 'SUB0202_0109-01',
      name: '取引履歴'
    }
    
    this.customerCode = '' // 顧客ID
    this.companyCode = '' // 会社コード
    this.times = '' // 回数
    this.issue = '' // 号
    this.securityType = '$NULL' // 商品区分
    this.tradeType = '$NULL' // 取引区分
    this.sortOrderItem = '0' // 並び順指定【項目】
    this.sortOrderProfile = '0' // 並び順指定【属性】
    this.period = [] // 期間指定

    this.listDisplayMode = '2' // 一覧表示モード
    this.transferDisplayMode = '1' // 振替表示モード
    
    // 取引履歴明細
    this.tradeHistoryList = [
      // {
      //   tradeDate: '', // 約定日
      //   deliveryDate: '', // 受渡日
      //   securityType: '', // 商品区分
      //   brandCdode: '', // 銘柄コード
      //   brandName: '', // 銘柄名
      //   detailsTrade: '', // 内訳取引
      //   detailsType: '', // 内訳区分
      //   depositTax: '', // 預り/税
      //   tradeMarketReason: '', // 取引市場/理由
      //   price: '', // 単価
      //   openPrice: '', // 取得単価
      //   acquireDate: '', // 取得日
      //   currency: '', // 通貨
      //   fx: '', // 為替
      //   quantity: '', // 数量
      //   fee: '', // 手数料
      //   consumptionTax: '', // 消費税
      //   accruedInterest: '', // 経過利子
      //   realProfitAndLoss: '', // 実現損益
      //   capitalGainTax: '', // 譲渡益税/源泉税
      //   accurateAmount: '', // 精算金額
      //   cancelFlg: '' // 取消区分
      // }
    ]

  }
}
