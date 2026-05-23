<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getMonthlyActionItemsMenuConfig } from '@/views/internalAdminMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getMonthlyActionItemsMenuConfig.bind(this)
    this.menuList = getter()
  },
  methods: {
    handleTableRefresh(tabInfo) {
      this.$refs['ifaSelfInspectBlotter'].refresh()
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
