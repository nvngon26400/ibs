import { routingTable } from './routingTable.js'
import store from '@/store'
import Logger from '@/utils/ifaLog.js'

let routing = false

function start(self, menuId, params) {
  if (routing) {
    Logger.warn('Warning: previous routing process does not finished.')
  }
  routing = true
  return new Promise(resolve => {
    // 画面IDからテーブル検索
    const table = routingTable.bind(this)
    const menuItem = table().find(item => item.menuId === menuId)
    if (!menuItem) {
      // 一致する画面IDが見つからない場合はエラー
      routing = false
      resolve({ msg: 'no entry.' })
      return
    }

    // Vue router で画面を遷移させる｡onAbort時はエラー
    const query = Object.assign({}, self.$route.query)
    query.menuId = menuItem.menuId
    query.url = menuItem.url
    query.target = menuItem.target
    store.commit('page/setRoute', params)
    self.$router.push({
      path: `/to`,
      query
    },
    // onComplete
    () => {
      resolve({ msg: '' })
    },
    // onAbort
    () => {
      resolve({ msg: 'Transition failed.' })
    })
  })
}

function isRooting() {
  return routing
}

function getParams(self) {
  return routing ? self.$store.getters.route.params : self.$store.getters.pageInfo.params
}

function commitPageInfo(self, info) {
  let pageInfo = {}
  if (info) {
    pageInfo = {
      menuId: info.menuId,
      label: info.label,
      componentName: info.componentName,
      target: info.target
    }
    if (routing && self.$store.getters.route.params) {
      pageInfo.params = { ...self.$store.getters.route.params }
    }
  }
  store.commit('page/setPageInfo', pageInfo)
  delete self.$route.query.menuId
  delete self.$route.query.url
  delete self.$route.query.target
  store.commit('page/setRoute', {})
  routing = false
}

export default {
  start,
  isRooting,
  getParams,
  commitPageInfo
}
