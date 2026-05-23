<template>
  <!-- 画面定義書_SUB0202_0212-08_2_現引現渡注文確認 -->
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title.name"
    :center="true"
    :show-close="false"
    :before-close="backA002"
    :close-on-click-modal="false"
    :style="dialogStyle"
    width="1200px"
    top="6%"
  >
    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="text-align: right;"
      >
        <ifa-button
          id="btnBack"
          text="戻る"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="backA002"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="messages.mains"
      :error-messages="messages.errors"
      :warning-messages="messages.warnings"
      :info-messages="messages.infos"
    ></ifa-message-area>

    <!-- 口座エリア -->
    <el-row style="font-weight: bold;">
      <el-col :offset="1">
        <span>{{ $_out(accountNumber) }}</span>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :offset="1"
        class="_bold_black_l"
        style="padding-top: 0.5rem; font-size: 20px;"
      >
        <span style="position: relative; top: 3px;">
          <el-icon><component :is="customerInfo.corporationType === '1' ? 'OfficeBuilding' : 'Avatar'"></component></el-icon>
        </span>
        <span style="margin-right: 1rem;">{{ $_out(customerName) }}</span>
        <ifa-notice-info
          :notice-info-level="customerInfo.noticeInfoLevel"
          :customer-code="customerInfo.customerCode"
          :buten-code="customerInfo.butenCode"
          :account-number="customerInfo.accountNumber"
          style="position: relative; top: 4px;"
        ></ifa-notice-info>
      </el-col>
    </el-row>
    <!-- 復唱項目エリア -->
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
            style="padding-top: 0.2rem; padding-left: 1rem;"
          >
            <span>ご注文内容（復唱項目）</span>
          </el-row>
          <hr>

          <!-- 銘柄 -->
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
              <span style="overflow-wrap: break-word;">[{{ $_out(form.req.brandCode) }}]&nbsp;{{ $_out(form.brandName) }}</span>
            </el-col>
            <el-col
              v-if="form.tradeNoticeInfoBrandMsg"
              :span="5"
              style="text-align: center;"
            >
              <el-icon
                style="color: red;  vertical-align: middle;"
              ><WarningFilled></WarningFilled></el-icon>
              <ifa-link
                :disp-name="'取引注意情報'"
                :url-id="16"
                :url-object="{ brandCode: getUrlObject}"
              ></ifa-link>
            </el-col>
          </el-row>

          <!-- 取引種別 -->
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
            <el-col :span="15">
              <span>
                <ifa-text
                  :style="{'color': fontColor}"
                  style="font-weight:bold"
                  :code-list-id="'DOMESTIC_STOCK_TRADE_CLASS'"
                  :disp-pattern="1"
                  :code-key="form.req.tradeCd"
                ></ifa-text>
              </span>
            </el-col>
          </el-row>

          <!-- 受注数量 -->
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
              <span>{{ form.req.quantity ? `${$_withCommaInteger(form.req.quantity)}株` : '-' }} </span>
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
            <el-col :span="15">
              <span>{{ $_out(form.req.paymentDeadlineCalculation) }}</span>
            </el-col>
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
            <el-col :span="15">
              <ifa-text
                :code-list-id="'DOMESTIC_DEPOSIT_TYPE'"
                :disp-pattern="customerInfo.specificAccountType === '1' || customerInfo.specificAccountType === '2' ? 2 : 9"
                :code-key="form.req.accountType"
              ></ifa-text>
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
            <el-col :span="15">
              <ifa-text
                :code-list-id="'NEW_MARKET'"
                :disp-pattern="1"
                :code-key="form.req.builtMarket"
              ></ifa-text>
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
              <span>{{ form.req.constructionDate ? $_getFormattedDateValue(form.req.constructionDate) : '----/--/--' }}</span>
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
              <span>{{ form.req.parentStockTradeDate ? $_getFormattedDateValue(form.req.parentStockTradeDate) : '----/--/--' }}</span>
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
              <span>{{ form.req.newPrice ? `${$_withCommaNoneZeroPadding(form.req.newPrice, 2)}円` : '-' }}</span>
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
            style="padding-top: 0.2rem; padding-left: 1rem;"
          >
            <span>その他注文内容</span>
          </el-row>
          <hr>

          <!-- 建玉No -->
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
              <span>{{ $_out($_zeroPadding(form.req.newOpenInterestNumber, 5)) }}</span>
            </el-col>
          </el-row>

          <!-- 約定金額 -->
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
              <span>{{ form.contractAmount ? `${$_withCommaInteger(form.contractAmount)}円` : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 手数料/諸費用 -->
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
              <span>{{ form.charge ? `${$_withCommaInteger(form.charge)}円` : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 消費税 -->
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
              <span>{{ form.consumptionTax ? `${$_withCommaInteger(form.consumptionTax)}円` : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 譲渡益税 -->
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
              <span>{{ form.yieldTax ? `${$_withCommaInteger(form.yieldTax)}円` : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 精算金額 -->
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
              <span>{{ form.settlementAmount ? `${$_withCommaInteger(form.settlementAmount)}円` : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 約定予定日 -->
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
              <span>{{ form.contractDate ? $_getFormattedDateValue(form.contractDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>

          <!-- 受渡予定日 -->
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
              <span>{{ form.deliveryDate ? $_getFormattedDateValue(form.deliveryDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>

          <!-- 受注日時 -->
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
              <span>{{ form.orderDate && form.orderTime ? $_getFormattedDateTimeValue(form.orderDate + form.orderTime,'datetime12') : '----/--/-- --:--' }}</span>
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
            <el-col :span="15">
              <ifa-text
                :code-list-id="'INVITATION_TYPE'"
                :disp-pattern="1"
                :code-key="form.req.kanyuKbn"
              ></ifa-text>
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
            <el-col :span="15">
              <ifa-text
                :code-list-id="'ORDER_METHOD_TYPE'"
                :disp-pattern="1"
                :code-key="form.req.receiveOrderType"
              ></ifa-text>
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
            <el-col :span="15">
              <ifa-text
                :code-list-id="'INSIDER_CONFIRM'"
                :disp-pattern="3"
                :code-key="form.req.checkCustomerAttribute"
              ></ifa-text>
            </el-col>
          </el-row>

        </el-card>
      </el-col>
    </el-row>
    <!-- /その他注文内容 -->

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row v-if="isErrMsgs">
      <el-col
        :span="22"
        :offset="1"
        style="color: #f00;"
      >
        <el-card
          :style="dialogStyle"
          class="box-card alert_content"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem; color: #f00;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>

          <!-- 取引注意情報（銘柄）確認 -->
          <el-row
            v-if="form.tradeNoticeInfoBrandMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_red_m"
            >
              <div class="required-mark">*</div><span style="color: #f00;">取引注意情報の説明</span>
            </el-col>
            <el-col :span="15">
              <ifa-input-check
                v-model="form.tradingCautionInformation"
                style="margin-left: 5px; color: #f00;"
                label-class="checkClass"
                code-list-id="TRADE_NOTICE_INFO_EXPLAIN"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!-- 注意情報アラート確認 -->
          <el-row
            v-if="form.noticeInfoAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_red_m"
            >
              <div class="required-mark">*</div><span style="color: #f00;">注意情報の確認</span>
            </el-col>
            <el-col :span="15">
              <ifa-input-check
                v-model="form.noteInfoCheck"
                style="margin-left: 5px; color: #f00;"
                label-class="checkClass"
                code-list-id="NOTICE_INFO_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!-- お知らせアラート確認 -->
          <el-row
            v-if="form.noticeAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_red_m"
            >
              <div class="required-mark">*</div><span style="color: #f00;">重要なお知らせの確認</span>
            </el-col>
            <el-col :span="15">
              <ifa-input-check
                v-model="form.noteLimitCheck"
                style="margin-left: 5px; color: #f00;"
                label-class="checkClass"
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!-- 内部者エラー確認 -->
          <el-row
            v-if="form.insiderConfirmMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_red_m"
              style="display: flex; align-items: flex-start;"
            >
              <div class="required-mark">*</div>
              <span
                class="el-link el-link--primary"
                style="font-weight: bold; text-decoration: underline; text-underline-offset:0.2em; padding-right: 10px; font-size: 16px;"
                @click="insiderErrorConfirmLinkA005"
              >内部者取引確認（知る前契約/計画の確認含む）
              </span>
            </el-col>
            <el-col :span="15">
              <ifa-input-check
                v-model="form.insiderErrorConfirmationCheck"
                style="margin-left: 5px; color: #f00;"
                code-list-id="INSIDER_TRADE_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
                :disabled="isInsiderCheck"
              ></ifa-input-check>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文発注ボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="margin-top: 1rem"
      >
        <ifa-button
          id="btnOrderRegister"
          text="注文発注"
          :disabled="buttonDisabled"
          action-type="requestAction"
          :request-model="A001RequestModel"
          action-id="SUB0202_0212-08_2#A001"
          style="padding-left: 0px;"
          @response-handler="orderPlacementA001($event)"
        ></ifa-button>
      </el-col>
    </el-row>

  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaReceiptDeliveryOrderConfirmFormModel } from './js/IfaReceiptDeliveryOrderConfirmFormModel.js'
import { IfaReceiptDeliveryOrderConfirmA001RequestModel } from './js/IfaReceiptDeliveryOrderConfirmA001RequestModel.js'

export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  // 親コンポーネントから受け取る値
  props: {
    // 本コンポーネントの表示・表示
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true },
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
      dialogStyle: '',
      fontColor: '#00847F',
      form: new IfaReceiptDeliveryOrderConfirmFormModel(),
      messages: {
        mains: [],
        errors: [],
        warnings: [],
        infos: []
      },
      isInsiderCheck: true
    }
  },
  computed: {
    A001RequestModel() {
      return new IfaReceiptDeliveryOrderConfirmA001RequestModel(this.form)
    },
    customerName() {
      return this.customerInfo.customerNameKanji + ' (' + this.customerInfo.customerNameKana + ')'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    },
    isPrivId() {
      if (this.$store.getters.userAccount) {
        return (this.$store.getters.userAccount.medUsers.privId === '1' || this.$store.getters.userAccount.medUsers.privId === '2')
      }
      return false
    },
    isErrMsgs() {
      if ((this.form.tradeNoticeInfoBrandMsg && this.form.tradeNoticeInfoBrandMsg.length > 0) ||
        (this.form.noticeInfoAlert && this.form.noticeInfoAlert.length > 0) ||
        (this.form.noticeAlert && this.form.noticeAlert.length > 0) ||
        (this.form.insiderConfirmMsg && this.form.insiderConfirmMsg.length > 0)) {
        return true
      } else {
        return false
      }
    },
    buttonDisabled() {
      if (this.isPrivId) {
        return true
      } else {
        if (!this.isErrMsgs) {
          return false
        } else {
          return (this.isNotEmptyMsg(this.form.tradeNoticeInfoBrandMsg) && this.form.tradingCautionInformation !== '1') ||
          (this.isNotEmptyMsg(this.form.noticeInfoAlert) && this.form.noteInfoCheck !== '1') ||
          (this.isNotEmptyMsg(this.form.noticeAlert) && this.form.noteLimitCheck !== '1') ||
          (this.isNotEmptyMsg(this.form.insiderConfirmMsg) && this.form.insiderErrorConfirmationCheck !== '1')
        }
      }
    },
    getUrlObject() {
      if (!this.form.req.brandCode) {
        return null
      }
      return this.form.req.brandCode.substring(0, 4)
    }

  },
  watch: {
    'form.req.tradeCd': {
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
  // エラー/警告/情報の更新
  beforeUpdate() {
    this.messages.mains.length = 0
    this.messages.mains.push('注文はまだ完了していません。画面下の注文発注ボタンを押下してください。')

    this.messages.errors.length = 0
    if (this.form.tradeNoticeInfoBrandMsg) {
      this.messages.errors.push(this.form.tradeNoticeInfoBrandMsg)
    }
    if (this.form.noticeInfoAlert) {
      this.messages.errors.push(this.form.noticeInfoAlert)
    }
    if (this.form.noticeAlert) {
      this.messages.errors.push(this.form.noticeAlert)
    }
    if (this.form.insiderConfirmMsg) {
      this.messages.errors.push(this.form.insiderConfirmMsg)
    }
    this.messages.warnings.length = 0
    this.messages.infos.length = 0
    if (this.form.code ? this.form.code.startsWith('W') : false && this.form.errMessage) {
      const EditErrMessage = this.form.errMessage + '（' + this.form.code + '）'
      this.messages.infos.push(EditErrMessage)
    }
    if (this.form.warningList && this.form.warningList.length > 0) {
      this.messages.infos.push(...this.form.warningList)
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.formData)
      this.isInsiderCheck = true
    },
    // 戻るボタン
    backA002() {
      this.$emit('close-modal')
      this.form.tradingCautionInformation = '0'
      this.form.noteInfoCheck = '0'
      this.form.noteLimitCheck = '0'
      this.form.insiderErrorConfirmationCheck = '0'
    },
    // 注文発注ボタン
    orderPlacementA001: function(data) {
      this.$emit('order-finish', data.dataList[0])
      this.form.tradingCautionInformation = '0'
      this.form.noteInfoCheck = '0'
      this.form.noteLimitCheck = '0'
      this.form.insiderErrorConfirmationCheck = '0'
    },
    insiderErrorConfirmLinkA005() {
      const resolvedRoute = this.$router.resolve({ name: 'Ifa-Faq' })
      this.$store.commit('page/setFaqParam', '10001')
      window.open(resolvedRoute.href, '_blank')
      this.$store.commit('page/setFaqParam', '')
      this.isInsiderCheck = false
    },
    isNotEmptyMsg(msg) {
      if (!msg) {
        return false
      } else {
        return true
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
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
.warn_link {
  float: right;
  line-height: 30px;
  &_icon {
    color: #f00;
    margin-right: 5px;
  }
  &_text {
    font-size: 16px;
    font-weight: 300;
    color: rgb(0, 85, 255);
    margin-right: 5px;
  }
}
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
  color: red;
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
  margin-right: 2px;
  width: 8px;
}
:deep(.el-checkbox__label) {
  padding-bottom: 0px;
  color: #f00;
  font-size: 16px;
  font-weight: bold;
}
:deep(.el-checkbox__input.is-checked) + .el-checkbox__label {
    color: #f00;
}
.result {
  height: 100%;
  vertical-align: middle;
}
.footer_button {
  text-align: left;
  margin-left: -20px;
}
:deep(.checkClass label>.el-checkbox__label){
  margin-left: 5px !important;
  color: #f00 !important;
  font-weight: bold !important;
  white-space: pre-wrap;
  font-size: 16px;
}
.alert_content {
  :deep(.el-form-item) {
    margin-bottom: 0;
  }
  :deep(.el-form-item__content) {
    line-height: normal;
  }
}
</style>
