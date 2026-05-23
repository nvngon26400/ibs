<template>
  <ifa-requester
    id="IfaLoginIdNewRegisterA002"
    action-id="SUB0601_01-02_1#A002"
    action-type="requestAction"
    :request-model="IfaLoginIdNewRegisterA002RequestModel"
    @response-handler="a002ActionHandler"
  ></ifa-requester>
  <ifa-requester
    id="IfaLoginIdNewRegisterA003"
    action-id="SUB0601_01-02_1#A003"
    action-type="requestAction"
    :request-model="IfaLoginIdNewRegisterA003RequestModel"
    @response-handler="a003ActionHandler"
  ></ifa-requester>
  <ifa-requester
    id="IfaLoginIdNewRegisterA004"
    action-id="SUB0601_01-02_1#A004"
    action-type="requestAction"
    :request-model="IfaLoginIdNewRegisterA004RequestModel"
    @response-handler="a004ActionHandler"
  ></ifa-requester>
  <ifa-requester
    id="IfaLoginIdNewRegisterA005"
    action-id="SUB0601_01-02_1#A005"
    action-type="requestAction"
    :request-model="IfaLoginIdNewRegisterA005RequestModel"
    @response-handler="a005ActionHandler"
  ></ifa-requester>
  <ifa-requester
    id="IfaLoginIdNewRegisterA011"
    action-id="SUB0601_01-02_1#A011"
    action-type="requestAction"
    :request-model="IfaLoginIdNewRegisterA011RequestModel"
    @response-handler="a011ActionHandler"
  ></ifa-requester>

  <div>
    <el-dialog
      v-model="vmIsVisible"
      :show-close="false"
      width="990px"
      :center="true"
      title="ログインID登録"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      class="loginUserManagementInput"
      @open="onShow"
    >
      <!-- メイン -->
      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-row style="margin-left: 603.5px">
            <ifa-button
              id="btnReset"
              text="リセット"
              width="110"
              color="secondary"
              action-id="SUB0601_01-02_1#A006"
              action-type="requestAction"
              :request-model="IfaLoginIdNewRegisterA006RequestModel"
              @response-handler="a006ActionHandler"
            ></ifa-button>
            <ifa-button
              id="btnBack"
              text="戻る"
              width="110"
              color="secondary"
              action-type="originalAction"
              @app-action-handler="onDialogClose"
            ></ifa-button>
          </el-row>
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
              <ifa-input-text
                v-model="form.loginId"
                prop="loginId"
                label="ログインID"
                size="small"
                maxlength="16"
                :required="true"
                :domain="IfaLoginIdDomainModel"
                :rules="rules.loginId"
                class="ifa-input__text-default"
                style="width: 400px;"
              ></ifa-input-text>
            </el-row>
            <!-- パスワード -->
            <el-row>
              <ifa-input-text
                v-model="form.password"
                prop="password"
                label="パスワード"
                type="password"
                size="small"
                maxlength="32"
                :required="true"
                :domain="IfaPasswordDomainModel"
                :rules="rules.password"
                class="ifa-input__text-default"
                style="width: 400px; margin-right: 1rem;"
              ></ifa-input-text>
            </el-row>
            <!-- ユーザー名 -->
            <el-row>
              <ifa-input-text
                id="userName"
                v-model="form.userName"
                prop="userName"
                label="ユーザー名"
                size="small"
                maxlength="255"
                :required="true"
                class="ifa-input__text-default"
                original-screen-id="SUB0601_01-02_1"
                :domain="IfaText255InputDomainModel"
                :disabled="disabledUserName"
                style="width: 400px;"
              ></ifa-input-text>
            </el-row>
            <!-- メールアドレス -->
            <el-row>
              <ifa-input-text
                v-model="form.mailAddress"
                prop="mailAddress"
                label="メールアドレス"
                size="small"
                maxlength="127"
                class="ifa-input__text-default"
                :domain="IfaMailAddressDomainModel"
                style="width: 400px;"
              ></ifa-input-text>
            </el-row>
            <!-- 所属権限 -->
            <el-row>
              <ifa-input-select
                v-model="form.privId"
                prop="privId"
                label="所属権限"
                size="small"
                :required="true"
                class="ifa-input__text-default"
                code-list-id="original"
                placeholder="--選択してください--"
                :original-list="privIdOptions"
                style="width: 400px;"
              >
              </ifa-input-select>
            </el-row>
            <!-- 本店／支店名 -->
            <el-row v-if="!['11', '12'].includes(form.privId) && form.privId > 0">
              <ifa-input-select
                v-model="form.branchCode"
                prop="branchCode"
                label="本店／支店名"
                size="small"
                class="ifa-input__text-default"
                code-list-id="original"
                placeholder="--選択してください--"
                :original-list="form.headOfficeBranchNameList"
                style="width: 400px;"
                :rules="rules.branchCode"
              >
              </ifa-input-select>
            </el-row>
            <!-- 仲介業者名 -->
            <el-row v-if="!['11', '12'].includes(form.privId) && form.privId > 2">
              <ifa-input-select
                v-model="form.brokerCode"
                prop="brokerCode"
                label="仲介業者名"
                size="small"
                class="ifa-input__text-default"
                code-list-id="original"
                placeholder="--選択してください--"
                :original-list="form.brokerNameList"
                style="width: 400px;"
                :rules="rules.brokerCode"
              >
              </ifa-input-select>
            </el-row>
            <!-- 仲介業者支店名 -->
            <el-row v-if="!['3', '4', '5', '11', '12'].includes(form.privId) && form.privId > 2">
              <ifa-input-select
                v-model="form.subBrokerId"
                prop="subBrokerId"
                label="仲介業者支店名"
                size="small"
                class="ifa-input__text-default"
                code-list-id="original"
                placeholder="--選択してください--"
                :original-list="form.branchNameList"
                style="width: 400px;"
                :rules="rules.subBrokerId"
              >
              </ifa-input-select>
            </el-row>
            <!-- 担当者名 -->
            <el-row v-if="!['11', '12'].includes(form.privId) && form.privId > 2">
              <ifa-input-select
                v-model="form.employeeId"
                prop="employeeId"
                label="担当者名"
                size="small"
                class="ifa-input__text-default"
                code-list-id="original"
                placeholder="--選択してください--"
                :original-list="form.chargeNameList"
                style="width: 400px;"
                :rules="rules.employeeId"
              >
              </ifa-input-select>
            </el-row>
            <!-- 非表示 / 表示 -->
            <el-row style="margin-left: 2rem">
              <el-form-item
                prop="displayList"
                class="transfer_error"
              >
                <el-transfer
                  :key="transferKey"
                  v-model="form.displayList"
                  :data="form.unDisplayList"
                  :titles="['非表示', '表示']"
                  :format="{noChecked: ' ', hasChecked: ' '}"
                  style="min-width: 900px;"
                  @change="changeDisplayList"
                ></el-transfer>
              </el-form-item>
            </el-row>
          </el-form>
        </el-col>

        <el-col
          :span="22"
          :offset="1"
        >
          <el-row style="margin-left: 2rem">
            <ifa-button
              text="登録"
              width="110"
              color="primary"
              style="padding-left: 0;"
              action-type="originalAction"
              @app-action-handler="confirmHandler"
            ></ifa-button>
          </el-row>
        </el-col>
      </el-row>
    </el-dialog>

    <!-- 確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="dialogConfirmVisible"
      title="ユーザー登録"
      message="ユーザー情報を登録します。よろしいですか？"
      @close-modal-ok="doOperation"
      @close-modal-cancel="dialogConfirmVisible = false"
    ></ifa-ok-cancel-dialog>

  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
