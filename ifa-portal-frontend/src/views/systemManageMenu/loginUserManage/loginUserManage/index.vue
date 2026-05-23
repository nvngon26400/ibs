<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getLoginUserManagementMenuConfig } from '@/views/systemManageMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'ifaLoginUserManageManagerLookup'
    }
  },
  created() {
    const getter = getLoginUserManagementMenuConfig.bind(this)
    this.menuList = getter()
  },
  mounted() {
    this.$refs[this.selectedTabName]?.setup?.()
  },
  methods: {
    handleFactoryReset(tabInfo) {
      this.$refs[this.selectedTabName]?.setup?.()
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
