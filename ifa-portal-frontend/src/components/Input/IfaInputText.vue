<template>
  <el-form-item
    v-if="visible && ctrlVisible"
    :ref="prop"
    class="form_label"
    :class="[
      { 'no-label': !label },
      labelClass
    ]"
    :style="inputTextStyle"
    :label="label"
    :prop="prop"
    :rules="setRule"
  >
    <div class="ifa-input-text__container">
      <el-tooltip
        :disabled="!tooltipEnabled"
        :content="tooltipContent"
        :placement="tooltipPlacement"
        :effect="tooltipEffect"
      >
        <el-input
          :id="id"
          :ref="`IfaInputText`"
          v-model="value"
          :maxlength="domain.formattedDigit"
          :disabled="disabled || ctrlDisabled || validating"
          :readonly="readonly || ctrlReadonly"
          :class="inputClass"
          v-bind="$attrs"
          :show-password="$attrs.type === 'password'"
          @focus="onFocus"
          @blur="setInternalValue(false)"
          @keydown.enter="setInternalValue(true)"
        ></el-input>
      </el-tooltip>
      <slot name="tail"></slot>
    </div>
  </el-form-item>
</template>

<script>
import ifaValidationUtils from '@/utils/ifaValidationUtils'
import { controlAuthorization } from '@/utils/controlAuth'
import { notifyMessage, backToLoginWithDialog, getMessage } from '@/utils/errorHandler'
import store from '@/store'
import { IfaInputTextRequestModel } from './IfaInputTextRequestModel'

