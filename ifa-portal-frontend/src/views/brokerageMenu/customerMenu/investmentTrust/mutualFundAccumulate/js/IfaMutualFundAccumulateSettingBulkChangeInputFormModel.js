import Logger from '@/utils/ifaLog.js'

export class IfaMutualFundAccumulateSettingBulkChangeInputFormModel {
  constructor(obj) {
    Logger.debug(obj)

    const transformBulkChangeList = item => item?.bulkChangeList.map(v => ({
      ...v,
      // 積立コース
      courseType: v?.courseType,
      // 毎週
      settingReserveWeek: v?.settingReserveWeek ?? '0',
      // 毎月
      settingReserveMonth: v?.courseType === '3' ? v?.settingReserveDay : '',
      // 複数日
      settingReserveMultiDay: v?.settingReserveMultiDay ?? [],
      // 奇数月
      settingReserveOddDay: v?.courseType === '5' ? v?.settingReserveDay : '',
      // 偶数月
      settingReserveEvenDay: v?.courseType === '6' ? v?.settingReserveDay : '',
      // 積立金額
      settingAmount: v?.settingAmount ?? ''
    }))

    // bulk list
    this.dynamicList = transformBulkChangeList(obj)
    // 勧誘区分
    this.kanyuKbn = obj?.kanyuKbn ?? ''
    // 受付方法
    this.receiveMethod = obj?.receiveMethod ?? ''
    // ご注意事項
    this.checkMadoAki = obj?.checkMadoAki ?? false
  }
}
