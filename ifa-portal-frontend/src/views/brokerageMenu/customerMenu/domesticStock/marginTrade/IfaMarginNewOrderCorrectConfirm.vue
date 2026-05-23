<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      title="信用新規注文訂正確認"
      :style="(orderInfo.orderKind === '3' || orderInfo.orderKind === '4') ? 'background-color: #E7DCF2' : orderInfo.tradeCd === '3' ? 'background-color: #fef0f0' : 'background-color: #ecf5ff'"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      width="1200px"
      @open="onOpen"
    >
      <!-- タイトルエリア -->
      <el-row>
        <el-col
          style="text-align: right;"
          :offset="1"
          :span="22"
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
          <span>{{ $_out(customerInfo.butenCode) + '-' + $_out($_zeroPadding(customerInfo.accountNumber,7)) }}</span>
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
          <span style="position: relative; top: 3px;">
            <span v-if="customerInfo.corporationType === '1'">
              <el-icon><OfficeBuilding></OfficeBuilding></el-icon>
            </span>
            <span v-else>
              <el-icon><avatar></avatar></el-icon>
            </span>
          </span>
          <span>{{ $_out(customerInfo.customerNameKanji) + '（' + $_out(customerInfo.customerNameKana) + '）' }}</span>
          <ifa-notice-info
            :notice-info-level="customerInfo.noticeInfoLevel"
            :buten-code="customerInfo.butenCode"
            :customer-code="customerInfo.customerCode"
            :account-number="customerInfo.accountNumber"
            style="position: relative; top: 4px;"
          ></ifa-notice-info>
        </el-col>
      </el-row>
      <!-- 注文訂正内容エリア -->
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
            <el-row
              class="_bold_black_m"
              style="padding-top: 0.2rem; padding-left: 1rem;"
            >
              <span>ご注文内容（復唱項目）</span><span
                v-if="orderInfo.orderKind === '3' || orderInfo.orderKind === '4'"
              >：IFD1</span>
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
                :span="8"
                :offset="1"
              >
                <span>訂正後</span>
              </el-col>
            </el-row>
            <hr>

            <el-row class="dotted_border">
              <el-col
                :span="6"
                class="_bold_black_m"
              >
                <span>[銘柄コード]&nbsp;銘柄名</span>
              </el-col>
              <el-col
                :span="12"
                :offset="1"
              >
                <span style="overflow-wrap: break-word;">[{{ $_out(orderInfo.brandCode) }}]&nbsp;{{ $_out(orderInfo.brandName) }}</span>
              </el-col>
              <el-col
                v-if="orderInfo.tradeNoticeInfoBrandMsg"
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
                  :url-object="{ brandCode: orderInfo.brandCode.substring(0, 4) }"
                ></ifa-link>
              </el-col>
            </el-row>

            <!-- 注文種別 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">注文種別</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
                style="font-weight: bold"
              >
                <span>{{ orderInfo.correctBeforePriceSasinariHouhou === '3' ? '逆指値注文' : orderInfo.orderKind ? $_getCodeValue('ORDER_CLASS', 2, orderInfo.orderKind) : '-' }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
                style="font-weight: bold"
              >
                <span>{{ orderInfo.correctBeforePriceSasinariHouhou === '3' ? '逆指値注文' : orderInfo.orderKind ? $_getCodeValue('ORDER_CLASS', 2, orderInfo.orderKind) : '-' }}</span>
              </el-col>
            </el-row>
            <!-- 取引種別 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">取引種別</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span :class="orderInfo.tradeCd === '3' ? 'font-color__plus bold' : 'font-color__minus bold'">{{ $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, orderInfo.tradeCd)) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span :class="orderInfo.tradeCd === '3' ? 'font-color__plus bold' : 'font-color__minus bold'">{{ $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, orderInfo.tradeCd)) }}</span>
              </el-col>
            </el-row>
            <!-- 市場 -->
            <el-row class="dotted_border"
              :class="{
                'correction-bg-color':
                  orderInfo.market !== orderInfo.afterCorrectMarket
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">市場</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out($_getCodeValue('SELECT_MARKET', 1, orderInfo.market)) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
                :class="{
                  _bold_black_origin:
                  orderInfo.market !== orderInfo.afterCorrectMarket
              　}"
              >
                <span>{{ $_out($_getCodeValue('SELECT_MARKET', 1, orderInfo.afterCorrectMarket)) }}</span>
              </el-col>
            </el-row>
            <!-- 未約定数量 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">未約定数量</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out($_withCommaInteger(orderInfo.unTradeQuantity)) }} 株</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out($_withCommaInteger(orderInfo.unTradeQuantity)) }} 株</span>
              </el-col>
            </el-row>
            <!-- 受注数量 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">受注数量</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out($_withCommaInteger(orderInfo.quantity)) }} 株</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>-</span>
              </el-col>
            </el-row>
            <!-- 価格/執行方法 -->
            <!-- 注文種別 = 通常/逆指値 -->
            <el-row
              v-if="orderInfo.orderKind === '1'"
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  orderInfo.correctBeforePriceSasinariHouhou != orderInfo.sasinariHouhou ||
                  orderInfo.correctBeforePriceSasinariJyouken != orderInfo.sasinariJyouken ||
                  orderInfo.correctBeforePricePrice != orderInfo.price ||
                  orderInfo.correctBeforePriceTriggerPrice != orderInfo.triggerPrice ||
                  orderInfo.correctBeforePriceGyakusasiHouhou != orderInfo.gyakusasiHouhou
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">価格</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <!-- 執行方法 -->
                <el-row>
                  <el-col>
                    <span>{{ orderInfo.correctBeforePriceSasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.correctBeforePriceSasinariHouhou) : '-' }}</span>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 4px;">
                  <el-col>
                    <!-- 指値（訂正前） -->
                    <span v-if="orderInfo.correctBeforePriceSasinariHouhou === '1'">
                      <span v-if="orderInfo.correctBeforePriceSasinariJyouken !== ' '">{{
                        orderInfo.correctBeforePriceSasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceSasinariJyouken) : '-'
                      }} / </span>
                      <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.correctBeforePricePrice?.trim())) }}円</span>
                    </span>
                    <!-- 成行（訂正前） -->
                    <span v-else-if="orderInfo.correctBeforePriceSasinariHouhou === '2'">
                      <span v-if="orderInfo.correctBeforePriceSasinariJyouken !== 'N'">{{
                        orderInfo.correctBeforePriceSasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 3, orderInfo.correctBeforePriceSasinariJyouken) : '-'
                      }}</span>
                    </span>
                    <!-- 逆指値（訂正前）-->
                    <span v-else-if="orderInfo.correctBeforePriceSasinariHouhou === '3'">
                      <span>現在値が{{ $_withCommaNoneZeroPadding(orderInfo.correctBeforePriceTriggerPrice?.trim()) }}円</span>
                      <span v-if="orderInfo.correctBeforePriceTriggerPriceZone === ' '">{{ orderInfo.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                      <span v-else>{{ orderInfo.correctBeforePriceTriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, orderInfo.correctBeforePriceTriggerPriceZone) : '-' }}になった時点で</span>
                      <span v-if="orderInfo.correctBeforePriceGyakusasiHouhou === '1' && orderInfo.correctBeforePriceSasinariJyouken !== ' '">{{ orderInfo.correctBeforePriceSasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceSasinariJyouken) : '-' }}</span>
                      <span v-if="orderInfo.correctBeforePriceGyakusasiHouhou === '1'">{{ $_out($_withCommaNoneZeroPadding(orderInfo.correctBeforePricePrice?.trim())) }}円</span>
                      <span v-if="orderInfo.correctBeforePriceGyakusasiHouhou === '2'">{{ orderInfo.correctBeforePriceSasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 1, orderInfo.correctBeforePriceSasinariJyouken) : '-' }}</span>
                      <span>で執行</span>
                    </span>
                  </el-col>
                </el-row>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <!-- 執行方法 -->
                <el-row>
                  <el-col>
                    <span
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceSasinariHouhou != orderInfo.sasinariHouhou ||
                          orderInfo.correctBeforePriceSasinariJyouken != orderInfo.sasinariJyouken ||
                          orderInfo.correctBeforePricePrice != orderInfo.price ||
                          orderInfo.correctBeforePriceTriggerPrice != orderInfo.triggerPrice ||
                          orderInfo.correctBeforePriceGyakusasiHouhou != orderInfo.gyakusasiHouhou
                      }"
                    >{{ orderInfo.sasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.sasinariHouhou) : '-' }}</span>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 4px;">
                  <el-col>
                    <!-- 指値（訂正後） -->
                    <span
                      v-if="orderInfo.sasinariHouhou === '1'"
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceSasinariHouhou != orderInfo.sasinariHouhou ||
                          orderInfo.correctBeforePriceSasinariJyouken != orderInfo.sasinariJyouken ||
                          orderInfo.correctBeforePricePrice != orderInfo.price
                      }"
                    >
                      <span v-if="orderInfo.sasinariJyouken !== ' '">{{
                        orderInfo.sasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.sasinariJyouken) : '-'
                      }} / </span>
                      <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.price?.trim())) }}円</span>
                    </span>
                    <!-- 成行（訂正後） -->
                    <span
                      v-else-if="orderInfo.sasinariHouhou === '2'"
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceSasinariHouhou != orderInfo.sasinariHouhou ||
                          orderInfo.correctBeforePriceSasinariJyouken != orderInfo.sasinariJyouken
                      }"
                    >
                      <span v-if="orderInfo.sasinariJyouken !== 'N'">{{
                        orderInfo.sasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 3, orderInfo.sasinariJyouken) : '-'
                      }}</span>
                    </span>
                    <!-- 逆指値（訂正後）-->
                    <span
                      v-else-if="orderInfo.sasinariHouhou === '3'"
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceSasinariHouhou != orderInfo.sasinariHouhou ||
                          orderInfo.correctBeforePriceSasinariJyouken != orderInfo.sasinariJyouken ||
                          orderInfo.correctBeforePricePrice != orderInfo.price ||
                          orderInfo.correctBeforePriceTriggerPrice != orderInfo.triggerPrice ||
                          orderInfo.correctBeforePriceGyakusasiHouhou != orderInfo.gyakusasiHouhou
                      }"
                    >
                      <span>現在値が{{ $_withCommaNoneZeroPadding(orderInfo.triggerPrice?.trim()) }}円</span>
                      <span>{{ orderInfo.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                      <span v-if="orderInfo.gyakusasiHouhou === '1' && orderInfo.sasinariJyouken !== ' '">{{ orderInfo.sasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.sasinariJyouken) : '-' }}</span>
                      <span v-if="orderInfo.gyakusasiHouhou === '1'">{{ $_out($_withCommaNoneZeroPadding(orderInfo.price?.trim())) }}円</span>
                      <span v-if="orderInfo.gyakusasiHouhou === '2'">{{ orderInfo.sasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 1, orderInfo.sasinariJyouken) : '-' }}</span>
                      <span>で執行</span>
                    </span>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
            <!-- 注文種別 = OCO -->
            <!-- 注文内容 OCO1 (注文種別が OCOの時のみ表示)-->
            <el-row
              v-if="orderInfo.orderKind === '2' && !orderInfo.workingStatus"
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  orderInfo.correctBeforePriceOco1SasinariHouhou != orderInfo.oco1SasinariHouhou ||
                  orderInfo.correctBeforePriceOco1SasinariJyouken != orderInfo.oco1SasinariJyouken ||
                  orderInfo.correctBeforePriceOco1Price != orderInfo.oco1Price
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">価格／OCO1</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <!-- 価格/執行方法（訂正前） -->
                <el-row>
                  <el-col>
                    <span>{{ orderInfo.correctBeforePriceOco1SasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.correctBeforePriceOco1SasinariHouhou) : '-' }}</span>
                  </el-col>
                </el-row>
                <!-- 執行条件（訂正前） -->
                <el-row style="margin-top: 4px;">
                  <el-col>
                    <span v-if="orderInfo.correctBeforePriceOco1SasinariJyouken !== ' '">{{
                      orderInfo.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceOco1SasinariJyouken) : '-'
                    }} / </span>
                    <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.correctBeforePriceOco1Price?.trim())) }}円</span>
                  </el-col>
                </el-row>
              </el-col>

              <el-col
                :span="8"
                :offset="1"
              >
                <!-- 価格/執行方法（訂正後） -->
                <el-row>
                  <el-col>
                    <span
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceOco1SasinariHouhou != orderInfo.oco1SasinariHouhou ||
                          orderInfo.correctBeforePriceOco1SasinariJyouken != orderInfo.oco1SasinariJyouken ||
                          orderInfo.correctBeforePriceOco1Price != orderInfo.oco1Price
                      }"
                    >{{ orderInfo.oco1SasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.oco1SasinariHouhou) : '-' }}</span>
                  </el-col>
                </el-row>
                <!-- 執行条件（訂正後） -->
                <el-row style="margin-top: 4px;">
                  <el-col>
                    <span
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceOco1SasinariHouhou != orderInfo.oco1SasinariHouhou ||
                          orderInfo.correctBeforePriceOco1SasinariJyouken != orderInfo.oco1SasinariJyouken ||
                          orderInfo.correctBeforePriceOco1Price != orderInfo.oco1Price
                      }"
                    >
                      <span v-if="orderInfo.oco1SasinariJyouken !== ' '">{{
                        orderInfo.oco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.oco1SasinariJyouken) : '-'
                      }} / </span>
                      <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.oco1Price?.trim())) }}円</span>
                    </span>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
            <!-- 注文内容 OCO2 (注文種別が OCO の時のみ表示)-->
            <el-row
              v-if="orderInfo.orderKind === '2'"
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  orderInfo.correctBeforePriceOco2TriggerPrice != orderInfo.oco2TriggerPrice ||
                  orderInfo.correctBeforePriceOco2GyakusasiHouhou != orderInfo.oco2GyakusasiHouhou ||
                  orderInfo.correctBeforePriceOco2GyakusasiJyouken != orderInfo.oco2GyakusasiJyouken ||
                  orderInfo.correctBeforePriceOco2Price != orderInfo.oco2Price
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">条件／OCO2</span>
              </el-col>
              <!-- 価格/執行方法（訂正前） -->
              <el-col
                :span="8"
                :offset="1"
              >
                <span>現在値が{{ $_withCommaNoneZeroPadding(orderInfo.correctBeforePriceOco2TriggerPrice?.trim()) }}円</span>
                <span v-if="orderInfo.correctBeforePriceOco2TriggerPriceZone === ' '">{{ orderInfo.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                <span v-else>{{ orderInfo.correctBeforePriceOco2TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, orderInfo.correctBeforePriceOco2TriggerPriceZone) : '-' }}になった時点で</span>
                <span v-if="orderInfo.correctBeforePriceOco1SasinariJyouken === ' '">OCO1（指値）を</span>
                <span v-else>OCO1（{{ orderInfo.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceOco1SasinariJyouken) : '-' }}）を</span>
                <span v-if="orderInfo.correctBeforePriceOco2GyakusasiHouhou === '1' && orderInfo.correctBeforePriceOco2GyakusasiJyouken === ' '">{{ $_out($_withCommaNoneZeroPadding(orderInfo.correctBeforePriceOco2Price?.trim())) }}円</span>
                <span v-if="orderInfo.correctBeforePriceOco2GyakusasiHouhou === '1' && orderInfo.correctBeforePriceOco2GyakusasiJyouken !== ' '">{{ orderInfo.correctBeforePriceOco2GyakusasiJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceOco2GyakusasiJyouken) : '-' }} / {{ $_out($_withCommaNoneZeroPadding(orderInfo.correctBeforePriceOco2Price?.trim())) }}円</span>
                <span v-if="orderInfo.correctBeforePriceOco2GyakusasiHouhou === '2'">成行</span>
                <span>に訂正</span>
              </el-col>
              <!-- 価格/執行方法（訂正後） -->
              <el-col
                :span="8"
                :offset="1"
              >
                <span
                  :class="{
                    _bold_black_origin:
                      orderInfo.correctBeforePriceOco2TriggerPrice != orderInfo.oco2TriggerPrice ||
                      orderInfo.correctBeforePriceOco2GyakusasiHouhou != orderInfo.oco2GyakusasiHouhou ||
                      orderInfo.correctBeforePriceOco2GyakusasiJyouken != orderInfo.oco2GyakusasiJyouken ||
                      orderInfo.correctBeforePriceOco2Price != orderInfo.oco2Price
                  }"
                >
                  <span>現在値が{{ $_withCommaNoneZeroPadding(orderInfo.oco2TriggerPrice?.trim()) }}円</span>
                  <span>{{ orderInfo.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                  <span v-if="orderInfo.correctBeforePriceOco1SasinariJyouken === ' '">OCO1（指値）を</span>
                  <span v-else>OCO1（{{ orderInfo.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceOco1SasinariJyouken) : '-' }}）を</span>
                  <span v-if="orderInfo.oco2GyakusasiHouhou === '1' && orderInfo.oco2GyakusasiJyouken === ' '">{{ $_out($_withCommaNoneZeroPadding(orderInfo.oco2Price?.trim())) }}円</span>
                  <span v-if="orderInfo.oco2GyakusasiHouhou === '1' && orderInfo.oco2GyakusasiJyouken !== ' '">{{ orderInfo.oco2GyakusasiJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.oco2GyakusasiJyouken) : '-' }} / {{ $_out($_withCommaNoneZeroPadding(orderInfo.oco2Price?.trim())) }}円</span>
                  <span v-if="orderInfo.oco2GyakusasiHouhou === '2'">成行</span>
                  <span>に訂正</span>
                </span>
              </el-col>
            </el-row>
            <!-- 注文種別=IFD -->
            <el-row
              v-if="orderInfo.orderKind === '3' || orderInfo.orderKind === '4'"
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  orderInfo.correctBeforePriceIfd1SasinariHouhou != orderInfo.ifd1SasinariHouhou ||
                  orderInfo.correctBeforePriceIfd1SasinariJyouken != orderInfo.ifd1SasinariJyouken ||
                  orderInfo.correctBeforePriceIfd1Price != orderInfo.ifd1Price ||
                  orderInfo.correctBeforePriceIfd1TriggerPrice != orderInfo.ifd1TriggerPrice ||
                  orderInfo.correctBeforePriceIfd1GyakusasiHouhou != orderInfo.ifd1GyakusasiHouhou
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">価格</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <!-- 執行方法 -->
                <el-row>
                  <el-col>
                    <span>{{ orderInfo.correctBeforePriceIfd1SasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.correctBeforePriceIfd1SasinariHouhou) : '-' }}</span>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 4px;">
                  <el-col>
                    <!-- 指値（訂正前） -->
                    <span v-if="orderInfo.correctBeforePriceIfd1SasinariHouhou === '1'">
                      <span v-if="orderInfo.correctBeforePriceIfd1SasinariJyouken !== ' '">{{
                        orderInfo.correctBeforePriceIfd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceIfd1SasinariJyouken) : '-'
                      }} / </span>
                      <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.correctBeforePriceIfd1Price?.trim())) }}円</span>
                    </span>
                    <!-- 成行（訂正前） -->
                    <span v-else-if="orderInfo.correctBeforePriceIfd1SasinariHouhou === '2'">
                      <span v-if="orderInfo.correctBeforePriceIfd1SasinariJyouken !== 'N'">{{
                        orderInfo.correctBeforePriceIfd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 3, orderInfo.correctBeforePriceIfd1SasinariJyouken) : '-'
                      }}</span>
                    </span>
                    <!-- 逆指値（訂正前）-->
                    <span v-else-if="orderInfo.correctBeforePriceIfd1SasinariHouhou === '3'">
                      <span>現在値が{{ $_withCommaNoneZeroPadding(orderInfo.correctBeforePriceIfd1TriggerPrice?.trim()) }}円</span>
                      <span v-if="orderInfo.correctBeforePriceIfd1TriggerPriceZone === ' '">{{ orderInfo.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                      <span v-else>{{ orderInfo.correctBeforePriceIfd1TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, orderInfo.correctBeforePriceIfd1TriggerPriceZone) : '-' }}になった時点で</span>
                      <span v-if="orderInfo.correctBeforePriceIfd1GyakusasiHouhou === '1' && orderInfo.correctBeforePriceIfd1SasinariJyouken !== ' '">{{ orderInfo.correctBeforePriceIfd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceIfd1SasinariJyouken) : '-' }}</span>
                      <span v-if="orderInfo.correctBeforePriceIfd1GyakusasiHouhou === '1'">{{ $_out($_withCommaNoneZeroPadding(orderInfo.correctBeforePriceIfd1Price?.trim())) }}円</span>
                      <span v-if="orderInfo.correctBeforePriceIfd1GyakusasiHouhou === '2'">{{ orderInfo.correctBeforePriceIfd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 1, orderInfo.correctBeforePriceIfd1SasinariJyouken) : '-' }}</span>
                      <span>で執行</span>
                    </span>
                  </el-col>
                </el-row>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <!-- 執行方法 -->
                <el-row>
                  <el-col>
                    <span
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceIfd1SasinariHouhou != orderInfo.ifd1SasinariHouhou ||
                          orderInfo.correctBeforePriceIfd1SasinariJyouken != orderInfo.ifd1SasinariJyouken ||
                          orderInfo.correctBeforePriceIfd1Price != orderInfo.ifd1Price ||
                          orderInfo.correctBeforePriceIfd1TriggerPrice != orderInfo.ifd1TriggerPrice ||
                          orderInfo.correctBeforePriceIfd1GyakusasiHouhou != orderInfo.ifd1GyakusasiHouhou
                      }"
                    >{{ orderInfo.ifd1SasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.ifd1SasinariHouhou) : '-' }}</span>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 4px;">
                  <el-col>
                    <!-- 指値（訂正後） -->
                    <span
                      v-if="orderInfo.ifd1SasinariHouhou === '1'"
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceIfd1SasinariHouhou != orderInfo.ifd1SasinariHouhou ||
                          orderInfo.correctBeforePriceIfd1SasinariJyouken != orderInfo.ifd1SasinariJyouken ||
                          orderInfo.correctBeforePriceIfd1Price != orderInfo.ifd1Price
                      }"
                    >
                      <span v-if="orderInfo.ifd1SasinariJyouken !== ' '">{{
                        orderInfo.ifd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.ifd1SasinariJyouken) : '-'
                      }} / </span>
                      <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.ifd1Price?.trim())) }}円</span>
                    </span>
                    <!-- 成行（訂正後） -->
                    <span
                      v-else-if="orderInfo.ifd1SasinariHouhou === '2'"
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceIfd1SasinariHouhou != orderInfo.ifd1SasinariHouhou ||
                          orderInfo.correctBeforePriceIfd1SasinariJyouken != orderInfo.ifd1SasinariJyouken
                      }"
                    >
                      <span v-if="orderInfo.ifd1SasinariJyouken !== 'N'">{{
                        orderInfo.ifd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 3, orderInfo.ifd1SasinariJyouken) : '-'
                      }}</span>
                    </span>
                    <!-- 逆指値（訂正後）-->
                    <span
                      v-else-if="orderInfo.ifd1SasinariHouhou === '3'"
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceIfd1SasinariHouhou != orderInfo.ifd1SasinariHouhou ||
                          orderInfo.correctBeforePriceIfd1SasinariJyouken != orderInfo.ifd1SasinariJyouken ||
                          orderInfo.correctBeforePriceIfd1Price != orderInfo.ifd1Price ||
                          orderInfo.correctBeforePriceIfd1TriggerPrice != orderInfo.ifd1TriggerPrice ||
                          orderInfo.correctBeforePriceIfd1GyakusasiHouhou != orderInfo.ifd1GyakusasiHouhou
                      }"
                    >
                      <span>現在値が{{ $_withCommaNoneZeroPadding(orderInfo.ifd1TriggerPrice?.trim()) }}円</span>
                      <span>{{ orderInfo.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                      <span v-if="orderInfo.ifd1GyakusasiHouhou === '1' && orderInfo.ifd1SasinariJyouken !== ' '">{{ orderInfo.ifd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.ifd1SasinariJyouken) : '-' }}</span>
                      <span v-if="orderInfo.ifd1GyakusasiHouhou === '1'">{{ $_out($_withCommaNoneZeroPadding(orderInfo.ifd1Price?.trim())) }}円</span>
                      <span v-if="orderInfo.ifd1GyakusasiHouhou === '2'">{{ orderInfo.ifd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 1, orderInfo.ifd1SasinariJyouken) : '-' }}</span>
                      <span>で執行</span>
                    </span>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
            <!-- 期間 -->
            <el-row
              class="dotted_border"
              :class="{ 'correction-bg-color': orderInfo.yukoKigenChangeFlag === '1' }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">注文期間</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out($_getFormattedDateValue(orderInfo.period, 'date8')) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span :class="{ _bold_black_origin: orderInfo.yukoKigenChangeFlag === '1' }">{{ orderInfo.yukoKigenChangeFlag === '1' ? $_out($_getFormattedDateValue(orderInfo.yukoKigen, 'date8')) : $_out($_getFormattedDateValue(orderInfo.period, 'date8')) }}</span>
              </el-col>
            </el-row>
            <!-- 信用取引区分 -->
            <el-row
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">信用取引区分</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out(orderInfo.marginTradeTypeText) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out(orderInfo.marginTradeTypeText) }}</span>
              </el-col>
            </el-row>
            <!-- 手数料区分 -->
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
                <span>{{ $_out($_getCodeValue('COMM_TYPE', 2, orderInfo.comId)) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out($_getCodeValue('COMM_TYPE', 2, orderInfo.comId)) }}</span>
              </el-col>
            </el-row>

          </el-card>
        </el-col>
      </el-row>

      <el-row>
        <div style="margin-bottom: 0.5rem;"></div>
      </el-row>

      <!-- 注文訂正内容エリア IFD2（注文種別が IFD また IFDOCO の時のみ表示）-->
      <el-row v-if="orderInfo.orderKind == '3' || orderInfo.orderKind == '4'">
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
              <span>ご注文内容（復唱項目）</span><span
                v-if="orderInfo.orderKind === '3' || orderInfo.orderKind === '4'"
              >：IFD2</span>
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
                :span="8"
                :offset="1"
              >
                <span>訂正後</span>
              </el-col>
            </el-row>
            <hr>
            <el-row class="dotted_border">
              <el-col
                :span="6"
                class="_bold_black_m"
              >
                <span>[銘柄コード]&nbsp;銘柄名</span>
              </el-col>
              <el-col
                :span="12"
                :offset="1"
              >
                <span style="overflow-wrap: break-word;">[{{ $_out(orderInfo.brandCode) }}]&nbsp;{{ $_out(orderInfo.brandName) }}</span>
              </el-col>
              <el-col
                v-if="orderInfo.tradeNoticeInfoBrandMsg"
                :span="5"
                style="text-align: center;"
              >
                <el-icon
                  style="color: red;  vertical-align: middle;"
                ><WarningFilled></WarningFilled></el-icon>
                <ifa-link
                  ref="tradeLimitIfdRef"
                  :disp-name="'取引注意情報'"
                  :url-id="16"
                  :url-object="{ brandCode: orderInfo.brandCode.substring(0, 4) }"
                ></ifa-link>
              </el-col>
            </el-row>
            <!-- 注文種別 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">注文種別</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
                style="font-weight: bold"
              >
                <span>{{ orderInfo.correctBeforePriceSasinariHouhou === '3' ? '逆指値注文' : orderInfo.orderKind ? $_getCodeValue('ORDER_CLASS', 2, orderInfo.orderKind) : '-' }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
                style="font-weight: bold"
              >
                <span>{{ orderInfo.correctBeforePriceSasinariHouhou === '3' ? '逆指値注文' : orderInfo.orderKind ? $_getCodeValue('ORDER_CLASS', 2, orderInfo.orderKind) : '-' }}</span>
              </el-col>
            </el-row>
            <!-- 取引種別 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">取引種別</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span :class="orderInfo.tradeCd === '3' ? 'font-color__minus bold' : 'font-color__plus bold'">{{ orderInfo.tradeCd === '3' ? '返済売' : '返済買' }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span :class="orderInfo.tradeCd === '3' ? 'font-color__minus bold' : 'font-color__plus bold'">{{ orderInfo.tradeCd === '3' ? '返済売' : '返済買' }}</span>
              </el-col>
            </el-row>
            <!-- 市場 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">市場</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out($_getCodeValue('SELECT_MARKET', 1, orderInfo.market)) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out($_getCodeValue('SELECT_MARKET', 1, orderInfo.market)) }}</span>
              </el-col>
            </el-row>
            <!-- 未約定数量 -->
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
            <!-- 受注数量 -->
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

            <!-- 価格/執行方法 (注文種別が IFD 時のみ表示)-->
            <el-row
              v-if="orderInfo.orderKind === '3'"
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  orderInfo.correctBeforePriceIfd2SasinariHouhou != orderInfo.ifd2SasinariHouhou ||
                  orderInfo.correctBeforePriceIfd2SasinariJyouken != orderInfo.ifd2SasinariJyouken ||
                  orderInfo.correctBeforePriceIfd2Price != orderInfo.ifd2Price ||
                  orderInfo.correctBeforePriceIfd2TriggerPrice != orderInfo.ifd2TriggerPrice ||
                  orderInfo.correctBeforePriceIfd2GyakusasiHouhou != orderInfo.ifd2GyakusasiHouhou
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">価格</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <!-- 執行方法 -->
                <el-row>
                  <el-col>
                    <span>{{ orderInfo.correctBeforePriceIfd2SasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.correctBeforePriceIfd2SasinariHouhou) : '-' }}</span>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 4px;">
                  <el-col>
                    <!-- 指値（訂正前） -->
                    <span v-if="orderInfo.correctBeforePriceIfd2SasinariHouhou === '1'">
                      <span v-if="orderInfo.correctBeforePriceIfd2SasinariJyouken !== ' '">{{
                        orderInfo.correctBeforePriceIfd2SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceIfd2SasinariJyouken) : '-'
                      }} / </span>
                      <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.correctBeforePriceIfd2Price?.trim())) }}円</span>
                    </span>
                    <!-- 逆指値（訂正前）-->
                    <span v-else-if="orderInfo.correctBeforePriceIfd2SasinariHouhou === '3'">
                      <span>現在値が{{ $_withCommaNoneZeroPadding(orderInfo.correctBeforePriceIfd2TriggerPrice?.trim()) }}円</span>
                      <span v-if="orderInfo.correctBeforePriceIfd2TriggerPriceZone === ' '">{{ orderInfo.tradeCd === '4' ? '以上になった時点で' : '以下になった時点で' }}</span>
                      <span v-else>{{ orderInfo.correctBeforePriceIfd2TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, orderInfo.correctBeforePriceIfd2TriggerPriceZone) : '-' }}になった時点で</span>
                      <span v-if="orderInfo.correctBeforePriceIfd2GyakusasiHouhou === '1' && orderInfo.correctBeforePriceIfd2SasinariJyouken !== ' '">{{ orderInfo.correctBeforePriceIfd2SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceIfd2SasinariJyouken) : '-' }}</span>
                      <span v-if="orderInfo.correctBeforePriceIfd2GyakusasiHouhou === '1'">{{ $_out($_withCommaNoneZeroPadding(orderInfo.correctBeforePriceIfd2Price?.trim())) }}円</span>
                      <span v-if="orderInfo.correctBeforePriceIfd2GyakusasiHouhou === '2'">{{ orderInfo.correctBeforePriceIfd2SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 1, orderInfo.correctBeforePriceIfd2SasinariJyouken) : '-' }}</span>
                      <span>で執行</span>
                    </span>
                  </el-col>
                </el-row>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <!-- 執行方法 -->
                <el-row>
                  <el-col>
                    <span
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceIfd2SasinariHouhou != orderInfo.ifd2SasinariHouhou ||
                          orderInfo.correctBeforePriceIfd2SasinariJyouken != orderInfo.ifd2SasinariJyouken ||
                          orderInfo.correctBeforePriceIfd2Price != orderInfo.ifd2Price ||
                          orderInfo.correctBeforePriceIfd2TriggerPrice != orderInfo.ifd2TriggerPrice ||
                          orderInfo.correctBeforePriceIfd2GyakusasiHouhou != orderInfo.ifd2GyakusasiHouhou
                      }"
                    >{{ orderInfo.ifd2SasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.ifd2SasinariHouhou) : '-' }}</span>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 4px;">
                  <el-col>
                    <!-- 指値（訂正後） -->
                    <span
                      v-if="orderInfo.ifd2SasinariHouhou === '1'"
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceIfd2SasinariHouhou != orderInfo.ifd2SasinariHouhou ||
                          orderInfo.correctBeforePriceIfd2SasinariJyouken != orderInfo.ifd2SasinariJyouken ||
                          orderInfo.correctBeforePriceIfd2Price != orderInfo.ifd2Price
                      }"
                    >
                      <span v-if="orderInfo.ifd2SasinariJyouken !== ' '">{{
                        orderInfo.ifd2SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.ifd2SasinariJyouken) : '-'
                      }} / </span>
                      <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.ifd2Price?.trim())) }}円</span>
                    </span>
                    <!-- 逆指値（訂正後）-->
                    <span
                      v-else-if="orderInfo.ifd2SasinariHouhou === '3'"
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceIfd2SasinariHouhou != orderInfo.ifd2SasinariHouhou ||
                          orderInfo.correctBeforePriceIfd2SasinariJyouken != orderInfo.ifd2SasinariJyouken ||
                          orderInfo.correctBeforePriceIfd2Price != orderInfo.ifd2Price ||
                          orderInfo.correctBeforePriceIfd2TriggerPrice != orderInfo.ifd2TriggerPrice ||
                          orderInfo.correctBeforePriceIfd2GyakusasiHouhou != orderInfo.ifd2GyakusasiHouhou
                      }"
                    >
                      <span>現在値が{{ $_withCommaNoneZeroPadding(orderInfo.ifd2TriggerPrice?.trim()) }}円</span>
                      <span>{{ orderInfo.tradeCd === '4' ? '以上になった時点で' : '以下になった時点で' }}</span>
                      <span v-if="orderInfo.ifd2GyakusasiHouhou === '1' && orderInfo.ifd2SasinariJyouken !== ' '">{{ orderInfo.ifd2SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.ifd2SasinariJyouken) : '-' }}</span>
                      <span v-if="orderInfo.ifd2GyakusasiHouhou === '1'">{{ $_out($_withCommaNoneZeroPadding(orderInfo.ifd2Price?.trim())) }}円</span>
                      <span v-if="orderInfo.ifd2GyakusasiHouhou === '2'">{{ orderInfo.ifd2SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 1, orderInfo.ifd2SasinariJyouken) : '-' }}</span>
                      <span>で執行</span>
                    </span>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>

            <!-- 注文内容 OCO1 (注文種別が IFDOCO の時のみ表示)-->
            <el-row
              v-if="orderInfo.orderKind === '4' && !orderInfo.workingStatus"
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  orderInfo.correctBeforePriceOco1SasinariHouhou != orderInfo.oco1SasinariHouhou ||
                  orderInfo.correctBeforePriceOco1SasinariJyouken != orderInfo.oco1SasinariJyouken ||
                  orderInfo.correctBeforePriceOco1Price != orderInfo.oco1Price
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">価格／OCO1</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <!-- 価格/執行方法（訂正前） -->
                <el-row>
                  <el-col>
                    <span>{{ orderInfo.correctBeforePriceOco1SasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.correctBeforePriceOco1SasinariHouhou) : '-' }}</span>
                  </el-col>
                </el-row>
                <!-- 執行条件（訂正前） -->
                <el-row style="margin-top: 4px;">
                  <el-col>
                    <span v-if="orderInfo.correctBeforePriceOco1SasinariJyouken !== ' '">{{
                      orderInfo.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceOco1SasinariJyouken) : '-'
                    }} / </span>
                    <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.correctBeforePriceOco1Price?.trim())) }}円</span>
                  </el-col>
                </el-row>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <!-- 価格/執行方法（訂正後） -->
                <el-row>
                  <el-col>
                    <span
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceOco1SasinariHouhou != orderInfo.oco1SasinariHouhou ||
                          orderInfo.correctBeforePriceOco1SasinariJyouken != orderInfo.oco1SasinariJyouken ||
                          orderInfo.correctBeforePriceOco1Price != orderInfo.oco1Price
                      }"
                    >{{ orderInfo.oco1SasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.oco1SasinariHouhou) : '-' }}</span>
                  </el-col>
                </el-row>
                <!-- 執行条件（訂正後） -->
                <el-row style="margin-top: 4px;">
                  <el-col>
                    <span
                      :class="{
                        _bold_black_origin:
                          orderInfo.correctBeforePriceOco1SasinariHouhou != orderInfo.oco1SasinariHouhou ||
                          orderInfo.correctBeforePriceOco1SasinariJyouken != orderInfo.oco1SasinariJyouken ||
                          orderInfo.correctBeforePriceOco1Price != orderInfo.oco1Price
                      }"
                    >
                      <span v-if="orderInfo.oco1SasinariJyouken !== ' '">{{
                        orderInfo.oco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.oco1SasinariJyouken) : '-'
                      }} / </span>
                      <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.oco1Price?.trim())) }}円</span>
                    </span>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>

            <!-- 注文内容 OCO2 (注文種別が IFDOCO の時のみ表示)-->
            <el-row
              v-if="orderInfo.orderKind === '4'"
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  orderInfo.correctBeforePriceOco2TriggerPrice != orderInfo.oco2TriggerPrice ||
                  orderInfo.correctBeforePriceOco2GyakusasiHouhou != orderInfo.oco2GyakusasiHouhou ||
                  orderInfo.correctBeforePriceOco2GyakusasiJyouken != orderInfo.oco2GyakusasiJyouken ||
                  orderInfo.correctBeforePriceOco2Price != orderInfo.oco2Price
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">条件／OCO2</span>
              </el-col>
              <!-- 価格/執行方法（訂正前） -->
              <el-col
                :span="8"
                :offset="1"
              >
                <span>現在値が{{ $_withCommaNoneZeroPadding(orderInfo.correctBeforePriceOco2TriggerPrice?.trim()) }}円</span>
                <span v-if="orderInfo.correctBeforePriceOco2TriggerPriceZone === ' '">{{ orderInfo.tradeCd === '4' ? '以上になった時点で' : '以下になった時点で' }}</span>
                <span v-else>{{ orderInfo.correctBeforePriceOco2TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, orderInfo.correctBeforePriceOco2TriggerPriceZone) : '-' }}になった時点で</span>
                <span v-if="orderInfo.correctBeforePriceOco1SasinariJyouken === ' '">OCO1（指値）を</span>
                <span v-else>OCO1（{{ orderInfo.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceOco1SasinariJyouken) : '-' }}）を</span>
                <span v-if="orderInfo.correctBeforePriceOco2GyakusasiHouhou === '1' && orderInfo.correctBeforePriceOco2GyakusasiJyouken === ' '">{{ $_out($_withCommaNoneZeroPadding(orderInfo.correctBeforePriceOco2Price?.trim())) }}円</span>
                <span v-if="orderInfo.correctBeforePriceOco2GyakusasiHouhou === '1' && orderInfo.correctBeforePriceOco2GyakusasiJyouken !== ' '">{{ orderInfo.correctBeforePriceOco2GyakusasiJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceOco2GyakusasiJyouken) : '-' }} / {{ $_out($_withCommaNoneZeroPadding(orderInfo.correctBeforePriceOco2Price?.trim())) }}円</span>
                <span v-if="orderInfo.correctBeforePriceOco2GyakusasiHouhou === '2'">成行</span>
                <span>に訂正</span>
              </el-col>
              <!-- 価格/執行方法（訂正後） -->
              <el-col
                :span="8"
                :offset="1"
              >
                <span
                  :class="{
                    _bold_black_origin:
                      orderInfo.correctBeforePriceOco2TriggerPrice != orderInfo.oco2TriggerPrice ||
                      orderInfo.correctBeforePriceOco2GyakusasiHouhou != orderInfo.oco2GyakusasiHouhou ||
                      orderInfo.correctBeforePriceOco2GyakusasiJyouken != orderInfo.oco2GyakusasiJyouken ||
                      orderInfo.correctBeforePriceOco2Price != orderInfo.oco2Price
                  }"
                >
                  <span>現在値が{{ $_withCommaNoneZeroPadding(orderInfo.oco2TriggerPrice?.trim()) }}円</span>
                  <span>{{ orderInfo.tradeCd === '4' ? '以上になった時点で' : '以下になった時点で' }}</span>
                  <span v-if="orderInfo.correctBeforePriceOco1SasinariJyouken === ' '">OCO1（指値）を</span>
                  <span v-else>OCO1（{{ orderInfo.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.correctBeforePriceOco1SasinariJyouken) : '-' }}）を</span>
                  <span v-if="orderInfo.oco2GyakusasiHouhou === '1' && orderInfo.oco2GyakusasiJyouken === ' '">{{ $_out($_withCommaNoneZeroPadding(orderInfo.oco2Price?.trim())) }}円</span>
                  <span v-if="orderInfo.oco2GyakusasiHouhou === '1' && orderInfo.oco2GyakusasiJyouken !== ' '">{{ orderInfo.oco2GyakusasiJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.oco2GyakusasiJyouken) : '-' }} / {{ $_out($_withCommaNoneZeroPadding(orderInfo.oco2Price?.trim())) }}円</span>
                  <span v-if="orderInfo.oco2GyakusasiHouhou === '2'">成行</span>
                  <span>に訂正</span>
                </span>
              </el-col>
            </el-row>

            <!-- 期間 -->
            <el-row
              class="dotted_border"
              :class="{ 'correction-bg-color': orderInfo.yukoKigenChangeFlag === '1' }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">注文期間</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out($_getFormattedDateValue(orderInfo.ifd2Limit, 'date8')) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span :class="{ _bold_black_origin: orderInfo.yukoKigenChangeFlag === '1' }">{{ orderInfo.yukoKigenChangeFlag === '1' ? $_out($_getFormattedDateValue(orderInfo.yukoKigen, 'date8')) : $_out($_getFormattedDateValue(orderInfo.ifd2Limit, 'date8')) }}</span>
              </el-col>
            </el-row>
            <!-- 信用取引区分 -->
            <el-row
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">信用取引区分</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out(orderInfo.marginTradeTypeText) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out(orderInfo.marginTradeTypeText) }}</span>
              </el-col>
            </el-row>
            <!-- 手数料区分 -->
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
                <span>{{ $_out($_getCodeValue('COMM_TYPE', 2, orderInfo.comId)) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out($_getCodeValue('COMM_TYPE', 2, orderInfo.comId)) }}</span>
              </el-col>
            </el-row>

          </el-card>
        </el-col>
      </el-row>

      <el-row>
        <div style="margin-bottom: 0.5rem;"></div>
      </el-row>

      <!-- その他注文内容エリア -->
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
              style="padding-left: 1rem;"
            >
              <span>その他注文内容</span>
            </el-row>
            <hr>
            <!-- 訂正後建玉余力 -->
            <el-row
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">訂正後建玉余力</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <span>
                  {{ orderInfo.afterCorrectPositionPower ? orderInfo.afterCorrectPositionPower?.trim() == '0' ? '-' : $_withCommaNoneZeroPadding(orderInfo.afterCorrectPositionPower?.trim()) : '-' }} 円
                </span>
              </el-col>
            </el-row>
            <!-- 約定予定日 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">約定予定日</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <span>{{ $_out($_getFormattedDateValue(orderInfo.contractDate, 'date8')) }}</span>
              </el-col>
            </el-row>
            <!-- 受渡予定日 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">受渡予定日</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <span>{{ $_out($_getFormattedDateValue(orderInfo.deliveryDate, 'date8')) }}</span>
              </el-col>
            </el-row>
            <!-- 受注日時 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">受注日時</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <span>{{ $_out($_getFormattedDateTimeValue(orderInfo.orderDayTime, 'datetime12')) }}</span>
              </el-col>
            </el-row>
            <!-- 勧誘区分 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">勧誘区分</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <span>{{ orderInfo.kanyuKbn ? $_getCodeValue('INVITATION_TYPE', 1, orderInfo.kanyuKbn) : '-' }}</span>
              </el-col>
            </el-row>
            <!-- 受注方法 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">受注方法</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <span>{{ orderInfo.orderMethod ? $_getCodeValue('ORDER_METHOD_TYPE', 1, orderInfo.orderMethod) : '-' }}</span>
              </el-col>
            </el-row>
            <!-- 確認項目 -->
            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">確認項目</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <span>{{ orderInfo.checkInsiderConfirmCheckBox ? $_getCodeValue('INSIDER_CONFIRM', 3, orderInfo.checkInsiderConfirmCheckBox) : '-' }}</span>
              </el-col>
              <el-col :span="6">
              </el-col>
              <el-col v-if="orderInfo.afterCorrectMarket === 'A'" 
                :disabled="false"
                :span="16"
                :offset="1"
              >
                <span>{{ orderInfo.checkSor ? $_getCodeValue('SOR_CONFIRM', 2, orderInfo.checkSor) : '-' }}</span>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>

      <el-row>
        <div style="margin-bottom: 0.5rem;"></div>
      </el-row>

      <!-- アラート内容確認エリア -->
      <el-row v-if="orderInfo.tradeNoticeInfoBrandMsg ||
        orderInfo.noticeInfoAlert ||
        orderInfo.noticeAlert ||
        orderInfo.shortSellingRegulationsMessage"
      >
        <el-col
          :span="22"
          :offset="1"
          style="padding-top: 0.3rem; color: #f00;"
        >
          <el-card
            :style="(orderInfo.orderKind === '3' || orderInfo.orderKind === '4') ? 'background-color: #E7DCF2' : orderInfo.tradeCd === '3' ? 'background-color: #fef0f0' : 'background-color: #ecf5ff'"
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
            <!-- 取引注意情報（銘柄） -->
            <el-row
              v-if="orderInfo.tradeNoticeInfoBrandMsg"
              class="dotted_border"
              style="align-items: center;"
            >
              <el-col
                :span="6"
                class="_bold_black_m"
                style="padding-left: 0;"
              >
                <div class="required-mark">*</div><span style="color: #f00;">取引注意情報の説明</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <ifa-input-check
                  v-model="formModel.tradeNoticeInfoBrandConfirm"
                  code-list-id="TRADE_NOTICE_INFO_EXPLAIN"
                  :disp-pattern="1"
                  :select-pattern="2"
                  label-class="checkClass"
                ></ifa-input-check>
              </el-col>
            </el-row>

            <!-- 注意情報アラート -->
            <el-row
              v-if="orderInfo.noticeInfoAlert"
              class="dotted_border"
              style="align-items: center;"
            >
              <el-col
                :span="6"
                class="_bold_black_m"
                style="padding-left: 0;"
              >
                <div class="required-mark">*</div><span style="color: #f00;">注意情報の確認</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <ifa-input-check
                  v-model="formModel.noticeInfoAlertConfirm"
                  code-list-id="NOTICE_INFO_CONFIRM"
                  :disp-pattern="1"
                  :select-pattern="2"
                  label-class="checkClass"
                ></ifa-input-check>
              </el-col>
            </el-row>

            <!-- お知らせアラート -->
            <el-row
              v-if="orderInfo.noticeAlert"
              class="dotted_border"
              style="align-items: center;"
            >
              <el-col
                :span="6"
                class="_bold_black_m"
                style="padding-left: 0;"
              >
                <div class="required-mark">*</div><span style="color: #f00;">重要なお知らせの確認</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <ifa-input-check
                  v-model="formModel.noticeAlertConfirm"
                  code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                  :disp-pattern="1"
                  :select-pattern="2"
                  label-class="checkClass"
                ></ifa-input-check>
              </el-col>
            </el-row>

            <!-- 空売り規制確認 -->
            <el-row
              v-if="orderInfo.shortSellingRegulationsMessage"
              class="dotted_border"
            >
              <el-col
                :span="6"
                class="_bold_black_m"
                style="padding-left: 0; margin-top:7px"
              >
                <div class="required-mark">*</div><span style="color: #f00;">空売り規制取引</span>
              </el-col>
              <el-col
                :span="16"
                :offset="1"
              >
                <ifa-input-check
                  v-model="formModel.shortSellingRegulationsConfirm"
                  code-list-id="original"
                  :original-list="shortSellingList"
                  label-class="checkClass1"
                ></ifa-input-check>
                <el-row v-if="orderInfo.shortSellingRegulationsMessage"
                        style="margin-top: 2.5rem; margin-left: 14px;"
                >
                  <ifa-link
                    :disp-name="'空売り規制についてはこちら'"
                    param-url="https://search.sbisec.co.jp/v2/popwin/attention/trading/stock_13.html"
                    link-icon-image="externalLink"
                  ></ifa-link>
                </el-row>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>

      <!-- 操作エリア -->
      <el-row style="margin-top: 20px;">
        <el-col
          :offset="1"
          :span="22"
          style="text-align: left;"
        >
          <ifa-button
            id="btnCorrectionOrder"
            text="注文訂正"
            :disabled="buttonDisabled"
            style="padding-left: 0;"
            action-type="requestAction"
            :request-model="IfaMarginNewOrderCorrectConfirmA001RequestModel"
            action-id="SUB0202_0212-02_2#A001"
            @response-handler="correctionOrderA001($event)"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaMarginNewOrderCorrectConfirmFormModel } from './js/IfaMarginNewOrderCorrectConfirmFormModel'
