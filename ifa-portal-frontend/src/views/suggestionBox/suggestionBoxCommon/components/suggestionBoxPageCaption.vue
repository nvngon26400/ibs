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
    textSize: { type: String, required: false, default: '20px' },
    textWeight: { type: String, required: false, default: 'bold' },
    scalingFactor: { type: String, required: false, default: '1.5' }
  },
  computed: {
    titleStyles() {
      return {
        '--side-color': this.sideColor,
        '--side-width': this.sideWidth,
        '--background-color': this.backgroundColor,
        '--text-size': this.textSize,
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
  height: auto;
  padding-left: 1rem;
  margin-left: 0.5rem;
  border-left: var(--side-width) solid var(--side-color);
  background-color: var(--background-color);
  display: inline-block;
}
.caption-content {
  color: var(--text-color);
  font-size: var(--text-size);
  font-weight: var(--text-weight);
  display: inline-block;
}
</style>
