<template>
  <el-popover
    v-if="iconType === 'info'"
    :visible="isInfoMessage"
    :placement="placement"
    width="490"
    transition=""
    :title="title"
  >
    <slot>{{ message }}</slot>
    <template #reference>
      <el-icon
        class="el-icon-info"
        v-bind="$attrs"
        @mouseover="isInfoMessage = true"
        @mouseleave="isInfoMessage = false"
      ><infoFilled></infoFilled></el-icon>
    </template>
  </el-popover>
</template>

<script>

export default {
  props: {
    iconType: {
      type: String,
      required: false,
      default: ''
    },
    message: {
      type: String,
      required: false,
      default: ''
    },
    title: {
      type: String,
      required: false,
      default: ''
    },
    placement: {
      type: String,
      required: false,
      default: 'bottom-end'
    }
  },
  data() {
    return {
      isInfoMessage: false
    }
  }
}
</script>

<style scoped>
.el-icon-info {
  font-size: 30px;
}
</style>
