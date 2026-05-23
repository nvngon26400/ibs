<template>
  <el-text
    type=""
    v-bind="$attrs"
  >
    <span v-html="codeValue"></span>
  </el-text>
</template>

<script>
export default {
  name: 'IfaText',
  inheritAttrs: false,
  props: {
    codeListId: { type: String, required: true }, // 区分ID
    dispPattern: { type: [Number, String], required: false, default: 1 }, // 表示パターン
    selectPattern: { type: [Number, String], required: false, default: 1 }, // 取得パターン
    codeKey: { type: String, required: true } // 区分値
  },
  emits: ['input'],
  data() {
    return {
      codeValue: '' // 区分値名称
    }
  },
  watch: {
    codeListId() {
      this.setCodeValue()
    },
    dispPattern() {
      this.setCodeValue()
    },
    selectPattern() {
      this.setCodeValue()
    },
    codeKey() {
      this.setCodeValue()
    }
  },
  created() {
    this.setCodeValue()
  },
  methods: {
    setCodeValue() {
      this.codeValue = ''
      const codeKey = this.codeKey === '' ? '$NULL' : this.codeKey
      const codeList = this.$_getCodeList(this.codeListId, this.dispPattern, this.selectPattern)
      if (codeList && codeList.length > 0) {
        const obj = codeList.find(code => code.key === codeKey)
        if (obj) {
          this.codeValue = obj.value ?? ''
        }
      }
      this.$emit('input', {
        key: this.codeKey,
        value: this.codeValue
      })
    }
  }
}
</script>

<style scoped>
.el-text {
  color: #18181A;
}
</style>
