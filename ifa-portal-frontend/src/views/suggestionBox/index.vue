<template>
  <div class="width-style">
    <ifa-menu
      :menu-list="menuList"
    ></ifa-menu>
  </div>
</template>

<script>
import { getSuggestionBoxMenuEmpConfig } from '@/views/companyEmployeeMenu/_config/menuConfig.js'
import { getSuggestionBoxMenuBrokerConfig } from '@/views/suggestionBox/_config/menuConfig.js'

export default {
  data() {
    return {
      menuList: [],
      selectedTabName: 'ifaSuggestionBoxPersonal'
    }
  },
  created() {
    let getter
    if (this.$store.getters.userAccount.medUsers.privId === '1') {
        getter = getSuggestionBoxMenuEmpConfig.bind(this)
      } else {
        getter = getSuggestionBoxMenuBrokerConfig.bind(this)
      }
    this.menuList = getter()
  },
  mounted() {
    this.$refs[this.selectedTabName]?.setup()
  },
  methods: {
    tabClickedProcess() {
      if (this.selectedTabName === 'ifaSuggestionBoxPersonal') {
        this.$refs['ifaSuggestionBoxPersonal'].setup()
      } else if (this.selectedTabName === 'ifaSuggestionBoxCommon') {
        this.$refs['ifaSuggestionBoxCommon'].setup()
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
