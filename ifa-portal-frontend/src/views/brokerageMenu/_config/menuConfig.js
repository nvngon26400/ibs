import { shallowRef } from 'vue'

// 仲介業メニュー > 顧客一覧
import IfaCustomerList from '@/views/brokerageMenu/customerList/customerList/IfaCustomerList.vue'
import IfaCustomerListFuturesOptions from '@/views/brokerageMenu/customerList/customerListFuturesOptions/IfaCustomerListFuturesOptions.vue'
import IfaCustomerListMargin from '@/views/brokerageMenu/customerList/customerListMargin/IfaCustomerListMargin.vue'

export const getCustomerListMenuConfig = () => {
  return [
    {
      name: 'ifa-customer-list',
      label: '顧客一覧',
      menuId: 'SUB0201_01',
      component: shallowRef(IfaCustomerList)
    },
    {
      name: 'ifa-customer-list-margin',
      label: '顧客一覧・信用',
      menuId: 'SUB0201_02',
      component: shallowRef(IfaCustomerListMargin)
    },
    {
      name: 'ifa-customer-list-futures-options',
      label: '顧客一覧・先OP',
      menuId: 'SUB0201_03',
      component: shallowRef(IfaCustomerListFuturesOptions)
    }
  ]
}

// 仲介業メニュー > 全顧客メニュー > アラート情報
import IfaJpyAmountUnpaidOverdraftAlertList from '@/views/brokerageMenu/wholeCustomer/alertInfo/unpaidMarginCollateralDeficientAlert/IfaJpyAmountUnpaidOverdraftAlertList'
import IfaDomesticMarginCollateralDeficientAlertList from '@/views/brokerageMenu/wholeCustomer/alertInfo/unpaidMarginCollateralDeficientAlert/IfaDomesticMarginCollateralDeficientAlertList'
import IfaForeignAmountUnpaidOverdraftAlertList from '@/views/brokerageMenu/wholeCustomer/alertInfo/unpaidMarginCollateralDeficientAlert/IfaForeignAmountUnpaidOverdraftAlertList.vue'
import IfaForeignMarginCollateralDeficientAlertList from '@/views/brokerageMenu/wholeCustomer/alertInfo/unpaidMarginCollateralDeficientAlert/IfaForeignMarginCollateralDeficientAlertList'
import IfaDomesticMarginPositionDueDateAlert from '@/views/brokerageMenu/wholeCustomer/alertInfo/dueDateAlert/IfaDomesticMarginPositionDueDateAlert'
import IfaForeignMarginPositionDueDateAlert from '@/views/brokerageMenu/wholeCustomer/alertInfo/dueDateAlert/IfaForeignMarginPositionDueDateAlert'
import IfaMutualFundPriceChangeBrandHoldingList from '@/views/brokerageMenu/wholeCustomer/alertInfo/brandDateAlert/IfaMutualFundPriceChangeBrandHoldingList'
import IfaKnockInBrandHoldingList from '@/views/brokerageMenu/wholeCustomer/alertInfo/brandDateAlert/IfaKnockInBrandHoldingList'
import IfaKnockOutBrandHoldingList from '@/views/brokerageMenu/wholeCustomer/alertInfo/brandDateAlert/IfaKnockOutBrandHoldingList'

