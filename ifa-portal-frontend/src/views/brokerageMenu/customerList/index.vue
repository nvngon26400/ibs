<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getCustomerListMenuConfig } from '@/views/brokerageMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'ifaCustomerList'
    }
  },
  created() {
    const getter = getCustomerListMenuConfig.bind(this)
    this.menuList = getter()
  }
}
</script>

<style lang="scss" scoped>
.width-style {
  min-width: 1210px;
  width: 100%;
}
</style>
