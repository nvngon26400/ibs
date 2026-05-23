import Logger from '@/utils/ifaLog.js'
export class IfaLoginIdNewRegisterA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.unDisplayList = obj.unDisplayList ? obj.unDisplayList : [] // 非表示リスト
    this.displayList = obj.displayList ? obj.displayList : [] // 表示リスト
  }
}
