export class IfaCommonSearchTestFormModel {
  constructor() {
    // 検索項目
    this.brokerCode = '' // 仲介業者コード
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名（漢字／カナ）
    this.customerNameKanjiKanaTerms = '3' // 顧客名（漢字／カナ）条件
    this.courseSelected = [] // 取引コース 【初期値】１個選択（IFAコース(プランA)）
    this.chkBrokerCodeExclude = false // 仲介業者除外
  }
}
