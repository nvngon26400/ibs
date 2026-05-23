<template>
  <ifa-menu
    :menu-list="menuList"
  ></ifa-menu>
</template>

<script>
import { getComplianceRelatedReportMenuConfig } from '@/views/internalAdminMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getComplianceRelatedReportMenuConfig.bind(this)
    this.menuList = getter()
  }
}
</script>
