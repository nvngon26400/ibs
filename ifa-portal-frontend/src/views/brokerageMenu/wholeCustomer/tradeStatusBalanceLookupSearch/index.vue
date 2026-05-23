<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getTradeStatusAndBalanceMenuConfig } from '@/views/brokerageMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      menuSelectedState: {},
      currentComponentName: '',
      selectedTabMenu: []
    }
  },
  created() {
    const getter = getTradeStatusAndBalanceMenuConfig.bind(this)
    this.menuList = getter()
  },
  methods: {
    handleTabClick(tabInfo) {
      const setSelectedMenuLocal = setSelectedMenu.bind(this)
      setSelectedMenuLocal(tabInfo)
      if (!this.selectedTabMenu.includes(tabInfo.name)) {
        this.selectedTabMenu.push(tabInfo.name)

        this.menuList.forEach(tab => {
          if (tab.name === tabInfo.name) {
            document.getElementById(tab.menuItems[0].id).click()
            return
          }
        })
      }
      for (let i = 0; i < 10; i++) {
        this.$refs.menuId[i].refresh()
      }
      if (this.currentComponentName === 'ifa-day-order-list') {
        this.$refs.menuId[0].setup()
      } else if (this.currentComponentName === 'ifa-inp-repay-principal-interest') {
        this.$refs.menuId[2].setup()
      } else if (this.currentComponentName === 'ifa-inp-trade-history') {
        this.$refs.menuId[3].setup()
      } else if (this.currentComponentName === 'ifa-fee-currency-trading-history') {
        this.$refs.menuId[4].setup()
      } else if (this.currentComponentName === 'ifa-deposit-withdrawal-detail') {
        this.$refs.menuId[5].setup()
      } else if (this.currentComponentName === 'ifa-deposit-withdraw') {
        this.$refs.menuId[6].setup()
      } else if (this.currentComponentName === 'ifa-inp-balance') {
        this.$refs.menuId[7].setup()
      }
    },
    handleClick(event, tabName, menuIndex, tabIndex) {
      if (!this.menuSelectedState[tabName]) {
        this.menuSelectedState[tabName] = { }
      }
      const menuItem = this.menulist[tabIndex].menuItems[menuIndex]
      this.menuSelectedState[tabName].menuName = menuItem.label
      this.menuSelectedState[tabName].componentName = menuItem.id
      this.currentComponentName = this.menuSelectedState[tabName].componentName
      const menuList = [this.$store.getters.customerPortalMenuList[0]]
      menuList.push({
        redirect: 'noRedirect',
        meta: { title: menuItem.label },
        path: 'dummyContentPath'
      })
      for (let i = 0; i < 10; i++) {
        this.$refs.menuId[i].refresh()
      }
      if (tabIndex === 0) {
        this.$refs.menuId[menuIndex].setup()
      } else if (tabIndex === 1) {
        this.$refs.menuId[menuIndex + 3].setup()
      } else {
        this.$refs.menuId[menuIndex + 7].setup()
      }
    }
  }
}
const setSelectedMenu = function(tabInfo) {
  const menuList = [
    {
      redirect: 'noRedirect',
      meta: { title: tabInfo.label },
      path: 'dummyPath'
    }
  ]

  if (this.menuSelectedState[tabInfo.name] && this.menuSelectedState[tabInfo.name].menuName) {
    menuList.push({
      redirect: 'noRedirect',
      meta: { title: this.menuSelectedState[tabInfo.name].menuName },
      path: 'dummySubPath'
    })
    this.currentComponentName = this.menuSelectedState[tabInfo.name].componentName
  }
  // this.$store.dispatch('customerPortalMenuList/setMenuList', {
  //   menuList
  // })
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
.customer-menu-wrapper {
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
.customer-potal-menubar {
  display: flex;
}
#customer-portal-menu :deep(.el-sub-menu__title) {
  padding: 0 20px !important;
}
.menuItems_un_visible {
  padding-left: 0px;
  padding-right: 0px;
  height: 0px;
  border-bottom-width: 0px;
}
</style>
