<template>
  <div
    id="app"
    v-loading="loading"
  >
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      loading: true
    }
  },
  mounted() {
    this.loading = false
    this.$_techTouch()
  }
}
</script>
