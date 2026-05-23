// 保有商品一覧

// 総合口座
exports.getGeneric = () => {
  return {
    // 株式
    domesticStock: {
      // 特定預り
      specialDeposit: [
        { code: '1384', name: 'ホクリョウ', volume: 100, sellingVolume: 0, averagePrice: 202, currentPrice: 764 },
        { code: '2342', name: 'トランスジェニック', volume: 600, sellingVolume: 0, averagePrice: 678, currentPrice: 613 },
        { code: '2408', name: 'ＫＧ情報', volume: 1800, sellingVolume: 200, averagePrice: 278, currentPrice: 348 },
        { code: '2435', name: 'シダー', volume: 2000, sellingVolume: 0, averagePrice: 98, currentPrice: 222 },
        { code: '2681', name: 'ゲオホールディングス', volume: 100, sellingVolume: 0, averagePrice: 1407, currentPrice: 1264 },
        { code: '2767', name: 'フィールズ', volume: 400, sellingVolume: 0, averagePrice: 226, currentPrice: 528 },
        { code: '2813', name: '和弘食品', volume: 600, sellingVolume: 0, averagePrice: 2301, currentPrice: 2713 },
        { code: '5122', name: 'オカモト', volume: 6600, sellingVolume: 1000, averagePrice: 614, currentPrice: 4055 },
        { code: '7201', name: '日産自動車', volume: 600, sellingVolume: 200, averagePrice: 321, currentPrice: 558.2 },
        { code: '7414', name: '小野建', volume: 600, sellingVolume: 0, averagePrice: 1201, currentPrice: 1607 },
        { code: '7867', name: 'タカラトミー', volume: 200, sellingVolume: 200, averagePrice: 924, currentPrice: 1104 }
      ],
      // 一般預り
      deposit: [
        { code: '3440', name: '日創プロニティ', volume: 200, sellingVolume: 0, averagePrice: 485, currentPrice: 536 },
        { code: '4708', name: 'りらいあコミュニケーションズ', volume: 200, sellingVolume: 0, averagePrice: 1100, currentPrice: 1176 }
      ],
      // NISA預り
      nisaDeposit: [
        { code: '7201', name: '日産自動車', volume: 600, sellingVolume: 0, averagePrice: 321, currentPrice: 558.2 },
        { code: '7414', name: '小野建', volume: 600, sellingVolume: 300, averagePrice: 1201, currentPrice: 1607 },
        { code: '7867', name: 'タカラトミー', volume: 200, sellingVolume: 0, averagePrice: 924, currentPrice: 1104 }
      ]
    },
    // 投資信託/口数
    investmentTrustByUnit: {
      // 特定預り
      specialDeposit: [
        { code: '0034- 52', name: 'ＭＨＡＭ　株式オープン', volume: 80000, sellingVolume: 0, acquisitionPrice: 1000, basePrice: 1896, acquisitionAmount: 80000, assessedValue: 151680, profit: 71680, principal: 1000, totalReturn: 7600, percent: 18.52, method: 0 },
        { code: '0000- 00', name: '日興ネクスト１０イヤーズ・グローバル・エクイティ・オープン', volume: 40000, sellingVolume: 0, acquisitionPrice: 13580, basePrice: '-', acquisitionAmount: '-', assessedValue: '-', profit: '-', principal: 0, totalReturn: '-', percent: '-', method: 0 },
        { code: '0422- 93', name: 'ニッセイ日経２２５インデックスファンド', volume: 80000, sellingVolume: 0, acquisitionPrice: 24042, basePrice: 33961, acquisitionAmount: 192336, assessedValue: 271688, profit: 79352, principal: 24042, totalReturn: 2195, percent: 22.02, method: 0 }
      ],
      // 一般預り
      deposit: [
        { code: '0066- 02', name: 'ノムラ・ジャパン・オープン', volume: 5000, sellingVolume: 0, acquisitionPrice: 50, basePrice: 12318, acquisitionAmount: 25, assessedValue: 6159, profit: 6134, principal: 50, totalReturn: 41, percent: 0.52, method: 0 },
        { code: '0000- 00', name: '外国債券オープン（３ヶ月決算型）', volume: 6000, sellingVolume: 0, acquisitionPrice: 0, basePrice: '-', acquisitionAmount: '-', assessedValue: '-', profit: '-', principal: 0, totalReturn: '-', percent: '-', method: 0 }
      ],
      // NISA預り
      nisaDeposit: [
        { code: '0422- 93', name: 'ニッセイ日経２２５インデックスファンド', volume: 80000, sellingVolume: 0, acquisitionPrice: 24042, basePrice: 33961, acquisitionAmount: 192336, assessedValue: 271688, profit: 79352, principal: 24042, method: 0, totalReturn: 45210, percent: 8.52 }
      ]
    },
    // 投資信託/金額
    investmentTrustByPrice: {
      // 特定預り
      specialDeposit: [
        { code: '0013- 08', name: '野村インデックスファンド・日経２２５', volume: 9706, sellingVolume: 0, acquisitionPrice: 23791, basePrice: 33481, acquisitionAmount: 23091, assessedValue: 32496, profit: 9405, principal: 23788.38, totalReturn: 5740, percent: 12.25, method: 0 },
        { code: '0275- 27', name: 'ｅＭＡＸＩＳ　全世界株式インデックス', volume: 190, sellingVolume: 0, acquisitionPrice: 26316, basePrice: 35356, acquisitionAmount: 500, assessedValue: 671, profit: 171, principal: 26315.79, totalReturn: 10, percent: 1.12, method: 0 },
        { code: '0506- 27', name: '三菱ＵＦＪ　米国バンクローンＦ　米ドル円プレミアム（年２回）', volume: 270, sellingVolume: 0, acquisitionPrice: 11112, basePrice: 11438, acquisitionAmount: 300, assessedValue: 308, profit: 8, principal: 10888.89, totalReturn: 5740, percent: 12.25, method: 0 },
        { code: '0055- 97', name: '東京海上・アジア中小型成長株ファンド', volume: 2060, sellingVolume: 0, acquisitionPrice: 8253, basePrice: 7746, acquisitionAmount: 1700, assessedValue: 1595, profit: -105, principal: 8033.98, totalReturn: -123, percent: -1.1, method: 0 },
        { code: '0658- 97', name: 'ニッセイ日経２２５インデックスファンド', volume: 250, sellingVolume: 0, acquisitionPrice: 24000, basePrice: 33961, acquisitionAmount: 600, assessedValue: 849, profit: 249, principal: 24000, totalReturn: -12345, percent: -56789, method: 0 },
        { code: '0674- 97', name: 'アムンディ・毎月分配ユーロ債券ファンド', volume: 18847, sellingVolume: 0, acquisitionPrice: 5307, basePrice: 5391, acquisitionAmount: 10002, assessedValue: 10160, profit: 158, principal: 5192.87, totalReturn: 12345, percent: 6988, method: 0 },
        { code: '0568- 98', name: '日本債券ベアファンド（５倍型）', volume: 177, sellingVolume: 0, acquisitionPrice: 5650, basePrice: 5741, acquisitionAmount: 100, assessedValue: 101, profit: 1, principal: 0, totalReturn: 741, percent: 8.52, method: 0 }
      ],
      // 一般預り
      deposit: [
        { code: '0201- 07', name: '株式インデックス　２２５', volume: 312645, sellingVolume: 0, acquisitionPrice: 4798, basePrice: 10621, acquisitionAmount: 150007, assessedValue: 332060, profit: 182053, principal: 4797.77, totalReturn: 5740, percent: 12.25, method: 0 },
        { code: '0203- 07', name: 'ノムラ・ジャパン・オープン', volume: 836117, sellingVolume: 0, acquisitionPrice: 6924, basePrice: 12318, acquisitionAmount: 578927, assessedValue: 1029928, profit: 451001, principal: 6924, totalReturn: -963, percent: -10.00, method: 0 },
        { code: '0000- 00', name: '野村日本債券ファンド（確定拠出年金向け）', volume: 6000, sellingVolume: 0, acquisitionPrice: 4600, basePrice: '-', acquisitionAmount: '-', assessedValue: '-', profit: '-', principal: 4600, totalReturn: 5740, percent: 12.25, method: 0 },
        { code: '0805- 07', name: 'スーパー　トレンド　オープン', volume: 1751074, sellingVolume: 0, acquisitionPrice: 3356, basePrice: 8357, acquisitionAmount: 587660, assessedValue: 1463372, profit: 875712, principal: 3356, totalReturn: 8520, percent: 12.25, method: 0 },
        { code: '0000- 00', name: '１回　野村の公社債投信', volume: 4000, sellingVolume: 0, acquisitionPrice: 4600, basePrice: '-', acquisitionAmount: '-', assessedValue: '-', profit: '-', principal: 4600, totalReturn: 5740, percent: 12.25, method: 0 },
        { code: '0000- 00', name: '１１回　野村の公社債投信', volume: 4502, sellingVolume: 0, acquisitionPrice: 6400, basePrice: '-', acquisitionAmount: '-', assessedValue: '-', profit: '-', principal: 6399.38, totalReturn: -5740, percent: -12.25, method: 0 }
      ],
      // NISA預り
      nisaDeposit: [
        { code: '0658- 97', name: 'ニッセイ日経２２５インデックスファンド', volume: 250, sellingVolume: 0, acquisitionPrice: 24000, basePrice: 33961, acquisitionAmount: 600, assessedValue: 849, profit: 249, principal: 24000, totalReturn: 123, percent: 4.00, method: 0 },
        { code: '0203- 07', name: 'ノムラ・ジャパン・オープン', volume: 836117, sellingVolume: 0, acquisitionPrice: 6924, basePrice: 12318, acquisitionAmount: 578927, assessedValue: 1029928, profit: 451001, principal: 6924, totalReturn: -5740, percent: -12.25, method: 0 },
        { code: '0000- 00', name: '野村日本債券ファンド（確定拠出年金向け）', volume: 6000, sellingVolume: 0, acquisitionPrice: 4600, basePrice: '-', acquisitionAmount: '-', assessedValue: '-', profit: '-', principal: 4600, totalReturn: 98740, percent: 118.11, method: 0 }
      ]
    },
    // 国内債券
    domesticBond: {
      deposit: [
        { issueType: '1', rank: '3', code: '0031.1', name: '入札前取引利付国債（１５年）（２００５年１月発行銘柄）', compoundInterest: '3.22', redemptionDate: '2030/01/20', interestPaymentDate: '03/09-末日', volume: 2500000, unitPrice: 500000, contractExchange: '-', referenceExchange: '-', unit: { lower: 500000, unit: 500000 }},
        { issueType: '0', rank: '3', code: '0039.10', name: '株価連動債（キーエンス）', compoundInterest: '5.325', redemptionDate: '2022/06/24', interestPaymentDate: '-', volume: 500000, unitPrice: 1000, contractExchange: '-', referenceExchange: '-', unit: { lower: 1000, unit: 1000 }}
      ],
      specialDeposit: [
        { issueType: '0', rank: '3', code: 'P2601', name: '株価連動債（日立製作所）', compoundInterest: '5.325', redemptionDate: '2023/06/23', interestPaymentDate: '-', volume: 2000000, unitPrice: 1000, contractExchange: '-', referenceExchange: '-', unit: { lower: 1000, unit: 1000 }}
      ]
    },
    // 外国債券
    foreignBond: {
      deposit: [
        { issueType: '1', rank: '3', code: 'P1312', name: 'ブラジルレアル建ディスカウント債券（円貨決済型）0.8%', currency: 'JPY', compoundInterest: '3.93', redemptionDate: '2030/01/20', interestPaymentDate: '06/02,12/02', volume: 1000000, unitPrice: 100, contractExchange: '-', referenceExchange: '-', unit: { lower: 10000, unit: 1000, code: '円' }},
        { issueType: '0', rank: '3', code: 'R2353', name: '国際復興開発銀行　７．４０％ ２０１９０７１９', currency: 'USD', compoundInterest: '5.325', redemptionDate: '2036/12/10', interestPaymentDate: '06/02,12/02', volume: 70000, unitPrice: 100, contractExchange: '132.23', referenceExchange: 'USD', unit: { lower: 100, unit: 100, code: '米ドル' }}
      ],
      specialDeposit: [
        { issueType: '1', rank: '3', code: 'R8843', name: 'バンク・オブ・アメリカ　ゼロクーポン債 ２０３１１１２９', currency: 'AUD', compoundInterest: '3.22', redemptionDate: '2038/09/20', interestPaymentDate: '06/02,12/02', volume: 1000000, unitPrice: 119.73, contractExchange: '98.81', referenceExchange: 'AUD', unit: { lower: 10000, unit: 1000, code: 'AUD' }},
        { issueType: '1', rank: '3', code: 'R5637', name: 'アフリカ開発銀行　７．２０％ ２０２１０２１７', currency: 'BRL', compoundInterest: '2.85', redemptionDate: '2030/01/20', interestPaymentDate: '06/02,12/02', volume: 80000, unitPrice: 122.06, contractExchange: '28.546', referenceExchange: 'BRL', unit: { lower: 10000, unit: 1000, code: 'BRL' }},
        { issueType: '0', rank: '3', code: 'P4396', name: 'ＳＢＩ　ＥＢ（大成建設） ２０１９０１２１', currency: 'USD', compoundInterest: '5.325', redemptionDate: '2023/06/23', interestPaymentDate: '06/02,12/02', volume: 200000, unitPrice: 100, contractExchange: '132.23', referenceExchange: 'USD', unit: { lower: 100, unit: 100, code: '米ドル' }}
      ]
    },
    // 外国株式
    foreignDomesticStock: {
      // 特定預り
      specialDeposit: [
        { code: 'KR', name: 'クローガー', marginType: '保護預り', volume: 100, sellingVolume: 0, averagePrice: 202, currentPrice: 120.51, countryCode: 'US', currency: 'USD', market: 'NYSE', desc: { unit: 1, current: '128.0100', priceQuotation: 1, last: '128.9300', lastDate: '21/09/13', diff: '-0.92', ratio: '-0.71', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '129.7900', low: '127.1200', high: '130.2700', volume: 1695916, tick: '2' }},
        { code: 'OC', name: 'オーウェンス コーニング', marginType: '代用預り', volume: 200, sellingVolume: 200, averagePrice: 924, currentPrice: 1104.02, countryCode: 'US', currency: 'USD', market: 'ナスダック', desc: { unit: 1, current: '196.0300', priceQuotation: 1, last: '199.7900', lastDate: '21/09/13', diff: '-3.76', ratio: '-1.88', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '201.7100', low: '194.7050', high: '201.7100', volume: 762094, tick: '2' }}
      ],
      // 一般預り
      deposit: [
        { code: '00001', name: 'CK ハチソン', marginType: '保護預り', volume: 1000, sellingVolume: 0, averagePrice: 485, currentPrice: 500.11, countryCode: 'CN', currency: 'HKD', market: 'メインボード', desc: { unit: 500, current: '48.9000', priceQuotation: 1, last: '49.2100', lastDate: '21/09/13', diff: '-0.31', ratio: '-0.63', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '48.7000', low: '48.6000', high: '49.0500', volume: 2843649, tick: '2' }},
        { code: 'ROP', name: 'ローパー テクノロジーズ', marginType: '保護預り', volume: 200, sellingVolume: 0, averagePrice: 1100, currentPrice: 1176, countryCode: 'US', currency: 'USD', market: 'NYSEArca', desc: { unit: 1, current: '159.2600', priceQuotation: 1, last: '157.8700', lastDate: '21/09/13', diff: '+1.39', ratio: '+0.88', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '159.3499', low: '159.1400', high: '160.0600', volume: 5196908, tick: '1' }}
      ],
      // NISA預り
      nisaDeposit: [
        { code: 'AFLT', name: 'アエロフロート航空', marginType: '代用預り', volume: 600, sellingVolume: 0, averagePrice: 321, currentPrice: 558.2, countryCode: 'RU', currency: 'RUB', market: 'ロシア MICEX', desc: { unit: 1, current: '30.9600', priceQuotation: 1, last: '28.8700', lastDate: '21/09/13', diff: '+2.12', ratio: '+7.35', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '28.5800', low: '28.5800', high: '31.1000', volume: 33598730, tick: '1' }},
        { code: 'DBT', name: 'ベンチェー薬品', marginType: '代用預り', volume: 600, sellingVolume: 600, averagePrice: 1201, currentPrice: 1150, countryCode: 'VN', currency: 'VND', market: 'ホーチミン', desc: { unit: 1, current: '12,400.0000', priceQuotation: 1, last: '28.8700', lastDate: '21/09/13', diff: '+2.12', ratio: '+7.35', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '28.5800', low: '28.5800', high: '31.1000', volume: 33598730, tick: '1' }},
        { code: 'DELF', name: 'デフィ', marginType: '代用預り', volume: 200, sellingVolume: 0, averagePrice: 1104, currentPrice: 924, countryCode: 'SG', currency: 'SGD', market: 'シンガポール証券取引所', desc: { unit: 100, current: '30.9600', priceQuotation: 1, last: '28.8700', lastDate: '21/09/13', diff: '+2.12', ratio: '+7.35', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '28.5800', low: '28.5800', high: '31.1000', volume: 33598730, tick: '1' }}
      ]
    },
    // 外貨建MMF
    foreignCurrencyMmf: {
      deposit: [
        { code: 'X0994000', name: 'ノムラ・グローバル・セレクト・トラスト', interest: '0.147', foreignVolume: 25000, volume: 2500000, sellingVolume: 500000, currency: { country: '米', name: 'ドル', code: 'USD' }, contractExchange: 100, currentPrice: 100, averagePrice: 202 },
        { code: 'X0995000', name: 'ニッコウ・マネー・マーケット・ファンド', interest: '0.073', foreignVolume: 8.5, volume: 1000, sellingVolume: 0, currency: { country: 'NZ', name: 'ドル', code: 'NZD' }, contractExchange: 1000, currentPrice: 100, averagePrice: 15 },
        { code: 'X0995000', name: 'ニッコウ・マネー・マーケット・ファンド', interest: '0.109', foreignVolume: 280, volume: 1500000, sellingVolume: 250000, currency: { country: '米', name: 'ドル', code: 'USD' }, contractExchange: 123100, currentPrice: 100, averagePrice: 123 }
      ]
    },
    // その他商品
    otherProduct: {
      // 特定預り
      specialDeposit: [
        { productKind: '国内CP', name: '日立製作所', volume: 3000000 },
        { productKind: '外国投信', name: 'パトナム・ハイ・イールド・ファンド', volume: 1000000 }
      ],
      // 一般預り
      deposit: [
        { productKind: '国内CP', name: '森トラスト・ホールディングス', volume: 3000000 },
        { productKind: '外国投信', name: 'アイガー・ファンド・シリーズーEMF', volume: 1000000 }
      ]
    }
  }
}

