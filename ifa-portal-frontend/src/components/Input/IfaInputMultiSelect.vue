<template>
  <el-form-item
    v-if="visible && ctrlVisible"
    :ref="prop"
    class="form_label"
    :class="labelClass"
    :label="label"
    :prop="prop"
    :rules="setRules"
  >
    <el-select
      :id="id"
      v-model="internalValue"
      :disabled="ctrlDisabled || ctrlReadonly"
      class="middle_input"
      :class="selectClass"
      :clearable="clearable"
      :placeholder="placeholder"
      :validate-event="false"
      v-bind="$attrs"
      multiple
      collapse-tags
      collapse-tags-tooltip
      @change="handleChange"
    >
      <template #header>
        <div class="ifa-multi-select-buttons_wrapper">
          <ifa-button
            text="全選択"
            action-type="originalAction"
            small
            width="100"
            @app-action-handler="handleCheckAll(true)"
          ></ifa-button>
          <ifa-button
            text="全選択解除"
            action-type="originalAction"
            small
            width="100"
            color="secondary"
            @app-action-handler="handleCheckAll(false)"
          ></ifa-button>
        </div>
      </template>
      <el-option
        v-for="item in options"
        :key="item.key"
        :label="item.value"
        :value="item.key"
      >
        <el-checkbox
          :model-value="isCheck(item.key)"
          style="padding-right: 8px;"
        ></el-checkbox>
        {{ item.value }}
      </el-option>
    </el-select>
  </el-form-item>
</template>

<script>
import { getMessage } from '@/utils/errorHandler'
import { controlAuthorization } from '@/utils/controlAuth'

export default {
  name: 'IfaInputSelect',
  inheritAttrs: false,
  props: {
    id: { type: String, required: false, default: null }, // ID
    modelValue: { type: Array, required: true }, // 入力値(v-model用)
    codeListId: { type: String, required: true }, // 区分ID
    dispPattern: { type: Number, required: false, default: 1 }, // 表示パターン
    selectPattern: { type: Number, required: false, default: 1 }, // 取得パターン
    clearable: { type: Boolean, required: false, default: false }, //
    placeholder: { type: String, required: false, default: '' }, //
    label: { type: String, required: false, default: '' }, // ラベル
    prop: { type: String, required: false, default: '' }, // プロパティ名
    rules: { type: [Object, Array], required: false, default: null }, // 入力チェックルール
    visible: { type: Boolean, required: false, default: true }, // 表示/非表示
    labelClass: { type: String, required: false, default: '' }, // labelクラス
    selectClass: { type: String, required: false, default: '' }, // selectクラス
    originalList: { type: [Array, Object], required: false, default: null }, // オリジナルリスト
    required: { type: Boolean, required: false, default: undefined }, // 必須項目
    controlAuthEnabled: { type: Boolean, required: false, default: true } // 認可制御不使用フラグ（true: 認可制御使用する,false: 認可制御使用しない）
  },
  emits: ['change', 'changeSelected', 'update:modelValue'],
  data() {
    return {
      options: [],
      internalValue: [],
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
        propRulesLocal.push({ validator: this.selectValidator, trigger: 'change' })
      }

      // インプット部品の validation を先に行い､その後親コンポーネントの validation を行う
      return [...propRulesLocal, ...propRules]
    },
    isCheck() {
      return key => {
        return this.internalValue.includes(key)
      }
    }
  },
  watch: {
    modelValue() {
      this.internalValue = [...this.modelValue]
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
    update() {
      this.handleChange(this.internalValue)
    },
    updateCodeList() {
      if (this.codeListId === 'original') {
        this.options = this.originalList
        this.internalValue = [...this.modelValue]
      } else {
        this.options = this.$_getCodeList(this.codeListId, this.dispPattern, this.selectPattern)
        this.internalValue = [...this.modelValue]
      }
      this.emitCompat(this.internalValue)
    },
    handleChange(value) {
      this.$emit('update:modelValue', value)
      this.$emit('change', value)
      this.emitCompat(value)

      this.validate()
    },
    handleCheckAll(val) {
      if (val) {
        this.internalValue = this.options.map(item => item.key)
      } else {
        this.internalValue = []
      }
      this.handleChange(this.internalValue)
    },
    handleCheckAny(val) {
      if (val) {
        this.internalValue = val.map(item => item.id)
        const selectedList = val.filter(v => v.isSelected).map(v => v.id)
        this.handleChange(selectedList)
      }
    },
    emitCompat(value) {
      const selectedList = []
      this.options.forEach(v => {
        selectedList.push({
          id: v.key,
          isSelected: !!value.find(item => item === v.key)
        })
      })
      this.$emit('changeSelected', selectedList)
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
    selectValidator(rule, value, callback) {
      // required が true の時は､項目が選択されていない場合はエラー
      if (!this.internalValue.length) {
        const label = [this.label && this.label.match(/^\s+$/) === null ? this.label : '項目']
        callback(getMessage('errors.selected', label))
        return
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
.small_input {
  width: 80px;
}
.middle_input {
  width: 200px;
}
.large_input {
  width: 330px;
}
.ifa-multi-select-buttons_wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
