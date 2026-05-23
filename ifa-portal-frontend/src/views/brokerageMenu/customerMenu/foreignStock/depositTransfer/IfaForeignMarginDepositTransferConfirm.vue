<template>
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
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
          style="padding-right: 0;"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="['振替指示はまだ完了していません。画面下の振替指示ボタンを押下してください。']"
    ></ifa-message-area>

    <!-- ヘッダ -->
    <!-- 顧客情報/口座情報 -->
    <el-row
      class="_bold_black_l"
      style="font-weight: bold;"
    >
      <el-col
        :offset="1"
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

    <!-- 注文内容(復唱項目) -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="padding: 0.3rem 0 0.6rem 0"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>振替内容（復唱項目）</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>振替口座</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                code-list-id="DEPOSIT_TRANSFER_TYPE"
                :disp-pattern="2"
                :code-key="form.accountSelect"
              ></ifa-text>
              <span v-if="!form.accountSelect">-</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>振替指定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.transferDate ? $_getFormattedDateValue(form.transferDate) : '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>指示金額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.destinationAmount ? `${$_withCommaZeroPadding(form.destinationAmount, 2)}USD` : '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>現在の振替指示可能額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.currentTransferDestinationAbleAmount ? `${$_withCommaZeroPadding(form.currentTransferDestinationAbleAmount, 2)}USD` : '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受付後振替指示可能額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.afterTransferDestinationAbleAmount ? `${$_withCommaZeroPadding(form.afterTransferDestinationAbleAmount, 2)}USD` : '-' }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- 振替指示前 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="padding: 0.3rem 0 0.6rem 0"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>振替結果（予定）　振替指示前</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>信用建余力</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.marginPositionPowerBefore ? `${$_withCommaZeroPadding(form.marginPositionPowerBefore, 2)}USD` : '--' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>委託保証金率</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.marginDepositRateBefore ? `${$_withCommaZeroPadding(form.marginDepositRateBefore, 2)}%` : '--' }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- 振替指示後 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="padding: 0.3rem 0 0.6rem 0"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>振替結果（予定）　振替指示後</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>信用建余力</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.marginPositionPowerAfter ? `${$_withCommaZeroPadding(form.marginPositionPowerAfter, 2)}USD` : '--' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>委託保証金率</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.marginDepositRateAfter ? `${$_withCommaZeroPadding(form.marginDepositRateAfter, 2)}%` : '--' }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col></el-row>

    <!-- 注文発注ボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnOrderRegister"
          text="振替指示"
          :disabled="buttonDisabled"
          action-type="requestAction"
          style="padding-left: 0;"
          :request-model="A001RequestModel"
          action-id="SUB0202_0304-01_2#A001"
          @response-handler="IfaForeignMarginDepositTransferConfirmA001($event)"
        ></ifa-button>
      </el-col>
    </el-row>

  </el-dialog>
</template>
<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaForeignMarginDepositTransferConfirmA001RequestModel } from './js/IfaForeignMarginDepositTransferConfirmA001RequestModel'
import { IfaForeignMarginDepositTransferConfirmFormModel } from './js/IfaForeignMarginDepositTransferConfirmFormModel'
export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    param: { type: Object, required: true }
  },
  emits: ['close-modal', 'guarantee-transfer-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaForeignMarginDepositTransferConfirmFormModel()
    }
  },
  computed: {
    A001RequestModel() {
      return new IfaForeignMarginDepositTransferConfirmA001RequestModel(this.form)
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    userInfo() {
      return this.$store.getters.userAccount
    },
    customerName() {
      return this.$_out(this.customerInfo.customerNameKanji) + '（' + this.$_out(this.customerInfo.customerNameKana) + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.customerInfo.accountNumber}`
    },
    buttonDisabled() {
      if (this.userInfo.medUsers.privId === '2' || this.userInfo.medUsers.privId === '1') {
        return true
      } else {
        return false
      }
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.param)
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 振替指示ボタン
    IfaForeignMarginDepositTransferConfirmA001(response) {
      this.$emit('guarantee-transfer-finish', response.dataList[0])
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
._bold_red_m {
  font-size: 16px;
  font-weight: bold;
  color: #f00;
}
._black_s {
  font-size: 14px;
  color: #606266;
  padding-bottom: 0.5rem;
}
.footer_button {
  text-align: left;
}
</style>
