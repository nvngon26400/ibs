<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getIfaReleaseNoteMenuConfig } from '@/views/releaseNote/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'ifaReleaseNote'
    }
  },
  created() {
    const getter = getIfaReleaseNoteMenuConfig.bind(this)
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
