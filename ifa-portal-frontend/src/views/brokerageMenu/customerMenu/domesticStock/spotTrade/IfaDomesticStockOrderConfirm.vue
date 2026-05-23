<template>
  <!-- 国内株式注文受付確認ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :class="form.requestContents.tradeCd === '1' ? 'buy-background-color' : 'sell-background-color'"
    :title="form.title"
    width="1200px"
    :center="true"
    :append-to-body="appendToBody"
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
          id="btnBack"
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
      :info-messages="messages.infos"
    ></ifa-message-area>
    <!-- ヘッダ -->
    <!-- 顧客情報/口座情報 -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col
        :offset="1"
        :span="22"
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
        :span="22"
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

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- 注文内容(復唱項目) -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          v-if="form.requestContents.orderKind === '1' || form.requestContents.orderKind === '2'"
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
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>[銘柄コード]&nbsp;銘柄名</span>
            </el-col>
            <el-col
              :span="10"
              style="font-size: 16px;"
            >
              <span style="overflow-wrap: break-word;">[{{ $_out(form.requestContents.brandCode) }}]&nbsp;{{ $_out(form.brandName) }}</span>
            </el-col>
            <el-col
              v-if="form.tradeNoticeInfoBrandMsg"
              :span="5"
              style="text-align: center;"
            >
              <el-icon
                style="color: red; vertical-align: middle;"
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
              :span="9"
              class="_bold_black_m"
            >
              <span>注文種別</span>
            </el-col>
            <el-col
              style="font-size:16px;font-weight: bold;"
              :span="15"
            >
              <span v-if="form.requestContents.normalPriceLimitReverseSasinariHouhou === '3'">逆指値注文</span>
              <ifa-text
                v-if="form.requestContents.normalPriceLimitReverseSasinariHouhou !== '3'"
                code-list-id="ORDER_CLASS"
                :disp-pattern="2"
                :code-key="form.requestContents.orderKind"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                :style="form.requestContents.tradeCd === '1' ? 'color: #E5004C; font-weight: bold' : 'color: #00847F; font-weight: bold'"
                code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                :disp-pattern="1"
                :code-key="form.requestContents.tradeCd"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!form.requestContents.tradeCd">-</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>市場</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                code-list-id="SELECT_MARKET"
                :disp-pattern="1"
                :code-key="form.requestContents.market"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!form.requestContents.market">-</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受注数量</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ $_out($_withCommaInteger(form.requestContents.quantity)) }} 株</span>
            </el-col>
          </el-row>
          <el-row
            v-if="form.requestContents.orderKind === '1'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                code-list-id="EXECUTE_METHOD"
                :disp-pattern="1"
                :code-key="form.requestContents.normalPriceLimitReverseSasinariHouhou"
                style="font-size: 16px;"
              ></ifa-text><br v-if="getPrice() !== ''">
              <span>{{ getPrice() }}</span>
            </el-col>
          </el-row>
          <div v-if="form.requestContents.orderKind === '2'">
            <el-row
              class="dotted_border"
            >
              <el-col
                :span="9"
                class="_bold_black_m"
              >
                <span>価格／OCO1</span>
              </el-col>
              <el-col
                :span="15"
                style="font-size: 16px;"
              >
                <span style="font-size:16px;">
                  <ifa-text
                    code-list-id="EXECUTE_METHOD"
                    :disp-pattern="1"
                    code-key="1"
                    style="font-size: 16px;"
                  ></ifa-text></span><br>
                <span>{{ getOcoPrice() }}</span>
                <span>{{ $_out($_withCommaNoneZeroPadding(form.requestContents.oco1Price)) }}円</span>
              </el-col>
            </el-row>
            <el-row
              class="dotted_border"
            >
              <el-col
                :span="9"
                class="_bold_black_m"
              >
                <span>条件／OCO2</span>
              </el-col>
              <el-col
                :span="15"
                style="font-size: 16px;"
              >
                <span>{{ getOcoCondition() }}</span>
              </el-col>
            </el-row>
          </div>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>注文期間</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ form.requestContents.periodTerms ? `${$_out(form.requestContents.limit)}まで` : "当日中" }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>預り区分</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                code-list-id="DOMESTIC_DEPOSIT_TYPE"
                :disp-pattern="setTextDepositTypeDispPattern"
                :code-key="form.requestContents.depositType"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!form.requestContents.depositType">-</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>手数料区分</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                code-list-id="PRE_CONTRACT_DOC_CODE"
                :disp-pattern="1"
                :code-key="customerInfo.customerAttribute"
                style="font-size: 16px;"
              ></ifa-text>
              <span>{{ customerInfo.customerAttribute ? '（電話手数料）' : '-' }}</span>
            </el-col>
          </el-row>
        </el-card>

        <el-card
          v-if="form.requestContents.orderKind === '3' || form.requestContents.orderKind === '4'"
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
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem;padding-left:0.5rem"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>[銘柄コード]&nbsp;銘柄名</span>
            </el-col>
            <el-col
              :span="10"
              style="font-size: 16px;"
            >
              <span style="overflow-wrap: break-word;">[{{ $_out(form.requestContents.brandCode) }}]&nbsp;{{ $_out(form.brandName) }}</span>
            </el-col>
            <el-col
              v-if="form.tradeNoticeInfoBrandMsg"
              :span="5"
              style="text-align: center;"
            >
              <el-icon
                style="color: red; vertical-align: middle;"
              ><WarningFilled></WarningFilled></el-icon>
              <ifa-link
                ref="tradeLimitIfdRef"
                :url-id="16"
                :url-object="{ brandCode: getUrlObject }"
                :disp-name="'取引注意情報'"
              ></ifa-link>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>注文種別</span>
            </el-col>
            <el-col
              style="font-size:16px;font-weight: bold;"
              :span="15"
            >
              <span v-if="form.requestContents.normalPriceLimitReverseSasinariHouhou === '3'">逆指値注文</span>
              <ifa-text
                v-if="form.requestContents.normalPriceLimitReverseSasinariHouhou !== '3'"
                code-list-id="ORDER_CLASS"
                :disp-pattern="2"
                :code-key="form.requestContents.orderKind"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>

          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem;padding-left: 20%;"
          >
            <span class="_bold_black_m">IFD1</span>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                :style="form.requestContents.tradeCd === '1' ? 'color: #E5004C; font-weight: bold' : 'color: #00847F; font-weight: bold'"
                code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                :disp-pattern="1"
                :code-key="form.requestContents.tradeCd"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!form.requestContents.tradeCd">-</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>市場</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                code-list-id="SELECT_MARKET"
                :disp-pattern="1"
                :code-key="form.requestContents.market"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!form.requestContents.market">-</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受注数量</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ $_out($_withCommaInteger(form.requestContents.quantity)) }} 株</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >

              <ifa-text
                code-list-id="EXECUTE_METHOD"
                :disp-pattern="1"
                :code-key="form.requestContents.ifd1SasinariHouhou"
                style="font-size: 16px;"
              ></ifa-text><br v-if="getIfd1Price() !== ''">
              <span>{{ getIfd1Price() }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>注文期間</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ form.requestContents.periodTerms ? $_out(form.requestContents.limit) : "当日中" }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>預り区分</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                code-list-id="DOMESTIC_DEPOSIT_TYPE"
                :disp-pattern="setTextDepositTypeDispPattern"
                :code-key="form.requestContents.depositType"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!form.requestContents.depositType">-</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>手数料区分</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                code-list-id="PRE_CONTRACT_DOC_CODE"
                :disp-pattern="1"
                :code-key="customerInfo.customerAttribute"
                style="font-size: 16px;"
              ></ifa-text>
              <span>{{ customerInfo.customerAttribute ? '（電話手数料）' : '-' }}</span>
            </el-col>
          </el-row>
          <div
            class="_bold_black_m"
            style="padding-top: 0.5rem;padding-left: 20%;"
          >
            <span class="_bold_black_m">IFD2</span>
          </div>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                style="color: #00847F; font-weight: bold; font-size: 16px;"
                code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                :disp-pattern="1"
                code-key="2"
              ></ifa-text>
            </el-col>
          </el-row>

          <el-row
            v-if="form.requestContents.orderKind === '3'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span style="font-size:16px;">
                <ifa-text
                  code-list-id="EXECUTE_METHOD"
                  :disp-pattern="1"
                  :code-key="form.requestContents.ifd2SasinariHouhou"
                  style="font-size: 16px;"
                ></ifa-text></span>
              <br v-if="getIfd2Price() !== ''">
              <span>{{ getIfd2Price() }}</span>
            </el-col>
          </el-row>
          <div v-if="form.requestContents.orderKind === '4'">
            <el-row
              class="dotted_border"
            >
              <el-col
                :span="9"
                class="_bold_black_m"
              >
                <span>価格／OCO1</span>
              </el-col>
              <el-col
                :span="15"
                style="font-size: 16px;"
              >
                <span style="font-size:16px;">
                  <ifa-text
                    code-list-id="EXECUTE_METHOD"
                    :disp-pattern="1"
                    code-key="1"
                    style="font-size: 16px;"
                  ></ifa-text></span><br>
                <span>{{ getOcoPrice() }}</span>
                <span>{{ $_out($_withCommaNoneZeroPadding(form.requestContents.oco1Price)) }}円</span>
              </el-col>
            </el-row>
            <el-row
              class="dotted_border"
            >
              <el-col
                :span="9"
                class="_bold_black_m"
              >
                <span>条件／OCO2</span>
              </el-col>
              <el-col
                :span="15"
                style="font-size: 16px;"
              >
                <span>{{ getOcoIfdCondition() }}</span>
              </el-col>
            </el-row>
          </div>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>注文期間</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ form.requestContents.ifd2PeriodTerms ? $_out($_getFormattedDateValue(form.requestContents.ifd2Limit)) : "当日中" }}</span>
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
        <el-card class="box-card"
                 style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>その他注文内容</span>
          </el-row>
          <hr>
          <el-row v-if="form.selectedAccount !== ''"
                  class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>選択口座</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                code-list-id="ACCOUNT_TYPE"
                :disp-pattern="1"
                :code-key="form.requestContents.account"
                style="font-size: 16px;"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>見積単価</span>
            </el-col>
            <el-col
              :span="15"
            >
              <span>{{ $_out($_withCommaNoneZeroPadding(form.quoteUnitPrice)) }}円</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
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
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
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
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
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
          <el-row v-if="form.requestContents.tradeCd === '2'"
                  class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>讓渡益税</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ $_out($_withCommaInteger(form.yieldTax)) }}円</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
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
          <el-row v-if="form.requestContents.depositType === 'H'"
                  class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>{{ `注文後のNISA投資可能枠（${getDeliveryDateYear(form.deliveryDate)}年）` }}</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <span>{{ $_out($_withCommaInteger(form.investableQuote)) }}円</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
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
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
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
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
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
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
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
                :code-key="form.requestContents.kanyuKbn"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!form.requestContents.kanyuKbn">-</span>
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
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                code-list-id="ORDER_METHOD_TYPE"
                :disp-pattern="1"
                :code-key="form.requestContents.receiveOrderType"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!form.requestContents.receiveOrderType">-</span>
            </el-col>
          </el-row>
          <!-- 確認項目 -->
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>確認項目</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size: 16px;"
            >
              <ifa-text
                code-list-id="INSIDER_CONFIRM"
                :disp-pattern="2"
                code-key="1"
                style="font-size: 16px;"
              ></ifa-text><br v-if="form.requestContents.market === 'A'">
              <ifa-text
                v-if="form.requestContents.market === 'A'"
                code-list-id="SOR_CONFIRM"
                :disp-pattern="2"
                code-key="1"
                style="font-size: 16px;"
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
    <!-- <el-row v-if="form.orderInfo.messages.errors.length > 1"> -->
    <el-row
      v-if="form.tradeNoticeInfoBrandMsg ||
        form.complianceRankCheck.message ||
        form.noticeInfoAlert ||
        form.noticeAlert ||
        form.insiderConfirmMsg"
    >
      <el-col
        :span="22"
        :offset="1"
        style="color: #f00;"
      >
        <el-card
          :class="form.requestContents.tradeCd === '1' ? 'buy-background-color_card' : 'sell-background-color_card'"
          class="box-card alert_content"
          style="font-size:16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem; color: #f00;"
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
              style="padding-left: 0rem;"
            >
              <div class="required-mark">*</div><span style="color: #f00;">取引注意情報の説明</span>
            </el-col>
            <el-col :span="15"
                    style="font-size: 16px;"
            >
              <ifa-input-check
                v-model="form.alert.tradeNoticeInfoConfirm"
                style="margin-left: auto; color: #f00;"
                code-list-id="TRADE_NOTICE_INFO_EXPLAIN"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.complianceRankCheck.message"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
              style="padding-left: 0rem;"
            >
              <div class="required-mark">*</div>
              <span style="color: #f00;">
                <ifa-text
                  style="color: #f00; font-size: 16px;"
                  code-list-id="COMPLA_CHECK_BOX_WORDING"
                  :disp-pattern="3"
                  :code-key="form.complianceRankCheck.chkBoxLabel"
                ></ifa-text>
              </span>
            </el-col>
            <el-col :span="15"
                    style="font-size: 16px;"
            >
              <el-checkbox
                v-if="form.complianceRankCheck.chkBoxLabel === '1'"
                id="warningCheck"
                v-model="form.alert.confirm"
                name="warningCheck"
                label="△・◇ワーニング申請は「申請・承認済」であることを確認済"
                style="margin-left: auto; color: #f00;"
              ></el-checkbox>
              <el-checkbox
                v-if="form.complianceRankCheck.chkBoxLabel === '2'"
                id="warningCheck"
                v-model="form.alert.confirm"
                name="warningCheck"
                label="勧誘なし"
                style="margin-left: auto; color: #f00;"
              ></el-checkbox>
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
              style="padding-left: 0rem;"
            >
              <div class="required-mark">*</div><span style="color: #f00;">注意情報の確認</span>
            </el-col>
            <el-col :span="15"
                    style="font-size: 16px;"
            >
              <ifa-input-check
                v-model="form.alert.noticeInfoAlertConfirm"
                style="margin-left: auto; color: #f00;"
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
              style="padding-left: 0rem;"
            >
              <div class="required-mark">*</div><span style="color: #f00;">重要なお知らせの確認</span>
            </el-col>
            <el-col :span="15"
                    style="font-size: 16px;"
            >
              <ifa-input-check
                v-model="form.alert.noticeAlertConfirm"
                style="margin-left: auto; color: #f00;"
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
              <div class="required-mark">*</div>
              <div
                class="el-link el-link--primary"
                style="font-size: 16px; font-weight: bold; display: inline-block;text-decoration:underline;"
                @click="insiderErrorConfirmLinkA005"
              >内部者取引確認（知る前契約/計画の確認含む）
              </div>
            </el-col>
            <el-col :span="15"
                    style="font-size: 16px;"
            >
              <ifa-input-check
                v-model="form.alert.confirm1"
                style="margin-left: auto; color: #f00;"
                code-list-id="INSIDER_TRADE_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
                :disabled="isInsiderCheck"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="stockInfo.code === '6001'"
            class="dotted_border"
            style="padding-bottom: 8px;"
          >
            <el-col
              :offset="9"
              :span="15"
              style="font-size: 16px;"
            >
              <div style="height: 300px; border: 1px solid black;"></div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文発注ボタン -->
    <!-- <template #footer> -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnOrderPlacement"
          text="注文発注"
          :disabled="buttonDisabled"
          action-type="requestAction"
          :request-model="A001RequestModel"
          style="padding-left: 0;"
          action-id="SUB0202_0208-01_3#A001"
          @response-handler="orderPlacementA001($event)"
        ></ifa-button>
      </el-col>
    </el-row>
    <!-- </template> -->
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaDomesticStockOrderConfirmA001RequestModel } from './js/IfaDomesticStockOrderConfirmA001RequestModel'
import { IfaDomesticStockOrderConfirmFormModel } from './js/IfaDomesticStockOrderConfirmFormModel'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true },
    userInfo: { type: Object, required: true },
    customerInfo: { type: Object, required: true },
    stockInfo: { type: Object, required: true },
    selectedMarket: { type: String, required: true },
    appendToBody: { type: Boolean, required: true }
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
      form: new IfaDomesticStockOrderConfirmFormModel(),
      marketName: ['東証', '東証', '名証', '福証', '札証', '東証', '東証'],
      marketSym: ['東', '東', '名', '福', '札', '東', '東'],
      depositTypeStr: ['NISA', '特定', '一般'],
      limitOrderConditionOptions: ['', '寄指(Y)', '引指(H)', '不成(F)', 'IOC指(I)'],
      marketOrderConditionOptions: ['', '寄成(Y)', '引成(H)', 'IOC成(I)'],
      stopOrderCondition1Options: ['', '引指(H)', '不成(F)'],
      stopOrderCondition2Options: ['', '引成(H)'],
      stopOrderCondition3Options: ['', '引成(H)'],
      invitationTypeOptions: ['勧誘あり', '勧誘なし'],
      orderMethodOptions: ['店頭', '訪問', '', '電話他'],
      forms: {
        explained: false,
        warningCheck: false,
        invitationCheck: false,
        noteInfoCheck: false,
        noteLimitCheck: false,
        insiderCheck: false
      },
      styleSeparator: {
        paddingLeft: '90px',
        fontWeight: 'bold',
        paddingTop: '8px'
      },
      isInsiderCheck: true,

      messages: {
        mains: [],
        errors: [],
        infos: []

      },
      messageKey: 0
    }
  },
  computed: {
    userInformation() {
      return this.$store.getters.userAccount
    },
    A001RequestModel() {
      return new IfaDomesticStockOrderConfirmA001RequestModel(this.form)
    },
    buttonDisabled() {
      if (this.userInformation.medUsers.privId === '1' || this.userInformation.medUsers.privId === '2') {
        return true
      } else if ((this.form.tradeNoticeInfoBrandMsg ? this.form.alert.tradeNoticeInfoConfirm === '1' : true) &&
        (this.form.complianceRankCheck.message ? this.form.alert.confirm : true) &&
        (this.form.noticeInfoAlert ? this.form.alert.noticeInfoAlertConfirm === '1' : true) &&
        (this.form.noticeAlert ? this.form.alert.noticeAlertConfirm === '1' : true) &&
        (this.form.insiderConfirmMsg ? this.form.alert.confirm1 === '1' : true)) {
        return false
      } else {
        return true
      }
    },
    customerName() {
      return this.customerInfo.customerNameKanji + '（' + this.customerInfo.customerNameKana + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.customerInfo.accountNumber}`
    },
    hasJrNisaAccount() {
      return this.customerInfo.jrNisaAccountNumber !== ''
    },
    getOrderType() {
      if (this.form.orderType === '0') {
        return '通常注文'
      } else if (this.form.orderType === '1') {
        return 'OCO注文'
      } else if (this.form.orderType === '2') {
        return 'IFD注文'
      } else if (this.form.orderType === '3') {
        return 'IFDOCO注文'
      } else {
        return '通常注文'
      }
    },
    getUrlObject() {
      if (!this.form.requestContents.brandCode) {
        return null
      }
      return this.form.requestContents.brandCode.substring(0, 4)
    },
    showWarning() {
      // 文字列に「w」が含まれているかチェック
      if (this.form.code.includes('W')) {
        return true
      } else {
        return false
      }
    },
    setTextDepositTypeDispPattern() {
      // 顧客共通情報.ジュニアISA契約区分=1：契約
      if (this.customerInfo.jrIsaContractType === '1') {
        // 顧客共通情報.特定口座区分=1:特定口座(代行納付) または 2:特定口座(確定申告)
        if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
          return 1
        } else {
          return 8
        }
      } else {
        // 顧客共通情報.特定口座区分=1:特定口座(代行納付) または 2:特定口座(確定申告)
        if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
          return 2
        } else {
          return 9
        }
      }
    }
  },
  methods: {
    onShow() {
      this.form.alert.tradeNoticeInfoConfirm = '0'
      this.form.alert.confirm = false
      this.form.alert.noticeInfoAlertConfirm = '0'
      this.form.alert.noticeAlertConfirm = '0'
      this.form.alert.confirm1 = '0'
      this.isInsiderCheck = true
      Object.assign(this.form, this.formData)
      this.setMsg()

      // 取引注意情報リンクの初期化
      this.$nextTick(() => {
        this.$refs['tradeLimitRef']?.trigger()
        this.$refs['tradeLimitIfdRef']?.trigger()
      })
    },
    // 戻るボタン
    stopPropagation(event) {
      event.stopPropagation()
    },
    getDeliveryDateYear(deliveryDate) {
      return deliveryDate.substring(0, 4)
    },
    insiderErrorConfirmLinkA005() {
      this.isInsiderCheck = false
      const resolvedRoute = this.$router.resolve({ name: 'Ifa-Faq' })
      this.$store.commit('page/setFaqParam', '10001')
      window.open(resolvedRoute.href, '_blank')
      this.$store.commit('page/setFaqParam', '')
    },
    getAlertNoteInfoNum(data) {
      return data.filter(row => row.class === '米国株式売却停止')
    },
    tableCellStyle(row) {
      if (row.row.class === '米国株式売却停止') {
        return 'color: #D10014;'
      } else {
        return ''
      }
    },
    onDialogClose() {
      this.$emit('close-modal')
      this.forms.explained = false
      this.forms.warningCheck = false
      this.forms.invitationCheck = false
      this.forms.noteInfoCheck = false
      this.forms.noteLimitCheck = false
      this.forms.insiderCheck = false
    },
    orderPlacementA001(response) {
      this.confirmData = response.dataList[0]
      if (this.confirmData.requestContents.periodTerms === '1') {
        this.confirmData.requestContents.periodTerms = true
      } else {
        this.confirmData.requestContents.periodTerms = false
      }
      if (this.confirmData.requestContents.ifd2PeriodTerms === '1') {
        this.confirmData.requestContents.ifd2PeriodTerms = true
      } else {
        this.confirmData.requestContents.ifd2PeriodTerms = false
      }
      this.$emit('order-finish', this.confirmData)
      this.forms.explained = false
      this.forms.warningCheck = false
      this.forms.invitationCheck = false
      this.forms.noteInfoCheck = false
      this.forms.noteLimitCheck = false
      this.forms.insiderCheck = false
    },
    // 注文発注ボタン
    onOrderFinish() {
      const loading = this.$loading({
        lock: true,
        text: '国内株式注文受付完了中',
        // spinner: 'Loading',
        background: 'rgba(0, 0, 0, 0.7)',
        customClass: 'custom-loading-text'
      })
      setTimeout(() => {
        this.$emit('order-finish')
        this.forms.explained = false
        this.forms.warningCheck = false
        this.forms.invitationCheck = false
        this.forms.noteInfoCheck = false
        this.forms.noteLimitCheck = false
        this.forms.insiderCheck = false
        loading.close()
      }, 1000 + Math.floor(Math.random() * 5) * 500)
    },
    // 数値をコンマを加えて3桁区切りにする
    addComma(num) {
      return Number(num).toLocaleString()
    },
    // 価格に表示する内容を生成する
    getMethod() {
      if (this.form.method.method === '0') {
        return '指値'
      } else if (this.form.method.method === '1') {
        return '成行'
      } else if (this.form.method.method === '2') {
        return '逆指値'
      }
      return '-'
    },
    getOcoPrice() {
      if (this.form.requestContents.oco1SasinariJyouken !== ' ') {
        return this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.requestContents.oco1SasinariJyouken) + '/'
      } else {
        return
      }
    },
    getOcoCondition() {
      let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.oco2TriggerPrice)) + '円' +
                  (this.form.requestContents.tradeCd === '1' ? '以上' : '以下') + 'になった時点でOCO1'
      if (this.form.requestContents.oco1SasinariJyouken !== ' ') {
        str += '（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.requestContents.oco1SasinariJyouken)) + '）を'
      } else {
        str += '（指値）を'
      }

      // 指値
      if (this.form.requestContents.oco2GyakusasiHouhou === '1') {
        // 条件
        if (this.form.requestContents.oco2GyakusasiJyouken !== ' ') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.requestContents.oco2GyakusasiJyouken)) + '/' +
          this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.oco2Price)) + '円に訂正'
        } else {
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.oco2Price)) + '円に訂正'
        }
      } else if (this.form.requestContents.oco2GyakusasiHouhou === '2') {
        str += '成行に訂正'
      }
      return str
    },
    getOcoIfdCondition() {
      let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.oco2TriggerPrice)) + '円' +
                  '以下になった時点でOCO1'
      if (this.form.requestContents.oco1SasinariJyouken !== ' ') {
        str += '（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.requestContents.oco1SasinariJyouken)) + '）を'
      } else {
        str += '（指値）を'
      }

      // 指値
      if (this.form.requestContents.oco2GyakusasiHouhou === '1') {
        // 条件
        if (this.form.requestContents.oco2GyakusasiJyouken !== ' ') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.requestContents.oco2GyakusasiJyouken)) + '/' +
          this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.oco2Price)) + '円に訂正'
        } else {
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.oco2Price)) + '円に訂正'
        }
      } else if (this.form.requestContents.oco2GyakusasiHouhou === '2') {
        str += '成行に訂正'
      }
      return str
    },
    // 価格に表示する内容を生成する
    getPrice() {
      if (this.form.requestContents.normalPriceLimitReverseSasinariHouhou === '1') {
        if (this.form.requestContents.normalPriceLimitReverseSasinariJyouken !== ' ') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.requestContents.normalPriceLimitReverseSasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.normalPriceLimitReversePrice)) + '円'
        } else {
          return this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.normalPriceLimitReversePrice)) + '円'
        }
      } else if (this.form.requestContents.normalPriceLimitReverseSasinariHouhou === '2') {
        if (this.form.requestContents.normalPriceLimitReverseSasinariJyouken !== 'N') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.form.requestContents.normalPriceLimitReverseSasinariJyouken))
        }
      } else if (this.form.requestContents.normalPriceLimitReverseSasinariHouhou === '3') {
        let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.normalPriceLimitReverseTriggerPrice)) + '円' +
                  (this.form.requestContents.tradeCd === '1' ? '以上' : '以下') + 'になった時点で'
        if (this.form.requestContents.normalPriceLimitReverseGyakusasiHouhou === '1') {
          if (this.form.requestContents.normalPriceLimitReverseSasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.requestContents.normalPriceLimitReverseSasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.normalPriceLimitReversePrice)) + '円'
        } else if (this.form.requestContents.normalPriceLimitReverseGyakusasiHouhou === '2') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.form.requestContents.normalPriceLimitReverseSasinariJyouken))
        }
        return str + 'で執行'
      }
      return '-'
    },
    getIfd1Price() {
      if (this.form.requestContents.ifd1SasinariHouhou === '1') {
        if (this.form.requestContents.ifd1SasinariJyouken !== ' ') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.requestContents.ifd1SasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.ifd1Price)) + '円'
        } else {
          return this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.ifd1Price)) + '円'
        }
      } else if (this.form.requestContents.ifd1SasinariHouhou === '2') {
        if (this.form.requestContents.ifd1SasinariJyouken !== 'N') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.form.requestContents.ifd1SasinariJyouken))
        }
      } else if (this.form.requestContents.ifd1SasinariHouhou === '3') {
        let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.ifd1TriggerPrice)) + '円' +
                  (this.form.requestContents.tradeCd === '1' ? '以上' : '以下') + 'になった時点で'
        if (this.form.requestContents.ifd1GyakusasiHouhou === '1') {
          if (this.form.requestContents.ifd1SasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.requestContents.ifd1SasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.ifd1Price)) + '円'
        } else if (this.form.requestContents.ifd1GyakusasiHouhou === '2') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.form.requestContents.ifd1SasinariJyouken))
        }
        return str + 'で執行'
      }
      return '-'
    },
    getIfd2Price() {
      if (this.form.requestContents.ifd2SasinariHouhou === '1') {
        if (this.form.requestContents.ifd2SasinariJyouken !== ' ') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.requestContents.ifd2SasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.ifd2Price)) + '円'
        } else {
          return this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.ifd2Price)) + '円'
        }
      } else if (this.form.requestContents.ifd2SasinariHouhou === '2') {
        if (this.form.requestContents.ifd2SasinariJyouken !== 'N') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.form.requestContents.ifd2SasinariJyouken))
        }
      } else if (this.form.requestContents.ifd2SasinariHouhou === '3') {
        let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.ifd2TriggerPrice)) + '円' +
                  '以下になった時点で'
        if (this.form.requestContents.ifd2GyakusasiHouhou === '1') {
          if (this.form.requestContents.ifd2SasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.requestContents.ifd2SasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.requestContents.ifd2Price)) + '円'
        } else if (this.form.requestContents.ifd2GyakusasiHouhou === '2') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.form.requestContents.ifd2SasinariJyouken))
        }
        return str + 'で執行'
      }
      return '-'
    },
    setMsg() {
      this.messages.mains = []
      this.messages.errors = []
      this.messages.infos = []

      this.messages.mains.push('注文はまだ完了していません。画面下の注文発注ボタンを押下してください。')

      if (this.form.tradeNoticeInfoBrandMsg && this.form.tradeNoticeInfoBrandMsg.length > 0) {
        if (Array.isArray(this.form.tradeNoticeInfoBrandMsg)) {
          this.messages.errors.push(...this.form.tradeNoticeInfoBrandMsg)
        } else {
          this.messages.errors.push(this.form.tradeNoticeInfoBrandMsg)
        }
      }
      if (this.form.complianceRankCheck.message && this.form.complianceRankCheck.message.length > 0) {
        if (Array.isArray(this.form.complianceRankCheck.message)) {
          this.messages.errors.push(...this.form.complianceRankCheck.message)
        } else {
          this.messages.errors.push(this.form.complianceRankCheck.message)
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
      if (this.form.insiderConfirmMsg && this.form.insiderConfirmMsg.length > 0) {
        if (Array.isArray(this.form.insiderConfirmMsg)) {
          this.messages.errors.push(...this.form.insiderConfirmMsg)
        } else {
          this.messages.errors.push(this.form.insiderConfirmMsg)
        }
      }
      if (this.showWarning) {
        const EditErrMessage = this.form.errMessage + '（' + this.form.code + '）'
        this.messages.infos.push(EditErrMessage)
      }
      this.messageKey++
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
  .domestic_stock_order_cancel {
  .el-card__body {
    padding: 5px !important;
  }
}
</style>
<style lang="scss" scoped>
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
.error-message_title {
  margin: 0.5rem 0 0;
  padding: 0 3rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
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
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
.required-mark {
  display: inline-block;
  color: #ff2b2b;
  width: 8px;
}
:deep(.el-checkbox__label) {
  font-size: 16px;
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
.label-padding{
  padding: 0.1rem 0 0.1rem 0.5rem;
}
.data-padding {
  padding: 0.1rem;
}
.label-padding_other {
  padding: 0.3rem 0;
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
