<template>
  <!-- ダイアログ名：担当追加 -->
  <div>
    <el-dialog
      v-model="$props.isVisible"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      width="1050px"
      class="loginUserManagementInput"
    >
      <!-- タイトル -->
      <template #header>
        <span>{{ form.title.name }}</span>
      </template>
      <!-- メイン -->
      <el-row>
        <el-col
          :span="22"
          class="close-button"
        >
          <ifa-button
            id="btnBack"
            text="戻る"
            width="90"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-form
            ref="form"
            :model="form"
            :inline="true"
            :rules="rules"
          >
            <!-- ログインID -->
            <el-row>
              <el-form-item
                label="ログインID"
                class="form_label"
              >
                <span
                  size="small"
                  maxlength="16"
                  class="ifa-input__text-default"
                > {{ $_out(form.loginId) }}</span>
              </el-form-item>
            </el-row>
            <!-- 所属権限 -->
            <el-row>
              <el-form-item
                label="所属権限"
                class="form_label"
                prop="privId"
              >
                <span
                  size="small"
                  maxlength="16"
                  class="ifa-input__text-default"
                >{{ $_out(form.privId) === '-' ? '-' : $_getCodeValue('INFO_REGISTER_VIEWER_AUTHORITY_SELECT', 1, form.privId) }}</span>
              </el-form-item>
            </el-row>
            <!-- 本店／支店名 -->
            <el-row>
              <el-form-item
                label="本店／支店名"
                class="form_label"
                prop="branchNameList"
              >
                <el-select
                  v-model="selectedBranchNameOptionValue"
                  size="small"
                  class="ifa-input__text-default"
                  @change="branchNameUpdateA008"
                >
                  <el-option
                    v-for="item in form.branchNameList"
                    :key="item.sbiSecurityCode"
                    :label="item.sbiSecurityCode + ' ' + item.mainBranchName"
                    :value="item.sbiSecurityCode"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-row>
            <!-- 仲介業者名 -->
            <el-row>
              <el-form-item
                ref="brokerNameList"
                label="仲介業者名"
                class="form_label"
                prop="brokerNameList"
              >
                <el-select
                  v-model="selectedBrokerNameOptionValue"
                  size="small"
                  class="ifa-input__text-default"
                  @change="brokerNameUpdateA002"
                >
                  <el-option
                    v-for="item in form.brokerNameList"
                    :key="item.brokerCode"
                    :label="item.brokerName"
                    :value="item.brokerCode"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-row>
            <!-- 仲介業者支店名 -->
            <el-row v-if="displayCheck('仲介業者支店名')">
              <el-form-item
                ref="brokerBranchNameList"
                label="仲介業者支店名"
                class="form_label"
                prop="brokerBranchNameList"
              >
                <el-select
                  v-model="selectedBrokerBranchNameOptionValue"
                  size="small"
                  class="ifa-input__text-default"
                  @change="brokerBranchNameUpdateA003"
                >
                  <el-option
                    v-for="item in form.brokerBranchNameList"
                    :key="item.subBrokerId"
                    :label="item.brokerBranchName"
                    :value="item.subBrokerId"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-row>
            <!-- 担当者名 -->
            <el-row>
              <el-form-item
                ref="employeeNameList"
                label="担当者名"
                class="form_label"
                prop="employeeNameList"
              >
                <el-select
                  v-model="selectedEmployeeNameOptionValue"
                  size="small"
                  class="ifa-input__text-default"
                  @change="setEmployeeName"
                >
                  <el-option
                    v-for="item in form.employeeNameList"
                    :key="item.employeeId"
                    :label="item.employeeName"
                    :value="item.employeeId"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-row>
          </el-form>
        </el-col>

        <el-col
          :offset="2"
          style="margin-top: 0.5rem"
        >
          <ifa-button
            id="btnAdd"
            :form="formRef"
            text="追加"
            width="90"
            color="primary"
            action-id="SUB0601_01-06_1#A005"
            action-type="requestAction"
            :request-model="getA005RequestModel"
            @response-handler="addA005($event)"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>
    <!-- 確認ダイアログ -->
    <el-dialog
      v-model="dialogComfirmVisible"
      width="400px"
      :center="true"
      :close-on-click-modal="false"
      :show-close="false"
      style="margin-top: 20vh;"
      class="confirm-dialog"
    >
      <!-- タイトル -->
      <template #header>
        <span>担当追加</span>
      </template>
      <!-- メッセージ -->
      <div class="dialog-body">
        <span>担当者情報を登録します。よろしいですか？</span>
      </div>
      <div class="dialog-footer">
        <ifa-button
          id="btnOk"
          :form="formRef"
          text="OK"
          width="90"
          color="primary"
          small
          action-id="SUB0601_01-06_1#A006"
          action-type="requestAction"
          :request-model="getA006RequestModel"
          @response-handler="additionOkA006($event)"
        ></ifa-button>
        <ifa-button
          id="btnCancel"
          text="キャンセル"
          width="100"
          color="secondary"
          small
          action-type="originalAction"
          @app-action-handler="dialogComfirmVisible = false"
        ></ifa-button>
      </div>
    </el-dialog>
  </div>
  <ifa-requester
    id="IfaRepAddBranchNameUpdateA008"
    :form="formRef"
    :request-model="getA008RequestModel"
    :pre-request-handler="preRequestHandlerBranchNameUpdateA008"
    action-id="SUB0601_01-06_1#A008"
    action-type="requestAction"
    @response-handler="responseHandlerBranchNameUpdateA008($event)"
  ></ifa-requester>
  <ifa-requester
    id="IfaRepAddBrokerNameUpdateA002"
    :form="formRef"
    :request-model="getA002RequestModel"
    :pre-request-handler="preRequestHandlerBrokerNameUpdateA002"
    action-id="SUB0601_01-06_1#A002"
    action-type="requestAction"
    @response-handler="responseHandlerBrokerNameUpdateA002($event)"
  ></ifa-requester>
  <ifa-requester
    id="IfaRepAddBrokerBranchNameUpdateA003"
    :form="formRef"
    :request-model="getA003RequestModel"
    :pre-request-handler="preRequestHandlerBrokerBranchNameUpdateA003"
    action-id="SUB0601_01-06_1#A003"
    action-type="requestAction"
    @response-handler="responseHandlerBrokerBranchNameUpdateA003($event)"
  ></ifa-requester>
