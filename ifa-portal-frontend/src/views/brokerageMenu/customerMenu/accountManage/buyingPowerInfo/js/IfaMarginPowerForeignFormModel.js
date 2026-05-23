export class IfaMarginPowerForeignFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_010304-01',
      name: '信用余力（米株）'
    }
    // 追加保証金リスト
    this.addDeposit = []
    // 預り金不足リスト
    this.depositDeficient = []
    // 新規建不足リスト
    this.newPositionDeficient = []
    // 信用取引リスト
    this.marginTrade = []
    // 余力情報
    this.foreignNewBuildingCapacity = '' // 余力情報_信用建余力
    this.marginDepositRate8 = '' // 余力情報_委託保証金率
    this.realTimeMarginDepositRate = '' // 余力情報_リアル委託保証金率
    this.usdSecurityDeposit = '' // 余力情報_米ドル保証金
    this.substituteSecurities = '' // 余力情報_代用有価証券
    // 委託保証金率リスト
    this.entrustDepositTransition = []
    // 追証フラグ
    this.marginCallFlag = ''
    // 預り金不足フラグ
    this.depositDeficientFlag = ''
    // 新規建不足フラグ
    this.newPositionDeficientFlag = ''
  }
}
