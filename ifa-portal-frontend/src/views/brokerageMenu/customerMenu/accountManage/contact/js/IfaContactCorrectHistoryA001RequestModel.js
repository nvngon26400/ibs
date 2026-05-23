import Logger from '@/utils/ifaLog.js'
export class IfaContactCorrectHistoryA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.ifaToiawaseNo = obj.ifaToiawaseNo ? obj.ifaToiawaseNo : '' // IFA問合せNO
    this.tourokuGroup = obj.tourokuGroup ? obj.tourokuGroup : '' // 登録グループ
  }
}
