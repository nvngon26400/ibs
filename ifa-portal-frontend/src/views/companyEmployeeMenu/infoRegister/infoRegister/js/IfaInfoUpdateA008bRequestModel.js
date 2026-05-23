import Logger from '@/utils/ifaLog.js'
export class IfaInfoUpdateA008bRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.notificationId = obj.notificationId ? obj.notificationId : '' // お知らせID
    this.viewerSetting = obj.viewerSetting ? obj.viewerSetting : '' // 閲覧者
    this.title = obj.title ? obj.title : '' // タイトル
    this.categoryIdList = obj.categoryIdList ? obj.categoryIdList : '' // カテゴリIDリスト
    this.contents = obj.contents ? obj.contents : '' // 内容
    this.url = obj.url ? obj.url : '' // URL
    this.urlComment = obj.urlComment ? obj.urlComment : '' // URL（コメント）
    this.registerFileName1 = obj.registerFileName1 ? obj.registerFileName1 : '' // 登録ファイル1（ファイル名）
    this.fileDelete1 = obj.fileDeleteName1 ? obj.fileDeleteName1 : '' // 登録ファイル1（削除）
    this.fileComment1 = obj.fileComment1 ? obj.fileComment1 : '' // 登録ファイル1（コメント）
    this.registerFileName2 = obj.registerFileName2 ? obj.registerFileName2 : '' // 登録ファイル2（ファイル名）
    this.fileDelete2 = obj.fileDeleteName2 ? obj.fileDeleteName2 : '' // 登録ファイル2（削除）
    this.fileComment2 = obj.fileComment2 ? obj.fileComment2 : '' // 登録ファイル2（コメント）
    this.registerFileName3 = obj.registerFileName3 ? obj.registerFileName3 : '' // 登録ファイル3（ファイル名）
    this.fileDelete3 = obj.fileDeleteName3 ? obj.fileDeleteName3 : '' // 登録ファイル3（削除）
    this.fileComment3 = obj.fileComment3 ? obj.fileComment3 : '' // 登録ファイル3（コメント）
    this.disclaimer = obj.disclaimer ? '1' : '0' // ディスクレーマー
    this.readManage = obj.readManage ? '1' : '0' // 既読管理
    this.t5nReadFlg = obj.t5nReadFlg ? obj.t5nReadFlg : '' // 既読フラグ
    this.notificationReferenceAuthorityList = obj.notificationReferenceAuthorityList ? obj.notificationReferenceAuthorityList : '' // お知らせ参照権限リスト
    this.individualRepList = obj.individualRepList ? obj.individualRepList : '' // 個別担当者リスト
    this.fileDirectory = obj.fileDirectory ? obj.fileDirectory : '' // A008aで取得したfileDirectory
  }
}
