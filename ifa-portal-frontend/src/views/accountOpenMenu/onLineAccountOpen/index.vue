<template>
  <ifa-menu
    :menu-list="menuList"
  ></ifa-menu>
</template>

<script>
import { getOnLineAccountOpenMenuConfig } from '@/views/accountOpenMenu/onLineAccountOpen/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getOnLineAccountOpenMenuConfig.bind(this)
    this.menuList = getter()
  }
}
</script>
