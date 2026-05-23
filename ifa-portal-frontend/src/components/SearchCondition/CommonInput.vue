<template>
  <el-input
    :type="type"
    :name="name"

    @input="updateValue"
  >
  </el-input>
</template>

<script>
export default {
  props: {

    type: { type: String, required: true },
    name: { type: String, required: true }
  },
  emits: ['input'],
  methods: {
    updateValue: function(e) {
      this.$emit('input', e.target.textContent)
    }
  }
}
</script>

<style scoped>
.search-form__item {
  margin: 0 0.2rem;
  width: 180px;
}
.middle_input {
  width: 120px;
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
  line-height: 2
}
</style>
