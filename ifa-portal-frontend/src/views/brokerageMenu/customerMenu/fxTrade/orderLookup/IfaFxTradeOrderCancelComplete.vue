<template>
  <!-- 為替取引注文取消完了ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :style="order.tradeKbn === '1' ? 'background-color: #fef0f0' : 'background-color: #ecf5ff'"
    :title="formModel.title.name"
    width="1200px"
    class="status_change"
    :center="true"
    :show-close="false"
    :before-close="handleDialogClose"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    @open="displayOnShow"
  >

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="['下記の内容で取消を受け付けました。']"
    ></ifa-message-area>
    <!-- エラー/警告表示 -->

    <!-- 顧客情報/口座情報 -->
    <el-row style="font-weight: bold; color: black;">
      <el-col :offset="1">
        <span>{{ $_out(customerInfo().butenCode) + '-' + $_out(customerInfo().accountNumber) }}</span>
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
          <span v-if="customerInfo().corporationType === '1'">
            <el-icon style="vertical-align:top"><OfficeBuilding></OfficeBuilding></el-icon>
          </span>
          <span v-else>
            <el-icon style="vertical-align:top"><avatar></avatar></el-icon>
          </span>
        </span>
        <span>{{ $_out(customerInfo().customerNameKanji) + ' (' + $_out(customerInfo().customerNameKana) + ')' }}</span>
      </el-col>
    </el-row>

    <!-- 注文情報エリア -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="padding: 0.3rem 0 0.3rem 0"
      >
        <el-card
          class="box-card"
          style="background-color: #eee;"
        >
          <el-row :gutter="20">
            <!-- 買付 -->
            <el-col
              v-if="order.tradeKbn === '1'"
              :span="19"
              class="info-item__header __left"
              style="display: flex; font-size: 20px;"
            >
              <span
                class="font-color__plus"
                style="padding: 0 1rem;"
              >{{ $_out($_getCodeValue('FX_TRADE_SEARCH_TRADE_CLASS', 2, order.tradeKbn)) }}</span>
              <span>円</span>
              <img
                :src="imgs('jp')"
                style="margin: 0 0.5rem;"
              >
              <span style="margin: 0.0rem 1.0rem">→</span>
              <span>{{ $_out(order.currency) }}</span>
              <img v-if="order.currencyCode"
                   :src="imgs(order.currencyCode.substring(0, 2).toLowerCase())"
                   style="margin: 0 0.5rem;"
              >
            </el-col>
            <!-- 売却 -->
            <el-col
              v-else
              :span="19"
              class="info-item__header __left"
              style="display: flex; font-size: 20px;"
            >
              <span
                class="font-color__minus"
                style="padding: 0 1rem;"
              >{{ $_out($_getCodeValue('FX_TRADE_SEARCH_TRADE_CLASS', 2, order.tradeKbn)) }}</span>
              <span>{{ $_out(order.currency) }}</span>
              <img v-if="order.currencyCode"
                   :src="imgs(order.currencyCode.substring(0, 2).toLowerCase())"
                   style="margin: 0 0.5rem;"
              >
              <span style="margin: 0.0rem 1.0rem">→</span>
              <span>円</span>
              <img
                :src="imgs('jp')"
                style="margin: 0 0.5rem;"
              >
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 取消内容エリア -->
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
            <span>ご注文内容</span>
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
              <span>取引種別</span>
            </el-col>
            <el-col :span="16">
              <span :class="order.tradeKbn === '1' ? 'font-color__plus bold' : 'font-color__minus bold'">{{ $_out($_getCodeValue('FX_TRADE_SEARCH_TRADE_CLASS', 2, order.tradeKbn)) }}</span>
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
              <span>通貨</span>
            </el-col>
            <el-col
              :span="16"
              style="display: flex; align-items: center;"
            >
              <span style="margin-right: 0.5rem;">{{ $_out(order.currency) }}</span>
              <img v-if="order.currencyCode"
                   :src="imgs(order.currencyCode.substring(0, 2).toLowerCase())"
              >
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
              <span>数量</span>
            </el-col>
            <el-col :span="16">
              <span v-if="order.quantity">{{ $_out(IfaFormatUtils.withCommaZeroPadding(order.quantity, 2)) }} {{ $_out(order.currencyCode) }}</span>
            </el-col>
          </el-row>
          <el-row v-if="customerInfo().jrIsaContractType === '1'"
                  class="dotted_border"
                  style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>{{ $_out(order.tradeKbn === '1' ? '買付口座' : '売却口座') }}</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out(order.accountType === 'JR_NISA' ? 'ジュニアNISA口座' : '総合口座') }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文照会ボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <span>
          <ifa-button
            id="btnLookUp"
            name="btnLookUp"
            text="注文照会"
            style="padding-left: 0;"
            color="primary"
            action-type="originalAction"
            @app-action-handler="handleDialogClose"
          ></ifa-button>
        </span>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaFxTradeOrderCancelCompleteFormModel } from './js/IfaFxTradeOrderCancelCompleteFormModel'
import IfaFormatUtils from '@/utils/ifaFormatUtils'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
export default {
  components: {
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    order: { type: Object, required: true, default: undefined },
    customerInfo: { type: Object, required: true }
  },
  emits: ['close-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      formModel: new IfaFxTradeOrderCancelCompleteFormModel(),
      IfaFormatUtils: IfaFormatUtils
    }
  },
  computed: {
    customerName() {
      return this.customerInfo.customerNameKanji + ' (' + this.customerInfo.customerNameKana + ')'
    },
    accountNumber() {
      return this.order.accountType === '0' && this.hasJrNisaAccount
        ? this.customerInfo.jrNisaAccountNumber
        : this.customerInfo.accountNumber
    },
    hasJrNisaAccount() {
      return this.customerInfo.jrNisaAccountNumber !== ''
    }
  },
  methods: {
    displayOnShow() {
      this.$nextTick(() => {

      })
    },
    // 注文照会ボタン
    handleDialogClose() {
      this.$emit('close-modal')
    },
    // 国旗の画像を読み込む
    imgs(icon) {
      return require('@/assets/png/flags/32/' + icon + '.png')
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.info-item__header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
}
</style>

<style lang="scss">
  .status_change{
    .el-dialog__header{
      margin-top: 10px;
      padding-bottom: 0px;
      padding-top: 30px;
      .el-dialog__title{
        font-weight: bold;
      }
    }
  }
</style>
