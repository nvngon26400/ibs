<template>
  <!-- 確認画面ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title.name"
    :close-on-click-modal="false"
    :show-close="false"
    width="1200px"
    :center="true"
    :before-close="backA005"
    :style="{ 'background-color': bgColor }"
    style="margin-top: 1rem;"
    class="counter_class"
    @open="onShow"
  >
    <ifa-requester
      id="ifaForeignStockCounterOrderConfirmGetNewMainSiteA019"
      action-id="SUB0202_0302-02_2#A019"
      action-type="requestAction"
      :request-model="ifaLinkRequestModel"
      @response-handler="responseHandlerGetNewMainSiteA019($event)"
    ></ifa-requester>
    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: right;"
      >
        <ifa-button
          id="btnBack"
          color="secondary"
          text="戻る"
          style="padding-right: 0;"
          action-type="originalAction"
          @app-action-handler="backA005"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :key="messageKey"
      :main-messages="messages.mains"
      :error-messages="messages.errors"
    ></ifa-message-area>

    <!-- 顧客情報 -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <span>{{ customerInfo.butenCode }}-{{ $_zeroPadding($_out(customerInfo.accountNumber), 7) }}</span>
      </el-col>
    </el-row>
    <el-row
      class="_bold_black_l"
      style="font-size: 20px; padding-top: 0.2rem;"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <span style="position: relative; top: 3px;">
          <el-icon v-if="customerInfo.corporationType === '1'"><OfficeBuilding></OfficeBuilding></el-icon>
          <el-icon v-else><Avatar></Avatar></el-icon>
        </span>
        <span>{{ customerInfo.customerNameKanji }}（{{ customerInfo.customerNameKana }}）</span>
        <ifa-notice-info
          :notice-info-level="customerInfo.noticeInfoLevel"
          :customer-code="customerInfo.customerCode"
          :buten-code="customerInfo.butenCode"
          :account-number="customerInfo.accountNumber"
          style="position: relative; top: 4px;"
        ></ifa-notice-info>
      </el-col>
    </el-row>

    <!--銘柄情報-->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="padding: 0.3rem 0 0.6rem 0"
      >
        <el-card
          class="brandInfo"
          style="background-color: #eee;"
        >
          <el-row>
            <el-col
              :span="22"
              style="display: flex; align-items: flex-start;"
            >
              <div
                style="font-size: 20px; display: inline-block; width: auto; white-space: nowrap;"
                class="_bold_black_l"
              >
                <span style="padding-left: 1rem;"> 銘柄：[
                  <span v-if="form.brandCode">{{ $_out(form.brandCode) }}</span>
                  ]&nbsp;</span>
              </div>
              <div
                style="font-size: 20px; display: inline-block;"
                class="_bold_black_l"
              >
                <span v-if="form.brandName">{{ $_out(form.brandName) }}</span>
              </div>
            </el-col>
            <el-col
              :span="2"
            >
              <ifa-button
                id="btnUpdate"
                color="primary"
                text="更新"
                small
                icon="RefreshRight"
                action-type="requestAction"
                action-id="SUB0202_0302-02_2#A002"
                :request-model="IfaForeignStockCounterOrderConfirmA002RequestModel"
                @response-handler="updateA002($event)"
                @response-error-handler="updateErrorA002()"
              ></ifa-button>
            </el-col>
            <el-col
              :span="24"
              style="display: flex; align-items: flex-start;"
            >
              <div
                class="info-item"
              >
                <span>上限数量：</span>
                <span v-if="form.upperLimitQuantity">{{ $_out($_withCommaInteger(form.upperLimitQuantity)) }} 株</span>
              </div>
              <div
                class="info-item"
              >
                <span>取引価格：</span>
                <span v-if="form.tradePrice">{{ $_out($_withCommaNoneZeroPadding(form.tradePrice)) }} USD</span>
              </div>
              <div
                style="margin: 0.5rem 0.5rem 0 auto;"
              >
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaForeignStockCounterOrderConfirmGetNewMainSiteA019(47,'1','POST',form.brandCode)"
                  > {{ "チャート" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaForeignStockCounterOrderConfirmGetNewMainSiteA019(47,'1','POST',form.brandCode)"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
                <div
                  class="external-link-wrapper"
                  :class="{
                    'external-link-disabled': isDisabled,
                    'external-link-enabled': !isDisabled
                  }"
                >
                  <el-link
                    :underline="false"
                    type="primary"
                    class="external-link"
                    @click="ifaForeignStockCounterOrderConfirmGetNewMainSiteA019(48,'1','POST',form.brandCode)"
                  > {{ "株価参照" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaForeignStockCounterOrderConfirmGetNewMainSiteA019(48,'1','POST',form.brandCode)"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文内容(復唱項目) -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="orderContent"
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
            <el-col
              style="font-size:16px; font-weight: bold"
              :span="16"
            >
              <ifa-text
                :class="form.tradeKbn === '3' ? 'font-color__plus bold' : 'font-color__minus bold'"
                code-list-id="FOREIGN_STOCK_TRADE_CLASS"
                :disp-pattern="1"
                :code-key="form.tradeCd"
              ></ifa-text>
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
            <el-col
              :span="16"
            >
              <span v-if="form.quantity">{{ $_out($_withCommaInteger(form.quantity)) }} 株</span>
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
              <span>約定金額</span>
            </el-col>
            <el-col
              :span="16"
            >
              <span v-if="form.contractAmount">{{ $_out($_withCommaNoneZeroPadding(form.contractAmount)) }} USD</span>
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
              <span>預り区分</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="FOREIGN_DEPOSIT_TYPE"
                :disp-pattern="1"
                :code-key="form.depositType"
              ></ifa-text>
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
              <span>決済方法</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="SETTLEMENT_TYPE"
                :disp-pattern="1"
                :code-key="form.settlementType"
              ></ifa-text>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文内容(復唱項目) -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="orderContent"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>その他注文内容</span>
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
              <span>勧誘区分</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="FOREIGN_STOCK_INVITATION_TYPE"
                :disp-pattern="1"
                :code-key="form.kanyuKbn"
              ></ifa-text>
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
              <span>受注方法</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="FOREIGN_STOCK_ORDER_METHOD_TYPE"
                :disp-pattern="1"
                :code-key="form.orderMethod"
              ></ifa-text>
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
              <span>余力</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="POWER_CONFIRM"
                :disp-pattern="1"
                :code-key="form.powerConfirm"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="form.importantMatter"
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>重要事項の説明</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="IMPORTANT_MATTERS_EXPLAIN"
                :disp-pattern="1"
                :code-key="form.importantMatter"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="form.foreignStockYmd || form.documentDeliveryDate || form.issuedMethod"
            class="dotted_border"
          >
            <el-row
              v-if="form.foreignStockYmd"
            >
              <el-col
                :span="5"
                class="_bold_black_m"
              >
                <span>外国証券情報</span>
              </el-col>
              <el-col
                :span="3"
                class="_bold_black_m"
              >
                <span>版番</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ $_out($_getFormattedDateValue(form.foreignStockYmd, 'date8Kanji')) }}</span>
              </el-col>
            </el-row>
            <el-row v-if="form.documentDeliveryDate">
              <el-col
                :span="5"
              ></el-col>
              <el-col
                :span="3"
                class="_bold_black_m"
              >
                <span>交付日</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ $_out($_getFormattedDateValue(form.documentDeliveryDate, 'date8Kanji')) }}</span>
              </el-col>
            </el-row>
            <el-row v-if="form.issuedMethod">
              <el-col
                :span="5"
              ></el-col>
              <el-col
                :span="3"
                class="_bold_black_m"
              >
                <span>交付方法</span>
              </el-col>
              <el-col
                :span="16"
              >
                <ifa-text
                  code-list-id="FOREIGN_SECURITY_INFO_ISSUE_METHOD"
                  :disp-pattern="1"
                  :code-key="form.issuedMethod"
                ></ifa-text>
              </el-col>
            </el-row>
          </el-row>
          <el-row
            v-if="form.switchingSolicitationEtf"
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>乗換え勧誘(ETF)</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="ETF_SOLICITING_TRANSFERS"
                :disp-pattern="1"
                :code-key="form.switchingSolicitationEtf"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="form.engPubBrand"
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>英文開示銘柄</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="ISSUES_DISCLOSED_IN_ENGLISH_BRAND"
                :disp-pattern="3"
                :code-key="form.engPubBrand"
              ></ifa-text>
              <span v-if="form.engPubYmd">（説明日：{{ $_out($_getFormattedDateValue(form.engPubYmd, 'date8Kanji')) }}）</span>
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
              <span>摘要(任意)</span>
            </el-col>
            <el-col
              :span="16"
            >
              <span v-if="form.summaryAny">{{ $_out(form.summaryAny) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-form
      ref="form"
      :model="form"
      label-width="200px"
      label-position="left"
    >
      <el-col
        :span="22"
        :offset="1"
        style="color: #f00;"
      >
        <el-card
          v-if="messages.errors.length > 0"
          class="orderContent alert_content"
          :style="{ 'background-color': bgColor }"
        >
          <el-row
            class="_bold_black_m _red"
            style="padding-top: 0.5rem; padding-left: 1rem; color: #f00;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <el-row
            v-if="form.msg && form.msg.length > 0"
            class="dotted_border"
            style="padding-bottom: 0.5rem; align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <span class="required-mark">*</span><span style="color: #f00;">ワーニング申請取引</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                id="complianceRankCheckConfirm"
                ref="complianceRankCheckConfirm"
                v-model="form.complianceRankCheckConfirm"
                prop="complianceRankCheckConfirm"
                label-class="alert-content-item"
                code-list-id="original"
                :original-list="complianceRankCheckConfirmOptions"
                required
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.noticeInfoAlert && form.noticeInfoAlert.length > 0"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <span class="required-mark">*</span><span style="color: #f00;">注意情報の確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                id="noticeInfoAlertConfirm"
                ref="noticeInfoAlertConfirm"
                v-model="form.noticeInfoAlertConfirm"
                prop="noticeInfoAlertConfirm"
                label-class="alert-content-item"
                code-list-id="NOTICE_INFO_CONFIRM"
                required
                :select-pattern="2"
                :disp-pattern="1"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.noticeAlert && form.noticeAlert.length > 0"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <span class="required-mark">*</span><span style="color: #f00;">重要なお知らせの確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                id="noticeAlertConfirm"
                ref="noticeAlertConfirm"
                v-model="form.noticeAlertConfirm"
                prop="noticeAlertConfirm"
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                label-class="alert-content-item"
                required
                :select-pattern="2"
                :disp-pattern="1"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.tradeAmountAlert && form.tradeAmountAlert.length > 0"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <span class="required-mark">*</span><span style="color: #f00;">約定金額100万ドル超過</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                id="tradeAmountAlertConfirm"
                ref="tradeAmountAlertConfirm"
                v-model="form.tradeAmountAlertConfirm"
                prop="tradeAmountAlertConfirm"
                label-class="alert-content-item"
                code-list-id="TRADE_AMOUNT_ALERT_CONFIRM"
                required
                :select-pattern="2"
                :disp-pattern="1"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.overseasEtfAlert && form.overseasEtfAlert.length > 0"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <span class="required-mark">*</span><span style="color: #f00;">乗換え勧誘(ETF)なし</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                id="overseasEtfAlertConfirm"
                ref="overseasEtfAlertConfirm"
                v-model="form.overseasEtfAlertConfirm"
                prop="overseasEtfAlertConfirm"
                label-class="alert-content-item"
                code-list-id="OVERSEAS_ETF_ALERT_CONFIRM"
                required
                :select-pattern="2"
                :disp-pattern="1"
              ></ifa-input-check>
            </el-col>
          </el-row>
        </el-card>
      </el-col>

      <el-row>
        <el-col
          :span="22"
          :offset="1"
          style="text-align: left; margin-top: 1rem;"
        >
          <ifa-button
            id="btnConfirm"
            name="btnConfirm"
            text="注文発注"
            width="120"
            :form="formRef"
            :disabled="btnDisabled"
            action-type="requestAction"
            action-id="SUB0202_0302-02_2#A001"
            style="padding-left: 0;"
            :request-model="IfaForeignStockCounterOrderConfirmA001RequestModel"
            @response-handler="orderPlacementA001($event)"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-form>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaForeignStockCounterOrderConfirmFormModel } from './js/IfaForeignStockCounterOrderConfirmFormModel.js'
import { IfaForeignStockCounterOrderConfirmA001RequestModel } from './js/IfaForeignStockCounterOrderConfirmA001RequestModel.js'
import { IfaForeignStockCounterOrderConfirmA002RequestModel } from './js/IfaForeignStockCounterOrderConfirmA002RequestModel.js'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaLinkRequestModel } from './js/IfaLinkRequestModel'
import { notifyMessage } from '@/utils/errorHandler'

