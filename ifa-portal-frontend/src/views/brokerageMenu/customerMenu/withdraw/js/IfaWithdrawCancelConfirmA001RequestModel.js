import Logger from '@/utils/ifaLog.js'
export class IfaWithdrawCancelConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.rpNumber = obj.rpNumber ? obj.rpNumber : '' // EC入出金番号
    this.payAmount = obj.payAmount ? obj.payAmount : '' // 出金額
    this.noticeInfoAlertConfirm = obj.noticeInfoAlertConfirm ? obj.noticeInfoAlertConfirm : '0' // アラート内容確認.注意情報アラート確認
    this.noticeAlertConfirm = obj.noticeAlertConfirm ? obj.noticeAlertConfirm : '0' // アラート内容確認.お知らせアラート確認
  }
}
