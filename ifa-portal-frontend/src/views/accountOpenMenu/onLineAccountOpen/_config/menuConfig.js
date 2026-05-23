import { shallowRef } from 'vue'

// オンライン口座開設
import IfaOnLineAccountOpen from '@/views/accountOpenMenu/onLineAccountOpen/IfaOnLineAccountOpen'

export const getOnLineAccountOpenMenuConfig = () => {
  return [
    {
      name: 'ifa-on-line-account-open',
      label: 'オンライン口座開設',
      menuId: 'SUB0207_0201',
      component: shallowRef(IfaOnLineAccountOpen)
    }
  ]
}
