import { shallowRef } from 'vue'

// コンプライアンス通信 > コンプライアンス通信
import IfaComplianceReport from '@/views/complianceReport/IfaComplianceReport'

export const getComplianceLetterMenuConfig = () => {
  return [
    {
      name: 'ifa-compliance-letter',
      label: 'コンプライアンス通信',
      menuId: 'SUB0302-01',
      component: shallowRef(IfaComplianceReport)
    }
  ]
}
