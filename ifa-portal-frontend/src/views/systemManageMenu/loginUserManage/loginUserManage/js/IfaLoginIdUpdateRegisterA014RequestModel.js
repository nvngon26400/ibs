import Logger from '@/utils/ifaLog.js'
export class IfaLoginIdUpdateRegisterA014RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.loginId = obj.loginId ? obj.loginId : '' // ログインID
    this.password = obj.password ? obj.password : '' // パスワード
    this.userName = obj.userName ? obj.userName : '' // ユーザー名
    this.mailAddress = obj.mailAddress ? obj.mailAddress : '' // メールアドレス
    this.privId = obj.privId ? obj.privId : '' // 所属権限コード
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 本支店コード
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.subBrokerId = obj.subBrokerId ? obj.subBrokerId : '' // 仲介業者支店コード
    this.employeeId = obj.employeeId ? obj.employeeId : '' // 担当者コード
    this.chargeName = obj.chargeName ? obj.chargeName : '' // 担当者名
    this.displayList = obj.displayList ? obj.displayList : '' // 表示リスト.メニューコード
    this.ccsId = obj.ccsId ? obj.ccsId : '' // CCS ID
    this.ccsPw = obj.ccsPw ? obj.ccsPw : '' // CCS パスワード
  }
}
