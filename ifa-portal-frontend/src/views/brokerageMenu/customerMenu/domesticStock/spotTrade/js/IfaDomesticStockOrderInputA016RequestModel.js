import Logger from '@/utils/ifaLog.js'
export class IfaDomesticStockOrderInputA016RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    let requestPeriodTerms = ''
    let requestIfd2PeriodTerms = ''
    if (obj.period.periodTerms) {
      requestPeriodTerms = '1'
    } else {
      requestPeriodTerms = '0'
    }
    if (obj.ifd2.period.periodTerms) {
      requestIfd2PeriodTerms = '1'
    } else {
      requestIfd2PeriodTerms = '0'
    }
    let requestConfirmItemSor = ''
    if (obj.market === 'A') {
      requestConfirmItemSor = obj.confirmItem.sor
    } else {
      requestConfirmItemSor = ''
    }
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.market = obj.market ? obj.market : '' // 市場
    this.account = obj.account ? obj.account : '' // 口座
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.periodTerms = obj.period.periodTerms ? requestPeriodTerms : '0' // 期間.期間条件
    this.limit = obj.period.limit ? obj.period.limit : '' // 期間.日付
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.orderKind = obj.orderKind ? obj.orderKind : '' // 注文種別
    const setNormal = obj.orderKind === '1' // 注文種別=通常/逆指値
    this.normalPriceLimitReverseSasinariHouhou = setNormal && obj.normalPriceLimitReverse.sasinariHouhou ? obj.normalPriceLimitReverse.sasinariHouhou : '' // 通常/逆指値.執行方法
    this.normalPriceLimitReverseSasinariJyouken = setNormal && obj.normalPriceLimitReverse.sasinariJyouken ? obj.normalPriceLimitReverse.sasinariJyouken : '' // 通常/逆指値.執行条件
    this.normalPriceLimitReverseTriggerPrice = setNormal && obj.normalPriceLimitReverse.sasinariHouhou === '3' && obj.normalPriceLimitReverse.triggerPrice ? obj.normalPriceLimitReverse.triggerPrice : '' // 通常/逆指値.発火条件価格（逆指値）
    this.normalPriceLimitReverseGyakusasiHouhou = setNormal && obj.normalPriceLimitReverse.sasinariHouhou === '3' && obj.normalPriceLimitReverse.gyakusasiHouhou ? obj.normalPriceLimitReverse.gyakusasiHouhou : '' // 通常/逆指値.執行方法（逆指値）
    this.normalPriceLimitReversePrice = setNormal && obj.normalPriceLimitReverse.price ? obj.normalPriceLimitReverse.price : '' // 通常/逆指値.注文単価

    const setOcoOrIfdoco = obj.orderKind === '2' || obj.orderKind === '4'
    this.oco1SasinariHouhou = setOcoOrIfdoco && obj.oco1.sasinariHouhou ? obj.oco1.sasinariHouhou : '' // OCO1.執行方法
    this.oco1SasinariJyouken = setOcoOrIfdoco && obj.oco1.sasinariJyouken ? obj.oco1.sasinariJyouken : '' // OCO1.執行条件
    this.oco1Price = setOcoOrIfdoco && obj.oco1.price ? obj.oco1.price : '' // OCO1.注文単価
    this.oco2TriggerPrice = setOcoOrIfdoco && obj.oco2.triggerPrice ? obj.oco2.triggerPrice : '' // OCO2.発火条件価格（逆指値）
    this.oco2GyakusasiHouhou = setOcoOrIfdoco && obj.oco2.gyakusasiHouhou ? obj.oco2.gyakusasiHouhou : '' // OCO2.執行方法（逆指値）
    this.oco2GyakusasiJyouken = setOcoOrIfdoco && obj.oco2.gyakusasiJyouken ? obj.oco2.gyakusasiJyouken : '' // OCO2.執行条件（逆指値）
    this.oco2Price = setOcoOrIfdoco && obj.oco2.gyakusasiHouhou === '1' && obj.oco2.price ? obj.oco2.price : '' // OCO2.注文単価

    const setIfdOrIfdoco = obj.orderKind === '3' || obj.orderKind === '4'
    this.ifd1SasinariHouhou = setIfdOrIfdoco && obj.ifd1.sasinariHouhou ? obj.ifd1.sasinariHouhou : '' // IFD1.執行方法
    this.ifd1SasinariJyouken = setIfdOrIfdoco && obj.ifd1.sasinariJyouken ? obj.ifd1.sasinariJyouken : '' // IFD1.執行条件
    this.ifd1TriggerPrice = setIfdOrIfdoco && obj.ifd1.sasinariHouhou === '3' && obj.ifd1.triggerPrice ? obj.ifd1.triggerPrice : '' // IFD1.発火条件価格（逆指値）
    this.ifd1GyakusasiHouhou = setIfdOrIfdoco && obj.ifd1.sasinariHouhou === '3' && obj.ifd1.gyakusasiHouhou ? obj.ifd1.gyakusasiHouhou : '' // IFD1.執行方法（逆指値）
    this.ifd1Price = setIfdOrIfdoco && obj.ifd1.price ? obj.ifd1.price : '' // IFD1.注文単価
    this.ifd2PeriodTerms = setIfdOrIfdoco && obj.ifd2.period.periodTerms ? requestIfd2PeriodTerms : '0' // IFD2.期間.期間条件
    this.ifd2Limit = setIfdOrIfdoco && obj.ifd2.period.limit ? obj.ifd2.period.limit : '' // IFD2.期間.日付

    const setIfd = obj.orderKind === '3'
    this.ifd2SasinariHouhou = setIfd && obj.ifd2.sasinariHouhou ? obj.ifd2.sasinariHouhou : '' // IFD2.執行方法
    this.ifd2SasinariJyouken = setIfd && obj.ifd2.sasinariJyouken ? obj.ifd2.sasinariJyouken : '' // IFD2.執行条件
    this.ifd2TriggerPrice = setIfd && obj.ifd2.sasinariHouhou === '3' && obj.ifd2.triggerPrice ? obj.ifd2.triggerPrice : '' // IFD2.発火条件価格（逆指値）
    this.ifd2GyakusasiHouhou = setIfd && obj.ifd2.sasinariHouhou === '3' && obj.ifd2.gyakusasiHouhou ? obj.ifd2.gyakusasiHouhou : '' // IFD2.執行方法（逆指値）
    this.ifd2Price = setIfd && obj.ifd2.price ? obj.ifd2.price : '' // IFD2.注文単価

    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.receiveOrderType = obj.receiveOrderType ? obj.receiveOrderType : '' // 受注方法
    this.confirmItemInsider = obj.confirmItem.insider ? obj.confirmItem.insider : '' // 確認項目.インサイダー確認
    this.confirmItemSor = requestConfirmItemSor // 確認項目.SOR確認
  }
}
