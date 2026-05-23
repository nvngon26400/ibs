import Logger from '@/utils/ifaLog.js'
export class IfaContactAcceptDetailA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.referenceKbn = obj.referenceKbn ? obj.referenceKbn : '' // 参照元履歴区分
    this.bigClass = obj.bigClass ? obj.bigClass : '' // 大分類
    this.recordDate = obj.recordDate ? obj.recordDate : '' // 記録日時
    this.contents = obj.contents ? obj.contents : '' // 内容
    this.createUser = obj.createUser ? obj.createUser : '' // 作成者
    this.edaban = obj.edaban ? obj.edaban : '' // 枝番
    this.shousaiKbn = obj.shousaiKbn ? obj.shousaiKbn : '' // 詳細区分
  }
}
