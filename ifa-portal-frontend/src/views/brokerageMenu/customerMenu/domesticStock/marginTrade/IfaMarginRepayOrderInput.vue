<template>
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="caption_card">
      <el-card
        style="background-color: #eee; margin-bottom: 0.5rem;"
      >
        <el-row
          style="display: flex; padding: 0 1rem"
        >
          <el-col :span="19">
            <!-- 銘柄検索 -->
            <ifa-brand-search
              ref="ifaMarginRepayOrderInputBrandSearch"
              :is-code-lock="true"
              :trading-type="'1'"
              :market-list="customMarketList"
              @change="handleChange"
            ></ifa-brand-search>
          </el-col>
          <el-col
            :span="4"
            class="update-button"
            style="margin-left: 0;"
          >
            <ifa-button
              text="詳細"
              icon="Document"
              small
              action-id="SUB0202_0208-02#A001"
              action-type="requestAction"
              :request-model="a003RequestModel"
              @response-handler="detailDisplayA003"
            ></ifa-button>
            <ifa-button
              id="btnUpdate"
              text="更新"
              icon="RefreshRight"
              small
              action-type="requestAction"
              action-id="SUB0202_0212-04_1#A004"
              :request-model="a004RequestModel"
              @response-handler="updateA004"
            ></ifa-button>
          </el-col>
        </el-row>
        <!-- 時価情報 ※ marketRequest については不具合管理#2527対応取り込み-->
        <ifa-brand-price-info
          ref="IfaMarginRepayOrderInputBrandPriceInfo"
          :brand-code="form.brandCode"
          :market="marketRequest"
          @change="handleChangeBrandPrice"
        ></ifa-brand-price-info>
      </el-card>

      <el-row>
        <!-- 通常注文タブ -->
        <el-card
          class="box-card"
          :style="{'background-color': bgColor}"
        >
          <el-form
            ref="form"
            :model="form"
            :rules="rules"
            label-width="220px"
            label-position="left"
          >

            <el-row :gutter="20">
              <div v-if="form.orderKind=='1' || form.orderKind=='2'">

                <!-- フォーム: 取引種別 -->
                <el-row
                  style="padding-top: 0.5rem;"
                  class="form-area__section label-left"
                >
                  <el-col :span="18">
                    <el-form-item
                      label="取引種別"
                      prop="tradeType"
                    >
                      <span v-if="form.tradeCd">
                        <ifa-text
                          code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                          :style="{'color': fontColor}"
                          style="font-weight:bold"
                          :code-key="form.tradeCd"
                          :disp-pattern="'1'"
                        ></ifa-text>
                      </span>
                      <span v-else>-</span>
                    </el-form-item>
                  </el-col>

                  <!-- リセットボタン -->
                  <el-col
                    :span="6"
                    class="form-reset-button__wrapper"
                  >
                    <ifa-button
                      text="リセット"
                      color="secondary"
                      tabindex="-1"
                      action-type="originalAction"
                      @app-action-handler="isA005=true, resetForm()"
                    ></ifa-button>
                  </el-col>
                </el-row>

                <!-- 売却可能数量 -->
                <div class="form-area__section label-left">
                  <el-form-item label="注文可能数量">
                    <span>
                      <span style="margin-left:0">{{ form.maxOrderableQuantity ? `${$_out($_withCommaInteger(form.maxOrderableQuantity))}`: '0' }}株</span>
                    </span>
                  </el-form-item>
                </div>

                <!-- フォーム: 数量 -->
                <el-row v-if="form.repayMethod === '2'"
                        style="margin: 0.5rem 0 0 0;"
                        class="label-left"
                >
                  <div>
                    <ifa-input-quantity
                      v-model="form.quantity"
                      label="数量"
                      prop="quantity"
                      :min="1"
                      :max="99999999"
                      :support="true"
                      :step="brandInfo.unit"
                      :placeholder="' '"
                      :initial-step="form.maxOrderableQuantity ? form.maxOrderableQuantity : (brandInfo.unit ? brandInfo.unit : 0)"
                      :domain="IfaStocks8DomainModel"
                      step-strictly
                      unit="株"
                    ></ifa-input-quantity>
                  </div>
                  <el-col
                    :span="5"
                    style="margin-top:0.5rem"
                  >
                    <span
                      style="margin-left: 3rem;"
                    >売買単位：{{ $_out(brandInfo.unit) }}株</span>
                  </el-col>
                </el-row>

                <div
                  v-if="form.repayMethod !== '2'"
                  style="margin: 0.5rem 0 0 0;display: inline-flex;"
                  class="label-left"
                >
                  <el-form-item
                    label="数量"
                    style="width:50rem"
                  >
                    <span>
                      {{ form.totalQuantity ? `${$_out($_withCommaInteger(form.totalQuantity))}`: '0' }}株
                    </span>
                    <!-- TODO: SUB0202_0212-05_1_信用一括返済入力への遷移処理未対応 -->
                    <ifa-button
                      text="建玉指定"
                      color="primary"
                      style="margin-left:1.5rem"
                      small
                      action-type="originalAction"
                      @app-action-handler="showOpenInterestBulkRepaymentA008()"
                    ></ifa-button>
                  </el-form-item>
                </div>
                <!-- フォーム: 建玉指定 -->
                <div
                  v-if="form.repayMethod !== '2'"
                  class="form-area__section"
                  style="margin-left:35px"
                >
                  <el-form-item>
                    <div>
                      <span style="margin-left:0.8rem">返済方法：{{ $_out($_getCodeValue('REPAY_METHOD', 1, form.repayMethod)) }}</span>
                      <span
                        v-if="form.repayMethod !== '2'"
                        style="margin-left:4rem"
                      >返済順序：{{ $_out($_getCodeValue('REPAY_ORDER', 1, form.repaymentOrder)) }}</span>
                      <ifa-open-interest-repayment-list
                        :table-data="form.repayPositionDetailList"
                        style="margin-left: 0.8rem;"
                      ></ifa-open-interest-repayment-list>
                    </div>
                  </el-form-item>
                </div>

                <!-- フォーム: 価格/執行方法(注文種別が OCO 以外の時に表示) -->
                <div v-if="form.orderKind === '1'">
                  <el-row
                    style="padding-top: 0.5rem;"
                  >
                    <div class="label-left">
                      <!-- No.32 -->
                      <!-- フォーム: 価格/執行方法 -->
                      <ifa-input-select
                        v-model="form.sasinariHouhou"
                        prop="sasinariHouhou"
                        label="価格/執行方法"
                        code-list-id="EXECUTE_METHOD"
                        style="width: 200px;"
                        :disp-pattern="1"
                        :select-pattern="form.selectedMarket === 'A' ? 2 : (form.selectedMarket === '7' ? 3 : 1)"
                        @change="changeExcuteMethod"
                      ></ifa-input-select>
                    </div>
                    <div
                      v-if="form.sasinariHouhou == '1'"
                    >
                      <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                      <ifa-input-select
                        v-model="form.sasinariJyouken"
                        code-list-id="LIMIT_MARKET_TYPE"
                        style="margin-left: 1rem; width: 200px;"
                        :disp-pattern="2"
                        :select-pattern="form.selectedMarket === 'A' || form.selectedMarket === '7' ? 7 : 2"
                      ></ifa-input-select>
                    </div>
                    <!-- No.35 -->
                    <div
                      v-if="form.sasinariHouhou == '1'"
                      style="margin-left: 1rem;"
                    >
                      <ifa-input-price
                        v-model="form.price"
                        style="width: 200px;"
                        prop="price"
                        :min="1"
                        :max="maxValueValidator"
                        :initial-step="initialStep"
                        :support="true"
                        :domain="IfaUnitPriceYen101DigitsCDomainModel"
                        :step-table="brandInfo.orderPriceUnit"
                        step-strictly
                        unit="円"
                        size="small"
                      ></ifa-input-price>
                    </div>

                    <!-- フォーム: 成行選択時の条件設定 @価格/執行方法 -->
                    <!-- No.39 -->
                    <el-col
                      v-if="form.sasinariHouhou == '2'"
                      :span="4"
                      style="margin-left: 1rem;"
                    >
                      <ifa-input-select
                        v-model="form.sasinariJyouken"
                        :code-list-id="'LIMIT_MARKET_TYPE'"
                        :disp-pattern="3"
                        style="width:200px"
                        :select-pattern="form.selectedMarket == 'A' ? 8 : 3"
                      ></ifa-input-select>
                    </el-col>

                    <!-- フォーム: 逆指値選択時の条件設定 @価格/執行方法 -->
                    <!-- No.41 -->
                    <el-col
                      v-if="form.sasinariHouhou == '3'"
                      :span="2"
                      style="padding-top: 15px; margin-left: 1rem;"
                    >
                      現在値が
                    </el-col>
                    <div
                      v-if="form.sasinariHouhou == '3'"
                      style="margin-left: 0.5rem;width:305px;"
                    >
                      <!-- No.42 -->
                      <ifa-input-price
                        v-model="form.triggerPrice"
                        prop="triggerPrice"
                        :min="1"
                        :max="maxValueValidator"
                        :digit="1"
                        :domain="IfaUnitPriceYen101DigitsCDomainModel"
                        :step-table="brandInfo.orderPriceUnit"
                        :initial-step="initialStep"
                        unit="円"
                        :support="true"
                        size="small"
                      ></ifa-input-price>
                    </div>
                    <!-- No.43 -->
                    <div
                      v-if="form.sasinariHouhou == '3'"
                      style="padding: 15px;"
                    >
                      {{ form.tradeCd == '5' ? '以上' : '以下' }} になった時点で
                    </div>
                  </el-row>

                  <!-- フォーム: 執行方法選択 @価格/執行方法(逆指値) -->
                  <el-row
                    v-if="form.sasinariHouhou == '3'"
                    style="margin-top: 0.5rem; flex-wrap: nowrap;"
                  >
                    <el-col
                      :span="4"
                      style="margin-left: 450px"
                    >
                      <!-- No.44 -->
                      <ifa-input-select
                        v-model="form.gyakusasiHouhou"
                        style="width: 200px;"
                        :code-list-id="'EXECUTE_METHOD'"
                        :disp-pattern="1"
                        :select-pattern="2"
                        @change="changeExcuteMethod"
                      ></ifa-input-select>
                    </el-col>
                    <el-col
                      :span="4"
                    >
                      <!-- No.46 -->
                      <ifa-input-select
                        v-if="form.gyakusasiHouhou == '2'"
                        v-model="form.sasinariJyouken"
                        style="width: 200px;"
                        :code-list-id="'LIMIT_MARKET_TYPE'"
                        :disp-pattern="3"
                        :select-pattern="5"
                      ></ifa-input-select>
                      <!-- No.45 -->
                      <ifa-input-select
                        v-if="form.gyakusasiHouhou == '1'"
                        v-model="form.sasinariJyouken"
                        style="width: 200px;"
                        :code-list-id="'LIMIT_MARKET_TYPE'"
                        :disp-pattern="2"
                        :select-pattern="4"
                      ></ifa-input-select>
                    </el-col>
                    <el-col
                      v-if="form.gyakusasiHouhou == '1'"
                      :span="5"
                    >
                      <!-- No.47 -->
                      <ifa-input-price
                        v-model="form.price"
                        prop="price"
                        :min="1"
                        :max="maxValueValidator"
                        :digit="1"
                        :support="true"
                        :domain="IfaUnitPriceYen101DigitsCDomainModel"
                        :step-table="brandInfo.orderPriceUnit"
                        :initial-step="initialStep"
                        unit="円"
                        size="small"
                      ></ifa-input-price>
                    </el-col>
                    <!-- No.50 -->
                    <el-col
                      :span="1"
                      style="padding-top: 15px;"
                    >
                      で執行
                    </el-col>
                  </el-row>

                  <!-- No.36,37 -->
                  <el-row
                    v-if="(priceRow == '1.0' && priceHigh == '9999999999999.9')"
                    style="padding-top: 0.5rem;"
                    class="limit-label"
                  >
                    <el-col
                      v-if="form.sasinariHouhou == '3'"
                      :span="8"
                      style="margin-left: 450px"
                    ></el-col>
                    <el-col
                      :span="6"
                      style="padding: 1rem 0 0 0;"
                      :style="form.sasinariHouhou == '3' ? '' : 'margin-left: 700px;'"
                    >
                      <span>制限値幅：なし</span>
                    </el-col>
                  </el-row>
                  <el-row
                    v-else
                    style="padding-top: 0.5rem;"
                    class="limit-label"
                  >
                    <el-col
                      v-if="form.sasinariHouhou == '3'"
                      :span="8"
                      style="margin-left: 450px"
                    ></el-col>
                    <el-col
                      :span="6"
                      style="padding: 1rem 0 0 0;"
                      :style="form.sasinariHouhou == '3' ? '' : 'margin-left: 700px;'"
                    >
                      <span>制限値幅：{{ $_out($_withCommaZeroPadding(priceRow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円 ({{ $_out($_getFormattedDateValue(brandInfo.baseDate, 'date6')) }})</span>
                    </el-col>
                  </el-row>
                </div>

                <!-- フォーム: 注文種別 -->
                <!-- No.52 -->
                <el-row
                  v-if="form.orderKind === '1'"
                  style="padding-top: 0.5rem;"
                  class="label-left"
                >
                  <ifa-input-select
                    v-model="form.orderKind"
                    label="注文種別"
                    prop="orderKind"
                    :code-list-id="'ORDER_CLASS'"
                    :disp-pattern="1"
                    :select-pattern="2"
                    style="width: 200px;"
                    @change="changeOrderFormat"
                  ></ifa-input-select>
                </el-row>

                <!-- フォーム: 期間指定 -->
                <el-row
                  style="padding-top: 0.5rem;"
                  class="form-area__section"
                >
                  <el-col :span="form.selectedMarket == '7' ? 10 : 16"
                          class="label-left"
                  >
                    <div style="width: 680px;">
                      <ifa-period-selector
                        ref="periodSelector"
                        v-model:period="form.limit"
                        v-model:period-type="periodType"
                        label="注文期間"
                        prop="periodTerms"
                        :required="true"
                        :button-options="periodButtonOption"
                        :picker-options="dateOptions"
                        class="date-picker"
                        @update:period-type="setPeriodRadio($event)"
                      ></ifa-period-selector>
                    </div>
                  </el-col>
                  <el-col
                    v-if="form.selectedMarket === '7'"
                    :span="12"
                    style="color:red; padding: 15px;"
                  >※PTSでの信用取引の場合、前場から後場への注文は引き継がれません。
                  </el-col>
                </el-row>

                <!-- No.57 -->
                <!-- フォーム: 信用取引区分 -->
                <el-row style="padding-top: 0.5rem;">
                  <el-col class="label-left">
                    <div
                      class="form-area__section"
                    >
                      <el-form-item
                        label="信用取引区分"
                        class="form_label"
                      >
                        <span>{{ $_out(form.paymentDeadlineCalculation) }}</span>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>

                <!-- No.59 -->
                <!-- フォーム: 返済方法 -->
                <el-row
                  v-if="form.repayMethod === '2'"
                  style="padding-top: 0.5rem;"
                >
                  <el-col class="label-left">
                    <div
                      class="form-area__section"
                    >
                      <el-form-item
                        label="返済方法"
                        class="form_label"
                      >
                        <span>{{ $_out($_getCodeValue('REPAY_METHOD', 1, form.repayMethod)) }}</span>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>

                <!-- No.60 -->
                <!-- フォーム: 建市場 -->
                <el-row
                  v-if="form.repayMethod === '2'"
                  style="padding-top: 0.5rem;"
                >
                  <el-col class="label-left">
                    <div
                      class="form-area__section"
                    >
                      <el-form-item
                        label="建市場"
                        class="form_label"
                      >
                        <span>{{ $_out($_getCodeValue('NEW_MARKET', 1, form.repayPositionDetailList[0].builtMarket)) }}</span>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>

                <!-- No.61 -->
                <!-- フォーム: 新規建日 -->
                <el-row
                  v-if="form.repayMethod === '2'"
                  style="padding-top: 0.5rem;"
                >
                  <el-col class="label-left">
                    <div
                      class="form-area__section"
                    >
                      <el-form-item
                        label="新規建日"
                        class="form_label"
                      >
                        <span>{{ form.repayPositionDetailList[0].constructionDate ? $_getFormattedDateValue(form.repayPositionDetailList[0].constructionDate) : '----/--/--' }}</span>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>

                <!-- No.62 -->
                <!-- フォーム: 親株新規約定日 -->
                <el-row
                  v-if="form.repayMethod === '2'"
                  style="padding-top: 0.5rem;"
                >
                  <el-col class="label-left">
                    <div
                      class="form-area__section"
                    >
                      <el-form-item
                        label="親株新規約定日"
                        class="form_label"
                      >
                        <span>{{ form.repayPositionDetailList[0].parentStockTradeDate ? $_getFormattedDateValue(form.repayPositionDetailList[0].parentStockTradeDate) : '----/--/--' }}</span>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>

                <!-- No.63 -->
                <!-- フォーム: 建単価 -->
                <el-row
                  v-if="form.repayMethod === '2'"
                  style="padding-top: 0.5rem;"
                >
                  <el-col class="label-left">
                    <div
                      class="form-area__section"
                    >
                      <el-form-item
                        label="建単価"
                        class="form_label"
                      >
                        <span>{{ form.repayPositionDetailList[0].newPrice ? `${$_out($_withCommaNoneZeroPadding(form.repayPositionDetailList[0].newPrice, 2))}円`: '-' }}</span>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>

                <!-- No.64 -->
                <!-- フォーム: 建玉No -->
                <el-row
                  v-if="form.repayMethod === '2'"
                  style="padding-top: 0.5rem;"
                >
                  <el-col class="label-left">
                    <div
                      class="form-area__section"
                    >
                      <el-form-item
                        label="建玉No"
                        class="form_label"
                      >
                        <span>{{ form.repayPositionDetailList[0].positionNo ? $_zeroPadding(form.repayPositionDetailList[0].positionNo, 5) : '-' }}</span>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>

                <!-- No.83 -->
                <!-- フォーム: 手数料区分 -->
                <el-row style="padding-top: 0.5rem;">
                  <el-col class="label-left">
                    <div
                      class="form-area__section"
                    >
                      <el-form-item
                        label="手数料区分"
                        class="charge-type form_label"
                      >
                        <span>{{ $_out($_getCodeValue('PRE_CONTRACT_DOC_CODE', 1, customerInfo.customerAttribute)) }}（電話手数料）</span>
                      </el-form-item>
                    </div>
                  </el-col>
                </el-row>
                <!-- No.84 -->
                <!-- フォーム: 勧誘区分 -->
                <el-row style="padding-top: 0.5rem;">
                  <el-col class="label-left">
                    <div class="form-area__section">
                      <ifa-input-select
                        v-model="form.kanyuKbn"
                        prop="kanyuKbn"
                        label="勧誘区分"
                        style="width: 200px;"
                        :code-list-id="'INVITATION_TYPE'"
                      ></ifa-input-select>
                    </div>
                  </el-col>
                </el-row>
                <!-- No.85 -->
                <!-- フォーム: 受注方法 -->
                <el-row style="padding-top: 0.5rem;">
                  <el-col class="label-left">
                    <div>
                      <ifa-input-select
                        v-model="form.orderMethod"
                        prop="orderMethod"
                        label="受注方法"
                        style="width: 200px;"
                        :code-list-id="'ORDER_METHOD_TYPE'"
                      >
                      </ifa-input-select>
                    </div>
                  </el-col>
                </el-row>
              </div>

              <!-- OCO2（注文種別が OCO の時のみ表示） -->
              <div
                v-if="form.orderKind =='2' || form.orderKind =='4'"
                class="oco1_section"
              >
                <div :style="styleSeparator">
                  <span :class="ocoIndentStyle">OCO1</span>
                </div>
                <el-row style="padding-top: 0.5rem;">
                  <div class="label-left">
                    <!-- No.69 -->
                    <!-- フォーム: 執行方法選択 OCO1執行方法 -->
                    <ifa-input-select
                      v-model="form.oco1SasinariHouhou"
                      prop="oco1SasinariHouhou"
                      label="価格/執行方法"
                      :code-list-id="'EXECUTE_METHOD'"
                      :disp-pattern="1"
                      :select-pattern="3"
                      style="width: 200px;"
                      @change="oco1ChangeExcuteMethod"
                    >
                    </ifa-input-select>
                  </div>
                  <!-- No.71 -->
                  <div>
                    <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                    <ifa-input-select
                      v-model="form.oco1SasinariJyouken"
                      :code-list-id="'LIMIT_MARKET_TYPE'"
                      :disp-pattern="2"
                      :select-pattern="6"
                      style="margin-left: 1rem; width: 200px;"
                      @change="oco1ChangeSasinariJyouken"
                    >
                    </ifa-input-select>
                  </div>
                  <div
                    style="margin-left: 1rem;"
                  >
                    <ifa-input-price
                      v-model="form.oco1Price"
                      style="width: 200px;"
                      prop="oco1Price"
                      :step-table="brandInfo.orderPriceUnit"
                      :initial-step="initialStep"
                      :min="1"
                      :max="maxValueValidator"
                      :digit="1"
                      :support="true"
                      :domain="IfaUnitPriceYen101DigitsCDomainModel"
                      unit="円"
                      size="small"
                    ></ifa-input-price>
                  </div>
                </el-row>
                <el-row
                  v-if="(priceRow == '1.0' && priceHigh == '9999999999999.9')"
                  style="padding-top: 0.5rem;"
                  class="limit-labal"
                >
                  <el-col
                    :span="6"
                    style="padding: 0.8rem 0 0 0; margin-left: 700px;"
                  >
                    <span>制限値幅：なし</span>
                  </el-col>
                </el-row>
                <el-row
                  v-else
                  style="padding-top: 0.5rem;"
                  class="limit-labal"
                >
                  <el-col
                    :span="6"
                    style="padding: 0.8rem 0 0 0; margin-left: 700px;"
                  >
                    <span>制限値幅：{{ $_out($_withCommaZeroPadding(priceRow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円 ({{ $_out($_getFormattedDateValue(form.priceLimitDate, 'date6')) }})</span>
                  </el-col>
                </el-row>

                <!-- フォーム: 注文種別 OCOの時のみ表示-->
                <!-- No.72 -->
                <el-row
                  v-if="form.orderKind == '2'"
                  style="padding-top: 0.5rem;"
                  class="label-left"
                >
                  <ifa-input-select
                    v-model="form.orderKind"
                    label="注文種別"
                    prop="orderKind"
                    :code-list-id="'ORDER_CLASS'"
                    :disp-pattern="1"
                    :select-pattern="2"
                    style="width: 200px;"
                    @change="changeOrderFormat"
                  ></ifa-input-select>
                </el-row>

                <!-- OCO2（注文種別が OCO の時のみ表示） -->
                <div :style="styleSeparator">
                  <span :class="ocoIndentStyle">OCO2</span>
                </div>
                <el-row style="padding-top: 0.5rem;">
                  <div class="label-left">
                    <!-- No.76 -->
                    <!-- フォーム: 執行方法選択 PTSの場合 @価格/執行方法 -->
                    <ifa-input-select
                      v-model="form.oco2OrderExecuteMethodList"
                      prop="oco2OrderExecuteMethodList"
                      label="価格/執行方法"
                      :code-list-id="'EXECUTE_METHOD'"
                      :disp-pattern="1"
                      :select-pattern="5"
                      style="width: 200px;"
                      @change="oco2ChangeExcuteMethod"
                    ></ifa-input-select>
                  </div>
                  <!-- フォーム: 逆指値選択時の条件設定 @価格/執行方法 -->
                  <el-col
                    style="padding-top: 15px; margin-left: 1rem;"
                    :span="2"
                  >
                    現在値が
                  </el-col>
                  <div
                    style="margin-left: 0.5rem;width:305px;"
                  >
                    <ifa-input-price
                      v-model="form.oco2TriggerPrice"
                      prop="oco2TriggerPrice"
                      :min="1"
                      :max="maxValueValidator"
                      :digit="1"
                      :domain="IfaUnitPriceYen101DigitsCDomainModel"
                      :step-table="brandInfo.orderPriceUnit"
                      :initial-step="initialStep"
                      unit="円"
                      :support="true"
                      size="small"
                    ></ifa-input-price>
                  </div>
                  <div
                    style="padding-top: 15px;"
                  >
                    <span> {{ form.tradeCd == '5' ? '以上になった時点でOCO1注文を以下の執行条件に訂正' : '以下になった時点でOCO1注文を以下の執行条件に訂正' }}</span>
                  </div>
                </el-row>

                <!-- フォーム: 執行方法選択 @価格/執行方法 -->
                <el-row
                  style="padding-top: 0.5rem; flex-wrap: nowrap;"
                >
                  <!-- No.79 -->
                  <el-col
                    :span="4"
                    style="margin-left: 450px"
                  >
                    <ifa-input-select
                      v-model="form.oco2GyakusasiHouhou"
                      :code-list-id="'EXECUTE_METHOD'"
                      :disp-pattern="1"
                      :select-pattern="form.oco1SasinariJyouken === 'F' ? 3 : 2"
                      style="width: 200px;"
                      @change="oco2ChangeExcuteMethod"
                    ></ifa-input-select>
                  </el-col>
                  <!-- No.81 -->
                  <el-col
                    v-if="form.oco2GyakusasiHouhou == '2'"
                    :span="4"
                  >
                    <ifa-input-select
                      v-model="form.oco2GyakusasiJyouken"
                      :code-list-id="'LIMIT_MARKET_TYPE'"
                      style="width: 200px;"
                      :disp-pattern="3"
                      :select-pattern="8"
                      :disabled="true"
                    ></ifa-input-select>
                  </el-col>
                  <!-- No.80 -->
                  <el-col
                    v-else-if="form.oco2GyakusasiHouhou == '1'"
                    :span="4"
                  >
                    <ifa-input-select
                      v-model="form.oco2GyakusasiJyouken"
                      :code-list-id="'LIMIT_MARKET_TYPE'"
                      :disp-pattern="2"
                      :select-pattern="6"
                      style="width: 200px;"
                      :disabled="true"
                    ></ifa-input-select>
                  </el-col>
                  <el-col
                    v-if="form.oco2GyakusasiHouhou == '1'"
                    :span="5"
                  >
                    <ifa-input-price
                      v-model="form.oco2Price"
                      prop="oco2Price"
                      :min="1"
                      :max="maxValueValidator"
                      :digit="1"
                      :support="true"
                      :domain="IfaUnitPriceYen101DigitsCDomainModel"
                      :step-table="brandInfo.orderPriceUnit"
                      :initial-step="initialStep"
                      unit="円"
                      size="small"
                    ></ifa-input-price>
                  </el-col>
                  <el-col
                    :span="1"
                    style="padding-top: 15px;"
                  >
                    <span>で執行</span>
                  </el-col>
                </el-row>
                <el-row
                  style="padding-top: 0.5rem;"
                  class="limit-label"
                >
                  <el-col
                    :span="8"
                    style="margin-left: 450px"
                  ></el-col>
                  <el-col
                    v-if="(priceRow == '1.0' && priceHigh == '9999999999999.9')"
                    class="limit-labal"
                    :span="6"
                    style="padding: 1rem 0 0 0;"
                  >
                    <span>制限値幅：なし</span>
                  </el-col>
                  <el-col
                    v-else
                    class="limit-labal"
                    :span="6"
                    style="padding: 1rem 0 0 0;"
                  >
                    <span>制限値幅：{{ $_out($_withCommaZeroPadding(priceRow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円 ({{ $_out($_getFormattedDateValue(form.priceLimitDate, 'date6')) }})</span>
                  </el-col>
                </el-row>
              </div>
              <!-- フォーム: 重要事項確認 -->
              <el-row style="padding-top: 1.5rem;">
                <el-col class="label-left">
                  <!-- No.87 -->
                  <div>
                    <ifa-input-check
                      ref="checkInsider"
                      v-model="form.checkInsider"
                      label-class="validation-error-width"
                      prop="checkInsider"
                      label="確認項目"
                      :code-list-id="'INSIDER_CONFIRM'"
                      :disp-pattern="2"
                      :select-pattern="2"
                    ></ifa-input-check>
                  </div>
                  <!-- No.88 -->
                  <div style="margin-left:235px;">
                    <ifa-input-check
                      v-if="form.selectedMarket == 'A'"
                      ref="checkSor"
                      v-model="form.checkSor"
                      label-class="validation-error-width"
                      prop="checkSor"
                      label=""
                      :code-list-id="'SOR_CONFIRM'"
                      :disp-pattern="2"
                      :select-pattern="2"
                    ></ifa-input-check>
                  </div>
                </el-col>
              </el-row>

              <!-- フォーム: 注文確認 -->
              <ifa-button
                id="btnOrderConfirm"
                class="label-btn"
                text="注文確認"
                color="primary"
                action-type="requestAction"
                action-id="SUB0202_0212-04_1#A016"
                :form="formRef"
                :request-model="a016RequestModel"
                @response-handler="responseHandlerA016($event)"
              ></ifa-button>
            </el-row>
          </el-form>
        </el-card>

        <!-- ダイアログ -->
        <ifa-stock-detail-info
          :is-visible="displayStockBoard"
          :form-data="detailInfo"
          @close-modal="displayStockBoard = false"
          @price-select="handlePriceSelect"
        ></ifa-stock-detail-info>

        <ifa-margin-repay-order-confirm
          ref="ifaMarginRepayOrderConfirm"
          :is-visible="dialogTableVisible"
          :form-data="confirmFormModel"
          :customer-info="customerInfo"
          @close-modal="handleCloseModal(false)"
          @order-finish="handleOrderFinish"
        ></ifa-margin-repay-order-confirm>

        <ifa-margin-repay-order-complete
          :is-visible="dialogFinish"
          :form="canselInfo"
          :customer-info="customerInfo"
          @close-modal="dialogFinish = false"
          @move-to-unexecuted-order-list="handleMoveToUnexecutedOrderList"
        ></ifa-margin-repay-order-complete>
      </el-row>
    </div>

    <!--A002-->
    <ifa-requester
      id="IfaMarginRepayOrderInputMarketSelectionA002"
      action-id="SUB0202_0212-04_1#A002"
      action-type="requestAction"
      :request-model="a002RequestModel"
      @response-handler="responseHandlerInitializeA002($event)"
    ></ifa-requester>

  </div>
</template>
<script>
import IfaBrandSearch from '@/components/SearchCondition/IfaBrandSearch.vue'
import IfaBrandPriceInfo from '@/components/Info/IfaBrandPriceInfo'
import IfaMarginRepayOrderConfirm from './IfaMarginRepayOrderConfirm'
import IfaMarginRepayOrderComplete from './IfaMarginRepayOrderComplete'
import IfaOpenInterestRepaymentList from '@/views/brokerageMenu/customerMenu/transaction/newOrderInput/IfaOpenInterestRepaymentList.vue'
import IfaStockDetailInfo from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/IfaStockDetailInfo'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import IfaStocks8DomainModel from '@/resource/domain/IfaStocks8DomainModel.json'
import IfaUnitPriceYen101DigitsCDomainModel from '@/resource/domain/IfaUnitPriceYen101DigitsCDomainModel.json'
import { IfaMarginRepayOrderInputA002RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginRepayOrderInputA002RequestModel.js'
import { IfaMarginRepayOrderInputA003RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginRepayOrderInputA003RequestModel.js'
import { IfaMarginRepayOrderInputA004RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginRepayOrderInputA004RequestModel.js'
import { IfaMarginRepayOrderInputA016RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginRepayOrderInputA016RequestModel.js'
import { IfaMarginRepayOrderInputFormModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginRepayOrderInputFormModel.js'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    IfaBrandSearch,
    IfaBrandPriceInfo,
    IfaMarginRepayOrderConfirm,
    IfaMarginRepayOrderComplete,
    IfaOpenInterestRepaymentList,
    IfaStockDetailInfo,
    screenTitle
  },
  props: {
    params: { type: Object, required: true }
  },
  emits: ['transition-margin-mass-repay-input', 'initializeError'],
  data() {
    return {
      A002ResParamsList: ['positionNo', 'profitAndLossReal'],
      upperRankMkt: '',
      isA005: false,
      periodType: null, // ※空文字だとpropsの仕様でtrueが渡され初期表示が未選択にならない
      detailInfo: {},
      originalMarketList: [{ 'key': '', 'value': '' }],
      customMarketList: [],
      brandInfo: {},
      form: new IfaMarginRepayOrderInputFormModel(),
      canselInfo: {},
      IfaStocks8DomainModel: IfaStocks8DomainModel,
      IfaUnitPriceYen101DigitsCDomainModel: IfaUnitPriceYen101DigitsCDomainModel,
      dialogTableVisible: false,
      dialogFinish: false,
      dialogInsiderConfirmVisible: false,
      sroaVisible: false,
      displayStockBoard: false,
      orderMode: '',
      fontColor: '#00847F',
      ocoIndentStyle: 'ifd-oco-label',
      openInterestRepaymentListData: [],
      fromBulkRepayment: false,
      bgColor: '#ecf5ff',
      styleSeparator: {
        height: '20px',
        background: '#ffd985',
        fontWeight: 'bold',
        marginBottom: '10px'
      },
      rules: {
        sasinariHouhou: [
          { required: true, message: getMessage('errors.required', ['執行方法']), trigger: 'change' }
        ],
        oco1SasinariHouhou: [
          { required: true, message: getMessage('errors.required', ['執行方法']), trigger: 'change' }
        ],
        oco2OrderExecuteMethodList: [
          { required: true, message: getMessage('errors.required', ['執行方法']), trigger: 'change' }
        ],
        quantity: [
          { required: true, message: getMessage('errors.required', ['株数']), trigger: 'blur' }
        ],
        oco2TriggerPrice: [
          { required: true, message: getMessage('errors.required', ['発火条件価格']), trigger: 'blur' }
        ],
        checkInsider: {
          validator: (rule, value, callback) => {
            if (value !== '1') {
              callback(new Error(getMessage('errors.selected', ['インサイダー取引および契約締結前交付書面'])))
            } else {
              callback()
            }
          }
        },
        checkSor: {
          validator: (rule, value, callback) => {
            if (value !== '1') {
              callback(new Error(getMessage('errors.selected', ['SOR対象銘柄の説明'])))
            } else {
              callback()
            }
          }
        },
        kanyuKbn: [
          { required: true, message: getMessage('errors.selected', ['勧誘区分']), trigger: 'blur' }
        ],
        orderMethod: [
          { required: true, message: getMessage('errors.selected', ['受注方法']), trigger: 'blur' }
        ],
        orderKind: [
          { required: true, message: getMessage('errors.selected', ['注文種別']), trigger: 'change' }
        ],
        // 不具合管理#3587対応
        periodTerms: [
          { required: true, message: getMessage('errors.required', ['注文期間']), trigger: 'change' }
        ]
      },
      confirmFormModel: {},
      formRef: {}
    }
  },
  computed: {
    dateOptions() {
      const dateList = []
      Object.assign(dateList, this.form.businessDayList)
      return {
        disabledDate(date) {
          const targetDate = formatDate(date)
          // 形式をレスポンスデータの日付と合わせる
          function formatDate(date) {
            const year = date.getFullYear()
            const month = String(date.getMonth() + 1).padStart(2, '0')
            const day = String(date.getDate()).padStart(2, '0')
            return `${year}/${month}/${day}`
          }
          const result = dateList.some(value => {
            return targetDate === value
          })
          return !result
        }
      }
    },

    // // 期間の表示条件
    periodButtonOption() {
      // 銘柄指定前(初期表示など)
      if (this.form.selectedMarket === '') {
        return {}
      }
      if (this.form.orderKind === '2') { // 注文種別＝OCOの場合
        if (this.form.oco1SasinariHouhou === '1' && // OCO1.執行方法=指値　かつ
          this.form.oco1SasinariJyouken === 'F') { // OCO1.執行条件（指値）=不成
          return { period: 'disabled' }
        }
        return {}
      } else { // 注文種別＝OCO以外の場合
        if (this.form.selectedMarket === '7') { // 市場がPTSの場合
          return { period: 'hide' }
        } else { // 市場がPTS以外の場合
          if ((this.form.sasinariHouhou === '1' && this.form.sasinariJyouken !== ' ') || // 執行方法=指値　かつ　執行条件<>条件なし　または
            this.form.sasinariHouhou === '2') { // 執行方法=成行
            return { period: 'disabled' }
          }
        }
        return {}
      }
    },
    priceRow() {
      // 信用返済売の場合
      if (this.form.tradeCd === '6') {
        return this.brandInfo.sellStopLow
      } else if (this.form.tradeCd === '5') { // 信用返済買の場合
        return this.brandInfo.buyStopLow
      } else {
        return ''
      }
    },
    priceHigh() {
      // 信用返済売の場合
      if (this.form.tradeCd === '6') {
        return this.brandInfo.sellStopHigh
      } else if (this.form.tradeCd === '5') { // 信用返済買の場合
        return this.brandInfo.buyStopHigh
      } else {
        return ''
      }
    },
    // 市場：「当社優先市場/SOR」が選択されている場合、最上位上場市場を返す
    marketRequest() {
      if (this.form.selectedMarket === 'A') {
        return this.upperRankMkt
      } else {
        return this.form.selectedMarket
      }
    },
    initialStep() {
      // CC013.現在値
      const currentPrice = Number(this.brandInfo?.currentPrice)
      if (currentPrice !== 0 && !isNaN(currentPrice)) {
        return currentPrice
      }

      // CC013.基準価格
      const basePrice = Number(this.brandInfo?.basePrice)
      if (basePrice !== 0 && !isNaN(currentPrice)) {
        return basePrice
      }

      return 0
    },
    maxValueValidator() {
      const stepTable = this.brandInfo?.orderPriceUnit
      if (stepTable) {
        const maxStep = stepTable.reduce((current, candidate) => {
          return current < Number(candidate.orderPriceUnit) ? Number(candidate.orderPriceUnit) : current
        }, 0)

        const splitedValue = String(maxStep).split('.')
        if (splitedValue[1] && splitedValue[1].length > 0) {
          return 99999999.9
        } else {
          return 9999999999
        }
      }

      return 9999999999
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    a002RequestModel() {
      return new IfaMarginRepayOrderInputA002RequestModel(this.form)
    },
    a003RequestModel() {
      return new IfaMarginRepayOrderInputA003RequestModel(
        {
          brandCode: this.form.brandCode,
          market: this.marketRequest
        }
      )
    },
    a004RequestModel() {
      return new IfaMarginRepayOrderInputA004RequestModel(this.form)
    },
    a016RequestModel() {
      return new IfaMarginRepayOrderInputA016RequestModel(this.form)
    }
  },
  watch: {
    'form.oco1SasinariJyouken'(newValue) {
      if (newValue === 'F') {
        this.form.oco2GyakusasiHouhou = '1'
        this.oco2ChangeExcuteMethod(this.form.oco2OrderExecuteMethodList)
      }
    },
    // 期間の初期設定値を判定して反映
    // 執行方法・条件の変更による期間の変更を監視
    periodButtonOption: {
      deep: true,
      handler() {
        this.setPeriodTypeInput(this.selectPeriodRadio())
      }
    },
    'form.businessDayList': {
      deep: true,
      handler() {
        if (this.periodType) { // 更新前の入力値が期間指定の場合
          // 更新後の営業日リストで選択可能期間リストを再描画させるため期間日付の入力値を一度クリアする
          this.$refs['periodSelector'].resetComponent()
          this.periodType = true
          this.setPeriodTypeInput(this.periodType)
        }
      }
    },
    // 不具合管理#3587対応
    'periodType': {
      handler(newValue) {
        this.setPeriodRadio(newValue)
      }
    }
  },

  methods: {
    showOpenInterestBulkRepaymentA008() {
      // 信用一括返済入力画面へ遷移
      this.$emit('transition-margin-mass-repay-input', this.form)
    },
    // 期間（periodSelector）の初期設定値、更新値を反映
    setPeriodTypeInput(periodTypeInput) {
      if (periodTypeInput === null && this.$refs['periodSelector']) {
        this.$refs['periodSelector'].resetComponent()
      } else {
        this.$nextTick(() => {
          if (this.$refs['periodSelector']) {
            this.$refs['periodSelector'].periodTypeInput = periodTypeInput
            this.$refs['periodSelector'].$emit('update:periodType', periodTypeInput)
          }
        })
      }
    },
    // 通常/逆指値, OCOで表示する期間初期値設定
    selectPeriodRadio() {
      // periodTypeInput がnullのとき初期選択なし、falseのとき"当日中"
      let periodTypeInput = null
      // 注文種別＝OCOの場合
      if (this.form.orderKind === '2') {
        if (this.form.oco1SasinariHouhou === '1' &&
          this.form.oco1SasinariJyouken === 'F') {
          periodTypeInput = false
        }
      // 注文種別が上記以外（通常/逆指値）の場合
      } else {
        if (this.form.selectedMarket === '7') {
          periodTypeInput = false
        } else if ((this.form.sasinariHouhou === '1' && this.form.sasinariJyouken !== ' ') ||
        (this.form.sasinariHouhou === '2')) {
          periodTypeInput = false
        }
      }
      return periodTypeInput
    },

    // 板情報から価格を選択したときの処理
    handlePriceSelect(value) {
      this.displayStockBoard = false
    },
    responseHandlerInitializeA002(response) {
      for (let i = 0; i < response.dataList[0].repayPositionDetailList.length; i++) {
        for (const key in response.dataList[0].repayPositionDetailList[i]) {
          if (!(this.A002ResParamsList.includes(key))) {
            delete response.dataList[0].repayPositionDetailList[i][key]
          }
        }
        this.form.repayPositionDetailList[i] = Object.assign({}, this.form.repayPositionDetailList[i], response.dataList[0].repayPositionDetailList[i])
      }
      Object.assign(this.form.businessDayList, response.dataList[0].businessDayList)
      this.$refs['IfaMarginRepayOrderInputBrandPriceInfo'].updateRequest()
      this.changeTradeType(this.form.tradeCd)
    },
    updateA004(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      this.$refs['IfaMarginRepayOrderInputBrandPriceInfo'].updateRequest()
      this.changeTradeType(this.form.tradeCd)
    },
    // 注文期間の選択値(Boolean)を区分値に変換する
    setPeriodRadio(periodType) {
      if (periodType) {
        this.form.periodTerms = '1'
      } else {
        this.form.periodTerms = '0'
      }
    },
    changeExcuteMethod(value) {
      if (value === '2') {
        this.form.sasinariJyouken = 'N'
      } else {
        this.form.sasinariJyouken = ' '
      }
    },
    oco1ChangeExcuteMethod(value) {
      if (value === '2') {
        this.form.oco1SasinariJyouken = 'N'
      } else {
        this.form.oco1SasinariJyouken = ' '
      }
    },
    oco1ChangeSasinariJyouken(value) {
      // OCO1.執行条件（指値）= 条件なし
      if (value === ' ') {
        this.form.oco2GyakusasiJyouken = ' '
      // OCO1.執行条件（指値）= 不成
      } else {
        this.form.oco2GyakusasiJyouken = 'F'
      }
    },
    oco2ChangeExcuteMethod(value) {
      // OCO2.執行方法（逆指値）= 成行
      if (value === '2') {
        this.form.oco2GyakusasiJyouken = 'N'
      // OCO2.執行方法（逆指値）= 指値
      } else {
        // OCO1.執行条件（指値）= 条件なし
        if (this.form.oco1SasinariJyouken === ' ') {
          this.form.oco2GyakusasiJyouken = ' '
        // OCO1.執行条件（指値）= 不成
        } else {
          this.form.oco2GyakusasiJyouken = 'F'
        }
      }
    },
    changeStopOrderPrice(value) {
      if (value !== '2') {
        this.form.sasinariJyouken = ''
      }
    },
    onShow() {
      // 初期化
      this.$nextTick(() => {
        this.formRef = this.$refs.form
      })
      // ⑧画面の初期表示を行う
      Object.assign(this.form, this.params.dataList[0])

      // 新規売買区分に応じた背景色・フォントの設定
      this.changeTradeType()

      // ⑨銘柄検索エリア.銘柄検索を初期化する。
      this.$refs['ifaMarginRepayOrderInputBrandSearch'].handleRowClick(this.params.dataList[0])

      this.$refs['ifaMarginRepayOrderInputBrandSearch'].resetAll()
      this.$refs['IfaMarginRepayOrderInputBrandPriceInfo'].initData()
      this.resetForm()
      this.changeOrderFormat('1')
    },
    changeTradeType() {
      // 取引種別が 信用返済買 の場合
      if (this.form.tradeCd === '5') {
        this.bgColor = '#fef0f0'
        this.fontColor = '#E5004C'
        // 取引種別が 信用返済売 の場合
      } else {
        this.bgColor = '#ecf5ff'
        this.fontColor = '#00847F'
      }
    },
    detailDisplayA003(response) {
      this.detailInfo = Object.assign(this.detailInfo, response.dataList[0])
      this.displayStockBoard = true
    },
    // 銘柄検索(子コンポーネント)からのイベント処理
    handleChange(param) {
      // [イベントID: 1] 銘柄が選択された
      if (param.id === '1') {
        this.form.selectedMarket = param.data.selectMarketList.length > 0 ? param.data.selectMarketList[0].key : ''
        this.form.oco1SasinariJyouken = ''
        this.upperRankMkt = param.data.upperRankMkt // 最上位上場市場

        // 銘柄時価情報エリアを更新する。
        this.$refs['IfaMarginRepayOrderInputBrandPriceInfo'].updateRequest()
        this.originalMarketList = param.data.selectMarketList
        this.changeMarketList(this.form.orderKind)

        // A002を実行
        this.$nextTick(() => {
          document.getElementById('IfaMarginRepayOrderInputMarketSelectionA002').click()
        }
        )
        // [イベントID: 2] 市場が変更された
      } else if (param.id === '2') {
        this.form.selectedMarket = param.data
        // 市場変更の際に執行方法の値が逆指値の場合、逆指値をださない画面なら不具合起きるためその際は初期値を代入
        if (this.form.selectedMarket === 'A' || this.form.selectedMarket === '7') {
          if (this.form.orderKind === '1' && this.form.sasinariHouhou === '3') {
            this.form.sasinariHouhou = '1'
          }
        }
        this.resetForm()
        // PTSが選択されたら期間を当日にする。
        if (this.form.selectedMarket === '7') {
          this.form.periodTerms = '0'
        }

        // 銘柄時価情報エリアを更新する。
        this.$refs['IfaMarginRepayOrderInputBrandPriceInfo'].updateRequest()

        // A002を実行
        this.$nextTick(() => {
          document.getElementById('IfaMarginRepayOrderInputMarketSelectionA002').click()
        }
        )
      } else if (param.id === '3') {
        // ×押下時param.id === '3'を返すため、そのタイミングで時価情報のリセットと入力された値をリセットする。
        this.$refs['IfaMarginRepayOrderInputBrandPriceInfo'].initData()
        this.resetForm()
      }
    },
    // 銘柄時価情報(子コンポーネント)からのイベント処理
    handleChangeBrandPrice(param) {
      this.brandInfo = param
    },
    // A016正常終了
    async responseHandlerA016(response) {
      this.confirmFormModel = Object.assign(this.confirmFormModel, response.dataList[0])
      await this.$nextTick() // 現在の更新を待つためのVueのメソッド
      await this.$refs.ifaMarginRepayOrderConfirm.onShow()
      this.dialogTableVisible = true
    },
    // 注文完了画面に遷移
    async handleOrderFinish(response) {
      Object.assign(this.canselInfo, response)
      this.dialogTableVisible = false
      this.dialogFinish = true
    },
    // 注文一覧画面に遷移
    handleMoveToUnexecutedOrderList() {
      this.resetForm('form')
      this.dialogFinish = false
      this.$_startShowMenu('SUB0202_0104')
    },
    // 確認画面の戻る
    async handleCloseModal(handleOrderFinishDialogClose) {
      this.dialogInsiderConfirmVisible = false
      this.dialogTableVisible = false
      this.dialogFinish = false
      this.sroaVisible = false
    },

    // 注文種別変更時処理
    changeOrderFormat(value) {
      this.changeMarketList(value)
      this.form.sasinariHouhou = '1'
      this.form.ifd1OrderExecuteMethodList = '1'
      this.form.ifd2OrderExecuteMethodList = '1'
      this.form.oco1SasinariHouhou = '1'
      this.form.oco2OrderExecuteMethodList = '3'
      this.form.oco2GyakusasiHouhou = '1'
      this.changeExcuteMethod(this.form.sasinariHouhou)
      this.oco1ChangeExcuteMethod(this.form.oco1SasinariHouhou)
      this.oco2ChangeExcuteMethod(this.form.oco2OrderExecuteMethodList)
    },
    changeMarketList(value) {
      // 注文種別に応じて、銘柄検索エリア.銘柄検索の市場リストを変更する。
      if (value === '1') {
        // 注文種別=通常/逆指値の場合:プロパティ.対象市場リスト=設定なし
        this.customMarketList = []
        this.$refs['ifaMarginRepayOrderInputBrandSearch'].internalMarketList = this.originalMarketList
        this.$refs['ifaMarginRepayOrderInputBrandSearch'].selectedMarket = this.originalMarketList[0].key
        this.form.selectedMarket = this.originalMarketList[0].key
      } else if (value === '2') {
        // 注文種別=OCOの場合:プロパティ.対象市場リスト=東証
        this.customMarketList = this.originalMarketList.filter(item => item.key === '0')
        this.$refs['ifaMarginRepayOrderInputBrandSearch'].internalMarketList = this.customMarketList
        this.$refs['ifaMarginRepayOrderInputBrandSearch'].selectedMarket = this.$refs['ifaMarginRepayOrderInputBrandSearch'].internalMarketList[0].key
        this.form.selectedMarket = this.$refs['ifaMarginRepayOrderInputBrandSearch'].internalMarketList[0].key
      }
    },
    // リセット処理
    resetForm() {
      this.form.quantity = ''
      this.form.sasinariHouhou = '1'
      this.form.sasinariJyouken = ' '
      this.form.price = ''
      this.form.triggerPrice = ''
      this.form.gyakusasiHouhou = '1'
      this.form.orderKind = '1'
      this.periodType = null
      this.form.periodTerms = ''
      this.form.limit = ''
      this.form.oco1SasinariHouhou = ''
      this.form.oco1SasinariJyouken = ''
      this.form.oco1Price = ''
      this.form.oco1SasinariHouhou = '1'
      this.form.oco2OrderExecuteMethodList = '3'
      this.form.oco2TriggerPrice = ''
      this.form.oco2GyakusasiHouhou = '1'
      this.form.oco2GyakusasiJyouken = ' '
      this.form.oco2Price = ''
      this.form.kanyuKbn = ''
      this.form.orderMethod = ''
      this.form.checkInsider = '0'
      this.form.checkSor = '0'
      this.$nextTick(() => {
        if (this.$refs['checkSor']) {
          this.$refs['checkSor'].$refs.checkSor.resetField()
        }
        this.$refs['checkInsider'].$refs.checkInsider.resetField()
        this.$refs['form'].clearValidate()
      })
      this.$refs['periodSelector'].resetComponent()
    }
  }
}
</script>

<style lang="scss" scoped>
@import '@/styles/orderStatusList.scss';
:deep(.el-tabs_full_content) .el-tabs__content {
  padding: 0;
}
.order-input {
  width: 80%;
}
.form-radio {
  width: 4rem;
}
.form-static-area__section {
  border-bottom: 0px solid #eee;
  margin: 0.5rem 0 0 0;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-start;
  padding: 0 2rem 0.2rem 0;
}
.form-reset-button__wrapper {
  display: flex;
  justify-content: flex-end;
  padding-right: 1rem;
}
.form-area__select {
  width: 9rem;
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
.ifd-oco-label {
  margin-left: 1rem;
  vertical-align: middle;
  font-weight: bold;
}
.oco-indent-label {
  @extend .ifd-oco-label;
  margin-left: 2rem;
}
:deep(.el-table--mini) td, .el-table--mini th {
  padding: 0px;
  height: 5px;
}
:deep(.el-table) td, .el-table th {
  padding: 0px;
  height: 5px;
}
:deep(.el-form-item__label) {
  font-weight: bold;
  padding: 0.2rem 0 0 0;
  margin-left: 0.5rem;
}
:deep(.el-form-item__content) {
  margin: 0.2rem 0 0 0;
  width: 80%;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
.label-left {
  padding-left: 2rem;
}
.label-btn {
  padding-left: 1rem;
}
.validation-error-width {
  :deep(.el-form-item__error) {
    --ifa-input-validation-error-width: 400px;
  }
}
.date-picker :deep(.el-input) {
  width: 200px;
  margin-left: 12px;
}
:deep(.date-picker) .el-form-item__content{
  display: flex !important;
}
</style>
