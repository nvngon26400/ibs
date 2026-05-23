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
    <div class="nowrap-contents">
      <el-date-picker
        :id="id"
        ref="fromPicker"
        v-model="internalValueFrom"
        class="ifa-date-range-picker__custom"
        :class="dateClass"
        type="date"
        :size="size"
        :format="dispFormatFrom"
        :readonly="readonly || ctrlReadonly"
        :disabled="disabled || ctrlDisabled"
        :shortcuts="shortcutsFrom"
        :disabled-date="disabledDateFrom"
        value-format="YYYY/MM/DD"
        :placeholder="fromPlaceHolderString"
        :validate-event="validateEventFlag"
        :clearable="clearable"
        @change="handleChangeFrom"
        @focus="handleFocusFrom"
        @blur="handleBlurFrom"
      ></el-date-picker>
      <span class="ifa-date-range--separator">{{ rangeSeparator }}</span>
      <el-date-picker
        :id="id"
        ref="toPicker"
        v-model="internalValueTo"
        class="ifa-date-range-picker__custom"
        :class="dateClass"
        type="date"
        :size="size"
        :format="dispFormatTo"
        :readonly="readonly || ctrlReadonly"
        :disabled="disabled || ctrlDisabled"
        :shortcuts="shortcutsTo"
        :disabled-date="disabledDateTo"
        value-format="YYYY/MM/DD"
        :placeholder="toPlaceHolderString"
        :validate-event="validateEventFlag"
        :clearable="clearable"
        @change="handleChangeTo"
        @focus="handleFocusTo"
        @blur="handleBlurTo"
      ></el-date-picker>
    </div>
  </el-form-item>
</template>

<script>
import { getPlaceholderString, isReversal, dateFormat } from './js/IfaDatePickerFunction'
import { getMessage } from '@/utils/errorHandler'
import { controlAuthorization } from '@/utils/controlAuth'

