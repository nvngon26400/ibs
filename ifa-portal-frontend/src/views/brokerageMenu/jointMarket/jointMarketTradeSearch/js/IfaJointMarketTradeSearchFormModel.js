export class IfaJointMarketTradeSearchFormModel {
  constructor() {
    this.title = {
      id: 'SUB0208_01-01',
      name: '共同店舗　取引検索'
    }
    this.comment = ''
    this.brokerCode = '' // 仲介業者コード
    this.chkBrokerCodeExclude = '' // 仲介業者除外
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名
    this.customerNameKanjiKanaTerms = '3' // 顧客名検索オプション
    this.courseSelected = '' // 取引コース
    this.period = [] // 期間指定
    this.periodDateFrom = '' // 期間指定_入力 【初期値】当日日付 （From,Toどちらも）
    this.periodDateTo = ''
    this.securityClass = [] // 証券種別_入力 【初期値】全選択
    this.brandCode12 = '' // 銘柄コード_入力 【初期値】""
    // 取引検索一覧
    this.jointMarketTradeSearchList = [
      {
        sumDateYmd: '', // 対象年月日
        brokerCode: '', // 取引検索一覧.仲介業者コード
        brokerName: '', // 取引検索一覧.仲介業者名
        buten: '', // 取引検索一覧.部店
        accountNumber: '', // 取引検索一覧.口座番号
        customerAttribute: '', // コースコード
        courseName: '', // 取引検索一覧.取引コース
        customerNameKanji: '', // 取引検索一覧.顧客名（漢字）
        customerNameKana: '', // 取引検索一覧.顧客名（カナ）
        branchCode: '', // 取引検索一覧.支店コード
        branchName: '', // 取引検索一覧.支店名
        securiytClass: '', // 取引検索一覧.証券種別
        brandCode: '', // 取引検索一覧.銘柄コード
        brandName: '', // 取引検索一覧.銘柄名
        productRank: '', //  取引検索一覧.商品ランク
        tradeTypeName: '', // 取引検索一覧.取引種別名
        tradeDate: '', // 取引検索一覧.約定日
        settlementDate: '', // 取引検索一覧.受渡日
        price: '', // 取引検索一覧.単価
        quantity: '', // 取引検索一覧.数量
        contractAmountJpyAmount: '', // 取引検索一覧.約定金額（円貨）
        yenFee: '', // 取引検索一覧.手数料（円貨）
        yenDeliveryAmount: '', // 取引検索一覧.受渡金額（円貨）
        currency: '', // 取引検索一覧.通貨
        fxRate: '', // 取引検索一覧.為替レート
        contractAmountForeign: '', // 取引検索一覧.約定金額（外貨）
        foreiforeignDeliveryAmountgnNetamount: '', // 取引検索一覧.受渡金額（外貨）
        jointRate: '', // 取引検索一覧.共募報酬率
        jointRewardPrice: '', // 取引検索一覧.支払額
        empCode: '', // 信託報酬一覧.営業員コード
        brokerChargeName: '', // 信託報酬一覧.営業員名
        bondIntermediary: '', // 信託報酬一覧.債券　媒介/非媒介
        usStockStoreEntrust: '', // 信託報酬一覧.米国株　店頭/委託
        depositName: '' // 預り区分
      }
    ]
  }
}
