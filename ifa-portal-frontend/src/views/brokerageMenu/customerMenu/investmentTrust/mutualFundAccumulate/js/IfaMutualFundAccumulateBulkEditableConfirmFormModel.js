export class IfaMutualFundAccumulateBulkEditableConfirmFormModel {
  constructor(obj) {
    const transformBulkChangeList = item => item?.bulkChangeList.map(() => ({
      complianceRankCheckConfirm: false,
      complianceRankCheckStartBaseConfirm: '0'
    }))

    // bulk list
    this.dynamicList = transformBulkChangeList(obj)

    this.noticeInfoAlertConfirm = '0'
    this.noticeAlertConfirm = '0'
    this.confirmDocumentAlertConfirm = '0'
  }
}
