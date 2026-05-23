<template>
  <!-- タイトル -->
  <div
    class="caption-container"
    :style="titleStyles"
  >
    <span
      class="caption-content"
      :style="contentStyles"
    >{{ text }}</span>
    <slot></slot>
  </div>
</template>

<script>
export default {
  props: {
    text: { type: String, required: true, default: '' },
    sideColor: { type: String, required: false, default: '#092987' },
    sideWidth: { type: String, required: false, default: '6px' },
    backgroundColor: { type: String, required: false, default: 'white' },
    textColor: { type: String, required: false, default: 'black' },
    textSize: { type: String, required: false, default: '14px' },
    textWeight: { type: String, required: false, default: 'bold' },
    scalingFactor: { type: String, required: false, default: '2' }
  },
  computed: {
    titleStyles() {
      return {
        '--side-color': this.sideColor,
        '--side-width': this.sideWidth,
        '--background-color': this.backgroundColor,
        '--text-color': this.textColor,
        '--text-size': this.textSize,
        '--text-weight': this.textWeight,
        '--sf': this.scalingFactor
      }
    },
    contentStyles() {
      return {
        '--text-color': this.textColor,
        '--text-size': this.textSize,
        '--text-weight': this.textWeight
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.caption-container {
  display: flex;
  align-items: center;
  height: calc(var(--text-size) * var(--sf));
  padding-left: 1rem;
  margin-left: 10px;
  margin-top: 10px;
  border-left: var(--side-width) solid var(--side-color);
  background-color: var(--background-color);
}
.caption-content {
  color: var(--text-color);
  font-size: var(--text-size);
  font-weight: var(--text-weight);
}
</style>
