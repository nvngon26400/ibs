<template>
  <el-form-item
    :ref="prop"
    :class="[
      { 'no-label': !label },
      labelClass
    ]"
    :label="label"
    :prop="prop"
    :rules="setRules"
  >
    <el-radio-group
      v-model="periodTypeInput"
      :disabled="disabled"
      @change="handleChangePeriodType($event)"
    >
      <el-radio-button
        v-if="showTodayButton"
        :label="false"
        :disabled="disabledTodayButton"
      >当日中</el-radio-button>
      <el-radio-button
        v-if="showPeriodButton"
        :label="true"
        :disabled="disabledPeriodButton"
      >期間指定</el-radio-button>
    </el-radio-group>
    <ifa-date-picker
      v-if="periodTypeInput"
      v-model="periodInput"
      class="ifa-date-picker"
      :class="dateClass"
      tabindex="0"
      type="date"
      :domain="IfaDate8DomainModel"
      :picker-options="pickerOptions"
      style="margin-left: 1rem;"
      @input="handleInputPeriod($event)"
    ></ifa-date-picker>
  </el-form-item>
</template>
<script>
import { getMessage } from '@/utils/errorHandler'
import IfaDate8DomainModel from '@/resource/domain/IfaDate8DomainModel.json'

export default {
  name: 'IfaPeriodSelector',
  props: {
    label: { type: String, required: false, default: '期間' }, // 入力値(ラベル名)
    period: { type: String, required: true },
    periodType: { type: Boolean, required: false, default: false }, // 期間指定（True：期間指定、False：当日中）
    prop: { type: String, required: false, default: '' }, // プロパティ名
    rules: { type: [Object, Array], required: false, default: null }, // 入力チェックルール
    pickerOptions: { type: Object, required: false, default: () => ({ }) },
    disabled: { type: Boolean, required: false, default: false },
    buttonOptions: { type: Object, required: false, default: () => {} },
    labelClass: { type: [String, Object], required: false, default: '' }, // labelクラス
    dateClass: { type: [String, Object], required: false, default: '' }, // dateクラス
    required: { type: Boolean, required: false, default: undefined } // 必須項目
  },
  emits: ['update:period', 'update:periodType'],
  data() {
    return {
      periodInput: null,
      periodTypeInput: null,
      IfaDate8DomainModel: IfaDate8DomainModel,
      isRequired: false // required = true になっている validator あり
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

      const propRulesLocal = []
      if (this.required === true && !propRules.find(rule => rule.required)) {
        // この validator は required の '*' を表示させるために設定する
        // 裏で動いて予期しないメッセージを表示しないように常に正常を返す validator を設定しておく
        propRulesLocal.push({ required: true, validator: (r, v, cb) => { cb() }, trigger: 'change' })
      }

      // required は､プロパティで指定されるものと､親コンポーネントがセットした validator の中で設定された2種類がある
      // その為､どちらかに required が存在している場合に isRequired でフラグ管理する
      this.setIsRequired(this.required === true || !!propRules.find(rule => rule.required))

      // 期間指定チェック
      // 期間指定の validation は､ periodType と period のセットで行う必要がある
      // その為､ここで push している validator をデフォルトとして使用する
      propRulesLocal.push({ validator: this.periodValidator, trigger: 'change' })

      // インプット部品の validation を先に行い､その後親コンポーネントの validation を行う
      return [...propRulesLocal, ...propRules]
    },
    showTodayButton() {
      // buttonOptions.today に 'hide' が設定されたときは当日中ボタンは非表示になる
      return !(this.buttonOptions?.today === 'hide')
    },
    disabledTodayButton() {
      // buttonOptions.range に 'disabled' が設定されたときは当日中ボタンは無効になる
      return !!this.buttonOptions && this.buttonOptions.today === 'disabled'
    },
    showPeriodButton() {
      // buttonOptions.today に 'hide' が設定されたときは期間指定ボタンは非表示になる
      return !(this.buttonOptions?.period === 'hide')
    },
    disabledPeriodButton() {
      // buttonOptions.range に 'disabled' が設定されたときは期間指定ボタンは無効になる
      return !!this.buttonOptions && this.buttonOptions.period === 'disabled'
    }
  },
  watch: {
    period() {
      this.periodInput = this.period
    },
    periodType() {
      this.periodTypeInput = this.periodType
    },
    disabledPeriodButton(newValue) {
      // Redmine #2914: 外部結合環境にて信用新規注文画面において、執行方法を成行以外で注文期間に期間指定を指定したあと
      // 執行方法を成行に変更した場合、注文期間の状態が保持されて成行でも期間指定が入力できてしまう。
      // → 対応: コンポーネントが非活性に変化した場合は初期化する
      if (newValue) {
        this.resetComponent()
        this.$nextTick(() => {
          this.periodTypeInput = false
        })
      }
    }
  },
  mounted() {
    this.periodInput = this.period
    this.periodTypeInput = this.periodType
    if ((!this.showTodayButton || this.disabledTodayButton) &&
        this.showPeriodButton && !this.disabledPeriodButton && this.periodTypeInput === false) {
      // 当日中ボタンが非表示または無効の場合でかつ
      // 期間指定ボタンが表示かつ有効な場合は､期間指定ボタンを選択する
      this.periodTypeInput = true
      this.$nextTick(() => {
        this.$emit('update:periodType', this.periodTypeInput)
      })
    } else if ((!this.showPeriodButton || this.disabledPeriodButton) &&
        this.showTodayButton && !this.disabledTodayButton && this.periodTypeInput === true) {
      // 期間指定ボタンが非表示または無効の場合でかつ
      // 当日中ボタンが表示かつ有効な場合は､当日中ボタンを選択する
      this.periodTypeInput = false
      this.periodInput = ''
      this.$nextTick(() => {
        this.$emit('update:periodType', this.periodTypeInput)
        this.$emit('update:period', this.periodInput)
      })
    }
  },
  methods: {
    // リセット
    // validation を行うとき､periodType が未選択(null)の時にエラーメッセージを表示する(他にも期間指定でカレンダーが未設定の場合)
    // periodType が null の時に submit するとエラーメッセージが表示される｡
    // この時に periodType をリセットしようとして null を書き込んだ場合､ null → null のため､変化が検出できず､メッセージを消すことができない｡
    // 以上の理由によりリッセットのタイミングで親コンポーネントから resetComponent() を呼んでもらう必要がある｡
    resetComponent() {
      this.periodTypeInput = null
      this.periodInput = ''
      this.$emit('update:periodType', this.periodTypeInput)
      this.$emit('update:period', this.periodInput)
      this.isReset = true
      if (this.prop) {
        this.$nextTick(() => {
          this.$refs[this.prop].resetField()
        })
      }
    },
    // 日付入力時のイベント
    handleInputPeriod(value) {
      if (this.pickerOptions.disabledDate(new Date(Date.parse(value)))) {
        this.periodInput = ''
      } else {
        this.periodInput = value
      }
      this.$emit('update:period', this.periodInput)
      this.validate()
    },
    // 期間種類変更時のイベント
    handleChangePeriodType(value) {
      if (!value) {
        this.periodInput = ''
        this.$emit('update:period', this.periodInput)
      }
      this.$emit('update:periodType', this.periodTypeInput)
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
    periodValidator(rule, value, callback) {
      if (this.isRequired) {
        const label = [this.label && this.label.match(/^\s+$/) === null ? this.label : '値']
        const message = getMessage('errors.required', label)

        //  当日中または期間指定が未選択ならエラー
        if (this.periodTypeInput == null) {
          callback(message)
        }

        // 期間指定を選択時に期間ボタンが選択可能でかつ期間が指定されていない場合はエラー
        // validate処理は $emit() 後直ぐに実行されるため､親コンポーネントの値が書き換わっていないので内部の変数でチェックを行う
        if (this.showPeriodButton && !this.disabledPeriodButton && this.periodTypeInput && this.periodInput === '') {
          callback(message)
          return
        }
      }
      // OK
      callback()
    },
    setIsRequired(value) {
      this.isRequired = value
    }
  }
}
</script>

<style lang="scss" scoped>
.no-label :deep(.el-form-item__content) {
  margin-left: 0 !important;
}
:deep(.el-radio-group) {
  margin-left: 0 !important;
}
</style>

