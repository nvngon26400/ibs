import Logger from '@/utils/ifaLog.js'
export class IfaMarginPositionListDomesticA004aRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.marginPositionListDomestic = obj.marginPositionListDomesticList ? obj.marginPositionListDomesticList : '' // 信用建玉一覧リスト
  }
}
