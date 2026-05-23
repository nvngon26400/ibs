import Logger from '@/utils/ifaLog.js'
export class A006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    const butenCode = obj.butenCode ?? '' // 部店コード
    const an = obj.accountNumber ?? '' // 口座番号
    const accountNumber = ('0000000' + an).slice(-7)
    this.accountNumber = butenCode + '-' + accountNumber
    this.nameKanji = obj.nameKanji
  }
}
