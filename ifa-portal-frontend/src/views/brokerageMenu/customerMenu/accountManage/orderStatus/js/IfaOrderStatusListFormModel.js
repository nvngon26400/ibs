export class IfaOrderStatusListFormModel {
  constructor() {
    this.title = '注文状況一覧'
    this.tradeSuspendFlag = '0' // 取引停止フラグ
    this.intermediaryValueList = []
    // this.productCd // 媒介可否リスト.証券金銭種別
    // this.tradeCd // 媒介可否リスト.取引種別
    // this.countryCd // 媒介可否リスト.国籍コード
    // this.mediatePropriety // 媒介可否リスト.媒介可否

    this.accountTypeRadio = '2' // 口座区分 【初期値】全て
    this.noDetailMsg = '注文明細はありません。' // 注文明細なしメッセージ

    this.domesticStockOrderCount = '' // 国内株式注文件数
    this.domesticStockOrderList = [] // 国内株式注文状況一覧
    // this.ecOrderNo = '' // 国内株式注文状況一覧.EC受注番号
    // this.securityType = '' // 国内株式注文状況一覧.商品区分
    // this.brandCode = '' // 国内株式注文状況一覧.銘柄コード
    // this.brandName = '' // 国内株式注文状況一覧.銘柄名
    // this.market = '' // 国内株式注文状況一覧.市場
    // this.orderStatus = '' // 国内株式注文状況一覧.注文状況
    // this.orderStatusSupplement = '' // 国内株式注文状況一覧.注文状況補足
    // this.buySellTypeName = '' // 国内株式注文状況一覧.取引種別
    // this.marginTradeTypeText = '' // 国内株式注文状況一覧.信用取引区分
    // this.coercionType = '' // 国内株式注文状況一覧.強制決済
    // this.latestMarketId = '' // 国内株式注文状況一覧.直近市場
    // this.sorLinkKbn = '' // 国内株式注文状況一覧.SOR連携区分
    // this.orderDayTime = '' // 国内株式注文状況一覧.受注日時
    // this.marketOrderDate = '' // 国内株式注文状況一覧.発注日
    // this.orderPeriod = '' // 国内株式注文状況一覧.期間
    // this.orderStatusListOrderClass = '' // 国内株式注文状況一覧.注文種別
    // this.notSpecificDepositTradeType = '' // 国内株式注文状況一覧.預り区分
    // this.orderQuantity = '' // 国内株式注文状況一覧.数量
    // this.unMatchedTradeQuantity = '' // 国内株式注文状況一覧.未出来数量
    // this.orderExecuteMethodList = '' // 国内株式注文状況一覧.執行方法
    // this.price = '' // 国内株式注文状況一覧.価格
    // this.tradingCoursText = '' // 国内株式注文状況一覧.手数料区分
    // this.point = '' // 国内株式注文状況一覧.利用ポイント
    // this.conditions = '' // 国内株式注文状況一覧.条件
    // this.domesticStockCorrectButtonDisplayJudgment = '' // 国内株式注文状況一覧.訂正ボタン表示判定
    // this.domesticStockCancelButtonDisplayJudgment = '' // 国内株式注文状況一覧.取消ボタン表示判定

    this.domesticMutualFundOrderCount = '' // 国内投信注文件数
    this.domesticMutualFundOrderStatusList = [] // 国内投信注文状況一覧
    // this.ecOrderNo = '' // 国内投信注文状況一覧.EC受注番号
    // this.tradeKbn = '' // 国内投信注文状況一覧.売買区分
    // this.fundCodeTimes = '' // 国内投信注文状況一覧.ファンドコード（回数）
    // this.fundCodeIssues = '' // 国内投信注文状況一覧.国内投信注文リスト.ファンドコード（号）
    // this.brandName = '' // 国内投信注文状況一覧.銘柄名
    // this.orderStatus = '' // 国内投信注文状況一覧.注文状況
    // this.buySellTypeName = '' // 国内投信注文状況一覧.取引種別
    // this.notSpecificDepositTradeType = '' // 国内投信注文状況一覧.預り区分
    // this.orderDayTime = '' // 国内投信注文状況一覧.受注日時
    // this.orderDate = '' // 国内投信注文状況一覧.発注日
    // this.unitAmount = '' // 国内投信注文状況一覧.口数/金額
    // this.unit = '' // 国内投信注文状況一覧.口数/金額（単位）
    // this.distributionReceiveMethod = '' // 国内投信注文状況一覧.分配金受取方法指定
    // this.callcenterKbn = '' // 国内投信注文状況一覧.受付経路区分
    // this.point = '' // 国内投信注文状況一覧.利用ポイント
    // this.pointType = '' // 国内投信注文状況一覧.ポイント種別
    // this.yoryokuCheckKbn = '' // 国内投信注文状況一覧.強制区分
    // this.mutualFundCancelButtonDisplayJudgment = '' // 国内投信注文状況一覧.取消ボタン表示判定

    this.subscriptOrderStatusCount = '' // 募集注文件数
    this.subscriptOrderStatusList = [] // 募集注文状況一覧
    // this.brandCode = '' // 募集注文状況一覧.銘柄コード
    // this.brandName = '' // 募集注文状況一覧.銘柄名
    // this.orderStatus = '' // 募集注文状況一覧.注文状況
    // this.depositType = '' // 募集注文状況一覧.預り区分
    // this.quantity = '' // 募集注文状況一覧.数量
    // this.recruitmentOrderDate = '' // 募集注文状況一覧.募集受注日時

    this.fsEntrustOrderStatusTotalCount = '' // 外株委託注文総件数
    this.foreignStockEntrustOrder = [] // 外株委託注文状況一覧
    // this.countryCd = '' // 国別一覧.国籍コード
    // this.fsEntrustOrderStatusCount = '' // 国別一覧.外株委託注文件数

    // this.fsEntrustOrderStatusList // 国別一覧.外株委託注文状況一覧
    // this.orderNumber = '' // 国別一覧.外株委託注文状況一覧.注文番号
    // this.orderSubNumber = '' // 国別一覧.外株委託注文状況一覧.注文Sub番号
    // this.brandCode = '' // 国別一覧.外株委託注文状況一覧.銘柄コード
    // this.brandName = '' // 国別一覧.外株委託注文状況一覧.銘柄名
    // this.market = '' // 国別一覧.外株委託注文状況一覧.市場
    // this.stockTradeType = '' // 国別一覧.外株委託注文状況一覧.株取引区分
    // this.orderStatus = '' // 国別一覧.外株委託注文状況一覧.注文状況
    // this.tradeStatus = '' // 国別一覧.外株委託注文状況一覧.約定状況
    // this.buySellTypeName = '' // 国別一覧.外株委託注文状況一覧.取引種別
    // this.kessaiHoho = '' // 国別一覧.外株委託注文状況一覧.決済方法
    // this.orderStatusListOrderClass = '' // 国別一覧.外株委託注文状況一覧.注文種別
    // this.depositType = '' // 国別一覧.外株委託注文状況一覧.預り区分
    // this.orderDate = '' // 国別一覧.外株委託注文状況一覧.国内注文日時
    // this.orderPeriod = '' // 国別一覧.外株委託注文状況一覧.注文期間
    // this.domesticQuantityInput = '' // 国別一覧.外株委託注文状況一覧.数量
    // this.tradeQuantity = '' // 国別一覧.外株委託注文状況一覧.約定数量
    // this.orderExecuteMethodList = '' // 国別一覧.外株委託注文状況一覧.執行方法
    // this.price = '' // 国別一覧.外株委託注文状況一覧.注文単価
    // this.nationalCurrencyUnit = '' // 国別一覧.外株委託注文状況一覧.注文単価_単位
    // this.averageTradePrice = '' // 国別一覧.外株委託注文状況一覧.平均約定単価
    // this.nationalCurrencyUnit = '' // 国別一覧.外株委託注文状況一覧.平均約定単価_単位
    // this.conditions = '' // 国別一覧.外株委託注文状況一覧.条件
    // this.commissionApplicationType = '' // 国別一覧.外株委託注文状況一覧.手数料適用区分

    this.foreignStockCounterOrderCount = '' // 外株店頭注文件数
    this.fsStoreOrderStatusList = [] // 外株店頭注文状況一覧
    // this.manageNumber = '' // 外株店頭注文状況一覧.管理番号
    // this.brandCode = '' // 外株店頭注文状況一覧.ティッカー
    // this.brandName = '' // 外株店頭注文状況一覧.銘柄名
    // this.status = '' // 外株店頭注文状況一覧.ステータス
    // this.buySellTypeName = '' // 外株店頭注文状況一覧.取引種別
    // this.depositType = '' // 外株店頭注文状況一覧.預り区分
    // this.orderDayTime = '' // 外株店頭注文状況一覧.注文日時
    // this.tradeDateTime = '' // 外株店頭注文状況一覧.約定日時
    // this.domesticQuantityInput = '' // 外株店頭注文状況一覧.数量
    // this.tradePrice = '' // 外株店頭注文状況一覧.価格
    // this.warningApplyTrade = '' // 外株店頭注文状況一覧.ワーニング取引
    // this.cancelReason = '' // 外株店頭注文状況一覧.取消理由
    // this.foreignMmfOrderCount = '' // 外貨建MMF注文件数
    // this.tradeKbn // 外株店頭注文状況一覧.売買区分

    this.ccsAuthId = '' // コールセンタ認証ID

    // this.ecOrderNo = '' // 国内投信注文状況一覧.EC受注番号（hidden）
    // this.countryCodeList = '' // 外株委託注文状況一覧.国籍コード（hidden）
    // this.orderNumber = '' // 外株委託注文状況一覧.注文番号（hidden）
    // this.orderSubNumber = '' // 外株委託注文状況一覧.注文Sub番号（hidden）
    // this.buySellTypeName = '' // 外株委託注文状況一覧.取引種別（hidden）
    // this.manageNumber = '' // 外株店頭注文状況一覧.管理番号（hidden）
    // this.trade = '' // 外株店頭注文状況一覧.売買区分（hidden）
    // this.orderNumber = '' // 外貨建MMF注文状況一覧.注文番号（hidden）
    // this.currencyTypeRadio = '' // 外貨建MMF注文状況一覧.決済方法（hidden）
  }
}
