import Logger from '@/utils/ifaLog.js'
export class IfaDocRequestExecuteConfirmA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.bmKoufuShubetsu = obj.bmKoufuShubetsu ? obj.bmKoufuShubetsu : '' // BM交付種別
    this.bunruiCd = obj.bunruiCd ? obj.bunruiCd : '' // 分類コード
    this.bunruimei = obj.bunruimei ? obj.bunruimei : '' // 分類名
    this.shoruiCd = obj.shoruiCd ? obj.shoruiCd : '' // 書類コード
    this.shoruimei = obj.shoruimei ? obj.shoruimei : '' // 書類名
    this.meigaraCd = obj.meigaraCd ? obj.meigaraCd : '' // 投信銘柄コード
    this.meigaraMei = obj.meigaraMei ? obj.meigaraMei : '' // 投信銘柄名
    this.fundCode = obj.fundCode ? obj.fundCode : '' // 協会コード
  }
}
