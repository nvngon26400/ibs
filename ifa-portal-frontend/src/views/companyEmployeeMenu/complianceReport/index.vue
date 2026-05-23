<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getComplianceInfoForAdministratorMenuConfig } from '@/views/companyEmployeeMenu/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: []
    }
  },
  created() {
    const getter = getComplianceInfoForAdministratorMenuConfig.bind(this)
    this.menuList = getter()
  },
  mounted() {
    this.$refs[this.selectedTabName]?.setup()
  },
  methods: {
    tabClickedProcess() {
      if (this.selectedTabName === 'ifaComplianceReportInfoRegisterManager') {
        this.$refs['ifaComplianceReportInfoRegisterManager'].setup()
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
