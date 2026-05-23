<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      title="現引現渡注文取消確認"
      width="1200px"
      :style="formModel.tradeCd === '8' ? 'background-color: #fef0f0' : 'background-color: #ecf5ff'"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      :destroy-on-close="true"
      @opened="onOpened"
    >
      <!-- タイトルエリア -->
      <el-row>
        <el-col
          :offset="1"
          :span="22"
          style="text-align: right;"
        >
          <ifa-button
            text="戻る"
            color="secondary"
            action-type="originalAction"
            style="padding-right: 0;"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
      </el-row>
      <!-- メッセージエリア -->
      <ifa-message-area
        :main-messages="messages.mains"
        :error-messages="messages.errors"
        :warning-messages="messages.warnings"
        :info-messages="messages.infos"
      ></ifa-message-area>
      <!-- 口座エリア -->
      <el-row style="font-weight: bold;">
        <el-col :offset="1">
          <span>{{ $_out(customerInfo.butenCode) + '-' + $_out($_zeroPadding(customerInfo.accountNumber,7)) }}</span>
        </el-col>
      </el-row>
      <el-row
        style="padding-top: 0.3rem; margin: auto"
        class="_bold_black_l"
      >
        <el-col
          :offset="1"
          :span="22"
          style="font-size: 20px;"
          class="_bold_black_l"
        >
          <span v-if="customerInfo.corporationType === '1'">
            <el-icon style="vertical-align:top"><OfficeBuilding></OfficeBuilding></el-icon>
          </span>
          <span v-else>
            <el-icon style="vertical-align:top"><avatar></avatar></el-icon>
          </span>
          <span style="vertical-align: top;">{{ $_out(customerInfo.customerNameKanji) + '（' + $_out(customerInfo.customerNameKana) + '）' }}</span>
          <ifa-notice-info
            :notice-info-level="customerInfo.noticeInfoLevel"
            :customer-code="customerInfo.customerCode"
            :buten-code="customerInfo.butenCode"
            :account-number="customerInfo.accountNumber"
          ></ifa-notice-info>
        </el-col>
      </el-row>
      <!-- 注文内容エリア -->
      <el-row>
        <el-col
          :span="22"
          :offset="1"
          style="padding-top: 0.7rem;"
        >
          <el-card
            class="box-card"
            style="font-size: 16px;"
          >
            <el-row
              class="_bold_black_m"
              style="padding-top: 0.2rem; padding-left: 1rem;"
            >
              <span>ご注文内容（復唱項目）</span>
            </el-row>
            <hr>
            <!-- 銘柄 -->
            <el-row class="dotted_border">
              <el-col :span="8">
                <span class="_bold_black_m">[銘柄コード]&nbsp;銘柄名</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span style="overflow-wrap: break-word;">[{{ $_out(cancelData.brandCode) }}]&nbsp;{{ $_out(cancelData.brandName) }}</span>
              </el-col>
            </el-row>
            <!-- 取引種別 -->
            <el-row class="dotted_border">
              <el-col :span="8">
                <span class="_bold_black_m">取引種別</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span :class="cancelData.tradeCd === '8' ? 'font-color__plus bold' : 'font-color__minus bold'">{{
                  $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, cancelData.tradeCd))
                }}</span>
              </el-col>
            </el-row>

            <!-- 信用取引区分 -->
            <el-row class="dotted_border">
              <el-col :span="8">
                <span class="_bold_black_m">信用取引区分</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ $_out(cancelData.marginTradeTypeText) }}</span>
              </el-col>
            </el-row>

            <!-- 数量 -->
            <el-row class="dotted_border">
              <el-col :span="8">
                <span class="_bold_black_m">数量</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ $_out($_withCommaInteger(cancelData.unTradeQuantity.trim())) }} 株</span>
              </el-col>
            </el-row>

            <!-- 特定・一般区分 -->
            <el-row
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">預り区分</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ $_out($_getCodeValue('DOMESTIC_DEPOSIT_TYPE', 2, cancelData.depositType)) }}</span>
              </el-col>
            </el-row>

            <!-- 新規単価 -->
            <el-row
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">建単価</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ $_out($_withCommaNoneZeroPadding(cancelData.newPrice)) }} 円</span>
              </el-col>
            </el-row>

            <!-- 新規建日 -->
            <el-row
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">新規建日</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ $_out($_getFormattedDateValue(cancelData.newTradeDate, 'date8')) }}</span>
              </el-col>
            </el-row>

            <!-- 建玉No -->
            <el-row
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">建玉No</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span v-if="cancelData.newPositionNumber.trim() === ''">-</span>
                <span v-else>{{ $_out($_zeroPadding(cancelData.newPositionNumber.trim(), 5)) }}</span>
              </el-col>
            </el-row>

            <!-- 受注日時 -->
            <el-row class="dotted_border">
              <el-col :span="8">
                <span class="_bold_black_m">受注日時</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ $_out($_getFormattedDateTimeValue(cancelData.orderDayTime, 'datetime12')) }}</span>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>

      <!-- 操作エリア -->
      <el-row style="margin-top: 20px;">
        <el-col
          :offset="1"
          :span="22"
          style="text-align: left;"
        >
          <ifa-button
            id="btnCancelOrder"
            text="注文取消"
            style="padding-left: 0;"
            :disabled="buttonDisabled"
            action-type="requestAction"
            :request-model="IfaReceiptDeliveryOrderCancelConfirmA002RequestModel"
            action-id="SUB0202_0212-09_1#A002"
            @response-handler="orderPlacementA002($event)"
          ></ifa-button>
        </el-col>
      </el-row>

    </el-dialog>

    <ifa-receipt-delivery-order-cancel-complete
      :is-visible="dialogFinishVisible"
      :complete-data="completeData"
      @move-to-order-list="handleMoveToOrderList"
    ></ifa-receipt-delivery-order-cancel-complete>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaReceiptDeliveryOrderCancelConfirmFormModel } from './js/IfaReceiptDeliveryOrderCancelConfirmFormModel'
