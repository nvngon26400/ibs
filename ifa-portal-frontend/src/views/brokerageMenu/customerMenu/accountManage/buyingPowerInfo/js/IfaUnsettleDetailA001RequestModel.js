import Logger from '@/utils/ifaLog.js'
export class IfaUnsettleDetailA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.requestType = obj.requestType ? obj.requestType : '' // リクエストタイプ
    this.requestDateClassification = obj.requestDateClassification ? obj.requestDateClassification : '' // リクエスト日付区分
    this.settlementDate = obj.settlementDate ? obj.settlementDate : '' // 受渡日
    this.accountType = obj.accountType ? obj.accountType : '' // 取得口座区分
  }
}
