<template>
  <!-- SUB0202_0212-04_3_信用返済注文完了 -->
  <el-dialog
    v-model="vmIsVisible"
    :title="formData.title.name"
    :show-close="false"
    :center="true"
    :close-on-click-modal="false"
    width="1200px"
    :style="dialogStyle"
    @open="onShow"
  >
    <!-- エラー/警告表示 -->
    <el-row style="margin-top: 40px;"></el-row>
    <ifa-message-area
      :main-messages="['下記の内容で注文を受け付けました。']"
    ></ifa-message-area>
    <!-- 口座エリア -->
    <el-row
      style="font-weight: bold; color: black;"
    >
      <el-col :offset="1">
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
        style="font-size: 20px;"
        class="_bold_black_l"
      >
        <span style="position: relative; top: 3px;">
          <el-icon v-if="customerInfo.corporationType == '1'"><OfficeBuilding></OfficeBuilding></el-icon>
          <el-icon v-else><Avatar></Avatar></el-icon>
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
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>ご注文内容</span>
          </el-row>
          <hr>
          <el-row class="dotted_border">
            <el-col
              class="_bold_black_m"
              :span="9"
              style="font-size: 16px;"
            >
              <span>[銘柄コード]&nbsp;銘柄名</span>
            </el-col>
            <el-col
              style="font-size:16px;"
              :span="10"
            >
              <span style="overflow-wrap: break-word;">[{{ $_out(formData.request.brandCode) }}]&nbsp;{{ $_out(formData.request.brandName) }}</span>
            </el-col>
            <el-col
              v-if="formData.request.tradeNoticeInfoBrandMsg"
              :span="5"
              style="text-align: center;"
            >
              <el-icon
                style="color: red;  vertical-align: middle;"
              ><WarningFilled></WarningFilled></el-icon>
              <ifa-link
                ref="tradeLimitRef"
                :disp-name="'取引注意情報'"
                :url-id="16"
                :url-object="{ brandCode: getUrlObject }"
              ></ifa-link>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              class="_bold_black_m"
              :span="9"
              style="font-size: 16px;"
            >
              <span>注文種別</span>
            </el-col>
            <el-col
              style="font-size:16px;font-weight: bold"
              :span="15"
            >
              <span v-if="formData.request.sasinariHouhou === '3'">逆指値注文</span>
              <ifa-text
                v-else
                code-list-id="ORDER_CLASS"
                :disp-pattern="2"
                :code-key="formData.request.orderKind"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>取引種別</span>
            </el-col>
            <el-col :span="15">
              <ifa-text
                :style="fontColor+ 'fontWeight:bold'"
                :code-list-id="'DOMESTIC_STOCK_TRADE_CLASS'"
                :disp-pattern="1"
                :code-key="formData.request.tradeCd"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>市場</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('SELECT_MARKET', 1, formData.request.orderMarket)) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>受注数量</span>
            </el-col>
            <el-col
              :span="15"
              class="data-padding"
            >
              <span>{{ formData.request.quantity ? `${$_out($_withCommaInteger(formData.request.quantity))}株` : '-' }}</span>
            </el-col>
          </el-row>
          <!-- 注文種別がOCO以外の場合のみに表示する -->
          <el-row
            v-if="formData.request.orderKind !== '2'"
            class="_bold_black_m"
            style="padding-left: 8px; font-size: 16px;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>返済建玉指定方法</span>
            </el-col>
            <el-col
              :span="15"
              class="data-padding"
            >
              <span>{{ $_out($_getCodeValue('REPAY_METHOD', 2, formData.request.repayMethod)) }}
              </span>
              <span
                v-if="formData.request.repaymentOrder"
                style="margin-left: 1rem;"
              >{{ $_getCodeValue('REPAY_ORDER', 1, formData.request.repaymentOrder) }}
              </span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.request.orderKind === '1'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>価格</span>
            </el-col>
            <el-col :span="15">
              <span>
                {{ $_out($_getCodeValue('EXECUTE_METHOD', 1, formData.request.sasinariHouhou)) }}
              </span>
              <br>
              <span>
                {{ getPrice() }}
              </span>
            </el-col>
          </el-row>
          <!-- OCO -->
          <el-row
            v-if="formData.request.orderKind === '2'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>価格/OCO1</span>
            </el-col>
            <el-col :span="15">
              <ifa-text
                :code-list-id="'EXECUTE_METHOD'"
                :disp-pattern="1"
                :code-key="formData.request.oco1SasinariHouhou"
              ></ifa-text>
              <br>
              <span>{{ getOCO1Price() }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.request.orderKind === '2'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>条件/OCO2</span>
            </el-col>
            <el-col
              :span="15"
              class="data-padding"
            >
              <span>{{ getOCO2Price() }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>注文期間</span>
            </el-col>
            <el-col :span="15">
              <span v-if="formData.request.periodTerms === '0'">
                {{ $_out($_getCodeValue('PERIOD_CONDITIONS', 1, formData.request.periodTerms)) }}
              </span>
              <span v-else-if="formData.request.periodTerms === '1'">
                {{ $_out($_getFormattedDateValue(formData.request.limit)) + "まで" }}
              </span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>信用取引区分</span>
            </el-col>
            <el-col :span="15">
              <span>
                {{ $_out(formData.request.paymentDeadlineCalculation) }}
              </span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>手数料区分</span>
            </el-col>
            <el-col :span="15">
              <span>
                {{ $_out($_getCodeValue('PRE_CONTRACT_DOC_CODE', 1, customerInfo.customerAttribute) + '（電話手数料）') }}
              </span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>建市場</span>
            </el-col>
            <el-col :span="15">
              <span>
                {{ formData.request.repayMethod == '2' ? $_out($_getCodeValue('NEW_MARKET', 1, formData.request.repayPositionDetail[0].builtMarket)) : '-' }}
              </span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>新規建日</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.request.repayMethod == '2' ? $_getFormattedDateValue(formData.request.repayPositionDetail[0].constructionDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>親株新規約定日</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.request.repayMethod == '2' ? $_getFormattedDateValue(formData.request.repayPositionDetail[0].parentStockTradeDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>建単価</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formData.request.repayMethod == '2' ? $_withCommaNoneZeroPadding(formData.request.repayPositionDetail[0].newPrice, 2) + '円': '-' }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>
    <!-- その他注文内容 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card class="box-card">
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>その他注文内容</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>見積単価</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ formData.quoteUnitPrice ? `${$_withCommaZeroPadding(formData.quoteUnitPrice, 1)}円`: '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>建玉No</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ formData.request.repayMethod == '2' && formData.request.repayPositionDetail[0].positionNo ? $_zeroPadding(formData.request.repayPositionDetail[0].positionNo, 5) : '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>約定金額</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ formData.contractAmount ? `${$_withCommaInteger(formData.contractAmount)}円`: '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>手数料/諸費用</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ formData.charge ? `${$_withCommaInteger(formData.charge)}円`: '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>消費税</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ formData.consumptionTax ? `${$_withCommaInteger(formData.consumptionTax)}円`: '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>譲渡益税</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ formData.yieldTax ? `${$_withCommaInteger(formData.yieldTax)}円`: '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>精算金額</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ formData.settlementAmount ? `${$_withCommaInteger(formData.settlementAmount)}円`: '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>約定予定日</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ formData.contractDate ? $_getFormattedDateValue(formData.contractDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>受渡予定日</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ formData.deliveryDate ? $_getFormattedDateValue(formData.deliveryDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>受注日時</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ formData.orderDayTime ? $_getFormattedDateTimeValue(formData.orderDayTime, 'datetime12') : '----/--/-- --:--' }} </span>
            </el-col>
          </el-row>
          <!-- 勧誘区分 -->
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ $_out($_getCodeValue('INVITATION_TYPE', 1, formData.request.kanyuKbn)) }}
              </span>
            </el-col>
          </el-row>
          <!-- 受注方法 -->
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>受注方法</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ $_out($_getCodeValue('ORDER_METHOD_TYPE', 1, formData.request.orderMethod)) }}
              </span>
            </el-col>
          </el-row>
          <el-row>
            <div style="margin-bottom: 0.5rem;"></div>
          </el-row>
          <!-- 確認項目 -->
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>確認項目</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ $_out($_getCodeValue('INSIDER_CONFIRM', 2, formData.request.checkInsider)) }}
              </span>
              <br v-if="formData.request.orderMarket === 'A'">
              <span v-if="formData.request.orderMarket === 'A'">{{ $_out($_getCodeValue('SOR_CONFIRM', 2, formData.request.checkSor)) }}
              </span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>EC受注番号</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ $_out(formData.ecOrderNo) }}
              </span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!-- アラート内容確認 -->
    <el-row
      v-if="formData.request.tradeNoticeInfoBrandMsg ||
        formData.request.noticeInfoAlert ||
        formData.request.noticeAlert ||
        formData.request.insiderConfirmMsg"
      style="margin-top:0.5rem;"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card class="box-card">
          <el-row
            class="_bold_black_m"
            style="padding-left: 1rem; padding-top: 0.5rem;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <!-- 取引注意情報（銘柄）確認 -->
          <el-row
            v-if="formData.request.tradeNoticeInfoBrandMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>取引注意情報の説明</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ $_getCodeValue('TRADE_NOTICE_INFO_EXPLAIN', 1, '1') }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.request.noticeInfoAlert"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>注意情報の確認</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ $_getCodeValue('NOTICE_INFO_CONFIRM', 1, '1') }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.request.noticeAlert"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>重要なお知らせの確認</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ $_getCodeValue('IMPORTANT_NOTIFICATION_CONFIRM', 1, '1') }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.request.insiderConfirmMsg"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_black_s_bold"
              style="padding-left: 8px; font-size: 16px;"
            >
              <span>内部者取引確認（知る前契約/計画の確認含む）</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ $_getCodeValue('INSIDER_TRADE_CONFIRM', 1, '1') }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!-- 注文状況一覧へボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnToOrderStatusList"
          text="注文状況一覧へ"
          color="primary"
          action-type="originalAction"
          style="padding-left: 0;"
          @app-action-handler="handleMoveToUnexecutedOrderList"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>
