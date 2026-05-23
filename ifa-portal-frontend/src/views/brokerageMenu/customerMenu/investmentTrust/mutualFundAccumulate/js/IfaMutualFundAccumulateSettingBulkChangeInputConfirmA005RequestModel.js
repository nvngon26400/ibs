export class IfaMutualFundAccumulateSettingBulkChangeInputConfirmA005RequestModel {
  constructor(formData, info) {
    const settingReserveMultiDay = arr => {
      if (!arr || arr.length === 0) return null

      return `${arr.sort((a, b) => a - b).join(';')};`
    }

    const mapSettingReverseDay = x => {
      if (x?.courseType === '3') return x?.settingReserveMonth
      if (x?.courseType === '5') return x?.settingReserveOddDay
      if (x?.courseType === '6') return x?.settingReserveEvenDay

      return null
    }

    const transformBulkChangeList = item => item?.dynamicList.map(v => ({
      // 協会コード
      fundCode: v.fundCode,
      // 預り区分
      accountType: v.accountType,
      // ファンドコード（回数）
      mfkaisu: v.mfkaisu,
      // ファンドコード（号）
      mfgo: v.mfgo,
      // 積立コース
      courseType: v.courseType,
      // 積立毎週設定
      settingReserveWeek: v.settingReserveWeek !== '0' ? v.settingReserveWeek : null,
      // 毎月、奇数月、偶数月の場合 設定日
      settingReserveDay: mapSettingReverseDay(v),
      // 積立複数日設定
      settingReserveMultiDay: settingReserveMultiDay(v.settingReserveMultiDay),
      // 積立金額 -> 金額
      settingAmount: v.settingAmount
    }))

    // bulk list
    this.bulkChangeInputDataList = transformBulkChangeList(formData)
    // 旧ジュニアNISA口座開設有無
    this.openedJnisa = info.openedJnisa
    // ご注意事項
    this.checkMadoAki = formData?.checkMadoAki ? '1' : '0'
    // 勧誘区分
    this.kanyuKbn = formData.kanyuKbn === '' ? null : formData.kanyuKbn
    // 受付方法
    this.receiveMethod = formData.receiveMethod === '' ? null : formData.receiveMethod
  }
}
