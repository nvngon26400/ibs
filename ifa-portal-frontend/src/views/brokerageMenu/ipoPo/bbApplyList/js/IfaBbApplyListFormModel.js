export class IfaBbApplyListFormModel {
  constructor() {
    this.chkBrokerCodeExclude = '' // 仲介業者除外
    this.brokerCode = '' // 仲介業者コード
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名（漢字／カナ）
    this.customerNameKanjiKanaTerms = '' // 顧客名（漢字／カナ）_条件
    this.courseSelected = [] // selected取引コース
    this.course = ''
    this.brandCode = '' // 銘柄コード 【初期値】""
    this.lotteryResult = '' // 抽選結果 【初期値】未選択
    this.orderStatus = '' // 注文状況 【初期値】未選択
    this.historyInclude = '' // 過去の申込も表示する 【初期値】"0":チェックなし
    this.empCodeAutoDispFlag = '' // 営業員自動判定コード
    this.bbApplyList = [ // BB申込一覧
      {
        brandCode12: '', // BB申込一覧.銘柄コード
        brandName: '', // BB申込一覧.銘柄名
        brokerCode: '', // BB申込一覧.仲介業者コード
        brokerName: '', // BB申込一覧.仲介業者名
        brokerageBranchCode: '', // BB申込一覧.支店コード
        brokerBranchName: '', // BB申込一覧.支店名
        brokerChargeCode: '', // BB申込一覧.営業員コード
        employeeName: '', // BB申込一覧.営業員名
        butenCode: '', // BB申込一覧.部店
        accountNumber: '', // BB申込一覧.口座番号
        customerNameKanji: '', // BB申込一覧.顧客名(漢字)
        customerNameKana: '', // BB申込一覧.顧客名(カナ)
        course: '', // BB申込一覧.取引コース
        investorAttributeName: '', // BB申込一覧.投資家属性
        bbQuantity: '', // BB申込一覧.BB申込株数
        bbPrice: '', // BB申込一覧.申込価格
        quantitySairyou: '', // BB申込一覧.裁量希望株数
        bbQuantityAlloc: '', // BB申込一覧.当選株数
        lotteryResult: '', // BB申込一覧.抽選結果
        orderStatus: '', // BB申込一覧.注文状況
        orderQuantity: '', // BB申込一覧.注文株数
        depositType: '', // BB申込一覧.預り区分
        kanyuKbn: '', // BB申込一覧.勧誘区分
        warningApplyArranged: '', // BB申込一覧.ワーニング申請済
        bbCreateDate: '', // BB申込一覧.申込日時
        bbCreateUserName: '', // BB申込一覧.申込者
        sectionName: '', // BB申込一覧.セクション名
        edelivAgreementDate: '', // BB申込一覧.電子交付同意
        readTime: '', // BB申込一覧.目論見書閲覧
        bbRemark: '', // BB申込一覧.備考
        bookBuildingPresentationFrom: '', // BB申込一覧.ブックビルディング申込期間（開始）
        detailNumber: '' // BB申込一覧.明細番号
      }
    ]
  }
}
