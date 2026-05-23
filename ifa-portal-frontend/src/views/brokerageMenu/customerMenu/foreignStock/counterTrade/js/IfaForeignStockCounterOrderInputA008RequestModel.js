import Logger from '@/utils/ifaLog.js'
export class IfaForeignStockCounterOrderInputA008RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.upperLimitQuantity = obj.upperLimitQuantity ? obj.upperLimitQuantity : '' // 上限数量
    this.tradePrice = obj.tradePrice ? obj.tradePrice : '' // 取引価格
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.contractAmount = obj.contractAmount ? obj.contractAmount : '' // 約定金額
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.settlementType = obj.settlementType ? obj.settlementType : '' // 決済区分
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.orderMethod = obj.orderMethod ? obj.orderMethod : '' // 受注方法
    this.powerConfirm = obj.powerConfirm ? obj.powerConfirm : '' // 余力確認
    this.importantMatter = obj.importantMatter ? obj.importantMatter : '' // 重要事項の説明
    this.foreignStockYmd = obj.foreignStockYmd ? obj.foreignStockYmd : '' // 外国証券情報.版番
    this.documentDeliveryDate = obj.documentDeliveryDate ? obj.documentDeliveryDate : '' // 外国証券情報.交付日
    this.issuedMethod = obj.issuedMethod ? obj.issuedMethod : '' // 外国証券情報.交付方法
    this.switchingSolicitationEtf = obj.switchingSolicitationEtf ? obj.switchingSolicitationEtf : '' // 乗換え勧誘(ETF)
    this.engPubBrand = obj.engPubBrand ? obj.engPubBrand : '' // 英文開示銘柄
    this.engPubYmd = obj.engPubYmd ? obj.engPubYmd : '' // 説明日
    this.summaryAny = obj.summaryAny ? obj.summaryAny : '' // 摘要(任意)
    this.marketCode = obj.marketCode ? obj.marketCode : '' // 市場コード
    this.tradeKbn = obj.tradeClassification ? obj.tradeClassification : '' // 売買区分
    this.forCheckUpperLimitContractAmount = obj.forCheckUpperLimitContractAmount ? obj.forCheckUpperLimitContractAmount : '' // チェック用上限約定金額
    this.forCheckUpperLimitOrderAmount = obj.forCheckUpperLimitOrderAmount ? obj.forCheckUpperLimitOrderAmount : '' // チェック用上限注文数量
    this.forCheckForeignStockYmd = obj.foreignSecurityInfoDate ? obj.foreignSecurityInfoDate : '' // チェック用外国証券情報版番
    this.etfFlag = obj.etfFlag ? obj.etfFlag : '' // ETFフラグ
    this.englishDisclosureBrandEffectiveDate = obj.englishDisclosureBrandEffectiveDate ? obj.englishDisclosureBrandEffectiveDate : '' // 英文開示銘柄適用日
    this.chartUrl = obj.chartUrl ? obj.chartUrl : '' // チャートURL
    this.stockPriceReferenceUrl = obj.stockPriceReferenceUrl ? obj.stockPriceReferenceUrl : '' // 株価参照URL
  }
}
