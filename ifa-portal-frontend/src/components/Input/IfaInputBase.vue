<template>
  <el-form-item
    v-if="visible"
    :ref="prop"
    class="form_label"
    :class="[
      { 'no-label': !label },
      labelClass
    ]"
    :label="label"
    :prop="prop"
    :rules="setRule"
  >
    <div class="ifa-input_base__wrapper">
      <el-input
        :model-value="formatValue(internalValue)"
        class="ifa-input_base no-spin"
        :class="[
          {
            small,
            'with-control': support,
            clearable,
            disabled
          },
          inputClass
        ]"
        :size="size"
        :clearable="clearable"
        :placeholder="placeholder"
        :readonly="readonly"
        :disabled="disabled"
        v-bind="$attrs"
        @focus="onFocus"
        @input="onInput"
        @blur="setInternalValue(false)"
        @keydown.enter="setInternalValue(true)"
      >
        <template
          v-if="unit !== ''"
          #suffix
        >{{ unit }}</template>
      </el-input>
      <div
        v-if="support"
        class="ifa-input_base__control_wrapper"
        :class="{'without-unit': unit === '',small}"
      >
        <el-button
          :disabled="addButtonDisabled || controlDisabled"
          class="ifa-button"
          :class="small === true ? 'small' : ''"
          @mousedown="mouseDownHandler(addByStep, addButtonDisabled)"
          @mouseup="mouseUpHandler"
          @mouseleave="mouseUpHandler"
        ><el-icon><Plus></Plus></el-icon></el-button>
        <el-button
          :disabled="subButtonDisabled || controlDisabled"
          class="ifa-button secondary-class"
          :class="small === true ? 'small' : ''"
          @mousedown="mouseDownHandler(subtractByStep, subButtonDisabled)"
          @mouseup="mouseUpHandler"
          @mouseleave="mouseUpHandler"
        ><el-icon><Minus></Minus></el-icon></el-button>
      </div>
    </div>
  </el-form-item>
</template>

<script>
import { BigNumber } from 'bignumber.js'
import ifaValidationUtils from '@/utils/ifaValidationUtils.js'
import { getMessage } from '@/utils/errorHandler'

