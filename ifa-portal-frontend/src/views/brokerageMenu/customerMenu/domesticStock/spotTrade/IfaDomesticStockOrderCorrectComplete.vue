<template>
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title.name"
    :show-close="false"
    :center="true"
    :before-close="toOrderStatusListA001"
    :close-on-click-modal="false"
    :class="classObject.classObjBgColor"
    :close-on-press-escape="false"
    width="1200px"
    @open="onShow"
  >
    <!-- ヘッダ -->
    <el-row style="margin-top: 40px;"></el-row>
    <ifa-message-area
      :main-messages="['下記の内容で訂正を受け付けました。']"
    ></ifa-message-area>
    <!-- 顧客情報 -->
    <el-row
      style="font-weight: bold; color: black;"
    >
      <el-col
        :offset="1"
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
          style="position: relative; top: 3.25px;"
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
              style="padding-top: 0.2rem; padding-left: 1rem"
            >
              <span>ご注文内容</span>
              <!--IFD、IFDOCOの時はラベル名変更-->
              <span v-if="completeData.orderKind === '3' || completeData.orderKind === '4'">：IFD1</span>
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
            <hr style="width: 100%;">

            <el-row class="dotted_border">
              <el-col :span="6">
                <span class="_bold_black_m">[銘柄コード]&nbsp;銘柄名</span>
              </el-col>
              <el-col
                :span="12"
                :offset="1"
              >
                <span style="overflow-wrap: break-word;">[{{ $_out(completeData.brandCode) }}]&nbsp;{{ $_out(completeData.brandName) }}</span>
              </el-col>
              <el-col
                v-if="completeData.regKbn"
                :span="5"
                style="text-align: center;"
              >
                <el-icon
                  style="color: red;  vertical-align: middle;"
                ><WarningFilled></WarningFilled></el-icon>
                <ifa-link
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
                style="font-weight: bold;"
              >
                <span v-if="completeData.beforeCorrectPrice[0].sasinariHouhou === '3'">逆指値注文</span>
                <ifa-text
                  v-else
                  code-list-id="ORDER_CLASS"
                  :disp-pattern="2"
                  :code-key="completeData.orderKind"
                ></ifa-text>
                <span v-if="!completeData.orderKind">-</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
                style="font-weight: bold;"
              >
                <span v-if="completeData.beforeCorrectPrice[0].sasinariHouhou === '3'">逆指値注文</span>
                <ifa-text
                  v-else
                  code-list-id="ORDER_CLASS"
                  :disp-pattern="2"
                  :code-key="completeData.orderKind"
                ></ifa-text>
                <span v-if="!completeData.orderKind">-</span>
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
                  code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                  :disp-pattern="1"
                  :code-key="completeData.tradeCd"
                ></ifa-text>
                <span v-if="!completeData.tradeCd">-</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  :class="classObject.classObjFontColor"
                  code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                  :disp-pattern="1"
                  :code-key="completeData.tradeCd"
                ></ifa-text>
                <span v-if="!completeData.tradeCd">-</span>
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
                  code-list-id="SELECT_MARKET"
                  :disp-pattern="1"
                  :code-key="completeData.market"
                ></ifa-text>
                <span v-if="!completeData.market">-</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  code-list-id="SELECT_MARKET"
                  :disp-pattern="1"
                  :code-key="completeData.afterCorrectMarket"
                ></ifa-text>
                <span v-if="!completeData.afterCorrectMarket">-</span>
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
                <span>{{ $_out($_withCommaInteger(completeData.unTradeQuantity)) }}</span>
                <span v-if="completeData.unTradeQuantity">株</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ $_out($_withCommaInteger(completeData.unTradeQuantity)) }}</span>
                <span v-if="completeData.unTradeQuantity">株</span>
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
                <span>{{ $_out($_withCommaInteger(completeData.quantity)) }}</span>
                <span v-if="completeData.quantity">株</span>
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
            v-if="completeData.orderKind === '1' || completeData.orderKind === '3' || completeData.orderKind === '4'"
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">価格</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span
                v-if="completeData.orderKind === '1'"
                style="white-space: pre-wrap;"
              >
                <ifa-text
                  code-list-id="EXECUTE_METHOD"
                  :disp-pattern="1"
                  :code-key="completeData.beforeCorrectPrice[0].sasinariHouhou"
                ></ifa-text>
                {{ getPrice(correctPriceParam, completeData.tradeCd) }}</span>
              <span
                v-if="(completeData.orderKind === '3' || completeData.orderKind === '4')"
                style="white-space: pre-wrap;"
              >
                <ifa-text
                  code-list-id="EXECUTE_METHOD"
                  :disp-pattern="1"
                  :code-key="completeData.beforeCorrectPrice[0].ifd1SasinariHouhou"
                ></ifa-text>
                {{ getPrice(correctIfd1PriceParam, completeData.tradeCd) }}</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span
                v-if="completeData.orderKind === '1'"
                style="white-space: pre-wrap;"
              >
                <ifa-text
                  code-list-id="EXECUTE_METHOD"
                  :disp-pattern="1"
                  :code-key="completeData.sasinariHouhou"
                ></ifa-text>
                {{ getPrice(priceParam, completeData.tradeCd) }}</span>
              <span
                v-if="(completeData.orderKind === '3' || completeData.orderKind === '4')"
                style="white-space: pre-wrap;"
              >
                <ifa-text
                  code-list-id="EXECUTE_METHOD"
                  :disp-pattern="1"
                  :code-key="completeData.ifd1SasinariHouhou"
                ></ifa-text>
                {{ getPrice(ifd1PriceParam, completeData.tradeCd) }}</span>
            </el-col>
          </el-row>

          <!--空のレスポンスが返ってきたときに表示-->
          <el-row v-if="!completeData.orderKind">
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
          <el-row v-if="completeData.orderKind === '2'">
            <el-row
              v-if="completeData.workingStatus === 'false'"
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">価格／OCO1</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span style="white-space: pre-wrap;">
                  {{ getOco1Price(completeData.beforeCorrectPrice[0].oco1SasinariHouhou, completeData.beforeCorrectPrice[0].oco1SasinariJyouken, completeData.beforeCorrectPrice[0].oco1Price) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span style="white-space: pre-wrap;">
                  {{ getOco1Price(completeData.oco1SasinariHouhou, completeData.oco1SasinariJyouken, completeData.oco1Price) }}</span>
              </el-col>
            </el-row>

            <el-row
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">条件／OCO2</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span style="white-space: pre-wrap;">{{ getOco2Price(completeData.tradeCd, completeData.beforeCorrectPrice[0].oco1SasinariJyouken, correctOco2Param) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span style="white-space: pre-wrap;">{{ getOco2Price(completeData.tradeCd, completeData.oco1SasinariJyouken, oco2Param) }}</span>
              </el-col>
            </el-row>
          </el-row>

          <!--期間、預り区分、手数料区分は共通して表示-->
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
              <span>{{ $_out($_getFormattedDateValue(completeData.period)) }}</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span v-if="completeData.yukoKigenChange === '1'">{{ $_out($_getFormattedDateValue(completeData.yukoKigen)) }}</span>
              <span v-else>{{ $_out($_getFormattedDateValue(completeData.period)) }}</span>
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
                code-list-id="DOMESTIC_DEPOSIT_TYPE"
                :disp-pattern="1"
                :code-key="completeData.depositType"
              ></ifa-text>
              <ifa-text
                v-else
                code-list-id="DOMESTIC_DEPOSIT_TYPE"
                :disp-pattern="2"
                :code-key="completeData.depositType"
              ></ifa-text>
              <span v-if="!completeData.depositType">-</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                v-if="customerInfo.jrIsaContractType === '1'"
                code-list-id="DOMESTIC_DEPOSIT_TYPE"
                :disp-pattern="1"
                :code-key="completeData.depositType"
              ></ifa-text>
              <ifa-text
                v-else
                code-list-id="DOMESTIC_DEPOSIT_TYPE"
                :disp-pattern="2"
                :code-key="completeData.depositType"
              ></ifa-text>
              <span v-if="!completeData.depositType">-</span>
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
                v-if="completeData.tesuuryouKbn"
                code-list-id="COMM_TYPE"
                :disp-pattern="2"
                :code-key="completeData.tesuuryouKbn"
              ></ifa-text>
              <span v-else>-</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                v-if="completeData.tesuuryouKbn"
                code-list-id="COMM_TYPE"
                :disp-pattern="2"
                :code-key="completeData.tesuuryouKbn"
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
    <el-row v-if="completeData.orderKind == '3' || completeData.orderKind == '4'">
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
            <span>ご注文内容：IFD2</span>
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
                <span style="overflow-wrap: break-word;">[{{ $_out(completeData.brandCode) }}]&nbsp;{{ $_out(completeData.brandName) }}</span>
              </el-col>
              <el-col
                v-if="completeData.regKbn"
                :span="5"
                style="text-align: center;"
              >
                <el-icon
                  style="color: red;  vertical-align: middle;"
                ><WarningFilled></WarningFilled></el-icon>
                <ifa-link
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
              style="font-weight: bold;"
            >
              <span v-if="completeData.sasinariHouhou === '3'">逆指値注文</span>
              <ifa-text
                v-else
                code-list-id="ORDER_CLASS"
                :disp-pattern="2"
                :code-key="completeData.orderKind"
              ></ifa-text>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
              style="font-weight: bold;"
            >
              <span v-if="completeData.sasinariHouhou === '3'">逆指値注文</span>
              <ifa-text
                v-else
                code-list-id="ORDER_CLASS"
                :disp-pattern="2"
                :code-key="completeData.orderKind"
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
                code-list-id="SELECT_MARKET"
                :disp-pattern="1"
                :code-key="completeData.market"
              ></ifa-text>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                code-list-id="SELECT_MARKET"
                :disp-pattern="1"
                :code-key="completeData.market"
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
            v-if="completeData.orderKind == '3'"
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">価格</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                code-list-id="EXECUTE_METHOD"
                :disp-pattern="1"
                :code-key="completeData.beforeCorrectPrice[0].ifd2SasinariHouhou"
              ></ifa-text>
              <span style="white-space: pre-wrap;">{{ getPrice(correctIfd2PriceParam, '2') }}</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                code-list-id="EXECUTE_METHOD"
                :disp-pattern="1"
                :code-key="completeData.ifd2SasinariHouhou"
              ></ifa-text>
              <span style="white-space: pre-wrap;">{{ getPrice(ifd2PriceParam, '2') }}</span>
            </el-col>
          </el-row>

          <!-- IFDOCOの時価格欄-->
          <el-row v-if="completeData.orderKind === '4'">
            <el-row
              v-if="completeData.workingStatus === 'false'"
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">価格／OCO1</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span style="white-space: pre-wrap;">
                  {{ getOco1Price(completeData.beforeCorrectPrice[0].oco1SasinariHouhou, completeData.beforeCorrectPrice[0].oco1SasinariJyouken, completeData.beforeCorrectPrice[0].oco1Price) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span style="white-space: pre-wrap;">
                  {{ getOco1Price(completeData.oco1SasinariHouhou, completeData.oco1SasinariJyouken, completeData.oco1Price) }}</span>
              </el-col>
            </el-row>

            <el-row
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">条件／OCO2</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span style="white-space: pre-wrap;">{{ getOco2Price('2', completeData.beforeCorrectPrice[0].oco1SasinariJyouken, correctOco2Param) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span style="white-space: pre-wrap;">{{ getOco2Price('2', completeData.oco1SasinariJyouken, oco2Param) }}</span>
              </el-col>
            </el-row>
          </el-row>

          <!--期間、預り区分、手数料区分は共通して表示-->
          <el-row>
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
                <span>{{ $_out($_getFormattedDateValue(completeData.ifd2Limit)) }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span v-if="completeData.yukoKigenChange === '1'">{{ $_out($_getFormattedDateValue(completeData.yukoKigen)) }}</span>
                <span v-else>{{ $_out($_getFormattedDateValue(completeData.period)) }}</span>
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
                  code-list-id="DOMESTIC_DEPOSIT_TYPE"
                  :disp-pattern="1"
                  :code-key="completeData.depositType"
                ></ifa-text>
                <ifa-text
                  v-else
                  code-list-id="DOMESTIC_DEPOSIT_TYPE"
                  :disp-pattern="2"
                  :code-key="completeData.depositType"
                ></ifa-text>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  v-if="customerInfo.jrIsaContractType === '1'"
                  code-list-id="DOMESTIC_DEPOSIT_TYPE"
                  :disp-pattern="1"
                  :code-key="completeData.depositType"
                ></ifa-text>
                <ifa-text
                  v-else
                  code-list-id="DOMESTIC_DEPOSIT_TYPE"
                  :disp-pattern="2"
                  :code-key="completeData.depositType"
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
                  code-list-id="COMM_TYPE"
                  :disp-pattern="2"
                  :code-key="completeData.tesuuryouKbn"
                ></ifa-text>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  code-list-id="COMM_TYPE"
                  :disp-pattern="2"
                  :code-key="completeData.tesuuryouKbn"
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
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.2rem; padding-left: 1rem"
          >
            <span>その他注文内容</span>
          </el-row>
          <hr>
          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">訂正後買付余力</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span v-if="!completeData.afterCorrecBuyPower || completeData.afterCorrecBuyPower === '0'">-</span>
              <span v-else>{{ $_out($_withCommaInteger(completeData.afterCorrecBuyPower)) }}円</span>
            </el-col>
          </el-row>

          <el-row
            v-if="completeData.depositType === 'H'"
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">注文後のNISA投資可能枠（{{ $_out(completeData.deliveryDate.slice(0,4)) }}年）</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ $_out($_withCommaInteger(completeData.nisaInvestableQuote)) }}円</span>
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
              <span>{{ $_out($_getFormattedDateValue(completeData.contractDate)) }}</span>
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
              <span>{{ $_out($_getFormattedDateValue(completeData.deliveryDate)) }}</span>
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
              <span>{{ $_out($_getFormattedDateTimeValue(completeData.orderDayTime, 'datetime12')) }}</span>
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
                v-if="completeData.kanyuKbn"
                code-list-id="INVITATION_TYPE"
                :disp-pattern="1"
                :code-key="completeData.kanyuKbn"
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
                v-if="completeData.receiveOrderType"
                code-list-id="ORDER_METHOD_TYPE"
                :disp-pattern="1"
                :code-key="completeData.receiveOrderType"
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
                v-if="completeData.checkInsider"
                code-list-id="INSIDER_CONFIRM"
                :disp-pattern="3"
                :code-key="completeData.checkInsider"
              ></ifa-text>
              <br v-if="completeData.afterCorrectMarket === 'A' && completeData.checkInsider">
              <ifa-text
                v-if="completeData.afterCorrectMarket === 'A'"
                code-list-id="SOR_CONFIRM"
                :disp-pattern="2"
                :code-key="completeData.checkSor"
                style="font-size: 16px;"
              ></ifa-text>
              <span v-if="!completeData.checkInsider && completeData.afterCorrectMarket !== 'A'">-</span>
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
          <el-row
            v-if="completeData.regKbn && completeData.regKbn.length > 0"
            class="dotted_border"
          >
            <el-col
              :span="6"
              class="_bold_black_m"
            >
              <span>取引注意情報の説明</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-text
                id="tradingCautionInformation"
                name="tradingCautionInformation"
                :code-list-id="'TRADE_NOTICE_INFO_EXPLAIN'"
                :disp-pattern="1"
                :code-key="form.tradingCautionInformation"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="completeData.messageId && completeData.messageId.length > 0"
            class="dotted_border"
          >
            <el-col
              :span="6"
              class="_bold_black_m"
            >
              <span><ifa-text
                code-list-id="COMPLA_CHECK_BOX_WORDING"
                :disp-pattern="3"
                :code-key="completeData.chkBoxLabel"
              ></ifa-text>
              </span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-text
                code-list-id="COMPLA_CHECK_BOX_WORDING"
                :disp-pattern="1"
                :code-key="form.chkBoxLabel"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="completeData.noticeInfoAlert && completeData.noticeInfoAlert.length > 0"
            class="dotted_border"
          >
            <el-col
              :span="6"
              class="_bold_black_m"
            >
              <span>注意情報の確認</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-text
                id="noticeInfoAlertCheck"
                name="noticeInfoAlertCheck"
                code-list-id="NOTICE_INFO_CONFIRM"
                :disp-pattern="1"
                :code-key="form.noticeInfoAlertCheck"
              ></ifa-text>
            </el-col>
          </el-row>
          <el-row
            v-if="completeData.noticeAlert && completeData.noticeAlert.length > 0"
            class="dotted_border"
          >
            <el-col
              :span="6"
              class="_bold_black_m"
            >
              <span>重要なお知らせの確認</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-text
                id="noticeAlertCheck"
                name="noticeAlertCheck"
                :code-list-id="'IMPORTANT_NOTIFICATION_CONFIRM'"
                :disp-pattern="1"
                :code-key="form.noticeAlertCheck"
              ></ifa-text>
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
        <span class="dialog-footer">
          <ifa-button
            id="btnToOrderStatusList"
            text="注文状況一覧へ"
            color="primary"
            action-type="originalAction"
            style="padding-left: 0;"
            @app-action-handler="toOrderStatusListA001"
          ></ifa-button>
        </span>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { useVModel } from 'vue-composable'
import { IfaDomesticStockOrderCorrectCompleteFormModel } from './js/IfaDomesticStockOrderCorrectCompleteFormModel.js'
export default {
  components: {
    IfaMessageArea,
    IfaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    completeData: { type: Object, required: true }
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
      form: new IfaDomesticStockOrderCorrectCompleteFormModel(),
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
        classObjFontColor: ''
      }
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    infoMessage() {
      const msg = []
      // エラーコード=ワーニングの場合（W05001～W09999）
      if (this.completeData.code.charAt(0) === 'W') {
        msg.push(this.completeData.errMessage + '（' + this.completeData.code + '）')
      }
      return msg
    },
    getUrlObject() {
      if (!this.completeData.brandCode) {
        return null
      }
      return this.completeData.brandCode.substring(0, 4)
    },
    alertContentIsVisible() {
      if ((this.completeData.regKbn && this.completeData.regKbn.length > 0) ||
      (this.completeData.messageId && this.completeData.messageId.length > 0) ||
      (this.completeData.noticeInfoAlert && this.completeData.noticeInfoAlert.length > 0) ||
      (this.completeData.noticeAlert && this.completeData.noticeAlert.length > 0)) {
        return true
      } else {
        return false
      }
    },
    userInfo() {
      return this.$store.getters.userAccount
    }
  },
  methods: {
    setData() {
      Object.assign(this.form, this.completeData)
    },
    onShow() {
      // gePriceに渡すパラメータへ値をセット
      Object.assign(this.priceParam, this.completeData)
      this.ifd1PriceParam = {
        sasinariHouhou: this.completeData.ifd1SasinariHouhou,
        sasinariJyouken: this.completeData.ifd1SasinariJyouken,
        triggerPrice: this.completeData.ifd1TriggerPrice,
        gyakusasiHouhou: this.completeData.ifd1GyakusasiHouhou,
        price: this.completeData.ifd1Price,
        afterCorrectFlag: true
      }
      this.ifd2PriceParam = {
        sasinariHouhou: this.completeData.ifd2SasinariHouhou,
        sasinariJyouken: this.completeData.ifd2SasinariJyouken,
        triggerPrice: this.completeData.ifd2TriggerPrice,
        gyakusasiHouhou: this.completeData.ifd2GyakusasiHouhou,
        price: this.completeData.ifd2Price,
        afterCorrectFlag: true
      }
      this.oco2Param = {
        triggerPrice: this.completeData.oco2TriggerPrice,
        gyakusasiHouhou: this.completeData.oco2GyakusasiHouhou,
        gyakusasiJyouken: this.completeData.oco2GyakusasiJyouken,
        price: this.completeData.oco2Price,
        afterCorrectFlag: true
      }
      Object.assign(this.correctPriceParam, this.completeData.beforeCorrectPrice[0])
      this.correctIfd1PriceParam = {
        sasinariHouhou: this.completeData.beforeCorrectPrice[0].ifd1SasinariHouhou,
        sasinariJyouken: this.completeData.beforeCorrectPrice[0].ifd1SasinariJyouken,
        triggerPrice: this.completeData.beforeCorrectPrice[0].ifd1TriggerPrice,
        stopOrderPriceText2: this.completeData.beforeCorrectPrice[0].ifd1StopOrderPriceText2,
        gyakusasiHouhou: this.completeData.beforeCorrectPrice[0].ifd1GyakusasiHouhou,
        price: this.completeData.beforeCorrectPrice[0].ifd1Price,
        afterCorrectFlag: false
      }
      this.correctIfd2PriceParam = {
        sasinariHouhou: this.completeData.beforeCorrectPrice[0].ifd2SasinariHouhou,
        sasinariJyouken: this.completeData.beforeCorrectPrice[0].ifd2SasinariJyouken,
        triggerPrice: this.completeData.beforeCorrectPrice[0].ifd2TriggerPrice,
        stopOrderPriceText2: this.completeData.beforeCorrectPrice[0].ifd2StopOrderPriceText2,
        gyakusasiHouhou: this.completeData.beforeCorrectPrice[0].ifd2GyakusasiHouhou,
        price: this.completeData.beforeCorrectPrice[0].ifd2Price,
        afterCorrectFlag: false
      }
      this.correctOco2Param = {
        triggerPrice: this.completeData.beforeCorrectPrice[0].oco2TriggerPrice,
        stopOrderPriceText2: this.completeData.beforeCorrectPrice[0].oco2StopOrderPriceText2,
        gyakusasiHouhou: this.completeData.beforeCorrectPrice[0].oco2GyakusasiHouhou,
        gyakusasiJyouken: this.completeData.beforeCorrectPrice[0].oco2GyakusasiJyouken,
        price: this.completeData.beforeCorrectPrice[0].oco2Price,
        afterCorrectFlag: false
      }
      // 背景、取引種別の色
      if (this.completeData.tradeCd === '1') {
        // 買付
        this.classObject.classObjFontColor = 'font-color__plus bold'
        if (this.completeData.orderKind === '1' || this.completeData.orderKind === '2') {
          this.classObject.classObjBgColor = 'buy-background-color'
        } else if (this.completeData.orderKind === '3' || this.completeData.orderKind === '4') {
          this.classObject.classObjBgColor = 'ifd-background-color'
        }
      } else if (this.completeData.tradeCd === '2') {
        // 売却
        this.classObject.classObjFontColor = 'font-color__minus bold'
        if (this.completeData.orderKind === '1' || this.completeData.orderKind === '2') {
          this.classObject.classObjBgColor = 'sell-background-color'
        } else if (this.completeData.orderKind === '3' || this.completeData.orderKind === '4') {
          this.classObject.classObjBgColor = 'ifd-background-color'
        }
      }
    },
    // 注文一覧へ遷移
    toOrderStatusListA001() {
      this.$emit('move-to-order-list')
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
@import '@/styles/orderStatusList.scss';
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
:deep(.el-text){
  font-size: 16px;
}
</style>
