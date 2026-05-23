<template>
  <div>
    <!-- メニューエリア -->
    <el-tabs
      type="border-card"
      stretch
      @tab-click="handleTabClick"
    >
      <!-- タブ -->
      <el-tab-pane
        v-for="(tab, tabIndex) in accessibleMenuList"
        :key="tabIndex"
        :label="tab.label"
        :name="tab.name"
      >
        <!-- メニュー -->
        <el-menu
          v-if="tab.menuItems && tab.menuItems.length > 0"
          id="tab.menuId"
          mode="horizontal"
          class="ifa-menu-menubar"
        >
          <span
            v-for="(menu, menuIndex) in tab.menuItems"
            :key="menuIndex"
          >
            <!-- 2階層のみの場合 -->
            <el-menu-item
              v-if="isItem(menu)"
              :id="menu.id"
              :ref="menu.id"
              :index="`${tabIndex}-${menuIndex}`"
              :class="[menu.label === '' ? 'menuItems_un_visible' : '']"
              @click="handleClick($event, tab.name, menu.id)"
            >
              {{ menu.label }}
            </el-menu-item>
            <!-- 3階層目を生成する場合 -->
            <el-sub-menu
              v-else
              :index="`${tabIndex}-${menuIndex}`"
            >
              <template #title>{{ menu.label }}</template>
              <!-- 3階層目の表示項目 -->
              <el-menu-item
                v-for="(item, itemIndex) in menu.children"
                :id="item.id"
                :key="itemIndex"
                :index="`${tabIndex}-${menuIndex}-${itemIndex}`"
                @click="handleClick($event, tab.name, item.id, menu.label)"
              >{{ item.label }}</el-menu-item>
            </el-sub-menu>
          </span>
        </el-menu>

        <!-- コンテンツ表示エリア -->
        <template
          v-if="errorInfo"
        >
          <ifa-initialize-error
            :error-info="errorInfo"
          ></ifa-initialize-error>
        </template>

        <template
          v-else-if="hasSecondLayer"
        >
          <div
            v-for="(menu, menuIndex) in tab.menuItems"
            :key="menuIndex"
          >
            <!-- 2階層目の場合 -->
            <template
              v-if="isItem(menu)"
            >
              <keep-alive>
                <component
                  :is="menu.component"
                  v-if="currentComponentName === menu.id"
                  :ref="menu.id"
                  @initialize-error="handleInitializeError($event)"
                  @update-customer-portal="redrawCustomerPortal"
                ></component>
              </keep-alive>
            </template>
            <!-- 3階層目の場合 -->
            <template
              v-else
            >
              <keep-alive>
                <component
                  :is="item.component"
                  v-for="(item, itemIndex) in menu.children"
                  v-show="currentComponentName === item.id"
                  :key="itemIndex"
                  :ref="item.id"
                  @initialize-error="handleInitializeError($event)"
                  @update-customer-portal="redrawCustomerPortal"
                ></component>
              </keep-alive>
            </template>
          </div>
        </template>

        <!-- コンテンツ表示エリア(1階層の場合) -->
        <template
          v-else
        >
          <keep-alive>
            <component
              :is="tab.component"
              v-if="currentComponentName === tab.name"
              :ref="tab.name"
              @initialize-error="handleInitializeError($event)"
            ></component>
          </keep-alive>
        </template>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { getAccessibleMenuList } from '@/utils/controlAuth'