export default {
  name: 'IfaInputBase',
  inheritAttrs: false,
  props: {
    domain: { type: Object, required: false, default: () => ({}) }, // ドメイン情報
    modelValue: { type: [String, Number], required: true }, // 入力値(v-model用)
    unit: { type: [String, Number], required: false, default: '' }, // 単位
    support: { type: Boolean, required: false, default: false }, // 入力補助(増減ボタン)有無指定
    step: { type: [String, Number], required: false, default: 0 }, // 増減数指定
    stepForValidation: { type: [String, Number], required: false, default: null }, // バリデーションチェック用増減値
    stepTable: { type: Array, required: false, default: null }, // 呼び値テーブル
    initialStep: { type: [String, Number], required: false, default: 0 }, // step専用初期値
    size: { type: String, required: false, default: '' }, // サイズ指定(small)
    clearable: { type: Boolean, required: false, default: false }, // 「クリアする」リンクの表示/非表示フラグ
    min: { type: [Number, String], required: false, default: NaN }, // 最小値指定
    max: { type: [Number, String], required: false, default: NaN }, // 最大値指定
    readonly: { type: Boolean, required: false, default: false }, // 読取専用
    disabled: { type: Boolean, required: false, default: false }, // 編集不可
    visible: { type: Boolean, required: false, default: true }, // 表示/非表示
    placeholder: { type: String, required: false, default: '' }, // プレースホルダー
    label: { type: String, required: false, default: '' }, // ラベル
    prop: { type: String, required: false, default: '' }, // プロパティ名
    rules: { type: [Object, Array], required: false, default: null }, // 入力チェックルール
    labelClass: { type: [String, Object], required: false, default: '' }, // labelクラス
    inputClass: { type: [String, Object], required: false, default: '' }, // inputクラス
    required: { type: Boolean, required: false, default: undefined }, // 必須項目
    allowZero: { type: Boolean, required: false, default: false } // 入力値:0でもバリデーションエラーを表示させない
  },
  emits: ['update:modelValue', 'change'],
  data() {
    return {
      isTyping: false, // 入力中フラグ
      internalValue: '', // 内部値
      specs: {
        precisionSpec: 0, // 桁数（精度）
        digitSpec: 0, // 小数点以下桁数
        formatSpec: null, // 表示形式
        validateSpec: null, // チェック仕様
        stepSpec: null // 増減ボタンによる加算/減算数
      },
      stepTableSpec: null,
      small: this.size === 'small',
      timer: null,
      addStep: 0, // ＋ボタンによる加算数(呼び値テーブル利用時)
      subStep: 0, // ―ボタンによる減算数(呼び値テーブル利用時)
      validateFailed: false,
      bigNumberFormat: {
        prefix: '',
        decimalSeparator: '.',
        groupSeparator: ',',
        groupSize: 3,
        secondaryGroupSize: 0,
        secondaryGroupSeparator: '',
        fractionGroupSize: 0,
        suffix: ''
      }
    }
  },
  computed: {
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

      // 最大/最小チェック
      propRulesLocal.push({ validator: this.rangeValidator, trigger: 'blur' })

      // 刻み幅チェック(入力補助(増減ボタン)有の場合のみ)
      if (this.support) {
        propRulesLocal.push({ validator: this.stepValidator, trigger: 'blur' })
      }

      // 有効小数桁数チェック
      propRulesLocal.push({ validator: this.digitValidator, trigger: 'blur' })

      // インプット部品の validation を先に行い､その後親コンポーネントの validation を行う
      return [...propRulesLocal, ...propRules]
    },
    addButtonDisabled() {
      // 未入力の場合('')プラスボタンのみ活性になる。
      if (new BigNumber(this.internalValue).isNaN() && this.bigNumberMax.gt(0)) {
        return false
      }
      const stepValue = this.stepTable ? this.addStep : this.specs.stepSpec
      if ((this.bigNumberAddByStepUtil(new BigNumber(this.internalValue), new BigNumber(stepValue))).isGreaterThan(this.bigNumberMax)) {
        // チェック用増減値と増減値が異なる場合のみ、チェック用増減値だけ増やしても上限を超えないかチェック
        if (this.stepForValidation && (new BigNumber(this.internalValue).plus(new BigNumber(this.stepForValidation)).lte(this.bigNumberMax))) {
          return false
        }

        // 「+」ボタンの非活性化
        return true
      }
      return false
    },
    subButtonDisabled() {
      const stepValue = (this.stepTable && this.subStep) ? this.subStep : this.specs.stepSpec
      if (new BigNumber(this.internalValue).isNaN() ||
        (this.bigNumberSubtractByStepUtil(new BigNumber(this.internalValue), new BigNumber(stepValue)).isLessThan(this.bigNumberMin))) {
        // 「-」ボタンの非活性化
        return true
      }
      return false
    },
    controlDisabled() {
      return this.readonly || this.disabled
    },
    bigNumberMin() {
      if (!isNaN(this.min)) {
        return BigNumber(this.min)
      } else if (this.specs.precisionSpec != null && this.specs.precisionSpec > 0) {
        return new BigNumber(10).exponentiatedBy(this.specs.precisionSpec - this.specs.digitSpec)
          .minus(new BigNumber(0.1).exponentiatedBy(this.specs.digitSpec))
          .negated()
      } else {
        return BigNumber(NaN)
      }
    },
    bigNumberMax() {
      if (!isNaN(this.max)) {
        return BigNumber(this.max)
      } else if (this.specs.precisionSpec != null && this.specs.precisionSpec > 0) {
        return new BigNumber(10).exponentiatedBy(this.specs.precisionSpec - this.specs.digitSpec)
          .minus(new BigNumber(0.1).exponentiatedBy(this.specs.digitSpec))
      } else {
        return BigNumber(NaN)
      }
    },
    initialStepValue() {
      if (this.isNumber(this.initialStep)) {
        return this.initialStep
      } else {
        return 0
      }
    }
  },
  watch: {
    modelValue() {
      if (this.modelValue != null && !isNaN(this.modelValue)) {
        this.internalValue = this.getFixedValue(this.modelValue)
      }
    },
    internalValue() {
      // 呼び値テーブルが設定されている場合のみ、適宜有効小数桁/ステップ幅を見直す。
      if (this.stepTable) {
        setDigitSpec.bind(this)()
      }
    },
    stepTable: {
      handler() {
        setDigitSpec.bind(this)()
      },
      deep: true
    },
    step() {
      setStepSpec.bind(this)()
    },
    'domain.precision': function() {
      setPrecisionSpec.bind(this)()
    },
    'domain.digit': function() {
      setDigitSpec.bind(this)()
    },
    'domain.format': function() {
      setFormatSpec.bind(this)()
    },
    'domain.validate': function() {
      setValidateSpec.bind(this)()
    }
  },
  mounted() {
    this.internalValue = this.modelValue != null && !isNaN(this.modelValue) ? this.modelValue.toString() : ''
    setPrecisionSpec.bind(this)()
    setDigitSpec.bind(this)()
    setStepSpec.bind(this)()
    setFormatSpec.bind(this)()
    setValidateSpec.bind(this)()
    this.internalValue = this.getFixedValue(this.internalValue)
  },
  methods: {
    mouseDownHandler(func, isButtonDisabled) {
      if (isButtonDisabled || this.controlDisabled) return
      func()
      if (this.timer != null) clearInterval(this.timer)
      const interval = 100
      const autoStepLimit = 800
      let holdTIme = 0
      this.timer = setInterval(() => {
        holdTIme += interval
        if (holdTIme > autoStepLimit) {
          func()
        }
      }, interval)
    },
    mouseUpHandler() {
      if (this.timer == null) return
      clearInterval(this.timer)
    },
    setInternalValue(onEnterFlag = false) {
      // blur 時は､タイピングモードを抜ける､enter キー時は､引き続きタイピングモードを継続する
      this.isTyping = onEnterFlag ? this.isTyping : false
      if (!this.controlDisabled) {
        if (!isNaN(this.internalValue) && this.internalValue !== '') {
          this.internalValue = this.getFixedValue(this.internalValue)
        }
        this.$nextTick(() => {
          this.emitInput(true)
        })
      }
      this.$nextTick(() => {
        this.checkValidateFailed()
      })
    },
    async checkValidateFailed() {
      while (this.$refs[this.prop] && this.$refs[this.prop].validateState === 'validating') {
        await this.wait(10)
      }
      this.validateFailed = this.$refs[this.prop].validateState === 'error'
    },
    async wait(n) {
      await new Promise(resolve => {
        setTimeout(() => resolve(), n)
      })
    },
    formatValue(value) {
      if (value === '' || this.isTyping === true || this.specs.formatSpec == null) {
        // 値が無い場合、入力中、表示形式設定なしの場合はそのまま返す。
        return value
      } else {
        // ドメインの表示形式に変換
        return this.specs.formatSpec(this, this.getFixedValue(value), this.specs)
      }
    },
    addByStep() {
      if (!this.controlDisabled && !this.addButtonDisabled) {
        if (new BigNumber(this.internalValue).isNaN()) {
          this.internalValue = this.getFixedValue(this.initialStepValue)
        } else if (new BigNumber(this.internalValue).lt(this.bigNumberMin)) {
          this.internalValue = this.getFixedValue(this.bigNumberMin)
          setDigitSpec.bind(this)()
          const stepValue = this.stepTable ? new BigNumber(this.addStep) : new BigNumber(this.specs.stepSpec)
          this.internalValue = this.getFixedValue(this.snapUp(new BigNumber(this.internalValue), stepValue))
        } else {
          const stepValue = this.stepTable ? new BigNumber(this.addStep) : new BigNumber(this.specs.stepSpec)
          this.internalValue = this.getFixedValue(this.bigNumberAddByStepUtil(new BigNumber(this.internalValue), stepValue))
        }

        // 最大・最小の範囲にない場合、範囲内に設定する(最大 < 最小の場合はなにもしない)
        const currentInternalValue = new BigNumber(this.internalValue)

        if (currentInternalValue.isGreaterThanOrEqualTo(this.bigNumberMin) && currentInternalValue.isGreaterThan(this.bigNumberMax)) {
          if (this.stepForValidation) { // チェック用ステップを設定している場合
            this.internalValue = this.snapDown(this.bigNumberMax, new BigNumber(this.stepForValidation))
          } else { // チェック用ステップを設定していない場合
            this.internalValue = this.getFixedValue(this.bigNumberMax)
            setDigitSpec.bind(this)()
            const stepValue = this.stepTable ? new BigNumber(this.subStep) : new BigNumber(this.specs.stepSpec)
            this.internalValue = this.getFixedValue(this.snapDown(new BigNumber(this.internalValue), stepValue))
          }
        } else if (currentInternalValue.isLessThan(this.bigNumberMin) && currentInternalValue.isLessThanOrEqualTo(this.bigNumberMax)) {
          if (this.stepForValidation) { // チェック用ステップを設定している場合
            this.internalValue = this.snapUp(this.bigNumberMin, new BigNumber(this.stepForValidation))
          } else { // チェック用ステップを設定していない場合
            this.internalValue = this.getFixedValue(this.bigNumberMin)
            setDigitSpec.bind(this)()
            const stepValue = this.stepTable ? new BigNumber(this.addStep) : new BigNumber(this.specs.stepSpec)
            this.internalValue = this.getFixedValue(this.snapUp(new BigNumber(this.internalValue), stepValue))
          }
        }

        this.$nextTick(() => {
          this.emitInput(true)
        })
      }
    },
    bigNumberAddByStepUtil(base, step) {
      const beforeSnapValue = base.plus(step)
      const result = this.snapDown(beforeSnapValue, step)
      return result
    },
    subtractByStep() {
      if (!this.controlDisabled && !this.subButtonDisabled) {
        if (new BigNumber(this.internalValue).isNaN()) {
          this.internalValue = this.getFixedValue(0)
        } else if (new BigNumber(this.internalValue).gt(this.bigNumberMax)) {
          if (this.stepForValidation) { // チェック用ステップを設定している場合
            this.internalValue = this.snapDown(this.bigNumberMax, new BigNumber(this.stepForValidation))
          } else { // チェック用ステップを設定していない場合
            this.internalValue = this.getFixedValue(this.bigNumberMax)
            setDigitSpec.bind(this)()
            const stepValue = this.stepTable ? new BigNumber(this.subStep) : new BigNumber(this.specs.stepSpec)
            this.internalValue = this.getFixedValue(this.snapDown(new BigNumber(this.internalValue), stepValue))
          }
        } else {
          const stepValue = this.stepTable ? new BigNumber(this.subStep) : new BigNumber(this.specs.stepSpec)
          this.internalValue = this.getFixedValue(this.bigNumberSubtractByStepUtil(new BigNumber(this.internalValue), stepValue))
        }
        this.$nextTick(() => {
          this.emitInput(true)
        })
      }
    },
    bigNumberSubtractByStepUtil(base, step) {
      const beforeSnapValue = base.minus(step)
      const result = this.snapUp(beforeSnapValue, step)
      return result
    },
    snapDown(base, step) {
      const snapOffset = base.mod(step)
      const result = base.minus(snapOffset)
      return result
    },
    snapUp(base, step) {
      const snapOffset = step.minus(base.mod(step)).mod(step)
      const result = base.plus(snapOffset.mod(step))
      return result
    },
    isStepSnappedInRange(value, min, max, step) {
      return value.gte(min) && value.lte(max) && value.mode(step).isZero()
    },
    onFocus() {
      if (!this.controlDisabled) {
        this.isTyping = true
        if (this.internalValue !== '') {
          this.internalValue = this.getFixedValue(this.internalValue)
        }
      }
    },
    onInput(value) {
      if (!this.isTyping) {
        this.isTyping = true
        this.internalValue = this.getFixedValue(value)
      } else {
        this.internalValue = value
      }
    },
    async emitInput(onEnterFlag = false) {
      if (this.internalValue === '') {
        this.$emit('update:modelValue', this.internalValue)
        this.$emit('change', this.internalValue)
      } else {
        const returnValue = this.getFixedValue(this.internalValue)
        this.$emit('update:modelValue', returnValue)
        this.$emit('change', returnValue)
      }

      try {
        if (onEnterFlag && this.$refs[this.prop]) {
          await this.$refs[this.prop].validate()
        }
      } catch (error) {
        this.$_logError('validation error occurred.')
      }
    },
    typeValidator(rule, value, callback) {
      const validateSpec = this.specs.validateSpec(this)
      if (this.internalValue && !String(this.internalValue).match(new RegExp(validateSpec.pattern))) {
        callback(validateSpec.message)
        return
      }
      callback()
    },
    digitValidator(rule, value, callback) {
      if (!this.internalValue) {
        callback()
        return
      }
      const digitVal = String(this.internalValue).split('.')[1] || ''
      if (this.specs.digitSpec > 0) {
        if (digitVal.length > this.specs.digitSpec) {
          callback(getMessage('errors.maxSize', ['小数', this.specs.digitSpec]))
          return
        }
      } else {
        if (digitVal) {
          callback(getMessage('errors.required', ['整数']))
          return
        }
      }
      callback()
    },
    rangeValidator(rule, value, callback) {
      const result = new BigNumber(this.internalValue)
      if (!this.allowZero || !result.isEqualTo(0)) {
        if (result.isLessThan(this.bigNumberMin) || result.isGreaterThan(this.bigNumberMax)) {
          callback(getMessage(
            'errors.frs.orderValue.outOfRange',
            [this.label ? this.label : '値', this.bigNumberMin.toFormat(this.bigNumberFormat), this.bigNumberMax.toFormat(this.bigNumberFormat)]
          ))
          return
        }
      }

      callback()
    },
    stepValidator(rule, value, callback) {
      const decimalValue = new BigNumber(this.internalValue)
      const decimalStep = this.stepForValidation ? new BigNumber(this.stepForValidation) : new BigNumber(this.specs.stepSpec)
      if (this.internalValue && BigNumber(0).comparedTo(decimalValue.modulo(decimalStep)) !== 0) {
        callback(getMessage('errors.cmn.increments.outOfRange', [decimalStep.toFormat(this.bigNumberFormat)]))
        return
      }
      callback()
    },
    isNumber(value) {
      return value !== null && !isNaN(value) && /^[+]?[\d.]+$/.test(value)
    },
    /**
     * カンマなしの値(String)を取得する。
     */
    getFixedValue(val) {
      if (isNaN(val)) {
        return val
      }
      return val ? new BigNumber(String(val).replace(/,/g, '')).toFixed() : val
    },
    /**
     * 国内株と外国株でstepTableのフォーマットが異なるため、フォーマットを外国株に合わせる
     */
    convertStepTable(stepTable) {
      const convStepTable = []
      for (let i = 0; i < stepTable.length; i++) {
        const row = stepTable[i]
        // 外国株の場合
        if (row.tickSize) {
          return stepTable
        } else if (row.orderPriceUnit) {
          convStepTable.push({
            basePriceFrom: row.over,
            basePriceTo: row.within,
            basePriceType: 'GREATER_LESS_EQUAL',
            tickSize: row.orderPriceUnit
          })
        } else {
          this.$_logWarn('Illegal StepTable Format.', row)
          break
        }
      }
      return convStepTable
    },
    isStepTableRange(basePriceType, current, basePriceFrom, basePriceTo) {
      const from = new BigNumber(basePriceFrom)
      const to = new BigNumber(basePriceTo)

      // 超～以下の場合
      if (basePriceType === 'GREATER_LESS_EQUAL') {
        if ((from.isNaN() || (from.isZero() && current.isZero()) || current.isGreaterThan(from)) && current.isLessThanOrEqualTo(to)) {
          return true
        }
      } else {
        if (current.isGreaterThanOrEqualTo(from) && (to.isNaN() || current.isLessThan(to))) {
          return true
        }
      }
      return false
    },
    isAddSubStepRange(isSub, current, basePriceFrom, basePriceTo) {
      const from = new BigNumber(basePriceFrom)
      const to = new BigNumber(basePriceTo)

      // 加算値の算出の場合
      if (!isSub) {
        if ((from.isNaN() || (from.isZero() && current.isZero()) || current.isGreaterThan(from)) && current.isLessThanOrEqualTo(to)) {
          return true
        }
      } else {
        if (current.isGreaterThanOrEqualTo(from) && (to.isNaN() || current.isLessThan(to))) {
          return true
        }
      }
      return false
    }
  }
}

