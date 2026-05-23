import Logger from '@/utils/ifaLog.js'
export class IfaMarginNewOrderInputA016RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード（半角英数字）
    this.market = obj.market ? obj.market : '' // 市場
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.orderQuantity = obj.orderQuantity ? obj.orderQuantity : '' // 注文数量
    this.periodRadio = obj.periodRadio ? '1' : '0' // 期間.期間条件
    this.limit = obj.limit ? obj.limit : '' // 期間.日付
    this.orderKind = obj.orderKind ? obj.orderKind : '' // 注文種別
    const setNormal = obj.orderKind === '1' // 注文種別=通常/逆指値
    this.sasinariHouhou = setNormal && obj.sasinariHouhou ? obj.sasinariHouhou : '' // 通常/逆指値.執行方法
    this.sasinariJyouken = setNormal && obj.sasinariJyouken ? obj.sasinariJyouken : '' // 通常/逆指値.執行条件
    this.triggerPrice = setNormal && obj.sasinariHouhou === '3' && obj.triggerPrice ? obj.triggerPrice : '' // 通常/逆指値.発火条件価格（逆指値）（数字）
    this.gyakusasiHouhou = setNormal && obj.gyakusasiHouhou ? obj.gyakusasiHouhou : '' // 通常/逆指値.執行方法（逆指値）
    this.price = setNormal && obj.price ? obj.price : '' // 通常/逆指値.注文単価（数字）
    this.marginTradeTypeText = obj.marginTradeTypeText ? obj.marginTradeTypeText : '' // 信用取引区分
    const setOcoOrIfdoco = obj.orderKind === '2' || obj.orderKind === '4'
    this.oco1OrderExecuteMethodList = setOcoOrIfdoco && obj.oco1OrderExecuteMethodList ? obj.oco1OrderExecuteMethodList : '' // OCO1.執行方法
    this.oco1LimitExecutionConditionList = setOcoOrIfdoco && obj.oco1LimitExecutionConditionList ? obj.oco1LimitExecutionConditionList : '' // OCO1.執行条件
    this.oco1DomesticLimitPrice = setOcoOrIfdoco && obj.oco1DomesticLimitPrice ? obj.oco1DomesticLimitPrice : '' // OCO1.注文単価（数字）
    this.oco2DomesticStopOrderPrice = setOcoOrIfdoco && obj.oco2DomesticStopOrderPrice ? obj.oco2DomesticStopOrderPrice : '' // OCO2.発火条件価格（逆指値）（数字）
    this.oco2StopOrderExecuteMethodList = setOcoOrIfdoco && obj.oco2StopOrderExecuteMethodList ? obj.oco2StopOrderExecuteMethodList : '' // OCO2.執行方法（逆指値）
    if (obj.oco2StopOrderExecuteMethodList === '2') { // OCO2.執行方法（逆指値）= 成行の場合、執行条件（逆指値/成行）をセット
      this.oco2StopOrderMarketExecutionConditionList = setOcoOrIfdoco && obj.oco2StopOrderMarketExecutionConditionList ? obj.oco2StopOrderMarketExecutionConditionList : '' // OCO2.執行条件（逆指値）
    } else { // OCO2.執行方法（逆指値）= 指値の場合、OCO1.執行条件（指値）をセット
      this.oco2StopOrderMarketExecutionConditionList = setOcoOrIfdoco && obj.oco1LimitExecutionConditionList ? obj.oco1LimitExecutionConditionList : '' // OCO2.執行条件（逆指値）
    }
    this.oco2DomesticLimitPrice = setOcoOrIfdoco && obj.oco2DomesticLimitPrice ? obj.oco2DomesticLimitPrice : '' // OCO2.注文単価（数字）
    const setIfdOrIfdoco = obj.orderKind === '3' || obj.orderKind === '4'
    this.ifd1OrderExecuteMethodList = setIfdOrIfdoco && obj.ifd1OrderExecuteMethodList ? obj.ifd1OrderExecuteMethodList : '' // IFD1.執行方法
    this.ifd1LimitExecutionConditionList = setIfdOrIfdoco && obj.ifd1LimitExecutionConditionList ? obj.ifd1LimitExecutionConditionList : '' // IFD1.執行条件
    this.ifd1DomesticStopOrderPrice = setIfdOrIfdoco && obj.ifd1OrderExecuteMethodList === '3' && obj.ifd1DomesticStopOrderPrice ? obj.ifd1DomesticStopOrderPrice : '' // IFD1.発火条件価格（逆指値）（数字）
    this.ifd1StopOrderExecuteMethodList = setIfdOrIfdoco && obj.ifd1StopOrderExecuteMethodList ? obj.ifd1StopOrderExecuteMethodList : '' // IFD1.執行方法（逆指値）
    this.ifd1DomesticLimitPrice = setIfdOrIfdoco && obj.ifd1DomesticLimitPrice ? obj.ifd1DomesticLimitPrice : '' // IFD1.注文単価（数字）
    this.ifd2PeriodRadio = setIfdOrIfdoco && obj.ifd2PeriodRadio ? '1' : '0' // IFD2.期間.期間条件
    this.ifd2Limit = setIfdOrIfdoco && obj.ifd2Limit ? obj.ifd2Limit : '' // IFD2.期間.日付
    const setIfd = obj.orderKind === '3'
    this.ifd2OrderExecuteMethodList = setIfd && obj.ifd2OrderExecuteMethodList ? obj.ifd2OrderExecuteMethodList : '' // IFD2.執行方法
    this.ifd2LimitExecutionConditionList = setIfd && obj.ifd2LimitExecutionConditionList ? obj.ifd2LimitExecutionConditionList : '' // IFD2.執行条件
    this.ifd2DomesticStopOrderPrice = setIfd && obj.ifd2OrderExecuteMethodList === '3' && obj.ifd2DomesticStopOrderPrice ? obj.ifd2DomesticStopOrderPrice : '' // IFD2.発火条件価格（逆指値）（数字）
    this.ifd2StopOrderExecuteMethodList = setIfd && obj.ifd2StopOrderExecuteMethodList ? obj.ifd2StopOrderExecuteMethodList : '' // IFD2.執行方法（逆指値）
    this.ifd2DomesticLimitPrice = setIfd && obj.ifd2DomesticLimitPrice ? obj.ifd2DomesticLimitPrice : '' // IFD2.注文単価（数字）

    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.receiveOrderType = obj.receiveOrderType ? obj.receiveOrderType : '' // 受注方法
    this.checkInsider = obj.checkInsider[0] ? obj.checkInsider[0] : '' // 確認項目.インサイダー確認
    this.checkSor = obj.checkSor[0] ? obj.checkSor[0] : '' // 確認項目.SOR確認
  }
}
