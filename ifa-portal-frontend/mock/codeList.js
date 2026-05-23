
module.exports = [
  // codeList
  {
    url: '/biz-serv/common/common-code/get-code-list',
    type: 'post',
    response: config => {
      return {
        dataList: [
          { key: 'A', value: 'a当社優先市場／SOR' },
          { key: '0', value: '東証' },
          { key: '2', value: '名証' },
          { key: '6', value: '福証' },
          { key: '7', value: 'PTS' },
          { key: '8', value: '札証' }
        ]
      }
    }
  }
]
