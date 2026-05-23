<template>
  <!-- 出金確認ダイアログ SUB0202_0601-02_1 -->
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    style="background-color: #fef0f0;"
  >

    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: right;"
      >
        <ifa-button
          id="btnBack"
          text="戻る"
          color="secondary"
          action-type="originalAction"
          style="padding-right: 0;"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :key="messageKey"
      :main-messages="messages.mains"
      :error-messages="messages.errors"
      :warning-messages="messages.warns"
    ></ifa-message-area>
    <!-- ヘッダ -->
    <!-- 顧客情報/口座情報 -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col
        :offset="1"
        :span="22"
      >
        <span>{{ $_out(accountNumber) }}</span>
      </el-col>
    </el-row>
    <el-row
      class="_bold_black_l"
      style="padding-top: 0.5rem; font-size: 20px;"
    >
      <el-col
        :offset="1"
        :span="22"
      >
        <span style="position: relative; top: 3px;">
          <el-icon><component :is="customerInfo.corporationType === '1' ? 'OfficeBuilding' : 'Avatar'"></component></el-icon>
        </span>
        <span>{{ $_out(customerName) }}</span>
        <ifa-notice-info
          :notice-info-level="customerInfo.noticeInfoLevel"
          :customer-code="customerInfo.customerCode"
          :buten-code="customerInfo.butenCode"
          :account-number="customerInfo.accountNumber"
          style="position: relative; top: 4px;"
        ></ifa-notice-info>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- ご出金内容(復唱項目) -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>ご出金内容（復唱項目）</span>
          </el-row>
          <hr>
          <!-- 出金日 -->
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>出金日</span>
            </el-col>
            <el-col
              :span="16"
              style="font-size: 16px;"
            >
              <span>{{ $_out($_getFormattedDateValue(form.payDate)) }}</span>
            </el-col>
          </el-row>
          <!-- 出金額 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>出金額</span>
            </el-col>
            <el-col
              :span="16"
              style="font-size: 16px;"
            >
              <span>{{ $_out($_withCommaInteger(form.payAmount)) }} 円</span>
            </el-col>
          </el-row>
          <!-- 受付前出金可能金額 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>現在の出金可能金額</span>
            </el-col>
            <el-col
              :span="16"
              style="font-size: 16px;"
            >
              <span>{{ $_out($_withCommaInteger(form.acBalance)) }} 円</span>
            </el-col>
          </el-row>
          <!-- 受付後出金可能金額 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受付後出金可能金額</span>
            </el-col>
            <el-col
              :span="16"
              style="font-size: 16px;"
            >
              <span>{{ $_out($_withCommaInteger(form.acBalanceAfter)) }} 円</span>
            </el-col>
          </el-row>
          <!-- 振込先金融機関 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>振込先金融機関</span>
            </el-col>
            <el-col
              :span="16"
              style="font-size: 16px; overflow-wrap: anywhere; white-space: break-spaces;"
            >
              <span>{{ $_out(form.bankKanji ) }}</span><br><span>{{ $_out(form.bankInfo ) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row
      v-if="form.noticeInfoAlert ||
        form.noticeAlert"
    >
      <el-col
        :span="22"
        :offset="1"
        style="color: #f00;"
      >
        <el-card
          class="box-card alert_content"
          style="font-size:16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem; color: #f00;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <el-row
            v-if="form.noticeInfoAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
              style="padding-left: 0rem;"
            >
              <div class="required-mark">*</div><span style="color: #f00;">注意情報の確認</span>
            </el-col>
            <el-col :span="16"
                    style="font-size: 16px;"
            >
              <ifa-input-check
                v-model="form.noticeInfoAlertConfirm"
                style="margin-left: auto; color: #f00;"
                code-list-id="NOTICE_INFO_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.noticeAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
              style="padding-left: 0rem;"
            >
              <div class="required-mark">*</div><span style="color: #f00;">重要なお知らせの確認</span>
            </el-col>
            <el-col :span="16"
                    style="font-size: 16px;"
            >
              <ifa-input-check
                v-model="form.noticeAlertConfirm"
                style="margin-left: auto; color: #f00;"
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 出金実行ボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnWithdrawExecute"
          text="出金実行"
          style="padding-left: 0;"
          action-id="SUB0202_0601-02_1#A001"
          action-type="requestAction"
          :request-model="A001RequestModel"
          @response-handler="handlerExecuteA001($event)"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaWithdrawExecuteConfirmFormModel } from './js/IfaWithdrawExecuteConfirmFormModel'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaWithdrawExecuteConfirmA001RequestModel } from './js/IfaWithdrawExecuteConfirmA001RequestModel'
export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true },
    customerInfo: { type: Object, required: true }
  },
  emits: ['close-modal', 'drawals-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaWithdrawExecuteConfirmFormModel(),
      confirmData: {},
      messages: {
        mains: [],
        errors: [],
        warns: []
      },
      messageKey: 0
    }
  },
  computed: {
    userInformation() {
      return this.$store.getters.userAccount
    },
    A001RequestModel() {
      return new IfaWithdrawExecuteConfirmA001RequestModel(this.form)
    },
    customerName() {
      return this.$_out(this.customerInfo.customerNameKanji) + '（' + this.$_out(this.customerInfo.customerNameKana) + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.formData)
      this.form.noticeInfoAlertConfirm = '0'
      this.form.noticeAlertConfirm = '0'
      this.setMsg()
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    setMsg() {
      this.messages.mains = []
      this.messages.errors = []
      this.messages.warns = []

      this.messages.mains.push('出金はまだ完了していません。')
      this.messages.mains.push('画面下の出金実行ボタンを押下してください。')
      if (this.form.noticeInfoAlert) {
        this.messages.errors.push(this.form.noticeInfoAlert)
      }
      if (this.form.noticeAlert) {
        this.messages.errors.push(this.form.noticeAlert)
      }
      this.messageKey++
    },
    handlerExecuteA001(response) {
      const res = response.dataList[0]
      this.confirmData.acBalance = res?.acBalance
      this.confirmData.acBalanceAfter = res?.acBalanceAfter
      this.confirmData.minPayDate = res?.minPayDate
      this.$emit('drawals-finish', this.confirmData)
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
<style lang="scss" scoped>
.error-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
}
.warning-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  font-size: 14px;
  white-space: pre-wrap;
}
.info-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: #000;
  font-size: 14px;
  white-space: pre-wrap;
}
.required-mark {
  display: inline-block;
  color: #ff2b2b;
  width: 8px;
}
.alert_content {
  :deep(.el-form-item) {
    margin-bottom: 0;
  }
  :deep(.el-form-item__content) {
    line-height: normal;
  }
}
:deep(.el-checkbox__label) {
  font-size: 16px;
  padding-bottom: 0px;
  color: #f00;
  font-weight: bold;
}
:deep(.el-checkbox__input.is-checked) + .el-checkbox__label {
    color: #f00;
}
</style>
