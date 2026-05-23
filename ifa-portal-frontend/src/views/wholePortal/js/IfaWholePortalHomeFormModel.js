export class IfaWholePortalHomeFormModel {
  constructor() {
    // 顧客アラートリスト
    this.customerAlertList = [{
      alertId: '', // アラート分類ID
      alertDate: '-' // アラート通知日
    }]
    // 管理者アラート・コンプライアンス通信リスト
    this.managerAlertComplianceReport = {
      thisMonthUnreplyFlag: '', // 当月未回答フラグ
      titleThisMonth: '', // タイトル（当月）
      lastMonthUnreplyFlag: '', // 過去月未回答フラグ
      titleLastMonth: '' // タイトル（過去月）
    }
    // 管理者アラート・自己点検リスト
    this.managerAlertSelfAssessment = {
      registerYearMonthThisMonth: '', // 登録年月（当月）
      registerYearMonthLastMonth: '' // 登録年月（過去月）
    }
    // 国内担保不足・信用期日アラート
    this.domesticSecurityDeficientMarginDueDateAlert = [{
      notPaymentInfo: '', // 赤残
      lastOccurrenceDate: '', // 最終発生日 【初期値】"-"
      minimumDepositLessThan300000: '', // 最低保証金30万割れ
      domesticSecurityDeficientMarginDueDateAlertLastOccurrenceDate1: '', // 最終発生日 【初期値】"-"
      retentionRateLessThan31per: '', // 維持率31%未満
      domesticSecurityDeficientMarginDueDateAlertLastOccurrenceDate2: '', // 最終発生日 【初期値】"-"
      retentionRateLessThan25per: '', // 維持率25%未満
      domesticSecurityDeficientMarginDueDateAlertLastOccurrenceDate3: '', // 最終発生日 【初期値】"-"
      additionalDeposit: '', // 追加保証金発生
      domesticSecurityDeficientMarginDueDateAlertLastOccurrenceDate4: '', // 最終発生日 【初期値】"-"
      settlementDueDate: '', // 信用決済期日
      domesticSecurityDeficientMarginDueDateAlertLastOccurrenceDate5: '' // 最終発生日 【初期値】"-"
    }]

    // 外国担保不足・信用期日アラート
    this.foreignSecurityDeficientMargindueDateAlert = [{
      foreignCurrencyDeposits: '', // 外貨預り金赤残
      foreignSecurityDeficientMargindueDateAlertLastOccurrenceDate1: '', // 最終発生日 【初期値】"-"
      foreignCurrencyDepositsLessThan2500: '', // 米株信用最低保証金2,500ドル割れ
      foreignSecurityDeficientMargindueDateAlertLastOccurrenceDate2: '', // 最終発生日 【初期値】"-"
      foreignCurrencyDepositMaintenanceRateLessThan51per: '', // 米株信用維持率51%未満
      foreignSecurityDeficientMargindueDateAlertLastOccurrenceDate3: '', // 最終発生日 【初期値】"-"
      foreignCurrencyDepositMaintenanceRateLessThan35per: '', // 米株信用維持率35%未満
      foreignSecurityDeficientMargindueDateAlertLastOccurrenceDate4: '', // 最終発生日 【初期値】"-"
      foreignCurrencyAdditionalMargin: '', // 米株信用追加保証金発生
      foreignSecurityDeficientMargindueDateAlertLastOccurrenceDate5: '', // 最終発生日 【初期値】"-"
      foreignCurrencySettlementDueDate: '', // 米株信用決済期日
      foreignSecurityDeficientMargindueDateAlertLastOccurrenceDate6: '' // 最終発生日 【初期値】"-"
    }]

    // 投信
    this.mutualFund = [{
      investmentTrustBaseValueVariation: '', // 投信基準価額変動
      mutualFundLastOccurrenceDate: '' // 最終発生日 【初期値】"-"
    }]

    // 国内債券・外国債券
    this.domesticClaimForeignClaim = [{
      knockIn: '', // ノックイン
      domesticClaimForeignClaimLastOccurrenceDate1: '', // 最終発生日 【初期値】"-"
      knockOut: '', // ノックアウト
      domesticClaimForeignClaimLastOccurrenceDate2: '', // 最終発生日 【初期値】"-"
      domesticBondMaturityRedemption: '', // 国内債券の満期償還
      domesticClaimForeignClaimLastOccurrenceDate3: '', // 最終発生日 【初期値】"-"
      foreignBondMaturityRedemption: '', // 外国債券の満期償還
      domesticClaimForeignClaimLastOccurrenceDate4: '' // 最終発生日 【初期値】"-"
    }]

    // 入出金・入出庫
    this.depositWithDrawDeliverInOut = [{
      depositsAndWithdrawals: '', // 入出金
      depositWithDrawDeliverInOutLastOccurrenceDate1: '', // 最終発生日 【初期値】"-"
      checkInOut: '', // 入出庫
      depositWithDrawDeliverInOutLastOccurrenceDate2: '' // 最終発生日 【初期値】"-"
    }]

    this.unviewedThisMonth = '' // 当月末閲覧者あり
    this.unviewedPastMonth = '' // 過去月末閲覧者あり
    this.titleThisMonth = '' // タイトル（当月）
    this.titleLastMonth = '' // タイトル（過去月）

    this.unansweredThisMonth = '' // 当月末回答あり
    this.unansweredPastMonth = '' // 過去未回答あり
    this.registerYearMonthThisMonth = '' // 登録年月（当月）
    this.registerYearMonthLastMonth = '' // 登録年月（過去月）

    this.title = '' // タイトル
    this.important = '' // 重要
    this.category = '全て' // カテゴリ
    this.categoryId = '' // カテゴリID
    this.requiredFlag = '' // 必須フラグ

    // お知らせカテゴリリスト
    this.notificationCategoryList = [{
      categoryId: '', // カテゴリID
      categoryName: '', // カテゴリ名
      requiredFlag: '' // 必須フラグ
    }]
    // お知らせリスト
    this.notificationList = [
      // updateTime: '', // 更新日時
      // title: '', // タイトル
      // notificationId: '', // お知らせID
      // t4nImportance: '', // 重要度
      // registerDayTime: '', // 登録日時
      // t5nReadFlg: '', // 既読フラグ
      // ontents: '', // 内容
      // attachDocument1: '', // 添付資料（添付ファイル１）
      // attachDocumentComment1: '', // コメント（添付ファイル１）
      // attachDocument2: '', // 添付資料（添付ファイル２）
      // attachDocumentComment2: '', // コメント（添付ファイル２）
      // attachDocument3: '', // 添付資料（添付ファイル３）
      // attachDocumentComment3: '', // コメント（添付ファイル３）
      // url: '',
      // urlComment: '',
      // disclaimer: '' // ディスクレーマー
    ]
    this.importantMark = '' // 重要マーク
    this.readDate = '' // 閲覧日
    this.title = '' // タイトル
    this.contents = '' // 内容
    this.url = '' // URL
    this.urlComment // URLコメント
    this.attachDocument1 = '' // 添付資料（添付ファイル１）
    this.attachDocumentComment1 = '' // コメント（添付ファイル１）
    this.attachDocument2 = '' // 添付資料（添付ファイル２）
    this.attachDocumentComment2 = '' // コメント（添付ファイル２）
    this.attachDocument3 = '' // 添付資料（添付ファイル３）
    this.attachDocumentComment3 = '' // コメント（添付ファイル３）
    this.disclaimer = '' // ディスクレーマー
    this.fileDirectory = '' // ファイルディレクトリ
  }
}
