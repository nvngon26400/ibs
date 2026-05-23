// IfaMutualFundAccumulateSettingInputConfirmA010RequestModel
import Logger from '@/utils/ifaLog.js'

export class IfaMutualFundAccumulateSettingChangeInputConfirmA009RequestModel {
  constructor(obj, info) {
    Logger.debug(obj)

    const settingReserveMultiDay = (arr) => {
      if (!arr || arr.length === 0) return null

      return `${arr.sort((a, b) => a - b).join(';')};`
    }

    const mapSettingReverseDay = () => {
      if (obj?.courseType === '3') return obj?.settingReserveMonth
      if (obj?.courseType === '5') return obj?.settingReserveOddDay
      if (obj?.courseType === '6') return obj?.settingReserveEvenDay

      return null
    }

    this.fundCode = info.fundCode
    // ファンドコード（回数）
    this.mfkaisu = info.mfkaisu
    // ファンドコード（号）
    this.mfgo = info.mfgo
    this.brandCode = `${info.mfkaisu}.${info.mfgo}`
    // 旧ジュニアNISA口座開設有無
    this.openedJnisa = info.openedJnisa
    // 決済方法
    this.paymentMethod = obj?.paymentMethod ?? '1'
    // ご注意事項
    this.checkMadoAki = obj?.checkMadoAki && obj?.checkMadoAki?.length > 0 ? '1' : '0'
    // 預り区分
    this.accountType = obj.accountType
    // checkbox option -> NISA枠ぎりぎり注文
    // 積立買付単位
    this.reserveOrderUnit = info?.reserveOrderUnit
    this.nisaBarelyBuyingType = obj.nisaOptions.includes('1') ? '2' : '1'
    this.nisaBarelyBuyingTypeShow = info.reserveOrderUnit === '0' && (obj.accountType === 'H' || obj.accountType === 'I') ? '1' : '0'
    // checkbox option -> 課税枠シフト注文
    this.taxShiftType = obj.nisaOptions.includes('2') ? '2' : '1'
    this.taxShiftTypeShow = info.reserveOrderUnit === '0' && (obj.accountType === 'H') ? '1' : '0'
    // 積立コース
    this.courseType = obj.courseType
    // 毎月、奇数月、偶数月の場合 設定日
    this.settingReserveDay = mapSettingReverseDay()
    // 積立毎週設定
    this.settingReserveWeek = obj.settingReserveWeek !== '0' ? obj.settingReserveWeek : null
    // 積立複数日設定
    this.settingReserveMultiDay = settingReserveMultiDay(obj.settingReserveMultiDay)
    // 積立金額 -> 金額
    this.settingAmount = obj.settingAmount
    // ボーナス月設定
    this.settingBonusFlag = obj.settingBonusFlag
    // ボーナス月設定 -> 金額
    this.settingBonusAmount = obj.settingBonusAmount === '' ? null : obj.settingBonusAmount
    // ボーナス月設定 -> ボーナス１設定月
    this.settingBonus1Month = obj.settingBonus1Month !== '00' ? obj.settingBonus1Month : null
    // ボーナス月設定 -> ボーナス１設定日
    this.settingBonus1Day = obj.settingBonus1Day !== '00' ? obj.settingBonus1Day : null
    // ボーナス月設定 -> ボーナス１設定月
    this.settingBonus2Month = obj.settingBonus2Month !== '00' ? obj.settingBonus2Month : null
    // ボーナス月設定 -> ボーナス１設定日
    this.settingBonus2Day = obj.settingBonus2Day !== '00' ? obj.settingBonus2Day : null
    // 勧誘区分
    this.kanyuKbn = obj.kanyuKbn === '' ? null : obj.kanyuKbn
    // 受付方法
    this.receiveMethod = obj.receiveMethod === '' ? null : obj.receiveMethod
  }
}
