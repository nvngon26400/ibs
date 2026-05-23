<template>
  <ifa-requester
    id="IfaLoginIdUpdateRegisterA002"
    action-id="SUB0601_01-03_1#A002"
    action-type="requestAction"
    :request-model="IfaLoginIdUpdateRegisterA002RequestModel"
    @response-handler="a002ActionHandler"
  ></ifa-requester>
  <ifa-requester
    id="IfaLoginIdUpdateRegisterA003"
    action-id="SUB0601_01-03_1#A003"
    action-type="requestAction"
    :request-model="IfaLoginIdUpdateRegisterA003RequestModel"
    @response-handler="a003ActionHandler"
  ></ifa-requester>
  <ifa-requester
    id="IfaLoginIdUpdateRegisterA004"
    action-id="SUB0601_01-03_1#A004"
    action-type="requestAction"
    :request-model="IfaLoginIdUpdateRegisterA004RequestModel"
    @response-handler="a004ActionHandler"
  ></ifa-requester>
  <ifa-requester
    id="IfaLoginIdUpdateRegisterA005"
    action-id="SUB0601_01-03_1#A005"
    action-type="requestAction"
    :request-model="IfaLoginIdUpdateRegisterA005RequestModel"
    @response-handler="a005ActionHandler"
  ></ifa-requester>
  <ifa-requester
    id="IfaLoginIdUpdateRegisterA014"
    action-id="SUB0601_01-03_1#A014"
    action-type="requestAction"
    :request-model="IfaLoginIdUpdateRegisterA014RequestModel"
    @response-handler="a014ActionHandler"
  ></ifa-requester>
  <ifa-requester
    id="IfaLoginIdUpdateRegisterA014DeleteCcsData"
    action-id="SUB0601_01-03_1#A014_DeleteCcsData"
    action-type="requestAction"
    :request-model="IfaLoginIdUpdateRegisterA014DeleteCcsDataRequestModel"
    @response-handler="a014DeleteCcsDataActionHandler"
  ></ifa-requester>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :show-close="false"
      width="990px"
      :center="true"
      title="ログインID更新"
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
              action-id="SUB0601_01-03_1#A006"
              action-type="requestAction"
              :request-model="IfaLoginIdUpdateRegisterA006RequestModel"
              @response-handler="a006ActionHandler"
            ></ifa-button>
            <ifa-button
              id="btnBack"
              text="戻る"
              width="110"
              color="secondary"
              style="padding-right: 0;"
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
            :rules="rules"
            :inline="true"
          >
            <!-- ログインID -->
            <el-row>
              <ifa-input-text
                v-model="form.loginId"
                prop="loginId"
                label="ログインID"
                size="small"
                maxlength="16"
                :disabled="true"
                :required="true"
                :domain="IfaLoginIdDomainModel"
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
                :type="isPasswordVisible ? 'text' : 'password'"
                size="small"
                maxlength="32"
                :disabled="true"
                :required="true"
                :domain="IfaPasswordDomainModel"
                class="ifa-input__text-default"
                style="width: 400px;"
              ></ifa-input-text>
              <ifa-button
                id="btnResetPassword"
                text="再設定"
                width="140"
                color="secondary"
                small
                action-type="originalAction"
                @app-action-handler="resetPassword"
              ></ifa-button>
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
                :domain="IfaText255InputDomainModel"
                :disabled="disabledUserName"
                style="width: 400px;"
                original-screen-id="SUB0601_01-03_1"
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
            <!-- CCS ID -->
            <el-row>
              <ifa-input-text
                v-model="form.ccsId"
                prop="ccsId"
                label="CCS ID"
                type="password"
                size="small"
                maxlength="127"
                class="ifa-input__text-default"
                disabled
                style="width: 400px;"
              ></ifa-input-text>
              <ifa-button
                id="btnCcsReset"
                text="CCS情報リセット"
                width="140"
                color="secondary"
                small
                action-type="originalAction"
                @app-action-handler="dialogCcsVisible = true"
              ></ifa-button>
            </el-row>
            <!-- CCS PW -->
            <el-row>
              <ifa-input-text
                v-model="form.ccsPassword"
                prop="ccsPassword"
                label="CCS PW"
                type="password"
                size="small"
                maxlength="127"
                class="ifa-input__text-default"
                disabled
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
                :disabled="selectedInfo.repNumber > 1"
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
                :disabled="selectedInfo.repNumber > 1"
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
                :disabled="selectedInfo.repNumber > 1"
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
                :disabled="selectedInfo.repNumber > 1"
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
                :disabled="selectedInfo.repNumber > 1"
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
              text="更新"
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
      title="ユーザー更新"
      message="ユーザー情報を更新します。よろしいですか？"
      @close-modal-ok="doOperation"
      @close-modal-cancel="dialogConfirmVisible = false"
    ></ifa-ok-cancel-dialog>

    <!-- CCSリセット -->
    <ifa-ok-cancel-dialog
      :is-visible="dialogCcsVisible"
      title="CCSリセット"
      :message="'CCS IDとCCS PWをリセットします。よろしいですか？\n更新ボタン押下後、CCS情報のリセットが行われます。'"
      @close-modal-ok="clearCcsInfo"
      @close-modal-cancel="dialogCcsVisible = false"
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
import { IfaLoginIdUpdateRegisterFormModel } from './js/IfaLoginIdUpdateRegisterFormModel'
import { IfaLoginIdUpdateRegisterA002RequestModel } from './js/IfaLoginIdUpdateRegisterA002RequestModel'
import { IfaLoginIdUpdateRegisterA003RequestModel } from './js/IfaLoginIdUpdateRegisterA003RequestModel'
import { IfaLoginIdUpdateRegisterA004RequestModel } from './js/IfaLoginIdUpdateRegisterA004RequestModel'
import { IfaLoginIdUpdateRegisterA005RequestModel } from './js/IfaLoginIdUpdateRegisterA005RequestModel'
import { IfaLoginIdUpdateRegisterA006RequestModel } from './js/IfaLoginIdUpdateRegisterA006RequestModel'
import { IfaLoginIdUpdateRegisterA014RequestModel } from './js/IfaLoginIdUpdateRegisterA014RequestModel'
import { IfaLoginIdUpdateRegisterA014DeleteCcsDataRequestModel } from './js/IfaLoginIdUpdateRegisterA014DeleteCcsDataRequestModel'
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
    selectedInfo: {
      type: Object,
      required: false,
      default: () => ({})
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
      IfaLoginIdDomainModel: IfaLoginIdDomainModel,
      IfaPasswordDomainModel: IfaPasswordDomainModel,
      IfaText255InputDomainModel: IfaText255InputDomainModel,
      IfaMailAddressDomainModel: IfaMailAddressDomainModel,
      form: new IfaLoginIdUpdateRegisterFormModel(),
      disabledUserName: true,
      dialogCcsVisible: false,
      dialogConfirmVisible: false,
      privIdOptions: privIdOptions,
      rules: {
        displayList: [{ type: 'array', validator: this.displayListValidator, trigger: 'blur' }],
        branchCode: { type: String, validator: this.branchCodeValidator, trigger: 'blur' },
        brokerCode: { type: String, validator: this.brokerCodeValidator, trigger: 'bler' },
        subBrokerId: { type: String, validator: this.subBrokerIdValidator, trigger: 'blur' },
        employeeId: { type: String, validator: this.employeeIdValidator, trigger: 'blur' }
      },
      isPasswordVisible: false,
      initializing: true,
      clearCcsInfoFlag: false,
      resetFlag: false,
      transferKey: 0
    }
  },
  computed: {
    IfaLoginIdUpdateRegisterA002RequestModel() { return new IfaLoginIdUpdateRegisterA002RequestModel(this.form) },
    IfaLoginIdUpdateRegisterA003RequestModel() { return new IfaLoginIdUpdateRegisterA003RequestModel(this.form) },
    IfaLoginIdUpdateRegisterA004RequestModel() { return new IfaLoginIdUpdateRegisterA004RequestModel(this.form) },
    IfaLoginIdUpdateRegisterA005RequestModel() { return new IfaLoginIdUpdateRegisterA005RequestModel(this.form) },
    IfaLoginIdUpdateRegisterA006RequestModel() { return new IfaLoginIdUpdateRegisterA006RequestModel(this.form) },
    IfaLoginIdUpdateRegisterA014RequestModel() { return new IfaLoginIdUpdateRegisterA014RequestModel(this.form) },
    IfaLoginIdUpdateRegisterA014DeleteCcsDataRequestModel() { return new IfaLoginIdUpdateRegisterA014DeleteCcsDataRequestModel(this.form) }
  },
  watch: {
    // 所属権限
    'form.privId': function(newVal, oldVal) {
      const ableList = ['1', '2', '11', '12']
      const isDisabledUserName = !ableList.includes(newVal)
      const isChangedToVisibleEmployeeId = !this.disabledUserName && isDisabledUserName
      this.disabledUserName = isDisabledUserName
      // 初期表示時、リセット時のウォッチャー動作をスキップ
      if (this.initializing || this.resetFlag) return
      if (newVal) {
        this.$nextTick(() => {
          document.getElementById('IfaLoginIdUpdateRegisterA002').click()
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
        // 所属権限が本店または支店から変更され、担当者名が表示されるようになった場合ユーザ名を空文字に
        if (isChangedToVisibleEmployeeId) {
          this.form.userName = ''
        }
      }
    },
    // 本店／支店名
    'form.branchCode': function(newVal, oldVal) {
      // 初期表示時、リセット時のウォッチャー動作をスキップ
      if (this.initializing || this.resetFlag) return
      if (newVal) {
        this.$nextTick(() => {
          document.getElementById('IfaLoginIdUpdateRegisterA003').click()
        })
      } else {
        // 本店／支店名がクリアされたときは、配下のリストもクリア
        this.form.brokerNameList = []
        this.form.branchNameList = []
        this.form.chargeNameList = []
      }
      if (oldVal !== '') {
        this.form.brokerCode = ''
        this.form.subBrokerId = ''
        this.form.employeeId = ''
      }
    },
    // 仲介業者名
    'form.brokerCode': function(newVal, oldVal) {
      // 初期表示時、リセット時のウォッチャー動作をスキップ
      if (this.initializing || this.resetFlag) return
      if (newVal) {
        this.$nextTick(() => {
          document.getElementById('IfaLoginIdUpdateRegisterA004').click()
        })
      } else {
        // 仲介業者名がクリアされたときは、配下のリストもクリア
        this.form.branchNameList = []
        this.form.chargeNameList = []
      }
      if (oldVal !== '') {
        this.form.subBrokerId = ''
        this.form.employeeId = ''
      }
    },
    // 仲介業者支店名
    'form.subBrokerId': function(newVal, oldVal) {
      // 初期表示時、リセット時のウォッチャー動作をスキップ
      if (this.initializing || this.resetFlag) return
      if (newVal) {
        this.$nextTick(() => {
          document.getElementById('IfaLoginIdUpdateRegisterA005').click()
        })
      } else {
        // 仲介業者支店名がクリアされた時は、配下のリストもクリア
        this.form.chargeNameList = []
      }
      if (oldVal !== '') {
        this.form.employeeId = ''
      }
    },
    // 担当者名
    'form.employeeId': function(newVal, oldVal) {
      // 初期表示時、リセット時のウォッチャー動作をスキップ
      if (this.initializing || this.resetFlag) return
      if (!this.disabledUserName === false) { // 担当者名が表示状態の場合
        if (newVal) { // 新しい担当者名が選択されている場合
          const chargeName = this.form.chargeNameList.find(item => item.key === newVal)
          this.form.userName = chargeName ? chargeName.value : ''
          this.form.chargeName = chargeName ? chargeName.value : ''
        } else { // 担当者名が未選択の場合
          this.form.userName = ''
          this.form.chargeName = ''
        }
      }
    }
  },
  methods: {
    onShow() {
      this.initializing = true
      this.form.beforeLoginId = this.selectedInfo.loginId
      this.form.beforePrivId = this.selectedInfo.privId
      this.form.beforeBranchCode = this.selectedInfo.branchCode
      this.form.beforeBrokerCode = this.selectedInfo.brokerCode
      this.form.beforeSubBrokerId = this.selectedInfo.subBrokerId
      this.form.beforeEmployeeId = this.selectedInfo.employeeId
      this.form.beforeUserName = this.selectedInfo.userName
      this.form.beforeMailAddress = this.selectedInfo.mailAddress
      this.formData.headOfficeBranchNameList.forEach(item => {
        item.value = item.key + ' ' + item.value
      })
      this.form = Object.assign(this.form, this.formData)
      this.form = Object.assign(this.form, this.selectedInfo)
      this.form.password = '******'
      this.form.ccsId = '******'
      this.form.ccsPassword = '******'
      // 初期化処理が完了したらフラグをfalseに設定
      this.$nextTick(() => {
        this.initializing = false
      })
      this.clearCcsInfoFlag = false
      this.resetFlag = false
      this.$refs['form'].clearValidate()
    },
    a002ActionHandler(data) {
      data.dataList[0].headOfficeBranchNameList.forEach(item => {
        item.value = item.key + ' ' + item.value
      })
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
      this.resetFlag = true
      this.transferKey++
      this.form = Object.assign(this.form, data.dataList[0])
      this.form.loginId = this.form.beforeLoginId
      this.form.privId = this.form.beforePrivId
      this.form.branchCode = this.form.beforeBranchCode
      this.form.brokerCode = this.form.beforeBrokerCode
      this.form.subBrokerId = this.form.beforeSubBrokerId
      this.form.employeeId = this.form.beforeEmployeeId
      this.form.userName = this.form.beforeUserName
      this.form.mailAddress = this.form.beforeMailAddress
      // キーと支店名を接続
      this.formData.headOfficeBranchNameList = this.form.headOfficeBranchNameList
      this.formData.headOfficeBranchNameList.forEach(item => {
        item.value = item.key + ' ' + item.value
      })
      this.form = Object.assign(this.form, this.formData)
      this.form.password = '******'
      this.form.ccsId = '******'
      this.form.ccsPassword = '******'
      // パスワード表示を非表示にする
      this.isPasswordVisible = false
      this.clearCcsInfoFlag = false
      this.$nextTick(() => {
        this.resetFlag = false
      })
      this.$refs['form'].clearValidate()
    },
    a014ActionHandler(data) {
      this.dialogConfirmVisible = false
      // CCS情報のリセット時に、サーバーへリクエスト送信
      if (this.clearCcsInfoFlag) {
        this.$nextTick(() => {
          document.getElementById('IfaLoginIdUpdateRegisterA014DeleteCcsData').click()
        })
      }
      this.$nextTick(() => {
        this.$emit('update-table')
      })
    },
    a014DeleteCcsDataActionHandler(data) {
      return
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
      if (this.form.password === '******') {
        // パスワードが初期値のままの場合は、空文字をセットする
        this.form.password = ''
      }
      if (this.form.ccsId === '******') {
        // CCSIDが初期値のままの場合は、空文字をセットする
        this.form.ccsId = ''
      }
      if (this.form.ccsPassword === '******') {
        // CCSPWが初期値のままの場合は、空文字をセットする
        this.form.ccsPassword = ''
      }
      this.$nextTick(() => {
        document.getElementById('IfaLoginIdUpdateRegisterA014').click()
      })
    },
    // ダイアログ閉じる
    onDialogClose() {
      this.form = new IfaLoginIdUpdateRegisterFormModel()
      this.$emit('close-modal')
      // ダイアログを閉じたら初期化中フラグをtrueにリセット
      this.initializing = true
    },
    // CCS情報をクリア
    clearCcsInfo() {
      this.form.ccsId = ''
      this.form.ccsPassword = ''
      this.dialogCcsVisible = false
      this.clearCcsInfoFlag = true
    },
    // パスワードリセット
    resetPassword() {
      const crypto = require('crypto')
      const S = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789'
      const N = 10
      const newPassword = Array.from(crypto.randomFillSync(new Uint8Array(N))).map((n) => S[n % S.length]).join('')
      this.form.password = newPassword
      this.isPasswordVisible = true
    },
    changeDisplayList() {
      this.$refs['form'].validateField('displayList', (valid) => {
        // validationのNG,OKに関わらず何もしない
        return
      })
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
