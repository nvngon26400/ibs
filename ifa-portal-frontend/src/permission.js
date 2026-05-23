import router from './router'
import store from './store'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import getPageTitle from '@/utils/get-page-title'
import { isAccessible } from '@/utils/controlAuth'
import { resetUserState } from '@/utils/storeHelper'
import { ElNotification } from 'element-plus'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

router.beforeEach((to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // 未ログインの場合はログイン画面へ移動
  if (to.path !== '/login' && !store.getters.frameworkSessionId) {
    next('/login')
  // 認可制御
  } else if (to.meta.menuId && !isAccessible(to.meta.menuId)) {
    ElNotification({
      message: 'アクセス権限がありません',
      type: 'error',
      duration: 10 * 1000
    })
    resetUserState()
    next('/login')
  } else {
    next()
  }

  NProgress.done()
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
