<template>
  <div class="external-link-wrapper">
    <el-link
      class="external-link"
      @click="openWindow(url)"
    > {{ label }}</el-link>
    <span class="external-link-icon"></span>
  </div>
</template>

<script>
export default {
  props: {
    url: {
      type: String,
      required: true
    },
    label: {
      type: String,
      required: true
    }
  },
  methods: {
    openWindow(url) {
      window.open(url, '_blank', 'noopener')
    }

  }
}
</script>

<style scoped>
.external-link-wrapper {
  display: inline-block;
}
.external-link {
  padding: 3px 7px;
}
.external-link-icon {
  content: "";
  background-image: url(~@/assets/icons/external-link.svg);
  height: 14px;
  width: 14px;
  display: inline-block;
  vertical-align: middle;
}
</style>
