export class IfaKnockInBrandHoldingListFormModel {
  constructor() {
    this.title = {
      id: 'SUB020301_03-02',
      name: 'ノックイン銘柄保有一覧'
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

    this.knockInBrandHoldingList = [{ // ノックイン銘柄保有一覧
      butenCode: '', // ノックイン銘柄保有一覧.部店
      accountNumber: '', // ノックイン銘柄保有一覧.口座番号
      customerAttribute: '', // ノックイン銘柄保有一覧.取引コース
      customerNameKanji: '', // ノックイン銘柄保有一覧.顧客名(漢字)
      customerNameKana: '', // ノックイン銘柄保有一覧.顧客名(カナ)
      dealerNumber: '', // ノックイン銘柄保有一覧.扱者コード
      brokerCode: '', // ノックイン銘柄保有一覧.仲介業者コード
      brokerName: '', // ノックイン銘柄保有一覧.仲介業者名
      brokerageBranchCode: '', // ノックイン銘柄保有一覧.支店コード
      brokerBranchName: '', // ノックイン銘柄保有一覧.支店名
      brokerChargeCode: '', // ノックイン銘柄保有一覧.営業員コード
      brokerChargeName: '', // ノックイン銘柄保有一覧.営業員名
      sbmBondCode: '', // ノックイン銘柄保有一覧.銘柄コード
      brandName: '', // ノックイン銘柄保有一覧.銘柄名
      holdingQuantity: '', // ノックイン銘柄保有一覧.保有数量
      judgmentBrandName: '', // ノックイン銘柄保有一覧.判定銘柄
      price: '', // ノックイン銘柄保有一覧.時価
      knockInLevelPrice: '', // ノックイン銘柄保有一覧.ノックイン水準価格
      knockinDate: '', // ノックイン銘柄保有一覧.ノックイン事由発生日
      pdfNoticeUrl: '' // PDF通知URL（hidden）
    }]
  }
}
