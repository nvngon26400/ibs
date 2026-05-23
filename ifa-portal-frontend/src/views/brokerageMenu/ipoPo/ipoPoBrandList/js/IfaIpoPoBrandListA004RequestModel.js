import Logger from '@/utils/ifaLog.js'
export class IfaIpoPoBrandListA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.bbProductCode = obj.brandCode12 ? obj.brandCode12 : '' // 銘柄コード
    this.bbPresentationFrom = obj.bookBuildingPresentationFrom ? obj.bookBuildingPresentationFrom : '' // ブックビルディング申込期間（開始）
  }
}