export default {
  components: {
    IfaMessageArea,
    IfaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true }
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
      messages: {
        mains: ['注文はまだ完了していません。画面下の注文発注ボタンを押下してください'],
        errors: []
      },
      formRef: {},
      form: new IfaForeignStockCounterOrderConfirmFormModel(),
      updateErrorFlag: false,
      messageKey: 0,
      request: '',
      requestProps: {},
      ifaLinkRequestModel: {}
    }
  },
  computed: {
    IfaForeignStockCounterOrderConfirmA001RequestModel() { return new IfaForeignStockCounterOrderConfirmA001RequestModel(this.form) },
    IfaForeignStockCounterOrderConfirmA002RequestModel() { return new IfaForeignStockCounterOrderConfirmA002RequestModel({ brandCode: this.form.brandCode, brokerCode: this.customerInfo.brokerCode, tradeKbn: this.form.tradeKbn }) },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    bgColor() {
      if (this.form.tradeKbn === '3') {
        // 買付
        return '#fef0f0'
      } else if (this.form.tradeKbn === '1') {
        // 売却
        return '#ecf5ff'
      } else {
        return ''
      }
    },
    userInfo() {
      return this.$store.getters.userAccount
    },
    btnDisabled() {
      return (this.form.msg && this.form.msg.length > 0 && this.form.complianceRankCheckConfirm !== '1') ||
      (this.form.noticeInfoAlert && this.form.noticeInfoAlert.length > 0 && this.form.noticeInfoAlertConfirm !== '1') ||
      (this.form.noticeAlert && this.form.noticeAlert.length > 0 && this.form.noticeAlertConfirm !== '1') ||
      (this.form.tradeAmountAlert && this.form.tradeAmountAlert.length > 0 && this.form.tradeAmountAlertConfirm !== '1') ||
      (this.form.overseasEtfAlert && this.form.overseasEtfAlert.length > 0 && this.form.overseasEtfAlertConfirm !== '1') ||
      (this.userInfo.medUsers.privId === '1' || this.userInfo.medUsers.privId === '2') ||
      this.updateErrorFlag // 更新ボタンでエラーの時非活性
    },
    complianceRankCheckConfirmLabel() {
      return this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 3, this.form.chkBoxLabel)
    },
    complianceRankCheckConfirmOptions() {
      const codeValue = this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, this.form.chkBoxLabel)
      return [{ key: '0', value: '' }, { key: '1', value: codeValue }]
    },
    target() {
      return 'link' + (this.requestProps.urlId === 0 ? '' : this.requestProps.urlId.toString())
    }
  },
  methods: {
    // 初期化
    setData() {
      this.messages.errors = []
      this.updateErrorFlag = false
      Object.assign(this.form, this.formData)
      if (this.form.msg && this.form.msg.length > 0) {
        if (Array.isArray(this.form.msg)) {
          this.messages.errors.push(...this.form.msg)
        } else {
          this.messages.errors.push(this.form.msg)
        }
      }
      if (this.form.noticeInfoAlert && this.form.noticeInfoAlert.length > 0) {
        if (Array.isArray(this.form.noticeInfoAlert)) {
          this.messages.errors.push(...this.form.noticeInfoAlert)
        } else {
          this.messages.errors.push(this.form.noticeInfoAlert)
        }
      }
      if (this.form.noticeAlert && this.form.noticeAlert.length > 0) {
        if (Array.isArray(this.form.noticeAlert)) {
          this.messages.errors.push(...this.form.noticeAlert)
        } else {
          this.messages.errors.push(this.form.noticeAlert)
        }
      }
      if (this.form.tradeAmountAlert && this.form.tradeAmountAlert.length > 0) {
        if (Array.isArray(this.form.tradeAmountAlert)) {
          this.messages.errors.push(...this.form.tradeAmountAlert)
        } else {
          this.messages.errors.push(this.form.tradeAmountAlert)
        }
      }
      if (this.form.overseasEtfAlert && this.form.overseasEtfAlert.length > 0) {
        if (Array.isArray(this.form.overseasEtfAlert)) {
          this.messages.errors.push(...this.form.overseasEtfAlert)
        } else {
          this.messages.errors.push(this.form.overseasEtfAlert)
        }
      }
      this.messageKey++
    },
    onShow() {
      this.formRef = this.$refs.form
      this.$nextTick(() => {
        if (this.formRef) {
          this.formRef.resetFields()
        }
      })
    },
    backA005() {
      this.$nextTick(() => {
        if (this.formRef) {
          this.formRef.resetFields()
        }
      })
      this.$emit('close-modal')
    },
    updateA002(response) {
      this.updateErrorFlag = false
      // 上限数量を更新
      if (this.form.tradeKbn === '3') {
        // 買付
        this.form.upperLimitQuantity = response.dataList[0].maxBuyLimitQuantity
      } else if (this.form.tradeKbn === '1') {
        // 売却
        this.form.upperLimitQuantity = response.dataList[0].maxSellLimitQuantity
      }
    },
    updateErrorA002() {
      // 上限数量が空もしくは0以下の場合、注文発注ボタンを非活性にする。
      this.updateErrorFlag = true
    },
    orderPlacementA001(response) {
      // 完了画面へ遷移、A001レスポンスを完了画面に渡す
      this.$emit('order-finish', response.dataList[0])
    },
    ifaForeignStockCounterOrderConfirmGetNewMainSiteA019(urlId, patternId, httpMethod, brandCode) {
      const newData = {
        urlId: urlId,
        patternId: patternId,
        httpMethod: httpMethod,
        brandCode: brandCode
      }
      this.requestProps = newData
      this.ifaLinkRequestModel = new IfaLinkRequestModel(this.requestProps)
      document.getElementById('ifaForeignStockCounterOrderConfirmGetNewMainSiteA019').click()
    },
    responseHandlerGetNewMainSiteA019(response) {
      Object.assign(this.form, response?.dataList?.[0])
      if (this.form.postRequest) {
        this.request = this.form.postRequest
      }
      this.openWindow()
    },
    openWindow() {
      // cursor: not-allowed; と pointer-events: none; が同時に使えなかったため
      this.linkUrl = this.form.linkUrl
      this.paramObject = this.form.newMainSiteParamList[0]
      const newWindow = window.open('', this.target)
      if (newWindow) {
        const linkForm = document.createElement('form')
        linkForm.target = this.target
        linkForm.method = 'POST'
        linkForm.action = this.linkUrl
        // request と paramObject をマージする｡
        // key が同じ場合は､ paramObject を優先する
        const objs = Object.assign({}, this.request, this.paramObject)
        const params = Object.entries(objs)
          .map(e => ({ name: e[0], value: e[1] }))
        if (params) {
          for (const param of params) {
            const linkInput = document.createElement('input')
            linkInput.type = 'hidden'
            linkInput.name = param.name
            linkInput.value = param.value
            linkForm.appendChild(linkInput)
          }
        }
        document.body.appendChild(linkForm)
        linkForm.submit()
        document.body.removeChild(linkForm)
      } else {
        const label = this.$store.getters.pageInfo.label
        notifyMessage(2, 'ポップアップを許可してください｡', label)
      }
    }
  }
}
</script>