import { routingTable } from './routingTable.js'
import IfaInitializeError from '@/views/error/IfaInitializeError.vue'
export default {
  name: 'IfaMenu',
  components: { IfaInitializeError },
  inheritAttrs: false,
  props: {
    menuList: { type: Array, required: true },
    manual: { type: Boolean, required: false, default: false }
  },
  emits: ['show', 'hide', 'update-customer-portal'],
  data() {
    return {
      menuSelectedState: {},
      currentComponentName: '',
      selectedTabMenu: [],
      selectedComponentName: [],
      accessibleMenuList: [], // アクセス可能なメニューリスト
      errorInfo: null // 初期化エラー情報
    }
  },
  computed: {
    hasSecondLayer() {
      if (this.accessibleMenuList &&
          this.accessibleMenuList.length > 0 &&
          this.accessibleMenuList[0].menuItems &&
          this.accessibleMenuList[0].menuItems.length > 0) {
        return true
      }
      return false
    },
    isItem: function() {
      return function(menu) {
        return menu.children === undefined
      }
    }
  },
  watch: {
    $route: {
      // 同画面内での遷移
      handler(to, from) {
        if (to.name === from.name) {
          this.$nextTick(() => {
            if (!this.manual) {
              this.onShow()
            }
          })
        }
      },
      deep: true
    },
    manual(newValue) {
      if (!newValue) {
        this.reset()
        this.onShow()
      }
    }
  },
  created() {
    this.reset()
  },
  mounted() {
    // 他の画面から遷移
    this.onShow()
  },
  unmounted() {
    if (this.currentComponentName) {
      this.$refs[this.currentComponentName].slice(-1)[0]?.onHide?.()
      this.$emit('hide')
    }
  },
  methods: {
    reset() {
      this.menuSelectedState = {}
      this.currentComponentName = ''
      this.selectedTabMenu = []
      this.selectedComponentName = []

      // メニュー認可制御
      this.accessibleMenuList = getAccessibleMenuList(this.menuList)

      this.$store.dispatch('customerPortalMenuList/setMenuList', {
        menuList: []
      })
    },
    onShow() {
      const url = window.location.href.replace(/\?.+$/, '')
      history.replaceState(history.state, '', url)
      // 左サイドメニュークリックで画面切り替えが発生した場合は通知ポップアップを消去する
      this.$store.dispatch('notifications/resetState')
      if (!this.manual) {
        if (!this.$_isRooting()) {
          // IfaMenu の生成時､ルーティング中以外なら メヌList の一番最初のコンポーネントを表示させる
          let target = 'tab-' + this.accessibleMenuList[0].name
          let menuId = this.accessibleMenuList[0].menuId
          if (this.accessibleMenuList[0].menuItems && this.accessibleMenuList[0].menuItems.length > 0) {
            if (this.accessibleMenuList[0].menuItems[0].children && this.accessibleMenuList[0].menuItems[0].children.length > 0) {
              target += ':' + this.accessibleMenuList[0].menuItems[0].children[0].id
              menuId = this.accessibleMenuList[0].menuItems[0].children[0].menuId
            } else {
              target += ':' + this.accessibleMenuList[0].menuItems[0].id
              menuId = this.accessibleMenuList[0].menuItems[0].menuId
            }
          }
          // IfaRouter を経由しない画面切り替えの為､Routerに画面IDとターゲットを保存する
          this.$route.query.menuId = menuId
          this.$route.query.target = target
        }
        const self = this
        const tab = this.$route.query.target.split(':')[0]
        if (tab) {
          this.clickElementById(tab)
            .then(result => {
              if (result.error) {
                // タブのクリック失敗時はホームに飛ばす
                self.$router.push('/')
                self.$_finishShowMenu()
              }
            })
        }
      }
    },
    clickElementById(target) {
      return new Promise(resolve => {
        this.$nextTick(() => {
          const el = document.getElementById(target)
          if (el) {
            el.click()
            resolve({ error: false })
          } else {
            // エレメントが見つからない場合はエラー
            resolve({ error: true })
          }
        })
      })
    },
    setComponent(component, isRouting = false) {
      if (this.currentComponentName && !this.errorInfo) {
        this.$refs[this.currentComponentName].slice(-1)[0].onHide?.()
      }
      this.errorInfo = null
      this.currentComponentName = component
      // タブクリック､メニュー項目クリックで画面切り替えが発生した場合は通知ポップアップを消去する
      this.$store.dispatch('notifications/resetState')
      this.$nextTick(() => {
        let resume = true
        if (!this.selectedComponentName.includes(component)) {
          this.selectedComponentName.push(component)
          resume = false
        }
        // コンポーネント切り替え時に window に resize イベントを発行する
        // resize イベントは､ GridTable を再描画させるために使用される
        window.dispatchEvent(new Event('resize'))

        let refComponent = this.$refs[component]?.slice(-1)[0]
        // 末尾の要素が'ElMenuItem'である場合には最初の要素を参照する
        if (refComponent?.$options?.name === 'ElMenuItem') {
          refComponent = this.$refs[component]?.[0]
        }
        this.$emit('show', refComponent)
        refComponent?.onShow?.(resume, isRouting)

        this.redrawCustomerPortal()
      })
    },
    handleTabClick(tabInfo) {
      const tab = this.accessibleMenuList.find(tab => tab.name === tabInfo.paneName)
      if (!this.selectedTabMenu.includes(tabInfo.paneName)) {
        // タブを初回表示する
        this.selectedTabMenu.push(tabInfo.paneName)
        if (!this.menuSelectedState[tab.name]) {
          this.menuSelectedState[tab.name] = {}
        }

        if (!tab.menuItems || tab.menuItems.length === 0) {
          // タブのみでメニューを持たない場合
          // コンポーネントを表示する
          if (!this.menuSelectedState[tab.name][tab.name]) {
            this.menuSelectedState[tab.name][tab.name] = {}
            this.menuSelectedState[tab.name][tab.name].menuId = tab.menuId
            this.menuSelectedState[tab.name][tab.name].label = tab.label
            this.menuSelectedState[tab.name][tab.name].componentName = tab.name
            this.menuSelectedState[tab.name][tab.name].target = 'tab-' + tab.name
            this.menuSelectedState[tab.name].currentComponentName = tab.name
          }
          const isRouting = this.$_isRooting()
          this.$_finishShowMenu(this.menuSelectedState[tab.name][tab.name])
          this.setComponent(this.menuSelectedState[tab.name].currentComponentName, isRouting)
        } else {
          // タブがメニューを持っている場合
          let target
          if (this.$_isRooting()) {
            target = this.$route.query.target.split(':')[1]
          } else {
            let menuId
            if (tab.menuItems[0].children && tab.menuItems[0].children.length > 0) {
              menuId = tab.menuItems[0].children[0].menuId
            } else {
              menuId = tab.menuItems[0].menuId
            }
            const table = routingTable.bind(this)
            const menuItem = table().find(item => item.menuId === menuId)
            if (menuItem === undefined) {
              this.$router.push('/')
              this.$_finishShowMenu()
              return
            }
            target = menuItem.target.split(':')[1]
          }
          const self = this
          this.clickElementById(target)
            .then(result => {
              if (result.error) {
                // タブのクリック失敗時はホームに飛ばす
                self.$router.push('/')
                self.$_finishShowMenu()
              }
            })
        }
      } else if (this.$_isRooting() && this.$route.query.target.split(':').length === 2) {
        const target = this.$route.query.target.split(':')[1]
        const self = this
        this.clickElementById(target)
          .then(result => {
            if (result.error) {
              // タブのクリック失敗時はホームに飛ばす
              self.$router.push('/')
              self.$_finishShowMenu()
            }
          })
      } else {
        const menuId = this.menuSelectedState[tabInfo.paneName].currentComponentName
        const target = this.menuSelectedState[tabInfo.paneName][menuId].target.split(':')
        if (target.length === 2) {
          const self = this
          this.clickElementById(target[1])
            .then(result => {
              if (result.error) {
                // タブのクリック失敗時はホームに飛ばす
                self.$router.push('/')
                self.$_finishShowMenu()
              }
            })
        } else {
          // 以前表示済みのタブが再表示された
          const isRouting = this.$_isRooting()
          this.$_finishShowMenu(this.menuSelectedState[tabInfo.paneName][menuId])
          this.setComponent(this.menuSelectedState[tabInfo.paneName].currentComponentName, isRouting)
        }
      }

      const ml = []
      ml.push({
        redirect: 'noRedirect',
        meta: { title: tab.label },
        path: 'dummyPath'
      })
      this.$store.dispatch('customerPortalMenuList/setMenuList', { menuList: ml })
    },
    handleClick(event, tabName, menuId, parentMenuLabel) {
      const tab = this.accessibleMenuList.find(tab => tab.name === tabName)
      if (!this.menuSelectedState[tabName]) {
        this.menuSelectedState[tabName] = {}
      }

      if (this.menuSelectedState[tabName][menuId]) {
        this.menuSelectedState[tabName].currentComponentName = this.menuSelectedState[tabName][menuId].componentName
        // 以前表示済みのタブが再表示された
        const isRouting = this.$_isRooting()
        this.$_finishShowMenu(this.menuSelectedState[tabName][menuId])
        this.setComponent(this.menuSelectedState[tabName].currentComponentName, isRouting)
      } else {
        let menu
        for (const menuItem of tab.menuItems) {
          if (menuItem.children) {
            menu = menuItem.children.find(item => item.id === menuId)
            if (menu) break
          } else {
            if (menuItem.id === menuId) {
              menu = menuItem
              break
            }
          }
        }
        const table = routingTable.bind(this)
        const menuItem = table().find(item => item.menuId === menu.menuId)
        if (!this.menuSelectedState[tabName][menuId]) {
          this.menuSelectedState[tabName][menuId] = {}
        }
        this.menuSelectedState[tabName][menuId].menuId = menu.menuId
        this.menuSelectedState[tabName][menuId].label = menu.label
        this.menuSelectedState[tabName][menuId].componentName = menu.id
        this.menuSelectedState[tabName][menuId].target = menuItem.target
        this.menuSelectedState[tabName].currentComponentName = menu.id
        const isRouting = this.$_isRooting()
        this.$_finishShowMenu(this.menuSelectedState[tabName][menuId])
        this.setComponent(this.menuSelectedState[tabName].currentComponentName, isRouting)
      }

      const ml = [this.$store.getters.customerPortalMenuList[0]]
      if (parentMenuLabel) {
        ml.push({
          redirect: 'noRedirect',
          meta: { title: parentMenuLabel },
          path: 'dummySubPath'
        })
      }
      ml.push({
        redirect: 'noRedirect',
        meta: { title: this.menuSelectedState[tabName][menuId].label },
        path: 'dummyContentPath'
      })
      this.$store.dispatch('customerPortalMenuList/setMenuList', { menuList: ml })
    },
    handleInitializeError(errorInfo) {
      this.errorInfo = errorInfo
    },
    redrawCustomerPortal() {
      // 顧客別メニュー内の場合、顧客ポータルの再描画を行う
      this.$emit('update-customer-portal')
    }
  }
}
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";
.el-sub-menu__title {
  width: 76px;
}
.empty-icon {
  font-size: 15rem;
  color: #eee;
}
.ifa-menu-wrapper {
  display: flex;
  margin: 0.2rem 0.5rem 0 0.5rem;
}
:deep(.el-tabs__item.is-top.is-active) {
  background-color: #f39800;
  color: #fff;
}
:deep(.el-collapse-item__header) {
  background-color: #eee;
}
:deep(.el-collapse-item__content) {
  padding: 0;
}
.scrollbar-style {
  overflow-x: auto;
}
.width-style {
  min-width: 1230px;
  width: 100%;
}
.ifa-menu-menubar {
  display: flex;
}
#ifa-menu-menu :deep(.el-sub-menu__title) {
  padding: 0 20px !important;
}
.menuItems_un_visible {
  padding-left: 0px;
  padding-right: 0px;
  height: 0px;
  border-bottom-width: 0px;
}
</style>
