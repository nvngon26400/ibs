<template>
  <!-- SUB0202_0301-01_2_外国現物取引注文確認 -->
  <el-dialog
    v-model="vmIsVisible"
    :title="form.screenTitle"
    :center="true"
    :show-close="false"
    :before-close="backA011"
    :destroy-on-close="true"
    :close-on-click-modal="false"
    :style="dialogStyle"
    width="1200px"
  >
    <ifa-requester
      id="ifaForeignSpotTradeOrderConfirmGetNewMainSiteA019"
      action-id="SUB0202_0301-01_2#A019"
      action-type="requestAction"
      :request-model="ifaLinkRequestModel"
      @response-handler="responseHandlerGetNewMainSiteA019($event)"
    ></ifa-requester>
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
          @app-action-handler="backA011"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="messages.mains"
      :error-messages="messages.errors"
      :warning-messages="messages.warnings"
      :info-messages="messages.infos"
    ></ifa-message-area>

    <!-- 顧客情報 -->
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
        <el-icon style="position: relative; top: 3px;"><component :is="customerInfo.corporationType === '1' ? 'OfficeBuilding' : 'Avatar'"></component></el-icon>
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
    <!-- /顧客情報 -->

    <!-- 銘柄情報 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="padding: 0.3rem 0 0.6rem 0"
      >
        <el-card
          class="box-card"
          style="background-color: #eee;"
        >
          <el-row>
            <el-col
              :span="24"
              class="brand_area_flex"
            >
              <div
                class="brand_area_flex _bold_black_l"
                style="font-size: 20px; min-width: 343px;"
              >
                <span
                  style="display: inline-block; white-space: nowrap;"
                >［{{ $_out(form.brandInfo.brandCode) }}］</span>
                <span
                  style="display: inline-block; word-break: break-all;"
                >{{ $_out(form.brandInfo.brandName) }}</span>
              </div>
              <div
                class="_bold_black_l"
                style="font-size: 20px; white-space: nowrap; width:253px;"
              >&nbsp;&nbsp;上場市場：{{ $_out(form.marketInfo.marketAbbreviation) }}</div>
              <div
                class="_bold_black_l"
                style="font-size: 20px; white-space: nowrap; width: 190px;"
              >&nbsp;&nbsp;国籍：{{ $_getCodeValue('NATIONALITY_CODE', 2, form.marketInfo.countryCode) }}</div>
              <div
                style="text-align: right; min-width: 140px; margin-left: auto;"
              >
                <ifa-button
                  id="btnUpdate"
                  text="更新"
                  icon="RefreshRight"
                  small
                  action-type="requestAction"
                  :request-model="A004RequestModel"
                  action-id="SUB0202_0301-01_2#A004"
                  @response-handler="updateA004($event)"
                ></ifa-button>
              </div>
            </el-col>
          </el-row>
          <el-row style="margin-bottom: 0.5rem;">
            <el-col
              v-if="toBoolean(form.tradeLimit)"
              :offset="1"
              :span="5"
            >
              <el-icon
                style="color: red; position: relative; top: 3px;"
              ><WarningFilled></WarningFilled></el-icon>
              <ifa-link
                :disp-name="'取引注意情報'"
                :url-id="18"
                :url-object="{ countryCode: form.marketInfo.countryCode }"
              ></ifa-link>
            </el-col>
            <el-col
              v-if="form.marketInfo.countryCode == 'US'"
              :span="4"
              style="text-align: left;"
            >
              <div
                class="external-link-wrapper"
                :class="{
                  'external-link-disabled': isDisabled,
                  'external-link-enabled': !isDisabled
                }"
              >
                <el-link
                  :underline="false"
                  type="primary"
                  class="external-link"
                  @click="ifaForeignSpotTradeOrderConfirmGetNewMainSiteA019(49,'1','POST',form.brandInfo.brandCode)"
                > {{ "株価チャート" }}
                </el-link>
                <el-icon
                  class="external-link-icon"
                  @click="ifaForeignSpotTradeOrderConfirmGetNewMainSiteA019(49,'1','POST',form.brandInfo.brandCode)"
                >
                  <img
                    src="@/assets/icons/external-link.svg"
                  >
                </el-icon>
              </div>
            </el-col>
          </el-row>
          <el-row
            :gutter="20"
            style="padding-top: 0.5rem;"
          >
            <el-col
              :span="7"
              :offset="1"
            >
              <div class="info_xs">
                <span class="info-item__header __left">現在値:</span>
                <span class="info-item__current __right">
                  <el-row>
                    <el-col :span="21">
                      <span>{{ $_out($_withCommaZeroPadding(form.priceBasicInfo?.currentPrice, 4)) }}</span>
                    </el-col>
                    <el-col :span="3">
                      <ifa-text
                        v-if="form.priceBasicInfo?.currentPrice"
                        :style="tickColor"
                        :code-list-id="'CURRENT_TICK'"
                        :disp-pattern="1"
                        :code-key="form.priceBasicInfo?.tick"
                      ></ifa-text>
                    </el-col>
                  </el-row>
                </span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">始値:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaZeroPadding(form.priceBasicInfo?.start, 4)) }}</span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">高値:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaZeroPadding(form.priceBasicInfo?.high, 4)) }}</span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">安値:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaZeroPadding(form.priceBasicInfo?.low, 4)) }}</span>
              </div>
            </el-col>
          </el-row>

          <el-row
            :gutter="20"
            style="padding-top: 0.5rem;"
          >
            <el-col
              :span="7"
              :offset="1"
            >
              <div class="info_xs">
                <span class="info-item__header __left">前日比</span>
                <span class="info-item__value __right">
                  <el-row>
                    <el-col :span="12">
                      <span :class="[ratioColor(form.priceBasicInfo?.diff)]">{{ $_out($_signedWithCommaZeroPadding(form.priceBasicInfo?.diff, 2)) }}</span>
                      <span> (</span>
                      <span :class="[ratioColor(form.priceBasicInfo?.ratio)]">
                        {{ $_out($_signedWithCommaZeroPadding(form.priceBasicInfo?.ratio, 2)) }}
                        <span>%</span>
                      </span>
                      <span>)</span>
                    </el-col>
                    <el-col :span="11">
                      <span style="font-size :8px;">{{ form.priceBasicInfo?.currentPriceDate ? `(${formatCurrentDateTime(formatDateTime(form.priceBasicInfo?.currentPriceDate))}` : '(--/--/--　--:--)' }} {{ form.marketInfo.timeZoneAbbreviation ? `${form.marketInfo.timeZoneAbbreviation})` : '' }}</span>
                    </el-col>
                  </el-row>
                </span>
              </div>
            </el-col>

            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">前日終値:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaZeroPadding(form.priceBasicInfo?.last, 4)) }} ({{ form.priceBasicInfo?.lastDate ? formatLastDate(formatDate(form.priceBasicInfo?.lastDate)) : '--/--/--' }}) </span>
              </div>
            </el-col>

            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">出来高:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaInteger(form.priceBasicInfo?.volume)) }}</span>
              </div>
            </el-col>

            <el-col
              :span="2"
              style="padding-top: 0.3rem;"
            >
              <div class="info__right">
                <span class="data-provider">© REFINITIV</span>
              </div>
            </el-col>
          </el-row>

        </el-card>
      </el-col>
    </el-row>
    <!-- /銘柄情報 -->

    <!--ご注意情報-->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          style="background-color: #eee; margin-bottom: 0.5rem;"
        >
          <el-row style="padding-top: 0.5rem;">
            <el-row>
              <el-col
                :offset="1"
                style="font-size :14px;"
              >
                <el-icon style="position: relative; top: 1px;"><WarningFilled></WarningFilled></el-icon>
                <span>{{ $_getCodeValue('NATIONALITY_CODE', 2, form.marketInfo.countryCode) }}株式　ご注意事項</span>
              </el-col>
            </el-row>
            <el-row style="padding-top: 0.5rem;">
              <el-col
                :offset="1"
                :span="5"
              >
                <ifa-link
                  disp-name="・本日の注意銘柄"
                  :url-id="18"
                  :url-object="{ countryCode: form.marketInfo.countryCode }"
                ></ifa-link>
              </el-col>
              <el-col :span="3">
                <ifa-link
                  disp-name="・休場日"
                  :url-id="19"
                  :url-object="{ closedCountryCode: form.marketInfo.countryCode === 'HK' ? 'cn' : form.marketInfo.countryCode.toLowerCase() }"
                ></ifa-link>
              </el-col>
              <el-col :span="5">
                <ifa-link
                  disp-name="・円貨決済停止日"
                  :param-url="form.yenClosedDateUrl"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
              <el-col :span="4">
                <ifa-link
                  disp-name="・取扱銘柄一覧"
                  :url-id="21"
                  :url-object="{ MarginCountryCode: form.marketInfo.countryCode === 'US' ? 'usequity' : (form.marketInfo.countryCode === 'CN' ? 'hk' :form.marketInfo.countryCode.toLowerCase() ) }"
                ></ifa-link>
              </el-col>
              <el-col :span="5">
                <ifa-link
                  disp-name="・お取引注意事項"
                  :param-url="form.noticeofTransactionPrecautionsUrl"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
            </el-row>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!-- /ご注意情報 -->

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
            style="padding: 0.5rem 0.5rem; padding-left: 1rem;"
          >
            <span>ご注文内容（復唱項目）</span>
          </el-row>
          <hr>
          <!-- 取引種別 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'FOREIGN_STOCK_TRADE_CLASS'"
                  :disp-pattern="1"
                  :code-key="form.buySellTypeName"
                  :class="form.buySellTypeName === '0' ? 'font-color__plus bold' : 'font-color__minus bold'"
                ></ifa-text>
                <span v-if="!form.buySellTypeName">-</span>
              </span>
            </el-col>
          </el-row>

          <!-- 数量 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>数量</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.orderQuantity ? $_withCommaInteger(form.orderQuantity) : '0' }} 株</span>
            </el-col>
          </el-row>
          <!-- 数量 -->

          <!-- 価格 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'SELECT_ABLE_PRICE_CONDITIONS'"
                :disp-pattern="1"
                :code-key="form.priceCondition"
              ></ifa-text>
              <span v-if="!form.priceCondition">-</span><br v-if="form.priceCondition !== '2'">
              <span>{{ getPrice() }}</span>
            </el-col>
          </el-row>
          <!-- /価格 -->

          <!-- 注文期間 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>期間</span>
            </el-col>
            <el-col :span="16">
              <span>{{ !form.periodRadio ? '-' : form.periodRadio === '1' ? `${$_out(formatDate(form.period))}まで` : "当日注文" }}</span>
            </el-col>
          </el-row>
          <!-- /注文期間 -->

          <!-- /預り区分 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>預り区分</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'FOREIGN_DEPOSIT_TYPE'"
                :disp-pattern="3"
                :code-key="form.depositType"
              ></ifa-text>
            </el-col>
          </el-row>
          <!-- 預り区分 -->

          <!-- 決済方法 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>決済方法</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'SETTLEMENT_TYPE'"
                :disp-pattern="1"
                :code-key="form.settlementType"
              ></ifa-text>
            </el-col>
          </el-row>
          <!-- /決済方法 -->

          <!-- 現地約定予定日 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>現地約定予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.localContractDate ? formatDate(form.localContractDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>
          <!-- /現地約定予定日 -->

          <!-- 国内約定予定日 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>国内約定予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.domesticTradeDate ? formatDate(form.domesticTradeDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>
          <!-- /国内約定予定日 -->

          <!-- 国内受渡予定日 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>国内受渡予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.domesticSettlementDate ? formatDate(form.domesticSettlementDate) : '----/--/--' }}</span>
            </el-col>
          </el-row>
          <!-- /国内受渡予定日 -->

        </el-card>
      </el-col>
    </el-row>
    <!-- /注文内容(復唱項目) -->

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- 概算注文見積 -->
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
            <span>概算注文見積</span>
          </el-row>
          <hr>

          <!-- 見積価格 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>見積価格</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.quotePrice ? `${$_withCommaZeroPadding(form.quotePrice, 2)} ${form.limitPriceText}` : '' }}</span>
            </el-col>
          </el-row>

          <!-- 概算為替レート -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算為替レート</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.fxRate ? `${$_withCommaZeroPadding(form.fxRate, 2)} 円` : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 概算約定代金 -->
          <el-row
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算約定代金</span>
            </el-col>
            <!-- 概算約定代金（外貨） -->
            <el-col
              v-if="form.settlementType == '2'"
              :span="16"
            >
              <span>{{ `${$_withCommaZeroPadding(form.contractAmountForeign, 2)} ${form.limitPriceText}` }} </span>
            </el-col>
            <!-- 概算約定代金（円貨） -->
            <el-col
              v-if="form.settlementType == '1'"
              :span="16"
            >
              <span>{{ `${$_withCommaInteger(form.contractAmountYen)}` }}円 </span>
            </el-col>
          </el-row>

          <!-- 概算約定代金（円） -->
          <el-row
            v-if="isNisaFrameRestrictionAmount"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算約定代金（円）</span>
            </el-col>
            <el-col
              :span="16"
            >
              <span>{{ `${$_withCommaInteger(form.nisaFrameRestrictionAmount)}` }}円 </span>
            </el-col>
          </el-row>

          <!-- 概算手数料 -->
          <el-row
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算手数料</span>
            </el-col>
            <!-- 概算手数料（外貨） -->
            <el-col
              v-if="form.settlementType == '2'"
              :span="16"
            >
              <span>{{ `${$_withCommaZeroPadding(form.domesticCommForeign, 2)} ${form.limitPriceText}` }} </span>
            </el-col>
            <!-- 概算手数料（円貨） -->
            <el-col
              v-if="form.settlementType == '1'"
              :span="16"
            >
              <span>{{ `${$_withCommaInteger(form.domesticCommJpAmount)}` }}円 </span>
            </el-col>
          </el-row>

          <!-- 概算消費税 -->
          <el-row
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算消費税</span>
            </el-col>
            <!-- 概算消費税（外貨） -->
            <el-col
              v-if="form.settlementType == '2'"
              :span="16"
            >
              <span>{{ `${$_withCommaZeroPadding(form.domesticConsumptionTaxForeign, 2)} ${form.limitPriceText}` }} </span>
            </el-col>
            <!-- 概算消費税（円貨） -->
            <el-col
              v-if="form.settlementType == '1'"
              :span="16"
            >
              <span>{{ `${$_withCommaInteger(form.domesticConsumptionTaxYen)}` }}円 </span>
            </el-col>
          </el-row>

          <!-- 概算譲渡益税 -->
          <el-row
            v-if="form.tradeKbn == '1'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算譲渡益税</span>
            </el-col>
            <el-col
              :span="16"
            >
              <span>{{ form.approximateCapitalGainsTax ? $_withCommaInteger(form.approximateCapitalGainsTax) : '0' }}円</span>
            </el-col>
          </el-row>

          <!-- 概算受渡金額 -->
          <el-row
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算受渡金額</span>
            </el-col>
            <!-- 概算受渡金額（外貨） -->
            <el-col
              v-if="form.settlementType == '2'"
              :span="5"
            >
              <span>{{ `${$_withCommaZeroPadding(form.foreignDeliveryAmount, 2)} ${form.limitPriceText}` }} </span>
            </el-col>
            <!-- 概算受渡金額（円貨） -->
            <el-col
              v-if="form.settlementType == '1'"
              :span="5"
            >
              <span>{{ `${$_withCommaInteger(form.yenDeliveryAmount)}` }}円 </span>
            </el-col>
            <!-- 概算受渡金額（売却コメント） -->
            <el-col
              v-if="form.tradeKbn == '1'"
              :span="11"
            >
              <span>※概算受渡金額は、概算譲渡益税を控除していません。</span>
            </el-col>
          </el-row>

        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- 注文後 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <!-- タイトル -->
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>注文後</span>
          </el-row>
          <hr>

          <!-- 注文後の買付余力ラベル -->
          <el-row
            v-if="form.tradeKbn == '3'"
            class="dotted_border"
            style="padding-top: 0.5rem; padding-left: 0.5rem;"
          >
            <!-- No.96 -->
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>{{ getDeliveryAmountExecuted(form.settlementType) }}</span>
            </el-col>

            <!-- 注文後の買付余力 -->
            <!-- No.97,98 -->
            <el-col
              :span="16"
            >
              <span>{{ `${form.settlementType == '2' ? $_withCommaZeroPadding(form.deliveryAmountExecuted, 2) : $_withCommaNoneZeroPadding(form.deliveryAmountExecuted)} ${form.settlementType == '2' ? form.limitPriceText : '円' }` }} </span>
            </el-col>
          </el-row>

          <!-- 注文後の売却可能株数 -->
          <el-row
            v-if="form.tradeKbn == '1'"
            class="dotted_border"
            style="padding-top: 0.5rem; padding-left: 0.5rem;"
          >
            <!-- 売却可能株数 -->
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>売却可能株数</span>
            </el-col>

            <!-- 売却可能数（注文後） -->
            <el-col
              :span="16"
            >
              <span>{{ $_withCommaInteger(form.acPositionAfter) }}株 </span>
            </el-col>
          </el-row>

          <!-- 買付余力（円貨）ラベル -->
          <el-row
            v-if="form.tradeKbn == '1' && form.settlementType == '2'"
            class="dotted_border"
            style="padding-top: 0.5rem; padding-left: 0.5rem;"
          >
            <!-- No.100 -->
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>{{ getDeliveryAmountExecuted('1') }}</span>
            </el-col>

            <!-- 買付余力（円貨） -->
            <!-- No.101 -->
            <el-col
              :span="5"
            >
              <span>{{ $_withCommaNoneZeroPadding(form.deliveryAmountExecuted) }}円 </span>
            </el-col>

            <!-- 注文後営業日 -->
            <!-- No.102 -->
            <el-col
              :span="11"
            >
              <span>{{ `（営業日：${form.domesticTradeDate}）` }}</span>
            </el-col>
          </el-row>

          <!-- NISA投資可能枠 -->
          <!-- No.103 -->
          <el-row
            v-if="isNisaInvestableQuote"
            class="dotted_border"
            style="padding-top: 0.5rem; padding-left: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>{{ `NISA投資可能枠（${getYearString(form.domesticSettlementDate)}）` }}</span>
            </el-col>

            <!-- NISA投資可能枠（注文後） -->
            <el-col
              :span="16"
            >
              <span>{{ $_withCommaInteger(form.nisaInvestableQuote) }}円 </span>
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

          <!-- 勧誘区分 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'FOREIGN_STOCK_INVITATION_TYPE'"
                :disp-pattern="1"
                :code-key="form.kanyuKbn"
              ></ifa-text>
            </el-col>
          </el-row>
          <!-- /勧誘区分 -->

          <!-- 受注方法 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受注方法</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'FOREIGN_STOCK_ORDER_METHOD_TYPE'"
                :disp-pattern="1"
                :code-key="form.receiveOrderType"
              ></ifa-text>
            </el-col>
          </el-row>
          <!-- /受注方法 -->

          <!-- 重要事項の説明 -->
          <el-row
            v-if="form.importantMatterType"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>重要事項の説明</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'IMPORTANT_MATTERS_EXPLAIN'"
                :disp-pattern="1"
                :code-key="form.importantMatterType"
              ></ifa-text>
            </el-col>
          </el-row>
          <!-- /重要事項の説明 -->

          <!-- 乗換え勧誘(ETF) -->
          <el-row
            v-if="form.solicitationEtf"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>乗換え勧誘(ETF)</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'ETF_SOLICITING_TRANSFERS'"
                :disp-pattern="1"
                :code-key="form.solicitationEtf"
              ></ifa-text>
            </el-col>
          </el-row>
          <!-- /乗換え勧誘(ETF) -->

          <!-- 英文開示銘柄 -->
          <el-row
            v-if="form.engPubCheckbox"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>英文開示銘柄</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'ISSUES_DISCLOSED_IN_ENGLISH_BRAND'"
                :disp-pattern="1"
                :code-key="form.engPubCheckbox"
              ></ifa-text>
            </el-col>
          </el-row>
          <!-- /英文開示銘柄 -->

          <!-- インサイダー -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>確認項目</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'INSIDER_CONFIRM'"
                :disp-pattern="2"
                :code-key="form.confirmItemList[0].checkInsider"
              ></ifa-text>
            </el-col>
          </el-row>
          <!-- /インサイダー -->
        </el-card>
      </el-col>
    </el-row>
    <!-- /その他注文内容 -->

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row v-if="form.tradeNoticeInfoBrandMsg ||
      (Array.isArray(form.complianceCheckList) ? Boolean(form.complianceCheckList.length) : form.complianceCheckList) ||
      form.noticeInfoAlert ||
      form.noticeAlert ||
      form.priceLimitCheckText ||
      form.methodCheckText ||
      form.localTradeDateLimitMsg ||
      form.overseasEtfAlert"
    >
      <el-col
        :span="22"
        :offset="1"
        style="color: #f00;"
      >
        <el-card
          :class="form.buySellTypeName === '0' ? 'buy-background-color_card' : 'sell-background-color_card'"
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
          <!-- 取引注意情報（銘柄）確認 -->
          <el-row
            v-if="form.tradeNoticeInfoBrandMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
              style="padding-left: 0;"
            >
              <span class="required-mark">*</span><span style="color: #f00;">取引注意情報の説明</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.AlertContentsConfirm.tradingCautionInformation"
                style="margin-left: 5px; color: #f00;"
                code-list-id="TRADE_NOTICE_INFO_EXPLAIN"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <!-- コンプラランクチェック -->
          <el-row
            v-if="complianceCheckListDispFlg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
              style="padding-left: 0;"
            >
              <span class="required-mark">*</span>
              <span style="color: #f00;">{{ $_getCodeValue('COMPLA_CHECK_BOX_WORDING', 3, form.complianceCheckList[0].chkBoxLabel) }}</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.AlertContentsConfirm.invitationCheck"
                style="margin-left: 5px; color: #f00;"
                code-list-id="original"
                :original-list="complianceRankList"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!-- 注意情報アラート確認 -->
          <el-row
            v-if="form.noticeInfoAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
              style="padding-left: 0;"
            >
              <span class="required-mark">*</span><span style="color: #f00;">注意情報の確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.AlertContentsConfirm.noteInfoCheckbox"
                style="margin-left: 5px; color: #f00;"
                code-list-id="NOTICE_INFO_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!-- お知らせアラート確認 -->
          <el-row
            v-if="form.noticeAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
              style="padding-left: 0;"
            >
              <span class="required-mark">*</span><span style="color: #f00;">重要なお知らせの確認</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.AlertContentsConfirm.noteLimitCheck"
                style="margin-left: 5px; color: #f00;"
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!-- 約定代金の上限超過 -->
          <el-row
            v-if="form.priceLimitCheckText"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
              style="padding-left: 0;"
            >
              <span class="required-mark">*</span><span style="color: #f00;">誤発注防止制限金額超過</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.AlertContentsConfirm.priceLimitCheck"
                style="margin-left: 5px; color: #f00;"
                code-list-id="EXCEEDING_TRADE_AMOUNT_LIMIT"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!-- 逆指値注文即時発火 -->
          <el-row
            v-if="form.methodCheckText"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
              style="padding-left: 0;"
            >
              <span class="required-mark">*</span><span style="color: #f00;">逆指値注文即時発火</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.AlertContentsConfirm.methodCheck"
                style="margin-left: 5px; color: #f00;"
                code-list-id="IMMEDIATE_STOP_ORDER"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!-- 翌営業日向け注文 -->
          <el-row
            v-if="form.localTradeDateLimitMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
              style="padding-left: 0;"
            >
              <span class="required-mark">*</span><span style="color: #f00;">翌営業日向け注文</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.AlertContentsConfirm.nextDayCheck"
                style="margin-left: 5px; color: #f00;"
                code-list-id="ORDER_FOR_NEXT_BUSINESS_DAY"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>

          <!-- 海外ETFアラート確認 -->
          <el-row
            v-if="form.overseasEtfAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
              style="padding-left: 0;"
            >
              <span class="required-mark">*</span><span style="color: #f00;">乗換え勧誘(ETF)なし</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                v-model="form.AlertContentsConfirm.overseasEtfCheck"
                style="margin-left: 5px; color: #f00;"
                code-list-id="OVERSEAS_ETF_ALERT_CONFIRM"
                :disp-pattern="1"
                :select-pattern="1"
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
          style="padding-left: 0;"
          :disabled="form.tradeNoticeInfoBrandMsg ||
            (Array.isArray(form.complianceCheckList) ? Boolean(form.complianceCheckList.length) : form.complianceCheckList) ||
            form.noticeInfoAlert ||
            form.noticeAlert ||
            form.priceLimitCheckText ||
            form.methodCheckText ||
            form.localTradeDateLimitMsg ||
            form.overseasEtfAlert ? buttonDisabled : false"
          action-type="requestAction"
          :request-model="A010RequestModel"
          action-id="SUB0202_0301-01_2#A010"
          @response-handler="orderPlacementA010($event)"
        ></ifa-button>
      </el-col>
    </el-row>
    <!-- /注文発注ボタン -->
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaForeignSpotTradeOrderConfirmFormModel } from './js/IfaForeignSpotTradeOrderConfirmFormModel'
import { IfaForeignSpotTradeOrderConfirmA004RequestModel } from './js/IfaForeignSpotTradeOrderConfirmA004RequestModel'
import { IfaForeignSpotTradeOrderConfirmA010RequestModel } from './js/IfaForeignSpotTradeOrderConfirmA010RequestModel'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'
import { getFormattedDateValue, getFormattedDateTimeValue, getFormattedTimeValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import { IfaLinkRequestModel } from './js/IfaLinkRequestModel'
import { notifyMessage } from '@/utils/errorHandler'

export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  // 親コンポーネントから受け取る値
  props: {
    // 本コンポーネントの表示・表示
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
      dialogStyle: '',
      form: new IfaForeignSpotTradeOrderConfirmFormModel(),
      depositTypeStr: ['NISA', '特定', '一般'],
      limitOrderConditionOptions: ['指値', '寄指(Y)', '引指(H)', '不成(F)', 'IOC指(I)'],
      marketOrderConditionOptions: ['成行/最良気配指値', '寄成(Y)', '引成(H)', 'IOC成(I)'],
      stopOrderCondition1Options: ['', '引指(H)', '不成(F)'],
      stopOrderCondition2Options: ['成行', '引成(H)'],
      messages: {
        mains: [],
        errors: [],
        warnings: [],
        infos: []
      },
      forms: {
        tradeNoticeInfoBrandMsg: false,
        complianceCheckList: false,
        noticeInfoAlert: false,
        noticeAlert: false,
        priceLimitCheckText: false,
        methodCheckText: false,
        localTradeDateLimitMsg: false,
        overseasEtfAlert: false
      },
      request: '',
      requestProps: {},
      ifaLinkRequestModel: {}
    }
  },
  computed: {
    userInfo() {
      return this.$store.getters.userAccount
    },
    A004RequestModel() {
      return new IfaForeignSpotTradeOrderConfirmA004RequestModel(this.form)
    },
    A010RequestModel() {
      return new IfaForeignSpotTradeOrderConfirmA010RequestModel(this.form)
    },
    buttonDisabled() {
      if ((this.form.tradeNoticeInfoBrandMsg ? (Boolean(Number(this.form.AlertContentsConfirm.tradingCautionInformation)) === (Boolean(this.form.tradeNoticeInfoBrandMsg))) : true) &&
        (this.complianceCheckListDispFlg ? (Boolean(Number(this.form.AlertContentsConfirm.invitationCheck)) === (Array.isArray(this.form.complianceCheckList) ? (Boolean(this.form.complianceCheckList.length) && 'complianceCheckMsg' in this.form.complianceCheckList[0] ? Boolean(this.form.complianceCheckList[0].complianceCheckMsg) : false) : false)) : true) &&
        (this.form.noticeInfoAlert ? Boolean(Number(this.form.AlertContentsConfirm.noteInfoCheckbox)) && (Boolean(this.form.noticeInfoAlert)) : true) &&
        (this.form.noticeAlert ? Boolean(Number(this.form.AlertContentsConfirm.noteLimitCheck)) === (Boolean(this.form.noticeAlert)) : true) &&
        (this.form.priceLimitCheckText ? Boolean(Number(this.form.AlertContentsConfirm.priceLimitCheck)) === (Boolean(this.form.priceLimitCheckText)) : true) &&
        (this.form.methodCheckText ? Boolean(Number(this.form.AlertContentsConfirm.methodCheck)) === (Boolean(this.form.methodCheckText)) : true) &&
        (this.form.localTradeDateLimitMsg ? Boolean(Number(this.form.AlertContentsConfirm.nextDayCheck)) === (Boolean(this.form.localTradeDateLimitMsg)) : true) &&
        (this.form.overseasEtfAlert ? Boolean(Number(this.form.AlertContentsConfirm.overseasEtfCheck)) === (Boolean(this.form.overseasEtfAlert)) : true) &&
        (Number(this.userInfo.medUsers.privId) > 2)) {
        return false
      } else {
        return true
      }
    },
    complianceRankList() {
      return [{ key: '0', value: this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, this.form.complianceCheckList[0].chkBoxLabel) }, { key: '1', value: this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, this.form.complianceCheckList[0].chkBoxLabel) }]
    },
    complianceCheckListDispFlg() {
      if (Array.isArray(this.form.complianceCheckList) ? (Boolean(this.form.complianceCheckList.length) && 'complianceCheckMsg' in this.form.complianceCheckList[0] ? this.form.complianceCheckList[0].complianceCheckMsg : false) : this.form.complianceCheckList) {
        return true
      } else {
        return false
      }
    },
    customerName() {
      return this.customerInfo.customerNameKanji + '（' + this.customerInfo.customerNameKana + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    },
    // 前日比の色を算出する
    ratioColor() {
      return function(value) {
        const n = Number(value)
        return n > 0 ? 'font-color__plus __bold' : n < 0 ? 'font-color__minus __bold' : '__black'
      }
    },
    // 上下矢印(現在値ティック)に表示する色を返す
    tickColor() {
      if (!this.form.priceBasicInfo?.currentPrice) {
        return 'color: #606266;'
      }

      switch (this.form.priceBasicInfo?.tick) {
        case '1':
          return 'color: #E5004C; font-weight: bold'
        case '2':
          return 'color: #00847F; font-weight: bold'
        default:
          return 'color: #606266; font-weight: bold;'
      }
    },
    /** 概算約定代金（円）表示可否チェック */
    isNisaFrameRestrictionAmount() {
      if (
        this.form.settlementType === '2' &&
        this.form.tradeKbn === '3' &&
        (
          this.form.depositType === '4' ||
          this.form.depositType === 'H'
        )
      ) {
        return true
      }
      return false
    },
    /** NISA投資可能枠（注文後）表示可否チェック */
    isNisaInvestableQuote() {
      if (
        this.form.tradeKbn === '3' &&
        (
          this.form.depositType === '4' ||
          this.form.depositType === 'H'
        )
      ) {
        return true
      }
      return false
    },
    target() {
      return 'link' + (this.requestProps.urlId === 0 ? '' : this.requestProps.urlId.toString())
    }
  },
  watch: {
    'form.buySellTypeName': {
      deep: true,
      handler(newValue) {
        if (newValue === '0') {
          this.dialogStyle = 'backgroundColor: #fef0f0;'
        } else {
          this.dialogStyle = 'backgroundColor: #ecf5ff;'
        }
      }
    }
  },
  // エラー/警告/情報の更新
  beforeUpdate() {
    this.messages.mains.length = 0
    this.messages.mains.push('注文はまだ完了していません。画面下の注文発注ボタンを押下してください。')

    this.messages.errors.length = 0
    if (this.form.tradeNoticeInfoBrandMsg) {
      this.messages.errors.push(this.form.tradeNoticeInfoBrandMsg)
    }
    if (Array.isArray(this.form.complianceCheckList) ? (Boolean(this.form.complianceCheckList.length) && 'complianceCheckMsg' in this.form.complianceCheckList[0] ? this.form.complianceCheckList[0].complianceCheckMsg : false) : this.form.complianceCheckList) {
      this.messages.errors.push(Array.isArray(this.form.complianceCheckList) ? (Boolean(this.form.complianceCheckList.length) && 'complianceCheckMsg' in this.form.complianceCheckList[0] ? this.form.complianceCheckList[0].complianceCheckMsg : '') : this.form.complianceCheckList)
    }
    if (this.form.noticeInfoAlert) {
      this.messages.errors.push(this.form.noticeInfoAlert)
    }
    if (this.form.noticeAlert) {
      this.messages.errors.push(this.form.noticeAlert)
    }
    if (this.form.priceLimitCheckText) {
      this.messages.errors.push(this.form.priceLimitCheckText)
    }
    if (this.form.methodCheckText) {
      this.messages.errors.push(this.form.methodCheckText)
    }
    if (this.form.localTradeDateLimitMsg) {
      this.messages.errors.push(this.form.localTradeDateLimitMsg)
    }
    if (this.form.overseasEtfAlert) {
      this.messages.errors.push(this.form.overseasEtfAlert)
    }

    this.messages.warnings.length = 0
    this.messages.infos.length = 0
  },
  methods: {
    toBoolean(booleanStr) {
      return typeof booleanStr === 'boolean' ? booleanStr : booleanStr.toLowerCase() === 'true'
    },
    onShow() {
      Object.assign(this.form, this.formData)
      console.log(this.form)
      console.log(this.formData)
    },
    // 戻るボタン
    backA011() {
      this.$emit('close-modal')
      this.form.AlertContentsConfirm.tradingCautionInformation = '0'
      this.form.AlertContentsConfirm.invitationCheck = '0'
      this.form.AlertContentsConfirm.noteInfoCheckbox = '0'
      this.form.AlertContentsConfirm.noteLimitCheck = '0'
      this.form.AlertContentsConfirm.priceLimitCheck = '0'
      this.form.AlertContentsConfirm.methodCheck = '0'
      this.form.AlertContentsConfirm.nextDayCheck = '0'
      this.form.AlertContentsConfirm.overseasEtfCheck = '0'
    },
    // 注文発注ボタン
    orderPlacementA010: function(data) {
      this.$emit('order-finish', data.dataList[0])
      this.form.AlertContentsConfirm.tradingCautionInformation = '0'
      this.form.AlertContentsConfirm.invitationCheck = '0'
      this.form.AlertContentsConfirm.noteInfoCheckbox = '0'
      this.form.AlertContentsConfirm.noteLimitCheck = '0'
      this.form.AlertContentsConfirm.priceLimitCheck = '0'
      this.form.AlertContentsConfirm.methodCheck = '0'
      this.form.AlertContentsConfirm.nextDayCheck = '0'
      this.form.AlertContentsConfirm.overseasEtfCheck = '0'
    },
    updateA004: function(data) {
      this.form = Object.assign(this.form, data.dataList[0])
    },
    // 価格に表示する内容を生成する
    getPrice() {
      if (this.form.priceCondition === '1') {
        return this.form.hiddenOrderPrice ? this.$_withCommaZeroPadding(this.form.hiddenOrderPrice, 2) + this.form.limitPriceText : '0' + this.form.limitPriceText
      } else if (this.form.priceCondition === '3' || this.form.priceCondition === '4') {
        let str = '（現在値が' + (this.form.triggerPrice ? this.$_withCommaZeroPadding(this.form.triggerPrice, 2) + this.form.limitPriceText : '0' + this.form.limitPriceText) +
                  (this.form.tradeKbn === '3' ? '以上' : '以下') + 'になった時点で'
        if (this.form.priceCondition === '3') {
          str += (this.form.hiddenOrderPrice ? this.$_withCommaZeroPadding(this.form.hiddenOrderPrice, 2) + this.form.limitPriceText : '0' + this.form.limitPriceText) + 'で発注）'
        } else if (this.form.priceCondition === '4') {
          str += '成行で発注）'
        }
        return str
      }
      return ''
    },
    /** 注文後の買付余力ラベル */
    getDeliveryAmountExecuted(settlementType) {
      /** 預り区分=5（Jr一般）預り区分=6（Jr特定）預り区分=7（JrNISA）預り区分=J（JrNISA継続管理）*/
      // 買付の場合、預り区分=J（JrNISA継続管理）は業務的に発生しないため、「No.96：注文後の買付余力ラベル」の「預り区分=J（JrNISA継続管理）」の考慮は不要
      const checkArray = ['5', '6', '7', 'J']
      const jrLabel = 'ジュニア口座 買付余力'
      const label = '買付余力'
      let settlement = ''
      if (settlementType === '2') {
        settlement = '（外貨）'
      } else if (settlementType === '1') {
        settlement = '（円貨）'
      }
      if (checkArray.includes(this.form.depositType)) {
        return jrLabel + settlement
      } else {
        return label + settlement
      }
    },

    /** 年の取得 */
    getYearString(str) {
      const pattern = /^\d{4}(?=\/)/
      return str.match(pattern)
    },

    /** コンプラランクチェック確認取得 */
    getCompChkBoxLabel(form) {
      return getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, form.complianceCheckList[0].chkBoxLabel)
    },
    formatDate(val) {
      return getFormattedDateValue(val)
    },
    formatDateTime(val) {
      return getFormattedDateTimeValue(val)
    },
    formatTime(val) {
      return getFormattedTimeValue(val)
    },
    formatCurrentDateTime(currentDateTime) {
      const dateObj = new Date(currentDateTime)
      const year = String(dateObj.getFullYear()).slice(-2)
      const month = `0${dateObj.getMonth() + 1}`.slice(-2)
      const day = `0${dateObj.getDate()}`.slice(-2)
      const hours = `0${dateObj.getHours()}`.slice(-2)
      const minutes = `0${dateObj.getMinutes()}`.slice(-2)
      return `${year}/${month}/${day} ${hours}:${minutes}`
    },
    formatTimeZoneAbbreviatedName(timeZoneAbbreviatedName) {
      const timeParts = timeZoneAbbreviatedName.split(':')
      const hours = timeParts[0]
      const minutes = timeParts[1]
      return `${hours}:${minutes}`
    },
    formatLastDate(lastDate) {
      const dateParts = lastDate.split('/')
      const year = String(dateParts[0]).slice(-2)
      const month = `0${dateParts[1]}`.slice(-2)
      const day = `0${dateParts[2]}`.slice(-2)
      return `${year}/${month}/${day}`
    },
    ifaForeignSpotTradeOrderConfirmGetNewMainSiteA019(urlId, patternId, httpMethod, brandCode) {
      const newData = {
        urlId: urlId,
        patternId: patternId,
        httpMethod: httpMethod,
        brandCode: brandCode
      }
      this.requestProps = newData
      this.ifaLinkRequestModel = new IfaLinkRequestModel(this.requestProps)
      document.getElementById('ifaForeignSpotTradeOrderConfirmGetNewMainSiteA019').click()
    },
    responseHandlerGetNewMainSiteA019(response) {
      Object.assign(this.form, response?.dataList?.[0])
      if (this.form.postRequest) {
        this.request = this.form.postRequest
      }
      this.openWindow()
    },
    openWindow() {
      // cursor: not-allowed; と pointer-events: none; が同時に使えなかったため
      this.linkUrl = this.form.linkUrl
      this.paramObject = this.form.newMainSiteParamList[0]
      const newWindow = window.open('', this.target)
      if (newWindow) {
        const linkForm = document.createElement('form')
        linkForm.target = this.target
        linkForm.method = 'POST'
        linkForm.action = this.linkUrl
        // request と paramObject をマージする｡
        // key が同じ場合は､ paramObject を優先する
        const objs = Object.assign({}, this.request, this.paramObject)
        const params = Object.entries(objs)
          .map(e => ({ name: e[0], value: e[1] }))
        if (params) {
          for (const param of params) {
            const linkInput = document.createElement('input')
            linkInput.type = 'hidden'
            linkInput.name = param.name
            linkInput.value = param.value
            linkForm.appendChild(linkInput)
          }
        }
        document.body.appendChild(linkForm)
        linkForm.submit()
        document.body.removeChild(linkForm)
      } else {
        const label = this.$store.getters.pageInfo.label
        notifyMessage(2, 'ポップアップを許可してください｡', label)
      }
    }

  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/variables.scss";
