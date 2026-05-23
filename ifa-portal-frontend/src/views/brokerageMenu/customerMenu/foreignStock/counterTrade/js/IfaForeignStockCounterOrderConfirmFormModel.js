export class IfaForeignStockCounterOrderConfirmFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0302-02-02',
      name: '外国株式店頭注文確認'
    }
    this.warningMessage = '' // 注意喚起文言(注文発注)メッセージ
    this.butenCode = '' // 部店 【初期値】""
    this.accountNumber = '' // 口座番号 【初期値】""
    this.customerNameKanji = '' // 顧客名（漢字） 【初期値】""
    this.customerNameKana = '' // 顧客名（カナ） 【初期値】""
    this.brandCode = '' // 銘柄コード 【初期値】""
    this.brandName = '' // 銘柄名 【初期値】""
    this.upperLimitQuantity = '' // 上限数量 【初期値】""
    this.tradePrice = '' // 取引価格 【初期値】""
    this.tradeCd = '' // 取引種別 【初期値】""
    this.quantity = '' // 数量 【初期値】""
    this.contractAmount = '' // 約定金額 【初期値】""
    this.depositType = '' // 預り区分 【初期値】""
    this.settlementType = '' // 決済区分 【初期値】""
    this.kanyuKbn = '' // 勧誘区分 【初期値】""
    this.orderMethod = '' // 受注方法 【初期値】""
    this.powerConfirm = '' // 余力確認 【初期値】""
    this.importantMatter = '' // 重要事項の説明 【初期値】""
    this.foreignStockYmd = '' // 外国証券情報.版番 【初期値】""
    this.documentDeliveryDate = '' // 外国証券情報.交付日 【初期値】""
    this.issuedMethod = '' // 外国証券情報.交付方法 【初期値】""
    this.switchingSolicitationEtf = '' // 乗換え勧誘(ETF) 【初期値】""
    this.engPubBrand = '' // 英文開示銘柄 【初期値】""
    this.engPubYmd = '' // 説明日 【初期値】""
    this.summaryAny = '' // 摘要(任意) 【初期値】""
    this.chkBoxLabel = '' // コンプラランクチェック確認見出し
    this.complianceRankCheckConfirm = '' // コンプラランクチェック確認 【初期値】空白
    this.noticeInfoAlertConfirm = '' // 注意情報アラート確認 【初期値】空白
    this.noticeAlertConfirm = '' // お知らせアラート確認 【初期値】空白
    this.tradeAmountAlertConfirm = '' // 約定金額アラート確認 【初期値】空白
    this.overseasEtfAlertConfirm = '' // 海外ETFアラート確認 【初期値】空白
    this.marketCode = '' // 市場コード
    this.tradeKbn = '' // 売買区分
    this.foreignSecurityInfoDate = '' // チェック用外国証券情報版番
    this.etfFlag = '' // ETFフラグ
    this.englishDisclosureBrandEffectiveDate = '' // 英文開示銘柄適用日
    this.newMainSiteParamList = [] // 新メインサイト用パラメータ
    this.linkUrl = '' // リンクURL
    this.postRequest = {} // POSTリクエスト
  }
}
