<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getTradeDataManageMenuConfig } from '@/views/companyEmployeeMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getTradeDataManageMenuConfig.bind(this)
    this.menuList = getter()
  },
  mounted() {
    this.$refs[this.selectedTabName]?.setup()
  },
  methods: {
    tabClickedProcess() {
      if (this.selectedTabName === 'ifaStarUploadFileReacquire') {
        this.$refs['ifaStarUploadFileReacquire'].setup()
      } else if (this.selectedTabName === 'other') {
        this.$refs['other'].setup()
      }
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
