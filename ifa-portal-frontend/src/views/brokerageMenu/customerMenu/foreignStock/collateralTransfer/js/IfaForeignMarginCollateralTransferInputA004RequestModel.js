import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginCollateralTransferInputA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    // 代用有価証券振替区分
    this.transferClassification = obj.transferClassification ? obj.transferClassification : ''
    // 代用有価証券振替情報
    this.collateralSecurityTransferInfoList = []
    for (let i = 0; i < obj.selected.length; i++) {
      this.collateralSecurityTransferInfoList.push({
        collateralDepositListBrandCode: obj.selected[i].collateralDepositListBrandCode, // 代用有価証券振替情報.銘柄コード
        specificAccountCode: obj.selected[i].specificAccountCode, // 代用有価証券振替情報.預り区分
        depositType: obj.selected[i].depositType // 代用有価証券振替情報.保護区分
      })
    }
  }
}
