<template>
  <!-- 現引現渡注文完了ダイアログ -->
  <el-dialog
    v-model="$props.isVisible"
    :title="form.title.name"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    :style="dialogStyle"
  >

    <!-- ヘッダ -->
    <el-row style="margin-top: 40px;">
      <ifa-message-area
        :main-messages="['下記の内容で振替指示を受け付けました。']"
      ></ifa-message-area>
    </el-row>

    <!-- 顧客情報 -->
    <el-row
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
        span="22"
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
    <!-- /顧客情報 -->

    <!-- 注文内容(復唱項目)エリア -->
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
            style="padding-top: 0.2rem; padding-left: 1rem"
          >
            <span>ご注文内容</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>[銘柄コード]&nbsp;銘柄名</span>
            </el-col>
            <el-col :span="10">
              <span style="overflow-wrap: break-word;">[{{ $_out(formData.req.brandCode) }}]&nbsp;{{ $_out(formData.req.brandName) }}</span>
            </el-col>
            <el-col
              v-if="formData.req.tradeNoticeInfoBrandMsg"
              :span="5"
              style="text-align: center;"
            >
              <el-icon
                style="color: red;  vertical-align: middle;"
              ><WarningFilled></WarningFilled></el-icon>
              <ifa-link
                disp-name="取引注意情報"
                :url-id="16"
                :url-object="{ brandCode: getUrlObject}"
              ></ifa-link>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col
              v-if="formData.req.tradeCd"
              :span="15"
            >
              <ifa-text
                :style="{'color': fontColor}"
                style="font-weight:bold"
                :code-list-id="'DOMESTIC_STOCK_TRADE_CLASS'"
                :disp-pattern="1"
                :code-key="formData.req.tradeCd"
              ></ifa-text>
            </el-col>
            <el-col
              v-else
              :span="15"
            >-
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受注数量</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.req.quantity ? `${$_withCommaInteger(formData.req.quantity)}株`: '-' }}</span>
            </el-col>
          </el-row>

          <!-- 信用取引区分 -->
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>信用取引区分</span>
            </el-col>
            <span>{{ $_out(formData.req.paymentDeadlineCalculation) }}</span>
          </el-row>

          <!-- /預り区分 -->
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>預り区分</span>
            </el-col>
            <el-col
              v-if="formData.req.accountType"
              :span="15"
            >
              <ifa-text
                :code-list-id="'DOMESTIC_DEPOSIT_TYPE'"
                :disp-pattern="customerInfo.specificAccountType === '1' || customerInfo.specificAccountType === '2' ? 2 : 9"
                :code-key="formData.req.accountType"
              ></ifa-text>
            </el-col>
            <el-col
              v-else
              :span="15"
            >-
            </el-col>

          </el-row>

          <!-- 建市場 -->
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>建市場</span>
            </el-col>
            <el-col
              v-if="formData.req.builtMarket"
              :span="15"
            >
              <ifa-text
                :code-list-id="'NEW_MARKET'"
                :disp-pattern="1"
                :code-key="formData.req.builtMarket"
              ></ifa-text>
            </el-col>
            <el-col
              v-else
              :span="15"
            >-
            </el-col>
          </el-row>

          <!-- 新規建日 -->
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>新規建日</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.req.constructionDate ? $_getFormattedDateValue(formData.req.constructionDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>

          <!-- 親株新規約定日 -->
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>親株新規約定日</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.req.parentStockTradeDate ? $_getFormattedDateValue(formData.req.parentStockTradeDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>

          <!-- 新規単価 -->
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>建単価</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.req.newPrice ? `${$_withCommaNoneZeroPadding(formData.req.newPrice)}円`: '-' }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!-- /注文内容(復唱項目) -->

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- その他注文内容 -->
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
            style="padding-top: 0.2rem; padding-left: 1rem"
          >
            <span>その他注文内容</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>建玉No</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.req.newOpenInterestNumber ? $_zeroPadding(formData.req.newOpenInterestNumber, 5) : '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>約定金額</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.contractAmount ? `${$_withCommaInteger(formData.contractAmount)}円`: '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>手数料/諸費用</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.charge ? `${$_withCommaInteger(formData.charge)}円`: '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>消費税</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.consumptionTax ? `${$_withCommaInteger(formData.consumptionTax)}円`: '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>譲渡益税</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.yieldTax ? `${$_withCommaInteger(formData.yieldTax)}円`: '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>精算金額</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.settlementAmount ? `${$_withCommaInteger(formData.settlementAmount)}円`: '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>約定予定日</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.contractDate ? $_getFormattedDateValue(formData.contractDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受渡予定日</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.deliveryDate ? $_getFormattedDateValue(formData.deliveryDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受注日時</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.orderDate && formData.orderTime ? $_getFormattedDateTimeValue(`${formData.orderDate} ${formData.orderTime}`,'datetime12') : '----/--/-- --:--' }} </span>
            </el-col>
          </el-row>
          <!-- 勧誘区分 -->
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col
              v-if="formData.req.kanyuKbn"
              :span="15"
            >
              <ifa-text
                :code-list-id="'INVITATION_TYPE'"
                :disp-pattern="1"
                :code-key="formData.req.kanyuKbn"
              ></ifa-text>
            </el-col>
            <el-col
              v-else
              :span="15"
            >-
            </el-col>
          </el-row>
          <!-- 受注方法 -->
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受注方法</span>
            </el-col>
            <el-col
              v-if="formData.req.receiveOrderType"
              :span="15"
            >
              <ifa-text
                :code-list-id="'ORDER_METHOD_TYPE'"
                :disp-pattern="1"
                :code-key="formData.req.receiveOrderType"
              ></ifa-text>
            </el-col>
            <el-col
              v-else
              :span="15"
            >-
            </el-col>
          </el-row>

          <!-- EC受注番号 -->
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>EC受注番号</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.ecOrderNo ? $_zeroPadding(formData.ecOrderNo,6) : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 確認項目 -->
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>確認項目</span>
            </el-col>
            <el-col
              v-if="formData.req.checkCustomerAttribute"
              :span="15"
            >
              <ifa-text
                :code-list-id="'INSIDER_CONFIRM'"
                :disp-pattern="3"
                :code-key="formData.req.checkCustomerAttribute"
              ></ifa-text>
            </el-col>
            <el-col
              v-else
              :span="15"
            >-
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!-- その他注文内容 -->

    <!-- アラート内容確認 -->
    <el-row
      v-if="isErrMsg()"
      style="margin-top:0.5rem;"
    >
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
            style="padding-top: 0.2rem; padding-left: 1rem"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>

          <!-- 取引注意情報（銘柄）確認 -->
          <el-row
            v-if="formData.req.tradeNoticeInfoBrandMsg"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>取引注意情報の説明</span>
            </el-col>
            <el-col
              :span="15"
            >
              <span>{{ $_getCodeValue('TRADE_NOTICE_INFO_EXPLAIN', 1, '1') }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.req.noticeInfoAlert"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>注意情報の確認</span>
            </el-col>
            <el-col
              :span="15"
            >
              <span>{{ $_getCodeValue('NOTICE_INFO_CONFIRM', 1, '1') }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.req.noticeAlert"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>重要なお知らせの確認</span>
            </el-col>
            <el-col
              :span="15"
            >
              <span>{{ $_getCodeValue('IMPORTANT_NOTIFICATION_CONFIRM', 1, '1') }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.req.insiderErrorMsg"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>内部者取引確認（知る前契約/計画の確認含む）</span>
            </el-col>
            <el-col
              :span="15"
            >
              <span>{{ $_getCodeValue('INSIDER_TRADE_CONFIRM', 1, '1') }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文状況一覧へボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="margin-top: 1rem"
      >
        <ifa-button
          id="btnToOrderStatusList"
          text="注文状況一覧へ"
          action-type="originalAction"
          style="padding-left: 0;"
          @app-action-handler="toOrderStatusListA001"
        ></ifa-button>
      </el-col>
    </el-row>

  </el-dialog>
</template>

<script>
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaReceiptDeliveryOrderCompleteFormModel } from './js/IfaReceiptDeliveryOrderCompleteFormModel.js'

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
  emits: ['close-modal', 'move-to-order-list'],

  data() {
    return {
      dialogStyle: '',
      fontColor: '#00847F',
      form: new IfaReceiptDeliveryOrderCompleteFormModel()
    }
  },
  computed: {
    customerName() {
      return this.$_out(this.customerInfo.customerNameKanji) + '（' + this.$_out(this.customerInfo.customerNameKana) + '）'
    },
    accountNumber() {
      return `${this.$_out(this.customerInfo.butenCode)}-${this.customerInfo.accountNumber ? this.$_zeroPadding(this.customerInfo.accountNumber, 7) : '-'}`
    },
    getUrlObject() {
      if (!this.formData.req.brandCode) {
        return null
      }
      return this.formData.req.brandCode.substring(0, 4)
    }
  },
  watch: {
    'formData.req.tradeCd': {
      deep: true,
      handler(newValue) {
        if (newValue === '8') {
          this.dialogStyle = 'backgroundColor: #fef0f0;'
          this.fontColor = '#E5004C'
        } else {
          this.dialogStyle = 'backgroundColor: #ecf5ff;'
          this.fontColor = '#00847F'
        }
      }
    }

  },
  methods: {
    isErrMsg() {
      if ((this.formData.req.tradeNoticeInfoBrandMsg && this.formData.req.tradeNoticeInfoBrandMsg.length > 0) ||
        (this.formData.req.noticeInfoAlert && this.formData.req.noticeInfoAlert.length > 0) ||
        (this.formData.req.noticeAlert && this.formData.req.noticeAlert.length > 0) ||
        (this.formData.req.insiderErrorMsg && this.formData.req.insiderErrorMsg.length > 0)) {
        return true
      } else {
        return false
      }
    },
    // 閉じるボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 注文状況一覧へボタン
    toOrderStatusListA001() {
      this.$emit('move-to-order-list')
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.footer_button {
  text-align: left;
  margin-top: 1rem;
  margin-left: -20px;
}
</style>
