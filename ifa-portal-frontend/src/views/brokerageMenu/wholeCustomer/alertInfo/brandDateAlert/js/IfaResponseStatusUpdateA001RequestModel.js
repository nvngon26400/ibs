import Logger from '@/utils/ifaLog.js'
export class IfaResponseStatusUpdateA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.declineRateKbn = obj.declineRateKbn ? obj.declineRateKbn : '' // 下落率区分
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.investmentTrustAssociationCode = obj.investmentTrustAssociationCode ? obj.investmentTrustAssociationCode : '' // 投信協会コード
    this.standardDateTo = obj.standardDateTo ? obj.standardDateTo : '' // 基準日
  }
}
