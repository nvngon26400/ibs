<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getSelfCheckMenuConfig } from '@/views/companyEmployeeMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'ifaSelfInspectBlotterConfirmManager'
    }
  },
  created() {
    const getter = getSelfCheckMenuConfig.bind(this)
    this.menuList = getter()
  },
  mounted() {
    this.$refs[this.selectedTabName]?.setup()
  },
  methods: {
    tabClickedProcess() {
      if (this.selectedTabName === 'ifaSelfInspectBlotterConfirmManager') {
        this.$refs['ifaSelfInspectBlotterConfirmManager'].setup()
      } else if (this.selectedTabName === 'ifaSelfInspectItemManage') {
        this.$refs['ifaSelfInspectItemManage'].setup()
      }
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
