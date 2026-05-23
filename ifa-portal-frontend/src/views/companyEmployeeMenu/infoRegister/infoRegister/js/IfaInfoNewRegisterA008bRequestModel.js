import Logger from '@/utils/ifaLog.js'
export class IfaInfoNewRegisterA008bRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.viewerSetting = obj.viewerSetting ? obj.viewerSetting : '' // 閲覧者
    this.title = obj.title ? obj.title : '' // タイトル
    this.categoryIdList = obj.categoryIdList ? obj.categoryIdList : '' // カテゴリIDリスト
    this.content = obj.content ? obj.content : '' // 内容
    this.url = obj.url ? obj.url : '' // URL
    this.urlComment = obj.urlComment ? obj.urlComment : '' // URL（コメント）
    this.registerFile1 = obj.registerFile1 ? obj.registerFile1 : '' // 登録ファイル1
    this.fileComment1 = obj.fileComment1 ? obj.fileComment1 : '' // 登録ファイル1（コメント）
    this.registerFile2 = obj.registerFile2 ? obj.registerFile2 : '' // 登録ファイル2
    this.fileComment2 = obj.fileComment2 ? obj.fileComment2 : '' // 登録ファイル2（コメント）
    this.registerFile3 = obj.registerFile3 ? obj.registerFile3 : '' // 登録ファイル3
    this.fileComment3 = obj.fileComment3 ? obj.fileComment3 : '' // 登録ファイル3（コメント）
    this.disclaimer = obj.disclaimer ? '1' : '0' // ディスクレーマー
    this.readManage = obj.readManage ? '1' : '0' // 既読管理
    this.subjectSendFlag = obj.subjectSendFlag ? '1' : '0' // 件名（送信しない／送信する）
    this.subject = obj.subject ? obj.subject : '' // 件名
    this.notificationReferenceAuthorityList = obj.notificationReferenceAuthorityList ? obj.notificationReferenceAuthorityList : '' // お知らせ参照権限リスト
    this.individualRepList = obj.individualRepList ? obj.individualRepList : '' // 個別担当者リスト
  }
}
