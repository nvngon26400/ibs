import Logger from '@/utils/ifaLog.js'
export class IfaContactA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.bigClassList = obj.bigClassList ? obj.bigClassList : '' // 接触履歴区分リスト
  }
}
