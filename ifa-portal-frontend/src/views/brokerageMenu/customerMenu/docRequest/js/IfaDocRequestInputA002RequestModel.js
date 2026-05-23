import Logger from '@/utils/ifaLog.js'
export class IfaDocRequestInputA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.bunruiCd = obj.bunruiCd ? obj.bunruiCd : '' // 分類コード
  }
}
