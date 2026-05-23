export class IfaMutualFundAccumulateSettingChangeInputInitA001RequestModel {
  constructor(obj) {
    this.accountType = obj?.accountType ?? ''
    this.fundCode = obj?.fundCode ?? ''
    this.mfkaisu = obj?.mfkaisu ?? ''
    this.mfgo = obj?.mfgo ?? ''
    this.paymentMethod = obj?.paymentMethod ?? '1'
  }
}
