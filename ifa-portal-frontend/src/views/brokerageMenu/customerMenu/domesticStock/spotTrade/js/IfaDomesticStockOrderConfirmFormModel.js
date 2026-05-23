export class IfaDomesticStockOrderConfirmFormModel {
  constructor() {
    this.screenId = 'SUB0202_0208-01_3' // 画面ID
    this.title = '国内株式注文確認' // 画面名
    this.brandName = '' // 銘柄名
    this.shubetu = '' // 種別
    this.code = '' // エラーコード
    this.errMessage = '' // エラーメッセージ
    this.noticeInfoAlert = '' // 注意情報アラート
    this.noticeAlert = '' // お知らせアラート
    this.complianceRankCheck = {
      message: '', // メッセージ
      chkBoxLabel: '', // チェックボックス文言
      complianceRankCheck: '' // コンプラチェック用資金性格
    } // コンプラランクチェック
    this.insiderConfirmMsg = '' // 内部者確認メッセージ
    this.tradeNoticeInfoBrandMsg = '' // 取引注意情報（銘柄）メッセージ
    this.jrnisaTransferAmount = '' // ジュニアNISA振替金額
    this.quoteUnitPrice = '' // 見積単価
    this.contractAmount = '' // 約定金額
    this.charge = '' // 手数料/諸費用
    this.consumptionTax = '' // 消費税
    this.yieldTax = '' // 讓渡益税
    this.settlementAmount = '' // 精算金額
    this.investableQuote = '' // 投資可能枠
    this.contractDate = '' // 約定予定日
    this.deliveryDate = '' // 受渡予定日
    this.orderDayTime = '' // 受注日時
    this.orderedMarket = '' // 注文入力市場
    this.requestContents = {
      brandCode: '', // 銘柄コード
      market: '', // 市場
      account: '', // 口座
      tradeCd: '', // 取引種別
      quantity: '', // 数量
      period: {
        periodTerms: '', // 期間.期間条件
        limit: '' // 期間.日付
      }, // 期間
      depositType: '', // 預り区分
      orderKind: '', // 注文種別
      normalPriceLimitReverse: {
        sasinariHouhou: '', // 通常/逆指値.執行方法
        sasinariJyouken: ' ', // 通常/逆指値.執行条件
        triggerPrice: '', // 通常/逆指値.発火条件価格（逆指値）
        gyakusasiHouhou: '', // 通常/逆指値.執行方法（逆指値）
        price: '' // 通常/逆指値.注文単価
      }, // 通常/逆指値
      oco1: {
        sasinariHouhou: '', // OCO1.執行方法
        sasinariJyouken: ' ', // OCO1.執行条件
        price: '' // OCO1.注文単価
      }, // OCO1
      oco2: {
        triggerPrice: '', // OCO2.発火条件価格（逆指値）
        gyakusasiHouhou: '', // OCO2.執行方法（逆指値）
        gyakusasiJyouken: '', // OCO2.執行条件（逆指値）
        price: '' // OCO2.注文単価
      }, // OCO2
      ifd1: {
        sasinariHouhou: '', // IFD1.執行方法
        sasinariJyouken: '', // IFD1.執行条件
        triggerPrice: '', // IFD1.発火条件価格（逆指値）
        gyakusasiHouhou: '', // IFD1.執行方法（逆指値）
        price: '' // IFD1.注文単価
      }, // IFD1
      ifd2: {
        period: {
          periodTerms: '', // 期間.期間条件
          limit: '' //  期間.日付
        }, // 期間
        sasinariHouhou: '', // IFD2.執行方法
        sasinariJyouken: '', // IFD2.執行条件
        triggerPrice: '', // IFD2.発火条件価格（逆指値）
        gyakusasiHouhou: '', // IFD2.執行方法（逆指値）
        price: '484484' // IFD2.注文単価
      }, // IFD2
      kanyuKbn: '', // 勧誘区分
      receiveOrderType: '', // 受注方法
      confirmItem: {
        insider: '', // 確認項目.インサイダー確認
        sor: '' // 確認項目.SOR確認
      } // 確認項目
    } // リクエスト内容
    this.alert = {
      tradeNoticeInfoConfirm: '0', // アラート内容確認.取引注意情報（銘柄）確認
      confirm: false, // アラート内容確認.コンプラランクチェック確認
      noticeInfoAlertConfirm: '0', // アラート内容確認.注意情報アラート確認
      noticeAlertConfirm: '0', // アラート内容確認.お知らせアラート確認
      confirm1: '0' // アラート内容確認.内部者エラー確認
    } // アラート内容確認
  }
}
