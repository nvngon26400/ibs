import Logger from '@/utils/ifaLog.js'
export class IfaDocRequestInputA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.bunruiCd = obj.bunruiCd ? obj.bunruiCd : '' // 分類コード
    this.shoruiCd = obj.shoruiCd ? obj.shoruiCd : '' // 書類コード
  }
}
