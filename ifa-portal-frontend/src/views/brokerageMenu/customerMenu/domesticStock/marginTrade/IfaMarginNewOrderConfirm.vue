<template>
  <!-- 信用新規注文受付確認ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :style="{ 'background-color': bgColor }"
    :title="formModel.title"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
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
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>
    <!-- エラー/警告表示 -->
    <ifa-message-area
      :key="messageKey"
      :main-messages="messages.mains"
      :error-messages="messages.errors"
      :warning-messages="messages.warnings"
      :info-messages="messages.infos"
    ></ifa-message-area>

    <!-- 顧客情報/口座情報 -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col :offset="1">
        <span>{{ $_out(customerInfo.butenCode) + '-' + $_out($_zeroPadding(customerInfo.accountNumber,7)) }}</span>
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
        <span>{{ customerInfo.customerNameKanji }}（{{ customerInfo.customerNameKana }}）</span>
        <ifa-notice-info
          :notice-info-level="customerInfoA().noticeInfoLevel"
          :customer-code="customerInfoA().customerCode"
          :buten-code="customerInfoA().butenCode"
          :account-number="customerInfoA().accountNumber"
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
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              class="_bold_black_m"
              :span="9"
            >
              <span>[銘柄コード]&nbsp;銘柄名</span>
            </el-col>
            <el-col
              :span="10"
              style="font-size: 16px;"
            >
              <span style="overflow-wrap: break-word;">[{{ $_out(formModel.brandCode) }}]&nbsp;{{ $_out(formModel.brandName) }}</span>
            </el-col>
            <el-col
              v-if="formModel.tradeNoticeInfoBrandMsg && formModel.tradeNoticeInfoBrandMsg.length > 0"
              :span="5"
              style="text-align: center;"
            >
              <el-icon
                style="color: red;  vertical-align: middle;"
              ><WarningFilled></WarningFilled></el-icon>
              <ifa-link
                ref="tradeLimitRef"
                :url-id="16"
                :url-object="{ brandCode: getUrlObject }"
                :disp-name="'取引注意情報'"
              ></ifa-link>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col
              class="_bold_black_m"
              :span="9"
            >
              <span>注文種別</span>
            </el-col>
            <el-col
              style="font-size:16px;font-weight: bold"
              :span="15"
            >
              <span v-if="formModel.orderKind =='1' && formModel.sasinariHouhou == '3'">逆指値注文</span>
              <span v-else>{{ $_out($_getCodeValue('ORDER_CLASS', 2, formModel.orderKind )) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.orderKind =='3' || formModel.orderKind =='4'"
            class="_bold_black_m"
            style="padding-top: 0.5rem;padding-left: 20%;"
          >
            <el-col :span="12"><span>IFD1</span></el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              class="_bold_black_m"
              :span="9"
            >
              <span>取引種別</span>
            </el-col>
            <el-col :span="15">
              <span
                v-if="formModel.tradeCd == '3'"
                class="font-color__plus bold"
              >{{ $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, formModel.tradeCd )) }}
              </span>
              <span
                v-else
                class="font-color__minus bold"
              >{{ $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, formModel.tradeCd )) }}
              </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>市場</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('SELECT_MARKET', 1, formModel.market )) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受注数量</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_withCommaInteger(formModel.orderQuantity)) }}株</span>
            </el-col>
          </el-row>
          <!-- 通常/逆指値 -->
          <el-row
            v-if="formModel.orderKind == '1'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col :span="15">
              <span>
                {{ $_out($_getCodeValue('EXECUTE_METHOD', 1, formModel.sasinariHouhou )) }}
              </span>
              <el-col :span="16">
                <span>
                  {{ getJyoukenPrice(formModel.sasinariHouhou, formModel.sasinariJyouken, formModel.price, formModel.triggerPrice) }}
                </span>
              </el-col>
            </el-col>
          </el-row>
          <!-- OCO -->
          <el-row
            v-if="formModel.orderKind == '2'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>価格／OCO1</span>
            </el-col>
            <el-col :span="15">
              <span>
                {{ $_out($_getCodeValue('EXECUTE_METHOD', 1, formModel.oco1OrderExecuteMethodList )) }}
              </span>
              <el-col :span="16">
                <span>
                  {{ getOco1JyoukenPrice(formModel.oco1LimitExecutionConditionList, formModel.oco1DomesticLimitPrice) }}
                </span>
              </el-col>
            </el-col>
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>条件／OCO2</span>
            </el-col>
            <el-col :span="15">
              <span>
                {{ getOco2JyoukenPrice(formModel.oco2StopOrderExecuteMethodList, formModel.oco2StopOrderMarketExecutionConditionList, formModel.oco2DomesticLimitPrice, formModel.oco2DomesticStopOrderPrice) }}
              </span>
            </el-col>
          </el-row>
          <!-- IFD-IFD1 -->
          <el-row
            v-if="formModel.orderKind =='3' || formModel.orderKind =='4'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col :span="15">
              <span>
                {{ $_out($_getCodeValue('EXECUTE_METHOD', 1, formModel.ifd1OrderExecuteMethodList )) }}
              </span>
              <el-col :span="16">
                <span>
                  {{ getIfd1JyoukenPrice(formModel.ifd1OrderExecuteMethodList, formModel.ifd1LimitExecutionConditionList, formModel.ifd1DomesticLimitPrice, formModel.ifd1DomesticStopOrderPrice) }}
                </span>
              </el-col>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>注文期間</span>
            </el-col>
            <el-col :span="15">
              <span>{{ formModel.periodRadio === '0' ? "当日中" : $_getFormattedDateValue(formModel.limit) + "まで" }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>信用取引区分</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('PAYMENT_DEADLINE', 1, formModel.marginTradeTypeText )) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>手数料区分</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('PRE_CONTRACT_DOC_CODE', 1, customerInfo.customerAttribute)) }}（電話手数料）</span>
            </el-col>
          </el-row>
          <div v-if="formModel.orderKind =='3' || formModel.orderKind =='4'">
            <el-row
              v-if="formModel.orderKind =='3' || formModel.orderKind =='4'"
              class="_bold_black_m"
              style="padding-top: 0.5rem; padding-left: 20%;"
            >
              <el-col :span="12"><span>IFD2</span></el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col
                :span="9"
                class="_bold_black_m"
              >
                <span>取引種別</span>
              </el-col>
              <el-col :span="15">
                <span
                  v-if="formModel.tradeCd == '3'"
                >{{ $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, '6' )) }}
                </span>
                <span
                  v-else
                >{{ $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, '5' )) }}
                </span>
              </el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col
                :span="9"
                class="_bold_black_m"
              >
                <span v-if="formModel.orderKind == '4'">価格／OCO1</span>
                <span v-else-if="formModel.orderKind == '3'">価格</span>
              </el-col>
              <!-- IFDOCO -->
              <el-col
                v-if="formModel.orderKind == '4'"
                :span="15"
              >
                <span>
                  {{ $_out($_getCodeValue('EXECUTE_METHOD', 1, formModel.oco1OrderExecuteMethodList )) }}
                </span>
                <el-col :span="16">
                  <span>
                    {{ getOco1JyoukenPrice(formModel.oco1LimitExecutionConditionList, formModel.oco1DomesticLimitPrice) }}
                  </span>
                </el-col>
              </el-col>
              <el-col
                v-if="formModel.orderKind == '4'"
                :span="9"
                class="_bold_black_m"
              >
                <span>条件／OCO2</span>
              </el-col>
              <el-col
                v-if="formModel.orderKind == '4'"
                :span="15"
              >
                <span>
                  {{ getOco2JyoukenPrice(formModel.oco2StopOrderExecuteMethodList, formModel.oco2StopOrderMarketExecutionConditionList, formModel.oco2DomesticLimitPrice, formModel.oco2DomesticStopOrderPrice) }}
                </span>
              </el-col>
              <!-- IFD-IFD2 -->
              <el-col
                v-if="formModel.orderKind == '3'"
                :span="15"
              >
                <span>
                  {{ $_out($_getCodeValue('EXECUTE_METHOD', 1, formModel.ifd2OrderExecuteMethodList )) }}
                </span>
                <el-col :span="16">
                  <span>
                    {{ getIfd2JyoukenPrice(formModel.ifd2OrderExecuteMethodList, formModel.ifd2LimitExecutionConditionList, formModel.ifd2DomesticLimitPrice, formModel.ifd2DomesticStopOrderPrice) }}
                  </span>
                </el-col>
              </el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col
                :span="9"
                class="_bold_black_m"
              >
                <span>注文期間</span>
              </el-col>
              <el-col :span="15">
                <span>{{ formModel.ifd2PeriodRadio === '0' ? '当日中' : $_out($_getFormattedDateValue(formModel.ifd2Limit)) + "まで" }}</span>
              </el-col>
            </el-row>
          </div>
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
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>その他注文内容</span>
          </el-row>
          <hr>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>見積単価</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_withCommaNoneZeroPadding(formModel.quoteUnitPrice)) }}円</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>約定金額</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_withCommaInteger(formModel.contractAmount)) }}円</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>手数料/諸費用</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_withCommaInteger(formModel.charge)) }}円</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>消費税</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_withCommaInteger(formModel.consumptionTax)) }}円</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.tradeCd == '3'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>適用金利</span>
            </el-col>
            <el-col :span="15">
              <span>{{ "年利" + $_out($_withCommaZeroPadding(formModel.applicableInterestRate)) + "％" }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.tradeCd == '4'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>適用貸株料</span>
            </el-col>
            <el-col :span="15">
              <span>{{ "年利" + $_out($_withCommaZeroPadding(formModel.applicableStockLendingFees)) + "％" }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>精算金額</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_withCommaInteger(formModel.settlementAmount)) }}円</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>約定予定日</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getFormattedDateValue(formModel.contractDate)) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受渡予定日</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getFormattedDateValue(formModel.deliveryDate)) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受注日時</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getFormattedDateTimeValue(formModel.orderDayTime, 'datetime12')) }}</span>
            </el-col>
          </el-row>
          <!-- 勧誘区分 -->
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('INVITATION_TYPE', 1, formModel.kanyuKbn )) }}</span>
            </el-col>
          </el-row>
          <!-- 受注方法 -->
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受注方法</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('ORDER_METHOD_TYPE', 1, formModel.receiveOrderType )) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>確認項目</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('INSIDER_CONFIRM', 2, formModel.checkInsider )) }}</span>
              <el-col
                v-if="formModel.market == 'A'"
                :span="16"
              >
                <span>{{ $_out($_getCodeValue('SOR_CONFIRM', 2, formModel.checkSor )) }}</span>
              </el-col>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row v-if="isErrMsg()">
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          :class="formModel.tradeCd === '3' ? 'buy-background-color_card' : 'sell-background-color_card'"
          class="box-card alert_content"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; color: #f00; padding-left: 1rem;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <el-row
            v-if="formModel.tradeNoticeInfoBrandMsg && formModel.tradeNoticeInfoBrandMsg.length > 0"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_red_alart"
            >
              <div class="required-mark">*</div><span style="color: #f00;">取引注意情報の説明</span>
            </el-col>
            <el-col :span="15">
              <ifa-input-check
                id="explained"
                v-model="formModel.tradingCautionInformation"
                name="explained"
                style="margin-left: 5px; color: #f00;"
                :code-list-id="'TRADE_NOTICE_INFO_EXPLAIN'"
                :select-pattern="2"
                :disp-pattern="1"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.noticeInfoAlert && formModel.noticeInfoAlert.length > 0"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_red_alart"
            >
              <div class="required-mark">*</div><span style="color: #f00;">注意情報の確認</span>
            </el-col>
            <el-col :span="15">
              <ifa-input-check
                id="invitationCheck"
                v-model="formModel.noteInfoCheckbox"
                name="invitationCheck"
                style="margin-left: 5px; color: #f00;"
                :code-list-id="'NOTICE_INFO_CONFIRM'"
                :select-pattern="2"
                :disp-pattern="1"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.noticeAlert && formModel.noticeAlert.length > 0"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_red_alart"
            >
              <div class="required-mark">*</div><span style="color: #f00;">重要なお知らせの確認</span>
            </el-col>
            <el-col :span="15">
              <ifa-input-check
                id="invitationCheck"
                v-model="formModel.noteLimitCheck"
                name="invitationCheck"
                style="margin-left: 5px; color: #f00;"
                :code-list-id="'IMPORTANT_NOTIFICATION_CONFIRM'"
                :select-pattern="2"
                :disp-pattern="1"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.insiderErrorMsg && formModel.insiderErrorMsg.length > 0"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_red_alart"
              style="display: flex; align-items: flex-start;"
            >
              <div class="required-mark">*</div>
              <div
                class="inner-link"
                @click="insiderErrorConfirmLinkA005"
              >内部者取引確認（知る前契約/計画の確認含む）
              </div>
            </el-col>
            <el-col :span="15">
              <ifa-input-check
                id="insiderCheck"
                v-model="formModel.insiderErrorConfirmationCheckbox"
                name="insiderCheck"
                style="margin-left: 5px; color: #f00;"
                :code-list-id="'INSIDER_TRADE_CONFIRM'"
                :select-pattern="2"
                :disp-pattern="1"
                :disabled="isDisabled"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.shortSellingRegulationConflictMessage && formModel.shortSellingRegulationConflictMessage.length > 0"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_red_alart"
              style="margin-top:7px"
            >
              <div class="required-mark">*</div><span style="color: #f00;">空売り規制取引</span>
            </el-col>
            <el-col :span="15">
              <ifa-input-check
                id="shortSale"
                v-model="formModel.shortSellingRegulationsCheckbox"
                name="insiderCheck"
                style="margin-left: 5px; color: #f00;"
                :code-list-id="'SHORT_SELLING_REGULATION_CONFIRM'"
                :select-pattern="1"
                :disp-pattern="2"
              ></ifa-input-check>
              <el-row style="font-weight: bold; color: #f00; margin-left: 22px; padding-right: 50px; line-height: normal; top: -11px;">
                【トークフロー】新規売り注文が空売り規制に抵触した場合、注文は取引所にて「失効」されます。よろしいでしょうか。</el-row>
              <div style="margin-left: 14px; top: -11px; position: relative;">
                <ifa-link
                  :param-url="'https://search.sbisec.co.jp/v2/popwin/attention/trading/stock_13.html'"
                  :disp-name="'空売り規制についてはこちら'"
                  link-icon-image="externalLink"
                ></ifa-link>
              </div>
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
          text="注文発注"
          color="primary"
          :disabled="editDisable"
          action-type="requestAction"
          action-id="SUB0202_0212-01_2#A001"
          style="padding-left: 0;"
          :request-model="IfaMarginNewOrderConfirmA001RequestModel"
          @response-handler="responseHandlerA001"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaLink from '@/components/Link/IfaLink'
