<template>
  <div class="ifa-common-search__wrapper">
    <el-row>
      <el-col
        :offset="19"
        :span="4"
        style="text-align: right;"
      >
        <div
          v-if="chkBrokerCodeExcludeIsVisible"
          style="padding-top: 12px;"
        >
          <ifa-input-check
            id="brokerCodeExclude"
            v-model="form.chkBrokerCodeExclude"
            style="padding-left: 30px;"
            code-list-id="original"
            :original-list="brokerCodeExcludeOptions"
          ></ifa-input-check>
        </div>
      </el-col>
    </el-row>
    <el-row class="ifa-common-search__align-items">
      <ifa-input-text
        v-if="selectPattern[0].values.brokerCodeIsVisible && selectPattern[0].values.brokerCodeMultipleInputsFlag"
        id="brokerCode"
        :model-value="form.brokerCode"
        label="仲介業者コード"
        prop="brokerCode"
        size="small"
        name="brokerCode"
        :input-class="[
          {
            'input-readonly': brokerCodeReadonly
          }
        ]"
        :domain="IfaBrokerCodeArrayDomainModel"
        :tooltip-enabled="!form.brokerCode.length < 1"
        :tooltip-content="form.brokerCode"
        :control-auth-enabled="false"
        tooltip-placement="bottom"
        tooltip-effect="light"
        :required="brokerCodeValidate"
        :readonly="brokerCodeReadonly"
        :rules="rules"
        @keydown="handleKeyDown"
        @input="handleCodeModified($event,4,'brokerCode')"
        @blur="validateForm"
      ></ifa-input-text>
      <ifa-input-text
        v-else-if="selectPattern[0].values.brokerCodeIsVisible && !selectPattern[0].values.brokerCodeMultipleInputsFlag"
        id="brokerCode"
        v-model="form.brokerCode"
        label="仲介業者コード"
        prop="brokerCode"
        type="text"
        size="small"
        name="brokerCode"
        :input-class="[
          {
            'input-readonly': brokerCodeReadonly
          }
        ]"
        :control-auth-enabled="false"
        :domain="IfaBrokerCodeDomainModel"
        :required="brokerCodeValidate"
        :readonly="brokerCodeReadonly"
      ></ifa-input-text>
      <ifa-input-text
        v-if="selectPattern[0].values.branchCodeIsVisible && selectPattern[0].values.branchCodeMultipleInputsFlag"
        id="branchCode"
        v-model="form.branchCode"
        label="支店コード"
        prop="branchCode"
        type="text"
        size="small"
        :input-class="[
          {
            'input-readonly': branchCodeReadonly
          }
        ]"
        name="brokerCode"
        :control-auth-enabled="false"
        :domain="IfaBranchCodeArrayDomainModel"
        :readonly="branchCodeReadonly"
        :rules="rules"
        @keydown="handleKeyDown"
        @input="handleCodeModified($event,3,'branchCode')"
        @blur="validateForm"
      ></ifa-input-text>
      <ifa-input-text
        v-if="selectPattern[0].values.branchCodeIsVisible && !selectPattern[0].values.branchCodeMultipleInputsFlag"
        id="branchCode"
        v-model="form.branchCode"
        label="支店コード"
        prop="branchCode"
        type="text"
        size="small"
        :input-class="[
          {
            'input-readonly': branchCodeReadonly
          }
        ]"
        name="brokerCode"
        :control-auth-enabled="false"
        :domain="IfaBranchCodeDomainModel"
        :readonly="branchCodeReadonly"
      ></ifa-input-text>
      <ifa-input-text
        v-if="selectPattern[0].values.empCodeIsVisible && selectPattern[0].values.empCodeMultipleInputsFlag"
        id="empCode"
        v-model="form.empCode"
        label="営業員コード"
        prop="empCode"
        size="small"
        :input-class="[
          {
            'input-readonly': empCodeReadonly
          }
        ]"
        :control-auth-enabled="false"
        :domain="IfaBrokerChargeCodeArrayDomainModel"
        :readonly="empCodeReadonly"
        :required="empCodeValidate"
        @keydown="handleKeyDown"
        @input="handleCodeModified($event, 4, 'empCode')"
        @blur="validateForm"
      ></ifa-input-text>
      <ifa-input-text
        v-if="selectPattern[0].values.empCodeIsVisible && !selectPattern[0].values.empCodeMultipleInputsFlag"
        id="empCode"
        v-model="form.empCode"
        label="営業員コード"
        prop="empCode"
        size="small"
        :input-class="[
          {
            'input-readonly': empCodeReadonly
          }
        ]"
        :control-auth-enabled="false"
        :domain="IfaBrokerChargeCodeDomainModel"
        :readonly="empCodeReadonly"
        :required="empCodeValidate"
      ></ifa-input-text>
    </el-row>
    <el-row class="ifa-common-search__align-items">
      <ifa-input-text
        v-if="selectPattern[0].values.butenCodeIsVisible"
        id="butenCode"
        v-model="form.butenCode"
        label="部店コード"
        prop="butenCode"
        type="text"
        size="small"
        :control-auth-enabled="false"
        :domain="IfaButenCodeDomainModel"
        @change="changeUpper"
      ></ifa-input-text>
      <ifa-input-text
        v-if="selectPattern[0].values.accountNumberIsVisible"
        id="accountNumber"
        v-model="form.accountNumber"
        label="口座番号"
        prop="accountNumber"
        type="text"
        size="small"
        :control-auth-enabled="false"
        :domain="IfaAccountNumberDomainModel"
      ></ifa-input-text>
      <div class="ifa-common-search__name-kanji-kana">
        <ifa-input-text
          v-if="selectPattern[0].values.customerNameIsVisible"
          id="customerNameKanjiKana"
          v-model="form.customerNameKanjiKana"
          :original-screen-id="originalScreenId"
          label="顧客名(漢字/カナ)"
          prop="customerNameKanjiKana"
          type="text"
          size="small"
          :control-auth-enabled="false"
          :domain="IfaCustNameKanjikanaInputDomainModel"
        >
          <template #tail>
            <div class="ifa-common-search__name-kanji-kana-terms">
              <ifa-input-select
                v-model="form.customerNameKanjiKanaTerms"
                code-list-id="CUSTOMER_NAME_SEARCH_METHOD"
                :disp-pattern="1"
                :select-pattern="1"
                prop="customerNameKanjiKanaTerms"
                size="small"
                :clearable="false"
              ></ifa-input-select>
            </div>
          </template>
        </ifa-input-text>
      </div>
      <div class="ifa-common-search__course-select">
        <course-select
          v-if="selectPattern[0].values.customerAttributeIsVisible"
          ref="courseSelect"
          :list-kind="listPattern"
          :id-string="listPattern"
          label="取引コース"
          prop="courseSelected"
          :required="courseValidate"
          :control-auth-enabled="false"
          @change-selected="form.courseSelected = $event"
        ></course-select>
      </div>
    </el-row>
    <ifa-requester
      id="initializeA001"
      action-id="IfaCommonSearch#A001"
      action-type="requestAction"
      @response-handler="responseHandlerInitializeA001($event)"
    ></ifa-requester>
  </div>
