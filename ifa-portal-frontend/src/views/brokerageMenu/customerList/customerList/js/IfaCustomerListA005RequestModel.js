import Logger from '@/utils/ifaLog.js'
export class IfaCustomerListA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCodeList = obj.brokerCodeList ? obj.brokerCodeList : [] // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? obj.chkBrokerCodeExclude : '' // 仲介業者除外
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 支店コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.customerNameKanjiKana = obj.customerNameKanjiKana ? obj.customerNameKanjiKana : '' // 顧客名(漢字/カナ)
    this.customerNameConditionList = obj.customerNameKanjiKanaTerms ? obj.customerNameKanjiKanaTerms : '' // 顧客名(漢字/カナ)　（条件リスト）
    this.course = obj.courseSelected ? obj.courseSelected : []
    this.tradeRestrictionCheckbox = obj.tradeRestrictionCheckbox ? obj.tradeRestrictionCheckbox : '' // 取引制限ありチェックボックス
    this.complianceLankFrom = obj.complianceLankFrom ? obj.complianceLankFrom : '' // コンプラランク（From）
    this.complianceLankTo = obj.complianceLankTo ? obj.complianceLankTo : '' // コンプラランク（To）
    this.adress = obj.adress ? obj.adress : '' // 住所
    this.adressConditionList = obj.adressConditionList ? obj.adressConditionList : '' // 住所　（条件リスト）
    this.corporatePhoneNumber = obj.customerListPhoneNumber ? obj.customerListPhoneNumber : '' // 電話番号
    this.ageFrom = obj.ageFrom ? obj.ageFrom : '' // 年齢（From）
    this.ageTo = obj.ageTo ? obj.ageTo : '' // 年齢（To）
    this.birthDateFrom = obj.BirthDateFrom ? obj.BirthDateFrom : '' // 生年月日（From）
    this.birthDateTo = obj.BirthDateTo ? obj.BirthDateTo : '' // 生年月日（To）
    this.openAccountFrom = obj.openAccountFrom ? obj.openAccountFrom : '' // 口座開設日（From）
    this.openAccountTo = obj.openAccountTo ? obj.openAccountTo : '' // 口座開設日（To）
    this.butenCodeArray = obj.butenCodeArray ? obj.selectedButenCodeArray : [''] // 閲覧可能部店　（複数入力）
    this.valuationFrom = obj.valuationFrom ? obj.valuationFrom : '' // 評価額（From）
    this.valuationTo = obj.valuationTo ? obj.valuationTo : '' // 評価額（To）
    this.valuationConditionList = obj.valuationConditionList ? obj.valuationConditionList : '' // 評価額（条件リスト）
    this.commTotalList = obj.commTotalList ? obj.commTotalList : '' // 累計期間?
    this.commTotalPeriodFrom = obj.commTotalPeriodFrom ? obj.commTotalPeriodFrom : '' // 手数料累計期間（From）
    this.commTotalPeriodTo = obj.commTotalPeriodTo ? obj.commTotalPeriodTo : '' // 手数料累計期間（To）
    this.commTotalAmountFrom = obj.commTotalAmountFrom ? obj.commTotalAmountFrom : '' // 手数料累計額（From）
    this.commTotalAmountTo = obj.commTotalAmountTo ? obj.commTotalAmountTo : '' // 手数料累計額（To）
    this.commTotalAmountConditionList = obj.commTotalAmountConditionList ? obj.commTotalAmountConditionList : '' // 手数料累計額（条件リスト）
    this.lastTradeDateCheck = obj.lastTradeDateDisplay ? obj.lastTradeDateDisplay : '' // 最終約定日（チェック）
    this.lastTradeDateFrom = obj.lastTradeDateFrom ? obj.lastTradeDateFrom : '' // 最終約定日（From）
    this.lastTradeDateTo = obj.lastTradeDateTo ? obj.lastTradeDateTo : '' // 最終約定日（To）
    this.courseChangeFinishDateFrom = obj.courseChangeFinishDateFrom ? obj.courseChangeFinishDateFrom : '' // コース変更完了日（From）
    this.courseChangeFinishDateTo = obj.courseChangeFinishDateTo ? obj.courseChangeFinishDateTo : '' // コース変更完了日（To）
    this.foreignSecurityAccountList = obj.foreignSecurityAccountName ? obj.foreignSecurityAccountName : '' // 外国証券取引口座
    this.nisaContractKbnList = obj.nisaContractKbnName ? obj.nisaContractKbnName : '' // NISA口座
    this.adressDisplay = obj.adressDisplay ? obj.adressDisplay : '' // 住所表示（チェック）
    this.phoneNumberDisplay = obj.phoneNumberDisplay ? obj.phoneNumberDisplay : '' // 電話番号表示（チェック）
    this.ageDisplay = obj.ageDisplay ? obj.ageDisplay : '' // 年齢表示（チェック）
    this.birthDateDisplay = obj.BirthDateDisplay ? obj.BirthDateDisplay : '' // 生年月日表示（チェック）
    this.openAccountDisplay = obj.openAccountDisplay ? obj.openAccountDisplay : '' // 口座開設日表示（チェック）
    this.viewAblrButenDisplay = obj.viewAblrButenDisplay ? obj.viewAblrButenDisplay : '' // 閲覧可能部店表示（チェック）
    this.nisaContractKbnDisplay = obj.nisaContractKbnDisplay ? obj.nisaContractKbnDisplay : '' // NISA口座（チェック）
    this.foreignSecurityAccountDisplay = obj.foreignSecurityAccountDisplay ? obj.foreignSecurityAccountDisplay : '' // 外国証券取引口座表示（チェック）
    this.courseChangeFinishDateDisplay = obj.courseChangeFinishDateDisplay ? obj.courseChangeFinishDateDisplay : '' // コース変更完了日表示（チェック）
    this.lastTradeDateDisplay = obj.lastTradeDateDisplay ? obj.lastTradeDateDisplay : '' // 最終約定日表示（チェック）
    this.personalCorporation = obj.personalCorporation // 個人/法人区分
    this.investmentPlan = obj.investmentPlan ? obj.investmentPlan : [] // 投資の方針
    this.fundCharacter = obj.fundCharacter ? obj.fundCharacter : [] // 資金の性格
    this.incomeForm = obj.incomeForm ? obj.incomeForm : [] // 主な収入源
    this.employmentPeriod = obj.employmentPeriod ? obj.employmentPeriod : [] // 資産運用期間
    this.annualIncome = obj.annualIncome ? obj.annualIncome : [] // 年収
    this.financialAssets = obj.financialAssets ? obj.financialAssets : [] // 金融資産
    this.occupation = obj.occupation ? obj.occupation : [] // 職業
    this.investmentExpDisplay = obj.investmentExpDisplay ? obj.investmentExpDisplay : '' // 投資経験（チェック）
    this.investmentExp = obj.investmentExp ? obj.investmentExp : [] // 投資経験
  }
}
