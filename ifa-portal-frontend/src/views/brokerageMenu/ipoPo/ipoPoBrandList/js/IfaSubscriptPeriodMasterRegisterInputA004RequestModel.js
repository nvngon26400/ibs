import Logger from '@/utils/ifaLog.js'
export class IfaSubscriptPeriodMasterRegisterInputA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.bookBuildingPresentationFrom = obj.bookBuildingPresentationFrom ? obj.bookBuildingPresentationFrom : '' // ブックビルディング申込期間（開始）
    this.brandCodeWith1 = obj.brandCodeWith1 ? obj.brandCodeWith1 : null // 銘柄コード１付き
    this.smokingCigarette = obj.smokingCigarette ? obj.smokingCigarette : null // たばこ開示
    this.onlyElectronicDelivery = obj.onlyElectronicDelivery ? obj.onlyElectronicDelivery : null // 電子交付のみ
    this.maxAllocation = obj.maxAllocation ? obj.maxAllocation : '' // 配分上限株数
    this.depositScheduleDate = obj.depositScheduleDate ? obj.depositScheduleDate : '' // 入金予定日
    this.bbPeriodFrom = obj.bbPeriodFrom ? obj.bbPeriodFrom : '' // 募集期間FROM
    this.bbPeriodTo = obj.bbPeriodTo ? obj.bbPeriodTo : '' // 募集期間TO
    this.issueBbPrice = obj.issueBbPrice ? obj.issueBbPrice : '' // 発行価格
    this.listedDate = obj.listedDate ? obj.listedDate : '' // 上場日
    this.ifaBookBuildingPresentationFrom = obj.ifaBookBuildingPresentationFrom ? obj.ifaBookBuildingPresentationFrom : '' // IFAのブックビルディング申込期間（開始）
    this.ifaBookBuildingPresentationTo = obj.ifaBookBuildingPresentationTo ? obj.ifaBookBuildingPresentationTo : '' // IFAのブックビルディング申込期間（終了）
    this.comm = obj.comm ? obj.comm : '' // 手数料
    this.updateDate = obj.updateDate ? obj.updateDate : '' // 更新日
    this.issueBbPriceHiddenItem = obj.issueBbPriceHiddenItem ? obj.issueBbPriceHiddenItem : '' // 発行価格（hidden項目）
    this.issuePriceType = obj.issuePriceType ? obj.issuePriceType : '' // 発行価格区分（hidden項目）
    this.onlyElectronicDeliveryHiddenItem = obj.onlyElectronicDeliveryHiddenItem ? obj.onlyElectronicDeliveryHiddenItem : null // 電子交付のみ（hidden項目）
    this.deliveryDueDate = obj.deliveryDueDate ? obj.deliveryDueDate : '' // 受渡期日(hidden)
    this.changePriceMsg = obj.changePriceMsg || ''
    this.changePriceFlag = obj.changePriceFlag || ''
    this.changeBbPeriodFlag = obj.changeBbPeriodFlag || ''
    this.changeBbPeriodMsg = obj.changeBbPeriodMsg || ''
  }
}
