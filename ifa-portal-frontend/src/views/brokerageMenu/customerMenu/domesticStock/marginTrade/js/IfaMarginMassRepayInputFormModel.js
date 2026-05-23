export class IfaMarginMassRepayInputFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0212-05_1',
      name: '信用一括返済入力'
    }
    /** 返済方法 */
    this.repayMethod = '0'
    /** 返済順序 */
    this.repaymentOrder = '61'
    /** 合計数量 */
    this.totalQuantity = ''
    /** 銘柄コード */
    this.brandCode = ''
    /** 銘柄名 */
    this.brandName = ''
    /** 売買単位 */
    this.unit = ''
    /** 新規建売買区分 */
    this.newCreditOrderType = ''
    /** 弁済期限 */
    this.paymentDeadline = ''
    /** 建玉件数 */
    this.positionCount = ''
    /** 建玉件数（東証） */
    this.positionCountTokyoSecurity = ''
    /** 建玉件数（PTS） */
    this.positionCountPts = ''
    /** 東証明細.建玉金額合計 */
    this.tokyoSecurityDetailTotalPrice = ''
    /** 東証明細.評価額合計（前日） */
    this.tokyoSecurityDetailValuationTotalPreviousDay = ''
    /** 東証明細.評価額合計（リアル） */
    this.tokyoSecurityDetailValuationTotalReal = ''
    /** 東証明細.諸経費合計 */
    this.tokyoSecurityDetailCostTotalYen = ''
    /** 東証明細.評価損益合計（前日） */
    this.tokyoSecurityDetailDomesticPositionValuationTotalPreviousDay = ''
    /** 東証明細.評価損益合計（リアル） */
    this.tokyoSecurityDetailDomesticPositionValuationTotalReal = ''
    /** PTS明細.建玉金額合計 */
    this.ptsDetailTotalPrice = ''
    /** PTS明細.評価額合計（前日） */
    this.ptsDetailValuationTotalPreviousDay = ''
    /** PTS明細.評価額合計（リアル） */
    this.ptsDetailValuationTotalReal = ''
    /** PTS明細.諸経費合計 */
    this.ptsDetailCostTotalYen = ''
    /** PTS明細.評価損益合計（前日） */
    this.ptsDetailDomesticPositionValuationTotalPreviousDay = ''
    /** PTS明細.評価損益合計（リアル） */
    this.ptsDetailDomesticPositionValuationTotalReal = ''
    /** 注文可能株数 */
    this.orderAbleStockQuantity = ''
    /** 建玉明細 */
    this.positionDetailList = [
      {
        market: '',
        constructionDate: '',
        lastTradeDate: '',
        dueDateShortenClassification: '',
        parentStockTradeDate: '',
        accountType: '',
        security: '',
        securityClassification: '',
        contPositionTotal: '',
        unactualQuantity: '',
        newPrice: '',
        dayBeforeValuationPrice: '',
        currentPriceReal: '',
        openInterestAmount: '',
        valuationPreviousDay: '',
        valuationReal: '',
        cost: '',
        profitAndLossPrevDay: '',
        profitAndLossReal: '',
        cashBond: '',
        quantity: '',
        newOpenInterestNumber: ''
      }
    ]
  }
}
