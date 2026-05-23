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
    <template v-if="isSingle">
      <template v-if="isButton">
        <el-checkbox-button
          :id="id"
          v-model="internalSingle"
          :disabled="ctrlDisabled || ctrlReadonly || $attrs.disabled"
          @change="handleChange"
        >
          <span v-html="codeList1.value"></span>
        </el-checkbox-button>
      </template>
      <template v-else>
        <el-checkbox
          :id="id"
          v-model="internalSingle"
          :disabled="ctrlDisabled || ctrlReadonly || $attrs.disabled"
          @change="handleChange"
        >
          <span v-html="codeList1.value"></span>
        </el-checkbox>
      </template>
    </template>
    <template v-else>
      <el-checkbox-group
        v-model="internalValue"
        :class="checkClass"
        :validate-event="false"
        v-bind="$attrs"
        @change="handleChange"
      >
        <template v-if="isButton">
          <el-checkbox-button
            v-for="code in codeList"
            :id="id"
            :key="code.key"
            :label="code.key"
            :disabled="ctrlDisabled || ctrlReadonly || disabledItems.includes(code.key)"
          >
            <span v-html="code.value"></span>
          </el-checkbox-button>
        </template>
        <template v-else>
          <el-checkbox
            v-for="code in codeList"
            :id="id"
            :key="code.key"
            :label="code.key"
            :disabled="ctrlDisabled || ctrlReadonly || disabledItems.includes(code.key)"
          >
            <span v-html="code.value"></span>
          </el-checkbox>
        </template>
      </el-checkbox-group>
    </template>
  </el-form-item>
</template>

<script>
import { getMessage } from '@/utils/errorHandler'
import { controlAuthorization } from '@/utils/controlAuth'

export default {
  name: 'IfaInputCheck',
  inheritAttrs: false,
  props: {
    id: { type: String, required: false, default: null }, // ID
    modelValue: { type: [Array, String, Boolean], required: true }, // 入力値(v-model用)
    codeListId: { type: String, required: true }, // ドメインID
    dispPattern: { type: Number, required: false, default: 1 }, // 表示パターン
    selectPattern: { type: Number, required: false, default: 1 }, // 取得パターン
    isButton: { type: Boolean, required: false, default: false }, // チェックボックスボタンフラグ
    label: { type: String, required: false, default: '' }, // ラベル
    prop: { type: String, required: false, default: '' }, // プロパティ名
    rules: { type: [Object, Array], required: false, default: null }, // 入力チェックルール
    visible: { type: Boolean, required: false, default: true }, // 表示/非表示
    labelClass: { type: [String, Object], required: false, default: '' }, // labelクラス
    checkClass: { type: [String, Object], required: false, default: '' }, // checkクラス
    originalList: { type: Array, required: false, default: null }, // オリジナルリスト
    required: { type: Boolean, required: false, default: undefined }, // 必須項目
    disabledItems: { type: Array, required: false, default: () => [] }, // 項目ごと非活性(非活性にする項目の区分値)
    controlAuthEnabled: { type: Boolean, required: false, default: true } // 認可制御不使用フラグ（true: 認可制御使用する,false: 認可制御使用しない）
  },
  emits: ['change', 'update:modelValue'],
  data() {
    return {
      codeList: [], // 区分値リスト
      internalValue: [], // 内部値
      internalSingle: false, // 内部値(シングル)
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
        propRulesLocal.push({ validator: this.checkValidator, trigger: 'change' })
      }

      // インプット部品の validation を先に行い､その後親コンポーネントの validation を行う
      return [...propRulesLocal, ...propRules]
    },
    isSingle() {
      return !Array.isArray(this.modelValue)
    },
    codeList1() {
      // codeList[1] を返す｡ codeList が取得できていなかったり､長さが2以上ない場合は空きを返す
      return Array.isArray(this.codeList) && this.codeList.length >= 2 ? this.codeList[1] : { key: '', value: '' }
    }
  },
  watch: {
    modelValue() {
      this.setInternalValue()
    },
    originalList() {
      this.updateCodeList()
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
        this.setInternalValue()
      } else {
        this.codeList = this.$_getCodeList(this.codeListId, this.dispPattern, this.selectPattern, this.isSingle)
        this.setInternalValue()
      }
    },
    setInternalValue() {
      if (!this.isSingle) {
        const codeKeyList = this.codeList.map(c => c.key)
        const includedKeyList = this.modelValue.filter(k => codeKeyList.includes(k))
        if (this.modelValue.length === includedKeyList.length) {
          this.internalValue = this.modelValue
        } else {
          this.internalValue = includedKeyList
        }
      } else {
        // single check 動作の時は､チェック状態を true/false で扱う
        this.internalSingle = this.codeList1.key === this.modelValue
      }
    },
    handleChange(value) {
      if (this.isSingle) {
        // チェックあり(true) の時､codeList[1] の key を､なし(false) の時､codeList[0] の key を返す
        const codeList0Key = this.codeList && this.codeList[0] ? this.codeList[0].key : ''
        const emitValue = this.internalSingle ? this.codeList1.key : codeList0Key
        this.$emit('update:modelValue', emitValue)
        this.$emit('change', emitValue)
      } else {
        this.$emit('update:modelValue', value)
        this.$emit('change', value)
      }

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
    checkValidator(rule, value, callback) {
      // required が true の時は､
      // Single 動作なら､未チェックの場合はエラー
      // Multiple 動作なら､1つ以上の項目が選択されていない場合はエラー
      if (this.isSingle) {
        if (!this.internalSingle) {
          const label = [this.label && this.label.match(/^\s+$/) === null ? this.label : '項目']
          callback(getMessage('errors.selected', label))
          return
        }
      } else {
        if (!this.internalValue || !this.internalValue.length) {
          const label = [this.label && this.label.match(/^\s+$/) === null ? this.label : '項目']
          callback(getMessage('errors.selected', label))
          return
        }
      }
      // OK
      callback()
    }
  }
}
</script>
<style lang="scss" scoped>
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
</style>
