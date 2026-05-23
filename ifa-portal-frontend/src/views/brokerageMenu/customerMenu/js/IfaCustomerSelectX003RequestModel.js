import Logger from '@/utils/ifaLog.js'
export class IfaCustomerSelectX003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.customerName = obj.customerNameKanjiKana ? obj.customerNameKanjiKana : '' // 顧客名
    this.favorite = obj.favoriteCheckbox ? obj.favoriteCheckbox : '' // お気に入り
    this.tradeRestrictionSelect = obj.tradeRestrictionCheckbox ? obj.tradeRestrictionCheckbox : '' // 取引制限有無
    this.customerNameConditionList = obj.customerNameConditionList ? obj.customerNameConditionList : '' // 顧客名（漢字・カナ）　（条件リスト）
    this.customerId = obj.customerId ? obj.customerId : '' // 顧客コード
  }
}
