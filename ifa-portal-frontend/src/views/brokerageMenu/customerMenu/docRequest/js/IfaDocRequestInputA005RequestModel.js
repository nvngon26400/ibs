import Logger from '@/utils/ifaLog.js'
export class IfaDocRequestInputA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.fundNricode4 = obj.fundNricode4 ? obj.fundNricode4 : '' // 銘柄コード(上4桁)
    this.fundNricode3 = obj.fundNricode3 ? obj.fundNricode3 : '' // 銘柄コード(下3桁)
  }
}
