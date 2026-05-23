<template>
  <!-- 外国株信用取引新規注文確認 -->
  <el-dialog
    v-model="vmIsVisible"
    :title="form.screenTitle"
    :center="true"
    :show-close="false"
    :before-close="backA011"
    :close-on-click-modal="false"
    :style="formData.tradeKbn === '3' ? 'background-color: #fef0f0;' : 'background-color: #ecf5ff;'"
    style="width: 1200px;"
    class="foreign-margin_dialog-class"
  >
    <ifa-requester
      id="ifaForeignMarginTradeOrderConfirmGetNewMainSiteA012"
      action-id="SUB0202_0303-01_2#A012"
      action-type="requestAction"
      :request-model="ifaLinkRequestModel"
      @response-handler="responseHandlerGetNewMainSiteA012($event)"
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
          style="padding-right: 0;"
          text="戻る"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="backA011"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="messages.mains"
      :error-messages="messages.errors"
    ></ifa-message-area>

    <!-- 顧客情報 -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col
        :offset="1"
      >
        <span>{{ $_out(accountNumber) }}</span>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        class="_bold_black_l"
        style="padding-top: 0.5rem; font-size: 20px;"
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
                style="font-size: 20px; min-width: 343px; max-width: 665px;"
              >
                <span
                  style="display: inline-block; white-space: nowrap;"
                >［{{ $_out(form.brandInformation.securitiesCode) }}］</span>
                <span
                  style="display: inline-block; word-break: break-all;"
                >{{ $_out(form.brandInformation.securitiesName) }}</span>
              </div>
              <div
                class="_bold_black_l"
                style="font-size: 20px; white-space: nowrap; width:253px;"
              >&nbsp;&nbsp;上場市場：{{ $_out(form.marketInformation.marketShortName) }}</div>
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
                  action-id="SUB0202_0303-01_2#A004"
                  @response-handler="updateA004($event)"
                ></ifa-button>
              </div>
            </el-col>
          </el-row>
          <el-row style="margin-bottom: 0.5rem;">
            <el-col
              v-if="form.tradeLimit"
              :offset="1"
              :span="5"
            >
              <el-icon
                style="color: red; position: relative; top: 3px;"
              ><WarningFilled></WarningFilled></el-icon>
              <ifa-link
                :param-url="form.tradeLimitUrl"
                :disp-name="'取引注意情報'"
                link-icon-image="externalLink"
              ></ifa-link>
            </el-col>
            <el-col
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
                  @click="ifaForeignMarginTradeOrderConfirmGetNewMainSiteA012(50,'1','POST',form.brand.brandCode)"
                > {{ "株価チャート" }}
                </el-link>
                <el-icon
                  class="external-link-icon"
                  @click="ifaForeignMarginTradeOrderConfirmGetNewMainSiteA012(50,'1','POST',form.brand.brandCode)"
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
            style="padding-top: 0.5rem; margin: 0;"
          >
            <el-col
              :span="8"
            >
              <div class="info_xs">
                <span class="info-item__header __left">現在値:</span>
                <span class="info-item__current __right">
                  <el-row>
                    <el-col style="text-align: center;">
                      <span>{{ $_out(ifaFormatUtils.withCommaZeroPadding(form.priceBasicInfo?.[0].currentPrice, 4)) }}  </span>
                      <ifa-text
                        v-if="form.priceBasicInfo?.[0].currentPrice"
                        :style="tickColor"
                        :code-list-id="'CURRENT_TICK'"
                        :disp-pattern="1"
                        :code-key="form.priceBasicInfo?.[0].tick"
                      ></ifa-text>
                    </el-col>
                  </el-row>
                </span>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="info_xs">
                <span class="info-item__header __left">始値:</span>
                <span class="info-item__value __right">{{ $_out(ifaFormatUtils.withCommaZeroPadding(form.priceBasicInfo?.[0].start, 4)) }}</span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">高値:</span>
                <span class="info-item__value __right">{{ $_out(ifaFormatUtils.withCommaZeroPadding(form.priceBasicInfo?.[0].high, 4)) }}</span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">安値:</span>
                <span class="info-item__value __right">{{ $_out(ifaFormatUtils.withCommaZeroPadding(form.priceBasicInfo?.[0].low, 4)) }}</span>
              </div>
            </el-col>
          </el-row>

          <el-row
            :gutter="20"
            style="padding-top: 0.5rem; margin: 0;"
          >
            <el-col
              :span="8"
            >
              <div class="info_xs">
                <span class="info-item__header __left">前日比:</span>
                <span class="info-item__value __right">
                  <span :class="[ratioColor(form.priceBasicInfo?.[0].diff)]">{{ $_out(ifaFormatUtils.signedWithCommaZeroPadding(form.priceBasicInfo?.[0].diff, 2)) }}</span>
                  <span> (</span>
                  <span :class="[ratioColor(form.priceBasicInfo?.[0].ratio)]">
                    {{ $_out(ifaFormatUtils.signedWithCommaZeroPadding(form.priceBasicInfo?.[0].ratio, 2)) }}
                    <span>%</span>
                  </span>
                  <span>) </span>
                  <span>{{ form.priceBasicInfo?.[0].currentDateTime ? `(${formatCurrentDateTime(formatDateTime(form.priceBasicInfo?.[0].currentDateTime))}` : '(--/--/--　--:--)' }} {{ form.marketList.timeZoneAbbreviatedName ? `${form.marketList.timeZoneAbbreviatedName})` : '' }}</span>
                </span>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="info_xs">
                <span class="info-item__header __left">前日終値:</span>
                <span class="info-item__value __right">
                  <span>{{ $_out(ifaFormatUtils.withCommaZeroPadding(form.priceBasicInfo?.[0].last, 4)) }} </span>
                  <span style="font-size: 8px"> ({{ form.priceBasicInfo?.[0].lastDate ? formatLastDate(formatDate(form.priceBasicInfo?.[0].lastDate)) : '--/--/--' }}) </span>
                </span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">出来高:</span>
                <span class="info-item__value __right">{{ $_out(ifaFormatUtils.withCommaInteger(form.priceBasicInfo?.[0].volume)) }}</span>
              </div>
            </el-col>
            <el-col
              :span="5"
              style="padding-top: 0.3rem; text-align: end;"
            >
              <span class="data-provider">© REFINITIV</span>
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
                <span>米国株式　ご注意事項</span>
              </el-col>
            </el-row>
            <el-row style="padding-top: 0.5rem;">
              <el-col
                :offset="1"
                :span="5"
              >
                <ifa-link
                  disp-name="・本日の注意銘柄"
                  :param-url="form.tradeLimitUrl"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
              <el-col :span="3">
                <ifa-link
                  disp-name="・休場日"
                  :param-url="form.closedDay"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
              <el-col :span="5">
                <ifa-link
                  disp-name="・円貨決済停止日"
                  :param-url="form.yenClosed"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
              <el-col :span="4">
                <ifa-link
                  disp-name="・取扱銘柄一覧"
                  :param-url="form.usequityList"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
              <el-col :span="5">
                <ifa-link
                  disp-name="・お取引注意事項"
                  :param-url="form.tradingAttention"
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
            style="padding-top: 0.5rem; padding-left: 1rem;"
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
              <span
                :style="form.tradeKbn === '3' ? 'color: #E5004C; font-weight: bold;' : 'color: #00847F; font-weight: bold;'"
              >
                <ifa-text
                  :style="form.tradeKbn === '3' ? 'color: #E5004C; font-weight: bold;' : 'color: #00847F; font-weight: bold;'"
                  :code-list-id="'FOREIGN_STOCK_TRADE_CLASS'"
                  :disp-pattern="1"
                  :code-key="form.tradeCd"
                ></ifa-text>
                <span v-if="!form.tradeCd">-</span>
              </span>
              <span
                :style="form.tradeKbn === '3' ? 'color: #E5004C; font-weight: bold;' : 'color: #00847F; font-weight: bold;'"
              >（
                <ifa-text
                  :style="form.tradeKbn === '3' ? 'color: #E5004C; font-weight: bold;' : 'color: #00847F; font-weight: bold;'"
                  :code-list-id="'MARGIN_DUE_DATE'"
                  :disp-pattern="1"
                  :code-key="form.marginDueDate"
                ></ifa-text>
                <span v-if="!form.marginDueDate">-</span>
                ）</span>
            </el-col>
          </el-row>

          <!-- 受注数量 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>数量</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.foreignQuantity ? ifaFormatUtils.withCommaInteger(form.foreignQuantity) : '0' }} 株</span>
            </el-col>
          </el-row>
          <!-- /受注数量 -->

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
                :code-key="form.orderPriceKindList"
              ></ifa-text>
              <span v-if="!form.orderPriceKindList">-</span><br v-if="form.orderPriceKindList !== '2'">
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
                :disp-pattern="2"
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
              <span>{{ form.localTradeDate ? formatDate(form.localTradeDate) : '----/--/--' }}</span>
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

          <!-- 保証金不足額 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>保証金不足額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.depositDeficientAmount ? `${ifaFormatUtils.withCommaZeroPadding(form.depositDeficientAmount, 2)} ${form.tradeCurrency}` : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 米ドル振替額 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>米ドル振替額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.transferDepositAmount ? `${ifaFormatUtils.withCommaZeroPadding(form.transferDepositAmount, 2)} ${form.tradeCurrency}` : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 米国株式振替額 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>米国株式振替額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.transferValuableSecurityValuation ? `${ifaFormatUtils.withCommaZeroPadding(form.transferValuableSecurityValuation, 2)} ${form.tradeCurrency}` : '-' }}</span>
            </el-col>
          </el-row>

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
              <span>{{ form.quotePrice ? `${ifaFormatUtils.withCommaZeroPadding(form.quotePrice, 2)} ${form.tradeCurrency}` : '' }}</span>
            </el-col>
          </el-row>
          <!-- /見積価格 -->

          <!-- 概算建代金 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算建代金</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.approximatePositionAmount ? `${ifaFormatUtils.withCommaZeroPadding(form.approximatePositionAmount, 2)} ${form.tradeCurrency}` : '' }}</span>
            </el-col>
          </el-row>
          <!-- /概算建代金 -->

          <!-- 概算手数料 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算手数料</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.domesticCommForeign ? `${ifaFormatUtils.withCommaZeroPadding(form.domesticCommForeign, 2)} ${form.tradeCurrency}` : '' }}</span>
            </el-col>
          </el-row>
          <!-- /概算手数料 -->

          <!-- 概算消費税 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算消費税</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.domesticConsumptionTaxForeign ? `${ifaFormatUtils.withCommaZeroPadding(form.domesticConsumptionTaxForeign, 2)} ${form.tradeCurrency}` : '' }}</span>
            </el-col>
          </el-row>
          <!-- /概算消費税 -->

          <!-- 適用金利 -->
          <el-row
            v-if="form.tradeKbn === '3'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>適用金利</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.applicableInterestRate ? `年利${ifaFormatUtils.withCommaZeroPadding(form.applicableInterestRate, 2)}%` : '' }}</span>
            </el-col>
          </el-row>
          <!-- /適用金利 -->
          <!-- 適用貸株料 -->
          <el-row
            v-if="form.tradeKbn === '1'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>適用貸株料</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.applicableStockLendingFeeRate ? `年利${ifaFormatUtils.withCommaZeroPadding(form.applicableStockLendingFeeRate, 2)}%` : '' }}</span>
            </el-col>
          </el-row>
          <!-- /適用貸株料 -->

        </el-card>
      </el-col>
    </el-row>
    <!-- /概算注文見積 -->

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

          <!-- 英文開示銘柄 -->
          <el-row
            v-if="form.engPubText"
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
                :code-key="form.engPubText"
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
                :code-key="form.checkInsider"
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
    <el-row v-if="form.tradeNoticeInformationBrand ||
      form.noticeInfoAlert ||
      form.noticeAlert ||
      form.additionalCollateralRegulationBrandTradeMsg ||
      form.methodCheckMessage ||
      form.localTradeDateLimitMsg"
    >
      <el-col
        :span="22"
        :offset="1"
        style="color: #f00;"
      >
        <el-card
          :class="form.tradeKbn === '3' ? 'buy-background-color_card' : 'sell-background-color_card'"
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem; color: #f00;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <el-row
            v-if="form.tradeNoticeInformationBrand"
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
              <el-checkbox
                id="explained"
                v-model="form.AlertContentsConfirm.tradingCautionInformation"
                name="explained"
                label="説明済"
                style="margin-left: 5px; color: #f00;"
              ></el-checkbox>
            </el-col>
          </el-row>
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
              <el-checkbox
                id="invitationCheck"
                v-model="form.AlertContentsConfirm.noteInfoCheckbox"
                name="invitationCheck"
                label="確認済"
                style="margin-left: 5px; color: #f00;"
              ></el-checkbox>
            </el-col>
          </el-row>
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
              <el-checkbox
                id="invitationCheck"
                v-model="form.AlertContentsConfirm.noteLimitCheck"
                name="invitationCheck"
                label="確認済"
                style="margin-left: 5px; color: #f00;"
              ></el-checkbox>
            </el-col>
          </el-row>
          <el-row
            v-if="form.additionalCollateralRegulationBrandTradeMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
              style="padding-left: 0;"
            >
              <span class="required-mark">*</span><span style="color: #f00;">増し担保規制の確認</span>
            </el-col>
            <el-col :span="16">
              <el-checkbox
                id="regulationsCheck"
                v-model="form.AlertContentsConfirm.additionalCollateralRegulationsConfirm"
                name="regulationsCheck"
                label="確認済"
                style="margin-left: 5px; color: #f00;"
              ></el-checkbox>
            </el-col>
          </el-row>
          <el-row
            v-if="form.methodCheckMessage"
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
              <el-checkbox
                id="invitationCheck"
                v-model="form.AlertContentsConfirm.methodCheck"
                name="invitationCheck"
                label="確認済"
                style="margin-left: 5px; color: #f00;"
              ></el-checkbox>
            </el-col>
          </el-row>
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
              <el-checkbox
                id="invitationCheck"
                v-model="form.AlertContentsConfirm.nextDayCheck"
                name="invitationCheck"
                label="確認済"
                style="margin-left: 5px; color: #f00;"
              ></el-checkbox>
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
          :disabled="buttonDisabled"
          action-type="requestAction"
          :request-model="A010RequestModel"
          action-id="SUB0202_0303-01_2#A010"
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
import { IfaForeignMarginTradeNewOrderConfirmFormModel } from './js/IfaForeignMarginTradeNewOrderConfirmFormModel'
import { IfaForeignMarginTradeNewOrderConfirmA004RequestModel } from './js/IfaForeignMarginTradeNewOrderConfirmA004RequestModel'
import { IfaForeignMarginTradeNewOrderConfirmA010RequestModel } from './js/IfaForeignMarginTradeNewOrderConfirmA010RequestModel'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
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
    customerInfo: { type: Object, required: true },
    stockInfo: { type: Object, required: true },
    priorityMarket: { type: String, required: true }
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
      form: new IfaForeignMarginTradeNewOrderConfirmFormModel(),
      ifaFormatUtils: ifaFormatUtils,
      messages: {
        mains: [],
        errors: []
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
      return new IfaForeignMarginTradeNewOrderConfirmA004RequestModel(this.form)
    },
    A010RequestModel() {
      return new IfaForeignMarginTradeNewOrderConfirmA010RequestModel(this.form)
    },
    buttonDisabled() {
      // デモ
      if (this.form.AlertContentsConfirm.tradingCautionInformation === (this.form.tradeNoticeInformationBrand.length > 0) &&
        this.form.AlertContentsConfirm.noteInfoCheckbox === (this.form.noticeInfoAlert.length > 0) &&
        this.form.AlertContentsConfirm.noteLimitCheck === (this.form.noticeAlert.length > 0) &&
        this.form.AlertContentsConfirm.additionalCollateralRegulationsConfirm === (this.form.additionalCollateralRegulationBrandTradeMsg.length > 0) &&
        this.form.AlertContentsConfirm.methodCheck === (this.form.methodCheckMessage.length > 0) &&
        this.form.AlertContentsConfirm.nextDayCheck === (this.form.localTradeDateLimitMsg.length > 0) &&
        this.userInfo.medUsers.privId > 2) {
        return false
      } else {
        return true
      }
    },
    customerName() {
      return this.$_out(this.customerInfo.customerNameKanji) + '（' + this.$_out(this.customerInfo.customerNameKana) + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.customerInfo.accountNumber}`
    },
    // v-modelをcomputed->変数のように間接的に扱う場合はgetterとsetterの両方が必要。
    // 参考: https://qiita.com/shizen-shin/items/a48cfbe7c88cc9f42e30
    // 前日比の色を算出する
    ratioColor() {
      return function(value) {
        const n = Number(value)
        return n > 0 ? 'font-color__plus __bold' : n < 0 ? 'font-color__minus __bold' : '__black'
      }
    },
    // 上下矢印(現在値ティック)に表示する色を返す
    tickColor() {
      // if (this.stockInfo.current === '△' || this.stockInfo.current === '-' || this.stockInfo.current === '０') {
      //   return '__black'
      // }
      if (!this.form.priceBasicInfo?.[0].currentPrice) {
        return 'color: #606266;'
      }

      switch (this.form.priceBasicInfo?.[0].tick) {
        case '1':
          return 'color: #E5004C; font-weight: bold'
        case '2':
          return 'color: #00847F; font-weight: bold'
        default:
          return 'color: #606266; font-weight: bold;'
      }
    },
    target() {
      return 'link' + (this.requestProps.urlId === 0 ? '' : this.requestProps.urlId.toString())
    }
  },
  // エラー/警告/情報の更新
  beforeUpdate() {
    this.messages.mains.length = 0
    this.messages.mains.push('注文はまだ完了していません。画面下の注文発注ボタンを押下ください。')

    this.messages.errors.length = 0
    if (this.form.tradeNoticeInformationBrand) {
      this.messages.errors.push(this.form.tradeNoticeInformationBrand)
    }
    if (this.form.noticeInfoAlert) {
      this.messages.errors.push(this.form.noticeInfoAlert)
    }
    if (this.form.noticeAlert) {
      this.messages.errors.push(this.form.noticeAlert)
    }
    if (this.form.additionalCollateralRegulationBrandTradeMsg) {
      this.messages.errors.push(this.form.additionalCollateralRegulationBrandTradeMsg)
    }
    if (this.form.methodCheckMessage) {
      this.messages.errors.push(this.form.methodCheckMessage)
    }
    if (this.form.localTradeDateLimitMsg) {
      this.messages.errors.push(this.form.localTradeDateLimitMsg)
    }
  },
  methods: {
    onShow(tradeCd) {
      Object.assign(this.form, this.formData)
      this.form.tradeCd = tradeCd
    },
    stopPropagation(event) {
      event.stopPropagation()
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
    // 価格に表示する内容を生成する
    getPrice() {
      if (this.form.orderPriceKindList === '1') {
        return this.form.hiddenOrderPrice ? this.ifaFormatUtils.withCommaZeroPadding(this.form.hiddenOrderPrice, 2) + this.form.tradeCurrency : '0' + this.form.tradeCurrency
      } else if (this.form.orderPriceKindList === '3' || this.form.orderPriceKindList === '4') {
        let str = '（現在値が' + (this.form.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice ? this.ifaFormatUtils.withCommaZeroPadding(this.form.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice, 2) + this.form.tradeCurrency : '0' + this.form.tradeCurrency) +
                  (this.form.tradeKbn === '3' ? '以上' : '以下') + 'になった時点で'
        if (this.form.orderPriceKindList === '3') {
          str += (this.form.hiddenOrderPrice ? this.ifaFormatUtils.withCommaZeroPadding(this.form.hiddenOrderPrice, 2) + this.form.tradeCurrency : '0' + this.form.tradeCurrency) + 'で発注）'
        } else if (this.form.orderPriceKindList === '4') {
          str += '成行で発注）'
        }
        return str
      }
      return ''
    },

    // 戻るボタン
    backA011() {
      this.$emit('close-modal')
      this.form.AlertContentsConfirm.tradingCautionInformation = false
      this.form.AlertContentsConfirm.noteInfoCheckbox = false
      this.form.AlertContentsConfirm.noteLimitCheck = false
      this.form.AlertContentsConfirm.additionalCollateralRegulationsConfirm = false
      this.form.AlertContentsConfirm.methodCheck = false
      this.form.AlertContentsConfirm.nextDayCheck = false
    },

    // 注文発注ボタン
    orderPlacementA010: function(data) {
      this.$emit('order-finish', data.dataList[0])
      this.form.AlertContentsConfirm.tradingCautionInformation = false
      this.form.AlertContentsConfirm.noteInfoCheckbox = false
      this.form.AlertContentsConfirm.noteLimitCheck = false
      this.form.AlertContentsConfirm.additionalCollateralRegulationsConfirm = false
      this.form.AlertContentsConfirm.methodCheck = false
      this.form.AlertContentsConfirm.nextDayCheck = false
    },
    updateA004: function(data) {
      this.form = Object.assign(this.form, data.dataList[0])
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
    ifaForeignMarginTradeOrderConfirmGetNewMainSiteA012(urlId, patternId, httpMethod, brandCode) {
      const newData = {
        urlId: urlId,
        patternId: patternId,
        httpMethod: httpMethod,
        brandCode: brandCode
      }
      this.requestProps = newData
      this.ifaLinkRequestModel = new IfaLinkRequestModel(this.requestProps)
      document.getElementById('ifaForeignMarginTradeOrderConfirmGetNewMainSiteA012').click()
    },
    responseHandlerGetNewMainSiteA012(response) {
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

<style lang="scss">
  @import "@/styles/orderStatusList.scss";
.foreign-margin_dialog-class {
  .el-dialog__body {
    font-weight: normal !important;
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
.error-message {
  margin: 0 0 0.5rem;
  padding: 0 4rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
}
.error-message_title {
  margin: 0.5rem 0 0;
  padding: 0 3rem;
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
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
.input-table {
  width:95%;
  margin: 10px;
  border-collapse: collapse;
  background-color:  #fff;
  color: rgb(72,116,173);
  font: 11px/1.3 sans-serif;
  text-shadow:0 1px 0 #fff;
  border:1px solid #d8e8fa
}
.search_header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 15px;
  font-weight: bold;
  padding-right: 0.5rem;
  height: 25px;
  line-height: 25px;
  white-space: nowrap;
}
.update-button {
  text-align: right;
  position: absolute;
  right: 0.5rem;
  top: 0;
}
.market-label {
  color: #f00;
  font-weight: bold;
  padding-left: 0.1rem;
}
.brand-name {
  width: 100%;
  color: #606266;
  font-weight: bold;
  font-size: 14px;
  padding-left: 0.5rem;
}
.__bold {
  font-weight: bold;
}
.__black {
  color: #606266;
}
.__color_empty {
  color: #bfcbd9;
}
.__right {
  text-align: right;
  padding-right: 0;
}
.info_xs {
  display: grid;
  width: 100%;
  grid-template-columns: 3.5rem 1fr;
}
.info_s {
  display: grid;
  width: 100%;
  grid-template-columns: 5rem 1fr;
}
.info-item__header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 13px;
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
.info-item__empty {
  resize: none;
  border: none;
  color: #bfcbd9;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.badge-item {
  padding-top: 0.4rem;
  padding-left: 0.4rem;
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
  color: #ff2b2b;
  margin-right: 2px;
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
:deep(.el-dialog__title) {
  font-weight: bold;
}
.reset_style {
 font-size: 17px;
}
.brand_area_flex {
  display: flex;
  align-items: flex-start;
}
:deep(.el-text){
  font-size: 16px;
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
