import { shallowRef } from 'vue'

// システム管理メニュー > ログイン者管理
import IfaLoginUserManageManagerLookup from '@/views/systemManageMenu/loginUserManage/loginUserManage/IfaLoginUserManageManagerLookup'

export const getLoginUserManagementMenuConfig = () => {
  return [
    {
      name: 'ifa-login-user-manage-manager-lookup',
      label: 'ログイン者管理',
      menuId: 'SUB0601_01',
      component: shallowRef(IfaLoginUserManageManagerLookup)
    }
  ]
}

// システム管理メニュー > SBI証券からのご連絡登録
import IfaPortalNotificationManagerLookup from '@/views/systemManageMenu/sbiSecurityNotificationRegister/IfaPortalNotificationManagerLookup'

export const getPortalNoticeMenuConfig = () => {
  return [
    {
      name: 'ifa-portal-notification-manager-lookup',
      label: 'SBI証券からのご連絡一覧',
      menuId: 'SUB0602-01',
      component: shallowRef(IfaPortalNotificationManagerLookup)
    }
  ]
}
