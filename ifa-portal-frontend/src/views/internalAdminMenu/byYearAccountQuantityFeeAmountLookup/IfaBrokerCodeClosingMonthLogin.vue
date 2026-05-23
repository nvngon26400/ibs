<template>
  <el-dialog
    v-model="vmIsVisible"
    width="620px"
    :center="true"
    :title="form.title.name"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    @open="onDialogOpen"
  >
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      :rules="rules"
    >
      <el-row>
        <el-col
          class="_right"
          style="padding-right: 5px;"
        >
          <ifa-button
            text="リセット"
            color="secondary"
            small
            action-type="originalAction"
            @app-action-handler="onResetForm"
          ></ifa-button>
          <ifa-button
            text="戻る"
            color="secondary"
            small
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
        <el-col>
          <div>
            <ifa-input-text
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
              :readonly="brokerCodeReadonly"
              :disabled="isDisabledButton"
              :rules="rules.brokerCode"
              @change="getBrokerNameClosingMonth"
            ></ifa-input-text>
          </div>
          <div
            style="line-height: 40px; display: flex;"
          >
            <span class="_label">仲介業者名</span>
            <span style="width: 400px; border-width: 1px;">{{ form.brokerName ? form.brokerName : '-' }}</span>
          </div>
          <div
            style="line-height: 40px; display: flex;"
          >
            <span class="_label">現在の決算月</span>
            <span style="border-width: 1px;">{{ $_getCodeValue('CLOSING_MONTH', 1, form.closingMonth) ?? '-' }}</span>
          </div>
          <ifa-input-select
            v-model="form.settingClosingMonth"
            prop="settingClosingMonth"
            label="設定する決算月"
            size="small"
            code-list-id="CLOSING_MONTH"
            :disp-pattern="1"
            :select-pattern="1"
            :clearable="false"
            :disabled="isDisabledButton"
            :rules="rules.settingClosingMonth"
          ></ifa-input-select>
        </el-col>
        <el-col style="margin:12px 0 0 24px; ">
          <ifa-button
            text="登録"
            small
            :disabled="isDisabledLoginButton"
            action-type="originalAction"
            @app-action-handler="confirmHandler"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-form>
  </el-dialog>
  <!-- 確認ダイアログ -->
  <ifa-ok-cancel-dialog
    :is-visible="dialogConfirmVisible"
    title="設定の確認"
    message="仲介業者決算月を設定します｡よろしいですか？"
    @close-modal-ok="doOperation"
    @close-modal-cancel="dialogConfirmVisible = false"
  ></ifa-ok-cancel-dialog>
  <ifa-requester
    id="IfaBrokerCodeClosingMonthLoginInitializeA001"
    action-id="SUB0406-01_01#A001"
    action-type="requestAction"
    @response-handler="responseHandlerInitializeA001($event)"
    @response-error-handler="responseErrorHandlerInitializeA001($event)"
  ></ifa-requester>
  <ifa-requester
    id="getBrokerNameClosingMonthA004"
    action-id="SUB0406-01_01#A004"
    action-type="requestAction"
    :request-model="requestModelA004"
    @response-handler="responseHandlerInitializeA004($event)"
    @response-error-handler="responseErrorHandlerA004($event)"
  ></ifa-requester>
  <ifa-requester
    id="updateBrokerCodeClosingMonthLoginA006"
    action-id="SUB0406-01_01#A006"
    action-type="requestAction"
    :request-model="IfaBrokerCodeClosingMonthLoginA006RequestModel"
    @response-handler="responseHandlerInitializeA006($event)"
  ></ifa-requester>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaBrokerCodeClosingMonthLoginFormModel } from './js/IfaBrokerCodeClosingMonthLoginFormModel'
import { IfaBrokerCodeClosingMonthLoginA006RequestModel } from './js/IfaBrokerCodeClosingMonthLoginA006RequestModel'
import IfaBrokerCodeDomainModel from '@/resource/domain/IfaBrokerCodeDomainModel.json'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'

