export class IfaMutualFundAccumulateSettingInputInitA001RequestModel {
  constructor(obj) {
    this.fundCode = obj?.fundCode ?? ''
    this.mfkaisu = obj?.mfkaisu ?? ''
    this.mfgo = obj?.mfgo ?? ''
    this.source = obj?.source ?? ''
    this.listFlag = obj?.listFlag ?? false
    this.step = obj?.step ?? null
  }
}
