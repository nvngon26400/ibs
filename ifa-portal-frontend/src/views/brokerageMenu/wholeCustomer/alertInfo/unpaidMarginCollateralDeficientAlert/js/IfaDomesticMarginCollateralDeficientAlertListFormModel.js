export class IfaDomesticMarginCollateralDeficientAlertListFormModel {
  constructor() {
    this.warningMessage = '' // 注意文言_メッセージ
    // 国内信用担保不足アラート一覧
    this.domesticMarginCollateralDeficientAlertList = [
      {
        accuralDate: '', // 発生日
        brokerCode: '', // 仲介業者コード
        brokerName: '', // 仲介業者名
        branchCode: '', // 支店コード
        branchName: '', // 支店名
        empCode: '', // 営業員コード
        brokerChargeName: '', // 営業員名
        butenCode: '', // 部店
        accountNumber: '', // 口座番号
        course: '', // 取引コース
        customerNameKanji: '', // 顧客名(漢字)
        customerNameKana: '', // 顧客名(カナ)
        beforeDepositBalance: '', // 前日保証金残高
        beforeDetentionRate: '', // 前日維持率
        marginDemandAmount: '' // 追証請求額
      }
    ]
  }
}
