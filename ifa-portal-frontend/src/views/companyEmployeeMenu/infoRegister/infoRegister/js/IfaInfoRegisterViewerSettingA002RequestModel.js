import Logger from '@/utils/ifaLog.js'
export class IfaInfoRegisterViewerSettingA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.notificationId = obj.notificationId ? obj.notificationId : '' // お知らせID
    this.corReferenceCondition = obj.corReferenceCondition ? obj.corReferenceCondition : '' // 閲覧者
    this.notificationReferenceAuthorityList = obj.notificationReferenceAuthorityList ? obj.notificationReferenceAuthorityList : [] // お知らせ参照権限リスト
    this.individualRepList = obj.individualRepList ? obj.individualRepList : [] // 個別担当者リスト
  }
}
