<template>
  <div>Redirecting...</div>
</template>

<script>
export default {
  created() {
    const params = { ...this.$route.query }
    this.$_startShowMenu(this.$route.params.menuId, params)
  }
}
</script>
