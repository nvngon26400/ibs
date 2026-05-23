<template>
  <el-tabs
    type="border-card"
    stretch
    @tab-click="handleTabClick"
  >
    <!-- タブ -->
    <el-tab-pane
      v-for="(tab,tabIndex) in accessibleMenuList"
      :key="tabIndex"
      :label="tab.label"
      :name="tab.name"
    >
      <!-- メニュー(2階層目以降がある場合) -->
      <el-menu
        v-if="hasSecondLayer"
        id="tab-second"
        mode="horizontal"
        class="menubar"
      >
        <span
          v-for="(menu,menuIndex) in tab.menuItems"
          :key="menuIndex"
        >
          <el-menu-item
            v-if="menu.isItem"
            :id="menu.id"
            :ref="menu.id"
            :index="`${tabIndex}-${menuIndex}`"
            :class="[menu.label === '' ? 'menuItems_un_visible' : '']"
            @click="handleClick($event, tab.name, menu.id)"
          >
            {{ menu.label }}
          </el-menu-item>
          <!-- 3階層目(ドロップダウン)を生成する場合 -->
          <el-sub-menu
            v-else
            :index="`${tabIndex}-${menuIndex}`"
          >
            <template #title>{{ menu.label }}</template>
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
        v-if="hasSecondLayer"
      >
        <div
          v-for="(menu,menuIndex) in tab.menuItems"
          :key="menuIndex"
        >
          <!-- 2階層目の場合 -->
          <template
            v-if="menu.isItem"
          >
            <component
              :is="menu.component"
              v-if="currentComponentName === menu.id"
              ref="menuId"
              v-bind="menu.props"
              v-on="menu.handlers"
            ></component>
          </template>
          <!-- 3階層目の場合 -->
          <template
            v-else
          >
            <component
              :is="item.component"
              v-for="(item, itemIndex) in menu.children"
              v-show="currentComponentName === item.id"
              :key="itemIndex"
              :ref="item.id"
              v-bind="item.props"
              v-on="item.handlers"
            ></component>
          </template>
        </div>
      </template>

      <!-- コンテンツ表示エリア(1階層の場合) -->
      <template
        v-else
      >
        <div
          v-for="(menu,menuIndex) in accessibleMenuList"
          :key="menuIndex"
        >
          <component
            :is="menu.component"
            v-if="currentComponentName === menu.name"
            ref="menuId"
            v-bind="menu.props"
            v-on="menu.handlers"
          ></component>
        </div>
      </template>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import { getAccessibleMenuList } from '@/utils/controlAuth'
export default {
  name: 'IfaTabMenu',
  inheritAttrs: false,
  props: {
    menuList: { type: Array, required: true } // メニューリスト
  },
  emits: ['tab-click-handler'],
  data() {
    return {
      menuSelectedState: {},
      currentComponentName: '',
      selectedTabMenu: [],
      accessibleMenuList: [], // アクセス可能なメニューリスト
      defaultActiveFirst: '', // 初期選択メニュー(1階層目)
      defaultActiveSecond: '' // 初期選択メニュー(2階層目)
    }
  },
  computed: {
    hasSecondLayer() {
      if (this.menuList[0].menuItems) {
        return true
      }
      return false
    }
  },
  created() {
    // メニュー認可制御
    [this.accessibleMenuList, this.defaultActiveFirst, this.defaultActiveSecond] = getAccessibleMenuList(this.menuList)
  },
  mounted() {
    // 初期表示時にそれぞれ先頭のタブをクリックして画面を表示する
    this.$nextTick(() => {
      if (this.defaultActiveFirst) {
        document.getElementById(this.defaultActiveFirst).click()
      }
      if (this.defaultActiveSecond) {
        document.getElementById(this.defaultActiveSecond).click()
      }
    })
  },
  methods: {
    handleTabClick(tabInfo) {
      if (this.menuSelectedState[tabInfo.paneName] && this.menuSelectedState[tabInfo.paneName].menuName) {
        this.currentComponentName = this.menuSelectedState[tabInfo.paneName].componentName
      } else {
        this.currentComponentName = tabInfo.paneName
      }

      if (!this.selectedTabMenu.includes(tabInfo.paneName)) {
        this.selectedTabMenu.push(tabInfo.paneName)

        this.menuList.forEach(tab => {
          if (tab.name === tabInfo.paneName) {
            const elementId = tab.menuItems ? tab.menuItems[0].id : 'tab-' + tab.name
            document.getElementById(elementId).click()
            return
          }
        })
      }
      // メニュー制御関連で独自実装が必要な場合は、画面側で実装する。
      this.$emit('tab-click-handler', tabInfo)
    },
    handleClick(event, tabName, menuId, parentMenuLabel) {
      if (!this.menuSelectedState[tabName]) {
        this.menuSelectedState[tabName] = { }
      }
      let menuItem
      for (const tab of this.accessibleMenuList) {
        if (menuItem !== undefined) break
        if (tab.menuItems.length > 0) {
          for (const menu of tab.menuItems) {
            if (menu.isItem) {
              if (menu.id === menuId) {
                menuItem = {
                  label: menu.label,
                  id: menu.id,
                  component: menu.component
                }
                break
              }
            } else {
              const submenu = menu.children.find(sb => sb.id === menuId)
              if (submenu) {
                menuItem = {
                  label: submenu.label,
                  id: submenu.id,
                  component: submenu.component
                }
                break
              }
            }
          }
        }
      }
      this.menuSelectedState[tabName].menuName = menuItem.label
      this.menuSelectedState[tabName].componentName = menuItem.id
      this.currentComponentName = this.menuSelectedState[tabName].componentName
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
.menubar {
  display: flex;
}
.menuItems_un_visible {
  padding-left: 0px;
  padding-right: 0px;
  height: 0px;
  border-bottom-width: 0px;
}
</style>
