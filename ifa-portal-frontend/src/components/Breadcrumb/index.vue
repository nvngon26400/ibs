<template>
  <el-breadcrumb
    class="app-breadcrumb"
    separator-class="ArrowRight"
  >
    <transition-group name="breadcrumb">
      <el-breadcrumb-item
        v-for="(item,index) in levelList"
        :key="item.path"
      >
        <span
          v-if="item.redirect==='noRedirect'||index==levelList.length-1"
          class="no-redirect"
        >{{ item.meta.title }}</span>
        <span
          v-else
        >{{ item.meta.title }}</span>
      </el-breadcrumb-item>
    </transition-group>
    <el-breadcrumb-item
      v-for="(item,index) in additionalMenuList"
      :key="item.path"
    >
      <span
        v-if="item.redirect==='noRedirect'||index==levelList.length-1"
        class="no-redirect"
      >{{ item.meta.title }}</span>
      <span
        v-else
      >{{ item.meta.title }}</span>
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>
import { pathToRegexp } from 'path-to-regexp'

export default {
  data() {
    return {
      levelList: null
      // additionalMenuList: []
    }
  },
  computed: {
    additionalMenuList() {
      if (this.$store.getters.customerPortalMenuList && this.$store.getters.customerPortalMenuList.length > 0) {
        const list = this.$store.getters.customerPortalMenuList
        // 現在の要素とその前の要素が同じ値を持つ場合に現在の要素を除外
        const uniqueList = list.filter((item, index, array) => {
          return index === 0 || item.meta.title !== array[index - 1].meta.title
        })
        // levelListの最後とuniqueListの最初が同じ値を持つ場合にfilteredListの要素を除外
        const filteredList = uniqueList.filter((item, index) => {
          if (index === 0 && item.meta.title === this.levelList[this.levelList?.length - 1]?.meta.title) {
            return false
          } else {
            return true
          }
        })
        if (filteredList && filteredList.length > 0) {
          return filteredList
        } else {
          return []
        }
      } else {
        return []
      }
    }
  },
  watch: {
    $route() {
      this.getBreadcrumb()
    }
  },

  created() {
    this.getBreadcrumb()
  },
  // mounted() {
  //   this.$store.subscribe((mutation, state) => {
  //     if (mutation.type === 'customerPortalMenuList/SET_MENU_LIST') {
  //       this.$_logDebug('SET_MENU_LIST! %s', state.customerPortalMenuList)
  //       this.$nextTick(() => {
  //         this.additionalMenuList = state.customerPortalMenuList.customerPortalMenuList
  //       })
  //     }
  //   })
  // },
  methods: {
    getBreadcrumb() {
      // only show routes with meta.title
      const matched = this.$route.matched.filter(item => item.meta && item.meta.title)
      // const first = matched[0]
      //      const second = matched.length > 1 ? matched[1] : null
      // if (!this.isDashboard(first)) {
      //   matched = [{ path: '/dashboard', meta: { title: 'ホーム' }}].concat(matched)
      // }
      // if (second && second.path === '/brokerageMenu/customerMenu') {
      //   this.$store.dispatch('customerPortalMenuList/setMenuList', { menuList: [{ redirect: 'noRedirect', meta: { title: 'aaa' }, path: 'dummyPath' }] })
      // } else {
      //   this.$store.dispatch('customerPortalMenuList/resetMenuList')
      // }

      const list = matched.filter(item => item.meta && item.meta.title && item.meta.breadcrumb !== false)
      // 現在の要素とその前の要素が同じ値を持つ場合に現在の要素を除外
      this.levelList = list.filter((item, index, array) => {
        return index === 0 || item.meta.title !== array[index - 1].meta.title
      })
    },
    isDashboard(route) {
      const name = route && route.name
      if (!name) {
        return false
      }
      return name.trim().toLocaleLowerCase() === 'Dashboard'.toLocaleLowerCase()
    },
    pathCompile(path) {
      // To solve this problem https://github.com/PanJiaChen/vue-element-admin/issues/561
      const { params } = this.$route
      const toPath = pathToRegexp.compile(path)
      return toPath(params)
    },
    handleLink(item) {
      const { redirect, path } = item
      if (redirect) {
        this.$router.push(redirect)
        return
      }
      this.$router.push(this.pathCompile(path))
    }
  }
}
</script>

<style lang="scss" scoped>
.app-breadcrumb.el-breadcrumb {
  display: inline-block;
  font-size: 14px;
  line-height: 50px;
  margin-left: 8px;

  .no-redirect {
    color: #fff;
    cursor: text;
  }
}
.el-breadcrumb__inner,
.el-breadcrumb__inner a {
  font-weight: 400 ;
  color: #fff;
}
.el-breadcrumb__separator {
  color: #fff;
}
.breadcrumb-wrapper {
  display: flex;
  justify-content: left;
}
.ifa-additional-breadcrumb {
  line-height: 50px;
  color: #fff;
}
</style>
