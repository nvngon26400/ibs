export class IfaForeignMarginDepositTransferInputFormModel {
  constructor() {
    this.screenId = 'SUB0202_0304-01_1'
    this.screenTitle = '米株信用保証金振替入力'

    this.accountSelect = '1' // 口座選択 【初期値】1(米ドル→米ドル保証金)
    this.destinationAbleAmount = '' // 指示可能金額
    this.destinationAmount = '' // 指示金額 【初期値】空欄
    this.marginPositionPowerBefore = '--' // 信用建余力_振替指示前 【初期値】"--"
    this.marginPositionPowerAfter = '--' // 信用建余力_振替指示後 【初期値】"--"
    this.marginDepositRateBefore = '--' // 委託保証金率_振替指示前 【初期値】"--"
    this.marginDepositRateAfter = '--' // 委託保証金率_振替指示後 【初期値】"--"
  }
}
