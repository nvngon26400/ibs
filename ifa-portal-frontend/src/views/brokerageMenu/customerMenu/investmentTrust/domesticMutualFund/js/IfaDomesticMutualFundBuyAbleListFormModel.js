export class IfaDomesticMutualFundBuyAbleListFormModel {
  constructor() {
    this.screenId = 'SUB0202_0401-01' // 画面ID
    this.screenTitle = '国内投信購入・積立可能一覧' // 画面タイトル
    this.fundCodeTimes = '' // ファンドコード（回数） 【初期値】空白
    this.fundCodeIssues = '' // ファンドコード（号） 【初期値】空白
    this.intermediaryValue = '' // 媒介可否
    this.hitNumber = '' // 検索結果総数

    this.transfersPreferentialLimitAmountThisMonth = '' // 乗換優遇限度額（総合口座）（今月残）
    this.transfersPreferentialLimitAmountNextMonth = '' // 乗換優遇限度額（総合口座）（来月残）
    this.switchingFavorableTreatmentLimitJuniorNisaAccountThisMonth = '' // 乗換優遇限度額（JrNISA口座）（今月残）
    this.switchingFavorableTreatmentLimitJuniorNisaAccountNextMonth = '' // 乗換優遇限度額（JrNISA口座）（来月残）

    this.detailList = [ // 投信購入可能一覧リスト
      {
        brandCodeBrandName: '', // コード銘柄名
        fundType: '', // 区分
        prospectus: '', // 目論見書
        price: '', // 基準価格
        diff: '', // 基準価額前日比
        buyComm: '', // 買付手数料（税込）
        preferentialApplicationRate: '', // 買付手数料（税込）優遇適用率
        prospectusShippingDate: '', // 目論見書発送日
        currencySelectConfirmDocument: '', // 通貨選択確認書
        complexMutualFundConfirmDocument: '', // 複雑投信確認書
        buyAbledate: '', // 購入可能日
        marketOrderDate: '', // マーケット発注日
        coercionOrderTarget: '', // 購入可否
        deadlines: '' // 締切時刻
      }
    ]

    this.fundCodeTimesInput = '' // 入力フィールド.ファンドコード（回数） 【初期値】空白
    this.fundCodeIssuesInput = '' // 入力フィールド.ファンドコード（号） 【初期値】空白
  }
}
