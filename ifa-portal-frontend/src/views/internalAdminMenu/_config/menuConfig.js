import { shallowRef } from 'vue'

// 内部管理責任者メニュー > マンスリー実施項目
import IfaComplianceReportViewStatusLookupInternalAdmin from '@//views/internalAdminMenu/monthlyImplementationItem/complianceReportViewStatusLookup/IfaComplianceReportViewStatusLookupInternalAdmin.vue'
import IfaSelfInspectBlotter from '@/views/internalAdminMenu/monthlyImplementationItem/selfInspectBlotter/IfaSelfInspectBlotter'

export const getMonthlyActionItemsMenuConfig = () => {
  return [
    {
      name: 'ifa-compliance-report-view-status-lookup-internal-admin',
      label: 'コンプライアンス通信閲覧状況照会',
      menuId: 'SUB0401_01',
      component: shallowRef(IfaComplianceReportViewStatusLookupInternalAdmin)
    },
    {
      name: 'ifa-self-inspect-blotter',
      label: '自己点検記録簿',
      menuId: 'SUB0401_02',
      component: shallowRef(IfaSelfInspectBlotter)
    }
  ]
}

// 内部管理責任者メニュー > 帳票取得
import IfaBrokerageSubLedgerAcquire from '@/views/internalAdminMenu/formAcquire/brokerageSubLedgerAcquire/IfaBrokerageSubLedgerAcquire.vue'
import IfaContractNoteCustomerLedgerAcquire from '@/views/internalAdminMenu/formAcquire/contractNoteCustomerLedgerAcquire/IfaContractNoteCustomerLedgerAcquire.vue'

export const getFormAcquisitionMenuConfig = () => {
  return [
    {
      name: 'ifa-brokerage-sub-ledger-acquire',
      label: '仲介業補助簿取得',
      menuId: 'SUB0402_01',
      component: shallowRef(IfaBrokerageSubLedgerAcquire)
    },
    {
      name: 'ifa-contract-note-customer-ledger-acquire',
      label: '取引日記帳・顧客勘定元帳取得',
      menuId: 'SUB0402_02',
      component: shallowRef(IfaContractNoteCustomerLedgerAcquire)
    }
  ]
}

// 内部管理責任者メニュー > 年度別口座数・報酬額照会
import IfaByYearAccountQuantityFeeAmountLookup from '@/views/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/IfaByYearAccountQuantityFeeAmountLookup.vue'

export const getByYearAccountQuantityFeeAmountLookupConfig = () => {
  return [
    {
      name: 'ifa-by-year-account-quantity-fee-amount-lookup',
      label: '年度別口座数・報酬額照会',
      menuId: 'SUB0406-01',
      component: shallowRef(IfaByYearAccountQuantityFeeAmountLookup)
    }
  ]
}

// 内部管理責任者メニュー > 個人情報管理
import IfaPersonalInfoManageLedgerList from '@/views/internalAdminMenu/personalInfoManage/IfaPersonalInfoManageLedgerList'

export const getManagePersonalyLedgerMenuConfig = () => {
  return [
    {
      name: 'ifa-personal-info-manage-ledger-list',
      label: '個人情報管理台帳一覧',
      menuId: 'SUB0403-01',
      component: shallowRef(IfaPersonalInfoManageLedgerList)
    }
  ]
}

// 内部管理責任者メニュー > 個人情報管理
import IfaAuthMailAddressChangeList from '@/views/internalAdminMenu/authMailAddressChange/IfaAuthMailAddressChangeList.vue'

export const getVerifyMailChangeMenuConfig = () => {
  return [
    {
      name: 'ifa-auth-mail-address-change-list',
      label: '認証用メールアドレス一覧',
      menuId: 'SUB0404-01',
      component: shallowRef(IfaAuthMailAddressChangeList)
    }
  ]
}

// 内部管理責任者メニュー > 支払通知書ダウンロード
import IfaPayNotificationDocDownload from '@/views/internalAdminMenu/payNotificationDocDownload/IfaPayNotificationDocDownload.vue'

export const getFormPayNotificationDocDownloadMenuConfig = () => {
  return [
    {
      name: 'tab-ifa-pay-notification-doc-download',
      label: '支払通知書ダウンロード',
      menuId: 'SUB0405-01',
      component: shallowRef(IfaPayNotificationDocDownload)
    }
  ]
}

// 内部管理責任者メニュー > 各種申請・報告(管理者向け)
import IfaComplianceRelatedReport from '@/views/internalAdminMenu/complianceRelatedReport/IfaComplianceRelatedReport'

export const getComplianceRelatedReportMenuConfig = () => {
  return [
    {
      name: 'ifa-compliance-related-report',
      label: '各種申請・報告(管理者向け)',
      menuId: 'SUB0408-99',
      component: shallowRef(IfaComplianceRelatedReport)
    }
  ]
}

// 内部管理責任者メニュー > 月末口座数
import IfaMonthCustomerNum from '@/views/internalAdminMenu/monthCustomerNum/IfaMonthCustomerNum.vue'

export const getIfaMonthCustomerNumMenuConfig = () => {
  return [
    {
      name: 'tab-ifa-month-customer-num',
      label: '月末口座数',
      menuId: 'SUB0407_01',
      component: shallowRef(IfaMonthCustomerNum)
    }
  ]
}
