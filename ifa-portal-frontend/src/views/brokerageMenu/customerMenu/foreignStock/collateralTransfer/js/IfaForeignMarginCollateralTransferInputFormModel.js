export class IfaForeignMarginCollateralTransferInputFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0305-01_1',
      name: '米株信用代用振替入力'
    }
    this.protectionList = [ // 保護預り一覧
      {
        collateralDepositListBrandCode: '',
        collateralDepositListBrandName: '',
        collateralAssessment: '',
        specificAccountCode: '',
        transferQuantity: '',
        notReceivedQuantity: '',
        valuatePrice: '',
        collateralValuation: '',
        isChecked: false
      }
    ]
    this.totalProtectCollateral = '0.00' // 合計（保護→代用） 【初期値】"0.00"

    this.substitutionList = [ // 代用預り一覧
      {
        collateralDepositListBrandCode: '',
        collateralDepositListBrandName: '',
        collateralAssessment: '',
        specificAccountCode: '',
        transferQuantity: '',
        notReceivedQuantity: '',
        valuatePrice: '',
        collateralValuation: '',
        isChecked: false
      }
    ]
    this.totalCollateralProtect = '0.00' // 合計（代用→保護） 【初期値】"0.00"

    this.ableWithdrawal = '' // 引出可能額

    this.marginPositionPowerBefore = '--' // 信用建余力_振替指示前 【初期値】"--"
    this.marginPositionPowerAfter = '--' // 信用建余力_振替指示後 【初期値】"--"
    this.marginDepositRateBefore = '--' // 委託保証金率_振替指示前 【初期値】"--"
    this.marginDepositRateAfter = '--' // 委託保証金率_振替指示後 【初期値】"--"
    this.collateralValuationBefore = '--' // 代用評価額_振替指示前 【初期値】"--"
    this.collateralValuationAfter = '--' // 代用評価額_振替指示後 【初期値】"--"

    this.selected = []
    this.transferClassification = '' // 代用有価証券振替区分
    this.protectionFlag = false // 保護→代用選択有無フラグ
    this.substitutionFlag = false // 代用→保護選択有無フラグ
    this.subscribed = false // 貸株加入
  }
}
