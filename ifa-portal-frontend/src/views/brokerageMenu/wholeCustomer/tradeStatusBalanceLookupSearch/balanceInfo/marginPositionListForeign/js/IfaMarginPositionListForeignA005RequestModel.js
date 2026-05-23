import Logger from '@/utils/ifaLog.js'
export class IfaMarginPositionListForeignA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)

    /** 部店コード（半角英数字）. */
    this.butenCode = obj.buten ? obj.buten : ''
    /** 口座番号（数字）. */
    this.accountNumber = obj.account ? obj.account : ''
    /** 国コード（全角半角）. */
    this.countryCode = obj.countryCode ? obj.countryCode : ''
    /** 銘柄コード（半角英数字）. */
    this.brandCode = obj.ticker ? obj.ticker : ''
    /** 新規売買区分（全角半角）. */
    this.openTradeKbn = obj.tradeKbn ? obj.tradeKbn : ''
    /** 弁済期限（全角半角）. */
    this.paymentDeadline = obj.marginDueDate ? obj.marginDueDate : ''
    /** 預り区分（全角半角）. */
    this.depositType = obj.depositType ? obj.depositType : ''
    /** 個別一括判定（全角半角）. */
    this.individualBatchJudge = '1' // 個別
    /** 国内約定日. */
    this.businessDaysAfterOrder = obj.domesticTradeDate ? obj.domesticTradeDate : ''
    /** 現地約定日. */
    this.localTradeDate = obj.foreignTradeDate ? obj.foreignTradeDate : ''
    /** 新規建単価（外貨）. */
    this.previousDayValue = obj.positionPrice ? obj.positionPrice : ''
    /** 新規建単価（円貨）（数値(整数)）. */
    this.jpyAmountNewPositionPrice = obj.jpyAmountNewPositionPrice ? obj.jpyAmountNewPositionPrice : ''
  }
}
