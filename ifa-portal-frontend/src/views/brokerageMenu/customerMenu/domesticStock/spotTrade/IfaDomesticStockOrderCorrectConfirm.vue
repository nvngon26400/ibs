<template>
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title.name"
    :show-close="false"
    :center="true"
    :before-close="backA002"
    :close-on-click-modal="false"
    :class="classObject.classObjBgColor"
    width="1200px"
    @open="onShow"
  >

    <!-- ヘッダ -->
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
          @app-action-handler="backA002"
        ></ifa-button>

      </el-col>
    </el-row>
    <ifa-message-area
      :key="messageKey"
      :main-messages="['訂正はまだ完了していません。画面下の注文訂正ボタンを押下してください。']"
      :error-messages="errorMessages"
      :info-messages="infoMessage"
    ></ifa-message-area>
    <el-form
      ref="form"
      :model="form"
      label-position="left"
      label-width="220px"
    >
      <!-- 顧客情報 -->
      <el-row
        style="font-weight: bold; color: black;"
      >
        <el-col
          :offset="1"
          :span="22"
        >
          <span>{{ $_out(customerInfo.butenCode) }}-{{ $_zeroPadding($_out(customerInfo.accountNumber), 7) }}</span>
        </el-col>
      </el-row>
      <el-row
        style="padding-top: 0.3rem;"
        class="_bold_black_l"
      >
        <el-col
          :offset="1"
          :span="22"
          style="font-size: 20px;"
          class="_bold_black_l"
        >
          <el-icon style="position: relative; top: 3px;">
            <OfficeBuilding v-if="customerInfo.corporationType == '1'"></OfficeBuilding>
            <Avatar v-else></Avatar>
          </el-icon>
          <span>{{ $_out(customerInfo.customerNameKanji) }}（{{ $_out(customerInfo.customerNameKana) }}）</span>
          <ifa-notice-info
            :notice-info-level="customerInfo.noticeInfoLevel"
            :customer-code="customerInfo.customerCode"
            :buten-code="customerInfo.butenCode"
            :account-number="customerInfo.accountNumber"
            style="position: relative; top: 4px;"
          ></ifa-notice-info>
        </el-col>
      </el-row>

      <!-- 注文内容 -->
      <el-row>
        <el-col
          :span="22"
          :offset="1"
          style="padding-top: 0.7rem;"
        >
          <el-card
            class="box-card"
            style="font-size: 16px;"
          >
            <!--見出し、注文種別、取引種別、市場、未約定数量、受注数量は共通して表示-->
            <el-row>
              <el-row
                class="_bold_black_m"
                style="padding-top: 0.2rem; padding-left: 1rem;"
              >
                <span>ご注文内容（復唱項目）</span>
                <!--IFD、IFDOCOの時はラベル名変更-->
                <span v-if="confirmData.orderKind === '3' || confirmData.orderKind === '4'">：IFD1</span>
              </el-row>

              <el-row class="_bold_black_m"
                      style="padding-left: 1rem;"
              >
                <el-col
                  :span="5"
                  :offset="7"
                >
                  <span>訂正前</span>
                </el-col>
                <el-col :span="3">
                  <span class="el-icon-right arrow">→</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <span>訂正後</span>
                </el-col>
              </el-row>
              <hr style="height: 1px; width: 100%;">

              <el-row class="dotted_border">
                <el-col :span="6">
                  <span class="_bold_black_m">[銘柄コード]&nbsp;銘柄名</span>
                </el-col>
                <el-col
                  :span="12"
                  :offset="1"
                >
                <span style="overflow-wrap: break-word;">[{{ $_out(confirmData.brandCode) }}]&nbsp;{{ $_out(confirmData.brandName) }}</span>
                </el-col>
                <el-col
                  v-if="confirmData.regKbn"
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
                    disp-name="取引注意情報"
                  ></ifa-link>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="6">
                  <span class="_bold_black_m">注文種別</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                  style="font-weight: bold"
                >
                  <span v-if="confirmData.beforeCorrectPrice[0].sasinariHouhou === '3'">逆指値注文</span>
                  <ifa-text
                    v-else
                    style="font-size: 16px;"
                    code-list-id="ORDER_CLASS"
                    :disp-pattern="2"
                    :code-key="confirmData.orderKind"
                  ></ifa-text>
                  <span v-if="!confirmData.orderKind">-</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                  style="font-weight: bold"
                >
                  <span v-if="confirmData.beforeCorrectPrice[0].sasinariHouhou === '3'">逆指値注文</span>
                  <ifa-text
                    v-else
                    style="font-size: 16px;"
                    code-list-id="ORDER_CLASS"
                    :disp-pattern="2"
                    :code-key="confirmData.orderKind"
                  ></ifa-text>
                  <span v-if="!confirmData.orderKind">-</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="6">
                  <span class="_bold_black_m">取引種別</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <ifa-text
                    :class="classObject.classObjFontColor"
                    style="font-size: 16px;"
                    code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                    :disp-pattern="1"
                    :code-key="confirmData.tradeCd"
                  ></ifa-text>
                  <span v-if="!confirmData.tradeCd">-</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <ifa-text
                    :class="classObject.classObjFontColor"
                    style="font-size: 16px;"
                    code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                    :disp-pattern="1"
                    :code-key="confirmData.tradeCd"
                  ></ifa-text>
                  <span v-if="!confirmData.tradeCd">-</span>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
                :class="{
                  'correction-bg-color':
                  confirmData.market !== confirmData.afterCorrectMarket
                }"
              >
                <el-col :span="6">
                  <span class="_bold_black_m">市場</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <ifa-text
                    style="font-size: 16px;"
                    code-list-id="SELECT_MARKET"
                    :disp-pattern="1"
                    :code-key="confirmData.market"
                  ></ifa-text>
                  <span v-if="!confirmData.market">-</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                  :class="{
                    'correction-font-weight':
                    confirmData.market !== confirmData.afterCorrectMarket
                  }"
                >
                  <ifa-text
                    style="font-size: 16px;"
                    code-list-id="SELECT_MARKET"
                    :disp-pattern="1"
                    :code-key="confirmData.afterCorrectMarket"
                  ></ifa-text>
                  <span v-if="!confirmData.afterCorrectMarket">-</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="6">
                  <span class="_bold_black_m">未約定数量</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <span>{{ $_out($_withCommaInteger(confirmData.unTradeQuantity)) }}</span>
                  <span v-if="confirmData.unTradeQuantity">株</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <span>{{ $_out($_withCommaInteger(confirmData.unTradeQuantity)) }}</span>
                  <span v-if="confirmData.unTradeQuantity">株</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="6">
                  <span class="_bold_black_m">受注数量</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <span>{{ $_out($_withCommaInteger(confirmData.quantity)) }}</span>
                  <span v-if="confirmData.quantity">株</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <span>-</span>
                </el-col>
              </el-row>
            </el-row>

            <!--通常/逆指値、IFD、IFDOCOの時価格欄は同じ構成-->
            <el-row
              v-if="confirmData.orderKind === '1' || confirmData.orderKind === '3' || confirmData.orderKind === '4'"
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  confirmData.orderKind === '1' &&
                  (confirmData.beforeCorrectPrice[0].sasinariHouhou != confirmData.sasinariHouhou ||
                    getPrice(correctPriceParam, confirmData.tradeCd) != getPrice(priceParam, confirmData.tradeCd)) ||
                  (confirmData.orderKind === '3' || confirmData.orderKind === '4') &&
                  (confirmData.beforeCorrectPrice[0].ifd1SasinariHouhou != confirmData.ifd1SasinariHouhou ||
                    getPrice(correctIfd1PriceParam, confirmData.tradeCd) != getPrice(ifd1PriceParam, confirmData.tradeCd))
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">価格</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span
                  v-if="confirmData.orderKind === '1'"
                  style="white-space: pre-wrap;"
                >
                  <ifa-text
                    style="font-size: 16px;"
                    code-list-id="EXECUTE_METHOD"
                    :disp-pattern="1"
                    :code-key="confirmData.beforeCorrectPrice[0].sasinariHouhou"
                  ></ifa-text>
                  {{ getPrice(correctPriceParam, confirmData.tradeCd) }}</span>
                <span
                  v-if="(confirmData.orderKind === '3' || confirmData.orderKind === '4')"
                  style="white-space: pre-wrap;"
                >
                  <ifa-text
                    style="font-size: 16px;"
                    code-list-id="EXECUTE_METHOD"
                    :disp-pattern="1"
                    :code-key="confirmData.beforeCorrectPrice[0].ifd1SasinariHouhou"
                  ></ifa-text>
                  {{ getPrice(correctIfd1PriceParam, confirmData.tradeCd) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
                :class="{
                  'correction-font-weight':
                    confirmData.orderKind === '1' &&
                    (confirmData.beforeCorrectPrice[0].sasinariHouhou != confirmData.sasinariHouhou ||
                      getPrice(correctPriceParam, confirmData.tradeCd) != getPrice(priceParam, confirmData.tradeCd)) ||
                    (confirmData.orderKind === '3' || confirmData.orderKind === '4') &&
                    (confirmData.beforeCorrectPrice[0].ifd1SasinariHouhou != confirmData.ifd1SasinariHouhou ||
                      getPrice(correctIfd1PriceParam, confirmData.tradeCd) != getPrice(ifd1PriceParam, confirmData.tradeCd))
                }"
              >
                <span
                  v-if="confirmData.orderKind === '1'"
                  style="white-space: pre-wrap;"
                >
                  <ifa-text
                    style="font-size: 16px;"
                    code-list-id="EXECUTE_METHOD"
                    :disp-pattern="1"
                    :code-key="confirmData.sasinariHouhou"
                  ></ifa-text>
                  {{ getPrice(priceParam, confirmData.tradeCd) }}</span>
                <span
                  v-if="(confirmData.orderKind === '3' || confirmData.orderKind === '4')"
                  style="white-space: pre-wrap;"
                >
                  <ifa-text
                    style="font-size: 16px;"
                    code-list-id="EXECUTE_METHOD"
                    :disp-pattern="1"
                    :code-key="confirmData.ifd1SasinariHouhou"
                  ></ifa-text>
                  {{ getPrice(ifd1PriceParam, confirmData.tradeCd) }}</span>
              </el-col>
            </el-row>

            <!--空のレスポンスが返ってきたときに表示-->
            <el-row v-if="!confirmData.orderKind">
              <el-row class="dotted_border">
                <el-col :span="6">
                  <span class="_bold_black_m">価格</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <span>-</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <span>-</span>
                </el-col>
              </el-row>
            </el-row>

            <!-- OCOの時価格欄-->
            <el-row v-if="confirmData.orderKind === '2'">
              <el-row
                v-if="confirmData.workingStatus === 'false'"
                class="dotted_border"
                :class="{
                  'correction-bg-color':
                    getOco1Price(confirmData.beforeCorrectPrice[0].oco1SasinariHouhou, confirmData.beforeCorrectPrice[0].oco1SasinariJyouken, confirmData.beforeCorrectPrice[0].oco1Price)
                    != getOco1Price(confirmData.oco1SasinariHouhou, confirmData.oco1SasinariJyouken, confirmData.oco1Price)
                }"
              >
                <el-col :span="6">
                  <span class="_bold_black_m">価格／OCO1</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <span style="white-space: pre-wrap;">
                    {{ getOco1Price(confirmData.beforeCorrectPrice[0].oco1SasinariHouhou, confirmData.beforeCorrectPrice[0].oco1SasinariJyouken, confirmData.beforeCorrectPrice[0].oco1Price) }}</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                  :class="{
                    'correction-font-weight':
                      getOco1Price(confirmData.beforeCorrectPrice[0].oco1SasinariHouhou, confirmData.beforeCorrectPrice[0].oco1SasinariJyouken, confirmData.beforeCorrectPrice[0].oco1Price)
                      != getOco1Price(confirmData.oco1SasinariHouhou, confirmData.oco1SasinariJyouken, confirmData.oco1Price)
                  }"
                >
                  <span style="white-space: pre-wrap;">
                    {{ getOco1Price(confirmData.oco1SasinariHouhou, confirmData.oco1SasinariJyouken, confirmData.oco1Price) }}</span>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
                :class="{
                  'correction-bg-color':
                    getOco2Price(confirmData.tradeCd, confirmData.beforeCorrectPrice[0].oco1SasinariJyouken, correctOco2Param)
                    != getOco2Price(confirmData.tradeCd, confirmData.oco1SasinariJyouken, oco2Param)
                }"
              >
                <el-col :span="6">
                  <span class="_bold_black_m">条件／OCO2</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <span style="white-space: pre-wrap;">{{ getOco2Price(confirmData.tradeCd, confirmData.beforeCorrectPrice[0].oco1SasinariJyouken, correctOco2Param) }}</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                  :class="{
                    'correction-font-weight':
                      getOco2Price(confirmData.tradeCd, confirmData.beforeCorrectPrice[0].oco1SasinariJyouken, correctOco2Param)
                      != getOco2Price(confirmData.tradeCd, confirmData.oco1SasinariJyouken, oco2Param)
                  }"
                >
                  <span style="white-space: pre-wrap;">{{ getOco2Price(confirmData.tradeCd, confirmData.oco1SasinariJyouken, oco2Param) }}</span>
                </el-col>
              </el-row>
            </el-row>

            <!--期間、預り区分、手数料区分は共通して表示-->
            <el-row
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  confirmData.yukoKigenChange === '1'
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">注文期間</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out($_getFormattedDateValue(confirmData.period)) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
                :class="{
                  'correction-font-weight':
                    confirmData.yukoKigenChange === '1'
                }"
              >
                <span v-if="confirmData.yukoKigenChange === '1'">{{ $_out($_getFormattedDateValue(confirmData.yukoKigen)) }}</span>
                <span v-else>{{ $_out($_getFormattedDateValue(confirmData.period)) }}</span>
              </el-col>
            </el-row>

            <el-row
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">預り区分</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  v-if="customerInfo.jrIsaContractType === '1'"
                  style="font-size: 16px;"
                  code-list-id="DOMESTIC_DEPOSIT_TYPE"
                  :disp-pattern="1"
                  :code-key="confirmData.depositType"
                ></ifa-text>
                <ifa-text
                  v-else
                  style="font-size: 16px;"
                  code-list-id="DOMESTIC_DEPOSIT_TYPE"
                  :disp-pattern="2"
                  :code-key="confirmData.depositType"
                ></ifa-text>
                <span v-if="!confirmData.depositType">-</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  v-if="customerInfo.jrIsaContractType === '1'"
                  style="font-size: 16px;"
                  code-list-id="DOMESTIC_DEPOSIT_TYPE"
                  :disp-pattern="1"
                  :code-key="confirmData.depositType"
                ></ifa-text>
                <ifa-text
                  v-else
                  style="font-size: 16px;"
                  code-list-id="DOMESTIC_DEPOSIT_TYPE"
                  :disp-pattern="2"
                  :code-key="confirmData.depositType"
                ></ifa-text>
                <span v-if="!confirmData.depositType">-</span>
              </el-col>
            </el-row>

            <el-row
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">手数料区分</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  v-if="confirmData.tesuuryouKbn"
                  style="font-size: 16px;"
                  code-list-id="COMM_TYPE"
                  :disp-pattern="2"
                  :code-key="confirmData.tesuuryouKbn"
                ></ifa-text>
                <span v-else>-</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  v-if="confirmData.tesuuryouKbn"
                  style="font-size: 16px;"
                  code-list-id="COMM_TYPE"
                  :disp-pattern="2"
                  :code-key="confirmData.tesuuryouKbn"
                ></ifa-text>
                <span v-else>-</span>
              </el-col>
            </el-row>

          </el-card>
        </el-col>
      </el-row>

      <el-row>
        <div style="margin-bottom: 0.5rem;"></div>
      </el-row>

      <!-- 注文内容 IFD2（注文種別が IFD また IFDOCO の時のみ表示）-->
      <el-row v-if="confirmData.orderKind == '3' || confirmData.orderKind == '4'">
        <el-col
          :span="22"
          :offset="1"
        >
          <el-card class="box-card"
                   style="font-size: 16px;"
          >
            <el-row
              class="_bold_black_m"
              style="padding-top: 0.2rem; padding-left: 1rem;"
            >
              <span>ご注文内容（復唱項目）：IFD2</span>
            </el-row>
            <el-row class="_bold_black_m">
              <el-col
                :span="5"
                :offset="7"
              >
                <span>訂正前</span>
              </el-col>
              <el-col :span="3">
                <span class="el-icon-right arrow">→</span>
              </el-col>
              <el-col
                :span="7"
                :offset="1"
              >
                <span>訂正後</span>
              </el-col>
            </el-row>
            <hr>

            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">[銘柄コード]&nbsp;銘柄名</span>
              </el-col>
              <el-col
                :span="12"
                :offset="1"
              >
              <span style="overflow-wrap: break-word;">[{{ $_out(confirmData.brandCode) }}]&nbsp;{{ $_out(confirmData.brandName) }}</span>
              </el-col>
              <el-col
                v-if="confirmData.regKbn"
                :span="5"
                style="text-align: center;"
              >
                <el-icon
                  style="color: red;  vertical-align: middle;"
                ><WarningFilled></WarningFilled></el-icon>
                <ifa-link
                  ref="tradeLimitIfdRef"
                  :url-id="16"
                  :url-object="{ brandCode: getUrlObject }"
                  disp-name="取引注意情報"
                ></ifa-link>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">注文種別</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
                style="font-weight: bold"
              >
                <span v-if="confirmData.sasinariHouhou === '3'">逆指値注文</span>
                <ifa-text
                  v-else
                  style="font-size: 16px;"
                  code-list-id="ORDER_CLASS"
                  :disp-pattern="2"
                  :code-key="confirmData.orderKind"
                ></ifa-text>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
                style="font-weight: bold"
              >
                <span v-if="confirmData.sasinariHouhou === '3'">逆指値注文</span>
                <ifa-text
                  v-else
                  style="font-size: 16px;"
                  code-list-id="ORDER_CLASS"
                  :disp-pattern="2"
                  :code-key="confirmData.orderKind"
                ></ifa-text>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">取引種別</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  style="font-size: 16px;"
                  class="font-color__minus bold"
                  code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                  :disp-pattern="1"
                  code-key="2"
                ></ifa-text>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  style="font-size: 16px;"
                  class="font-color__minus bold"
                  code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                  :disp-pattern="1"
                  code-key="2"
                ></ifa-text>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">市場</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  style="font-size: 16px;"
                  code-list-id="SELECT_MARKET"
                  :disp-pattern="1"
                  :code-key="confirmData.market"
                ></ifa-text>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  style="font-size: 16px;"
                  code-list-id="SELECT_MARKET"
                  :disp-pattern="1"
                  :code-key="confirmData.market"
                ></ifa-text>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">未約定数量</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>-</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>-</span>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">受注数量</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>-</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>-</span>
              </el-col>
            </el-row>

            <!--IFDの時価格欄-->
            <el-row
              v-if="confirmData.orderKind == '3'"
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  confirmData.beforeCorrectPrice[0].ifd2SasinariHouhou != confirmData.ifd2SasinariHouhou ||
                  getPrice(correctIfd2PriceParam, confirmData.tradeCd) != getPrice(ifd2PriceParam, confirmData.tradeCd)
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">価格</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  style="font-size: 16px;"
                  code-list-id="EXECUTE_METHOD"
                  :disp-pattern="1"
                  :code-key="confirmData.beforeCorrectPrice[0].ifd2SasinariHouhou"
                ></ifa-text>
                <span style="white-space: pre-wrap;">{{ getPrice(correctIfd2PriceParam, '2') }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
                :class="{
                  'correction-font-weight':
                    confirmData.beforeCorrectPrice[0].ifd2SasinariHouhou != confirmData.ifd2SasinariHouhou ||
                    getPrice(correctIfd2PriceParam, confirmData.tradeCd) != getPrice(ifd2PriceParam, confirmData.tradeCd)
                }"
              >
                <ifa-text
                  style="font-size: 16px;"
                  code-list-id="EXECUTE_METHOD"
                  :disp-pattern="1"
                  :code-key="confirmData.ifd2SasinariHouhou"
                ></ifa-text>
                <span style="white-space: pre-wrap;">{{ getPrice(ifd2PriceParam, '2') }}</span>
              </el-col>
            </el-row>

            <!-- IFDOCOの時価格欄-->
            <el-row v-if="confirmData.orderKind === '4'">
              <el-row
                v-if="confirmData.workingStatus === 'false'"
                class="dotted_border"
                :class="{
                  'correction-bg-color':
                    getOco1Price(confirmData.beforeCorrectPrice[0].oco1SasinariHouhou, confirmData.beforeCorrectPrice[0].oco1SasinariJyouken, confirmData.beforeCorrectPrice[0].oco1Price)
                    != getOco1Price(confirmData.oco1SasinariHouhou, confirmData.oco1SasinariJyouken, confirmData.oco1Price)
                }"
              >
                <el-col :span="6">
                  <span class="_bold_black_m">価格／OCO1</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <span style="white-space: pre-wrap;">
                    {{ getOco1Price(confirmData.beforeCorrectPrice[0].oco1SasinariHouhou, confirmData.beforeCorrectPrice[0].oco1SasinariJyouken, confirmData.beforeCorrectPrice[0].oco1Price) }}</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                  :class="{
                    'correction-font-weight':
                      getOco1Price(confirmData.beforeCorrectPrice[0].oco1SasinariHouhou, confirmData.beforeCorrectPrice[0].oco1SasinariJyouken, confirmData.beforeCorrectPrice[0].oco1Price)
                      != getOco1Price(confirmData.oco1SasinariHouhou, confirmData.oco1SasinariJyouken, confirmData.oco1Price)
                  }"
                >
                  <span style="white-space: pre-wrap;">
                    {{ getOco1Price(confirmData.oco1SasinariHouhou, confirmData.oco1SasinariJyouken, confirmData.oco1Price) }}</span>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
                :class="{
                  'correction-bg-color':
                    getOco2Price('2', confirmData.beforeCorrectPrice[0].ifd2SasinariJyouken, correctOco2Param)
                    != getOco2Price('2', confirmData.ifd2SasinariJyouken, oco2Param)
                }"
              >
                <el-col :span="6">
                  <span class="_bold_black_m">条件／OCO2</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <span style="white-space: pre-wrap;">{{ getOco2Price('2', confirmData.beforeCorrectPrice[0].oco1SasinariJyouken, correctOco2Param) }}</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                  :class="{
                    'correction-font-weight':
                      getOco2Price('2', confirmData.beforeCorrectPrice[0].ifd2SasinariJyouken, correctOco2Param)
                      != getOco2Price('2', confirmData.ifd2SasinariJyouken, oco2Param)
                  }"
                >
                  <span style="white-space: pre-wrap;">{{ getOco2Price('2', confirmData.oco1SasinariJyouken, oco2Param) }}</span>
                </el-col>
              </el-row>
            </el-row>

            <!--期間、預り区分、手数料区分は共通して表示-->
            <el-row>
              <el-row
                class="dotted_border"
                :class="{
                  'correction-bg-color':
                    confirmData.yukoKigenChange === '1'
                }"
              >
                <el-col :span="6">
                  <span class="_bold_black_m">注文期間</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <span>{{ $_out($_getFormattedDateValue(confirmData.ifd2Limit)) }}</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                  :class="{
                    'correction-font-weight':
                      confirmData.yukoKigenChange === '1'
                  }"
                >
                  <span v-if="confirmData.yukoKigenChange === '1'">{{ $_out($_getFormattedDateValue(confirmData.yukoKigen)) }}</span>
                  <span v-else>{{ $_out($_getFormattedDateValue(confirmData.period)) }}</span>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
              >
                <el-col :span="6">
                  <span class="_bold_black_m">預り区分</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <ifa-text
                    v-if="customerInfo.jrIsaContractType === '1'"
                    style="font-size: 16px;"
                    code-list-id="DOMESTIC_DEPOSIT_TYPE"
                    :disp-pattern="1"
                    :code-key="confirmData.depositType"
                  ></ifa-text>
                  <ifa-text
                    v-else
                    style="font-size: 16px;"
                    code-list-id="DOMESTIC_DEPOSIT_TYPE"
                    :disp-pattern="2"
                    :code-key="confirmData.depositType"
                  ></ifa-text>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <ifa-text
                    v-if="customerInfo.jrIsaContractType === '1'"
                    style="font-size: 16px;"
                    code-list-id="DOMESTIC_DEPOSIT_TYPE"
                    :disp-pattern="1"
                    :code-key="confirmData.depositType"
                  ></ifa-text>
                  <ifa-text
                    v-else
                    style="font-size: 16px;"
                    code-list-id="DOMESTIC_DEPOSIT_TYPE"
                    :disp-pattern="2"
                    :code-key="confirmData.depositType"
                  ></ifa-text>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
              >
                <el-col :span="6">
                  <span class="_bold_black_m">手数料区分</span>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <ifa-text
                    style="font-size: 16px;"
                    code-list-id="COMM_TYPE"
                    :disp-pattern="2"
                    :code-key="confirmData.tesuuryouKbn"
                  ></ifa-text>
                </el-col>
                <el-col
                  :span="8"
                  :offset="1"
                >
                  <ifa-text
                    style="font-size: 16px;"
                    code-list-id="COMM_TYPE"
                    :disp-pattern="2"
                    :code-key="confirmData.tesuuryouKbn"
                  ></ifa-text>
                </el-col>
              </el-row>
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
          <el-card
            class="box-card"
            style="font-size: 16px;"
          >
            <el-row class="_bold_black_m"
                    style="padding-left: 1rem;"
            >
              <span>その他注文内容</span>
            </el-row>
            <hr>
            <el-row
              class="dotted_border"
              :class="{ 'correction-bg-color correction-font-weight': !(!confirmData.afterCorrecBuyPower || confirmData.afterCorrecBuyPower === '0') }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">訂正後買付余力</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <span v-if="!confirmData.afterCorrecBuyPower || confirmData.afterCorrecBuyPower === '0'">-</span>
                <span v-else>{{ $_out($_withCommaInteger(confirmData.afterCorrecBuyPower)) }}円</span>
              </el-col>
            </el-row>

            <el-row
              v-if="confirmData.depositType === 'H'"
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">注文後のNISA投資可能枠（{{ $_out(confirmData.deliveryDate.slice(0,4)) }}年）</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <span style="font-size: 16px;">{{ $_out($_withCommaInteger(confirmData.nisaInvestableQuote)) }}円</span>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">約定予定日</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <span style="font-size: 16px;">{{ $_out($_getFormattedDateValue(confirmData.contractDate)) }}</span>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">受渡予定日</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <span style="font-size: 16px;">{{ $_out($_getFormattedDateValue(confirmData.deliveryDate)) }}</span>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">受注日時</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <span style="font-size: 16px;">{{ $_out($_getFormattedDateTimeValue(confirmData.orderDayTime, 'datetime12')) }}</span>
              </el-col>
            </el-row>

            <!-- 勧誘区分 -->
            <el-row
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">勧誘区分</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <ifa-text
                  v-if="confirmData.kanyuKbn"
                  style="font-size: 16px;"
                  code-list-id="INVITATION_TYPE"
                  :disp-pattern="1"
                  :code-key="confirmData.kanyuKbn"
                ></ifa-text>
                <span v-else>-</span>
              </el-col>
            </el-row>

            <!-- 受注方法 -->
            <el-row
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">受注方法</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <ifa-text
                  v-if="confirmData.receiveOrderType"
                  style="font-size: 16px;"
                  code-list-id="ORDER_METHOD_TYPE"
                  :disp-pattern="1"
                  :code-key="confirmData.receiveOrderType"
                ></ifa-text>
                <span v-else>-</span>
              </el-col>
            </el-row>

            <!-- 確認項目 -->
            <el-row
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">確認項目</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <ifa-text
                  v-if="confirmData.checkInsider"
                  style="font-size: 16px;"
                  code-list-id="INSIDER_CONFIRM"
                  :disp-pattern="3"
                  :code-key="confirmData.checkInsider"
                ></ifa-text>
                <br v-if="confirmData.afterCorrectMarket === 'A' && confirmData.checkInsider">
                <ifa-text
                  v-if="confirmData.afterCorrectMarket === 'A'"
                  code-list-id="SOR_CONFIRM"
                  :disp-pattern="2"
                  :code-key="confirmData.checkSor"
                  style="font-size: 16px;"
                ></ifa-text>
                <span v-if="!confirmData.checkInsider && confirmData.afterCorrectMarket !== 'A'">-</span>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>

      <el-row>
        <div style="margin-bottom: 0.5rem;"></div>
      </el-row>

      <!-- アラート内容確認 -->
      <el-row v-if="alertContentIsVisible">
        <el-col
          :span="22"
          :offset="1"
        >
          <el-card
            :class="classObject.classObjBgAlertColor"
            class="box-card alert_content"
            style="font-size: 16px;"
          >
            <el-row
              class="_bold_black_m"
              style="color: #f00; padding-left: 1rem;"
            >
              <span>アラート内容確認</span>
            </el-row>
            <hr>
            <el-row
              v-if="confirmData.regKbn && confirmData.regKbn.length > 0"
              class="dotted_border"
              style="align-items: center;"
            >
              <el-col
                :span="6"
                class="_bold_red_alart"
              >
                <div class="required-mark">*</div><span style="color: #f00;">取引注意情報の説明</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
                style="font-size: 16px;"
              >
                <ifa-input-check
                  id="tradingCautionInformation"
                  v-model="form.tradingCautionInformation"
                  prop="tradingCautionInformation"
                  name="tradingCautionInformation"
                  required
                  :code-list-id="'TRADE_NOTICE_INFO_EXPLAIN'"
                  :select-pattern="2"
                  :disp-pattern="1"
                ></ifa-input-check>
              </el-col>
            </el-row>
            <el-row
              v-if="confirmData.messageId && confirmData.messageId.length > 0"
              class="dotted_border"
              style="align-items: center;"
            >
              <el-col
                :span="6"
                class="_bold_red_alart"
              >
                <div class="required-mark">*</div><span style="color: #f00;">ワーニング申請取引</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <ifa-input-check
                  v-model="form.invitationCheck"
                  prop="invitationCheck"

                  required
                  code-list-id="original"
                  :original-list="invitationCheckOptions"
                ></ifa-input-check>
              </el-col>
            </el-row>
            <el-row
              v-if="confirmData.noticeInfoAlert && confirmData.noticeInfoAlert.length > 0"
              class="dotted_border"
              style="align-items: center;"
            >
              <el-col
                :span="6"
                class="_bold_red_alart"
              >
                <div class="required-mark">*</div><span style="color: #f00;">注意情報の確認</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <ifa-input-check
                  id="noticeInfoAlertCheck"
                  v-model="form.noticeInfoAlertCheck"
                  name="noticeInfoAlertCheck"
                  prop="noticeInfoAlertCheck"

                  required
                  code-list-id="NOTICE_INFO_CONFIRM"
                  :select-pattern="2"
                  :disp-pattern="1"
                ></ifa-input-check>
              </el-col>
            </el-row>
            <el-row
              v-if="confirmData.noticeAlert && confirmData.noticeAlert.length > 0"
              class="dotted_border"
              style="align-items: center;"
            >
              <el-col
                :span="6"
                class="_bold_red_alart"
              >
                <div class="required-mark">*</div><span style="color: #f00;">重要なお知らせの確認</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <ifa-input-check
                  id="noticeAlertCheck"
                  v-model="form.noticeAlertCheck"
                  name="noticeAlertCheck"
                  prop="noticeAlertCheck"
                  required
                  :code-list-id="'IMPORTANT_NOTIFICATION_CONFIRM'"
                  :select-pattern="2"
                  :disp-pattern="1"
                ></ifa-input-check>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>

      <!-- フォーム: 注文確認/リセット -->
      <el-row>
        <el-col
          :offset="1"
          style="margin-top: 1rem"
        >
          <span class="dialog-footer">
            <ifa-button
              id="btnOrderRegister"
              text=" 注文訂正"
              :disabled="btnDisabled"
              :form="formRef"
              action-type="requestAction"
              style="padding-left: 0;"
              action-id="SUB0202_0208-03_2#A001"
              :request-model="IfaDomesticStockOrderCorrectConfirmA001RequestModel"
              @response-handler="orderPlacementA001($event)"
            ></ifa-button>
          </span>
        </el-col>
      </el-row>
    </el-form>
  </el-dialog>
