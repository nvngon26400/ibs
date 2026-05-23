import request from '@/utils/request'
import store from '@/store'

export function getInfo(token) {
  return request({
    url: '/vue-element-admin/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/common/logout',
    method: 'post'
  })
}

export function resetAllStates() {
  store.dispatch('app/resetState')
  store.dispatch('page/resetState')
  store.dispatch('customerPortalMenuList/resetState')
  store.dispatch('settings/resetState')
  store.dispatch('user/resetState')
  store.dispatch('code/resetState')
  store.dispatch('notifications/resetState')
}
