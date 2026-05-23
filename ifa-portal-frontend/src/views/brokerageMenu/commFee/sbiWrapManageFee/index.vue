<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getSbiWrapManageFeeMenuConfig } from '@/views/brokerageMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getSbiWrapManageFeeMenuConfig.bind(this)
    this.menuList = getter()
  }
}
</script>

<style lang="scss" scoped>
.width-style {
  min-width: 1020px;
  width: 100%;
}
</style>