</template>

<script>
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { useVModel } from 'vue-composable'
import { IfaDomesticStockOrderCorrectConfirmA001RequestModel } from './js/IfaDomesticStockOrderCorrectConfirmA001RequestModel.js'
import { IfaDomesticStockOrderCorrectConfirmFormModel } from './js/IfaDomesticStockOrderCorrectConfirmFormModel.js'
export default {
  components: {
    IfaMessageArea,
    IfaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    confirmData: { type: Object, required: true }
  },
  emits: ['order-finish', 'update:isVisible', 'back-modal'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaDomesticStockOrderCorrectConfirmFormModel(),
      priceParam: {
        sasinariHouhou: '',
        sasinariJyouken: '',
        triggerPrice: '',
        gyakusasiHouhou: '',
        price: '',
        afterCorrectFlag: true
      },
      ifd1PriceParam: {
        sasinariHouhou: '',
        sasinariJyouken: '',
        triggerPrice: '',
        gyakusasiHouhou: '',
        price: '',
        afterCorrectFlag: true
      },
      ifd2PriceParam: {
        sasinariHouhou: '',
        sasinariJyouken: '',
        triggerPrice: '',
        gyakusasiHouhou: '',
        price: '',
        afterCorrectFlag: true
      },
      oco2Param: {
        triggerPrice: '',
        gyakusasiHouhou: '',
        gyakusasiJyouken: '',
        price: '',
        afterCorrectFlag: true
      },
      correctPriceParam: {
        sasinariHouhou: '',
        sasinariJyouken: '',
        triggerPrice: '',
        stopOrderPriceText2: '',
        gyakusasiHouhou: '',
        price: '',
        afterCorrectFlag: false
      },
      correctIfd1PriceParam: {
        sasinariHouhou: '',
        sasinariJyouken: '',
        triggerPrice: '',
        stopOrderPriceText2: '',
        gyakusasiHouhou: '',
        price: '',
        afterCorrectFlag: false
      },
      correctIfd2PriceParam: {
        sasinariHouhou: '',
        sasinariJyouken: '',
        triggerPrice: '',
        stopOrderPriceText2: '',
        gyakusasiHouhou: '',
        price: '',
        afterCorrectFlag: false
      },
      correctOco2Param: {
        triggerPrice: '',
        stopOrderPriceText2: '',
        gyakusasiHouhou: '',
        gyakusasiJyouken: '',
        price: '',
        afterCorrectFlag: false
      },
      classObject: {
        classObjBgColor: '',
        classObjFontColor: '',
        classObjBgAlertColor: ''
      },
      formRef: {},
      messageKey: 0
    }
  },
  computed: {
    IfaDomesticStockOrderCorrectConfirmA001RequestModel() { return new IfaDomesticStockOrderCorrectConfirmA001RequestModel(this.form) },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    errorMessages() {
      const msg = []
      if (this.confirmData.regKbn) { msg.push(this.confirmData.regKbn) }
      if (this.confirmData.messageId) { msg.push(this.confirmData.messageId) }
      if (this.confirmData.noticeInfoAlert) { msg.push(this.confirmData.noticeInfoAlert) }
      if (this.confirmData.noticeAlert) { msg.push(this.confirmData.noticeAlert) }
      return msg
    },
    infoMessage() {
      const msg = []
      // エラーコード=ワーニングの場合（W05001～W09999）
      if (this.confirmData.code.charAt(0) === 'W') {
        msg.push(this.confirmData.errMessage + '（' + this.confirmData.code + '）')
      }
      return msg
    },
    getUrlObject() {
      if (!this.confirmData.brandCode) {
        return null
      }
      return this.confirmData.brandCode.substring(0, 4)
    },
    alertContentIsVisible() {
      if ((this.confirmData.regKbn && this.confirmData.regKbn.length > 0) ||
      (this.confirmData.messageId && this.confirmData.messageId.length > 0) ||
      (this.confirmData.noticeInfoAlert && this.confirmData.noticeInfoAlert.length > 0) ||
      (this.confirmData.noticeAlert && this.confirmData.noticeAlert.length > 0)) {
        return true
      } else {
        return false
      }
    },
    userInfo() {
      return this.$store.getters.userAccount
    },
    btnDisabled() {
      if ((this.userInfo.medUsers.privId === '1' || this.userInfo.medUsers.privId === '2') ||
      (this.confirmData.regKbn && this.confirmData.regKbn.length > 0 && this.form.tradingCautionInformation === '0') ||
      (this.confirmData.messageId && this.confirmData.messageId.length > 0 & this.form.invitationCheck === '0') ||
      (this.confirmData.noticeInfoAlert && this.confirmData.noticeInfoAlert.length > 0 && this.form.noticeInfoAlertCheck === '0') ||
      (this.confirmData.noticeAlert && this.confirmData.noticeAlert.length > 0 && this.form.noticeAlertCheck === '0')) {
        return true
      }
      return false
    },
    invitationCheckLabel() {
      return this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 3, this.confirmData.chkBoxLabel)
    },
    invitationCheckOptions() {
      const codeValue = this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, this.confirmData.chkBoxLabel)
      return [{ key: '0', value: '' }, { key: '1', value: codeValue }]
    }
  },
  methods: {
    onShow() {
      this.formRef = this.$refs.form
      this.$nextTick(() => {
        if (this.formRef) {
          this.formRef.resetFields()
        }
      })
      Object.assign(this.form, this.confirmData)
      // gePriceに渡すパラメータへ値をセット
      Object.assign(this.priceParam, this.confirmData)
      this.ifd1PriceParam = {
        sasinariHouhou: this.confirmData.ifd1SasinariHouhou,
        sasinariJyouken: this.confirmData.ifd1SasinariJyouken,
        triggerPrice: this.confirmData.ifd1TriggerPrice,
        gyakusasiHouhou: this.confirmData.ifd1GyakusasiHouhou,
        price: this.confirmData.ifd1Price,
        afterCorrectFlag: true
      }
      this.ifd2PriceParam = {
        sasinariHouhou: this.confirmData.ifd2SasinariHouhou,
        sasinariJyouken: this.confirmData.ifd2SasinariJyouken,
        triggerPrice: this.confirmData.ifd2TriggerPrice,
        gyakusasiHouhou: this.confirmData.ifd2GyakusasiHouhou,
        price: this.confirmData.ifd2Price,
        afterCorrectFlag: true
      }
      this.oco2Param = {
        triggerPrice: this.confirmData.oco2TriggerPrice,
        gyakusasiHouhou: this.confirmData.oco2GyakusasiHouhou,
        gyakusasiJyouken: this.confirmData.oco2GyakusasiJyouken,
        price: this.confirmData.oco2Price,
        afterCorrectFlag: true
      }
      Object.assign(this.correctPriceParam, this.confirmData.beforeCorrectPrice[0])
      this.correctIfd1PriceParam = {
        sasinariHouhou: this.confirmData.beforeCorrectPrice[0].ifd1SasinariHouhou,
        sasinariJyouken: this.confirmData.beforeCorrectPrice[0].ifd1SasinariJyouken,
        triggerPrice: this.confirmData.beforeCorrectPrice[0].ifd1TriggerPrice,
        stopOrderPriceText2: this.confirmData.beforeCorrectPrice[0].ifd1StopOrderPriceText2,
        gyakusasiHouhou: this.confirmData.beforeCorrectPrice[0].ifd1GyakusasiHouhou,
        price: this.confirmData.beforeCorrectPrice[0].ifd1Price,
        afterCorrectFlag: false
      }
      this.correctIfd2PriceParam = {
        sasinariHouhou: this.confirmData.beforeCorrectPrice[0].ifd2SasinariHouhou,
        sasinariJyouken: this.confirmData.beforeCorrectPrice[0].ifd2SasinariJyouken,
        triggerPrice: this.confirmData.beforeCorrectPrice[0].ifd2TriggerPrice,
        stopOrderPriceText2: this.confirmData.beforeCorrectPrice[0].ifd2StopOrderPriceText2,
        gyakusasiHouhou: this.confirmData.beforeCorrectPrice[0].ifd2GyakusasiHouhou,
        price: this.confirmData.beforeCorrectPrice[0].ifd2Price,
        afterCorrectFlag: false
      }
      this.correctOco2Param = {
        triggerPrice: this.confirmData.beforeCorrectPrice[0].oco2TriggerPrice,
        stopOrderPriceText2: this.confirmData.beforeCorrectPrice[0].oco2StopOrderPriceText2,
        gyakusasiHouhou: this.confirmData.beforeCorrectPrice[0].oco2GyakusasiHouhou,
        gyakusasiJyouken: this.confirmData.beforeCorrectPrice[0].oco2GyakusasiJyouken,
        price: this.confirmData.beforeCorrectPrice[0].oco2Price,
        afterCorrectFlag: false
      }

      // 背景、取引種別の色
      if (this.confirmData.tradeCd === '1') {
        // 買付
        this.classObject.classObjFontColor = 'font-color__plus bold'
        if (this.confirmData.orderKind === '1' || this.confirmData.orderKind === '2') {
          this.classObject.classObjBgColor = 'buy-background-color'
          this.classObject.classObjBgAlertColor = 'buy-background-alert-color'
        } else if (this.confirmData.orderKind === '3' || this.confirmData.orderKind === '4') {
          this.classObject.classObjBgColor = 'ifd-background-color'
          this.classObject.classObjBgAlertColor = 'ifd-background-alert-color'
        }
      } else if (this.confirmData.tradeCd === '2') {
        // 売却
        this.classObject.classObjFontColor = 'font-color__minus bold'
        if (this.confirmData.orderKind === '1' || this.confirmData.orderKind === '2') {
          this.classObject.classObjBgColor = 'sell-background-color'
          this.classObject.classObjBgAlertColor = 'sell-background-alert-color'
        } else if (this.confirmData.orderKind === '3' || this.confirmData.orderKind === '4') {
          this.classObject.classObjBgColor = 'ifd-background-color'
          this.classObject.classObjBgAlertColor = 'ifd-background-alert-color'
        }
      }
      this.messageKey++

      // 訂正SOR注文結果区分="1"：SOR訂正注文以外の場合、市場（訂正後）を市場（訂正前）と同じにする
      if (this.confirmData.correctSorOrderResultClassification !== '1') {
        this.confirmData.afterCorrectMarket = this.confirmData.market
        this.form.afterCorrectMarket = this.confirmData.market
      }

      // 取引注意情報リンクの初期化
      this.$nextTick(() => {
        this.$refs['tradeLimitRef']?.trigger()
        this.$refs['tradeLimitIfdRef']?.trigger()
      })
    },
    // 戻るボタン
    // 入力画面へ戻る
    backA002() {
      this.$emit('back-modal')
      this.$refs.form.clearValidate()
    },
    // 注文訂正ボタン
    orderPlacementA001(response) {
      this.$emit('order-finish', response)
      this.$refs.form.clearValidate()
    },
    // 価格欄に表示する内容を生成する
    getPrice(param, tradeCd) {
      if (param.sasinariHouhou === '1') {
      // 指値
        const str = this.$_out(this.$_withCommaNoneZeroPadding(param.price)) + '円'
        if (param.sasinariJyouken !== ' ') {
          return `\n` + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, param.sasinariJyouken)) +
               '/' + str
        } else {
          return `\n` + str
        }
      } else if (param.sasinariHouhou === '2') {
      // 成行
        if (param.sasinariJyouken !== 'N') {
          return `\n` + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, param.sasinariJyouken))
        }
      } else if (param.sasinariHouhou === '3') {
        // 逆指値
        let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(param.triggerPrice)) + '円'
        if (!param.afterCorrectFlag) {
          // 訂正前
          if (param.stopOrderPriceText2 === ' ') {
            // 指定なし
            switch (tradeCd) {
              case '1':
                str += '以上になった時点で'
                break
              case '2':
                str += '以下になった時点で'
                break
            }
          } else if (param.stopOrderPriceText2 === '0' || param.stopOrderPriceText2 === '1') {
            // 以上、以下の場合
            str += this.$_out(this.$_getCodeValue('LATEST_TRIGGER_ZONE', 1, param.stopOrderPriceText2)) +
                 'になった時点で'
          }
        } else {
          // 訂正後
          switch (tradeCd) {
            case '1':
              str += '以上になった時点で'
              break
            case '2':
              str += '以下になった時点で'
              break
          }
        }
        if (param.gyakusasiHouhou === '1') {
        // 執行方法（逆指値）が指値の時
          if (param.sasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, param.sasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(param.price)) + '円'
        } else if (param.gyakusasiHouhou === '2') {
        // 執行方法（逆指値）が成行の時
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, param.sasinariJyouken))
        }
        return `\n` + str + 'で執行'
      }
      return ''
    },
    getOco1Price(sasinariHouhou, sasinariJyouken, price) {
      let str = this.$_out(this.$_getCodeValue('EXECUTE_METHOD', 1, sasinariHouhou))
      str += `\n`
      if (sasinariJyouken !== ' ') {
        str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, sasinariJyouken)) + '/'
      }
      return str + this.$_out(this.$_withCommaNoneZeroPadding(price)) + '円'
    },
    getOco2Price(tradeCd, sasinariJyouken, param) {
      let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(param.triggerPrice)) + '円'
      if (!param.afterCorrectFlag) {
        // 訂正前
        if (param.stopOrderPriceText2 === ' ') {
          // 指定なし
          switch (tradeCd) {
            case '1':
              str += '以上になった時点で'
              break
            case '2':
              str += `以下になった時点で`
              break
          }
        } else if (param.stopOrderPriceText2 === '0' || param.stopOrderPriceText2 === '1') {
          // 以上、以下の場合
          str += this.$_out(this.$_getCodeValue('LATEST_TRIGGER_ZONE', 1, param.stopOrderPriceText2)) +
               `になった時点で`
        }
      } else {
        // 訂正後
        switch (tradeCd) {
          case '1':
            str += '以上になった時点で'
            break
          case '2':
            str += `以下になった時点で`
            break
        }
      }
      if (sasinariJyouken === ' ') {
      // 条件なし
        str += 'OCO1（指値）を'
      } else {
        str += 'OCO1（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, sasinariJyouken)) + '）を'
      }
      if (param.gyakusasiHouhou === '1') {
      // 執行方法（逆指値）が指値の時
        if (param.gyakusasiJyouken === ' ') {
          str += this.$_out(this.$_withCommaNoneZeroPadding(param.price)) + '円'
        } else {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, param.gyakusasiJyouken)) + '/' +
                 this.$_out(this.$_withCommaNoneZeroPadding(param.price)) + '円'
        }
      } else if (param.gyakusasiHouhou === '2') {
      // 執行方法（逆指値）が成行の時
        str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, 'N'))
      }
      return str + 'に訂正'
    }
  }
}
</script>

<style lang="scss">
.domestic_stock_order_correct {
  .el-card__body {
    padding: 5px !important;
  }
}
  .arrow {
      font-weight: 700;
      font-size: 22px;
      margin-top: -2px;
      -webkit-transform: scaleX(1.4);
      transform: scaleX(1.4);
      color: #000;
  }
  .el-icon-right {
  font-family: element-icons!important;
  font-style: normal;
  font-variant: normal;
  text-transform: none;
  line-height: 1;
  vertical-align: baseline;
  display: inline-block;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  }
</style>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
@import "@/styles/variables.scss";

:deep(.el-checkbox__label) {
  color: #f00;
  font-weight: bold;
}
:deep(.el-checkbox__input.is-checked + .el-checkbox__label) {
  color: #f00;
}
.buy-background-alert-color {
  background-color: #fef0f0;
}
.sell-background-alert-color {
  background-color: #ecf5ff;
}
.ifd-background-alert-color {
  background-color: #e7dcf2;
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
.correction-font-weight {
  font-weight: bold;
}
._bold_red_alart {
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
.correction-font-normal {
  font-weight: normal;
}

:deep(.el-checkbox__label){
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