/**
 * 桁数設定を登録する。
 */
const setPrecisionSpec = function() {
  if (this.domain != null && this.domain.precision != null) {
    // 桁数の設定値がある時。小数桁は切り捨てて使用する。
    this.specs.precisionSpec = Number(new BigNumber(this.domain.precision).toFixed(0, 1))
  } else {
    this.specs.precisionSpec = null
  }
}

/**
 * 小数桁設定を登録する。
 */
const setDigitSpec = function() {
  // 呼び値テーブルの設定がある場合
  if (this.stepTable) {
    // 国内株、外国株でstepTableのformatを統一する。
    this.specs.stepTableSpec = this.convertStepTable(this.stepTable)

    const unformattedVal = this.internalValue
      ? String(this.internalValue).replace(/,/g, '')
      : this.initialStepValue

    // 呼び値テーブルから現在地のstep幅を取得する。
    const current = new BigNumber(unformattedVal)
    let tickSize = 0
    for (let i = 0; i < this.specs.stepTableSpec.length; i++) {
      const row = this.specs.stepTableSpec[i]
      if (this.isStepTableRange(row.basePriceType, current, row.basePriceFrom, row.basePriceTo, false)) {
        tickSize = row.tickSize
        break
      }
    }
    this.specs.stepSpec = tickSize

    // 取得したStep幅の小数桁数を有効小数桁数とする。
    const val = String(this.specs.stepSpec).split('.')
    this.specs.digitSpec = val[1] ? val[1].length : 0

    // 加算値の算出
    const addCurrent = current.plus(new BigNumber(this.specs.stepSpec))
    let addStep = 0
    for (let i = 0; i < this.specs.stepTableSpec.length; i++) {
      const row = this.specs.stepTableSpec[i]
      if (this.isAddSubStepRange(false, addCurrent, row.basePriceFrom, row.basePriceTo)) {
        addStep = row.tickSize
        break
      }
    }
    this.addStep = addStep

    // 減算値の算出
    const subCurrent = current.minus(new BigNumber(this.specs.stepSpec))
    let subStep = 0
    for (let i = 0; i < this.specs.stepTableSpec.length; i++) {
      const row = this.specs.stepTableSpec[i]
      if (this.isAddSubStepRange(true, subCurrent, row.basePriceFrom, row.basePriceTo)) {
        subStep = row.tickSize
        break
      }
    }
    this.subStep = subStep
  } else if (this.domain != null && this.domain.digit != null && this.domain.digit >= 0) {
    // 小数点以下の桁数の設定値がある時。小数桁は切り捨てて使用する。
    this.specs.digitSpec = Number(new BigNumber(this.domain.digit).toFixed(0, 1))
  } else {
    this.specs.digitSpec = 0
  }
}

