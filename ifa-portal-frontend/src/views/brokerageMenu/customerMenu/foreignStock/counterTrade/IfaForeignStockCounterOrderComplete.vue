<template>
  <!-- 完了画面ダイアログ -->
  <el-dialog
    :model-value="isVisible"
    :title="form.title.name"
    :close-on-click-modal="false"
    :show-close="false"
    width="1200px"
    :center="true"
    :style="{ 'background-color': bgColor }"
    :close-on-press-escape="false"
    style="margin-top: 1rem;"
    class="counter_class"
  >

    <!-- メッセージエリア -->
    <ifa-message-area
      :main-messages="messages.mains"
    ></ifa-message-area>

    <!-- 顧客情報エリア -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col
        :offset="1"
      >
        <span>{{ customerInfo.butenCode }}-{{ $_zeroPadding($_out(customerInfo.accountNumber), 7) }}</span>

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

    <!-- 銘柄情報エリア -->
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
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文内容エリア -->
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
            <el-col
              style="font-size:16px; font-weight: bold"
              :span="16"
            >
              <ifa-text
                :class="form.tradeCd === '11' ? 'font-color__plus bold' : 'font-color__minus bold'"
                code-list-id="FOREIGN_STOCK_TRADE_CLASS"
                :select-pattern="4"
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
                :select-pattern="2"
                :disp-pattern="2"
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
                :select-pattern="1"
                :disp-pattern="1"
                :code-key="form.settlementType"
              ></ifa-text>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- その他注文内容エリア -->
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
                :select-pattern="1"
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
                :select-pattern="1"
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
                :select-pattern="1"
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
                :select-pattern="1"
                :disp-pattern="1"
                :code-key="form.importantMatter"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="form.foreignStockYmd || form.documentDeliveryDate || form.issuedMethod"
            class="dotted_border"
          >
            <el-row v-if="form.foreignStockYmd">
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
                class="_bold_black_m"
              >
                <span v-if="!form.foreignStockYmd">外国証券情報</span>
              </el-col>
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
                class="_bold_black_m"
              >
                <span v-if="!form.foreignStockYmd && !form.documentDeliveryDate">外国証券情報</span>
              </el-col>
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
                  :select-pattern="1"
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
                :select-pattern="1"
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
                :select-pattern="1"
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

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          v-if="isAlertExists"
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <el-row
            v-if="isComplianceRankCheckExists"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <ifa-text
                code-list-id="COMPLA_CHECK_BOX_WORDING"
                :select-pattern="1"
                :disp-pattern="3"
                :code-key="form.chkBoxLabel"
              ></ifa-text>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="COMPLA_CHECK_BOX_WORDING"
                :select-pattern="1"
                :disp-pattern="1"
                :code-key="form.chkBoxLabel"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="isNoticeInfoAlertExists"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>注意情報の確認</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="NOTICE_INFO_CONFIRM"
                :select-pattern="2"
                :disp-pattern="1"
                :code-key="form.noticeInfoAlertConfirm"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="isNoticeAlertExists"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>重要なお知らせの確認</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                :select-pattern="2"
                :disp-pattern="1"
                :code-key="form.noticeAlertConfirm"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="isTradeAmountAlertExists"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>約定金額100万ドル超過</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="TRADE_AMOUNT_ALERT_CONFIRM"
                :select-pattern="2"
                :disp-pattern="1"
                :code-key="form.tradeAmountAlertConfirm"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="isOverseasEtfAlertExists"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>乗換え勧誘(ETF)なし</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-text
                code-list-id="OVERSEAS_ETF_ALERT_CONFIRM"
                :select-pattern="2"
                :disp-pattern="1"
                :code-key="form.overseasEtfAlertConfirm"
              ></ifa-text>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          text="注文状況一覧へ"
          width="150"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="handleDispatchOrderStatusListA001"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaForeignStockCounterOrderCompleteFormModel } from './js/IfaForeignStockCounterOrderCompleteFormModel.js'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'

export default {
  components: {
    IfaMessageArea,
    IfaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true }
  },
  emits: ['move-to-order-list'],
  data() {
    return {
      messages: {
        mains: ['下記の内容で注文を受け付けました。']
      },
      form: new IfaForeignStockCounterOrderCompleteFormModel()
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    bgColor() {
      if (this.form.tradeCd === '11') {
        // 買付
        return '#fef0f0'
      } else if (this.form.tradeCd === '12') {
        // 売却
        return '#ecf5ff'
      } else {
        return ''
      }
    },
    // コンプラチェック.メッセージ有り無し判定
    isComplianceRankCheckExists() {
      if (Array.isArray(this.form.msg)) {
        if (this.form.msg.length > 0) {
          return true
        }
      } else if (this.form.msg) {
        return true
      }
      return false
    },
    // 注意情報アラートメッセージ有り無し判定
    isNoticeInfoAlertExists() {
      if (Array.isArray(this.form.noticeInfoAlert)) {
        if (this.form.noticeInfoAlert.length > 0) {
          return true
        }
      } else if (this.form.noticeInfoAlert) {
        return true
      }
      return false
    },
    // お知らせアラートメッセージ有り無し判定
    isNoticeAlertExists() {
      if (Array.isArray(this.form.noticeAlert)) {
        if (this.form.noticeAlert.length > 0) {
          return true
        }
      } else if (this.form.noticeAlert) {
        return true
      }
      return false
    },
    // 約定金額アラートメッセージ有り無し判定
    isTradeAmountAlertExists() {
      if (Array.isArray(this.form.tradeAmountAlert)) {
        if (this.form.tradeAmountAlert.length > 0) {
          return true
        }
      } else if (this.form.tradeAmountAlert) {
        return true
      }
      return false
    },
    // 海外ETFアラートメッセージ有り無し判定
    isOverseasEtfAlertExists() {
      if (Array.isArray(this.form.overseasEtfAlert)) {
        if (this.form.overseasEtfAlert.length > 0) {
          return true
        }
      } else if (this.form.overseasEtfAlert) {
        return true
      }
      return false
    },
    // アラートメッセージ有り無し判定
    isAlertExists() {
      return this.isComplianceRankCheckExists || this.isNoticeInfoAlertExists ||
        this.isNoticeAlertExists || this.isTradeAmountAlertExists || this.isOverseasEtfAlertExists
    }
  },
  methods: {
    // 初期化
    setData() {
      Object.assign(this.form, this.formData)
    },
    handleDispatchOrderStatusListA001(response) {
      // 注文状況一覧画面へ遷移
      this.$emit('move-to-order-list')
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
.brandInfo {
  background-color: #eee;
  margin-bottom: 0.5rem;
  padding-top: 0.5rem;
}
.orderContent {
  width: 100%;
  margin-bottom: 0.5rem;
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
._black_s_bold {
  font-size: 14px;
  font-weight: bold;
  color: #4a4e55;
  padding-bottom: 0.2rem;
}
</style>
