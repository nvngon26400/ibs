<template>
  <section
    id="app-main"
    class="app-main scrollbar-style"
  >
    <router-view
      :key="key"
      v-slot="{ Component }"
    >
      <transition
        name="fade-transform"
        mode="out-in"
      >
        <keep-alive>
          <component :is="Component"></component>
        </keep-alive>
      </transition>
    </router-view>
  </section>
</template>

<script>
export default {
  name: 'AppMain',
  components: {
  },
  data() {
    return {
      frequentlyAskedQuestionsVisible: false
    }
  },
  computed: {
    key() {
      return this.$route.path
    }
  },
  methods: {
    OpenFaqModal() {
      this.frequentlyAskedQuestionsVisible = true
    },
    closeFaqModal() {
      this.frequentlyAskedQuestionsVisible = false
    }
  }
}
</script>

<style scoped>
.app-main {
  /*50 = navbar  */
  background-color: #f6f7f8;
  height: calc(100vh - 4rem);
  width: 100%;
  position: relative;
  margin-bottom: 1.1rem;
}
.scrollbar-style {
  overflow: scroll;
}
.fixed-header+.app-main {
  padding-top: 50px;
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
