const Mock = require('mockjs')
const { param2Obj } = require('./utils')

const user = require('./user')
const table = require('./table')
const samplePageTableData = require('./samplePageTableData')
const foreignManagingContractListTableData = require('./foreignManagingContractListTableData')
const contactHistoryTableData = require('./contactHistoryTableData')
const depositWithdrawalTableData = require('./depositWithdrawalTableData')
const principalAndInterestTableData = require('./principalAndInterestTableData')
const newAccountHistoryTable = require('./newAccountHistoryTable')
const brandAlertTableData = require('./brandAlertTableData')
const complianceInfoInputTableData = require('./complianceInfoInputTableData')
const complianceInfoBrowsingStatusTableData = require('./complianceInfoBrowsingStatusTableData')
const complianceInfoAllBrowsingDisabledTableData = require('./complianceInfoAllBrowsingDisabledTableData')
const complianceInfoBrowsingStatusForInternalTableData = require('./complianceInfoBrowsingStatusForInternalTableData')
const basicCustomerSearchCreditTableData = require('./basicCustomerSearchCreditTableData')
const chargeRewardTableData = require('./chargeRewardTableData')
const trustRewardTableData = require('./trustRewardTableData')
const remainPowerAlertData = require('./remainPowerAlertData')
const inpTradeHistoryData = require('./inpTradeHistoryData')
const codeList = require('./codeList')

const mocks = [
  ...user,
  ...table,
  ...samplePageTableData,
  ...foreignManagingContractListTableData,
  ...contactHistoryTableData,
  ...depositWithdrawalTableData,
  ...principalAndInterestTableData,
  ...newAccountHistoryTable,
  ...brandAlertTableData,
  ...complianceInfoInputTableData,
  ...complianceInfoBrowsingStatusTableData,
  ...complianceInfoAllBrowsingDisabledTableData,
  ...complianceInfoBrowsingStatusForInternalTableData,
  ...basicCustomerSearchCreditTableData,
  ...chargeRewardTableData,
  ...trustRewardTableData,
  ...remainPowerAlertData,
  ...inpTradeHistoryData,
  ...codeList
]

// for front mock
// please use it cautiously, it will redefine XMLHttpRequest,
// which will cause many of your third-party libraries to be invalidated(like progress event).
function mockXHR() {
  // mock patch
  // https://github.com/nuysoft/Mock/issues/300
  // eslint-disable-next-line camelcase
  Mock.XHR.prototype.proxy_send = Mock.XHR.prototype.send
  Mock.XHR.prototype.send = function() {
    if (this.custom.xhr) {
      this.custom.xhr.withCredentials = this.withCredentials || false

      if (this.responseType) {
        this.custom.xhr.responseType = this.responseType
      }
    }
    this.proxy_send(...arguments)
  }

  function XHR2ExpressReqWrap(respond) {
    return function(options) {
      let result = null
      if (respond instanceof Function) {
        const { body, type, url } = options
        // https://expressjs.com/en/4x/api.html#req
        result = respond({
          method: type,
          body: JSON.parse(body),
          query: param2Obj(url)
        })
      } else {
        result = respond
      }
      return Mock.mock(result)
    }
  }

  for (const i of mocks) {
    Mock.mock(new RegExp(i.url), i.type || 'get', XHR2ExpressReqWrap(i.response))
  }
}

module.exports = {
  mocks,
  mockXHR
}

