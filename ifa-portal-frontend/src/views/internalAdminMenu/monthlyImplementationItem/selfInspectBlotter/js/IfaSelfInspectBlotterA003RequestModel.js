import Logger from '@/utils/ifaLog.js'
export class IfaSelfInspectBlotterA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.assignMonth = obj.assignMonth ? obj.assignMonth : '' // 表示年月
  }
}
