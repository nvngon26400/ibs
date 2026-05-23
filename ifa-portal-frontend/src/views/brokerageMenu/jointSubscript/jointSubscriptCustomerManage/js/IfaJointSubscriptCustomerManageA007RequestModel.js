// 共同募集　顧客管理 A007削除確認のリクエストモデル
export class IfaJointSubscriptCustomerManageA007RequestModel {
  constructor(objArray) {
    if (!Array.isArray(objArray)) {
      throw new Error('The provided obj should be an array.')
    }
    this.a007Model = []
    objArray.forEach((srcObj, index) => {
      const desObj = this.processObject(srcObj)
      this.a007Model.push(desObj)
    })
  }
  processObject(obj) {
    const desObj = {}
    desObj.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    desObj.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
    desObj.butenCode = obj.butenCode ? obj.butenCode : '' // 部店
    desObj.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    desObj.customerAttributeName = obj.customerAttributeName ? obj.customerAttributeName : '' // コース名
    desObj.nameKanji = obj.nameKanji ? obj.nameKanji : '' // 顧客名
    desObj.jointBranchCode = obj.jointBranchCode ? obj.jointBranchCode : '' // 共募支店コード
    desObj.jointBranchName = obj.jointBranchName ? obj.jointBranchName : '' // 共募支店名
    desObj.contractDate = obj.contractDate ? obj.contractDate : '' // 契約締結日
    desObj.startDate = obj.startDate ? obj.startDate : '' // 同意日
    desObj.endDate = obj.endDate ? obj.endDate : '' // 終了日
    desObj.jointRewardRate = obj.jointRewardRate ? obj.jointRewardRate : '' // 支払率
    desObj.editStatus = obj.editStatus ? obj.editStatus : '' // 手続状況
    desObj.editStatusName = obj.editStatusName ? obj.editStatusName : '' // 手続状況名
    desObj.brokerChargeCode = obj.brokerChargeCode ? obj.brokerChargeCode : '' // 営業員コード
    desObj.brokerChargeName = obj.brokerChargeName ? obj.brokerChargeName : '' // 営業名
    return desObj
  }
}
