export class IfaMutualFundAccumulateSettingBrandListInitA002RequestModel {
  constructor(obj) {
    this.fundCode = obj?.fundCode ?? ''
    this.mfkaisu = obj?.mfkaisu ?? ''
    this.mfgo = obj?.mfgo ?? ''
    this.source = obj?.source ?? ''
    this.step = obj?.step ?? null
    this.listFlag = obj?.listFlag ?? false
  }
}
