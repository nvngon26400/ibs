import Logger from '@/utils/ifaLog.js'
export class IfaDocRequestInputA007RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.hassouSts = obj.hassouSts ? obj.hassouSts : '' // 交付区分
    this.bmKoufuShubetsu = obj.bmKoufuShubetsu ? obj.bmKoufuShubetsu : '' // BM交付種別
    this.fundCode = obj.fundCode ? obj.fundCode : '' // 協会コード
    this.fundMdBuyKubun = obj.fundMdBuyKubun ? obj.fundMdBuyKubun : '' // 購入可否判定区分
    this.meigaraCd = obj.meigaraCd ? obj.meigaraCd : '' // 銘柄コード
  }
}
