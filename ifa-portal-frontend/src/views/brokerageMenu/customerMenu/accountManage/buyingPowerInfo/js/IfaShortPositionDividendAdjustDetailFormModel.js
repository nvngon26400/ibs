export class IfaShortPositionDividendAdjustDetailFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_010302-02',
      name: '売建配当調整金明細'
    }
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    // 売建配当調整金明細リスト
    this.detailList = [
      {
        brandCode: '', // 売建配当調整金明細リスト.銘柄コード
        brandName: '', // 売建配当調整金明細リスト.銘柄名
        expectedDividend: '', // 売建配当調整金明細リスト.予想配当
        stockQuantity: '', // 売建配当調整金明細リスト.株数
        restraintAmount: '', // 売建配当調整金明細リスト.拘束金
        rightLastDate: '' // 売建配当調整金明細リスト.権利付最終日
      }
    ]
    this.searchResultCount = '' // 検索結果件数
  }
}
