export class IfaCollateralSecurityDeliverInOutDetailFormModel {
  constructor() {
    this.screenId = 'SUB0202_010204-02' // 画面ID
    this.screenTitle = '' // 画面名
    this.deliverInOutClassification = ''// 入出庫区分
    this.settlementDate = '' // 受渡日
    this.displayBaseDate = '' // 表示基準日（受渡日）
    this.collateralSecurityValuationPriceName = '' // 代用有価証券評価額名
    this.collateralValuationTotal = '' // 代用有価証券評価額合計
    this.noDetailMsg = '' // 代用有価証券明細なしメッセージ
    this.detailList = [ // 「代用有価証券入出庫個別詳細リスト」
      {
        securityClass: '',
        brandCode: '',
        brandName: '',
        depositType: '',
        contPosition: '',
        valuationPrice: '',
        collateralAssessment: '',
        collateralValuation: ''
      }
    ]
  }
}
