<template>
  <el-dialog
    v-model="vmIsVisible"
    title="現引現渡注文取消完了"
    width="1200px"
    :style="formModel.tradeCd === '8' ? 'background-color: #fef0f0' : 'background-color: #ecf5ff'"
    :show-close="false"
    :center="true"
    :before-close="moveToOrderList"
    :close-on-click-modal="false"
    :destroy-on-close="true"
    @open="onOpen"
  >
    <!-- メッセージエリア -->
    <el-row style="margin-top: 40px;">
      <ifa-message-area
        :main-messages="messages.mains"
        :error-messages="messages.errors"
        :warning-messages="messages.warnings"
        :info-messages="messages.infos"
      ></ifa-message-area>
    </el-row>
    <!-- 口座エリア -->
    <el-row style="font-weight: bold;">
      <el-col :offset="1">
        <span>{{ $_out(customerInfo.butenCode) + '-' + $_out($_zeroPadding(customerInfo.accountNumber,7)) }}</span>
      </el-col>
    </el-row>
    <el-row
      style="padding-top: 0.3rem;"
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
            <span>ご注文内容</span>
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
              <span style="overflow-wrap: break-word;">[{{ $_out(completeData.brandCode) }}]&nbsp;{{ $_out(completeData.brandName) }}</span>
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
              <span :class="completeData.tradeCd === '8' ? 'font-color__plus bold' : 'font-color__minus bold'">{{
                $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, completeData.tradeCd))
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
              <span>{{ $_out(completeData.marginTradeTypeText) }}</span>
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
              <span>{{ $_out($_withCommaInteger(completeData.unTradeQuantity.trim())) }} 株</span>
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
              <span>{{ $_out($_getCodeValue('DOMESTIC_DEPOSIT_TYPE', 2, completeData.depositType)) }}</span>
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
              <span>{{ $_out($_withCommaNoneZeroPadding(completeData.newPrice)) }} 円</span>
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
              <span>{{ $_out($_getFormattedDateValue(completeData.newTradeDate, 'date8')) }}</span>
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
              <span>{{ $_out($_zeroPadding(completeData.newPositionNumber.trim(), 5)) }}</span>
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
              <span>{{ $_out($_getFormattedDateTimeValue(completeData.orderDayTime, 'datetime12')) }}</span>
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
          text="注文状況一覧へ"
          color="primary"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="handleClick"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaReceiptDeliveryOrderCancelCompleteFormModel } from './js/IfaReceiptDeliveryOrderCancelCompleteFormModel'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
export default {
  components: {
    IfaMessageArea,
    IfaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    completeData: { type: Object, required: true }
  },
  emits: ['move-to-order-list', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      formModel: new IfaReceiptDeliveryOrderCancelCompleteFormModel(),
      messages: {
        mains: [],
        errors: [],
        warnings: [],
        infos: []
      }
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    userInfo() {
      return this.$store.getters.userAccount
    }
  },
  methods: {
    onOpen() {
      Object.assign(this.formModel, this.completeData)
      this.$_logDebug(this.formModel)
      this.messages.mains.length = 0
      this.messages.mains.push('下記の内容で取消を受け付けました。')
    },
    // 注文状況一覧へ遷移
    moveToOrderList() {
      this.$emit('move-to-order-list')
    },
    handleClick() {
      this.moveToOrderList()
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
