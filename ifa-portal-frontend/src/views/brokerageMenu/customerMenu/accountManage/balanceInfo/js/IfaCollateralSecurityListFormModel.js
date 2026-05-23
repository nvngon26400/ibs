export class IfaCollateralSecurityListFormModel {
  constructor() {
    this.screenId = 'SUB0202_010204-01'
    this.screenTitle = '代用有価証券・担保貸株一覧'
    this.deliverInOut = '' // 入出庫区分
    this.displayBaseDateList = [] // 表示基準日（受渡日）select選択肢
    this.displayBaseDate = '' // 表示基準日（受渡日）【初期値】受渡日(T+0)（当日）
    this.collateralClass = '' // 代用種別 【初期値】すべて
    this.updateTime = '' // 更新日時
    this.domesticStockCollateralValuationTotal = '' // 国内株式評価額
    this.domesticMutualCollateralValuationTotal = '' // 投資信託評価額
    this.alternativeSecuritiesTotal = '' // 代用有価証券評価額合計
    this.warningMessage = '' // 代用有価証券明細なしメッセージ(明細用)
    this.settlementDateList = [{ // 「受渡日（T0）～受渡日（T5）」リスト
      settlementDate: '', // 受渡日
      collateralSecurityDeliverIn: '', // 代用有価証券入庫
      collateralSecurityDeliverOut: '', // 代用有価証券出庫
      alternativeSecuritiesTotal: '', // 代用有価証券評価額合計
      displayBaseDate: '' // 表示基準日
    }]
    this.detailList = [{ // 代用有価証券明細部リスト
      securityClass: '', // 商品分類
      brandCode: '', // 銘柄コード
      brandName: '', // 銘柄名
      depositType: '', // 預り区分
      contPosition: '', // 株数／口数
      valuationPrice: '', // 評価単価
      collateralAssessment: '', // 掛目(％)
      collateralValuation: '', // 代用評価額
      securityStockLendingClassification: '' // 担保貸株区分
    }]
  }
}
