import Logger from '@/utils/ifaLog.js'
export class IfaInfoRegisterLookupA009RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.notificationId = obj.notificationId ? obj.notificationId : '' // お知らせID
    if (obj.attachFile1) { this.attachFile1 = obj.attachFile1 ? obj.attachFile1 : '' } // 添付ファイル１
    if (obj.attachFile2) { this.attachFile2 = obj.attachFile2 ? obj.attachFile2 : '' } // 添付ファイル２
    if (obj.attachFile3) { this.attachFile3 = obj.attachFile3 ? obj.attachFile3 : '' } // 添付ファイル３
  }
}
