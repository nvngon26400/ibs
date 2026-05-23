<template>
  <div>
    <ifa-requester
      id="ifaForeignMarginTradeRepayOrderInputA003"
      action-id="SUB07-06#X001"
      action-type="requestAction"
      :request-model="IfaForeignMarginTradeRepayOrderInputA003RequestModel"
      @response-handler="responseHandlerA003"
    ></ifa-requester>
    <ifa-requester
      id="ifaForeignMarginTradeRepayOrderInputGetNewMainSiteA021"
      action-id="SUB0202_0303-04_1#A021"
      action-type="requestAction"
      :request-model="ifaLinkRequestModel"
      @response-handler="responseHandlerGetNewMainSiteA021($event)"
    ></ifa-requester>
    <screen-title :text="formModel.title"></screen-title>
    <div
      v-if="formModel.foreignStockMarginPositionSummary.stockNumOrderable <= 0"
      :class="'font-color__plus bold'"
      style="margin-left:2%"
    >{{ formModel.settlementOrderAbleQuantityMsg }}</div>
    <div class="caption_card">
      <!-- 銘柄検索 -->
      <el-row style="display: flex;">
        <el-card
          style="background-color: #eee; margin-bottom: 0.5rem;"
        >
          <el-row>
            <el-col :span="8"
                    style="padding-left: 1rem;"
            >
              <span class="search_header __left">銘柄:</span>
              <span>[ {{ $_out(formModel.brandCode) }} ]</span>
              <span
                v-if="brandCode !== ''"
                class="search_header __left"
                style="margin-left:40px"
              >{{ $_out(formModel.brandName) }}</span>
            </el-col>
            <el-col :span="6">
              <span class="search_header __left">上場市場:</span>
              <span
                class="search_header __left"
                style="margin-left:10px"
              >{{ $_out(formModel.marketAbbreviatedName) }}</span>
            </el-col>

            <el-col
              v-if="formModel.tradeLimitTitle === 'true'"
              :span="3"
            >
              <span style="position: relative; top: 3px;">
                <el-icon
                  style="color: red;"
                ><WarningFilled></WarningFilled></el-icon>
              </span>
              <ifa-link
                :disp-name="'取引注意情報'"
                :param-url="formModel.tradeLimitUrl"
                link-icon-image="externalLink"
              ></ifa-link>
            </el-col>
            <el-col
              :span="3"
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
                  @click="ifaForeignMarginTradeRepayOrderInputGetNewMainSiteA021(51,'1','POST',formModel.brandCode)"
                > {{ "株価チャート" }}
                </el-link>
                <el-icon
                  class="external-link-icon"
                  @click="ifaForeignMarginTradeRepayOrderInputGetNewMainSiteA021(51,'1','POST',formModel.brandCode)"
                >
                  <img
                    src="@/assets/icons/external-link.svg"
                  >
                </el-icon>
              </div>
            </el-col>
            <div
              :span="5"
              class="update-button"
            >
              <ifa-button
                id="btnUpdate"
                text="更新"
                icon="RefreshRight"
                small
                action-type="requestAction"
                action-id="SUB0202_0303-04_1#A005"
                :request-model="IfaForeignMarginTradeRepayOrderInputA005RequestModel"
                @response-handler="responseHandlerA005"
              ></ifa-button>
            </div>
          </el-row>

          <!-- 時価情報 -->
          <el-row
            :gutter="22"
            style="margin-top: 1rem;"
          >
            <el-col
              :span="7"
              :offset="1"
              style="padding-left: 10px;"
            >
              <div class="info_xs">
                <span class="info-item__header __left">現在値:</span>
                <span
                  class="info-item__current __right"
                  style="text-align: center;"
                >
                  <el-row v-if="formModel.brandCode !== ''">
                    <el-col>
                      <span>{{ $_out($_withCommaZeroPadding(formModel.priceBasicInfo.currentPrice, 4)) }} </span>
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
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">始値:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaZeroPadding(formModel.priceBasicInfo.start, 4)) }}</span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">高値:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaZeroPadding(formModel.priceBasicInfo.high, 4)) }}</span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">安値:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaZeroPadding(formModel.priceBasicInfo.low, 4)) }}</span>
              </div>
            </el-col>
          </el-row>
          <el-row
            :gutter="22"
            style="padding-top: 0.5rem;"
          >
            <el-col
              :span="7"
              :offset="1"
            >
              <div class="info_xs">
                <span class="info-item__header __left">前日比:</span>
                <span class="info-item__value __right">
                  <span :class="[ratioColor()]">{{ $_out($_signedWithCommaZeroPadding(formModel.priceBasicInfo.diff, 2)) }}</span>
                  <span> (</span>
                  <span :class="[ratioColor()]">{{ $_out($_signedWithCommaZeroPadding(formModel.priceBasicInfo.diffPercentage, 2)) }}%</span>
                  <span>) </span>
                  <span>({{ formModel.priceBasicInfo.currentDateTime ? $_getFormattedDateValue(formModel.priceBasicInfo.currentDateTime, 'date6') : '--/--/--' }}</span>
                  <span>&nbsp;{{ formModel.priceBasicInfo.currentDateTime ? $_getFormattedTimeValue(formModel.priceBasicInfo.currentDateTime.substring(11,16), 'time4') : '--:--' }}</span>
                  <span>&nbsp;{{ $_out(formModel.timeZoneAbbreviatedName) }})</span>
                </span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">前日終値:</span>
                <span class="info-item__value __right"
                      style="font-size: 10.5px"
                >{{ $_out($_withCommaZeroPadding(formModel.priceBasicInfo.last, 4)) }} ({{ $_out($_getFormattedDateValue(formModel.priceBasicInfo.lastDate, 'date6')) }})</span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">出来高:</span>
                <span class="info-item__value __right">{{ $_out($_withCommaInteger(formModel.priceBasicInfo.volume)) }}</span>
              </div>
            </el-col>
            <el-col
              :span="5"
              style="text-align: right; padding-top: 10px;"
            >
              <span class="data-provider">© REFINITIV</span>
            </el-col>
          </el-row>
        </el-card>
      </el-row>
      <!-- /銘柄検索 -->

      <!-- ＊＊株式 ご注意事項 -->
      <el-row>
        <el-card
          v-if="formModel.brandCode !== ''"
          style="background-color: #eee;
          margin-bottom: 0.5rem;"
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
            <el-row style="padding-top: 0.5rem;">
              <el-col
                :offset="1"
                :span="4"
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
              <el-col :span="4">
                <ifa-link
                  :param-url="formModel.yenClosedUrl"
                  :disp-name="'・円貨決済停止日'"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
              <el-col :span="4">
                <ifa-link
                  :param-url="formModel.usequityListUrl"
                  :disp-name="'・取扱銘柄一覧'"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
              <el-col :span="4">
                <ifa-link
                  :param-url="formModel.tradingAttentionUrl"
                  :disp-name="'・お取引注意事項'"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-col>
            </el-row>
          </el-row>
        </el-card>
      </el-row>
      <!-- /＊＊株式 ご注意事項 -->

      <!-- 建玉情報 -->
      <el-row>
        <table class="_table__horizontal _table__body">
          <thead>
            <tr>
              <th
                class="_table__header __center __bold"
                style="width: 5%; min-width: 8rem;"
              >
                建区分<br>
                期限
              </th>
              <th
                class="_table__header __center __bold"
                style="width: 14%; min-width: 10rem;"
              >建単価平均</th>
              <th
                class="_table__header __center __bold"
                style="width: 14%; min-width: 10rem;"
              >建株数合計<br>(注文中合計)
              </th>
              <th
                class="_table__header __center __bold"
                style="width: 14%; min-width: 10rem;"
              >注文可能数量</th>
              <th
                class="_table__header __center __bold"
                style="width: 14%; min-width: 10rem;"
              >建代金合計</th>
              <th
                class="_table__header __center __bold"
                style="width: 14%; min-width: 10rem;"
              >諸経費等</th>
              <th
                class="_table__header __center __bold"
                style="width: 20%; min-width: 10rem;"
              >評価損益合計</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td class="_table__data __center">
                <span :class="formModel.foreignStockMarginPositionSummary.tradeKbn === '3' ? 'font-color__plus bold' : 'font-color__minus bold'">
                  {{ $_out($_getCodeValue('SELL_BUY_TYPE', 6, formModel.foreignStockMarginPositionSummary.tradeKbn)) }}<br>
                </span>
                <span>{{ $_out($_getCodeValue('MARGIN_DUE_DATE', 1, formModel.foreignStockMarginPositionSummary.marginDueDate)) }}</span>
              </td>
              <td class="_table__data __right">
                {{ $_out($_withCommaZeroPadding(formModel.foreignStockMarginPositionSummary.previousDayValue, 2)) }}&nbsp;
                {{ formModel.currencyCode }}
              </td>
              <td class="_table__data __right">
                {{ $_out($_withCommaInteger(formModel.foreignStockMarginPositionSummary.quantity)) }}株<br>
                ({{ $_out($_withCommaInteger(formModel.foreignStockMarginPositionSummary.stockNumOrdering)) }}&nbsp;株)
              </td>
              <td class="_table__data __right">
                {{ $_out($_withCommaInteger(formModel.foreignStockMarginPositionSummary.stockNumOrderable)) }}株
              </td>
              <td class="_table__data __right">
                {{ $_out($_withCommaZeroPadding(formModel.foreignStockMarginPositionSummary.foreignNewPositionAmount, 2)) }}&nbsp;USD
              </td>
              <td
                class="_table__data __right"
                style="color: #092987;"
              >
                <span
                  style="text-decoration: underline;cursor: pointer;"
                  @click="a003Action"
                >{{ $_out($_withCommaZeroPadding(formModel.foreignStockMarginPositionSummary.costForeignLink, 2)) }}&nbsp;{{ formModel.currencyCode }}</span>
              </td>
              <td class="_table__data __right">
                <div
                  :class="formModel.foreignStockMarginPositionSummary.foreignPositionTotalProfitOrLossValuation === '' ? 'black-normal' : formModel.foreignStockMarginPositionSummary.foreignPositionTotalProfitOrLossValuation < 0 ? 'font-color__minus bold' : 'font-color__plus bold'"
                  disable-transitions=""
                >{{ $_out($_signedWithCommaZeroPadding(formModel.foreignStockMarginPositionSummary.foreignPositionTotalProfitOrLossValuation, 2)) }}&nbsp;{{ formModel.currencyCode }}
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </el-row>
      <!-- /建玉情報 -->

      <el-form
        ref="form"
        :model="formModel"
        :rules="rules"
        label-width="180px"
        label-position="left"
      >
        <!-- 建玉指定：ラベル -->
        <el-row style="display:flex; align-items:flex-end; margin: 1.2rem 0 1rem;">
          <!-- ラベル -->
          <el-col
            style="vertical-align:bottom;"
            :span="20"
          >
            <div style="font-weight: bold">建玉指定</div>
          </el-col>
          <!-- /ラベル -->

          <!-- リセット・戻るボタン /-->
          <el-col
            :span="4"
            class="__right"
          >
            <ifa-button
              id="btnReset"
              text="リセット"
              color="secondary"
              action-type="originalAction"
              @app-action-handler="resetForm()"
            ></ifa-button>
          </el-col>
        </el-row>
        <!-- /建玉指定：ラベル -->

        <!-- 建玉指定 -->
        <el-card class="box-card">
          <!--  返済建玉指定方法 -->
          <el-row class="form-area__section">
            <el-col :span="20">
              <ifa-input-radio
                v-model="formModel.repayPositionDesignateMethod"
                label="返済建玉指定方法"
                prop="repayPositionDesignateMethod"
                :code-list-id="'FOREIGN_REPAY_METHOD'"
                :disp-pattern="1"
                :select-pattern="1"
                style="padding-left:-1px;"
                @change="changeRepayPositionDesignateMethod"
              ></ifa-input-radio>
            </el-col>
          </el-row>
          <!-- /返済建玉指定方法 -->

          <!-- 返済順序 -->
          <div class="form-area__section">
            <ifa-input-radio
              ref="repaySelectOrderRadio"
              v-model="formModel.repaySelectOrder"
              label="返済順序"
              prop="repaySelectOrder"
              :code-list-id="'FOREIGN_REPAY_ORDER'"
              :disp-pattern="1"
              :select-pattern="1"
              @change="changeRepaySelectOrderRadio"
            ></ifa-input-radio>
          </div>
          <!-- /返済順序 -->

          <!-- 返済株数：一括指定 -->
          <el-row
            v-if="formModel.repayPositionDesignateMethod === '1'"
            style="margin-top: 0.5rem;"
            class="form-area__section"
          >
            <div
              style="flex: 0 0 600px"
            >
              <ifa-input-quantity
                v-model="formModel.closeOrderQuantity"
                label="返済株数"
                prop="closeOrderQuantity"
                unit="株"
                :min="formModel.tradeLowerLimitQuantity"
                :max="getMaxQuantityMassRepay"
                :step="formModel.tradingUnit"
                :initial-step="formModel.tradingUnit"
                :support="true"
                class="form-area__input-number"
                style="width: 180px;"
                :domain="IfaStocks10DomainModel"
              ></ifa-input-quantity>
            </div>
            <div
              style="flex: 0 0 100px"
            >
              <ifa-button
                id="btnAll"
                text="全株数"
                color="secondary"
                tabindex="-1"
                action-type="originalAction"
                @app-action-handler="getAllStockNumberClick()"
              ></ifa-button>
            </div>
            <div
              style="flex: 1 0 300px; margin-left: 3rem; margin-top: 0.7rem;"
            >
              <span>（{{ $_out($_withCommaInteger(formModel.tradeLowerLimitQuantity)) }}株以上{{ $_out($_withCommaInteger(formModel.tradingUnit)) }}株単位）</span>
            </div>
          </el-row>
          <!-- 返済株数：個別指定 -->
          <el-row v-else-if="formModel.repayPositionDesignateMethod === '0'">

            <el-table
              :data="formModel.foreignStockMarginPositionDetailList"
              :cell-class-name="tableCellClassName"
              style="width: 100%; margin-top: 1rem;font-size:12px;"
            >
              <!-- 建日 -->
              <el-table-column
                width="100px"
                :min-width="100"
              >
                <template #header="">
                  <div>新規建日</div>
                  <div>(返済期限)</div>
                </template>
                <template #default="scope">
                  <div>{{ $_out($_getFormattedDateValue(scope.row.domesticTradeDate)) }}</div>
                  <div v-if="scope.row.foreignSetDate !== '9999-12-31'">{{ $_out($_getFormattedDateValue(scope.row.foreignSetDate)) }}</div>
                  <div v-else>----/--/--</div>
                </template>
              </el-table-column>
              <!-- /建日 -->
              <!-- 期限 -->
              <el-table-column
                label="期限"
                prop="tradeLimit"
                :resizable="false"
                header-align="center"
                align="center"
                width="100px"
                :min-width="100"
              >
                <template #default="scope">
                  <div>{{ $_out($_getCodeValue('MARGIN_DUE_DATE', 1, scope.row.marginDueDate)) }}</div>
                </template>
              </el-table-column>
              <!-- /期限 -->
              <!-- 担保 -->
              <el-table-column
                label="担保"
                width="100px"
                :min-width="100"
              >
                <template #default="scope">
                  <div>{{ $_out($_addComma(scope.row.marginRequirementRatio)) + '％' }}{{ scope.row.securityOpenPositionFlag == true ? "増担" : '' }}</div>
                </template>
              </el-table-column>
              <!-- /担保 -->
              <!-- 建単価 -->
              <el-table-column
                label="建単価"
                width="100px"
                :min-width="100"
              >
                <template #default="scope">
                  <div>{{ $_out($_withCommaZeroPadding(scope.row.sinyoPreviousDayValue, 2)) + $_out(formModel.currencyCode) }}</div>
                </template>
              </el-table-column>
              <!-- /建単価 -->
              <!-- 数量 -->
              <el-table-column
                width="100px"
                :min-width="100"
              >
                <template #header="">
                  <div>数量</div>
                  <div>(注文中)</div>
                </template>
                <template #default="scope">
                  <div>{{ $_out($_withCommaInteger(scope.row.quantity)) }}&nbsp;株</div>
                  <div>({{ $_out($_withCommaInteger(scope.row.quantityOrdering)) }}&nbsp;株)</div>
                </template>
              </el-table-column>
              <!-- /数量 -->
              <!-- 注文可能数量 -->
              <el-table-column
                label="注文可能数"
                width="100px"
                :min-width="100"
              >
                <template #default="scope">
                  <div>{{ $_out($_withCommaInteger(scope.row.perOpenInterestMaxOrderableQuantity)) }}株</div>
                </template>
              </el-table-column>
              <!-- /注文可能数量 -->
              <!-- 注文数量 -->
              <el-table-column width="450px">
                <template #header="">
                  <div><span class="required-mark">*</span>注文数量</div>
                  <div>
                    {{ $_out(formModel.tradeLowerLimitQuantity) }}株以上{{ $_out(formModel.tradingUnit) }}株単位
                  </div>
                </template>
                <template #default="scope">
                  <!-- エラーメッセージが表示されるため、行の高さを調整 -->
                  <el-row style="padding-bottom: 0.7em;">
                    <el-col :span="18">
                      <ifa-input-amount
                        v-model="scope.row.orderCount"
                        prop="orderCount"
                        :min="0"
                        :max="formModel.foreignStockMarginPositionDetailList[scope.$index].perOpenInterestMaxOrderableQuantity"
                        :step="formModel.tradingUnit"
                        :initial-step="formModel.tradingUnit"
                        :support="true"
                        :unit="'株'"
                        class="form-area__input-number"
                        style="width: 180px;"
                        :domain="IfaStocks10DomainModel"
                        @change="handleInputValue"
                      ></ifa-input-amount>
                    </el-col>
                    <el-col :span="5">
                      <ifa-button
                        id="btnAll"
                        text="全株数"
                        tabindex="-1"
                        color="secondary"
                        action-type="originalAction"
                        @app-action-handler="getAllStockValueClick(scope.$index)"
                      ></ifa-button>
                    </el-col>
                  </el-row>
                </template>
              </el-table-column>
              <!-- /注文数量 -->
              <!-- 評価損益 -->
              <el-table-column
                width="150px"
                :min-width="200"
              >
                <template #header="">
                  <div>評価損益</div>
                  <div>(諸経費等含む)</div>
                </template>
                <template #default="scope">
                  <div
                    :class="scope.row.profitAndLossContainOtherCost === '' ? 'black-normal' : scope.row.profitAndLossContainOtherCost < 0 ? 'font-color__minus bold' : 'font-color__plus bold'"
                    disable-transitions=""
                  >{{ $_out($_signedWithCommaZeroPadding(scope.row.profitAndLossContainOtherCost, 2)) }}&nbsp;{{ formModel.currencyCode }}</div>
                </template>
              </el-table-column>
              <!-- 評価損益 -->
            </el-table>
            <div
              style="margin-top: 0.5rem;width: 100%;"
            >
              <el-col
                style="text-align:center"
              >{{ '合計   ' + $_out($_withCommaInteger(formModel.closeOrderQuantity)) + ' 株' }}</el-col>
            </div>
          </el-row>
          <!-- /返済株数 -->
        </el-card>
        <!-- /建玉指定 -->

        <!-- 注文入力：ラベル /-->
        <el-row style="margin: 1.2rem 0 1rem;">
          <span style="font-weight: bold">注文入力</span>
        </el-row>
        <!-- /注文入力：ラベル /-->

        <!-- 注文入力 -->
        <el-card
          class="box-card"
          :style="{ 'background-color': bgColor, 'font-weight': 'bold' }"
        >
          <!-- 取引種別 -->
          <el-row class="form-area__section">
            <el-col :span="20">
              <el-form-item
                label="取引種別"
                prop="deal"
              >
                <span
                  class="__center"
                  :class="tradeCdColor()"
                >{{ $_out($_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, formModel.tradeCd)) }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <!-- /取引種別 -->

          <!-- 価格 -->
          <div class="form-area__section">
            <el-row style="padding-top: 10px; padding-bottom: 10px; width: 1190px;">
              <el-col :span="9">
                <!-- フォーム: 価格/執行方法 -->
                <ifa-input-select
                  ref="orderPriceKindListSelect"
                  v-model="formModel.orderPriceKindList"
                  label="価格"
                  prop="orderPriceKindList"
                  class="form-area__select"
                  :code-list-id="'SELECT_ABLE_PRICE_CONDITIONS'"
                  :disp-pattern="2"
                  :select-pattern="1"
                  style="width: 180px;"
                  @change="changeOrderPriceKindListSelect"
                ></ifa-input-select>
                <!-- /フォーム: 価格/執行方法 -->
              </el-col>
              <el-col
                v-if="formModel.orderPriceKindList == '1'"
                :span="7"
                style="margin-left: 10px;"
              >
                <!-- フォーム: 価格/執行方法/指値の条件 -->
                <ifa-input-price
                  v-model="formModel.limitPrice2"
                  prop="limitPrice2"
                  :required="formModel.orderPriceKindList === '1'"
                  :min="0.01"
                  :max="999999999.99"
                  :step="0.01"
                  :support="true"
                  style="width: 180px;"
                  :unit="formModel.currencyCode"
                  class="form-area__input-number"
                  :domain="IfaUnitPriceForeign122DomainModel"
                  @change="changeLimitPrice2"
                ></ifa-input-price>
                <!-- /フォーム: 価格/執行方法/指値の条件 -->
              </el-col>

              <!-- フォーム: 価格/執行方法/逆指値の条件 -->
              <el-col
                v-if="formModel.orderPriceKindList == '3' || formModel.orderPriceKindList == '4'"
                :span="2"
                style="margin-top:10px; margin-left: 10px; margin-right: -35px;"
              >
                <span style="font-weight: normal;">現在値が</span>
              </el-col>
              <el-col
                v-if="formModel.orderPriceKindList == '3' || formModel.orderPriceKindList == '4'"
                :span="7"
                style="margin-right: -29px;"
              >
                <ifa-input-price
                  v-model="formModel.stopOrderPrice"
                  prop="stopOrderPrice"
                  :required="formModel.orderPriceKindList == '3' || formModel.orderPriceKindList == '4'"
                  :min="0.01"
                  :max="999999999.99"
                  :step="0.01"
                  :support="true"
                  style="width: 180px;"
                  :unit="formModel.currencyCode"
                  class="form-area__input-number"
                  :domain="IfaUnitPriceForeign122DomainModel"
                  @change="changeStopOrderPrice"
                ></ifa-input-price>
              </el-col>
              <el-col
                v-if="formModel.orderPriceKindList == '3' || formModel.orderPriceKindList == '4'"
                :span="4"
                style="margin-top:10px; font-weight: normal;"
              >
                {{ formModel.tradeCd === '4' ? '以上' : '以下' }}になった時点で
              </el-col>
            </el-row>

            <el-row
              v-if="formModel.orderPriceKindList == '3' || formModel.orderPriceKindList == '4'"
              style="padding-bottom: 10px; width: 1190px;"
            >
              <el-col :span="9"></el-col>
              <!-- フォーム: /価格/執行方法/逆指値の執行方法  -->
              <el-col :span="4"
                      style="margin-left: 10px;"
              >
                <!-- フォーム: /価格/執行方法/逆指値の執行方法/成行  -->
                <ifa-input-select
                  ref="stopOrderSelect"
                  v-model="formModel.stopOrderPriceKindList"
                  class="form-area__select"
                  prop="orderPriceKindList"
                  style="width: 180px;"
                  :required="formModel.orderPriceKindList == '3' || formModel.orderPriceKindList == '4'"
                  :code-list-id="'SELECT_ABLE_PRICE_CONDITIONS'"
                  :disp-pattern="4"
                  :select-pattern="3"
                ></ifa-input-select>
                <!-- /フォーム: /価格/執行方法/逆指値の執行方法/成行  -->
              </el-col>
              <el-col
                v-if="formModel.orderPriceKindList == '3' && formModel.stopOrderPriceKindList == '3'"
                :span="7"
                style="margin-left: 3px; margin-right: -30px;"
              >
                <!-- フォーム: /価格/執行方法/逆指値の執行方法/指値  -->
                <ifa-input-price
                  v-if="formModel.orderPriceKindList == '3' && formModel.stopOrderPriceKindList === '3'"
                  v-model="formModel.stopOrderExecutePrice2"
                  prop="stopOrderExecutePrice2"
                  :required="formModel.orderPriceKindList == '3' && formModel.stopOrderPriceKindList === '3'"
                  :min="0.01"
                  :max="999999999.99"
                  :support="true"
                  :step="0.01"
                  class="form-area__input-number"
                  style="width: 180px;"
                  :domain="IfaUnitPriceForeign122DomainModel"
                  :unit="formModel.currencyCode"
                  @change="changeStopOrderExecutePrice2"
                ></ifa-input-price>
                <!-- /フォーム: /価格/執行方法/逆指値の執行方法/指値  -->
              </el-col>
              <span style="margin-left: 1px; margin-top:10px; font-weight: normal;">で執行</span>
              <!-- /フォーム: /価格/執行方法/逆指値の執行方法  -->
            </el-row>
          </div>
          <!-- /価格 -->

          <!-- 期間 -->
          <div
            class="form-area__section"
            style="padding-top: 10px; padding-bottom: 10px;  display: flex; align-items: center; width: 680px;"
          >
            <ifa-period-selector
              ref="periodSelector"
              v-model:period="formModel.period"
              v-model:period-type="formModel.periodRadio"
              label="期間"
              prop="periodRadio"
              :required="true"
              :picker-options="dateOptions"
              :button-options="buttonOptions"
              class="date-picker"
              tabindex="-1"
              @update:period="setPeriod"
            ></ifa-period-selector>
          </div>
          <!-- /期間 -->

          <!-- 預り区分 -->
          <div
            class="form-area__section"
            style="padding-top: 10px; padding-bottom: 10px;"
          >
            <el-form-item
              label="預り区分"
              prop="depositType"
              :tabindex="-1"
            >
              <span style="font-weight: normal;">{{ $_out($_getCodeValue('FOREIGN_DEPOSIT_TYPE', 2, formModel.depositType)) }}</span>
            </el-form-item>
          </div>
          <!-- 預り区分 -->

          <!-- 決済方法 -->
          <div
            class="form-area__section"
            style="padding-top: 10px; padding-bottom: 10px;"
          >
            <el-form-item
              label="決済方法"
              prop="settlementMethod"
              :tabindex="-1"
            >
              <span style="font-weight: normal;">{{ $_out($_getCodeValue('SETTLEMENT_TYPE', 1, formModel.kessaiHoho)) }}</span>
            </el-form-item>
          </div>
          <!-- /決済方法 -->

          <!-- 勧誘区分 -->
          <div
            class="form-area__section"
            style="padding-top: 10px; padding-bottom: 10px;"
          >
            <ifa-input-select
              v-model="formModel.kanyuKbn"
              label="勧誘区分"
              style="width: 180px;"
              :code-list-id="'FOREIGN_STOCK_INVITATION_TYPE'"
              prop="kanyuKbn"
            ></ifa-input-select>
          </div>
          <!-- /勧誘区分 -->

          <!-- 受注方法 -->
          <div
            class="form-area__section"
            style="padding-top: 10px; padding-bottom: 10px;"
          >
            <ifa-input-select
              v-model="formModel.receiveOrderType"
              label="受注方法"
              style="width: 180px;"
              :code-list-id="'FOREIGN_STOCK_ORDER_METHOD_TYPE'"
              prop="receiveOrderType"
            ></ifa-input-select>
          </div>
          <!-- /受注方法 -->

          <!-- 確認項目 -->
          <div class="form-area__section">
            <ifa-input-check
              v-model="formModel.checkInsider"
              label="確認項目"
              prop="checkInsider"
              :code-list-id="'INSIDER_CONFIRM'"
              :disp-pattern="2"
              :select-pattern="2"
            ></ifa-input-check>
          </div>
          <!-- /確認項目 -->
        </el-card>

        <!-- 注文確認 -->
        <div
          class="form-area__section"
          style="margin-top: 0.7rem; margin-left: -5px;"
        >
          <ifa-button
            id="btnOrderConfirm"
            text="注文確認"
            color="primary"
            action-type="requestAction"
            action-id="SUB0202_0303-04_1#A012"
            :disabled="formModel.foreignStockMarginPositionSummary.stockNumOrderable <= 0"
            :form="$refs['form']"
            :request-model="IfaForeignMarginTradeRepayOrderInputA012RequestModel"
            @response-handler="responseHandlerA012"
          ></ifa-button>
        </div>
        <!-- /注文確認 -->
      </el-form>
    </div>
    <!-- ダイアログ：信用建玉詳細 -->
    <ifa-foreign-position-detail
      :is-visible="dialogVisible"
      :form-data="foreignPositionDetailModel"
      @close-modal="handleCloseModal"
    ></ifa-foreign-position-detail>
    <!-- /ダイアログ：信用建玉詳細 -->

    <!-- ダイアログ：外国信用取引返済注文確認 -->
    <ifa-foreign-margin-trade-repay-order-confirm
      ref="ifaForeignMarginTradeRepayOrderConfirm"
      :is-visible="dialogConfirmVisible"
      :confirm-form-model="confirmFormModel"
      :customer-info="customerInfo"
      :stock-info="stockInfo"
      @close-modal="handleConfirmClose"
      @order-finish="handleOrderFinish"
    ></ifa-foreign-margin-trade-repay-order-confirm>
    <!-- /ダイアログ：外国信用取引返済注文確認 -->

    <!-- ダイアログ：外国信用取引返済注文完了 -->
    <ifa-foreign-margin-trade-repay-order-complete
      :is-visible="dialogFinishVisible"
      :form-model="completeFormModel"
      :customer-info="customerInfo"
      :stock-info="stockInfo"
      @close-modal="handleFinishClose"
      @move-to-order-list="handleMoveToOrderList"
    ></ifa-foreign-margin-trade-repay-order-complete>
    <!-- /ダイアログ：外国信用取引返済注文完了 -->
  </div>
