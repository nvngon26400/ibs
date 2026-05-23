<template>
  <div
    class="sidebar-logo-container"
    :class="{'collapse':collapse}"
  >
    <transition name="sidebarLogoFade">
      <router-link
        v-if="collapse"
        key="collapse"
        class="sidebar-logo-link"
        to="/"
      >
        <img
          v-if="logo"
          :src="logoClosed"
          class="sidebar-logo"
        >
        <h1
          v-else
          class="sidebar-title"
        >{{ title }} </h1>
      </router-link>
      <router-link
        v-else
        key="expand"
        class="sidebar-logo-link"
        to="/"
      >
        <div class="sidebar-logo__inner">
          <img
            v-if="logo"
            :src="logo"
            class="sidebar-logo"
          >
          <span class="sidebar-version">{{ version }}</span>
          <span class="sidebar-title">{{ title }} </span>
        </div>
      </router-link>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'SidebarLogo',
  props: {
    collapse: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      version: 'v1.4.4',
      title: 'IFAポータル',
      logo: require('@/assets/logo.png'),
      logoClosed: require('@/assets/logo2.png')
    }
  }
}
</script>

<style lang="scss" scoped>
.sidebarLogoFade-enter-active {
  transition: opacity 1.5s;
}

.sidebarLogoFade-enter,
.sidebarLogoFade-leave-to {
  opacity: 0;
}

.sidebar-logo-container {
  position: relative;
  width: 100%;
  height: 45px;
  line-height: 50px;
  /* background: #2b2f3a; */
  text-align: center;
  overflow: hidden;
  border-bottom: 1px solid rgb(0 21 41 / 8%);
  background-color: #FFF;
  & .sidebar-logo__inner {
    display: flex;
  }
  & .sidebar-logo-link {
    height: 100%;
    width: 100%;

    & .sidebar-logo {
      /* width: 32px; */
      height: 25px;
      vertical-align: middle;
      margin: 0.7rem 0 0.5rem 0.3rem
    }
    & .sidebar-version {
      position: absolute;
      top: -0.2rem;
      right: 0rem;
      margin-right: 0.2rem;
      margin-top: 0.5rem;
      color: #000;
      font-weight: 600;
      line-height: 1rem;
      font-size:14px;
      vertical-align: top;
    }
    & .sidebar-title {
      position: absolute;
      bottom: -0.2rem;
      right: 0;
      margin-right: 0.2rem;
      margin-bottom: 0.5rem;
      color: #000;
      font-weight: 600;
      line-height: 1rem;
      font-size: 14px;
      vertical-align: bottom;
    }
  }

  &.collapse {
    .sidebar-logo {
      margin-right: 0px;
    }
  }
}
</style>
