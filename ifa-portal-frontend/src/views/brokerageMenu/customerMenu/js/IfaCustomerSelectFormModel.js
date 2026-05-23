export class IfaCustomerSelectFormModel {
  constructor() {
    this.screenId = 'SUB0202_00-01'
    this.screenTitle = '顧客検索'
    this.butenCode = '' // 部店テキストボックス 【初期値】空欄
    this.accountNumber = '' // 口座番号テキストボックス 【初期値】空欄
    this.customerNameKanjiKana = '' // 顧客名（漢字・カナ）テキストボックス 【初期値】空欄
    this.favoriteCheckbox = true // お気に入りチェックボックス 【初期値】チェックあり
    this.tradeRestrictionCheckbox = false // 取引制限ありチェックボックス 【初期値】チェックなし
    this.butenCodeSelected = '' // 選択行部店
    this.customerCodeSelected = '' // 選択行顧客コード
    this.accountNumberSelected = '' // 選択行口座番号
    this.favoRegStatusSelected = '' // 選択行お気に入り登録状況
    this.customerNameConditionList = '2' // 顧客名（漢字・カナ）　（条件リスト） 【初期値】をで始まる
    this.customerId = '' // 顧客コード
    this.customerList = [
      {
        butenCode: '', // 顧客一覧.部店
        accountNumber: '', // 顧客一覧.口座番号
        tradeRestrictionSelect: '', // 顧客一覧.取引制限
        customerNameKanji: '', // 顧客一覧.顧客名（漢字）
        customerNameKana: '', // 顧客一覧.顧客名（カナ）
        openAcctDate: '', // 顧客一覧.口座開設年月日
        customerCode: '', // 顧客一覧.顧客コード
        favoRegStatus: '' // 顧客一覧.お気に入り登録状況
      }
    ]
  }
}