/**
 * 増減値設定を登録する。
 */
const setStepSpec = function() {
  // 呼び値テーブルは小数桁と合わせて設定
  if (this.stepTable) {
    return
  }

  if (this.isNumber(this.step) && Number(this.step) !== 0) {
    // 増減値の設定がある時
    this.specs.stepSpec = this.step
  } else {
    // 増減値の設定がない時
    if (this.specs.digitSpec === 0) {
      // 整数の時
      this.specs.stepSpec = 1
    } else {
      // 小数の時
      this.specs.stepSpec = 1 / 10 ** this.specs.digitSpec
    }
  }
}

/**
 * 表示形式設定を登録する。
 */
const setFormatSpec = function() {
  if (
    this.domain != null &&
    this.domain.format != null &&
    this.domain.format in formatFunctions
  ) {
    this.specs.formatSpec = formatFunctions[this.domain.format].bind(this)
  } else {
    this.specs.formatSpec = null
  }
}

/**
 * チェック仕様設定を登録する。
 */
const setValidateSpec = function() {
  if (
    this.domain != null &&
    this.domain.validate != null &&
    this.domain.validate in validateFunctions
  ) {
    this.specs.validateSpec = validateFunctions[this.domain.validate].bind(this)
  } else {
    this.specs.validateSpec = null
  }
}