</template>

<script>

import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import IfaInputRadio from '@/components/Input/IfaInputRadio'
import IfaForeignPositionDetail from '@/views/common/IfaForeignPositionDetail'
import IfaForeignMarginTradeRepayOrderConfirm from './IfaForeignMarginTradeRepayOrderConfirm'
import IfaForeignMarginTradeRepayOrderComplete from './IfaForeignMarginTradeRepayOrderComplete'
import IfaStocks10DomainModel from '@/resource/domain/IfaStocks10DomainModel.json'
import IfaUnitPriceForeign122DomainModel from '@/resource/domain/IfaUnitPriceForeign122DomainModel.json'
import IfaText12DomainModel from '@/resource/domain/IfaText12DomainModel.json'
import IfaText6DomainModel from '@/resource/domain/IfaText6DomainModel.json'
import IfaLinkText20DomainModel from '@/resource/domain/IfaLinkText20DomainModel.json'
import { getMessage } from '@/utils/errorHandler'
import { IfaForeignMarginTradeRepayOrderInputFormModel } from './js/IfaForeignMarginTradeRepayOrderInputFormModel'
import { IfaForeignMarginTradeRepayOrderInputA003RequestModel } from './js/IfaForeignMarginTradeRepayOrderInputA003RequestModel'
import { IfaForeignMarginTradeRepayOrderInputA005RequestModel } from './js/IfaForeignMarginTradeRepayOrderInputA005RequestModel'
import { IfaForeignMarginTradeRepayOrderInputA012RequestModel } from './js/IfaForeignMarginTradeRepayOrderInputA012RequestModel'
import { IfaForeignMarginTradeRepayOrderConfirmFormModel } from './js/IfaForeignMarginTradeRepayOrderConfirmFormModel'
import { IfaForeignMarginTradeRepayOrderCompleteFormModel } from './js/IfaForeignMarginTradeRepayOrderCompleteFormModel'
import { IfaLinkRequestModel } from './js/IfaLinkRequestModel'
import { notifyMessage } from '@/utils/errorHandler'

