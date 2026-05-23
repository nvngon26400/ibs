export class IfaJpyAmountUnpaidOverdraftAlertListFormModel {
  constructor() {
    // 円貨未入金・赤残アラート一覧
    this.jpyAmountUnpaidOverdraftAlertList = [
      {
        stlDate0: '', // 受渡日(1)
        brokerCode: '', // 仲介業者コード
        brokerBranchName: '', // 仲介業者支店名
        subBrokerId: '', // 仲介業者支店コード
        brokerChargeCode: '', // 仲介業者営業員コード
        employeeName: '', // 仲介業者担当者名
        butenCode: '', // 部店コード
        accountNumber: '', // 口座番号
        customerAttributeName: '', // 契約締結前交付書面コード名
        customerNameKanji: '', // 顧客名_姓名(漢字)
        customerNameKana: '', // 顧客名_姓名(カナ)
        chargeAmount0: '', // 預り金赤残(1)
        targetFlag: '', // 対象フラグ
        totalAssets: '' // 総資産
      }
    ]
  }
}
