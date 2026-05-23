export class IfaBrandPriceInfoRequestModel {
  constructor(obj) {
    this.brandCode = obj.brandCode ? obj.brandCode : ''
    this.market = obj.market ? obj.market : ''
  }
}
