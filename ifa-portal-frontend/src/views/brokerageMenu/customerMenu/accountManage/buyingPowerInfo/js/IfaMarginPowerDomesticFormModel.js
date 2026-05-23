export class IfaMarginPowerDomesticFormModel {
  constructor() {
    this.screenId = 'SUB0202_010302-01'
    this.screenTitle = '信用余力(国内)'
    // 追証情報
    this.marginCallinfoList = [{
      cancellationDeadlineRecordingDate: '', // 追証情報.解消期限/入金・入庫計上日（T-4～T+0）
      cancellationDeadlineRecordingDateDefiniteApproximate: '', // 追証情報.解消期限/入金・入庫計上日（T-4～T+0）(確定・概算)
      cancellationDeadlineRecordingDateSettlementDate: '', // 追証情報.解消期限/入金・入庫計上日受渡日（T+1）
      initialMarginAmount: '', // 追証情報.当初追証額（T-4～T+0）
      marginCallDate: '', // 追証情報.追証発生日（T-4～T+0）
      depositShortage: '', // 追証情報.預り金不足額（T-4～T+0））
      depositAmount: '', // 追証情報.入金額（T-4～T+0）
      settlementOpenInterestYen: '' // 追証情報.入庫額 及び 決済建玉充当額（決済建玉金額×20%）（T-4～T+0）
    }]
    this.deliveryDateDepositScheduleTotalAmount = '' // 最下段入金額

    // 追証ステータス関連
    this.marginStatus = '' // 追証ステータス
    this.plannedDateForcedRedemptionOpenInterest = '' // 追加保証金未解消に伴う建玉強制返済予定日時
    this.marginDepositScheduleDate = '' // 追証入金予定日
    this.amountrequiredtocancelallAdditionalsecuritydeposits = '' // 追加保証金を全て解消するための必要額
    this.marginstandardmaintenanceRate = '' // 追証基準維持率
    this.marginAmount = '' // 追証金額
    this.marginCallDate = '' // 追証発生日
    this.marginCancellationDeadline = '' // 追証解消期限
    this.deadlineforforcedrepaymentOfopeninterest = '' // 建玉強制返済執行期限

    // 信用余力サマリー情報（信用建余力）
    this.creditCapacity = '' // 信用建余力
    this.cashOnDelivery = '' // 現引可能額
    this.buyingRemainingPower = '' // 現物買付余力
    this.withdrawalCapacity = '' // 出金・振替可能額
    this.openInterestLimit = '' // 建玉総限度額

    // 信用余力サマリー情報（委託保証金率の推移）
    this.consignmentDeposit = '' // 前日委託保証金率(T+0)

    // 信用余力サマリー情報（各種保証金率）
    this.newOpenInterest = '' // 新規建保証金率（信用建余力の計算に適用）
    this.chache = '' // 現金保証金率（新規建に必要な保証金現金の計算に適用）
    this.inKindPurchase = '' // 現物買付保証金率（現物の買付余力の計算に適用）
    this.withdrawTransfer = '' // 出金・振替保証金率（出金・振替可能額の計算に適用）

    // 信用余力詳細情報（必要保証金）
    this.previousDayBaseOpenInterestAmount = '' // 前日基準未決済建玉金額
    // 自動スイープ対象フラグ
    this.autoSweepTargetFlag = ''

    // 受渡日（T+0）～受渡日（T+5）
    this.deliveryDateToFiveDaysLaterList = [{
      // 信用余力サマリー情報（委託保証金率の推移）
      settlementDateT0toT5: '', // 受渡日（T+0）～受渡日（T+5）.受渡日
      marginDepositRate9: '', // 受渡日（T+0）～受渡日（T+5）.委託保証金率
      domesticReferenceMarginDeposit: '', // 受渡日（T+0）～受渡日（T+5）.参考委託保証金率
      openInterestCostYen: '', // 受渡日（T+0）～受渡日（T+5）.建玉必要額
      additionalMarginPlannedAmount: '', // 受渡日（T+0）～受渡日（T+5）.追証予定額
      shortageDepositDue: '0', // 受渡日（T+0）～受渡日（T+5）.預り金不足予定額 【初期値】0
      // 信用余力詳細情報（信用余力情報）
      reservePowerDetailDeliveryDateToFiveDaysLaterMarginDepositRate9: '', // 余力詳細_受渡日（T+0）～受渡日（T+5）.委託保証金率
      over31per: '', // 受渡日（T+0）～受渡日（T+5）.31％超分
      over31perOurCompany: '', // 受渡日（T+0）～受渡日（T+5）.31%超分(当社のみ)
      over31perOurCompanySsnb: '', // 受渡日（T+0）～受渡日（T+5）.31%超分(当社+SSNB)
      creditCapacity: '', // 受渡日（T+0）～受渡日（T+5）.信用建余力
      over30per: '', // 受渡日（T+0）～受渡日（T+5）.30％超分
      over30perOurCompany: '', //  受渡日（T+0）～受渡日（T+5）.30%超分(当社のみ)
      over30perOurCompanySsnb: '', //  受渡日（T+0）～受渡日（T+5）.30%超分(当社+SSNB)
      over302perOurCompany: '', //  受渡日（T+0）～受渡日（T+5）.30.2%超分(当社のみ)
      over302perOurCompanySsnb: '', //  受渡日（T+0）～受渡日（T+5）.30.2%超分(当社+SSNB)
      buyingRemainingPower: '', // 受渡日（T+0）～受渡日（T+5）.現物買付余力
      withdrawalCapacity: '', // 受渡日（T+0）～受渡日（T+5）.出金振替可能額
      // 信用余力詳細情報（委託保証金）
      consignmentSecurityDepositDeliveryDate: '', // 受渡日（T+0）～受渡日（T+5）.委託保証金受渡日
      remainingCapacityWkTop: '', //  受渡日（T+0）～受渡日（T+5）.保証金現金（余力WK）（上段）
      remainingCapacityWkUnder: '', //  受渡日（T+0）～受渡日（T+5）.保証金現金（余力WK）（下段）
      marginDepositBeforeSsnbCalculateTop: '', //  受渡日（T+0）～受渡日（T+5）.SSNB精算前保証金現金(余力WK)（上段）
      marginDepositBeforeSsnbCalculateUnder: '', //  受渡日（T+0）～受渡日（T+5）.SSNB精算前保証金現金(余力WK)（下段）
      ssnbCalculateAmount: '', // 受渡日（T+0）～受渡日（T+5）.SSNB精算額
      marginDepositAfterSsnbCalculate: '', // 受渡日（T+0）～受渡日（T+5）.SSNB精算後保証金
      alternativeReceipt: '', // 受渡日（T+0）～受渡日（T+5）.代用入庫額
      substituteDeliveryAmount: '', // 受渡日（T+0）～受渡日（T+5）.代用出庫額
      alternativeDeposit: '', // 受渡日（T+0）～受渡日（T+5）.保証金代用
      totalDeposit: '', // 受渡日（T+0）～受渡日（T+5）.保証金合計
      valuationLoss: '', //  受渡日（T+0）～受渡日（T+5）.評価損
      domesticPositionValuation: '', //  受渡日（T+0）～受渡日（T+5）.評価損益
      settlement: '', // 受渡日（T+0）～受渡日（T+5）.決済損益
      paymentExpence: '', // 受渡日（T+0）～受渡日（T+5）.支払諸経費
      yenActualDeposit: '', // 受渡日（T+0）～受渡日（T+5）.実質保証金
      hybridDepositBalance: '', // 受渡日（T+0）～受渡日（T+5）.SBIハイブリッド預金残高
      realInsuranceAndHybridDepositBalance: '', // 受渡日（T+0）～受渡日（T+5）.実保+SBIハイブリッド預金
      depositAndHybridDepositBalance: '', //  受渡日（T+0）～受渡日（T+5）.当社保証金+SBIハイブリッド預金(現金部分のみ)
      // 信用余力詳細情報（必要保証金）
      orderOpenInterest: '', // 受渡日（T+0）～受渡日（T+5）.注文中建玉金額
      unsetteledOpenInterest: '', // 受渡日（T+0）～受渡日（T+5）.未決済建玉金額
      setteledOpenInterest: '', // 受渡日（T+0）～受渡日（T+5）.決済済建玉金額
      currentDeliverlyOpenInterest: '', // 受渡日（T+0）～受渡日（T+5）.現引現渡建玉金額
      openInterstDeposit: '', // 受渡日（T+0）～受渡日（T+5）.建玉必要保証金
      openInterstDepositCash: '', //  受渡日（T+0）～受渡日（T+5）.建玉必要保証金(うち現金)
      purchaseDeposit30: '', //  受渡日（T+0）～受渡日（T+5）.買付必要保証金（30％）
      purchaseDeposit30Cash: '', //  受渡日（T+0）～受渡日（T+5）.買付必要保証金（30.％）(うち現金
      purchaseDeposit32: '', //  受渡日（T+0）～受渡日（T+5）.買付必要保証金（30.2％）
      purchaseDeposit32AdditionalCollateral: '', // 受渡日（T+0）～受渡日（T+5）.買付必要保証金（30.2％）(増担保)
      purchaseDeposit32Divined: '', //  受渡日（T+0）～受渡日（T+5）.買付必要保証金（30.2％）[配当金]
      returnDeposit: '', // 受渡日（T+0）～受渡日（T+5）.返却必要保証金
      returnDepositCash: '', // 受渡日（T+0）～受渡日（T+5）.返却必要保証金(うち現金)
      returnDepositAdditionalCollateral: '', // 受渡日（T+0）～受渡日（T+5）.返却必要保証金[増担保]
      returnDepositDivined: '', // 受渡日（T+0）～受渡日（T+5）.返却必要保証金[配当金]
      currentDeliverlyDeposit: '', // 受渡日（T+0）～受渡日（T+5）.現引・現渡必要保証金
      creditOrderDeposit: '', // 受渡日（T+0）～受渡日（T+5）.信用取引必要保証金（※）
      innerDeposit: '' // 受渡日（T+0）～受渡日（T+5）.当社必要保証金（※）
    }]
  }
}
