<template>
  <div class="faq-side-menu-main">
    <!-- サイドメニュー -->
    <el-menu class="side-menu">
      <span
        v-for="lMenu in menuList"
        :key="lMenu.name"
      >
        <!-- 親子関係のあるメニュー -->
        <el-sub-menu
          v-if="lMenu.menuList.length > 0"
          :index="lMenu.lId"
        >
          <template #title>
            <span>{{ $_out(lMenu.name) }}</span>
          </template>
          <el-menu-item
            v-for="sMenu in lMenu.menuList"
            :key="sMenu.name"
            :index="sMenu.sId"
            @click="handleMenuClickA003A004"
          >{{ $_out(sMenu.name) }}</el-menu-item>
        </el-sub-menu>
        <!-- /親子関係のあるメニュー -->

        <!-- 親のみのメニュー -->
        <el-menu-item
          v-else
          :index="lMenu.lId"
          @click="handleMenuClickA003A004"
        >
          <template #title>
            <span>{{ $_out(lMenu.name) }}</span>
          </template>
        </el-menu-item>
        <!-- /親のみのメニュー -->
      </span>
    </el-menu>
    <!-- !サイドメニュー -->
  </div>
</template>

<script>

export default {
  props: {
    // メニュー
    menuList: {
      required: true,
      type: Array
    }
  },
  emits: ['menu-click'],
  mounted() {
    this.$_logDebug('menuList')
  },
  methods: {
    // A003 大項目指定(小項目リストが存在しない場合) / A004 小項目指定
    handleMenuClickA003A004(item) {
      // 親コンポーネントにイベントを発行
      this.$emit('menu-click', item)
    }
  }
}
</script>

<style lang="scss" scoped>
.faq-side-menu-main {
  display: flex;
  font-weight: bold;
  max-height: calc(100lvh - 80px);
  overflow-y: scroll;
  overflow-x: auto;
}
.side-menu {
  background-color: #E6E8F0;
  width: 100%;
  min-width: 235px;
}
:deep(.el-menu--inline) {
  min-width: 410px;
}
</style>
