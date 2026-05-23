<template>
  <ifa-menu
    :menu-list="menuList"
  ></ifa-menu>
</template>

<script>
import { getSandboxConfig } from '@/views/sandbox/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getSandboxConfig.bind(this)
    this.menuList = getter()
  }
}
</script>
