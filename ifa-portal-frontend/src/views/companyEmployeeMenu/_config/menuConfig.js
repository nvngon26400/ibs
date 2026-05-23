import { shallowRef } from 'vue'

// 社員用メニュー > Information登録
import IfaInfoRegisterLookup from '@/views/companyEmployeeMenu/infoRegister/infoRegister/IfaInfoRegisterLookup'

export const getInformationRegistrationMenuConfig = () => {
  return [
    {
      name: 'ifa-info-register-lookup',
      label: 'インフォメーション登録',
      menuId: 'SUB0501_01',
      component: shallowRef(IfaInfoRegisterLookup)
    }
  ]
}

// 社員用メニュー > コンプライアンス通信
import IfaComplianceReportInfoRegisterManager from '@/views/companyEmployeeMenu/complianceReport/ComplianceReportInfoRegister/IfaComplianceReportInfoRegisterManager'
import IfaComplianceReportViewStatusLookupManager from '@/views/companyEmployeeMenu/complianceReport/complianceReportViewStatusLookup/IfaComplianceReportViewStatusLookupManager'
import IfaComplianceReportBrokerBlockViewExcludeSetting from '@/views/companyEmployeeMenu/complianceReport/complianceReportBrokerBlockViewExcludeSetting/IfaComplianceReportBrokerBlockViewExcludeSetting'

export const getComplianceInfoForAdministratorMenuConfig = () => {
  return [
    {
      name: 'ifa-compliance-report-info-register-manager',
      label: 'コンプライアンス通信情報登録',
      menuId: 'SUB0505_01',
      component: shallowRef(IfaComplianceReportInfoRegisterManager)
    },
    {
      name: 'ifa-compliance-report-view-status-lookup-manager',
      label: 'コンプライアンス通信閲覧状況照会',
      menuId: 'SUB0505_02',
      component: shallowRef(IfaComplianceReportViewStatusLookupManager)
    },
    {
      name: 'ifa-compliance-report-broker-block-view-exclude-setting',
      label: 'コンプライアンス通信仲介業者一括閲覧不要設定',
      menuId: 'SUB0505_03',
      component: shallowRef(IfaComplianceReportBrokerBlockViewExcludeSetting)
    }
  ]
}

// 社員用メニュー > 自己点検
import IfaSelfInspectBlotterConfirmManager from '@/views/companyEmployeeMenu/selfInspect/selfInspectBlotterConfirm/IfaSelfInspectBlotterConfirmManager'
import IfaSelfInspectItemManage from '@/views/companyEmployeeMenu/selfInspect/selfInspectItemManage/IfaSelfInspectItemManage'

export const getSelfCheckMenuConfig = () => {
  return [
    {
      name: 'ifa-self-inspect-blotter-confirm-manager',
      label: '自己点検記録簿確認',
      menuId: 'SUB0506_01',
      component: shallowRef(IfaSelfInspectBlotterConfirmManager)
    },
    {
      name: 'ifa-self-inspect-item-manage',
      label: '自己点検項目管理',
      menuId: 'SUB0506_02',
      component: shallowRef(IfaSelfInspectItemManage)
    }
  ]
}

// 社員用メニュー > 目安箱
import IfaSuggestionBoxPersonal from '@/views/suggestionBox/suggestionBoxPersonal/IfaSuggestionBoxPersonal'
import IfaSuggestionBoxCommon from '@/views/suggestionBox/suggestionBoxCommon/IfaSuggestionBoxCommon'

export const getSuggestionBoxMenuEmpConfig = () => {
  return [
    {
      name: 'ifa-suggestion-box-personal',
      label: '仲介業者からの要望確認',
      menuId: 'SUB0511_01',
      component: shallowRef(IfaSuggestionBoxPersonal)
    },
    {
      name: 'ifa-suggestion-box-common',
      label: '皆様からの要望',
      menuId: 'SUB0511_02',
      component: shallowRef(IfaSuggestionBoxCommon)
    }
  ]
}

// 社員用メニュー > 共同募集
import IfaJointContractMaster from '@/views/companyEmployeeMenu/jointContract/jointContract/IfaJointContractMaster'

export const getJointContractMasterConfig = () => {
  return [
    {
      name: 'ifa-joint-contract-master',
      label: '共同募集契約設定',
      menuId: 'SUB0513_01',
      component: shallowRef(IfaJointContractMaster)
    }
  ]
}

// 社員用メニュー > リリースノート
import IfaReleaseNoteEmployee from '@/views/companyEmployeeMenu/releaseNote/IfaReleaseNoteEmployee'

export const getIfaReleaseNoteEmployeeMenuConfig = () => {
  return [
    {
      name: 'ifa-release-note-employee',
      label: 'リリースノート(社員用)',
      menuId: 'SUB0512-01',
      component: shallowRef(IfaReleaseNoteEmployee)
    }
  ]
}

// 社員用メニュー > 取引データ管理
import IfaForeignBondBuyAmountInputList from '@/views/companyEmployeeMenu/tradeDataManage/foreignBondBuyAmountInput/IfaForeignBondBuyAmountInputList'
import IfaEdelivAgreementDataRegister from '@/views/companyEmployeeMenu/tradeDataManage/edelivAgreementDataRegister/IfaEdelivAgreementDataRegister'

export const getTradeDataManageMenuConfig = () => {
  return [
    {
      name: 'ifa-edeliv-agreement-data-register',
      label: '電子交付同意データ登録',
      menuId: 'SUB0504_02',
      component: shallowRef(IfaEdelivAgreementDataRegister)
    },
    {
      name: 'ifa-foreign-bond-buy-amount-input-list',
      label: '外債買付代金入力一覧',
      menuId: 'SUB0504_04',
      component: shallowRef(IfaForeignBondBuyAmountInputList)
    }
  ]
}
