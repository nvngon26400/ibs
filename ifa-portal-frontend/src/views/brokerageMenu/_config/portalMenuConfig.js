import { shallowRef } from 'vue'
import IfaPortfolio from '@/views/brokerageMenu/customerMenu/accountManage/assetsStatus/IfaPortfolio' // 資産状況
import IfaBuyingPowerDomestic from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/IfaBuyingPowerDomestic.vue' // 買付余力
import IfaBuyingPowerForeign from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/IfaBuyingPowerForeign' // 買付余力(外国・現物)
import IfaMarginPowerForeign from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/IfaMarginPowerForeign' // 信用余力（米株）
import IfaPriceViewLookupForeignStockBrandList from '@/views/brokerageMenu/customerMenu/foreignStock/counterTrade/IfaPriceViewLookupForeignStockBrandList' // 外国株式店頭注文入力
import IfaMarginPowerDomestic from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/IfaMarginPowerDomestic' // 信用余力サマリー
import IfaDomesticMutualFundBuyAbleList from '@/views/brokerageMenu/customerMenu/investmentTrust/domesticMutualFund/IfaDomesticMutualFundBuyAbleList' // 国内投信
import IfaDomesticStockOrderInput from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/IfaDomesticStockOrderInput' // 国内株式注文入力
import IfaForeignSpotTradeOrderInput from '@/views/brokerageMenu/customerMenu/foreignStock/spotTrade/IfaForeignSpotTradeOrderInput' // 外国現物取引注文入力
import IfaDomesticTradeStatusList from '@/views/brokerageMenu/customerMenu/accountManage/tradeStatus/IfaDomesticTradeStatusList' // 当日約定一覧
import IfaFxTradeOrderLookup from '@/views/brokerageMenu/customerMenu/fxTrade/orderLookup/IfaFxTradeOrderLookup' // 為替取引注文照会
import IfaCurrencyDealtList from '@/views/brokerageMenu/customerMenu/fxTrade/fxTrade/IfaCurrencyDealtList' // 為替取引
import IfaIfaCurrencyDealtList from '@/views/brokerageMenu/customerMenu/fxTrade/ifaFxTrade/IfaIfaCurrencyDealtList.vue' // IFA専用為替取引
import IfaCreditOrderWrapper from '@/views/brokerageMenu/customerMenu/transaction/newOrderInput/IfaCreditOrderWrapper' // 信用取引
import IfaForeignStockCreditWrapper from '@/views/brokerageMenu/customerMenu/transaction/newOrderInput/IfaForeignStockCreditWrapper' // 外国信用取引
import IfaForeignMarginDepositTransferInput from '@/views/brokerageMenu/customerMenu/foreignStock/depositTransfer/IfaForeignMarginDepositTransferInput' // 保証金振替入力
import IfaDomesticPositionList from '@/views/brokerageMenu/customerMenu/accountManage/balanceInfo/IfaDomesticPositionList' // 国内建玉一覧 / 旧：建玉一覧サマリー
import IfaForeignPositionList from '@/views/brokerageMenu/customerMenu/accountManage/balanceInfo/IfaForeignPositionList' // 米株建玉一覧
import IfaHoldingSecurityList from '@/views/brokerageMenu/customerMenu/accountManage/balanceInfo/IfaHoldingSecurityList' // 保有商品一覧
import IfaCollateralSecurityList from '@/views/brokerageMenu/customerMenu/accountManage/balanceInfo/IfaCollateralSecurityList' // 代用有価証券一覧
import IfaOrderStatusList from '@/views/brokerageMenu/customerMenu/accountManage/orderStatus/IfaOrderStatusList' // 未約定注文一覧
import IfaForeignMarginCollateralTransferInput from '@/views/brokerageMenu/customerMenu/foreignStock/collateralTransfer/IfaForeignMarginCollateralTransferInput' // 外国信用代用振替入力
import IfaForeignMarginAutoTransferSettingInput from '@/views/brokerageMenu/customerMenu/foreignStock/autoTransferSetting/IfaForeignMarginAutoTransferSettingInput' // 米株信用自動振替設定入力
import IfaWithdrawInput from '@/views/brokerageMenu/customerMenu/withdraw/IfaWithdrawInput' // 出金入力／出金明細一覧
import IfaCustomerTradeHistory from '@/views/brokerageMenu/customerMenu/accountManage/customerTradeHistory/IfaCustomerTradeHistory' // 取引履歴（顧客別）
import IfaContact from '@/views/brokerageMenu/customerMenu/accountManage/contact/IfaContact' // 接触履歴
import IfaOtherRemainPowerRestrainInput from '@/views/brokerageMenu/customerMenu/accountManage/otherRemainPowerRestrain/IfaOtherRemainPowerRestrainInput' // その他余力拘束
import IfaRegisterInfo from '@/views/brokerageMenu/customerMenu/customerManage/registerInfo/IfaRegisterInfo' // 登録情報
import IfaExternalLink from '@/views/brokerageMenu/customerMenu/externalLink/IfaExternalLink' // 外部リンク
import IfaCcsLink from '@/views/common/ccsLink/IfaCcsLink'
import IfaSendReceiveStatusLookUp from '@/views/brokerageMenu/customerMenu/customerManage/sendReceiveStatusLookUp'
import IfaDocRequestInput from '@/views/brokerageMenu/customerMenu/docRequest/IfaDocRequestInput' // 書類請求入力
import IfaMutualFundAccumulate from '@/views/brokerageMenu/customerMenu/investmentTrust/mutualFundAccumulate'

