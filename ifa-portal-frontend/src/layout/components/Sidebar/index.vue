<template>
  <div :class="{'has-logo':showLogo}">
    <logo
      v-if="showLogo"
      :collapse="isCollapse"
    ></logo>
    <el-scrollbar wrap-class="scrollbar-wrapper" ref="scrollbar">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item
          v-for="route in routes"
          :key="route.path"
          :item="route"
          :base-path="route.path"
        ></sidebar-item>
        <ifa-sss-login
          v-if="isShowSssLogin"
          :collapse="isCollapse"
        ></ifa-sss-login>
        <ifa-sss-new-login
          v-if="isShowSssNewLogin"
          :collapse="isCollapse"
        ></ifa-sss-new-login>
        <ifa-old-link-list
          :collapse="isCollapse"
        ></ifa-old-link-list>
      </el-menu>
    </el-scrollbar>
    <div v-if="!isCollapse" class="side-user-info" ref="userInfo">
      <div style="margin-bottom: 0.2rem;">{{ displayDateTime }}</div>
      <div>{{ organization }}</div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Logo from './Logo'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'
import { getAccessibleMenuListForRouter, isAccessible } from '@/utils/controlAuth'
import IfaOldLinkList from './IfaOldLinkList'
import IfaSssLogin from './IfaSssLogin'
import IfaSssNewLogin from './IfaSssNewLogin'

export default {
  components: {
    SidebarItem,
    Logo,
    IfaOldLinkList,
    IfaSssLogin,
    IfaSssNewLogin
  },
  data() {
    return {
      isShowSssLogin: false,
      isShowSssNewLogin: false
    }
  },
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    routes() {
      this.setData()
      return getAccessibleMenuListForRouter(this.$router.options.routes)
    },
    activeMenu() {
      const route = this.$route
      const { meta, path } = route
      // if set path, the sidebar will highlight the path you set
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    variables() {
      return variables
    },
    isCollapse() {
      this.dispatchResizeEvent()
      return !this.sidebar.opened
    },
    organization() {
      // ログアウト時に store が消去されて undefined でエラーになるのを防止
      if (!this.$store.getters.userAccount) return ''

      // ユーザ共通情報.ログインinfo
      return this.$store.getters.userAccount.organization ?? ''
    },
    displayDateTime() {
      return this.$store.getters.requestedTime
    }
  },
  mounted() { 
      this.adjustMenuHeight();
  },
  methods: {
    /**
     * サイドバーの開閉に連動してwindow に resize イベントを発行する
     * resize イベントは､ GridTable を再描画させるために使用される
     * サイドバーのアニメーションが 280ms なので､イベント発行は 300ms 遅延させる
     * 参考: 280ms は @/styles/sidebar.scss の .main-container と .sidebar-container の transition で指定される値
     */
    dispatchResizeEvent() {
      setTimeout(() => {
        window.dispatchEvent(new Event('resize'))
      }, 300)
    },
    setData() {
      this.isShowSssLogin = isAccessible('SUB08-01')
      this.isShowSssNewLogin = isAccessible('SUB09-01')
    },
    adjustMenuHeight() {
      // ログインユーザ情報の領域に合わせてメニュー一覧の高さを調節
      const height = this.$refs.userInfo.offsetHeight;
      const scrollbar = this.$refs.scrollbar;
      const scrollbarEl = scrollbar && scrollbar.$el;
      if (height && scrollbarEl) {
        scrollbarEl.style.height = `calc(100% - 50px - ${height}px)`;
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/variables.scss";
:deep(.el-sub-menu) .el-menu-item {
  white-space: pre;
  // height: auto;
}
.side-user-info {
  color: $menuText;
  background-color: $menuBg;
  font-size: 14px;
  line-height: 1rem;
  padding: 0px 10px 20px 10px;
  position: absolute;
  bottom: 0;
  width: 100%;
}
</style>
