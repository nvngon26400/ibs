export class IfaForeignMarginPositionDueDateAlertFormModel {
  constructor() {
    this.warningMessage = '' // 注意文言_メッセージ
    this.foreignMarginPositionDueDateAlertList = [{ // 米株信用建玉期日アラート一覧
      brokerCode: '', // 仲介業者コード
      brokerName: '', // 仲介業者名
      brokerageBranchCode: '', // 支店コード
      brokerBranchName: '', // 支店名
      empCode: '', // 営業員コード
      brokerChargeName: '', // 営業員名
      butenCode: '', // 部店
      accountNumber: '', // 口座番号
      courceName: '', // 取引コース
      customerName: '', // 顧客名(漢字)
      customerNameKana: '', // 顧客名(カナ)
      brandCode: '', // ティッカー
      brandName: '', // 銘柄名
      contractStandardDeposit: '', // 建株数
      recentRepayDeadline: '' // 返済期限
    }]
  }
}
