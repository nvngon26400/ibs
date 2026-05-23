import Logger from '@/utils/ifaLog.js'
export class IfaBuyingPowerDomesticA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.requestType = obj.requestType ? obj.requestType : '' // リクエストタイプ
    this.requestDateClassification = obj.requestDateType ? obj.requestDateType : '' // リクエスト日付区分
    this.settlementDate = obj.settlementDate ? obj.settlementDate : '' // 受渡日
    this.accountType = obj.acquireAccountType ? obj.acquireAccountType : '' // 取得口座区分
  }
}
