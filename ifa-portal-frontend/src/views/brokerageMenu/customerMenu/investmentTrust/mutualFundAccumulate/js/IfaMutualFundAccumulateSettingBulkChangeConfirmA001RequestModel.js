export class IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestModel {
  constructor(formData, info) {
    const transformBulkChangeList = (formData, info) => {
      const { dynamicList } = formData
      if (!info?.bulkChangeList || !dynamicList) return []

      return info.bulkChangeList.reduce((acc, v, idx) => {
        const {
          complianceRankCheck,
          reserveSettingChangeAfter
        } = v

        const {
          message,
          startCriteriaConfirmMsg
        } = complianceRankCheck ?? {}

        const {
          fundCode,
          mfkaisu,
          mfgo,
          brandCode,
          accountType,
          settingAmount,
          courseType,
          settingReserveDay,
          settingReserveWeek,
          settingReserveMultiDay
        } = reserveSettingChangeAfter ?? {}

        const matchedItem = dynamicList[idx]

        if (matchedItem) {
          acc.push({
            fundCode,
            mfkaisu,
            mfgo,
            brandCode,
            accountType,
            settingAmount,
            courseType,
            settingReserveDay,
            settingReserveWeek,
            settingReserveMultiDay,
            complianceRankCheckConfirm: message
              ? matchedItem?.complianceRankCheckConfirm
                ? '1'
                : '0'
              : '',
            complianceRankCheckStartBaseConfirm: startCriteriaConfirmMsg
              ? matchedItem?.complianceRankCheckStartBaseConfirm === '1'
                ? '1'
                : '0'
              : ''
          })
        }

        return acc
      }, [])
    }

    // bulk list
    this.bulkChangeInputDataList = transformBulkChangeList(formData, info)
    // 旧ジュニアNISA口座開設有無
    this.openedJnisa = info?.openedJnisa
    // ご注意事項
    this.checkMadoAki = info?.checkMadoAki
    // 勧誘区分
    this.kanyuKbn = info?.kanyuKbn
    // 受付方法
    this.receiveMethod = info?.receiveMethod

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
    // 注意情報アラート（全角半角）
    this.noticeInfoAlert = info?.noticeInfoAlert ? info?.noticeInfoAlert : ''
    // お知らせアラート（全角半角）
    this.noticeAlert = info?.noticeAlert ? info?.noticeAlert : ''
    // 確認書受け入れアラート
    this.confirmDocumentAlert = info?.confirmDocumentAlert ? info?.confirmDocumentAlert : ''
  }
}