// 表示形式関数
export const formatFunctions = {
  // カンマ区切り0埋め
  withCommaZeroPadding: (_self, value, specs) => _self.$_withCommaZeroPadding(value, specs.digitSpec),
  // カンマ区切り0埋めなし
  withCommaNoneZeroPadding: (_self, value) => _self.$_withCommaNoneZeroPadding(value),
  // カンマ区切り無し0埋め
  noneWithCommaZeroPadding: (_self, value, specs) => _self.$_noneWithCommaZeroPadding(value, specs.digitSpec),
  // カンマ区切り無し0埋めなし
  noneWithCommaNoneZeroPadding: (_self, value) => _self.$_noneWithCommaNoneZeroPadding(value),
  // 符号付きカンマ区切り0埋め
  signedWithCommaZeroPadding: (_self, value, specs) => _self.$_signedWithCommaZeroPadding(value, specs.digitSpec),
  // 符号付きカンマ区切り0埋めなし
  signedWithCommaNoneZeroPadding: (_self, value) => _self.$_signedWithCommaNoneZeroPadding(value),
  // カンマ区切り整数
  withCommaInteger: (_self, value) => _self.$_withCommaInteger(value),
  // 符号付きカンマ区切り整数
  signedWithCommaInteger: (_self, value) => _self.$_signedWithCommaInteger(value)
}

