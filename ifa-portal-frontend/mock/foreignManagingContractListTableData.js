const data = {
  items:
  [
    { butenCode: 'Z56', accountNumber: '411167', customerAttribute: 'プランAコース', nameKanji: '藤川静枝', orderNo: '21', orderStatus: '注文中(一部約定) ', brandName: 'アップル', brandCode: 'AAPL', market: 'NASDAQ', tradeKbn: '売建(6カ月)', tokuteiContractComIdR: '一般 /S', orderDate: '2022/01/13', oraderLimit: '2022/07/13', quantity: '200', execLeftQuantity: '100', limitPrice: '成行', lastPrice: '11.00', stopOrderPrice: '1240以下になったら　成行', tokuteiContract: '特定 ', tradeDate: '2016/08/03', settlementDate: '2016/08/06', quantityTotal: '200', averagePrice: '1247', intermediaryEmpCd: '4202', brokerChargeName: '坂井益', brokerCode: '4202', brokerName: '住信SBIネット株式会社', branchCode: '4202', branchName: '住信SBIネット株式会社', actualGrntRate: '234%', openTradeKbn: '買建(6ヶ月)', openTradeDate: '2016/08/03', lastTradeDate: '2017/02/02', contPositionTotal: '10', unactualQuantity: '0', openPrice: '10.00', openAmount: '10.00', cost: '15.00', profitLoss: '-25.00', collateral: '50.12%' },
    { butenCode: 'Z56', accountNumber: '411167', customerAttribute: 'プランAコース', nameKanji: '藤川静枝', orderNo: '22', orderStatus: '注文中', brandName: 'アップル', brandCode: 'AAPL', market: 'NASDAQ', tradeKbn: '買建(無期限)', tokuteiContractComIdR: '特定 /S', orderDate: '2022/01/13', oraderLimit: '----/--/--', quantity: '1,000', execLeftQuantity: '1,000', limitPrice: '成行', lastPrice: '11.00', stopOrderPrice: '', tokuteiContract: '特定 ', tradeDate: '2016/08/03', settlementDate: '2016/08/06', quantityTotal: '1,000', averagePrice: '160.3', intermediaryEmpCd: '5358', brokerChargeName: '坂井益', brokerCode: '5358', brokerName: '住信SBIネット株式会社', branchCode: '5358', branchName: '住信SBIネット株式会社', actualGrntRate: '234%', openTradeKbn: '買建(無期限)', openTradeDate: '2016/08/03', lastTradeDate: '----/--/--', contPositionTotal: '100', unactualQuantity: '100', openPrice: '10.00', openAmount: '1000.00', cost: '15.00', profitLoss: '+85.00', collateral: '60%(増担)' }
  ]
}

module.exports = [
  {
    url: '/ifa-mock/foreignManagingContractList/list',
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
