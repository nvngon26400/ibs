<template>
  <!-- 為替取引注文取消確認ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :style="order.tradeCd === '1' ? 'background-color: #fef0f0' : 'background-color: #ecf5ff'"
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

    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: right;"
      >
        <ifa-button
          id="btnBack"
          name="btnBack"
          text="戻る"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="handleDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="['取消はまだ完了していません。画面下の注文取消ボタンを押下してください。']"
    ></ifa-message-area>
    <!-- エラー/警告表示 -->

    <!-- 口座エリア -->
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
              v-if="formModel.tradeKbn === '1'"
              :span="19"
              class="info-item__header __left"
              style="display: flex; font-size: 20px;"
            >
              <span
                class="font-color__plus"
                style="padding: 0 1rem;"
              >{{ $_out($_getCodeValue('FX_TRADE_SEARCH_TRADE_CLASS', 2, formModel.tradeKbn)) }}</span>
              <span>円</span>
              <img
                :src="imgs('jp')"
                style="margin: 0 0.5rem;"
              >
              <span style="margin: 0.0rem 1.0rem">→</span>
              <span>{{ $_out(formModel.selectedRowCurrency) }}</span>
              <img
                :src="imgs(formModel.currencyCode.substring(0, 2).toLowerCase())"
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
              >{{ $_out($_getCodeValue('FX_TRADE_SEARCH_TRADE_CLASS', 2, formModel.tradeKbn)) }}</span>
              <span>{{ $_out(formModel.selectedRowCurrency) }}</span>
              <img
                :src="imgs(formModel.currencyCode.substring(0, 2).toLowerCase())"
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
            <span>ご注文内容（復唱項目）</span>
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
              <span :class="formModel.tradeKbn === '1' ? 'font-color__plus bold' : 'font-color__minus bold'">{{ $_out($_getCodeValue('FX_TRADE_SEARCH_TRADE_CLASS', 2, formModel.tradeKbn)) }}</span>
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
              <span style="margin-right: 0.5rem;">{{ $_out(formModel.selectedRowCurrency) }}</span>
              <img
                :src="imgs(formModel.currencyCode.substring(0, 2).toLowerCase())"
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
              <span>{{ $_out(IfaFormatUtils.withCommaZeroPadding(formModel.quantity, 2)) }} {{ $_out(formModel.currencyCode) }}</span>
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
              <span>{{ $_out(formModel.tradeKbn === '1' ? '買付口座' : '売却口座') }}</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out(formModel.accountType === 'JR_NISA' ? 'ジュニアNISA口座' : '総合口座') }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文発注ボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <span>
          <ifa-button
            id="btnOrderCancel"
            name="btnOrderCancel"
            text="注文取消"
            color="primary"
            action-id="SUB0202_0501-02_1#A002"
            action-type="requestAction"
            style="padding-left: 0;"
            :disabled="authorityHandle()"
            :request-model="IfaFxTradeOrderCancelConfirmA002RequestModel"
            @response-handler="orderCancelHandle($event)"
            @response-error-handler="orderCancelErrorHandle($event)"
          ></ifa-button>
        </span>
      </el-col>
    </el-row>
    <!-- <ifa-requester
      id="initializeA001"
      action-id="SUB0202_0501-02_1#A001"
      action-type="requestAction"
      :request-model="IfaFxTradeOrderCancelConfirmA001RequestModel"
      @response-handler="responseHandlerInitializeA001($event)"
    ></ifa-requester> -->

  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
// import { IfaFxTradeOrderCancelConfirmA001RequestModel } from './js/IfaFxTradeOrderCancelConfirmA001RequestModel'
import { IfaFxTradeOrderCancelConfirmA002RequestModel } from './js/IfaFxTradeOrderCancelConfirmA002RequestModel'
import { IfaFxTradeOrderCancelConfirmFormModel } from './js/IfaFxTradeOrderCancelConfirmFormModel'
import IfaFormatUtils from '@/utils/ifaFormatUtils'
import { notifyWrapper } from '@/utils/errorHandler'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
export default {
  components: {
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    order: { type: Object, required: true },
    customerInfo: { type: Object, required: true }
  },
  emits: ['close-modal', 'order-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      formModel: new IfaFxTradeOrderCancelConfirmFormModel(),
      IfaFormatUtils: IfaFormatUtils
    }
  },
  computed: {
    IfaFxTradeOrderCancelConfirmA002RequestModel() {
      const ifaFxTradeOrderCancelConfirmA002RequestModel = new IfaFxTradeOrderCancelConfirmA002RequestModel(this.formModel)
      ifaFxTradeOrderCancelConfirmA002RequestModel.tradeCd = this.formModel.tradeKbn
      ifaFxTradeOrderCancelConfirmA002RequestModel.currency = this.formModel.selectedRowCurrency
      ifaFxTradeOrderCancelConfirmA002RequestModel.businessDay = this.formModel.selectedRowBusinessDay
      if (this.formModel.accountType === 'JR_NISA') {
        ifaFxTradeOrderCancelConfirmA002RequestModel.tradingAccount = '2'
      } else {
        ifaFxTradeOrderCancelConfirmA002RequestModel.tradingAccount = '1'
      }
      return ifaFxTradeOrderCancelConfirmA002RequestModel
    }
    // IfaFxTradeOrderCancelConfirmA001RequestModel() {
    //   return new IfaFxTradeOrderCancelConfirmA001RequestModel(this.order)
    // }
  },
  created() {
  },
  methods: {
    displayOnShow() {
      this.$nextTick(() => {
        this.$_logDebug(this.formModel)
        // document.getElementById('initializeA001').click()
      })

      // this.a001Action(new IfaFxTradeOrderCancelConfirmA001RequestModel(this.order))
    },
    responseHandlerInitializeA001(response) {
      this.$_logDebug(response)
      this.formModel = Object.assign(this.formModel, response.dataList[0])
      this.formModel.orderEventId = response.dataList[0].selectedRowOrderEventId
    },
    // 取消ボタン権限確認
    authorityHandle() {
      if (this.$store.getters.userAccount.medUsers.privId === '1' || this.$store.getters.userAccount.medUsers.privId === '2') {
        return true
      } else {
        return false
      }
    },
    // 取消確認（正常）
    orderCancelHandle(response) {
      if ((response.errorLevel === 0 || response.errorLevel === 1) && response.dataList.length !== 0 && response.dataList.length !== undefined) {
        this.formModel = Object.assign(this.formModel, response.dataList[0])
        this.$emit('order-finish', response)
      }
    },
    // 取消確認（エラー発生）
    orderCancelErrorHandle(response) {
      this.$_logWarn('☆errorlog☆')
      notifyWrapper({
        title: this.formModel.title,
        message: response.message,
        type: 'warning'
      })
    },
    // 戻るボタン
    handleDialogClose() {
      this.$emit('close-modal')
    },
    // 国旗の画像を読み込む
    imgs(icon) {
      if (icon !== '') {
        return require('@/assets/png/flags/32/' + icon + '.png')
      }
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
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -60px 2.5rem 0 auto;
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
