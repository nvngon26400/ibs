export class IfaCustomerListFormModel {
  constructor() {
    this.screenId = 'SUB0201_01-01' // 画面ID
    this.screenTitle = '顧客一覧' // 画面タイトル
    this.ifaCustomerListComment = [] // コメント
    this.complianceLankFrom = '' // コンプラランク（From）
    this.complianceLankTo = '' // コンプラランク（To）
    this.tradeRestrictionCheckbox = '' // 取引制限ありチェックボックス 【初期値】OFF
    this.adressDisplay = '' // 住所表示（チェック） 【初期値】OFF
    this.adress = '' // 住所
    this.adressConditionList = '3' // 住所　（条件リスト） 【初期値】を含む
    this.phoneNumberDisplay = '' // 電話番号表示（チェック） 【初期値】OFF
    this.customerListPhoneNumber = '' // 電話番号
    this.ageDisplay = '' // 年齢表示（チェック） 【初期値】OFF
    this.ageFrom = '' // 年齢（From）
    this.ageTo = '' // 年齢（To）
    this.BirthDateDisplay = '' // 生年月日表示（チェック） 【初期値】OFF
    this.BirthDateCalender = [] // 生年月日（FromToカレンダー）
    this.BirthDate = []
    this.BirthDateFrom = '' // 生年月日（From）
    this.BirthDateTo = '' // 生年月日（To）
    this.openAccountDisplay = '' // 口座開設日表示（チェック） 【初期値】OFF
    this.openAccount = []
    this.openAccountFrom = '' // 口座開設日（From）
    this.openAccountTo = '' // 口座開設日（To）
    this.viewAblrButenDisplay = '' // 閲覧可能部店表示（チェック） 【初期値】OFF
    this.butenCodeArray = '' // 閲覧可能部店　（複数入力）
    this.selectedButenCodeArray = [''] // A002リクエスト用閲覧可能部店　（複数入力）
    this.nisaContractKbnDisplay = '' // NISA口座（チェック） 【初期値】OFF
    this.nisaContractKbnName = '' // NISA口座（チェック） 【初期値】ブランク
    this.foreignSecurityAccountDisplay = '' // 外国証券取引口座表示（チェック） 【初期値】OFF
    this.foreignSecurityAccountName = '' // 外国証券取引口座 【初期値】ブランク
    this.courseChangeFinishDateDisplay = '' // コース変更完了日表示（チェック） 【初期値】OFF
    this.courseChangeFinishDateFrom = '' // コース変更完了日（From）
    this.courseChangeFinishDateTo = '' // コース変更完了日（To）
    this.valuationFrom = '' // 評価額（From）
    this.valuationTo = '' // 評価額（To）
    this.valuationConditionList = '' // 評価額（条件リスト） 【初期値】全商品
    this.commTotalList = 1 // 手数料累計 【初期値】全て
    this.commTotalPeriod = []
    this.commTotalPeriodFrom = '' // 手数料累計期間（From）
    this.commTotalPeriodTo = '' // 手数料累計期間（To）
    this.commTotalAmountFrom = '' // 手数料累計額（From）
    this.commTotalAmountTo = '' // 手数料累計額（To）
    this.commTotalAmountConditionList = '1' // 手数料累計額（条件リスト） 【初期値】全商品
    this.lastTradeDateDisplay = '' // 最終約定日表示（チェック） 【初期値】OFF
    this.lastTradeDate = []
    this.lastTradeDateFrom = '' // 最終約定日（From）
    this.lastTradeDateTo = '' // 最終約定日（To）
    this.brokerName = '' // 仲介業者名
    this.empCode = '' // 営業員コード
    this.brokerChargeName = '' // 営業員名
    this.butenCode = '' // 部店
    this.accountNumber = '' // 口座番号
    this.accountNumberSelected = '' // 選択されたグリッドセルの口座番号
    this.butenCodeSelected = '' // 選択されたグリッドセルの部店コード
    this.brokerCodeSelected = '' // 選択されたグリッドセルの仲介業者コード
    this.empCodeSelected = '' // 選択されたグリッドセルの営業員コード
    this.courseSelected = [
      { id: 'J', isSelected: true },
      { id: 'A', isSelected: true },
      { id: 'B', isSelected: true },
      { id: 'C', isSelected: true },
      { id: 'D', isSelected: true },
      { id: 'E', isSelected: true },
      { id: 'F', isSelected: true },
      { id: 'G', isSelected: true },
      { id: 'H', isSelected: true },
      { id: 'I', isSelected: true }
    ] // 取引コース
    this.customerNameKanjiKana = '' // 顧客名(漢字/カナ)
    this.customerNameKanjiKanaTerms = '3' // 顧客名検索条件
    this.age = '' // 年齢
    this.corBirthFlg = '' // 生年月日
    this.tcCompRank = '' // Cランク
    this.lastTradeDate = '' // 最終約定日
    this.brokerCode = '' // 仲介業者コード
    this.brokerCodeList = [''] // 仲介業者コードリスト
    this.chkBrokerCodeExclude = false // 仲介業者除外
    this.branchCode = '' // 支店コード
    this.branchName = '' // 支店名
    this.edelivAgreementDate = '' // 電子交付同意
    this.openAcctDate = '' // 口座開設日
    this.depositAmount = '' // 預り金額
    this.customerListValuation = '' // 評価額
    this.customerListProfitAndLoss = '' // 評価損益
    this.annualComm = '' // 年間手数料
    this.courseChangeFinishDate = [] // コース変更完了日
    this.nisaContractKbnName = '' // NISA口座
    this.foreignSecurityAccountName = '' // 外国証券取引口座
    this.personalCorporation = 2 // 個人/法人区分 【初期値】全て
    this.investmentPlan = [] // 投資の方針
    this.fundCharacter = [] // 資金の性格
    this.incomeForm = [] // 主な収入源
    this.employmentPeriod = [] // 資産運用期間
    this.annualIncome = [] // 年収
    this.financialAssets = [] // 金融資産
    this.occupation = [] // 職業
    this.investmentExpDisplay = false // 投資経験（チェック） 【初期値】OFF
    this.investmentExp = [] // 投資経験
  }
}