</template>

<script>
import { IfaRepAddFormModel } from './js/IfaRepAddFormModel.js'
import { IfaRepAddA002RequestModel } from './js/IfaRepAddA002RequestModel.js'
import { IfaRepAddA003RequestModel } from './js/IfaRepAddA003RequestModel.js'
import { IfaRepAddA005RequestModel } from './js/IfaRepAddA005RequestModel.js'
import { IfaRepAddA006RequestModel } from './js/IfaRepAddA006RequestModel.js'
import { IfaRepAddA008RequestModel } from './js/IfaRepAddA008RequestModel.js'

export default {
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    selectedInfo: {
      type: Object,
      required: false,
      default: null
    },
    operation: {
      type: String,
      required: false,
      default: null
    }
  },
  emits: ['close-modal', 'call-search-a002'],

  data() {
    return {
      formRef: {},
      rules: {
        branchNameList: { required: true, trigger: 'change', validator: this.branchNameValidator },
        brokerNameList: { required: true, trigger: 'change', validator: this.brokerNameValidator },
        brokerBranchNameList: { required: true, trigger: 'change', validator: this.brokerBranchNameValidator },
        employeeNameList: { required: true, trigger: 'change', validator: this.employeeNameValidator }
      },
      isA002: false,
      isA002A003: false,
      isA008: false,
      form: new IfaRepAddFormModel(),
      selectedBranchNameOptionValue: null,
      selectedBrokerNameOptionValue: null,
      selectedBrokerBranchNameOptionValue: null,
      selectedEmployeeNameOptionValue: null,
      selectedEmployeeNameOptionLabel: null,
      dialogComfirmVisible: false
    }
  },
  computed: {
    getA008RequestModel() {
      return new IfaRepAddA008RequestModel(
        {
          privId: this.form.privId, // 権限コード
          sbiSecurityCode: this.selectedBranchNameOptionValue // SBI証券支店コード
        }
      )
    },
    getA002RequestModel() {
      return new IfaRepAddA002RequestModel(
        {
          privId: this.form.privId, // 権限コード
          brokerCode: this.selectedBrokerNameOptionValue // 仲介業者コード
        }
      )
    },
    getA003RequestModel() {
      return new IfaRepAddA003RequestModel(
        {
          privId: this.form.privId, // 権限コード
          brokerCode: this.selectedBrokerNameOptionValue, // 仲介業者コード
          subBrokerId: this.selectedBrokerBranchNameOptionValue // 仲介業者支店コード
        }
      )
    },
    getA005RequestModel() {
      return new IfaRepAddA005RequestModel(
        {
          userId: this.form.loginId, // ユーザーID
          sbiSecurityCode: this.selectedBranchNameOptionValue, // SBI証券支店コード
          brokerCode: this.selectedBrokerNameOptionValue, // 仲介業者コード
          subBrokerId: this.selectedBrokerBranchNameOptionValue, // 仲介業者支店コード
          employeeId: this.selectedEmployeeNameOptionValue, // 仲介業者担当者コード
          chargeName: this.selectedEmployeeNameOptionLabel // 担当者名
        }

      )
    },
    getA006RequestModel() {
      return new IfaRepAddA006RequestModel(
        {
          userId: this.form.loginId, // ユーザーID
          sbiSecurityCode: this.selectedBranchNameOptionValue, // SBI証券支店コード
          brokerCode: this.selectedBrokerNameOptionValue, // 仲介業者コード
          subBrokerId: this.selectedBrokerBranchNameOptionValue, // 仲介業者支店コード
          employeeId: this.selectedEmployeeNameOptionValue, // 仲介業者担当者コード
          chargeName: this.selectedEmployeeNameOptionLabel // 担当者名
        }
      )
    }
  },
  methods: {
    onShow(data) {
      this.isA002 = false
      this.isA002A003 = false
      this.isA008 = false

      this.form = new IfaRepAddFormModel()
      Object.assign(this.form, data.dataList[0])
      this.selectedBranchNameOptionValue = null
      this.selectedBrokerNameOptionValue = null
      this.selectedBrokerBranchNameOptionValue = null
      this.selectedEmployeeNameOptionValue = null
      this.selectedEmployeeNameOptionLabel = null

      // 本店／支店名の空データを削除し表示しない
      for (let key = 0; key < this.form.branchNameList.length; key++) {
        if (Object.values(this.form.branchNameList[key]).some((value) => value === '') || Object.values(this.form.branchNameList[key]).some((value) => value === null)) {
          // デクリメント
          this.form.branchNameList.splice(key--, 1)
        }
      }
      // 仲介業者名の空データを削除し表示しない
      for (let key = 0; key < this.form.brokerNameList.length; key++) {
        if (Object.values(this.form.brokerNameList[key]).some((value) => value === '') || Object.values(this.form.brokerNameList[key]).some((value) => value === null)) {
          // デクリメント
          this.form.brokerNameList.splice(key--, 1)
        }
      }
      // 仲介業者支名の空データを削除し表示しない
      for (let key = 0; key < this.form.brokerBranchNameList.length; key++) {
        if (Object.values(this.form.brokerBranchNameList[key]).some((value) => value === '') || Object.values(this.form.brokerBranchNameList[key]).some((value) => value === null)) {
          // デクリメント
          this.form.brokerBranchNameList.splice(key--, 1)
        }
      }
      // 担当者名の空データを削除し表示しない
      for (let key = 0; key < this.form.employeeNameList.length; key++) {
        if (Object.values(this.form.employeeNameList[key]).some((value) => value === '') || Object.values(this.form.employeeNameList[key]).some((value) => value === null)) {
          // デクリメント
          this.form.employeeNameList.splice(key--, 1)
        }
      }

      if (this.formRef.resetFields) {
        this.formRef.resetFields()
        this.$refs.brokerBranchNameList.resetField()
        this.$refs.employeeNameList.resetField()
      }
      this.$nextTick(() => {
        this.formRef = this.$refs.form
      })
    },

    // 本店／支店名のバリデーションチェック処理
    branchNameValidator(rule, value, callback) {
      if (this.isA002A003) {
        callback() // isA002A003 が true の時はエラーが表示されなくなる
        return
      }
      // 仲介業者名を入力されていない場合はエラー
      if (this.selectedBranchNameOptionValue === null) {
        callback('本店／支店名を入力してください｡')
        return
      }
      // OK
      callback()
    },
    // 仲介業者名のバリデーションチェック処理
    brokerNameValidator(rule, value, callback) {
      if (!this.isA002A003 && this.isA008) {
        callback() // isA002A003 が false かつ isA008 が true の時はエラーが表示されなくなる
        return
      }
      // 仲介業者名を入力されていない場合はエラー
      if (this.selectedBrokerNameOptionValue === null) {
        callback('仲介業者名を入力してください｡')
        return
      }
      // OK
      callback()
    },
    // 仲介業者支店名のバリデーションチェック処理
    brokerBranchNameValidator(rule, value, callback) {
      if (this.isA008 || this.isA002) {
        callback() // isA008またはisA002が true の時はエラーが表示されなくなる
        // 仲介業者支店名を１度非表示にすると担当者名との検証順が前後するため、以下の通り判定
        // 担当者名が未検証の場合
        if (this.$refs.employeeNameList.validateState === '') {
          return
        } else { // 担当者名が検証済の場合
          this.isA008 = false
          this.isA002 = false
          return
        }
      }
      // 仲介業者支店名を入力されていない場合はエラー
      if (this.selectedBrokerBranchNameOptionValue === null) {
        callback('仲介業者支店名を入力してください｡')
        return
      }
      // OK
      callback()
    },
    // 担当者名のバリデーションチェック処理
    employeeNameValidator(rule, value, callback) {
      if (this.isA002A003 || this.isA008) {
        callback() // isA002A003またはisA008 が true の時はエラーが表示されなくなる
        this.isA002A003 = false
        try {
          // 仲介業者支店名が未検証の場合
          if (this.$refs.brokerBranchNameList.validateState === '') {
            return
          } else { // 仲介業者支店名が検証済の場合
            this.isA008 = false
            this.isA002 = false
            return
          }
        } catch (error) { // 仲介業者支店名が非表示の場合
          this.isA008 = false
          this.isA002 = false
          return
        }
      }
      // 担当者名を入力されていない場合はエラー
      if (this.selectedEmployeeNameOptionValue === null) {
        callback('担当者名を入力してください｡')
        return
      }
      // OK
      callback()
    },
    // 本店／支店名プルダウンリスト変更時処理
    branchNameUpdateA008(value) {
      this.selectedBranchNameOptionValue = value
      this.$refs.brokerNameList.clearValidate()
      // 仲介業者支店名が表示されている場合
      if (this.$refs.brokerBranchNameList) this.$refs.brokerBranchNameList.clearValidate()
      this.$refs.employeeNameList.clearValidate()
      // リクエスト項目(formRef)のバリデーションが実行されるため、フラグ有効化
      this.isA008 = true
      this.$nextTick(() => {
        document.getElementById('IfaRepAddBranchNameUpdateA008').click()
      })
    },
    preRequestHandlerBranchNameUpdateA008() {
      // 初期値でない場合のみフラグ有効化
      if (this.selectedBrokerNameOptionValue !== null || this.selectedBrokerBranchNameOptionValue !== null || this.selectedEmployeeNameOptionValue !== null) {
        // 初期化後にバリデーションが実行されるため、フラグ有効化
        this.isA008 = true
      }

      // 仲介業者名を初期化
      this.$refs.brokerNameList.clearValidate()
      this.selectedBrokerNameOptionValue = null
      // 仲介業者支店名を初期化
      if (this.$refs.brokerBranchNameList) this.$refs.brokerBranchNameList.clearValidate()
      this.selectedBrokerBranchNameOptionValue = null
      // 担当者名を初期化
      this.$refs.employeeNameList.clearValidate()
      this.selectedEmployeeNameOptionValue = null
    },
    responseHandlerBranchNameUpdateA008(data) {
      this.isA008 = false
      if (data.errorLevel === 0) {
        Object.assign(this.form.brokerNameList, data.dataList[0].brokerNameList)
        Object.assign(this.form.brokerBranchNameList, data.dataList[0].brokerBranchNameList)
        Object.assign(this.form.employeeNameList, data.dataList[0].employeeNameList)

        // 仲介業者名の空データを削除し表示しない
        for (let key = 0; key < this.form.brokerNameList.length; key++) {
          if (Object.values(this.form.brokerNameList[key]).some((value) => value === '') || Object.values(this.form.brokerNameList[key]).some((value) => value === null)) {
          // デクリメント
            this.form.brokerNameList.splice(key--, 1)
          }
        }
      } else {
        return
      }
    },
    // 仲介業者名プルダウンリスト変更時処理
    brokerNameUpdateA002(value) {
      this.selectedBrokerNameOptionValue = value
      if (this.$refs.brokerBranchNameList) {
        this.$refs.brokerBranchNameList.clearValidate()
      }
      this.$refs.employeeNameList.clearValidate()
      // リクエスト項目(formRef)のバリデーションが実行されるため、フラグ有効化
      this.isA002A003 = true
      this.isA002 = true
      this.$nextTick(() => {
        document.getElementById('IfaRepAddBrokerNameUpdateA002').click()
      })
    },
    preRequestHandlerBrokerNameUpdateA002() {
      // 初期値でない場合のみフラグ有効化
      if (this.selectedBrokerBranchNameOptionValue !== null || this.selectedEmployeeNameOptionValue !== null) {
        // 初期化後にバリデーションが実行されるため、フラグ有効化
        this.isA002A003 = true
        this.isA002 = true
      }

      // 仲介業者支店名を初期化
      if (this.$refs.brokerBranchNameList) this.$refs.brokerBranchNameList.clearValidate()
      this.selectedBrokerBranchNameOptionValue = null
      // 担当者名を初期化
      this.$refs.employeeNameList.clearValidate()
      this.selectedEmployeeNameOptionValue = null
    },
    responseHandlerBrokerNameUpdateA002(data) {
      this.isA002A003 = false
      this.isA002 = false
      if (data.errorLevel === 0) {
        this.form.brokerBranchNameList = [] // A008で取得した仲介業者支店名リストを削除
        Object.assign(this.form.brokerBranchNameList, data.dataList[0].brokerBranchNameList)
        this.form.employeeNameList = [] // A008で取得した担当者名リストを削除
        Object.assign(this.form.employeeNameList, data.dataList[0].employeeNameList)

        // 仲介業者支店名の空データを削除し表示しない
        for (let key = 0; key < this.form.brokerBranchNameList.length; key++) {
          if (Object.values(this.form.brokerBranchNameList[key]).some((value) => value === '') || Object.values(this.form.brokerBranchNameList[key]).some((value) => value === null)) {
          // デクリメント
            this.form.brokerBranchNameList.splice(key--, 1)
          }
        }
        // 担当者名の空データを削除し表示しない
        for (let key = 0; key < this.form.employeeNameList.length; key++) {
          if (Object.values(this.form.employeeNameList[key]).some((value) => value === '') || Object.values(this.form.employeeNameList[key]).some((value) => value === null)) {
          // デクリメント
            this.form.employeeNameList.splice(key--, 1)
          }
        }
      } else {
        return
      }
    },
    // 仲介業者支店名プルダウンリスト変更時処理
    brokerBranchNameUpdateA003(value) {
      this.selectedBrokerBranchNameOptionValue = value
      // リクエスト項目(formRef)のバリデーションが実行されるため、フラグ有効化
      this.isA002A003 = true
      this.$nextTick(() => {
        document.getElementById('IfaRepAddBrokerBranchNameUpdateA003').click()
      })
    },
    preRequestHandlerBrokerBranchNameUpdateA003() {
      // 初期値でない場合のみフラグ有効化
      if (this.selectedEmployeeNameOptionValue !== null) {
        // 初期化後にバリデーションが実行されるため、フラグ有効化
        this.isA002A003 = true
      }

      // 担当者名を初期化
      this.$refs.employeeNameList.clearValidate()
      this.selectedEmployeeNameOptionValue = null
    },
    responseHandlerBrokerBranchNameUpdateA003(data) {
      this.isA002A003 = false
      if (data.errorLevel === 0) {
        this.form.employeeNameList = [] // A002で取得した担当者名リストを削除
        Object.assign(this.form.employeeNameList, data.dataList[0].employeeNameList)
        // this.form.employeeNameList.unshift(employeeNameAddOptionDefaultValue) // 担当者名dropDownListに未選択時のデータを設定

        // 担当者名の空データを削除し表示しない
        for (let key = 0; key < this.form.employeeNameList.length; key++) {
          if (Object.values(this.form.employeeNameList[key]).some((value) => value === '') || Object.values(this.form.employeeNameList[key]).some((value) => value === null)) {
          // デクリメント
            this.form.employeeNameList.splice(key--, 1)
          }
        }
      } else {
        return
      }
    },
    // 担当者名プルダウンリスト変更時処理
    setEmployeeName(value) {
      this.$refs.form.clearValidate()
      this.formRef = this.$refs.form
      this.selectedEmployeeNameOptionValue = value
      this.selectedEmployeeNameOptionArray = this.form.employeeNameList.filter(object => object.employeeId === this.selectedEmployeeNameOptionValue)
      this.selectedEmployeeNameOptionLabel = this.selectedEmployeeNameOptionArray[0].employeeName
    },
    addA005(data) {
      // APIレスポンス正常時業務処理（必須）
      if (data.errorLevel === 0) {
        Object.assign(this.form, data.dataList[0])
        if (this.form.searchResultCount === 0) {
          this.dialogComfirmVisible = true
        } else {
          return
        }
      }
    },
    // 担当追加処理
    additionOkA006(data) {
      // APIレスポンス正常時業務処理（必須）
      if (data.errorLevel === 0) {
        Object.assign(this.form, data.dataList[0])
        if (this.form.searchResultCount === 0) {
          this.dialogComfirmVisible = false
          this.onDialogClose()
          this.$emit('call-search-a002')
        } else {
          return
        }
      }
    },
    // ダイアログ閉じる
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 仲介業者支店名表示・非表示チェック
    displayCheck(item) {
      if (item === '仲介業者支店名') {
        const desplayPrivId = ['3', '4', '5']
        return !desplayPrivId.includes(this.selectedInfo.privId)
      }
    }
  }
}

