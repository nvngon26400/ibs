export class IfaMutualFundAccumulateSettingInputFormModel {
  constructor(obj) {
    this.paymentMethod = '1'
    this.accountType = obj?.accountTypeOptions?.[0]?.key
    this.nisaOptions = []
    this.courseType = '3'
    this.settingReserveWeek = '0'
    this.settingReserveMonth = '00'
    this.settingReserveOddDay = '00'
    this.settingReserveEvenDay = '00'
    this.settingReserveMultiDay = []
    this.settingAmount = ''
    this.settingBonusAmount = ''
    this.settingBonus1Month = '00'
    this.settingBonus1Day = '00'
    this.settingBonus2Month = '00'
    this.settingBonus2Day = '00'
    this.kanyuKbn = ''
    this.receiveMethod = ''
    this.settingBonusFlag = '2'
    this.checkMadoAki = []
  }
}
