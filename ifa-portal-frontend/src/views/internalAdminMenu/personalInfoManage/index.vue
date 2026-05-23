<template>
  <ifa-menu
    :menu-list="menuList"
  ></ifa-menu>
</template>

<script>
import { getManagePersonalyLedgerMenuConfig } from '@/views/internalAdminMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getManagePersonalyLedgerMenuConfig.bind(this)
    this.menuList = getter()
  },
  methods: {
    handleTableRefresh(tabInfo) {
      this.$refs['contents']?.refreshInfo?.()
    }
  }
}
</script>
