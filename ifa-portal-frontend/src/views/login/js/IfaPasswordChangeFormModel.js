export class IfaPasswordChangeFormModel {
  constructor() {
    this.screenId = 'SUB00-03_1'
    this.screenTitle = 'パスワード変更'
    this.message = '' // メッセージ
    this.oldPassword = '' // 旧パスワード
    this.newPassword = '' // 新パスワード
    this.newPasswordCheck = '' // 新パスワード（確認入力）
    this.passwordValidityPeriodFlag = true // パスワード有効期限切れフラグ 【初期値】true
    this.finishMassage = '' // メッセージ
  }
}