export const getAlartInfomationMenuConfig = () => {
  return [
    {
      name: 'spare-capacity-alert',
      label: '未済・信用担保不足アラート',
      menuId: 'SUB020301_01',
      menuItems: [
        {
          label: '円貨未入金・赤残アラート一覧',
          id: 'ifa-jpy-amount-unpaid-overdraft-alert-list',
          menuId: 'SUB020301_01-01',
          component: shallowRef(IfaJpyAmountUnpaidOverdraftAlertList)
        },
        {
          isItem: true,
          label: '国内信用担保不足アラート一覧',
          id: 'ifa-domestic-margin-collateral-deficient-alert-list',
          menuId: 'SUB020301_01-02',
          component: shallowRef(IfaDomesticMarginCollateralDeficientAlertList)
        },
        {
          isItem: true,
          label: '外貨未入金・赤残アラート一覧',
          id: 'ifa-foreign-amount-unpaid-overdraft-alert-list',
          menuId: 'SUB020301_01-03',
          component: shallowRef(IfaForeignAmountUnpaidOverdraftAlertList)
        },
        {
          isItem: true,
          label: '米株信用担保不足アラート一覧',
          id: 'ifa-foreign-margin-collateral-deficient-alert-list',
          menuId: 'SUB020301_01-04',
          component: shallowRef(IfaForeignMarginCollateralDeficientAlertList)
        }
      ]
    },
    {
      name: 'due-date-alert',
      label: '期日アラート',
      menuId: 'SUB020301_02',
      menuItems: [
        {
          label: '国内信用建玉期日アラート一覧',
          id: 'ifa-domestic-margin-position-due-date-alert',
          menuId: 'SUB020301_02-01',
          component: shallowRef(IfaDomesticMarginPositionDueDateAlert)
        },
        {
          label: '米株信用建玉期日アラート一覧',
          id: 'ifa-foreign-margin-position-due-date-alert',
          menuId: 'SUB020301_02-02',
          component: shallowRef(IfaForeignMarginPositionDueDateAlert)
        }
      ]
    },
    {
      name: 'stock-alert',
      label: '銘柄アラート',
      menuId: 'SUB020301_03',
      menuItems: [
        {
          label: '投信基準価額変動の銘柄保有一覧',
          id: 'ifa-mutual-fund-price-change-brand-holding-list',
          menuId: 'SUB020301_03-01',
          component: shallowRef(IfaMutualFundPriceChangeBrandHoldingList)
        },
        {
          label: 'ノックイン銘柄保有一覧',
          id: 'ifa-knock-in-brand-holding-list',
          menuId: 'SUB020301_03-02',
          component: shallowRef(IfaKnockInBrandHoldingList)
        },
        {
          label: 'ノックアウト銘柄保有一覧',
          id: 'ifa-knock-out-brand-holding-list',
          menuId: 'SUB020301_03-03',
          component: shallowRef(IfaKnockOutBrandHoldingList)
        }
      ]
    }
  ]
}

// 仲介業メニュー > 全顧客メニュー > 取引状況/残高検索・照会
import IfaOrderList from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/tradeStatus/orderList/IfaOrderList'
import IfaTodayTradeList from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/tradeStatus/todayTradeList/IfaTodayTradeList'
import IfaTradeHistory from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/tradeHistory/tradeHistory/IfaTradeHistory'
import IfaFxTradeHistory from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/tradeHistory/fxTradeHistory/IfaFxTradeHistory'
import IfaCouponRedemptionPaymentScheduleList from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/tradeStatus/withdrawScheduleList/IfaCouponRedemptionPaymentScheduleList'
import IfaDepositWithdrawDetail from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/tradeHistory/depositWithdrawDetail/IfaDepositWithdrawDetail'
import IfaDeliverInOutDetail from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/tradeHistory/depositWithdrawDetail/IfaDeliverInOutDetail'
import IfaMarginPositionListDomestic from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/balanceInfo/marginPositionListDomestic/IfaMarginPositionListDomestic'
import IfaMarginPositionListForeign from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/balanceInfo/marginPositionListForeign/IfaMarginPositionListForeign'
import IfaSecurityCashBalanceLookup from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/balanceInfo/securityCashBalanceLookup/IfaSecurityCashBalanceLookup'
import IfaTradeTrendSearch from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/tradeTrendSearch/tradeTrendSearch/IfaTradeTrendSearch'

