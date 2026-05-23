import Logger from '@/utils/ifaLog.js'
export class IfaForeignStockBrandSearchPopupA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.brandNameBrandCode = obj.brandNameBrandCode ? obj.brandNameBrandCode : '' // 銘柄名称またはコード
    this.searchOptions = obj.searchOptions ? obj.searchOptions : '' // 検索マッチ種別
    this.countryCode = obj.countryCode ? obj.countryCode : '' // 国籍
    this.market = obj.market ? obj.market : '' // 市場
  }
}
