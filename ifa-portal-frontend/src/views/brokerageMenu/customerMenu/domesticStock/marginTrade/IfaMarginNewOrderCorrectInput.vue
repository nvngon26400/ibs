<template>
  <div>
    <!-- 訂正入力ダイアログ -->
    <el-dialog
      v-model="vmIsVisible"
      class="margin_new_order_correct"
      title="信用新規注文訂正入力"
      width="1220px"
      :style="(formModel.orderKind === '3' || formModel.orderKind === '4') ? 'background-color: #E7DCF2' : formModel.tradeCd === '3' ? 'background-color: #fef0f0' : 'background-color: #ecf5ff'"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      :destroy-on-close="true"
      @opened="onOpen"
    >

      <!-- 戻るボタン -->
      <div class="header-button">
        <ifa-button
          text="戻る"
          color="secondary"
          action-type="originalAction"
          style="padding-right: 0;"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </div>

      <!-- 口座エリア -->
      <el-row
        style="font-weight: bold;"
      >
        <el-col>
          <span>{{ $_out(customerInfo().butenCode) + '-' + $_out($_zeroPadding(customerInfo().accountNumber,7)) }}</span>
        </el-col>
      </el-row>
      <el-row
        style="padding-top: 0.2rem;"
        class="_bold_black_l"
      >
        <el-col
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
          ></ifa-notice-info>
        </el-col>
      </el-row>

      <!-- 銘柄エリア -->
      <el-card
        style="background-color: #eee; margin-bottom: 0.5rem; margin-top: 0.3rem;"
      >
        <!-- 銘柄検索 -->
        <el-row
          :style="{
            display: 'flex',
            justifyContent: 'space-between',
            flexDirection: 'row'
          }"
        >
          <div style="flex: 0 0 4.16%;"></div>
          <div
            style="flex: 1;"
          >
            <ifa-brand-search
              ref="ifaBrandSearch"
              :is-code-lock="true"
              :trading-type="1"
              :market-list="[ { key: formModel.latestMarketId, value: formModel.latestMarketId } ]"
              @change="handleChangeBrand"
            ></ifa-brand-search>
          </div>
          <div
            style="flex: 0 0 200px; margin-left: 32px;"
          >
            <ifa-button
              text="詳細"
              icon="Document"
              small
              action-id="SUB0202_0208-02#A001"
              action-type="requestAction"
              style="margin-right: 12px;"
              :request-model="IfaStockDetailInfoA001RequestModel"
              @response-handler="responseHandlerA003"
            ></ifa-button>
            <ifa-button
              text="更新"
              icon="RefreshRight"
              small
              action-id="SUB0202_0212-02_1#A004"
              action-type="requestAction"
              :request-model="IfaMarginNewOrderCorrectInputA004RequestModel"
              @response-handler="responseHandlerA004"
            ></ifa-button>
          </div>
        </el-row>
        <!-- 銘柄時価情報エリア -->
        <ifa-brand-price-info
          ref="ifaBrandPriceInfo"
          :brand-code="formModel.cc014.brandCode"
          :market="formModel.cc014.selectedMarket"
          @change="handleChangeBrandPrice"
        ></ifa-brand-price-info>

        <!-- 新規建余力エリア -->
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
              <div class="info-item__header __right">{{ $_out($_getFormattedDateValue(formModel.settlementDate0)) }}(当日)</div>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="info_xs">
              <div class="info-item__header __right">{{ $_out($_getFormattedDateValue(formModel.settlementDate1)) }}(翌営業日)</div>
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
              <div class="info-item__value __right">{{ $_withCommaInteger(fomatNumber(formModel.marginCapacity0)) }}円</div>
            </div>
          </el-col>
          <el-col
            :span="4"
            :pull="1"
          >
            <div class="info_xs">
              <div class="info-item__value __right">{{ $_withCommaInteger(fomatNumber(formModel.marginCapacity1)) }}円</div>
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
              <div class="info-item__value __right">{{ $_out($_withCommaZeroPadding(formatToDecimal(formModel.actualGrntRate0), 2)) }}%</div>
            </div>
          </el-col>
          <el-col
            :span="4"
            :pull="1"
          >
            <div class="info_xs">
              <div class="info-item__value __right">{{ $_out($_withCommaZeroPadding(formatToDecimal(formModel.actualGrntRate1), 2)) }}%</div>
            </div>
          </el-col>
        </el-row>
      </el-card>

      <!-- 注文訂正入力エリア -->
      <el-form
        ref="orderForm"
        :model="formModel"
        :rules="rules"
        label-width="180px"
        label-position="left"
        style="line-height: 2;"
      >

        <el-row>
          <el-col :span="24">

            <!-- 注文種別=通常/逆指値のとき表示 -->
            <div v-if="formModel.orderKind === '1'">
              <!-- 取引種別-->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="取引種別">
                      <span :class="formModel.tradeCd === '3' ? 'font-color__plus bold' : 'font-color__minus bold'">{{ $_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, formModel.tradeCd) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 数量(未約定) -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="数量(未約定)">
                      <span>{{ $_out($_withCommaInteger(formModel.orderQuantity)) + '(' + $_out($_withCommaInteger(formModel.unTradeQuantity)) + ')株' }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 訂正前価格/執行方法 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="価格">
                      <el-row>
                        <el-col>
                          <span>{{ formModel.correctBeforePriceSasinariHouhou? $_getCodeValue('EXECUTE_METHOD', 1, formModel.correctBeforePriceSasinariHouhou) : '-' }}</span>
                        </el-col>
                      </el-row>
                      <el-row>
                        <!-- 指値 -->
                        <el-col v-if="formModel.correctBeforePriceSasinariHouhou === '1'">
                          <span v-if="formModel.correctBeforePriceSasinariJyouken !== ' '">{{ formModel.correctBeforePriceSasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceSasinariJyouken) : '-' }}/</span>
                          <span>{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePricePrice.trim())) }}円</span>
                        </el-col>
                        <!-- 成行 -->
                        <el-col v-if="formModel.correctBeforePriceSasinariHouhou === '2'">
                          <span v-if="formModel.correctBeforePriceSasinariJyouken !== 'N'">{{ formModel.correctBeforePriceSasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 3, formModel.correctBeforePriceSasinariJyouken) : '-' }}</span>
                        </el-col>
                        <!-- 逆指値 -->
                        <el-col v-if="formModel.correctBeforePriceSasinariHouhou === '3'">
                          <span>現在値が{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceTriggerPrice.trim())) }}円</span>
                          <span v-if="formModel.correctBeforePriceTriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                          <span v-else>{{ formModel.correctBeforePriceTriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceTriggerPriceZone) : '-' }}になった時点で</span>
                          <span v-if="formModel.correctBeforePriceGyakusasiHouhou === '1' && formModel.correctBeforePriceSasinariJyouken !== ' '">{{ formModel.correctBeforePriceSasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceSasinariJyouken) : '-' }}</span>
                          <span v-if="formModel.correctBeforePriceGyakusasiHouhou === '1'">{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePricePrice.trim())) }}円</span>
                          <span v-if="formModel.correctBeforePriceGyakusasiHouhou === '2'">{{ formModel.correctBeforePriceSasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 1, formModel.correctBeforePriceSasinariJyouken) : '-' }}</span>
                          <span>で執行</span>
                        </el-col>
                      </el-row>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 訂正価格/執行方法 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <!-- 執行方法ドロップダウンリスト -->
                    <el-row>
                      <el-col :span="8">
                        <ifa-input-select
                          v-model="formModel.sasinariHouhou"
                          label="訂正価格/執行方法"
                          prop="sasinariHouhou"
                          :required="true"
                          :disabled="editDisable"
                          code-list-id="original"
                          :original-list="sasinariHouhouDropDownList"
                          style="width: 100px;"
                          @change="handlerA005"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 指値  執行方法（通常/逆指値）=指値のとき表示 -->
                      <!-- 執行条件（指値） -->
                      <el-col v-if="formModel.sasinariHouhou === '1'"
                              :span="3"
                              style="margin-right: 10px;margin-left: -10px;"
                      >
                        <ifa-input-select
                          v-if="formModel.sasinariHouhou === '1'"
                          v-model="formModel.sasinariJyouken"
                          :clearable="false"
                          :disabled="true"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="2"
                          :select-pattern="2"
                          style="width: 120px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 注文単価（指値） -->
                      <el-col v-if="formModel.sasinariHouhou === '1'"
                              :span="6"
                      >
                        <ifa-input-price
                          :key="priceInfo.orderPriceUnit"
                          v-model="formModel.price"
                          prop="price"
                          :required="true"
                          :min="1"
                          :max="maxValueValidator"
                          :digit="1"
                          :domain="IfaUnitPriceYen102DigitsBDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                          support
                          size="small"
                        ></ifa-input-price>
                      </el-col>
                      <!-- 制限値幅（指値） -->
                      <el-row
                        v-if="formModel.sasinariHouhou === '1' && (priceLow == '1.0' && priceHigh == '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>値幅制限：なし</span>
                        </el-col>
                      </el-row>
                      <el-row
                        v-else-if="formModel.sasinariHouhou === '1' && (priceLow != '1.0' || priceHigh != '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>
                            値幅制限：{{ $_out($_withCommaZeroPadding(priceLow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                          </span>
                        </el-col>
                      </el-row>
                      <!-- 成行 執行方法（通常/逆指値）=成行のとき表示 -->
                      <!-- 執行条件（成行） -->
                      <el-col v-if="formModel.sasinariHouhou === '2'"
                              :span="3"
                              style="margin-right: 10px; margin-left: -10px;"
                      >
                        <ifa-input-select
                          v-if="formModel.sasinariHouhou === '2'"
                          v-model="formModel.sasinariJyouken"
                          :clearable="false"
                          :disabled="true"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="3"
                          :select-pattern="3"
                          style="width: 120px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 制限値幅（成行） -->
                      <el-row
                        v-if="formModel.sasinariHouhou === '2' && (priceLow == '1.0' && priceHigh == '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>値幅制限：なし</span>
                        </el-col>
                      </el-row>
                      <el-row
                        v-else-if="formModel.sasinariHouhou === '2' && (priceLow != '1.0' || priceHigh != '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>
                            値幅制限：{{ $_out($_withCommaZeroPadding(priceLow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                          </span>
                        </el-col>
                      </el-row>
                      <!-- 逆指値 執行方法（通常/逆指値）=逆指値のとき表示 -->
                      <!-- 発火条件価格（逆指値） -->
                      <el-col v-if="formModel.correctBeforePriceSasinariHouhou === '3'"
                              :span="2"
                              style="margin-right: 10px; margin-left: -10px;"
                      >
                        <span>現在値が</span>
                      </el-col>
                      <el-col v-if="formModel.sasinariHouhou === '3'"
                              :span="6"
                      >
                        <ifa-input-price
                          :key="priceInfo.orderPriceUnit"
                          v-model="formModel.triggerPrice"
                          prop="triggerPrice"
                          :required="true"
                          :min="1"
                          :max="maxValueValidator"
                          :digit="1"
                          :disabled="formModel.workingStatus"
                          :domain="IfaCurrency152DigitsBDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                          support
                          size="small"
                        ></ifa-input-price>
                      </el-col>
                      <el-col v-if="formModel.correctBeforePriceSasinariHouhou === '3'"
                              :span="3"
                      >
                        <span v-if="formModel.correctBeforePriceTriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                        <span v-else>{{ formModel.correctBeforePriceTriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceTriggerPriceZone) : '-' }}になった時点で</span>
                      </el-col>
                      <!-- 執行方法（逆指値） -->
                      <el-row v-if="formModel.correctBeforePriceSasinariHouhou === '3'"
                              style="margin-top: 1rem;"
                      >
                        <el-col :span="3"
                                style="margin-left: 32%;"
                        >
                          <ifa-input-select
                            v-model="formModel.gyakusasiHouhou"
                            prop="gyakusasiHouhou"
                            :clearable="false"
                            :disabled="gyakusasiHouhouEditDisable"
                            code-list-id="original"
                            :original-list="gyakusasiHouhouDropDownList"
                            style="width: 100px;"
                            @change="handlerA008"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.gyakusasiHouhou === '1'"
                                :span="3"
                                style="margin-right: 10px; margin-left: -10px;"
                        >
                          <ifa-input-select
                            v-if="formModel.gyakusasiHouhou === '1'"
                            v-model="formModel.sasinariJyouken"
                            :disabled="true"
                            :clearable="false"
                            :code-list-id="'LIMIT_MARKET_TYPE'"
                            :disp-pattern="2"
                            :select-pattern="4"
                            style="width: 120px;"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.gyakusasiHouhou === '2'"
                                :span="3"
                                style="margin-right: 10px; margin-left: -10px;"
                        >
                          <ifa-input-select
                            v-if="formModel.gyakusasiHouhou === '2'"
                            v-model="formModel.sasinariJyouken"
                            :disabled="true"
                            :clearable="false"
                            :code-list-id="'LIMIT_MARKET_TYPE'"
                            :disp-pattern="3"
                            :select-pattern="5"
                            style="width: 120px;"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.gyakusasiHouhou === '1'"
                                :span="6"
                        >
                          <ifa-input-price
                            :key="priceInfo.orderPriceUnit"
                            v-model="formModel.price"
                            prop="price"
                            :required="true"
                            :min="1"
                            :max="maxValueValidator"
                            :digit="1"
                            :domain="IfaUnitPriceYen102DigitsBDomainModel"
                            :initial-step="initialStep"
                            :step-table="priceInfo.orderPriceUnit"
                            unit="円"
                            support
                            size="small"
                          ></ifa-input-price>
                        </el-col>
                        <el-col :span="2">
                          <span>で執行</span>
                        </el-col>
                        <!-- 制限値幅（指値） -->
                        <el-row
                          v-if="priceLow == '1.0' && priceHigh == '9999999999999.9'"
                          style="padding-top: 0.5rem; width: 97.8%;"
                        >
                          <!-- 空白で6列占有 -->
                          <el-col :span="14"></el-col>
                          <!-- 値幅制限が残りのスペースを占有 -->
                          <el-col :span="10">
                            <span>値幅制限：なし</span>
                          </el-col>
                        </el-row>
                        <el-row
                          v-else-if="priceLow != '1.0' || priceHigh != '9999999999999.9'"
                          style="padding-top: 0.5rem; width: 97.8%;"
                        >
                          <!-- 空白で6列占有 -->
                          <el-col :span="14"></el-col>
                          <!-- 値幅制限が残りのスペースを占有 -->
                          <el-col :span="10">
                            <span>
                              値幅制限：{{ $_out($_withCommaZeroPadding(priceLow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                            </span>
                          </el-col>
                        </el-row>
                      </el-row>
                    </el-row>
                  </div>
                </el-col>
              </el-row>
              <!-- 市場ドロップダウンリスト -->
              <el-row>
                <el-col>
                  <div
                    class="form-area__section"
                  >
                    <el-row v-if="isMarketVisible">
                     <el-col :span="8">
                        <ifa-input-select
                          v-model="formModel.afterCorrectMarket"
                         label="市場"
                         prop="afterCorrectMarket"
                         :required="true"
                         code-list-id="SELECT_MARKET"
                         :disp-pattern="1"
                         :select-pattern="3"
                         style="width: 200px;"
                        ></ifa-input-select>
                      </el-col>
                    </el-row>
                  </div>
                </el-col>
              </el-row>
              <!-- 注文期間 -->
              <el-row>
                <el-col>
                  <div 
                    v-if="isPeriodVisible"
                    class="form-area__section"
                  >
                    <el-form-item label="注文期間">
                      <span>{{ $_out($_getFormattedDateValue(formModel.period, 'date8')) }}</span>
                    </el-form-item>
                  </div>

                  <div 
                    v-if="isPeriodTodayVisible"
                    class="form-area__section"
                  >
                    <el-form-item label="注文期間">
                      <span>当日中</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 信用取引区分 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="信用取引区分">
                      <span>{{ $_out(formModel.marginTradeTypeTextCalculation) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
            </div>
            <!-- 注文種別=OCOのとき表示 -->
            <div v-if="formModel.orderKind === '2'">
              <!-- 取引種別-->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="取引種別">
                      <span :class="formModel.tradeCd === '3' ? 'font-color__plus bold' : 'font-color__minus bold'">{{ $_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, formModel.tradeCd) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 数量 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="数量(未約定)">
                      <span>{{ $_out($_withCommaInteger(formModel.orderQuantity)) + '(' + $_out($_withCommaInteger(formModel.unTradeQuantity)) + ')株' }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- OCO1見出し -->
              <div v-if="!formModel.workingStatus"
                   :style="styleSeparator"
              >
                <span class="ifd-oco-label">OCO1</span>
              </div>
              <!-- 訂正前価格/執行方法 -->
              <el-row v-if="!formModel.workingStatus">
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="価格">
                      <el-row>
                        <el-col>
                          <span>{{ formModel.correctBeforePriceOco1SasinariHouhou? $_getCodeValue('EXECUTE_METHOD', 1, formModel.correctBeforePriceOco1SasinariHouhou) : '-' }}</span>
                        </el-col>
                      </el-row>
                      <el-row>
                        <!-- 指値 -->
                        <el-col v-if="formModel.correctBeforePriceOco1SasinariHouhou === '1'">
                          <span v-if="formModel.correctBeforePriceOco1SasinariJyouken !== ' '">{{ formModel.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceOco1SasinariJyouken) : '-' }}/</span>
                          <span>{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceOco1Price.trim())) }}円</span>
                        </el-col>
                        <!-- 成行 -->
                        <el-col v-if="formModel.correctBeforePriceOco1SasinariHouhou === '2'">
                          <span v-if="formModel.correctBeforePriceOco1SasinariJyouken !== 'N'">{{ formModel.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 3, formModel.correctBeforePriceOco1SasinariJyouken) : '-' }}</span>
                        </el-col>
                        <!-- 逆指値 -->
                        <el-col v-if="formModel.correctBeforePriceOco1SasinariHouhou === '3'">
                          <span>現在値が{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceOco1TriggerPrice.trim())) }}円</span>
                          <span v-if="formModel.correctBeforePriceOco1TriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                          <span v-else>{{ formModel.correctBeforePriceOco1TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceOco1TriggerPriceZone) : '-' }}になった時点で</span>
                          <span v-if="formModel.correctBeforePriceOco1GyakusasiHouhou === '1' && formModel.correctBeforePriceOco1SasinariJyouken !== ' '">{{ formModel.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceOco1SasinariJyouken) : '-' }}</span>
                          <span v-if="formModel.correctBeforePriceOco1GyakusasiHouhou === '1'">{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceOco1Price.trim())) }}円</span>
                          <span v-if="formModel.correctBeforePriceOco1GyakusasiHouhou === '2'">{{ formModel.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 1, formModel.correctBeforePriceOco1SasinariJyouken) : '-' }}</span>
                          <span>で執行</span>
                        </el-col>
                      </el-row>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 価格/執行方法 -->
              <el-row v-if="!formModel.workingStatus">
                <el-col>
                  <div class="form-area__section">
                    <!-- 執行方法ドロップダウンリスト -->
                    <el-row>
                      <el-col :span="8">
                        <ifa-input-select
                          v-model="formModel.oco1SasinariHouhou"
                          label="訂正価格/執行方法"
                          prop="oco1SasinariHouhou"
                          :disabled="true"
                          :required="true"
                          :code-list-id="'EXECUTE_METHOD'"
                          :disp-pattern="1"
                          :select-pattern="3"
                          style="width: 100px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 指値  執行方法（通常/逆指値）=指値のとき表示 -->
                      <!-- 執行条件（指値） -->
                      <el-col v-if="formModel.oco1SasinariHouhou === '1'"
                              :span="3"
                              style="margin-right: 10px;margin-left: -10px;"
                      >
                        <ifa-input-select
                          v-if="formModel.oco1SasinariHouhou === '1'"
                          v-model="formModel.oco1SasinariJyouken"
                          :disabled="true"
                          :clearable="false"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="2"
                          :select-pattern="6"
                          style="width: 120px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 注文単価（指値） -->
                      <el-col v-if="formModel.oco1SasinariHouhou === '1'"
                              :span="6"
                      >
                        <ifa-input-price
                          :key="priceInfo.orderPriceUnit"
                          v-model="formModel.oco1Price"
                          prop="oco1Price"
                          :required="true"
                          :min="1"
                          :max="maxValueValidator"
                          :digit="1"
                          :domain="IfaUnitPriceYen102DigitsBDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                          support
                          size="small"
                        ></ifa-input-price>
                      </el-col>
                      <!-- 制限値幅（指値） -->
                      <el-row
                        v-if="formModel.oco1SasinariHouhou === '1' && (priceLow == '1.0' && priceHigh == '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>値幅制限：なし</span>
                        </el-col>
                      </el-row>
                      <el-row
                        v-else-if="formModel.oco1SasinariHouhou === '1' && (priceLow != '1.0' || priceHigh != '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>
                            値幅制限：{{ $_out($_withCommaZeroPadding(priceLow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                          </span>
                        </el-col>
                      </el-row>
                    </el-row>
                  </div>
                </el-col>
              </el-row>
              <!-- OCO2見出し -->
              <div :style="styleSeparator">
                <span class="ifd-oco-label">OCO2</span>
              </div>
              <!-- 訂正前価格/執行方法（条件） -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="条件">
                      <span>現在値が</span>
                      <span>{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceOco2TriggerPrice.trim())) }}円</span>
                      <span v-if="formModel.correctBeforePriceOco2TriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                      <span v-else>{{ formModel.correctBeforePriceOco2TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceOco2TriggerPriceZone) : '-' }}になった時点で</span>
                      <span v-if="formModel.correctBeforePriceOco1SasinariJyouken === ' '">OCO1（指値）を</span>
                      <span v-else>OCO1（{{ formModel.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceOco1SasinariJyouken) : '-' }}）を</span>
                      <span v-if="formModel.correctBeforePriceOco2GyakusasiHouhou === '1'">{{ formModel.correctBeforePriceOco2GyakusasiJyouken === ' ' ? $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceOco2Price.trim())) + '円' : $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceOco2GyakusasiJyouken) + '/' + $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceOco2Price.trim())) + '円' }}</span>
                      <span v-if="formModel.correctBeforePriceOco2GyakusasiHouhou === '2'">成行</span>
                      <span>に訂正</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 価格/執行方法 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <!-- 執行方法ドロップダウンリスト -->
                    <el-row>
                      <el-col :span="8">
                        <ifa-input-select
                          v-model="oco2GyakusasiHouhouPullDownList"
                          label="訂正価格/執行方法"
                          :disabled="true"
                          :required="true"
                          :code-list-id="'EXECUTE_METHOD'"
                          :disp-pattern="1"
                          :select-pattern="5"
                          style="width: 100px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 逆指値 執行方法（通常/逆指値）=逆指値のとき表示 -->
                      <!-- 発火条件価格（逆指値） -->
                      <el-col :span="2"
                              style="margin-right: 10px; margin-left: -10px;"
                      >
                        <span>現在値が</span>
                      </el-col>
                      <el-col :span="6">
                        <ifa-input-price
                          :key="priceInfo.orderPriceUnit"
                          v-model="formModel.oco2TriggerPrice"
                          prop="oco2TriggerPrice"
                          :required="true"
                          :min="1"
                          :max="maxValueValidator"
                          :digit="1"
                          :disabled="formModel.workingStatus"
                          :domain="IfaCurrency152DigitsBDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                          support
                          size="small"
                        ></ifa-input-price>
                      </el-col>
                      <el-col :span="8">
                        <span v-if="formModel.correctBeforePriceOco2TriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点でOCO1注文を以下の執行条件に訂正' : '以下になった時点でOCO1注文を以下の執行条件に訂正' }}</span>
                        <span v-else>{{ formModel.correctBeforePriceOco2TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceOco2TriggerPriceZone) : '-' }}になった時点でOCO1注文を以下の執行条件に訂正</span>
                      </el-col>
                      <!-- 執行方法（逆指値） -->
                      <el-row style="margin-top: 1rem;">
                        <el-col :span="3"
                                style="margin-left: 32%;"
                        >
                          <ifa-input-select
                            v-model="formModel.oco2GyakusasiHouhou"
                            prop="oco2GyakusasiHouhou"
                            :clearable="false"
                            :disabled="oco2GyakusasiHouhouEditDisable2"
                            :code-list-id="'EXECUTE_METHOD'"
                            :disp-pattern="1"
                            :select-pattern="2"
                            style="width: 100px;"
                            @change="handlerA008"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.oco2GyakusasiHouhou === '1'"
                                :span="3"
                                style="margin-right: 10px; margin-left: -10px;"
                        >
                          <ifa-input-select
                            v-if="formModel.oco2GyakusasiHouhou === '1'"
                            v-model="formModel.oco2GyakusasiJyouken"
                            :disabled="true"
                            :clearable="false"
                            :code-list-id="'LIMIT_MARKET_TYPE'"
                            :disp-pattern="2"
                            :select-pattern="6"
                            style="width: 120px;"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.oco2GyakusasiHouhou === '2'"
                                :span="3"
                                style="margin-right: 10px; margin-left: -10px;"
                        >
                          <ifa-input-select
                            v-if="formModel.oco2GyakusasiHouhou === '2'"
                            v-model="formModel.oco2GyakusasiJyouken"
                            :disabled="true"
                            :clearable="false"
                            :code-list-id="'LIMIT_MARKET_TYPE'"
                            :disp-pattern="3"
                            :select-pattern="8"
                            style="width: 120px;"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.oco2GyakusasiHouhou === '1'"
                                :span="6"
                        >
                          <ifa-input-price
                            :key="priceInfo.orderPriceUnit"
                            v-model="formModel.oco2Price"
                            prop="oco2Price"
                            :required="true"
                            :min="1"
                            :max="maxValueValidator"
                            :digit="1"
                            :domain="IfaUnitPriceYen102DigitsBDomainModel"
                            :initial-step="initialStep"
                            :step-table="priceInfo.orderPriceUnit"
                            unit="円"
                            support
                            size="small"
                          ></ifa-input-price>
                        </el-col>
                        <el-col :span="2">
                          <span>で執行</span>
                        </el-col>
                        <!-- 制限値幅（指値） -->
                        <el-row
                          v-if="priceLow == '1.0' && priceHigh == '9999999999999.9'"
                          style="padding-top: 0.5rem; width: 97.8%;"
                        >
                          <!-- 空白で6列占有 -->
                          <el-col :span="14"></el-col>
                          <!-- 値幅制限が残りのスペースを占有 -->
                          <el-col :span="10">
                            <span>値幅制限：なし</span>
                          </el-col>
                        </el-row>
                        <el-row
                          v-else-if="priceLow != '1.0' || priceHigh != '9999999999999.9'"
                          style="padding-top: 0.5rem; width: 97.8%;"
                        >
                          <!-- 空白で6列占有 -->
                          <el-col :span="14"></el-col>
                          <!-- 値幅制限が残りのスペースを占有 -->
                          <el-col :span="10">
                            <span>
                              値幅制限：{{ $_out($_withCommaZeroPadding(priceLow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                            </span>
                          </el-col>
                        </el-row>
                      </el-row>
                    </el-row>
                  </div>
                </el-col>
              </el-row>
              <!-- 注文期間 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="注文期間">
                      <span>{{ $_out($_getFormattedDateValue(formModel.period, 'date8')) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 信用取引区分 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="信用取引区分">
                      <span>{{ $_out(formModel.marginTradeTypeTextCalculation) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
            </div>
            <!-- 注文種別=IFDのとき表示 -->
            <div v-if="formModel.orderKind === '3'">
              <!-- IFD1 -->
              <!-- IFD1見出し -->
              <div :style="styleSeparator">
                <span class="ifd-oco-label">IFD1</span>
              </div>
              <!-- 取引種別-->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="取引種別">
                      <span :class="formModel.tradeCd === '3' ? 'font-color__plus bold' : 'font-color__minus bold'">{{ $_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, formModel.tradeCd) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 数量 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="数量(未約定)">
                      <span>{{ $_out($_withCommaInteger(formModel.orderQuantity)) + '(' + $_out($_withCommaInteger(formModel.unTradeQuantity)) + ')株' }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 訂正前価格 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="価格">
                      <el-row>
                        <el-col>
                          <span>{{ formModel.correctBeforePriceIfd1SasinariHouhou? $_getCodeValue('EXECUTE_METHOD', 1, formModel.correctBeforePriceIfd1SasinariHouhou) : '-' }}</span>
                        </el-col>
                      </el-row>
                      <el-row>
                        <!-- 指値 -->
                        <el-col v-if="formModel.correctBeforePriceIfd1SasinariHouhou === '1'">
                          <span v-if="formModel.correctBeforePriceIfd1SasinariJyouken !== ' '">{{ formModel.correctBeforePriceIfd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceIfd1SasinariJyouken) : '-' }}/</span>
                          <span>{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceIfd1Price.trim())) }}円</span>
                        </el-col>
                        <!-- 成行 -->
                        <el-col v-if="formModel.correctBeforePriceIfd1SasinariHouhou === '2'">
                          <span v-if="formModel.correctBeforePriceIfd1SasinariJyouken !== 'N'">{{ formModel.correctBeforePriceIfd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 3, formModel.correctBeforePriceIfd1SasinariJyouken) : '-' }}</span>
                        </el-col>
                        <!-- 逆指値 -->
                        <el-col v-if="formModel.correctBeforePriceIfd1SasinariHouhou === '3'">
                          <span>現在値が{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceIfd1TriggerPrice.trim())) }}円</span>
                          <span v-if="formModel.correctBeforePriceIfd1TriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                          <span v-else>{{ formModel.correctBeforePriceIfd1TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceIfd1TriggerPriceZone) : '-' }}になった時点で</span>
                          <span v-if="formModel.correctBeforePriceIfd1GyakusasiHouhou === '1' && formModel.correctBeforePriceIfd1SasinariJyouken !== ' '">{{ formModel.correctBeforePriceIfd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceIfd1SasinariJyouken) : '-' }}</span>
                          <span v-if="formModel.correctBeforePriceIfd1GyakusasiHouhou === '1'">{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceIfd1Price.trim())) }}円</span>
                          <span v-if="formModel.correctBeforePriceIfd1GyakusasiHouhou === '2'">{{ formModel.correctBeforePriceIfd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 1, formModel.correctBeforePriceIfd1SasinariJyouken) : '-' }}</span>
                          <span>で執行</span>
                        </el-col>
                      </el-row>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 価格/執行方法 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <!-- 執行方法ドロップダウンリスト -->
                    <el-row>
                      <el-col :span="8">
                        <ifa-input-select
                          v-model="formModel.ifd1SasinariHouhou"
                          label="訂正価格/執行方法"
                          prop="ifd1SasinariHouhou"
                          :required="true"
                          :disabled="ifd1SasinariHouhouEditDisable"
                          code-list-id="original"
                          :original-list="ifd1SasinariHouhouDropDownList"
                          style="width: 100px;"
                          @change="ifd1HandlerA005"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 指値  執行方法（通常/逆指値）=指値のとき表示 -->
                      <!-- 執行条件（指値） -->
                      <el-col v-if="formModel.ifd1SasinariHouhou === '1'"
                              :span="3"
                              style="margin-right: 10px;margin-left: -10px;"
                      >
                        <ifa-input-select
                          v-if="formModel.ifd1SasinariHouhou === '1'"
                          v-model="formModel.ifd1SasinariJyouken"
                          :disabled="true"
                          :clearable="false"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="2"
                          :select-pattern="2"
                          style="width: 120px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 注文単価（指値） -->
                      <el-col v-if="formModel.ifd1SasinariHouhou === '1'"
                              :span="6"
                      >
                        <ifa-input-price
                          :key="priceInfo.orderPriceUnit"
                          v-model="formModel.ifd1Price"
                          prop="ifd1Price"
                          :required="true"
                          :min="1"
                          :max="maxValueValidator"
                          :digit="1"
                          :domain="IfaUnitPriceYen102DigitsBDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                          support
                          size="small"
                        ></ifa-input-price>
                      </el-col>
                      <!-- 制限値幅（指値） -->
                      <el-row
                        v-if="formModel.ifd1SasinariHouhou === '1' && (priceLow == '1.0' && priceHigh == '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>値幅制限：なし</span>
                        </el-col>
                      </el-row>
                      <el-row
                        v-else-if="formModel.ifd1SasinariHouhou === '1' && (priceLow != '1.0' || priceHigh != '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>
                            値幅制限：{{ $_out($_withCommaZeroPadding(priceLow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                          </span>
                        </el-col>
                      </el-row>
                      <!-- 成行 執行方法（通常/逆指値）=成行のとき表示 -->
                      <!-- 執行条件（成行） -->
                      <el-col v-if="formModel.ifd1SasinariHouhou === '2'"
                              :span="3"
                              style="margin-right: 10px; margin-left: -10px;"
                      >
                        <ifa-input-select
                          v-if="formModel.ifd1SasinariHouhou === '2'"
                          v-model="formModel.ifd1SasinariJyouken"
                          :disabled="true"
                          :clearable="false"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="3"
                          :select-pattern="3"
                          style="width: 120px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 制限値幅（指値） -->
                      <el-row
                        v-if="formModel.ifd1SasinariHouhou === '2' && (priceLow == '1.0' && priceHigh == '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>値幅制限：なし</span>
                        </el-col>
                      </el-row>
                      <el-row
                        v-else-if="formModel.ifd1SasinariHouhou === '2' && (priceLow != '1.0' || priceHigh != '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>
                            値幅制限：{{ $_out($_withCommaZeroPadding(priceLow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                          </span>
                        </el-col>
                      </el-row>
                      <!-- 逆指値 執行方法（通常/逆指値）=逆指値のとき表示 -->
                      <!-- 発火条件価格（逆指値） -->
                      <el-col v-if="formModel.correctBeforePriceIfd1SasinariHouhou === '3'"
                              :span="2"
                              style="margin-right: 10px; margin-left: -10px;"
                      >
                        <span>現在値が</span>
                      </el-col>
                      <el-col v-if="formModel.ifd1SasinariHouhou === '3'"
                              :span="6"
                      >
                        <ifa-input-price
                          :key="priceInfo.orderPriceUnit"
                          v-model="formModel.ifd1TriggerPrice"
                          prop="ifd1TriggerPrice"
                          :required="true"
                          :min="1"
                          :max="maxValueValidator"
                          :digit="1"
                          :disabled="formModel.workingStatus"
                          :domain="IfaCurrency152DigitsBDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                          support
                          size="small"
                        ></ifa-input-price>
                      </el-col>
                      <el-col v-if="formModel.correctBeforePriceIfd1SasinariHouhou === '3'"
                              :span="3"
                      >
                        <span v-if="formModel.correctBeforePriceIfd1TriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                        <span v-else>{{ formModel.correctBeforePriceIfd1TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceIfd1TriggerPriceZone) : '-' }}になった時点で</span>
                      </el-col>
                      <!-- 執行方法（逆指値） -->
                      <el-row v-if="formModel.correctBeforePriceIfd1SasinariHouhou === '3'"
                              style="margin-top: 1rem;"
                      >
                        <el-col :span="3"
                                style="margin-left: 32%;"
                        >
                          <ifa-input-select
                            v-model="formModel.ifd1GyakusasiHouhou"
                            prop="ifd1GyakusasiHouhou"
                            :clearable="false"
                            :disabled="ifd1GyakusasiHouhouEditDisable"
                            code-list-id="original"
                            :original-list="ifd1GyakusasiHouhouDropDownList"
                            style="width: 100px;"
                            @change="handlerA008"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.ifd1GyakusasiHouhou === '1'"
                                :span="3"
                                style="margin-right: 10px; margin-left: -10px;"
                        >
                          <ifa-input-select
                            v-if="formModel.ifd1GyakusasiHouhou === '1'"
                            v-model="formModel.ifd1SasinariJyouken"
                            :disabled="true"
                            :clearable="false"
                            :code-list-id="'LIMIT_MARKET_TYPE'"
                            :disp-pattern="2"
                            :select-pattern="4"
                            style="width: 120px;"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.ifd1GyakusasiHouhou === '2'"
                                :span="3"
                                style="margin-right: 10px; margin-left: -10px;"
                        >
                          <ifa-input-select
                            v-if="formModel.ifd1GyakusasiHouhou === '2'"
                            v-model="formModel.ifd1SasinariJyouken"
                            :disabled="true"
                            :clearable="false"
                            :code-list-id="'LIMIT_MARKET_TYPE'"
                            :disp-pattern="3"
                            :select-pattern="5"
                            style="width: 120px;"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.ifd1GyakusasiHouhou === '1'"
                                :span="6"
                        >
                          <ifa-input-price
                            :key="priceInfo.orderPriceUnit"
                            v-model="formModel.ifd1Price"
                            prop="ifd1Price"
                            :required="true"
                            :min="1"
                            :max="maxValueValidator"
                            :digit="1"
                            :domain="IfaUnitPriceYen102DigitsBDomainModel"
                            :initial-step="initialStep"
                            :step-table="priceInfo.orderPriceUnit"
                            unit="円"
                            support
                            size="small"
                          ></ifa-input-price>
                        </el-col>
                        <el-col :span="2">
                          <span>で執行</span>
                        </el-col>
                        <!-- 制限値幅（指値） -->
                        <el-row
                          v-if="priceLow == '1.0' && priceHigh == '9999999999999.9'"
                          style="padding-top: 0.5rem; width: 97.8%;"
                        >
                          <!-- 空白で6列占有 -->
                          <el-col :span="14"></el-col>
                          <!-- 値幅制限が残りのスペースを占有 -->
                          <el-col :span="10">
                            <span>値幅制限：なし</span>
                          </el-col>
                        </el-row>
                        <el-row
                          v-else-if="priceLow != '1.0' || priceHigh != '9999999999999.9'"
                          style="padding-top: 0.5rem; width: 97.8%;"
                        >
                          <!-- 空白で6列占有 -->
                          <el-col :span="14"></el-col>
                          <!-- 値幅制限が残りのスペースを占有 -->
                          <el-col :span="10">
                            <span>
                              値幅制限：{{ $_out($_withCommaZeroPadding(priceLow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                            </span>
                          </el-col>
                        </el-row>
                      </el-row>
                    </el-row>
                  </div>
                </el-col>
              </el-row>
              <!-- 注文期間 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="注文期間">
                      <span>{{ $_out($_getFormattedDateValue(formModel.period, 'date8')) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- IFD2 -->
              <!-- IFD2見出し -->
              <div :style="styleSeparator">
                <span class="ifd-oco-label">IFD2</span>
              </div>
              <!-- 取引種別-->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="取引種別">
                      <span :class="formModel.tradeCd === '3' ? 'font-color__minus bold' : 'font-color__plus bold'">{{ formModel.tradeCd === '3' ? '返済売' : '返済買' }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 訂正前価格 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="価格">
                      <el-row>
                        <el-col>
                          <span>{{ formModel.correctBeforePriceIfd2SasinariHouhou? $_getCodeValue('EXECUTE_METHOD', 1, formModel.correctBeforePriceIfd2SasinariHouhou) : '-' }}</span>
                        </el-col>
                      </el-row>
                      <el-row>
                        <!-- 指値 -->
                        <el-col v-if="formModel.correctBeforePriceIfd2SasinariHouhou === '1'">
                          <span v-if="formModel.correctBeforePriceIfd2SasinariJyouken !== ' '">{{ formModel.correctBeforePriceIfd2SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceIfd2SasinariJyouken) : '-' }}/</span>
                          <span>{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceIfd2Price.trim())) }}円</span>
                        </el-col>
                        <!-- 逆指値 -->
                        <el-col v-if="formModel.correctBeforePriceIfd2SasinariHouhou === '3'">
                          <span>現在値が{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceIfd2TriggerPrice.trim())) }}円</span>
                          <span v-if="formModel.correctBeforePriceIfd2TriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                          <span v-else>{{ formModel.correctBeforePriceIfd2TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceIfd2TriggerPriceZone) : '-' }}になった時点で</span>
                          <span v-if="formModel.correctBeforePriceIfd2GyakusasiHouhou === '1' && formModel.correctBeforePriceIfd2SasinariJyouken !== ' '">{{ formModel.correctBeforePriceIfd2SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceIfd2SasinariJyouken) : '-' }}</span>
                          <span v-if="formModel.correctBeforePriceIfd2GyakusasiHouhou === '1'">{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceIfd2Price.trim())) }}円</span>
                          <span v-if="formModel.correctBeforePriceIfd2GyakusasiHouhou === '2'">{{ formModel.correctBeforePriceIfd2SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 1, formModel.correctBeforePriceIfd2SasinariJyouken) : '-' }}</span>
                          <span>で執行</span>
                        </el-col>
                      </el-row>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 価格/執行方法 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <!-- 執行方法ドロップダウンリスト -->
                    <el-row>
                      <el-col :span="8">
                        <ifa-input-select
                          v-model="formModel.ifd2SasinariHouhou"
                          label="訂正価格/執行方法"
                          prop="ifd2SasinariHouhou"
                          :required="true"
                          :disabled="true"
                          code-list-id="original"
                          :original-list="ifd2SasinariHouhouDropDownList"
                          style="width: 100px;"
                          @change="ifd2HandlerA005"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 指値  執行方法（通常/逆指値）=指値のとき表示 -->
                      <!-- 執行条件（指値） -->
                      <el-col v-if="formModel.ifd2SasinariHouhou === '1'"
                              :span="3"
                              style="margin-right: 10px;margin-left: -10px;"
                      >
                        <ifa-input-select
                          v-if="formModel.ifd2SasinariHouhou === '1'"
                          v-model="formModel.ifd2SasinariJyouken"
                          :disabled="true"
                          :clearable="false"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="2"
                          :select-pattern="4"
                          style="width: 120px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 注文単価（指値） -->
                      <el-col v-if="formModel.ifd2SasinariHouhou === '1'"
                              :span="6"
                      >
                        <ifa-input-price
                          :key="priceInfo.orderPriceUnit"
                          v-model="formModel.ifd2Price"
                          prop="ifd2Price"
                          :required="true"
                          :min="1"
                          :max="maxValueValidator"
                          :digit="1"
                          :domain="IfaUnitPriceYen102DigitsBDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                          support
                          size="small"
                        ></ifa-input-price>
                      </el-col>
                      <!-- 制限値幅（指値） -->
                      <el-row
                        v-if="formModel.ifd2SasinariHouhou === '1' && (ifd2PriceLow == '1.0' && ifd2PriceHigh == '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>値幅制限：なし</span>
                        </el-col>
                      </el-row>
                      <el-row
                        v-else-if="formModel.ifd2SasinariHouhou === '1' && (ifd2PriceLow != '1.0' || ifd2PriceHigh != '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>
                            値幅制限：{{ $_out($_withCommaZeroPadding(ifd2PriceLow)) }}円～{{ $_out($_withCommaZeroPadding(ifd2PriceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                          </span>
                        </el-col>
                      </el-row>
                      <!-- 成行 執行方法（通常/逆指値）=成行のとき表示 -->
                      <!-- 執行条件（成行） -->
                      <el-col v-if="formModel.ifd2SasinariHouhou === '2'"
                              :span="3"
                              style="margin-right: 10px; margin-left: -10px;"
                      >
                        <ifa-input-select
                          v-if="formModel.ifd2SasinariHouhou === '2'"
                          v-model="formModel.ifd2SasinariJyouken"
                          :disabled="true"
                          :clearable="false"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="3"
                          :select-pattern="3"
                          style="width: 120px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 制限値幅（指値） -->
                      <el-row
                        v-if="formModel.ifd2SasinariHouhou === '2' && (ifd2PriceLow == '1.0' && ifd2PriceHigh == '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>値幅制限：なし</span>
                        </el-col>
                      </el-row>
                      <el-row
                        v-else-if="formModel.ifd2SasinariHouhou === '2' && (ifd2PriceLow != '1.0' || ifd2PriceHigh != '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>
                            値幅制限：{{ $_out($_withCommaZeroPadding(ifd2PriceLow)) }}円～{{ $_out($_withCommaZeroPadding(ifd2PriceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                          </span>
                        </el-col>
                      </el-row>
                      <!-- 逆指値 執行方法（通常/逆指値）=逆指値のとき表示 -->
                      <!-- 発火条件価格（逆指値） -->
                      <el-col v-if="formModel.correctBeforePriceIfd2SasinariHouhou === '3'"
                              :span="2"
                              style="margin-right: 10px; margin-left: -10px;"
                      >
                        <span>現在値が</span>
                      </el-col>
                      <el-col v-if="formModel.ifd2SasinariHouhou === '3'"
                              :span="6"
                      >
                        <ifa-input-price
                          :key="priceInfo.orderPriceUnit"
                          v-model="formModel.ifd2TriggerPrice"
                          prop="ifd2TriggerPrice"
                          :required="true"
                          :min="1"
                          :max="maxValueValidator"
                          :digit="1"
                          :domain="IfaCurrency152DigitsBDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                          support
                          size="small"
                        ></ifa-input-price>
                      </el-col>
                      <el-col v-if="formModel.correctBeforePriceIfd2SasinariHouhou === '3'"
                              :span="3"
                      >
                        <span v-if="formModel.correctBeforePriceIfd2TriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                        <span v-else>{{ formModel.correctBeforePriceIfd2TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceIfd2TriggerPriceZone) : '-' }}になった時点で</span>
                      </el-col>
                      <!-- 執行方法（逆指値） -->
                      <el-row v-if="formModel.correctBeforePriceIfd2SasinariHouhou === '3'"
                              style="margin-top: 1rem;"
                      >
                        <el-col :span="3"
                                style="margin-left: 32%;"
                        >
                          <ifa-input-select
                            v-model="formModel.ifd2GyakusasiHouhou"
                            prop="ifd2GyakusasiHouhou"
                            :clearable="false"
                            :disabled="ifd2GyakusasiHouhouEditDisable"
                            code-list-id="original"
                            :original-list="ifd2GyakusasiHouhouDropDownList"
                            style="width: 100px;"
                            @change="handlerA008"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.ifd2GyakusasiHouhou === '1'"
                                :span="3"
                                style="margin-right: 10px; margin-left: -10px;"
                        >
                          <ifa-input-select
                            v-if="formModel.ifd2GyakusasiHouhou === '1'"
                            v-model="formModel.ifd2SasinariJyouken"
                            :disabled="true"
                            :clearable="false"
                            :code-list-id="'LIMIT_MARKET_TYPE'"
                            :disp-pattern="2"
                            :select-pattern="4"
                            style="width: 120px;"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.ifd2GyakusasiHouhou === '2'"
                                :span="3"
                                style="margin-right: 10px; margin-left: -10px;"
                        >
                          <ifa-input-select
                            v-if="formModel.ifd2GyakusasiHouhou === '2'"
                            v-model="formModel.ifd2SasinariJyouken"
                            :disabled="true"
                            :clearable="false"
                            :code-list-id="'LIMIT_MARKET_TYPE'"
                            :disp-pattern="3"
                            :select-pattern="5"
                            style="width: 120px;"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.ifd2GyakusasiHouhou === '1'"
                                :span="6"
                        >
                          <ifa-input-price
                            :key="priceInfo.orderPriceUnit"
                            v-model="formModel.ifd2Price"
                            prop="ifd2Price"
                            :required="true"
                            :min="1"
                            :max="maxValueValidator"
                            :digit="1"
                            :domain="IfaUnitPriceYen102DigitsBDomainModel"
                            :initial-step="initialStep"
                            :step-table="priceInfo.orderPriceUnit"
                            unit="円"
                            support
                            size="small"
                          ></ifa-input-price>
                        </el-col>
                        <el-col :span="2">
                          <span>で執行</span>
                        </el-col>
                        <!-- 制限値幅（指値） -->
                        <el-row
                          v-if="ifd2PriceLow == '1.0' && ifd2PriceHigh == '9999999999999.9'"
                          style="padding-top: 0.5rem; width: 97.8%;"
                        >
                          <!-- 空白で6列占有 -->
                          <el-col :span="14"></el-col>
                          <!-- 値幅制限が残りのスペースを占有 -->
                          <el-col :span="10">
                            <span>値幅制限：なし</span>
                          </el-col>
                        </el-row>
                        <el-row
                          v-else-if="ifd2PriceLow != '1.0' || ifd2PriceHigh != '9999999999999.9'"
                          style="padding-top: 0.5rem; width: 97.8%;"
                        >
                          <!-- 空白で6列占有 -->
                          <el-col :span="14"></el-col>
                          <!-- 値幅制限が残りのスペースを占有 -->
                          <el-col :span="10">
                            <span>
                              値幅制限：{{ $_out($_withCommaZeroPadding(ifd2PriceLow)) }}円～{{ $_out($_withCommaZeroPadding(ifd2PriceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                            </span>
                          </el-col>
                        </el-row>
                      </el-row>
                    </el-row>
                  </div>
                </el-col>
              </el-row>
              <!-- 注文期間 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="注文期間">
                      <span>{{ $_out($_getFormattedDateValue(formModel.ifd2Limit, 'date8')) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 信用取引区分 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="信用取引区分">
                      <span>{{ $_out(formModel.marginTradeTypeTextCalculation) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
            </div>
            <!-- 注文種別=IFDOCOのとき表示 -->
            <div v-if="formModel.orderKind === '4'">
              <!-- IFD1 -->
              <!-- IFD1見出し -->
              <div :style="styleSeparator">
                <span class="ifd-oco-label">IFD1</span>
              </div>
              <!-- 取引種別-->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="取引種別">
                      <span :class="formModel.tradeCd === '3' ? 'font-color__plus bold' : 'font-color__minus bold'">{{ $_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, formModel.tradeCd) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 数量 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="数量(未約定)">
                      <span>{{ $_out($_withCommaInteger(formModel.orderQuantity)) + '(' + $_out($_withCommaInteger(formModel.unTradeQuantity)) + ')株' }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 訂正前価格 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="価格">
                      <el-row>
                        <el-col>
                          <span>{{ formModel.correctBeforePriceIfd1SasinariHouhou? $_getCodeValue('EXECUTE_METHOD', 1, formModel.correctBeforePriceIfd1SasinariHouhou) : '-' }}</span>
                        </el-col>
                      </el-row>
                      <el-row>
                        <!-- 指値 -->
                        <el-col v-if="formModel.correctBeforePriceIfd1SasinariHouhou === '1'">
                          <span v-if="formModel.correctBeforePriceIfd1SasinariJyouken !== ' '">{{ formModel.correctBeforePriceIfd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceIfd1SasinariJyouken) : '-' }}/</span>
                          <span>{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceIfd1Price.trim())) }}円</span>
                        </el-col>
                        <!-- 成行 -->
                        <el-col v-if="formModel.correctBeforePriceIfd1SasinariHouhou === '2'">
                          <span v-if="formModel.correctBeforePriceIfd1SasinariJyouken !== 'N'">{{ formModel.correctBeforePriceIfd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 3, formModel.correctBeforePriceIfd1SasinariJyouken) : '-' }}</span>
                        </el-col>
                        <!-- 逆指値 -->
                        <el-col v-if="formModel.correctBeforePriceIfd1SasinariHouhou === '3'">
                          <span>現在値が{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceIfd1TriggerPrice.trim())) }}円</span>
                          <span v-if="formModel.correctBeforePriceIfd1TriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                          <span v-else>{{ formModel.correctBeforePriceIfd1TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceIfd1TriggerPriceZone) : '-' }}になった時点で</span>
                          <span v-if="formModel.correctBeforePriceIfd1GyakusasiHouhou === '1' && formModel.correctBeforePriceIfd1SasinariJyouken !== ' '">{{ formModel.correctBeforePriceIfd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceIfd1SasinariJyouken) : '-' }}</span>
                          <span v-if="formModel.correctBeforePriceIfd1GyakusasiHouhou === '1'">{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceIfd1Price.trim())) }}円</span>
                          <span v-if="formModel.correctBeforePriceIfd1GyakusasiHouhou === '2'">{{ formModel.correctBeforePriceIfd1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 1, formModel.correctBeforePriceIfd1SasinariJyouken) : '-' }}</span>
                          <span>で執行</span>
                        </el-col>
                      </el-row>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 価格/執行方法 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <!-- 執行方法ドロップダウンリスト -->
                    <el-row>
                      <el-col :span="8">
                        <ifa-input-select
                          v-model="formModel.ifd1SasinariHouhou"
                          label="訂正価格/執行方法"
                          prop="ifd1SasinariHouhou"
                          :required="true"
                          :disabled="ifd1SasinariHouhouEditDisable"
                          code-list-id="original"
                          :original-list="ifd1SasinariHouhouDropDownList"
                          style="width: 100px;"
                          @change="ifd1HandlerA005"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 指値  執行方法（通常/逆指値）=指値のとき表示 -->
                      <!-- 執行条件（指値） -->
                      <el-col v-if="formModel.ifd1SasinariHouhou === '1'"
                              :span="3"
                              style="margin-right: 10px;margin-left: -10px;"
                      >
                        <ifa-input-select
                          v-if="formModel.ifd1SasinariHouhou === '1'"
                          v-model="formModel.ifd1SasinariJyouken"
                          :disabled="true"
                          :clearable="false"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="2"
                          :select-pattern="2"
                          style="width: 120px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 注文単価（指値） -->
                      <el-col v-if="formModel.ifd1SasinariHouhou === '1'"
                              :span="6"
                      >
                        <ifa-input-price
                          :key="priceInfo.orderPriceUnit"
                          v-model="formModel.ifd1Price"
                          prop="ifd1Price"
                          :required="true"
                          :min="1"
                          :max="maxValueValidator"
                          :digit="1"
                          :domain="IfaUnitPriceYen102DigitsBDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                          support
                          size="small"
                        ></ifa-input-price>
                      </el-col>
                      <!-- 制限値幅（指値） -->
                      <el-row
                        v-if="formModel.ifd1SasinariHouhou === '1' && (priceLow == '1.0' && priceHigh == '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>値幅制限：なし</span>
                        </el-col>
                      </el-row>
                      <el-row
                        v-else-if="formModel.ifd1SasinariHouhou === '1' && (priceLow != '1.0' || priceHigh != '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>
                            値幅制限：{{ $_out($_withCommaZeroPadding(priceLow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                          </span>
                        </el-col>
                      </el-row>
                      <!-- 成行 執行方法（通常/逆指値）=成行のとき表示 -->
                      <!-- 執行条件（成行） -->
                      <el-col v-if="formModel.ifd1SasinariHouhou === '2'"
                              :span="3"
                              style="margin-right: 10px; margin-left: -10px;"
                      >
                        <ifa-input-select
                          v-if="formModel.ifd1SasinariHouhou === '2'"
                          v-model="formModel.ifd1SasinariJyouken"
                          :disabled="true"
                          :clearable="false"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="3"
                          :select-pattern="3"
                          style="width: 120px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 制限値幅（指値） -->
                      <el-row
                        v-if="formModel.ifd1SasinariHouhou === '2' && (priceLow == '1.0' && priceHigh == '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>値幅制限：なし</span>
                        </el-col>
                      </el-row>
                      <el-row
                        v-else-if="formModel.ifd1SasinariHouhou === '2' && (priceLow != '1.0' || priceHigh != '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>
                            値幅制限：{{ $_out($_withCommaZeroPadding(priceLow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                          </span>
                        </el-col>
                      </el-row>
                      <!-- 逆指値 執行方法（通常/逆指値）=逆指値のとき表示 -->
                      <!-- 発火条件価格（逆指値） -->
                      <el-col v-if="formModel.correctBeforePriceIfd1SasinariHouhou === '3'"
                              :span="2"
                              style="margin-right: 10px; margin-left: -10px;"
                      >
                        <span>現在値が</span>
                      </el-col>
                      <el-col v-if="formModel.ifd1SasinariHouhou === '3'"
                              :span="6"
                      >
                        <ifa-input-price
                          :key="priceInfo.orderPriceUnit"
                          v-model="formModel.ifd1TriggerPrice"
                          prop="ifd1TriggerPrice"
                          :required="true"
                          :min="1"
                          :max="maxValueValidator"
                          :digit="1"
                          :disabled="formModel.workingStatus"
                          :domain="IfaCurrency152DigitsBDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                          support
                          size="small"
                        ></ifa-input-price>
                      </el-col>
                      <el-col v-if="formModel.correctBeforePriceIfd1SasinariHouhou === '3'"
                              :span="3"
                      >
                        <span v-if="formModel.correctBeforePriceIfd1TriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                        <span v-else>{{ formModel.correctBeforePriceIfd1TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceIfd1TriggerPriceZone) : '-' }}になった時点で</span>
                      </el-col>
                      <!-- 執行方法（逆指値） -->
                      <el-row v-if="formModel.correctBeforePriceIfd1SasinariHouhou === '3'"
                              style="margin-top: 1rem;"
                      >
                        <el-col :span="3"
                                style="margin-left: 32%;"
                        >
                          <ifa-input-select
                            v-model="formModel.ifd1GyakusasiHouhou"
                            prop="ifd1GyakusasiHouhou"
                            :clearable="false"
                            :disabled="ifd1GyakusasiHouhouEditDisable"
                            code-list-id="original"
                            :original-list="ifd1GyakusasiHouhouDropDownList"
                            style="width: 100px;"
                            @change="handlerA008"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.ifd1GyakusasiHouhou === '1'"
                                :span="3"
                                style="margin-right: 10px; margin-left: -10px;"
                        >
                          <ifa-input-select
                            v-if="formModel.ifd1GyakusasiHouhou === '1'"
                            v-model="formModel.ifd1SasinariJyouken"
                            :disabled="true"
                            :clearable="false"
                            :code-list-id="'LIMIT_MARKET_TYPE'"
                            :disp-pattern="2"
                            :select-pattern="4"
                            style="width: 120px;"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.ifd1GyakusasiHouhou === '2'"
                                :span="3"
                                style="margin-right: 10px; margin-left: -10px;"
                        >
                          <ifa-input-select
                            v-if="formModel.ifd1GyakusasiHouhou === '2'"
                            v-model="formModel.ifd1SasinariJyouken"
                            :disabled="true"
                            :clearable="false"
                            :code-list-id="'LIMIT_MARKET_TYPE'"
                            :disp-pattern="3"
                            :select-pattern="5"
                            style="width: 120px;"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.ifd1GyakusasiHouhou === '1'"
                                :span="6"
                        >
                          <ifa-input-price
                            :key="priceInfo.orderPriceUnit"
                            v-model="formModel.ifd1Price"
                            prop="ifd1Price"
                            :required="true"
                            :min="1"
                            :max="maxValueValidator"
                            :digit="1"
                            :domain="IfaUnitPriceYen102DigitsBDomainModel"
                            :initial-step="initialStep"
                            :step-table="priceInfo.orderPriceUnit"
                            unit="円"
                            support
                            size="small"
                          ></ifa-input-price>
                        </el-col>
                        <el-col :span="2">
                          <span>で執行</span>
                        </el-col>
                        <!-- 制限値幅（指値） -->
                        <el-row
                          v-if="priceLow == '1.0' && priceHigh == '9999999999999.9'"
                          style="padding-top: 0.5rem; width: 97.8%;"
                        >
                          <!-- 空白で6列占有 -->
                          <el-col :span="14"></el-col>
                          <!-- 値幅制限が残りのスペースを占有 -->
                          <el-col :span="10">
                            <span>値幅制限：なし</span>
                          </el-col>
                        </el-row>
                        <el-row
                          v-else-if="priceLow != '1.0' || priceHigh != '9999999999999.9'"
                          style="padding-top: 0.5rem; width: 97.8%;"
                        >
                          <!-- 空白で6列占有 -->
                          <el-col :span="14"></el-col>
                          <!-- 値幅制限が残りのスペースを占有 -->
                          <el-col :span="10">
                            <span>
                              値幅制限：{{ $_out($_withCommaZeroPadding(priceLow)) }}円～{{ $_out($_withCommaZeroPadding(priceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                            </span>
                          </el-col>
                        </el-row>
                      </el-row>
                    </el-row>
                  </div>
                </el-col>
              </el-row>
              <!-- 注文期間 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="注文期間">
                      <span>{{ $_out($_getFormattedDateValue(formModel.period, 'date8')) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- IFD2 -->
              <!-- IFD2見出し -->
              <div :style="styleSeparator">
                <span class="ifd-oco-label">IFD2</span>
              </div>
              <!-- 取引種別-->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="取引種別">
                      <span :class="formModel.tradeCd === '3' ? 'font-color__minus bold' : 'font-color__plus bold'">{{ formModel.tradeCd === '3' ? '返済売' : '返済買' }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- OCO1 -->
              <!-- OCO1見出し -->
              <div :style="styleSeparator">
                <span class="ifd-oco-label"
                      style="margin-left: 3rem;"
                >OCO1</span>
              </div>
              <!-- 訂正前価格/執行方法 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="価格">
                      <el-row>
                        <el-col>
                          <span>{{ formModel.correctBeforePriceOco1SasinariHouhou? $_getCodeValue('EXECUTE_METHOD', 1, formModel.correctBeforePriceOco1SasinariHouhou) : '-' }}</span>
                        </el-col>
                      </el-row>
                      <el-row>
                        <!-- 指値 -->
                        <el-col v-if="formModel.correctBeforePriceOco1SasinariHouhou === '1'">
                          <span v-if="formModel.correctBeforePriceOco1SasinariJyouken !== ' '">{{ formModel.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceOco1SasinariJyouken) : '-' }}/</span>
                          <span>{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceOco1Price.trim())) }}円</span>
                        </el-col>
                        <!-- 成行 -->
                        <el-col v-if="formModel.correctBeforePriceOco1SasinariHouhou === '2'">
                          <span v-if="formModel.correctBeforePriceOco1SasinariJyouken !== 'N'">{{ formModel.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 3, formModel.correctBeforePriceOco1SasinariJyouken) : '-' }}</span>
                        </el-col>
                        <!-- 逆指値 -->
                        <el-col v-if="formModel.correctBeforePriceOco1SasinariHouhou === '3'">
                          <span>現在値が{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceOco1TriggerPrice.trim())) }}円</span>
                          <span v-if="formModel.correctBeforePriceOco1TriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                          <span v-else>{{ formModel.correctBeforePriceOco1TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceOco1TriggerPriceZone) : '-' }}になった時点で</span>
                          <span v-if="formModel.correctBeforePriceOco1GyakusasiHouhou === '1' && formModel.correctBeforePriceOco1SasinariJyouken !== ' '">{{ formModel.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceOco1SasinariJyouken) : '-' }}</span>
                          <span v-if="formModel.correctBeforePriceOco1GyakusasiHouhou === '1'">{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceOco1Price.trim())) }}円</span>
                          <span v-if="formModel.correctBeforePriceOco1GyakusasiHouhou === '2'">{{ formModel.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 1, formModel.correctBeforePriceOco1SasinariJyouken) : '-' }}</span>
                          <span>で執行</span>
                        </el-col>
                      </el-row>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 価格/執行方法 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <!-- 執行方法ドロップダウンリスト -->
                    <el-row>
                      <el-col :span="8">
                        <ifa-input-select
                          v-model="formModel.oco1SasinariHouhou"
                          label="訂正価格/執行方法"
                          prop="oco1SasinariHouhou"
                          :disabled="true"
                          :required="true"
                          :code-list-id="'EXECUTE_METHOD'"
                          :disp-pattern="1"
                          :select-pattern="3"
                          style="width: 100px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 指値  執行方法（通常/逆指値）=指値のとき表示 -->
                      <!-- 執行条件（指値） -->
                      <el-col v-if="formModel.oco1SasinariHouhou === '1'"
                              :span="3"
                              style="margin-right: 10px;margin-left: -10px;"
                      >
                        <ifa-input-select
                          v-if="formModel.oco1SasinariHouhou === '1'"
                          v-model="formModel.oco1SasinariJyouken"
                          :disabled="true"
                          :clearable="false"
                          :code-list-id="'LIMIT_MARKET_TYPE'"
                          :disp-pattern="2"
                          :select-pattern="6"
                          style="width: 120px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 注文単価（指値） -->
                      <el-col v-if="formModel.oco1SasinariHouhou === '1'"
                              :span="6"
                      >
                        <ifa-input-price
                          :key="priceInfo.orderPriceUnit"
                          v-model="formModel.oco1Price"
                          prop="oco1Price"
                          :required="true"
                          :min="1"
                          :max="maxValueValidator"
                          :digit="1"
                          :domain="IfaUnitPriceYen102DigitsBDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                          support
                          size="small"
                        ></ifa-input-price>
                      </el-col>
                      <!-- 制限値幅（指値） -->
                      <el-row
                        v-if="formModel.oco1SasinariHouhou === '1' && (ifd2PriceLow == '1.0' && ifd2PriceHigh == '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>値幅制限：なし</span>
                        </el-col>
                      </el-row>
                      <el-row
                        v-else-if="formModel.oco1SasinariHouhou === '1' && (ifd2PriceLow != '1.0' || ifd2PriceHigh != '9999999999999.9')"
                        style="padding-top: 0.5rem;"
                      >
                        <el-col :offset="11">
                          <span>
                            値幅制限：{{ $_out($_withCommaZeroPadding(ifd2PriceLow)) }}円～{{ $_out($_withCommaZeroPadding(ifd2PriceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                          </span>
                        </el-col>
                      </el-row>
                    </el-row>
                  </div>
                </el-col>
              </el-row>
              <!-- OCO2 -->
              <!-- OCO2見出し -->
              <div :style="styleSeparator">
                <span class="ifd-oco-label"
                      style="margin-left: 3rem;"
                >OCO2</span>
              </div>
              <!-- 訂正前価格/執行方法（条件） -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="条件">
                      <span>現在値が</span>
                      <span>{{ $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceOco2TriggerPrice.trim())) }}円</span>
                      <span v-if="formModel.correctBeforePriceOco2TriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点で' : '以下になった時点で' }}</span>
                      <span v-else>{{ formModel.correctBeforePriceOco2TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceOco2TriggerPriceZone) : '-' }}になった時点で</span>
                      <span v-if="formModel.correctBeforePriceOco1SasinariJyouken === ' '">OCO1（指値）を</span>
                      <span v-else>OCO1（{{ formModel.correctBeforePriceOco1SasinariJyouken ? $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceOco1SasinariJyouken) : '-' }}）を</span>
                      <span v-if="formModel.correctBeforePriceOco2GyakusasiHouhou === '1'">{{ formModel.correctBeforePriceOco2GyakusasiJyouken === ' ' ? $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceOco2Price.trim())) + '円' : $_getCodeValue('LIMIT_MARKET_TYPE', 2, formModel.correctBeforePriceOco2GyakusasiJyouken) + '/' + $_out($_withCommaNoneZeroPadding(formModel.correctBeforePriceOco2Price.trim())) + '円' }}</span>
                      <span v-if="formModel.correctBeforePriceOco2GyakusasiHouhou === '2'">成行</span>
                      <span>に訂正</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 価格/執行方法 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <!-- 執行方法ドロップダウンリスト -->
                    <el-row>
                      <el-col :span="8">
                        <ifa-input-select
                          v-model="oco2GyakusasiHouhouPullDownList"
                          label="訂正価格/執行方法"
                          :disabled="true"
                          :required="true"
                          :code-list-id="'EXECUTE_METHOD'"
                          :disp-pattern="1"
                          :select-pattern="5"
                          style="width: 100px;"
                        ></ifa-input-select>
                      </el-col>
                      <!-- 逆指値 執行方法（通常/逆指値）=逆指値のとき表示 -->
                      <!-- 発火条件価格（逆指値） -->
                      <el-col :span="2"
                              style="margin-right: 10px; margin-left: -10px;"
                      >
                        <span>現在値が</span>
                      </el-col>
                      <el-col :span="6">
                        <ifa-input-price
                          :key="priceInfo.orderPriceUnit"
                          v-model="formModel.oco2TriggerPrice"
                          prop="oco2TriggerPrice"
                          :required="true"
                          :min="1"
                          :max="maxValueValidator"
                          :digit="1"
                          :domain="IfaCurrency152DigitsBDomainModel"
                          :initial-step="initialStep"
                          :step-table="priceInfo.orderPriceUnit"
                          unit="円"
                          support
                          size="small"
                        ></ifa-input-price>
                      </el-col>
                      <el-col :span="8">
                        <span v-if="formModel.correctBeforePriceOco2TriggerPriceZone === ' '">{{ formModel.tradeCd === '3' ? '以上になった時点でOCO1注文を以下の執行条件に訂正' : '以下になった時点でOCO1注文を以下の執行条件に訂正' }}</span>
                        <span v-else>{{ formModel.correctBeforePriceOco2TriggerPriceZone ? $_getCodeValue('LATEST_TRIGGER_ZONE', 1, formModel.correctBeforePriceOco2TriggerPriceZone) : '-' }}になった時点でOCO1注文を以下の執行条件に訂正</span>
                      </el-col>
                      <!-- 執行方法（逆指値） -->
                      <el-row style="margin-top: 1rem;">
                        <el-col :span="3"
                                style="margin-left: 32%;"
                        >
                          <ifa-input-select
                            v-model="formModel.oco2GyakusasiHouhou"
                            prop="oco2GyakusasiHouhou"
                            :clearable="false"
                            :disabled="oco2GyakusasiHouhouEditDisable2"
                            :code-list-id="'EXECUTE_METHOD'"
                            :disp-pattern="1"
                            :select-pattern="2"
                            style="width: 100px;"
                            @change="handlerA008"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.oco2GyakusasiHouhou === '1'"
                                :span="3"
                                style="margin-right: 10px; margin-left: -10px;"
                        >
                          <ifa-input-select
                            v-if="formModel.oco2GyakusasiHouhou === '1'"
                            v-model="formModel.oco2GyakusasiJyouken"
                            :disabled="true"
                            :clearable="false"
                            :code-list-id="'LIMIT_MARKET_TYPE'"
                            :disp-pattern="2"
                            :select-pattern="6"
                            style="width: 120px;"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.oco2GyakusasiHouhou === '2'"
                                :span="3"
                                style="margin-right: 10px; margin-left: -10px;"
                        >
                          <ifa-input-select
                            v-if="formModel.oco2GyakusasiHouhou === '2'"
                            v-model="formModel.oco2GyakusasiJyouken"
                            :disabled="true"
                            :clearable="false"
                            :code-list-id="'LIMIT_MARKET_TYPE'"
                            :disp-pattern="3"
                            :select-pattern="8"
                            style="width: 120px;"
                          ></ifa-input-select>
                        </el-col>
                        <el-col v-if="formModel.oco2GyakusasiHouhou === '1'"
                                :span="6"
                        >
                          <ifa-input-price
                            :key="priceInfo.orderPriceUnit"
                            v-model="formModel.oco2Price"
                            prop="oco2Price"
                            :required="true"
                            :min="1"
                            :max="maxValueValidator"
                            :digit="1"
                            :domain="IfaUnitPriceYen102DigitsBDomainModel"
                            :initial-step="initialStep"
                            :step-table="priceInfo.orderPriceUnit"
                            unit="円"
                            support
                            size="small"
                          ></ifa-input-price>
                        </el-col>
                        <el-col :span="2">
                          <span>で執行</span>
                        </el-col>
                        <!-- 制限値幅（指値） -->
                        <el-row
                          v-if="ifd2PriceLow == '1.0' && ifd2PriceHigh == '9999999999999.9'"
                          style="padding-top: 0.5rem; width: 97.8%;"
                        >
                          <!-- 空白で6列占有 -->
                          <el-col :span="14"></el-col>
                          <!-- 値幅制限が残りのスペースを占有 -->
                          <el-col :span="10">
                            <span>値幅制限：なし</span>
                          </el-col>
                        </el-row>
                        <el-row
                          v-else-if="ifd2PriceLow != '1.0' || ifd2PriceHigh != '9999999999999.9'"
                          style="padding-top: 0.5rem; width: 97.8%;"
                        >
                          <!-- 空白で6列占有 -->
                          <el-col :span="14"></el-col>
                          <!-- 値幅制限が残りのスペースを占有 -->
                          <el-col :span="10">
                            <span>
                              値幅制限：{{ $_out($_withCommaZeroPadding(ifd2PriceLow)) }}円～{{ $_out($_withCommaZeroPadding(ifd2PriceHigh)) }}円（{{ $_out($_getFormattedDateValue(formModel.priceLimitDate, 'date6')) }}）
                            </span>
                          </el-col>
                        </el-row>
                      </el-row>
                    </el-row>
                  </div>
                </el-col>
              </el-row>
              <!-- 注文期間 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="注文期間">
                      <span>{{ $_out($_getFormattedDateValue(formModel.ifd2Limit, 'date8')) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
              <!-- 信用取引区分 -->
              <el-row>
                <el-col>
                  <div class="form-area__section">
                    <el-form-item label="信用取引区分">
                      <span>{{ $_out(formModel.marginTradeTypeTextCalculation) }}</span>
                    </el-form-item>
                  </div>
                </el-col>
              </el-row>
            </div>

            <!-- IFA固有エリア -->
            <!-- フォーム: 手数料区分 -->
            <el-row>
              <el-col>
                <div class="form-area__section">
                  <el-form-item label="手数料区分">
                    <span>{{ $_getCodeValue('COMM_TYPE', 2, formModel.comId) }}</span>
                  </el-form-item>
                </div>
              </el-col>
            </el-row>

            <!-- フォーム: 勧誘区分 -->
            <el-row>
              <el-col>
                <div class="form-area__section">
                  <ifa-input-select
                    v-model="formModel.kanyuKbn"
                    label="勧誘区分"
                    prop="kanyuKbn"
                    :required="true"
                    :code-list-id="'INVITATION_TYPE'"
                    :disp-pattern="1"
                    :select-pattern="1"
                    :rules="rules"
                  ></ifa-input-select>
                </div>
              </el-col>
            </el-row>

            <!-- フォーム: 受注方法 -->
            <el-row>
              <el-col>
                <ifa-input-select
                  v-model="formModel.orderMethod"
                  label="受注方法"
                  prop="orderMethod"
                  :required="true"
                  :code-list-id="'ORDER_METHOD_TYPE'"
                  :disp-pattern="1"
                  :select-pattern="1"
                  :rules="rules"
                >
                </ifa-input-select>
              </el-col>
            </el-row>

            <!-- フォーム: 重要事項確認 -->
            <el-row style="padding-top: 0.5rem;">
              <el-col>
                <div>
                  <ifa-input-check
                    v-model="formModel.checkInsiderConfirmCheckBox"
                    label="確認項目"
                    prop="checkInsiderConfirmCheckBox"
                    :required="true"
                    :code-list-id="'INSIDER_CONFIRM'"
                    :disp-pattern="3"
                    :select-pattern="2"
                  ></ifa-input-check>
                </div>
                <div 
                  v-if = "formModel.afterCorrectMarket === 'A'"
                  style="padding-top: 0.5rem; margin-left: 235px;"
                  :span="3" 
                >
                  <ifa-input-check
                    v-model="formModel.checkSor"
                    label=""
                    prop="checkSor"
                    :code-list-id="'SOR_CONFIRM'"
                    :disp-pattern="2"
                    :select-pattern="2"
                    :rules="rules"
                  ></ifa-input-check>
                </div>
              </el-col>
            </el-row>
          </el-col>
        </el-row>

        <!-- 操作エリア -->
        <!-- フォーム: 訂正確認ボタン -->
        <el-row style="padding-top: 20px;">
          <ifa-button
            id="ifaMarginNewOrderCorrectInputCorrectionConfirmA010"
            text="訂正確認"
            color="primary"
            action-type="requestAction"
            action-id="SUB0202_0212-02_1#A010"
            :form="$refs.orderForm"
            :pre-request-handler="preRequestHandlerA010"
            :request-model="IfaMarginNewOrderCorrectInputA010RequestModel"
            @response-handler="responseHandlerA010"
          ></ifa-button>
        </el-row>

      </el-form>

    </el-dialog>

    <!-- ダイアログ -->
    <ifa-margin-new-order-correct-confirm
      :is-visible="dialogComfirmVisible"
      :order-info="orderInfo"
      @close-modal="handleCloseModal"
      @order-finish="handleOrderFinish($event)"
    ></ifa-margin-new-order-correct-confirm>

    <ifa-margin-new-order-correct-complete
      :is-visible="dialogFinishVisible"
      :order-info="orderInfo"
      @move-to-order-list="handleMoveToOrderList"
    ></ifa-margin-new-order-correct-complete>

    <ifa-stock-detail-info
      :is-visible="displayStockBoard"
      :form-data="detailInfo"
      @close-modal="displayStockBoard = false"
      @price-select="''"
    ></ifa-stock-detail-info>
  </div>
</template>

<script>
import IfaBrandPriceInfo from '@/components/Info/IfaBrandPriceInfo'
import IfaBrandSearch from '@/components/SearchCondition/IfaBrandSearch'
import ifaNoticeInfo from '@/components/icon/IfaNoticeInfo'
import IfaCurrency152DigitsBDomainModel from '@/resource/domain/IfaCurrency152DigitsBDomainModel.json'
import IfaText10DomainModel from '@/resource/domain/IfaText10DomainModel.json'
import IfaUnitPriceYen102DigitsBDomainModel from '@/resource/domain/IfaUnitPriceYen102DigitsBDomainModel.json'
import IfaMarginNewOrderCorrectComplete from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaMarginNewOrderCorrectComplete'
import IfaMarginNewOrderCorrectConfirm from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaMarginNewOrderCorrectConfirm'
import IfaStockDetailInfo from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/IfaStockDetailInfo'
import { useVModel } from 'vue-composable'
import { IfaStockDetailInfoA001RequestModel } from '../spotTrade/js/IfaStockDetailInfoA001RequestModel'
import { IfaMarginNewOrderCorrectInputA004RequestModel } from './js/IfaMarginNewOrderCorrectInputA004RequestModel'
import { IfaMarginNewOrderCorrectInputA010RequestModel } from './js/IfaMarginNewOrderCorrectInputA010RequestModel'
import { IfaMarginNewOrderCorrectInputFormModel } from './js/IfaMarginNewOrderCorrectInputFormModel'
import { getMessage } from '@/utils/errorHandler'
export default {
  components: {
    IfaMarginNewOrderCorrectConfirm,
    IfaMarginNewOrderCorrectComplete,
    IfaStockDetailInfo,
    IfaBrandSearch,
    IfaBrandPriceInfo,
    ifaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    beforeData: { type: Object, required: true },
    form: { type: Object, required: true },
    ecOrderNo: { type: String, required: true }
  },
  emits: ['close-modal', 'update:isVisible', 'initialize-order-status-list'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    const vmForm = useVModel(props, 'form')
    return {
      vmIsVisible,
      vmForm
    }
  },
  data() {
    return {
      formModel: new IfaMarginNewOrderCorrectInputFormModel(),
      oco2GyakusasiHouhouPullDownList: '3',
      detailInfo: {},
      priceInfo: {
        orderPriceUnit: [] // 使用
      },
      IfaUnitPriceYen102DigitsBDomainModel: IfaUnitPriceYen102DigitsBDomainModel,
      IfaCurrency152DigitsBDomainModel: IfaCurrency152DigitsBDomainModel,
      IfaText10DomainModel: IfaText10DomainModel,
      orderInfo: {},
      styleSeparator: {
        background: '#ffd985',
        fontWeight: 'bold',
        marginBottom: '10px'
      },
      styleDisplay: {
        visibility: 'visible'
      },
      rules: {
        equal: { required: true, type: 'object', trigger: 'blur', validator: this.equalValidator },
        invitationType: [{ required: true, message: '勧誘区分を選択してください｡', trigger: 'blur' }],
        orderMethod: [{ required: true, message: '受注方法を選択してください｡', trigger: 'blur' }],
        checkSor: {
          validator: (rule, value, callback) => {
            if (value !== '1') {
              callback(new Error(getMessage('errors.selected', ['確認項目'])))
            } else {
              callback()
            }
          }
        }
      },
      dialogComfirmVisible: false,
      dialogFinishVisible: false,
      displayStockBoard: false,
      count: 0
    }
  },
  computed: {
    ifd2PriceLow() {
      if (this.formModel.tradeCd === '3') {
        return this.formModel.priceSellLimitLow
      } else if (this.formModel.tradeCd === '4') {
        return this.formModel.priceBuyLimitLow
      } else {
        return ''
      }
    },
    ifd2PriceHigh() {
      if (this.formModel.tradeCd === '3') {
        return this.formModel.priceSellLimitHigh
      } else if (this.formModel.tradeCd === '4') {
        return this.formModel.priceBuyLimitHigh
      } else {
        return ''
      }
    },
    priceLow() {
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
    editDisable() {
      if (this.formModel.sasinariHouhou === '1') {
        if (this.formModel.sasinariJyouken === 'F') {
          return true
        }
        return false
      } else if (this.formModel.sasinariHouhou === '2') {
        return false
      } else if (this.formModel.sasinariHouhou === '3') {
        return true
      }
      return false
    },
    ifd1SasinariHouhouEditDisable() {
      if (this.formModel.ifd1SasinariHouhou === '1') {
        if (this.formModel.ifd1SasinariJyouken === 'F') {
          return true
        }
        return false
      } else if (this.formModel.ifd1SasinariHouhou === '2') {
        return false
      } else if (this.formModel.ifd1SasinariHouhou === '3') {
        return true
      }
      return false
    },
    ifd2SasinariHouhouEditDisable() {
      if (this.formModel.ifd2SasinariHouhou === '1') {
        if (this.formModel.ifd2SasinariJyouken === 'F') {
          return true
        }
        return false
      } else if (this.formModel.ifd2SasinariHouhou === '3') {
        return true
      }
      return false
    },
    gyakusasiHouhouEditDisable() {
      if (this.formModel.gyakusasiHouhou === '1') {
        if (this.formModel.sasinariJyouken === 'F') {
          return true
        }
        return false
      } else if (this.formModel.gyakusasiHouhou === '2') {
        return false
      }
      return false
    },
    oco2GyakusasiHouhouEditDisable2() {
      if (this.formModel.oco2GyakusasiHouhou === '1') {
        if (this.formModel.oco2GyakusasiJyouken === 'F') {
          return true
        }
        return false
      } else if (this.formModel.oco2GyakusasiHouhou === '2') {
        return false
      }
      return false
    },
    ifd1GyakusasiHouhouEditDisable() {
      if (this.formModel.ifd1GyakusasiHouhou === '1') {
        if (this.formModel.ifd1SasinariJyouken === 'F') {
          return true
        }
        return false
      } else if (this.formModel.ifd1GyakusasiHouhou === '2') {
        return false
      }
      return false
    },
    ifd2GyakusasiHouhouEditDisable() {
      if (this.formModel.ifd2GyakusasiHouhou === '1') {
        if (this.formModel.ifd2SasinariJyouken === 'F') {
          return true
        }
        return false
      } else if (this.formModel.ifd2GyakusasiHouhou === '2') {
        return false
      }
      return false
    },
    // 訂正価格/執行方法.取得パターン
    sasinariHouhouDropDownList() {
      if (this.formModel.correctBeforePriceSasinariHouhou === '1') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 2)
      } else if (this.formModel.correctBeforePriceSasinariHouhou === '2') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 2)
      } else if (this.formModel.correctBeforePriceSasinariHouhou === '3') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 5)
      }
      return this.$_getCodeList('EXECUTE_METHOD', 1, 1)
    },
    // 執行方法（逆指値）.取得パターン
    gyakusasiHouhouDropDownList() {
      if (this.formModel.correctBeforePriceGyakusasiHouhou === '1') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 2)
      } else if (this.formModel.correctBeforePriceGyakusasiHouhou === '2') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 2)
      }
      return this.$_getCodeList('EXECUTE_METHOD', 1, 1)
    },
    ifd1SasinariHouhouDropDownList() {
      if (this.formModel.correctBeforePriceIfd1SasinariHouhou === '1') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 2)
      } else if (this.formModel.correctBeforePriceIfd1SasinariHouhou === '2') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 2)
      } else if (this.formModel.correctBeforePriceIfd1SasinariHouhou === '3') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 5)
      }
      return this.$_getCodeList('EXECUTE_METHOD', 1, 1)
    },
    ifd1GyakusasiHouhouDropDownList() {
      if (this.formModel.correctBeforePriceIfd1GyakusasiHouhou === '1') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 2)
      } else if (this.formModel.correctBeforePriceIfd1GyakusasiHouhou === '2') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 2)
      }
      return this.$_getCodeList('EXECUTE_METHOD', 1, 1)
    },
    ifd2SasinariHouhouDropDownList() {
      if (this.formModel.correctBeforePriceIfd2SasinariHouhou === '1') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 3)
      } else if (this.formModel.correctBeforePriceIfd2SasinariHouhou === '3') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 5)
      }
      return this.$_getCodeList('EXECUTE_METHOD', 1, 1)
    },
    ifd2GyakusasiHouhouDropDownList() {
      if (this.formModel.correctBeforePriceIfd2GyakusasiHouhou === '1') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 2)
      } else if (this.formModel.correctBeforePriceIfd2GyakusasiHouhou === '2') {
        return this.$_getCodeList('EXECUTE_METHOD', 1, 2)
      }
      return this.$_getCodeList('EXECUTE_METHOD', 1, 1)
    },
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

      return 0
    },
    IfaStockDetailInfoA001RequestModel() {
      return new IfaStockDetailInfoA001RequestModel(
        {
          brandCode: this.formModel.brandCode,
          market: this.vmForm.market
        }
      )
    },
    IfaMarginNewOrderCorrectInputA004RequestModel() {
      return new IfaMarginNewOrderCorrectInputA004RequestModel(this.formModel)
    },
    IfaMarginNewOrderCorrectInputA010RequestModel() {
      return new IfaMarginNewOrderCorrectInputA010RequestModel(this.formModel)
    },
    isMarketVisible() {
      
      // 注文種別 = 通常/逆指値 かつ 執行方法（通常/逆指値）≠ 逆指値 かつ 直近市場 = 東京の場合、項目表示
      if (this.formModel.orderKind === '1' && this.formModel.sasinariHouhou !== '3' && this.formModel.sorServiceKbn === '1' && this.formModel.latestMarketId === '0') {
          return true
      }
      // 上記以外 項目非表示
      return false
    },
    isPeriodVisible() {
      // 注文種別 != 通常/逆指値　の場合、項目表示
      if (this.formModel.orderKind !== '1'){
        return true
      // 注文種別 = 通常/逆指値　かつ　執行方法（通常/逆指値）= 逆指値 の場合、項目表示
      } else if (this.formModel.orderKind === '1' && this.formModel.sasinariHouhou === '3'){
        return true
      // 上記以外
      } else {
        // 直近市場 != 東証 の場合、項目表示
        if (this.formModel.latestMarketId !== '0'){
          return true
        } else {
          // 訂正後市場 = 東証 または 約定ステータス = 未出来 の場合、項目表示
          if (this.formModel.afterCorrectMarket === '0' || this.formModel.tradeStatus === '0'){
            return true
          }
        }
        // 上記以外 項目非表示
        return false
      }
    },
    isPeriodTodayVisible() {
      // 訂正後市場＝当社優先市場／SOR　かつ　約定ステータス＝"1"：一部出来　の場合　項目表示
      if (this.formModel.afterCorrectMarket === 'A' && this.formModel.tradeStatus === '1') {
        return true
      }
      // 上記以外 項目非表示
      return false
    }
  },
  methods: {
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
    fomatNumber(value) {
      const num = parseFloat(value)
      if (isNaN(num)) {
        return '-'
      }
      return num < 0 ? 0 : num
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    onOpen() {
      Object.assign(this.formModel, this.vmForm)
      this.$refs['ifaBrandSearch'].resetAll()
      this.$nextTick(() => {
        this.$refs['ifaBrandSearch'].setStockInfo({ brandCode: this.vmForm.brandCode })
      })
      this.formModel.ecOrderNo = this.ecOrderNo
      this.formModel.triggerPrice = this.vmForm.triggerPrice
      if (
        this.formModel.sasinariHouhou === '2' ||
        this.formModel.gyakusasiHouhou === '2'
      ) {
        this.formModel.price = ''
      }
      if (this.formModel.oco2GyakusasiHouhou === '2') {
        this.formModel.oco2Price = ''
      }
      if (
        this.formModel.ifd1SasinariHouhou === '2' ||
        this.formModel.ifd1GyakusasiHouhou === '2'
      ) {
        this.formModel.ifd1Price = ''
      }
      if (
        this.formModel.ifd2SasinariHouhou === '2' ||
        this.formModel.ifd2GyakusasiHouhou === '2'
      ) {
        this.formModel.ifd2Price = ''
      }
      // 訂正後市場のデフォルト値セット
      // 表示条件を満たす場合のみセットする
      if (this.isMarketVisible) {
        // SOR注文区分＝"△"：SOR対象外注文　の場合 東証　をデフォルト選択
        if (this.formModel.sorOrderClassification === ' ') {
          this.formModel.afterCorrectMarket = '0'
        // 上記以外 当社優先市場／SOR　をデフォルト選択
        } else {
          this.formModel.afterCorrectMarket = 'A'
        }
      }
      this.formModel.correctBeforePriceSasinariHouhou = this.formModel.sasinariHouhou
      this.formModel.correctBeforePriceSasinariJyouken = this.formModel.sasinariJyouken
      this.formModel.correctBeforePriceTriggerPrice = this.formModel.triggerPrice
      this.formModel.correctBeforePriceTriggerPriceZone = this.formModel.triggerPriceZone
      this.formModel.correctBeforePriceGyakusasiHouhou = this.formModel.gyakusasiHouhou
      this.formModel.correctBeforePricePrice = this.formModel.price
      this.formModel.correctBeforePriceOco1SasinariHouhou = this.formModel.oco1SasinariHouhou // 訂正前価格.OCO1.執行方法
      this.formModel.correctBeforePriceOco1SasinariJyouken = this.formModel.oco1SasinariJyouken // 訂正前価格.OCO1.執行条件
      this.formModel.correctBeforePriceOco1Price = this.formModel.oco1Price // 訂正前価格.OCO1.注文単価
      this.formModel.correctBeforePriceOco2TriggerPrice = this.formModel.oco2TriggerPrice // 訂正前価格.OCO2.発火条件価格（逆指値）
      this.formModel.correctBeforePriceOco2TriggerPriceZone = this.formModel.oco2TriggerPriceZone // 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン
      this.formModel.correctBeforePriceOco2GyakusasiHouhou = this.formModel.oco2GyakusasiHouhou // 訂正前価格.OCO2.執行方法（逆指値）
      this.formModel.correctBeforePriceOco2GyakusasiJyouken = this.formModel.oco2GyakusasiJyouken // 訂正前価格.OCO2.執行条件（逆指値）
      this.formModel.correctBeforePriceOco2Price = this.formModel.oco2Price // 訂正前価格.OCO2.注文単価
      this.formModel.correctBeforePriceIfd1SasinariHouhou = this.formModel.ifd1SasinariHouhou // 訂正前価格.IFD1.執行方法
      this.formModel.correctBeforePriceIfd1SasinariJyouken = this.formModel.ifd1SasinariJyouken // 訂正前価格.IFD1.執行条件
      this.formModel.correctBeforePriceIfd1TriggerPrice = this.formModel.ifd1TriggerPrice // 訂正前価格.IFD1.発火条件価格（逆指値）
      this.formModel.correctBeforePriceIfd1TriggerPriceZone = this.formModel.ifd1TriggerPriceZone // 訂正前価格.IFD1.発火条件価格（逆指値）ゾーン
      this.formModel.correctBeforePriceIfd1GyakusasiHouhou = this.formModel.ifd1GyakusasiHouhou // 訂正前価格.IFD1.執行方法（逆指値）
      this.formModel.correctBeforePriceIfd1Price = this.formModel.ifd1Price // 訂正前価格.IFD1.注文単価
      this.formModel.correctBeforePriceIfd2Limit = this.formModel.ifd2Limit // 訂正前価格.IFD2.期間.日付
      this.formModel.correctBeforePriceIfd2SasinariHouhou = this.formModel.ifd2SasinariHouhou // 訂正前価格.IFD2.執行方法
      this.formModel.correctBeforePriceIfd2SasinariJyouken = this.formModel.ifd2SasinariJyouken // 訂正前価格.IFD2.執行条件
      this.formModel.correctBeforePriceIfd2TriggerPrice = this.formModel.ifd2TriggerPrice // 訂正前価格.IFD2.発火条件価格（逆指値）
      this.formModel.correctBeforePriceIfd2TriggerPriceZone = this.formModel.ifd2TriggerPriceZone // 訂正前価格.IFD2.発火条件価格（逆指値）ゾーン
      this.formModel.correctBeforePriceIfd2GyakusasiHouhou = this.formModel.ifd2GyakusasiHouhou // 訂正前価格.IFD2.執行方法（逆指値）
      this.formModel.correctBeforePriceIfd2Price = this.formModel.ifd2Price // 訂正前価格.IFD2.注文単価
      this.formModel.marginTradeTypeText = this.formModel.marginTradeTypeTextCalculation
    },
    handleChangeBrand(brandInfo) {
      this.formModel.cc014.brandCode = brandInfo.data.brandCode
      this.formModel.cc014.selectedMarket = this.$refs['ifaBrandSearch'].selectedMarket !== 'A'
        ? this.$refs['ifaBrandSearch'].selectedMarket
        : brandInfo.data.upperRankMkt
      this.$refs['ifaBrandPriceInfo'].updateRequest()
    },
    responseHandlerA003(response) {
      Object.assign(this.detailInfo, response.dataList[0])
      this.displayStockBoard = true
    },
    responseHandlerA004(response) {
      this.formModel = Object.assign(this.formModel, response.dataList[0])
      this.$refs['ifaBrandPriceInfo'].updateRequest()
      // this.changeTradeType(this.formModel.tradeCd)
    },
    formatToDecimal(value) {
      if (!value) {
        return value
      }
      if (value.length > 2) {
        return value.slice(0, value.length - 2) + '.' + value.slice(value.length - 2, value.length)
      } else if (value.length === 2) {
        return '0.' + value
      } else if (value.length === 1) {
        return '0.0' + value
      }
      return value
    },
    handlerA005() {
      if (this.formModel.sasinariHouhou === '1') {
        if (this.formModel.sasinariJyouken === 'N') {
          this.formModel.sasinariJyouken = ' '
        }
        if (this.formModel.sasinariJyouken === 'Y') {
          this.formModel.sasinariJyouken = 'Z'
        }
        if (this.formModel.sasinariJyouken === 'H') {
          this.formModel.sasinariJyouken = 'I'
        }
        if (this.formModel.sasinariJyouken === 'O') {
          this.formModel.sasinariJyouken = 'P'
        }
      }
      if (this.formModel.sasinariHouhou === '2') {
        if (this.formModel.sasinariJyouken === ' ') {
          this.formModel.sasinariJyouken = 'N'
        }
        if (this.formModel.sasinariJyouken === 'Z') {
          this.formModel.sasinariJyouken = 'Y'
        }
        if (this.formModel.sasinariJyouken === 'I') {
          this.formModel.sasinariJyouken = 'H'
        }
        if (this.formModel.sasinariJyouken === 'P') {
          this.formModel.sasinariJyouken = 'O'
        }
      }
    },
    ifd1HandlerA005() {
      if (this.formModel.ifd1SasinariHouhou === '1') {
        if (this.formModel.ifd1SasinariJyouken === 'N') {
          this.formModel.ifd1SasinariJyouken = ' '
        }
        if (this.formModel.ifd1SasinariJyouken === 'Y') {
          this.formModel.ifd1SasinariJyouken = 'Z'
        }
        if (this.formModel.ifd1SasinariJyouken === 'H') {
          this.formModel.ifd1SasinariJyouken = 'I'
        }
        if (this.formModel.ifd1SasinariJyouken === 'O') {
          this.formModel.ifd1SasinariJyouken = 'P'
        }
      }
      if (this.formModel.ifd1SasinariHouhou === '2') {
        if (this.formModel.ifd1SasinariJyouken === ' ') {
          this.formModel.ifd1SasinariJyouken = 'N'
        }
        if (this.formModel.ifd1SasinariJyouken === 'Z') {
          this.formModel.ifd1SasinariJyouken = 'Y'
        }
        if (this.formModel.ifd1SasinariJyouken === 'I') {
          this.formModel.ifd1SasinariJyouken = 'H'
        }
        if (this.formModel.ifd1SasinariJyouken === 'P') {
          this.formModel.ifd1SasinariJyouken = 'O'
        }
      }
    },
    ifd2HandlerA005() {
      if (this.formModel.ifd2SasinariHouhou === '1') {
        if (this.formModel.ifd2SasinariJyouken === 'N') {
          this.formModel.ifd2SasinariJyouken = ' '
        }
        if (this.formModel.ifd2SasinariJyouken === 'Y') {
          this.formModel.ifd2SasinariJyouken = 'Z'
        }
        if (this.formModel.ifd2SasinariJyouken === 'H') {
          this.formModel.ifd2SasinariJyouken = 'I'
        }
        if (this.formModel.ifd2SasinariJyouken === 'O') {
          this.formModel.ifd2SasinariJyouken = 'P'
        }
      }
      if (this.formModel.ifd2SasinariHouhou === '2') {
        if (this.formModel.ifd2SasinariJyouken === ' ') {
          this.formModel.ifd2SasinariJyouken = 'N'
        }
        if (this.formModel.ifd2SasinariJyouken === 'Z') {
          this.formModel.ifd2SasinariJyouken = 'Y'
        }
        if (this.formModel.ifd2SasinariJyouken === 'I') {
          this.formModel.ifd2SasinariJyouken = 'H'
        }
        if (this.formModel.ifd2SasinariJyouken === 'P') {
          this.formModel.ifd2SasinariJyouken = 'O'
        }
      }
    },
    handlerA008() {
      if (this.formModel.gyakusasiHouhou === '1') {
        if (this.formModel.sasinariJyouken === 'N') {
          this.formModel.sasinariJyouken = ' '
        }
        if (this.formModel.sasinariJyouken === 'H') {
          this.formModel.sasinariJyouken = 'I'
        }
      }
      if (this.formModel.gyakusasiHouhou === '2') {
        if (this.formModel.sasinariJyouken === ' ') {
          this.formModel.sasinariJyouken = 'N'
        }
        if (this.formModel.sasinariJyouken === 'I') {
          this.formModel.sasinariJyouken = 'H'
        }
      }
      if (this.formModel.oco2GyakusasiHouhou === '1') {
        if (this.formModel.oco2GyakusasiJyouken === 'N') {
          this.formModel.oco2GyakusasiJyouken = ' '
        }
      }
      if (this.formModel.oco2GyakusasiHouhou === '2') {
        if (this.formModel.oco2GyakusasiJyouken === ' ') {
          this.formModel.oco2GyakusasiJyouken = 'N'
        }
      }
      if (this.formModel.ifd1GyakusasiHouhou === '1') {
        if (this.formModel.ifd1SasinariJyouken === 'N') {
          this.formModel.ifd1SasinariJyouken = ' '
        }
        if (this.formModel.ifd1SasinariJyouken === 'H') {
          this.formModel.ifd1SasinariJyouken = 'I'
        }
      }
      if (this.formModel.ifd1GyakusasiHouhou === '2') {
        if (this.formModel.ifd1SasinariJyouken === ' ') {
          this.formModel.ifd1SasinariJyouken = 'N'
        }
        if (this.formModel.ifd1SasinariJyouken === 'I') {
          this.formModel.ifd1SasinariJyouken = 'H'
        }
      }
      if (this.formModel.ifd2GyakusasiHouhou === '1') {
        if (this.formModel.ifd2SasinariJyouken === 'N') {
          this.formModel.ifd2SasinariJyouken = ' '
        }
        if (this.formModel.ifd2SasinariJyouken === 'H') {
          this.formModel.ifd2SasinariJyouken = 'I'
        }
      }
      if (this.formModel.ifd2GyakusasiHouhou === '2') {
        if (this.formModel.ifd2SasinariJyouken === ' ') {
          this.formModel.ifd2SasinariJyouken = 'N'
        }
        if (this.formModel.ifd2SasinariJyouken === 'I') {
          this.formModel.ifd2SasinariJyouken = 'H'
        }
      }
    },
    preRequestHandlerA010() {
      if (this.formModel.sasinariHouhou === '1') {
        this.IfaMarginNewOrderCorrectInputA010RequestModel.price = this.formModel.price
      } else if (this.formModel.correctBeforePriceSasinariHouhou === '3' && this.formModel.gyakusasiHouhou === '1') {
        this.IfaMarginNewOrderCorrectInputA010RequestModel.price = this.formModel.price
      } else {
        this.IfaMarginNewOrderCorrectInputA010RequestModel.price = ''
      }
      if (this.formModel.oco2GyakusasiHouhou === '1') {
        this.IfaMarginNewOrderCorrectInputA010RequestModel.oco2Price = this.formModel.oco2Price
      } else {
        this.IfaMarginNewOrderCorrectInputA010RequestModel.oco2Price = ''
      }
      if (this.formModel.ifd1SasinariHouhou === '1') {
        this.IfaMarginNewOrderCorrectInputA010RequestModel.ifd1Price = this.formModel.ifd1Price
      } else if (this.formModel.correctBeforePriceIfd1SasinariHouhou === '3' && this.formModel.ifd1GyakusasiHouhou === '1') {
        this.IfaMarginNewOrderCorrectInputA010RequestModel.ifd1Price = this.formModel.ifd1Price
      } else {
        this.IfaMarginNewOrderCorrectInputA010RequestModel.ifd1Price = ''
      }
      if (this.formModel.ifd2SasinariHouhou === '1') {
        this.IfaMarginNewOrderCorrectInputA010RequestModel.ifd2Price = this.formModel.ifd2Price
      } else if (this.formModel.correctBeforePriceIfd2SasinariHouhou === '3' && this.formModel.ifd2GyakusasiHouhou === '1') {
        this.IfaMarginNewOrderCorrectInputA010RequestModel.ifd2Price = this.formModel.ifd2Price
      } else {
        this.IfaMarginNewOrderCorrectInputA010RequestModel.ifd2Price = ''
      }
    },
    responseHandlerA010(response) {
      this.orderInfo = response.dataList[0]
      this.dialogComfirmVisible = true
    },
    // 戻るボタン
    onDialogClose() {
      this.$refs['orderForm'].clearValidate()
      this.$emit('close-modal')
    },
    // 確認画面で戻るボタン押下時のイベント処理
    handleCloseModal() {
      this.styleDisplay.visibility = 'visible'
      this.dialogComfirmVisible = false
    },
    // 完了画面に遷移
    handleOrderFinish(response) {
      this.orderInfo = response.dataList[0]
      this.dialogComfirmVisible = false
      this.dialogFinishVisible = true
    },
    // 注文一覧画面に遷移（画面更新）
    handleMoveToOrderList() {
      this.dialogFinishVisible = false
      this.styleDisplay.visibility = 'visible'
      this.$emit('initialize-order-status-list')
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
.margin_new_order_correct {
.el-dialog__title{
font-weight: bold;
}
}
</style>

<style lang="scss" scoped>
  .info-item__header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
}
:deep(.el-form-item__label) {
  font-weight: 700 !important;
  margin-left: 3rem;
}
:deep(.el-form-item__label) {
  font-weight: 700 !important
}
:deep(.el-tabs_full_content) .el-tabs__content {
  padding: 0;
}
.order-input {
  width: 80%;
}
.header-button {
  display: flex;
  justify-content: flex-end;
  padding: 0 0 1rem;
}
.form-radio {
  width: 4rem;
}
.form-area__section {
  border-bottom: 1px solid #eee;
  margin: 0.5rem 0 0 0;
  padding: 0.5rem 0 1rem 0px;
}
.form-button__wrapper {
  display: flex;
  padding: 1.5rem 2rem 0.3rem 2rem;
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
.error-message {
  margin: 0.5rem;
  color: #f00;
  font-size: 14px;
  font-weight: 600;
}
.warning-message {
  margin: 0.5rem;
  color: #f00;
  font-size: 14px;
}
.info-message {
  margin: 0.5rem;
  color: #000;
  font-size: 14px;
}
.custom-loading-text {
  color: #fff;
  font-size: 20px;
}
:deep(.el-form-item__error) {
  // margin-left: 2.5rem;
  width: max-content;
}
.input-execution-form {
  width: 105px;
}
.input-method-form {
  margin-left: 1rem;
  width: 115px;
}
.input-price-form {
  margin-left: 1rem;
  width: 270px;
}
:deep(.ifa-brand-search__wrapper) .market-info {
  padding-left: 0.4rem !important;
}
:deep(input) {
  height: 40px;
}
:deep(.correction-dialog) .el-dialog {
  width: 1200px;
}
:deep(.el-input__wrapper>.el-input__inner) {
  height: 30px;
}
</style>