export default {
  components: {
    screenTitle,
    IfaInputRadio,
    IfaForeignPositionDetail,
    IfaForeignMarginTradeRepayOrderConfirm,
    IfaForeignMarginTradeRepayOrderComplete
  },
  props: {
    params: { type: Object, required: true }
  },
  data() {
    return {
      IfaStocks10DomainModel: IfaStocks10DomainModel,
      IfaUnitPriceForeign122DomainModel: IfaUnitPriceForeign122DomainModel,
      IfaText12DomainModel: IfaText12DomainModel,
      IfaText6DomainModel: IfaText6DomainModel,
      IfaLinkText20DomainModel: IfaLinkText20DomainModel,
      // フォーム
      formModel: new IfaForeignMarginTradeRepayOrderInputFormModel(),
      confirmFormModel: new IfaForeignMarginTradeRepayOrderConfirmFormModel(),
      completeFormModel: new IfaForeignMarginTradeRepayOrderCompleteFormModel(),
      a003RequestModel: {},
      foreignPositionDetailModel: {},
      // 入力チェック
      rules: {
        repayPositionDesignateMethod: [{ required: true, trigger: 'change', message: getMessage('errors.selected', ['返済建玉指定方法']) }],
        repaySelectOrder: [{ required: true, trigger: 'change', message: getMessage('errors.selected', ['返済順序']) }],
        closeOrderQuantity: [{ required: true, trigger: 'blur', message: getMessage('errors.required', ['数量']) }],
        orderPriceKindList: [{ required: true, trigger: 'change', message: getMessage('errors.required', ['価格']) }],
        limitPrice2: [{ required: true, trigger: 'change', message: getMessage('errors.required', ['注文単価（指値）']), validator: this.priceValidator }],
        stopOrderPrice: [{ required: true, trigger: 'change', message: getMessage('errors.required', ['参照価格（逆指値）']), validator: this.priceValidator }],
        stopOrderExecutePrice2: [{ required: true, trigger: 'change', message: getMessage('errors.required', ['注文単価（逆指値）']), validator: this.priceValidator }],
        kanyuKbn: [{ required: true, trigger: 'change', message: getMessage('errors.required', ['勧誘区分']) }],
        receiveOrderType: [{ required: true, trigger: 'change', message: getMessage('errors.required', ['受注方法']) }],
        checkInsider: [{ required: true, trigger: 'change', message: getMessage('errors.required', ['確認項目']) }]
      },
      // 外国株式建玉一覧：選択行のサマリ
      sumsInParams: {
        previousDayValue: 0.00,
        stockNum: 0,
        stockNumOrdering: 0,
        price: 0.00,
        expences: 0.00,
        previousDayValuation: 0.00
      },
      // 銘柄検索
      stockInfo: {},
      // ダイアログ：信用建玉詳細：表示制御
      dialogVisible: false,
      // ダイアログ：外国信用取引返済注文確認
      dialogConfirmVisible: false,
      // ダイアログ：外国信用取引返済注文完了
      dialogFinishVisible: false,
      buttonOptions: {
        today: '',
        period: ''
      },
      originalRepayOrderCodeList: [],
      originalOrderPriceKindCodeList: [],
      request: '',
      requestProps: {},
      ifaLinkRequestModel: {}
    }
  },
  computed: {
    bgColor() {
      if (this.formModel.tradeCd === '0' || this.formModel.tradeCd === '2' || this.formModel.tradeCd === '4' || this.formModel.tradeCd === '11') {
        return '#fef0f0'
      } else if (this.formModel.tradeCd === '1' || this.formModel.tradeCd === '3' || this.formModel.tradeCd === '5' || this.formModel.tradeCd === '12') {
        return '#ecf5ff'
      }
      return '#fdf6ec'
    },
    IfaForeignMarginTradeRepayOrderInputA003RequestModel() { return new IfaForeignMarginTradeRepayOrderInputA003RequestModel(this.a003RequestModel) },
    IfaForeignMarginTradeRepayOrderInputA005RequestModel() { return new IfaForeignMarginTradeRepayOrderInputA005RequestModel(this.formModel) },
    IfaForeignMarginTradeRepayOrderInputA012RequestModel() { return new IfaForeignMarginTradeRepayOrderInputA012RequestModel(this.formModel) },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    dateOptions() {
      const dateList = this.formModel.periodList
      return {
        disabledDate(date) {
          const targetDate = formatDate(date)
          function formatDate(date) {
            const year = date.getFullYear()
            const month = String(date.getMonth() + 1).padStart(2, '0')
            const day = String(date.getDate()).padStart(2, '0')
            return `${year}-${month}-${day}`
          }
          const result = dateList.some(value => {
            return targetDate === value
          })
          return !result
        }
      }
    },
    getMaxQuantityMassRepay() {
      if (this.formModel.tradeUpperLimitQuantity === '0') {
        return this.formModel.foreignStockMarginPositionSummary.stockNumOrderable
      } else {
        return Math.min(
          this.formModel.foreignStockMarginPositionSummary.stockNumOrderable,
          this.formModel.tradeUpperLimitQuantity
        )
      }
    },
    target() {
      return 'link' + (this.requestProps.urlId === 0 ? '' : this.requestProps.urlId.toString())
    }
  },
  methods: {
    onShow() {
      this.resetForm()
      this.formModel = Object.assign(this.formModel, this.$_getMenuParams())
      this.originalRepayOrderCodeList = this.$refs['repaySelectOrderRadio'].codeList
      this.originalOrderPriceKindCodeList = this.$refs['orderPriceKindListSelect'].options
      this.filtering()
    },
    filtering() {
      // 初期表示と更新ボタン押下時に動く
      // 更新ボタン押下は期間指定に影響なし
      // 期間条件のフィルタリング
      if (!this.formModel.selectAblePeriodTermsList.includes('0')) {
        this.buttonOptions.today = 'hide'
      } else {
        this.buttonOptions.today = ''
      }
      if (!this.formModel.selectAblePeriodTermsList.includes('1')) {
        this.buttonOptions.period = 'hide'
      } else {
        this.buttonOptions.period = ''
      }
      if (this.buttonOptions.period !== 'hide') {
        if (this.formModel.orderPriceKindList === '2') {
        // 成行の場合「当日中」のみ選択可能
          this.buttonOptions.period = 'disabled'
        } else {
          this.buttonOptions.period = ''
        }
      }
      this.$nextTick(() => {
        if (this.formModel.orderPriceKindList === '2' && this.$refs['periodSelector']) {
          this.formModel.periodRadio = false
          this.$refs['periodSelector'].periodTypeInput = this.formModel.periodRadio
          this.$refs['periodSelector'].$emit('update:periodType', this.formModel.periodRadio)
        } else {
          this.$refs['periodSelector'].resetComponent()
        }
      })
      // 返済順序項目のフィルタリング
      const filteredRepaySelectOrder = this.originalRepayOrderCodeList.filter(
        item => this.formModel.selectAbleRepaySelectSequenceList.indexOf(item.key) >= 0)
      this.$refs['repaySelectOrderRadio'].codeList = filteredRepaySelectOrder
      // 選択中の項目が選択肢に無い場合、選択肢の1つ目の項目に選びなおす
      if (filteredRepaySelectOrder.filter(item => item.key === this.formModel.repaySelectOrder).length === 0) {
        this.formModel.repaySelectOrder = filteredRepaySelectOrder[0] ? filteredRepaySelectOrder[0].key : ''
      }

      // 価格条件項目のフィルタリング
      const seletablePriceList = []
      if (this.formModel.selectAblePriceTermsList.length > 0) {
        this.formModel.selectAblePriceTermsList.forEach(item => {
          if (item.indexOf('1') >= 0) {
            seletablePriceList.push('1')
          } else if (item.indexOf('2') >= 0) {
            seletablePriceList.push('2')
          } else if ((item.indexOf('3') >= 0 || item.indexOf('4') >= 0) && seletablePriceList.indexOf('3') < 0) {
            seletablePriceList.push('3')
          }
        })
      }
      const filteredOrderPriceKind = this.originalOrderPriceKindCodeList.filter(
        item => seletablePriceList.indexOf(item.key) >= 0)
      this.$refs['orderPriceKindListSelect'].options = filteredOrderPriceKind
      // 選択中の項目が選択肢に無い場合、選択肢の1つ目の項目に選びなおす
      if (filteredOrderPriceKind.filter(item => item.key === this.formModel.orderPriceKindList).length === 0 && this.formModel.orderPriceKindList !== '') {
        this.formModel.orderPriceKindList = filteredOrderPriceKind[0] ? filteredOrderPriceKind[0].key : ''
      }
    },
    changeLimitPrice2(newVal) {
      this.$_logDebug(newVal)
      if (!newVal) {
        if (this.formModel.priceBasicInfo.currentPrice) {
          this.$nextTick(() => {
            this.formModel.limitPrice2 = this.formModel.priceBasicInfo.currentPrice
          })
        } else if (this.formModel.priceBasicInfo.last) {
          this.$nextTick(() => {
            this.formModel.limitPrice2 = this.formModel.priceBasicInfo.last
          })
        } else {
          this.$nextTick(() => {
            this.formModel.limitPrice2 = 0
          })
        }
      }
    },
    changeStopOrderPrice(newVal) {
      if (!newVal) {
        if (this.formModel.priceBasicInfo.currentPrice) {
          this.$nextTick(() => {
            this.formModel.stopOrderPrice = this.formModel.priceBasicInfo.currentPrice
          })
        } else if (this.formModel.priceBasicInfo.last) {
          this.$nextTick(() => {
            this.formModel.stopOrderPrice = this.formModel.priceBasicInfo.last
          })
        } else {
          this.$nextTick(() => {
            this.formModel.stopOrderPrice = 0
          })
        }
      }
    },
    changeStopOrderExecutePrice2(newVal) {
      if (!newVal) {
        if (this.formModel.priceBasicInfo.currentPrice) {
          this.$nextTick(() => {
            this.formModel.stopOrderExecutePrice2 = this.formModel.priceBasicInfo.currentPrice
          })
        } else if (this.formModel.priceBasicInfo.last) {
          this.$nextTick(() => {
            this.formModel.stopOrderExecutePrice2 = this.formModel.priceBasicInfo.last
          })
        } else {
          this.$nextTick(() => {
            this.formModel.stopOrderExecutePrice2 = 0
          })
        }
      }
    },
    changeRepaySelectOrderRadio(value) {
      if (value === '0') { // 返済順序＝評価益順の場合
        if (this.formModel.foreignStockMarginPositionSummary.tradeKbn === '3') { // 買建玉に対する返済売の場合
          // 建単価昇順＞現地新規約定日昇順
          this.formModel.foreignStockMarginPositionDetailList.sort((a, b) => {
            if (a.sinyoPreviousDayValue > b.sinyoPreviousDayValue) {
              return 1
            } else if (a.sinyoPreviousDayValue < b.sinyoPreviousDayValue) {
              return -1
            } else {
              if (a.foreignTradeDate > b.foreignTradeDate) {
                return 1
              } else {
                return -1
              }
            }
          })
        } else if (this.formModel.foreignStockMarginPositionSummary.tradeKbn === '1') { // 売建玉に対する返済買の場合
          // 建単価降順＞現地新規約定日昇順
          this.formModel.foreignStockMarginPositionDetailList.sort((a, b) => {
            if (a.sinyoPreviousDayValue > b.sinyoPreviousDayValue) {
              return -1
            } else if (a.sinyoPreviousDayValue < b.sinyoPreviousDayValue) {
              return 1
            } else {
              if (a.foreignTradeDate > b.foreignTradeDate) {
                return 1
              } else {
                return -1
              }
            }
          })
        }
      } else if (value === '1') { // 返済順序＝評価損順の場合
        if (this.formModel.foreignStockMarginPositionSummary.tradeKbn === '3') { // 買建玉に対する返済売の場合
          // 建単価降順＞現地新規約定日昇順
          this.formModel.foreignStockMarginPositionDetailList.sort((a, b) => {
            if (a.sinyoPreviousDayValue > b.sinyoPreviousDayValue) {
              return -1
            } else if (a.sinyoPreviousDayValue < b.sinyoPreviousDayValue) {
              return 1
            } else {
              if (a.foreignTradeDate > b.foreignTradeDate) {
                return 1
              } else {
                return -1
              }
            }
          })
        } else if (this.formModel.foreignStockMarginPositionSummary.tradeKbn === '1') { // 売建玉に対する返済買の場合
          // 建単価昇順＞現地新規約定日昇順
          this.formModel.foreignStockMarginPositionDetailList.sort((a, b) => {
            if (a.sinyoPreviousDayValue > b.sinyoPreviousDayValue) {
              return 1
            } else if (a.sinyoPreviousDayValue < b.sinyoPreviousDayValue) {
              return -1
            } else {
              if (a.foreignTradeDate > b.foreignTradeDate) {
                return 1
              } else {
                return -1
              }
            }
          })
        }
      } else if (value === '2') { // 返済順序＝建日古い順の場合
        if (this.formModel.foreignStockMarginPositionSummary.tradeKbn === '3') { // 買建玉に対する返済売の場合
          // 現地新規約定日昇順＞建単価昇順
          this.formModel.foreignStockMarginPositionDetailList.sort((a, b) => {
            if (a.foreignTradeDate > b.foreignTradeDate) {
              return 1
            } else if (a.foreignTradeDate < b.foreignTradeDate) {
              return -1
            } else {
              if (a.sinyoPreviousDayValue > b.sinyoPreviousDayValue) {
                return 1
              } else {
                return -1
              }
            }
          })
        } else if (this.formModel.foreignStockMarginPositionSummary.tradeKbn === '1') { // 売建玉に対する返済買の場合
          // 現地新規約定日昇順＞建単価降順
          this.formModel.foreignStockMarginPositionDetailList.sort((a, b) => {
            if (a.foreignTradeDate > b.foreignTradeDate) {
              return 1
            } else if (a.foreignTradeDate < b.foreignTradeDate) {
              return -1
            } else {
              if (a.sinyoPreviousDayValue > b.sinyoPreviousDayValue) {
                return -1
              } else {
                return 1
              }
            }
          })
        }
      } else if (value === '3') { // 返済順序＝建日新しい順
        if (this.formModel.foreignStockMarginPositionSummary.tradeKbn === '3') { // 買建玉に対する返済売の場合
          // 現地新規約定日降順＞建単価昇順
          this.formModel.foreignStockMarginPositionDetailList.sort((a, b) => {
            if (a.foreignTradeDate > b.foreignTradeDate) {
              return -1
            } else if (a.foreignTradeDate < b.foreignTradeDate) {
              return 1
            } else {
              if (a.sinyoPreviousDayValue > b.sinyoPreviousDayValue) {
                return 1
              } else {
                return -1
              }
            }
          })
        } else if (this.formModel.foreignStockMarginPositionSummary.tradeKbn === '1') { // 売建玉に対する返済買の場合
          // 現地新規約定日降順＞建単価降順
          this.formModel.foreignStockMarginPositionDetailList.sort((a, b) => {
            if (a.foreignTradeDate > b.foreignTradeDate) {
              return -1
            } else if (a.foreignTradeDate < b.foreignTradeDate) {
              return 1
            } else {
              if (a.sinyoPreviousDayValue > b.sinyoPreviousDayValue) {
                return -1
              } else {
                return 1
              }
            }
          })
        }
      }
    },
    changeOrderPriceKindListSelect(value) {
      if (this.buttonOptions.period !== 'hide') {
        if (value === '2') {
        // 成行の場合「当日中」のみ選択可能
          this.buttonOptions.period = 'disabled'
        } else {
          this.buttonOptions.period = ''
          this.$refs['periodSelector'].resetComponent()
        }
      }
      this.$nextTick(() => {
        if (value === '2' && this.$refs['periodSelector']) {
          this.formModel.periodRadio = false
          this.$refs['periodSelector'].periodTypeInput = this.formModel.periodRadio
          this.$refs['periodSelector'].$emit('update:periodType', this.formModel.periodRadio)
        } else {
          this.$refs['periodSelector'].resetComponent()
        }
      })
      this.$nextTick(() => {
        if (value === '3' || value === '4') {
        // 価格条件（逆指値）項目のフィルタリング
          if (this.$refs['stopOrderSelect']) {
            const filteredStopOrder = this.$refs['stopOrderSelect'].options.filter(
              item => this.formModel.selectAblePriceTermsList.indexOf(item.key) >= 0)
            this.$refs['stopOrderSelect'].options = filteredStopOrder
            if (filteredStopOrder && filteredStopOrder[0]) {
              this.formModel.stopOrderPriceKindList = filteredStopOrder[0].key
            }
          }
        }
      })
    },
    changeRepayPositionDesignateMethod(value) {
      if (value === '0') {
        this.formModel.closeOrderQuantity = 0
        this.handleInputValue()
      }
      this.changeRepayQuantity()
    },
    changeRepayQuantity() {
      this.formModel.closeOrderQuantity = ''
      if (this.formModel.foreignStockMarginPositionDetailList.length > 0) {
        this.formModel.foreignStockMarginPositionDetailList.forEach(item => {
          item.orderCount = ''
        })
      }
    },
    setStockInfo(data) {
      this.stockInfo = data
    },
    tradeCdColor() {
      if (this.formModel.tradeCd === '0' || this.formModel.tradeCd === '2' || this.formModel.tradeCd === '4' || this.formModel.tradeCd === '11') {
        return 'font-color__plus bold'
      } else if (this.formModel.tradeCd === '1' || this.formModel.tradeCd === '3' || this.formModel.tradeCd === '5' || this.formModel.tradeCd === '12') {
        return 'font-color__minus bold'
      } else {
        return
      }
    },
    tickColor() {
      if (!this.formModel.priceBasicInfo.currentPrice) {
        return '__black'
      }
      switch (this.formModel.priceBasicInfo.tickArrow) {
        case '1':
          return 'font-color__plus __bold'
        case '2':
          return 'font-color__minus __bold'
        default:
          return '__black __bold'
      }
    },
    ratioColor() {
      const n = Number(this.formModel.priceBasicInfo.diff)
      return n > 0 ? 'font-color__plus' : n < 0 ? 'font-color__minus __bold' : '__black'
    },
    tickLabel() {
      if (!this.formModel.priceBasicInfo.currentPrice) {
        // 現在値＝｢" "、-、0｣の場合、表示しない
        return ' '
      }
      switch (this.formModel.priceBasicInfo.tickArrow) {
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
    a003Action() {
      this.a003RequestModel = {
        butenCode: this.$store.getters.customerInfo.butenCode,
        accountNumber: this.$store.getters.customerInfo.accountNumber,
        countryCode: this.formModel.countryCode,
        brandCode: this.formModel.brandCode,
        marginDueDate: this.formModel.foreignStockMarginPositionSummary.marginDueDate,
        depositType: this.formModel.depositType,
        trade: this.formModel.trade
      }
      this.$nextTick(() => {
        document.getElementById('ifaForeignMarginTradeRepayOrderInputA003').click()
      })
    },
    responseHandlerA003(response) {
      this.foreignPositionDetailModel = Object.assign(this.foreignPositionDetailModel, response.dataList[0])
      this.dialogVisible = true
    },
    responseHandlerA005(response) {
      this.formModel = Object.assign(this.formModel, response.dataList[0])
      this.filtering()
    },
    async responseHandlerA012(response) {
      this.confirmFormModel = Object.assign(this.confirmFormModel, response.dataList[0])
      await this.$nextTick() // 現在の更新を待つためのVueのメソッド
      await this.$refs.ifaForeignMarginTradeRepayOrderConfirm.onShow()
      this.dialogConfirmVisible = true
    },
    // リセットボタン処理
    resetForm() {
      // 返済建玉指定方法
      this.formModel.repayPositionDesignateMethod = '1'
      // 返済順序
      this.formModel.repaySelectOrder = '0'
      // 返済株数
      this.formModel.closeOrderQuantity = ''
      this.formModel.foreignStockMarginPositionDetailList.forEach(item => {
        item.orderCount = ''
      })
      // 価格
      this.formModel.orderPriceKindList = ''
      this.formModel.limitPrice2 = ''
      this.formModel.stopOrderPrice = ''
      this.formModel.stopOrderExecutePrice2 = ''
      // 期間
      this.formModel.period = ''
      this.$refs['periodSelector'].resetComponent()
      this.$nextTick(() => {
        if (this.buttonOptions.period !== 'hide') {
          this.buttonOptions.period = ''
        }
      })
      // 勧誘区分
      this.formModel.kanyuKbn = ''
      // 受注方法
      this.formModel.receiveOrderType = ''
      // 確認項目
      this.formModel.checkInsider = []
      this.$nextTick(function() {
        this.$refs['form'].clearValidate()
      })
    },
    // 注文完了画面に遷移
    handleOrderFinish(data) {
      this.completeFormModel = Object.assign(this.completeFormModel, data)
      this.dialogConfirmVisible = false
      this.dialogFinishVisible = true
    },
    // 再計算
    getAllStockNumberClick() {
      if (this.formModel.foreignStockMarginPositionSummary.stockNumOrderable > this.formModel.tradeUpperLimitQuantity) {
        if (this.formModel.tradeUpperLimitQuantity === '0' || this.formModel.tradeUpperLimitQuantity === 0) {
          this.formModel.closeOrderQuantity = this.formModel.foreignStockMarginPositionSummary.stockNumOrderable
        } else {
          this.formModel.closeOrderQuantity = this.formModel.tradeUpperLimitQuantity
        }
      } else {
        if (this.formModel.foreignStockMarginPositionSummary.stockNumOrderable === '0' || this.formModel.foreignStockMarginPositionSummary.stockNumOrderable === 0) {
          this.formModel.closeOrderQuantity = this.formModel.tradeUpperLimitQuantity
        } else {
          this.formModel.closeOrderQuantity = this.formModel.foreignStockMarginPositionSummary.stockNumOrderable
        }
      }
      this.$nextTick(() => {
        this.$refs['form'].validateField('closeOrderQuantity')
          .catch(() => { /* Do nothing */ })
      })
    },
    // el-table用
    tableCellClassName({ row, column, rowIndex, columnIndex }) {
      const rowStyle = ''
      // 建日
      if (columnIndex === 0) {
        return rowStyle + '__center'
      // 期限
      } else if (columnIndex === 1) {
        return rowStyle + '__center'
      // 担保
      } else if (columnIndex === 2) {
        return rowStyle + '__center'
      // 建単価
      } else if (columnIndex === 3) {
        return rowStyle + '__right'
      // 数量
      } else if (columnIndex === 4) {
        return rowStyle + '__right'
      // 注文可能数量
      } else if (columnIndex === 5) {
        return rowStyle + '__right'
      // 注文数量
      } else if (columnIndex === 6) {
        return rowStyle + '__left'
      // 評価損益
      } else if (columnIndex === 7) {
        return rowStyle + '__right'
      }
    },
    // ダイアログ：信用建玉詳細：戻るボタン
    handleCloseModal() {
      this.dialogVisible = false
      this.dialogBrandDetailVisible = false
    },
    handleConfirmClose() {
      this.dialogConfirmVisible = false
    },
    handleFinishClose() {
      this.dialogFinishVisible = false
    },
    // 注文一覧画面に遷移
    handleMoveToOrderList() {
      this.resetForm()
      this.dialogFinishVisible = false
      // 注文一覧画面に遷移
      this.$_startShowMenu('SUB0202_0104')
    },
    handleInputValue() {
      this.formModel.closeOrderQuantity = 0
      this.formModel.foreignStockMarginPositionDetailList.forEach((row) => {
        if (row.orderCount !== '') {
          this.formModel.closeOrderQuantity += parseInt(row.orderCount)
        }
      })
    },
    getAllStockValueClick(index) {
      this.$nextTick(function() {
        this.formModel.foreignStockMarginPositionDetailList[index].orderCount = this.formModel.foreignStockMarginPositionDetailList[index].perOpenInterestMaxOrderableQuantity
        this.handleInputValue()

        this.$nextTick(() => {
          this.$refs['form'].validateField('orderCount')
            .catch(() => { /* Do nothing */ })
        })
      })
    },
    setPeriod(value) {
      this.formModel.period = value
    },
    priceValidator(rule, value, callback) {
      if (value === 0 || value === '0') {
        callback('')
      }
      callback()
    },
    ifaForeignMarginTradeRepayOrderInputGetNewMainSiteA021(urlId, patternId, httpMethod, brandCode) {
      const newData = {
        urlId: urlId,
        patternId: patternId,
        httpMethod: httpMethod,
        brandCode: brandCode
      }
      this.requestProps = newData
      this.ifaLinkRequestModel = new IfaLinkRequestModel(this.requestProps)
      document.getElementById('ifaForeignMarginTradeRepayOrderInputGetNewMainSiteA021').click()
    },
    responseHandlerGetNewMainSiteA021(response) {
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
@import "@/styles/table.scss";
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
:deep(.el-form-item__label) {
  font-weight: bold;
  line-height: 2;
  justify-content: flex-start;
  margin-right: 0.5rem;
  margin-left: 5rem;
  padding-bottom: 0.5px;
  width: 180px;
}
:deep(.el-el-input__wrapper) {
  width: 180px;
}
.__right {
  text-align: right;
}

.form-area__input-number {
  margin-left: 0.1rem;
}
.form-area__select {
  width: 9rem;
}
.form-area__select-auto {
  width: calc(100% - 1rem);
}
.form-area__section {
  height: auto;
  margin: 0.2rem 0;
  padding-bottom: 0.2rem;
  border-bottom: 1px solid #eee;
}
.form-radio {
  width: 6rem;
}

.info-item {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
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

.input-table {
  width:95%;
  margin: 10px;
  border-collapse: collapse;
  color: rgb(72,116,173);
  font: 11px/1.3 sans-serif;
  text-shadow:0 1px 0 #fff;
  border:1px solid #d8e8fa
}

/**/
.dummy-content {
  font-size: 2.0rem;
  text-align: center;
  background-color: #eee;
}
:deep(.el-table) .__center {
  text-align: center;
}
:deep(.el-table) .__left {
  text-align: left;
}
:deep(.el-table) .__right {
  text-align: right;
}
:deep(.el-table) .__blue {
  color: blue;
}
:deep(.el-table) .__colored {
  background-color: #fafafa
}
:deep(.el-table) td .__border_bottom_none {
  border-bottom: none;
}
:deep(.el-table) td .__border_normal {
  border-collapse: collapse;
  border: 3px solid #eee;
}
:deep(.el-table) th {
   text-align: center;
   font-size: 12px;
  border: 1px solid #eee;
}
:deep(.el-table) td {
  padding: 6px 0 6px 0;
  border-collapse: collapse;
  border: 1px solid #eee;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
.required-mark {
  color: #F56C6C;
  margin-right: 4px;
}

.market-label {
  color: #f00;
  font-weight: bold;
  padding-left: 0.1rem;
  line-height: 25px;
}
.__right {
  text-align: right;
}
.info_xs {
  display: grid;
  width: 100%;
  grid-template-columns: 4rem 1fr;
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
  font-size: 11px;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
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
.__red {
  color: red;
}
.__blue {
  color: blue;
}
.__black {
  color: #18181A;
}
.black-normal{
  color: black;
  font-weight: normal;
}
.update-button {
  text-align: right;
  position: absolute;
  right: 0;
  top: 0;
}
.not-label-div {
  margin-left: -200px;
}
.data-provider {
  resize: none;
  border: none;
  color: #bfcbd9;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
}
:deep(.el-radio) {
  margin-left: -6px;
}
.date-picker :deep(.el-input) {
  width: 180px;
  margin-left: 12px;
}
:deep(.date-picker) .el-form-item__content{
  display: flex !important;
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
