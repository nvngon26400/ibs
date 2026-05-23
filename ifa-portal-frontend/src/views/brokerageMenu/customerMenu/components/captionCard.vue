<template>
  <!-- タイトル -->
  <el-card
    class="box-card"
    :style="captionStyles"
  >
    <header>
      <slot name="header">
        {{ caption }}
      </slot>
    </header>
    <main>
      <slot></slot>
    </main>
  </el-card>
</template>

<script>
export default {
  props: {
    caption: { type: String, required: false, default: '' },
    sideColor: { type: String, required: false, default: '#092987' },
    sideWidth: { type: String, required: false, default: '6px' },
    backgroundColor: { type: String, required: false, default: '#eee' },
    textColor: { type: String, required: false, default: 'black' },
    textSize: { type: String, required: false, default: '14px' },
    textWeight: { type: String, required: false, default: 'bold' },
    scalingFactor: { type: String, required: false, default: '2' }
  },
  computed: {
    captionStyles() {
      return {
        '--side-color': this.sideColor,
        '--side-width': this.sideWidth,
        '--background-color': this.backgroundColor,
        '--text-color': this.textColor,
        '--text-size': this.textSize,
        '--text-weight': this.textWeight,
        '--sf': this.scalingFactor
      }
    }
  }
}
</script>

<style lang="scss" scoped>
header {
  display: flex;
  align-items: center;
  padding: 0 1rem;
  height: calc(var(--text-size) * var(--sf));
  border-left: var(--side-width) solid var(--side-color);
  color: var(--text-color);
  font-size: var(--text-size);
  font-weight: var(--text-weight);
  background-color: var(--background-color);
  white-space: break-spaces;
}
.box-card {
  margin: 10px 10px 15px 10px;
}
</style>