// domain
import IfaLoginIdDomainModel from '@/resource/domain/IfaLoginIdDomainModel.json'
import IfaPasswordDomainModel from '@/resource/domain/IfaPasswordDomainModel.json'
import IfaText255InputDomainModel from '@/resource/domain/IfaText255InputDomainModel.json'
import IfaMailAddressDomainModel from '@/resource/domain/IfaMailAddressDomainModel.json'
// model
import { IfaLoginIdNewRegisterFormModel } from './js/IfaLoginIdNewRegisterFormModel'
import { IfaLoginIdNewRegisterA002RequestModel } from './js/IfaLoginIdNewRegisterA002RequestModel'
import { IfaLoginIdNewRegisterA003RequestModel } from './js/IfaLoginIdNewRegisterA003RequestModel'
import { IfaLoginIdNewRegisterA004RequestModel } from './js/IfaLoginIdNewRegisterA004RequestModel'
import { IfaLoginIdNewRegisterA005RequestModel } from './js/IfaLoginIdNewRegisterA005RequestModel'
import { IfaLoginIdNewRegisterA006RequestModel } from './js/IfaLoginIdNewRegisterA006RequestModel'
import { IfaLoginIdNewRegisterA011RequestModel } from './js/IfaLoginIdNewRegisterA011RequestModel'
import { getMessage } from '@/utils/errorHandler'