import { IfaMarginNewOrderConfirmA001RequestModel } from './js/IfaMarginNewOrderConfirmA001RequestModel'
import { IfaMarginNewOrderCompleteFormModel } from './js/IfaMarginNewOrderCompleteFormModel'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { notifyWrapper } from '@/utils/errorHandler'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
export default {
  components: {
    IfaLink,
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formModel: { type: Object, required: true },
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
      completeFormModel: new IfaMarginNewOrderCompleteFormModel(),
      // 品質改善レビュー指摘No214対応
      isDisabled: true,
      messages: {
        mains: [],
        errors: [],
        warnings: [],
        infos: []
      },
      messageKey: 0
    }
  },
  computed: {
    getUrlObject() {
      if (!this.formModel.brandCode) {
        return null
      }
      return this.formModel.brandCode.substring(0, 4)
    },
    IfaMarginNewOrderConfirmA001RequestModel() {
      return new IfaMarginNewOrderConfirmA001RequestModel(this.formModel)
    },
    bgColor() {
      return this.formModel.tradeCd === '3' ? '#fef0f0' : '#ecf5ff'
    },
    infoIcon() {
      return this.customerInfo.noticeInfoLevel === '3' ? 'alert-icon' : 'notice-icon'
    },

    editDisable() {
      return ((this.formModel.tradeNoticeInfoBrandMsg && this.formModel.tradeNoticeInfoBrandMsg.length > 0 && this.formModel.tradingCautionInformation !== '1') ||
      (this.formModel.noticeInfoAlert && this.formModel.noticeInfoAlert.length > 0 && this.formModel.noteInfoCheckbox !== '1') ||
      (this.formModel.noticeAlert && this.formModel.noticeAlert.length > 0 && this.formModel.noteLimitCheck !== '1') ||
      (this.formModel.insiderErrorMsg && this.formModel.insiderErrorMsg.length > 0 && this.formModel.insiderErrorConfirmationCheckbox !== '1') ||
      (this.formModel.shortSellingRegulationConflictMessage && this.formModel.shortSellingRegulationConflictMessage.length > 0 && this.formModel.shortSellingRegulationsCheckbox !== '1')) ||
      (this.$store.getters.userAccount && this.$store.getters.userAccount.medUsers.privId === '1' || this.$store.getters.userAccount.medUsers.privId === '2')
    }
  },
  methods: {
    setData() {
      this.messages.mains = []
      this.messages.errors = []
      this.messages.warnings = []
      this.messages.infos = []

      this.messages.mains.push(this.formModel.warnMessage)

      if (this.formModel.tradeNoticeInfoBrandMsg && this.formModel.tradeNoticeInfoBrandMsg.length > 0) {
        if (Array.isArray(this.formModel.tradeNoticeInfoBrandMsg)) {
          this.messages.errors.push(...this.formModel.tradeNoticeInfoBrandMsg)
        } else {
          this.messages.errors.push(this.formModel.tradeNoticeInfoBrandMsg)
        }
      }
      if (this.formModel.noticeInfoAlert && this.formModel.noticeInfoAlert.length > 0) {
        if (Array.isArray(this.formModel.noticeInfoAlert)) {
          this.messages.errors.push(...this.formModel.noticeInfoAlert)
        } else {
          this.messages.errors.push(this.formModel.noticeInfoAlert)
        }
      }
      if (this.formModel.noticeAlert && this.formModel.noticeAlert.length > 0) {
        if (Array.isArray(this.formModel.noticeAlert)) {
          this.messages.errors.push(...this.formModel.noticeAlert)
        } else {
          this.messages.errors.push(this.formModel.noticeAlert)
        }
      }
      if (this.formModel.insiderErrorMsg && this.formModel.insiderErrorMsg.length > 0) {
        if (Array.isArray(this.formModel.insiderErrorMsg)) {
          this.messages.errors.push(...this.formModel.insiderErrorMsg)
        } else {
          this.messages.errors.push(this.formModel.insiderErrorMsg)
        }
      }
      if (this.formModel.shortSellingRegulationConflictMessage && this.formModel.shortSellingRegulationConflictMessage.length > 0) {
        if (Array.isArray(this.formModel.shortSellingRegulationConflictMessage)) {
          this.messages.errors.push(...this.formModel.shortSellingRegulationConflictMessage)
        } else {
          this.messages.errors.push(this.formModel.shortSellingRegulationConflictMessage)
        }
      }
      if (this.formModel.code.startsWith('W') && this.formModel.errMessage && this.formModel.errMessage.length > 0) {
        const EditErrMessage = this.formModel.errMessage + '（' + this.formModel.code + '）'
        this.messages.infos.push(EditErrMessage)
      }
      this.messageKey++
      this.formModel.tradingCautionInformation = '0'
      this.formModel.noteInfoCheckbox = '0'
      this.formModel.noteLimitCheck = '0'
      this.formModel.insiderErrorConfirmationCheckbox = '0'
      this.formModel.shortSellingRegulationsCheckbox = '0'
      this.isDisabled = true

      // 取引注意情報リンクの初期化
      this.$nextTick(() => {
        this.$refs['tradeLimitRef']?.trigger()
      })
    },
    isErrMsg() {
      if ((this.formModel.tradeNoticeInfoBrandMsg && this.formModel.tradeNoticeInfoBrandMsg.length > 0) ||
        (this.formModel.noticeInfoAlert && this.formModel.noticeInfoAlert.length > 0) ||
        (this.formModel.noticeAlert && this.formModel.noticeAlert.length > 0) ||
        (this.formModel.insiderErrorMsg && this.formModel.insiderErrorMsg.length > 0) ||
        (this.formModel.shortSellingRegulationConflictMessage && this.formModel.shortSellingRegulationConflictMessage.length > 0)) {
        return true
      } else {
        return false
      }
    },
    responseHandlerA001(response) {
      this.completeFormModel = Object.assign(this.completeFormModel, response.dataList[0])
      this.completeFormModel.customerAttributeName = this.customerInfo.customerAttributeName
      this.$emit('order-finish', this.completeFormModel)
    },
    errorHandler(response) {
      notifyWrapper({
        title: this.formModel.title,
        message: response.message,
        type: 'warning'
      })
    },
    // Infoアイコンイベント
    stopPropagation(event) {
      event.stopPropagation()
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 価格に表示する内容を生成する
    getPrice(jyouken, price) {
      if (jyouken === ' ') { // 通常/逆指値.執行条件=条件なし
        return '/' + this.$_withCommaNoneZeroPadding(price) + '円'
      } else if (jyouken !== ' ') { // 通常/逆指値.執行条件≠条件なし
        return ''
      }
    },
    getJyoukenPrice(houhou, jyouken, price, trigger) {
      if (houhou === '1') { // 通常/逆指値.執行方法=指値
        if (jyouken !== ' ') {
          return this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + '/' + this.$_withCommaNoneZeroPadding(price) + '円'
        } else {
          return this.$_withCommaNoneZeroPadding(price) + '円'
        }
      } else if (houhou === '2') { // 通常/逆指値.執行方法=成行
        if (jyouken !== 'N') {
          return this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, jyouken)
        }
      } else if (houhou === '3') { // 通常/逆指値.執行方法=逆指値
        return '現在値が' + this.$_withCommaNoneZeroPadding(trigger) + '円' + (this.formModel.tradeCd === '3' ? '以上' : '以下') + 'になった時点で' +
        (this.formModel.gyakusasiHouhou === '1'
          ? (jyouken === ' ' ? this.$_withCommaNoneZeroPadding(price) + '円' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + this.$_withCommaNoneZeroPadding(price) + '円')
          : (jyouken === ' ' ? '' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, jyouken))) + 'で執行'
      }
    },
    getOco1JyoukenPrice(jyouken, price) {
      if (jyouken !== ' ') {
        return this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + '/' + this.$_withCommaNoneZeroPadding(price) + '円'
      } else {
        return this.$_withCommaNoneZeroPadding(price) + '円'
      }
    },
    getOco2JyoukenPrice(houhou, jyouken, price, trigger) {
      return '現在値が' + this.$_withCommaNoneZeroPadding(trigger) + '円' +
      (this.formModel.tradeCd === '3' ? '以上' : '以下') + 'になった時点で' +
      (this.formModel.oco1LimitExecutionConditionList === ' ' ? 'OCO1(指値)を' : 'OCO1(' + this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formModel.oco1LimitExecutionConditionList) + ')を') +
      (houhou === '1' ? (jyouken === ' ' ? this.$_withCommaNoneZeroPadding(price) + '円' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + '/' + this.$_withCommaNoneZeroPadding(price) + '円') : '') +
      (houhou === '2' ? '成行' : '') + 'に訂正'
    },
    getIfd1JyoukenPrice(houhou, jyouken, price, trigger) {
      if (houhou === '1') { // 通常/逆指値.執行方法=指値
        if (jyouken !== ' ') {
          return this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + '/' + this.$_withCommaNoneZeroPadding(price) + '円'
        } else {
          return this.$_withCommaNoneZeroPadding(price) + '円'
        }
      } else if (houhou === '2') { // 通常/逆指値.執行方法=成行
        if (jyouken !== 'N') {
          return this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, jyouken)
        }
      } else if (houhou === '3') { // 通常/逆指値.執行方法=逆指値
        return '現在値が' + this.$_withCommaNoneZeroPadding(trigger) + '円' + (this.formModel.tradeCd === '3' ? '以上' : '以下') + 'になった時点で' +
        (this.formModel.ifd1StopOrderExecuteMethodList === '1'
          ? (jyouken === ' ' ? this.$_withCommaNoneZeroPadding(price) + '円' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + this.$_withCommaNoneZeroPadding(price) + '円')
          : (jyouken === ' ' ? '' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, jyouken))) + 'で執行'
      }
    },
    getIfd2JyoukenPrice(houhou, jyouken, price, trigger) {
      if (houhou === '1') { // 通常/逆指値.執行方法=指値
        if (jyouken !== ' ') {
          return this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + '/' + this.$_withCommaNoneZeroPadding(price) + '円'
        } else {
          return this.$_withCommaNoneZeroPadding(price) + '円'
        }
      } else if (houhou === '2') { // 通常/逆指値.執行方法=成行
        if (jyouken !== 'N') {
          return this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, jyouken)
        }
      } else if (houhou === '3') { // 通常/逆指値.執行方法=逆指値
        return '現在値が' + this.$_withCommaNoneZeroPadding(trigger) + '円' + (this.formModel.tradeCd === '3' ? '以上' : '以下') + 'になった時点で' +
        (this.formModel.ifd2StopOrderExecuteMethodList === '1'
          ? (jyouken === ' ' ? this.$_withCommaNoneZeroPadding(price) + '円' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + this.$_withCommaNoneZeroPadding(price) + '円')
          : (jyouken === ' ' ? '' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, jyouken))) + 'で執行'
      }
    },
    insiderErrorConfirmLinkA005() {
      const resolvedRoute = this.$router.resolve({ name: 'Ifa-Faq' })
      this.$store.commit('page/setFaqParam', '10001')
      window.open(resolvedRoute.href, '_blank')
      this.$store.commit('page/setFaqParam', '')
      // 品質改善レビュー指摘No214対応
      this.isDisabled = false
    },
    customerInfoA() {
      return this.$store.getters.customerInfo
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
@import "@/styles/variables.scss";
.notice-icon {
  font-size: 22px;
  color: $subBlueColor;
  animation: poyopoyo 2.0s 1 normal;
}
.alert-icon {
  font-size: 22px;
  color: $errorColor;
  animation: poyopoyo 2.0s 1 normal;
}
@keyframes poyopoyo {
  0%, 40%, 60%, 80% {
    transform: scale(1.0);
  }
  50%, 70% {
    transform: scale(0.80);
  }
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
.buy-background-color_card {
  background-color: #fef0f0;
}
.sell-background-color_card {
  background-color: #ecf5ff;
}
._bold_red_alart {
  font-size: 16px;
  font-weight: bold;
  padding-right: 0.5rem;
  color: red;
}
.inner-link {
  vertical-align: middle;
  display: inline-block;
  cursor: pointer;
  color: #092987;
  text-decoration: underline;
  &:hover {
    opacity: 0.7;
      }
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
