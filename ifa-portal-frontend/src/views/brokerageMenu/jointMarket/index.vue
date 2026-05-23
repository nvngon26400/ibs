<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getJointMarketMenuConfig } from '@/views/brokerageMenu/_config/menuConfig'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'ifaJointMarketTradeSearch'
    }
  },
  created() {
    const getter = getJointMarketMenuConfig.bind(this)
    this.menuList = getter()
  },
  mounted() {
    this.$refs[this.selectedTabName]?.setup()
  }
}
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";
.width-style {
  min-width: 1230px;
  width: 100%;
}
</style>
