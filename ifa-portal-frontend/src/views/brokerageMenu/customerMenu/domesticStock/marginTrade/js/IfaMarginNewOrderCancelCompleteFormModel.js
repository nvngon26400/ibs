export class IfaMarginNewOrderCancelCompleteFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0212-03_2',
      name: '信用新規注文取消完了'
    }
    /** 受注日時. */
    this.orderDayTime = ''
    /** リクエスト内容. */
    this.request = {
      /** EC受注番号（半角英数字）. */
      ecOrderNo: '',
      /** 市場（全角）. */
      market: '',
      /** 銘柄コード（半角英数字）. */
      brandCode: '',
      /** 数量（数値(整数)）. */
      quantity: '',
      /** 注文種別（半角英数字）. */
      orderKind: '',
      /** 取引種別（全角半角）. */
      tradeCd: '',
      /** 期間. */
      period: '',
      /** 通常/逆指値.執行方法（半角英数字）. */
      sasinariHouhou: '',
      /** 通常/逆指値.執行条件（半角英数字）. */
      sasinariJyouken: '',
      /** 通常/逆指値.発火条件価格（逆指値）（数値(整数)）. */
      triggerPrice: '',
      /** 通常/逆指値.発火条件価格（逆指値）ゾーン. */
      triggerPriceZone: '',
      /** 通常/逆指値.執行方法（逆指値）（半角英数字）. */
      gyakusasiHouhou: '',
      /** 通常/逆指値.注文単価（数値(整数)）. */
      price: '',
      /** OCO1.執行方法（半角英数字）. */
      oco1SasinariHouhou: '',
      /** OCO1.執行条件（半角英数字）. */
      oco1SasinariJyouken: '',
      /** OCO1.注文単価（数値(整数)）. */
      oco1Price: '',
      /** OCO2.発火条件価格（逆指値）（数値(整数)）. */
      oco2TriggerPrice: '',
      /** OCO2.発火条件価格（逆指値）ゾーン. */
      oco2TriggerPriceZone: '',
      /** OCO2.執行方法（逆指値）（半角英数字）. */
      oco2GyakusasiHouhou: '',
      /** OCO2.執行条件（逆指値）（半角英数字）. */
      oco2GyakusasiJyouken: '',
      /** OCO2.注文単価（数値(整数)）. */
      oco2Price: '',
      /** IFD1.執行方法（半角英数字）. */
      ifd1SasinariHouhou: '',
      /** IFD1.執行条件（半角英数字）. */
      ifd1SasinariJyouken: '',
      /** IFD1.発火条件価格（逆指値）（数値(整数)）. */
      ifd1TriggerPrice: '',
      /** IFD1.発火条件価格（逆指値）ゾーン. */
      ifd1TriggerPriceZone: '',
      /** IFD1.執行方法（逆指値）（半角英数字）. */
      ifd1GyakusasiHouhou: '',
      /** IFD1.注文単価（数値(整数)）. */
      ifd1Price: '',
      /** IFD2.期間.期間条件. */
      ifd2PeriodTerm: '',
      /** IFD2.期間.日付（全角半角）. */
      ifd2Limit: '',
      /** IFD2.執行方法（半角英数字）. */
      ifd2SasinariHouhou: '',
      /** IFD2.執行条件（半角英数字）. */
      ifd2SasinariJyouken: '',
      /** IFD2.発火条件価格（逆指値）（数値(整数)）. */
      ifd2TriggerPrice: '',
      /** IFD2.発火条件価格（逆指値）ゾーン. */
      ifd2TriggerPriceZone: '',
      /** IFD2.執行方法（逆指値）（半角英数字）. */
      ifd2GyakusasiHouhou: '',
      /** IFD2.注文単価（数値(整数)）. */
      ifd2Price: '',
      /** 信用取引区分（全角半角）. */
      marginTradeTypeText: '',
      /** 弁済期限（全角半角）. */
      paymentDeadline: '',
      /** 銘柄名（全角半角）. */
      brandName: '',
      /** 自動注文種別（半角英数字）. */
      autoOrderClass: '',
      /** RBE注文種別（全角半角）. */
      rbeChumonShubetsu: '',
      /** 指成区分（半角英数字）. */
      sashinariKbn: '',
      /** 指値（数値(小数)）. */
      sashine: '',
      /** トリガ発動ゾーン（半角英数字）. */
      triggerZone: '',
      /** トリガ値段（数値(小数)）. */
      triggerNedan: '',
      /** OCO指成区分（半角英数字）. */
      ocoSasinariKbn: '',
      /** OCO指値（数値(小数)）. */
      ocoSashine: '',
      /** DONE指成区分（半角英数字）. */
      doneSasinariKbn: '',
      /** DONE指値（数値(小数)）. */
      doneSashine: '',
      /** DONERBE注文種別（全角半角）. */
      doneRbeOrderKind: '',
      /** DONEトリガ発動ゾーン（半角英数字）. */
      doneTriggerZone: '',
      /** DONEトリガ値段（数値(小数)）. */
      doneTriggerNedan: '',
      /** DONEOCO指成区分（半角英数字）. */
      doneOcoSasinariKbn: '',
      /** DONEOCO指値（数値(小数)）. */
      doneOcoSashine: '',
      /** DONE有効期限（全角半角）. */
      doneYuukouKigen: '',
      /** RBE注文ステータス（半角英数字）. */
      rbeOrderStatus: '',
      /** 受注日時. */
      orderDayTime: ''
    }
  }
}
