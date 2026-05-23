import Logger from '@/utils/ifaLog.js'
export class IfaDomesticStockOrderConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    let requestConfirmItemSor = ''
    if (obj.requestContents.market === 'A') {
      requestConfirmItemSor = obj.requestContents.confirmItemSor
    } else {
      requestConfirmItemSor = ''
    }
    this.brandCode = obj.requestContents.brandCode ? obj.requestContents.brandCode : '' // 銘柄コード
    this.market = obj.requestContents.market ? obj.requestContents.market : '' // 市場
    this.account = obj.requestContents.account ? obj.requestContents.account : '' // 口座
    this.tradeCd = obj.requestContents.tradeCd ? obj.requestContents.tradeCd : '' // 取引種別
    this.quantity = obj.requestContents.quantity ? obj.requestContents.quantity : '' // 数量
    this.periodTerms = obj.requestContents.periodTerms ? '1' : '0' // 期間.期間条件
    this.limit = obj.requestContents.limit ? obj.requestContents.limit : '' // 期間.日付
    this.depositType = obj.requestContents.depositType ? obj.requestContents.depositType : '' // 預り区分
    this.orderKind = obj.requestContents.orderKind ? obj.requestContents.orderKind : '' // 注文種別
    this.sasinariHouhou = obj.requestContents.normalPriceLimitReverseSasinariHouhou ? obj.requestContents.normalPriceLimitReverseSasinariHouhou : '' // 通常/逆指値.執行方法
    this.sasinariJyouken = obj.requestContents.normalPriceLimitReverseSasinariJyouken ? obj.requestContents.normalPriceLimitReverseSasinariJyouken : '' // 通常/逆指値.執行条件
    this.triggerPrice = obj.requestContents.normalPriceLimitReverseTriggerPrice ? obj.requestContents.normalPriceLimitReverseTriggerPrice : '' // 通常/逆指値.発火条件価格（逆指値）
    this.gyakusasiHouhou = obj.requestContents.normalPriceLimitReverseGyakusasiHouhou ? obj.requestContents.normalPriceLimitReverseGyakusasiHouhou : '' // 通常/逆指値.執行方法（逆指値）
    this.price = obj.requestContents.normalPriceLimitReversePrice ? obj.requestContents.normalPriceLimitReversePrice : '' // 通常/逆指値.注文単価
    this.oco1SasinariHouhou = obj.requestContents.oco1SasinariHouhou ? obj.requestContents.oco1SasinariHouhou : '' // OCO1.執行方法
    this.oco1SasinariJyouken = obj.requestContents.oco1SasinariJyouken ? obj.requestContents.oco1SasinariJyouken : '' // OCO1.執行条件
    this.oco1Price = obj.requestContents.oco1Price ? obj.requestContents.oco1Price : '' // OCO1.注文単価
    this.oco2TriggerPrice = obj.requestContents.oco2TriggerPrice ? obj.requestContents.oco2TriggerPrice : '' // OCO2.発火条件価格（逆指値）
    this.oco2GyakusasiHouhou = obj.requestContents.oco2GyakusasiHouhou ? obj.requestContents.oco2GyakusasiHouhou : '' // OCO2.執行方法（逆指値）
    this.oco2GyakusasiJyouken = obj.requestContents.oco2GyakusasiJyouken ? obj.requestContents.oco2GyakusasiJyouken : '' // OCO2.執行条件（逆指値）
    this.oco2Price = obj.requestContents.oco2Price ? obj.requestContents.oco2Price : '' // OCO2.注文単価
    this.ifd1SasinariHouhou = obj.requestContents.ifd1SasinariHouhou ? obj.requestContents.ifd1SasinariHouhou : '' // IFD1.執行方法
    this.ifd1SasinariJyouken = obj.requestContents.ifd1SasinariJyouken ? obj.requestContents.ifd1SasinariJyouken : '' // IFD1.執行条件
    this.ifd1TriggerPrice = obj.requestContents.ifd1TriggerPrice ? obj.requestContents.ifd1TriggerPrice : '' // IFD1.発火条件価格（逆指値）
    this.ifd1GyakusasiHouhou = obj.requestContents.ifd1GyakusasiHouhou ? obj.requestContents.ifd1GyakusasiHouhou : '' // IFD1.執行方法（逆指値）
    this.ifd1Price = obj.requestContents.ifd1Price ? obj.requestContents.ifd1Price : '' // IFD1.注文単価
    this.ifd2PeriodTerms = obj.requestContents.ifd2PeriodTerms ? '1' : '0' // IFD2.期間.期間条件
    this.ifd2Limit = obj.requestContents.ifd2Limit ? obj.requestContents.ifd2Limit : '' // IFD2.期間.日付
    this.ifd2SasinariHouhou = obj.requestContents.ifd2SasinariHouhou ? obj.requestContents.ifd2SasinariHouhou : '' // IFD2.執行方法
    this.ifd2SasinariJyouken = obj.requestContents.ifd2SasinariJyouken ? obj.requestContents.ifd2SasinariJyouken : '' // IFD2.執行条件
    this.ifd2TriggerPrice = obj.requestContents.ifd2TriggerPrice ? obj.requestContents.ifd2TriggerPrice : '' // IFD2.発火条件価格（逆指値）
    this.ifd2GyakusasiHouhou = obj.requestContents.ifd2GyakusasiHouhou ? obj.requestContents.ifd2GyakusasiHouhou : '' // IFD2.執行方法（逆指値）
    this.ifd2Price = obj.requestContents.ifd2Price ? obj.requestContents.ifd2Price : '' // IFD2.注文単価
    this.kanyuKbn = obj.requestContents.kanyuKbn ? obj.requestContents.kanyuKbn : '' // 勧誘区分
    this.ordersHouhou = obj.requestContents.receiveOrderType ? obj.requestContents.receiveOrderType : '' // 受注方法
    this.checkInsider = obj.requestContents.confirmItemInsider ? obj.requestContents.confirmItemInsider : '' // 確認項目.インサイダー確認
    this.checkSor = requestConfirmItemSor // 確認項目.SOR確認
    this.tradeNoticeInfoConfirm = obj.alert.tradeNoticeInfoConfirm ? obj.alert.tradeNoticeInfoConfirm : '' // アラート内容確認.取引注意情報（銘柄）確認
    this.confirm = obj.alert.confirm === true ? '1' : (obj.alert.confirm === false ? '0' : '') // アラート内容確認.コンプラランクチェック確認
    this.noticeInfoAlertConfirm = obj.alert.noticeInfoAlertConfirm ? obj.alert.noticeInfoAlertConfirm : '' // アラート内容確認.注意情報アラート確認
    this.noticeAlertConfirm = obj.alert.noticeAlertConfirm ? obj.alert.noticeAlertConfirm : '' // アラート内容確認.お知らせアラート確認
    this.confirm1 = obj.alert.confirm1 ? obj.alert.confirm1 : '' // アラート内容確認.内部者エラー確認
    this.noticeInfoAlert = obj.noticeInfoAlert ? obj.noticeInfoAlert : '' // 注意情報アラート
    this.noticeAlert = obj.noticeAlert ? obj.noticeAlert : '' // お知らせアラート
    this.complianceRankCheckMsg = obj.complianceRankCheck.message ? obj.complianceRankCheck.message : '' // コンプラランクチェック.メッセージ
    this.checkBoxWording = obj.complianceRankCheck.chkBoxLabel ? obj.complianceRankCheck.chkBoxLabel : '' // コンプラランクチェック.チェックボックス文言
    this.complianceCheckUseFundCharacter = obj.complianceRankCheck.complianceRankCheck ? obj.complianceRankCheck.complianceRankCheck : '' // コンプラランクチェック.コンプラチェック用資金性格
    this.insiderConfirmMsg = obj.insiderConfirmMsg ? obj.insiderConfirmMsg : '' // 内部者確認メッセージ
    this.tradeNoticeInfoBrandMsg = obj.tradeNoticeInfoBrandMsg ? obj.tradeNoticeInfoBrandMsg : '' // 取引注意情報（銘柄）メッセージ
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
  }
}
