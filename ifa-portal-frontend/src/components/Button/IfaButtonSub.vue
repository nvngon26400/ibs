<template>
  <el-button
    :size="size"
    :icon="iconName"
    :type="buttonType"
    :class="{ 'ifa-button': isIfaButton }"
    :plain="plain"
    :disabled="disabled"
    :style="isWidthSpecified ? { width: widthSpec } : {}"
    @click="$emit('click')"
  >{{ text }}</el-button>
</template>

<script>
const searchIconName = 'Search'

export default {
  name: 'IfaButton',
  props: {
    text: {
      required: true,
      type: String
    },
    size: {
      required: true,
      type: String
    },

    color: {
      required: false,
      type: String,
      default: ''
    },
    plain: {
      required: false,
      type: Boolean,
      default: false
    },
    search: {
      required: false,
      type: Boolean,
      default: false
    },
    disabled: {
      required: false,
      type: Boolean,
      default: false
    },
    width: {
      required: false,
      type: String,
      default: '0'
    }
  },
  emits: ['click'],
  computed: {
    buttonType() {
      switch (this.color) {
        case 'blue':
          return ''
        case 'green':
          return 'success'
        case 'gray':
          return 'info'
        case 'red':
          return 'danger'
        case 'orange':
          return 'warning'
        case 'white':
          return ''
        default:
          return ''
      }
    },
    isIfaButton() {
      return this.color === ''
    },
    iconName() {
      return this.search ? searchIconName : ''
    },
    isWidthSpecified() {
      return this.width !== '0'
    },
    widthSpec() {
      return this.width + 'px'
    }
  }
}
</script>

<style lang="scss" scoped>
$bg-color: #1da6c6;
$bg-color_plain: #ecf5ff;
$bg-color_light: rgba(29, 167, 198, 0.6);
$border-color: #1da6c6;
$border-color_plain: rgba(29, 167, 198, 0.4);
$border-color_light: rgba(29, 167, 198, 0.2);

.el-button {
  font-size: 14px;
}

.ifa-button {
  color: #fff;
  background-color: $bg-color;
  border-color: $border-color;
  font-size: 14px;
  &:hover,
  &:active {
    background: $bg-color_light;
    border-color: $border-color_light;
    color: #fff;
  }

  &.is-disabled {
    background-color: $bg-color_light;
    border-color: $border-color_light;
  }
  &.is-plain {
    color: $bg-color;
    background: $bg-color_plain;
    border-color: $border-color_plain;
    &.is-disabled {
      color: $bg-color_light;
      background: $bg-color_plain;
      border-color: $border-color_light;
    }
    &:not(.is-disabled):hover,
    &:not(.is-disabled):active {
      color: #fff;
      background: $bg-color;
      border-color: $border-color_light;
    }
  }
}
</style>
