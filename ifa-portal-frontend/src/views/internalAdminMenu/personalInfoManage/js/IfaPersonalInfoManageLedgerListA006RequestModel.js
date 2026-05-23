import Logger from '@/utils/ifaLog.js'
export class IfaPersonalInfoManageLedgerListA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.loginId = obj.loginId ? obj.loginId : '' // ユーザーID
    this.processDayTime = obj.processDayTime ? obj.processDayTime : '' // 処理日時
    this.personalInfoManageId = obj.personalInfoManageId ? obj.personalInfoManageId : '' // 個人情報管理ID
    this.storageSendingMedium = obj.storageSendingMedium ? obj.storageSendingMedium : '' // 保管_送付媒体
    this.depositDestinations = obj.depositDestinations ? obj.depositDestinations : '' // 預託先
    this.destination = obj.destination ? obj.destination : '' // 提供先
    this.storageSpace = obj.storageSpace ? obj.storageSpace : '' // 保管場所
    this.preservePeriod = obj.preservePeriod ? obj.preservePeriod : '' // 保管期間
    this.disposeMethod = obj.disposeMethod ? obj.disposeMethod : '' // 廃棄方法
    this.notDispose = obj.notDispose ? obj.notDispose : '' // 破棄なし
    this.disposeDateYmd = obj.disposeDateYmd ? obj.disposeDateYmd : '' // 廃棄年月日
    this.corDepositOutline = obj.corDepositOutline ? obj.corDepositOutline : '' // 摘要_預託先
    this.corDonationOutline = obj.corDonationOutline ? obj.corDonationOutline : '' // 摘要_提供先
    this.corDepositoryOutline = obj.corDepositoryOutline ? obj.corDepositoryOutline : '' // 摘要_保管場所
  }
}