export default {
  name: 'IfaInputText',
  inheritAttrs: false,
  props: {
    id: { type: String, required: false, default: null }, // ID
    domain: { type: Object, required: false, default: () => ({}) }, // ドメイン情報
    modelValue: { type: String, required: false, default: '' }, // 入力値(v-model用)
    disabled: { type: Boolean, required: false, default: false }, // disabled
    readonly: { type: Boolean, required: false, default: false }, // 読み取り専用
    label: { type: String, required: false, default: '' }, // ラベル
    prop: { type: String, required: false, default: '' }, // プロパティ名
    visible: { type: Boolean, required: false, default: true }, // 表示/非表示
    labelClass: { type: [String, Object], required: false, default: '' }, // labelクラス
    inputClass: { type: [String, Object], required: false, default: '' }, // inputクラス
    rules: { type: [Object, Array], required: false, default: null }, // 入力チェックルール
    tooltipEnabled: { type: Boolean, required: false, default: false }, // ツールチップ有効
    tooltipContent: { type: String, required: false, default: '' }, // ツールチップコンテンツ
    tooltipPlacement: { type: String, required: false, default: 'bottom' }, // ツールチップ位置
    tooltipEffect: { type: String, required: false, default: 'light' }, // ツールチップテーマ
    required: { type: Boolean, required: false, default: undefined }, // 必須項目
    controlAuthEnabled: { type: Boolean, required: false, default: true }, // 認可制御不使用フラグ（true: 認可制御使用する,false: 認可制御使用しない）
    originalScreenId: { type: String, required: false, default: null }
  },
  emits: ['change', 'update:modelValue'],
  data() {
    return {
      isTyping: false, // 入力中フラグ
      internalValue: '', // 内部値
      specs: {
        precisionSpec: null, // 桁数（精度）
        lengthFixedSpec: false, // 固定長フラグ
        formatSpec: null, // 表示形式
        validateSpec: null // チェック仕様
      },
      ctrlReadonly: false, // 認可制御(読取専用)
      ctrlVisible: true, // 認可制御(表示/非表示)
      ctrlDisabled: false, // 認可制御(編集不可)
      validating: false, // サーババリデーション中フラグ
      inputWidth: ''
    }
  },
  computed: {
    title() {
      // メッセージダイアログのタイトルをIfaRouterの情報から設定
      return this.$store.getters.pageInfo?.label ?? ''
    },
    setRule() {
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

      const propRulesLocal = []
      if (this.required === true && !propRules.find(rule => rule.required)) {
        // required の時､必須チェックが優先順位1位で実施する
        const label = [this.label && this.label.match(/^\s+$/) === null ? this.label : '値']
        propRulesLocal.push({ required: true, message: getMessage('errors.required', label), trigger: 'blur' })
      }

      // 文字種チェック
      if (this.specs.validateSpec) {
        propRulesLocal.push({ validator: this.typeValidator, trigger: 'blur' })
      }
      // レングスチェック
      if (this.specs.precisionSpec) {
        propRulesLocal.push({ validator: this.lengthValidator, trigger: 'blur' })
      }

      // インプット部品の validation を先に行い､その後親コンポーネントの validation を行う
      return [...propRulesLocal, ...propRules]
    },
    controlDisabled() {
      return this.readonly || this.disabled || this.ctrlReadonly || this.ctrlDisabled
    },
    value: {
      get() {
        return this.formatValue(this.internalValue)
      },
      set(value) {
        this.$emit('update:modelValue', value)
      }
    },
    inputTextStyle() {
      return {
        '--ifa-input-validation-error-width': `${this.inputWidth ? this.inputWidth : 200}px`
      }
    }
  },
  watch: {
    modelValue() {
      this.internalValue = this.modelValue != null ? this.modelValue.toString() : ''
    },
    'domain.precision': function() {
      setPrecisionSpec.bind(this)()
    },
    'domain.lengthFixed': function() {
      setLengthFixedSpec.bind(this)()
    },
    'domain.format': function() {
      setFormatSpec.bind(this)()
    },
    'domain.validate': function() {
      setValidateSpec.bind(this)()
    }
  },
  mounted() {
    this.internalValue = this.modelValue != null ? this.modelValue.toString() : ''
    this.acquireInputWidth()
    setPrecisionSpec.bind(this)()
    setLengthFixedSpec.bind(this)()
    setFormatSpec.bind(this)()
    setValidateSpec.bind(this)()
    if (this.controlAuthEnabled) {
      controlAuthorization.bind(this)() // 認可制御
    }
  },
  updated() {
    this.acquireInputWidth()
  },
  methods: {
    setInternalValue(onEnterFlag = false) {
      // blur 時は､タイピングモードを抜ける､enter キー時は､引き続きタイピングモードを継続する
      this.isTyping = onEnterFlag ? this.isTyping : false
      if (!this.controlDisabled) {
        this.onChange(onEnterFlag)
      }
    },
    formatValue(value) {
      if (value === '' || this.isTyping === true || this.specs.formatSpec == null) {
        // 値が無い場合、入力中、表示形式設定なしの場合はそのまま返す。
        return value
      } else {
        // ドメインの表示形式に変換
        return this.specs.formatSpec(this, value, this.specs)
      }
    },
    onFocus() {
      if (!this.controlDisabled) {
        this.isTyping = true
      }
    },
    async onChange(onEnterFlag = false) {
      try {
        if (onEnterFlag && this.$refs[this.prop] && this.required === true) {
          await this.$refs[this.prop].validate()
        }
      } catch (error) {
        this.$_logError('validation error occurred.')
      }

      this.$emit('change', this.internalValue)
    },
    async typeValidator(rule, value, callback) {
      if (!value) {
        callback()
        return
      }
      if (this.validating) {
        while (this.validating) {
          await new Promise(resolve => setTimeout(resolve, 10)) // 10ミリ秒待機
        }
      }
      const validateSpec = this.specs.validateSpec(this)
      if (validateSpec.validationType === 'fullWidthHalfWidth' || validateSpec.validationType === 'fullWidth') {
        // validationType が 'fullWidthHalfWidth' または 'fullWidth' の場合､サーバでバリデーションする
        // サーバからバリデートの結果を受け取るまで操作できない($loading())ようにする
        this.validating = true
        let data = null
        try {
          const requestData = {
            screenId: this.originalScreenId ? this.originalScreenId : store.getters.pageInfo.menuId,
            fieldId: this.id,
            value
          }
          const requestModel = new IfaInputTextRequestModel(requestData)
          data = await this.$_request('IfaInputText#A001', requestModel)
            .catch((error) => { throw error })
        } finally {
          if (data && data.errorLevel === 2) {
            // errorLevel が 2(WARNIG) のときは､バリデーションエラーとしてメッセージを表示する
            callback(data.message)
          } else if (data && data.errorLevel !== 0) {
            if (data.errorLevel === -2) {
              // errorLevel が -2(SYSTEM_ERROR) のときは､ログイン画面に遷移させる
              backToLoginWithDialog(data.message, this.title)
            } else {
              // 通知でメッセージを表示する
              notifyMessage(data.errorLevel, data.message, this.title, data.requestedTime)
            }
            callback()
          } else {
            callback()
          }
          this.validating = false
        }
      } else if (!String(value).match(new RegExp(validateSpec.pattern))) {
        if (validateSpec.validationType === 'threeDigitsNumberWithComma') {
          const numberRegex = /^([0-9],?)+$/
          if (!numberRegex.test(value)) {
            callback(getMessage('errors.required', ['数字']))
          } else {
            callback(getMessage('errors.neqSize', [this.label ? this.label : '値', '3']))
          }
        } else if (validateSpec.validationType === 'fourDigitsAlphaNumberWithComma') {
          const numberRegex = /^([0-9a-zA-Z],?)+$/
          if (!numberRegex.test(value)) {
            callback(getMessage('errors.required', ['英数字']))
          } else {
            callback(getMessage('errors.neqSize', [this.label ? this.label : '値', '4']))
          }
        } else {
          callback(validateSpec.message)
        }
      } else {
        callback()
      }
    },
    lengthValidator(rule, value, callback) {
      if (!value) {
        callback()
        return
      }
      if (this.specs.lengthFixedSpec) {
        if (this.domain && this.domain.formattedDigit && value.length !== this.domain.formattedDigit) {
          callback(getMessage('errors.neqSize', [this.label ? this.label : '値', this.domain.formattedDigit]))
          return
        }
      } else {
        if (this.domain && this.domain.formattedDigit && value.length > this.domain.formattedDigit) {
          callback(getMessage('errors.maxSize', [this.label ? this.label : '値', this.domain.formattedDigit]))
          return
        }
      }
      callback()
    },
    async acquireInputWidth() {
      let retry = 10
      while (!this.inputWidth && retry--) {
        await this.$nextTick()
        this.inputWidth = this.$refs['IfaInputText'].$el.offsetWidth
      }
    }
  }
}
/**
 * 桁数設定を登録する。
 */
