const data = {
  items:
  [
    { 'brokerName': '神奈川県ファイナンシャルプランナーズ協同組合', 'salespersonCode': '1234', 'contactPersonName': '北條 文明', 'butenCode': 'Z42', 'accountNo': '050857', 'course': 'IFAコース（プランA-2）', 'customerNameKanji': '桝 秀夫', 'customerNameKana': 'マス ヒデオ', 'mainBranch': '三菱UFA銀行', 'branchNameKanji': '新宿通支店', 'depositType': '普通預金', 'financialInstitutionAccountNumber': '1234567', 'brokerCode': '0276', 'branchCode': '120', 'branchName': '神奈川県ファイナンシャルプランナーズ協同組合' },
    { 'brokerName': '神奈川県ファイナンシャルプランナーズ協同組合', 'salespersonCode': '1234', 'contactPersonName': '北條 文明', 'butenCode': 'Z42', 'accountNo': '060119', 'course': 'IFAコース（プランA-2）', 'customerNameKanji': '桝 秀夫', 'customerNameKana': 'マス ヒデオ', 'mainBranch': '三菱UFA銀行', 'branchNameKanji': '亀戸支店', 'depositType': '貯蓄預金', 'financialInstitutionAccountNumber': '1234568', 'brokerCode': '0276', 'branchCode': '120', 'branchName': '神奈川県ファイナンシャルプランナーズ協同組合' },
    { 'brokerName': '三十三銀行', 'salespersonCode': '0234', 'contactPersonName': '佐藤 太郎', 'butenCode': 'Z45', 'accountNo': '498434', 'course': 'IFAコース（プランA-2）', 'customerNameKanji': '山田 三郎', 'customerNameKana': 'ヤマダ サブロウ', 'mainBranch': '三井住友銀行', 'branchNameKanji': '武蔵小杉支店', 'depositType': '普通預金', 'financialInstitutionAccountNumber': '8356476', 'brokerCode': '2464', 'branchCode': '010', 'branchName': '三十三銀行 秋葉原支店' },
    { 'brokerName': '三十三銀行', 'salespersonCode': '0234', 'contactPersonName': '佐藤 太郎', 'butenCode': 'Z45', 'accountNo': '166493', 'course': 'IFAコース（プランA-2）', 'customerNameKanji': '山田 三郎', 'customerNameKana': 'ヤマダ サブロウ', 'mainBranch': '三井住友銀行', 'branchNameKanji': '新宿西口支店', 'depositType': '貯蓄預金', 'financialInstitutionAccountNumber': '5375674', 'brokerCode': '2464', 'branchCode': '010', 'branchName': '三十三銀行 秋葉原支店' },
    { 'brokerName': 'SBIマネープラザ株式会社', 'salespersonCode': '1519', 'contactPersonName': '佐藤 太郎', 'butenCode': 'Z45', 'accountNo': '172233', 'course': 'IFAコース（プランA-2）', 'customerNameKanji': '山田 三郎', 'customerNameKana': 'ヤマダ サブロウ', 'mainBranch': '三井住友銀行', 'branchNameKanji': '氷川台支店', 'depositType': '普通預金', 'financialInstitutionAccountNumber': '2535756', 'brokerCode': '0509', 'branchCode': '010', 'branchName': '三十三銀行 秋葉原支店' },
    { 'brokerName': 'SBIマネープラザ株式会社', 'salespersonCode': '1519', 'contactPersonName': '大澤 孝文', 'butenCode': '109', 'accountNo': '232507', 'course': 'プランBコース', 'customerNameKanji': '花沢 里美', 'customerNameKana': 'ハナザワ サトミ', 'mainBranch': 'PayPay銀行', 'branchNameKanji': 'すずめ', 'depositType': '普通預金', 'financialInstitutionAccountNumber': '1234567', 'brokerCode': '0509', 'branchCode': '104', 'branchName': 'SBIマネープラザ株式会社　ソリューション営業部' },
    { 'brokerName': 'SBIマネープラザ株式会社', 'salespersonCode': '5365', 'contactPersonName': '大澤 孝文', 'butenCode': '109', 'accountNo': '275325', 'course': 'プランBコース', 'customerNameKanji': '花沢 里美', 'customerNameKana': 'ハナザワ サトミ', 'mainBranch': 'PayPay銀行', 'branchNameKanji': 'はやぶさ', 'depositType': '普通預金', 'financialInstitutionAccountNumber': '4364757', 'brokerCode': '0509', 'branchCode': '104', 'branchName': 'SBIマネープラザ株式会社　ソリューション営業部' },
    { 'brokerName': 'SBIマネープラザ株式会社', 'salespersonCode': '5365', 'contactPersonName': '佐藤 次郎', 'butenCode': 'Z41', 'accountNo': '169132', 'course': 'インターネットコース', 'customerNameKanji': '渡辺 清', 'customerNameKana': 'ワタナベ キヨシ', 'mainBranch': '住信SBIネット銀行', 'branchNameKanji': 'イチゴ支店', 'depositType': '貯蓄預金', 'financialInstitutionAccountNumber': '2456896', 'brokerCode': '0509', 'branchCode': '110', 'branchName': 'SBIマネープラザ株式会社　ソリューション営業部' },
    { 'brokerName': 'SBIマネープラザ株式会社', 'salespersonCode': '5365', 'contactPersonName': '佐藤 次郎', 'butenCode': 'Z41', 'accountNo': '165391', 'course': 'インターネットコース', 'customerNameKanji': '渡辺 清', 'customerNameKana': 'ワタナベ キヨシ', 'mainBranch': '住信SBIネット銀行', 'branchNameKanji': 'キウイ支店', 'depositType': '貯蓄預金', 'financialInstitutionAccountNumber': '2578544', 'brokerCode': '0509', 'branchCode': '110', 'branchName': 'SBIマネープラザ株式会社　ソリューション営業部' }
  ] }

module.exports = [
  {
    url: '/ifa-mock/page1/list',
    type: 'get',
    response: config => {
      const items = data.items
      return {
        code: 20000,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  }
]
