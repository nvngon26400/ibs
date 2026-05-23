<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getChargeRewardMenuConfig } from '@/views/brokerageMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'ifaCommFee',
      currentTabName: ''
    }
  },
  created() {
    const getter = getChargeRewardMenuConfig.bind(this)
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
  min-width: 1020px;
  width: 100%;
}
</style>
