<template>
  <!-- 信用返済注文受付確認ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :style="{ 'background-color': bgColor, 'font-weight': 'bold' }"
    :title="formModel.title"
    width="1200px"
    top="6%"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    class="foreign-margin_dialog-class"
  >
    <ifa-requester
      id="ifaForeignMarginTradeRepayOrderInputGetNewMainSiteA012"
      action-id="SUB0202_0303-04_2#A012"
      action-type="requestAction"
      :request-model="ifaLinkRequestModel"
      @response-handler="responseHandlerGetNewMainSiteA012($event)"
    ></ifa-requester>

    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="23"
        style="text-align: right;"
      >
        <ifa-button
          id="btnBack"
          text="戻る"
          color="secondary"
          class="form-button__wrapper"
          action-type="originalAction"
          style="padding-right: 18px;"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="messages.mains"
      :error-messages="messages.errors"
    ></ifa-message-area>
    <!-- エラー/警告表示 -->

    <!-- 顧客情報 -->
    <el-row style="font-weight: bold;">
      <el-col :offset="1">
        <span>{{ customerInfo.butenCode + '-' +　$_zeroPadding(customerInfo.accountNumber,7) }}</span>
      </el-col>
    </el-row>
    <el-row
      class="_bold_black_l"
      style="padding-top: 0.5rem; font-size: 20px;"
    >
      <el-col
        :offset="1"
        :span="20"
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
      <el-col
        :span="2"
        style="text-align: right;"
      >
        <ifa-button
          id="btnUpdate"
          text="更新"
          icon="RefreshRight"
          small
          action-type="requestAction"
          action-id="SUB0202_0303-04_2#A004"
          :request-model="IfaForeignMarginTradeRepayOrderConfirmA004RequestModel"
          @response-handler="responseHandlerA004"
        ></ifa-button>
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
              style="padding: 0.2rem 0.5rem;"
            >
              <div
                class="brand_area_flex _bold_black_l"
                style="font-size: 20px; min-width: 343px; max-width: 665px;"
              >
                <span
                  style="display: inline-block; white-space: nowrap;"
                >［{{ $_out(formModel.brandCode) }}］</span>
                <span
                  style="display: inline-block; word-break: break-all;"
                >{{ $_out(formModel.brandName) }}</span>
              </div>
              <div
                class="_bold_black_l"
                style="font-size: 20px; white-space: nowrap; min-width:253px;"
              >&nbsp;&nbsp;上場市場：{{ $_out(formModel.marketAbbreviatedName) }}</div>
              <div
                style="min-width: 140px; margin-left: auto;"
              >
                <el-icon style="color: red; position: relative; top: 3px;">
                  <WarningFilled></WarningFilled>
                </el-icon>
                <ifa-link
                  :param-url="formModel.tradeLimitUrl"
                  :disp-name="'取引注意情報'"
                  link-icon-image="externalLink"
                ></ifa-link>
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
                    @click="ifaForeignMarginTradeRepayOrderInputGetNewMainSiteA012(51,'1','POST',formModel.brandCode)"
                  > {{ "株価チャート" }}
                  </el-link>
                  <el-icon
                    class="external-link-icon"
                    @click="ifaForeignMarginTradeRepayOrderInputGetNewMainSiteA012(51,'1','POST',formModel.brandCode)"
                  >
                    <img
                      src="@/assets/icons/external-link.svg"
                    >
                  </el-icon>
                </div>
              </div>
            </el-col>
          </el-row>

          <!-- 時価情報 -->
          <el-row
            :gutter="20"
            style="margin-top: 1rem;"
          >
            <el-col
              :span="8"
            >
              <div class="info_xs">
                <span class="info-item__header __left">現在値:</span>
                <span
                  class="info-item__current __right"
                  style="text-align: center;"
                >

                  <el-row v-if="formModel.brandCode !== ''">
                    <el-col>
                      <span>{{ $_out($_withCommaZeroPadding(formModel.priceBasicInfo?.currentPrice, 4)) }} </span>
                      <span
                        :class="[tickColor()]"
                        class="__bold"
                      >{{ $_out(tickLabel()) }} </span>
                    </el-col>
                  </el-row>
                  <el-row
                    v-else
                    class="__color_empty __center"
                  >
                    <span>-</span>
                  </el-row>
                </span>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="info_xs">
                <span class="info-item__header __left">始値:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaZeroPadding(formModel.priceBasicInfo?.start, 4)) }}</span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">高値:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaZeroPadding(formModel.priceBasicInfo?.high, 4)) }}</span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">安値:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaZeroPadding(formModel.priceBasicInfo?.low, 4)) }}</span>
              </div>
            </el-col>
          </el-row>
          <el-row
            :gutter="20"
            style="padding-top: 0.5rem;"
          >
            <el-col
              :span="8"
            >
              <div class="info_xs">
                <span class="info-item__header __left">前日比:</span>
                <span class="info-item__value __right">
                  <span :class="[ratioColor()]">{{ $_out($_signedWithCommaZeroPadding(formModel.priceBasicInfo?.diff, 2)) }}</span>
                  <span> (</span>
                  <span :class="[ratioColor()]">{{ $_out($_signedWithCommaZeroPadding(formModel.priceBasicInfo?.diffPercentage, 2)) }}%</span>
                  <span>) </span>
                  <span>({{ formModel.priceBasicInfo?.currentDateTime ? $_getFormattedDateValue(formModel.priceBasicInfo?.currentDateTime, 'date6') : '--/--/--' }}</span>
                  <span>&nbsp;{{ formModel.priceBasicInfo?.currentDateTime ? $_getFormattedTimeValue(formModel.priceBasicInfo?.currentDateTime.substring(11,16), 'time4') : '--:--' }}</span>
                  <span>&nbsp;{{ $_out(formModel.timeZoneAbbreviatedName) }})</span>
                </span>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="info_xs">
                <span class="info-item__header __left">前日終値:</span>
                <span class="info-item__value __right"
                      style="font-size: 10.5px"
                >
                  {{ $_out($_withCommaZeroPadding(formModel.priceBasicInfo?.last, 4)) }} ({{ $_out($_getFormattedDateValue(formModel.priceBasicInfo?.lastDate, 'date6')) }})
                </span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">出来高:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaInteger(formModel.priceBasicInfo?.volume)) }}</span>
              </div>
            </el-col>
            <el-col
              :span="5"
              style="text-align: right;"
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
                <el-icon><WarningFilled></WarningFilled></el-icon>
                <span><b>米国株式&emsp;ご注意事項</b></span>
              </el-col>
            </el-row>
            <!-- 再描画を行うためにv-ifを追加 -->
            <el-row
              style="padding-top: 0.5rem;"
            >
              <el-col
                :offset="1"
                :span="5"
              >
                <ifa-link
                  :param-url="formModel.tradeLimitUrl"
                  :disp-name="'・本日の注意銘柄'"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
              <el-col :span="3">
                <ifa-link
                  :param-url="formModel.closedDayUrl"
                  :disp-name="'・休場日'"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
              <el-col :span="5">
                <ifa-link
                  :param-url="formModel.yenClosedUrl"
                  :disp-name="'・円貨決済停止日'"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
              <el-col :span="5">
                <ifa-link
                  :param-url="formModel.usequityListUrl"
                  :disp-name="'・取扱銘柄一覧'"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
              <el-col :span="5">
                <ifa-link
                  :param-url="formModel.tradingAttentionUrl"
                  :disp-name="'・お取引注意事項'"
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
          <!-- /ご注文内容（ラベル） -->
          <hr>
          <!-- 取引種別 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col :span="16">
              <span
                v-if="formModel.tradeCd === '4'"
                class="font-color__plus bold"
              >
                {{ $_out($_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, formModel.tradeCd)) }}
                ({{ $_out($_getCodeValue('MARGIN_DUE_DATE', 1, formModel.marginDueDate)) }})
              </span>
              <span
                v-else
                class="font-color__minus bold"
              >
                {{ $_out($_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, formModel.tradeCd)) }}
                ({{ $_out($_getCodeValue('MARGIN_DUE_DATE', 1, formModel.marginDueDate)) }})
              </span>
            </el-col>
          </el-row>
          <!-- /取引種別 -->

          <!-- 数量 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>数量</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_withCommaInteger(formModel.orderQuantity)) }}&nbsp;株</span>
            </el-col>
          </el-row>
          <!-- /数量 -->

          <!-- 返済建玉指定方法 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>返済建玉指定方法</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_out($_getCodeValue('FOREIGN_REPAY_METHOD', 1, formModel.repayPositionDesignateMethod)) }}&nbsp;
                ({{ $_out($_getCodeValue('FOREIGN_REPAY_ORDER', 1, formModel.repaySelectOrder)) }})
              </span>
            </el-col>
          </el-row>
          <!-- /返済建玉指定方法 -->

          <!-- 価格 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col :span="16">
              <el-col
                v-if="formModel.orderPriceKindList == '1' || formModel.orderPriceKindList == '2'"
                :span="16"
              >
                {{ $_out($_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', 1, formModel.orderPriceKindList)) }}<br>
                <span v-if="formModel.orderPriceKindList == '1'">{{ $_out($_withCommaZeroPadding(formModel.hiddenOrderPrice, 2)) }}{{ $_out(formModel.currencyCode) }}</span>
              </el-col>
              <el-col
                v-if="formModel.orderPriceKindList == '3' || formModel.orderPriceKindList == '4'"
                :span="16"
              >
                {{ $_out($_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', 1, formModel.orderPriceKindList)) }}
              </el-col>
              <el-row
                v-if="formModel.orderPriceKindList == '3' || formModel.orderPriceKindList == '4'"
                :span="16"
              >
                {{ getPrice() }}
              </el-row>
            </el-col>
          </el-row>
          <!-- /価格 -->

          <!-- 期間 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>期間</span>
            </el-col>
            <el-col :span="16">
              <span v-if="formModel.periodRadio === '0'">{{ $_getCodeValue('PERIOD_CONDITIONS', 2, formModel.periodRadio) }}</span>
              <span v-if="formModel.periodRadio === '1'">{{ $_out($_getFormattedDateValue(formModel.period)) + "まで" }}</span>
            </el-col>
          </el-row>
          <!-- /期間 -->

          <!-- 預り区分 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>預り区分</span>
            </el-col>
            <el-col :span="16">
              {{ $_out($_getCodeValue('FOREIGN_DEPOSIT_TYPE', 2, formModel.depositType)) }}
            </el-col>
          </el-row>
          <!-- /預り区分 -->

          <!-- 決済方法 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>決済方法</span>
            </el-col>
            <el-col :span="16">
              {{ $_out($_getCodeValue('SETTLEMENT_TYPE', 1, formModel.kessaiHoho)) }}
            </el-col>
          </el-row>
          <!-- /決済方法 -->

          <!-- 現地約定予定日 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>現地約定予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getFormattedDateValue(formModel.localTradeDate)) }}</span>
            </el-col>
          </el-row>
          <!-- 現地約定予定日 -->

          <!-- 国内約定予定日 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>国内約定予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getFormattedDateValue(formModel.businessDaysAfterOrder)) }}</span>
            </el-col>
          </el-row>
          <!-- /国内約定予定日 -->

          <!-- 国内受渡予定日 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>国内受渡予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getFormattedDateValue(formModel.domesticSettlementDate)) }}</span>
            </el-col>
          </el-row>
          <!-- /国内受渡予定日 -->
        </el-card>
      </el-col>
    </el-row>
    <!-- /注文内容(復唱項目) -->

    <el-row>
      <div
        style="margin-bottom: 0.5rem;"
      ></div>
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
          <!-- /概算注文見積（ラベル） -->
          <hr>
          <!-- 見積単価 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>見積価格</span>
            </el-col>
            <el-col
              :span="16"
            >
              <span>{{ $_out($_withCommaZeroPadding(formModel.quotePrice, 2)) }}&nbsp;{{ $_out(formModel.currencyCode) }}</span>
            </el-col>
          </el-row>
          <!-- /見積単価 -->

          <!-- 概算約定代金 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算約定代金</span>
            </el-col>
            <el-col
              :span="16"
            >
              <span>{{ $_out($_withCommaZeroPadding(formModel.contractAmountForeign, 2)) }}&nbsp;{{ $_out(formModel.currencyCode) }}</span>
            </el-col>
          </el-row>
          <!-- /概算約定代金 -->

          <!-- 概算諸経費等 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算諸経費等</span>
            </el-col>
            <el-col
              :span="16"
            >
              <span>{{ $_out($_withCommaZeroPadding(formModel.approximateCost, 2)) }}&nbsp;{{ $_out(formModel.currencyCode) }}</span>
            </el-col>
          </el-row>
          <!-- /概算諸経費等 -->

          <!-- 概算損益金 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算損益金</span>
            </el-col>
            <el-col
              :span="16"
            >
              <span
                :class="formModel.settlement === '' ? 'black-normal' : formModel.settlement < 0 ? 'font-color__minus bold' : 'font-color__plus bold'"
              >
                {{ formModel.settlement === '' ? '-' : $_out($_signedWithCommaZeroPadding(formModel.settlement, 2)) }}&nbsp;{{ $_out(formModel.currencyCode) }}
              </span>
            </el-col>
          </el-row>
          <!-- /概算損益金 -->
        </el-card>
      </el-col>
    </el-row>
    <!-- /概算注文見積 -->

    <el-row>
      <div
        style="margin-bottom: 0.5rem;"
      ></div>
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
          <!-- /その他注文内容（ラベル） -->
          <hr>
          <!-- 勧誘区分 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('FOREIGN_STOCK_INVITATION_TYPE', 1, formModel.kanyuKbn)) }}</span>
            </el-col>
          </el-row>
          <!-- /勧誘区分 -->

          <!-- 受注方法 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受注方法</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('FOREIGN_STOCK_ORDER_METHOD_TYPE', 1, formModel.receiveOrderType)) }}</span>
            </el-col>
          </el-row>
          <!-- /受注方法 -->

          <!-- 確認項目 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>確認項目</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('INSIDER_CONFIRM', 2, formModel.checkInsider)) }}</span>
            </el-col>
          </el-row>
          <!-- /確認項目 -->
        </el-card>
      </el-col>
    </el-row>
    <!-- /その他注文内容 -->

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row v-if="messagesIsVisible">
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          :class="formModel.tradeCd === '4' ? 'buy-background-color_card' : 'sell-background-color_card'"
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
              :span="8"
              class="_bold_red_alart"
            >
              <div class="required-mark">*</div><span style="color: #f00;">取引注意情報の説明</span>
            </el-col>
            <el-col :span="16">
              <ifa-input-check
                id="explained"
                v-model="formModel.tradingCautionInformation"
                name="explained"
                class="warning-message"
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
              :span="8"
              class="_bold_red_alart"
            >
              <div class="required-mark">*</div><span style="color: #f00;">注意情報の確認</span>
            </el-col>
            <el-col :span="16"
                    style="font-weight: bold; color: red;"
            >
              <ifa-input-check
                id="invitationCheck"
                v-model="formModel.noteInfoCheckbox"
                name="invitationCheck"
                class="warning-message"
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
              :span="8"
              class="_bold_red_alart"
            >
              <div class="required-mark">*</div><span style="color: #f00;">重要なお知らせの確認</span>
            </el-col>
            <el-col :span="16"
                    style="font-weight: bold; color: red;"
            >
              <ifa-input-check
                id="invitationCheck"
                v-model="formModel.noteLimitCheck"
                name="invitationCheck"
                class="warning-message"
                :code-list-id="'IMPORTANT_NOTIFICATION_CONFIRM'"
                :select-pattern="2"
                :disp-pattern="1"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.methodCheckMessage && formModel.methodCheckMessage.length > 0 "
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <div class="required-mark">*</div><span style="color: #f00;">逆指値注文即時発火</span>
            </el-col>
            <el-col :span="16"
                    style="font-weight: bold; color: red;"
            >
              <ifa-input-check
                id="methodCheck"
                v-model="formModel.methodCheck"
                name="methodCheck"
                class="warning-message"
                :code-list-id="'IMMEDIATE_STOP_ORDER'"
                :select-pattern="2"
                :disp-pattern="1"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.localTradeDateLimitMsg && formModel.localTradeDateLimitMsg.length > 0 "
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
              style="margin-top:10px"
            >
              <div class="required-mark">*</div><span style="color: #f00;">翌営業日向け注文</span>
            </el-col>
            <el-col :span="16"
                    style="font-weight: bold; color: red;"
            >
              <ifa-input-check
                id="nextDayCheck"
                v-model="formModel.nextDayCheck"
                name="nextDayCheck"
                class="warning-message"
                :code-list-id="'ORDER_FOR_NEXT_BUSINESS_DAY'"
                :select-pattern="2"
                :disp-pattern="1"
              ></ifa-input-check>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!-- 注文発注ボタン -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="text-align: left; margin-top: 1rem;"
      >
        <ifa-button
          id="btnOrderRegister"
          text="注文発注"
          color="primary"
          :disabled="editDisable"
          action-type="requestAction"
          action-id="SUB0202_0303-04_2#A010"
          style="padding-left: 0"
          :request-model="IfaForeignMarginTradeRepayOrderConfirmA010RequestModel"
          @response-handler="responseHandlerA010"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaForeignMarginTradeRepayOrderConfirmFormModel } from './js/IfaForeignMarginTradeRepayOrderConfirmFormModel'