// チェック仕様関数
export const validateFunctions = {
  // 数値(整数)
  numberInteger: () => ifaValidationUtils.numberInteger(),
  // 数値(小数)
  numberFloat: () => ifaValidationUtils.numberFloat()
}
</script>

<style lang="scss" scoped>
.ifa-input_base__wrapper {
  display: inline-block;
  min-width: 250px;
  max-width: 350px;
}
.no-label :deep(.el-form-item__content) {
  margin-left: 0 !important;
}
.ifa-input_base {
  display: inline-block;
  margin-right: 5px;
  &.with-control {
    width: calc(100% - 145px);
  }
}
.ifa-input_base :deep(.el-input__inner) {
  text-align: right;
  vertical-align: middle;
  display: inline-block;
  margin-right: 5px;
}
.ifa-input_base :deep(.el-input__suffix) {
  vertical-align: middle;
  line-height: 40px;
}
.ifa-input_base.el-input--small :deep(.el-input__suffix) {
  vertical-align: middle;
  line-height: 32px;
}
.ifa-input_base :deep(.el-input__suffix-inner) {
  color: #18181a;
}
.ifa-input_base.disabled :deep(.el-input__suffix-inner) {
  color: #C0C4CC;
}
.form_label :deep(.el-form-item) {
  margin-bottom: 1.2rem;
}
.form_label :deep(.el-form-item__label) {
  width: 135px;
  line-height: 2
}
.no-spin::-webkit-inner-spin-button,
.no-spin::-webkit-outer-spin-button {
  -webkit-appearance: none;
  margin: 0;
  // -moz-appearance: textfield;
}

