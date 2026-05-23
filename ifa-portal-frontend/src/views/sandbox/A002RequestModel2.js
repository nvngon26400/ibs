import Logger from '@/utils/ifaLog.js'
export class A002RequestModel2 {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.domesticMarginPositionListBrandCode = obj.domesticMarginPositionListBrandCode ? obj.domesticMarginPositionListBrandCode : '' // 銘柄コード
  }
}
