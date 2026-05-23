export const IfaCodeListDisplayPatternModel = {
  displayPatternModel: [
    {
      codeListId: 'INVITATION_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '勧誘あり' },
            { key: '2', value: '勧誘なし' }
          ]
        }
      ]
    },
    {
      codeListId: 'ORDER_METHOD_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '3', value: '電話他' },
            { key: '1', value: '店頭' },
            { key: '2', value: '訪問' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRADE_RESTRICTIONS_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '取引制限なし' },
            { key: '1', value: '取引制限あり' }
          ]
        }
      ]
    },
    {
      codeListId: 'FAVORITE_REGISTER_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '登録なし' },
            { key: '1', value: '登録あり' }
          ]
        }
      ]
    },
    {
      codeListId: 'RESTRICTION_KIND',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '注意情報' },
            { key: '2', value: '未読の重要なお知らせによる取引制限' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRADE_RESTRICTIONS_SETTING_VALUE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: 'アラート' },
            { key: '2', value: 'エラー' }
          ]
        }
      ]
    },
    {
      codeListId: 'CORPORATION_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '個人' },
            { key: '1', value: '法人' }
          ]
        }
      ]
    },
    {
      codeListId: 'TITLE_OF_AGENT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '取締役社長' },
            { key: '02', value: '代表取締役' },
            { key: '03', value: '取締役' },
            { key: '04', value: '理事長' },
            { key: '05', value: '理事' },
            { key: '06', value: '取締役頭取' },
            { key: '07', value: '代表社員' },
            { key: '08', value: '組合長' },
            { key: '09', value: '医院長' },
            { key: '10', value: '支店長' },
            { key: '11', value: '営業所長' },
            { key: '12', value: '支配人' },
            { key: '13', value: '専務取締役' },
            { key: '14', value: '常務取締役' },
            { key: '15', value: '経理部' },
            { key: '16', value: '財務部' },
            { key: '17', value: '総務部' },
            { key: '18', value: '証券部' },
            { key: '19', value: '有価証券部' },
            { key: '20', value: '資金部' },
            { key: '21', value: '秘書室' },
            { key: '$NULL', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'SPECIFIC_ACCOUNT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '特定口座(代行納付)' },
            { key: '2', value: '特定口座(確定申告)' },
            { key: '3', value: '非特定口座' },
            { key: ' ', value: '未登録' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '特定源徴有' },
            { key: '2', value: '特定源徴無' },
            { key: '3', value: '一般' },
            { key: ' ', value: '一般' }
          ]
        }
      ]
    },
    {
      codeListId: 'TAX_EXEMPT_QUALIFIED_PERSON_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '老人' },
            { key: '2', value: '障害者' },
            { key: '3', value: 'その他' },
            { key: ' ', value: '初期値' },
            { key: '$NULL', value: ' ' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: 'マル優' },
            { key: '2', value: 'マル優' },
            { key: '3', value: 'マル優' },
            { key: ' ', value: ' ' },
            { key: '$NULL', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'ISA_CONTRACT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '契約' },
            { key: '9', value: '閉鎖済' },
            { key: ' ', value: '未契約' }
          ]
        }
      ]
    },
    {
      codeListId: 'JR_ISA_CONTRACT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '契約' },
            { key: '9', value: '閉鎖済' },
            { key: ' ', value: '未契約' }
          ]
        }
      ]
    },
    {
      codeListId: 'MSG_CONFIRM_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'DIVIDEND_RECEIPT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '該当しない' },
            { key: '1', value: '該当する' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: '配受無' }
          ]
        }
      ]
    },
    {
      codeListId: 'NOTICE_INFO_LEVEL',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '注意情報なし' },
            { key: '2', value: '注意情報あり（エラーなし）' },
            { key: '3', value: '注意情報あり（エラーあり）' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_SECURITY_TRADE_ACCOUNT_OPEN_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未開設' },
            { key: '1', value: '開設済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: '外貨建商品' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未開設' },
            { key: '1', value: '開設済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: '外国株式' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_BUY_POWER_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '無' },
            { key: '1', value: '有' }
          ]
        }
      ]
    },
    {
      codeListId: 'DOMESTIC_MARGIN_ACCOUNT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: '現物口座' },
            { key: '1', value: '信用口座' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: ' ', value: ' ' },
            { key: '1', value: '国内信用' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_MARGIN_ACCOUNT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: '現物口座' },
            { key: '1', value: '信用口座' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: ' ', value: ' ' },
            { key: '1', value: '米株信用' }
          ]
        }
      ]
    },
    {
      codeListId: 'NISA_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '成人NISA' },
            { key: '2', value: 'つみたてNISA' },
            { key: '3', value: '総合NISA' },
            { key: ' ', value: ' ' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: 'NISA' },
            { key: '2', value: '積立NISA' },
            { key: '3', value: 'NISA' },
            { key: ' ', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'WITHDRAWAL_RESTRICTION_CANCEL_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: '払出制限解除' },
            { key: '1', value: '払出制限中' }
          ]
        }
      ]
    },
    {
      codeListId: 'COMM_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: 'スタンダード' },
            { key: '2', value: 'コールセンター' },
            { key: '3', value: 'スタンダード' },
            { key: '4', value: '-' },
            { key: '5', value: 'スタンダード' },
            { key: '6', value: '立会外' },
            { key: '7', value: 'スタンダード' },
            { key: '8', value: 'スタンダード' },
            { key: '9', value: 'アクティブ' },
            { key: '10', value: 'S株' },
            { key: '11', value: 'コールセンター' },
            { key: '12', value: 'PTS' },
            { key: '13', value: 'コールセンター' },
            { key: '14', value: 'PTS' },
            { key: '15', value: 'PTS' },
            { key: '16', value: 'PTS' },
            { key: '17', value: 'PTS' },
            { key: '18', value: '支店手数料' },
            { key: '19', value: '支店手数料2' },
            { key: '20', value: '支店手数料' },
            { key: '21', value: '支店手数料2' },
            { key: '22', value: '支店手数料' },
            { key: '23', value: '支店手数料2' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: 'インターネット手数料' },
            { key: '2', value: 'コールセンター手数料' },
            { key: '3', value: 'I-MODE手数料' },
            { key: '4', value: '立会外分売' },
            { key: '5', value: 'HTS手数料' },
            { key: '6', value: '立会外トレード' },
            { key: '7', value: 'I-HET手数料' },
            { key: '9', value: 'Active' },
            { key: '10', value: '単位未満株' },
            { key: '11', value: '単位未満株' },
            { key: '18', value: '対面プランA' },
            { key: '19', value: '対面プランB・ダイレクト' }
          ]
        }
      ]
    },
    {
      codeListId: 'DOMESTIC_FOREIGN_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '国内' },
            { key: '1', value: '外国' }
          ]
        }
      ]
    },
    {
      codeListId: 'SECURITY_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1 ', value: '株式' },
            { key: '2 ', value: '債券' },
            { key: '3 ', value: '投信' }
          ]
        }
      ]
    },
    {
      codeListId: 'COMPLA_MATRIX_SETTING_VALUE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '00', value: 'ノーマル' },
            { key: '01', value: 'ワーニング' },
            { key: '02', value: 'エラー' },
            { key: '03', value: 'フェイタル' }
          ]
        }
      ]
    },
    {
      codeListId: 'COMPLA_CHECK_KIND',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '買付注文' },
            { key: '2', value: '目論見書請求' }
          ]
        }
      ]
    },
    {
      codeListId: 'COMPLA_CHECK_JUDGMENT_RESULT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'ノーマル' },
            { key: '1', value: 'アラート' },
            { key: '2', value: 'エラー' }
          ]
        }
      ]
    },
    {
      codeListId: 'COMPLA_CHECK_FUND_CHARACTER',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '余裕資金' },
            { key: '2', value: 'その他' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_CHARACTER_PERSONAL_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '余裕資金' },
            { key: '02', value: '使途確定金' },
            { key: '03', value: '借入金' },
            { key: '04', value: '相続財産' },
            { key: '05', value: '退職金' },
            { key: '06', value: '年金・生活費' },
            { key: '09', value: 'その他' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_CHARACTER_CORPORATION_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '余裕資金' },
            { key: '2', value: '使途確定金' },
            { key: '3', value: '借入金' },
            { key: '4', value: '相続財産' },
            { key: '9', value: 'その他' }
          ]
        }
      ]
    },
    {
      codeListId: 'SECURITY_MONEY_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '国内株式' },
            { key: '06', value: '国内投信' },
            { key: '12', value: '円貨建債券' },
            { key: '15', value: '外国株式' },
            { key: '16', value: '外貨建MMF' },
            { key: '08', value: '外国投信' },
            { key: '14', value: '外貨建債券' },
            { key: '99', value: '円貨' },
            { key: '98', value: '外貨' }
          ]
        }
      ]
    },
    {
      codeListId: 'LIMIT_MARKET_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: '指値' },
            { key: 'Z', value: '寄指(Y)' },
            { key: 'I', value: '引指(H)' },
            { key: 'F', value: '不成(F)' },
            { key: 'P', value: 'IOC指(I)' },
            { key: 'N', value: '成行' },
            { key: 'Y', value: '寄成(Y)' },
            { key: 'H', value: '引成(H)' },
            { key: 'O', value: 'IOC成(I)' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: ' ', value: '条件なし' },
            { key: 'Z', value: '寄指(Y)' },
            { key: 'I', value: '引指(H)' },
            { key: 'F', value: '不成(F)' },
            { key: 'P', value: 'IOC指(I)' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: 'N', value: '条件なし' },
            { key: 'Y', value: '寄成(Y)' },
            { key: 'H', value: '引成(H)' },
            { key: 'O', value: 'IOC成(I)' }
          ]
        }
      ]
    },
    {
      codeListId: 'NATIONALITY_CODE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'US', value: 'アメリカ合衆国(米国)' },
            { key: 'HK', value: '香港' },
            { key: 'KR', value: '大韓民国' },
            { key: 'RU', value: 'ロシア連邦' },
            { key: 'VN', value: 'ベトナム社会主義共和国' },
            { key: 'ID', value: 'インドネシア共和国' },
            { key: 'SG', value: 'シンガポール共和国' },
            { key: 'TH', value: 'タイ王国' },
            { key: 'MY', value: 'マレーシア' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: 'US', value: '米国' },
            { key: 'HK', value: '中国' },
            { key: 'KR', value: '韓国' },
            { key: 'RU', value: 'ロシア' },
            { key: 'VN', value: 'ベトナム' },
            { key: 'ID', value: 'インドネシア' },
            { key: 'SG', value: 'シンガポール' },
            { key: 'TH', value: 'タイ' },
            { key: 'MY', value: 'マレーシア' }
          ]
        }
      ]
    },
    {
      codeListId: 'CURRENCY_CODE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'USD', value: '米ドル' },
            { key: 'HKD', value: '香港ドル' },
            { key: 'EUR', value: 'ユーロ' },
            { key: 'AUD', value: 'オーストラリアドル' },
            { key: 'NZD', value: 'ニュージーランドドル' },
            { key: 'CAD', value: 'カナダドル' },
            { key: 'ZAR', value: 'ランド' },
            { key: 'MXN', value: 'メキシコペソ' },
            { key: 'TRY', value: 'トルコリラ' },
            { key: 'SGD', value: 'シンガポールドル' },
            { key: 'KRW', value: 'ウォン' },
            { key: 'RUB', value: 'ロシアルーブル' },
            { key: 'VND', value: 'ドン' },
            { key: 'IDR', value: 'ルピア' },
            { key: 'THB', value: 'バーツ' },
            { key: 'MYR', value: 'マレーシアリンギット' },
            { key: 'CNY', value: '人民元' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: 'USD', value: '米ドル' },
            { key: 'HKD', value: '香港ドル' },
            { key: 'EUR', value: 'ユーロ' },
            { key: 'AUD', value: '豪ドル' },
            { key: 'NZD', value: 'NZドル' },
            { key: 'CAD', value: 'カナダドル' },
            { key: 'ZAR', value: '南アランド' },
            { key: 'MXN', value: 'メキシコペソ' },
            { key: 'TRY', value: 'トルコリラ' },
            { key: 'SGD', value: 'シンガポールドル' },
            { key: 'KRW', value: '韓国ウォン' },
            { key: 'RUB', value: 'ロシアルーブル' },
            { key: 'VND', value: 'ベトナムドン' },
            { key: 'IDR', value: 'インドネシアルピア' },
            { key: 'THB', value: 'タイバーツ' },
            { key: 'MYR', value: 'マレーシアリンギット' },
            { key: 'CNY', value: '人民元' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: 'USD', value: 'USD' },
            { key: 'HKD', value: 'HKD' },
            { key: 'EUR', value: 'EUR' },
            { key: 'AUD', value: 'AUD' },
            { key: 'NZD', value: 'NZD' },
            { key: 'CAD', value: 'CAD' },
            { key: 'ZAR', value: 'ZAR' },
            { key: 'MXN', value: 'MXN' },
            { key: 'TRY', value: 'TRY' },
            { key: 'SGD', value: 'SGD' },
            { key: 'KRW', value: 'KRW' },
            { key: 'RUB', value: 'RUB' },
            { key: 'VND', value: 'VND' },
            { key: 'IDR', value: 'IDR' },
            { key: 'THB', value: 'THB' },
            { key: 'MYR', value: 'MYR' },
            { key: 'CNY', value: 'CNY' }
          ]
        },
        {
          displayPattern: 4,
          codeList: [
            { key: 'USD', value: '米国' }
          ]
        },
        {
          displayPattern: 5,
          codeList: [
            { key: 'USD', value: '米国' },
            { key: 'HKD', value: '香港' },
            { key: 'EUR', value: 'ユーロ' },
            { key: 'AUD', value: 'オーストラリア' },
            { key: 'NZD', value: 'ニュージーランド' },
            { key: 'CAD', value: 'カナダ' },
            { key: 'ZAR', value: '南アフリカ' },
            { key: 'MXN', value: 'メキシコ' },
            { key: 'TRY', value: 'トルコ' },
            { key: 'SGD', value: 'シンガポール' },
            { key: 'KRW', value: '韓国' },
            { key: 'RUB', value: 'ロシア' },
            { key: 'VND', value: 'ベトナム' },
            { key: 'IDR', value: 'インドネシア' },
            { key: 'THB', value: 'タイ' },
            { key: 'MYR', value: 'マレーシア' },
            { key: 'CNY', value: '中国' }
          ]
        }
      ]
    },
    {
      codeListId: 'SELL_BUY_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '3', value: '買付' },
            { key: '1', value: '売却' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '3', value: '現物買付' },
            { key: '1', value: '現物売却' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '3', value: '信用新規買' },
            { key: '1', value: '信用新規売' }
          ]
        },
        {
          displayPattern: 4,
          codeList: [
            { key: '3', value: '現物買' },
            { key: '1', value: '現物売' }
          ]
        },
        {
          displayPattern: 5,
          codeList: [
            { key: '3', value: '信用返済買' },
            { key: '1', value: '信用返済売' }
          ]
        },
        {
          displayPattern: 6,
          codeList: [
            { key: '3', value: '買建' },
            { key: '1', value: '売建' }
          ]
        },
        {
          displayPattern: 7,
          codeList: [
            { key: '3', value: '店頭買' },
            { key: '1', value: '店頭売' }
          ]
        }
      ]
    },
    {
      codeListId: 'FX_TRADE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: 'リアルタイム為替' },
            { key: '2', value: '定時為替' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: 'リアルタイム' },
            { key: '2', value: '定時為替' }
          ]
        }
      ]
    },
    {
      codeListId: 'FX_GROUP',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'FXCHANGE_GROUP_USD', value: '米ドル' },
            { key: 'FXCHANGE_GROUP_HKD', value: '香港ドル' },
            { key: 'FXCHANGE_GROUP_KRW', value: '韓国ウォン' },
            { key: 'FXCHANGE_GROUP_EUR', value: 'ユーロ' },
            { key: 'FXCHANGE_GROUP_AUD', value: '豪ドル' },
            { key: 'FXCHANGE_GROUP_NZD', value: 'NZドル' },
            { key: 'FXCHANGE_GROUP_CAD', value: 'カナダドル' },
            { key: 'FXCHANGE_GROUP_ZAR', value: '南アランド' },
            { key: 'FXCHANGE_GROUP_MXN', value: 'メキシコペソ' },
            { key: 'FXCHANGE_GROUP_TRY', value: 'トルコリラ' },
            { key: 'FXCHANGE_GROUP_RUB', value: 'ロシアルーブル' },
            { key: 'FXCHANGE_GROUP_VND', value: 'ベトナムドン' },
            { key: 'FXCHANGE_GROUP_IDR', value: 'インドネシアルピア' },
            { key: 'FXCHANGE_GROUP_SGD', value: 'シンガポールドル' },
            { key: 'FXCHANGE_GROUP_THB', value: 'タイバーツ' },
            { key: 'FXCHANGE_GROUP_MYR', value: 'マレーシアリンギット' },
            { key: 'FXCHANGE_GROUP_CNY', value: '人民元' }
          ]
        }
      ]
    },
    {
      codeListId: 'SELL_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '指定した数量を売却' },
            { key: '2', value: '全て売却' }
          ]
        }
      ]
    },
    {
      codeListId: 'CALENDAR_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'CONFIRM_DOC_ACCEPTANCE_NECESSITY',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'COMPLA_CHECK_BOX_WORDING',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '△・◇ワーニング申請は「申請・承認済」であることを確認済' },
            { key: '2', value: '勧誘なし' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '*ワーニング申請取引' },
            { key: '2', value: '*勧誘なし再確認' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '1', value: 'ワーニング申請取引' },
            { key: '2', value: '勧誘なし再確認' }
          ]
        },
        {
          displayPattern: 4,
          codeList: [
            { key: '1', value: '申請・承認済' },
            { key: '2', value: '勧誘なし' }
          ]
        }
      ]
    },
    {
      codeListId: 'SELECT_MARKET',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'A', value: '当社優先市場／SOR' },
            { key: '0', value: '東証' },
            { key: '2', value: '名証' },
            { key: '6', value: '福証' },
            { key: '8', value: '札証' },
            { key: '7', value: 'PTS' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: 'A', value: 'SOR' },
            { key: '0', value: '東証' },
            { key: '2', value: '名証' },
            { key: '6', value: '福証' },
            { key: '8', value: '札証' },
            { key: '7', value: 'PTS' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_DEPOSIT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '一般' },
            { key: '2', value: '特定' },
            { key: '3', value: '特定管理' },
            { key: '4', value: '旧NISA' },
            { key: '5', value: 'Jr一般' },
            { key: '6', value: 'Jr特定' },
            { key: '7', value: 'JrNISA' },
            { key: 'H', value: 'NISA' },
            { key: 'J', value: 'JrNISA継続' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '一般預り' },
            { key: '2', value: '特定預り' },
            { key: '3', value: '特定管理' },
            { key: '4', value: '旧NISA預り' },
            { key: '5', value: 'Jr一般預り' },
            { key: '6', value: 'Jr特定預り' },
            { key: '7', value: 'JrNISA預り' },
            { key: 'H', value: 'NISA預り' },
            { key: 'J', value: 'Jr継続NISA預り' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '1', value: '一般預り' },
            { key: '2', value: '特定預り' },
            { key: '3', value: '特定管理預り' },
            { key: '4', value: '旧NISA預り' },
            { key: '5', value: 'JrNISA－一般預り' },
            { key: '6', value: 'JrNISA－特定預り' },
            { key: '7', value: 'JrNISA－NISA預り' },
            { key: 'H', value: 'NISA預り' },
            { key: 'J', value: 'Jr継続NISA預り' }
          ]
        },
        {
          displayPattern: 4,
          codeList: [
            { key: '1', value: '一般預り' },
            { key: '2', value: '特定預り' },
            { key: '3', value: '特定管理' },
            { key: '4', value: '旧NISA預り' },
            { key: '5', value: 'Jr一般預り' },
            { key: '6', value: 'Jr特定預り' },
            { key: '7', value: 'JrNISA預り' },
            { key: 'H', value: 'NISA預り（成長投資枠）' },
            { key: 'J', value: 'Jr継続NISA預り' }
          ]
        }
      ]
    },
    {
      codeListId: 'SETTLEMENT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '円貨決済' },
            { key: '2', value: '外貨決済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '円決' },
            { key: '2', value: '外決' }
          ]
        }
      ]
    },
    {
      codeListId: 'PRE_CONTRACT_DOC_CODE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'A', value: 'インターネットコース' },
            { key: 'B', value: 'コールセンターコース' },
            { key: 'C', value: 'ダイレクトコース' },
            { key: 'D', value: 'IFAコース' },
            { key: 'E', value: 'IFAコース（プランA）' },
            { key: 'F', value: 'プランAコース' },
            { key: 'G', value: 'プランBコース' },
            { key: 'H', value: '対面取引コース' },
            { key: 'I', value: 'IFAコース（プランA-2）' },
            { key: 'J', value: 'インターネットコース（プランC）' },
            { key: 'K', value: 'WMコース' },
            { key: 'L', value: 'レベルフィーコース' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: 'A', value: 'コールセンター手数料' },
            { key: 'B', value: 'コールセンター手数料' },
            { key: 'C', value: '対面プランB・ダイレクト' },
            { key: 'D', value: '対面プランB・ダイレクト' },
            { key: 'E', value: '対面プランA' },
            { key: 'F', value: '対面プランA' },
            { key: 'G', value: '対面プランB・ダイレクト' },
            { key: 'H', value: '対面プランA' },
            { key: 'I', value: '対面プランA' },
            { key: 'J', value: 'コールセンター手数料' },
            { key: 'K', value: '対面プランA' },
            { key: 'L', value: '対面プランA' }
          ]
        }
      ]
    },
    {
      codeListId: 'SELECT_ABLE_PRICE_CONDITIONS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '指値' },
            { key: '2', value: '成行' },
            { key: '3', value: '逆指値/指値' },
            { key: '4', value: '逆指値/成行' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '指値' },
            { key: '2', value: '成行' },
            { key: '3', value: '逆指値' },
            { key: '4', value: '逆指値' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '2', value: '成行' },
            { key: '3', value: '逆指値/指値' },
            { key: '4', value: '逆指値/成行' }
          ]
        },
        {
          displayPattern: 4,
          codeList: [
            { key: '1', value: '指値' },
            { key: '2', value: '成行' },
            { key: '3', value: '指値' },
            { key: '4', value: '成行' }
          ]
        },
        {
          displayPattern: 5,
          codeList: [
            { key: '1', value: '通常注文' },
            { key: '2', value: '通常注文' },
            { key: '3', value: '逆指値注文' },
            { key: '4', value: '逆指値注文' }
          ]
        }
      ]
    },
    {
      codeListId: 'DOMESTIC_DEPOSIT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '総合口座－特定' },
            { key: '1', value: '総合口座－一般' },
            { key: '-', value: '-' },
            { key: '4', value: '総合口座－旧NISA' },
            { key: '5', value: 'ジュニアNISA口座－特定' },
            { key: '6', value: 'ジュニアNISA口座－一般' },
            { key: '7', value: 'ジュニアNISA口座－NISA' },
            { key: '8', value: '-' },
            { key: ' ', value: ' ' },
            { key: 'H', value: '総合口座－NISA（成長投資枠）' },
            { key: 'J', value: 'ジュニアNISA口座－継続NISA' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: '特定' },
            { key: '1', value: '一般' },
            { key: '-', value: '-' },
            { key: '4', value: '旧NISA' },
            { key: '5', value: 'Jr特定' },
            { key: '6', value: 'Jr一般' },
            { key: '7', value: 'JrNISA' },
            { key: '8', value: 'つみたてNISA' },
            { key: ' ', value: ' ' },
            { key: 'H', value: 'NISA（成長投資枠）' },
            { key: 'J', value: 'JrNISA継続' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '0', value: '特定' },
            { key: '1', value: '一般' },
            { key: '-', value: '一般' },
            { key: '4', value: '旧NISA' },
            { key: '5', value: 'Jr特定' },
            { key: '6', value: 'Jr一般' },
            { key: '7', value: 'JrNISA' },
            { key: ' ', value: '-' },
            { key: 'H', value: 'NISA（成長投資枠）' },
            { key: 'J', value: 'JrNISA継続' }
          ]
        },
        {
          displayPattern: 4,
          codeList: [
            { key: '0', value: '特定' },
            { key: '1', value: '一般' },
            { key: '-', value: '一般' }
          ]
        },
        {
          displayPattern: 5,
          codeList: [
            { key: '0', value: '特定預り' },
            { key: '1', value: '一般預り' },
            { key: '-', value: '一般預り' },
            { key: '4', value: '旧NISA預り' },
            { key: '5', value: 'ジュニア特定預り' },
            { key: '6', value: 'ジュニア一般預り' },
            { key: '7', value: 'ジュニアNISA預り' },
            { key: ' ', value: '-' },
            { key: 'H', value: 'NISA預り（成長投資枠）' },
            { key: 'J', value: 'ジュニア継続NISA預り' }
          ]
        },
        {
          displayPattern: 6,
          codeList: [
            { key: '0', value: '特定' },
            { key: '1', value: '一般' },
            { key: '4', value: 'NISA' }
          ]
        },
        {
          displayPattern: 7,
          codeList: [
            { key: '0', value: '一般預り' },
            { key: '1', value: '特定預り' },
            { key: '-', value: '特定管理' },
            { key: '4', value: '旧NISA預り' },
            { key: '5', value: 'Jr一般預り' },
            { key: '6', value: 'Jr特定預り' },
            { key: '7', value: 'JrNISA預り' },
            { key: '8', value: '旧つみたてNISA預り' },
            { key: 'H', value: 'NISA預り（成長投資枠）' },
            { key: 'J', value: 'ジュニア継続NISA預り' },
            { key: 'I', value: 'NISA預り（つみたて）' }
          ]
        },
        {
          displayPattern: 8,
          codeList: [
            { key: '0', value: '総合口座－特定' },
            { key: '1', value: '総合口座－一般' },
            { key: '-', value: '総合口座－一般' },
            { key: '4', value: '総合口座－旧NISA' },
            { key: '5', value: 'ジュニアNISA口座－特定' },
            { key: '6', value: 'ジュニアNISA口座－一般' },
            { key: '7', value: 'ジュニアNISA口座－NISA' },
            { key: '8', value: '-' },
            { key: ' ', value: ' ' },
            { key: 'H', value: '総合口座－NISA（成長投資枠）' },
            { key: 'J', value: 'ジュニアNISA口座－継続NISA' }
          ]
        },
        {
          displayPattern: 9,
          codeList: [
            { key: '0', value: '特定' },
            { key: '1', value: '一般' },
            { key: '-', value: '一般' },
            { key: '4', value: '旧NISA' },
            { key: '5', value: 'Jr特定' },
            { key: '6', value: 'Jr一般' },
            { key: '7', value: 'JrNISA' },
            { key: '8', value: 'つみたてNISA' },
            { key: ' ', value: ' ' },
            { key: 'H', value: 'NISA（成長投資枠）' },
            { key: 'J', value: 'JrNISA継続' }
          ]
        }
      ]
    },
    {
      codeListId: 'POSITION_SELL_BUY_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '買建' },
            { key: '1', value: '売建' }
          ]
        }
      ]
    },
    {
      codeListId: 'MARGIN_DUE_DATE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '無期限' },
            { key: '1', value: '6ヶ月' }
          ]
        }
      ]
    },
    {
      codeListId: 'COLLATERAL_ELIGIBLE_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '不適格' },
            { key: '1', value: '適格' }
          ]
        }
      ]
    },
    {
      codeListId: 'SELL_UNABLE_DETAIL_SECURITY_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'K', value: '国内株式' },
            { key: 'T', value: '投資信託' },
            { key: 'Y', value: '投資信託' }
          ]
        }
      ]
    },
    {
      codeListId: 'RESTRICTION_REASON',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '11', value: '保振移管' },
            { key: '21', value: '買取請求' },
            { key: '31', value: '個別株オプション' },
            { key: '32', value: '名義書換出庫（出庫中）' },
            { key: '41', value: '特定→一般' },
            { key: '42', value: '一般→特定' },
            { key: '43', value: '特定→特定' },
            { key: '51', value: '株券出庫' },
            { key: '61', value: 'TOB予約' },
            { key: '91', value: 'その他（WEB表示）' },
            { key: '92', value: 'その他（WEB非表示）' }
          ]
        }
      ]
    },
    {
      codeListId: 'IMPORTANT_MATTERS_EXPLAIN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '説明済' },
            { key: '1', value: '説明不要を確認（属性登録済）' }
          ]
        }
      ]
    },
    {
      codeListId: 'ETF_SOLICITING_TRANSFERS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '勧誘あり（説明明記、確認書受入）' },
            { key: '0', value: '勧誘なし' }
          ]
        }
      ]
    },
    {
      codeListId: 'ISSUES_DISCLOSED_IN_ENGLISH_BRAND',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '未説明' },
            { key: '0', value: '説明済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '未説明' },
            { key: '0', value: '説明済' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '1', value: '未説明' },
            { key: '0', value: '説明済' }
          ]
        }
      ]
    },
    {
      codeListId: 'CONFIRM_DOC_ACCEPTANCE_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'ACCEPTANCE_DOC_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'MUTUAL_FUND_BRAND_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: ' ' },
            { key: '2', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'TOTAL_RETURN_SPECIFIC_DEPOSIT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '特定/一般' },
            { key: '4', value: '旧NISA/旧つみたてNISA' },
            { key: '5', value: 'ジュニア特定/一般' },
            { key: '7', value: 'ジュニアNISA' },
            { key: 'H', value: 'NISA預り（成長投資枠）' },
            { key: 'I', value: 'NISA預り（つみたて）' },
            { key: 'J', value: 'ジュニア継続NISA預り' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRADE_NOTICE_INFO_EXPLAIN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未説明' },
            { key: '1', value: '説明済' }
          ]
        }
      ]
    },
    {
      codeListId: 'SPECIFIC_POSITION_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '一般' },
            { key: '1', value: '特定' },
            { key: '-', value: '-' }
          ]
        }
      ]
    },
    {
      codeListId: 'ACCOUNT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '2', value: '全て' },
            { key: ' ', value: '総合口座' },
            { key: '1', value: 'ジュニアNISA口座' }
          ]
        }
      ]
    },
    {
      codeListId: 'PERIOD_CONDITIONS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '当日中' },
            { key: '1', value: '期間指定' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: '当日注文' }
          ]
        }
      ]
    },
    {
      codeListId: 'DISTRIBUTION_RECEIVE_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: '-' },
            { key: '2', value: '再投資' },
            { key: '1', value: '受取' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '2', value: '再投資' },
            { key: '1', value: '受取' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: ' ', value: '無' },
            { key: '2', value: '有(再投資)' },
            { key: '1', value: '有(受取)' }
          ]
        },
        {
          displayPattern: 4,
          codeList: [
            { key: ' ', value: '指定なし' },
            { key: '2', value: '再投資' },
            { key: '1', value: '受取' }
          ]
        }
      ]
    },
    {
      codeListId: 'FILE_OUTPUT_DEPOSIT_SELL_BUY_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: ' ' },
            { key: '1', value: ' ' },
            { key: '4', value: ' ' },
            { key: 'H', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'BRAND_ACQUIRE_PROPRIETY',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'PORTAL_USER_NOTIFICATION_REFERENCE_SCOPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '全担当者' },
            { key: '2', value: '権限担当者' },
            { key: '3', value: '個別担当者' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '全担当者設定' },
            { key: '2', value: '権限設定' },
            { key: '3', value: '個別担当者設定' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_TRANSFER_CHECK',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: ' ' },
            { key: '1', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'NEW_MARKET',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '東証' },
            { key: '7', value: 'PTS' }
          ]
        }
      ]
    },
    {
      codeListId: 'NEW_CREDIT_SELL_BUY_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '買建' },
            { key: '1', value: '売建' }
          ]
        }
      ]
    },
    {
      codeListId: 'PAYMENT_DEADLINE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '6', value: '6ヶ月' },
            { key: '9', value: '無期限' },
            { key: 'A', value: '1日' },
            { key: 'B', value: '5日' },
            { key: 'D', value: '15日' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '6', value: '(6ヶ月)' },
            { key: '9', value: '(無期限)' },
            { key: 'A', value: '(1日)' },
            { key: 'B', value: '(5日)' },
            { key: 'D', value: '(15日)' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '6', value: '(制度/6ヶ月)' },
            { key: '9', value: '(一般/無期限)' },
            { key: 'A', value: '(一般/1日)' },
            { key: 'B', value: '(一般/5日)' },
            { key: 'D', value: '(一般/15日)' }
          ]
        }
      ]
    },
    {
      codeListId: 'COERCION_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '-' },
            { key: '1', value: '期日' },
            { key: '2', value: '追証' },
            { key: '3', value: '在庫不足' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: '-' },
            { key: '1', value: '期日' },
            { key: '2', value: '追証' },
            { key: '3', value: '-' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRAD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '買付' },
            { key: '2', value: '売却' },
            { key: '3', value: '買決済' },
            { key: '4', value: '売決済' },
            { key: '5', value: '現引' },
            { key: '6', value: '現渡' },
            { key: '7', value: '買決済' },
            { key: '8', value: '売決済' }
          ]
        }
      ]
    },
    {
      codeListId: 'SUBSCRIPT_ORDER_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未注文' },
            { key: '1', value: '募集入力済' },
            { key: '2', value: '受付済' },
            { key: '3', value: '約定済' },
            { key: '4', value: '不出来' },
            { key: '5', value: '手入力' },
            { key: '6', value: '募集取消' },
            { key: '9', value: 'StarUplaod file 出力対象(占用)' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '1', value: '募集入力済' },
            { key: '2', value: '受付済' },
            { key: '3', value: '約定済' },
            { key: '4', value: '不出来' },
            { key: '5', value: '手入力' }
          ]
        }
      ]
    },
    {
      codeListId: 'SORT_ORDER',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '61', value: '評価益順' },
            { key: '51', value: '評価損順' },
            { key: '16', value: '建日古い順' },
            { key: '26', value: '建日新しい順' }
          ]
        }
      ]
    },
    {
      codeListId: 'BB_ACCEPT_MARKET_EXECUTION',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'BB_BRAND_ISSUE_PRICE_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: ' ' },
            { key: '2', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'BB_ACCEPT_ELECTED_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: ' ' },
            { key: '2', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '一般' },
            { key: '2', value: '特定' },
            { key: '4', value: '旧NISA' },
            { key: 'H', value: 'NISA（成長投資枠）' }
          ]
        }
      ]
    },
    {
      codeListId: 'SUBSCRIPT_BB_DRAWING_RESULT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未抽選' },
            { key: '1', value: '当選' },
            { key: '2', value: '落選' },
            { key: '3', value: '当選(繰上)' },
            { key: '4', value: '裁量配分' },
            { key: '5', value: '申込取消' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '当選' },
            { key: '2', value: '落選' },
            { key: '3', value: '当選(繰上)' },
            { key: '4', value: '裁量配分' },
            { key: '5', value: '申込取消' }
          ]
        }
      ]
    },
    {
      codeListId: 'FACE_TO_FACE_SUBSCRIPT_ORDER_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未注文' },
            { key: '1', value: '募集入力済' },
            { key: '2', value: '受付済' },
            { key: '3', value: '約定済' },
            { key: '4', value: '不出来' },
            { key: '5', value: '手入力' },
            { key: '6', value: '募集取消' },
            { key: '9', value: 'StarUplaod file 出力対象(占用)' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: '未注文' },
            { key: '1', value: '募集入力済' },
            { key: '2', value: '受付済' },
            { key: '3', value: '約定済' },
            { key: '4', value: '不出来' },
            { key: '5', value: '手入力' },
            { key: '6', value: '募集取消' },
            { key: '9', value: '-' }
          ]
        }
      ]
    },
    {
      codeListId: 'BB_ACCEPT_EXCLUDE_CODE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: ' ' },
            { key: '2', value: ' ' },
            { key: '3', value: ' ' },
            { key: '4', value: ' ' },
            { key: '5', value: ' ' },
            { key: '6', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUNCTION_CONTRACT_APPLY_SERVICE_ID',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: ' ' },
            { key: '02', value: ' ' },
            { key: '03', value: ' ' },
            { key: '99', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUNCTION_CONTRACT_APPLY_STATUS_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: ' ' },
            { key: '2', value: ' ' },
            { key: '3', value: ' ' },
            { key: '4', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'FACE_TO_FACE_SUBSCRIPT_ORDER_PERIOD_WARNING_DONE_APPLY_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'ORDER_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '通常/逆指値' },
            { key: '2', value: 'OCO' },
            { key: '3', value: 'IFD' },
            { key: '4', value: 'IFDOCO' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '通常注文' },
            { key: '2', value: 'OCO注文' },
            { key: '3', value: 'IFD注文' },
            { key: '4', value: 'IFDOCO注文' }
          ]
        }
      ]
    },
    {
      codeListId: 'EXECUTE_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '指値' },
            { key: '2', value: '成行' },
            { key: '3', value: '逆指値' }
          ]
        }
      ]
    },
    {
      codeListId: 'CUSTOMER_NAME_SEARCH_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: 'と等しい' },
            { key: '2', value: 'で始まる' },
            { key: '3', value: 'を含む' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '2', value: 'で始まる' },
            { key: '3', value: 'を含む' }
          ]
        }
      ]
    },
    {
      codeListId: 'FX_RATE_ADDITIONAL_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'ADJUST_AMOUNT', value: ' ' },
            { key: 'ADJUST_PERCENT', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '一般' },
            { key: '2', value: '累投' },
            { key: '3', value: '積立' },
            { key: '4', value: '累投／積立' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '一般' },
            { key: '2', value: '累投／定額' }
          ]
        }
      ]
    },
    {
      codeListId: 'ORDER_SUSPEND_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '取引停止' }
          ]
        }
      ]
    },
    {
      codeListId: 'BUY_ABLE_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '買付可能（電子閲覧済）' },
            { key: '2', value: '発送処理中（郵送請求中）' },
            { key: '3', value: '買付可能（郵送請求または即時交付済）' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '発送済' },
            { key: '2', value: '発送中' },
            { key: '3', value: '郵送請求または即時交付済' }
          ]
        }
      ]
    },
    {
      codeListId: 'MUTUAL_FUND_SELL_BUY_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '買（購入）' },
            { key: '2', value: '売（解約）' },
            { key: '3', value: '売（買取）' }
          ]
        }
      ]
    },
    {
      codeListId: 'MEDIATE_PROPRIETY',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'CURRENCY_TYPE_COMPLICATED_CONFIRM_DOC_ACCEPTANCE_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '受入不要' },
            { key: '2', value: 'WEB閲覧' },
            { key: '3', value: '受入済' },
            { key: '4', value: '確認書受入確認' },
            { key: '5', value: '-' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '受入不要' },
            { key: '2', value: 'WEB閲覧' },
            { key: '3', value: '受入済' },
            { key: '4', value: '未受入' },
            { key: '5', value: '-' }
          ]
        }
      ]
    },
    {
      codeListId: 'BUY_PROPRIETY',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '可能' },
            { key: '2', value: '不可' },
            { key: '3', value: '強制注文対象' },
            { key: '4', value: '不可' }
          ]
        }
      ]
    },
    {
      codeListId: 'PROSPECTUS_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '取引停止' },
            { key: '2', value: '発送済' },
            { key: '3', value: '発送中' }
          ]
        }
      ]
    },
    {
      codeListId: 'HOLDING_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '解約済' },
            { key: '1', value: '保有中' }
          ]
        }
      ]
    },
    {
      codeListId: 'DEPOSIT_BALANCE_LIST_SPECIFIC_DEPOSIT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '旧NISA預り' },
            { key: '2', value: '特定預り' },
            { key: '3', value: '一般預り' },
            { key: '4', value: '旧つみたてNISA預り' },
            { key: '5', value: 'NISA預り(成長投資枠)' },
            { key: '6', value: 'NISA預り(つみたて投資枠)' },
            { key: '7', value: 'NISA預り（継続管理勘定）' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '旧NISA預り' },
            { key: '2', value: '特定預り' },
            { key: '3', value: '一般預り' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '2', value: '特定預り' },
            { key: '3', value: '一般預り' }
          ]
        }
      ]
    },
    {
      codeListId: 'DEPOSIT_BALANCE_LIST_ACCOUNT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '総合口座' },
            { key: '2', value: 'ジュニアNISA口座' }
          ]
        }
      ]
    },
    {
      codeListId: 'DEPOSIT_BALANCE_LIST_COURSE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '口数' },
            { key: '2', value: '金額' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_DEPOSIT_BALANCE_LIST_DEPOSIT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '特定預り' },
            { key: '2', value: '一般預り' },
            { key: '3', value: 'NISA預り' }
          ]
        }
      ]
    },
    {
      codeListId: 'JOINT_SUBSCRIPT_CUSTOMER_PROCEDURE_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: ' ' },
            { key: '0', value: ' ' },
            { key: '1', value: ' ' },
            { key: '2', value: ' ' },
            { key: '3', value: ' ' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: ' ', value: '全て' },
            { key: '0', value: '新規申請' },
            { key: '1', value: '修正申請' },
            { key: '2', value: '承認' }
          ]
        }
      ]
    },
    {
      codeListId: 'DOMESTIC_STOCK_TRADE_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '現物買' },
            { key: '2', value: '現物売' },
            { key: '3', value: '新規買' },
            { key: '4', value: '新規売' },
            { key: '5', value: '返済買' },
            { key: '6', value: '返済売' },
            { key: '7', value: '現渡' },
            { key: '8', value: '現引' },
            { key: '9', value: 'BB申込' },
            { key: 'A', value: '募集' },
            { key: 'B', value: '立会外分売' },
            { key: 'C', value: '立会外トレード' },
            { key: 'D', value: '単元未満買' },
            { key: 'E', value: '単元未満売' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '現物買' },
            { key: '2', value: '現物売' },
            { key: '3', value: '新規買' },
            { key: '4', value: '新規売' },
            { key: '5', value: '返済買' },
            { key: '6', value: '返済売' },
            { key: '7', value: '現渡' },
            { key: '8', value: '現引' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '1', value: '現物買' },
            { key: '2', value: '現物売' },
            { key: '3', value: '信用新規買' },
            { key: '4', value: '信用新規売' },
            { key: '5', value: '信用返済買' },
            { key: '6', value: '信用返済売' },
            { key: '7', value: '現渡' },
            { key: '8', value: '現引' }
          ]
        }
      ]
    },
    {
      codeListId: 'DOMESTIC_MUTUAL_FUND_TRADE_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '購入（一般）' },
            { key: '1', value: '購入（累投）' },
            { key: '2', value: '購入（積立）' },
            { key: '3', value: '買取（一般）' },
            { key: '4', value: '買取（累投）' },
            { key: '5', value: '買取（定額）' },
            { key: '6', value: '全額買取' },
            { key: '7', value: '解約（一般）' },
            { key: '8', value: '解約（累投）' },
            { key: '9', value: '解約（定額）' },
            { key: '10', value: '全額解約' },
            { key: '11', value: '購入（カード積立）' },
            { key: '12', value: '買取（定率）' },
            { key: '13', value: '買取（期間）' },
            { key: '14', value: '解約（定率）' },
            { key: '15', value: '解約（期間）' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: '口数買' },
            { key: '1', value: '金額買' },
            { key: '2', value: '購入（積立）' },
            { key: '3', value: '口数売(買取)' },
            { key: '4', value: '金額・口数売(買取)' },
            { key: '5', value: '買取（定額）' },
            { key: '6', value: '金額売(全額買取)' },
            { key: '7', value: '口数売(解約)' },
            { key: '8', value: '金額・口数売(解約)' },
            { key: '9', value: '解約（定額）' },
            { key: '10', value: '金額売(全額解約)' },
            { key: '11', value: '購入（カード積立）' },
            { key: '12', value: '買取（定率）' },
            { key: '13', value: '買取（期間）' },
            { key: '14', value: '解約（定率）' },
            { key: '15', value: '解約（期間）' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '0', value: '口数買' },
            { key: '1', value: '金額買' },
            { key: '7', value: '口数売' },
            { key: '8', value: '金額・口数売' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_STOCK_TRADE_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '現物買' },
            { key: '1', value: '現物売' },
            { key: '2', value: '新規買' },
            { key: '3', value: '新規売' },
            { key: '4', value: '返済買' },
            { key: '5', value: '返済売' },
            { key: '6', value: '定期' },
            { key: '7', value: '預り金⇒保証金' },
            { key: '8', value: '保証金⇒預り金' },
            { key: '9', value: '保護⇒代用' },
            { key: '10', value: '代用⇒保護' },
            { key: '11', value: '店頭買' },
            { key: '12', value: '店頭売' },
            { key: '13', value: '自動振替設定' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '2', value: '信用新規買' },
            { key: '3', value: '信用新規売' },
            { key: '4', value: '信用返済買' },
            { key: '5', value: '信用返済売' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_MMF_TRADE_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '買付' },
            { key: '1', value: '売却' },
            { key: '2', value: '自動買付' },
            { key: '3', value: '積立買付' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_STOCK_ENTRUST_ORDER_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '注文中' },
            { key: '1', value: '訂正中' },
            { key: '2', value: '取消中' },
            { key: '3', value: '失効済' },
            { key: '4', value: '取消済' },
            { key: '5', value: '待機中' },
            { key: '6', value: '完了' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_STOCK_COUNTER_ORDER_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '10', value: '承認待ち' },
            { key: '30', value: '約定' },
            { key: '31', value: '取消' },
            { key: '40', value: '不出来' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_STOCK_TRADE_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未約定' },
            { key: '1', value: '一部約定' },
            { key: '2', value: '全部約定' }
          ]
        }
      ]
    },
    {
      codeListId: 'WARNING_APPLICATION_TRAD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'ワーニングなし' },
            { key: '1', value: '申請・承認済' },
            { key: '2', value: '勧誘なし' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: 'ワーニングなし' },
            { key: '1', value: '申請・承認済' },
            { key: '2', value: '勧誘なし' }
          ]
        }
      ]
    },
    {
      codeListId: 'NATIONAL_CURRENCY_UNIT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'US', value: 'USD' },
            { key: 'HK', value: 'HKD' },
            { key: 'KR', value: 'KRW' },
            { key: 'RU', value: 'RUB' },
            { key: 'VN', value: 'VND' },
            { key: 'ID', value: 'IDR' },
            { key: 'SG', value: 'SGD' },
            { key: 'TH', value: 'THB' },
            { key: 'MY', value: 'MYR' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: 'US', value: '米ドル' },
            { key: 'HK', value: '香港ドル' },
            { key: 'KR', value: '韓国ウォン' },
            { key: 'RU', value: 'ロシアルーブル' },
            { key: 'VN', value: 'ベトナムドン' },
            { key: 'ID', value: 'インドネシアルピア' },
            { key: 'SG', value: 'シンガポールドル' },
            { key: 'TH', value: 'タイバーツ' },
            { key: 'MY', value: 'マレーシアリンギット' }
          ]
        }
      ]
    },
    {
      codeListId: 'MUTUAL_FUND_DEPOSIT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '特定' },
            { key: '1', value: '一般' },
            { key: '4', value: '旧NISA' },
            { key: '5', value: 'Jr特定' },
            { key: '6', value: 'Jr一般' },
            { key: '7', value: 'JrNISA' },
            { key: '8', value: 'つみたてNISA' },
            { key: 'H', value: 'NISA（成長投資枠）' },
            { key: 'I', value: 'NISA（つみたて投資枠）' },
            { key: 'J', value: 'JrNISA継続' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: ' ', value: '特定/一般' },
            { key: '4', value: '旧NISA' },
            { key: '5', value: 'Jr特定/Jr一般' },
            { key: '7', value: 'JrNISA' },
            { key: '8', value: 'つみたてNISA' },
            { key: 'H', value: 'NISA（成長投資枠）' },
            { key: 'I', value: 'NISA（つみたて投資枠）' },
            { key: 'J', value: 'JrNISA継続' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: ' ', value: '総合口座－特定/一般' },
            { key: '0', value: '総合口座－特定' },
            { key: '1', value: '総合口座－一般' },
            { key: '4', value: '総合口座－旧NISA' },
            { key: '5', value: 'ジュニアNISA口座－特定/一般' },
            { key: '6', value: 'ジュニアNISA口座－一般' },
            { key: '7', value: 'ジュニアNISA口座－NISA' },
            { key: '8', value: '総合口座－つみたてNISA' },
            { key: 'H', value: '総合口座－NISA（成長投資枠）' },
            { key: 'I', value: '総合口座－NISA（つみたて投資枠）' },
            { key: 'J', value: 'ジュニアNISA口座－継続' }
          ]
        },
        {
          displayPattern: 4,
          codeList: [
            { key: ' ', value: '総合口座－特定/一般' },
            { key: '0', value: '総合口座－特定' },
            { key: '1', value: '総合口座－一般' },
            { key: '4', value: '総合口座－旧NISA' },
            { key: '5', value: 'ジュニアNISA口座－特定' },
            { key: '6', value: 'ジュニアNISA口座－一般' },
            { key: '7', value: 'ジュニアNISA口座－NISA' },
            { key: '8', value: '総合口座－つみたてNISA' },
            { key: 'H', value: '総合口座－NISA（成長投資枠）' },
            { key: 'I', value: '総合口座－NISA（つみたて投資枠）' },
            { key: 'J', value: 'ジュニアNISA口座－継続' }
          ]
        },
        {
          displayPattern: 5,
          codeList: [
            { key: ' ', value: '特定/一般' },
            { key: '0', value: '特定' },
            { key: '1', value: '一般' },
            { key: '4', value: '旧NISA' },
            { key: '5', value: 'Jr特定' },
            { key: '6', value: 'Jr一般' },
            { key: '7', value: 'JrNISA' },
            { key: '8', value: 'つみたてNISA' },
            { key: 'H', value: 'NISA（成長投資枠）' },
            { key: 'I', value: 'NISA（つみたて投資枠）' },
            { key: 'J', value: 'JrNISA継続' }
          ]
        },
        {
          displayPattern: 6,
          codeList: [
            { key: ' ', value: '総合口座－一般' },
            { key: '0', value: '総合口座－特定' },
            { key: '1', value: '総合口座－一般' },
            { key: '4', value: '総合口座－旧NISA' },
            { key: '5', value: 'ジュニアNISA口座－一般' },
            { key: '6', value: 'ジュニアNISA口座－一般' },
            { key: '7', value: 'ジュニアNISA口座－NISA' },
            { key: '8', value: '総合口座－つみたてNISA' },
            { key: 'H', value: '総合口座－NISA（成長投資枠）' },
            { key: 'I', value: '総合口座－NISA（つみたて投資枠）' },
            { key: 'J', value: 'ジュニアNISA口座－継続' }
          ]
        },
        {
          displayPattern: 7,
          codeList: [
            { key: ' ', value: '一般' },
            { key: '0', value: '特定' },
            { key: '1', value: '一般' },
            { key: '4', value: '旧NISA' },
            { key: '5', value: 'Jr一般' },
            { key: '6', value: 'Jr一般' },
            { key: '7', value: 'JrNISA' },
            { key: '8', value: 'つみたてNISA' },
            { key: 'H', value: 'NISA（成長投資枠）' },
            { key: 'I', value: 'NISA（つみたて投資枠）' },
            { key: 'J', value: 'JrNISA継続' }
          ]
        }
      ]
    },
    {
      codeListId: 'LATEST_TRIGGER_ZONE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: '指定なし' },
            { key: '0', value: '以上' },
            { key: '1', value: '以下' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRANSFERS_PREFERENTIAL_QUOTA_APPLICATION',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '適用する' },
            { key: '0', value: '適用しない' }
          ]
        }
      ]
    },
    {
      codeListId: 'LEVERAGED_INVESTMENT_TRUST',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '該当する（勧誘開始基準、確認書受入）' },
            { key: '2', value: '該当しない' }
          ]
        }
      ]
    },
    {
      codeListId: 'SOLICITING_TRANSFERS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '勧誘あり（記録表、確認書受入）' },
            { key: '2', value: '勧誘なし' }
          ]
        }
      ]
    },
    {
      codeListId: 'SAME_CURRENCY_SAME_COUNTRY_TRANSFERS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '該当する（同一・他商品間含む）' },
            { key: '2', value: '該当しない' }
          ]
        }
      ]
    },
    {
      codeListId: 'INTERCOMPANY_MUTUAL_FUND_TRANSFER_SOLICITATION',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '該当する（乗換に関する重要事項の説明済）' },
            { key: '2', value: '該当しない' }
          ]
        }
      ]
    },
    {
      codeListId: 'SHORT_TERM_SALE_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '該当する（90日以内）' },
            { key: '2', value: '該当しない' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '該当する' },
            { key: '2', value: '該当しない' }
          ]
        }
      ]
    },
    {
      codeListId: 'PRE_REDEMPTION_SELL_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '該当する（2ヶ月以内）' },
            { key: '2', value: '該当しない' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '該当する' },
            { key: '2', value: '該当しない' }
          ]
        }
      ]
    },
    {
      codeListId: 'USE_OF_POINTS_SELECT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '使用しない' },
            { key: '1', value: '一部使用する' },
            { key: '2', value: '全部使用する' }
          ]
        }
      ]
    },
    {
      codeListId: 'ALERT_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'PROSPECTUS_ISSUE_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '店頭' },
            { key: '1', value: '訪問' },
            { key: '2', value: '郵送' },
            { key: '3', value: '既に交付済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: '店頭' },
            { key: '1', value: '訪問' },
            { key: '2', value: '郵送' },
            { key: '3', value: '電子交付' }
          ]
        }
      ]
    },
    {
      codeListId: 'MUTUAL_FUND_SELL_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '解約' },
            { key: '2', value: '買取請求' }
          ]
        }
      ]
    },
    {
      codeListId: 'DESIGNATED_FOR_SALE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '金額指定' },
            { key: '2', value: '口数指定' },
            { key: '3', value: '全額売却' }
          ]
        }
      ]
    },
    {
      codeListId: 'INFO_REGISTER_VIEWER_AUTHORITY_SELECT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '3', value: '仲介業者(内部管理責任者)' },
            { key: '4', value: '仲介業者(営業責任者)' },
            { key: '5', value: '仲介業者(外務員)' },
            { key: '6', value: '仲介業者支店(内部管理責任者)' },
            { key: '7', value: '仲介業者支店(営業責任者)' },
            { key: '8', value: '仲介業者支店(外務員)' },
            { key: '9', value: '担当' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_MARKET_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'US_NYSE', value: 'NYSE' },
            { key: 'US_ARCA', value: 'NYSEArca' },
            { key: 'US_AMEX', value: 'NYSE American' },
            { key: 'US_NASDAQ', value: 'NASDAQ' },
            { key: 'HK_MAIN', value: 'MAIN' },
            { key: 'HK_GEM', value: 'GEM' },
            { key: 'KR_KSE', value: 'KOSPI' },
            { key: 'KR_KOSDAQ', value: 'KOSDAQ' },
            { key: 'RU_MICEX', value: 'MICEX' },
            { key: 'VN_HOSE', value: 'HOSE' },
            { key: 'VN_HNX', value: 'HNX' },
            { key: 'ID_IDX', value: 'IDX' },
            { key: 'SG_MAIN', value: 'SGX' },
            { key: 'TH_SET', value: 'SET' },
            { key: 'MY_MAIN', value: 'MYX' },
            { key: 'US_CBOE', value: 'CBOE' },
            { key: 'US_OTC', value: 'OTC Market' }
          ]
        }
      ]
    },
    {
      codeListId: 'EXCEEDING_ORDER_AMOUNT_LIMIT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'COLLATERAL_ELIGIBLE_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '不適格' },
            { key: '1', value: '適格' }
          ]
        }
      ]
    },
    {
      codeListId: 'RECEIPT_DELIVERY_TRADE_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '現渡' },
            { key: '1', value: '現引' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_STOCK_INVITATION_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '勧誘あり' },
            { key: '0', value: '勧誘なし' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_STOCK_ORDER_METHOD_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '3', value: '電話他' },
            { key: '0', value: '店頭' },
            { key: '1', value: '訪問' }
          ]
        }
      ]
    },
    {
      codeListId: 'BRAND_SEARCH_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: 'と等しい' },
            { key: '2', value: 'で始まる' },
            { key: '3', value: 'を含む' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '2', value: 'で始まる' },
            { key: '3', value: 'を含む' }
          ]
        }
      ]
    },
    {
      codeListId: 'CANCEL_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '有効' },
            { key: '1', value: '取消' },
            { key: '2', value: '-' },
            { key: '3', value: '-' }
          ]
        }
      ]
    },
    {
      codeListId: 'ORDER_LIST_SECURITY_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '国内株式（現物・信用・現引・現渡）' },
            { key: '1', value: '国内投資信託' },
            { key: '2', value: '募集注文' },
            { key: '3', value: '外国株式（委託注文）' },
            { key: '4', value: '外国株式（店頭注文）' },
            { key: '5', value: '外貨建MMF' }
          ]
        }
      ]
    },
    {
      codeListId: 'NOTICE_INFO_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'IMPORTANT_NOTIFICATION_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'INSIDER_TRADE_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: 'インサイダー情報に基づく売買ではない' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRADE_COURSE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '全て' },
            { key: 'A', value: 'インターネットコース' },
            { key: 'B', value: 'コールセンターコース' },
            { key: 'C', value: 'ダイレクトコース' },
            { key: 'D', value: 'IFAコース' },
            { key: 'E', value: 'IFAコース（プランA）' },
            { key: 'F', value: 'プランAコース' },
            { key: 'G', value: 'プランBコース' },
            { key: 'H', value: '対面取引コース' },
            { key: 'I', value: 'IFAコース（プランA-2）' },
            { key: 'J', value: 'インターネットコース（プランC）' },
            { key: 'K', value: 'WMコース' },
            { key: 'L', value: 'レベルフィーコース' }

          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: 'A', value: 'インターネットコース' },
            { key: 'B', value: 'コールセンターコース' },
            { key: 'C', value: 'ダイレクトコース' },
            { key: 'D', value: 'IFAコース' },
            { key: 'E', value: 'IFAコース（プランA）' },
            { key: 'F', value: 'プランAコース' },
            { key: 'G', value: 'プランBコース' },
            { key: 'H', value: '対面取引コース' },
            { key: 'I', value: 'IFAコース（プランA-2）' },
            { key: 'J', value: 'インターネットコース（プランC）' },
            { key: 'K', value: 'WMコース' },
            { key: 'L', value: 'レベルフィーコース' }
          ]
        }
      ]
    },
    {
      codeListId: 'REPAY_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '一括指定' },
            { key: '1', value: '個別指定' },
            { key: '2', value: '個別指定（単独）' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: '一括指定' },
            { key: '1', value: '個別指定' },
            { key: '2', value: '個別指定' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '0', value: '一括指定' },
            { key: '1', value: '個別指定' }
          ]
        }
      ]
    },
    {
      codeListId: 'REPAY_ORDER',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '61', value: '評価益順' },
            { key: '51', value: '評価損順' },
            { key: '16', value: '建日古い順' },
            { key: '26', value: '建日新しい順' }
          ]
        }
      ]
    },
    {
      codeListId: 'SHORT_SELLING_REGULATION_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '説明済$CRLF【トークフロー】新規売り注文が空売り規制に抵触した場合、注文は取引所にて「失効」されます。よろしいでしょうか。' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '説明済' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_STOCK_COUNTER_SEARCH_CONDITION_SELECT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'ティッカー' },
            { key: '1', value: '名称' }
          ]
        }
      ]
    },
    {
      codeListId: 'COMPLIANCE_REPORT_STATE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '仮登録' },
            { key: '1', value: '本番' }
          ]
        }
      ]
    },
    {
      codeListId: 'COMPLIANT_STATUS_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: ' ' },
            { key: '0', value: '未対応' },
            { key: '2', value: '外務員対応済' },
            { key: '1', value: '承認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'MEANS_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: ' ' },
            { key: '1', value: '口頭説明' },
            { key: '2', value: 'レポート郵送' },
            { key: '9', value: 'その他' }
          ]
        }
      ]
    },
    {
      codeListId: 'DECLINE_RATE_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '前日比5%下落' },
            { key: '1', value: '1か月10%下落' }
          ]
        }
      ]
    },
    {
      codeListId: 'CURRENT_TICK',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: '↑' },
            { key: '2', value: '↓' }
          ]
        }
      ]
    },
    {
      codeListId: 'CURRENT_PRICE_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'C', value: 'C' },
            { key: '*', value: '*' },
            { key: '#', value: '#' },
            { key: 'H', value: 'H' },
            { key: 'L', value: 'L' },
            { key: ' ', value: ' ' },
            { key: '$NULL', value: ' ' },
            { key: '-', value: ' ' },
            { key: '!', value: '!' },
            { key: 'X', value: 'X' },
            { key: 'A', value: 'A' },
            { key: 'B', value: 'B' },
            { key: 'E', value: 'E' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: 'C', value: 'C' },
            { key: '*', value: '*' },
            { key: '#', value: '#' },
            { key: 'H', value: ' ' },
            { key: 'L', value: ' ' },
            { key: ' ', value: ' ' },
            { key: '$NULL', value: ' ' },
            { key: '-', value: ' ' },
            { key: '!', value: '!' },
            { key: 'X', value: 'X' },
            { key: 'A', value: 'A' },
            { key: 'B', value: 'B' },
            { key: 'E', value: 'E' }
          ]
        }
      ]
    },
    {
      codeListId: 'INSIDER_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: 'インサイダー取引及び契約締結前交付書面の確認' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '1', value: '契約締結前交付書面の確認' }
          ]
        }
      ]
    },
    {
      codeListId: 'DEPOSIT_TYPE_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'COLLATERAL_REGULATIONS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: ' ' },
            { key: '1', value: '※1' },
            { key: '2', value: '※2' },
            { key: '3', value: '※3' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '※1 増担保規制' },
            { key: '2', value: '※2 レバレッジ型ETF等の保証金適用' },
            { key: '3', value: '※3 増担保規制、レバレッジ型ETF等の保証金適用' }
          ]
        }
      ]
    },
    {
      codeListId: 'PROFESSION',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'A', value: '開業医' },
            { key: 'B', value: '勤務医' },
            { key: 'C', value: '弁護士会計士税理士' },
            { key: 'D', value: '自由業' },
            { key: 'E', value: '公務員(管理職)' },
            { key: 'F', value: '公務員(職員)' },
            { key: 'G', value: '会社役員(上場・店頭公開企業)' },
            { key: 'H', value: '会社経営者(未公開企業)' },
            { key: 'I', value: '会社役員(未公開企業)' },
            { key: 'J', value: '会社員' },
            { key: 'K', value: '団体組合諸法人役員' },
            { key: 'L', value: '団体組合諸法人職員' },
            { key: 'M', value: '教職員' },
            { key: 'N', value: '商店サービス自営業' },
            { key: 'O', value: '商店サービス従業員' },
            { key: 'P', value: '農林・漁業' },
            { key: 'Q', value: '無職' },
            { key: 'R', value: '不明' },
            { key: 'S', value: '無職／主婦' },
            { key: 'T', value: '無職／学生' },
            { key: 'U', value: '無職／定年退職' },
            { key: '10', value: '日本銀行' },
            { key: '11', value: '都市銀行' },
            { key: '12', value: '信託銀行' },
            { key: '13', value: '地方銀行' },
            { key: '14', value: '相互銀行' },
            { key: '15', value: '農林中金' },
            { key: '16', value: '商工中金' },
            { key: '17', value: '長期信用銀行' },
            { key: '18', value: '外国金融機関' },
            { key: '20', value: '生命保険' },
            { key: '21', value: '損害保険' },
            { key: '22', value: '外国保険会社' },
            { key: '30', value: '県信連' },
            { key: '31', value: '共済連' },
            { key: '32', value: '農協' },
            { key: '33', value: '漁協・林協・その他' },
            { key: '40', value: '信用金庫(含全信連)' },
            { key: '41', value: '信用組合(含全組連)' },
            { key: '42', value: '労働金庫(含連合会)' },
            { key: '43', value: 'その他金融機関' },
            { key: '50', value: '官公庁共済組合' },
            { key: '51', value: 'その他共済組合' },
            { key: '60', value: '上場会社' },
            { key: '61', value: '店頭上場会社' },
            { key: '62', value: '非上場会社' },
            { key: '63', value: '外国事業会社' },
            { key: '70', value: '学校法人' },
            { key: '71', value: '宗教法人' },
            { key: '72', value: '医療法人' },
            { key: '73', value: '健康保険組合' },
            { key: '74', value: '財団法人' },
            { key: '75', value: 'その他諸法人' },
            { key: '80', value: '外国ファンド' },
            { key: '81', value: '外国年金基金財団等' },
            { key: '82', value: 'その他外国法人' },
            { key: '86', value: '公社公団等政府系機関' },
            { key: '87', value: '地方公共団体' },
            { key: '88', value: '外国政府系機関' },
            { key: '90', value: '国内証券会社' },
            { key: '91', value: '外国証券会社' },
            { key: '95', value: '持株会' },
            { key: '97', value: '投信委託会社' },
            { key: '98', value: '不明' }
          ]
        }
      ]
    },
    {
      codeListId: 'INVESTMENT_POLICY_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '  ', value: '-' },
            { key: '01', value: '利回り・安定重視' },
            { key: '02', value: '利回り・値上り益重視' },
            { key: '03', value: '値上り益重視' },
            { key: '04', value: '積極的値上り益重視' },
            { key: '05', value: '安定投資重視' },
            { key: '06', value: 'バランス' },
            { key: '07', value: '値上り益重視' },
            { key: '09', value: 'その他' },
            { key: '1 ', value: '政策投資' },
            { key: '2 ', value: '資金運用' },
            { key: '3 ', value: '元本重視' },
            { key: '4 ', value: '利子・配当重視' },
            { key: '5 ', value: '利子・配当と値上がりのバランスを重視' },
            { key: '6 ', value: '値上り益重視' },
            { key: '7 ', value: '積極的値上り追求' },
            { key: '9 ', value: 'その他' }
          ]
        }
      ]
    },
    {
      codeListId: 'FINANCIAL_ASSETS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '  ', value: '-' },
            { key: '01', value: '300万円未満' },
            { key: '02', value: '300～500万円' },
            { key: '03', value: '500～1000万円' },
            { key: '04', value: '1000～2000万円' },
            { key: '05', value: '2000～3000万円' },
            { key: '06', value: '3000～5000万円' },
            { key: '07', value: '5000万円～1億円' },
            { key: '08', value: '1億～5億円' },
            { key: '99', value: '5億円以上' }
          ]
        }
      ]
    },
    {
      codeListId: 'FX_TRADE_SEARCH_ORDER_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'すべて' },
            { key: '1', value: '約定済' },
            { key: '2', value: '注文中' },
            { key: '3', value: '取消済' }
          ]
        }
      ]
    },
    {
      codeListId: 'FX_TRADE_SEARCH_TRADE_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'すべて' },
            { key: '1', value: '買付' },
            { key: '2', value: '売却' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '買付' },
            { key: '2', value: '売却' }
          ]
        }
      ]
    },
    {
      codeListId: 'FX_TRADE_SEARCH_RESULT_TRADE_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '買付' },
            { key: '1', value: '売却' },
            { key: '2', value: '振替' },
            { key: '3', value: '債券購入' },
            { key: '4', value: '利金償還金' },
            { key: '5', value: '外貨建MMF購入' },
            { key: '6', value: '外貨建MMF売却' },
            { key: '7', value: '譲渡益税充当' }
          ]
        }
      ]
    },
    {
      codeListId: 'COMPLIANCE_START_CRITERIA_CONFIRM_CHECK_BOX_WORDING',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'SHORT_TERM_SELL_CONFIRM_ALERT_CHECKBOX_WORD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '短期売却について「申請・承認済」であることを確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'PRE_REDEMPTION_SELL_CONFIRM_ALERT_CHECKBOX_WORD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '償還前売却について「償還乗換優遇制度」を説明済$CRLF償還日1ヶ月前からの売却勧誘及び乗換勧誘取引は禁止である旨を確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'PROSPECTUS_SUPPLEMENTARY_DOC_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '目論見書補完書面の確認' }
          ]
        }
      ]
    },
    {
      codeListId: 'WINDOW_SPACE_FUND_PRECAUTIONS_CONSENT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '窓空きファンドの注意事項に同意' }
          ]
        }
      ]
    },
    {
      codeListId: 'COST_EXPLAINED',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '販売手数料の料率（金額）、販売手数料の負担率逓減、信託報酬、信託財産留保額、解約手数料、その他費用について説明済' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '0', value: '未説明' },
            { key: '1', value: '説明済み' }
          ]
        }
      ]
    },
    {
      codeListId: 'MULTIPLE_TRADE_CLEARLY_COMM_STATED',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '所属金融商品取引業者が複数ある場合、それぞれの取引業者での手数料等明示済' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '0', value: '未説明' },
            { key: '1', value: '説明済み' }
          ]
        }
      ]
    },
    {
      codeListId: 'SELL_BUY_UNIT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: ' ' },
            { key: '2', value: ' ' },
            { key: '3', value: ' ' }
          ]
        }
      ]
    },
    {
      codeListId: 'BRAND_CODE_WITH_1',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: ' ' },
            { key: '1', value: '１付き' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '○' }
          ]
        }
      ]
    },
    {
      codeListId: 'CIGARETTE_DISCLOSE_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: ' ' },
            { key: '1', value: 'たばこ' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '○' }
          ]
        }
      ]
    },
    {
      codeListId: 'ONLY_ELECTRONIC_DOC_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: ' ' },
            { key: '1', value: '電子交付' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '○' }
          ]
        }
      ]
    },
    {
      codeListId: 'CHARGE_CUSTOMER_COUNTING_UNIT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '営業員毎' },
            { key: '1', value: '顧客毎' }
          ]
        }
      ]
    },
    {
      codeListId: 'SOR_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: 'SOR対象銘柄の説明済' }
          ]
        }
      ]
    },
    {
      codeListId: 'DAILY_MONTHLY_COUNTING_UNIT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '日次' },
            { key: '1', value: '月次' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: '日次' },
            { key: '1', value: '月次累計' }
          ]
        }
      ]
    },
    {
      codeListId: 'BROKER_CHARGE_BRANCH_COUNTING_UNIT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '仲介業者' },
            { key: '1', value: '営業員' },
            { key: '2', value: '支店' }
          ]
        }
      ]
    },
    {
      codeListId: 'AGGREGATOR_HANDLER_COUNTING_UNIT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '集計' },
            { key: '1', value: '扱者' }
          ]
        }
      ]
    },
    {
      codeListId: 'FEE_COMM_DISPLAY_CONTENT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '手数料' },
            { key: '1', value: '報酬' }
          ]
        }
      ]
    },
    {
      codeListId: 'EXCEEDING_TRADE_AMOUNT_LIMIT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'IMMEDIATE_STOP_ORDER',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'ORDER_FOR_NEXT_BUSINESS_DAY',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'FX_QUANTITY_DESIGNATION_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '外貨で指定' },
            { key: '2', value: '円で指定' }
          ]
        }
      ]
    },
    {
      codeListId: 'DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '明細' },
            { key: '1', value: '顧客・商品分類・通貨毎' },
            { key: '2', value: '通貨毎' }
          ]
        }
      ]
    },
    {
      codeListId: 'STRUCTURED_BOND_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '  ', value: ' ' },
            { key: '1 ', value: '仕組債' }
          ]
        }
      ]
    },
    {
      codeListId: 'ADDITIONAL_COLLATERAL_REGULATIONS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'NOTIFICATION_VIEW_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: '未' },
            { key: '0', value: '未' },
            { key: '1', value: '済' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_SECURITY_INFO_ISSUE_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '店頭' },
            { key: '1', value: '訪問' },
            { key: '2', value: '郵送' }
          ]
        }
      ]
    },
    {
      codeListId: 'SECURITY_CASH_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '国内株式現物' },
            { key: '15', value: '外国株式現物' },
            { key: '06', value: '国内投信' },
            { key: '08', value: '外国投信' },
            { key: '09', value: '外国投信(他)' },
            { key: '16', value: '外貨建MMF' },
            { key: '12', value: '国内債券' },
            { key: '25', value: '国内債券(国債)' },
            { key: '04', value: '国内CB' },
            { key: '13', value: '外国債券(円建)' },
            { key: '14', value: '外国債券(外貨建)' },
            { key: '33', value: 'ST' },
            { key: '50', value: 'MRF' },
            { key: '100', value: '金銭(スィープ専用銀行口座)' },
            { key: '99', value: '金銭(円貨)' },
            { key: '98', value: '金銭(外貨)' }
          ]
        }
      ]
    },
    {
      codeListId: 'MARGIN_FUTURES_OPTIONS_SECURITY_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '02', value: '国内株式信用' },
            { key: '29', value: '米株信用' },
            { key: '10', value: '先物' },
            { key: '11', value: 'オプション' }
          ]
        }
      ]
    },
    {
      codeListId: 'MARGIN_SHORT_POSITION_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '日計り' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '売' }
          ]
        }
      ]
    },
    {
      codeListId: 'MARGIN_LONG_POSITION_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '日計り' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '買' }
          ]
        }
      ]
    },
    {
      codeListId: 'PREMIUM_SHORT_SELLING_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: 'HYPER' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '売' }
          ]
        }
      ]
    },
    {
      codeListId: 'SEVERAL_SELL_QUOTE_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'O', value: '前' },
            { key: 'G', value: ' ' },
            { key: 'S', value: '特' },
            { key: 'A', value: '連' },
            { key: 'X', value: ' ' },
            { key: 'B', value: ' ' },
            { key: 'W', value: 'W' },
            { key: ' ', value: ' ' },
            { key: 'T', value: ' ' },
            { key: 'E', value: 'S' }
          ]
        }
      ]
    },
    {
      codeListId: 'SEVERAL_BUY_QUOTE_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'O', value: '前' },
            { key: 'G', value: ' ' },
            { key: 'S', value: '特' },
            { key: 'A', value: '連' },
            { key: 'X', value: ' ' },
            { key: 'B', value: ' ' },
            { key: 'W', value: 'W' },
            { key: ' ', value: ' ' },
            { key: 'T', value: ' ' },
            { key: 'E', value: 'S' }
          ]
        }
      ]
    },
    {
      codeListId: 'MARGIN_LOAN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '貸借' },
            { key: '2', value: '信用' },
            { key: '$NULL', value: '-' },
            { key: ' ', value: '-' }
          ]
        }
      ]
    },
    {
      codeListId: 'MEDIATE_ABLE_TRADE_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'なし' },
            { key: '1', value: 'あり' }
          ]
        }
      ]
    },
    {
      codeListId: 'TARGET_CUSTOMER_REFERENCE_AUTHORITY_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '権限なし' },
            { key: '1', value: '権限あり' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRADE_SUSPEND_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: '初期値' },
            { key: '0', value: '取引停止口座ではない' },
            { key: '1', value: '取引停止口座' }
          ]
        }
      ]
    },
    {
      codeListId: 'ADDITIONAL_COLLATERAL',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '増担' }
          ]
        }
      ]
    },
    {
      codeListId: 'DEPOSIT_TRANSFER_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '振替元：米ドル$CRLF振替先：米ドル保証金' },
            { key: '0', value: '振替元：米ドル保証金$CRLF振替先：米ドル' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '米ドル　→　米ドル保証金' },
            { key: '0', value: '米ドル保証金　→　米ドル' }
          ]
        }
      ]
    },
    {
      codeListId: 'COLLATERAL_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'すべて' },
            { key: '1', value: '国内株式のみ' },
            { key: '2', value: '投資信託のみ' }
          ]
        }
      ]
    },
    {
      codeListId: 'DOCUMENT_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '  ', value: ' ' },
            { key: '01', value: '受益証券　取引日記帳' },
            { key: '02', value: '公社債　取引日記帳' },
            { key: '03', value: '顧客勘定元帳' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_SERVICE_HOURS_CHECK_TARGET',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: '委託' },
            { key: '1', value: '振替' }
          ]
        }
      ]
    },
    {
      codeListId: 'STORAGE_SENDING_MEDIUM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: ' ' },
            { key: '1', value: '紙' },
            { key: '2', value: 'データ' },
            { key: '3', value: '紙とデータ' },
            { key: '4', value: '保管なし/送付しない' }
          ]
        }
      ]
    },
    {
      codeListId: 'DEPOSIT_DESTINATIONS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: ' ' },
            { key: '1', value: '-' },
            { key: '2', value: 'なし' },
            { key: '3', value: 'あり' }
          ]
        }
      ]
    },
    {
      codeListId: 'DESTINATION',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: ' ' },
            { key: '1', value: '-' },
            { key: '2', value: '顧客' },
            { key: '3', value: '顧客以外' },
            { key: '4', value: '提供先なし' }
          ]
        }
      ]
    },
    {
      codeListId: 'STORAGE_SPACE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: ' ' },
            { key: '1', value: '-' },
            { key: '2', value: '施錠キャビネ' },
            { key: '3', value: 'PC共有フォルダ' },
            { key: '4', value: '施錠キャビネとPC共有フォルダ' },
            { key: '5', value: '保管なし' }
          ]
        }
      ]
    },
    {
      codeListId: 'DISPOSE_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: ' ' },
            { key: '1', value: '-' },
            { key: '2', value: 'シュレッダー' },
            { key: '3', value: 'データ削除' },
            { key: '4', value: 'シュレッダーとデータ削除' }
          ]
        }
      ]
    },
    {
      codeListId: 'NOT_DISPOSE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: ' ' },
            { key: '1', value: '-' },
            { key: '2', value: '○' }
          ]
        }
      ]
    },
    {
      codeListId: 'SELF_INSPECT_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: '-' },
            { key: '0', value: 'いいえ' },
            { key: '1', value: 'はい' }
          ]
        }
      ]
    },
    {
      codeListId: 'LIST_ORDER_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '通常注文' },
            { key: '1', value: '逆指値注文' },
            { key: '2', value: 'OCO注文' },
            { key: '3', value: 'IFD1注文（通常）' },
            { key: '4', value: 'IFD1注文（逆指値）' },
            { key: '5', value: 'IFD2注文（通常）' },
            { key: '6', value: 'IFD2注文（逆指値）' },
            { key: '7', value: 'IFD2注文（OCO）' }
          ]
        }
      ]
    },
    {
      codeListId: 'LIST_ORDER_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '-' },
            { key: '1', value: '訂正' },
            { key: '2', value: '取消' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_TRADE_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '買付' },
            { key: '2', value: '売却' }
          ]
        }
      ]
    },
    {
      codeListId: 'JPY_AMOUNT_TRADE_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '出金' }
          ]
        }
      ]
    },
    {
      codeListId: 'POWER_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: '余力（株数／金額）確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'DEPOSIT_WITHDRAW_DETAIL_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '全て' },
            { key: '1', value: '入金' },
            { key: '2', value: '出金' },
            { key: '3', value: '振替入金' },
            { key: '4', value: '振替出金' }
          ]
        }
      ]
    },
    {
      codeListId: 'DELIVER_IN_OUT_SECURITY_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'K0', value: '国内株式' },
            { key: 'S0', value: '国内債券' },
            { key: 'T0', value: '国内投信(一般型)' },
            { key: 'K1', value: '外国株式' },
            { key: 'S1', value: '外国債券' },
            { key: 'T1', value: '外国投信' },
            { key: 'Y0', value: '国内投信(金融型)' }
          ]
        }
      ]
    },
    {
      codeListId: 'DELIVER_IN_OUT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '入庫' },
            { key: '0', value: '出庫' },
            { key: '2', value: '不明' }
          ]
        }
      ]
    },
    {
      codeListId: 'IPO_PO_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: 'IPO' },
            { key: '2', value: 'PO' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRADE_HISTORY_SECURITY_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '国内株式現物' },
            { key: '02', value: '国内株式信用' },
            { key: '03', value: '国内株式IPO・PO' },
            { key: '24', value: '国内株式(単元未満)' },
            { key: '15', value: '外国株式現物' },
            { key: '29', value: '米株信用' },
            { key: '27', value: '現株ポイント' },
            { key: '05', value: '国内投信' },
            { key: '07', value: '外国投信' },
            { key: '21', value: '外貨建MMF' },
            { key: '12', value: '国内債券' },
            { key: '25', value: '国内債券(国債)' },
            { key: '04', value: '国内CB' },
            { key: '13', value: '外国債券(円建)' },
            { key: '14', value: '外国債券(外貨建)' },
            { key: '33', value: 'ST' },
            { key: '10', value: '先物' },
            { key: '11', value: 'オプション' }
          ]
        }
      ]
    },
    {
      codeListId: 'DISCRETION_SELECT_REASON',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '取引拡大のため' },
            { key: '2', value: '取引拡大、資産拡大のため' },
            { key: '3', value: '新規開拓、資産拡大のため' }
          ]
        }
      ]
    },
    {
      codeListId: 'DISCRETION_ALLOCATE_TIMES_OVER_FIVE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'FINANCIAL_ASSET_LESS_THAN_THIRTY_MILLION_YEN_DISCRETION_APPLY',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'MSG_DISPLAY_TARGET_TRADE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '為替取引' },
            { key: '2', value: '国内株式取引' },
            { key: '3', value: '国内信用取引' },
            { key: '4', value: '国内投信取引' },
            { key: '5', value: '米株信用取引' },
            { key: '6', value: '外国株式取引' },
            { key: '7', value: 'BB申込' },
            { key: '8', value: 'IPO/PO取引' },
            { key: '9', value: '出金' },
            { key: '10', value: '投信積立取引' },
            { key: '6A', value: '外国株式現物取引（委託）' },
            { key: '6B', value: '米株店頭取引' }
          ]
        }
      ]
    },
    {
      codeListId: 'PUBLIC_PRIVATE_OFFER_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '公募債' },
            { key: '3', value: '私募債' }
          ]
        }
      ]
    },
    {
      codeListId: 'KNOCK_IN_EVENT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'なし' },
            { key: '1', value: 'あり' }
          ]
        }
      ]
    },
    {
      codeListId: 'EARLY_REDEMPTION_EVENT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'なし' },
            { key: '1', value: 'あり' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRADE_FLOOR_JUDGMEN_DAYS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '5', value: '取引所5営業日前' },
            { key: '10', value: '取引所10営業日前' }
          ]
        }
      ]
    },
    {
      codeListId: 'COUPON_KIND',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '固定クーポン' },
            { key: '2', value: 'デジタルクーポン' },
            { key: '3', value: '固定+デジタルクーポン' }
          ]
        }
      ]
    },
    {
      codeListId: 'REFERENCE_VALUE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '終値' },
            { key: '2', value: '始値' },
            { key: '3', value: '高値' },
            { key: '4', value: '安値' }
          ]
        }
      ]
    },
    {
      codeListId: 'USE_RATE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: 'なし' },
            { key: '2', value: 'bidレート' },
            { key: '3', value: 'midレート' },
            { key: '4', value: 'askレート' }
          ]
        }
      ]
    },
    {
      codeListId: 'EARLY_REDEMPTION_JUDGMENT_STEP_DOWN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'なし' },
            { key: '1', value: 'あり' }
          ]
        }
      ]
    },
    {
      codeListId: 'KNOCK_IN_EXEMPT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'なし' },
            { key: '1', value: 'あり' }
          ]
        }
      ]
    },
    {
      codeListId: 'EARLY_REDEMPTION_MEMORY',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'なし' },
            { key: '1', value: 'あり' }
          ]
        }
      ]
    },
    {
      codeListId: 'BRAND_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '個別株' },
            { key: '2', value: '指数（国内株）' },
            { key: '3', value: '指数（アジア系）' },
            { key: '4', value: '指数（欧米系）' },
            { key: '5', value: '米国株' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRADE_AMOUNT_ALERT_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'OVERSEAS_ETF_ALERT_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: ' ' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'FX_ACCOUNT_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '総合口座' },
            { key: '2', value: 'ジュニアNISA口座' }
          ]
        }
      ]
    },
    {
      codeListId: 'FX_DEPOSIT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '保護預り' },
            { key: '2', value: '代用預り' },
            { key: '3', value: '預り金' },
            { key: '4', value: '信用保証金' }
          ]
        }
      ]
    },
    {
      codeListId: 'FX_SERVICE_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: 'IFA' },
            { key: '2', value: 'リテール' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRADE_RESTRICTIONS_DISTINCTION_MARKET',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '7', value: 'PTS' },
            { key: '99', value: 'その他' }
          ]
        }
      ]
    },
    {
      codeListId: 'ONE_DAY_MARGIN_FLAG',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '一日信用でない' },
            { key: '1', value: '一日信用' }
          ]
        }
      ]
    },
    {
      codeListId: 'DEPOSIT_BALANCE_LIST_DISTRIBUTION_RECEIVE_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '口数' },
            { key: '2', value: '金額' }
          ]
        }
      ]
    },
    {
      codeListId: 'DEPOSIT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'PROTECTION_KEEPING', value: '保護預り' },
            { key: 'COLLATERAL_SECURITIES', value: '代用預り' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: 'PROTECTION_KEEPING', value: '設定しない' },
            { key: 'COLLATERAL_SECURITIES', value: '設定する' }
          ]
        }
      ]
    },
    {
      codeListId: 'COMMISSION_APPLICATION_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: 'WEB' },
            { key: '2', value: '対面' },
            { key: '3', value: 'WEB' },
            { key: '4', value: '大口' }
          ]
        }
      ]
    },
    {
      codeListId: 'POINT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: 'ポイント利用なし' },
            { key: '0', value: 'ポイント利用なし' },
            { key: '1', value: 'Vポイント(旧T)' },
            { key: '2', value: 'Pontaポイント' },
            { key: '3', value: '旧Vポイント' },
            { key: '4', value: 'dポイント' },
            { key: '5', value: 'TOKYU POINT' },
            { key: '6', value: 'タカシマヤポイント' },
            { key: '7', value: 'JALマイル' },
            { key: '8', value: 'Uポイント/majicaポイント' },
            { key: '9', value: 'PayPayポイント' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '$NULL', value: 'ポイント' },
            { key: '0', value: 'ポイント' },
            { key: '1', value: 'ポイント' },
            { key: '2', value: 'ポイント' },
            { key: '3', value: 'ポイント' },
            { key: '4', value: 'ポイント' },
            { key: '5', value: 'ポイント' },
            { key: '6', value: 'ポイント' },
            { key: '7', value: 'マイル' },
            { key: '8', value: 'ポイント' },
            { key: '9', value: 'ポイント' }
          ]
        }
      ]
    },
    {
      codeListId: 'MARGIN_BUYING_POWER_SHORTFALL_SECURITIES',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '米国株式(保護預り)' }
          ]
        }
      ]
    },
    {
      codeListId: 'MARGIN_BUYING_POWER_SHORTFALL_CASH',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '米ドル預り金' }
          ]
        }
      ]
    },
    {
      codeListId: 'MARGIN_SHORTFALL_SECURITIES',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '米国株式(保護預り)' }
          ]
        }
      ]
    },
    {
      codeListId: 'MARGIN_SHORTFALL_CASH',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '米ドル預り金' }
          ]
        }
      ]
    },
    {
      codeListId: 'ASSET_DEPOSIT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '一般預り' },
            { key: '2', value: '特定預り' },
            { key: '4', value: '旧NISA預り' },
            { key: '5', value: 'ジュニア一般預り' },
            { key: '6', value: 'ジュニア特定預り' },
            { key: '8', value: 'ジュニアNISA預り' },
            { key: '9', value: '旧つみたてNISA預り' },
            { key: 'H', value: 'NISA預り（成長投資枠）' },
            { key: 'I', value: 'NISA預り（つみたて）' },
            { key: 'J', value: 'ジュニア継続NISA預り' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_REPAY_ORDER',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '評価益順' },
            { key: '1', value: '評価損順' },
            { key: '2', value: '建日古い順' },
            { key: '3', value: '建日新しい順' }
          ]
        }
      ]
    },
    {
      codeListId: 'METHOD_OTHER_CONTENTS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: ' ' },
            { key: '1', value: '顧客意向により連絡せず' },
            { key: '2', value: '非勧誘顧客' },
            { key: '3', value: 'インターネットコースへ変更済' },
            { key: '4', value: '他の仲介業者へ変更済' },
            { key: '5', value: '死亡' },
            { key: '6', value: '連絡先・転居先共に不明' },
            { key: '7', value: '入院中・施設入所' },
            { key: '8', value: '預り移管済' },
            { key: '99', value: 'その他' }
          ]
        }
      ]
    },
    {
      codeListId: 'ASSET_CURRENCY_CODE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'USD', value: 'ドル' },
            { key: 'HKD', value: '香港ドル' },
            { key: 'EUR', value: 'ユーロ' },
            { key: 'AUD', value: 'オーストラリアドル' },
            { key: 'NZD', value: 'ニュージーランドドル' },
            { key: 'CAD', value: 'カナダドル' },
            { key: 'ZAR', value: 'ランド' },
            { key: 'MXN', value: 'メキシコペソ' },
            { key: 'TRY', value: 'トルコリラ' },
            { key: 'SGD', value: 'シンガポールドル' },
            { key: 'KRW', value: '韓ウォン' },
            { key: 'RUB', value: 'ルーブル' },
            { key: 'VND', value: 'ベトナムドン' },
            { key: 'IDR', value: 'インドネシアルピア' },
            { key: 'THB', value: 'タイバーツ' },
            { key: 'MYR', value: 'マレーシアリンギット' },
            { key: 'CNY', value: '人民元' },
            { key: 'JPY', value: '円' },
            { key: 'GBP', value: 'イギリスポンド' },
            { key: 'CHF', value: 'スイスフラン' },
            { key: 'BRL', value: 'ブラジルレアル' },
            { key: 'TWD', value: '台湾ドル' },
            { key: 'SEK', value: 'スウェーデンクローネ' }
          ]
        }
      ]
    },
    {
      codeListId: 'COMMISSION_LIST_SECURITY_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '98', value: '国内株式（現物／信用／IPO・PO／単元未満）' },
            { key: '01', value: '国内株式現物' },
            { key: '02', value: '国内株式信用' },
            { key: '03', value: '国内株式IPO・PO' },
            { key: '04', value: '国内CB' },
            { key: '05', value: '国内投信' },
            { key: '07', value: '外国投信' },
            { key: '10', value: '先物' },
            { key: '11', value: 'オプション' },
            { key: '12', value: '国内債券' },
            { key: '13', value: '外国債券(円建)' },
            { key: '14', value: '外国債券(外貨建)' },
            { key: '15', value: '外国株式' },
            { key: '21', value: '外貨建MMF' },
            { key: '23', value: 'カバードワラント' },
            { key: '24', value: '国内株式(単元未満)' },
            { key: '25', value: '国内債券(国債)' },
            { key: '27', value: '現株ポイント' },
            { key: '29', value: '米株信用' },
            { key: '33', value: 'ST' },
            { key: '99', value: '為替取引' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRUST_FEE_SECURITY_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '06', value: '国内投信残高' },
            { key: '08', value: '外国投信残高' },
            { key: '09', value: '外国投信残高(他)' },
            { key: '16', value: '外貨建MMF残高' },
            { key: '22', value: '投信マイレージ' },
            { key: '28', value: '国内投信つみたてNISA' },
            { key: '32', value: 'SBIラップマイレージ' },
            { key: '34', value: 'ST信報' }
          ]
        }
      ]
    },
    {
      codeListId: 'ALERT_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '赤残' },
            { key: '2', value: '最低保証金' },
            { key: '3', value: '維持率31%' },
            { key: '4', value: '維持率25%' },
            { key: '5', value: '追証' },
            { key: '6', value: '決済期日' },
            { key: '7', value: '外貨赤残' },
            { key: '8', value: '米株信用最低保証金' },
            { key: '9', value: '米株信用維持率51%' },
            { key: '10', value: '米株信用維持率35%' },
            { key: '11', value: '米株信用追証' },
            { key: '12', value: '米株信用決済期日' },
            { key: '13', value: '投信基準価額' },
            { key: '14', value: 'ノックイン' },
            { key: '15', value: 'ノックアウト' },
            { key: '16', value: '国内満期償還' },
            { key: '17', value: '外国満期償還' },
            { key: '18', value: '入出金' },
            { key: '19', value: '入出庫' }
          ]
        }
      ]
    },
    {
      codeListId: 'ORDER_LIST_SECURITY_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '国内株式（現物・信用・現引・現渡）' },
            { key: '1', value: '国内投資信託' },
            { key: '5', value: '国内投資信託 （定期積立）' },
            { key: '2', value: '国内株式（IPO/PO）' },
            { key: '3', value: '外国株式（委託注文）' },
            { key: '4', value: '外国株式（店頭注文）' }
          ]
        }
      ]
    },
    {
      codeListId: 'FOREIGN_REPAY_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '一括指定' },
            { key: '0', value: '個別指定' }
          ]
        }
      ]
    },
    {
      codeListId: 'PERIOD_CHANGE_INFO',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: ' ' },
            { key: '1', value: '期間変更' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '○' }
          ]
        }
      ]
    },
    {
      codeListId: 'PRICE_CHANGE_INFO',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: ' ' },
            { key: '1', value: '価格変更' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '○' }
          ]
        }
      ]
    },
    {
      codeListId: 'SEC_TYPE_NAME',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'K0', value: '国内株式' },
            { key: 'T0', value: '投資信託' }
          ]
        }
      ]
    },
    {
      codeListId: 'SEC_TYPE_CODE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '外国株式' },
            { key: '1', value: '外貨建MMF' },
            { key: '2', value: '外国債券' }
          ]
        }
      ]
    },
    {
      codeListId: 'APPOINTMENT_KBN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '-', value: '6ヶ月' },
            { key: '1', value: '1ヶ月' },
            { key: '2', value: '2ヶ月' },
            { key: '3', value: '3ヶ月' },
            { key: '4', value: '4ヶ月' },
            { key: '5', value: '5ヶ月' },
            { key: '6', value: '6ヶ月' },
            { key: '7', value: '7ヶ月' },
            { key: '8', value: '8ヶ月' },
            { key: '9', value: '無期限' },
            { key: 'A', value: '1日' },
            { key: 'B', value: '5日' },
            { key: 'D', value: '15日' }
          ]
        }
      ]
    },
    {
      codeListId: 'SECURITY_STORE_TRADE_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '国内株式現物' },
            { key: '02', value: '国内株式信用' },
            { key: '03', value: '国内株式IPO・PO' },
            { key: '24', value: '国内株式(単元未満)' },
            { key: '15', value: '外国株式現物' },
            { key: '29', value: '米株信用' },
            { key: '27', value: '現株ポイント' },
            { key: '05', value: '国内投信' },
            { key: '07', value: '外国投信' },
            { key: '21', value: '外貨建MMF' },
            { key: '12', value: '国内債券' },
            { key: '25', value: '国内債券(国債)' },
            { key: '04', value: '国内CB' },
            { key: '13', value: '外国債券(円建)' },
            { key: '14', value: '外国債券(外貨建)' },
            { key: '33', value: 'ST' },
            { key: '10', value: '先物' },
            { key: '11', value: 'オプション' },
            { key: '17', value: '為替取引' },
            { key: '18', value: '外株円決' },
            { key: '19', value: '外債円決' },
            { key: '20', value: '利金償還金' },
            { key: '26', value: '外貨建MMF円決' }
          ]
        }
      ]
    },
    {
      codeListId: 'JOINT_SUBSCRIPT_TRUST_FEE_SECURITY_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '06', value: '国内投信残高' },
            { key: '08', value: '外国投信残高' },
            { key: '09', value: '外国投信残高(他)' },
            { key: '16', value: '外貨建MMF残高' },
            { key: '28', value: '国内投信つみたてNISA' },
            { key: '34', value: 'ST信報' }
          ]
        }
      ]
    },
    {
      codeListId: 'PERSONAL_INVESTMENT_PLAN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '利回り・安定重視' },
            { key: '02', value: '利回り・値上り益重視' },
            { key: '03', value: '値上り益重視' },
            { key: '04', value: '積極的値上り益重視' },
            { key: '09', value: 'その他' },
            { key: '999', value: '未登録' }
          ]
        }
      ]
    },
    {
      codeListId: 'CORPORATION_INVESTMENT_PLAN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '3', value: '元本重視' },
            { key: '4', value: '利子・配当重視' },
            { key: '5', value: '利子・配当と値上がりのバランスを重視' },
            { key: '6', value: '値上り益重視' },
            { key: '7', value: '積極的値上り追求' },
            { key: '9', value: 'その他' },
            { key: '999', value: '未登録' }
          ]
        }
      ]
    },
    {
      codeListId: 'PERSONAL_FUND_CHARACTER',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '余裕資金' },
            { key: '02', value: '使途確定金' },
            { key: '03', value: '借入金' },
            { key: '04', value: '相続財産' },
            { key: '05', value: '退職金' },
            { key: '06', value: '年金・生活費' },
            { key: '09', value: 'その他' },
            { key: '999', value: '未登録' }
          ]
        }
      ]
    },
    {
      codeListId: 'CORPORATION_FUND_CHARACTER',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '余裕資金' },
            { key: '2', value: '使途確定金' },
            { key: '3', value: '借入金' },
            { key: '9', value: 'その他' },
            { key: '999', value: '未登録' }
          ]
        }
      ]
    },
    {
      codeListId: 'PERSONAL_INCOME_FORM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '事業収入' },
            { key: '02', value: '不動産収入' },
            { key: '03', value: '給与収入' },
            { key: '04', value: '利子配当収入' },
            { key: '05', value: '年金' },
            { key: '06', value: '世帯主の収入' },
            { key: '07', value: 'なし' },
            { key: '09', value: 'その他' },
            { key: '999', value: '未登録' }
          ]
        }
      ]
    },
    {
      codeListId: 'PERSONAL_CORPORATION_EMPLOYMENT_PERIOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '長期運用(3年以上)' },
            { key: '02', value: '中期運用(1以上3年未満)' },
            { key: '03', value: '短期運用(1年未満)' },
            { key: '09', value: 'その他' },
            { key: '999', value: '未登録' }
          ]
        }
      ]
    },
    {
      codeListId: 'PERSONAL_ANNUAL_INCOME',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '300万円未満' },
            { key: '02', value: '300～500万円' },
            { key: '03', value: '500～1000万円' },
            { key: '04', value: '1000～2000万円' },
            { key: '05', value: '2000～3000万円' },
            { key: '06', value: '3000～5000万円' },
            { key: '07', value: '5000万円～1億円' },
            { key: '08', value: '1億円～5億円' },
            { key: '99', value: '5億円以上' },
            { key: '999', value: '未登録' }
          ]
        }
      ]
    },
    {
      codeListId: 'PERSONAL_FINANCIAL_ASSETS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '300万円未満' },
            { key: '02', value: '300～500万円' },
            { key: '03', value: '500～1000万円' },
            { key: '04', value: '1000～2000万円' },
            { key: '05', value: '2000～3000万円' },
            { key: '06', value: '3000～5000万円' },
            { key: '07', value: '5000万円～1億円' },
            { key: '08', value: '1億円～5億円' },
            { key: '99', value: '5億円以上' },
            { key: '999', value: '未登録' }
          ]
        }
      ]
    },
    {
      codeListId: 'PERSONAL_OCCUPATION',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: 'A', value: '開業医' },
            { key: 'B', value: '勤務医' },
            { key: 'C', value: '弁護・会計税理・司法行政書士' },
            { key: 'D', value: '自由業' },
            { key: 'E', value: '公務員(管理職)' },
            { key: 'F', value: '公務員(職員)' },
            { key: 'G', value: '会社役員(上場・店頭公開企業)' },
            { key: 'H', value: '会社経営者(未公開企業)' },
            { key: 'I', value: '会社役員(未公開企業)' },
            { key: 'J', value: '会社員' },
            { key: 'K', value: '団体組合諸法人役員' },
            { key: 'L', value: '団体組合諸法人職員' },
            { key: 'M', value: '教職員' },
            { key: 'N', value: '商店サービス自営業' },
            { key: 'O', value: '商店サービス従業員' },
            { key: 'P', value: '農林・漁業' },
            { key: 'Q', value: '無職' },
            { key: 'R', value: '不明' },
            { key: 'S', value: '無職／主婦・主夫' },
            { key: 'T', value: '無職／学生' },
            { key: 'U', value: '無職／定年退職' },
            { key: 'V', value: 'パートアルバイト派遣・契約社員' },
            { key: 'W', value: 'その他' },
            { key: '999', value: '未登録' }
          ]
        }
      ]
    },
    {
      codeListId: 'CORPORATION_OCCUPATION',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '10', value: '日本銀行' },
            { key: '11', value: '都市銀行' },
            { key: '12', value: '信託銀行' },
            { key: '13', value: '地方銀行' },
            { key: '14', value: '第二地方銀行' },
            { key: '15', value: '農林中金' },
            { key: '16', value: '商工中金' },
            { key: '17', value: '長期信用銀行' },
            { key: '18', value: '外国金融機関' },
            { key: '20', value: '生命保険' },
            { key: '21', value: '損害保険' },
            { key: '22', value: '外国保険会社' },
            { key: '30', value: '県信連' },
            { key: '31', value: '共済連' },
            { key: '32', value: '農協' },
            { key: '33', value: '漁協・林協・その他' },
            { key: '40', value: '信用金庫(含全信連)' },
            { key: '41', value: '信用組合(含全組連)' },
            { key: '42', value: '労働金庫(含連合会)' },
            { key: '43', value: 'その他金融機関' },
            { key: '50', value: '官公庁共済組合' },
            { key: '51', value: 'その他共済組合' },
            { key: '60', value: '上場会社' },
            { key: '61', value: '店頭上場会社' },
            { key: '62', value: '非上場会社' },
            { key: '63', value: '外国事業会社' },
            { key: '70', value: '学校法人' },
            { key: '71', value: '宗教法人' },
            { key: '72', value: '医療法人' },
            { key: '73', value: '健康保険組合' },
            { key: '74', value: '財団法人' },
            { key: '75', value: 'その他諸法人' },
            { key: '80', value: '外国ファンド' },
            { key: '81', value: '外国年金基金財団等' },
            { key: '82', value: 'その他外国法人' },
            { key: '86', value: '公社公団等政府系機関' },
            { key: '87', value: '地方公共団体' },
            { key: '88', value: '外国政府系機関' },
            { key: '90', value: '国内証券会社' },
            { key: '91', value: '外国証券会社' },
            { key: '95', value: '持株会' },
            { key: '97', value: '投信委託会社' },
            { key: '98', value: '不明' },
            { key: '999', value: '未登録' }
          ]
        }
      ]
    },
    {
      codeListId: 'PERSONAL_CORPORATION_INVESTMENT_EXP',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '投資経験 あり（株式現物）' },
            { key: '02', value: '投資経験 なし（株式現物）' },
            { key: '03', value: '投資経験 あり（債券）' },
            { key: '04', value: '投資経験 なし（債券）' },
            { key: '05', value: '投資経験 あり（転換社債）' },
            { key: '06', value: '投資経験 なし（転換社債）' },
            { key: '07', value: '投資経験 あり（信用）' },
            { key: '08', value: '投資経験 なし（信用）' },
            { key: '09', value: '投資経験 あり（ワラント）' },
            { key: '10', value: '投資経験 なし（ワラント）' },
            { key: '11', value: '投資経験 あり（先物OP）' },
            { key: '12', value: '投資経験 なし（先物OP）' },
            { key: '13', value: '投資経験 あり（貯蓄型投信）' },
            { key: '14', value: '投資経験 なし（貯蓄型投信）' },
            { key: '15', value: '投資経験 あり（その他投信）' },
            { key: '16', value: '投資経験 なし（その他投信）' },
            { key: '17', value: '投資経験 あり（外国証券）' },
            { key: '18', value: '投資経験 なし（外国証券）' }
          ]
        }
      ]
    },
    {
      codeListId: 'JOINT_SECURITY_CASH_CLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '国内株式現物' },
            { key: '15', value: '外国株式現物' },
            { key: '06', value: '国内投信' },
            { key: '08', value: '外国投信' },
            { key: '09', value: '外国投信(他)' },
            { key: '16', value: '外貨建MMF' },
            { key: '12', value: '国内債券' },
            { key: '25', value: '国内債券(国債)' },
            { key: '04', value: '国内CB' },
            { key: '13', value: '外国債券(円建)' },
            { key: '14', value: '外国債券(外貨建)' },
            { key: '33', value: 'ST' },
            { key: '50', value: 'MRF' },
            { key: '100', value: '金銭(スィープ専用銀行口座)' },
            { key: '98', value: '金銭(外貨)' },
            { key: '99', value: '金銭(円貨)' }
          ]
        }
      ]
    },
    {
      codeListId: 'AGGREGATOR_DETAILS_COUNTING_UNIT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '集計' },
            { key: '1', value: '明細' }
          ]
        }
      ]
    },
    {
      codeListId: 'OTHER_REMAINPOWER_RESTRAIN_ACCOUNT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: 'ジュニアNISA口座' },
            { key: ' ', value: '総合口座' }
          ]
        }
      ]
    },
    {
      codeListId: 'RESTRAIN_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '買付余力' },
            { key: '4', value: 'NISA（成長投資枠）投資可能枠' },
            { key: '5', value: 'NISA（つみたて投資枠）投資可能枠' },
            { key: '6', value: '買付余力・NISA（成長投資枠）投資可能枠' },
            { key: '7', value: '買付余力・NISA（つみたて投資枠）投資可能枠' }
          ]
        }
      ]
    },
    {
      codeListId: 'SEX_KBN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '男' },
            { key: '2', value: '女' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_RESERVE_MODIFY_KBN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '-' },
            { key: '2', value: '変更' },
            { key: '3', value: '解除' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_RESERVE_COURSE_KBN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '毎日' },
            { key: '2', value: '毎週' },
            { key: '3', value: '毎月' },
            { key: '4', value: '複数日' },
            { key: '5', value: '奇数月' },
            { key: '6', value: '偶数月' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '毎日' },
            { key: '2', value: '毎週' },
            { key: '3', value: '毎月' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_RESERVE_NISA_BARELY_BUYING_KBN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '-' },
            { key: '1', value: '設定しない' },
            { key: '2', value: '設定する' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '設定なし' },
            { key: '2', value: '設定あり' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_RESERVE_TAX_SHIFT_KBN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '-' },
            { key: '1', value: '設定しない' },
            { key: '2', value: '設定する' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '設定なし' },
            { key: '2', value: '設定あり' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_RESERVE_WEEKLY_SETTING',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '-' },
            { key: '1', value: '月' },
            { key: '2', value: '火' },
            { key: '3', value: '水' },
            { key: '4', value: '木' },
            { key: '5', value: '金' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '月曜日' },
            { key: '2', value: '火曜日' },
            { key: '3', value: '水曜日' },
            { key: '4', value: '木曜日' },
            { key: '5', value: '金曜日' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_RESERVE_MONTHLY_SETTING',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '00', value: '--' },
            { key: '01', value: '1' },
            { key: '02', value: '2' },
            { key: '03', value: '3' },
            { key: '04', value: '4' },
            { key: '05', value: '5' },
            { key: '06', value: '6' },
            { key: '07', value: '7' },
            { key: '08', value: '8' },
            { key: '09', value: '9' },
            { key: '10', value: '10' },
            { key: '11', value: '11' },
            { key: '12', value: '12' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_RESERVE_DAY_SETTING',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '00', value: '--' },
            { key: '01', value: '1' },
            { key: '02', value: '2' },
            { key: '03', value: '3' },
            { key: '04', value: '4' },
            { key: '05', value: '5' },
            { key: '06', value: '6' },
            { key: '07', value: '7' },
            { key: '08', value: '8' },
            { key: '09', value: '9' },
            { key: '10', value: '10' },
            { key: '11', value: '11' },
            { key: '12', value: '12' },
            { key: '13', value: '13' },
            { key: '14', value: '14' },
            { key: '15', value: '15' },
            { key: '16', value: '16' },
            { key: '17', value: '17' },
            { key: '18', value: '18' },
            { key: '19', value: '19' },
            { key: '20', value: '20' },
            { key: '21', value: '21' },
            { key: '22', value: '22' },
            { key: '23', value: '23' },
            { key: '24', value: '24' },
            { key: '25', value: '25' },
            { key: '26', value: '26' },
            { key: '27', value: '27' },
            { key: '28', value: '28' },
            { key: '29', value: '29' },
            { key: '30', value: '30' },
            { key: '31', value: '31' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '00', value: '--' },
            { key: '01', value: '1' },
            { key: '02', value: '2' },
            { key: '03', value: '3' },
            { key: '04', value: '4' },
            { key: '05', value: '5' },
            { key: '06', value: '6' },
            { key: '07', value: '7' },
            { key: '08', value: '8' },
            { key: '09', value: '9' },
            { key: '10', value: '10' },
            { key: '11', value: '11' },
            { key: '12', value: '12' },
            { key: '13', value: '13' },
            { key: '14', value: '14' },
            { key: '15', value: '15' },
            { key: '16', value: '16' },
            { key: '17', value: '17' },
            { key: '18', value: '18' },
            { key: '19', value: '19' },
            { key: '20', value: '20' },
            { key: '21', value: '21' },
            { key: '22', value: '22' },
            { key: '23', value: '23' },
            { key: '24', value: '24' },
            { key: '25', value: '25' },
            { key: '26', value: '26' },
            { key: '27', value: '27' },
            { key: '28', value: '28' },
            { key: '29', value: '29' },
            { key: '30', value: '30' },
            { key: '31', value: '月末' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '01', value: '1日' },
            { key: '02', value: '2日' },
            { key: '03', value: '3日' },
            { key: '04', value: '4日' },
            { key: '05', value: '5日' },
            { key: '06', value: '6日' },
            { key: '07', value: '7日' },
            { key: '08', value: '8日' },
            { key: '09', value: '9日' },
            { key: '10', value: '10日' },
            { key: '11', value: '11日' },
            { key: '12', value: '12日' },
            { key: '13', value: '13日' },
            { key: '14', value: '14日' },
            { key: '15', value: '15日' },
            { key: '16', value: '16日' },
            { key: '17', value: '17日' },
            { key: '18', value: '18日' },
            { key: '19', value: '19日' },
            { key: '20', value: '20日' },
            { key: '21', value: '21日' },
            { key: '22', value: '22日' },
            { key: '23', value: '23日' },
            { key: '24', value: '24日' },
            { key: '25', value: '25日' },
            { key: '26', value: '26日' },
            { key: '27', value: '27日' },
            { key: '28', value: '28日' },
            { key: '29', value: '29日' },
            { key: '30', value: '30日' },
            { key: '31', value: '月末' }
          ]
        },
        {
          displayPattern: 4,
          codeList: [
            { key: '01', value: '1日' },
            { key: '02', value: '2日' },
            { key: '03', value: '3日' },
            { key: '04', value: '4日' },
            { key: '05', value: '5日' },
            { key: '06', value: '6日' },
            { key: '07', value: '7日' },
            { key: '08', value: '8日' },
            { key: '09', value: '9日' },
            { key: '10', value: '10日' },
            { key: '11', value: '11日' },
            { key: '12', value: '12日' },
            { key: '13', value: '13日' },
            { key: '14', value: '14日' },
            { key: '15', value: '15日' },
            { key: '16', value: '16日' },
            { key: '17', value: '17日' },
            { key: '18', value: '18日' },
            { key: '19', value: '19日' },
            { key: '20', value: '20日' },
            { key: '21', value: '21日' },
            { key: '22', value: '22日' },
            { key: '23', value: '23日' },
            { key: '24', value: '24日' },
            { key: '25', value: '25日' },
            { key: '26', value: '26日' },
            { key: '27', value: '27日' },
            { key: '28', value: '28日' },
            { key: '29', value: '29日' },
            { key: '30', value: '30日' },
            { key: '31', value: '31日' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_RESERVE_COMPLA_CHECK_KBN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'チェックOFF' },
            { key: '1', value: 'チェックON' },
            { key: '2', value: '勧誘なし' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_RESERVE_PAYMENT_METHOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '現金' },
            { key: '2', value: 'クレジットカード' }
          ]
        }
      ]
    },
    {
      codeListId: 'FUND_RESERVE_BIMONTHLY_KBN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '-' },
            { key: '1', value: '奇数月' },
            { key: '2', value: '偶数月' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: '毎月' },
            { key: '1', value: '奇数月' },
            { key: '2', value: '偶数月' }
          ]
        }
      ]
    },
    {
      codeListId: 'HISTORY_BIGCLASS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '国内注文' },
            { key: '10', value: '外株委託注文' },
            { key: '11', value: '外株店頭注文' },
            { key: '12', value: '為替取引注文' },
            { key: '2', value: '接触履歴（入力）' },
            { key: '4', value: '書類請求' },
            { key: '5', value: '入出金' },
            { key: '3', value: '積立買付／定期売却' },
            { key: '6', value: 'PWリセット' },
            { key: '7', value: '貸株' },
            { key: '8', value: 'ネーム変更' },
            { key: '9', value: '貸株振替' }
          ]
        }
      ]
    },
    {
      codeListId: 'JUUYOUDO',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '高' },
            { key: '2', value: '中' },
            { key: '3', value: '低' }
          ]
        }
      ]
    },
    {
      codeListId: 'TAIOU_STS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未' },
            { key: '1', value: '済' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: '未対応' },
            { key: '1', value: '対応済' }
          ]
        }
      ]
    },
    {
      codeListId: 'NYUUDEN_HOUKOU',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'イン' },
            { key: '1', value: 'アウト' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: 'イン' },
            { key: '1', value: 'アウト' },
            { key: '2', value: '全て' }
          ]
        }
      ]
    },
    {
      codeListId: 'CREAM_REQUEST',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: 'ALL' },
            { key: '1', value: 'クレーム' },
            { key: '2', value: 'リクエスト' },
            { key: '3', value: '-' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '0', value: '-' },
            { key: '1', value: 'クレーム' },
            { key: '2', value: 'リクエスト' },
            { key: '3', value: 'クレーム/リクエスト' }
          ]
        }
      ]
    },
    {
      codeListId: 'SESSYOKU_KEIRO',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: ' ' },
            { key: 'A', value: '訪問' },
            { key: 'B', value: '来店' },
            { key: 'C', value: '架電' },
            { key: 'D', value: '入電' },
            { key: 'E', value: 'メール送信' },
            { key: 'F', value: 'メール受信' },
            { key: 'G', value: '郵便／書類発送' },
            { key: 'H', value: '郵便／書類受領' },
            { key: 'I', value: 'ＦＡＸ送信' },
            { key: 'J', value: 'ＦＡＸ受信' },
            { key: 'K', value: 'Ｗｅｂ面談' }
          ]
        }
      ]
    },
    {
      codeListId: 'RESERVE_TRADE_DEPOSIT_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: '特定/一般' },
            { key: 'H', value: 'NISA（成長投資枠）' },
            { key: 'I', value: 'NISA（つみたて投資枠）' }
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: ' ', value: '総合口座－特定/一般' },
            { key: '5', value: 'ジュニアNISA口座－特定/一般' },
            { key: 'H', value: '総合口座－NISA（成長投資枠）' },
            { key: 'I', value: '総合口座－NISA（つみたて投資枠）' }
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: ' ', value: '特定/一般' },
            { key: '5', value: 'Jr特定/Jr一般' },
            { key: 'H', value: 'NISA（成長投資枠）' },
            { key: 'I', value: 'NISA（つみたて投資枠）' }
          ]
        }
      ]
    },
    {
      codeListId: 'AGGREGATION_UNIT_TRADE_TREND',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '仲介業者' },
            { key: '2', value: '営業員' },
            { key: '3', value: '顧客' }
          ]
        }
      ]
    },
    {
      codeListId: 'CONFIRMATION_LETTER_ACCEPT_CONFIRM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認' },
            { key: '1', value: '確認済' }
          ]
        }
      ]
    },
    {
      codeListId: 'BROKER_CHARGE_CUSTOMER_COUNTING_UNIT',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '仲介業者' },
            { key: '1', value: '営業員' },
            { key: '2', value: '顧客' }
          ]
        }
      ]
    },
    {
      codeListId: 'AGE_GROUP',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '20歳未満' },
            { key: '02', value: '20代' },
            { key: '03', value: '30代' },
            { key: '04', value: '40代' },
            { key: '05', value: '50代' },
            { key: '06', value: '60代' },
            { key: '07', value: '70代' },
            { key: '08', value: '80代' },
            { key: '09', value: '90歳以上' },
            { key: '99', value: '法人' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRADING_MOTIVE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '00', value: '-' },
            { key: '01', value: '紹介' },
            { key: '02', value: 'ダイレクトメール' },
            { key: '03', value: 'ＣＭ・広告を見て' },
            { key: '04', value: '当社からの訪問' },
            { key: '05', value: '当社からの電話' },
            { key: '06', value: '講演会に参加して' },
            { key: '07', value: '折込広告' },
            { key: '08', value: '店周ビラ' },
            { key: '09', value: 'ポスター' },
            { key: '10', value: 'セミナー' },
            { key: '11', value: '親戚知人' },
            { key: '12', value: '来店' },
            { key: '13', value: 'ホームページ' },
            { key: '14', value: '新聞・雑誌' },
            { key: '99', value: 'その他' }
          ]
        }
      ]
    },
    {
      codeListId: 'ASSET_MANAGEMENT_PERIOD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '00', value: '-' },
            { key: '01', value: '長期運用(3年以上)' },
            { key: '02', value: '中期運用(1以上3年未満)' },
            { key: '03', value: '短期運用(1年未満)' },
            { key: '09', value: 'その他' }
          ]
        }
      ]
    },
    {
      codeListId: 'MAIN_INCOME',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '  ', value: '-' },
            { key: '01', value: '事業収入' },
            { key: '02', value: '不動産収入' },
            { key: '03', value: '給与収入' },
            { key: '04', value: '利子配当収入' },
            { key: '05', value: '年金' },
            { key: '06', value: '世帯主の収入' },
            { key: '07', value: 'なし' },
            { key: '09', value: 'その他' }
          ]
        }
      ]
    },
    {
      codeListId: 'ANNUAL_INCOME',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '  ', value: '-' },
            { key: '01', value: '300万円未満' },
            { key: '02', value: '300～500万円' },
            { key: '03', value: '500～1000万円' },
            { key: '04', value: '1000～2000万円' },
            { key: '05', value: '2000～3000万円' },
            { key: '06', value: '3000～5000万円' },
            { key: '07', value: '5000万円～1億円' },
            { key: '08', value: '1億円以上' },
            { key: '99', value: '5億円以上' }
          ]
        }
      ]
    },
    {
      codeListId: 'INTEREST_FINANCIAL_PROD',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '  ', value: '-' },
            { key: '01', value: '株式現金取引' },
            { key: '02', value: '株式信用取引' },
            { key: '03', value: '投資信託' },
            { key: '04', value: '公社債' },
            { key: '05', value: '累積投資' },
            { key: '06', value: '外国証券' },
            { key: '07', value: '先物・オプション' },
            { key: '08', value: 'その他' }
          ]
        }
      ]
    },
    {
      codeListId: 'CLOSING_KBN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '-' },
            { key: '1', value: '済' }
          ]
        }
      ]
    },
    {
      codeListId: 'CLOSING_MONTH',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: '１月' },
            { key: '02', value: '２月' },
            { key: '03', value: '３月' },
            { key: '04', value: '４月' },
            { key: '05', value: '５月' },
            { key: '06', value: '６月' },
            { key: '07', value: '７月' },
            { key: '08', value: '８月' },
            { key: '09', value: '９月' },
            { key: '10', value: '１０月' },
            { key: '11', value: '１１月' },
            { key: '12', value: '１２月' }
          ]
        }
      ]
    },
    {
      codeListId: 'SUG_BOX_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: '全て' },
            { key: '9', value: '完了' },
            { key: '1', value: '確認中' },
            { key: '0', value: '回答待ち' }
          ]
        }
      ]
    },
    {
      codeListId: 'SUG_BOX_CATEGORY',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '01', value: 'システム' },
            { key: '02', value: 'コンプラ' },
            { key: '03', value: '商品/サービス' },
            { key: '04', value: '業務全般' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRADE_HISTORY_SECURITY_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: ' ' },
            { key: 'A', value: '国内株式ＡＬＬ[A]' },
            { key: 'A01', value: '国内上場株式ＡＬＬ[A01]' },
            { key: 'A02', value: '国内店頭上場株式ＡＬＬ[A02]' },
            { key: 'A03', value: '国内株式非上場ＡＬＬ[A03]' },
            { key: 'A04', value: '東証上場外国株ＡＬＬ[A04]' },
            { key: 'A05', value: '大証上場外国株ＡＬＬ[A05]' },
            { key: 'A06', value: '上場投信ＡＬＬ[A06]' },
            { key: 'B', value: '国内公社債ＡＬＬ[B]' },
            { key: 'B01', value: '国内一般債ＡＬＬ[B01]' },
            { key: 'B0103', value: '国内中期割引国債[B0103]' },
            { key: 'B0104', value: '国内中期国債[B0104]' },
            { key: 'B0105', value: '国内長期国債[B0105]' },
            { key: 'B0113', value: '国内特殊債[B0113]' },
            { key: 'B0119', value: '国内割引金融債[B0119]' },
            { key: 'B0120', value: '国内普通社債(SB)[B0120]' },
            { key: 'B0121', value: '国内EXワラント[B0121]' },
            { key: 'B0122', value: '国内普通電力債[B0122]' },
            { key: 'B02', value: '国内転換社債ＡＬＬ[B02]' },
            { key: 'B0201', value: '国内転換社債[B0201]' },
            { key: 'B0202', value: '国内転換社債(電力債)[B0202]' },
            { key: 'B04', value: '国内ワラントＡＬＬ[B04]' },
            { key: 'C', value: '国内金融商品ＡＬＬ[C]' },
            { key: 'C01', value: '国内短期金融資産ＡＬＬ[C01]' },
            { key: 'D', value: '国内投信ＡＬＬ[D]' },
            { key: 'D01', value: '国内投信株式型単位型ＡＬＬ[D01]' },
            { key: 'D02', value: '国内投信株式型追加型ＡＬＬ[D02]' },
            { key: 'D03', value: '国内投信公社債型単位型ＡＬＬ[D03]' },
            { key: 'D04', value: '国内投信公社債型追加型ＡＬＬ[D04]' },
            { key: 'H', value: '外国株式ＡＬＬ[H]' },
            { key: 'I', value: '外国公社債ＡＬＬ[I]' },
            { key: 'I01', value: '外国公社債ＡＬＬ[I01]' },
            { key: 'I0118', value: '外国ワラント[I0118]' },
            { key: 'I0119', value: '外国ヤンキーユーロ債[I0119]' },
            { key: 'I0120', value: '外国変動利付債[I0120]' },
            { key: 'I0123', value: '外国私募債[I0123]' },
            { key: 'I0199', value: '外国その他債[I0199]' },
            { key: 'K', value: '外国投信ＡＬＬ[K]' },
            { key: 'Q', value: '継投ＡＬＬ[Q]' },
            { key: 'QLM', value: 'ＭＭＦＡＬＬ[QLM]' },
            { key: 'QMF', value: 'ＭＲＦＡＬＬ[QMF]' },
            { key: 'QPY', value: '汎用累投ＡＬＬ[QPY]' },
            { key: 'QTY', value: '中国ファンドＡＬＬ[QTY]' },
            { key: 'Z', value: '現金ＡＬＬ[Z]' }
          ]
        }
      ]
    },
    {
      codeListId: 'TRADE_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '$NULL', value: ' ' },
            { key: 'A', value: '現物買い約定ＡＬＬ[A]' },
            { key: 'A1', value: '現物買付(除募集)ＡＬＬ[A1]' },
            { key: 'A11', value: '買付[A11]' },
            { key: 'A12', value: '現先買い新規[A12]' },
            { key: 'A16', value: '現先買い返済[A16]' },
            { key: 'A2', value: '募集ＡＬＬ[A2]' },
            { key: 'A21', value: '募集(既約)[A21]' },
            { key: 'A22', value: '募集(未約)[A22]' },
            { key: 'B', value: '募集/入札(旧CY)ＡＬＬ[B]' },
            { key: 'B1', value: '募集/入札(旧CY)ＡＬＬ[B1]' },
            { key: 'B11', value: '募集(旧CY)[B11]' },
            { key: 'C', value: '現物売り約定ＡＬＬ[C]' },
            { key: 'C1', value: '現物売却ＡＬＬ[C1]' },
            { key: 'C11', value: '売却[C11]' },
            { key: 'C12', value: '現先売り新規[C12]' },
            { key: 'C14', value: '解約[C14]' },
            { key: 'C15', value: '買取[C15]' },
            { key: 'C18', value: '現先売り返済[C18]' },
            { key: 'D', value: '信用取引ＡＬＬ[D]' },
            { key: 'D1', value: '信用取引買いＡＬＬ[D1]' },
            { key: 'D11', value: '信用買い新規[D11]' },
            { key: 'D12', value: '信用買い返済[D12]' },
            { key: 'D13', value: '現引[D13]' },
            { key: 'D2', value: '信用取引売りＡＬＬ[D2]' },
            { key: 'D21', value: '信用売り新規[D21]' },
            { key: 'D22', value: '信用売り返済[D22]' },
            { key: 'D23', value: '現渡[D23]' },
            { key: 'E', value: '入庫/入金ＡＬＬ[E]' },
            { key: 'E1', value: '入庫ＡＬＬ[E1]' },
            { key: 'E10', value: '預り(HA)[E10]' },
            { key: 'E11', value: '増資[E11]' },
            { key: 'E13', value: '部店移管(新)[E13]' },
            { key: 'E14', value: '強制[E14]' },
            { key: 'E19', value: '振替[E19]' },
            { key: 'E3', value: '入金ＡＬＬ[E3]' },
            { key: 'E31', value: '振替入金(CF)[E31]' },
            { key: 'E32', value: '配当金(株式、信用、外証)[E32]' },
            { key: 'E33', value: '権利処理代[E33]' },
            { key: 'E35', value: '償還金[E35]' },
            { key: 'E36', value: '利金/分配金[E36]' },
            { key: 'E37', value: 'パック入金[E37]' },
            { key: 'E3Y', value: '損益通算還付金[E3Y]' },
            { key: 'F', value: '出庫/出金ＡＬＬ[F]' },
            { key: 'F1', value: '出庫ＡＬＬ[F1]' },
            { key: 'F10', value: '返却(HT)[F10]' },
            { key: 'F11', value: '減資[F11]' },
            { key: 'F13', value: '部店移管(旧)[F13]' },
            { key: 'F14', value: '強制[F14]' },
            { key: 'F17', value: '振替[F17]' },
            { key: 'F3', value: '出金ＡＬＬ[F3]' },
            { key: 'F31', value: '振替出金(CG)[F31]' },
            { key: 'F32', value: '配当金(信用)[F32]' },
            { key: 'F33', value: '権利処理代[F33]' }
          ]
        }
      ]
    },
    {
      codeListId: 'SORT_ORDER_ITEM',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '約定日' },
            { key: '1', value: '銘柄コード' }
          ]
        }
      ]
    },
    {
      codeListId: 'SORT_ORDER_PROFILE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '昇順' },
            { key: '1', value: '降順' }
          ]
        }
      ]
    },
    {
      codeListId: 'CONFLICT_OF_INTEREST_EXPLAIN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未確認'},
            { key: '1', value: '確認済'}
          ]
        },
        {
          displayPattern: 2,
          codeList: [
            { key: '1', value: '説明実施済'}
          ]
        },
        {
          displayPattern: 3,
          codeList: [
            { key: '0', value: '未説明'},
            { key: '1', value: '説明済み'}
          ]
        }
      ]
    },
    {
      codeListId: 'JOINT_CONTRACT_STATUS',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: ' ', value: '全て' },
            { key: '1', value: '契約' },
            { key: '0', value: '未契約' }
          ]
        }
      ]
    },
    {
      codeListId: 'FILE_TYPE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '画像' },
            { key: '1', value: 'PDF' }
          ]
        }
      ]
    },
    {
      codeListId: 'PDF_SIZE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '16：9' },
            { key: '1', value: '4：3' }
          ]
        }
      ]
    },
    {
      codeListId: 'VERTICAL_AND_HORIZONTAL',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '縦' },
            { key: '1', value: '横' }
          ]
        }
      ]
    },
    {
      codeListId: 'JOINT_USER_DISP_KBN',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '1', value: '共同募集顧客' },
            { key: '2', value: '自動解約顧客' }
          ]
        }
      ]
    },
    {
      codeListId: 'JOINT_USER_REASON_CODE',
      displayPatternList: [
        {
          displayPattern: 1,
          codeList: [
            { key: '0', value: '未設定' },
            { key: '1', value: '口座契約変更（共募契約先）' },
            { key: '2', value: '口座契約変更（IFA契約解除）' },
            { key: '3', value: '口座契約変更（共募非契約先）' },
            { key: '4', value: '仲介業者変更（共募非契約先）' },
            { key: '5', value: '仲介業者変更（削除）' }
          ]
        }
      ]
    }
  ]
}
