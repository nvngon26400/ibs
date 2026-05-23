export class IfaMutualFundAccumulateSettingConfirmA001RequestModel {
  constructor(obj, formData, info) {
    this.fundCode = obj.fundCode
    // ファンドコード（回数）
    this.mfkaisu = obj.mfkaisu
    // ファンドコード（号）
    this.mfgo = obj.mfgo
    this.brandCode = obj.brandCode
    // 旧ジュニアNISA口座開設有無
    this.openedJnisa = obj.openedJnisa
    // 決済方法
    this.paymentMethod = info?.paymentMethod ?? '1'
    // ご注意事項
    this.checkMadoAki = obj.checkMadoAki
    // 預り区分
    this.accountType = obj.accountType
    // 積立買付単位
    this.reserveOrderUnit = info?.reserveOrderUnit
    // checkbox option -> NISA枠ぎりぎり注文
    this.nisaBarelyBuyingType = obj.nisaBarelyBuyingType
    this.nisaBarelyBuyingTypeShow = obj.nisaBarelyBuyingTypeShow
    // checkbox option -> 課税枠シフト注文
    this.taxShiftType = obj.taxShiftType

    this.taxShiftTypeShow = obj.taxShiftTypeShow
    // 積立コース
    this.courseType = obj.courseType
    // 毎月、奇数月、偶数月の場合 設定日
    this.settingReserveDay = obj.settingReserveDay
    // 積立毎週設定
    this.settingReserveWeek = obj.settingReserveWeek
    // 積立複数日設定
    this.settingReserveMultiDay = obj.settingReserveMultiDay
    // 積立金額 -> 金額
    this.settingAmount = obj.settingAmount
    // ボーナス月設定
    this.settingBonusFlag = obj.settingBonusFlag
    // ボーナス月設定 -> 金額
    this.settingBonusAmount = obj.settingBonusAmount
    // ボーナス月設定 -> ボーナス１設定月
    this.settingBonus1Month = obj.settingBonus1Month
    // ボーナス月設定 -> ボーナス１設定日
    this.settingBonus1Day = obj.settingBonus1Day
    // ボーナス月設定 -> ボーナス１設定月
    this.settingBonus2Month = obj.settingBonus2Month
    // ボーナス月設定 -> ボーナス１設定日
    this.settingBonus2Day = obj.settingBonus2Day
    // 勧誘区分
    this.kanyuKbn = obj.kanyuKbn
    // 受付方法
    this.receiveMethod = obj.receiveMethod

    // ワーニング申請
    this.complianceRankCheckConfirm = info?.complianceRankCheck?.message
      ? formData.complianceRankCheckConfirm
        ? '1'
        : '0'
      : ''
    // 開始基準の確認
    this.complianceRankCheckStartBaseConfirm = info?.complianceRankCheck?.startCriteriaConfirmMsg
      ? formData.complianceRankCheckStartBaseConfirm === '1'
        ? '1'
        : '0'
      : ''
    // 注意情報の確認
    this.noticeInfoAlertConfirm = info?.noticeInfoAlert
      ? formData.noticeInfoAlertConfirm === '1'
        ? '1'
        : '0'
      : ''
    // 重要なお知らせの確認
    this.noticeAlertConfirm = info?.noticeAlert
      ? formData.noticeAlertConfirm === '1'
        ? '1'
        : '0'
      : ''
    // 確認書の受け入れの確認
    this.confirmDocumentAlertConfirm = info?.confirmDocumentAlert
      ? formData.confirmDocumentAlertConfirm === '1'
        ? '1'
        : '0'
      : ''

    this.complianceRankCheck = {
      message: info?.complianceRankCheck?.message ?? '',
      invitationCheck: info?.complianceRankCheck?.invitationCheck ?? '',
      startCriteriaConfirmMsg: info?.complianceRankCheck?.startCriteriaConfirmMsg ?? ''
    }

    this.noticeInfoAlert = info?.noticeInfoAlert ?? ''
    this.noticeAlert = info?.noticeAlert ?? ''
    this.confirmDocumentAlert = info?.confirmDocumentAlert ? info?.confirmDocumentAlert : ''
  }
}
