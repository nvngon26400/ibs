export class IfaForeignStockCounterOrderInputFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0302-02-01',
      name: '外国株式店頭注文入力'
    }
    this.brandCode = '' // 銘柄コード
    this.brandName = '' // 銘柄名
    this.upperLimitQuantity = '' // 上限数量
    this.tradePrice = '' // 取引価格
    this.storeBuyingPower = '' // 買付余力（外貨） 【初期値】""
    this.tradeCd = '' // 取引種別
    this.maxOrderableQuantity = '' // 注文可能数量 【初期値】""
    this.quantity = '' // 数量 【初期値】""
    this.contractAmount = '' // 約定金額 【初期値】""
    this.depositType = '' // 預り区分 【初期値】編集内容欄参照
    this.settlementType = '2' // 決済区分 【初期値】外貨決済選択
    this.kanyuKbn = '' // 勧誘区分 【初期値】未選択
    this.orderMethod = '' // 受注方法 【初期値】未選択
    this.powerConfirm = '0' // 余力確認 【初期値】チェックなし
    this.importantMatter = '' // 重要事項の説明 【初期値】未選択
    this.foreignStockYmd = '' // 外国証券情報.版番 【初期値】未入力
    this.documentDeliveryDate = '' // 外国証券情報.交付日 【初期値】未入力
    this.issuedMethod = '' // 外国証券情報.交付方法 【初期値】未選択
    this.switchingSolicitationEtf = '' // 乗換え勧誘(ETF) 【初期値】未選択
    this.engPubBrand = '' // 英文開示銘柄 【初期値】チェックなし
    this.engPubYmd = '' // 説明日 【初期値】未入力
    this.summaryAny = '' // 摘要(任意) 【初期値】""
    this.returnTickerSelectFlag = '' // 返却用ティッカー選択フラグ
    this.forReturnBrandCode = '' // 返却用銘柄コード
    this.forReturnBrandName = '' // 返却用銘柄名
    this.marketCode = '' // 市場コード
    this.tradeClassification = '' // 売買区分
    this.forCheckUpperLimitContractAmount = '' // チェック用上限約定金額
    this.foreignSecurityInfoDate = '' // チェック用外国証券情報版番

    this.newMainSiteParamList = []
    this.linkUrl = ''
    this.postRequest = {}
  }
}