export const getPortalMenuConfig = function() {
  return [
    {
      name: 'account-management',
      label: '口座管理',
      menuId: 'SUB0202_01',
      menuItems: [
        {
          label: '残高情報',
          id: 'balance-information',
          menuId: 'SUB0202_0102',
          children: [
            {
              label: '保有商品一覧',
              id: 'ifa-holding-security-list',
              menuId: 'SUB0202_010201',
              component: shallowRef(IfaHoldingSecurityList)
            },
            {
              label: '国内建玉一覧', // 旧：建玉一覧サマリー
              id: 'ifa-domestic-position-list',
              menuId: 'SUB0202_010202',
              component: shallowRef(IfaDomesticPositionList)
            },
            {
              label: '米株建玉一覧',
              id: 'ifa-foreign-position-list',
              menuId: 'SUB0202_010203',
              component: shallowRef(IfaForeignPositionList)
            },
            {
              label: '代用有価証券一覧',
              id: 'ifa-collateral-security-list',
              menuId: 'SUB0202_010204',
              component: shallowRef(IfaCollateralSecurityList)
            }
          ]
        },
        {
          label: '資産状況',
          id: 'ifa-portfolio',
          menuId: 'SUB0202_0101',
          component: shallowRef(IfaPortfolio)
        },
        {
          label: '余力情報',
          id: 'margin-information',
          menuId: 'SUB0202_0103',
          children: [
            {
              label: '買付余力（国内）',
              id: 'ifa-buying-power-domestic',
              menuId: 'SUB0202_010301',
              component: shallowRef(IfaBuyingPowerDomestic)
            },
            {
              label: '買付余力（外国）',
              id: 'ifa-buying-power-foreign',
              menuId: 'SUB0202_010303',
              component: shallowRef(IfaBuyingPowerForeign)
            },
            {
              label: '信用余力（国内）',
              id: 'ifa-margin-power-domestic',
              menuId: 'SUB0202_010302',
              component: shallowRef(IfaMarginPowerDomestic)
            },
            {
              label: '信用余力（米株）',
              id: 'ifa-margin-power-foreign',
              menuId: 'SUB0202_010304',
              component: shallowRef(IfaMarginPowerForeign)
            }
          ]
        },
        {
          label: '注文状況',
          id: 'ifa-order-status-list',
          menuId: 'SUB0202_0104',
          component: shallowRef(IfaOrderStatusList)
        },
        {
          label: '国内株当日約定状況（一覧）',
          id: 'Ifa-domestic-trade-status-list',
          menuId: 'SUB0202_0105',
          component: shallowRef(IfaDomesticTradeStatusList)
        },
        {
          label: '外貨入出金',
          id: 'ifa-foreign-currency-deposit-withdraw',
          menuId: 'SUB0202_0108',
          component: shallowRef(IfaCcsLink)
        },
        {
          label: '取引履歴',
          id: 'ifa-customer-trade-history',
          menuId: 'SUB0202_0109',
          component: shallowRef(IfaCustomerTradeHistory)
        },
        {
          label: '接触履歴',
          id: 'ifa-contact',
          menuId: 'SUB0202_0106',
          component: shallowRef(IfaContact)
        },
        {
          label: '問合せ',
          id: 'ifa-inquiry',
          menuId: 'SUB0202_0107',
          component: shallowRef(IfaCcsLink)
        },
        {
          label: 'その他余力拘束',
          id: 'ifa-other-remain-power-restrain',
          menuId: 'SUB0202_0110',
          component: shallowRef(IfaOtherRemainPowerRestrainInput)
        }
      ]
    },
    {
      name: 'domestic-stock-order',
      label: '国内株式',
      menuId: 'SUB0202_02',
      menuItems: [
        {
          label: '現物取引',
          id: 'ifa-domestic-stock-order-input',
          menuId: 'SUB0202_0208',
          component: shallowRef(IfaDomesticStockOrderInput)
        },
        {
          label: '立会外分売',
          id: 'ifa-off-floor-distribution',
          menuId: 'SUB0202_0209',
          component: shallowRef(IfaCcsLink)
        },
        {
          label: '立会外TRD',
          id: 'ifa-off-floor-trd',
          menuId: 'SUB0202_0210',
          component: shallowRef(IfaCcsLink)
        },
        {
          label: '単元未満',
          id: 'ifa-fraction-stock',
          menuId: 'SUB0202_0211',
          component: shallowRef(IfaCcsLink)
        },
        {
          label: '信用取引',
          id: 'ifa-credit-order-wrapper',
          menuId: 'SUB0202_0212',
          component: shallowRef(IfaCreditOrderWrapper)
        }
      ]
    },
    {
      name: 'foreign-stocks',
      label: '外国株式',
      menuId: 'SUB0202_03',
      menuItems: [
        {
          label: '現物取引',
          id: 'ifa-spot-transaction-order',
          menuId: 'SUB0202_0301',
          component: shallowRef(IfaForeignSpotTradeOrderInput)
        },
        {
          label: '米株店頭',
          id: 'ifa-price-view-lookup-foreign-stock-brand-list',
          menuId: 'SUB0202_0302',
          component: shallowRef(IfaPriceViewLookupForeignStockBrandList)
        },
        {
          label: '信用取引',
          id: 'ifa-foreign-stock-credit-wrapper',
          menuId: 'SUB0202_0303',
          component: shallowRef(IfaForeignStockCreditWrapper)
        },
        {
          label: '保証金振替',
          id: 'ifa-foreign-margin-deposit-transfer-input',
          menuId: 'SUB0202_0304',
          component: shallowRef(IfaForeignMarginDepositTransferInput)
        },
        {
          label: '代用振替',
          id: 'ifa-foreign-margin-collateral-transfer-input',
          menuId: 'SUB0202_0305',
          component: shallowRef(IfaForeignMarginCollateralTransferInput)
        },
        {
          label: '自動振替設定',
          id: 'ifa-foreign-margin-auto-transfer-setting-input',
          menuId: 'SUB0202_0306',
          component: shallowRef(IfaForeignMarginAutoTransferSettingInput)
        }
      ]
    },
    {
      name: 'investment-trust',
      label: '投資信託',
      menuId: 'SUB0202_04',
      menuItems: [
        {
          label: '国内投信',
          id: 'ifa-domestic-mutual-fund-buy-ableList',
          menuId: 'SUB0202_0401',
          component: shallowRef(IfaDomesticMutualFundBuyAbleList)
        },
        {
          label: '定期積立',
          id: 'ifa-mutual-fund-accumulate',
          menuId: 'SUB0202_0403',
          component: shallowRef(IfaMutualFundAccumulate)
        },
        {
          label: '外貨建MMF',
          id: 'ifa-foreign-currency-mmf',
          menuId: 'SUB0202_0404',
          component: shallowRef(IfaCcsLink)
        }
      ]
    },
    {
      name: 'foreign-exchange-transactions',
      label: '為替取引',
      menuId: 'SUB0202_05',
      menuItems: [
        {
          label: '為替取引',
          id: 'ifa-currency-dealt-list',
          menuId: 'SUB0202_0502',
          component: shallowRef(IfaCurrencyDealtList)
        },
        {
          label: '【IFA】為替取引',
          id: 'ifa-ifa-currency-dealt-list',
          menuId: 'SUB0202_0503',
          component: shallowRef(IfaIfaCurrencyDealtList)
        },
        {
          label: '注文照会',
          id: 'ifa-fx-trade-order-lookup',
          menuId: 'SUB0202_0501',
          component: shallowRef(IfaFxTradeOrderLookup)
        }
      ]
    },
    {
      name: 'withdraw',
      label: '出金',
      menuId: 'SUB0202_06',
      menuItems: [
        {
          label: '出金',
          id: 'ifa-withdraw',
          menuId: 'SUB0202_0601',
          component: shallowRef(IfaWithdrawInput)
        }
      ]
    },
    {
      name: 'customer-manage',
      label: '顧客管理',
      menuId: 'SUB0202_07',
      menuItems: [
        {
          label: '登録情報',
          id: 'ifa-register-info',
          menuId: 'SUB0202_0701',
          component: shallowRef(IfaRegisterInfo)
        },
        {
          label: '受発信状況',
          id: 'ifa-send-receive-status',
          menuId: 'SUB0202_0703',
          component: shallowRef(IfaSendReceiveStatusLookUp)
        },
        {
          label: '書類請求',
          id: 'ifa-doc-request',
          menuId: 'SUB0202_0704',
          component: shallowRef(IfaDocRequestInput)
        }
      ]
    },
    {
      name: 'external-link',
      label: '外部リンク',
      menuId: 'SUB0202_08',
      menuItems: [
        {
          label: '外部リンク',
          id: 'ifa-external-link',
          menuId: 'SUB0202_08',
          component: shallowRef(IfaExternalLink)
        }
      ]
    },
    // 顧客属性
    {
      name: 'ccs-link',
      label: 'CCS',
      menuId: 'SUB0202_09',
      menuItems: [
        {
          label: 'CCS',
          id: 'ifa-customer-profile',
          menuId: 'SUB0202_09',
          component: shallowRef(IfaCcsLink)
        }
      ]
    }
  ]
}

export const getInitializeErrorConfig = function() {
  return [
    {
      id: 'ifa-initialize-error',
      component: 'IfaInitializeError',
      props: {
        errorInfo: this.getErrorInfo
      },
      handlers: { }
    }
  ]
}
