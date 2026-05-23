<template>
  <!-- 出金完了ダイアログ SUB0202_0601-02_2_2 -->
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
    <!-- エラー/警告表示 -->
    <ifa-message-area
      :key="messageKey"
      :main-messages="messages.mains"
      :warning-messages="messages.warns"
      :error-messages="messages.errors"
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
              <span>{{ $_out($_getFormattedDateValue(formData.payDate)) }}</span>
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
              <span>{{ $_out($_withCommaInteger(formData.payAmount)) }} 円</span>
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
              <span>{{ $_out($_withCommaInteger(formData.acBalance)) }} 円</span>
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
              <span>{{ $_out($_withCommaInteger(formData.acBalanceAfter)) }} 円</span>
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
              <span>{{ $_out(formData.bankKanji ) }}</span><br><span>{{ $_out(formData.bankInfo ) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- 出金明細一覧へボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          text="出金明細一覧へ"
          action-id="SUB0202_0601-02_2#A002"
          action-type="requestAction"
          style="padding-left: 0;"
          @response-handler="handlerMoveInputA002($event)"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaWithdrawAcceptCompleteFormModel } from './js/IfaWithdrawAcceptCompleteFormModel'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
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
  emits: ['close-modal', 'move-to-drawals-list', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaWithdrawAcceptCompleteFormModel(),
      cancelComfirmVisible: false,
      messages: {
        mains: [],
        errors: [],
        warns: []
      },
      messageKey: 0
    }
  },
  computed: {
    customerName() {
      return this.$_out(this.customerInfo.customerNameKanji) + '（' + this.$_out(this.customerInfo.customerNameKana) + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    }
  },
  methods: {
    onShow() {
      this.setMsg()
    },
    setMsg() {
      this.messages.mains = []
      this.messages.warns = []
      this.messages.errors = []
      this.messages.mains.push('下記の内容で出金を受け付けました。')
      this.messageKey++
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    handlerMoveInputA002(response) {
      this.$emit('move-to-drawals-list', response.dataList[0]?.minPayDate)
    }
  }
}
</script>

<style lang="scss">
@import "@/styles/orderStatusList.scss";
</style>
