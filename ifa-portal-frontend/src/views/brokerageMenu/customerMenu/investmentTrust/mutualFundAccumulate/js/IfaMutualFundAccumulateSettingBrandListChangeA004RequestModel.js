export class IfaMutualFundAccumulateSettingBrandListChangeA004RequestModel {
  constructor(obj) {
    this.fundCode = obj?.fundCode ?? ''
    this.mfkaisu = obj?.mfkaisu ?? ''
    this.mfgo = obj?.mfgo ?? ''
    this.accountType = obj?.accountType ?? ''
    this.paymentMethod = obj?.paymentMethod ?? '1'
  }
}
