<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getNewAccountOpenImcompleteStatusMenuConfig } from '@/views/accountOpenMenu/newAccountOpenImcompleteStatus/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getNewAccountOpenImcompleteStatusMenuConfig.bind(this)
    this.menuList = getter()
  }
}
</script>

<style lang="scss" scoped>
.width-style {
  min-width: 1210px;
  width: 100%;
}
</style>