import { IfaMarginNewOrderCorrectConfirmA001RequestModel } from './js/IfaMarginNewOrderCorrectConfirmA001RequestModel'
export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    orderInfo: { type: Object, required: true }
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
      formModel: new IfaMarginNewOrderCorrectConfirmFormModel(),
      messages: {
        mains: [],
        errors: [],
        warnings: [],
        infos: []
      }
    }
  },
  computed: {
    shortSellingList() {
      return [{ key: '0', value: this.$_getCodeValue('SHORT_SELLING_REGULATION_CONFIRM', 1, '1').replace('$CRLF', '\n') }, { key: '1', value: this.$_getCodeValue('SHORT_SELLING_REGULATION_CONFIRM', 1, '1').replace('$CRLF', '\n') }]
    },
    IfaMarginNewOrderCorrectConfirmA001RequestModel() {
      const ifaMarginNewOrderCorrectConfirmA001RequestModel = new IfaMarginNewOrderCorrectConfirmA001RequestModel(this.formModel)
      this.$_logDebug(ifaMarginNewOrderCorrectConfirmA001RequestModel)
      return ifaMarginNewOrderCorrectConfirmA001RequestModel
    },
    CorrectionBg() {
      if (this.orderInfo.afterCorrectPositionPower) {
        if (this.orderInfo.afterCorrectPositionPower?.trim() !== '0') {
          return true
        }
      }
      return false
    },
    buttonDisabled() {
      if (this.userInfo.medUsers.privId === '1' || this.userInfo.medUsers.privId === '2') {
        return true
      }
      if (this.orderInfo.tradeNoticeInfoBrandMsg && this.formModel.tradeNoticeInfoBrandConfirm !== '1') {
        return true
      }
      if (this.orderInfo.noticeInfoAlert && this.formModel.noticeInfoAlertConfirm !== '1') {
        return true
      }
      if (this.orderInfo.noticeAlert && this.formModel.noticeAlertConfirm !== '1') {
        return true
      }
      if (this.orderInfo.shortSellingRegulationsMessage && this.formModel.shortSellingRegulationsConfirm !== '1') {
        return true
      }
      return false
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    userInfo() {
      return this.$store.getters.userAccount
    }
  },
  methods: {
    stock13Url() {
      const url = 'https://search.sbisec.co.jp/v2/popwin/attention/trading/stock_13.html'
      return url
    },
    onOpen() {
      Object.assign(this.formModel, this.orderInfo)

      this.messages.mains.length = 0
      this.messages.mains.push('訂正はまだ完了していません。画面下の注文訂正ボタンを押下してください。')

      this.messages.errors.length = 0
      if (this.orderInfo.tradeNoticeInfoBrandMsg) {
        this.messages.errors.push(this.orderInfo.tradeNoticeInfoBrandMsg)
      }
      if (this.orderInfo.noticeInfoAlert) {
        this.messages.errors.push(this.orderInfo.noticeInfoAlert)
      }
      if (this.orderInfo.noticeAlert) {
        this.messages.errors.push(this.orderInfo.noticeAlert)
      }
      if (this.orderInfo.shortSellingRegulationsMessage) {
        this.messages.errors.push(this.orderInfo.shortSellingRegulationsMessage)
      }
      this.messages.warnings.length = 0
      this.messages.infos.length = 0
      if (this.orderInfo.code.startsWith('W')) {
        this.messages.infos.push(this.orderInfo.errMessage + '（' + this.orderInfo.code + '）')
      }
      this.formModel.ifd2PeriodTerm = this.orderInfo.ifd2Limit
      this.formModel.tradeNoticeInfoBrandConfirm = '0'
      this.formModel.noticeInfoAlertConfirm = '0'
      this.formModel.noticeAlertConfirm = '0'
      this.formModel.shortSellingRegulationsConfirm = '0'
      this.$_logDebug(this.formModel)
      
      // 訂正SOR注文結果区分≠"1"：SOR訂正注文以外の場合、市場（訂正後）を市場（訂正前）と同じにする
      if (this.orderInfo.correctSorOrderResultClassification !== '1') {
        this.orderInfo.afterCorrectMarket = this.orderInfo.market
        this.formModel.afterCorrectMarket = this.orderInfo.market
      }

      // 取引注意情報リンクの初期化
      this.$nextTick(() => {
        this.$refs['tradeLimitRef']?.trigger()
        this.$refs['tradeLimitIfdRef']?.trigger()
      })
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 注文訂正ボタン
    correctionOrderA001(response) {
      this.$emit('order-finish', response)
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.required-mark {
  display: inline-block;
  color: #ff2b2b;
  width: 8px;
}
:deep(.checkClass label>.el-checkbox__label){
  color: #f00 !important;
  font-size: 16px;
  white-space: pre-wrap;
  font-weight: bold;
}
:deep(.checkClass1 label>.el-checkbox__label){
  color: #f00 !important;
  font-size: 16px;
  white-space: pre-wrap;
  margin-top: 44px;
  line-height: normal;
  font-weight: bold;
}
.footer_button {
  margin-top: 1rem;
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
._bold_black_origin {
  font-size: 16px;
  font-weight: 700;
  color: #18181A;
  padding-bottom: 0.5rem;
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
