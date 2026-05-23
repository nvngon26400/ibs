export class IfaForeignMarginDepositTransferConfirmFormModel {
  constructor() {
    this.screenId = 'SUB0202_0304-01_2' // 画面ID
    this.title = '米株信用保証金振替確認' // 画面名
    this.confirmMassage = '' // 振替指示未完了
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    this.butenCode = '' // 部店
    this.accountNumber = '' // 口座番号
    this.transferAccount = '' // 振替口座
    this.transferDate = '' // 振替指定日
    this.destinationAmount = '' // 指示金額
    this.afterTransferDestinationAbleAmount = '' // 現在の振替指示可能額
    this.currentTransferDestinationAbleAmount = '' // 受付後振替指示可能額
    this.marginPositionPowerBefore = '' // 信用建余力_振替指示前 【初期値】"--"
    this.marginDepositRateBefore = '' // 委託保証金率_振替指示前 【初期値】"--"
    this.marginPositionPowerAfter = '' // 信用建余力_振替指示後 【初期値】"--"
    this.marginDepositRateAfter = '' // 委託保証金率_振替指示後 【初期値】"--"
    this.accountSelect = '' // 口座選択
  }
}
