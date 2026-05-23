export class IfaRealEntrustDepositRateDomesticFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_010302-03',
      name: 'リアル委託保証金率'
    }
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.todayBase = {
      entrustDepositRate: '', // 委託保証金率
      entrustDepositRateType: '', // 委託保証金率区分
      marginDepositCache: '', // 委託保証金現金
      alternativeSecuritiesTotal: '', // 代用有価証券評価額合計
      totalOpenInterestValuationLoss: '', // 建玉評価損合計
      totalSettlementProfitLoss: '', // 決済損益合計
      totalExpensesPaid: '', // 支払諸経費等合計
      currencyActualDeposit: '', // 実質保証金
      constructionPriceTotal: '', // 建代金合計
      referenceMarginDeposit: '', // 参考委託保証金率
      referenceMarginDepositType: '', // 参考委託保証金率区分
      hybridDepositBalance: '' // SBIハイブリッド預金残高
    } // 当日基準
    this.nextBusinessDayBase = {
      entrustDepositRate: '', // 委託保証金率
      entrustDepositRateType: '', // 委託保証金率区分
      marginDepositCache: '', // 委託保証金現金
      alternativeSecuritiesTotal: '', // 代用有価証券評価額合計
      totalOpenInterestValuationLoss: '', // 建玉評価損合計
      totalSettlementProfitLoss: '', // 決済損益合計
      totalExpensesPaid: '', // 支払諸経費等合計
      currencyActualDeposit: '', // 実質保証金
      constructionPriceTotal: '', // 建代金合計
      referenceMarginDeposit: '', // 参考委託保証金率
      referenceMarginDepositType: '', // 参考委託保証金率区分
      hybridDepositBalance: '' // SBIハイブリッド預金残高
    } // 翌営業日基準
    this.markToBase = {
      entrustDepositRate: '', // 委託保証金率
      entrustDepositRateType: '', // 委託保証金率区分
      marginDepositCache: '', // 委託保証金現金
      alternativeSecuritiesTotal: '', // 代用有価証券評価額合計
      totalOpenInterestValuationLoss: '', // 建玉評価損合計
      totalSettlementProfitLoss: '', // 決済損益合計
      totalExpensesPaid: '', // 支払諸経費等合計
      currencyActualDeposit: '', // 実質保証金
      constructionPriceTotal: '', // 建代金合計
      referenceMarginDeposit: '', // 参考委託保証金率
      referenceMarginDepositType: '', // 参考委託保証金率区分
      hybridDepositBalance: '' // SBIハイブリッド預金残高
    } // 値洗い基準
  }
}