// 所属権限一覧
const privIdOptions = [
  { key: '1', value: '本店' },
  { key: '2', value: '支店' },
  { key: '3', value: '仲介業者(内部管理責任者)' },
  { key: '4', value: '仲介業者(営業責任者)' },
  { key: '5', value: '仲介業者(外務員)' },
  { key: '6', value: '仲介業者支店(内部管理責任者)' },
  { key: '7', value: '仲介業者支店(営業責任者)' },
  { key: '8', value: '仲介業者支店(外務員)' },
  { key: '9', value: '担当' },
  { key: '11', value: '業務部' },
  { key: '12', value: '管理部' }
]

export default {
  components: {
    IfaOkCancelDialog
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    formData: {
      type: Object,
      required: true
    }
  },
  emits: ['close-modal', 'update-table', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      transferKey: 0,
      IfaLoginIdDomainModel: IfaLoginIdDomainModel,
      IfaPasswordDomainModel: IfaPasswordDomainModel,
      IfaText255InputDomainModel: IfaText255InputDomainModel,
      IfaMailAddressDomainModel: IfaMailAddressDomainModel,
      form: new IfaLoginIdNewRegisterFormModel(),
      disabledUserName: true,
      dialogCcsVisible: false,
      dialogConfirmVisible: false,
      privIdOptions: privIdOptions,
      rules: {
        loginId: [{ type: 'string', validator: this.loginIdValidator, trigger: 'blur' }],
        password: [{ type: 'string', validator: this.passwordValidator, trigger: 'blur' }],
        displayList: [{ type: 'array', validator: this.displayListValidator, trigger: 'blur' }],
        branchCode: { type: String, validator: this.branchCodeValidator, trigger: 'blur' },
        brokerCode: { type: String, validator: this.brokerCodeValidator, trigger: 'bler' },
        subBrokerId: { type: String, validator: this.subBrokerIdValidator, trigger: 'blur' },
        employeeId: { type: String, validator: this.employeeIdValidator, trigger: 'blur' }
      }
    }
  },
  computed: {
    IfaLoginIdNewRegisterA002RequestModel() { return new IfaLoginIdNewRegisterA002RequestModel(this.form) },
    IfaLoginIdNewRegisterA003RequestModel() { return new IfaLoginIdNewRegisterA003RequestModel(this.form) },
    IfaLoginIdNewRegisterA004RequestModel() { return new IfaLoginIdNewRegisterA004RequestModel(this.form) },
    IfaLoginIdNewRegisterA005RequestModel() { return new IfaLoginIdNewRegisterA005RequestModel(this.form) },
    IfaLoginIdNewRegisterA006RequestModel() { return new IfaLoginIdNewRegisterA006RequestModel(this.form) },
    IfaLoginIdNewRegisterA011RequestModel() { return new IfaLoginIdNewRegisterA011RequestModel(this.form) }
  },
  watch: {
    // 所属権限
    'form.privId': function(newVal, oldVal) {
      const ableList = ['1', '2', '11', '12']
      this.disabledUserName = !ableList.includes(newVal)
      if (newVal) {
        this.$nextTick(() => {
          document.getElementById('IfaLoginIdNewRegisterA002').click()
        })
      } else {
        // 所属権限がクリアされたときは、配下のリストもクリア
        this.form.headOfficeBranchNameList = []
        this.form.brokerNameList = []
        this.form.branchNameList = []
        this.form.chargeNameList = []
      }
      // 所属権限が更新されたときは、配下の値もクリア
      if (oldVal !== '') {
        this.form.branchCode = ''
        this.form.brokerCode = ''
        this.form.subBrokerId = ''
        this.form.employeeId = ''
      }
    },
    // 本店／支店名
    'form.branchCode': function(newVal, oldVal) {
      if (oldVal !== '') {
        this.form.brokerCode = ''
        this.form.subBrokerId = ''
        this.form.employeeId = ''
      }
      if (newVal) {
        this.$nextTick(() => {
          document.getElementById('IfaLoginIdNewRegisterA003').click()
        })
      } else {
        // 本店／支店名がクリアされたときは、配下のリストもクリア
        this.form.brokerNameList = []
        this.form.branchNameList = []
        this.form.chargeNameList = []
      }
    },
    // 仲介業者名
    'form.brokerCode': function(newVal, oldVal) {
      if (oldVal !== '') {
        this.form.subBrokerId = ''
        this.form.employeeId = ''
      }
      if (newVal) {
        this.$nextTick(() => {
        // eslint-disable-next-line indent
        document.getElementById('IfaLoginIdNewRegisterA004').click()
        })
      } else {
        // 仲介業者名がクリアされたときは、配下のリストもクリア
        this.form.branchNameList = []
        this.form.chargeNameList = []
      }
    },
    // 仲介業者支店名
    'form.subBrokerId': function(newVal, oldVal) {
      if (oldVal !== '') {
        this.form.employeeId = ''
      }
      if (newVal) {
        this.$nextTick(() => {
          document.getElementById('IfaLoginIdNewRegisterA005').click()
        })
      } else {
        // 仲介業者支店名がクリアされた時は、配下のリストもクリア
        this.form.chargeNameList = []
      }
    },
    // 担当者名
    'form.employeeId': function(newVal, oldVal) {
      const chargeName = this.form.chargeNameList.filter(item => item.key === newVal)[0]
      this.form.userName = chargeName ? chargeName.value : ''
      this.form.chargeName = chargeName ? chargeName.value : ''
    }
  },
  methods: {
    onShow() {
      this.form = new IfaLoginIdNewRegisterFormModel()
      this.form = Object.assign(this.form, this.formData)
      this.$refs['form'].clearValidate()
    },
    a002ActionHandler(data) {
      if (data.dataList[0].headOfficeBranchNameList) {
        data.dataList[0].headOfficeBranchNameList.forEach(item => {
          item.value = item.key + ' ' + item.value
        })
      }
      this.form = Object.assign(this.form, data.dataList[0])
    },
    a003ActionHandler(data) {
      this.form = Object.assign(this.form, data.dataList[0])
    },
    a004ActionHandler(data) {
      this.form = Object.assign(this.form, data.dataList[0])
    },
    a005ActionHandler(data) {
      this.form = Object.assign(this.form, data.dataList[0])
    },
    a006ActionHandler(data) {
      this.transferKey++
      this.onShow()
    },
    a011ActionHandler(data) {
      this.dialogConfirmVisible = false
      this.$emit('update-table')
    },
    // 確認ダイアログ表示
    confirmHandler() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.dialogConfirmVisible = true
        } else {
          return false
        }
      })
    },
    // 登録・更新処理
    doOperation() {
      this.$nextTick(() => {
        document.getElementById('IfaLoginIdNewRegisterA011').click()
      })
    },
    // ダイアログ閉じる
    onDialogClose() {
      this.form = new IfaLoginIdNewRegisterFormModel()
      this.$emit('close-modal')
    },
    changeDisplayList() {
      this.$refs['form'].validateField('displayList', (valid) => {
        // validationのNG,OKに関わらず何もしない
        return
      })
    },
    loginIdValidator(rule, value, callback) {
      if (value.length < 6 || value.length > 16) {
        const params = ['ログインID', '6', '16']
        const msg = getMessage('errors.size', params)
        callback(msg)
        return
      }
      // OK
      callback()
    },
    passwordValidator(rule, value, callback) {
      if (value.length < 6 || value.length > 32) {
        const params = ['パスワード', '6', '32']
        const msg = getMessage('errors.size', params)
        callback(msg)
        return
      }
      // OK
      callback()
    },
    displayListValidator(rule, value, callback) {
      if (value.length === 0) {
        const params = ['【表示】エリアのメニューリスト']
        const msg = getMessage('errors.required', params)
        callback(msg)
        return
      } else {
        callback()
      }
    },
    branchCodeValidator(rule, value, callback) {
      if (!value) {
        callback(getMessage('errors.required', ['本店／支店名']))
        return
      }
      callback()
    },
    brokerCodeValidator(rule, value, callback) {
      if (!value) {
        callback(getMessage('errors.required', ['仲介業者名']))
        return
      }
      callback()
    },
    subBrokerIdValidator(rule, value, callback) {
      if (!value) {
        callback(getMessage('errors.required', ['仲介業者支店名']))
        return
      }
      callback()
    },
    employeeIdValidator(rule, value, callback) {
      if (!value) {
        callback(getMessage('errors.required', ['担当者名']))
        return
      }
      callback()
    }
  }
}
</script>

<style lang="scss" scoped>
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}
:deep(.el-transfer-panel) {
  width: 350px;
  border: 1px solid #A7B1C3;
}
:deep(.el-transfer-panel) .el-transfer-panel__header {
  border-bottom: 1px solid #A7B1C3;
}
:deep(.el-transfer__buttons) {
  display: inline-grid;
}
:deep(.el-transfer__button) {
  margin-left: 0;
}
.transfer_error :deep(.el-form-item__error) {
  margin-left: 455px;
}
</style>
