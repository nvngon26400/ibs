import Logger from '@/utils/ifaLog.js'
export class IfaPasswordChangeA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.oldPassword = obj.oldPassword ? obj.oldPassword : '' // 旧パスワード
    this.newPassword = obj.newPassword ? obj.newPassword : '' // 新パスワード
    this.newPasswordRepeat = obj.newPasswordCheck ? obj.newPasswordCheck : '' // 新パスワード（確認入力）
    this.passwordValidityPeriodFlag = obj.passwordValidityPeriodFlag ? obj.passwordValidityPeriodFlag : '' // パスワード有効期限切れフラグ
  }
}
