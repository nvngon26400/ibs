<template>
  <ifa-menu
    :menu-list="menuList"
  ></ifa-menu>
</template>

<script>
import { getAllSortsApplyReportMenuConfig } from '@/views/brokerageMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getAllSortsApplyReportMenuConfig.bind(this)
    this.menuList = getter()
  }
}
</script>