</script>

<style lang="scss" scoped>
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}
.ifa-input__text-default {
  width: 400px;
}
:deep(.el-input.is-disabled) .el-input__inner {
  color: #58585e;
}
:deep(.el-dialog) {
  padding: 30px 10px;
}
:deep(.el-dialog__header) {
  color: #18181A;
  padding: 0px;
}
:deep(.el-dialog__header) span{
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
}
:deep(.el-dialog__body) {
  padding: 0px;
  margin-bottom: 1rem;
}
// 確認ダイアログ
:deep(.confirm-dialog) .el-dialog{
  padding: 30px 30px 10px 30px;
}
:deep(.confirm-dialog) .el-dialog__header span{
  font-size: 16px;
  margin: 0px;
  font-weight: bold;
}
:deep(.confirm-dialog) .dialog-body{
  padding: 25px 0 25px 0;
}
:deep(.confirm-dialog) .dialog-footer{
  margin-left: 120px
}
:deep(.el-select) .el-input__inner {
  font-size: 15px;
}
:deep(.el-form-item__error) {
  margin-top: 0 !important;
}
:deep(.el-transfer-panel) {
  width: 350px;
  border: 1px solid #A7B1C3;
}
:deep(.el-transfer-panel) .el-transfer-panel__header {
  border-bottom: 1px solid #A7B1C3;
}
:deep(.el-form-item__content) {
    vertical-align: middle;
}
:deep(.el-form-item.text) .el-form-item__content {
    vertical-align: middle;
    margin-top: 4px;
}
:deep(.el-form-item__label) {
  font-weight:700;
}
</style>