@import "@/styles/orderStatusList.scss";
@import "@/styles/foreignStockOrder.scss";
._bold_red_m {
  font-size: 16px;
  font-weight: bold;
  color: #f00;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
.info_xs {
  display: grid;
  width: 100%;
  grid-template-columns: 4rem 1fr;
}
.info-item__header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__value {
  resize: none;
  border: none;
  color: #606266;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__current {
  resize: none;
  border: none;
  color: #606266;
  font-size: 15px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.__right {
  text-align: right;
  padding-right: 0.5rem;
}
.data-provider {
  resize: none;
  border: none;
  color: #bfcbd9;
  font-size: 10px;
  // height: 25px;
  // line-height: 25px;
}
.required-mark {
  display: inline-block;
  color: #ff2b2b;
  width: 8px;
}
:deep(.error-check) .el-form-item__error {
  margin-left: 0px;
  font-weight: normal;
}
.el-form-item__error_custom-margin :deep(.el-form-item__error) {
  margin-top: -0.7rem;
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
.footer_button {
  text-align: left;
}
.info__right {
  position: absolute;
  bottom: 0px;
  right: 0px;
}
:deep(.el-text){
  font-size: 16px;
}
.brand_area_flex {
  display: flex;
  align-items: flex-start;
}
.alert_content {
  :deep(.el-form-item) {
    margin-bottom: 0;
  }
  :deep(.el-form-item__content) {
    line-height: normal;
  }
}
.external-link {
  padding: 3px 7px;
  text-decoration: underline;
  &:hover, &:focus {
    text-decoration: underline;
      }
}
.external-link-icon {
  --background-image: url(~@/assets/icons/external-link.svg);
  content: "";

  height: 14px;
  width: 14px;
  display: inline-block;
  vertical-align: middle;
}
.external-link-wrapper {
  display: inline-block
}
.external-link-enabled {
  cursor: pointer;
  .el-link {
    &:hover {
      // el-link の元々の opacity と混ざるのでリセット
      opacity: unset;
    }
  }
  &:hover {
    opacity: 0.7;
  }
}
.external-link-disabled {
  .el-link {
    // el-link が pointer アイコンにしてしまうので強制的にアイコンを変更
    cursor: not-allowed;
    &:hover {
      // el-link の元々の opacity と混ざるのでリセット
      opacity: unset;
    }
  }
  cursor: not-allowed;
  &:hover {
    opacity: 0.7;
  }
}
</style>
