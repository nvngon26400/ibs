import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginCollateralTransferConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    // 代用有価証券振替区分
    this.transferClassification = obj.transferClassification ? obj.transferClassification : ''
    // 代用有価証券振替情報
    this.collateralSecurityTransferInfoList = []
    for (let i = 0; i < obj.collateralSecurityTransferInfo.length; i++) {
      this.collateralSecurityTransferInfoList.push({
        collateralDepositListBrandCode: obj.collateralSecurityTransferInfo[i].collateralDepositListBrandCode, // 代用有価証券振替情報.銘柄コード
        specificAccountCode: obj.collateralSecurityTransferInfo[i].specificAccountCode, // 代用有価証券振替情報.預り区分
        transferQuantity: obj.collateralSecurityTransferInfo[i].transferQuantity, // 代用有価証券振替情報.保有数量
        depositType: obj.collateralSecurityTransferInfo[i].depositType // 代用有価証券振替情報.保護区分
      })
    }
  }
}
