export class IfaForeignMarginCollateralTransferCompleteFormModel {
  constructor() {
    this.finishMessage = '' // 振替指示完了
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    this.butenCode = '' // 部店
    this.accountNumber = '' // 口座番号
    this.foreignMarginPositionPower = '' // 信用建余力 【初期値】"--"
    this.collateralTransferMarginDepositRateAfter = '' // 委託保証金率 【初期値】"--"
    this.collateralValuation = '' // 代用評価額 【初期値】"--"
  }
}
