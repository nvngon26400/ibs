import { shallowRef } from 'vue'

// Navbar > リリースノート
import IfaReleaseNote from '@/views/releaseNote/IfaReleaseNote'
export const getIfaReleaseNoteMenuConfig = () => {
  return [
    {
      name: 'ifa-release-note',
      label: 'リリースノート',
      menuId: 'SUB00-07_1',
      component: shallowRef(IfaReleaseNote)
    }
  ]
}
