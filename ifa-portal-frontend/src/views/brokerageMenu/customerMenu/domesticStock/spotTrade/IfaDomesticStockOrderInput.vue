<template>
  <div>
    <screen-title :text="form.title"></screen-title>
    <div class="caption_card">
      <!-- エラー/警告表示 -->
      <el-row>
        <div>
          <ul
            type="disc"
            class="error-message"
          >
            <li
              v-for="message in userInfo.messages.errors"
              :key="message"
            >
              {{ message }}
            </li>
          </ul>
          <ul
            type="disc"
            class="warning-message"
          >
            <li
              v-for="message in userInfo.messages.warnings"
              :key="message"
            >
              {{ message }}
            </li>
          </ul>
          <ul
            type="disc"
            class="info-message"
          >
            <li
              v-for="message in userInfo.messages.infos"
              :key="message"
            >
              {{ message }}
            </li>
          </ul>
        </div>
      </el-row>

      <el-card style="background-color: #eee; margin-bottom: 0.5rem;">
        <el-row
          style="display: flex; padding: 0 1rem"
        >
          <el-col :span="19">
            <!-- 銘柄検索 -->
            <ifa-brand-search
              ref="ifaBrandSearch"
              :is-code-lock="source === 'other'"
              trading-type="0"
              :market-list="marketList"
              @change="handleChangeStock"
              @display-stock-board="displayStockBoard = true"
            ></ifa-brand-search>
          </el-col>
          <el-col
            v-if="form.brandCode !== ''"
            :span="4"
            class="update-button"
            style="margin-left: 0;"
          >
            <ifa-button
              v-if="showDisplayDetailButton"
              id="btnDetail"
              text="詳細"
              icon="Document"
              small
              action-id="SUB0202_0208-02#A001"
              action-type="requestAction"
              :request-model="IfaStockDetailInfoA001RequestModel"
              @response-handler="responseHandlerA004"
            ></ifa-button>
            <ifa-button
              id="btnUpdate"
              text="更新"
              icon="RefreshRight"
              small
              action-id="SUB0202_0208-01_1#A005"
              action-type="requestAction"
              :request-model="A005RequestModel"
              @response-handler="responseHandlerUpdateA005($event)"
            ></ifa-button>
          </el-col>
        </el-row>
        <!-- 時価情報 -->
        <ifa-brand-price-info
          ref="ifaBrandPriceInfo"
          :brand-code="form.brandCode"
          :market="form.selectedMarket"
          @change="handleChangePrice"
        ></ifa-brand-price-info>
      </el-card>

      <el-card
        v-if="form.tradeCd === '1'"
        style="background-color: #eee; margin-bottom: 0.5rem;"
      >
        <el-row
          :gutter="20"
          style="padding: 0.5rem 0 0 1rem;"
        >
          <el-col :span="4">
            <div class="info_xs">
              <span class="info-item__header __left">買付余力</span>
            </div>
          </el-col>
          <el-col
            :span="4"
          >
            <div class="info_xs">
              <div class="info-item__header">{{ $_out(formatDate(form.buyingPower.deliveryDateT2)) }}(2営業日後)</div>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="info_xs">
              <div class="info-item__header">{{ $_out(formatDate(form.buyingPower.deliveryDateT3)) }}(3営業日後)</div>
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
            <div v-if="customerInfo.jrIsaContractType === '1'"
                 class="info_xs"
            >
              <span class="info-item__header __left">(総合口座)</span>
            </div>
          </el-col>
          <el-col
            :span="3"
            :pull="1"
          >
            <div class="info_xs">
              <div class="info-item__value __right">{{ form.buyingPower.wholeAccountT2 ? `${ifaFormatUtils.withCommaInteger(form.buyingPower.wholeAccountT2)} 円` : '-' }}</div>
            </div>
          </el-col>
          <el-col :span="3">
            <div class="info_xs">
              <div class="info-item__value __right">{{ form.buyingPower.wholeAccountT3 ? `${ifaFormatUtils.withCommaInteger(form.buyingPower.wholeAccountT3)} 円` : '-' }}</div>
            </div>
          </el-col>
        </el-row>
        <el-row
          v-if="customerInfo.jrIsaContractType === '1'"
          :gutter="20"
          style="padding-top: 0.5rem;"
        >
          <el-col
            :span="4"
            :offset="1"
          >
            <div class="info_xs">
              <span class="info-item__header __left">(ジュニアNISA口座)</span>
            </div>
          </el-col>
          <el-col
            :span="3"
            :pull="1"
          >
            <div class="info_xs">
              <div class="info-item__value __right">{{ form.buyingPower.jrNisaAccountStatusT2 ? `${ifaFormatUtils.withCommaInteger(form.buyingPower.jrNisaAccountStatusT2)} 円` : '-' }}</div>
            </div>
          </el-col>
          <el-col :span="3">
            <div class="info_xs">
              <div class="info-item__value __right">{{ form.buyingPower.jrNisaAccountStatusT3 ? `${ifaFormatUtils.withCommaInteger(form.buyingPower.jrNisaAccountStatusT3)} 円` : '-' }}</div>
            </div>
          </el-col>
        </el-row>
      </el-card>

      <el-row>
        <el-card
          class="box-card"
          :style="{ 'background-color': bgColor }"
        >
          <div>
            <el-form
              ref="form"
              :model="form"
              label-width="220px"
              label-position="left"
              class="form-main"
              :style="{ 'background-color': bgColor }"
            >
              <!-- フォーム: 取引 -->
              <div
                class="form-area__section"
              >

                <!-- IFD1(注文種別が IFD または IFDOCO のとき表示) -->
                <div
                  v-if="orderMode==='3' || orderMode==='4'"
                  :style="styleSeparator"
                >
                  <span class="ifd-oco-label">IFD1</span>
                </div>
                <!-- IFD1 -->
                <el-form-item
                  class="account_form"
                  label="口座"
                  inline-message="true"
                  :required="!depositTypeParamFlag && customerInfo.jrIsaContractType === '1'"
                >
                  <span v-if="fixAccountKbn">{{ getFixedAccountKbn }}</span>

                  <el-col
                    v-if="!fixAccountKbn"
                    :span="12"
                  >
                    <el-radio
                      :id="getIDforTopElement(2)"
                      v-model="form.account"
                      :disabled="editDisable"
                      :tabindex="getIDforTopElement(2) === 'ff' ? 0 : -1"
                      label="1"
                      class="form-radio__auto"
                    >ジュニアNISA口座</el-radio>
                    <el-radio
                      :id="getIDforTopElement(3)"
                      v-model="form.account"
                      :disabled="editDisable"
                      :tabindex="getIDforTopElement(3) === 'ff' ? 0 : -1"
                      label=" "
                      class="form-radio__auto"
                    >総合口座</el-radio>
                  </el-col>
                </el-form-item>

                <span class="form-reset-button__wrapper">
                  <!-- リセットボタン -->
                  <ifa-button
                    id="btnReset"
                    :disabled="editDisable"
                    text="リセット"
                    color="secondary"
                    action-type="originalAction"
                    @app-action-handler="resetForm('form')"
                  ></ifa-button>
                </span>
              </div>

              <!-- フォーム: 取引種別 -->
              <div class="form-area__section">
                <el-form-item
                  label="取引種別"
                  class="el-form-item__error_custom-margin"
                >
                  <el-col :span="8">
                    <span :class="form.tradeCd === '1' ? 'font-color__plus bold' : form.tradeCd === '2' ? 'font-color__minus bold' : ''">
                      <ifa-text
                        :style="form.tradeCd === '2' ? 'color: #00847F; font-weight: bold;' : 'color: #E5004C; font-weight: bold;'"
                        code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                        disp-pattern="1"
                        :code-key="form.tradeCd ? form.tradeCd : '1'"
                      ></ifa-text>

                    </span>
                  </el-col>
                </el-form-item>
              </div>

              <!-- 売却可能数量 -->
              <div
                v-if="form.tradeCd === '2'"
                class="form-area__section"
              >
                <el-form-item>
                  <template #label>
                    売却可能数量
                  </template>
                  <span>
                    {{ form.acPosition ? ifaFormatUtils.withCommaInteger(form.acPosition) : '0' }} 株
                  </span>
                </el-form-item>
              </div>

              <!-- フォーム: 数量 -->
              <el-row class="form-area__section">
                <div style="flex: 0 0 685px">
                  <ifa-input-amount
                    :id="getIDforTopElement(0)"
                    v-model="form.quantity"
                    label="数量"
                    prop="quantity"
                    :disabled="editDisable"
                    :min="stockInfo.unit"
                    :max="maxVolume"
                    :initial-step="stockInfo.unit"
                    :step="stockInfo.unit"
                    placeholder=" "
                    support
                    unit="株"
                    style="width: 200px;"
                    :domain="IfaStocks8DomainModel"
                    required
                  ></ifa-input-amount>
                </div>
                <div
                  class="el-form-item"
                  style="flex: 0 1 300px"
                >
                  <span
                    v-if="form.brandCode !== ''"
                  >売買単位：{{ stockInfo.unit }} 株</span>
                </div>
              </el-row>

              <!-- フォーム: 価格/執行方法(注文種別が OCO 以外の時に表示) -->
              <div
                v-if="orderMode!=='2'"
                class="form-area__section"
              >
                <el-form-item
                  v-if="orderMode !=='3' && orderMode !=='4'"
                  label="価格/執行方法"
                  prop="normalPriceLimitReverse.sasinariHouhou"
                  required
                >
                  <el-row>
                    <el-col :span="3">
                      <!-- フォーム: 執行方法選択 @価格/執行方法 -->
                      <ifa-input-select
                        :key="methodSelectPattern"
                        v-model="form.normalPriceLimitReverse.sasinariHouhou"
                        select-class="form-area__input_select"
                        :disabled="editDisable"
                        code-list-id="EXECUTE_METHOD"
                        :disp-pattern="1"
                        style="width: 100px;"
                        :select-pattern="methodSelectPattern"
                        @change="changeMethod"
                      >
                      </ifa-input-select>
                    </el-col>

                    <el-col
                      :span="21"
                      style="padding-left: 0.5rem;"
                    >
                      <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                      <el-row v-if="form.normalPriceLimitReverse.sasinariHouhou === '1'">
                        <el-col :span="6">
                          <ifa-input-select
                            :key="form.market"
                            v-model="form.normalPriceLimitReverse.sasinariJyouken"
                            select-class="form-area__input_select"
                            :disabled="editDisable"
                            code-list-id="LIMIT_MARKET_TYPE"
                            :disp-pattern="2"
                            style="width: 200px;"
                            :select-pattern="form.market === 'A' || form.market === '7' ? 7 : 2"
                            @change="changeJyouken"
                          >
                          </ifa-input-select>
                        </el-col>
                        <el-col
                          :span="10"
                          style="margin-left: 1rem;"
                        >
                          <ifa-input-price
                            v-model="form.normalPriceLimitReverse.price"
                            prop="normalPriceLimitReverse.price"
                            :disabled="editDisable"
                            :min="minValueValidator"
                            :max="maxValueValidator"
                            :step="stockInfo.priceQuotation"
                            :digit="0"
                            :step-table="priceInfo.orderPriceUnit"
                            :initial-step="stockInfo.initialStep"
                            placeholder=" "
                            unit="円"
                            support
                            :domain="IfaUnitPriceYen101DigitsCDomainModel"
                            :required="true"
                          ></ifa-input-price>
                        </el-col>
                        <el-col
                          v-if="form.market === 'A'"
                          :span="8"
                          style="color: red; font-weight: bold; margin-left: -30px;"
                        >(PTS発注時は優先市場最良気配指値)</el-col>
                      </el-row>
                      <!-- フォーム: 成行選択時の条件設定 @価格/執行方法 -->
                      <el-row v-if="form.normalPriceLimitReverse.sasinariHouhou == '2'">
                        <el-col :span="6">
                          <ifa-input-select
                            :key="form.market"
                            v-model="form.normalPriceLimitReverse.sasinariJyouken"
                            select-class="form-area__input_select"
                            code-list-id="LIMIT_MARKET_TYPE"
                            :disp-pattern="3"
                            style="width: 200px;"
                            :select-pattern="form.market === 'A' || form.market === '7' ? 8 : 3"
                          >
                          </ifa-input-select>
                        </el-col>
                        <el-col
                          v-if="form.market === 'A'"
                          :span="7"
                          style="color: red; font-weight: bold; margin-left: 1rem;"
                        >(PTS発注時は優先市場最良気配指値)</el-col>
                      </el-row>

                      <!-- フォーム: 逆指値選択時の条件設定 @価格/執行方法 -->
                      <el-row v-if="form.normalPriceLimitReverse.sasinariHouhou == '3'">
                        <el-row>
                          <el-col :span="2">
                            <span>現在値が</span>
                          </el-col>
                          <el-col :span="13">
                            <ifa-input-price
                              v-model="form.normalPriceLimitReverse.triggerPrice"
                              prop="normalPriceLimitReverse.triggerPrice"
                              :min="minValueValidator"
                              :max="maxValueValidator"
                              :step="stockInfo.priceQuotation"
                              :step-table="priceInfo.orderPriceUnit"
                              :initial-step="stockInfo.initialStep"
                              placeholder=" "
                              unit="円"
                              support
                              :required="true"
                              :domain="IfaUnitPriceYen101DigitsCDomainModel"
                              class="form-area__input-number"
                            ></ifa-input-price>
                          </el-col>
                          <el-col
                            :span="7"
                            style="margin-left: -110px;"
                          >
                            <span>
                              {{ form.tradeCd === "1" ? "以上" : "以下" }}になった時点で
                            </span>
                          </el-col>
                        </el-row>

                        <el-row style="padding-top: 0.5rem;">
                          <el-col
                            :span="3"
                            style="margin-right: 1rem;"
                          >
                            <ifa-input-select
                              v-model="form.normalPriceLimitReverse.gyakusasiHouhou"
                              select-class="form-area__input_select"
                              code-list-id="EXECUTE_METHOD"
                              :disp-pattern="1"
                              :select-pattern="2"
                              style="width: 100px;"
                              @change="changeMethod"
                            >
                            </ifa-input-select>
                          </el-col>

                          <el-col
                            v-if="form.normalPriceLimitReverse.gyakusasiHouhou === '1'"
                            :span="6"
                            style="margin-right: 1rem;"
                          >
                            <ifa-input-select
                              v-model="form.normalPriceLimitReverse.sasinariJyouken"
                              select-class="form-area__input_select"
                              code-list-id="LIMIT_MARKET_TYPE"
                              :disp-pattern="2"
                              :select-pattern="4"
                              style="width: 200px;"
                            >
                            </ifa-input-select>
                          </el-col>
                          <el-col
                            v-if="form.normalPriceLimitReverse.gyakusasiHouhou === '1'"
                            :span="13"
                            style="margin-right: -110px;"
                          >
                            <ifa-input-price
                              v-model="form.normalPriceLimitReverse.price"
                              prop="normalPriceLimitReverse.price"
                              :min="minValueValidator"
                              :max="maxValueValidator"
                              :step="stockInfo.priceQuotation"
                              :step-table="priceInfo.orderPriceUnit"
                              :initial-step="stockInfo.initialStep"
                              placeholder=" "
                              unit="円"
                              support
                              :required="true"
                              :domain="IfaUnitPriceYen101DigitsCDomainModel"
                              class="form-area__input-number"
                            ></ifa-input-price>
                          </el-col>

                          <el-col
                            v-if="form.normalPriceLimitReverse.gyakusasiHouhou === '2'"
                            :span="6"
                            style="margin-right: 1rem;"
                          >
                            <ifa-input-select
                              v-model="form.normalPriceLimitReverse.sasinariJyouken"
                              select-class="form-area__input_select"
                              code-list-id="LIMIT_MARKET_TYPE"
                              :disp-pattern="3"
                              :select-pattern="5"
                              style="width: 200px;"
                            >
                            </ifa-input-select>
                          </el-col>
                          <el-col
                            :span="2"
                          >
                            <span>で執行</span>
                          </el-col>
                        </el-row>
                        <!-- フォーム: 逆指値選択時の条件設定 @制限値幅 -->
                        <el-row
                          v-if="form.normalPriceLimitReverse.sasinariHouhou === '3'"
                          class="limit-labal"
                        >
                          <el-col
                            :span="9"
                            :offset="9"
                            style="padding: 0.8rem 0 0 1.4rem;"
                          >
                            <span v-if="stockInfo.limitLow === 1.0 && stockInfo.limitHigh === 9999999999999.9">制限値幅：なし</span>
                            <span v-else>制限値幅：{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitLow) }}円～{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitHigh) }}円</span>
                            <span v-if="stockInfo.limitLow !== 1.0 || stockInfo.limitHigh !== 9999999999999.9"> ({{ stockInfo.limitDate }})</span>
                          </el-col>
                        </el-row>
                      </el-row>
                    </el-col>
                  </el-row>
                  <!-- フォーム: 指値選択時の条件設定 @制限値幅 -->
                  <el-row
                    v-if="form.normalPriceLimitReverse.sasinariHouhou !== '3'"
                    class="limit-labal"
                  >
                    <el-col
                      :span="16"
                      :offset="8"
                      style="padding: 0.8rem 0 0 1.1rem;"
                    >
                      <span v-if="stockInfo.limitLow === 1.0 && stockInfo.limitHigh === 9999999999999.9">制限値幅：なし</span>
                      <span v-else>制限値幅：{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitLow) }}円～{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitHigh) }}円</span>
                      <span v-if="stockInfo.limitLow !== 1.0 || stockInfo.limitHigh !== 9999999999999.9"> ({{ stockInfo.limitDate }})</span>
                    </el-col>
                  </el-row>
                </el-form-item>

                <!-- 注文種別: IFDの場合 -->
                <el-form-item
                  v-if="orderMode==='3' || orderMode==='4'"
                  label="価格/執行方法"
                  prop="ifd1.sasinariHouhou"
                >
                  <el-row>
                    <el-col :span="3">
                      <!-- フォーム: 執行方法選択 @価格/執行方法 -->
                      <ifa-input-select
                        :key="methodSelectPattern"
                        v-model="form.ifd1.sasinariHouhou"
                        select-class="form-area__input_select"
                        code-list-id="EXECUTE_METHOD"
                        :disp-pattern="1"
                        style="width: 100px;"
                        :select-pattern="methodSelectPattern"
                        @change="changeIfd1Method"
                      >
                      </ifa-input-select>
                    </el-col>

                    <el-col
                      :span="21"
                      style="padding-left: 0.5rem;"
                    >
                      <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                      <el-row v-if="form.ifd1.sasinariHouhou === '1'">
                        <el-col :span="6">
                          <ifa-input-select
                            :key="form.market"
                            v-model="form.ifd1.sasinariJyouken"
                            select-class="form-area__input_select"
                            code-list-id="LIMIT_MARKET_TYPE"
                            :disp-pattern="2"
                            style="width: 200px;"
                            :select-pattern="form.market === 'A' || form.market === '7' ? 7 : 2"
                            @change="changeJyouken"
                          >
                          </ifa-input-select>
                        </el-col>
                        <el-col
                          :span="10"
                          style="margin-left: 1rem; margin-right: -85px;"
                        >
                          <ifa-input-price
                            :key="stockInfo.initialStep"
                            v-model="form.ifd1.price"
                            prop="ifd1.price"
                            :min="minValueValidator"
                            :max="maxValueValidator"
                            :step="stockInfo.priceQuotation"
                            :digit="0"
                            :step-table="priceInfo.orderPriceUnit"
                            :initial-step="stockInfo.initialStep"
                            placeholder=" "
                            unit="円"
                            support
                            :domain="IfaUnitPriceYen101DigitsCDomainModel"
                            :required="true"
                          ></ifa-input-price>
                        </el-col>
                        <el-col
                          v-if="form.market === 'A'"
                          :span="8"
                          style="color: red; font-weight: bold;"
                        >(PTS発注時は優先市場最良気配指値)</el-col>
                      </el-row>
                      <!-- フォーム: 成行選択時の条件設定 @価格/執行方法 -->
                      <el-row v-if="form.ifd1.sasinariHouhou == '2'">
                        <el-col :span="6">
                          <ifa-input-select
                            :key="form.market"
                            v-model="form.ifd1.sasinariJyouken"
                            select-class="form-area__input_select"
                            code-list-id="LIMIT_MARKET_TYPE"
                            :disp-pattern="3"
                            style="width: 200px;"
                            :select-pattern="form.market === 'A' || form.market === '7' ? 8 : 3"
                          >
                          </ifa-input-select>
                        </el-col>
                        <el-col
                          v-if="form.market === 'A'"
                          :span="6"
                          style="color: red; font-weight: bold;"
                        >(PTS発注時は優先市場最良気配指値)</el-col>
                      </el-row>

                      <!-- フォーム: 逆指値選択時の条件設定 @価格/執行方法 -->
                      <el-row v-if="form.ifd1.sasinariHouhou == '3'">
                        <el-row>
                          <el-col :span="2">
                            <span>現在値が</span>
                          </el-col>
                          <el-col :span="13">
                            <ifa-input-price
                              :key="stockInfo.initialStep"
                              v-model="form.ifd1.triggerPrice"
                              prop="ifd1.triggerPrice"
                              :min="minValueValidator"
                              :max="maxValueValidator"
                              :step="stockInfo.priceQuotation"
                              :step-table="priceInfo.orderPriceUnit"
                              :initial-step="stockInfo.initialStep"
                              placeholder=" "
                              unit="円"
                              support
                              :domain="IfaUnitPriceYen101DigitsCDomainModel"
                              :required="true"
                              class="form-area__input-number"
                            ></ifa-input-price>
                          </el-col>
                          <el-col
                            :span="7"
                            style="margin-left: -110px;"
                          >
                            <span>
                              {{ form.tradeCd === "1" ? "以上" : "以下" }}になった時点で
                            </span>
                          </el-col>
                        </el-row>

                        <el-row style="padding-top: 0.5rem;">
                          <el-col
                            :span="3"
                            style="margin-right: 1rem;"
                          >
                            <ifa-input-select
                              v-model="form.ifd1.gyakusasiHouhou"
                              select-class="form-area__input_select"
                              code-list-id="EXECUTE_METHOD"
                              :disp-pattern="1"
                              :select-pattern="2"
                              style="width: 100px;"
                              @change="changeIfd1Method"
                            >
                            </ifa-input-select>
                          </el-col>

                          <el-col
                            v-if="form.ifd1.gyakusasiHouhou === '1'"
                            :span="6"
                            style="margin-right: 1rem;"
                          >
                            <ifa-input-select
                              v-model="form.ifd1.sasinariJyouken"
                              select-class="form-area__input_select"
                              code-list-id="LIMIT_MARKET_TYPE"
                              :disp-pattern="2"
                              :select-pattern="4"
                              style="width: 200px;"
                            >
                            </ifa-input-select>
                          </el-col>
                          <el-col
                            v-if="form.ifd1.gyakusasiHouhou === '1'"
                            :span="13"
                            style="margin-right: -110px;"
                          >
                            <ifa-input-price
                              :key="stockInfo.initialStep"
                              v-model="form.ifd1.price"
                              prop="ifd1.price"
                              :min="minValueValidator"
                              :max="maxValueValidator"
                              :step="stockInfo.priceQuotation"
                              :step-table="priceInfo.orderPriceUnit"
                              :initial-step="stockInfo.initialStep"
                              placeholder=" "
                              unit="円"
                              support
                              :domain="IfaUnitPriceYen101DigitsCDomainModel"
                              :required="true"
                              class="form-area__input-number"
                            ></ifa-input-price>
                          </el-col>

                          <el-col
                            v-if="form.ifd1.gyakusasiHouhou === '2'"
                            :span="6"
                            style="margin-right: 1rem;"
                          >
                            <ifa-input-select
                              v-model="form.ifd1.sasinariJyouken"
                              select-class="form-area__input_select"
                              code-list-id="LIMIT_MARKET_TYPE"
                              :disp-pattern="3"
                              :select-pattern="5"
                              style="width: 200px;"
                            >
                            </ifa-input-select>
                          </el-col>
                          <el-col
                            :span="2"
                          >
                            <span>で執行</span>
                          </el-col>
                        </el-row>
                        <!-- フォーム: 逆指値選択時の条件設定 @制限値幅 -->
                        <el-row
                          v-if="form.ifd1.sasinariHouhou === '3'"
                          class="limit-labal"
                        >
                          <el-col
                            :span="9"
                            :offset="9"
                            style="padding: 0.8rem 0 0 1.4rem;"
                          >
                            <span v-if="stockInfo.limitLow === 1.0 && stockInfo.limitHigh === 9999999999999.9">制限値幅：なし</span>
                            <span v-else>制限値幅：{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitLow) }}円～{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitHigh) }}円</span>
                            <span v-if="stockInfo.limitLow !== 1.0 || stockInfo.limitHigh !== 9999999999999.9"> ({{ stockInfo.limitDate }})</span>
                          </el-col>
                        </el-row>
                      </el-row>
                    </el-col>
                  </el-row>
                  <!-- フォーム: 指値選択時の条件設定 @制限値幅 -->
                  <el-row
                    v-if="form.ifd1.sasinariHouhou !== '3'"
                    class="limit-labal"
                  >
                    <el-col
                      :span="14"
                      :offset="8"
                      style="padding: 0.8rem 0 0 1.1rem;"
                    >
                      <span v-if="stockInfo.limitLow === 1.0 && stockInfo.limitHigh === 9999999999999.9">制限値幅：なし</span>
                      <span v-else>制限値幅：{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitLow) }}円～{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitHigh) }}円</span>
                      <span v-if="stockInfo.limitLow !== 1.0 || stockInfo.limitHigh !== 9999999999999.9"> ({{ stockInfo.limitDate }})</span>
                    </el-col>
                  </el-row>
                </el-form-item>
              </div>

              <!-- フォーム: 注文種別 -->
              <div
                v-if="orderMode!=='2'"
                class="form-area__section"
              >
                <el-form-item
                  label="注文種別"
                  prop="orderType"
                  required
                >
                  <el-row>
                    <el-col :span="4">
                      <ifa-input-select
                        :key="orderKindSelectPattern"
                        v-model="form.orderKind"
                        select-class="form-area__input_select"
                        :disabled="editDisable"
                        code-list-id="ORDER_CLASS"
                        :disp-pattern="1"
                        style="width: 200px;"
                        :select-pattern="orderKindSelectPattern"
                        @change="changeOrderFormat($event)"
                      >
                      </ifa-input-select>
                    </el-col>
                  </el-row>
                </el-form-item>
              </div>

              <!-- フォーム: 期間指定 -->
              <div class="form-area__section form-area__period"
                   style="width: 880px;"
              >
                <!-- IfaPeriodSelector の prop は必ず "periodTerms" を指定する -->
                <ifa-period-selector
                  ref="periodSelector"
                  v-model:period="form.period.limit"
                  v-model:period-type="form.period.periodTerms"
                  label="注文期間"
                  prop="period.periodTerms"
                  :button-options="buttonOptions"
                  :picker-options="dateOptions"
                  :disabled="editDisable"
                  class="date-picker"
                  :required="true"
                ></ifa-period-selector>
              </div>

              <!-- フォーム: 預り区分 -->
              <div class="form-area__section">
                <el-form-item
                  label="預り区分"
                  :required="!depositTypeParamFlag"
                >
                  <ifa-text
                    v-if="depositTypeParamFlag"
                    code-list-id="DOMESTIC_DEPOSIT_TYPE"
                    :disp-pattern="setTextDepositTypeDispPattern"
                    :code-key="form.depositType"
                  ></ifa-text>
                  <ifa-input-radio
                    v-if="!depositTypeParamFlag"
                    :key="form.account"
                    v-model="form.depositType"
                    prop="form.depositType"
                    tabindex="0"
                    :disabled="editDisable"
                    code-list-id="DOMESTIC_DEPOSIT_TYPE"
                    :disp-pattern="setRadioDepositTypePattern.dispPattern"
                    :select-pattern="setRadioDepositTypePattern.selectPattern"
                    :required="true"
                  >
                  </ifa-input-radio>
                  <div
                    v-if="(customerInfo.nisaType === '3' || customerInfo.nisaTypeYearNext === '3') && form.account === ' '"
                    class="jrnisa_warning"
                  >NISA投資可能枠は{{ form.buyingPower.nisaBuy ? `${ifaFormatUtils.withCommaInteger(form.buyingPower.nisaBuy)}円` : '-' }}です。</div>
                </el-form-item>
              </div>

              <!-- IFD2（注文種別が IFD また IFDOCO の時のみ表示） -->
              <div
                v-if="orderMode==='3' || orderMode==='4'"
                class="ifd2_section"
              >
                <div :style="styleSeparator">
                  <span class="ifd-oco-label">IFD2</span>
                </div>
                <div class="form-area__section">
                  <el-form-item label="取引種別">
                    <ifa-text
                      v-model="ifd2TradeCd"
                      style="color: #00847F; font-weight: bold;"
                      code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                      disp-pattern="1"
                      :code-key="'2'"
                    ></ifa-text>
                  </el-form-item>
                </div>
                <!-- IFD2（注文種別が IFD の時のみ表示） -->
                <div
                  v-if="orderMode==='3'"
                  class="form-area__section"
                >
                  <el-form-item
                    label="価格/執行方法"
                    prop="ifd2.sasinariHouhou"
                  >
                    <el-row>
                      <el-col :span="3">
                        <!-- フォーム: 執行方法選択 @価格/執行方法 -->
                        <ifa-input-select
                          v-model="form.ifd2.sasinariHouhou"
                          select-class="form-area__input_select"
                          code-list-id="EXECUTE_METHOD"
                          :disp-pattern="1"
                          :select-pattern="4"
                          style="width: 100px;"
                          @change="changeIfd2SasinariHouhou"
                        >
                        </ifa-input-select>
                      </el-col>

                      <el-col
                        :span="21"
                      >
                        <el-row v-if="form.ifd2.sasinariHouhou == '1'">
                          <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                          <el-col
                            :span="6"
                            style="margin-left: 9px;"
                          >
                            <ifa-input-select
                              v-model="form.ifd2.sasinariJyouken"
                              select-class="form-area__input_select"
                              code-list-id="LIMIT_MARKET_TYPE"
                              :disp-pattern="2"
                              :select-pattern="4"
                              style="width: 200px;"
                              @change="changeIfd2SasinariJyouken"
                            >
                            </ifa-input-select>
                          </el-col>
                          <el-col
                            :span="10"
                            style="margin-left: 1rem;  margin-right: -85px;"
                          >
                            <ifa-input-price
                              v-model="form.ifd2.price"
                              prop="ifd2.price"
                              :min="minValueValidator"
                              :max="maxValueValidator"
                              :support="true"
                              :step-table="priceInfo.orderPriceUnit"
                              :initial-step="stockInfo.initialStep"
                              placeholder=" "
                              unit="円"
                              :domain="IfaUnitPriceYen101DigitsCDomainModel"
                              class="form-area__input-number"
                              :required="true"
                            ></ifa-input-price>
                          </el-col>
                        </el-row>

                        <!-- フォーム: 逆指値選択時の条件設定 @価格/執行方法 -->
                        <el-row v-if="form.ifd2.sasinariHouhou == '3'">
                          <el-row>
                            <el-col :span="2">
                              <span>現在値が</span>
                            </el-col>
                            <el-col :span="13">
                              <ifa-input-price
                                v-model="form.ifd2.triggerPrice"
                                prop="ifd2.triggerPrice"
                                :min="minValueValidator"
                                :max="maxValueValidator"
                                :support="true"
                                :step-table="priceInfo.orderPriceUnit"
                                :initial-step="stockInfo.initialStep"
                                placeholder=" "
                                unit="円"
                                :domain="IfaUnitPriceYen101DigitsCDomainModel"
                                class="form-area__input-number"
                                :required="true"
                              ></ifa-input-price>
                            </el-col>
                            <el-col
                              :span="7"
                              style="margin-left: -110px;"
                            >
                              <span> 以下になった時点で</span>
                            </el-col>
                          </el-row>

                          <!-- フォーム: 執行方法選択 @価格/執行方法 -->
                          <el-row>
                            <el-row style="padding-top: 0.5rem;">
                              <el-col
                                :span="3"
                                style="margin-right: 1rem;"
                              >
                                <ifa-input-select
                                  v-model="form.ifd2.gyakusasiHouhou"
                                  select-class="form-area__input_select"
                                  code-list-id="EXECUTE_METHOD"
                                  :disp-pattern="1"
                                  :select-pattern="2"
                                  style="width: 100px;"
                                  @change="changeIfd2Method"
                                >
                                </ifa-input-select>
                              </el-col>

                              <el-col
                                v-if="form.ifd2.gyakusasiHouhou === '2'"
                                :span="6"
                                style="margin-right: 1rem;"
                              >
                                <ifa-input-select
                                  v-model="form.ifd2.sasinariJyouken"
                                  select-class="form-area__input_select"
                                  code-list-id="LIMIT_MARKET_TYPE"
                                  :disp-pattern="3"
                                  :select-pattern="5"
                                  style="width: 200px;"
                                >
                                </ifa-input-select>
                              </el-col>
                              <el-col
                                v-if="form.ifd2.gyakusasiHouhou === '1'"
                                :span="6"
                                style="margin-right: 1rem;"
                              >
                                <ifa-input-select
                                  v-model="form.ifd2.sasinariJyouken"
                                  select-class="form-area__input_select"
                                  code-list-id="LIMIT_MARKET_TYPE"
                                  :disp-pattern="2"
                                  :select-pattern="4"
                                  style="width: 200px;"
                                >
                                </ifa-input-select>
                              </el-col>
                              <el-col
                                v-if="form.ifd2.gyakusasiHouhou === '1'"
                                :span="13"
                                style="margin-right: -110px;"
                              >
                                <ifa-input-price
                                  v-model="form.ifd2.price"
                                  prop="ifd2.price"
                                  :min="minValueValidator"
                                  :max="maxValueValidator"
                                  :support="true"
                                  :step-table="priceInfo.orderPriceUnit"
                                  :initial-step="stockInfo.initialStep"
                                  placeholder=" "
                                  unit="円"
                                  :domain="IfaUnitPriceYen101DigitsCDomainModel"
                                  class="form-area__input-number"
                                  :required="true"
                                ></ifa-input-price>
                              </el-col>
                              <el-col
                                :span="2"
                              >
                                <span>で執行</span>
                              </el-col>
                            </el-row>
                          </el-row>
                        </el-row>
                      </el-col>
                    </el-row>
                    <!-- フォーム: 逆指値選択時の条件設定 @制限値幅 -->
                    <el-row
                      v-if="form.tradeCd !== '' && form.ifd2.sasinariHouhou === '3'"
                      class="limit-labal"
                    >
                      <el-col
                        :span="10"
                        :offset="11"
                        style="padding: 0.8rem 0 0 0.6rem;"
                      >
                        <span v-if="stockInfo.limitLow === 1.0 && stockInfo.limitHigh === 9999999999999.9">制限値幅：なし</span>
                        <span v-else>制限値幅：{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitLow) }}円～{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitHigh) }}円</span>
                        <span v-if="stockInfo.limitLow !== 1.0 || stockInfo.limitHigh !== 9999999999999.9"> ({{ stockInfo.limitDate }})</span>
                      </el-col>
                    </el-row>
                    <!-- フォーム: 指値選択時の条件設定 @制限値幅 -->
                    <el-row
                      v-else-if="form.tradeCd !== ''"
                      class="limit-labal"
                    >
                      <el-col
                        :span="16"
                        :offset="8"
                        style="padding: 0.8rem 0 0 1.1rem;"
                      >
                        <span v-if="stockInfo.limitLow === 1.0 && stockInfo.limitHigh === 9999999999999.9">制限値幅：なし</span>
                        <span v-else>制限値幅：{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitLow) }}円～{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitHigh) }}円</span>
                        <span v-if="stockInfo.limitLow !== 1.0 || stockInfo.limitHigh !== 9999999999999.9"> ({{ stockInfo.limitDate }})</span>
                      </el-col>
                    </el-row>
                  </el-form-item>
                </div>
                <!-- フォーム: 期間指定 -->
                <div class="form-area__section form-area__period"
                     style="width: 880px;"
                >
                  <ifa-period-selector
                    ref="ifd2PeriodSelector"
                    v-model:period="form.ifd2.period.limit"
                    v-model:period-type="form.ifd2.period.periodTerms"
                    label="注文期間"
                    prop="ifd2.period.periodTerms"
                    :button-options="ifd2buttonOptions"
                    :picker-options="dateOptions"
                    :disabled="editDisable"
                    class="date-picker"
                    :required="true"
                  ></ifa-period-selector>
                </div>
              </div>
              <!-- IFD2 -->

              <!-- OCO1 (注文種別が OCO または IFDOCO の時のみ表示) -->
              <div
                v-if="orderMode==='2' || orderMode==='4'"
                class="form-area__section"
              >
                <div :style="styleSeparator">
                  <span :class="ocoIndentStyle">OCO1</span>
                </div>
                <el-form-item
                  label="価格/執行方法"
                  prop="oco1.sasinariHouhou"
                >

                  <el-row>
                    <el-col :span="3">
                      <!-- フォーム: 執行方法選択 @価格/執行方法 -->
                      <ifa-input-select
                        :key="methodSelectPattern"
                        v-model="form.oco1.sasinariHouhou"
                        select-class="form-area__input_select"
                        code-list-id="EXECUTE_METHOD"
                        :disp-pattern="1"
                        :select-pattern="3"
                        style="width: 100px;"
                      >
                      </ifa-input-select>
                    </el-col>

                    <el-col
                      v-if="form.oco1.sasinariHouhou == '1'"
                      :span="21"
                      style="padding-left: 0.5rem;"
                    >
                      <el-row>
                        <el-col :span="6">
                          <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                          <ifa-input-select
                            :key="form.market"
                            v-model="form.oco1.sasinariJyouken"
                            select-class="form-area__input_select"
                            code-list-id="LIMIT_MARKET_TYPE"
                            :disp-pattern="2"
                            :select-pattern="6"
                            style="width: 200px;"
                            @change="changeOco1SasinariJyouken"
                          >
                          </ifa-input-select>
                        </el-col>
                        <el-col
                          :span="10"
                          style="margin-left: 1rem;"
                        >
                          <ifa-input-price
                            v-model="form.oco1.price"
                            prop="oco1.price"
                            :min="minValueValidator"
                            :max="maxValueValidator"
                            :step="stockInfo.priceQuotation"
                            :digit="0"
                            :step-table="priceInfo.orderPriceUnit"
                            :initial-step="stockInfo.initialStep"
                            placeholder=" "
                            unit="円"
                            support
                            :domain="IfaUnitPriceYen101DigitsCDomainModel"
                            :required="true"
                          ></ifa-input-price>
                        </el-col>
                      </el-row>
                    </el-col>
                  </el-row>
                  <el-row
                    v-if="form.tradeCd !== ''"
                    class="limit-labal"
                  >
                    <el-col
                      :span="16"
                      :offset="8"
                      style="padding: 0.8rem 0 0 1.1rem;"
                    >
                      <span v-if="stockInfo.limitLow === 1.0 && stockInfo.limitHigh === 9999999999999.9">制限値幅：なし</span>
                      <span v-else>制限値幅：{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitLow) }}円～{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitHigh) }}円</span>
                      <span v-if="stockInfo.limitLow !== 1.0 || stockInfo.limitHigh !== 9999999999999.9"> ({{ stockInfo.limitDate }})</span>
                    </el-col>
                  </el-row>
                </el-form-item>
              </div>
              <!-- フォーム: 注文種別 -->
              <div
                v-if="orderMode==='2'"
                class="form-area__section"
              >
                <el-form-item
                  label="注文種別"
                  :tabindex="-1"
                >
                  <el-row>
                    <el-col :span="4">
                      <ifa-input-select
                        :key="orderKindSelectPattern"
                        v-model="form.orderKind"
                        select-class="form-area__input_select"
                        code-list-id="ORDER_CLASS"
                        :disp-pattern="1"
                        style="width: 200px;"
                        :select-pattern="orderKindSelectPattern"
                        @change="changeOrderFormat($event)"
                      >
                      </ifa-input-select>
                    </el-col>
                  </el-row>
                </el-form-item>
              </div>
              <!-- OCO1 -->

              <!-- OCO2（注文種別が OCO または IFDOCO の時のみ表示） -->
              <div
                v-if="orderMode==='2' || orderMode==='4'"
                class="form-area__section"
              >
                <div :style="styleSeparator">
                  <span :class="ocoIndentStyle">OCO2</span>
                </div>
                <el-form-item
                  label="価格/執行方法"
                  prop="oco2.sasinariHouhou"
                >

                  <el-row>
                    <el-col :span="3">
                      <!-- フォーム: 執行方法選択 @価格/執行方法 -->
                      <ifa-input-select
                        v-model="form.oco2.sasinariHouhou"
                        select-class="form-area__input_select"
                        code-list-id="EXECUTE_METHOD"
                        :disp-pattern="1"
                        :select-pattern="5"
                        style="width: 100px;"
                      >
                      </ifa-input-select>
                    </el-col>

                    <el-col
                      :span="21"
                      style="padding-left: 0.5rem;"
                    >
                      <!-- フォーム: 逆指値選択時の条件設定 @価格/執行方法 -->
                      <el-row v-if="form.oco2.sasinariHouhou == '3'">
                        <el-row>
                          <el-col :span="2">
                            <span>現在値が</span>
                          </el-col>
                          <el-col :span="13">
                            <ifa-input-price
                              v-model="form.oco2.triggerPrice"
                              prop="oco2.triggerPrice"
                              :min="minValueValidator"
                              :max="maxValueValidator"
                              :step="stockInfo.priceQuotation"
                              :digit="0"
                              :step-table="priceInfo.orderPriceUnit"
                              :initial-step="stockInfo.initialStep"
                              placeholder=" "
                              unit="円"
                              support
                              :domain="IfaUnitPriceYen101DigitsCDomainModel"
                              :required="true"
                            ></ifa-input-price>
                          </el-col>
                          <el-col :span="7">
                            <span style="margin-left: -110px;"> {{ ifdOcoCondition() }}になった時点でOCO1注文を以下の執行条件に訂正</span>
                          </el-col>
                        </el-row>

                        <!-- フォーム: 執行方法選択 @価格/執行方法 -->
                        <el-row>
                          <el-row style="padding-top: 0.5rem;">
                            <el-col
                              :span="3"
                              style="margin-right: 1rem;"
                            >
                              <ifa-input-select
                                :key="form.oco1.sasinariJyouken"
                                v-model="form.oco2.gyakusasiHouhou"
                                select-class="form-area__input_select"
                                code-list-id="EXECUTE_METHOD"
                                :disp-pattern="1"
                                style="width: 100px;"
                                :select-pattern="form.oco1.sasinariJyouken === 'F' ? 3 : 2"
                                @change="changeOco2Method"
                              >
                              </ifa-input-select>
                            </el-col>

                            <el-col
                              v-if="form.oco2.gyakusasiHouhou === '2'"
                              :span="6"
                              style="margin-right: 1rem;"
                            >
                              <ifa-input-select
                                v-model="form.oco2.gyakusasiJyouken"
                                select-class="form-area__input_select"
                                code-list-id="LIMIT_MARKET_TYPE"
                                :disp-pattern="3"
                                :select-pattern="8"
                                style="width: 200px;"
                                disabled
                              ></ifa-input-select>
                            </el-col>
                            <el-col
                              v-if="form.oco2.gyakusasiHouhou === '1'"
                              :span="6"
                              style="margin-right: 1rem;"
                            >
                              <ifa-input-select
                                v-model="form.oco2.gyakusasiJyouken"
                                select-class="form-area__input_select"
                                code-list-id="LIMIT_MARKET_TYPE"
                                :disp-pattern="2"
                                :select-pattern="6"
                                style="width: 200px;"
                                disabled
                              ></ifa-input-select>
                            </el-col>
                            <el-col
                              v-if="form.oco2.gyakusasiHouhou === '1'"
                              :span="13"
                              style="margin-right: -110px;"
                            >
                              <ifa-input-price
                                v-model="form.oco2.price"
                                prop="oco2.price"
                                :min="minValueValidator"
                                :max="maxValueValidator"
                                :step="stockInfo.priceQuotation"
                                :step-table="priceInfo.orderPriceUnit"
                                :initial-step="stockInfo.initialStep"
                                placeholder=" "
                                unit="円"
                                :domain="IfaUnitPriceYen101DigitsCDomainModel"
                                support
                                :required="true"
                              ></ifa-input-price>
                            </el-col>
                            <el-col
                              :span="2"
                            >
                              <span>で執行</span>
                            </el-col>
                          </el-row>
                          <el-row
                            v-if="form.tradeCd !== ''"
                            class="limit-labal"
                          >
                            <el-col
                              :span="9"
                              :offset="9"
                              style="padding: 0.8rem 0 0 1.4rem;"
                            >
                              <span v-if="stockInfo.limitLow === 1.0 && stockInfo.limitHigh === 9999999999999.9">制限値幅：なし</span>
                              <span v-else>制限値幅：{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitLow) }}円～{{ ifaFormatUtils.withCommaNoneZeroPadding(stockInfo.limitHigh) }}円</span>
                              <span v-if="stockInfo.limitLow !== 1.0 || stockInfo.limitHigh !== 9999999999999.9"> ({{ stockInfo.limitDate }})</span>
                            </el-col>
                          </el-row>
                        </el-row>
                      </el-row>
                    </el-col>
                  </el-row>
                </el-form-item>
              </div>
              <!-- OCO2 -->

              <!-- フォーム: 手数料区分 -->
              <div class="form-area__section">
                <el-form-item
                  class="charge-type"
                  label="手数料区分"
                >
                  <ifa-text
                    code-list-id="PRE_CONTRACT_DOC_CODE"
                    disp-pattern="1"
                    :code-key="customerInfo.customerAttribute"
                  ></ifa-text>
                  <span>{{ customerInfo.customerAttribute ? '（電話手数料）' : '-' }}</span>
                </el-form-item>
              </div>

              <!-- フォーム: 勧誘区分 -->
              <div class="form-area__section">
                <ifa-input-select
                  v-model="form.kanyuKbn"
                  label="勧誘区分"
                  prop="kanyuKbn"
                  :disabled="editDisable"
                  code-list-id="INVITATION_TYPE"
                  :disp-pattern="1"
                  :select-pattern="1"
                  style="width: 200px;"
                  size="small"
                  required
                >
                </ifa-input-select>
              </div>

              <!-- フォーム: 受注方法 -->
              <div class="form-area__section">
                <ifa-input-select
                  v-model="form.receiveOrderType"
                  label="受注方法"
                  prop="receiveOrderType"
                  :disabled="editDisable"
                  code-list-id="ORDER_METHOD_TYPE"
                  :disp-pattern="1"
                  :select-pattern="1"
                  style="width: 200px;"
                  size="small"
                  required
                >
                </ifa-input-select>
              </div>

              <!-- フォーム: 重要事項確認 -->
              <div
                class="form-area__checkbox el-form-item__error_custom-margin"
              >
                <ifa-input-check
                  :key="form.confirmItem.insider"
                  v-model="form.confirmItem.insider"
                  prop="confirmItem.insider"
                  label="確認項目"
                  code-list-id="INSIDER_CONFIRM"
                  :disp-pattern="2"
                  :select-pattern="2"
                  :disabled="editDisable"
                  :required="true"
                >
                </ifa-input-check>
              </div>
              <div
                v-if="form.market === 'A'"
                class="form-area__checkbox el-form-item__error_custom-margin"
                style="margin-left:308px;"
              >
                <ifa-input-check
                  :key="form.confirmItem.sor"
                  v-model="form.confirmItem.sor"
                  prop="confirmItem.sor"
                  label=""
                  code-list-id="SOR_CONFIRM"
                  :disp-pattern="2"
                  :select-pattern="2"
                  :disabled="editDisable"
                  :required="true"
                >
                </ifa-input-check>
              </div>

              <!-- 注文確認ボタン -->
              <!-- Note: タブ移動での順番の関係でリセットボタンよりも前に配置する必要あり -->
              <ifa-button
                class="order-button"
                :disabled="editDisable"
                :form="formRef"
                text="注文確認"
                action-id="SUB0202_0208-01_1#A016"
                action-type="requestAction"
                :pre-request-handler="preRequestHandlerA016"
                :request-model="A016RequestModel"
                @response-handler="orderConfirmA016($event)"
              ></ifa-button>

            </el-form>

          </div>
        </el-card>

        <ifa-domestic-stock-order-confirm
          ref="ifaDomesticStockOrderConfirm"
          :is-visible="dialogTableVisible"
          :form="form"
          :form-data="confirmData"
          :user-info="userInfo"
          :customer-info="customerInfo"
          :append-to-body="true"
          :stock-info="stockInfo"
          :selected-market="form.market"
          @close-modal="handleCloseModal"
          @order-finish="handleOrderFinish"
        ></ifa-domestic-stock-order-confirm>

        <ifa-domestic-stock-order-complete
          :is-visible="dialogFinish"
          :form-data="confirmResponseData"
          :user-info="userInfo"
          :customer-info="customerInfo"
          :append-to-body="true"
          :stock-info="stockInfo"
          :selected-market="form.market"
          @close-modal="
            handleCloseModal();
            resetBrandSearch();
          "
          @move-to-order-list="handleMoveToOrderList"
        ></ifa-domestic-stock-order-complete>

        <ifa-stock-detail-info
          :is-visible="displayStockBoard"
          :form-data="detailInfo"
          @close-modal="displayStockBoard = false"
          @price-select="updateNum"
        ></ifa-stock-detail-info>

      </el-row>
    </div>
    <ifa-requester
      id="ifaDomesticStockOrderInputInitializeA001"
      action-id="SUB0202_0208-01_1#A001"
      action-type="requestAction"
      :request-model="A001RequestModel"
      @response-handler="responseHandlerInitializeA001($event)"
      @response-error-handler="errorHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaDomesticStockOrderInputBrandSelectionMarketSelectionA002"
      action-id="SUB0202_0208-01_1#A002"
      action-type="requestAction"
      :request-model="A002RequestModel"
      @response-handler="responseHandlerBrandSelectionMarketSelectionA002($event)"
    ></ifa-requester>
  </div>

