<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getDrawersAccountMenuConfig } from '@/views/brokerageMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'ifaCustomerDestinationBankAccount'
    }
  },
  created() {
    const getter = getDrawersAccountMenuConfig.bind(this)
    this.menuList = getter()
  },
  methods: {
    handleFactoryReset(tabInfo) {
      // this.$_logDebug('aaaaa')
      // this.$refs[this.selectedTabName]?.setup()
    }
  }
}
</script>

<style lang="scss" scoped>
.width-style {
  min-width: 1210px;
  width: 100%;
}
</style>
