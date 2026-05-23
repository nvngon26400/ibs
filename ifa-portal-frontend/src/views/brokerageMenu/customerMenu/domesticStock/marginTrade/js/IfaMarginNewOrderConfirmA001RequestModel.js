import Logger from '@/utils/ifaLog.js'
export class IfaMarginNewOrderConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード（半角英数字）
    this.market = obj.market ? obj.market : '' // 市場（全角）
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別（全角半角）
    this.orderQuantity = obj.orderQuantity ? obj.orderQuantity : '' // 受注数量（数値(整数)）
    this.periodRadio = obj.periodRadio ? obj.periodRadio : '' // 期間.期間条件
    this.limit = obj.limit ? obj.limit : '' // 期間.日付（全角半角）
    this.orderKind = obj.orderKind ? obj.orderKind : '' // 注文種別（全角半角）
    this.sasinariHouhou = obj.sasinariHouhou ? obj.sasinariHouhou : '' // 通常/逆指値.執行方法（全角半角）
    this.sasinariJyouken = obj.sasinariJyouken ? obj.sasinariJyouken : '' // 通常/逆指値.執行条件（全角半角）
    this.triggerPrice = obj.triggerPrice ? obj.triggerPrice : '' // 通常/逆指値.発火条件価格（逆指値）（数値(整数)）
    this.gyakusasiHouhou = obj.gyakusasiHouhou ? obj.gyakusasiHouhou : '' // 通常/逆指値.執行方法（逆指値）（全角半角）
    this.price = obj.price ? obj.price : '' // 通常/逆指値.注文単価（数値(整数)）
    this.oco1OrderExecuteMethodText = obj.oco1OrderExecuteMethodList ? obj.oco1OrderExecuteMethodList : '' // OCO1.執行方法
    this.oco1SasinariJyouken = obj.oco1LimitExecutionConditionList ? obj.oco1LimitExecutionConditionList : '' // OCO1.執行条件（全角半角）
    this.oco1Price = obj.oco1DomesticLimitPrice ? obj.oco1DomesticLimitPrice : '' // OCO1.注文単価（数値(整数)）
    this.oco2TriggerPrice = obj.oco2DomesticStopOrderPrice ? obj.oco2DomesticStopOrderPrice : '' // OCO2.発火条件価格（逆指値）（数値(整数)）
    this.oco2GyakusasiHouhou = obj.oco2StopOrderExecuteMethodList ? obj.oco2StopOrderExecuteMethodList : '' // OCO2.執行方法（逆指値）（全角半角）
    this.oco2GyakusasiJyouken = obj.oco2StopOrderMarketExecutionConditionList ? obj.oco2StopOrderMarketExecutionConditionList : '' // OCO2.執行条件（逆指値）（全角半角）
    this.oco2Price = obj.oco2DomesticLimitPrice ? obj.oco2DomesticLimitPrice : '' // OCO2.注文単価（数値(整数)）
    this.ifd1SasinariHouhou = obj.ifd1OrderExecuteMethodList ? obj.ifd1OrderExecuteMethodList : '' // IFD1.執行方法（全角半角）
    this.ifd1SasinariJyouken = obj.ifd1LimitExecutionConditionList ? obj.ifd1LimitExecutionConditionList : '' // IFD1.執行条件（全角半角）
    this.ifd1TriggerPrice = obj.ifd1DomesticStopOrderPrice ? obj.ifd1DomesticStopOrderPrice : '' // IFD1.発火条件価格（逆指値）（数値(整数)）
    this.ifd1GyakusasiHouhou = obj.ifd1StopOrderExecuteMethodList ? obj.ifd1StopOrderExecuteMethodList : '' // IFD1.執行方法（逆指値）（全角半角）
    this.ifd1Price = obj.ifd1DomesticLimitPrice ? obj.ifd1DomesticLimitPrice : '' // IFD1.注文単価（数値(整数)）
    this.ifd2PeriodDate = obj.ifd2PeriodRadio ? obj.ifd2PeriodRadio : '' // IFD2.期間.期間条件
    this.ifd2Limit = obj.ifd2Limit ? obj.ifd2Limit : '' // IFD2.期間.日付（全角半角）
    this.ifd2SasinariHouhou = obj.ifd2OrderExecuteMethodList ? obj.ifd2OrderExecuteMethodList : '' // IFD2.執行方法（全角半角）
    this.ifd2SasinariJyouken = obj.ifd2LimitExecutionConditionList ? obj.ifd2LimitExecutionConditionList : '' // IFD2.執行条件（全角半角）
    this.ifd2TriggerPrice = obj.ifd2DomesticStopOrderPrice ? obj.ifd2DomesticStopOrderPrice : '' // IFD2.発火条件価格（逆指値）（数値(整数)）
    this.ifd2OrderExecuteMethodText = obj.ifd2StopOrderExecuteMethodList ? obj.ifd2StopOrderExecuteMethodList : '' // IFD2.執行方法（逆指値）
    this.ifd2Price = obj.ifd2DomesticLimitPrice ? obj.ifd2DomesticLimitPrice : '' // IFD2.注文単価（数値(整数)）
    this.marginTradeTypeText = obj.marginTradeTypeText ? obj.marginTradeTypeText : '' // 信用取引区分（全角半角）
    this.tesuuryouKbn = obj.customerAttribute ? obj.customerAttribute : '' // 手数料区分（全角半角）
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分（全角半角）
    this.receiveOrderTypeName = obj.receiveOrderType ? obj.receiveOrderType : '' // 受注方法
    this.checkInsider = obj.checkInsider ? obj.checkInsider : '' // 確認項目.インサイダー確認（全角半角）
    this.checkSor = obj.checkSor ? obj.checkSor : '' // 確認項目.SOR確認（全角半角）
    this.tradingCautionInformation = obj.tradingCautionInformation[0] ? obj.tradingCautionInformation[0] : '' // アラート内容確認.取引注意情報（銘柄）確認
    this.noteInfoCheckbox = obj.noteInfoCheckbox[0] ? obj.noteInfoCheckbox[0] : '' // アラート内容確認.注意情報アラート確認
    this.noteLimitCheck = obj.noteLimitCheck[0] ? obj.noteLimitCheck[0] : '' // アラート内容確認.お知らせアラート確認
    this.insiderErrorConfirmationCheckbox = obj.insiderErrorConfirmationCheckbox[0] ? obj.insiderErrorConfirmationCheckbox[0] : '' // アラート内容確認.内部者エラー確認
    this.shortSellingRegulationsCheckbox = obj.shortSellingRegulationsCheckbox[0] ? obj.shortSellingRegulationsCheckbox[0] : '' // アラート内容確認.空売り規制の抵触確認
    this.noticeInfoAlert = obj.noticeInfoAlert ? obj.noticeInfoAlert : '' // 注意情報アラート（全角半角）
    this.noticeAlert = obj.noticeAlert ? obj.noticeAlert : '' // お知らせアラート（全角半角）
    this.insiderErrorMsg = obj.insiderErrorMsg ? obj.insiderErrorMsg : '' // 内部者確認メッセージ
    this.shortSellingRegulationsMessage = obj.shortSellingRegulationConflictMessage ? obj.shortSellingRegulationConflictMessage : '' // 空売り規制の抵触確認メッセージ
    this.tradeNoticeInfoBrandMsg = obj.tradeNoticeInfoBrandMsg ? obj.tradeNoticeInfoBrandMsg : '' // 取引注意情報（銘柄）メッセージ（全角半角）
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名（全角半角）
    this.applicableInterestRate = obj.applicableInterestRate ? obj.applicableInterestRate : '' // 適用金利
    this.applicableStockLendingFees = obj.applicableStockLendingFees ? obj.applicableStockLendingFees : '' // 適用貸株料
  }
}
