export class IfaKnockOutBrandHoldingListFormModel {
  constructor() {
    this.title = {
      id: 'SUB020301_03-03',
      name: 'ノックアウト銘柄保有一覧'
    }

    this.chkBrokerCodeExclude = '' // 仲介業者除外
    this.brokerCode = '' // 仲介業者コード
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名（漢字／カナ）
    this.customerNameKanjiKanaTerms = '3' // 顧客名（漢字／カナ）_条件
    this.courseSelected = [] // selected取引コース

    this.knockOutBrandHoldingList = [{ // ノックアウト銘柄保有一覧
      butenCode: '', // ノックアウト銘柄保有一覧.部店
      accountNumber: '', // ノックアウト銘柄保有一覧.口座番号
      customerAttribute: '', // ノックアウト銘柄保有一覧.取引コース
      customerNameKanji: '', // ノックアウト銘柄保有一覧.顧客名(漢字)
      customerNameKana: '', // ノックアウト銘柄保有一覧.顧客名(カナ)
      dealerNumber: '', // ノックアウト銘柄保有一覧.扱者コード
      brokerCode: '', // ノックアウト銘柄保有一覧.仲介業者コード
      brokerName: '', // ノックアウト銘柄保有一覧.仲介業者名
      brokerageBranchCode: '', // ノックアウト銘柄保有一覧.支店コード
      brokerBranchName: '', // ノックアウト銘柄保有一覧.支店名
      brokerChargeCode: '', // ノックアウト銘柄保有一覧.営業員コード
      brokerChargeName: '', // ノックアウト銘柄保有一覧.営業員名
      sbmBondCode: '', // ノックアウト銘柄保有一覧.銘柄コード
      brandName: '', // ノックアウト銘柄保有一覧.銘柄名
      holdingQuantity: '', // ノックアウト銘柄保有一覧.保有数量
      judgmentBrandName: '', // ノックアウト銘柄保有一覧.判定銘柄
      price: '', // ノックアウト銘柄保有一覧.時価
      earlyRedemptionLevelPrice: '', // ノックアウト銘柄保有一覧.早期償還水準価格
      earlyRedemptionDeterminationDate: '' // ノックアウト銘柄保有一覧.早期償還発生日
    }]
    this.pdfNoticeUrl = '' // PDF通知URL（hidden）
  }
}