// ジュニアNISA口座
exports.getJrNisa = () => {
  return {
    // 株式
    domesticStock: {
      // 特定預り
      specialDeposit: [
        { code: '2435', name: 'シダー', volume: 2000, sellingVolume: 0, averagePrice: 98, currentPrice: 222 },
        { code: '2681', name: 'ゲオホールディングス', volume: 1, sellingVolume: 0, averagePrice: 1407, currentPrice: 1264 },
        { code: '2767', name: 'フィールズ', volume: 400, sellingVolume: 400, averagePrice: 226, currentPrice: 528 }
      ],
      // 一般預り
      deposit: [
        { code: '2813', name: '和弘食品', volume: 600, sellingVolume: 0, averagePrice: 2301, currentPrice: 2713 },
        { code: '3440', name: '日創プロニティ', volume: 200, sellingVolume: 0, averagePrice: 485, currentPrice: 536 }
      ],
      // NISA預り
      nisaDeposit: [
        { code: '3002', name: 'グンゼ', volume: 1000, sellingVolume: 0, averagePrice: 5080, currentPrice: 4235 }
      ]
    },
    // 投資信託/口数
    investmentTrustByUnit: {
      // 特定預り
      specialDeposit: [
        { code: '0000- 00', name: '日興ネクスト１０イヤーズ・グローバル・エクイティ・オープン', volume: 10000, sellingVolume: 0, acquisitionPrice: 13580, basePrice: '-', acquisitionAmount: '-', assessedValue: '-', profit: '-', principal: 0, method: 0, totalReturn: '-', percent: '-' },
        { code: '0422- 93', name: 'ニッセイ日経２２５インデックスファンド', volume: 120000, sellingVolume: 0, acquisitionPrice: 24042, basePrice: 33961, acquisitionAmount: 288504, assessedValue: 407532, profit: 119028, principal: 24042, method: 0, totalReturn: 741, percent: 8.52 }
      ],
      // 一般預り
      deposit: [
        { code: '0349- 93', name: '新生・ＵＴＩインドインフラ関連株式ファンド', volume: 440, sellingVolume: 0, acquisitionPrice: 6819, basePrice: 8144, acquisitionAmount: 300, assessedValue: 358, profit: 58, principal: 6706.48, method: 0, totalReturn: 741, percent: 8.52 },
        { code: '0422- 93', name: 'ニッセイ日経２２５インデックスファンド', volume: 1305, sellingVolume: 0, acquisitionPrice: 24039, basePrice: 33961, acquisitionAmount: 3137, assessedValue: 4431, profit: 1294, principal: 24017.28, method: 0, totalReturn: 1741, percent: 28.52 }
      ],
      // NISA預り
      nisaDeposit: [
        { code: '0428- 93', name: 'アムンディ・毎月分配ユーロ債券ファンド', volume: 18859, sellingVolume: 0, acquisitionPrice: 5307, basePrice: 5391, acquisitionAmount: 10008, assessedValue: 10166, profit: 158, principal: 5192.75, method: 0, totalReturn: 741, percent: 8.52 }
      ]
    },
    // 投資信託/金額
    investmentTrustByPrice: {
      // 特定預り
      specialDeposit: [
        { code: '0013- 08', name: '野村インデックスファンド・日経２２５', volume: 4876, sellingVolume: 0, acquisitionPrice: 23795, basePrice: 33481, acquisitionAmount: 11602, assessedValue: 16325, profit: 4723, principal: 23790.32, method: 0, totalReturn: 2041, percent: 10.22 },
        { code: '0201- 57', name: 'ＭＨＡＭ　株式オープン', volume: 900, sellingVolume: 0, acquisitionPrice: 1000, basePrice: 1896, acquisitionAmount: 900, assessedValue: 1706, profit: 806, principal: 1000, method: 0, totalReturn: 41, percent: 1.52 },
        { code: '0000- 00', name: '日興ネクスト１０イヤーズ・グローバル・エクイティ・オープン', volume: 1982, sellingVolume: 0, acquisitionPrice: 13569, basePrice: '-', acquisitionAmount: '-', assessedValue: '-', profit: '-', principal: 0, method: 0, totalReturn: '-', percent: '-' },
        { code: '0570- 97', name: '新生・ＵＴＩインドインフラ関連株式ファンド', volume: 440, sellingVolume: 0, acquisitionPrice: 6819, basePrice: 8144, acquisitionAmount: 300, assessedValue: 358, profit: 58, principal: 6706.48, method: 0, totalReturn: 41, percent: 1.12 },
        { code: '0658- 97', name: 'ニッセイ日経２２５インデックスファンド', volume: 1305, sellingVolume: 0, acquisitionPrice: 24039, basePrice: 33961, acquisitionAmount: 3137, assessedValue: 4431, profit: 1294, principal: 24017.28, method: 0, totalReturn: 50421, percent: 108.52 },
        { code: '0674- 97', name: 'アムンディ・毎月分配ユーロ債券ファンド', volume: 18859, sellingVolume: 0, acquisitionPrice: 5307, basePrice: 5391, acquisitionAmount: 10008, assessedValue: 10166, profit: 158, principal: 5192.75, method: 0, totalReturn: 130, percent: 5.52 }
      ],
      // 一般預り
      deposit: [
        { code: '0000- 00', name: '日興ネクスト１０イヤーズ・グローバル・エクイティ・オープン', volume: 1982, sellingVolume: 0, acquisitionPrice: 13569, basePrice: '-', acquisitionAmount: '-', assessedValue: '-', profit: '-', principal: 0, method: 0, totalReturn: '-', percent: '-' }
      ],
      // NISA預り
      nisaDeposit: [
        { code: '0223- 07', name: 'リサーチ・アクティブ・オープン', volume: 254, sellingVolume: 0, acquisitionPrice: 11731, basePrice: 16112, acquisitionAmount: 297, assessedValue: 409, profit: 112, principal: 11524.93, method: 0, totalReturn: -41, percent: -2.02 },
        { code: '0201- 57', name: 'ＭＨＡＭ　株式オープン', volume: 2500, sellingVolume: 0, acquisitionPrice: 1000, basePrice: 1896, acquisitionAmount: 2500, assessedValue: 4740, profit: 2240, principal: 1000, method: 0, totalReturn: 741, percent: 8.52 },
        { code: '0000- 00', name: '日興ネクスト１０イヤーズ・グローバル・エクイティ・オープン', volume: 1897, sellingVolume: 0, acquisitionPrice: 13575, basePrice: '-', acquisitionAmount: '-', assessedValue: '-', profit: '-', principal: 0, method: 0, totalReturn: '-', percent: '-' },
        { code: '0233- 87', name: '日本インデックスオープン２２５', volume: 669, sellingVolume: 0, acquisitionPrice: 14963, basePrice: 20415, acquisitionAmount: 1001, assessedValue: 1365, profit: 364, principal: 14663.68, method: 0, totalReturn: 1401, percent: 18.32 },
        { code: '0570- 97', name: '新生・ＵＴＩインドインフラ関連株式ファンド', volume: 732, sellingVolume: 0, acquisitionPrice: 6831, basePrice: 8144, acquisitionAmount: 500, assessedValue: 596, profit: 96, principal: 6706.48, method: 0, totalReturn: 2041, percent: 10.52 },
        { code: '0658- 97', name: 'ニッセイ日経２２５インデックスファンド', volume: 84, sellingVolume: 0, acquisitionPrice: 23810, basePrice: 33961, acquisitionAmount: 200, assessedValue: 285, profit: 85, principal: 24017.28, method: 0, totalReturn: -41, percent: -1.12 }
      ]
    },
    // 外国株式
    foreignDomesticStock: {
      // 特定預り
      specialDeposit: [
        { code: 'KR', name: 'クローガー', marginType: '保護預り', volume: 100, sellingVolume: 0, averagePrice: 202, currentPrice: 120.51, countryCode: 'US', currency: 'USD', market: 'NYSE', desc: { unit: 1, current: '128.0100', priceQuotation: 1, last: '128.9300', lastDate: '21/09/13', diff: '-0.92', ratio: '-0.71', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '129.7900', low: '127.1200', high: '130.2700', volume: 1695916, tick: '2' }},
        { code: 'OC', name: 'オーウェンス コーニング', marginType: '代用預り', volume: 200, sellingVolume: 200, averagePrice: 924, currentPrice: 1104.02, countryCode: 'US', currency: 'USD', market: 'ナスダック', desc: { unit: 1, current: '196.0300', priceQuotation: 1, last: '199.7900', lastDate: '21/09/13', diff: '-3.76', ratio: '-1.88', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '201.7100', low: '194.7050', high: '201.7100', volume: 762094, tick: '2' }}
      ],
      // 一般預り
      deposit: [
        { code: '00001', name: 'CK ハチソン', marginType: '保護預り', volume: 1000, sellingVolume: 0, averagePrice: 485, currentPrice: 500.11, countryCode: 'CN', currency: 'HKD', market: 'メインボード', desc: { unit: 500, current: '48.9000', priceQuotation: 1, last: '49.2100', lastDate: '21/09/13', diff: '-0.31', ratio: '-0.63', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '48.7000', low: '48.6000', high: '49.0500', volume: 2843649, tick: '2' }},
        { code: 'ROP', name: 'ローパー テクノロジーズ', marginType: '保護預り', volume: 200, sellingVolume: 0, averagePrice: 1100, currentPrice: 1176, countryCode: 'US', currency: 'USD', market: 'NYSEArca', desc: { unit: 1, current: '159.2600', priceQuotation: 1, last: '157.8700', lastDate: '21/09/13', diff: '+1.39', ratio: '+0.88', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '159.3499', low: '159.1400', high: '160.0600', volume: 5196908, tick: '1' }}
      ],
      // NISA預り
      nisaDeposit: [
        { code: 'AFLT', name: 'アエロフロート航空', marginType: '代用預り', volume: 600, sellingVolume: 0, averagePrice: 321, currentPrice: 558.2, countryCode: 'RU', currency: 'RUB', market: 'ロシア MICEX', desc: { unit: 1, current: '30.9600', priceQuotation: 1, last: '28.8700', lastDate: '21/09/13', diff: '+2.12', ratio: '+7.35', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '28.5800', low: '28.5800', high: '31.1000', volume: 33598730, tick: '1' }},
        { code: 'DBT', name: 'ベンチェー薬品', marginType: '代用預り', volume: 600, sellingVolume: 300, averagePrice: 1201, currentPrice: 1150, countryCode: 'VN', currency: 'VND', market: 'ホーチミン', desc: { unit: 1, current: '12,400.0000', priceQuotation: 1, last: '28.8700', lastDate: '21/09/13', diff: '+2.12', ratio: '+7.35', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '28.5800', low: '28.5800', high: '31.1000', volume: 33598730, tick: '1' }},
        { code: 'DELF', name: 'デフィ', marginType: '代用預り', volume: 200, sellingVolume: 0, averagePrice: 1104, currentPrice: 924, countryCode: 'SG', currency: 'SGD', market: 'シンガポール証券取引所', desc: { unit: 100, current: '30.9600', priceQuotation: 1, last: '28.8700', lastDate: '21/09/13', diff: '+2.12', ratio: '+7.35', diffDateTime: '21/09/14 15:00', limitDate: '21/09/14', start: '28.5800', low: '28.5800', high: '31.1000', volume: 33598730, tick: '1' }}
      ]
    },
    // 外貨建MMF
    foreignCurrencyMmf: {
      deposit: [
        { code: 'X099A000', name: 'トルコ・リラ・マネー・マーケットファンド', interest: '11.36', foreignVolume: 90, volume: 100000, sellingVolume: 50000, currency: { country: 'トルコ', name: 'リラ', code: 'TRY' }, contractExchange: 123100, currentPrice: 100, averagePrice: 202 },
        { code: 'X0934000', name: 'ブラックロック・スーパー・マネー・マーケット・ファンド', interest: '0.067', foreignVolume: 3, volume: 8500, sellingVolume: 0, currency: { country: '米', name: 'ドル', code: 'USD' }, contractExchange: 15200, currentPrice: 100, averagePrice: 54 }
      ]
    },
    // 国内債券
    domesticBond: {
      deposit: [
        { issueType: '1', rank: '3', code: '0031.1', name: '入札前取引利付国債（１５年）（２００５年１月発行銘柄）', compoundInterest: '3.22', redemptionDate: '2030/01/20', interestPaymentDate: '03/09-末日', volume: 2500000, unitPrice: 500000, contractExchange: '-', referenceExchange: '-', unit: { lower: 500000, unit: 500000 }},
        { issueType: '0', rank: '3', code: '0039.10', name: '株価連動債（キーエンス）', compoundInterest: '5.325', redemptionDate: '2022/06/24', interestPaymentDate: '-', volume: 500000, unitPrice: 1000, contractExchange: '-', referenceExchange: '-', unit: { lower: 1000, unit: 1000 }}
      ],
      specialDeposit: [
        { issueType: '0', rank: '3', code: 'P2601', name: '株価連動債（日立製作所）', compoundInterest: '5.325', redemptionDate: '2023/06/23', interestPaymentDate: '-', volume: 2000000, unitPrice: 1000, contractExchange: '-', referenceExchange: '-', unit: { lower: 1000, unit: 1000 }}
      ]
    },
    // 外国債券
    foreignBond: {
      deposit: [
        { issueType: '1', rank: '3', code: 'P1312', name: 'ブラジルレアル建ディスカウント債券（円貨決済型）0.8%', currency: 'JPY', compoundInterest: '3.93', redemptionDate: '2030/01/20', interestPaymentDate: '06/02,12/02', volume: 1000000, unitPrice: 100, contractExchange: '-', referenceExchange: '-', unit: { lower: 10000, unit: 1000, code: '円' }},
        { issueType: '0', rank: '3', code: 'R2353', name: '国際復興開発銀行　７．４０％ ２０１９０７１９', currency: 'USD', compoundInterest: '5.325', redemptionDate: '2036/12/10', interestPaymentDate: '06/02,12/02', volume: 70000, unitPrice: 100, contractExchange: '132.23', referenceExchange: 'USD', unit: { lower: 100, unit: 100, code: '米ドル' }}
      ],
      specialDeposit: [
        { issueType: '1', rank: '3', code: 'R8843', name: 'バンク・オブ・アメリカ　ゼロクーポン債 ２０３１１１２９', currency: 'AUD', compoundInterest: '3.22', redemptionDate: '2038/09/20', interestPaymentDate: '06/02,12/02', volume: 1000000, unitPrice: 119.73, contractExchange: '98.81', referenceExchange: 'AUD', unit: { lower: 10000, unit: 1000, code: 'AUD' }},
        { issueType: '1', rank: '3', code: 'R5637', name: 'アフリカ開発銀行　７．２０％ ２０２１０２１７', currency: 'BRL', compoundInterest: '2.85', redemptionDate: '2030/01/20', interestPaymentDate: '06/02,12/02', volume: 80000, unitPrice: 122.06, contractExchange: '28.546', referenceExchange: 'BRL', unit: { lower: 10000, unit: 1000, code: 'BRL' }},
        { issueType: '0', rank: '3', code: 'P4396', name: 'ＳＢＩ　ＥＢ（大成建設） ２０１９０１２１', currency: 'USD', compoundInterest: '5.325', redemptionDate: '2023/06/23', interestPaymentDate: '06/02,12/02', volume: 200000, unitPrice: 100, contractExchange: '132.23', referenceExchange: 'USD', unit: { lower: 100, unit: 100, code: '米ドル' }}
      ]
    },
    // その他商品
    otherProduct: {
      // 特定預り
      specialDeposit: [
        { productKind: '国内CP', name: 'JR東海', volume: 10000000 },
        { productKind: '外国投信', name: 'バンガード・スモールキャップ・インデックス・ファンド', volume: 1000000 }
      ],
      // 一般預り
      deposit: [
        { productKind: '国内CP', name: 'トヨタ自動車', volume: 3000000 },
        { productKind: '外国投信', name: 'JPモルガン・F-ヨーロッパ・エクイティ・ファンド', volume: 2000000 }
      ]
    }
  }
}
