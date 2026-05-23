import Logger from '@/utils/ifaLog.js'
export class IfaMarginPositionListForeignA006aRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    /** 信用建玉一覧リスト. */
    this.marginPositionListForeignList = obj.marginPositionListForeignList ? obj.marginPositionListForeignList : ''
  }
}
