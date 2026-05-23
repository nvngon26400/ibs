<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getIfaReleaseNoteEmployeeMenuConfig } from '@/views/companyEmployeeMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'ifaReleaseNoteEmployee'
    }
  },
  created() {
    const getter = getIfaReleaseNoteEmployeeMenuConfig.bind(this)
    this.menuList = getter()
  },
  mounted() {
    this.handleFactoryReset()
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
  min-width: 1230px;
  width: 100%;
}
</style>
