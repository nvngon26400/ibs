<template>
  <ifa-menu
    :menu-list="menuList"
  ></ifa-menu>
</template>

<script>
import { getAccountOpenApplicationPrePrintMenuConfig } from '@/views/accountOpenMenu/accountOpenApplicationPrePrint/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getAccountOpenApplicationPrePrintMenuConfig.bind(this)
    this.menuList = getter()
  }
}
</script>
