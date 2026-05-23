export class IfaOrderListFormModel {
  constructor() {
    this.titleModel = {
      id: 'SUB020302_0101-01',
      name: '注文一覧'
    }
    this.chkBrokerCodeExclude = ''
    this.brokerCode = ''
    this.branchCode = ''
    this.empCode = ''
    this.butenCode = ''
    this.accountNumber = ''
    this.customerNameKanjiKana = ''
    this.customerNameKanjiKanaTerms = ''
    this.courseSelected = []
    this.orderSelected = [] // 商品_入力 【初期値】全選択
    this.brandCode = '' // 銘柄コード_入力 【初期値】""
    this.period = []
    this.periodYmFrom = '' // 期間指定_入力 【初期値】当日日付 （From,Toどちらも）
    this.periodYmTo = ''

    this.securityType = '' // 商品区分（hidden）
    this.ecOrderNo = '' // EC受注番号_国内株式（hidden）
    this.tradeCd = '' // 取引種別_国内株式（hidden）
    this.ecOrderNo = '' // EC受注番号_国内投信（hidden）
    this.hiddenBrandCode = '' // 銘柄コード_募集（hidden）
    this.depositType = '' // 預り区分_募集（hidden）
    this.acceptOrderNumber = '' // 注文番号_外株委託（hidden）
    this.acceptOrderSubNumber = '' // 注文SUB番号_外株委託（hidden）
    this.manageNumber = '' // 管理番号_店頭取引（hidden）
    this.ifaOrderNo = '' // IFA注文番号_投信（hidden）
    this.ifaOrderSubNo = '' // IFA注文サブ番号_投信（hidden）

    this.orderListComment = ''

    this.domesticStockList = []

    this.domesticMutualFundlist = []

    this.domesticMutualFundRegularRecruitmentList = []

    this.subscriptOrderList = []

    this.foreignStockEntrustOrderList = []

    this.foreignStockStoreOrderList = []
    this.empCodeAutoDispFlag = ''
  }
}
