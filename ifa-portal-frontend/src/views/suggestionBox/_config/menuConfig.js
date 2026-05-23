import { shallowRef } from 'vue'

// Navbar > 目安箱
import IfaSuggestionBoxPersonal from '@/views/suggestionBox/suggestionBoxPersonal/IfaSuggestionBoxPersonal'
import IfaSuggestionBoxCommon from '@/views/suggestionBox/suggestionBoxCommon/IfaSuggestionBoxCommon'

export const getSuggestionBoxMenuBrokerConfig = () => {
  return [
    {
      name: 'ifa-suggestion-box-personal',
      label: 'あなたの要望',
      menuId: 'SUB00_01',
      component: shallowRef(IfaSuggestionBoxPersonal)
    },
    {
      name: 'ifa-suggestion-box-common',
      label: '皆様からの要望',
      menuId: 'SUB00_02',
      component: shallowRef(IfaSuggestionBoxCommon)
    }
  ]
}
