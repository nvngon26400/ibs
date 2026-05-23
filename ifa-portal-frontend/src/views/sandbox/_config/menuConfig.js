import { shallowRef } from 'vue'

// ※共通部品テスト※
import IfaInputTest from '@/views/sandbox/IfaInputTest'
import IfaInputDateTest from '@/views/sandbox/IfaInputDateTest'
import IfaCompositePartsTest from '@/views/sandbox/IfaCompositePartsTest'
import IfaButtonTest from '@/views/sandbox/IfaButtonTest'
import IfaGridTest from '@/views/sandbox/IfaGridTest'

export const getSandboxConfig = () => {
  return [
    {
      name: 'ifa-input-test',
      label: '入力テスト',
      menuId: 'DBG01-01',
      component: shallowRef(IfaInputTest)
    },
    {
      name: 'ifa-input-date-test',
      label: '日時テスト',
      menuId: 'DBG01-02',
      component: shallowRef(IfaInputDateTest)
    },
    {
      name: 'ifa-button-test',
      label: 'ボタンテスト',
      menuId: 'DBG01-03',
      component: shallowRef(IfaButtonTest)
    },
    {
      name: 'ifa-composite-parts-test',
      label: 'コンポジット部品テスト',
      menuId: 'DBG01-04',
      component: shallowRef(IfaCompositePartsTest)
    },
    {
      name: 'ifa-grid-test',
      label: 'グリッドテスト',
      menuId: 'DBG01-05',
      component: shallowRef(IfaGridTest)
    }
  ]
}
