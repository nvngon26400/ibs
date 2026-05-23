<template>
  <el-popover
    v-if="iconType === 'info'"
    :visible="isInfoMessage"
    :placement="placement"
    width="auto"
    popper-style="max-width:78%; min-width:490px;"
    transition="none"
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

  <el-popover
    v-else-if="iconType !== 'info'"
    :visible="isInfoMessage"
    :placement="placement"
    width="auto"
    popper-style="max-width:78%; min-width:490px;"
    :title="title"
  >
    <slot>{{ message }}</slot>
    <template #reference>
      <el-icon
        class="el-icon-info"
        v-bind="$attrs"
        @mouseover="isInfoMessage = true"
        @mouseleave="isInfoMessage = false"
      ><QuestionFilled></QuestionFilled></el-icon>
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
    },
    showMsgOnMount: {
      type: Boolean,
      required: false,
      default: () => true
    }
  },
  data() {
    return {
      isInfoMessage: false
    }
  },
  mounted() {
    if (this.showMsgOnMount) {
      this.displayMessage()
    }
  },
  methods: {
    displayMessage() {
      if (this.iconType === 'info') {
        this.isInfoMessage = true
      }
    }
  }
}
</script>

<style scoped>
.el-icon-info {
  font-size: 30px;
}
</style>
