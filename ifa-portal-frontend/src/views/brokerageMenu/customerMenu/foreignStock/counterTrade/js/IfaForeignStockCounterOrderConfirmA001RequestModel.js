import Logger from '@/utils/ifaLog.js'
export class IfaForeignStockCounterOrderConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
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
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
    this.complianceRankCheckConfirm = obj.complianceRankCheckConfirm ? obj.complianceRankCheckConfirm : '' // アラート内容確認.コンプラランクチェック確認
    this.noticeInfoAlertConfirm = obj.noticeInfoAlertConfirm ? obj.noticeInfoAlertConfirm : '' // アラート内容確認.注意情報アラート確認
    this.noticeAlertConfirm = obj.noticeAlertConfirm ? obj.noticeAlertConfirm : '' // アラート内容確認.お知らせアラート確認
    this.tradeAmountAlertConfirm = obj.tradeAmountAlertConfirm ? obj.tradeAmountAlertConfirm : '' // アラート内容確認.約定金額アラート確認
    this.overseasEtfAlertConfirm = obj.overseasEtfAlertConfirm ? obj.overseasEtfAlertConfirm : '' // アラート内容確認.海外ETFアラート確認
    this.msg = obj.msg ? obj.msg : '' // コンプラチェック.メッセージ
    this.chkBoxLabel = obj.chkBoxLabel ? obj.chkBoxLabel : '' // コンプラチェック.チェックボックス文言
    this.noticeInfoAlert = obj.noticeInfoAlert ? obj.noticeInfoAlert : '' // 注意情報アラートメッセージ
    this.noticeAlert = obj.noticeAlert ? obj.noticeAlert : '' // お知らせアラートメッセージ
    this.tradeAmountAlert = obj.tradeAmountAlert ? obj.tradeAmountAlert : '' // 約定金額アラートメッセージ
    this.overseasEtfAlert = obj.overseasEtfAlert ? obj.overseasEtfAlert : '' // 海外ETFアラートメッセージ
  }
}
