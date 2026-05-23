export class IfaForeignMarginCollateralTransferConfirmFormModel {
  constructor() {
    this.warningMessage = '' // 振替指示未完了
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    this.butenCode = '' // 部店
    this.accountNumber = '' // 口座番号
    this.marginPositionPowerBefore = '--' // 信用建余力_振替指示前 【初期値】"--"
    this.marginPositionPowerAfter = '--' // 信用建余力_振替指示後 【初期値】"--"
    this.marginDepositRateBefore = '--' // 委託保証金率_振替指示前 【初期値】"--"
    this.marginDepositRateAfter = '--' // 委託保証金率_振替指示後 【初期値】"--"
    this.collateralValuationBefore = '--' // 代用評価額_振替指示前 【初期値】"--"
    this.collateralValuationAfter = '--' // 代用評価額_振替指示後 【初期値】"--"
    this.protectionList = [ // 保護預り一覧
      {
        collateralDepositListBrandCode: '',
        collateralDepositListBrandName: '',
        collateralAssessment: '',
        specificAccountCode: '',
        transferQuantity: '',
        notReceivedQuantity: '',
        valuatePrice: '',
        collateralValuation: ''
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
        collateralValuation: ''
      }
    ]
    this.totalCollateralProtect = '0.00' // 合計（代用→保護） 【初期値】"0.00"
    this.depositType = '' // 保護区分
  }
}
