export class IfaForeignStockBrandSearchPopupFormModel {
  constructor() {
    this.title = '' // タイトル
    this.infoMsg = '' // メッセージ
    this.brandNameBrandCode = '' // 銘柄名称またはコード
    this.searchOptions = '2' // 検索マッチ種別 【初期値】"から始まる"
    this.countryCodeList = '' // 国籍 【初期値】遷移元画面.国コード
    this.marketList = '' // 市場 【初期値】空白
    this.brandSearchList = [] // 「銘柄検索一覧」
    this.brandCode = '' // 銘柄コード
    this.countryCode = '' // 国コード
    this.brandName = '' // 銘柄
    this.marketAbbreviatedName = '' // 市場 "設定元：A002.市場略名"
    this.positionQuantity = '' // 売建可能数量
    this.tradeCd = '' // 取引種別 hidden
  }
}
