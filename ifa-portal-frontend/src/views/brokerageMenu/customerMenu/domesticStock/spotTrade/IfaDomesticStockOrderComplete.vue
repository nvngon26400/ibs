<template>
  <!-- 国内株式注文受付完了ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title"
    class="domestic_stock_order_comp"
    width="1200px"
    :center="true"
    :append-to-body="appendToBody"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    :style="dialogStyle"
    @open="onOpen"
  >

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="['下記の内容で注文を受け付けました。']"
    ></ifa-message-area>

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

    <!-- 注文受付内容 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          v-if="formData.requestContents.orderKind === '1' || formData.requestContents.orderKind === '2'"
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding: 0.5rem;padding-left: 1rem;;"
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
            <el-col
              :span="10"
              style="font-size:16px"
            >
              <span style="overflow-wrap: break-word;">[{{ $_out(formData.requestContents.brandCode) }}]&nbsp;{{ $_out(formData.requestContents.brandName) }}</span>
            </el-col>
            <el-col
              v-if="formData.requestContents.tradeNoticeInfoBrandMsg"
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
              :span="15"
              style="font-size:16px"
            >
              <span v-if="formData.requestContents.sasinariHouhou === '3'">逆指値注文</span>
              <ifa-text
                v-if="formData.requestContents.sasinariHouhou !== '3'"
                code-list-id="ORDER_CLASS"
                :disp-pattern="2"
                :code-key="formData.requestContents.orderKind"
                style="font-size:16px;font-weight: bold;"
              ></ifa-text>
              <span v-if="!formData.requestContents.orderKind">-</span>
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
              style="font-size:16px"
            >
              <ifa-text
                :style="formData.requestContents.tradeCd === '1' ? 'color: #E5004C; font-weight: bold' : 'color: #00847F; font-weight: bold'"
                code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                :disp-pattern="1"
                :code-key="formData.requestContents.tradeCd"
                style="font-size:16px;"
              ></ifa-text>
              <span v-if="!formData.requestContents.tradeCd">-</span>
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="SELECT_MARKET"
                :disp-pattern="1"
                :code-key="formData.requestContents.market"
                style="font-size:16px;"
              ></ifa-text>
              <span v-if="!formData.requestContents.market">-</span>
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
              style="font-size:16px"
            >
              <span>{{ $_out($_withCommaZeroPadding(formData.requestContents.quantity)) }} 株</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.requestContents.orderKind === '1'"
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
            >
              <ifa-text
                code-list-id="EXECUTE_METHOD"
                :disp-pattern="1"
                :code-key="formData.requestContents.sasinariHouhou"
                style="font-size:16px;"
              ></ifa-text><br v-if="getPrice() !== ''">
              <span>{{ getPrice() }}</span>
            </el-col>
          </el-row>
          <div v-if="formData.requestContents.orderKind === '2'">
            <el-row
              class=" dotted_border"
            >
              <el-col
                :span="9"
                class="_bold_black_m"
              >
                <span>価格／OCO1</span>
              </el-col>
              <el-col
                :span="15"
                style="font-size:16px"
              >
                <ifa-text
                  code-list-id="EXECUTE_METHOD"
                  :disp-pattern="1"
                  code-key="1"
                  style="font-size:16px;"
                ></ifa-text><br>
                <span>{{ getOcoPrice() }}</span>
                <span>{{ $_out($_withCommaNoneZeroPadding(formData.requestContents.oco1Price)) }}円</span>
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
                style="font-size:16px"
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
              style="font-size:16px"
            >
              <span>{{ formData.requestContents.periodTerms ? `${$_out(formData.requestContents.limit)}まで` : "当日中" }}</span>
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="DOMESTIC_DEPOSIT_TYPE"
                :disp-pattern="setTextDepositTypeDispPattern"
                :code-key="formData.requestContents.depositType"
                style="font-size:16px;"
              ></ifa-text>
              <span v-if="!formData.requestContents.depositType">-</span>
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="PRE_CONTRACT_DOC_CODE"
                :disp-pattern="1"
                :code-key="customerInfo.customerAttribute"
                style="font-size:16px;"
              ></ifa-text>
              <span>{{ customerInfo.customerAttribute ? '（電話手数料）' : '-' }}</span>
            </el-col>
          </el-row>
        </el-card>
        <el-card
          v-if="formData.requestContents.orderKind === '3' || formData.requestContents.orderKind === '4'"
          class="box-card"
          style="font-size:16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding: 0.5rem;padding-left:1rem"
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
            <el-col
              :span="10"
              style="font-size:16px"
            >
              <span style="overflow-wrap: break-word;">[{{ $_out(formData.requestContents.brandCode) }}]&nbsp;{{ $_out(formData.requestContents.brandName) }}</span>
            </el-col>
            <el-col
              v-if="formData.requestContents.tradeNoticeInfoBrandMsg"
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
          <el-row class="_ dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>注文種別</span>
            </el-col>
            <el-col
              :span="15"
            >
              <span v-if="formData.requestContents.sasinariHouhou === '3'">逆指値注文</span>
              <ifa-text
                v-if="formData.requestContents.sasinariHouhou !== '3'"
                code-list-id="ORDER_CLASS"
                :disp-pattern="2"
                :code-key="formData.requestContents.orderKind"
                style="font-size:16px;font-weight: bold"
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
              style="font-size:16px"
            >
              <ifa-text
                :style="formData.requestContents.tradeCd === '1' ? 'color: #E5004C; font-weight: bold' : 'color: #00847F; font-weight: bold'"
                code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                :disp-pattern="1"
                :code-key="formData.requestContents.tradeCd"
                style="font-size:16px;"
              ></ifa-text>
              <span v-if="!formData.requestContents.tradeCd">-</span>
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="SELECT_MARKET"
                :disp-pattern="1"
                :code-key="formData.requestContents.market"
                style="font-size:16px;"
              ></ifa-text>
              <span v-if="!formData.requestContents.market">-</span>
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
              style="font-size:16px"
            >
              <span>{{ $_out($_withCommaZeroPadding(formData.requestContents.quantity)) }} 株</span>
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
            >
              <ifa-text
                code-list-id="EXECUTE_METHOD"
                :disp-pattern="1"
                :code-key="formData.requestContents.ifd1SasinariHouhou"
                style="font-size:16px;"
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
              style="font-size:16px"
            >
              <span>{{ formData.requestContents.periodTerms ? $_out(formData.requestContents.limit) : "当日中" }}</span>
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="DOMESTIC_DEPOSIT_TYPE"
                :disp-pattern="setTextDepositTypeDispPattern"
                :code-key="formData.requestContents.depositType"
                style="font-size:16px;"
              ></ifa-text>
              <span v-if="!formData.requestContents.depositType">-</span>
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="PRE_CONTRACT_DOC_CODE"
                :disp-pattern="1"
                :code-key="customerInfo.customerAttribute"
                style="font-size:16px;"
              ></ifa-text>
              <span>{{ customerInfo.customerAttribute ? '（電話手数料）' : '-' }}</span>
            </el-col>
          </el-row>
          <div
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
              style="font-size:16px"
            >
              <ifa-text
                style="color: #00847F; font-weight: bold; font-size:16px;"
                code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                :disp-pattern="1"
                code-key="2"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.requestContents.orderKind === '3'"
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="EXECUTE_METHOD"
                :disp-pattern="1"
                :code-key="formData.requestContents.ifd2SasinariHouhou"
                style="font-size:16px;"
              ></ifa-text><br v-if="getIfd2Price() !== ''">
              <span>{{ getIfd2Price() }}</span>
            </el-col>
          </el-row>
          <div v-if="formData.requestContents.orderKind === '4'">
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
                style="font-size:16px"
              >
                <ifa-text
                  code-list-id="EXECUTE_METHOD"
                  :disp-pattern="1"
                  code-key="1"
                  style="font-size:16px;"
                ></ifa-text><br>
                <span>{{ getOcoPrice() }}</span>
                <span>{{ $_out($_withCommaNoneZeroPadding(formData.requestContents.oco1Price)) }}円</span>
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
                style="font-size:16px"
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
              style="font-size:16px"
            >
              <span>{{ formData.requestContents.ifd2PeriodTerms ? $_out(formData.requestContents.ifd2Limit) : "当日中" }}</span>
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
          <el-row
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="ACCOUNT_TYPE"
                :disp-pattern="1"
                :code-key="formData.requestContents.account"
                style="font-size:16px;"
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
              style="font-size:16px"
            >
              <span>{{ $_out($_withCommaNoneZeroPadding(formData.quoteUnitPrice)) }}円</span>
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
              style="font-size:16px"
            >
              <span>{{ $_out($_withCommaInteger(formData.contractAmount)) }}円</span>
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
              style="font-size:16px"
            >
              <span>{{ $_out($_withCommaInteger(formData.charge)) }}円</span>
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
              style="font-size:16px"
            >
              <span>{{ $_out($_withCommaInteger(formData.consumptionTax)) }}円</span>
            </el-col>
          </el-row>
          <el-row v-if="formData.requestContents.tradeCd !== '1'"
                  class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>譲渡益税</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size:16px"
            >
              <span>{{ $_out($_withCommaInteger(formData.yieldTax)) }}円</span>
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
              style="font-size:16px"
            >
              <span>{{ $_out($_withCommaInteger(formData.settlementAmount)) }}円</span>
            </el-col>
          </el-row>
          <el-row v-if="formData.requestContents.depositType === 'H'"
                  class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>{{ `注文後のNISA投資可能枠（${getDeliveryDateYear(formData.deliveryDate)}年）` }}</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size:16px"
            >
              <span>{{ $_out($_withCommaInteger(formData.investableAmount)) }}円</span>
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
              style="font-size:16px"
            >
              <span>{{ $_out($_getFormattedDateValue(formData.contractDate)) }}</span>
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
              style="font-size:16px"
            >
              <span>{{ $_out($_getFormattedDateValue(formData.deliveryDate)) }}</span>
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
            >
              <span>{{ $_out($_getFormattedDateTimeValue(formData.orderDayTime, 'datetime12')) }}</span>
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="INVITATION_TYPE"
                :disp-pattern="1"
                :code-key="formData.requestContents.kanyuKbn"
                style="font-size:16px;"
              ></ifa-text>
              <span v-if="!formData.requestContents.kanyuKbn">-</span>
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="ORDER_METHOD_TYPE"
                :disp-pattern="1"
                :code-key="formData.requestContents.ordersHouhou"
                style="font-size:16px;"
              ></ifa-text>
              <span v-if="!formData.requestContents.ordersHouhou">-</span>
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="INSIDER_CONFIRM"
                :disp-pattern="2"
                code-key="1"
                style="font-size:16px;"
              ></ifa-text><br v-if="formData.requestContents.market === 'A'">
              <ifa-text
                v-if="formData.requestContents.market === 'A'"
                code-list-id="SOR_CONFIRM"
                :disp-pattern="2"
                code-key="1"
                style="font-size:16px;"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>EC受注番号</span>
            </el-col>
            <el-col
              :span="15"
              style="font-size:16px"
            >
              <span>{{ formData.ecOrderNo }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row v-if="formData.requestContents.tradeNoticeInfoBrandMsg ||
      formData.requestContents.complianceRankCheckMsg ||
      formData.requestContents.noticeInfoAlert ||
      formData.requestContents.noticeAlert ||
      formData.requestContents.insiderConfirmMsg"
    >
      <el-col
        :span="22"
        :offset="1"
        style="color: #f00;"
      >
        <el-card
          class="box-card"
          style="font-size:16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <el-row
            v-if="formData.requestContents.tradeNoticeInfoBrandMsg"
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="TRADE_NOTICE_INFO_EXPLAIN"
                :disp-pattern="1"
                code-key="1"
                style="font-size:16px;"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.requestContents.complianceRankCheckMsg"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>
                <ifa-text
                  code-list-id="COMPLA_CHECK_BOX_WORDING"
                  :disp-pattern="3"
                  :code-key="formData.requestContents.checkBoxWording"
                  style="font-size:16px;"
                ></ifa-text>
              </span>
            </el-col>
            <el-col :span="15"
                    style="font-size:16px"
            >
              <ifa-text
                code-list-id="COMPLA_CHECK_BOX_WORDING"
                :disp-pattern="1"
                :code-key="formData.requestContents.checkBoxWording"
                style="font-size:16px;"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.requestContents.noticeInfoAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>注意情報の確認</span>
            </el-col>
            <el-col :span="15">
              <ifa-text
                code-list-id="NOTICE_INFO_CONFIRM"
                :disp-pattern="1"
                code-key="1"
                style="font-size:16px;"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.requestContents.noticeAlert"
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
              style="font-size:16px"
            >
              <ifa-text
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                :disp-pattern="1"
                code-key="1"
                style="font-size:16px;"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.requestContents.insiderConfirmMsg"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>内部者取引確認（知る前契約/計画の確認含む）</span>
            </el-col>
            <el-col :span="15"
                    style="font-size:16px"
            >
              <ifa-text
                code-list-id="INSIDER_TRADE_CONFIRM"
                :disp-pattern="1"
                code-key="1"
                style="font-size:16px;"
              ></ifa-text>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文状況一覧へボタン -->

    <el-row
      style="margin-top: 20px;"
    >
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnToPortfolio"
          text="注文状況一覧へ"
          action-type="originalAction"
          style="padding-left: 0;"
          @app-action-handler="handleClick"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaDomesticStockOrderCompleteFormModel } from './js/IfaDomesticStockOrderCompleteFormModel'
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
  emits: ['close-modal', 'move-to-order-list', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaDomesticStockOrderCompleteFormModel(),
      dialogStyle: '',
      messages: {
        errors: [],
        warnings: [],
        infos: []
      },
      styleSeparator: {
        paddingLeft: '90px',
        fontWeight: 'bold',
        paddingTop: '8px'
      }
    }
  },
  computed: {
    customerName() {
      return this.customerInfo.customerNameKanji + '（' + this.customerInfo.customerNameKana + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.customerInfo.accountNumber}`
    },
    getUrlObject() {
      if (!this.formData.requestContents.brandCode) {
        return null
      }
      return this.formData.requestContents.brandCode.substring(0, 4)
    },
    setTextDepositTypeDispPattern() {
      // 顧客共通情報.ジュニアISA契約区分=1：契約
      if (this.customerInfo.jrIsaContractType === '1') {
        // 顧客共通情報.特定口座区分=1:特定口座(代行納付)　または　2:特定口座(確定申告)
        if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
          return 1
        } else {
          return 8
        }
      } else {
        // 顧客共通情報.特定口座区分=1:特定口座(代行納付)　または　2:特定口座(確定申告)
        if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
          return 2
        } else {
          return 9
        }
      }
    }
  },
  watch: {
    'formData.requestContents.tradeCd'(newValue) {
      if (newValue === '1') {
        this.dialogStyle = 'backgroundColor: #fef0f0;'
      } else {
        this.dialogStyle = 'backgroundColor: #ecf5ff;'
      }
    }
  },
  methods: {
    onOpen() {
      // 取引注意情報リンクの初期化
      this.$nextTick(() => {
        this.$refs['tradeLimitRef']?.trigger()
        this.$refs['tradeLimitIfdRef']?.trigger()
      })
    },
    // 閉じるボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    //  注文状況一覧へボタン
    handleClick() {
      this.$emit('move-to-order-list')
    },
    getDeliveryDateYear(deliveryDate) {
      return deliveryDate.substring(0, 4)
    },
    // 価格に表示する内容を生成する
    getPrice() {
      if (this.formData.requestContents.sasinariHouhou === '1') {
        if (this.formData.requestContents.sasinariJyouken !== ' ') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.requestContents.sasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.price)) + '円'
        } else {
          return this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.price)) + '円'
        }
      } else if (this.formData.requestContents.sasinariHouhou === '2') {
        if (this.formData.requestContents.sasinariJyouken !== 'N') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.formData.requestContents.sasinariJyouken))
        }
      } else if (this.formData.requestContents.sasinariHouhou === '3') {
        let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.triggerPrice)) + '円' +
                  (this.formData.requestContents.tradeCd === '1' ? '以上' : '以下') + 'になった時点で'
        if (this.formData.requestContents.gyakusasiHouhou === '1') {
          if (this.formData.requestContents.sasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.requestContents.sasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.price)) + '円'
        } else if (this.formData.requestContents.gyakusasiHouhou === '2') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.formData.requestContents.sasinariJyouken))
        }
        return str + 'で執行'
      }
      return '-'
    },
    getOcoPrice() {
      if (this.formData.requestContents.oco1SasinariJyouken !== ' ') {
        return this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.requestContents.oco1SasinariJyouken) + '/'
      } else {
        return
      }
    },
    getOcoCondition() {
      let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.oco2TriggerPrice)) + '円' +
                  (this.formData.requestContents.tradeCd === '1' ? '以上' : '以下') + 'になった時点でOCO1'
      if (this.formData.requestContents.oco1SasinariJyouken !== ' ') {
        str += '（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.requestContents.oco1SasinariJyouken)) + '）を'
      } else {
        str += '（指値）を'
      }

      // 指値
      if (this.formData.requestContents.oco2GyakusasiHouhou === '1') {
        // 条件
        if (this.formData.requestContents.oco2GyakusasiJyouken !== ' ') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.requestContents.oco2GyakusasiJyouken)) + '/' +
          this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.oco2Price)) + '円に訂正'
        } else {
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.oco2Price)) + '円に訂正'
        }
      } else if (this.formData.requestContents.oco2GyakusasiHouhou === '2') {
        str += '成行に訂正'
      }
      return str
    },
    getIfd1Price() {
      if (this.formData.requestContents.ifd1SasinariHouhou === '1') {
        if (this.formData.requestContents.ifd1SasinariJyouken !== ' ') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.requestContents.ifd1SasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.ifd1Price)) + '円'
        } else {
          return this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.ifd1Price)) + '円'
        }
      } else if (this.formData.requestContents.ifd1SasinariHouhou === '2') {
        if (this.formData.requestContents.ifd1SasinariJyouken !== 'N') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.formData.requestContents.ifd1SasinariJyouken))
        }
      } else if (this.formData.requestContents.ifd1SasinariHouhou === '3') {
        let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.ifd1TriggerPrice)) + '円' +
                  (this.formData.requestContents.tradeCd === '1' ? '以上' : '以下') + 'になった時点で'
        if (this.formData.requestContents.ifd1GyakusasiHouhou === '1') {
          if (this.formData.requestContents.ifd1SasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.requestContents.ifd1SasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.ifd1Price)) + '円'
        } else if (this.formData.requestContents.ifd1GyakusasiHouhou === '2') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.formData.requestContents.ifd1SasinariJyouken))
        }
        return str + 'で執行'
      }
      return '-'
    },
    getIfd2Price() {
      if (this.formData.requestContents.ifd2SasinariHouhou === '1') {
        if (this.formData.requestContents.ifd2SasinariJyouken !== ' ') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.requestContents.ifd2SasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.ifd2Price)) + '円'
        } else {
          return this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.ifd2Price)) + '円'
        }
      } else if (this.formData.requestContents.ifd2SasinariHouhou === '2') {
        if (this.formData.requestContents.ifd2SasinariJyouken !== 'N') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.formData.requestContents.ifd2SasinariJyouken))
        }
      } else if (this.formData.requestContents.ifd2SasinariHouhou === '3') {
        let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.ifd2TriggerPrice)) + '円' +
                  '以下になった時点で'
        if (this.formData.requestContents.ifd2GyakusasiHouhou === '1') {
          if (this.formData.requestContents.ifd2SasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.requestContents.ifd2SasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.ifd2Price)) + '円'
        } else if (this.formData.requestContents.ifd2GyakusasiHouhou === '2') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.formData.requestContents.ifd2SasinariJyouken))
        }
        return str + 'で執行'
      }
      return '-'
    },
    getOcoIfdCondition() {
      let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.oco2TriggerPrice)) + '円' +
                  '以下になった時点でOCO1'
      if (this.formData.requestContents.oco1SasinariJyouken !== ' ') {
        str += '（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.requestContents.oco1SasinariJyouken)) + '）を'
      } else {
        str += '（指値）を'
      }

      // 指値
      if (this.formData.requestContents.oco2GyakusasiHouhou === '1') {
        // 条件
        if (this.formData.requestContents.oco2GyakusasiJyouken !== ' ') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.formData.requestContents.oco2GyakusasiJyouken)) + '/' +
          this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.oco2Price)) + '円に訂正'
        } else {
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.formData.requestContents.oco2Price)) + '円に訂正'
        }
      } else if (this.formData.requestContents.oco2GyakusasiHouhou === '2') {
        str += '成行に訂正'
      }
      return str
    }
  }
}
</script>

<style lang="scss">
.domestic_stock_order_comp {
  .el-dialog__header {
      font-weight: bold;
      padding-top: 40px;
  }
    .el-dialog__body {
      padding-left: 45px;
      padding-right: 45px;
      padding-bottom: 50px;
    }
}
</style>
<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
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
.label-padding {
  padding: 0.1rem 0 0.1rem 0.5rem;
}
.data-padding {
  padding: 0.1rem;
}
.label-padding_other {
  padding: 0.3rem 0;
}
</style>
