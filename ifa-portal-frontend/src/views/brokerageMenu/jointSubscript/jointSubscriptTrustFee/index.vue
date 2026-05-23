<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getTrustRewardMenuConfig } from '@/views/brokerageMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'IfaJointSubscriptTrustFee',
      currentTabName: ''
    }
  },
  created() {
    const getter = getTrustRewardMenuConfig.bind(this)
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
