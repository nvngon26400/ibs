<template>
  <el-form-item
    v-if="visible && ctrlVisible"
    :ref="prop"
    class="form_label"
    :class="[
      { 'no-label': !label },
      labelClass
    ]"
    :label="label"
    :prop="prop"
    :rules="setRules"
  >
    <el-date-picker
      :id="id"
      v-model="internalValue"
      class="ifa-date-picker"
      :class="dateClass"
      type="date"
      :size="size"
      :format="dispFormat"
      :readonly="readonly || ctrlReadonly"
      :disabled="disabled || ctrlDisabled"
      :shortcuts="pickerOptions.shortcuts"
      :disabled-date="pickerOptions.disabledDate"
      :clearable="false"
      value-format="YYYY/MM/DD"
      :placeholder="placeHolderString"
      :validate-event="validateEventFlag"
      @change="handleChange"
      @focus="handleFocus"
      @blur="handleBlur"
    ></el-date-picker>
  </el-form-item>
</template>

<script>
import { getPlaceholderString, dateFormat } from './js/IfaDatePickerFunction'
import { getMessage } from '@/utils/errorHandler'
import { controlAuthorization } from '@/utils/controlAuth'

export default {
  name: 'IfaDatePicker',
  props: {
    id: { type: String, required: false, default: null }, // ID
    modelValue: { type: [String, Number], required: true }, // 入力値(v-model用)
    size: { type: String, required: false, default: '' }, // サイズ指定(small)
    readonly: { type: Boolean, required: false, default: false },
    disabled: { type: Boolean, required: false, default: false },
    pickerOptions: { type: Object, required: false, default: () => ({ }) },
    domain: { type: Object, required: false, default: () => ({}) }, // ドメイン情報
    label: { type: String, required: false, default: '' }, // ラベル
    prop: { type: String, required: false, default: '' }, // プロパティ名
    rules: { type: [Object, Array], required: false, default: null }, // 入力チェックルール
    visible: { type: Boolean, required: false, default: true }, // 表示/非表示
    labelClass: { type: [String, Object], required: false, default: '' }, // labelクラス
    dateClass: { type: [String, Object], required: false, default: '' }, // dateクラス
    required: { type: Boolean, required: false, default: undefined }, // 必須項目
    placeholder: { type: String, required: false, default: undefined }, // プレースホルダー
    blurCheck: { type: String, required: false, default: undefined }, // ロストフォーカス必須項目
    controlAuthEnabled: { type: Boolean, required: false, default: true } // 認可制御不使用フラグ（true: 認可制御使用する,false: 認可制御使用しない）
  },
  emits: ['input', 'update:modelValue'],
  data() {
    return {
      internalValue: '',
      dispFormat: 'YYYY/MM/DD',
      validateEventFlag: true,
      ctrlReadonly: false, // 認可制御(読取専用)
      ctrlVisible: true, // 認可制御(表示/非表示)
      ctrlDisabled: false // 認可制御(編集不可)
    }
  },
  computed: {
    setRules() {
      const propRules = []
      // 画面から引き継いだルールの設定
      if (this.rules) {
        const tempRule = this.rules[this.prop] ? this.rules[this.prop] : this.rules
        if (!Array.isArray(tempRule)) {
          propRules.push(tempRule)
        } else {
          tempRule.forEach(rule => {
            propRules.push(rule)
          })
        }
      }

      if (this.required === true && !propRules.find(rule => rule.required)) {
        // required の時､必須チェックが優先順位1位で実施する
        const label = [this.label && this.label.match(/^\s+$/) === null ? this.label : '値']
        propRules.unshift({ required: true, message: getMessage('errors.required', label), trigger: 'change' })
      }
      return propRules
    },
    placeHolderString() {
      if (this.placeholder === 'blank') {
        return ''
      }
      return getPlaceholderString(this.domain.format)
    }
  },
  watch: {
    modelValue() {
      this.internalValue = this.modelValue ?? ''
    }
  },
  mounted() {
    this.internalValue = this.modelValue ?? ''
    if (this.controlAuthEnabled) {
      controlAuthorization.bind(this)() // 認可制御
    }
  },
  methods: {
    handleFocus() {
      // 編集時はYYYYMMDDのフォーマットとする。
      this.dispFormat = 'YYYYMMDD'
      // 入力開始時にフォームバリデーションを無効にし、値削除後の即時バリデートエラーを抑止する。
      this.validateEventFlag = false
    },
    handleChange(value) {
      const formattedDisplayedValue = this.$_getFormattedDateValue(value)
      if (formattedDisplayedValue == null) {
        this.internalValue = ''
      }
      this.$emit('update:modelValue', this.internalValue)
      this.validate()
    },
    handleBlur(currentInstance) {
      // 入力完了後はフォームバリデーションを有効にする。
      this.validateEventFlag = true
      // 表示時はdomain指定のフォーマットとする。
      this.dispFormat = dateFormat(this.domain.format)

      this.$emit('input', this.internalValue ?? '')

      if (this.blurCheck) {
        this.validate()
      }
    },
    async validate() {
      try {
        if (this.$refs[this.prop]) {
          await this.$refs[this.prop].validate()
        }
      } catch (error) {
        this.$_logError('validation error occurred.')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.form_label :deep(.el-form-item__content) {
  --ifa-input-validation-error-width: 220px;
}
.no-label :deep(.el-form-item__content) {
  margin-left: 0 !important;
}
</style>
