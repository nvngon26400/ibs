<template>
  <el-form-item
    v-if="visible && ctrlVisible"
    class="form_label"
    :class="labelClass"
    :label="label"
    :prop="prop"
  >
    <div>
      <ifa-date-time-picker
        :id="id"
        v-model="internalValue[0]"
        :format="dispFormat"
        :readonly="readonly || ctrlReadonly"
        :disabled="disabled || ctrlDisabled"
        :shortcuts="pickerOptions.shortcuts"
        :disabled-date="pickerOptions.disabledDate"
        :value-format="dispFormat"
        :placeholder="startPlaceHolderString"
        :type="type"
        :class="dateClass"
        size="small"
        style="display: inline-table;"
        @change="handleChange"
        @focus="handleFocus"
        @blur="handleBlur"
      ></ifa-date-time-picker>
      <div style="display: inline-block; text-align: center; padding: 0.5rem;">～</div>
      <ifa-date-time-picker
        :id="id"
        v-model="internalValue[1]"
        :format="dispFormat"
        :readonly="readonly || ctrlReadonly"
        :disabled="disabled || ctrlDisabled"
        :shortcuts="pickerOptions.shortcuts"
        :disabled-date="pickerOptions.disabledDate"
        :value-format="dispFormat"
        :placeholder="endPlaceHolderString"
        :type="type"
        :class="dateClass"
        size="small"
        style="display: inline-table;"
        @change="handleChange"
        @focus="handleFocus"
        @blur="handleBlur"
      ></ifa-date-time-picker>
    </div>
  </el-form-item>
</template>

<script>
import { getPlaceholderString, isReversal, dateFormat } from './js/IfaDatePickerFunction'
import { controlAuthorization } from '@/utils/controlAuth'

export default {
  name: 'IfaDateTimeRangePicker',
  props: {
    id: { type: String, required: false, default: null }, // ID
    modelValue: { type: Array, required: true }, // 入力値(v-model用)
    size: { type: String, required: false, default: 'small' }, // サイズ指定(small)
    readonly: { type: Boolean, required: false, default: false },
    disabled: { type: Boolean, required: false, default: false },
    pickerOptions: { type: Object, required: false, default: () => ({ }) },
    domain: { type: Object, required: false, default: () => ({}) }, // ドメイン情報
    type: { type: String, required: false, default: 'date' },
    label: { type: String, required: false, default: '' }, // ラベル
    prop: { type: String, required: false, default: '' }, // プロパティ名
    visible: { type: Boolean, required: false, default: true }, // 表示/非表示
    labelClass: { type: String, required: false, default: '' }, // labelクラス
    dateClass: { type: String, required: false, default: '' }, // dateクラス
    controlAuthEnabled: { type: Boolean, required: false, default: true } // 認可制御不使用フラグ（true: 認可制御使用する,false: 認可制御使用しない）
  },
  emits: ['input', 'update:modelValue'],
  data() {
    return {
      internalValue: [],
      small: this.size === 'small',
      dispFormat: '',
      isError: false,
      validateEventFlag: true,
      ctrlReadonly: false, // 認可制御(読取専用)
      ctrlVisible: true, // 認可制御(表示/非表示)
      ctrlDisabled: false // 認可制御(編集不可)
    }
  },
  computed: {
    startPlaceHolderString() {
      return getPlaceholderString(this.domain.format)
    },
    endPlaceHolderString() {
      // this.$_logDebug(getPlaceholderString(1))
      return getPlaceholderString(this.domain.format, 1)
    }
  },
  watch: {
    modelValue() {
      this.internalValue = this.modelValue ?? []
    }
  },
  mounted() {
    this.internalValue = this.modelValue ?? []
    this.dispFormat = dateFormat(this.domain.format)
    if (this.controlAuthEnabled) {
      controlAuthorization.bind(this)() // 認可制御
    }
  },
  methods: {
    handleFocus() {
      // フォーカス時にフォームバリデートエラー時のスタイルをクリアする。
      this.isError = false
      // 入力開始時にフォームバリデーションを無効にし、値削除後の即時バリデートエラーを抑止する。
      this.validateEventFlag = false
    },
    handleChange(value) {
      const formattedDisplayedFromValue = this.$_getFormattedDateValue(value[0])
      const formattedDisplayedToValue = this.$_getFormattedDateValue(value[1])
      if (formattedDisplayedFromValue == null || formattedDisplayedToValue == null) {
        this.internalValue = ['', '']
        this.isError = true
      } else {
        if (isReversal(formattedDisplayedFromValue, formattedDisplayedToValue)) {
          this.internalValue[0] = formattedDisplayedToValue
          this.internalValue[1] = formattedDisplayedFromValue
        } else {
          this.internalValue[0] = formattedDisplayedFromValue
          this.internalValue[1] = formattedDisplayedToValue
        }
        this.isError = false
      }
      this.$emit('update:modelValue', this.internalValue)
    },
    handleBlur(currentInstance) {
      this.$emit('input', this.internalValue)
      // 入力完了後はフォームバリデーションを有効にする。
      this.validateEventFlag = true
    }
  }
}
</script>

<style lang="scss" scoped>
.input-error {
  border-color: #f56c6c;
}
</style>

