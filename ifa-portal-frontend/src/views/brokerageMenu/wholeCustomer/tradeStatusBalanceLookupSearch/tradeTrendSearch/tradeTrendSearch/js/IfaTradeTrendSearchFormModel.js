export class IfaTradeTrendSearchFormModel {
  constructor() {
    this.title = {
      id: 'SUB020302_0401-01',
      name: '取引動向検索'
    }
    this.chkBrokerCodeExclude = '' // 仲介業者除外
    this.brokerCode = '' // 仲介業者コード
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.courseSelected = [] // selected取引コース

    this.countingUnit = '3' // 集計単位_入力 【初期値】'3':顧客
    this.visibleButenCode = '' // 閲覧可能部店
    this.securityClassList = [] // 証券種別_入力 【初期値】全選択
    this.periodMonth = [] // 期間指定）_入力 【初期値】当月（From,Toどちらも）

    // 取引動向検索一覧
    this.tradeTrendSearchList = [
      {
        brokerCode: '', // 仲介業者コード
        brokerName: '', // 仲介業者名
        brokerChargeName: '', // 営業員名
        butenCode: '', // 部店コード
        accountNumber: '', // 口座番号
        nameKanji: '', // 顧客名
        age: '', // 年齢
        tcCompRank: '', // Cランク
        customerAttribute: '', // 取引コース
        occupation: '', // 職業区分
        investmentPlan: '', // 投資方針
        fundCharacter: '', // 資金性格
        emloymentPeriod: '', // 運用期間
        incomeForm: '', // 収入区分
        annualIncome: '', // 年収
        financialAssets: '', // 金融資産
        uaiExpStock: '', // 投資経験年数（株式現物）
        uaiExpMargin: '', // 投資経験年数（信用）
        uaiExpOtherfund: '', // 投資経験年数（その他投信）
        uaiExpForeign: '', // 投資経験年数（外国証券）
        empCode: '', // 営業員コード
        dealerNumber: '', // 扱者コード
        brokerBranchCode: '', // 支店コード
        branchName: '', // 支店名
        visibleButenCode: '', // 閲覧可能部店
        tradeCount: '', // 取引回数
        adjustmentTradeCount: '', // 調整後回数
        grossAmountYen: '', // 約定総金額
        assets: '', // 預り資産
        grossAmountYenAssets: '', // 資金回転率
        fee: '', // 手数料
        rewardPrice: '', // 担当者手数料
        feeRewardPrice: '', // 手数料依存率
        currenctPrice: '', // 評価額
        profitAndLoss: '', // 評価損益
        profitAndLossCurrenctPrice: '', // 評価損益率
        currenctPriceMonth: '', // 前月比評価損
        yearRealProfitAndLoss: '', // 年間実現損益
        monthlyRealProfitAndLoss: '', // 当月実現損益
        periodRealProfitAndLoss: '' // 期間指定実現損益
      }
    ]
  }
}
