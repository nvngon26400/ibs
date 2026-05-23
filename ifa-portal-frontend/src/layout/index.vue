<template>
  <div
    :class="classObj"
    class="app-wrapper"
  >
    <sidebar class="sidebar-container"></sidebar>
    <div class="main-container">
      <div :class="{'fixed-header':fixedHeader}">
        <navbar></navbar>
      </div>
      <app-main ref="appMain"></app-main>
    </div>
    <footer>
      <p>© SBI SECURITIES Co., Ltd. ALL Rights Reserved</p>
    </footer>
    <!-- 通知ポップアップ -->
    <ifa-notifications ref="ifaNotifications"></ifa-notifications>
  </div>
</template>

<script>
import { Navbar, Sidebar, AppMain } from './components'
import { mapState } from 'vuex'
import IfaNotifications from '@/components/Notifications/IfaNotifications'

export default {
  name: 'Layout',
  components: {
    Navbar,
    Sidebar,
    AppMain,
    IfaNotifications
  },
  computed: {
    ...mapState({
      sidebar: state => state.app.sidebar,
      fixedHeader: state => state.settings.fixedHeader
    }),
    classObj() {
      return {
        hideSidebar: !this.sidebar.opened,
        openSidebar: this.sidebar.opened,
        withoutAnimation: this.sidebar.withoutAnimation
      }
    }
  },
  mounted() {
    // ログイン後、TechTouch を再読み込みします
    if (Object.prototype.hasOwnProperty.call(window, 'TechtouchObject')) {
      delete window.TechtouchObject
      delete window.TechtouchAddon
      delete window.isRunningTechtouchApp
      if (document.querySelector('script#techtouch-snippet')) {
        document.querySelector('script#techtouch-snippet').remove()
      }
      if (document.getElementById('techtouch-player-snippet')) {
        document.getElementById('techtouch-player-snippet').remove()
      }
      // delete https://apps.techtouch.jp/script/v4.0.1/content.js
      if (document.getElementsByTagName('script')[document.getElementsByTagName('script').length - 1].src.includes('techtouch')) {
        document.getElementsByTagName('script')[document.getElementsByTagName('script').length - 1].remove()
      }
    }
    this.$_techTouch(this.$store.getters.userAccount.medUsers)
  }
}
</script>

<style lang="scss" scoped>
  @import "~@/styles/mixin.scss";
  @import "~@/styles/variables.scss";

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;
  }
  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .fixed-header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$sideBarWidth});
    transition: width 0.28s;
  }

  .hideSidebar .fixed-header {
    width: calc(100% - 54px)
  }
</style>
