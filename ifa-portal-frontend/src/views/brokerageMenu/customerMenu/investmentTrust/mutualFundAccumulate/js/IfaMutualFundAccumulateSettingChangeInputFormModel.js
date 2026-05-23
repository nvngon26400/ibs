export class IfaMutualFundAccumulateSettingChangeInputFormModel {
  constructor(obj) {
    const nisaOptions = (obj) => {
      // ■NISAぎりぎり注文チェック＝チェックありの場合   -->"2"
      // ■NISAぎりぎり注文チェック＝チェックなしの場合   -->"1"
      // ■上記以外 --> "0"
      const arr = []
      if (obj.nisaBarelyBuyingType === '2') {
        arr.push('1')
      }
      if (obj.taxShiftType === '2') {
        arr.push('2')
      }

      return arr
    }

    this.paymentMethod = '1'
    this.accountType = obj?.accountType
    this.nisaOptions = nisaOptions(obj)
    this.courseType = obj?.courseType
    this.settingReserveWeek = obj?.settingReserveWeek
    this.settingReserveMonth = obj?.courseType === '3' ? obj?.settingReserveDay : ''
    this.settingReserveOddDay = obj?.courseType === '5' ? obj?.settingReserveDay : ''
    this.settingReserveEvenDay = obj?.courseType === '6' ? obj?.settingReserveDay : ''
    this.settingReserveMultiDay = obj?.settingReserveMultiDay
    this.settingAmount = obj?.settingAmount ?? ''
    this.settingBonusAmount = obj?.settingBonusAmount ?? ''
    this.settingBonus1Month = obj?.settingBonus1Month ?? '00'
    this.settingBonus1Day = obj?.settingBonus1Day ?? '00'
    this.settingBonus2Month = obj?.settingBonus2Month ?? '00'
    this.settingBonus2Day = obj?.settingBonus2Day ?? '00'
    this.kanyuKbn = obj?.kanyuKbn ?? ''
    this.receiveMethod = obj?.receiveMethod ?? ''
    this.settingBonusFlag = obj?.settingBonusFlag
    this.checkMadoAki = obj?.checkMadoAki
  }
}