export default {
  components: {
    IfaOkCancelDialog
  },
  props: {
    isVisible: { type: Boolean, required: true }
  },
  emits: ['close-modal', 'update-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      rules: {
        brokerCode: [{ required: true, trigger: 'blur', validator: this.brokerCodeValidator }],
        settingClosingMonth: [{ required: true, trigger: 'blur', validator: this.settingClosingMonthValidator }]
      },
      form: new IfaBrokerCodeClosingMonthLoginFormModel(),
      brokerCodeReadonly: false,
      requestModelA004: null,
      IfaBrokerCodeDomainModel: IfaBrokerCodeDomainModel,
      dialogConfirmVisible: false,
      isDisabledLoginButton: true
    }
  },
  computed: {
    isDisabledButton() {
      if (this.$store.getters.userAccount) {
        return !(this.$store.getters.userAccount.medUsers.privId === '3')
      } else {
        return true
      }
    },
    IfaBrokerCodeClosingMonthLoginA006RequestModel() {
      return new IfaBrokerCodeClosingMonthLoginA006RequestModel(this.form)
    }
  },
  watch: {
  },
  created() {
  },
  methods: {
    onDialogOpen() {
      this.form.settingClosingMonth = ''
      document.getElementById('IfaBrokerCodeClosingMonthLoginInitializeA001').click()
    },
    onResetForm() {
      this.$refs['form'].clearValidate()
      this.form.settingClosingMonth = ''
      if (this.brokerCodeReadonly === false) {
        this.form.brokerCode = ''
        this.form.brokerName = ''
        this.form.closingMonth = ''
        this.isDisabledLoginButton = true
      }
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
      this.onResetForm()
      this.form.brokerName = ''
      this.form.closingMonth = ''
    },
    responseHandlerInitializeA001(response) {
      if (response.dataList.length === 1) {
        this.brokerCodeReadonly = true
        this.form.brokerCode = response.dataList[0].brokerCode
        this.requestModelA004 = { brokerCode: this.form.brokerCode }
        document.getElementById('getBrokerNameClosingMonthA004').click()
      }
    },
    responseErrorHandlerInitializeA001(response) {
      this.brokerCodeReadonly = true
    },
    getBrokerNameClosingMonth() {
      if (this.form.brokerCode.length === 4 && /^\d+$/.test(this.form.brokerCode)) {
        this.requestModelA004 = { brokerCode: this.form.brokerCode }
        document.getElementById('getBrokerNameClosingMonthA004').click()
      }
    },
    responseHandlerInitializeA004(response) {
      this.form.brokerName = response.dataList[0]?.brokerName
      this.form.closingMonth = response.dataList[0]?.closingMonth
      this.isDisabledLoginButton = false
    },
    responseErrorHandlerA004(response) {
      this.form.brokerName = ''
      this.form.closingMonth = ''
      this.isDisabledLoginButton = true
    },
    responseHandlerInitializeA006() {
      this.$emit('update-modal')
      this.onDialogClose()
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
        document.getElementById('updateBrokerCodeClosingMonthLoginA006').click()
      })
      this.dialogConfirmVisible = false
    },
    brokerCodeValidator(rule, value, callback) {
      if (this.form.brokerCode.length < 4 && !this.brokerCodeReadonly) {
        this.form.brokerName = ''
        this.form.closingMonth = ''
        callback('仲介業者コードは4桁を入力してください。')
        return
      }
      callback()
    },
    settingClosingMonthValidator(rule, value, callback) {
      if (!this.form?.settingClosingMonth) {
        callback('設定する決算月を選択してください｡')
        return
      }
      callback()
    }
  }
}
</script>

<style lang="scss" scoped>
._right {
  text-align: right;
}
._label {
  width: 135px;
  padding-right: 12px;
  margin: 0 8px;
  font-weight: 700;
  text-align: right;
  color: #18181A
}
:deep(.el-input) {
    --el-input-width: 200px;
  }
</style>
