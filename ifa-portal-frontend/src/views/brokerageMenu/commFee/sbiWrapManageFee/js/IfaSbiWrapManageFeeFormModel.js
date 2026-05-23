export class IfaSbiWrapManageFeeFormModel {
  constructor() {
    this.title = 'SBIラップ管理報酬'
    this.registeredDate = '' // 登録日 【初期値】From日付、 To日付は前営業日
    this.periodAlert = '' // 期間指定メッセージ
    this.sbiWrapManageFee = [{ // SBIラップ管理報酬 【初期値】1行目に"No rows to display."を表示。
      brokerCode: '', // 仲介業者コード
      brokerName: '', // 仲介業者名
      empCode: '', // 営業員コード
      brokerChargeName: '', // 営業員名
      buten: '', // 部店
      accountNumber: '', // 口座番号
      dealerNumber: '', // 扱者コード
      customerName: '', // 顧客名（漢字）
      targetDateYmd: '', // 手数料徴収日
      operationFeeCollectionInfoId: '', // 手数料番号
      name: '', // 運用サービスID
      yenFee: '' // 徴収額（税抜）
    }]
    this.brokerCode = '' // 仲介業者コード
    this.brokerName = '' // 仲介業者名
    this.empCode = '' // 営業員コード
    this.brokerChargeName = '' // 営業員名
    this.buten = '' // 部店
    this.accountNumber = '' // 口座番号
    this.dealerNumber = '' // 扱者コード
    this.customerName = '' // 顧客名（漢字）
    this.targetDateYmd = '' // 手数料徴収日
    this.operationFeeCollectionInfoId = '' // 手数料番号
    this.name = '' // 運用サービスID
    this.yenFee = '' // 徴収額（税抜）
    this.period = []
  }
}
