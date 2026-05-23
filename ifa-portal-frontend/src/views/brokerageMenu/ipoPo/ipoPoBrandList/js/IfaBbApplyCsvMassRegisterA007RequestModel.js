import Logger from '@/utils/ifaLog.js'
export class IfaBbApplyCsvMassRegisterA007RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.bbApplyList = obj || []
  }
}
