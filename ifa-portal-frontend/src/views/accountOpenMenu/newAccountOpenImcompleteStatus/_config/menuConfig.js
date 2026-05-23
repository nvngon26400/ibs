import { shallowRef } from 'vue'

// 新規口座開設不備状況
import IfaNewAccountOpenImcompleteStatus from '@/views/accountOpenMenu/newAccountOpenImcompleteStatus/IfaNewAccountOpenImcompleteStatus'

export const getNewAccountOpenImcompleteStatusMenuConfig = () => {
  return [
    {
      name: 'ifa-new-account-open-imcomplete-status',
      label: '新規口座開設不備状況',
      menuId: 'SUB020305',
      component: shallowRef(IfaNewAccountOpenImcompleteStatus)
    }
  ]
}
