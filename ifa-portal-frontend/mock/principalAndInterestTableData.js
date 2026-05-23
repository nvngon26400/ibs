const data = {
  items:
  [
    { brokerCode: '1008', brokerName: 'GAIA株式会社', brokerChargeCode: '50', brokerChargeName: '佐藤蒼', butenCode: 'Z63', accountNumber: '510050', dealerNumber: '562', customerAttributeName: 'IFAコース（プランA）', nameKanji: '鈴木テスト', produtName: '外国債券(外貨建)', brandCode: 'R4523', brandName: 'トヨタモーターファイナンス 1.00 20240827', execBaseBalance: '5000', currency: 'USD', paymentName: '利金', scheduleDate: '2022/9/30', nextInterest: '2.01', schedulePrice: '50250', couponDeterminationDate: '', ki: '', branchCode: '4202', branchName: '住信SBIネット株式会社' },
    { brokerCode: '1019', brokerName: 'LWJTEST仲介業者支店名', brokerChargeCode: '60', brokerChargeName: '鈴木陽葵', butenCode: 'Z63', accountNumber: '510068', dealerNumber: '010', customerAttributeName: '対面取引コース', nameKanji: '田中テスト', produtName: '外国債券(外貨建)', brandCode: 'R4523', brandName: 'トヨタモーターファイナンス 1.00 20240827', execBaseBalance: '8000', currency: 'USD', paymentName: '利金', scheduleDate: '2022/9/30', nextInterest: '2.01', schedulePrice: '80400', couponDeterminationDate: '', ki: '', branchCode: '5358', branchName: '住信SBIネット株式会社' },
    { brokerCode: '1019', brokerName: 'GAIA株式会社', brokerChargeCode: '50', brokerChargeName: '高橋凪', butenCode: 'Z63', accountNumber: '510050', dealerNumber: '562', customerAttributeName: 'IFAコース（プランA）', nameKanji: '鈴木テスト', produtName: '外国債券(円貨建)', brandCode: 'W3330', brandName: 'ＭＳ　ＥＢ（複数外株参照型）　２０２３０３１６', execBaseBalance: '3000', currency: 'USD', paymentName: '利金', scheduleDate: '2022/9/20', nextInterest: '3.5', schedulePrice: '8750', couponDeterminationDate: '2022/9/14', ki: '', branchCode: '4202', branchName: '住信SBIネット株式会社' },
    { brokerCode: '1019', brokerName: 'LWJTEST仲介業者支店名', brokerChargeCode: '60', brokerChargeName: '田中凛', butenCode: 'Z63', accountNumber: '510068', dealerNumber: '010', customerAttributeName: '対面取引コース', nameKanji: '田中テスト', produtName: '外国債券(円貨建)', brandCode: 'W3330', brandName: 'ＭＳ　ＥＢ（複数外株参照型）　２０２３０３１６', execBaseBalance: '70000', currency: 'USD', paymentName: '利金', scheduleDate: '2022/9/20', nextInterest: '3.5', schedulePrice: '204166', couponDeterminationDate: '2022/9/14', ki: '●', branchCode: '5358', branchName: '住信SBIネット株式会社' },
    { brokerCode: '1019', brokerName: 'LWJTEST仲介業者支店名', brokerChargeCode: '60', brokerChargeName: '伊藤連', butenCode: 'Z63', accountNumber: '510068', dealerNumber: '010', customerAttributeName: '対面取引コース', nameKanji: '田中テスト', produtName: '国内債券', brandCode: '09984.0051', brandName: '第５１回ソフトバンクグループ株式会社無担保社債', execBaseBalance: '2000', currency: 'JPY', paymentName: '利金', scheduleDate: '2022/9/30', nextInterest: '1.5', schedulePrice: '15000', couponDeterminationDate: '', ki: '', branchCode: '4202', branchName: '住信SBIネット株式会社' },
    { brokerCode: '1019', brokerName: 'LWJTEST仲介業者支店名', brokerChargeCode: '60', brokerChargeName: '渡邊詩', butenCode: 'Z63', accountNumber: '510068', dealerNumber: '010', customerAttributeName: '対面取引コース', nameKanji: '田中テスト', produtName: '国内債券(国債)', brandCode: '00082.0071', brandName: '第７１回個人向け利付国債（変動・１０年）', execBaseBalance: '50000', currency: 'JPY', paymentName: '利金', scheduleDate: '2022/9/30', nextInterest: '0.5', schedulePrice: '125000', couponDeterminationDate: '', ki: '', branchCode: '5358', branchName: '住信SBIネット株式会社' }
  ]
}

module.exports = [
  {
    url: '/ifa-mock/principalAndInterest/list',
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
