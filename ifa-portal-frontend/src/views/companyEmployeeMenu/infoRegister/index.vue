<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getInformationRegistrationMenuConfig } from '@/views/companyEmployeeMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'ifaInfoRegisterLookup'
    }
  },
  created() {
    const getter = getInformationRegistrationMenuConfig.bind(this)
    this.menuList = getter()
  },
  mounted() {
    this.$refs[this.selectedTabName]?.setup?.()
  },
  methods: {
    handleFactoryReset(tabInfo) {
      this.$refs['contents']?.setup?.()
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
