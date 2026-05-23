export class IfaBuyingPowerForeignFormModel {
  constructor() {
    // 画面のみで使用する項目
    this.title = {
      id: 'SUB0202_010303-01',
      name: '買付余力（外国）'
    }

    this.selectedAccountType = '2'
    this.selectedCurrency = 'ALL'

    // サーバからの取得項目
    this.byCountryList = []
  }
}
