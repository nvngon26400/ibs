<template>
  <el-dialog
    v-model="vmIsVisible"
    :style="dialogStyle"
    :title="form.title.name"
    width="1200px"
    top="6%"
    :center="true"
    :show-close="false"
    :before-close="backA002"
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
          text="戻る"
          color="secondary"
          action-type="originalAction"
          style="padding-right: 0;"
          @app-action-handler="backA002"
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
    <!-- 注文内容(復唱項目) -->
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
            <span>ご注文内容（復唱項目）</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
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
              <span style="overflow-wrap: break-word;">[{{ $_out(form.request.brandCode) }}]&nbsp;{{ $_out(form.brandName) }}</span>
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
                ref="tradeLimitRef"
                :disp-name="'取引注意情報'"
                :url-id="16"
                :url-object="{ brandCode: getUrlObject }"
              ></ifa-link>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="vertical-align: middle;"
          >
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
              <span v-if="form.request.sasinariHouhou === '3'">逆指値注文</span>
              <ifa-text
                v-else
                code-list-id="ORDER_CLASS"
                :disp-pattern="2"
                :code-key="form.request.orderKind"
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
                :style="fontStyle+ 'fontWeight:bold'"
                :code-list-id="'DOMESTIC_STOCK_TRADE_CLASS'"
                :disp-pattern="1"
                :code-key="form.request.tradeCd"
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
              <ifa-text
                :code-list-id="'SELECT_MARKET'"
                :disp-pattern="1"
                :code-key="form.request.orderMarket"
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
              <span>受注数量</span>
            </el-col>
            <el-col
              :span="15"
              style="font: size 16px;"
            >
              <span>{{ form.request.quantity ? `${$_withCommaInteger(form.request.quantity)}株` : '-' }} </span>
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
              <span>返済建玉指定方法</span>
            </el-col>
            <el-col :span="15">
              <ifa-text
                :code-list-id="'REPAY_METHOD'"
                :disp-pattern="2"
                :code-key="form.request.repayMethod"
              ></ifa-text>
              <ifa-text
                v-if="form.request.repaymentOrder"
                :code-list-id="'REPAY_ORDER'"
                :disp-pattern="1"
                :code-key="form.request.repaymentOrder"
                style="margin-left: 1rem;"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 通常/逆指値 -->
          <el-row
            v-if="form.request.orderKind === '1'"
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
              <ifa-text
                :code-list-id="'EXECUTE_METHOD'"
                :disp-pattern="1"
                :code-key="form.request.sasinariHouhou"
              ></ifa-text><br v-if="getPrice() !== ''">
              <span>{{ getPrice() }}</span>
            </el-col>
          </el-row>
          <!-- OCO -->
          <div v-if="form.request.orderKind === '2'">
            <el-row
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
                  :code-key="form.request.oco1SasinariHouhou"
                ></ifa-text>／
                <span>{{ getOCO1Price() }}</span>
              </el-col>
            </el-row>
            <el-row
              class="dotted_border"
            >
              <el-col
                :span="9"
                class="_bold_black_m"
                style="padding-left: 8px; font-size: 16px;"
              >
                <span>条件/OCO2</span>
              </el-col>
              <el-col :span="15">
                <span>{{ getOCO2Price() }}</span>
              </el-col>
            </el-row>
          </div>

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
              <span>{{ form.request.periodTerms == '1' ? `${$_out($_getFormattedDateValue(form.request.limit))}まで` : "当日中" }}</span>
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
              <span>{{ $_out(form.request.paymentDeadlineCalculation) }}</span>
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
              <ifa-text
                :code-list-id="'PRE_CONTRACT_DOC_CODE'"
                :disp-pattern="1"
                :code-key="customerInfo.customerAttribute"
              ></ifa-text>
              <span>{{ customerInfo.customerAttribute ? '（電話手数料）' : '-' }}</span>
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
              <span v-if="form.request.repayMethod === '2'">
                <ifa-text
                  :code-list-id="'NEW_MARKET'"
                  :disp-pattern="1"
                  :code-key="form.request.repayPositionDetail[0].builtMarket"
                ></ifa-text>
              </span>
              <span v-else>-</span>
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
              <span>{{ form.request.repayMethod === '2' ? $_out($_getFormattedDateValue(form.request.repayPositionDetail[0].constructionDate)) : "----/--/--" }}</span>
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
              <span>{{ form.request.repayMethod === '2' ? $_out($_getFormattedDateValue(form.request.repayPositionDetail[0].parentStockTradeDate)) : "----/--/--" }}</span>
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
              <span>{{ form.request.repayMethod === '2' ? `${$_out($_withCommaNoneZeroPadding(form.request.repayPositionDetail[0].newPrice))}円` : "-" }}</span>
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
              <span>{{ $_out($_withCommaInteger(form.quoteUnitPrice)) }}円</span>
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
              <span>{{ form.request.repayMethod === '2' && form.request.repayPositionDetail[0].positionNo ? $_zeroPadding(form.request.repayPositionDetail[0].positionNo, 5) : "-" }}</span>
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
              <span>{{ $_out($_withCommaInteger(form.contractAmount)) }}円</span>
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
              <span>{{ $_out($_withCommaInteger(form.charge)) }}円</span>
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
              <span>{{ $_out($_withCommaInteger(form.consumptionTax)) }}円</span>
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
              <span>{{ $_out($_withCommaInteger(form.yieldTax)) }}円</span>
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
              <span>{{ $_out($_withCommaInteger(form.settlementAmount)) }}円</span>
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
              <span>{{ $_out($_getFormattedDateValue(form.contractDate)) }}</span>
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
              <span>{{ $_out($_getFormattedDateValue(form.deliveryDate)) }}</span>
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
              <span>{{ $_out($_getFormattedDateTimeValue(form.orderDayTime, 'datetime12')) }}</span>
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
              <ifa-text
                code-list-id="INVITATION_TYPE"
                :disp-pattern="1"
                :code-key="form.request.kanyuKbn"
              ></ifa-text>
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
              <ifa-text
                code-list-id="ORDER_METHOD_TYPE"
                :disp-pattern="1"
                :code-key="form.request.orderMethod"
              ></ifa-text>
            </el-col>
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
              class="label-padding_other"
            >
              <ifa-text
                code-list-id="INSIDER_CONFIRM"
                :disp-pattern="2"
                code-key="1"
              ></ifa-text><br v-if="form.request.orderMarket === 'A'">
              <ifa-text
                v-if="form.request.orderMarket === 'A'"
                code-list-id="SOR_CONFIRM"
                :disp-pattern="2"
                code-key="1"
              ></ifa-text>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row
      v-if="form.tradeNoticeInfoBrandMsg ||
        form.noticeInfoAlert ||
        form.noticeAlert ||
        form.insiderConfirmMsg"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          :class="form.request.tradeCd === '5' ? 'buy-background-color_card' : 'sell-background-color_card'"
          class="box-card"
        >
          <el-row
            class="_bold_black_m"
            style="padding-left: 1rem; padding-top: 0.5rem; color: #f00;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>

          <el-row
            v-if="form.tradeNoticeInfoBrandMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style=" font-size: 16px; padding-left: 0rem;"
            >
              <span class="required-mark">*</span><span style="color: #f00;">取引注意情報の説明</span>
            </el-col>
            <el-col
              :span="15"
            >
              <ifa-input-check
                v-model="form.tradeNoticeInfoBrandConfirm"
                style="margin-left: 5px; color: #f00;"
                code-list-id="TRADE_NOTICE_INFO_EXPLAIN"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.noticeInfoAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style=" font-size: 16px; padding-left: 0rem;"
            >
              <span class="required-mark">*</span><span style="color: #f00;">注意情報の確認</span>
            </el-col>
            <el-col :span="15">
              <ifa-input-check
                v-model="form.noticeInfoAlertConfirm"
                style="margin-left: 5px; color: #f00;"
                code-list-id="NOTICE_INFO_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.noticeAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style=" font-size: 16px; padding-left: 0rem;"
            >
              <span class="required-mark">*</span><span style="color: #f00;">重要なお知らせの確認</span>
            </el-col>
            <el-col :span="15">
              <ifa-input-check
                v-model="form.noticeAlertConfirm"
                style="margin-left: 5px; color: #f00;"
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.insiderConfirmMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="display: flex; align-items: flex-start; padding-left: 0rem;"
            >
              <span class="required-mark">*</span>
              <span
                class="el-link el-link--primary"
                style="font-weight: bold; text-decoration: underline; font-size: 16px;"
                @click="insiderErrorConfirmLinkA005"
              >内部者取引確認（知る前契約/計画の確認含む）
              </span>
            </el-col>
            <el-col :span="15">
              <ifa-input-check
                v-model="form.insiderErrorConfirm"
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
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnOrderRegister"
          text="注文発注"
          :disabled="buttonDisabled"
          action-type="requestAction"
          :request-model="A001RequestModel"
          action-id="SUB0202_0212-04_2#A001"
          style="padding-left: 0;"
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
import { IfaMarginRepayOrderConfirmFormModel } from './js/IfaMarginRepayOrderConfirmFormModel.js'
import { IfaMarginRepayOrderConfirmA001RequestModel } from './js/IfaMarginRepayOrderConfirmA001RequestModel.js'
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
  emits: ['close-modal', 'order-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaMarginRepayOrderConfirmFormModel(),
      dialogStyle: '',
      fontStyle: '',
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
      return new IfaMarginRepayOrderConfirmA001RequestModel(this.form)
    },
    customerName() {
      return this.customerInfo.customerNameKanji + '（' + this.customerInfo.customerNameKana + '）'
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
      }
      return false
    },
    buttonDisabled() {
      if (this.isPrivId) {
        return true
      }
      if (!this.isErrMsgs) {
        return false
      }
      return (this.isNotEmptyMsg(this.form.tradeNoticeInfoBrandMsg) && this.form.tradeNoticeInfoBrandConfirm !== '1') ||
          (this.isNotEmptyMsg(this.form.noticeInfoAlert) && this.form.noticeInfoAlertConfirm !== '1') ||
          (this.isNotEmptyMsg(this.form.noticeAlert) && this.form.noticeAlertConfirm !== '1') ||
          (this.isNotEmptyMsg(this.form.insiderConfirmMsg) && this.form.insiderErrorConfirm !== '1')
    },
    getUrlObject() {
      if (!this.form.request.brandCode) {
        return null
      }
      return this.form.request.brandCode.substring(0, 4)
    }
  },
  watch: {
    'form.request.tradeCd': {
      deep: true,
      handler(newValue) {
        // 信用返済買
        if (newValue === '5') {
          this.dialogStyle = 'backgroundColor: #fef0f0;'
          this.fontStyle = 'color: #E5004C;'
        } else {
        // 信用返済売
          this.dialogStyle = 'backgroundColor: #ecf5ff;'
          this.fontStyle = 'color: #00847F;'
        }
      }
    }
  },
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

      // 取引注意情報リンクの初期化
      this.$nextTick(() => {
        this.$refs['tradeLimitRef']?.trigger()
      })
    },
    // 戻るボタン
    backA002() {
      this.$emit('close-modal')
      this.form.tradeNoticeInfoBrandConfirm = '0'
      this.form.noticeInfoAlertConfirm = '0'
      this.form.noticeAlertConfirm = '0'
      this.form.insiderErrorConfirm = '0'
    },
    // 注文発注ボタン
    orderPlacementA001: function(data) {
      this.$emit('order-finish', data.dataList[0])
      this.form.tradeNoticeInfoBrandConfirm = '0'
      this.form.noticeInfoAlertConfirm = '0'
      this.form.noticeAlertConfirm = '0'
      this.form.insiderErrorConfirm = '0'
    },
    // 価格に表示する内容を生成する
    getPrice() {
      // 通常/逆指値.執行方法 : 指値
      if (this.form.request.sasinariHouhou === '1') {
        if (this.form.request.sasinariJyouken !== ' ') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.sasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.price)) + '円'
        }
        return this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.price)) + '円'
      }
      // 通常/逆指値.執行方法 : 成行
      if (this.form.request.sasinariHouhou === '2') {
        if (this.form.request.sasinariJyouken !== 'N') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.form.request.sasinariJyouken))
        }
      }
      // 通常/逆指値.執行方法 : 逆指値
      if (this.form.request.sasinariHouhou === '3') {
        let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.triggerPrice)) + '円' +
                  (this.form.request.tradeCd === '5' ? '以上' : '以下') + 'になった時点で'
        // 通常/逆指値.執行方法（逆指値）: 指値
        if (this.form.request.gyakusasiHouhou === '1') {
          if (this.form.request.sasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.sasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.price)) + '円で執行'
        // 通常/逆指値.執行方法（逆指値）: 成行
        } else if (this.form.request.gyakusasiHouhou === '2') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.form.request.sasinariJyouken)) + 'で執行'
        }
        return str
      }
      return ''
    },
    getOCO1Price() {
      if (this.form.request.oco1SasinariJyouken !== ' ') {
        return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.oco1SasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco1Price)) + '円'
      }
      return this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco1Price)) + '円'
    },
    getOCO2Price() {
      let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco2TriggerPrice)) + '円' +
                  (this.form.request.tradeCd === '5' ? '以上' : '以下') + 'になった時点でOCO1'
      if (this.form.request.oco1SasinariJyouken !== ' ') {
        str += '（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.oco1SasinariJyouken)) + '）を'
      } else {
        str += '（指値）を'
      }
      // OCO2.執行方法（逆指値）: 指値
      if (this.form.request.oco2GyakusasiHouhou === '1') {
        // OCO2.執行条件（逆指値）：≠条件なし
        if (this.form.request.oco2GyakusasiJyouken !== ' ') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.oco2GyakusasiJyouken)) + '/' +
          this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco2Price)) + '円に訂正'
        } else {
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco2Price)) + '円に訂正'
        }
        // OCO2.執行方法（逆指値）: 成行
      } else if (this.form.request.oco2GyakusasiHouhou === '2') {
        str += '成行に訂正'
      }
      return str
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
      }
      return true
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
.error-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
}
.warning-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 14px;
}
.info-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: #000;
  font-size: 14px;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
.required-mark {
  color: #ff2b2b;
  margin-right: 2px;
}
:deep(.el-checkbox__label) {
  padding-bottom: 0px;
  color: #f00;
  font-weight: bold;
}
:deep(.el-checkbox__input.is-checked) + .el-checkbox__label {
    color: #f00;
}
.buy-background-color_card {
  background-color: #fef0f0;
}
.sell-background-color_card {
  background-color: #ecf5ff;
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
