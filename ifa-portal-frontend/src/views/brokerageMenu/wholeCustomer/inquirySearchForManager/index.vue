<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getInquiryMenuConfig } from '@/views/brokerageMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'ifaInquirySearchForManager'
    }
  },
  created() {
    const getter = getInquiryMenuConfig.bind(this)
    this.menuList = getter()
  },
  methods: {
    handleFactoryReset(tabInfo) {
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
