<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getFormAcquisitionMenuConfig } from '@/views/internalAdminMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getFormAcquisitionMenuConfig.bind(this)
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
.width-style {
  min-width: 1210px;
  width: 100%;
}
</style>
