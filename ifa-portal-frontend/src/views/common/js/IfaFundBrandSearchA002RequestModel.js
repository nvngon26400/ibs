import Logger from '@/utils/ifaLog.js'
export class IfaFundBrandSearchA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.keyWordSearch = obj.keyWordSearch ? obj.keyWordSearch : '' // 郵便番号
  }
}
