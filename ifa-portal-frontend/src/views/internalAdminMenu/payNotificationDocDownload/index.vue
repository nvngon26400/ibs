<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getFormPayNotificationDocDownloadMenuConfig } from '@/views/internalAdminMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getFormPayNotificationDocDownloadMenuConfig.bind(this)
    this.menuList = getter()
  },
  mounted() {
    this.handleTableRefresh()
  },
  methods: {
    handleTableRefresh(tabInfo) {
      this.$refs['contents']?.refresh?.()
    }
  }
}
</script>
<style lang="scss" scoped>
@import "~@/styles/mixin.scss";
.width-style {
  min-width: 1230px;
  width: 100%;
}
</style>
