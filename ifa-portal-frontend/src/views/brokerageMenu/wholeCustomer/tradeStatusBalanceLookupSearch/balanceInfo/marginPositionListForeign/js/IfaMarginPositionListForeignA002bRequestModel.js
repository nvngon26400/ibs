import Logger from '@/utils/ifaLog.js'
export class IfaMarginPositionListForeignA002bRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード（半角英数字）
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号（数字）
    this.customerNameKanji = obj.customerNameKanji ? obj.customerNameKanji : '' // 顧客名_姓名(漢字)
    this.customerNameKana = obj.customerNameKana ? obj.customerNameKana : '' // 顧客名_姓名(カナ)
    this.course = obj.course ? obj.course : '' // コース
    this.customerAttribute = obj.customerAttribute ? obj.customerAttribute : '' // 契約締結前交付書面コード（全角半角）
    this.apiProfile = obj.apiProfile ? obj.apiProfile : '' // プロファイル値
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.brokerChargeName = obj.brokerChargeName ? obj.brokerChargeName : '' // 営業員名
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 支店コード
    this.branchName = obj.branchName ? obj.branchName : '' // 支店名
    this.ticker = obj.ticker ? obj.ticker : null // ティッカー（全角半角）
  }
}
