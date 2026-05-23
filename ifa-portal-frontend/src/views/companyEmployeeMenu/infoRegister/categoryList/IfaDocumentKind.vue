<template>
  <!-- 登録済カテゴリ -->
  <span>
    <el-checkbox
      v-model="checkValue"
      class="chcek_box"
      style="width:20px"
      @change="handleChange"
    ></el-checkbox>
    <el-form-item
      prop="t9nName"
      :rules="categoryRule"
    >
      <el-form
        :model="form"
      >
        <ifa-input-text
          id="documentKind"
          v-model="form.documentKindName"
          prop="documentKindName"
          type="text"
          size="small"
          original-screen-id="SUB0501_02-01"
          :style="inputStyles"
          :domain="IfaText127DomainModel"
          @input="setValue()"
        ></ifa-input-text>
      </el-form>
    </el-form-item>
  </span>
</template>

<script>
import { getMessage } from '@/utils/errorHandler'
import IfaText127DomainModel from '@/resource/domain/IfaText127DomainModel.json'

export default {
  props: {
    documentKindName: { type: String, required: true },
    value: { type: String, required: true },
    isDeleteBtn: { type: Boolean, required: true }
  },
  emits: ['change-document-kind-name', 'check-status-changed'],
  data() {
    return {
      IfaText127DomainModel: IfaText127DomainModel,
      checkValue: false,
      categoryRule: {
        documentKindName: [{ required: true, trigger: 'blur', validator: this.courseSelectedValidator }]
      },
      form: {
        documentKindName: ''
      }
    }
  },
  computed: {
    inputStyles() {
      return {
        '--width': '235px',
        'vertical-align': 'top'
      }
    }
  },
  watch: {
    documentKindName: {
      handler: function(newVal) {
        this.form.documentKindName = newVal
      },
      immediate: true
    }
  },
  methods: {
    handleChange() {
      this.$emit('check-status-changed', this.checkValue)
    },
    courseSelectedValidator(rule, value, callback) {
      if (!this.isDeleteBtn) {
        if (this.documentKindName === '') {
          callback(getMessage('errors.required', ['カテゴリ']))
          return
        }
        if (this.documentKindName && this.documentKindName.length > 127) {
          callback(getMessage('errors.maxSize', ['カテゴリ', '127']))
          return
        }
      }
      callback()
    },
    setValue() {
      this.$emit('change-document-kind-name', { newName: this.form.documentKindName, value: this.value })
    }
  }
}
</script>

<style lang="scss" scoped>
:deep(.el-form-item) {
  vertical-align: top !important;
}
:deep(.el-input) {
  width: var(--width);
}
</style>
