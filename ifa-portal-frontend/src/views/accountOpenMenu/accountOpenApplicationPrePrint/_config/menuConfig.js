import { shallowRef } from 'vue'

// 口座開設申込書プレ印字 (CCS-SSO)
import IfaCcsLink from '@/views/common/ccsLink/IfaCcsLink'

export const getAccountOpenApplicationPrePrintMenuConfig = () => {
  return [
    {
      name: 'ifa-account-open-application-pre-print',
      label: '口座開設申込書プレ印字',
      menuId: 'SUB0207',
      component: shallowRef(IfaCcsLink)
    }
  ]
}
