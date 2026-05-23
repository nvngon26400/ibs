const data = {
  items:
  [
    { kouban: '1', butenCode: '119', kouzaNo: '521477', name: 'テスト　テスト太郎', contactContent: '注文　株式買付', registDate: '2021/10/25', detail: '顧客属性詳細' },
    { kouban: '2', butenCode: '119', kouzaNo: '521477', name: 'テスト　テスト太郎', contactContent: '注文　株式買付', registDate: '2021/10/25', detail: '顧客属性詳細' },
    { kouban: '3', butenCode: '119', kouzaNo: '521477', name: 'テスト　テスト太郎', contactContent: '注文　株式買付　取消', registDate: '2021/10/25', detail: '顧客属性詳細' },
    { kouban: '4', butenCode: '119', kouzaNo: '521477', name: 'テスト　テスト太郎', contactContent: '注文　株式買付　取消', registDate: '2021/10/25', detail: '顧客属性詳細' },
    { kouban: '5', butenCode: '119', kouzaNo: '521477', name: 'テスト　テスト太郎', contactContent: '注文　新規買付 6ヶ月', registDate: '2021/10/25', detail: '顧客属性詳細' },
    { kouban: '6', butenCode: '119', kouzaNo: '521477', name: 'テスト　テスト太郎', contactContent: '注文　株式買付', registDate: '2021/10/25', detail: '顧客属性詳細' },
    { kouban: '7', butenCode: '119', kouzaNo: '521477', name: 'テスト　テスト太郎', contactContent: '注文　株式買付', registDate: '2021/10/25', detail: '顧客属性詳細' }
  ]
}

module.exports = [
  {
    url: '/ifa-mock/contactHistory/list',
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
