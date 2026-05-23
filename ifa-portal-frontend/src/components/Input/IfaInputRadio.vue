<template>
  <el-form-item
    v-if="visible && ctrlVisible"
    :ref="prop"
    class="form_label"
    :class="[
      { 'no-label': !label },
      labelClass
    ]"
    :label="link ? '' : label"
    :prop="prop"
    :rules="setRules"
  >
    <template
      v-if="link"
      #label
    >
      <span
        class="ifa-input-radio__label--link"
        @click="onLabelClick"
      >{{ label }}</span>
    </template>
    <el-radio-group
      v-model="internalValue"
      :class="[
        {
          'ifa-input-radio__multi': codeListId === 'multi'
        },
        radioClass
      ]"
      :validate-event="false"
      v-bind="$attrs"
      @change="handleChange"
    >
      <template v-if="isButton">
        <template v-if="codeListId === 'multi'">
          <div
            v-for="group in originalList"
            :key="group.groupId"
          >
            <el-radio-button
              v-for="code in group.options"
              :id="id"
              :key="code.key"
              :label="code.key"
              :disabled="ctrlDisabled || ctrlReadonly || disabledItems.includes(code.key)"
            >
              <span v-html="code.value"></span>
            </el-radio-button>
          </div>
        </template>
        <template v-else>
          <el-radio-button
            v-for="code in codeList"
            :id="id"
            :key="code.key"
            :label="code.key"
            :disabled="ctrlDisabled || ctrlReadonly || disabledItems.includes(code.key)"
          >
            <span v-html="code.value"></span>
          </el-radio-button>
        </template>
      </template>
      <template v-else>
        <template v-if="codeListId === 'multi'">
          <div
            v-for="group in originalList"
            :key="group.groupId"
          >
            <el-radio
              v-for="code in group.options"
              :id="id"
              :key="code.key"
              :label="code.key"
              :disabled="ctrlDisabled || ctrlReadonly || disabledItems.includes(code.key)"
            >
              <span v-html="code.value"></span>
            </el-radio>
          </div>
        </template>
        <template v-else>
          <el-radio
            v-for="code in codeList"
            :id="id"
            :key="code.key"
            :label="code.key"
            :disabled="ctrlDisabled || ctrlReadonly || disabledItems.includes(code.key)"
          >
            <span v-html="code.value"></span>
          </el-radio>
        </template>
      </template>
    </el-radio-group>
  </el-form-item>
</template>

<script>
import { getMessage } from '@/utils/errorHandler'
import { controlAuthorization } from '@/utils/controlAuth'

export default {
  name: 'IfaInputRadio',
  inheritAttrs: false,
  props: {
    id: { type: String, required: false, default: null }, // ID
    modelValue: { type: String, required: true }, // 入力値(v-model用)
    codeListId: { type: String, required: true }, // ドメインID
    dispPattern: { type: Number, required: false, default: 1 }, // 表示パターン
    selectPattern: { type: Number, required: false, default: 1 }, // 取得パターン
    isButton: { type: Boolean, required: false, default: false }, // ラジオボタンフラグ
    label: { type: String, required: false, default: '' }, // ラベル
    prop: { type: String, required: false, default: '' }, // プロパティ名
    rules: { type: [Object, Array], required: false, default: null }, // 入力チェックルール
    visible: { type: Boolean, required: false, default: true }, // 表示/非表示
    labelClass: { type: [String, Object], required: false, default: '' }, // labelクラス
    radioClass: { type: [String, Object], required: false, default: '' }, // radioクラス
    originalList: { type: Array, required: false, default: null }, // オリジナルリスト
    required: { type: Boolean, required: false, default: undefined }, // 必須項目
    link: { type: Boolean, required: false, default: false }, // リンクラベルフラグ
    disabledItems: { type: Array, required: false, default: () => [] }, // 項目ごと非活性(非活性にする項目の区分値)
    controlAuthEnabled: { type: Boolean, required: false, default: true } // 認可制御不使用フラグ（true: 認可制御使用する,false: 認可制御使用しない）
  },
  emits: ['change', 'update:modelValue', 'clickLabel'],
  data() {
    return {
      codeList: [], // 区分値リスト
      internalValue: '', // 内部値
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
      const propRulesLocal = []
      if (this.required === true && !propRules.find(rule => rule.required)) {
        propRulesLocal.push({ required: true, validator: (r, v, cb) => { cb() }, trigger: 'change' })
        // 入力チェック
        propRulesLocal.push({ validator: this.radioValidator, trigger: 'change' })
      }

      // インプット部品の validation を先に行い､その後親コンポーネントの validation を行う
      return [...propRulesLocal, ...propRules]
    }
  },
  watch: {
    modelValue() {
      this.setInternalValue()
    },
    originalList: {
      handler() {
        this.updateCodeList()
      },
      deep: true
    },
    codeListId() {
      this.updateCodeList()
    },
    dispPattern() {
      this.updateCodeList()
    },
    selectPattern() {
      this.updateCodeList()
    }
  },
  created() {
    this.updateCodeList()
  },
  mounted() {
    if (this.controlAuthEnabled) {
      controlAuthorization.bind(this)() // 認可制御
    }
  },
  methods: {
    updateCodeList() {
      if (this.codeListId === 'original') {
        this.codeList = this.originalList
      } else if (this.codeListId === 'multi') {
        this.originalList.forEach(group => {
          this.codeList.push(...group.options)
        })
      } else {
        this.codeList = this.$_getCodeList(this.codeListId, this.dispPattern, this.selectPattern)
      }
      this.setInternalValue()
    },
    setInternalValue() {
      if (this.modelValue !== '') {
        const code = this.codeList.filter(c => c.key === this.modelValue)[0]
        if (code != null) {
          this.internalValue = this.modelValue
        } else {
          this.internalValue = ''
        }
      } else {
        this.internalValue = ''
      }
    },
    handleChange(value) {
      this.$emit('update:modelValue', value)
      this.$emit('change', value)

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
    radioValidator(rule, value, callback) {
      // required が true の時は､項目が選択されていない場合はエラー
      if (!this.internalValue) {
        const label = [this.label && this.label.match(/^\s+$/) === null ? this.label : '項目']
        callback(getMessage('errors.selected', label))
        return
      }
      // OK
      callback()
    },
    onLabelClick() {
      this.$emit('clickLabel')
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/variables.scss";

.form_label :deep(.el-form-item) {
  margin-bottom: 1.2rem;
}
.form_label :deep(.el-form-item__label) {
  width: 135px;
  line-height: 2
}
.no-label :deep(.el-form-item__content) {
  margin-left: 0 !important;
}
.ifa-input-radio__multi {
  display: flex;
  flex-direction: column;
  align-items: start;
}
.ifa-input-radio__label--link {
  color: $mainBlueColor;
  cursor: pointer;
  text-decoration: underline;
  &:hover {
    text-decoration: underline;
    opacity: 0.7;
  }
}
</style>