export const getTradeStatusAndBalanceMenuConfig = () => {
  return [
    {
      name: 'trading-conditions',
      label: '取引状況',
      menuId: 'SUB020302_01',
      menuItems: [
        {
          label: '注文一覧',
          id: 'ifa-order-list',
          menuId: 'SUB020302_0101',
          component: shallowRef(IfaOrderList)
        },
        {
          label: '国内株当日約定一覧',
          id: 'ifa-today-trade-list',
          menuId: 'SUB020302_0102',
          component: shallowRef(IfaTodayTradeList)
        },
        {
          label: '利金・償還金支払予定一覧',
          id: 'ifa-coupon-redemption-payment-schedule-list',
          menuId: 'SUB020302_0104',
          component: shallowRef(IfaCouponRedemptionPaymentScheduleList)
        }
      ]
    },
    {
      name: 'withdrawals',
      label: '取引履歴',
      menuId: 'SUB020302_02',
      menuItems: [
        {
          label: '取引履歴',
          id: 'ifa-trade-history',
          menuId: 'SUB020302_0201',
          component: shallowRef(IfaTradeHistory)
        },
        {
          label: '為替取引履歴',
          id: 'ifa-fx-trade-history',
          menuId: 'SUB020302_0202',
          component: shallowRef(IfaFxTradeHistory)
        },
        {
          label: '入出金明細',
          id: 'ifa-deposit-withdraw-detail',
          menuId: 'SUB020302_0203',
          component: shallowRef(IfaDepositWithdrawDetail)
        },
        {
          label: '入出庫明細',
          id: 'ifa-deliver-in-out-detail',
          menuId: 'SUB020302_0204',
          component: shallowRef(IfaDeliverInOutDetail)
        }
      ]
    },
    {
      name: 'trade-trend',
      label: '取引動向',
      menuId: 'SUB020302_04',
      menuItems: [
        {
          label: '取引動向',
          id: 'ifa-trade-trend-search',
          menuId: 'SUB020302_0401',
          component: shallowRef(IfaTradeTrendSearch)
        }
      ]
    },
    {
      name: 'balance-information',
      label: '残高情報',
      menuId: 'SUB020302_03',
      menuItems: [
        {
          label: '証券・金銭・残高照会',
          id: 'ifa-security-cash-balance-lookup',
          menuId: 'SUB020302_0301',
          component: shallowRef(IfaSecurityCashBalanceLookup)
        },
        {
          label: '信用建玉一覧（国内）',
          id: 'ifa-margin-position-list-domestic',
          menuId: 'SUB020302_0302',
          component: shallowRef(IfaMarginPositionListDomestic)
        },
        {
          label: '信用建玉一覧（米株）',
          id: 'ifa-margin-position-list-foreign',
          menuId: 'SUB020302_0303',
          component: shallowRef(IfaMarginPositionListForeign)
        }
      ]
    }
  ]
}

// 仲介業メニュー > 全顧客メニュー > 顧客振込先金融機関口座
import IfaCustomerDestinationBankAccount from '@/views/brokerageMenu/wholeCustomer/customerDestinationBankAccount/IfaCustomerDestinationBankAccount'

export const getDrawersAccountMenuConfig = () => {
  return [
    {
      name: 'ifa-customer-destination-bank-account',
      label: '顧客振込先金融機関口座',
      menuId: 'SUB020303',
      component: shallowRef(IfaCustomerDestinationBankAccount)
    }
  ]
}

// 仲介業メニュー > 全顧客メニュー > 接触履歴（入力）検索
import IfaInquirySearchForManager from '@/views/brokerageMenu/wholeCustomer/inquirySearchForManager/IfaInquirySearchForManager'

export const getInquiryMenuConfig = () => {
  return [
    {
      name: 'ifa-inquiry-search-for-manager',
      label: '接触履歴（入力）検索',
      menuId: 'SUB020304',
      component: shallowRef(IfaInquirySearchForManager)
    }
  ]
}

// 仲介業メニュー > IPO/PO
import IfaIpoPoBrandList from '@/views/brokerageMenu/ipoPo/ipoPoBrandList/IfaIpoPoBrandList'
import IfaBbApplyList from '@/views/brokerageMenu/ipoPo/bbApplyList/IfaBbApplyList'

export const getIpoPoMenuConfig = () => {
  return [
    {
      name: 'ifa-ipo-Po-brand-list',
      label: 'IPO/PO銘柄一覧',
      menuId: 'SUB0204_01',
      component: shallowRef(IfaIpoPoBrandList)
    },
    {
      name: 'ifa-bb-apply-list',
      label: 'BB申込一覧',
      menuId: 'SUB0204_02',
      component: shallowRef(IfaBbApplyList)
    }
  ]
}

// 仲介業メニュー > 手数料･報酬 > 担当顧客別手数料一覧
import IfaRepCustomerCommList from '@/views/brokerageMenu/commFee/repCustomerCommList/IfaRepCustomerCommList.vue'

export const getChargesTotalMenuConfig = () => {
  return [
    {
      name: 'ifa-rep-customer-comm-list',
      label: '担当顧客別手数料一覧',
      menuId: 'SUB020501-01',
      component: shallowRef(IfaRepCustomerCommList)
    }
  ]
}

// 仲介業メニュー > 手数料･報酬 > 手数料･報酬
import IfaCommFee from '@/views/brokerageMenu/commFee/commFee/IfaCommFee'

export const getChargeRewardMenuConfig = () => {
  return [
    {
      name: 'ifa-comm-fee',
      label: '手数料・報酬',
      menuId: 'SUB020502-01',
      component: shallowRef(IfaCommFee)
    }
  ]
}

