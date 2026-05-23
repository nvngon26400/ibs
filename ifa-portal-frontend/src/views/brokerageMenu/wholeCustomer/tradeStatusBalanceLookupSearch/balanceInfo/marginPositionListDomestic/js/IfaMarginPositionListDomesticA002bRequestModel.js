import Logger from '@/utils/ifaLog.js'
export class IfaMarginPositionListDomesticA002bRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.customerNameKanji = obj.customerNameKanji ? obj.customerNameKanji : '' // 顧客名_姓名(漢字)
    this.customerNameKana = obj.customerNameKana ? obj.customerNameKana : '' // 顧客名_姓名(カナ)
    this.course = obj.course ? obj.course : '' // コース
    this.aPIProfile = obj.aPIProfile ? obj.aPIProfile : '' // プロファイル値
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.brokerChargeName = obj.brokerChargeName ? obj.brokerChargeName : '' // 営業員名
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 支店コード
    this.branchName = obj.branchName ? obj.branchName : '' // 支店名
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
  }
}
