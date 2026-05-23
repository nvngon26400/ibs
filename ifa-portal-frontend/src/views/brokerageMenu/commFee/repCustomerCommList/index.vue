<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getChargesTotalMenuConfig } from '@/views/brokerageMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'ifaChargIfaRepCustomerCommListesTotal',
      currentTabName: ''
    }
  },
  created() {
    const getter = getChargesTotalMenuConfig.bind(this)
    this.menuList = getter()
  },
  mounted() {
    this.handleFactoryReset()
  },
  methods: {
    handleFactoryReset(tabInfo) {
      this.$refs[this.currentTabName]?.refresh()
      this.$refs[this.selectedTabName]?.firstShowMessage()
      this.currentTabName = this.selectedTabName
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