<style lang="scss">
@import '@/styles/foreignStockOrder.scss';
.counter_class .el-dialog__title {
  font-size: 18px;
  font-weight: bold;
}
</style>
<style lang="scss" scoped>
:deep(.el-checkbox__label) {
  padding-bottom: 0px;
  color: #f00 !important;
  font-weight: bold;
  font-size: 16px;
}
.info-item {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
  display: flex;
  justify-content: space-between;
  margin: 0 1.5rem;
  padding: 0 0.25rem;
}
.brandInfo {
  background-color: #eee;
}
.orderInfo {
  background-color: #eee;
  width: 100%;
}
.orderContent {
  width: 100%;
}
.dotted_border {
  border-bottom: 1px dotted #dddddd;
  padding: 0.5rem 0.2rem 0.2rem 0.5rem;
}
._bold_black_l {
  font-size: 16px;
  font-weight: bold;
  padding-bottom: 0.25rem;
  color: #313131;
}
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}
._red {
  color: #f00;
}

.alert-content-item {
  :deep(.el-form-item__label) {
    margin-left: 0;
    font-weight: 700;
    color: #ff1e00;
  }
  :deep(.el-form-item__content) {
    --ifa-input-validation-error-width: 400px;
  }
  :deep(.el-form-item__error) {
    margin-left: 0;
  }
}
:deep(._bold_red_alart) {
  font-size: 16px;
  font-weight: bold;
  padding-right: 0.5rem;
  color: red;
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
.external-link {
  padding: 3px 7px;
  text-decoration: underline;
  &:hover, &:focus {
    text-decoration: underline;
      }
}
.external-link-icon {
  --background-image: url(~@/assets/icons/external-link.svg);
  content: "";

  height: 14px;
  width: 14px;
  display: inline-block;
  vertical-align: middle;
}
.external-link-wrapper {
  display: inline-block
}
.external-link-enabled {
  cursor: pointer;
  .el-link {
    &:hover {
      // el-link の元々の opacity と混ざるのでリセット
      opacity: unset;
    }
  }
  &:hover {
    opacity: 0.7;
  }
}
.external-link-disabled {
  .el-link {
    // el-link が pointer アイコンにしてしまうので強制的にアイコンを変更
    cursor: not-allowed;
    &:hover {
      // el-link の元々の opacity と混ざるのでリセット
      opacity: unset;
    }
  }
  cursor: not-allowed;
  &:hover {
    opacity: 0.7;
  }
}
</style>
