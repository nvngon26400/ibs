import Logger from '@/utils/ifaLog.js'
export class IfaHoldingSecurityListA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.holdingStock = obj.holdingStock ? obj.holdingStock : '' // 保有株数
    this.sellingVolume = obj.sellingVolume ? obj.sellingVolume : '' // エラー: 「売却注文中数」が、項目辞書に存在しません。
  }
}