</template>

<script>
import IfaBrandSearch from '@/components/SearchCondition/IfaBrandSearch'
import IfaBrandPriceInfo from '@/components/Info/IfaBrandPriceInfo'
import IfaDomesticStockOrderConfirm from './IfaDomesticStockOrderConfirm'
import IfaDomesticStockOrderComplete from './IfaDomesticStockOrderComplete'
import IfaStockDetailInfo from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/IfaStockDetailInfo'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { IfaDomesticStockOrderInputFormModel } from './js/IfaDomesticStockOrderInputFormModel'
import { IfaDomesticStockOrderInputA001RequestModel } from './js/IfaDomesticStockOrderInputA001RequestModel'
import { IfaDomesticStockOrderInputA002RequestModel } from './js/IfaDomesticStockOrderInputA002RequestModel'
import { IfaDomesticStockOrderInputA004RequestModel } from './js/IfaDomesticStockOrderInputA004RequestModel'
import { IfaDomesticStockOrderInputA005RequestModel } from './js/IfaDomesticStockOrderInputA005RequestModel'
import { IfaDomesticStockOrderInputA016RequestModel } from './js/IfaDomesticStockOrderInputA016RequestModel'
import { IfaStockDetailInfoA001RequestModel } from './js/IfaStockDetailInfoA001RequestModel'
import IfaStocks8DomainModel from '@/resource/domain/IfaStocks8DomainModel.json'
import IfaUnitPriceYen101DigitsCDomainModel from '@/resource/domain/IfaUnitPriceYen101DigitsCDomainModel.json'