import { IfaForeignMarginTradeRepayOrderConfirmA004RequestModel } from './js/IfaForeignMarginTradeRepayOrderConfirmA004RequestModel'
import { IfaForeignMarginTradeRepayOrderConfirmA010RequestModel } from './js/IfaForeignMarginTradeRepayOrderConfirmA010RequestModel'
import { IfaForeignMarginTradeRepayOrderCompleteFormModel } from './js/IfaForeignMarginTradeRepayOrderCompleteFormModel'
import IfaLink from '@/components/Link/IfaLink'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaLinkRequestModel } from './js/IfaLinkRequestModel'
import { notifyMessage } from '@/utils/errorHandler'
export default {
  components: {
    IfaLink,
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    confirmFormModel: { type: Object, required: true },
    customerInfo: { type: [Object, Function], required: true },
    stockInfo: { type: Object, required: true }
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
      formModel: new IfaForeignMarginTradeRepayOrderConfirmFormModel(),
      completeFormModel: new IfaForeignMarginTradeRepayOrderCompleteFormModel(),
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
    IfaForeignMarginTradeRepayOrderConfirmA004RequestModel() { return new IfaForeignMarginTradeRepayOrderConfirmA004RequestModel(this.formModel) },
    IfaForeignMarginTradeRepayOrderConfirmA010RequestModel() { return new IfaForeignMarginTradeRepayOrderConfirmA010RequestModel(this.formModel) },
    // 背景色
    bgColor() {
      if (this.formModel.tradeCd === '4') {
        return '#fef0f0'
      } else if (this.formModel.tradeCd === '5') {
        return '#ecf5ff'
      }
      return '#fdf6ec'
    },
    // アラーム確認
    messagesIsVisible() {
      if ((this.formModel.tradeNoticeInfoBrandMsg && this.formModel.tradeNoticeInfoBrandMsg.length > 0) ||
        (this.formModel.noticeInfoAlert && this.formModel.noticeInfoAlert.length > 0) ||
        (this.formModel.noticeAlert && this.formModel.noticeAlert.length > 0) ||
        (this.formModel.methodCheckMessage && this.formModel.methodCheckMessage.length > 0) ||
        (this.formModel.localTradeDateLimitMsg && this.formModel.localTradeDateLimitMsg.length > 0)) {
        return true
      } else {
        return false
      }
    },
    // 注文発注ボタンの活性/非活性条件　trueの場合非活性　falseの場合活性
    editDisable() {
      return ((this.formModel.tradeNoticeInfoBrandMsg && this.formModel.tradeNoticeInfoBrandMsg.length > 0 && this.formModel.tradingCautionInformation.length !== 1) ||
      (this.formModel.noticeInfoAlert && this.formModel.noticeInfoAlert.length > 0 && this.formModel.noteInfoCheckbox.length !== 1) ||
      (this.formModel.noticeAlert && this.formModel.noticeAlert.length > 0 && this.formModel.noteLimitCheck.length !== 1) ||
      (this.formModel.methodCheckMessage && this.formModel.methodCheckMessage.length > 0 && this.formModel.methodCheck.length !== 1) ||
      (this.formModel.localTradeDateLimitMsg && this.formModel.localTradeDateLimitMsg.length > 0 && this.formModel.nextDayCheck.length !== 1)) ||
      (this.$store.getters.userAccount && this.$store.getters.userAccount.medUsers.privId === '1' || this.$store.getters.userAccount.medUsers.privId === '2')
    },
    target() {
      return 'link' + (this.requestProps.urlId === 0 ? '' : this.requestProps.urlId.toString())
    }
  },
  // エラー/警告/情報の更新
  beforeUpdate() {
    this.messages.mains = []
    this.messages.errors = []

    this.messages.mains.push('注文はまだ完了していません。画面下の注文発注ボタンを押下ください。')

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
    if (this.formModel.methodCheckMessage && this.formModel.methodCheckMessage.length > 0) {
      if (Array.isArray(this.formModel.methodCheckMessage)) {
        this.messages.errors.push(...this.formModel.methodCheckMessage)
      } else {
        this.messages.errors.push(this.formModel.methodCheckMessage)
      }
    }
    if (this.formModel.localTradeDateLimitMsg && this.formModel.localTradeDateLimitMsg.length > 0) {
      if (Array.isArray(this.formModel.localTradeDateLimitMsg)) {
        this.messages.errors.push(...this.formModel.localTradeDateLimitMsg)
      } else {
        this.messages.errors.push(this.formModel.localTradeDateLimitMsg)
      }
    }
  },
  methods: {
    // 表示時
    onShow() {
      this.formModel = Object.assign(this.formModel, this.confirmFormModel)
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // Infoアイコンイベント
    stopPropagation(event) {
      event.stopPropagation()
    },
    // 価格に表示する内容を生成する
    getPrice() {
      let str = ''
      if (this.formModel.orderPriceKindList === '3' || this.formModel.orderPriceKindList === '4') {
        str += '(現在値が' + this.$_out(this.$_withCommaZeroPadding(this.formModel.stopOrderPrice, 2))
        str += this.$_out(this.formModel.currencyCode) + (this.formModel.tradeKbn === '3' ? '以上' : '以下') + 'になった時点で'
      }
      if (this.formModel.orderPriceKindList === '3') {
        str += this.$_out(this.$_withCommaZeroPadding(this.formModel.hiddenOrderPrice, 2)) + this.$_out(this.formModel.currencyCode) + 'で発注)'
      } else if (this.formModel.orderPriceKindList === '4') {
        str += '成行で発注)'
      }
      return str
    },
    responseHandlerA004(response) {
      this.formModel = Object.assign(this.formModel, response.dataList[0])
    },
    responseHandlerA010(response) {
      this.completeFormModel = Object.assign(this.completeFormModel, response.dataList[0])
      this.$emit('order-finish', this.completeFormModel)
    },
    tickColor() {
      if (!this.formModel.priceBasicInfo?.currentPrice) {
        return '__black'
      }
      switch (this.formModel.priceBasicInfo?.tickArrow) {
        case '1':
          return 'font-color__plus __bold'
        case '2':
          return 'font-color__minus __bold'
        default:
          return '__black __bold'
      }
    },
    ratioColor() {
      const n = Number(this.formModel.priceBasicInfo?.diff)
      return n > 0 ? 'font-color__plus' : n < 0 ? 'font-color__minus __bold' : '__black'
    },
    tickLabel() {
      if (!this.formModel.priceBasicInfo?.currentPrice) {
        // 現在値＝｢" "、-、0｣の場合、表示しない
        return ' '
      }
      switch (this.formModel.priceBasicInfo?.tickArrow) {
        case '1':
          // 1=↑
          return '↑'
        case '2':
          // 2=↓
          return '↓'
        case ' ':
        case '-':
        case '0':
          // [" ", -, 0]=''
          return ' '
        default:
          // 未指定時=スペース
          return ' '
      }
    },
    customerInfoA() {
      return this.$store.getters.customerInfo
    },
    ifaForeignMarginTradeRepayOrderInputGetNewMainSiteA012(urlId, patternId, httpMethod, brandCode) {
      const newData = {
        urlId: urlId,
        patternId: patternId,
        httpMethod: httpMethod,
        brandCode: brandCode
      }
      this.requestProps = newData
      this.ifaLinkRequestModel = new IfaLinkRequestModel(this.requestProps)
      document.getElementById('ifaForeignMarginTradeRepayOrderInputGetNewMainSiteA012').click()
    },
    responseHandlerGetNewMainSiteA012(response) {
      Object.assign(this.formModel, response?.dataList?.[0])
      if (this.formModel.postRequest) {
        this.request = this.formModel.postRequest
      }
      this.openWindow()
    },
    openWindow() {
      // cursor: not-allowed; と pointer-events: none; が同時に使えなかったため
      this.linkUrl = this.formModel.linkUrl
      this.paramObject = this.formModel.newMainSiteParamList[0]
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
@import "@/styles/orderStatusList.scss";
.foreign-margin_dialog-class {
  .el-dialog__body {
    font-weight: normal !important;
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
  padding: 0 8rem;
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
  font-size: 14px;
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
  padding-right: 0.5rem;
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
  font-size: 12px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__value {
  resize: none;
  border: none;
  color: #606266;
  font-size: 11px;
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
.buy-background-color {
  background-color: #fef0f0;
}
.sell-background-color {
  background-color: #ecf5ff;
}
.buy-background-color_card {
  background-color: #fef0f0;
}
.sell-background-color_card {
  background-color: #ecf5ff;
}
.data-provider {
  resize: none;
  border: none;
  color: #bfcbd9;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
}
.brand_area_flex {
  display: flex;
  align-items: flex-start;
}
:deep(.el-text){
  font-size: 16px;
}
.required-mark {
  display: inline-block;
  color: #ff2b2b;
  width: 8px;
}
._bold_red_alart {
  font-size: 16px;
  font-weight: bold;
  padding-right: 0.5rem;
  color: red;
}
:deep(.el-checkbox__label) {
  padding-bottom: 0px;
  color: #f00;
  font-size: 16px;
  font-weight: bold;
}
:deep(.el-checkbox__input) {
  padding-bottom: 0px;
  color: #f00;
  font-size: 16px;
  font-weight: bold;
}
:deep(.el-checkbox__input.is-checked) + .el-checkbox__label {
    color: #f00;
}
.black-normal{
  color: black;
  font-weight: normal;
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
