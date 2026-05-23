<template>
  <!-- 画面名：国内株式/信用取引 -->
  <div>
    <!-- 初期処理 -->
    <ifa-requester
      id="IfaMarginNewOrderInputA001"
      action-id="SUB0202_0212-01_1#A001"
      action-type="requestAction"
      @response-handler="responseHandlerInitializeA001"
      @response-error-handler="responseErrorHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaMarginNewOrderInputA002"
      action-id="SUB0202_0212-01_1#A002"
      action-type="requestAction"
      :request-model="IfaMarginNewOrderInputA002RequestModel"
      @response-handler="responseHandlerA002"
    ></ifa-requester>
    <screen-title :text="formModel.title"></screen-title>
    <div class="caption_card">
      <el-card style="background-color: #eee; margin-bottom: 0.5rem;">
        <el-row
          justify="space-between"
          style="display: flex;; padding: 0 1rem"
        >
          <!-- 銘柄検索 -->
          <el-col :span="19">
            <ifa-brand-search
              ref="ifaBrandSearch"
              :trading-type="'1'"
              :market-list="customMarketList"
              @change="handleChangeBrand"
            ></ifa-brand-search>
          </el-col>

          <el-col
            v-if="formModel.brandCode !== ''"
            :span="4"
            class="update-button"
          >
            <ifa-button
              text="詳細"
              icon="Document"
              small
              action-id="SUB0202_0208-02#A001"
              action-type="requestAction"
              :request-model="IfaStockDetailInfoA001RequestModel"
              @response-handler="responseHandlerA004"
            ></ifa-button>
            <ifa-button
              text="更新"
              icon="RefreshRight"
              small
              action-id="SUB0202_0212-01_1#A005"
              action-type="requestAction"
              :request-model="IfaMarginNewOrderInputA005RequestModel"
              @response-handler="responseHandlerA005"
            ></ifa-button>
          </el-col>
        </el-row>
        <!-- 時価情報 -->
        <ifa-brand-price-info
          ref="ifaBrandPriceInfo"
          :brand-code="formModel.brandCode"
          :market="marketRequest"
          @change="handleChangeBrandPrice"
        ></ifa-brand-price-info>

        <el-row
          :gutter="20"
          style="padding-top: 0.5rem;"
        >
          <el-col :span="4">
            <div class="info_xs">
              <span class="info-item__header __left"></span>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="info_xs">
              <div class="info-item__header __right">{{ $_out($_getFormattedDateValue(formModel.settlementDateT0)) }}(当日)</div>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="info_xs">
              <div class="info-item__header __right">{{ $_out($_getFormattedDateValue(formModel.settlementDateT1)) }}(翌営業日)</div>
            </div>
          </el-col>
        </el-row>
        <el-row
          :gutter="20"
          style="padding-top: 0.5rem;"
        >
          <el-col
            :span="4"
            :offset="1"
          >
            <div class="info_xs">
              <span class="info-item__header __left">新規建余力</span>
            </div>
          </el-col>
          <el-col
            :span="4"
            :pull="1"
          >
            <div class="info_xs">
              <div class="info-item__value __right">{{ newBuildingCapacityT0 }}円</div>
            </div>
          </el-col>
          <el-col
            :span="4"
            :pull="1"
          >
            <div class="info_xs">
              <div class="info-item__value __right">{{ newBuildingCapacityT1 }}円</div>
            </div>
          </el-col>
        </el-row>
        <el-row
          :gutter="20"
          style="padding-top: 0.5rem;"
        >
          <el-col
            :span="4"
            :offset="1"
          >
            <div class="info_xs">
              <span class="info-item__header __left">
                維持率
                <ifa-help-icon
                  v-if="formModel.maintenanceRateJpyAmountDescriptionMessage"
                  :message="formModel.maintenanceRateJpyAmountDescriptionMessage"
                >
                </ifa-help-icon>
              </span>
            </div>
          </el-col>
          <el-col
            :span="4"
            :pull="1"
          >
            <div class="info_xs">
              <div class="info-item__value __right">{{ $_out($_withCommaZeroPadding(formatToDecimal(formModel.rateT0), 2)) }}%</div>
            </div>
          </el-col>
          <el-col
            :span="4"
            :pull="1"
          >
            <div class="info_xs">
              <div class="info-item__value __right">{{ $_out($_withCommaZeroPadding(formatToDecimal(formModel.rateT1), 2)) }}%</div>
            </div>
          </el-col>
        </el-row>
      </el-card>

      <el-row style="padding-top: 0.5rem;">
        <!-- 通常注文タブ -->
        <el-card
          class="box-card"
          :style="{'background-color': bgColor}"
        >
          <el-form
            ref="formModel"
            :model="formModel"
            :rules="rules"
            label-width="220px"
            label-position="left"
            :style="{'background-color': bgColor}"
          >
            <el-row
              style="padding-top: 0.5rem;"
              :gutter="20"
            >

              <div v-if="formModel.orderKind=='1' || formModel.orderKind=='2'">
                <div class="form-area__section">
                  <el-row
                    style="padding-top: 0.5rem;"
                    class="el-col-offset-1"
                  >
                    <el-col class="label-left"
                            :span="16"
                    >
                      <!-- フォーム: 取引種別 -->
                      <ifa-input-radio
                        v-model="formModel.tradeCd"
                        label="取引種別"
                        prop="tradeCd"
                        :disabled="editDisable"
                        :code-list-id="'DOMESTIC_STOCK_TRADE_CLASS'"
                        :disp-pattern="1"
                        :select-pattern="3"
                        class="label"
                        @change="changeTradeType(formModel.tradeCd)"
                      ></ifa-input-radio>
                    </el-col>

                    <el-col
                      class="label-left"
                      :span="7"
                    >
                      <!-- リセットボタン -->
                      <span class="form-reset-button__wrapper">
                        <ifa-button
                          :disabled="editDisable"
                          text="リセット"
                          color="secondary"
                          tabindex="-1"
                          action-type="originalAction"
                          @app-action-handler="resetAll()"
                        ></ifa-button>
                      </span>
                    </el-col>
                  </el-row>
                </div>

                <el-row class="el-col-offset-1"
                        style="padding-top: 0.5rem;"
                >
                  <div
                    class="label-left"
                    style="flex: 0 0 550px"
                  >
                    <!-- フォーム: 数量 -->
                    <ifa-input-amount
                      v-model="formModel.orderQuantity"
                      prop="orderQuantity"
                      label="数量"
                      :min="formModel.unit"
                      :disabled="editDisable"
                      :step="formModel.unit"
                      :initial-step="formModel.unit"
                      :support="true"
                      :domain="IfaStocks8DomainModel"
                      unit="株"
                      step-strictly
                    >
                    </ifa-input-amount>
                  </div>
                  <div
                    v-if="formModel.tradeCd"
                    class="el-form-item"
                    style="flex: 1 0 300px"
                  >
                    {{ "売買単位：" + formModel.unit + "株" }}
                  </div>
                </el-row>
                <!-- フォーム: 価格/執行方法(注文種別が 通常/逆指値 の時表示) -->
                <div v-if="formModel.orderKind=='1'">
                  <el-row style="padding-top: 0.5rem;"
                          class="el-col-offset-1"
                  >
                    <el-col :span="6"
                            class="label-left"
                    >
                      <!-- フォーム: 執行方法選択 当社優先市場／SORの場合 @価格/執行方法 -->
                      <ifa-input-select
                        v-if="formModel.market == 'A'"
                        v-model="formModel.sasinariHouhou"
                        prop="sasinariHouhou"
                        label="価格/執行方法"
                        :code-list-id="'EXECUTE_METHOD'"
                        :disp-pattern="1"
                        :select-pattern="2"
                        style="min-width: 100px"
                        :disabled="editDisable"
                        @change="changeExcuteMathed"
                      ></ifa-input-select>
                      <!-- フォーム: 執行方法選択 PTSの場合 @価格/執行方法 -->
                      <ifa-input-select
                        v-else-if="formModel.market == '7'"
                        v-model="formModel.sasinariHouhou"
                        prop="sasinariHouhou"
                        label="価格/執行方法"
                        :code-list-id="'EXECUTE_METHOD'"
                        :disp-pattern="1"
                        :select-pattern="3"
                        style="min-width: 100px"
                        :disabled="editDisable"
                        @change="changeExcuteMathed"
                      ></ifa-input-select>
                      <!-- フォーム: 執行方法選択 そのほかの場合 @価格/執行方法 -->
                      <ifa-input-select
                        v-else
                        v-model="formModel.sasinariHouhou"
                        prop="sasinariHouhou"
                        label="価格/執行方法"
                        :code-list-id="'EXECUTE_METHOD'"
                        :disp-pattern="1"
                        :select-pattern="1"
                        style="min-width: 100px"
                        :disabled="editDisable"
                        @change="changeExcuteMathed"
                      ></ifa-input-select>
                    </el-col>
                    <el-col
                      v-if="formModel.sasinariHouhou == '1'"
                      :span="3"
                      style="margin-left: 2rem;"
                    >
                      <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                      <ifa-input-select
                        v-if="formModel.market == '7' || formModel.market == 'A'"
                        v-model="formModel.sasinariJyouken"
                        :code-list-id="'LIMIT_MARKET_TYPE'"
                        :disp-pattern="2"
                        :select-pattern="7"
                        :disabled="editDisable"
                      ></ifa-input-select>
                      <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                      <ifa-input-select
                        v-else
                        v-model="formModel.sasinariJyouken"
                        :code-list-id="'LIMIT_MARKET_TYPE'"
                        :disp-pattern="2"
                        :select-pattern="2"
                        :disabled="editDisable"
                      ></ifa-input-select>
                    </el-col>
                    <el-col
                      v-if="formModel.sasinariHouhou == '1'"
                      :span="5"
                      style="margin-left: 1rem;"
                    >
                      <ifa-input-price
                        v-model="formModel.price"
                        prop="price"
                        :disabled="editDisable"
                        :min="1"
                        :max="maxValueValidator(formModel.price)"
                        :digit="1"
                        :support="true"
                        :domain="IfaUnitPriceYen101DigitsCDomainModel"
                        :initial-step="initialStep"
                        :step-table="priceInfo.orderPriceUnit"
                        unit="円"
                      ></ifa-input-price>
                    </el-col>

                    <!-- フォーム: 成行選択時の条件設定 @価格/執行方法 -->
                    <el-col
                      v-if="formModel.sasinariHouhou == '2'"
                      :span="3"
                      style="padding-left: 2rem;"
                    >
                      <ifa-input-select
                        v-if="formModel.market == 'A'"
                        v-model="formModel.sasinariJyouken"
                        :code-list-id="'LIMIT_MARKET_TYPE'"
                        :disp-pattern="3"
                        :select-pattern="8"
                        :disabled="editDisable"
                      ></ifa-input-select>
                      <ifa-input-select
                        v-else-if="formModel.market !== 'A'"
                        v-model="formModel.sasinariJyouken"
                        :code-list-id="'LIMIT_MARKET_TYPE'"
                        :disp-pattern="3"
                        :select-pattern="3"
                        :disabled="editDisable"
                      ></ifa-input-select>
                    </el-col>
                    <el-col
                      v-if="formModel.orderKind != '2' && formModel.market == 'A'"
                      :span="6"
                      style="color: red; margin-top: 15px; margin-left: 1rem"
                    >
                      <span>(PTS発注時は優先市場最良気配指値)</span>
                    </el-col>

                    <!-- フォーム: 逆指値選択時の条件設定 @価格/執行方法(逆指値) -->
                    <el-col
                      v-if="formModel.sasinariHouhou == '3'"
                      :span="2"
                      style="padding: 15px 10px; margin-left: 1rem;"
                    >
                      <span>現在値が</span>
                    </el-col>
                    <el-col
                      v-if="formModel.sasinariHouhou == '3'"
                      :span="5"
                      style="margin-left: 1rem;"
                    >
                      <ifa-input-price
                        v-model="formModel.triggerPrice"
                        prop="triggerPrice"
                        :disabled="editDisable"
                        :min="1"
                        :max="maxValueValidator(formModel.triggerPrice)"
                        :digit="1"
                        :domain="IfaUnitPriceYen101DigitsCDomainModel"
                        :initial-step="initialStep"
                        :step-table="priceInfo.orderPriceUnit"
                        unit="円"
                        support
                      ></ifa-input-price>
                    </el-col>
                    <el-col
                      v-if="formModel.sasinariHouhou == '3'"
                      :span="6"
                      style="padding: 15px;"
                    >
                      {{ formModel.tradeCd == '3' ? '以上' : '以下' }} になった時点で
                    </el-col>
                  </el-row>
                  <!-- フォーム: 執行方法選択 @価格/執行方法(逆指値) -->
                  <el-row
                    v-if="formModel.sasinariHouhou == '3'"
                    style="padding-top: 0.5rem;"
                    class="el-col-offset-1"
                  >
                    <el-col :span="6"></el-col>
                    <el-col
                      :span="3"
                    >
                      <ifa-input-select
                        v-model="formModel.gyakusasiHouhou"
                        :code-list-id="'EXECUTE_METHOD'"
                        :disp-pattern="1"
                        :select-pattern="2"
                        :disabled="editDisable"
                        @change="changeExcuteMathed"
                      ></ifa-input-select>
                    </el-col>
                    <el-col
                      :span="3"
                      style="margin-left: 1rem;"
                    >
                      <ifa-input-select
                        v-if="formModel.gyakusasiHouhou == '2'"
                        v-model="formModel.sasinariJyouken"
                        :code-list-id="'LIMIT_MARKET_TYPE'"
                        :disp-pattern="3"
                        :select-pattern="5"
                      ></ifa-input-select>
                      <ifa-input-select
                        v-if="formModel.gyakusasiHouhou == '1'"
                        v-model="formModel.sasinariJyouken"
                        :code-list-id="'LIMIT_MARKET_TYPE'"
                        :disp-pattern="2"
                        :select-pattern="4"
                      ></ifa-input-select>
                    </el-col>
                    <el-col
                      v-if="formModel.gyakusasiHouhou == '1'"
                      :span="5"
                      style="margin-left: 1rem;"
                    >
                      <ifa-input-price
                        v-model="formModel.price"
                        prop="price"
                        :min="1"
                        :max="maxValueValidator(formModel.price)"
                        :digit="1"
                        :support="true"
                        :domain="IfaUnitPriceYen101DigitsCDomainModel"
                        :initial-step="initialStep"
                        :step-table="priceInfo.orderPriceUnit"
                        unit="円"
                      ></ifa-input-price>
                    </el-col>
                    <el-col
                      :span="2"
                      style="padding: 15px;"
                    >
                      <span>で執行</span>
                    </el-col>
                  </el-row>
                  <el-row
                    v-if="priceRow == '1.0' && priceHigh == '9999999999999.9'"
                    class="el-col-offset-1"
                  >
                    <el-col :span="(formModel.sasinariHouhou === '1' || formModel.sasinariHouhou === '2') ? 9 : 12"></el-col>
                    <el-col
                      :span="6"
                      :style="formModel.sasinariHouhou === '1' ? 'padding: 0.8rem 0px 0.8px 2.5rem;' : formModel.sasinariHouhou === '2' ? 'padding: 0.8rem 0px 0.8px 0.5rem;' : 'padding: 0.8rem 0px 0.8px 2rem;'"
                    >
                      <span>制限値幅：なし</span>
                    </el-col>
                  </el-row>
                  <el-row
                    v-else-if="priceRow != '1.0' || priceHigh != '9999999999999.9'"
                    class="el-col-offset-1"
                  >
                    <el-col :span="(formModel.sasinariHouhou === '1' || formModel.sasinariHouhou === '2') ? 9 : 12"></el-col>
                    <el-col
                      :span="6"
                      :style="formModel.sasinariHouhou === '1' ? 'padding: 0.8rem 0px 0.8px 2.5rem;' : formModel.sasinariHouhou === '2' ? 'padding: 0.8rem 0px 0.8px 0.5rem;' : 'padding: 0.8rem 0px 0.8px 2rem;'"
                    >
                      <span>制限値幅：{{ $_out($_withCommaZeroPadding(priceRow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円 ({{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }})</span>
                    </el-col>
                  </el-row>

                  <!-- フォーム: 注文種別 -->
                  <el-row
                    v-if="formModel.market == '7'"
                    style="padding-top: 0.5rem;"
                    class="el-col-offset-1"
                  >
                    <el-col class="label-left">
                      <ifa-input-select
                        v-model="formModel.orderKind"
                        label="注文種別"
                        prop="orderKind"
                        :required="true"
                        :code-list-id="'ORDER_CLASS'"
                        :disp-pattern="1"
                        :select-pattern="3"
                        :disabled="editDisable"
                        :clearable="false"
                        @change="changeOrderFormat"
                      ></ifa-input-select>
                    </el-col>
                  </el-row>
                  <el-row
                    v-else
                    style="padding-top: 0.5rem;"
                    class="el-col-offset-1"
                  >
                    <el-col class="label-left">
                      <ifa-input-select
                        v-model="formModel.orderKind"
                        label="注文種別"
                        prop="orderKind"
                        :required="true"
                        :code-list-id="'ORDER_CLASS'"
                        :disp-pattern="1"
                        :select-pattern="1"
                        :disabled="editDisable"
                        :clearable="false"
                        @change="changeOrderFormat"
                      ></ifa-input-select>
                    </el-col>
                  </el-row>
                </div>

                <!-- フォーム: 期間指定 -->
                <div class="form-area__section">
                  <el-row
                    style="padding-top: 0.5rem;"
                    class="el-col-offset-1"
                  >
                    <el-col :span="formModel.market == '7' ? 8 : 16"
                            class="label-left"
                    >
                      <div :style="formModel.market == '7' ? '' : 'width: 680px;'">
                        <ifa-period-selector
                          ref="periodSelector"
                          v-model:period="formModel.limit"
                          v-model:period-type="formModel.periodRadio"
                          label="注文期間"
                          class="date-picker"
                          prop="periodRadio"
                          :required="true"
                          :button-options="periodButtonOption"
                          :picker-options="checkBusinessDay"
                          :disabled="editDisable"
                          @update:limit="setPeriod"
                        ></ifa-period-selector>
                      </div>
                    </el-col>
                    <el-col
                      v-if="formModel.market == '7'"
                      :span="12"
                      style="color:red; padding: 15px;"
                      class="label-left"
                    >※PTSでの信用取引の場合、前場から後場への注文は引き継がれません。
                    </el-col>
                  </el-row>
                </div>
                <!-- フォーム: 信用取引区分 -->
                <div class="form-area__section">
                  <el-row style="padding-top: 0.5rem;"
                          class="el-col-offset-1"
                  >
                    <el-col class="label-left marginTradeTypeTextField">
                      <el-form-item
                        v-if="!formModel.tradeCd"
                        label="信用取引区分"
                        :required="true"
                        class="label-style"
                      ></el-form-item>
                      <el-form-item
                        v-if="sixMonthDisp()"
                        label="信用取引区分"
                        :required="true"
                        class="label-style"
                      >
                        <span class="el-form-item__content">{{ getMarginTradeTypeText() }}</span>
                      </el-form-item>
                      <el-form-item
                        v-if="noLimitDisp()"
                        label="信用取引区分"
                        :required="true"
                        class="label-style"
                      >
                        <span class="el-form-item__content">{{ getMarginTradeTypeNoLimitText() }}</span>
                      </el-form-item>
                      <ifa-input-radio 
                        v-if="tradeKbnDisp()"                    
                        ref="Radio"
                        v-model="formModel.marginTradeTypeText"
                        prop="marginTradeTypeText"
                        :label="'信用取引区分'"
                        :disabled="editDisable"
                        :code-list-id="'PAYMENT_DEADLINE'"
                        :disp-pattern="1"
                        :select-pattern="3"
                        class="form-radio label-style"
                      ></ifa-input-radio>
                    </el-col>
                  </el-row>
                </div>
              </div>

              <!-- IFD（注文種別が IFD or IFDOCO の時のみ表示） -->
              <div
                v-if="formModel.orderKind=='3' || formModel.orderKind=='4'"
                class="ifd1_section"
              >
                <div :style="styleSeparator">
                  <span class="ifd-oco-label">IFD1</span>
                </div>
                <!-- IFD（注文種別が IFD の時のみ表示） -->
                <div
                  v-if="formModel.orderKind=='3' || formModel.orderKind=='4'"
                >
                  <!-- IFD 取引種別 -->
                  <div class="form-area__section">
                    <el-row
                      style="padding-top: 0.5rem;"
                      class="el-col-offset-1"
                    >
                      <el-col
                        class="label-left"
                        :span="16"
                      >
                        <ifa-input-radio
                          v-model="formModel.tradeCd"
                          label="取引種別"
                          prop="tradeCd"
                          :disabled="editDisable"
                          :code-list-id="'DOMESTIC_STOCK_TRADE_CLASS'"
                          :disp-pattern="1"
                          :select-pattern="3"
                          @change="changeTradeType(formModel.tradeCd)"
                        >
                        </ifa-input-radio>
                      </el-col>

                      <!-- リセットボタン -->
                      <el-col
                        class="label-left"
                        :span="7"
                      >
                        <span class="form-reset-button__wrapper">
                          <ifa-button
                            :disabled="editDisable"
                            text="リセット"
                            color="secondary"
                            tabindex="-1"
                            action-type="originalAction"
                            @app-action-handler="resetAll()"
                          ></ifa-button>
                        </span>
                      </el-col>
                    </el-row>
                  </div>
                  <!-- フォーム: 数量 -->
                  <div class="form-area__section">
                    <el-row class="el-col-offset-1"
                            style="padding-top: 0.5rem;"
                    >
                      <div
                        class="label-left"
                        style="flex: 0 0 550px"
                      >
                        <ifa-input-amount
                          v-model="formModel.orderQuantity"
                          prop="orderQuantity"
                          label="数量"
                          :min="formModel.unit"
                          :disabled="editDisable"
                          :step="formModel.unit"
                          :initial-step="formModel.unit"
                          :support="true"
                          :domain="IfaStocks8DomainModel"
                          unit="株"
                          step-strictly
                        ></ifa-input-amount>
                      </div>

                      <div
                        v-if="formModel.tradeCd"
                        class="el-form-item"
                        style="flex: 1 0 300px"
                      >
                        {{ "売買単位：" + formModel.unit + "株" }}
                      </div>
                    </el-row>
                  </div>
                  <div class="form-area__section">
                    <el-row class="el-col-offset-1"
                            style="padding-top: 0.5rem;"
                    >
                      <!-- フォーム: 執行方法選択 @価格/執行方法 -->
                      <el-col
                        :span="6"
                        class="label-left"
                      >
                        <!-- フォーム: 執行方法選択 当社優先市場／SORの場合 @価格/執行方法 -->
                        <ifa-input-select
                          v-if="formModel.market == 'A'"
                          v-model="formModel.ifd1OrderExecuteMethodList"
                          prop="ifd1OrderExecuteMethodList"
                          label="価格/執行方法"
                          :code-list-id="'EXECUTE_METHOD'"
                          :disp-pattern="1"
                          :select-pattern="2"
                          style="min-width: 100px"
                          :disabled="editDisable"
                          @change="ifd1ChangeExcuteMathed"
                        ></ifa-input-select>
                        <!-- フォーム: 執行方法選択 PTSの場合 @価格/執行方法 -->
                        <ifa-input-select
                          v-else-if="formModel.market == '7'"
                          v-model="formModel.ifd1OrderExecuteMethodList"
                          prop="ifd1OrderExecuteMethodList"
                          label="価格/執行方法"
                          :code-list-id="'EXECUTE_METHOD'"
                          :disp-pattern="1"
                          :select-pattern="3"
                          style="min-width: 100px"
                          :disabled="editDisable"
                          @change="ifd1ChangeExcuteMathed"
                        ></ifa-input-select>
                        <!-- フォーム: 執行方法選択 そのほかの場合 @価格/執行方法 -->
                        <ifa-input-select
                          v-else-if="formModel.market != '7' && formModel.market != 'A'"
                          v-model="formModel.ifd1OrderExecuteMethodList"
                          prop="ifd1OrderExecuteMethodList"
                          label="価格/執行方法"
                          :code-list-id="'EXECUTE_METHOD'"
                          :disp-pattern="1"
                          :select-pattern="1"
                          style="min-width: 100px"
                          :disabled="editDisable"
                          @change="ifd1ChangeExcuteMathed"
                        ></ifa-input-select>
                      </el-col>
                      <el-col
                        v-if="formModel.ifd1OrderExecuteMethodList == '1'"
                        :span="3"
                        style="margin-left: 2rem;"
                      >
                        <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                        <ifa-input-select
                          v-if="formModel.market == '7' || formModel.market == 'A'"
                          v-model="formModel.ifd1LimitExecutionConditionList"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="2"
                          :select-pattern="7"
                          :disabled="editDisable"
                        ></ifa-input-select>
                        <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                        <ifa-input-select
                          v-else
                          v-model="formModel.ifd1LimitExecutionConditionList"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="2"
                          :select-pattern="2"
                          :disabled="editDisable"
                        ></ifa-input-select>
                      </el-col>
                      <el-col
                        v-if="formModel.ifd1OrderExecuteMethodList == '1'"
                        :span="5"
                        style="margin-left: 1rem;"
                      >
                        <ifa-input-price
                          v-model="formModel.ifd1DomesticLimitPrice"
                          prop="ifd1DomesticLimitPrice"
                          :disabled="editDisable"
                          :min="1"
                          :max="maxValueValidator(formModel.ifd1DomesticLimitPrice)"
                          :digit="1"
                          :support="true"
                          :domain="IfaUnitPriceYen101DigitsCDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                        ></ifa-input-price>
                      </el-col>
                      <el-col
                        v-else-if="formModel.ifd1OrderExecuteMethodList == '2'"
                        :span="3"
                        style="margin-left: 2rem;"
                      >
                        <ifa-input-select
                          v-if="formModel.market == 'A'"
                          v-model="formModel.ifd1LimitExecutionConditionList"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="3"
                          :select-pattern="8"
                          :disabled="editDisable"
                        ></ifa-input-select>
                        <ifa-input-select
                          v-else-if="formModel.market != 'A'"
                          v-model="formModel.ifd1LimitExecutionConditionList"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="3"
                          :select-pattern="3"
                          :disabled="editDisable"
                        ></ifa-input-select>
                      </el-col>
                      <el-col
                        v-if="formModel.orderKind != '2' && formModel.market == 'A'"
                        :span="4"
                        style="color:red;margin-top:10px;margin-left:20px;"
                      >
                        <span>(PTS発注時は優先市場最良気配指値)</span>
                      </el-col>
                      <!-- フォーム: 逆指値選択時の条件設定 @価格/執行方法 -->
                      <el-col
                        v-if="formModel.ifd1OrderExecuteMethodList == '3'"
                        :span="2"
                        style="padding: 15px 10px; margin-left: 1rem;"
                      >
                        <span>現在値が</span>
                      </el-col>
                      <el-col
                        v-if="formModel.ifd1OrderExecuteMethodList == '3'"
                        :span="5"
                        style="margin-left: 1rem;"
                      >
                        <ifa-input-price
                          v-model="formModel.ifd1DomesticStopOrderPrice"
                          :min="1"
                          :max="maxValueValidator(formModel.ifd1DomesticStopOrderPrice)"
                          :digit="1"
                          :support="true"
                          :disabled="editDisable"
                          :domain="IfaUnitPriceYen101DigitsCDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                        ></ifa-input-price>
                      </el-col>
                      <el-col
                        v-if="formModel.ifd1OrderExecuteMethodList == '3'"
                        :span="4"
                        style="padding: 15px;"
                      >
                        <span> {{ formModel.tradeCd == '3' ? '以上' : '以下' }} になった時点で</span>
                      </el-col>

                    </el-row>
                    <!-- フォーム: 執行方法選択 @価格/執行方法 -->
                    <el-row
                      v-if="formModel.ifd1OrderExecuteMethodList == '3'"
                      class="el-col-offset-1"
                      style="padding-top: 0.5rem;"
                    >
                      <el-col :span="6"></el-col>
                      <el-col :span="2">
                        <ifa-input-select
                          v-model="formModel.ifd1StopOrderExecuteMethodList"
                          :code-list-id="'EXECUTE_METHOD'"
                          :disp-pattern="1"
                          :select-pattern="2"
                          style="width: 100px"
                          :disabled="editDisable"
                          @change="ifd1ChangeStopOrderExcuteMathed"
                        ></ifa-input-select>
                      </el-col>
                      <el-col
                        v-if="formModel.ifd1StopOrderExecuteMethodList == '2'"
                        :span="3"
                        style="margin-left: 1rem;"
                      >
                        <ifa-input-select
                          v-model="formModel.ifd1LimitExecutionConditionList"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="3"
                          :select-pattern="5"
                        >
                        </ifa-input-select>
                      </el-col>
                      <el-col
                        v-if="formModel.ifd1StopOrderExecuteMethodList == '1'"
                        :span="3"
                        style="margin-left: 1rem;"
                      >
                        <ifa-input-select
                          v-model="formModel.ifd1LimitExecutionConditionList"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="2"
                          :select-pattern="4"
                        >
                        </ifa-input-select>
                      </el-col>
                      <el-col
                        v-if="formModel.ifd1StopOrderExecuteMethodList == '1'"
                        :span="5"
                        style="margin-left: 1rem;"
                      >
                        <ifa-input-price
                          v-model="formModel.ifd1DomesticLimitPrice"
                          :min="1"
                          :max="maxValueValidator(formModel.ifd1DomesticLimitPrice)"
                          :digit="1"
                          :support="true"
                          :domain="IfaUnitPriceYen101DigitsCDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                        ></ifa-input-price>
                      </el-col>
                      <el-col
                        :span="2"
                        style="padding: 15px;"
                      >
                        <span>で執行</span>
                      </el-col>
                    </el-row>
                    <el-row
                      v-if="priceRow == '1.0' && priceHigh == '9999999999999.9'"
                      class="el-col-offset-1"
                    >
                      <el-col :span="(formModel.ifd1OrderExecuteMethodList === '1' || formModel.ifd1OrderExecuteMethodList === '2') ? 9 : 11"></el-col>
                      <el-col
                        :span="6"
                        :style="(formModel.ifd1OrderExecuteMethodList === '1' || formModel.ifd1OrderExecuteMethodList === '2') ? 'padding: 0.8rem 0px 0.8px 1.5rem;' : 'padding: 0.8rem 0px 0.8px 2rem;'"
                      >
                        <span>制限値幅：なし</span>
                      </el-col>
                    </el-row>
                    <el-row
                      v-else-if="priceRow != '1.0' || priceHigh != '9999999999999.9'"
                      class="el-col-offset-1"
                    >
                      <el-col :span="(formModel.ifd1OrderExecuteMethodList === '1' || formModel.ifd1OrderExecuteMethodList === '2') ? 9 : 11"></el-col>
                      <el-col
                        :span="6"
                        :style="(formModel.ifd1OrderExecuteMethodList === '1' || formModel.ifd1OrderExecuteMethodList === '2') ? 'padding: 0.8rem 0px 0.8px 2rem;' : 'padding: 0.8rem 0px 0.8px 2rem;'"
                      >
                        <span>制限値幅：{{ $_out($_withCommaZeroPadding(priceRow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円 ({{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }})</span>
                      </el-col>
                    </el-row>
                  </div>

                  <!-- フォーム: 注文種別 -->
                  <div class="form-area__section">
                    <el-row v-if="formModel.market == '7'"
                            class="el-col-offset-1"
                            style="padding-top: 0.5rem;"
                    >
                      <el-col class="label-left">
                        <ifa-input-select
                          v-model="formModel.orderKind"
                          label="注文種別"
                          prop="orderKind"
                          :required="true"
                          :code-list-id="'ORDER_CLASS'"
                          :disp-pattern="1"
                          :select-pattern="3"
                          :disabled="editDisable"
                          :clearable="false"
                          @change="changeOrderFormat"
                        >
                        </ifa-input-select>
                      </el-col>
                    </el-row>
                    <el-row v-else
                            class="el-col-offset-1"
                            style="padding-top: 0.5rem;"
                    >
                      <el-col class="label-left">
                        <ifa-input-select
                          v-model="formModel.orderKind"
                          label="注文種別"
                          prop="orderKind"
                          :required="true"
                          :code-list-id="'ORDER_CLASS'"
                          :disp-pattern="1"
                          :select-pattern="1"
                          :disabled="editDisable"
                          :clearable="false"
                          @change="changeOrderFormat"
                        ></ifa-input-select>
                      </el-col>
                    </el-row>
                  </div>
                </div>
                <!-- フォーム: 期間指定 -->
                <div class="form-area__section">
                  <el-row
                    style="padding-top: 0.5rem;"
                    class="el-col-offset-1"
                  >
                    <el-col :span="formModel.market == '7' ? 8 : 16"
                            class="label-left"
                    >
                      <div :style="formModel.market == '7' ? '' : 'width: 680px;'">
                        <ifa-period-selector
                          ref="periodSelector2"
                          v-model:period="formModel.limit"
                          v-model:period-type="formModel.periodRadio"
                          label="注文期間"
                          class="date-picker"
                          prop="periodRadio"
                          :required="true"
                          :button-options="periodButtonOption"
                          :picker-options="checkBusinessDay"
                          :disabled="editDisable"
                          @update:limit="setPeriod"
                        ></ifa-period-selector>
                      </div>
                    </el-col>
                    <el-col
                      v-if="formModel.market == '7'"
                      :span="12"
                      style="color:red; padding: 15px;"
                    >※PTSでの信用取引の場合、前場から後場への注文は引き継がれません。
                    </el-col>
                  </el-row>
                </div>

                <!-- フォーム: 信用取引区分 -->
                <div class="form-area__section">
                  <el-row
                    style="padding-top: 0.5rem;"
                    class="el-col-offset-1"
                  >
                    <el-col class="label-left marginTradeTypeTextField">
                      <el-form-item
                        v-if="!formModel.tradeCd"
                        label="信用取引区分"
                        :required="true"
                        class="label-style"
                      ></el-form-item>
                      <el-form-item
                        v-if="(formModel.tradeCd === '3' &&
                          (formModel.mktIppanLoanKbnTky !== '1' &&
                            formModel.mktIppanLoanKbnNgy !== '1' &&
                            formModel.mktIppanLoanKbnFko !== '1' &&
                            formModel.mktIppanLoanKbnSpr !== '1') || formModel.tradeCd === '4')"
                        label="信用取引区分"
                        :required="true"
                        class="label-style"
                      >
                        <span class="el-form-item__content">{{ getMarginTradeTypeText() }}</span>
                      </el-form-item>
                      <ifa-input-radio
                        v-if="formModel.tradeCd == '3' && (formModel.mktIppanLoanKbnTky == '1' || formModel.mktIppanLoanKbnNgy == '1' || formModel.mktIppanLoanKbnFko == '1' || formModel.mktIppanLoanKbnSpr == '1')"
                        v-model="formModel.marginTradeTypeText"
                        prop="marginTradeTypeText"
                        :label="'信用取引区分'"
                        :disabled="editDisable"
                        :code-list-id="'PAYMENT_DEADLINE'"
                        :disp-pattern="1"
                        :select-pattern="3"
                        :disabled-items="['A', '7'].includes(formModel.market) ? ['9'] : []"
                        class="form-radio label-style"
                      ></ifa-input-radio>
                    </el-col>
                  </el-row>
                </div>
              </div>

              <!-- IFD2 -->
              <div
                v-if="formModel.orderKind =='3' || formModel.orderKind =='4'"
                class="ifd1_section"
              >
                <div :style="styleSeparator">
                  <span class="ifd-oco-label">IFD2</span>
                </div>
                <!-- ここにIFD1の取引種別を表示させる -->
                <div class="form-area__section">
                  <el-row class="el-col-offset-1"
                          style="padding-top: 0.5rem;"
                  >
                    <el-col class="label-left">
                      <el-form-item
                        label="取引種別"
                        style="font-weight:bold;"
                        class="form_label"
                      >
                        <span
                          :style="formModel.tradeCd == '3' ? 'color:#00847F' : 'color:#E5004C' "
                        >{{ $_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, (formModel.tradeCd == '3' ? '6' : '5' )) }}</span>
                      </el-form-item>
                    </el-col>
                  </el-row>
                </div>
                <!-- IFD（注文種別が IFD の時のみ表示） -->
                <div
                  v-if="formModel.orderKind=='3'"
                  class="form-area__section"
                >
                  <el-row class="el-col-offset-1"
                          style="padding-top: 0.5rem;"
                  >
                    <el-col :span="6"
                            class="label-left"
                    >
                      <!-- フォーム: 執行方法選択 そのほかの場合 @価格/執行方法 -->
                      <ifa-input-select
                        v-model="formModel.ifd2OrderExecuteMethodList"
                        prop="ifd2OrderExecuteMethodList"
                        label="価格/執行方法"
                        :code-list-id="'EXECUTE_METHOD'"
                        :disp-pattern="1"
                        :select-pattern="4"
                        style="min-width: 100px"
                        :disabled="editDisable"
                        :required="true"
                        @change="ifd2ChangeExcuteMathed"
                      >
                      </ifa-input-select>
                    </el-col>
                    <el-col
                      v-if="formModel.ifd2OrderExecuteMethodList == '1'"
                      :span="3"
                      style="margin-left: 2rem;"
                    >
                      <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                      <ifa-input-select
                        v-model="formModel.ifd2LimitExecutionConditionList"
                        :code-list-id="'LIMIT_MARKET_TYPE'"
                        :disp-pattern="2"
                        :select-pattern="4"
                        :disabled="editDisable"
                      ></ifa-input-select>
                    </el-col>
                    <el-col
                      v-if="formModel.ifd2OrderExecuteMethodList == '1'"
                      :span="5"
                      style="margin-left: 1rem;"
                    >
                      <ifa-input-price
                        v-model="formModel.ifd2DomesticLimitPrice"
                        prop="ifd2DomesticLimitPrice"
                        :disabled="editDisable"
                        :min="1"
                        :max="maxValueValidator(formModel.ifd2DomesticLimitPrice)"
                        :digit="1"
                        :support="true"
                        :domain="IfaUnitPriceYen101DigitsCDomainModel"
                        :initial-step="initialStep"
                        :step-table="priceInfo.orderPriceUnit"
                        unit="円"
                      ></ifa-input-price>
                    </el-col>
                    <!-- フォーム: 逆指値選択時の条件設定 @価格/執行方法 -->
                    <el-col
                      v-if="formModel.ifd2OrderExecuteMethodList == '3'"
                      :span="2"
                      style="padding: 15px 10px; margin-left: 1rem;"
                    >
                      <span>現在値が</span>
                    </el-col>
                    <el-col
                      v-if="formModel.ifd2OrderExecuteMethodList == '3'"
                      :span="5"
                      style="margin-left: 1rem;"
                    >
                      <ifa-input-price
                        v-model="formModel.ifd2DomesticStopOrderPrice"
                        :min="1"
                        :max="maxValueValidator(formModel.ifd2DomesticStopOrderPrice)"
                        :digit="1"
                        :support="true"
                        :disabled="editDisable"
                        :domain="IfaUnitPriceYen101DigitsCDomainModel"
                        :initial-step="initialStep"
                        :step-table="priceInfo.orderPriceUnit"
                        unit="円"
                      ></ifa-input-price>
                    </el-col>
                    <el-col
                      v-if="formModel.ifd2OrderExecuteMethodList == '3'"
                      :span="4"
                      style="padding: 15px;"
                    >
                      <span> {{ formModel.tradeCd == '3' ? '以下' : '以上' }} になった時点で</span>
                    </el-col>
                  </el-row>
                  <el-row
                    v-if="formModel.ifd2OrderExecuteMethodList == '3'"
                    style="padding-top: 0.5rem"
                    class="el-col-offset-1"
                  >
                    <el-col :span="6"></el-col>
                    <el-col :span="2">
                      <ifa-input-select
                        v-model="formModel.ifd2StopOrderExecuteMethodList"
                        :code-list-id="'EXECUTE_METHOD'"
                        :disp-pattern="1"
                        :select-pattern="2"
                        style="width: 100px"
                        :disabled="editDisable"
                        @change="ifd2ChangeStopOrderExcuteMathed"
                      ></ifa-input-select>
                    </el-col>
                    <el-col
                      :span="3"
                      style="margin-left: 1rem;"
                    >
                      <ifa-input-select
                        v-if="formModel.ifd2StopOrderExecuteMethodList == '2'"
                        v-model="formModel.ifd2LimitExecutionConditionList"
                        :code-list-id="'LIMIT_MARKET_TYPE'"
                        :disp-pattern="3"
                        :select-pattern="5"
                      ></ifa-input-select>
                      <ifa-input-select
                        v-else-if="formModel.ifd2StopOrderExecuteMethodList == '1'"
                        v-model="formModel.ifd2LimitExecutionConditionList"
                        :code-list-id="'LIMIT_MARKET_TYPE'"
                        :disp-pattern="2"
                        :select-pattern="4"
                      ></ifa-input-select>
                    </el-col>
                    <el-col
                      v-if="formModel.ifd2StopOrderExecuteMethodList == '1'"
                      :span="5"
                      style="margin-left: 1rem;"
                    >
                      <ifa-input-price
                        v-model="formModel.ifd2DomesticLimitPrice"
                        :min="1"
                        :max="maxValueValidator(formModel.ifd2DomesticLimitPrice)"
                        :digit="1"
                        :support="true"
                        :domain="IfaUnitPriceYen101DigitsCDomainModel"
                        :initial-step="initialStep"
                        :step-table="priceInfo.orderPriceUnit"
                        unit="円"
                      ></ifa-input-price>
                    </el-col>
                    <el-col
                      :span="2"
                      style="padding: 15px;"
                    >
                      <span>{{ formModel.stopOrderExecutePriceText2 }}</span>
                    </el-col>
                  </el-row>
                  <el-row
                    v-if="priceRow == '1.0' && priceHigh == '9999999999999.9'"
                    class="el-col-offset-1"
                  >
                    <el-col :span="formModel.ifd2OrderExecuteMethodList === '1' ? 9 : 12"></el-col>
                    <el-col
                      :span="6"
                      :style="formModel.ifd2OrderExecuteMethodList === '1' ? 'padding: 0.8rem 0px 0.8px 1.5rem;' : 'padding: 0.8rem 0px 0.8px 2rem;'"
                    >
                      <span>制限値幅：なし</span>
                    </el-col>
                  </el-row>
                  <el-row
                    v-else-if="priceRow != '1.0' || priceHigh != '9999999999999.9'"
                    class="el-col-offset-1"
                  >
                    <el-col :span="formModel.ifd2OrderExecuteMethodList === '1' ? 9 : 11"></el-col>
                    <el-col
                      :span="6"
                      :style="formModel.ifd2OrderExecuteMethodList === '1' ? 'padding: 0.8rem 0px 0.8px 2.5rem;' : 'padding: 0.8rem 0px 0.8px 2rem;'"
                    >
                      <span>制限値幅：{{ $_out($_withCommaZeroPadding(priceRow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円 ({{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }})</span>
                    </el-col>
                  </el-row>
                </div>
                <!-- フォーム: 期間指定 -->
                <div class="form-area__section">
                  <el-row
                    style="padding-top: 0.5rem;"
                    class="el-col-offset-1"
                  >
                    <el-col :span="formModel.market == '7' ? 8 : 16"
                            class="label-left"
                    >
                      <div :style="formModel.market == '7' ? '' : 'width: 680px;'">
                        <ifa-period-selector
                          ref="periodSelectorIfd2"
                          v-model:period="formModel.ifd2Limit"
                          v-model:period-type="formModel.ifd2PeriodRadio"
                          label="注文期間"
                          class="date-picker"
                          prop="ifd2PeriodRadio"
                          :required="true"
                          :button-options="periodButtonOptionIfd2"
                          :picker-options="checkBusinessDay"
                          :disabled="editDisable"
                          @update:ifd2-limit="setPeriod2"
                        ></ifa-period-selector>
                      </div>
                    </el-col>
                    <el-col
                      v-if="formModel.market == '7'"
                      :span="12"
                      style="color:red; padding: 15px;"
                    >※PTSでの信用取引の場合、前場から後場への注文は引き継がれません。
                    </el-col>
                  </el-row>
                </div>
              </div>

              <!-- OCO1 OCO2 (注文種別が OCO or IFDOCO の時のみ表示) -->
              <div
                v-if="formModel.orderKind =='2' || formModel.orderKind =='4'"
                class="oco1_section"
              >
                <div :style="styleSeparator">
                  <span :class="ocoIndentStyle">OCO1</span>
                </div>
                <el-row class="el-col-offset-1"
                        style="padding-top: 0.5rem;"
                >
                  <el-col
                    :span="6"
                    class="label-left"
                  >
                    <!-- フォーム: 執行方法選択 OCO1執行方法 -->
                    <ifa-input-select
                      v-model="formModel.oco1OrderExecuteMethodList"
                      prop="oco1OrderExecuteMethodList"
                      label="価格/執行方法"
                      :code-list-id="'EXECUTE_METHOD'"
                      :disp-pattern="1"
                      :select-pattern="3"
                      style="min-width: 100px"
                      :disabled="editDisable"
                      @change="oco1ChangeExcuteMathed"
                    >
                    </ifa-input-select>
                  </el-col>
                  <el-col
                    :span="3"
                    style="margin-left: 2rem;"
                  >
                    <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                    <ifa-input-select
                      v-model="formModel.oco1LimitExecutionConditionList"
                      :code-list-id="'LIMIT_MARKET_TYPE'"
                      :disp-pattern="2"
                      :select-pattern="6"
                      :disabled="editDisable"
                    >
                    </ifa-input-select>
                  </el-col>
                  <el-col
                    :span="5"
                    style="margin-left: 1rem;"
                  >
                    <ifa-input-price
                      v-model="formModel.oco1DomesticLimitPrice"
                      prop="oco1DomesticLimitPrice"
                      :disabled="editDisable"
                      :min="1"
                      :max="maxValueValidator(formModel.oco1DomesticLimitPrice)"
                      :digit="1"
                      :support="true"
                      :domain="IfaUnitPriceYen101DigitsCDomainModel"
                      :initial-step="initialStep"
                      :step-table="priceInfo.orderPriceUnit"
                      unit="円"
                    ></ifa-input-price>
                  </el-col>
                </el-row>
                <el-row
                  v-if="formModel.oco1OrderExecuteMethodList != '2' && (priceRow == '1.0' && priceHigh == '9999999999999.9')"
                  class="el-col-offset-1"
                >
                  <el-col :span="9"></el-col>
                  <el-col
                    :span="6"
                    style="padding: 0.8rem 0px 0.8px 2.5rem;"
                  >
                    <span>制限値幅：なし</span>
                  </el-col>
                </el-row>
                <el-row
                  v-else-if="formModel.oco1OrderExecuteMethodList != '2' && (priceRow != '1.0' || priceHigh != '9999999999999.9')"
                  class="el-col-offset-1"
                >
                  <el-col :span="9"></el-col>
                  <el-col
                    :span="6"
                    style="padding: 0.8rem 0px 0.8px 2.5rem;"
                  >
                    <span>制限値幅：{{ $_out($_withCommaZeroPadding(priceRow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円 ({{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }})</span>
                  </el-col>
                </el-row>

                <!-- フォーム: 注文種別 OCOの時のみ表示-->
                <el-row v-if="formModel.orderKind == '2'"
                        class="el-col-offset-1"
                >
                  <el-col class="label-left">
                    <ifa-input-select
                      v-model="formModel.orderKind"
                      label="注文種別"
                      prop="orderKind"
                      :required="true"
                      :code-list-id="'ORDER_CLASS'"
                      :disp-pattern="1"
                      :select-pattern="1"
                      :disabled="editDisable"
                      :clearable="false"
                      @change="changeOrderFormat"
                    >
                    </ifa-input-select>
                  </el-col>
                </el-row>
                <!-- OCO2（注文種別が OCO または IFDOCO の時のみ表示） -->
                <div :style="styleSeparator">
                  <span :class="ocoIndentStyle">OCO2</span>
                </div>
                <el-row class="el-col-offset-1"
                        style="padding-top: 0.5rem;"
                >
                  <el-col :span="6"
                          class="label-left"
                  >
                    <!-- フォーム: 執行方法選択 PTSの場合 @価格/執行方法 -->
                    <ifa-input-select
                      v-model="formModel.oco2OrderExecuteMethodList"
                      prop="oco2OrderExecuteMethodList"
                      label="価格/執行方法"
                      :code-list-id="'EXECUTE_METHOD'"
                      :disp-pattern="1"
                      :select-pattern="5"
                      style="min-width: 100px"
                      :disabled="editDisable"
                      @change="oco2ChangeExcuteMathed"
                    ></ifa-input-select>
                  </el-col>
                  <!-- フォーム: 逆指値選択時の条件設定 @価格/執行方法 -->
                  <el-col
                    style="padding: 15px 10px; margin-left: 1rem;"
                    :span="2"
                  >
                    <span>現在値が</span>
                  </el-col>
                  <el-col :span="5"
                          style="margin-left: 1rem;"
                  >
                    <ifa-input-price
                      v-model="formModel.oco2DomesticStopOrderPrice"
                      prop="oco2DomesticStopOrderPrice"
                      :min="1"
                      :max="maxValueValidator(formModel.oco2DomesticStopOrderPrice)"
                      :digit="1"
                      :support="true"
                      :disabled="editDisable"
                      :domain="IfaUnitPriceYen101DigitsCDomainModel"
                      :initial-step="initialStep"
                      :step-table="priceInfo.orderPriceUnit"
                      unit="円"
                    ></ifa-input-price>
                  </el-col>
                  <el-col
                    style="padding: 15px;"
                    :span="8"
                  >
                    <!--OCO-->
                    <span v-if="formModel.orderKind =='2'"> {{ formModel.tradeCd == '3' ? '以上' : '以下' }} {{ formModel.domesticStopOrderPriceText1 }}</span>
                    <!--IFDOCO-->
                    <span v-else> {{ formModel.tradeCd == '3' ? '以下' : '以上' }} {{ formModel.domesticStopOrderPriceText1 }}</span>
                  </el-col>
                </el-row>

                <!-- フォーム: 執行方法選択 @価格/執行方法 -->
                <el-row
                  class="el-col-offset-1"
                >
                  <el-col :span="6"></el-col>
                  <el-col
                    :span="2"
                  >
                    <ifa-input-select
                      v-if="formModel.oco1LimitExecutionConditionList !== 'F'"
                      v-model="formModel.oco2StopOrderExecuteMethodList"
                      :code-list-id="'EXECUTE_METHOD'"
                      :disp-pattern="1"
                      :select-pattern="2"
                      style="width: 100px"
                      :disabled="editDisable"
                      @change="oco2ChangeExcuteMathed"
                    ></ifa-input-select>
                    <ifa-input-select
                      v-else-if="formModel.oco1LimitExecutionConditionList === 'F'"
                      v-model="formModel.oco2StopOrderExecuteMethodList"
                      :code-list-id="'EXECUTE_METHOD'"
                      :disp-pattern="1"
                      :select-pattern="3"
                      style="width: 100px"
                      :disabled="editDisable"
                      @change="oco2ChangeExcuteMathed"
                    ></ifa-input-select>
                  </el-col>
                  <el-col
                    v-if="formModel.oco2StopOrderExecuteMethodList == '2'"
                    :span="3"
                    style="margin-left: 1rem;"
                  >
                    <ifa-input-select
                      v-model="formModel.oco2StopOrderMarketExecutionConditionList"
                      :code-list-id="'LIMIT_MARKET_TYPE'"
                      :disp-pattern="3"
                      :select-pattern="8"
                    ></ifa-input-select>
                  </el-col>
                  <el-col
                    v-else-if="formModel.oco2StopOrderExecuteMethodList == '1'"
                    :span="3"
                    style="margin-left: 1rem;"
                  >
                    <ifa-input-select
                      v-model="formModel.oco1LimitExecutionConditionList"
                      :code-list-id="'LIMIT_MARKET_TYPE'"
                      :disp-pattern="2"
                      :select-pattern="6"
                      :disabled="true"
                    ></ifa-input-select>
                  </el-col>
                  <el-col
                    v-if="formModel.oco2StopOrderExecuteMethodList == '1'"
                    :span="5"
                    style="margin-left: 1rem;"
                  >
                    <ifa-input-price
                      v-model="formModel.oco2DomesticLimitPrice"
                      prop="oco2DomesticLimitPrice"
                      :min="1"
                      :max="maxValueValidator(formModel.oco2DomesticLimitPrice)"
                      :digit="1"
                      :support="true"
                      :domain="IfaUnitPriceYen101DigitsCDomainModel"
                      :initial-step="initialStep"
                      :step-table="priceInfo.orderPriceUnit"
                      unit="円"
                    ></ifa-input-price>
                  </el-col>
                  <el-col
                    :span="2"
                    style="padding: 15px;"
                  >
                    <span>で執行</span>
                  </el-col>
                </el-row>
                <el-row
                  v-if="priceRow == '1.0' && priceHigh == '9999999999999.9'"
                  class="el-col-offset-1"
                >
                  <el-col :span="11"></el-col>
                  <el-col
                    :span="6"
                    style="padding: 0.8rem 0px 0.8px 2rem;"
                  >
                    <span>制限値幅：なし</span>
                  </el-col>
                </el-row>
                <el-row
                  v-else-if="priceRow != '1.0' || priceHigh != '9999999999999.9'"
                  class="el-col-offset-1"
                >
                  <el-col :span="11"></el-col>
                  <el-col
                    :span="6"
                    style="padding: 0.8rem 0px 0.8px 2rem;"
                  >
                    <span>制限値幅：{{ $_out($_withCommaZeroPadding(priceRow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円 ({{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }})</span>
                  </el-col>
                </el-row>
              </div>

              <!-- フォーム: 手数料区分 -->
              <!-- 表示のさせ方を要相談 -->
              <el-row class="form-area__section"
                      style="padding-top: 0.5rem;"
              >
                <el-col class="el-col-offset-1">
                  <div
                    class="label-left"
                  >
                    <el-form-item
                      label="手数料区分"
                      class="charge-type form_label"
                    >
                      <span>{{ $_getCodeValue('PRE_CONTRACT_DOC_CODE', 1, customerInfo.customerAttribute) }}（電話手数料）</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <el-row class="form-area__section"
                      style="padding-top: 0.5rem;"
              >
                <!-- フォーム: 勧誘区分 -->
                <el-col class="el-col-offset-1">
                  <div class="label-left">
                    <ifa-input-select
                      v-model="formModel.kanyuKbn"
                      label="勧誘区分"
                      :code-list-id="'INVITATION_TYPE'"
                      :disabled="editDisable"
                      :clearable="false"
                      prop="kanyuKbn"
                    ></ifa-input-select>
                  </div>
                </el-col>
              </el-row>
              <el-row class="el-col-offset-1"
                      style="padding-top: 0.5rem;"
              >
                <!-- フォーム: 受注方法 -->
                <el-col class="label-left">
                  <div>
                    <ifa-input-select
                      v-model="formModel.receiveOrderType"
                      label="受注方法"
                      :code-list-id="'ORDER_METHOD_TYPE'"
                      :disabled="editDisable"
                      :clearable="false"
                      prop="receiveOrderType"
                    >
                    </ifa-input-select>
                  </div>
                </el-col>
              </el-row>
              <el-row class="el-col-offset-1"
                      style="padding-top: 0.5rem;"
              >
                <!-- フォーム: 重要事項確認 -->
                <el-col class="label-left">
                  <div>
                    <ifa-input-check
                      v-model="formModel.checkInsider"
                      label-class="validation-error-width"
                      label="確認項目"
                      :code-list-id="'INSIDER_CONFIRM'"
                      :disp-pattern="2"
                      :select-pattern="2"
                      :disabled="editDisable"
                      prop="checkInsider"
                    ></ifa-input-check>
                  </div>
                  <div style="margin-left:236px;">
                    <ifa-input-check
                      v-if="formModel.market == 'A'"
                      v-model="formModel.checkSor"
                      label-class="validation-error-width"
                      label=""
                      :code-list-id="'SOR_CONFIRM'"
                      :disp-pattern="2"
                      :select-pattern="2"
                      :disabled="editDisable"
                      prop="checkSor"
                    ></ifa-input-check>
                  </div>
                </el-col>
              </el-row>
              <!-- フォーム: 注文確認 -->
              <ifa-button
                id="btnOrderConfirm"
                class="label-btn"
                :disabled="editDisable"
                text="注文確認"
                color="primary"
                action-type="requestAction"
                action-id="SUB0202_0212-01_1#A016"
                :form="$refs.formModel"
                :pre-request-handler="preRequestHandlerA016"
                :request-model="IfaMarginNewOrderInputA016RequestModel"
                @response-handler="responseHandlerA016"
              ></ifa-button>
            </el-row>
          </el-form>
          <!-- ダイアログ -->
          <ifa-stock-detail-info
            :is-visible="displayStockBoard"
            :form-data="detailInfo"
            @close-modal="displayStockBoard = false"
            @price-select="handlePriceSelect"
          ></ifa-stock-detail-info>
        </el-card>

        <ifa-margin-new-order-confirm
          ref="IfaMarginNewOrderConfirm"
          :is-visible="dialogConfirmVisible"
          :form-model="confirmFormModel"
          :priority-market="formModel.market"
          :customer-info="customerInfo"
          @close-modal="handleCloseModal"
          @order-finish="handleOrderFinish"
        ></ifa-margin-new-order-confirm>

        <ifa-margin-new-order-complete
          :is-visible="dialogCompleteVisible"
          :ref-form-model="completeFormModel"
          :stock-info="formModel"
          :priority-market="formModel.market"
          :customer-info="customerInfo"
          @close-modal="handleCloseModal(); resetForm()"
          @move-to-order-list="handleMoveToOrderList"
        ></ifa-margin-new-order-complete>
      </el-row>
    </div>
  </div>
</template>

<script>
import IfaBrandSearch from '@/components/SearchCondition/IfaBrandSearch'
import IfaBrandPriceInfo from '@/components/Info/IfaBrandPriceInfo'
import IfaMarginNewOrderConfirm from './IfaMarginNewOrderConfirm'
import IfaMarginNewOrderComplete from './IfaMarginNewOrderComplete'
import IfaStockDetailInfo from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/IfaStockDetailInfo'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import { IfaMarginNewOrderInputFormModel } from './js/IfaMarginNewOrderInputFormModel'
import { IfaMarginNewOrderConfirmFormModel } from './js/IfaMarginNewOrderConfirmFormModel'
import { IfaMarginNewOrderInputA002RequestModel } from './js/IfaMarginNewOrderInputA002RequestModel'
import { IfaMarginNewOrderInputA004RequestModel } from './js/IfaMarginNewOrderInputA004RequestModel'
import { IfaMarginNewOrderInputA005RequestModel } from './js/IfaMarginNewOrderInputA005RequestModel'
import { IfaMarginNewOrderInputA016RequestModel } from './js/IfaMarginNewOrderInputA016RequestModel'
import { IfaStockDetailInfoA001RequestModel } from '../spotTrade/js/IfaStockDetailInfoA001RequestModel'
import { IfaMarginNewOrderCompleteFormModel } from './js/IfaMarginNewOrderCompleteFormModel'
import IfaStocks8DomainModel from '@/resource/domain/IfaStocks8DomainModel.json'
import IfaUnitPriceYen101DigitsCDomainModel from '@/resource/domain/IfaUnitPriceYen101DigitsCDomainModel.json'
// import IfaLinkText20DomainModel from '@/resource/domain/IfaLinkText20DomainModel.json'
import IfaPeriodSelector from '@/components/Date/IfaPeriodSelector'

const orderInput = require('@/views/brokerageMenu/customerMenu/commonProcess/orderInput.js')

export default {
  components: {
    IfaBrandSearch,
    IfaBrandPriceInfo,
    IfaMarginNewOrderConfirm,
    IfaMarginNewOrderComplete,
    IfaPeriodSelector,
    IfaStockDetailInfo,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      IfaStocks8DomainModel: IfaStocks8DomainModel,
      IfaUnitPriceYen101DigitsCDomainModel: IfaUnitPriceYen101DigitsCDomainModel,
      dialogConfirmVisible: false,
      dialogCompleteVisible: false,
      displayStockBoard: false,
      ocoIndentStyle: 'ifd-oco-label',
      maxOrderableQuantity: 9999999999,
      formModel: new IfaMarginNewOrderInputFormModel(),
      confirmFormModel: new IfaMarginNewOrderConfirmFormModel(),
      completeFormModel: new IfaMarginNewOrderCompleteFormModel(),
      detailInfo: {},
      originalMarketList: [],
      customMarketList: [],
      bgColor: '#fdf6ec',
      styleSeparator: {
        height: '20px',
        background: '#ffd985',
        fontWeight: 'bold',
        marginBottom: '10px'
      },
      priceInfo: {
        orderPriceUnit: [] // 使用
      },
      upperRankMkt: '',
      rules: {
        sasinariHouhou: [
          { required: true, message: '執行方法を選択してください｡', trigger: 'change' }
        ],
        ifd1OrderExecuteMethodList: [
          { required: true, message: '執行方法を選択してください｡', trigger: 'change' }
        ],
        ifd2OrderExecuteMethodList: [
          { required: true, message: '執行方法を選択してください｡', trigger: 'change' }
        ],
        oco1OrderExecuteMethodList: [
          { required: true, message: '執行方法を選択してください｡', trigger: 'change' }
        ],
        oco2OrderExecuteMethodList: [
          { required: true, message: '執行方法を選択してください｡', trigger: 'change' }
        ],
        tradeCd: [
          { required: true, message: '取引種別を選択してください｡', trigger: 'blur' }
        ],
        orderQuantity: [
          { required: true, message: '株数を入力してください。', trigger: 'blur' }
        ],
        price: [
          { required: true, message: '価格を入力してください。', trigger: 'blur' }
        ],
        triggerPrice: [
          { required: true, message: '発火条件価格を入力してください。', trigger: 'blur' }
        ],
        ifd1DomesticLimitPrice: [
          { required: true, message: '価格を入力してください。', trigger: 'blur' }
        ],
        ifd1DomesticStopOrderPrice: [
          { required: true, message: '発火条件価格を入力してください。', trigger: 'blur' }
        ],
        ifd2DomesticLimitPrice: [
          { required: true, message: '価格を入力してください。', trigger: 'blur' }
        ],
        ifd2DomesticStopOrderPrice: [
          { required: true, message: '発火条件価格を入力してください。', trigger: 'blur' }
        ],
        oco1DomesticLimitPrice: [
          { required: true, message: '価格を入力してください。', trigger: 'blur' }
        ],
        oco2DomesticStopOrderPrice: [
          { required: true, message: '発火条件価格を入力してください。', trigger: 'blur' }
        ],
        oco2DomesticLimitPrice: [
          { required: true, message: '価格を入力してください。', trigger: 'blur' }
        ],
        marginTradeTypeText: [
          { required: true, message: '信用取引区分を選択してください｡', trigger: 'blur' }
        ],
        checkInsider: [
          { required: true, message: 'インサイダー取引および契約締結前交付書面を確認してください｡', trigger: 'blur' }
        ],
        checkSor: [
          { required: true, message: 'SOR対象銘柄の説明を確認してください｡', trigger: 'blur' }
        ],
        kanyuKbn: [
          { required: true, message: '勧誘区分を選択してください｡', trigger: 'blur' }
        ],
        receiveOrderType: [
          { required: true, message: '受注方法を選択してください｡', trigger: 'blur' }
        ]
      },
      checkBusinessDay: {
        disabledDate(date) {
          // 'date'が土日の場合は無効な日付(true)
          if (date.getDay() === 0 || date.getDay() === 6) {
            return true
          }
          // 翌日を算出する｡現在時刻が15時以降なら翌々日に補正
          const before = new Date()
          const hours = before.getHours()
          before.setHours(0, 0, 0, 0)
          if (hours < 15) {
            before.setDate(before.getDate() + 1)
          } else {
            before.setDate(before.getDate() + 2)
          }
          // 翌日または翌々日が土日の場合は翌月曜日に補正
          if (before.getDay() === 6) {
            before.setDate(before.getDate() + 2)
          } else if (before.getDay() === 0) {
            before.setDate(before.getDate() + 1)
          }
          // 'date'が算出した日付より小さい場合は無効な日付
          if (before > date) {
            return true
          }
          // 14営業日先の日付を算出する
          const after = new Date(before)
          let d = 13
          while (d > 0) {
            after.setDate(after.getDate() + 1)
            if (after.getDay() !== 0 && after.getDay() !== 6) {
              d--
            }
          }
          // 'date'が算出した日付より大きい場合は無効な日付
          if (after < date) {
            return true
          }
          // 上記以外は有効な日付
          return false
        }
      }
    }
  },
  computed: {
    IfaMarginNewOrderInputA004RequestModel() {
      return new IfaMarginNewOrderInputA004RequestModel(this.formModel)
    },
    IfaMarginNewOrderInputA005RequestModel() {
      return new IfaMarginNewOrderInputA005RequestModel(this.formModel)
    },
    IfaMarginNewOrderInputA002RequestModel() {
      return new IfaMarginNewOrderInputA002RequestModel(this.formModel)
    },
    IfaMarginNewOrderInputA016RequestModel() {
      return new IfaMarginNewOrderInputA016RequestModel(this.formModel)
    },
    IfaStockDetailInfoA001RequestModel() {
      return new IfaStockDetailInfoA001RequestModel(
        {
          brandCode: this.formModel.brandCode,
          market: this.marketRequest
        }
      )
    },
    editDisable() {
      return this.formModel.brandCode === ''
    },
    // 金額未入力時に+ボタン押下でセットする値
    initialStep() {
      // CC013.現在値
      const currentPrice = Number(this.priceInfo?.currentPrice)
      if (currentPrice !== 0 && !isNaN(currentPrice)) {
        return currentPrice
      }

      // CC013.基準価格
      const basePrice = Number(this.priceInfo?.basePrice)
      if (basePrice !== 0 && !isNaN(basePrice)) {
        return basePrice
      }

      // CC013.現在値、基準価格がともに取得できない場合
      return 0
    },
    // 通常/逆指値, OCO, IFD(IFD1), IFDOCO(IFD2)で表示する期間の表示条件
    periodButtonOption() {
      // 銘柄指定前(初期表示など)
      if (this.formModel.market === '') {
        return {}
      }
      if (this.formModel.orderKind === '2') { // 注文種別＝OCOの場合
        if (this.formModel.oco1OrderExecuteMethodList === '1' && // OCO1.執行方法=指値　かつ
          this.formModel.oco1LimitExecutionConditionList === 'F') { // OCO1.執行条件（指値）=不成
          return { period: 'disabled' }
        }
        return {}
      } else if (this.formModel.orderKind === '3' || this.formModel.orderKind === '4') { // 注文種別＝IFDまたはIFDOCOの場合
        if (this.formModel.market === '7') { // 市場がPTSの場合
          return { period: 'hide' }
        } else { // 市場がPTS以外の場合
          if ((this.formModel.ifd1OrderExecuteMethodList === '1' && this.formModel.ifd1LimitExecutionConditionList !== ' ') || // 執行方法=指値　かつ　執行条件<>条件なし　または
            this.formModel.ifd1OrderExecuteMethodList === '2') { // 執行方法=成行
            return { period: 'disabled' }
          }
        }
        return {}
      } else { // 注文種別＝OCO、IFD、IFDOCO以外の場合
        if (this.formModel.market === '7') { // 市場がPTSの場合
          return { period: 'hide' }
        } else { // 市場がPTS以外の場合
          if ((this.formModel.sasinariHouhou === '1' && this.formModel.sasinariJyouken !== ' ') || // 執行方法=指値　かつ　執行条件<>条件なし　または
            this.formModel.sasinariHouhou === '2') { // 執行方法=成行
            return { period: 'disabled' }
          }
        }
        return {}
      }
    },
    // IFD(IFD2), IFDOCO(IDF2)で表示する期間の表示条件
    periodButtonOptionIfd2() {
      if (this.formModel.orderKind === '4') { // 注文種別＝IFDOCOの場合
        if (this.formModel.oco1OrderExecuteMethodList === '1' && // OCO1.執行方法=指値　かつ
          this.formModel.oco1LimitExecutionConditionList === 'F') { // OCO1.執行条件（指値）=不成
          return { period: 'disabled' }
        }
      } else {
        if (this.formModel.market === '7') { // 市場がPTSの場合
          return { period: 'hide' }
        } else { // 市場がPTS以外の場合
          if ((this.formModel.ifd2OrderExecuteMethodList === '1' && this.formModel.ifd2LimitExecutionConditionList !== ' ') || // 執行方法=指値　かつ　執行条件<>条件なし　または
            this.formModel.ifd2OrderExecuteMethodList === '2') { // 執行方法=成行
            return { period: 'disabled' }
          }
        }
      }
      return {}
    },
    newBuildingCapacityT0() {
      let newBuildingCapacityT0 = this.formModel.newBuildingCapacityT0
      newBuildingCapacityT0 = +newBuildingCapacityT0 < 0 ? '0' : newBuildingCapacityT0
      return this.$_out(this.$_withCommaInteger(newBuildingCapacityT0))
    },
    newBuildingCapacityT1() {
      let newBuildingCapacityT1 = this.formModel.newBuildingCapacityT1
      newBuildingCapacityT1 = +newBuildingCapacityT1 < 0 ? '0' : newBuildingCapacityT1
      return this.$_out(this.$_withCommaInteger(newBuildingCapacityT1))
    },
    priceRow() {
      if (this.formModel.tradeCd === '4') {
        return this.formModel.priceSellLimitLow
      } else if (this.formModel.tradeCd === '3') {
        return this.formModel.priceBuyLimitLow
      } else {
        return ''
      }
    },
    priceHigh() {
      if (this.formModel.tradeCd === '4') {
        return this.formModel.priceSellLimitHigh
      } else if (this.formModel.tradeCd === '3') {
        return this.formModel.priceBuyLimitHigh
      } else {
        return ''
      }
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    // 市場：「当社優先市場/SOR」が選択されている場合、最上位上場市場を返す
    marketRequest() {
      if (this.formModel.market === 'A') {
        return this.upperRankMkt
      } else {
        return this.formModel.market
      }
    }
  },
  watch: {
    'formModel.oco1LimitExecutionConditionList'(newValue) {
      if (newValue === 'F') {
        this.formModel.oco2StopOrderExecuteMethodList = '1'
        this.oco2ChangeExcuteMathed(this.formModel.oco2OrderExecuteMethodList)
      }
    },
    // 期間の初期設定値を判定して反映
    // 執行方法・条件の変更による期間の変更を監視
    periodButtonOption: {
      deep: true,
      handler(newOption, oldOption) {
        this.setPeriodTypeInput(this.selectPeriodRadio())
      }
    },
    periodButtonOptionIfd2: {
      deep: true,
      handler(newOption, oldOption) {
        this.setPeriodTypeInputIfd2(this.selectPeriodRadioIfd2())
      }
    }
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('IfaMarginNewOrderInputA001').click()
      })
      this.$refs['ifaBrandSearch'].resetAll()
      this.$refs['ifaBrandPriceInfo'].initData()
      // 銘柄コードを初期化
      this.formModel.brandCode = ''
      // 画面を初期化
      this.resetAll()
      this.changeOrderFormat()
    },
    // 銘柄検索(子コンポーネント)からのイベント処理
    handleChangeBrand(brandInfo) {
      if (brandInfo.id === '1') {
        // [イベントID: 1] 銘柄が選択された
        this.formModel.brandCode = brandInfo.data.brandCode
        this.formModel.brandName = brandInfo.data.brandName
        this.formModel.market = brandInfo.data.selectMarketList[0].key
        this.formModel.ifd1LimitExecutionConditionList = ''
        this.formModel.ifd2LimitExecutionConditionList = ''
        this.formModel.oco1LimitExecutionConditionList = ''
        this.upperRankMkt = brandInfo.data.upperRankMkt // 最上位上場市場
        this.$refs['ifaBrandPriceInfo'].updateRequest()
        this.originalMarketList = brandInfo.data.selectMarketList
        this.changeMarketList(this.formModel.orderKind)
        this.$nextTick(() => {
          document.getElementById('IfaMarginNewOrderInputA002').click()
        })
      } else if (brandInfo.id === '2') {
        // [イベントID: 2] 市場が変更された
        this.formModel.market = brandInfo.data

        // 市場変更の際執行方法/執行条件を指値/条件なしに変更する。
        // ※ 注文種別 != 通常/逆指値の場合は、執行方法が制限される市場への変更ができないため
        // 　 IFD、OCO、IFDOCO注文は考慮不要。
        this.formModel.sasinariHouhou = '1' // 執行方法 = 指値
        this.formModel.sasinariJyouken = ' ' // 執行条件 = 条件なし(指値)

        //信用取引区分の初期化
        this.formModel.marginTradeTypeText = ''

        // 銘柄時価情報エリアをアップデート
        this.$refs['ifaBrandPriceInfo'].updateRequest()
        this.$nextTick(() => {
          document.getElementById('IfaMarginNewOrderInputA002').click()
        })
      } else if (brandInfo.id === '3') {
        // ×押下時（銘柄削除時）param.id === '3'を返すため、そのタイミングで時価情報のリセットと入力された値をリセットする。
        this.$refs['ifaBrandPriceInfo'].initData()
        this.formModel.brandCode = ''
        this.formModel.brandName = ''
        this.formModel.market = ''
        this.resetAll()
      }
    },
    // 通常/逆指値, OCO, IFD(IFD1), IFDOCO(IFD2)で表示する期間初期値設定
    selectPeriodRadio() {
      // periodTypeInput がnullのとき初期選択なし、falseのとき"当日中"
      let periodTypeInput = null
      // 注文種別＝OCOの場合
      if (this.formModel.orderKind === '2') {
        if (this.formModel.oco1OrderExecuteMethodList === '1' &&
          this.formModel.oco1LimitExecutionConditionList === 'F') {
          periodTypeInput = false
        }
      // 注文種別＝IFDまたはIFDOCOの場合
      } else if (this.formModel.orderKind === '3' || this.formModel.orderKind === '4') {
        if ((this.formModel.ifd1OrderExecuteMethodList === '1' && this.formModel.ifd1LimitExecutionConditionList !== ' ') || // 執行方法=指値　かつ　執行条件<>条件なし　または
          this.formModel.ifd1OrderExecuteMethodList === '2') {
          periodTypeInput = false
        }
      // 注文種別が上記以外（通常/逆指値）の場合
      } else {
        if (this.formModel.market === '7') {
          periodTypeInput = false
        } else if ((this.formModel.sasinariHouhou === '1' && this.formModel.sasinariJyouken !== ' ') ||
        (this.formModel.sasinariHouhou === '2')) {
          periodTypeInput = false
        }
      }
      return periodTypeInput
    },
    // IFD(IFD2), IFDOCO(IDF2)で表示する期間初期値設定
    selectPeriodRadioIfd2() {
      // periodTypeInputIfd2 がnullのとき初期選択なし、falseのとき"当日中"
      let periodTypeInputIfd2 = null
      // IFDOCOのときの条件
      if (this.formModel.orderKind === '4') {
        if (this.formModel.oco1OrderExecuteMethodList === '1' && this.formModel.oco1LimitExecutionConditionList === 'F') {
          periodTypeInputIfd2 = false
        }
      // IFDOCO以外（IFD）のときの条件
      } else {
        if ((this.formModel.market === '7') ||
        (this.formModel.ifd2OrderExecuteMethodList === '1' && this.formModel.ifd2LimitExecutionConditionList !== ' ') ||
        (this.formModel.ifd2OrderExecuteMethodList === '2')) {
          periodTypeInputIfd2 = false
        }
      }
      return periodTypeInputIfd2
    },
    // 銘柄時価情報(子コンポーネント)からのイベント処理
    handleChangeBrandPrice(priceInfo) {
      this.priceInfo = priceInfo
      this.formModel.unit = priceInfo.unit
      this.formModel.priceBuyLimitLow = priceInfo.buyStopLow
      this.formModel.priceBuyLimitHigh = priceInfo.buyStopHigh
      this.formModel.priceLimitDate = priceInfo.baseDate
      this.formModel.priceSellLimitLow = priceInfo.sellStopLow
      this.formModel.priceSellLimitHigh = priceInfo.sellStopHigh
    },
    getMarginTradeTypeText() {
      this.formModel.marginTradeTypeText = '6'
      return this.$_getCodeValue('PAYMENT_DEADLINE', 1, this.formModel.marginTradeTypeText)
    },
    getMarginTradeTypeNoLimitText() {
      this.formModel.marginTradeTypeText = '9'
      return this.$_getCodeValue('PAYMENT_DEADLINE', 1, this.formModel.marginTradeTypeText)
    },
    responseHandlerA002(response) {
      this.formModel = Object.assign(this.formModel, response.dataList[0])
    },
    responseHandlerA004(response) {
      this.detailInfo = Object.assign(this.detailInfo, response.dataList[0])
      this.displayStockBoard = true
    },
    responseHandlerA005(response) {
      this.formModel = Object.assign(this.formModel, response.dataList[0])
      this.$refs['ifaBrandPriceInfo'].updateRequest()
      this.changeTradeType(this.formModel.tradeCd)
    },
    sixMonthDisp() {
      // 通常/逆指値.取引種別="信用新規買" 
      if(this.formModel.tradeCd === '3'){
        //市場=東証
        if(this.formModel.market === '0'){
            //東証一般信用区分≠"1"（一般信用不可銘柄）かつ 名証一般信用区分≠"1"（一般信用不可銘柄）かつ 福証一般信用区分≠"1"（一般信用不可銘柄）かつ 札証一般信用区分≠"1"（一般信用不可銘柄）
            if(this.formModel.mktIppanLoanKbnTky !== '1' && this.formModel.mktIppanLoanKbnNgy !== '1' && this.formModel.mktIppanLoanKbnFko !== '1' && this.formModel.mktIppanLoanKbnSpr !== '1'){
              return true
            }
          //市場=PTS
        } else if(this.formModel.market === '7'){
            //(PTS貸借区分="1"（貸借・制度信用銘柄）または "2"（制度信用銘柄）) かつ PTS一般信用区分≠"1"（一般信用不可銘柄）
            if((this.formModel.mktLoanKbnPts === '1' || this.formModel.mktLoanKbnPts === '2') && this.formModel.mktIppanLoanKbnPts !== '1'){
              return true
            }
          //市場=当社優先市場/SOR
        } else if(this.formModel.market === 'A'){
            //(東証貸借区分="1"（貸借・制度信用銘柄）または "2"（制度信用銘柄）) かつ 東証一般信用区分≠"1"（一般信用不可銘柄）
            if((this.formModel.mktLoanKbnTky === '1' || this.formModel.mktLoanKbnTky === '2') && this.formModel.mktIppanLoanKbnTky !== '1'){
              return true
            }
        }
        //通常/逆指値.取引種別="信用新規売"
      } else if(this.formModel.tradeCd === '4'){
        return true
      }
      // 上記以外 項目非表示
      return false
    },
    noLimitDisp() {
      // 通常/逆指値.取引種別="信用新規買" 
      if(this.formModel.tradeCd === '3'){
        //市場=PTS
        if(this.formModel.market === '7'){
            //PTS一般信用区分="1"（一般信用銘柄）かつ PTS貸借区分＝"1"（貸借・制度信用銘柄）および "2"（制度信用銘柄）以外
            if(this.formModel.mktIppanLoanKbnPts === '1' && this.formModel.mktLoanKbnPts !== '1' && this.formModel.mktLoanKbnPts !== '2'){
              return true
            }
          //市場=当社優先市場/SOR
        } else if(this.formModel.market === 'A'){
            //東証一般信用区分="1"（一般信用銘柄）かつ 東証貸借区分＝"1"（貸借・制度信用銘柄）および "2"（制度信用銘柄）以外
            if(this.formModel.mktIppanLoanKbnTky === '1' && this.formModel.mktLoanKbnTky !== '1' && this.formModel.mktLoanKbnTky !== '2'){
              return true
            }
        }
      }
      // 上記以外 項目非表示
      return false
    },
    tradeKbnDisp() {
      // 通常/逆指値.取引種別="信用新規買" 
      if(this.formModel.tradeCd === '3'){
        //市場=東証
        if(this.formModel.market === '0'){
            //東証一般信用区分＝"1"（一般信用銘柄）または 名証一般信用区分＝"1"（一般信用銘柄）または 福証一般信用区分＝"1"（一般信用銘柄）または 札証一般信用区分＝"1"（一般信用銘柄）
            if(this.formModel.mktIppanLoanKbnTky == '1' || this.formModel.mktIppanLoanKbnNgy == '1' || this.formModel.mktIppanLoanKbnFko == '1' || this.formModel.mktIppanLoanKbnSpr == '1'){
              return true
            }
          //市場=PTS
        } else if(this.formModel.market === '7'){
            //（PTS貸借区分＝"1"(貸借・制度信用銘柄）または "2"（制度信用銘柄））かつ PTS一般信用区分＝"1"（一般信用銘柄）
            if((this.formModel.mktLoanKbnPts === '1' || this.formModel.mktLoanKbnPts === '2') && this.formModel.mktIppanLoanKbnPts === '1'){
              return true
            }
          //市場=当社優先市場/SOR
        } else if(this.formModel.market === 'A'){
            //(東証貸借区分＝"1"（貸借・制度信用銘柄）または "2"（制度信用銘柄）) かつ 東証一般信用区分＝"1"（一般信用銘柄）
            if((this.formModel.mktLoanKbnTky === '1' || this.formModel.mktLoanKbnTky === '2') && this.formModel.mktIppanLoanKbnTky === '1'){
              return true
            }
        }
      }
      // 上記以外 項目非表示
      return false
    },
    // 初期処理
    responseHandlerInitializeA001(response) {
      this.formModel = Object.assign(this.formModel, response.dataList[0])
    },
    preRequestHandlerA016() {
      if (this.formModel.orderKind === '2' || this.formModel.orderKind === '4') {
        if (this.formModel.oco2StopOrderExecuteMethodList === '1') {
          this.IfaMarginNewOrderInputA016RequestModel.oco2DomesticLimitPrice = this.formModel.oco2DomesticLimitPrice
        } else {
          this.IfaMarginNewOrderInputA016RequestModel.oco2DomesticLimitPrice = ''
        }
      } else {
        this.IfaMarginNewOrderInputA016RequestModel.oco2DomesticLimitPrice = ''
      }
    },
    // A016正常終了
    responseHandlerA016(response) {
      this.confirmFormModel = Object.assign(this.confirmFormModel, response.dataList[0])
      this.$nextTick(() => {
        this.$refs['IfaMarginNewOrderConfirm'].setData()
        this.dialogConfirmVisible = true
      })
    },
    // 注文完了画面に遷移
    handleOrderFinish(params) {
      this.completeFormModel = Object.assign(this.completeFormModel, params)
      this.dialogConfirmVisible = false
      this.dialogCompleteVisible = true
    },
    // 注文状況一覧画面に遷移
    handleMoveToOrderList() {
      this.resetAll()
      this.dialogConfirmVisible = false
      this.dialogCompleteVisible = false
      // 注文状況一覧画面に遷移
      this.$_startShowMenu('SUB0202_0104')
    },
    // 注文完了画面に遷移
    async handleCloseModal() {
      this.dialogConfirmVisible = false
      this.dialogCompleteVisible = false
    },
    // 取引種別が選択/変更された
    changeTradeType(value) {
      if (
        value === '4' || (value === '3' &&
        (this.formModel.mktIppanLoanKbnTky === ' ' &&
        this.formModel.mktIppanLoanKbnNgy === ' ' &&
        this.formModel.mktIppanLoanKbnFko === ' ' &&
        this.formModel.mktIppanLoanKbnSpr === ' '))) {
        this.formModel.marginTradeTypeText = '6'
      }
      // 注文種別が IFD または IFDOCO の場合
      if (this.formModel.orderKind === '3' || this.formModel.orderKind === '4') {
        return
      }

      // 取引種別が 信新買 の場合
      if (this.formModel.tradeCd === '3') {
        this.bgColor = '#fef0f0'
      // 取引種別が 信新売 の場合
      } else if (this.formModel.tradeCd === '4') {
        this.bgColor = '#ecf5ff'
      // 取引種別が選択されていないときはデフォルトの背景色とする
      } else if (this.formModel.tradeCd === '') {
        this.bgColor = '#fdf6ec'
      }
    },
    // 注文種別変更時処理
    changeOrderFormat(value) {
      this.changeMarketList(value)
      this.setBgColor(value)
      this.setOcoIndentStyle(value)
      this.formModel.sasinariHouhou = '1'
      this.formModel.ifd1OrderExecuteMethodList = '1'
      this.formModel.ifd2OrderExecuteMethodList = '1'
      this.formModel.oco1OrderExecuteMethodList = '1'
      this.formModel.oco2OrderExecuteMethodList = '3'
      this.formModel.oco2StopOrderExecuteMethodList = '1'
      this.changeExcuteMathed(this.formModel.sasinariHouhou)
      this.ifd1ChangeExcuteMathed(this.formModel.ifd1OrderExecuteMethodList)
      this.ifd2ChangeExcuteMathed(this.formModel.ifd2OrderExecuteMethodList)
      this.oco1ChangeExcuteMathed(this.formModel.oco1OrderExecuteMethodList)
      this.oco2ChangeExcuteMathed(this.formModel.oco2OrderExecuteMethodList)
    },
    changeMarketList(value) {
      // 注文種別に応じて、銘柄検索エリア.銘柄検索の市場リストを変更する。
      if (value === '1') {
        // 注文種別=通常/逆指値の場合:プロパティ.対象市場リスト=設定なし
        this.customMarketList = []
        this.$refs['ifaBrandSearch'].internalMarketList = this.originalMarketList
        // 初期化処理の時に、this.originalMarketList[0]がundefinedでエラーが出ないようにする
        if (this.originalMarketList.length > 0) {
          this.$refs['ifaBrandSearch'].selectedMarket = this.originalMarketList[0].key
          this.formModel.market = this.originalMarketList[0].key
        }
      } else if (value === '2' || value === '4') {
        // 注文種別=OCO　または　IFDOCOの場合:プロパティ.対象市場リスト=東証
        this.customMarketList = this.originalMarketList.filter(item => item.key === '0')
        this.$refs['ifaBrandSearch'].internalMarketList = this.customMarketList
        this.$refs['ifaBrandSearch'].selectedMarket = this.$refs['ifaBrandSearch'].internalMarketList[0].key
        this.formModel.market = this.$refs['ifaBrandSearch'].internalMarketList[0].key
      } else if (value === '3') {
        // 注文種別=IFDの場合:プロパティ.対象市場リスト=東証、名証、福証、札証
        this.customMarketList = this.originalMarketList.filter(item => item.key === '0' || item.key === '2' || item.key === '6' || item.key === '8')
        this.$refs['ifaBrandSearch'].internalMarketList = this.customMarketList
        this.$refs['ifaBrandSearch'].selectedMarket = this.$refs['ifaBrandSearch'].internalMarketList[0].key
        this.formModel.market = this.$refs['ifaBrandSearch'].internalMarketList[0].key
      }
    },
    // 背景色変更処理
    setBgColor(value) {
      if (value === '3' || value === '4') {
        this.bgColor = '#e7dcf2'
      } else {
        this.changeTradeType()
      }
    },
    // 注文種別変更時処理
    keepLimitOrderPrice(value) {
      // 入力値チェック対象の変更
      if (value === '1') {
        this.rules.price[0].required = true
        this.rules.triggerPrice[0].required = true
        this.rules.ifd1DomesticLimitPrice[0].required = false
        this.rules.ifd1DomesticStopOrderPrice[0].required = false
        this.rules.ifd2DomesticLimitPrice[0].required = false
        this.rules.ifd2DomesticStopOrderPrice[0].required = false
        this.rules.oco1DomesticLimitPrice[0].required = false
        this.rules.oco2DomesticLimitPrice[0].required = false
        this.rules.oco2DomesticStopOrderPrice[0].required = false
      } else if (value === '2') {
        this.rules.price[0].required = false
        this.rules.triggerPrice[0].required = false
        this.rules.ifd1DomesticLimitPrice[0].required = false
        this.rules.ifd1DomesticStopOrderPrice[0].required = false
        this.rules.ifd2DomesticLimitPrice[0].required = false
        this.rules.ifd2DomesticStopOrderPrice[0].required = false
        this.rules.oco1DomesticLimitPrice[0].required = true
        this.rules.oco2DomesticLimitPrice[0].required = true
        this.rules.oco2DomesticStopOrderPrice[0].required = true
      } else if (value === '3') {
        this.rules.price[0].required = false
        this.rules.triggerPrice[0].required = false
        this.rules.ifd1DomesticLimitPrice[0].required = true
        this.rules.ifd1DomesticStopOrderPrice[0].required = true
        this.rules.ifd2DomesticLimitPrice[0].required = true
        this.rules.ifd2DomesticStopOrderPrice[0].required = true
        this.rules.oco1DomesticLimitPrice[0].required = false
        this.rules.oco2DomesticLimitPrice[0].required = false
        this.rules.oco2DomesticStopOrderPrice[0].required = false
      } else if (value === '4') {
        this.rules.price[0].required = false
        this.rules.triggerPrice[0].required = false
        this.rules.ifd1DomesticLimitPrice[0].required = true
        this.rules.ifd1DomesticStopOrderPrice[0].required = true
        this.rules.ifd2DomesticLimitPrice[0].required = false
        this.rules.ifd2DomesticStopOrderPrice[0].required = false
        this.rules.oco1DomesticLimitPrice[0].required = true
        this.rules.oco2DomesticLimitPrice[0].required = true
        this.rules.oco2DomesticStopOrderPrice[0].required = true
      }
    },
    // ラベル表示変更処理
    setOcoIndentStyle(value) {
      if (value === '3') {
        // IFDOCO 選択時
        this.ocoIndentStyle = 'oco-indent-label'
      } else {
        // IFDOCO 選択時以外
        this.ocoIndentStyle = 'ifd-oco-label'
      }
    },
    // 板情報から価格を選択したときの処理
    handlePriceSelect(value) {
      this.displayStockBoard = false
    },
    // フォームリセット処理
    resetForm() {
      this.formModel.tradeCd = ''
      this.formModel.orderQuantity = ''
      this.formModel.sasinariHouhou = '1'
      this.formModel.sasinariJyouken = ' '
      this.formModel.price = ''
      this.formModel.triggerPrice = ''
      this.formModel.gyakusasiHouhou = '1'
      this.formModel.periodRadio = null
      this.formModel.limit = ''
      this.formModel.marginTradeTypeText = ''
      this.formModel.ifd1SasinariJyouken = ' '
      this.formModel.ifd1OrderExecuteMethodList = '1'
      this.formModel.ifd1LimitExecutionConditionList = ' '
      this.formModel.ifd1DomesticLimitPrice = ''
      this.formModel.ifd1DomesticStopOrderPrice = ''
      this.formModel.ifd1StopOrderExecuteMethodList = ''
      this.formModel.ifd2SasinariJyouken = ' '
      this.formModel.ifd2OrderExecuteMethodList = '1'
      this.formModel.ifd2LimitExecutionConditionList = ' '
      this.formModel.ifd2DomesticLimitPrice = ''
      this.formModel.ifd2DomesticStopOrderPrice = ''
      this.formModel.ifd2StopOrderExecuteMethodList = '1'
      this.formModel.ifd2Limit = ''
      this.formModel.ifd2PeriodRadio = null
      this.formModel.oco1SasinariJyouken = ' '
      this.formModel.oco1OrderExecuteMethodList = ''
      this.formModel.oco1LimitExecutionConditionList = ''
      this.formModel.oco1DomesticLimitPrice = ''
      this.formModel.oco1OrderExecuteMethodList = '1'
      this.formModel.oco2OrderExecuteMethodList = '3'
      this.formModel.oco2DomesticStopOrderPrice = ''
      this.formModel.oco2StopOrderExecuteMethodList = '1'
      this.formModel.oco2StopOrderMarketExecutionConditionList = ' '
      this.formModel.oco2DomesticLimitPrice = ''
      this.formModel.kanyuKbn = ''
      this.formModel.receiveOrderType = ''
      this.formModel.checkInsider = []
      this.formModel.checkSor = []
      this.$refs['formModel']?.clearValidate()
      this.changeTradeType(this.formModel.tradeCd)
    },
    // リセット処理
    // 初期表示時、リセットボタン押下時、銘柄削除時（初期表示に戻す）に呼び出す。
    resetAll() {
      // フォームをリセット
      this.resetForm()
      // 注文種別を初期化
      this.formModel.orderKind = '1'
      // 背景色を初期表示状態に設定
      this.bgColor = '#fdf6ec'
      // 期間のバリデーションをクリア
      // 項目が非表示の時はpropを読み込めないため、処理の対象外に
      if (this.$refs['periodSelector'] && (this.formModel.orderKind === '1' || this.formModel.orderKind === '2')) {
        this.$refs['periodSelector'].resetComponent()
      } else if (this.$refs['periodSelector']) {
        this.formModel.limit = ''
        this.formModel.periodRadio = null
      }
      if (this.$refs['periodSelector2'] && (this.formModel.orderKind === '3' || this.formModel.orderKind === '4')) {
        this.$refs['periodSelector2'].resetComponent()
      } else if (this.$refs['periodSelector2']) {
        this.formModel.limit = ''
        this.formModel.periodRadio = null
      }
      if (this.$refs['periodSelectorIfd2'] && (this.formModel.orderKind === '3' || this.formModel.orderKind === '4')) {
        this.$refs['periodSelectorIfd2'].resetComponent()
      } else if (this.$refs['periodSelectorIfd2']) {
        this.formModel.ifd2Limit = ''
        this.formModel.ifd2PeriodRadio = null
      }
      this.$nextTick(() => {
        this.changeMarketList(this.formModel.orderKind)
      })
    },
    changeExcuteMathed(value) {
      if (value === '2') {
        this.formModel.sasinariJyouken = 'N'
      } else {
        this.formModel.sasinariJyouken = ' '
      }
    },
    ifd1ChangeExcuteMathed(value) {
      if (this.formModel.market !== 'A' && this.formModel.market !== '7') {
        this.formModel.ifd1StopOrderExecuteMethodList = '1'
      }
      if (value === '2') {
        this.formModel.ifd1LimitExecutionConditionList = 'N'
      } else {
        this.formModel.ifd1LimitExecutionConditionList = ' '
      }
    },
    ifd1ChangeStopOrderExcuteMathed(value) {
      if (value === '2') {
        this.formModel.ifd1LimitExecutionConditionList = 'N'
      } else {
        this.formModel.ifd1LimitExecutionConditionList = ' '
      }
    },
    ifd2ChangeExcuteMathed(value) {
      this.formModel.ifd2StopOrderExecuteMethodList = '1'
      if (value === '2') {
        this.formModel.ifd2LimitExecutionConditionList = 'N'
      } else {
        this.formModel.ifd2LimitExecutionConditionList = ' '
      }
    },
    ifd2ChangeStopOrderExcuteMathed(value) {
      if (value === '2') {
        this.formModel.ifd2LimitExecutionConditionList = 'N'
      } else {
        this.formModel.ifd2LimitExecutionConditionList = ' '
      }
    },
    oco1ChangeExcuteMathed(value) {
      if (value === '2') {
        this.formModel.oco1LimitExecutionConditionList = 'N'
      } else {
        this.formModel.oco1LimitExecutionConditionList = ' '
      }
    },
    oco2ChangeExcuteMathed(value) {
      if (value === '2') {
        this.formModel.oco2StopOrderMarketExecutionConditionList = 'N'
      } else {
        this.formModel.oco2StopOrderMarketExecutionConditionList = ' '
      }
    },
    // その他注文内容のセット
    setOrderInfo() {
      orderInput.setOrderInfo(this)
    },
    formatToDecimal(value) {
      if (value.length > 2) {
        return value.slice(0, value.length - 2) + '.' + value.slice(value.length - 2, value.length)
      } else if (value.length === 2) {
        return '0.' + value
      } else if (value.length === 1) {
        return '0.0' + value
      }
      return value
    },
    // 期間の値を渡す
    setPeriod(value) {
      this.formModel.limit = value
    },
    setPeriod2(value) {
      this.formModel.ifd2Limit = value
    },
    // 品質改善指摘対応No.204
    maxValueValidator(value) {
      if (Number.isInteger(Number(value))) {
        return 9999999999
      } else {
        return 99999999.9
      }
    },
    // 品質改善指摘対応No.200
    responseErrorHandlerInitializeA001(data) {
      const errorInfo = {
        title: this.formModel.title,
        message: data.message
      }
      this.$emit('initializeError', errorInfo)
    },
    // 期間（periodSelector,periodSelector2）の初期設定値を反映
    setPeriodTypeInput(periodTypeInput) {
      if (periodTypeInput === null) {
      // 項目が非表示の時はpropを読み込めないため、処理の対象外に
        if (this.$refs['periodSelector'] && (this.formModel.orderKind === '1' || this.formModel.orderKind === '2')) {
          this.$refs['periodSelector'].resetComponent()
        } else if (this.$refs['periodSelector']) {
          this.formModel.limit = ''
          this.formModel.periodRadio = null
        }
        if (this.$refs['periodSelector2'] && (this.formModel.orderKind === '3' || this.formModel.orderKind === '4')) {
          this.$refs['periodSelector2'].resetComponent()
        } else if (this.$refs['periodSelector2']) {
          this.formModel.limit = ''
          this.formModel.periodRadio = null
        }
      } else {
        this.$nextTick(() => {
          if (this.$refs['periodSelector']) {
            this.$refs['periodSelector'].periodTypeInput = periodTypeInput
            this.$refs['periodSelector'].$emit('update:periodType', periodTypeInput)
          }
          if (this.$refs['periodSelector2']) {
            this.$refs['periodSelector2'].periodTypeInput = periodTypeInput
            this.$refs['periodSelector2'].$emit('update:periodType', periodTypeInput)
          }
        })
      }
    },
    // 期間（periodSelectorIfd2）の初期設定値を反映
    setPeriodTypeInputIfd2(periodTypeInput) {
      if (periodTypeInput === null) {
        if (this.$refs['periodSelectorIfd2'] && (this.formModel.orderKind === '3' || this.formModel.orderKind === '4')) {
          this.$refs['periodSelectorIfd2'].resetComponent()
        } else if (this.$refs['periodSelectorIfd2']) {
          this.formModel.ifd2Limit = ''
          this.formModel.ifd2PeriodRadio = null
        }
      } else {
        this.$nextTick(() => {
          if (this.$refs['periodSelectorIfd2']) {
            this.$refs['periodSelectorIfd2'].periodTypeInput = periodTypeInput
            this.$refs['periodSelectorIfd2'].$emit('update:periodType', periodTypeInput)
          }
        })
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import '@/styles/orderStatusList.scss';
:deep(.el-tabs_full_content) .el-tabs__content {
  padding: 0;
}
:deep(.el-form-item__content) .not-label {
  margin-left: -200px;
}
.not-label-div {
  margin-left: -180px;
}
:deep(.el-form-item__content) .label {
  margin-left: 0px;
}
.order-input {
  width: 80%;
}
.form-radio {
  width: 4rem;
}
.form-area__section {
  height: auto;
  margin: 0.2rem 0;
  padding-bottom: 0.2rem;
  border-bottom: 1px solid #eee;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-start;
  padding: 0 2rem 0.2rem 0;
}
.form-reset-button__wrapper {
  display: flex;
  width: 100%;
  justify-content: flex-end;
  margin: 0 0 0.5rem 0;
  padding-right: 1rem;
}
// .form-area__select {
//   width: 9rem;
// }
// .form-area__select-auto {
//   width: calc(100% - 1rem);
// }
.form-area__input-number {
  width: 18rem;
  margin-left: 0.1rem;
}
.confirm-dialog {
  background-color: rgb(254, 240, 240);
  width: 200px;
}
.amount-text {
  margin-left: 5.8rem;
}
.item {
  margin-left: 10px;
}
:deep(.charge-type) .el-form-item__label {
  color: #f00;
}
:deep(.label-style) .el-form-item__label {
  font-weight: bold;
}
.error-message {
  margin: 0.5rem;
  color: red;
  font-size: 14px;
  font-weight: 600;
}
.warning-message {
  margin: 0.5rem;
  padding-left: 4rem;
  color: red;
  font-size: 14px;
}
.info-message {
  margin: 0.5rem;
  padding-left: 4rem;
  color: black;
  font-size: 14px;
}
.ifd-oco-label {
  margin-left: 1rem;
  vertical-align: middle;
  font-weight: bold;
}
.oco-indent-label {
  @extend .ifd-oco-label;
  margin-left: 2rem;
}
.form-container {
  display: flex;
  justify-content: space-between;
}
:deep(.el-table--mini) td, .el-table--mini th {
  padding: 0px;
  height: 5px;
}
:deep(.el-table) td, .el-table th {
  padding: 0px;
  height: 5px;
}
:deep(.el-form-item__error) {
  width: max-content;
}
:deep(.el-form-item__label) {
  margin-left: 0.5rem;
}
:deep(.el-form-item__content) {
  width: 70%;
}
.info-item__header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
}
.info-item__value {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
.label-left {
  margin-left: -9px;
}
.label-btn {
  padding-left: 1rem;
}
.validation-error-width {
  :deep(.el-form-item__error) {
    --ifa-input-validation-error-width: 400px;
  }
}
.marginTradeTypeTextField {
  :deep(.el-form-item) {
   align-items: center;
  }
}
:deep(.date-picker) .el-form-item__content {
  display: flex;
}
</style>
