import { createStore } from 'vuex'
import getters from './getters'
import app from './modules/app'
import settings from './modules/settings'
import user from './modules/user'
import page from './modules/page'
import customerPortalMenuList from './modules/customerPotalMenu'
import createPersistedState from 'vuex-persistedstate'
import code from './modules/code'
import SecureLS from '@cbcheng/secure-ls'
import notifications from './modules/notifications.js'

const VUE_APP_VUEX_ENCRYPTION_ENABLED = process.env.VUE_APP_VUEX_ENCRYPTION_ENABLED === 'true'
const VUE_APP_VUEX_ENCRYPTION_TYPE = process.env.VUE_APP_VUEX_ENCRYPTION_TYPE
const VUE_APP_VUEX_ENCRYPTION_SECRET = process.env.VUE_APP_VUEX_ENCRYPTION_SECRET

function getStorage() {
  // process.env から .env.{環境(develpment/production/staging)} に定義した値を読み込む
  if (VUE_APP_VUEX_ENCRYPTION_ENABLED) {
    const ls = new SecureLS({
      storageType: 'sessionStorage',
      encodingType: VUE_APP_VUEX_ENCRYPTION_TYPE,
      isCompression: false,
      encryptionSecret: VUE_APP_VUEX_ENCRYPTION_SECRET
    })
    return {
      getItem: key => ls.get(key),
      setItem: (key, value) => ls.set(key, value),
      removeItem: key => ls.remove(key),
      clear: () => ls.clear()
    }
  } else {
    return window.sessionStorage
  }
}

const store = createStore({
  modules: {
    app,
    settings,
    user,
    page,
    customerPortalMenuList,
    code,
    notifications
  },
  getters,
  plugins: [createPersistedState({ storage: getStorage() })]
})

export default store
