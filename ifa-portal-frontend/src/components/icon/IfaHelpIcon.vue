<template>
  <el-tooltip
    :content="message"
    placement="bottom"
    effect="light"
    class="custom-tooltip"
  >
    <el-icon
      :class="['el-icon-info', iconClass]"
      v-bind="$attrs"
    ><QuestionFilled></QuestionFilled></el-icon>
  </el-tooltip>
</template>

<script>

export default {
  props: {
    message: {
      type: String,
      required: false,
      default: ''
    },
    small: {
      required: false,
      type: Boolean,
      default: false
    } // スモールスタイル
  },
  computed: {
    iconClass() {
      return this.small ? 'el-icon-small' : 'el-icon-normal'
    }
  }
}
</script>

<style scoped>
.el-icon-normal {
  font-size: 20px;
  top: 0.3rem;
}
.el-icon-small {
  font-size: 14px;
}
:deep(.custom-tooltip .el-tooltip__popper) {
  margin-left: 30px;
  margin-right: 30px;
  max-width: 10px;
  word-break: break-all;
}
</style>