export default {
  name: 'IfaDateRangePicker',
  props: {
    id: { type: String, required: false, default: null }, // ID
    modelValue: { type: Array, required: true }, // 入力値(v-model用)
    size: { type: String, required: false, default: '' }, // サイズ指定(small)
    readonly: { type: Boolean, required: false, default: false },
    disabled: { type: Boolean, required: false, default: false },
    pickerOptions: { type: Object, required: false, default: () => ({ }) },
    unlinkPanels: { type: Boolean, required: false, default: true },
    domain: { type: Object, required: false, default: () => ({}) }, // ドメイン情報
    label: { type: String, required: false, default: '' }, // ラベル
    prop: { type: String, required: false, default: '' }, // プロパティ名
    rules: { type: [Object, Array], required: false, default: null }, // 入力チェックルール
    visible: { type: Boolean, required: false, default: true }, // 表示/非表示
    labelClass: { type: [String, Object], required: false, default: '' }, // labelクラス
    dateClass: { type: [String, Object], required: false, default: '' }, // dateクラス
    required: { type: Boolean, required: false, default: undefined }, // 必須項目
    rangeSeparator: { type: String, required: false, default: '～' }, // セパレータ
    noSwap: { type: Boolean, required: false, default: false }, // 自動でスワップしない
    allowOne: { type: Boolean, required: false, default: false }, // required 時に､一方だけの入力を許容する
    clearable: { type: Boolean, required: false, default: false }, // クリアボタン
    controlAuthEnabled: { type: Boolean, required: false, default: true } // 認可制御不使用フラグ（true: 認可制御使用する,false: 認可制御使用しない）
  },
  emits: ['input', 'update:modelValue'],
  data() {
    return {
      internalValueFrom: '',
      internalValueTo: '',
      dispFormatFrom: 'YYYY/MM/DD',
      dispFormatTo: 'YYYY/MM/DD',
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
        propRules.unshift({ required: true, validator: this.dateRangeValidator, trigger: 'change' })
      }
      return propRules
    },
    fromPlaceHolderString() {
      return getPlaceholderString(this.domain.format)
    },
    toPlaceHolderString() {
      return getPlaceholderString(this.domain.format, 1)
    },
    shortcutsFrom() {
      return this.pickerOptions.shortcutsFrom ?? this.shortcutsWrapper ?? undefined
    },
    shortcutsTo() {
      return this.pickerOptions.shortcutsTo ?? this.shortcutsWrapper ?? undefined
    },
    disabledDateFrom() {
      return this.pickerOptions.disabledDateFrom ?? this.pickerOptions.disabledDate ?? undefined
    },
    disabledDateTo() {
      return this.pickerOptions.disabledDateTo ?? this.pickerOptions.disabledDate ?? undefined
    },
    shortcutsWrapper() {
      if (this.pickerOptions.shortcuts && this.pickerOptions.shortcuts.length > 0) {
        return this.pickerOptions.shortcuts.map((item, index) => {
          return {
            text: item.text,
            value: new Date(9998, 11, index + 1)
          }
        })
      }
      return undefined
    }
  },
  watch: {
    modelValue() {
      if (Array.isArray(this.modelValue)) {
        this.internalValueFrom = this.modelValue[0] ?? ''
        this.internalValueTo = this.modelValue[1] ?? ''
      }
    }
  },
  mounted() {
    if (Array.isArray(this.modelValue)) {
      this.internalValueFrom = this.modelValue[0] ?? ''
      this.internalValueTo = this.modelValue[1] ?? ''
    }
    if (this.controlAuthEnabled) {
      controlAuthorization.bind(this)() // 認可制御
    }
  },
  methods: {
    handleFocusFrom() {
      // 編集時はYYYYMMDDのフォーマットとする。
      this.dispFormatFrom = 'YYYYMMDD'
      // 入力開始時にフォームバリデーションを無効にし、値削除後の即時バリデートエラーを抑止する。
      this.validateEventFlag = false
    },
    handleChangeFrom(value) {
      if (this.pickerOptions.shortcuts && this.pickerOptions.shortcuts.length > 0 &&
          /^9998\/12\/[0-3][0-9]$/.test(value)) {
        const index = value.slice(-2) - 1
        const values = this.pickerOptions.shortcuts[index].value()
        this.$nextTick(() => {
          this.internalValueFrom = this.formatDate(values[0])
          this.internalValueTo = this.formatDate(values[1])
          this.$emit('update:modelValue', [this.internalValueFrom ?? '', this.internalValueTo ?? ''])
        })
      } else {
        const formattedDisplayedValue = this.$_getFormattedDateValue(value)
        if (formattedDisplayedValue == null) {
          this.internalValueFrom = ''
        }
        this.$emit('update:modelValue', [this.internalValueFrom ?? '', this.modelValue[1] ?? ''])

        if (!this.allowOne && !this.internalValueTo) {
          this.$refs['toPicker'].focus()
        }
      }
    },
    handleBlurFrom() {
      // 入力完了後はフォームバリデーションを有効にする。
      this.validateEventFlag = true
      // 表示時はdomain指定のフォーマットとする。
      this.dispFormatFrom = dateFormat(this.domain.format)

      // input を emit する
      this.$nextTick(() => {
        this.emitInput()
      })
    },
    handleFocusTo() {
      // 編集時はYYYYMMDDのフォーマットとする。
      this.dispFormatTo = 'YYYYMMDD'
      // 入力開始時にフォームバリデーションを無効にし、値削除後の即時バリデートエラーを抑止する。
      this.validateEventFlag = false
    },
    handleChangeTo(value) {
      if (this.pickerOptions.shortcuts && this.pickerOptions.shortcuts.length > 0 &&
          /^9998\/12\/[0-3][0-9]$/.test(value)) {
        const index = value.slice(-2) - 1
        const values = this.pickerOptions.shortcuts[index].value()
        this.$nextTick(() => {
          this.internalValueFrom = this.formatDate(values[0])
          this.internalValueTo = this.formatDate(values[1])
          this.$emit('update:modelValue', [this.internalValueFrom ?? '', this.internalValueTo ?? ''])
        })
        return
      } else {
        const formattedDisplayedValue = this.$_getFormattedDateValue(value)
        if (formattedDisplayedValue == null) {
          this.internalValueTo = ''
        }
        this.$emit('update:modelValue', [this.modelValue[0] ?? '', this.internalValueTo ?? ''])

        if (!this.allowOne && !this.internalValueFrom) {
          this.$refs['fromPicker'].focus()
        }
      }
    },
    handleBlurTo() {
      // 入力完了後はフォームバリデーションを有効にする。
      this.validateEventFlag = true
      // 表示時はdomain指定のフォーマットとする。
      this.dispFormatTo = dateFormat(this.domain.format)

      // input を emit する
      this.$nextTick(() => {
        this.emitInput()
      })
    },
    emitInput() {
      const formattedDisplayedFromValue = this.$_getFormattedDateValue(this.internalValueFrom)
      const formattedDisplayedToValue = this.$_getFormattedDateValue(this.internalValueTo)
      if (this.allowOne &&
        ((this.internalValueFrom && !this.internalValueTo) ||
        (!this.internalValueFrom && this.internalValueTo))) {
        // 一方だけの入力を許容する場合で､実際に一方だけしか入力されなかった時はスワップ処理を行わない
      } else if (!this.noSwap && isReversal(formattedDisplayedFromValue, formattedDisplayedToValue)) {
        // 開始日>終了日なら入れ替える (noSwap が指定されている場合は入れ替えしない)
        this.internalValueFrom = formattedDisplayedToValue
        this.internalValueTo = formattedDisplayedFromValue
        this.$emit('update:modelValue', [this.internalValueFrom ?? '', this.internalValueTo ?? ''])
      } else {
        this.internalValueFrom = formattedDisplayedFromValue
        this.internalValueTo = formattedDisplayedToValue
      }
      this.$emit('input', [this.internalValueFrom ?? '', this.internalValueTo ?? ''])

      this.validate()
    },
    async validate() {
      try {
        if (this.$refs[this.prop]) {
          await this.$refs[this.prop].validate()
        }
      } catch (error) {
        this.$_logError('validation error occurred.')
      }
    },
    dateRangeValidator(rule, value, callback) {
      const label = [this.label && this.label.match(/^\s+$/) === null ? this.label : '']
      // required が true の時は､項目が選択されていない場合はエラー
      if (!this.internalValueFrom && !this.internalValueTo) {
        callback(getMessage('errors.selected', [`${label}`]))
        return
      }

      if (!this.allowOne && !this.internalValueFrom) {
        callback(getMessage('errors.selected', [`${label}From`]))
        return
      }

      if (!this.allowOne && !this.internalValueTo) {
        callback(getMessage('errors.selected', [`${label}To`]))
        return
      }

      // OK
      callback()
    },
    formatDate(date) {
      if (date && typeof date === 'object' && 'toLocaleDateString' in date) {
        return date.toLocaleDateString('ja-JP', { year: 'numeric', month: '2-digit', day: '2-digit' })
      } else {
        return date
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.form_label :deep(.el-form-item__content) {
  --ifa-input-validation-error-width: 350px;
}
.no-label :deep(.el-form-item__content) {
  margin-left: 0 !important;
}
.ifa-date-range--separator {
  margin: 0 0.5rem;
  align-content: space-around;
}
:deep(.ifa-date-range-picker__custom) {
  --el-date-editor-width: 160px;
  .el-input__inner {
    font-size: 12px;
    line-height: 22px;
    height: 22px;
    text-align: left;
    margin-left: 2em;
  }
}
.nowrap-contents {
  display: inline-flex;
}
</style>

