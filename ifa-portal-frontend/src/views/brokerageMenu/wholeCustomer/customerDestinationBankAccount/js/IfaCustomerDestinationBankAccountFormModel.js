export class IfaCustomerDestinationBankAccountFormModel {
  constructor() {
    this.title = {
      id: 'SUB020303-01',
      name: '顧客振込先金融機関口座'
    }
    // 検索項目
    this.brokerCode = '' // 仲介業者コード
    this.chkBrokerCodeExclude = false // 仲介業者除外
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名（漢字／カナ）
    this.customerNameKanjiKanaTerms = '3' // 顧客名（漢字／カナ）_条件
    this.courseSelected = [] // 取引コース

    // 顧客振込先金融機関口座一覧
    this.gridTable = [
      {
        brokerName: '',
        brokerChargeCode: '',
        employeeName: '',
        butenCode: '',
        accountNumber: '',
        customerAttributeName: '',
        customerNameKanji: '',
        customerNameKana: '',
        bankNameKanji: '',
        branchNameKanji: '',
        depositType: '',
        destinationAccountNumber: '',
        brokerCode: '',
        brokerageBranchCode: '',
        branchNameOfBroker: ''
      }
    ]
  }
}