import { IfaReceiptDeliveryOrderCancelConfirmA002RequestModel } from './js/IfaReceiptDeliveryOrderCancelConfirmA002RequestModel'
import IfaReceiptDeliveryOrderCancelComplete from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaReceiptDeliveryOrderCancelComplete'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'

export default {
  components: {
    IfaReceiptDeliveryOrderCancelComplete,
    IfaMessageArea,
    IfaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    cancelData: { type: Object, required: true },
    ecOrderNo: { type: String, required: true }
  },
  emits: ['close-modal', 'update:isVisible', 'initialize-order-status-list'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      formModel: new IfaReceiptDeliveryOrderCancelConfirmFormModel(),
      messages: {
        mains: [],
        errors: [],
        warnings: [],
        infos: []
      },
      dialogFinishVisible: false,
      completeData: {}
    }
  },
  computed: {
    IfaReceiptDeliveryOrderCancelConfirmA002RequestModel() {
      return new IfaReceiptDeliveryOrderCancelConfirmA002RequestModel(this.formModel)
    },
    buttonDisabled() {
      if (this.userInfo.medUsers.privId === '1' || this.userInfo.medUsers.privId === '2') {
        return true
      }
      return false
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    userInfo() {
      return this.$store.getters.userAccount
    }
  },
  methods: {
    onOpened() {
      Object.assign(this.formModel, this.cancelData)
      this.formModel.ecOrderNo = this.ecOrderNo
      this.messages.mains.length = 0
      this.messages.mains.push('取消はまだ完了していません。画面下の注文取消ボタンを押下してください。')
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 注文取消ボタン
    orderPlacementA002(response) {
      this.completeData = response.dataList[0]
      this.dialogFinishVisible = true
    },
    // 注文一覧画面に遷移（画面更新）
    handleMoveToOrderList() {
      this.dialogFinishVisible = false
      this.$emit('initialize-order-status-list')
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
:deep(.el-dialog){
    width: 1200px !important
}
.footer_button {
  margin-top: 1rem;
}
</style>
