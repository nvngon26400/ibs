import Logger from '@/utils/ifaLog.js'
export class IfaPortalNotificationManagerLookupA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    let dateYmdTo = obj.period[1]
    if (obj.period[1]) {
      dateYmdTo = dateYmdTo.concat(' 23:59:59')
    }
    let pastDateExcrudeValue = ''
    if (obj.pastDateExcrude) {
      pastDateExcrudeValue = 'true'
    } else {
      pastDateExcrudeValue = 'false'
    }
    this.searchDateYmdFrom = obj.period[0] ? obj.period[0] : '' // 検索年月日(From)
    this.searchDateYmdTo = obj.period[1] ? dateYmdTo : '' // 検索年月日(To)
    this.pastDateExcrude = obj.pastDateExcrude ? pastDateExcrudeValue : 'false' // 過去日除外
  }
}
