export class IfaMutualFundDetailInfoFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0401-03',
      name: '投信詳細情報'
    }
    this.brandName = '' // 銘柄名
    this.updateTime = '' // 更新日時
    this.priceDate = '' // 基準価額日付
    this.price = '' // 基準価額
    this.basePriceUnit = '' // 基準価額単位
    this.diff = '' // 前日比
    this.ratio = '' // 前日比率
    this.junshisan = '' // 純資産
    this.w52Takane = '' // 52週高値
    this.w52Takanedate = '' // 52週高値日付
    this.w52Yasune = '' // 52週安値
    this.w52Yasunedate = '' // 52週安値日付

    this.thisMonthClosedDayList = [] // 当月休場日リスト
    // this.closedDay = '' // 休場日
    // this.businessDayFlag = '' // 営業日フラグ

    this.nextMonthClosedDayList = [] // 翌月休場日リスト
    // this.closedDay = '' // 休場日
    // this.businessDayFlag = '' // 営業日フラグ

    this.deadlineDateRecent = '' // 締切日時（直近）
    this.deadlineDateNext = '' // 締切日時（次回）
    this.deadlines = '' // 締切時間
    this.shrineNotificationUpdateDate = '' // 当社からのお知らせ更新日
    this.shrineNotificationContents = '' // 当社からのお知らせコンテンツ
    this.outsourcingCompanyNotificationUpdateDate = '' // 委託会社からのお知らせ更新日
    this.outsourcingCompanyNotificationContents = '' // 委託会社からのお知らせコンテンツ
    this.wealthAdvisorCommentUpdateDate = '' // ウエルスアドバイザー社のコメント更新日
    this.wealthAdvisorCommentContents = '' // ウエルスアドバイザー社のコメントコンテンツ
    this.course = '' // 取引コース
    this.kyoukaiCd = '' // 協会コード（SQL001）
    this.fundTypeName = '' // 定期売却
    this.operationPolicy = '' // 運用方針
    this.buyUnitWord = '' // 買付単位
    this.sellSharesWord = '' // 売却単位
    this.individualPrincipal = '' // 当初一口当り元本
    this.buyCommLeft = '' // 買付手数料（税込）左
    this.buyCommRight = '' // 買付手数料（税込）右
    this.buyCommNisaLeft = '' // 買付手数料（税込）(NISA)左
    this.buyCommNisaRight = '' // 買付手数料（税込）(NISA)右
    this.trustFeeAmount = '' // 信託報酬(税込)/年
    this.partialRedemptionCharge = '' // 信託財産留保額
    this.feeContent = '' // 解約手数料（税込）
    this.preferentialRedemptionApplication = '' // 償還優遇の適用
    this.shrineDeadlines = '' // 当社締切時間
    this.tradeDate = '' // 約定日
    this.settlementDate = '' // 受渡日
    this.settleLastDay = '' // 決算日
    this.dividendHandling = '' // 分配金
    this.distributionReceiveMethodWord = '' // 分配金受取方法
    this.settingDate = '' // 設定日
    this.redemptionDate = '' // 償還日
  }
}
