<template>
  <el-dialog
    v-model="vmIsVisible"
    class="margin_new_order_comp"
    title="信用新規注文訂正完了"
    width="1200px"
    :style="(orderInfo.orderKind === '3' || orderInfo.orderKind === '4') ? 'background-color: #E7DCF2' : orderInfo.tradeCd === '3' ? 'background-color: #fef0f0' : 'background-color: #ecf5ff'"
    :show-close="false"
    :center="true"
    :before-close="moveToOrderList"
    :close-on-click-modal="false"
    @open="onOpen"
  >
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
        <span>{{ $_out(customerInfo().butenCode) + '-' + $_out($_zeroPadding(customerInfo().accountNumber,7)) }}</span>
      </el-col>
    </el-row>
    <el-row
      style="padding-top: 0.3rem; width: 84%; margin: 0rem 0rem 0rem 1rem"
      class="_bold_black_l"
    >
      <el-col
        :offset="1"
        style="font-size: 20px;"
        class="_bold_black_l"
      >
        <span v-if="customerInfo().corporationType === '1'">
          <el-icon style="vertical-align:top"><OfficeBuilding></OfficeBuilding></el-icon>
        </span>
        <span v-else>
          <el-icon style="vertical-align:top"><avatar></avatar></el-icon>
        </span>
        <span style="vertical-align: top;">{{ $_out(customerInfo().customerNameKanji) + ' (' + $_out(customerInfo().customerNameKana) + ')' }}</span>
        <ifa-notice-info
          :notice-info-level="customerInfo().noticeInfoLevel"
          :customer-code="customerInfo().customerCode"
          :buten-code="customerInfo().butenCode"
          :account-number="customerInfo().accountNumber"
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
            style="padding-top: 0.2rem; padding-left: 1rem"
          >
            <span>ご注文内容</span><span
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
              style="font-weight: bold;"
            >
              <span>{{ orderInfo.correctBeforePriceSasinariHouhou === '3' ? '逆指値注文' : orderInfo.orderKind ? $_getCodeValue('ORDER_CLASS', 2, orderInfo.orderKind) : '-' }}</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
              style="font-weight: bold;"
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
              <span>{{$_out($_getCodeValue('SELECT_MARKET', 1, orderInfo.afterCorrectMarket)) }}</span>
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
                  <span>{{ orderInfo.sasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.sasinariHouhou) : '-' }}</span>
                </el-col>
              </el-row>
              <el-row style="margin-top: 4px;">
                <el-col>
                  <!-- 指値（訂正後） -->
                  <span
                    v-if="orderInfo.sasinariHouhou === '1'"
                  >
                    <span v-if="orderInfo.sasinariJyouken !== ' '">{{
                      orderInfo.sasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.sasinariJyouken) : '-'
                    }} / </span>
                    <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.price?.trim())) }}円</span>
                  </span>
                  <!-- 成行（訂正後） -->
                  <span v-else-if="orderInfo.sasinariHouhou === '2'">
                    <span v-if="orderInfo.sasinariJyouken !== 'N'">{{
                      orderInfo.sasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 3, orderInfo.sasinariJyouken) : '-'
                    }}</span>
                  </span>
                  <!-- 逆指値（訂正後）-->
                  <span v-else-if="orderInfo.sasinariHouhou === '3'">
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
                  <span>{{ orderInfo.oco1SasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.oco1SasinariHouhou) : '-' }}</span>
                </el-col>
              </el-row>
              <!-- 執行条件（訂正後） -->
              <el-row style="margin-top: 4px;">
                <el-col>
                  <span>
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
              <span>
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
                  <span>{{ orderInfo.ifd1SasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.ifd1SasinariHouhou) : '-' }}</span>
                </el-col>
              </el-row>
              <el-row style="margin-top: 4px;">
                <el-col>
                  <!-- 指値（訂正後） -->
                  <span
                    v-if="orderInfo.ifd1SasinariHouhou === '1'"
                  >
                    <span v-if="orderInfo.ifd1SasinariJyouken !== ' '">{{
                      orderInfo.ifd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.ifd1SasinariJyouken) : '-'
                    }} / </span>
                    <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.ifd1Price?.trim())) }}円</span>
                  </span>
                  <!-- 成行（訂正後） -->
                  <span
                    v-else-if="orderInfo.ifd1SasinariHouhou === '2'"
                  >
                    <span v-if="orderInfo.ifd1SasinariJyouken !== 'N'">{{
                      orderInfo.ifd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 3, orderInfo.ifd1SasinariJyouken) : '-'
                    }}</span>
                  </span>
                  <!-- 逆指値（訂正後）-->
                  <span
                    v-else-if="orderInfo.ifd1SasinariHouhou === '3'"
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
              <span>{{ orderInfo.yukoKigenChangeFlag === '1' ? $_out($_getFormattedDateValue(orderInfo.yukoKigen, 'date8')) : $_out($_getFormattedDateValue(orderInfo.period, 'date8')) }}</span>
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
            style="padding-top: 0.2rem"
          >
            <span>ご注文内容</span><span
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
              style="font-weight: bold;"
            >
              <span>{{ orderInfo.correctBeforePriceSasinariHouhou === '3' ? '逆指値注文' : orderInfo.orderKind ? $_getCodeValue('ORDER_CLASS', 2, orderInfo.orderKind) : '-' }}</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
              style="font-weight: bold;"
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
                  <span>{{ orderInfo.ifd2SasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.ifd2SasinariHouhou) : '-' }}</span>
                </el-col>
              </el-row>
              <el-row style="margin-top: 4px;">
                <el-col>
                  <!-- 指値（訂正後） -->
                  <span
                    v-if="orderInfo.ifd2SasinariHouhou === '1'"
                  >
                    <span v-if="orderInfo.ifd2SasinariJyouken !== ' '">{{
                      orderInfo.ifd2SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, orderInfo.ifd2SasinariJyouken) : '-'
                    }} / </span>
                    <span>{{ $_out($_withCommaNoneZeroPadding(orderInfo.ifd2Price?.trim())) }}円</span>
                  </span>
                  <!-- 逆指値（訂正後）-->
                  <span
                    v-else-if="orderInfo.ifd2SasinariHouhou === '3'"
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
                  <span>{{ orderInfo.oco1SasinariHouhou ? $_getCodeValue('EXECUTE_METHOD', 1, orderInfo.oco1SasinariHouhou) : '-' }}</span>
                </el-col>
              </el-row>
              <!-- 執行条件（訂正後） -->
              <el-row style="margin-top: 4px;">
                <el-col>
                  <span>
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
              <span>
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
              <span>{{ orderInfo.yukoKigenChangeFlag === '1' ? $_out($_getFormattedDateValue(orderInfo.yukoKigen, 'date8')) : $_out($_getFormattedDateValue(orderInfo.ifd2Limit, 'date8')) }}</span>
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
            style="padding-top: 0.2rem; padding-left: 1rem"
          >
            <span>その他注文内容</span>
          </el-row>
          <hr>
          <!-- 訂正後建玉余力 -->
          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">訂正後建玉余力</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ orderInfo.afterCorrectPositionPower ? orderInfo.afterCorrectPositionPower?.trim() == '0' ? '-' : $_withCommaNoneZeroPadding(orderInfo.afterCorrectPositionPower?.trim()) : '-' }} 円</span>
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
        style="padding-top: 0.3rem;"
      >
        <el-card class="box-card"
                 style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.2rem; padding-left: 1rem"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <!-- 取引注意情報（銘柄） -->
          <el-row
            v-if="orderInfo.tradeNoticeInfoBrandMsg"
            class="dotted_border"
          >
            <el-col :span="6"
                    class="_bold_black_m"
                    style="padding-bottom: 0;"
            >
              <span>取引注意情報の説明</span>
            </el-col>
            <el-col :span="16"
                    :offset="1"
            >
              <span>{{ $_out($_getCodeValue('TRADE_NOTICE_INFO_EXPLAIN', 1, orderInfo.tradeNoticeInfoBrandConfirm)) }}</span>
            </el-col>
          </el-row>

          <!-- 注意情報アラート -->
          <el-row
            v-if="orderInfo.noticeInfoAlert"
            class="dotted_border"
          >
            <el-col
              :span="6"
              class="_bold_black_m"
              style="padding-bottom: 0;"
            >
              <span>注意情報の確認</span>
            </el-col>
            <el-col :span="16"
                    :offset="1"
            >
              <span>{{ $_out($_getCodeValue('NOTICE_INFO_CONFIRM', 1, orderInfo.noticeInfoAlertConfirm)) }}</span>
            </el-col>
          </el-row>

          <!-- お知らせアラート -->
          <el-row
            v-if="orderInfo.noticeAlert"
            class="dotted_border"
          >
            <el-col
              :span="6"
              class="_bold_black_m"
              style="padding-bottom: 0;"
            >
              <span>重要なお知らせの確認</span>
            </el-col>
            <el-col :span="16"
                    :offset="1"
            >
              <span>{{ $_out($_getCodeValue('IMPORTANT_NOTIFICATION_CONFIRM', 1, orderInfo.noticeAlertConfirm)) }}</span>
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
              style="padding-bottom: 0;"
            >
              <span>空売り規制取引</span>
            </el-col>
            <el-col :span="16"
                    :offset="1"
            >
              <span>{{ $_out($_getCodeValue('SHORT_SELLING_REGULATION_CONFIRM', 2, orderInfo.shortSellingRegulationsConfirm)) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文状況一覧へボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="margin-top: 1rem"
      >
        <ifa-button
          text="注文状況一覧へ"
          color="primary"
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
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    orderInfo: { type: Object, required: true }
  },
  emits: ['move-to-order-list', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      messages: {
        mains: [],
        errors: [],
        warnings: [],
        infos: []
      }
    }
  },
  methods: {
    onOpen() {
      this.$_logDebug(this.orderInfo)
      this.messages.mains.length = 0
      this.messages.mains.push('下記の内容で訂正を受け付けました。')
      this.messages.errors.length = 0
      this.messages.warnings.length = 0
      this.messages.infos.length = 0
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    // 注文一覧へ遷移
    moveToOrderList() {
      this.$emit('move-to-order-list')
    },
    handleClick() {
      this.moveToOrderList()
    },
    getTrade: function(tradeKbn) {
      if (tradeKbn === '0') {
        return '現物買'
      } else if (tradeKbn === '1') {
        return '現物売'
      } else if (tradeKbn === '2') {
        return '新規買'
      } else if (tradeKbn === '3') {
        return '新規売'
      } else if (tradeKbn === '4') {
        return '返済買'
      } else if (tradeKbn === '5') {
        return '返済売'
      } else if (tradeKbn === '6') {
        return '現引'
      } else if (tradeKbn === '7') {
        return '現渡'
      }
    },
    getOrderKind: function(orderKindKbn) {
      if (orderKindKbn === '0') {
        return '通常注文'
      } else if (orderKindKbn === '1') {
        return '逆指値注文'
      } else if (orderKindKbn === '2' || orderKindKbn === '5') {
        return 'OCO注文'
      } else if (orderKindKbn === '3') {
        return 'IFD注文'
      } else if (orderKindKbn === '4') {
        return 'IFDOCO注文'
      } else return '-'
    },
    getAccountType: function(accountTypeKbn) {
      if (accountTypeKbn === '0') {
        return '総合:NISA'
      } else if (accountTypeKbn === '1') {
        return '総合:特定'
      } else if (accountTypeKbn === '2') {
        return '総合:一般'
      } else if (accountTypeKbn === '3') {
        return 'ジュニアNISA:NISA'
      } else if (accountTypeKbn === '4') {
        return 'ジュニアNISA:特定'
      } else if (accountTypeKbn === '5') {
        return 'ジュニアNISA:一般'
      }
    },
    getMarket: function(marketKbn) {
      if (marketKbn === '0') {
        return 'SOR'
      } else if (marketKbn === '1') {
        return '東証'
      } else if (marketKbn === '2') {
        return '名証'
      } else if (marketKbn === '3') {
        return '福証'
      } else if (marketKbn === '4') {
        return '札証'
      } else if (marketKbn === '5') {
        return '単元未満'
      } else if (marketKbn === '6') {
        return 'PTS'
      } else return '-'
    },
    getMediationKubun: function() {
      if (this.orderInfo.mediationKubun === false) {
        return 'なし'
      } else if (this.orderInfo.mediationKubun === true) {
        return 'あり'
      }
    },
    getLimitOrderConditionOptions: function(limitOrderConditionOptions) {
      if (limitOrderConditionOptions === '0') {
        return '条件なし'
      } else if (limitOrderConditionOptions === '1') {
        return '寄指(Y)'
      } else if (limitOrderConditionOptions === '2') {
        return '引指(H)'
      } else if (limitOrderConditionOptions === '3') {
        return '不成(F)'
      } else if (limitOrderConditionOptions === '4') {
        return 'IOC指(I)'
      }
    },
    getMarketOrderConditionOptions: function(marketOrderConditionOptions) {
      if (marketOrderConditionOptions === '0') {
        return '条件なし'
      } else if (marketOrderConditionOptions === '1') {
        return '寄指(Y)'
      } else if (marketOrderConditionOptions === '2') {
        return '引指(H)'
      } else if (marketOrderConditionOptions === '3') {
        return 'IOC指(I)'
      }
    },
    getStopOrderCondition1Options: function(stopOrderCondition1Options) {
      if (stopOrderCondition1Options === '0') {
        return '条件なし'
      } else if (stopOrderCondition1Options === '1') {
        return '引指(H)'
      } else if (stopOrderCondition1Options === '2') {
        return '不成(F)'
      }
    },
    getStopOrderCondition2Options: function(stopOrderCondition2Options) {
      if (stopOrderCondition2Options === '0') {
        return '条件なし'
      } else if (stopOrderCondition2Options === '1') {
        return '引指(H)'
      }
    },
    getStopOrderCondition3Options: function(stopOrderCondition3Options) {
      if (stopOrderCondition3Options === '0') {
        return '条件なし'
      } else if (stopOrderCondition3Options === '1') {
        return '引指(H)'
      }
    },
    getExecution: function(execution) {
      if (execution === '0') {
        return '指値'
      } else if (execution === '1') {
        return '成行'
      } else if (execution === '2') {
        return '逆指値'
      }
    },
    getPriceMessage1: function(data, time) {
      let message = '現在値が' + data.stopOrderPrice + '円' +
                    (data.trade === '0' ? '以上' : '以下') + 'になった時点で'
      if (data.stopOrderMethod === '0') {
        message = message +
                  (data.stopOrderCondition1 === '0' ? this.getExecution(data.stopOrderMethod) : this.getStopOrderCondition1Options(data.stopOrderCondition1)) +
                  data.stopOrderExecutePrice + '円で執行'
      } else if (data.stopOrderMethod === '1') {
        message = message +
                  this.getExecution(data.stopOrderMethod) +
                  this.getStopOrderCondition2Options(data.stopOrderCondition2) + 'で執行'
      }
      if (time === 'after') {
        this.afterPriceMessage1 = message
      } else if (time === 'before') {
        this.beforePriceMessage1 = message
      }
      return message
    },
    getPriceMessage2: function(data, time) {
      let message = '現在値が' + data.oco2.stopOrderPrice + '円' +
                    (data.trade === '0' ? '以上' : '以下') + 'になった時点で'
      if (data.oco2.stopOrderMethod === '0') {
        message = message +
                  (data.oco2.stopOrderCondition1 === '0' ? this.getExecution(data.oco2.stopOrderMethod) : this.getStopOrderCondition1Options(data.oco2.stopOrderCondition1)) +
                  data.oco2.stopOrderExecutePrice + '円で執行'
      } else if (data.oco2.stopOrderMethod === '1') {
        message = message +
                  this.getExecution(data.oco2.stopOrderMethod) +
                  (data.oco2.stopOrderCondition2 !== '0' ? this.getStopOrderCondition2Options(data.oco2.stopOrderCondition2) : '') +
                  'で執行'
      }
      if (time === 'after') {
        this.afterPriceMessage2 = message
      } else if (time === 'before') {
        this.beforePriceMessage2 = message
      }
      return message
    },
    getPriceMessage3: function(data, time) {
      let message = '現在値が' + data.stopOrderPrice + '円' +
                    (data.trade === '0' ? '以上' : '以下') + 'になった時点で'
      if (data.stopOrderMethod === '0') {
        message = message +
                  (data.stopOrderCondition1 === '0' ? this.getExecution(data.stopOrderMethod) : this.getStopOrderCondition1Options(data.stopOrderCondition1)) +
                  data.stopOrderExecutePrice + '円で執行'
      } else if (data.stopOrderMethod === '1') {
        message = message +
                  this.getExecution(data.stopOrderMethod) +
                  (data.stopOrderCondition2 !== '0' ? this.getStopOrderCondition2Options(data.stopOrderCondition2) : '') +
                  'で執行'
      }
      if (time === 'after') {
        this.afterPriceMessage3 = message
      } else if (time === 'before') {
        this.beforePriceMessage3 = message
      }
      return message
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
  .margin_new_order_comp {
  .el-dialog__title{
    font-weight: bold;
  }
}
  .domestic_stock_order_correct {
  .el-card__body {
    padding: 5px !important;
  }
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
</style>
<style lang="scss" scoped>
:deep(.el-text){
  font-size: 16px;
}
</style>