.ifa-input_base__control_wrapper {
  display: inline-block;
  vertical-align: middle;
  width: 100px;
  margin-left: 19px;
  &.small {
    width: 80px;
  }
  .ifa-input_base__control {
    background: #ffffff 0% 0% no-repeat padding-box;
    border: 1px solid #005fcc;
    border-radius: 4px;
    opacity: 1;
    width: 35px;
    height: 35px;
    display: inline-block;
    line-height: 1.1rem;
    vertical-align: bottom;
    user-select: none;
    margin: 0px 5px 3px 5px;
    &:hover {
      cursor: pointer;
      background: #edf8ff 0% 0% no-repeat padding-box;
    }
    &.disabled {
      background: #f5f6f7 0% 0% no-repeat padding-box;
      border-color: #a7b1c3;
      color: #a7b1c3;
      cursor: default;
      &:hover {
        pointer-events: none;
        background-color: #f5f6f7;
      }
      &::before {
        position: relative;
        top: 50%;
        left: 50%;
        content: "";
        display: inline-block;
        width: 16px;
        height: 16px;
        border-top: 2px solid #a7b1c3 !important;
        transform: translateX(-50%);
      }
      &:after {
        border-top: 2px solid #a7b1c3 !important;
      }
    }
    &.small {
      width: 20px;
      height: 20px;

      &.ifa-input_base__add-button::before,
      &.ifa-input_base__add-button::after,
      &.ifa-input_base__subtract-button::before {
        position: relative;
        top: calc(70% - 7px);
        left: 5%;
        content: "";
        display: inline-block;
        width: 10px;
        height: 10px;
        border-top: 2px solid #005fcc;
        transform: translateX(-50%);
        margin-left: 8px;
      }
      &.ifa-input_base__add-button:after {
        top: -96%;
        left: -45%;
        transform: rotate(90deg);
      }
    }
    &.ifa-input_base__add-button::before,
    &.ifa-input_base__add-button::after,
    &.ifa-input_base__subtract-button::before {
      position: relative;
      top: 50%;
      left: 50%;
      content: "";
      display: inline-block;
      width: 14px;
      height: 14px;
      border-top: 2px solid #005fcc;
      transform: translateX(-50%);
    }
    &.ifa-input_base__add-button:after {
      top: 32%;
      left: -31%;
      transform: rotate(90deg);
    }
  }
}

.ifa-button {
  font-size: 18px;
  border: 1px solid #005fcc;
  color: #005fcc;
  width: 35px;
  height: 35px;
  background: #ffffff;
  &:hover {
    background: #edf8ff;
  }
  &.is-disabled {
    background: #f5f6f7;
    border-color: #a7b1c3;
    color: #97a0b0;
    &:hover {
      background-color: #f5f6f7;
    }
  }
  &.small {
    width: 20px;
    height: 20px;
    font-size: 15px;
    padding: 8px;
  }
}
</style>