// 仲介業メニュー > 手数料･報酬 > 信託報酬
import IfaTrustFee from '@/views/brokerageMenu/commFee/trustFee/IfaTrustFee'

export const getTrustRewardMenuConfig = () => {
  return [
    {
      name: 'ifa-trust-fee',
      label: '信託報酬',
      menuId: 'SUB020503-01',
      component: shallowRef(IfaTrustFee)
    }
  ]
}

// 仲介業メニュー > 手数料･報酬 > SBIラップ管理報酬
import IfaSbiWrapManageFee from '@/views/brokerageMenu/commFee/sbiWrapManageFee/IfaSbiWrapManageFee'

export const getSbiWrapManageFeeMenuConfig = () => {
  return [
    {
      name: 'ifa-sbi-wrap-manage-fee',
      label: 'SBIラップ管理報酬',
      menuId: 'SUB020504-01',
      component: shallowRef(IfaSbiWrapManageFee)
    }
  ]
}

// 仲介業メニュー > 各種申請・報告
import IfaAllSortsApplyReport from '@/views/brokerageMenu/allSortsApplyReport/IfaAllSortsApplyReport'

export const getAllSortsApplyReportMenuConfig = () => {
  return [
    {
      name: 'ifa-all-sorts-apply-report',
      label: '各種申請・報告',
      menuId: 'SUB0209-99',
      component: shallowRef(IfaAllSortsApplyReport)
    }
  ]
}

// 仲介業メニュー > 手数料･報酬 > レベルフィー
import IfaLevelFee from '@/views/brokerageMenu/commFee/levelFee/IfaLevelFee'

export const getLevelFeeMenuConfig = () => {
  return [
    {
      name: 'ifa-level-fee',
      label: 'レベルフィー',
      menuId: 'SUB020505-01',
      component: shallowRef(IfaLevelFee)
    }
  ]
}

// 仲介業メニュー > 共同募集
import IfaJointSubscriptCustomerManage from '@/views/brokerageMenu/jointSubscript/jointSubscriptCustomerManage/IfaJointSubscriptCustomerManage'
import IfaJointSubscriptTradeSearch from '@/views/brokerageMenu/jointSubscript/jointSubscriptTradeSearch/IfaJointSubscriptTradeList'
import IfaJointSubscriptTrustFee from '@/views/brokerageMenu/jointSubscript/jointSubscriptTrustFee/IfaJointSubscriptTrustFee'
import IfaJointSubscriptSecurityCashBalanceLookup from '@/views/brokerageMenu/jointSubscript/jointSubscriptSecurityCashBalanceLookup/IfaJointSubscriptSecurityCashBalanceLookup'

export const getJointSubscript = () => {
  return [
    {
      name: 'ifa-joint-subscript-customer-manage',
      label: '共同募集　顧客管理',
      menuId: 'SUB0206_01-01',
      component: shallowRef(IfaJointSubscriptCustomerManage)
    },
    {
      name: 'ifa-joint-subscript-trade-search',
      label: '共同募集　取引検索',
      menuId: 'SUB0206_02-01',
      component: shallowRef(IfaJointSubscriptTradeSearch)
    },
    {
      name: 'ifa-joint-subscript-trust-fee',
      label: '共同募集　信託報酬',
      menuId: 'SUB0206_03-01',
      component: shallowRef(IfaJointSubscriptTrustFee)
    },
    {
      name: 'tab-ifa-joint-subscript-security-cash-balance-lookup',
      label: '共同募集　証券・金銭　残高照会',
      menuId: 'SUB0206_04-01',
      component: shallowRef(IfaJointSubscriptSecurityCashBalanceLookup)
    }
  ]
}

// 仲介業メニュー > 共同店舗
import IfaJointMarketTradeSearch from '@/views/brokerageMenu/jointMarket/jointMarketTradeSearch/IfaJointMarketTradeSearch.vue'
import IfaJointMarketTrustFee from '@/views/brokerageMenu/jointMarket/jointMarketTrustFee/IfaJointMarketTrustFee'

export const getJointMarketMenuConfig = () => {
  return [
    {
      name: 'ifa-joint-market-trade-search',
      label: '共同店舗　取引検索',
      menuId: 'SUB0208_01',
      component: shallowRef(IfaJointMarketTradeSearch)
    },
    {
      name: 'ifa-joint-market-trust-fee',
      label: '共同店舗　信託報酬',
      menuId: 'SUB0208_02',
      component: shallowRef(IfaJointMarketTrustFee)
    }
  ]
}
