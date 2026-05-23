import Logger from '@/utils/ifaLog.js'
export class IfaCustomerSelectA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCodeSelected ? obj.butenCodeSelected : '' // 部店
    this.accountNumber = obj.accountNumberSelected ? obj.accountNumberSelected : '' // 口座番号
    this.favoRegStatus = obj.favoRegStatusSelected === '0' ? '1' : obj.favoRegStatusSelected === '1' ? '0' : '' // お気に入り登録状況
    this.customerCode = obj.customerCodeSelected ? obj.customerCodeSelected : '' // 顧客コード
  }
}
