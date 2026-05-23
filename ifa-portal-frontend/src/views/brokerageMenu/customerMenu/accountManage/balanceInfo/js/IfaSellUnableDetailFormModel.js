export class IfaSellUnableDetailFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_010201-03',
      name: '売却不可明細'
    }
    // 売却不可明細一覧
    this.sellUnableDetailList = [
      {
        securityType: '', // 売却不可明細一覧.商品区分
        brandCodeBrandName: '', // 売却不可明細一覧.コード　銘柄名
        depositType: '', // 売却不可明細一覧.預り区分
        fundDepositType: '', // 売却不可明細一覧.預り区分(ファンドタイプ)
        collateralEligibleType: '', // 売却不可明細一覧.代用適格区分
        restrictionCount: '', // 売却不可明細一覧.制限数
        reason: '', // 売却不可明細一覧.理由
        limitedPeriodStart: '', // 売却不可明細一覧.制限期間開始
        limitedPeriodFinish: '', // 売却不可明細一覧.制限期間終了
        substituteStart: '', // 売却不可明細一覧.代用制限期間開始
        substituteFinish: '', // 売却不可明細一覧.代用制限期間終了
        registeredDate: '' // 売却不可明細一覧.登録日
      }
    ]
    this.noDetailMsg = '売却不可明細はありません。' // 売却不可明細なしメッセージ
  }
}