</template>
<script>
import store from '@/store'
import CourseSelect from '@/components/MultiSelect/CourseSelect'
import IfaAccountNumberDomainModel from '@/resource/domain/IfaAccountNumberDomainModel.json'
import IfaBranchCodeDomainModel from '@/resource/domain/IfaBranchCodeDomainModel.json'
import IfaBranchCodeArrayDomainModel from '@/resource/domain/IfaBranchCodeArrayDomainModel.json'
import IfaBrokerChargeCodeDomainModel from '@/resource/domain/IfaBrokerChargeCodeDomainModel.json'
import IfaBrokerChargeCodeArrayDomainModel from '@/resource/domain/IfaBrokerChargeCodeArrayDomainModel.json'
import IfaBrokerCodeArrayDomainModel from '@/resource/domain/IfaBrokerCodeArrayDomainModel.json'
import IfaButenCodeDomainModel from '@/resource/domain/IfaButenCodeDomainModel.json'
import IfaCustNameKanjikanaInputDomainModel from '@/resource/domain/IfaCustNameKanjikanaInputDomainModel.json'
import IfaBrokerCodeDomainModel from '@/resource/domain/IfaBrokerCodeDomainModel.json'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: { CourseSelect },
  props: {
    displayPattern: { type: String, required: true },
    listPattern: { type: String, required: false, default: 'pt1' },
    form: { type: Object, required: true },
    brokerCodeValidate: { type: Boolean, required: false, default: false },
    empCodeValidate: { type: Boolean, required: false, default: false },
    courseValidate: { type: Boolean, required: false, default: true },
    originalScreenId: { type: String, required: false, default: '' }
  },
  emits: ['mediateUserPrivacy'],
  data() {
    return {
      brokerCodeReadonly: false,
      empCodeReadonly: true,
      branchCodeReadonly: false,
      selectPattern: {},
      patternOptions: [
        { key: 'pt1',
          values: {
            brokerCodeIsVisible: true,
            chkBrokerCodeExcludeIsVisible: true,
            branchCodeIsVisible: true,
            empCodeIsVisible: true,
            butenCodeIsVisible: true,
            accountNumberIsVisible: true,
            customerNameIsVisible: true,
            customerAttributeIsVisible: false,
            brokerCodeMultipleInputsFlag: true,
            branchCodeMultipleInputsFlag: true,
            empCodeMultipleInputsFlag: true
          }
        }
      ],
      rules: {
        brokerCode: { validator: this.brokerCodeValidator, trigger: 'blur' },
        branchCode: { validator: this.branchCodeValidator, trigger: 'blur' }
      },
      IfaAccountNumberDomainModel: IfaAccountNumberDomainModel,
      IfaBranchCodeDomainModel: IfaBranchCodeDomainModel,
      IfaBranchCodeArrayDomainModel: IfaBranchCodeArrayDomainModel,
      IfaBrokerChargeCodeDomainModel: IfaBrokerChargeCodeDomainModel,
      IfaBrokerChargeCodeArrayDomainModel: IfaBrokerChargeCodeArrayDomainModel,
      IfaBrokerCodeArrayDomainModel: IfaBrokerCodeArrayDomainModel,
      IfaButenCodeDomainModel: IfaButenCodeDomainModel,
      IfaCustNameKanjikanaInputDomainModel: IfaCustNameKanjikanaInputDomainModel,
      IfaBrokerCodeDomainModel: IfaBrokerCodeDomainModel,
      delFlag: false,
      currentText: '',
      brokerCodeAutoDispFlag: false, // 認可制御(自動入力フラグ)
      empCodeAutoDispFlag: false, // 認可制御(自動入力フラグ)
      courseRule: [
        { required: this.courseValidate, message: getMessage('errors.required', ['取引コース']), trigger: 'change' },
        { validator: this.courseSelectedValidator, trigger: 'change' }
      ],
      brokerCodeExcludeOptions: [
        { key: false, value: '' },
        { key: true, value: '仲介業者除外' }
      ],
      // 初期値
      initialForm: {
        brokerCode: '',
        branchCode: '',
        empCodeRule: '',
        butenCode: '',
        accountNumber: '',
        customerNameKanjiKana: '',
        customerNameKanjiKanaTerms: '3',
        courseSelected: [],
        domesticMarginPositionListBrandCode: ''
      }
    }
  },
  computed: {
    chkBrokerCodeExcludeIsVisible() {
      const list = this.patternOptions.filter(list => list.key === this.displayPattern)
      return list[0].values.chkBrokerCodeExcludeIsVisible
    }
  },
  watch: {
    'form.brokerCode'() {
      // 営業員コード
      if (this.empCodeAutoDispFlag === false) {
        if (this.form.brokerCode.length <= 4) {
          this.empCodeReadonly = false
        } else {
          this.form.empCode = ''
          this.empCodeReadonly = true
        }
      }
      // 支店コード
      if (this.form.brokerCode.length <= 4) {
        this.branchCodeReadonly = false
      } else {
        this.form.branchCode = ''
        this.branchCodeReadonly = true
      }
    },
    'form.branchCode'() {
      // 営業員コード
      if (this.empCodeAutoDispFlag === false) {
        if (this.form.brokerCode.length <= 4 && this.form.branchCode.length <= 3) {
          this.empCodeReadonly = false
        } else {
          this.form.empCode = ''
          this.empCodeReadonly = true
        }
      }
    },
    'form.customerNameKanjiKanaTerms'(newValue) {
      // 親コンポーネントによっては､初期状態が'含む'になっていないケースが見受けられる
      // おそらく親コンポーネント初期化処理で formModel から form を初期化している時に値が上書きされてしまっていると思われる
      // そのため､`form.customerNameKanjiKanaTerms` が不正な値になっていた場合､強制的に `含む` を選択させる
      const num = Number(newValue)
      if (num !== 1 && num !== 2 && num !== 3) {
        this.form.customerNameKanjiKanaTerms = '3'
      }
    },
    displayPattern() {
      this.selectPattern = this.patternOptions.filter(list => list.key === this.displayPattern)
    }
  },
  created() {
    this.selectPattern = this.patternOptions.filter(list => list.key === this.displayPattern)
    this.$nextTick(() => {
      this.$el.querySelector('#initializeA001').click()
    })
  },
  mounted() {
    this.currentText = this.form.brokerCode
    // 初期値を保存
    this.initialForm.brokerCode = this.form.brokerCode ?? ''
    this.initialForm.branchCode = this.form.branchCode ?? ''
    this.initialForm.empCode = this.form.empCode ?? ''
    this.initialForm.butenCode = this.form.butenCode ?? ''
    this.initialForm.accountNumber = this.form.accountNumber ?? ''
    this.initialForm.customerNameKanjiKana = this.form.customerNameKanjiKana ?? ''
    this.initialForm.customerNameKanjiKanaTerms = this.form.customerNameKanjiKanaTerms ?? '3'
    this.courseSelected = Array.isArray(this.form.courseSelected) ? [...this.form.courseSelected] : []
  },
  methods: {
    // リセット
    formClear() {
      if (!this.brokerCodeAutoDispFlag) {
        this.form.brokerCode = this.initialForm.brokerCode
      }
      this.form.branchCode = this.initialForm.branchCode
      if (!this.empCodeAutoDispFlag) {
        this.form.empCode = this.initialForm.empCode
      }
      this.form.butenCode = this.initialForm.butenCode
      this.form.accountNumber = this.initialForm.accountNumber
      this.form.customerNameKanjiKana = this.initialForm.customerNameKanjiKana
      this.form.customerNameKanjiKanaTerms = this.initialForm.customerNameKanjiKanaTerms
      if (this.$refs.courseSelect) { this.$refs.courseSelect.clearHandler() }
      this.form.chkBrokerCodeExclude = false
    },
    // 仲介業者コード、支店コード、営業員コードの制御を行う。
    validateForm() {
      const parentForm = this.findParentForm()
      if (parentForm) {
        parentForm.validateField('brokerCode').catch(() => {})
        parentForm.validateField('branchCode').catch(() => {})
        parentForm.validateField('empCode').catch(() => {})
      }
    },
    // 親の ElForm コンポーネントを見つけるメソッド
    findParentForm() {
      let parent = this.$parent
      while (parent) {
        if (parent.$options.name === 'ElForm') {
          return parent
        }
        parent = parent.$parent
      }
      return null
    },
    // キー入力
    handleKeyDown(event) {
      switch (event.key) {
        case 'Delete': // event.keyCode: 46
          this.delFlag = true
          break
        case 'Backspace': // event.keyCode: 8
          this.delFlag = false
          break
      }
    },
    // 仲介業者コードの制御を行う。
    brokerCodeValidator(rule, value, callback) {
      if (value.length !== 4 && this.form.branchCode.length > 3) {
        callback(getMessage('errors.neqSize', ['仲介業者コード', 4]))
      } else if (value.length !== 4 && this.form.empCode.length > 4) {
        callback(getMessage('errors.neqSize', ['仲介業者コード', 4]))
      } else {
        // OK
        callback()
      }
    },
    // 支店コードの制御を行う。
    branchCodeValidator(rule, value, callback) {
      if (value.length !== 3 && this.form.empCode.length > 4) {
        callback(getMessage('errors.neqSize', ['支店コード', 3]))
      } else {
        // OK
        callback()
      }
    },
    // 複数コードが編集された
    handleCodeModified(newValue, minLength, field) {
      if (newValue.match(/[^a-zA-Z0-9,]/g) !== null) {
        this.form[field] = newValue
        return
      }
      const newText = newValue.replace(/[^a-zA-Z0-9]/g, '')
      const currentText = this.form[field].replace(/[^a-zA-Z0-9]/g, '')
      const reges = new RegExp(`\[a-zA-Z0-9]{1,${minLength}}`, 'g')
      this.form[field] = newText.match(reges)?.join() || ''
      const obj = this.$el.querySelector('#brokerCode')
      let pos = obj.selectionStart
      if (newText !== currentText) {
        if (newText.length > currentText.length) {
          // 文字が追加された
          if (this.form[field].length - pos === 1) {
            // カーソル位置が最後尾かつコンマが追加されたときは､1文字進める (1111|[2] -> 1111,[2]| )
            pos += 1
          } else {
            const m = newValue.match(new RegExp(`\[a-zA-Z0-9]{${minLength + 1},}`))
            if (m) {
              const i = m[0].length + m.index
              if (pos === i && newValue.charAt(i) === ',') {
                // コンマの前に文字が挿入された場合､1文字進める (1111,9999|[2],222 -> 1111,9999,[2]|222)
                pos += 1
              }
            }
          }
        }
      } else {
        // Backspace キーまたはDEL キーでコンマが削除されたとき
        if (this.delFlag) {
          // DEL キーで削除された場合
          if (pos > 0 && pos % minLength + 1 === minLength) {
            // コンマが削除されても復活するのでカーソルだけ1文字進める (1111|,2222 -> 1111,|2222)
            pos += 1
          }
        } else {
          // Backspace キーで削除された場合
          if (pos > 0 && pos % minLength + 1 === 0) {
            // コンマが削除されても復活するのでカーソルだけ1文字戻す (1111,|2222 -> 1111|,2222)
            pos -= 1
          }
        }
      }
      this.$nextTick(() => {
        this.$nextTick(() => {
          // カーソルを確実に移動するために $nextTick を2回重ねる (1回だと reactive が効いてカーソルが最後尾に移動してしまう)
          obj.selectionStart = pos
          obj.selectionEnd = pos
        })
      })
    },
    courseSelectedValidator(rule, value, callback) {
      if (!this.courseValidate) {
        // courseValidate が true 以外の時はバリデーションしない
        callback()
        return
      }

      for (const i in this.form.courseSelected) {
        if (this.form.courseSelected[i].isSelected === true) {
          // OK
          callback()
          return
        }
      }
      callback('取引コースを選択してください。')
    },
    // 仲介業者・営業員コードの自動表示判定を取得する
    responseHandlerInitializeA001(data) {
      this.brokerCodeAutoDispFlag = data.dataList[0].brokerCodeAutoDispFlag === '1'
      this.empCodeAutoDispFlag = data.dataList[0].empCodeAutoDispFlag === '1'

      this.brokerCodeReadonly = false
      this.empCodeReadonly = false

      if (this.brokerCodeAutoDispFlag) {
        this.brokerCodeReadonly = true
        this.form.brokerCode = store.getters.userAccount.broker.brokerCode
        this.selectPattern[0].values.brokerCodeMultipleInputsFlag = false
      }
      if (this.empCodeAutoDispFlag) {
        this.empCodeReadonly = true
        this.form.empCode = store.getters.userAccount.mediateChargeInfo.brokerChargeCode
      }
      this.$emit('mediateUserPrivacy', data.dataList[0])
    },
    changeUpper() {
      const str = this.form.butenCode
      this.form.butenCode = str.toUpperCase()
    }
  }
}
</script>
<style lang="scss" scoped>
.ifa-common-search__wrapper {
  .ifa-common-search__align-items {
    display: flex;
    flex-wrap: wrap;
    align-items: top;
  }
  :deep(.el-form-item) {
    margin-bottom: 1.2rem;
    margin-right: 10px;
  }
  :deep(.el-checkbox__label) {
    font-weight: bold;
  }
  :deep(.el-input) {
    --el-input-width: 180px;
  }
  :deep(.el-form-item__error) {
    display: flex;
    word-break: keep-all;
    white-space: nowrap;
    overflow-wrap: break-word;
  }
  .ifa-common-search__name-kanji-kana {
    :deep(.el-form-item__label) {
      white-space: nowrap;
    }
    :deep(.ifa-input-text__container) {
      height: 34px;
    }
    .ifa-common-search__name-kanji-kana-terms {
      :deep(.el-input) {
        --el-input-width: 105px;
      }
      :deep(.el-form-item) {
        margin-right: 0px;
      }
      :deep(.el-form-item__content) {
        line-height: 28px;
      }
    }
  }
  .ifa-common-search__course-select {
    :deep(.el-form-item__label) {
      width: 100px;
    }
    :deep(.el-input) {
      --el-input-width: 180px;
    }
  }
}
</style>
