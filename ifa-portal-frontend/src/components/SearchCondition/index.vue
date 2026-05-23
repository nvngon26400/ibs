<template>
  <el-card class="content-card">
    <el-row>
      <slot name="upper-content"></slot>
    </el-row>
    <el-row>
      <el-button
        icon="Search"
        @click="$emit('search')"
      >
        表示
      </el-button>
      <el-button
        class="filter-item search-item"
        @click="$emit('clear')"
      >
        クリア
      </el-button>
    </el-row>
    <el-row
      v-if="displayLowerContent"
      class="lower-content__row"
    >
      <slot name="lower-content"></slot>
    </el-row>
  </el-card>
</template>

<script>
export default {
  props: {
    displayLowerContent: {
      type: Boolean,
      required: true,
      default: false
    }
  },
  emits: ['search', 'clear']
}

</script>

<style scoped>
.content-card {
    margin: 1rem;
  }
.lower-content__row {
  margin-top: 0.5rem;
}
</style>