const undecidedBalanceList = require('@/views/brokerageMenu/customerMenu/data/undecidedBalanceList.js')
const DEPOSIT_TYPE_JR_SPECIFIC = '5' // Jr特定
const DEPOSIT_TYPE_JR_GENERAL = '6' // Jr一般
const DEPOSIT_TYPE_JR_NISA = '7' // JrNISA
const DEPOSIT_TYPE_JR_NISA_CONTINUOUS = 'J' // JrNISA（継続管理勘定）

const jrNisaDepositTypes = [
  DEPOSIT_TYPE_JR_SPECIFIC,
  DEPOSIT_TYPE_JR_GENERAL,
  DEPOSIT_TYPE_JR_NISA,
  DEPOSIT_TYPE_JR_NISA_CONTINUOUS
]

export default {
  components: {
    IfaBrandSearch,
    IfaBrandPriceInfo,
    IfaDomesticStockOrderConfirm,
    IfaDomesticStockOrderComplete,
    IfaStockDetailInfo,
    screenTitle
  },
  emits: ['show-tab-by-name', 'initializeError'],
  data() {
    return {
      ifd2TradeCd: '',
      formatDate: getFormattedDateValue,
      ifaFormatUtils: ifaFormatUtils,
      form: new IfaDomesticStockOrderInputFormModel(),
      IfaStocks8DomainModel: IfaStocks8DomainModel,
      IfaUnitPriceYen101DigitsCDomainModel: IfaUnitPriceYen101DigitsCDomainModel,
      formRef: {},
      confirmData: {},
      confirmResponseData: {},
      dialogInsiderConfirmVisible: false,
      dialogTableVisible: false,
      dialogFinish: false,
      displayStockBoard: false,
      detailInfo: {},
      count: 0,
      buttonOptions: {
        today: '',
        period: ''
      },
      ifd2buttonOptions: {
        today: '',
        period: ''
      },
      orderMode: '',
      orderStyle: {
        title: '',
        color: '',
        fontWeight: 'bold'
      },
      ocoIndentStyle: 'ifd-oco-label',
      userInfo: {
        chargeType: 'インターネットコース（電話手数料）',
        messages: {
          errors: [],
          warnings: [],
          infos: []
        },
        insideTradeStockCode: '6001'
      },
      stockInfo: {
        data: null,
        code: '',
        name: '',
        unit: 100, // 使用
        current: '',
        priceQuotation: 1,
        limitDate: '--/--/--', // 使用
        limitHigh: '-', // 使用
        limitLow: '-', // 使用
        validMarkets: [true, true, true, true, true, true, true],
        initialStep: 0, // 使用
        stepTable: []
      },
      originalMarketList: null,
      priceInfo: {
        orderPriceUnit: [] // 使用
      },
      methodSelectPattern: 1, // 使用
      orderKindSelectPattern: 1, // 使用
      priorityMarket: '0',
      bgColor: '#fef0f0',
      styleSeparator: {
        height: '20px',
        background: '#ffd985',
        fontWeight: 'bold',
        marginBottom: '10px'
      },
      resetGuardFlag: false,
      source: '',
      params: null,
      ownStockInfo: phonyOwnStockInfo,
      marketList: [],
      depositTypeParamFlag: false // true: リクエスト.預り区分あり
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    A001RequestModel() {
      return new IfaDomesticStockOrderInputA001RequestModel(this.form)
    },
    A002RequestModel() {
      return new IfaDomesticStockOrderInputA002RequestModel(this.form)
    },
    A004RequestModel() {
      return new IfaDomesticStockOrderInputA004RequestModel(this.form)
    },
    A005RequestModel() {
      return new IfaDomesticStockOrderInputA005RequestModel(this.form)
    },
    A016RequestModel() {
      return new IfaDomesticStockOrderInputA016RequestModel(this.form)
    },
    IfaStockDetailInfoA001RequestModel() {
      return new IfaStockDetailInfoA001RequestModel(
        {
          brandCode: this.form.brandCode,
          market: this.form.selectedMarket
        }
      )
    },
    minValueValidator() {
      return 1
    },
    maxValueValidator() {
      const stepTable = this.priceInfo?.orderPriceUnit
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
    fixAccountKbn() {
      return this.depositTypeParamFlag || this.customerInfo.jrIsaContractType !== '1'
    },
    getFixedAccountKbn() {
      if (this.depositTypeParamFlag) {
        return jrNisaDepositTypes.includes(this.form.depositType) ? 'ジュニアNISA口座' : '総合口座'
      } else if (this.customerInfo.jrIsaContractType !== '1') {
        return '総合口座'
      }

      return ''
    },
    setTextDepositTypeDispPattern() {
      // 顧客共通情報.ジュニアISA契約区分=1：契約
      if (this.customerInfo.jrIsaContractType === '1') {
        // 顧客共通情報.特定口座区分=1:特定口座(代行納付)　または　2:特定口座(確定申告)
        if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
          return 1
        } else {
          return 8
        }
      } else {
        // 顧客共通情報.特定口座区分=1:特定口座(代行納付)　または　2:特定口座(確定申告)
        if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
          return 2
        } else {
          return 9
        }
      }
    },
    setRadioDepositTypePattern() {
      const pattern = {
        selectPattern: '1',
        dispPattern: '1'
      }
      // 顧客共通情報.ジュニアISA契約区分=1：契約
      if (this.customerInfo.jrIsaContractType === '1') {
        // 口座=総合口座
        if (this.form.account === ' ') {
          // 顧客共通情報.NISA区分（当年）='3':総合NISA　または　顧客共通情報.NISA区分（翌年）='3':総合NISA
          if (this.customerInfo.nisaType === '3' || this.customerInfo.nisaTypeYearNext === '3') {
            // 顧客共通情報.特定口座区分=1:特定口座(代行納付)　または　2:特定口座(確定申告)
            if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
              // @取得パターン:2
              // @表示パターン:1
              pattern.selectPattern = 2
              pattern.dispPattern = 1
            } else {
              // @取得パターン:7
              // @表示パターン:8
              pattern.selectPattern = 7
              pattern.dispPattern = 8
            }
          } else {
            // 顧客共通情報.特定口座区分=1:特定口座(代行納付)　または　2:特定口座(確定申告)
            if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
              // @取得パターン:3
              // @表示パターン:1
              pattern.selectPattern = 3
              pattern.dispPattern = 1
            } else {
              // @取得パターン:8
              // @表示パターン:8
              pattern.selectPattern = 8
              pattern.dispPattern = 8
            }
          }
        // 口座=ジュニアNISA口座
        } else {
          // 顧客共通情報.ジュニア特定口座区分=1:特定口座(代行納付)　または　2:特定口座(確定申告)　の場合
          if (this.customerInfo.jrTokuteiKouzaKbn === '1' || this.customerInfo.jrTokuteiKouzaKbn === '2') {
            // @取得パターン:4
          // @表示パターン:1
            pattern.selectPattern = 4
            pattern.dispPattern = 1
          } else {
            // @取得パターン:9
          // @表示パターン:8
            pattern.selectPattern = 9
            pattern.dispPattern = 8
          }
        }
      } else {
        // 顧客共通情報.NISA区分（当年）='3':総合NISA　または　顧客共通情報.NISA区分（翌年）='3':総合NISA
        if (this.customerInfo.nisaType === '3' || this.customerInfo.nisaTypeYearNext === '3') {
          // 顧客共通情報.特定口座区分=1:特定口座(代行納付)　または　2:特定口座(確定申告)
          if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
            // @取得パターン:2
            // @表示パターン:2
            pattern.selectPattern = 2
            pattern.dispPattern = 2
          } else {
            // @取得パターン:7
            // @表示パターン:9
            pattern.selectPattern = 7
            pattern.dispPattern = 9
          }
        } else {
          // 顧客共通情報.特定口座区分=1:特定口座(代行納付)　または　2:特定口座(確定申告)
          if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
            // @取得パターン:3
            // @表示パターン:2
            pattern.selectPattern = 3
            pattern.dispPattern = 2
          } else {
            // @取得パターン:8
            // @表示パターン:9
            pattern.selectPattern = 8
            pattern.dispPattern = 9
          }
        }
      }
      return pattern
    },
    hasJrNisaAccount() {
      return this.customerInfo.jrNisaAccountNumber === '1'
    },
    maxVolume() {
      return this.form.tradeCd === '2'
        ? this.form.acPosition
          ? this.form.acPosition
          : 0
        : 99999999
    },
    editDisable() {
      return this.form.brandCode === ''
    },
    dateOptions() {
      const dateList = this.form.businessDayList
      return {
        disabledDate(date) {
          const targetDate = formatDate(date)
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
        },
        shortcuts: [{
          text: '週末',
          value: () => {
            return dateList.find((element) => {
              const date = new Date(element)
              const day = date.getDay()
              return day === 5
            })
          }
        }, {
          text: '翌営業日',
          value: () => {
            return dateList[1]
          }
        }, {
          text: '最大',
          value: () => {
            return dateList[dateList.length - 1]
          }
        }]
      }
    }
  },
  mounted() {
    this.formRef = this.$refs['form']
  },
  methods: {
    ifdOcoCondition() {
      if (this.orderMode === '2') {
        if (this.form.tradeCd === '1') {
          return '以上'
        } else {
          return '以下'
        }
      } else {
        if (this.ifd2TradeCd === '1') {
          return '以上'
        } else {
          return '以下'
        }
      }
    },
    responseHandlerInitializeA001(response) {
      Object.assign(this.form, response.dataList[0])
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.title,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    responseHandlerBrandSelectionMarketSelectionA002(response) {
      Object.assign(this.form, response.dataList[0])
    },
    responseHandlerA004(response) {
      this.detailInfo = Object.assign(this.detailInfo, response.dataList[0])
      this.displayStockBoard = true
    },
    responseHandlerUpdateA005(response) {
      Object.assign(this.form, response.dataList[0])
      this.$refs['ifaBrandPriceInfo'].updateRequest()
    },
    getMethodSelectPattern(market) {
      if (market === '') {
        this.methodSelectPattern = 1
      } else if (market === 'A') {
        this.methodSelectPattern = 2
      } else if (market === '7') {
        this.methodSelectPattern = 3
      } else {
        this.methodSelectPattern = 1
      }
    },
    getOrderKindSelectPattern(market) {
      if (market === '') {
        this.orderKindSelectPattern = 1
      } else if (market === '7') {
        this.form.orderKind = '1'
        this.orderKindSelectPattern = 3
      } else if (this.form.tradeCd === '1') {
        this.orderKindSelectPattern = 1
      } else if (this.form.tradeCd === '2') {
        this.orderKindSelectPattern = 2
      }
      this.changeOrderFormat(this.form.orderKind)
    },
    changeMethod(val) {
      if (this.form.normalPriceLimitReverse.sasinariHouhou === '1') {
        // 指値
        this.form.normalPriceLimitReverse.sasinariJyouken = ' '
        this.$refs['periodSelector'].resetComponent()
        this.buttonOptions.period = ''
      } else if (this.form.normalPriceLimitReverse.sasinariHouhou === '2') {
        // 成行
        this.form.normalPriceLimitReverse.sasinariJyouken = 'N'
        this.form.period.periodTerms = false
        this.buttonOptions.period = 'disabled'
      } else {
        // 逆指値
        this.form.normalPriceLimitReverse.sasinariJyouken = ' '
        this.$refs['periodSelector'].resetComponent()
        this.buttonOptions.period = ''
        this.buttonOptions.today = ''
        if (this.form.normalPriceLimitReverse.gyakusasiHouhou === '1') {
          this.form.normalPriceLimitReverse.sasinariJyouken = ' '
        } else if (this.form.normalPriceLimitReverse.gyakusasiHouhou === '2') {
          this.form.normalPriceLimitReverse.sasinariJyouken = 'N'
        }
      }
    },
    changeJyouken(val) {
      if (val === ' ') {
        this.buttonOptions.period = ''
        this.$refs['periodSelector'].resetComponent()
      } else {
        this.buttonOptions.period = 'disabled'
      }
    },
    changeIfd2SasinariHouhou(val) {
      if (val === '1') {
        // 指値
        this.form.ifd2.sasinariJyouken = ' '
        this.$refs['ifd2PeriodSelector'].resetComponent()
        this.ifd2buttonOptions.period = ''
      } else if (val === '2') {
        // 成行
        this.form.ifd2.sasinariJyouken = 'N'
        this.form.ifd2.period.periodTerms = false
        this.ifd2buttonOptions.period = 'disabled'
      } else {
        // 逆指値
        this.form.ifd2.sasinariJyouken = ' '
        this.$refs['ifd2PeriodSelector'].resetComponent()
        this.ifd2buttonOptions.period = ''
        this.ifd2buttonOptions.today = ''
        this.changeIfd2Method(this.form.ifd2.gyakusasiHouhou)
      }
    },
    changeIfd1Method(val) {
      if (this.form.ifd1.sasinariHouhou === '1') {
        // 指値
        this.form.ifd1.sasinariJyouken = ' '
        this.$refs['periodSelector'].resetComponent()
        this.buttonOptions.period = ''
      } else if (this.form.ifd1.sasinariHouhou === '2') {
        // 成行
        this.form.ifd1.sasinariJyouken = 'N'
        this.form.period.periodTerms = false
        this.buttonOptions.period = 'disabled'
      } else {
        // 逆指値
        this.form.ifd1.sasinariJyouken = ' '
        this.$refs['periodSelector'].resetComponent()
        this.buttonOptions.period = ''
        this.buttonOptions.today = ''
        if (this.form.ifd1.gyakusasiHouhou === '1') {
          this.form.ifd1.sasinariJyouken = ' '
        } else {
          this.form.ifd1.sasinariJyouken = 'N'
        }
      }
    },
    changeIfd2Method(val) {
      if (val === '1') {
        this.form.ifd2.sasinariJyouken = ' '
      } else if (val === '2') {
        this.form.ifd2.sasinariJyouken = 'N'
      } else {
        this.form.ifd2.sasinariJyouken = ' '
      }
    },
    changeOco2Method(val) {
      if (val === '1') {
        this.form.oco2.gyakusasiJyouken = ' '
      } else if (val === '2') {
        this.form.oco2.gyakusasiJyouken = 'N'
      } else {
        this.form.oco2.gyakusasiJyouken = ' '
      }
    },
    changeOco1SasinariJyouken(val) {
      if (this.orderMode === '4') {
        // IFDOCO
        if (val === ' ') {
          this.form.oco2.gyakusasiJyouken = ' '
          if (this.form.ifd2.sasinariHouhou === '1') {
            this.$refs['ifd2PeriodSelector'].resetComponent()
            this.ifd2buttonOptions.period = ''
            this.ifd2buttonOptions.today = ''
          }
        } else {
          this.form.oco2.gyakusasiHouhou = '1'
          this.form.oco2.gyakusasiJyouken = 'F'
          this.form.ifd2.period.periodTerms = false
          this.ifd2buttonOptions.period = 'disabled'
          this.ifd2buttonOptions.today = ''
        }
      } else {
        // OCO
        if (val === 'F') {
          this.form.oco2.gyakusasiHouhou = '1'
          this.form.oco2.gyakusasiJyouken = 'F'
          this.form.period.periodTerms = false
          this.buttonOptions.period = 'disabled'
          this.buttonOptions.today = ''
        } else {
          this.form.oco2.gyakusasiJyouken = ' '
          this.$refs['periodSelector'].resetComponent()
          this.buttonOptions.period = ''
          this.buttonOptions.today = ''
        }
      }
    },
    changeIfd2SasinariJyouken(val) {
      if (val === ' ') {
        if (this.form.ifd2.sasinariHouhou === '1') {
          this.$refs['ifd2PeriodSelector'].resetComponent()
          this.ifd2buttonOptions.period = ''
          this.ifd2buttonOptions.today = ''
        }
      } else {
        this.form.ifd2.period.periodTerms = false
        this.ifd2buttonOptions.period = 'disabled'
        this.ifd2buttonOptions.today = ''
      }
    },
    preRequestHandlerA016() {
      if (this.form.orderKind === '1') {
        if (this.A016RequestModel.normalPriceLimitReverseGyakusasiHouhou === '2' || this.A016RequestModel.normalPriceLimitReverseSasinariHouhou === '2') {
          this.A016RequestModel.normalPriceLimitReversePrice = ''
        }
      }
      if (this.form.orderKind === '3' || this.form.orderKind === '4') {
        if (this.A016RequestModel.ifd1GyakusasiHouhou === '2' || this.A016RequestModel.ifd1SasinariHouhou === '2') {
          this.A016RequestModel.ifd1Price = ''
        }
      }
      if (this.form.orderKind === '3') {
        if (this.A016RequestModel.ifd2GyakusasiHouhou === '2' || this.A016RequestModel.ifd2SasinariHouhou === '2') {
          this.A016RequestModel.ifd2Price = ''
        }
      }
    },
    // 内部者取引確認画面 または 注文確認画面 に遷移
    async orderConfirmA016(response) {
      this.confirmData = response.dataList[0]
      if (this.confirmData.requestContents.periodTerms === '1') {
        this.confirmData.requestContents.periodTerms = true
      } else {
        this.confirmData.requestContents.periodTerms = false
      }
      if (this.confirmData.requestContents.ifd2PeriodTerms === '1') {
        this.confirmData.requestContents.ifd2PeriodTerms = true
      } else {
        this.confirmData.requestContents.ifd2PeriodTerms = false
      }
      await this.$nextTick() // 現在の更新を待つためのVueのメソッド
      await this.$refs['ifaDomesticStockOrderConfirm'].onShow()
      this.dialogTableVisible = true
    },
    // 注文確認画面に遷移
    handleShowConfirm() {
      this.dialogInsiderConfirmVisible = false
      this.dialogTableVisible = true
    },
    // 注文完了画面に遷移
    async handleOrderFinish(response) {
      this.confirmResponseData = response
      this.dialogTableVisible = false
      this.dialogFinish = true
    },
    // 注文一覧画面に遷移
    handleMoveToOrderList() {
      this.resetBrandSearch()
      this.dialogFinish = false
      this.$_startShowMenu('SUB0202_0104')
    },
    // 戻る
    async handleCloseModal() {
      this.dialogInsiderConfirmVisible = false
      this.dialogTableVisible = false
      this.dialogFinish = false
    },
    // 取引種別が選択/変更された
    changeTradeType() {
      if (this.form.tradeCd === '1') {
        this.bgColor = '#fef0f0'
        this.orderStyle.title = '現物売却'
        this.orderStyle.color = '#00847F'
        if (this.depositType === '2') {
          this.depositType = ''
        }
      } else if (this.form.tradeCd === '2') {
        this.bgColor = '#ecf5ff'
        this.orderStyle.title = '現物買付'
        this.orderStyle.color = '#E5004C'
      }
    },
    // 銘柄検索(子コンポーネント)からのイベント処理
    handleChangeStock(param) {
      if (param.id === '1') {
        // [イベントID: 1] 銘柄が選択された
        this.stockInfo.data = { ...param.data }
        this.form.brandCode = param.data.brandCode
        this.stockInfo.name = param.data.brandName
        // selectMarketList は､mktSor, upperRankMkt が上位に来るようにソート済み
        this.form.market = this.$refs['ifaBrandSearch'].selectedMarket
        this.priorityMarket = param.data.upperRankMkt
        this.originalMarketList = param.data.selectMarketList
        this.showDisplayDetailButton = true
        this.getMethodSelectPattern(this.$refs['ifaBrandSearch'].selectedMarket)
        this.getOrderKindSelectPattern(this.$refs['ifaBrandSearch'].selectedMarket)
        this.$refs['ifaBrandPriceInfo'].updateRequest()
        // this.updateStockInfo()
        if (this.source === '') {
          this.source = 'searchBrand'
          // this.form.tradeCd = '1'
          // this.changeTradeType()
        }
        if (this.$refs['ifaBrandSearch'].selectedMarket === 'A') {
          this.form.selectedMarket = param.data.upperRankMkt
        } else {
          this.form.selectedMarket = this.$refs['ifaBrandSearch'].selectedMarket
        }
        if (this.$refs['ifaBrandSearch'].selectedMarket === '7') {
          // PTS
          this.buttonOptions.period = 'hide'
          this.form.period.periodTerms = false
        } else {
          this.buttonOptions.period = ''
          this.$refs['periodSelector'].resetComponent()
        }
      } else if (param.id === '2') {
        // [イベントID: 2] 市場が変更された
        this.form.normalPriceLimitReverse.sasinariHouhou = '1'
        this.form.normalPriceLimitReverse.sasinariJyouken = ' '
        this.form.ifd1.sasinariHouhou = '1'
        this.form.ifd1.sasinariJyouken = ' '
        this.form.market = param.data
        this.getMethodSelectPattern(param.data)
        this.$refs['ifaBrandPriceInfo'].updateRequest()
        if (param.data === 'A') {
          this.form.selectedMarket = this.priorityMarket
        } else {
          this.form.selectedMarket = param.data
        }
        if (param.data === '7') {
          // PTS
          this.buttonOptions.period = 'hide'
          this.form.period.periodTerms = false
        } else {
          this.buttonOptions.period = ''
          this.$refs['periodSelector'].resetComponent()
        }
      } else if (param.id === '3') {
        // [イベントID: 3] クリアボタン押下時
        this.resetAll()
        this.$refs['ifaBrandPriceInfo'].initData()
      }
      document.getElementById('ifaDomesticStockOrderInputBrandSelectionMarketSelectionA002').click()
      this.display()
    },
    // 銘柄時価情報(子コンポーネント)からのイベント処理
    handleChangePrice(param) {
      this.priceInfo = param
      this.stockInfo.unit = param.unit
      if (this.form.tradeCd === '1') {
        this.stockInfo.limitLow = Number(param.buyStopLow)
        this.stockInfo.limitHigh = Number(param.buyStopHigh)
      } else {
        this.stockInfo.limitLow = Number(param.sellStopLow)
        this.stockInfo.limitHigh = Number(param.sellStopHigh)
      }
      this.stockInfo.limitDate = param.baseDate
      if (Number(param.currentPrice) !== 0 && !isNaN(Number(param.currentPrice))) {
        this.stockInfo.initialStep = Number(param.currentPrice)
      } else if (Number(param.basePrice) !== 0 && !isNaN(Number(param.basePrice))) {
        this.stockInfo.initialStep = Number(param.basePrice)
      } else {
        this.stockInfo.initialStep = 0
      }
      this.priceInfo.orderPriceUnit = param.orderPriceUnit
    },
    resetBrandSearch() {
      this.$refs['ifaBrandSearch'].resetAll()
      this.resetAll()
    },
    // 口座や預り区分に応じた現保有株式をセットする
    updateStockInfo() {
      this.ownStockInfo = phonyOwnStockInfo
      if (this.form.depositType === '') {
        return
      }

      const ds = this.form.account === '0'
        ? undecidedBalanceList.getJrNisa().domesticStock
        : undecidedBalanceList.getGeneric().domesticStock

      const items = this.form.depositType === '0'
        ? ds.nisaDeposit
        : this.form.depositType === '1'
          ? ds.specialDeposit
          : ds.deposit

      for (const item of items) {
        if (item.code === this.form.brandCode) {
          this.ownStockInfo = item
          break
        }
      }
    },
    // タブが表示された時にフォーカスを取引種別に合わせる
    onShow(resume, isRouting) {
      if (isRouting) {
        const params = this.$_getMenuParams()
        if (params !== null) {
          this.params = { ...params }
          this.resetAll()
          this.$refs['ifaBrandSearch'].resetAll()
          this.source = 'other'
          this.form.brandCode = params.brandCode
          this.form.tradeCd = params.tradeKbn
          this.form.depositType = params.depositType
          this.depositTypeParamFlag = true
          this.$nextTick(() => {
            this.$refs['ifaBrandSearch'].handleRowClick(params)
            this.changeTradeType()
          })
        }
      } else {
        this.params = null
        this.resetAll()
        this.$refs['ifaBrandSearch'].resetAll()
        this.$refs['ifaBrandPriceInfo'].initData()
      }
      this.$nextTick(() => {
        document.getElementById('ifaDomesticStockOrderInputInitializeA001').click()
      })
    },
    display() {
      this.$nextTick(() => {
        document.getElementById('ff').focus()
        // 預り区分パラメータの指定がある場合
        if (this.depositTypeParamFlag) {
          if (jrNisaDepositTypes.includes(this.form.depositType)) {
            // ジュニアNISA口座
            this.form.account = '1'
          } else {
            // 総合口座
            this.form.account = ' '
          }
        // 預り区分パラメータの指定がない場合 かつ 顧客共通情報.ジュニアISA契約区分 !=1：契約の場合
        } else if (this.customerInfo.jrIsaContractType !== '1') {
          // 総合口座
          this.form.account = ' '
        } else {
          // 初期値 ジュニアNISA口座
          this.form.account = '1'
        }
      })
    },
    // 注文種別変更時処理
    changeOrderFormat(value) {
      this.orderMode = value
      this.setBgColor(value)
      this.keepLimitOrderPrice(value)
      this.setOcoIndentStyle(value)
      this.$nextTick(() => {
        const val = this.form.normalPriceLimitReverse.sasinariHouhou
        if (val === '1') {
          this.$refs['periodSelector'].resetComponent()
          this.buttonOptions.period = ''
        } else if (val === '2') {
          this.form.period.periodTerms = false
          this.buttonOptions.period = 'disabled'
        } else {
          this.$refs['periodSelector'].resetComponent()
          this.buttonOptions.period = ''
          this.buttonOptions.today = ''
        }
        this.changeJyouken(this.form.normalPriceLimitReverse.sasinariJyouken)
      })
      if (value === '1') {
        if (this.originalMarketList) {
          this.customMarketList = []
          this.$refs['ifaBrandSearch'].internalMarketList = this.originalMarketList
          // 初期化処理の時に、this.originalMarketList[0]がundefinedでエラーが出ないようにする
          if (this.originalMarketList.length > 0) {
            this.$refs['ifaBrandSearch'].selectedMarket = this.originalMarketList[0].key
            this.form.market = this.originalMarketList[0].key
          }
        }
      }
      if (value === '2') {
        // OCO
        this.buttonOptions.period = ''
        this.buttonOptions.today = ''
        this.marketList = [
          { 'key': '0', 'value': '東証' }
        ]
        this.$refs['ifaBrandSearch'].internalMarketList = this.marketList
        this.$refs['ifaBrandSearch'].selectedMarket = '0'
        this.form.market = this.$refs['ifaBrandSearch'].selectedMarket
        this.$nextTick(() => {
          const val = this.form.oco1.sasinariJyouken
          if (val === 'F') {
            this.form.period.periodTerms = false
            this.buttonOptions.period = 'disabled'
            this.buttonOptions.today = ''
          } else {
            this.$refs['periodSelector'].resetComponent()
            this.buttonOptions.period = ''
            this.buttonOptions.today = ''
          }
        })
      }
      if (value === '3') {
        // IFD
        this.buttonOptions.period = ''
        this.buttonOptions.today = ''
        this.ifd2buttonOptions.period = ''
        this.ifd2buttonOptions.today = ''
        this.marketList = [
          { 'key': '0', 'value': '東証' },
          { 'key': '6', 'value': '福証' },
          { 'key': '8', 'value': '札証' },
          { 'key': '2', 'value': '名証' }
        ]
        this.$refs['ifaBrandSearch'].internalMarketList = this.marketList
        this.$refs['ifaBrandSearch'].selectedMarket = '0'
        this.form.market = this.$refs['ifaBrandSearch'].selectedMarket
        this.$nextTick(() => {
          const val = this.form.ifd1.sasinariHouhou
          if (val === '1') {
            this.$refs['periodSelector'].resetComponent()
            this.buttonOptions.period = ''
          } else if (val === '2') {
            this.form.period.periodTerms = false
            this.buttonOptions.period = 'disabled'
          } else {
            this.$refs['periodSelector'].resetComponent()
            this.buttonOptions.period = ''
            this.buttonOptions.today = ''
          }
          this.changeJyouken(this.form.ifd1.sasinariJyouken)
          if (this.form.ifd2.sasinariHouhou === '1') {
            this.$refs['ifd2PeriodSelector'].resetComponent()
            this.ifd2buttonOptions.period = ''
          } else if (this.form.ifd2.sasinariHouhou === '2') {
            this.form.ifd2.period.periodTerms = false
            this.ifd2buttonOptions.period = 'disabled'
          } else {
            this.$refs['ifd2PeriodSelector'].resetComponent()
            this.ifd2buttonOptions.period = ''
            this.ifd2buttonOptions.today = ''
          }
          this.changeIfd2SasinariJyouken(this.form.ifd2.sasinariJyouken)
        })
      }
      if (value === '4') {
        // IFDOCO
        this.buttonOptions.period = ''
        this.buttonOptions.today = ''
        this.ifd2buttonOptions.period = ''
        this.ifd2buttonOptions.today = ''
        this.marketList = [
          { 'key': '0', 'value': '東証' }
        ]
        this.$refs['ifaBrandSearch'].internalMarketList = this.marketList
        this.$refs['ifaBrandSearch'].selectedMarket = '0'
        this.form.market = this.$refs['ifaBrandSearch'].selectedMarket
        this.$nextTick(() => {
          if (this.form.ifd1.sasinariHouhou === '1') {
            this.$refs['periodSelector'].resetComponent()
            this.buttonOptions.period = ''
          } else if (this.form.ifd1.sasinariHouhou === '2') {
            this.form.period.periodTerms = false
            this.buttonOptions.period = 'disabled'
          } else {
            this.$refs['periodSelector'].resetComponent()
            this.buttonOptions.period = ''
            this.buttonOptions.today = ''
          }
          this.changeJyouken(this.form.ifd1.sasinariJyouken)
          if (this.form.oco1.sasinariJyouken === ' ') {
            if (this.form.ifd2.sasinariHouhou === '1') {
              this.$refs['ifd2PeriodSelector'].resetComponent()
              this.ifd2buttonOptions.period = ''
              this.ifd2buttonOptions.today = ''
            }
          } else {
            this.form.ifd2.period.periodTerms = false
            this.ifd2buttonOptions.period = 'disabled'
            this.ifd2buttonOptions.today = ''
          }
        })
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
      if (value === '0' || value === '1') {
        // OCO1 の価格に注文種別 = 「通常/逆指値選択」状態の値をコピーする
        // IFDOCO 選択時に消えてしまうので、復活させる
        this.form.oco1.price = this.form.normalPriceLimitReverse.price
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
    updateNum(value) {
      // 通常/逆指値 : 価格にセットする
      // OCO : OCO1の価格にセットする
      // IFD : IFD1の価格にセットする
      // IFDOCO : IFD1の価格にセットする
      this.form.normalPriceLimitReverse.price = Number(value.replace(',', ''))
      if (this.orderMode === '2') {
        // 注文種別が OCO のとき
        this.form.oco1.price = Number(value.replace(',', ''))
      }
      // 株式詳細情報のポップアップを非表示にする
      this.displayStockBoard = false
    },
    // リセットボタン処理
    resetAll() {
      this.$refs.form.clearValidate()
      this.stockInfo.data = null
      this.form.brandCode = ''
      this.stockInfo.name = ''
      this.stockInfo.unit = 100
      this.stockInfo.current = ''
      this.stockInfo.priceQuotation = 1
      this.stockInfo.limitDate = '--/--/--'
      this.stockInfo.limitHigh = '-'
      this.stockInfo.limitLow = '-'
      this.stockInfo.validMarkets = [true, true, true, true, true, true, true]
      this.showDisplayDetailButton = false
      this.depositTypeParamFlag = false

      this.form.market = '0'
      this.priorityMarket = '0'
      this.marketList = []

      this.resetForm()
    },
    // リセットボタン処理
    resetForm(formName = 'form') {
      if (this.params) {
        this.form.account = this.params.accountType
        this.form.tradeCd = this.params.tradeKbn
        this.form.depositType = this.params.depositType
        this.depositTypeParamFlag = true
        this.$refs['ifaBrandSearch'].handleRowClick(this.params)
        this.changeTradeType()
      } else {
        this.form.account = '0'
        this.form.tradeCd = '1'
        this.form.depositType = ''
        this.depositTypeParamFlag = false
        this.source = ''
        this.bgColor = '#fef0f0'
        this.ownStockInfo = phonyOwnStockInfo
      }
      if (this.$refs['periodSelector']) { this.$refs['periodSelector'].resetComponent() }
      if (this.$refs['ifd2PeriodSelector']) { this.$refs['ifd2PeriodSelector'].resetComponent() }
      if (this.buttonOptions.period !== 'hide') {
        this.buttonOptions.period = ''
      }
      if (this.ifd2buttonOptions !== 'hide') {
        this.ifd2buttonOptions.period = ''
      }
      this.form.quantity = ''
      this.form.normalPriceLimitReverse.sasinariHouhou = '1'
      this.form.normalPriceLimitReverse.sasinariJyouken = ' '
      this.form.normalPriceLimitReverse.price = ''
      this.form.normalPriceLimitReverse.triggerPrice = ''
      this.form.normalPriceLimitReverse.gyakusasiHouhou = '1'
      this.form.ifd1.sasinariHouhou = '1'
      this.form.ifd1.sasinariJyouken = ' '
      this.form.ifd1.gyakusasiHouhou = '1'
      this.form.ifd1.price = ''
      this.form.ifd1.triggerPrice = ''
      this.form.ifd2.sasinariHouhou = '1'
      this.form.ifd2.sasinariJyouken = ' '
      this.form.ifd2.triggerPrice = ''
      this.form.ifd2.gyakusasiHouhou = '1'
      this.form.ifd2.price = ''
      this.form.ifd2.period.periodTerms = null
      this.form.ifd2.period.limit = ''
      this.form.oco1.sasinariHouhou = '1'
      this.form.oco1.sasinariJyouken = ' '
      this.form.oco1.price = ''
      this.form.oco2.sasinariHouhou = '3'
      this.form.oco2.triggerPrice = ''
      this.form.oco2.gyakusasiHouhou = '1'
      this.form.oco2.gyakusasiJyouken = ' '
      this.form.oco2.price = ''
      this.form.orderType = '0'
      this.changeOrderFormat('0')
      this.form.confirmItem.insider = '0'
      this.form.confirmItem.sor = '0'
      this.form.kanyuKbn = ''
      this.form.receiveOrderType = ''
      this.form.orderKind = '1'
      this.form.period.periodTerms = null
      this.form.period.limit = ''
      // Note: resetFields() だとタイミングによりエラーが発生するため､clearValidate() を使用
      // [NGケース] this.$refs[formName].resetFields()
      // [OKケース] ↓
      // this.$refs[formName]?.fields.forEach(e => {
      //   e.clearValidate()
      // })
      this.display()
      this.$refs.form.clearValidate()
    },
    getIDforTopElement(tradeType) {
      if (this.hasJrNisaAccount) {
        // ジュニアNISA口座がある場合は､選択されている口座に初期フォーカスさせる
        if (tradeType === 2) {
          return this.form.account === '0' ? 'ff' : 'x'
        } else if (tradeType === 3) {
          return this.form.account === '1' ? 'ff' : 'x'
        }
      } else {
        // ジュニアNISA口座がない場合は､数量にフォーカスさせる
        if (tradeType === 0) {
          return 'ff'
        }
      }
      return 'x'
    },
    // 数値をコンマを加えて3桁区切りにする
    addComma(num) {
      return Number(num).toLocaleString()
    }
  }
}
const phonyOwnStockInfo = {
  volume: '-',
  averagePrice: 0,
  sellingVolume: '-'
}
</script>

<style lang="scss" scoped>
@import '@/styles/orderStatusList.scss';
:deep(.el-tabs_full_content) .el-tabs__content {
  padding: 0;
}
.form-radio {
  width: 4rem;
}
.form-radio__auto {
  width: auto;
}
.form-area__section {
  height: auto;
  padding: 0.5rem 0;
  border-bottom: 1px solid #eee;
}
.form-area__period.form-area__section {
  padding-bottom: 0;
}
:deep(.charge-type) .el-form-item__label {
  color: red;
}
:deep(.el-form-item__label) {
  padding-right: 3rem;
  font-weight: 700;
}
.error-message {
  margin: 0.5rem;
  padding-left: 4rem;
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
.custom-loading-text {
  color: white;
  font-size: 20px;
}
:deep(.el-table--mini) td,
.el-table--mini th {
  padding: 0;
  height: 5px;
}
:deep(.el-table) td,
.el-table th {
  padding: 0;
  height: 5px;
}
.form-area__select {
  width: calc(100% - 1.5rem);
  min-width: 140px;
}
:deep(.form-area__input_select) {
  width: calc(100% - 1.5rem);
}
.limit-labal {
  margin-left: 1rem;
  white-space: nowrap;
  line-height: 14px;
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
// .form-container {
//   display: flex;
//   justify-content: space-between;
// }
// .form-main {
//   width: 85%;
//   margin-left: 3%;
// }
.form-area__checkbox {
  margin-top: 0.5rem;
}
:deep(.el-form-item__error) {
  white-space: nowrap;
}
.el-form-item__error_custom-margin :deep(.el-form-item__error) {
  margin-top: -0.7rem;
  margin-bottom: 5px;
}
.jrnisa_warning {
  color: #f00;
  line-height: 1rem;
  padding-left: 1rem;
  margin-top: 4px;
}
.order-button {
  margin-top: 1rem;
}
.form-reset-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -2.3rem 0 0.5rem 0;
  padding-right: 1rem;
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
  width: 145px;
  margin-left: 10px;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
.account_form {
  width: 70%
}
.date-picker :deep(.el-input) {
  width: 200px;
  margin-left: 12px;
}
:deep(.date-picker) .el-form-item__content{
  display: flex !important;
}
</style>