const setPrecisionSpec = function() {
  if (this.domain != null && this.domain.precision != null && this.domain.precision > 0) {
    // 桁数が指定されている場合、小数点以下を切り捨てて使用する。
    this.specs.precisionSpec = Math.floor(this.domain.precision)
  } else {
    this.specs.precisionSpec = null
  }
}

/**
 * 固定長設定を登録する。
 */
const setLengthFixedSpec = function() {
  if (this.domain != null && this.domain.precision != null && this.domain.precision > 0) {
    // 桁数が指定されている場合のみ、固定長指定可能。
    this.specs.lengthFixedSpec = this.domain.lengthFixed != null ? this.domain.lengthFixed === true : false
  } else {
    this.specs.lengthFixedSpec = false
  }
}

/**
 * 表示形式設定を登録する。
 */
const setFormatSpec = function() {
  if (this.domain != null && this.domain.format != null && this.domain.format in formatFunctions) {
    this.specs.formatSpec = formatFunctions[this.domain.format].bind(this)
  } else {
    this.specs.formatSpec = null
  }
}

/**
 * チェック仕様設定を登録する。
 */
const setValidateSpec = function() {
  if (this.domain != null && this.domain.validate != null && this.domain.validate in validateFunctions) {
    this.specs.validateSpec = validateFunctions[this.domain.validate].bind(this)
  } else {
    this.specs.validateSpec = null
  }
}

// 表示形式関数
export const formatFunctions = {
  // 0埋め
  zeroPadding: (_self, value, specs) => _self.$_zeroPadding(value, specs.precisionSpec)
}

// チェック仕様関数
export const validateFunctions = {
  // 数字
  number: () => ifaValidationUtils.number(),
  // 全角半角
  fullWidthHalfWidth: () => ifaValidationUtils.fullWidthHalfWidth(),
  // 全角
  fullWidth: () => ifaValidationUtils.fullWidth(),
  // メールアドレス
  mailAddress: () => ifaValidationUtils.mailAddress(),
  // 半角英数字
  alphaNumber: () => ifaValidationUtils.alphaNumber(),
  // 英字（大文字）
  upperAlpha: () => ifaValidationUtils.upperAlpha(),
  // 英数字記号A(+-_./@*#%)
  alphaNumberSpecialPatternA: () => ifaValidationUtils.alphaNumberSpecialPatternA(),
  // 英数字記号B(+-_./@*#%!"$&()=~^\?>,|`[]{}:;<')
  alphaNumberSpecialPatternB: () => ifaValidationUtils.alphaNumberSpecialPatternB(),
  // 銘柄コード
  securityCode: () => ifaValidationUtils.securityCode(),
  // 3桁区切り
  threeDigitsWithComma: (self) => ifaValidationUtils.threeDigitsWithComma(self),
  // 3桁区切り(数字のみ)
  threeDigitsNumberWithComma: () => ifaValidationUtils.threeDigitsNumberWithComma(),
  // 4桁区切り(英数字のみ)
  fourDigitsAlphaNumberWithComma: () => ifaValidationUtils.fourDigitsAlphaNumberWithComma(),
  // 4桁区切り
  fourDigitsWithComma: (self) => ifaValidationUtils.fourDigitsWithComma(self),
  // 英字
  alpha: () => ifaValidationUtils.alpha(),
  // URL
  url: () => ifaValidationUtils.url()
}
</script>

<style lang="scss" scoped>
.form_label :deep(.el-form-item) {
  margin-bottom: 1.2rem;
}
.form_label :deep(.el-form-item__label) {
  width: 135px;
  line-height: 2;
}
.no-label :deep(.el-form-item__content) {
  margin-left: 0 !important;
}
.ifa-input-text__container {
  display: flex;
}
// IfaCommonSearch から指定されるスタイル(ここから)
.search-form__item {
  margin: 0 0.2rem;
  width: 180px;
}
.search-form__itemL {
  margin: 0 0.2rem;
  width: 420px;
}
.small_input {
  width: 80px;
}
.middle_input {
  width: 200px;
}
.large_input {
  width: 330px;
}
.input-readonly {
  :deep(.el-input__wrapper) {
    background-color: var(--el-fill-color-light);
  }
  :deep(.el-input__inner) {
    background-color: rbga(0, 0, 0, 0)
  }
}
// IfaCommonSearch から指定されるスタイル(ここまで)
</style>