<script>
import { useVModel } from 'vue-composable'
import { IfaMarginRepayOrderCompleteFormModel } from './js/IfaMarginRepayOrderCompleteFormModel'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'

export default {
  components: {
    IfaMessageArea,
    IfaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    form: { type: Object, required: true },
    customerInfo: { type: Object, required: true }
  },
  emits: ['close-modal', 'move-to-unexecuted-order-list', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      formData: new IfaMarginRepayOrderCompleteFormModel(),
      dialogStyle: '',
      fontColor: ''
    }
  },
  computed: {
    customerName() {
      return this.customerInfo.customerNameKanji + '（' + this.customerInfo.customerNameKana + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    },
    getUrlObject() {
      if (!this.formData.request.brandCode) {
        return null
      }
      return this.formData.request.brandCode.substring(0, 4)
    }
  },
  watch: {
    'formData.request.tradeCd': {
      deep: true,
      handler(newValue) {
        // 信用返済買
        if (newValue === '5') {
          this.dialogStyle = 'backgroundColor: #fef0f0;'
          this.fontColor = 'color: #E5004C;'
        } else {
        // 信用返済売
          this.dialogStyle = 'backgroundColor: #ecf5ff;'
          this.fontColor = 'color: #00847F;'
        }
      }
    }
  },
  methods: {
    onShow() {
      this.formData = Object.assign(this.formData, this.form)

      // 取引注意情報リンクの初期化
      this.$nextTick(() => {
        this.$refs['tradeLimitRef']?.trigger()
      })
    },
    getPrice() {
      if (this.formData.request.sasinariHouhou === '1') {
        // 指値
        const str = this.formData.request.price ? this.$_withCommaZeroPadding(this.formData.request.price, 1) + '円' : '-'
        if (this.formData.request.sasinariJyouken !== ' ') {
          // 通常/逆指値.執行条件≠条件なし　の場合
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.request.sasinariJyouken)) +
               '/' + str
        } else {
          return str
        }
      } else if (this.formData.request.sasinariHouhou === '2') {
        // 成行
        if (this.formData.request.sasinariJyouken !== 'N') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.formData.request.sasinariJyouken))
        }
      } else if (this.formData.request.sasinariHouhou === '3') {
        // 逆指値
        const triggerPrice = this.formData.request.triggerPrice ? this.$_withCommaZeroPadding(this.formData.request.triggerPrice, 1) + '円' : '-'
        let strTriggerPrice = '現在値が' + triggerPrice
        switch (this.formData.request.tradeCd) {
          // 取引種別=信用返済買　の場合
          case '5':
            strTriggerPrice += '以上になった時点で'
            break
          // 取引種別=信用返済売　の場合
          case '6':
            strTriggerPrice += '以下になった時点で'
            break
        }
        if (this.formData.request.gyakusasiHouhou === '1') {
          // 執行方法（逆指値）＝指値　の場合
          const strPrice = this.formData.request.price ? this.$_withCommaZeroPadding(this.formData.request.price, 1) + '円' : '-'
          if (this.formData.request.sasinariJyouken !== ' ') {
            // 通常/逆指値.執行条件≠条件なし　の場合
            return strTriggerPrice + this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.request.sasinariJyouken) + strPrice + 'で執行'
          } else {
            return strTriggerPrice + strPrice + 'で執行'
          }
        } else if (this.formData.request.gyakusasiHouhou === '2') {
          // 執行方法（逆指値）が成行の時
          return strTriggerPrice + this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.formData.request.sasinariJyouken) + 'で執行'
        }
        return strTriggerPrice + 'で執行'
      }
      return ''
    },
    getOCO1Price() {
      if (this.formData.request.oco1SasinariJyouken !== ' ') {
        return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.request.oco1SasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.formData.request.oco1Price)) + '円'
      }
      return this.$_out(this.$_withCommaNoneZeroPadding(this.formData.request.oco1Price)) + '円'
    },
    getOCO2Price() {
      let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.formData.request.oco2TriggerPrice)) + '円' +
                  (this.formData.request.tradeCd === '5' ? '以上' : '以下') + 'になった時点でOCO1'
      if (this.formData.request.oco1SasinariJyouken !== ' ') {
        str += '（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.request.oco1SasinariJyouken)) + '）を'
      } else {
        str += '（指値）を'
      }
      // OCO2.執行方法（逆指値）: 指値
      if (this.formData.request.oco2GyakusasiHouhou === '1') {
        // OCO2.執行条件（逆指値）：≠条件なし
        if (this.formData.request.oco2GyakusasiJyouken !== ' ') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.request.oco2GyakusasiJyouken)) + '/' +
          this.$_out(this.$_withCommaNoneZeroPadding(this.formData.request.oco2Price)) + '円に訂正'
        } else {
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.formData.request.oco2Price)) + '円に訂正'
        }
        // OCO2.執行方法（逆指値）: 成行
      } else if (this.formData.request.oco2GyakusasiHouhou === '2') {
        str += '成行に訂正'
      }
      return str
    },
    // 閉じるボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 注文状況一覧へボタン
    handleMoveToUnexecutedOrderList() {
      this.$emit('move-to-unexecuted-order-list')
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.error-message {
  margin: 0.5rem 0;
  padding: 0 8rem;
  color: red;
  font-size: 18px;
  font-weight: bold;
}
.warning-message {
  margin: 0.5rem 0;
  padding: 0 8rem;
  color: red;
  font-size: 14px;
}
.info-message {
  margin: 0.5rem 0;
  padding: 0 8rem;
  color: #000;
  font-size: 14px;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
.footer_button {
  text-align: left;
}
:deep(.el-text){
  font-size: 16px;
}
:deep(.el-checkbox__label) {
    font-size: 16px;
  }
</style>
