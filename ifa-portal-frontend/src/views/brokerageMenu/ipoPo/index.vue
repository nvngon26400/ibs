<template>
  <ifa-menu
    :menu-list="menuList"
  ></ifa-menu>
</template>

<script>
import { getIpoPoMenuConfig } from '@/views/brokerageMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getIpoPoMenuConfig.bind(this)
    this.menuList = getter()
  },
  methods: {
    setBrandCode(params) {
      this.$refs['ifaBbApplyList'].setBrandCode(params)
    },
    factoryReset(tabInfo) {
      this.$refs['ifaBbApplyList']?.setup()
    },
    showTabBbApplyList() {
      this.selectedTabName = 'ifaBbApplyList'
      this.factoryReset()
    }
  }
}
</script>
<style scoped>
</style>
