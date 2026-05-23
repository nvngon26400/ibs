import Logger from '@/utils/ifaLog.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
export class IfaResponseStatusUpdateA006RequestModel {
  constructor(obj, flag) {
    Logger.debug(obj)
    this.statusClassification = obj.statusClassification ? obj.statusClassification : '' // ステータス区分
    this.declineRateKbn = obj.declineRateKbn ? obj.declineRateKbn : '' // 下落率区分
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.investmentTrustAssociationCode = obj.investmentTrustAssociationCode ? obj.investmentTrustAssociationCode : '' // 投信協会コード
    this.standardDateTo = obj.standardDate ? getFormattedDateValue(obj.standardDate, 'date8') : '' // 基準日
    this.responseMethodClassification = obj.responseMethodClassification ? obj.responseMethodClassification : '' // 対応方法区分
    this.otherContentsClassification = obj.responseMethodClassification === '9' ? obj.otherContentsClassification : null // その他内容区分
    this.otherDetail = obj.otherContentsClassification === '99' ? obj.otherDetail : null // その他詳細
    this.customerResponseDate = obj.responseMethodClassification !== '9' ? obj.customerResponseDate : null // 顧客対応日
    this.responseFinishConfirmDate = flag ? obj.responseFinishConfirmDate : null // 対応完了確認日
  }
}
