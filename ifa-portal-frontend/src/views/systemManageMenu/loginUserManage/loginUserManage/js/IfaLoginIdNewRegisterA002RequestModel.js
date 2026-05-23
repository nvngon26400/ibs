import Logger from '@/utils/ifaLog.js'
export class IfaLoginIdNewRegisterA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.privId = obj.privId ? obj.privId : '' // 所属権限コード
    // this.branchCode = obj.branchCode ? obj.branchCode : '' // 本支店コード
    // this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    // this.subBrokerId = obj.subBrokerId ? obj.subBrokerId : '' // 仲介業者支店コード
    this.unDisplayList = obj.unDisplayList ? obj.unDisplayList : [] // 非表示リスト
    this.displayList = obj.displayList ? obj.displayList : [] // 表示リスト
  }
}
