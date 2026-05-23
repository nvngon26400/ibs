<template>
  <div></div>
</template>

<script>
import { routingTable } from './routingTable.js'
export default {
  name: 'IfaInit',
  created() {
    const table = routingTable.bind(this)
    const userAccount = this.$store.getters.userAccount
    if (userAccount) {
      const accessibleViewList = userAccount.userPermissionInfo.accessibleViewList
      if (accessibleViewList) {
        // routingTable から認可されている画面を検索する
        const target = table().find(item => !item.exclude && accessibleViewList.includes(item.menuId))
        if (target) {
          // 認可されている画面があった場合､その画面にリダイレクトする
          this.$_startShowMenu(target.menuId)
        }
      }
    }
  }
}
</script>
