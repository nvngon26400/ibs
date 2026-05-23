<template>
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="caption_card">
      <el-form
        ref="orderForm"
        :model="form"
        :rules="rules"
        label-width="180px"
        label-position="left"
      >
        <el-row
          v-if="form.buySellTypeName === '1' && form.acPosition === '0'"
        >
          <div style="color: red; margin: 1rem 0">注文可能な預りが不足しているため、注文できません。</div>
        </el-row>

        <!-- 銘柄検索 -->
        <el-row>
          <ifa-foreign-brand-search
            ref="ifaForeignSpotTradeOrderInputIfaForeignBrandSearch"
            :trade-type="form.buySellTypeName"
            :is-ifa-button="isIfaButton"
            :form="form"
            :a003-request-model="a003RequestModel"
            :a005-request-model="a005RequestModel"
            :a011-request-model="a011RequestModel"
            :edit-disable="editDisable"
            @a003-pre="stockPriceDisplayA003Pre"
            @a003-res="stockPriceDisplayA003Res"
            @a005="updateA005"
            @reset-brand-search="initialize"
          ></ifa-foreign-brand-search>
        </el-row>
        <!-- /銘柄検索 -->

        <!--ご注意情報-->
        <el-row>
          <el-card
            v-if="!editDisable"
            style="background-color: #eee; margin-bottom: 0.5rem;"
          >
            <el-row style="padding-top: 0.5rem;">
              <el-row>
                <el-col
                  :offset="1"
                  style="font-size :14px; display:flex; align-items: center;"
                >
                  <el-icon><WarningFilled></WarningFilled></el-icon>
                  <span>{{ $_getCodeValue('NATIONALITY_CODE', 2, form.countryCode) }}株式&emsp;ご注意事項</span>
                </el-col>
              </el-row>
              <el-row
                class="links"
                style="padding-top: 0.5rem; margin-left: 4.1666666667%;"
              >
                <ifa-link
                  disp-name="・本日の注意銘柄"
                  :url-id="18"
                  :url-object="{ countryCode: form.countryCode }"
                ></ifa-link>
                <ifa-link
                  disp-name="・休場日"
                  :url-id="19"
                  :url-object="{ closedCountryCode: form.countryCode === 'HK' ? 'cn' : form.countryCode.toLowerCase() }"
                ></ifa-link>
                <ifa-link
                  disp-name="・円貨決済停止日"
                  :param-url="form.yenClosed"
                  link-icon-image="externalLink"
                ></ifa-link>
                <ifa-link
                  disp-name="・取扱銘柄一覧"
                  :url-id="21"
                  :url-object="{ MarginCountryCode: form.countryCode === 'US' ? 'usequity' : (form.countryCode === 'CN' ? 'hk' :form.countryCode.toLowerCase() ) }"
                ></ifa-link>
                <ifa-link
                  disp-name="・お取引注意事項"
                  :param-url="form.tradingAttention"
                  link-icon-image="externalLink"
                ></ifa-link>
              </el-row>
            </el-row>
          </el-card>
        </el-row>

        <!--余力情報-->
        <el-row>
          <el-card 
            v-if="!editDisable"
            style="background-color: #eee; margin-bottom: 0.5rem;">
            <el-row
              style="padding-top: 0.5rem; max-width: 1050px;"
            >
              <el-col
                :offset="1"
                :span="8"
                style="display: flex; align-items: flex-start;"
              >
                <div style="display: inline-block;">
                  <!-- No.43 -->
                  <span
                    v-if="form.stockPriceKindList.includes('FOREIGN_SETTLEMENT') && customerInfo.jrIsaContractType === '1'"
                    class="info-item__header"
                  >総合口座_買付余力（外貨）</span>
                  <span
                    v-else-if="form.stockPriceKindList.includes('FOREIGN_SETTLEMENT')"
                    class="info-item__header"
                  >買付余力（外貨）
                  </span>
                  <span
                    v-else
                    class="info-item__header"
                  >
                  </span>
                </div>
                <div style="display: inline-block; margin-left: auto;">
                  <!-- No.44 -->
                  <span
                    v-if="!editDisable && form.stockPriceKindList.includes('FOREIGN_SETTLEMENT')"
                    class="info-item__header"
                  >{{ form.buyingPowerForeignList[0].foreignBuyingPower ? ifaFormatUtils.withCommaInteger(form.buyingPowerForeignList[0].foreignBuyingPower) : '' }}&nbsp;</span>
                  <span
                    v-else
                    class="info-item__header"
                  ></span>
                </div>
              </el-col>
              <el-col
                :span="2"
              >
                <!-- No.45 -->
                <span
                  v-if="!editDisable && form.stockPriceKindList.includes('FOREIGN_SETTLEMENT')"
                  class="info-item__header"
                >{{ $_out(form.buyingPowerForeignList[0].currencyCode) }}</span>
                <span
                  v-else
                  class="info-item__header"
                ></span>
              </el-col>
              <el-col
                v-if="form.stockPriceKindList.includes('YEN_SETTLEMENT')"
                :offset="3"
                :span="8"
                style="display: flex; align-items: flex-start;"
              >
                <div style="display: inline-block;">
                  <!-- No.47 -->
                  <span
                    v-if="customerInfo.jrIsaContractType === '1'"
                    class="info-item__header"
                  >総合口座_買付余力（円貨）</span>
                  <span
                    v-else
                    class="info-item__header"
                  >買付余力（円貨）</span>
                </div>
                <div
                  v-if="form.stockPriceKindList.includes('YEN_SETTLEMENT')"
                  style="display: inline-block; margin-left: auto;"
                >

                  <!-- No.48 -->
                  <span
                    v-if="!editDisable && form.buyingPowerDomesticList[0].yenBuyingPowerGeneralAccount"
                    class="info-item__header"
                  >{{ form.buyingPowerDomesticList[0].yenBuyingPowerGeneralAccount ? ifaFormatUtils.withCommaInteger(form.buyingPowerDomesticList[0].yenBuyingPowerGeneralAccount) : '' }}&nbsp;</span>
                  <span
                    v-else
                    class="info-item__header"
                  ></span>
                </div>
              </el-col>
              <el-col
                v-if="form.stockPriceKindList.includes('YEN_SETTLEMENT')"
                :span="2"
              >
                <!-- No.48の単位 -->
                <span
                  v-if="!editDisable && form.buyingPowerDomesticList[0].yenBuyingPowerGeneralAccount"
                  class="info-item__header"
                >円
                </span>
                <span
                  v-else
                  class="info-item__header"
                >
                </span>
              </el-col>
            </el-row>
            <el-row
              style="padding-top: 0.5rem; max-width: 1050px;"
            >
              <el-col
                :offset="1"
                :span="8"
                style="display: flex; align-items: flex-start;"
              >
                <div style="display: inline-block;">
                  <!-- No.49 -->
                  <span
                    v-if="customerInfo.isaContractType === '1' && form.stockPriceKindList.includes('YEN_SETTLEMENT')"
                    class="info-item__header"
                  >{{ NisaInvestableQuoteLabelThisYear() }}
                  </span>
                  <!-- No.55 -->
                  <span
                    v-else-if="customerInfo.jrIsaContractType === '1' && form.stockPriceKindList.includes('FOREIGN_SETTLEMENT')"
                    class="info-item__header"
                  >ジュニア口座　買付余力（外貨）</span>
                </div>
                <div style="display: inline-block; margin-left: auto;">
                  <!-- No.50 -->
                  <span
                    v-if="!editDisable && customerInfo.isaContractType === '1' && form.stockPriceKindList.includes('YEN_SETTLEMENT')"
                    class="info-item__header"
                  >{{ NisaInvestableQuoteThisYear() }}&nbsp;
                  </span>
                  <span
                    v-else-if="!editDisable && customerInfo.jrIsaContractType === '1' && form.stockPriceKindList.includes('FOREIGN_SETTLEMENT')"
                    class="info-item__header"
                  >
                    {{ form.buyingPowerForeignList[0].foreignBuyingPowerJrNisa ? ifaFormatUtils.withCommaInteger(form.buyingPowerForeignList[0].foreignBuyingPowerJrNisa) : '' }}&nbsp;
                  </span>
                  <span
                    v-else
                    class="info-item__header"
                  >
                  </span>
                </div>
              </el-col>
              <el-col :span="2">
                <span
                  v-if="!editDisable && customerInfo.isaContractType === '1' && form.stockPriceKindList.includes('YEN_SETTLEMENT')"
                  class="info-item__header"
                >円
                </span>
                <!-- No.56 -->
                <span
                  v-else-if="!editDisable && customerInfo.jrIsaContractType === '1' && form.stockPriceKindList.includes('FOREIGN_SETTLEMENT')"
                  class="info-item__header"
                >{{ form.buyingPowerForeignList[0].currencyCodeJrNisa }}
                </span>
                <span
                  v-else
                  class="info-item__header"
                >
                </span>
              </el-col>
              <el-col
                v-if="form.stockPriceKindList.includes('YEN_SETTLEMENT')"
                :offset="3"
                :span="8"
                style="display: flex; align-items: flex-start;"
              >
                <div style="display: inline-block;">
                  <!-- No.51 -->
                  <span
                    v-if="customerInfo.isaContractType === '1'"
                    class="info-item__header"
                  >{{ NisaInvestableQuoteLabelNextYear() }}
                  </span>
                  <!-- No.58のラベル -->
                  <span
                    v-else-if="customerInfo.jrIsaContractType === '1'"
                    class="info-item__header"
                  >ジュニア口座　買付余力（円貨）
                  </span>
                </div>
                <div
                  v-if="!editDisable && form.stockPriceKindList.includes('YEN_SETTLEMENT')"
                  style="display: inline-block; margin-left: auto;"
                >
                  <!-- No.52 -->
                  <span
                    v-if="customerInfo.isaContractType === '1'"
                    class="info-item__header"
                  >{{ NisaInvestableQuoteNextYear() }}&nbsp;
                  </span>
                  <!-- No.58 -->
                  <span
                    v-else-if="customerInfo.jrIsaContractType === '1'"
                    class="info-item__header"
                  >
                    {{ form.buyingPowerDomesticList[0].yenBuyingPowerJrNisa ? ifaFormatUtils.withCommaInteger(form.buyingPowerDomesticList[0].yenBuyingPowerJrNisa) : '' }}&nbsp;
                  </span>
                </div>
              </el-col>
              <el-col
                v-if="!editDisable && form.stockPriceKindList.includes('YEN_SETTLEMENT')"
                :span="2"
              >
                <!-- No.52 -->
                <span
                  v-if="customerInfo.isaContractType === '1'"
                  class="info-item__header"
                >{{ NisaInvestableQuoteUnitNextYear() }}
                </span>
                <!-- No.58の単位 -->
                <span
                  v-else-if="customerInfo.jrIsaContractType === '1'"
                  class="info-item__header"
                >
                  {{ form.buyingPowerDomesticList[0].yenBuyingPowerJrNisa ? '円' : '' }}
                </span>
              </el-col>
            </el-row>
          </el-card>
        </el-row>

        <!-- 通常注文タブ -->
        <el-card
          class="box-card"
          :style="{'background-color': bgColor}"
        >
          <el-row class="form-area__section">
            <el-col :span="12">
              <el-form-item
                label="取引種別"
                prop="buySellTypeName"
                style="margin-top: 1rem;"
              >
                <!--form取得前に画面レンダリングされてしまうためv-ifで制御 -->
                <span
                  v-if="form.buySellTypeName"
                  class="bold"
                  :class="{'font-color__plus': form.buySellTypeName === '0', 'font-color__minus': form.buySellTypeName === '1'}"
                >{{ $_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, form.buySellTypeName) }}</span>
              </el-form-item>
            </el-col>
            <!-- リセットボタン -->
            <el-col
              :span="4"
              class="right-btn"
            >
              <ifa-button
                id="btnReset"
                :disabled="editDisable"
                text="リセット"
                color="secondary"
                action-type="originalAction"
                @app-action-handler="resetA016"
              ></ifa-button>
            </el-col>
          </el-row>

          <!--注文可能数量（売却注文でのみ表示）-->
          <el-row
            v-if="form.buySellTypeName === '1'"
            class="form-area__section"
          >
            <el-col
              :span="12"
            >
              <el-form-item
                label="注文可能数量"
              >
                <span>{{ $_out(ifaFormatUtils.withCommaInteger(form.acPosition)) }} 株</span>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row class="form-area__section">
            <div
              style="flex: 0 0 635px"
            >
              <ifa-input-quantity
                id="quantity"
                v-model="form.foreignQuantity"
                style="width: 200px;"
                label="数量"
                prop="foreignQuantity"
                :disabled="editDisable"
                :min="Math.max(form.minimumTradingQuantity, 1)"
                :max="maxQuantity"
                :step="form.tradingUnit"
                :initial-step="Math.max(Math.max(form.minimumTradingQuantity, form.tradingUnit), 1)"
                :support="true"
                :placeholder="' '"
                :domain="IfaVolume17DomainModel"
                unit="株"
              >
              </ifa-input-quantity>
            </div>
            <div
              style="flex: 1 0 300px; margin-top: 0.7rem;"
            >
              <span v-if="!editDisable">（{{ $_out(form.minimumTradingQuantity) }}株以上{{ $_out(form.tradingUnit) }}株単位）</span>
            </div>
          </el-row>
          <!--注文可能数量（売却注文でのみ表示）-->

          <!-- フォーム: 価格 -->
          <div class="form-area__section">
            <el-row>
              <el-col
                :span="24"
                style="display: flex;"
              >
                <div>
                  <el-form-item
                    label="価格"
                    prop="orderPriceKindList"
                    style="width: 500px"
                  >
                    <!-- フォーム: 価格/執行方法 -->
                    <el-select
                      v-model="form.orderPriceKind"
                      :disabled="editDisable"
                      @change="changeOrderPrice"
                    >
                      <el-option
                        v-for="item in orderPriceKindList"
                        :key="item.value"
                        :value="item.value"
                        :label="item.label"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                </div>
                <!-- /フォーム: 価格/執行方法 -->
                <!-- フォーム: 指値選択時の条件設定 @価格/執行方法 -->
                <div
                  v-if="form.orderPriceKind === '1'"
                  style="width: 850px;"
                >
                  <div style="display: inline-flex;">
                    <!-- No.71 -->
                    <ifa-input-price
                      id="limitOrderPrice"
                      v-model="form.limitOrderPrice"
                      prop="limitOrderPrice"
                      :disabled="editDisable"
                      :min="getMinValue()"
                      :max="getMaxValue()"
                      :step-table="form.countryCode === 'US' || form.countryCode === 'RU' ? null : form.callValue"
                      :step="form.countryCode === 'US' ? 0.01 : (form.countryCode === 'RU' ? form.russianCallValue : 0)"
                      :initial-step="round(form.priceBasicInfo?.currentPrice ? form.priceBasicInfo?.currentPrice : (form.priceBasicInfo?.last ? form.priceBasicInfo?.last : 0), 2)"
                      :digit="form.countryCode === 'US' ? 2 : (form.countryCode === 'RU' ? getDigit(form.russianCallValue) : null)"
                      :placeholder="' '"
                      :support="true"
                      :domain="IfaUnitPriceForeign12DigitsDDomainModel"
                      :unit="form.limitPriceText"
                    >
                    </ifa-input-price>
                    <!-- A003実行時に描画されるようv-ifで制御 -->
                    <div
                      v-if="!editDisable && form.countryCode !== 'US'"
                      style="display: inline-block; margin-top: 0.45rem;"
                    >
                      <ifa-link
                        disp-name="呼値・制限値幅"
                        :url-id="36"
                        :url-object="{ OrderRulePriceCountryCode: form.countryCode === 'RU' ? 'rs' : form.countryCode.toLowerCase() }"
                      ></ifa-link>
                    </div>
                  </div>
                </div>

                <!-- フォーム: 逆指値選択時の条件設定 @価格/執行方法 -->
                <div
                  v-if="form.orderPriceKind == '3'"
                  style="width: 1000px;"
                >
                  <div style="display: inline-flex;">
                    <span style="padding: 0.6rem 30px 0 0">現在値が</span>
                    <!-- No.74 -->
                    <ifa-input-price
                      id="stopOrderPrice"
                      v-model="form.stopOrderPrice"
                      prop="stopOrderPrice"
                      :disabled="editDisable"
                      :min="getMinValue()"
                      :max="getMaxValue()"
                      :step-table="form.countryCode === 'US' || form.countryCode === 'RU' ? null : form.callValue"
                      :step="form.countryCode === 'US' ? 0.01 : (form.countryCode === 'RU' ? form.russianCallValue : 0)"
                      :unit="form.limitPriceText"
                      :initial-step="round(form.priceBasicInfo?.currentPrice ? form.priceBasicInfo?.currentPrice : (form.priceBasicInfo?.last ? form.priceBasicInfo?.last : 0), 2)"
                      :digit="form.countryCode === 'US' ? 2 : (form.countryCode === 'RU' ? getDigit(form.russianCallValue) : null)"
                      :placeholder="' '"
                      :support="true"
                      :domain="IfaUnitPriceForeign12DigitsDDomainModel"
                    >
                    </ifa-input-price>
                    <span style="width: 200px; padding: 0.6rem 0 0 0"> {{ form.buySellTypeName === '0' ? '以上' : '以下' }}になった時点で</span>
                  </div>

                  <!-- フォーム: /価格/執行方法/逆指値の執行方法  -->
                  <div>
                    <el-row
                      style="margin-top: 0.5rem"
                    >
                      <el-form-item
                        prop="orderPriceKindListReversePriceLimit"
                        class="no-label"
                      >
                        <el-select
                          v-model="form.orderPriceKindListReversePriceLimit"
                          class="form-area__select"
                          @change="changeStopOrderPrice"
                        >
                          <el-option
                            v-for="item in stopOrderPriceKindList"
                            :key="item.value"
                            :value="item.value"
                            :label="item.label"
                          ></el-option>
                        </el-select>
                      </el-form-item>

                      <!-- フォーム: /価格/執行方法/逆指値の執行方法/指値  -->
                      <!-- No.78 -->
                      <div
                        v-show="form.orderPriceKindListReversePriceLimit === '3'"
                        style="margin-left: 20px; width: 350px;"
                      >
                        <ifa-input-price
                          id="stopOrderExecutePrice"
                          v-model="form.stopOrderExecutePrice"
                          prop="stopOrderExecutePrice"
                          :disabled="editDisable"
                          :min="getMinValue()"
                          :max="getMaxValue()"
                          :step-table="form.countryCode === 'US' || form.countryCode === 'RU' ? null : form.callValue"
                          :step="form.countryCode === 'US' ? 0.01 : (form.countryCode === 'RU' ? form.russianCallValue : 0)"
                          :unit="form.limitPriceText"
                          :initial-step="round(form.priceBasicInfo?.currentPrice ? form.priceBasicInfo?.currentPrice : (form.priceBasicInfo?.last ? form.priceBasicInfo?.last : 0), 2)"
                          :digit="form.countryCode === 'US' ? 2 : (form.countryCode === 'RU' ? getDigit(form.russianCallValue) : null)"
                          :placeholder="' '"
                          :support="true"
                          :domain="IfaUnitPriceForeign12DigitsDDomainModel"
                        >
                        </ifa-input-price>
                      </div>
                      <el-col
                        v-if="form.orderPriceKindListReversePriceLimit === '3'"
                        :span="2"
                        style="margin: 0.6rem 0.5rem 0 0;"
                      >
                        <span> で執行</span>
                      </el-col>
                      <el-col
                        v-else
                        :span="2"
                        style="margin: 0.6rem 0.5rem 0 30px;"
                      >
                        <span> で執行</span>
                      </el-col>
                      <!-- A003実行時に描画されるようv-ifで制御 -->
                      <el-col
                        v-if="!editDisable && form.countryCode !== 'US'"
                        :span="4"
                        style="margin-top: 0.45rem;"
                      >
                        <ifa-link
                          disp-name="呼値・制限値幅"
                          :url-id="36"
                          :url-object="{ OrderRulePriceCountryCode: form.countryCode === 'RU' ? 'rs' : form.countryCode.toLowerCase() }"
                        ></ifa-link>
                      </el-col>
                    </el-row>

                  </div>
                  <!-- /フォーム: /価格/執行方法/逆指値の執行方法/指値  -->
                </div>
              </el-col>
            </el-row>
          </div>
          <!-- /フォーム: 価格 -->

          <el-row class="form-area__section">
            <div style="width: 680px;">
              <ifa-period-selector
                ref="periodSelector"
                v-model:period="form.period"
                v-model:period-type="periodType"
                :button-options="periodButtonOption"
                class="date-picker"
                label="期間"
                required
                prop="periodRadio"
                :picker-options="dateOptions"
                :disabled="editDisable"
                @update:period-type="setPeriodRadio($event)"
              >
              </ifa-period-selector>
            </div>
          </el-row>

          <el-row class="form-area__section">
            <el-col v-if="form.buySellTypeName === '0'">
              <ifa-input-radio
                v-if="customerInfo.jrIsaContractType === '1'"
                ref="depositTypeParent"
                v-model="form.depositTypeRadio"
                label="預り区分"
                prop="depositTypeRadio"
                :disabled="editDisable"
                :code-list-id="'multi'"
                :original-list="depositTypeOptions"
                @update:model-value="setDepositType"
              ></ifa-input-radio>
              <ifa-input-radio
                v-else-if="customerInfo.jrIsaContractType === ' '"
                ref="depositTypeParent"
                v-model="form.depositTypeRadio"
                label="預り区分"
                prop="depositTypeRadio"
                :disabled="editDisable"
                :code-list-id="'original'"
                :original-list="depositTypeOptions"
                @update:model-value="setDepositType"
              ></ifa-input-radio>
            </el-col>
            <el-col v-else>
              <el-form-item
                label="預り区分"
              >
                <ifa-text
                  code-list-id="FOREIGN_DEPOSIT_TYPE"
                  :disp-pattern="'3'"
                  :code-key="form.depositType"
                ></ifa-text>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="form-area__section">
            <el-col>
              <!-- 選択可能決済方法リスト取得後に画面レンダリングされるようv-ifで制御 -->
              <ifa-input-radio
                ref="currencyTypeNameParent"
                v-model="form.currencyTypeName"
                label="決済方法"
                prop="currencyTypeName"
                :disabled="editDisable || currencyTypeDisabled"
                :code-list-id="'original'"
                :original-list="currencyTypeOptions"
                @update:model-value="setCurrencyTypeName"
              ></ifa-input-radio>
            </el-col>
          </el-row>
          <el-row class="form-area__section">
            <el-col>
              <ifa-input-select
                v-model="form.solicitTypeList"
                label="勧誘区分"
                prop="solicitTypeList"
                :disabled="editDisable"
                code-list-id="FOREIGN_STOCK_INVITATION_TYPE"
                :disp-pattern="1"
                :select-pattern="1"
                size="small"
                placeholder=""
              >
              </ifa-input-select>
            </el-col>
          </el-row>
          <el-row class="form-area__section">
            <el-col>
              <ifa-input-select
                v-model="form.receiveOrderType"
                label="受注方法"
                prop="receiveOrderType"
                :disabled="editDisable"
                code-list-id="FOREIGN_STOCK_ORDER_METHOD_TYPE"
                :disp-pattern="1"
                :select-pattern="1"
                size="small"
                placeholder=""
              >
              </ifa-input-select>
            </el-col>
          </el-row>
          <el-row
            v-if="form.buySellTypeName == '0'"
            class="form-area__section custom_radio_margin"
          >
            <el-col>
              <ifa-input-radio
                v-model="form.importantMatterType"
                label="重要事項の説明"
                prop="importantMatterType"
                :disabled="editDisable"
                :code-list-id="'IMPORTANT_MATTERS_EXPLAIN'"
                :disp-pattern="1"
                :select-pattern="1"
              ></ifa-input-radio>
            </el-col>
          </el-row>
          <el-row
            v-if="form.buySellTypeName == '0' && form.stockType == 'ETF'"
            class="form-area__section"
          >
            <el-col>
              <ifa-input-radio
                v-model="form.solicitationEtf"
                label="乗換え勧誘(ETF)"
                prop="solicitationEtf"
                :disabled="editDisable"
                :code-list-id="'ETF_SOLICITING_TRANSFERS'"
                :disp-pattern="1"
                :select-pattern="1"
              ></ifa-input-radio>
            </el-col>
          </el-row>

          <el-row
            v-if="form.buySellTypeName == '0' && form.engPubCheck == '1'"
            class="form-area__section"
          >
            <div>
              <ifa-input-check
                ref="engPubCheckboxParent"
                v-model="form.engPubCheckbox"
                label="英文開示銘柄"
                prop="engPubCheckbox"
                :disabled="editDisable"
                :code-list-id="'ISSUES_DISCLOSED_IN_ENGLISH_BRAND'"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </div>
            <el-col
              :span="8"
              style="margin-left: 30px; margin-top: 0.4rem;"
            >
              <span
                class="inner-link"
                @click="englishDisclosureBrandAboutLinkA019"
              >英文開示銘柄について
              </span>
            </el-col>
          </el-row>

          <el-row class="form-area__section">
            <el-col>
              <ifa-input-check
                ref="checkInsiderParent"
                v-model="form.checkInsider"
                label="確認項目"
                prop="checkInsider"
                :disabled="editDisable"
                :code-list-id="'INSIDER_CONFIRM'"
                :disp-pattern="2"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <!-- フォーム: 注文確認 -->
          <div class="form-area__section">
            <ifa-button
              id="btnConfirm"
              name="btnConfirm"
              text="注文確認"
              :disabled="editDisable || (form.buySellTypeName === '1' && form.acPosition === '0')"
              action-type="requestAction"
              action-id="SUB0202_0301-01_1#A013"
              :form="formRef"
              :request-model="a013RequestModel"
              @response-handler="orderConfirmA013($event)"
            ></ifa-button>
          </div>
        </el-card>
      </el-form>
    </div>

    <!-- ダイアログ：外国現物取引注文確認 -->
    <ifa-foreign-spot-trade-order-confirm
      ref="IfaForeignSpotTradeOrderConfirm"
      :is-visible="dialogConfirmVisible"
      :form-data="orderInfo"
      :customer-info="customerInfo"
      @close-modal="handleCloseModal(false)"
      @order-finish="handleOrderFinish"
    ></ifa-foreign-spot-trade-order-confirm>

    <!-- ダイアログ：外国現物取引注文完了 -->
    <ifa-foreign-spot-trade-order-complete
      :is-visible="dialogFinish"
      :form-data="IfaForeignSpotTradeOrderConfirmResponseData"
      :customer-info="customerInfo"
      @close-modal="
        handleCloseModal(true);
        initialize();
      "
      @move-to-order-list="handleMoveToOrderList"
    ></ifa-foreign-spot-trade-order-complete>
    <!-- ダイアログ：外国現物取引注文完了 -->

    <ifa-requester
      id="ifaForeignSpotTradeOrderInputInitializeA001"
      action-id="SUB0202_0301-01_1#A001"
      action-type="requestAction"
      :request-model="a001RequestModel"
      @response-handler="responseHandlerInitializeA001($event)"
      @response-error-handler="responseErrorHandlerInitializeA001($event)"
    ></ifa-requester>

  </div>
</template>
<script>
import IfaForeignBrandSearch from './components/IfaForeignBrandSearch'
import IfaForeignSpotTradeOrderConfirm from './IfaForeignSpotTradeOrderConfirm'
import IfaForeignSpotTradeOrderComplete from './IfaForeignSpotTradeOrderComplete'
import IfaPeriodSelector from '@/components/Date/IfaPeriodSelector'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { getMessage } from '@/utils/errorHandler'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import { IfaForeignSpotTradeOrderInputFormModel } from './js/IfaForeignSpotTradeOrderInputFormModel'
import { IfaForeignSpotTradeOrderInputA001RequestModel } from './js/IfaForeignSpotTradeOrderInputA001RequestModel'
import { IfaForeignSpotTradeOrderInputA003RequestModel } from './js/IfaForeignSpotTradeOrderInputA003RequestModel'
import { IfaForeignSpotTradeOrderInputA005RequestModel } from './js/IfaForeignSpotTradeOrderInputA005RequestModel'
import { IfaForeignSpotTradeOrderInputA011RequestModel } from './js/IfaForeignSpotTradeOrderInputA011RequestModel'
import { IfaForeignSpotTradeOrderInputA013RequestModel } from './js/IfaForeignSpotTradeOrderInputA013RequestModel'

import IfaUnitPriceForeign12DigitsDDomainModel from '@/resource/domain/IfaUnitPriceForeign12DigitsDDomainModel.json'
import IfaVolume17DomainModel from '@/resource/domain/IfaVolume17DomainModel.json'
import IfaText12DomainModel from '@/resource/domain/IfaText12DomainModel.json'
import IfaText6DomainModel from '@/resource/domain/IfaText6DomainModel.json'
import IfaInputPrice from '@/components/Input/IfaInputPrice.vue'

export default {
  components: {
    IfaForeignBrandSearch,
    IfaForeignSpotTradeOrderConfirm,
    IfaForeignSpotTradeOrderComplete,
    IfaPeriodSelector,
    screenTitle,
    IfaInputPrice
  },
  emits: ['show-tab-by-name', 'initializeError'],

  data() {
    return {
      IfaForeignSpotTradeOrderConfirmResponseData: {},
      isIfaButton: false,
      NisaInvestableQuoteLabelThisYear: this.getNisaInvestableQuoteLabelThisYear,
      NisaInvestableQuoteThisYear: this.getNisaInvestableQuoteThisYear,
      NisaInvestableQuoteLabelNextYear: this.getNisaInvestableQuoteLabelNextYear,
      NisaInvestableQuoteNextYear: this.getNisaInvestableQuoteNextYear,
      NisaInvestableQuoteUnitNextYear: this.getNisaInvestableQuoteUnitNextYear,
      periodType: null, // ※空文字だとpropsの仕様でtrueが渡され初期表示が未選択にならない
      currencyType: '',
      stopOrderPriceKindList: [],
      orderPriceKindList: [],
      baseDepositTypeListMulti: [{}],
      params: {},
      dialogConfirmVisible: false,
      dialogFinish: false,
      IfaUnitPriceForeign12DigitsDDomainModel: IfaUnitPriceForeign12DigitsDDomainModel,
      IfaVolume17DomainModel: IfaVolume17DomainModel,
      IfaText12DomainModel: IfaText12DomainModel,
      IfaText6DomainModel: IfaText6DomainModel,
      form: new IfaForeignSpotTradeOrderInputFormModel(),
      ifaFormatUtils: ifaFormatUtils,
      formRef: {},
      orderInfo: {},
      bgColor: '#fef0f0',
      // ボタン活性制御フラグ↓↓↓
      disabledBuyType: true,
      disabledOrderBtn: true,
      disabledConfirmBtn: false,
      disabledForm: false,
      currencyDisabled: false,
      // ボタン活性制御フラグ↑↑↑
      rules: {
        // 数量
        foreignQuantity: [
          { required: true, trigger: 'blur', validator: this.foreignQuantityValidator, message: getMessage('errors.required', ['数量']) }
        ],
        // 価格条件
        orderPriceKindList: [
          { required: true, trigger: 'change', message: getMessage('errors.selected', ['価格']) }
        ],
        // 指値執行価格
        limitOrderPrice: [
          { required: true, trigger: 'blur', validator: this.limitOrderPriceValidator, message: getMessage('errors.required', ['注文単価（指値）']) }
        ],
        // 逆指値/指値執行価格
        stopOrderExecutePrice: [
          { required: true, trigger: 'blur', validator: this.stopOrderExecutePriceValidator, message: getMessage('errors.required', ['注文単価（逆指値）']) }
        ],
        // 逆指値/価格条件
        orderPriceKindListReversePriceLimit: [
          { required: true, trigger: 'change', validator: this.orderPriceKindListReversePriceLimitValidator, message: getMessage('errors.selected', ['価格']) }
        ],
        // 逆指値条件価格
        stopOrderPrice: [
          { required: true, trigger: 'blur', validator: this.stopOrderPriceValidator, message: getMessage('errors.required', ['発火条件価格（逆指値）']) }
        ],

        // 期間
        periodRadio: [
          { required: true, trigger: 'change', message: getMessage('errors.selected', ['期間']) }
        ],
        // 預り区分
        depositTypeRadio: [
          { required: true, trigger: 'change', message: getMessage('errors.selected', ['預り区分']) }
        ],
        // 決済方法
        currencyTypeName: [
          { required: true, trigger: 'change', message: getMessage('errors.selected', ['決済区分']) }
        ],
        // 勧誘区分
        solicitTypeList: [
          { required: true, trigger: 'change', validator: this.isCheckValidator, message: getMessage('errors.selected', ['勧誘区分']) }
        ],
        // 受注方法
        receiveOrderType: [
          { required: true, trigger: 'change', validator: this.isCheckValidator, message: getMessage('errors.selected', ['受注方法']) }
        ],
        // 重要事項の説明
        importantMatterType: [
          { required: true, trigger: 'change', message: getMessage('errors.selected', ['重要事項の説明']) }
        ],
        // 乗換え勧誘（ETF）
        solicitationEtf: [
          { required: true, trigger: 'change', message: getMessage('errors.selected', ['乗換え勧誘（ETF）']) }
        ],
        // 英文開示銘柄
        engPubCheckbox: [
          { required: true, trigger: 'change', validator: this.engPubTextValidator, message: getMessage('errors.selected', ['英文開示銘柄']) }
        ],
        // 確認項目
        checkInsider: [
          { required: true, trigger: 'change', validator: this.checkInsiderValidator, message: getMessage('errors.selected', ['確認項目']) }
        ]
      }

    }
  },
  computed: {
    maxQuantity() {
      if (this.form.buySellTypeName === '1') { // 現物売却
        return Math.min(
          this.form.maximumTradingQuantity === '0' ? '9999999999' : this.form.maximumTradingQuantity,
          this.form.acPosition
        )
      } else {
        return this.form.maximumTradingQuantity === '0' ? '9999999999' : this.form.maximumTradingQuantity
      }
    },
    periodButtonOption() {
      const buttonOptions = {
        today: '',
        period: ''
      }
      if (!this.form.selectAblePeriodTermsList.includes('TODAY_ORDER')) {
        buttonOptions.today = 'hide'
      } else {
        buttonOptions.today = ''
      }
      if (this.form.orderPriceKind === '2' && this.form.selectAblePeriodTermsList.includes('CARRY_OVER_ORDER')) {
        buttonOptions.period = 'disabled'
      } else if (!this.form.selectAblePeriodTermsList.includes('CARRY_OVER_ORDER')) {
        buttonOptions.period = 'hide'
      } else {
        buttonOptions.period = ''
      }
      return buttonOptions
    },
    depositTypeOptions() {
      let codeList = this.$_getCodeList('FOREIGN_DEPOSIT_TYPE', 3, 1)
      // 上記tのうち、選択可能預り区分リストに存在するもののみ表示
      // 特定管理預り,旧NISA預り,JrNISA－NISA預り,Jr継続NISA預りは選択可能預り区分リストに必ず含まれないため表示しない
      codeList = codeList.filter(cl => cl.key !== '3' && cl.key !== '4' && cl.key !== '7' && cl.key !== 'J')

      if (this.form.depositTypeList.filter(e => e === '1').length === 0) {
        // 一般預りを表示しない
        codeList = codeList.filter(cl => cl.key !== '1')
      }
      if (this.form.depositTypeList.filter(e => e === '2').length === 0) {
        // 特定預りを表示しない
        codeList = codeList.filter(cl => cl.key !== '2')
      }
      if (this.form.depositTypeList.filter(e => e === '5').length === 0) {
        // JrNISA－一般預りを表示しない
        codeList = codeList.filter(cl => cl.key !== '5')
      }
      if (this.form.depositTypeList.filter(e => e === '6').length === 0) {
        // JrNISA－特定預りを表示しない
        codeList = codeList.filter(cl => cl.key !== '6')
      }
      if (this.form.depositTypeList.filter(e => e === 'H').length === 0) {
        //  NISA預りを表示しない
        codeList = codeList.filter(cl => cl.key !== 'H')
      }

      if (this.customerInfo.jrIsaContractType === '1') {
        // 表示行数を設定
        const line = 2
        // 行数分depositTypeListMulti配列を作成
        const depositTypeListMulti = [{}]
        for (let i = 1; i <= line; i++) {
          if (i !== 1) depositTypeListMulti.push(this.baseDepositTypeListMulti)
          this.editDepositTypeListMulti(i, codeList, depositTypeListMulti)
        }
        return depositTypeListMulti || []
      } else {
      // 上記のうち、選択可能預り区分リストに存在するもののみ表示
        // 一般預り・特定預り・旧NISA預り・NISA預りを表示しない
        codeList = codeList.filter(cl => cl.key !== '4')
        codeList = codeList.filter(cl => cl.key !== '5')
        codeList = codeList.filter(cl => cl.key !== '6')
        return codeList || []
      }
    },
    currencyTypeOptions() {
      let codeList = this.$_getCodeList('SETTLEMENT_TYPE', 1, 1)
      // 上記のうち、選択可能決済方法リストに存在するもののみ表示

      if (this.form.stockPriceKindList.filter(e => e === 'YEN_SETTLEMENT').length === 0) {
        // 円貨決済を表示しない
        codeList = codeList.filter(cl => cl.key !== '1')
      }
      if (this.form.stockPriceKindList.filter(e => e === 'FOREIGN_SETTLEMENT').length === 0) {
        // 外貨決済を表示しない
        codeList = codeList.filter(cl => cl.key !== '2')
      }
      return codeList || []
    },
    currencyTypeDisabled() {
      let isDisabled = false
      // 取引種別=現物買付 または 取引種別=現物売却 かつ A003.売却可能保護区分＝保護預り
      if (this.form.buySellTypeName === '1' || (this.form.buySellTypeName === 3 && this.form.sellableProtectionCategory === 'PROTECTION_KEEPING')) {
        // 活性表示
        isDisabled = false
      }
      // 取引種別=現物売却 かつ A003.売却可能保護区分＝代用預り
      if (this.form.buySellTypeName === '1' && this.form.sellableProtectionCategory === 'COLLATERAL_SECURITIES') {
        //  "外貨決済"を選択
        const codeList = this.$_getCodeList('SETTLEMENT_TYPE', 1, 1)
        this.setPredefinedCurrencyTypeName(codeList.find(cl => cl.key === '2').key)
        // 非活性表示
        isDisabled = true
      }
      return isDisabled
    },
    a001RequestModel() {
      return new IfaForeignSpotTradeOrderInputA001RequestModel(this.form)
    },
    a003RequestModel() {
      return new IfaForeignSpotTradeOrderInputA003RequestModel(this.form)
    },
    a005RequestModel() {
      return new IfaForeignSpotTradeOrderInputA005RequestModel(this.form)
    },
    a011RequestModel() {
      return new IfaForeignSpotTradeOrderInputA011RequestModel(this.form)
    },
    a013RequestModel() {
      return new IfaForeignSpotTradeOrderInputA013RequestModel(this.form)
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    editDisable() {
      return this.form.brandCode === ''
    },
    dateOptions() {
      const dateList = this.form.validPeriodList
      return {
        disabledDate(date) {
          const targetDate = formatDate(date)
          // 形式をレスポンスデータの日付と合わせる
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
    }
  },
  watch: {
    // 期間の初期設定値を判定して反映
    // 執行方法・条件の変更による期間の変更を監視
    periodButtonOption: {
      deep: true,
      handler() {
        this.setPeriodTypeInput(this.selectPeriodRadio())
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

    selectPeriodRadio() {
      // periodTypeInput がnullのとき初期選択なし、falseのとき"当日中"
      let periodTypeInput = null
      // 価格条件=成行の場合
      if (this.form.orderPriceKind === '2') {
        periodTypeInput = false
      }
      return periodTypeInput
    },
    changeOrderPrice(value) {
      if (value !== '3') {
        this.form.stopOrderPrice = ''
        this.form.stopOrderExecutePrice = ''
      }
      if (value !== '1') this.form.limitOrderPrice = ''
    },
    changeStopOrderPrice(value) {
      if (value !== '3') {
        this.form.stopOrderExecutePrice = ''
      }
    },
    getMinValue() {
      // 米株の場合0.01
      if (this.form.countryCode === 'US') {
        return 0.01
      }

      // ロシア株の場合、A003.ロシア呼び値
      if (this.form.countryCode === 'RU') {
        return this.form.russianCallValue
      }

      // 最小の値を返す比較関数を定義
      const araryMin = function(a, b) { return Math.min(a, b) }
      // それぞれの呼値を取得し、比較の上、最小の呼び値を取得する
      const minValue = this.form.callValue.map(function(obj) { return obj.tickSize }).reduce(araryMin)
      // 呼値リストの中で最小の呼値を返す
      return minValue
    },
    getMaxValue() {
      // 整数部の文字列を作成
      const int = '999999999'
      // 小数桁数分(=プロパティdigit)をもとに小数部の文字列を作成
      let digit = 0
      if (this.form.countryCode === 'US') {
        digit = 2
      } else if (this.form.countryCode === 'RU') {
        digit = this.getDigit(this.form.russianCallValue)
      } else {
        const araryMax = function(a, b) { return Math.max(a, b) }
        const maxCallValue = this.form.callValue.map(function(obj) { return obj.tickSize }).reduce(araryMax)
        digit = this.getDigit(maxCallValue)
      }
      // 小数部の文字列を小数桁数分作成する
      const decimal = '9'
      const maxValue = Number(int.concat('.', (decimal.repeat(digit))))
      // 最大値を返す
      return maxValue
    },
    // No.49 NISA投資可能枠（円貨）_今年［ラベル］を表示
    getNisaInvestableQuoteLabelThisYear() {
      // NISA投資可能枠リストが、データありの場合
      if ('nisaBuyLimitList' in this.form.buyingPowerDomesticList[0] && Array.isArray(this.form.buyingPowerDomesticList[0].nisaBuyLimitList) && Boolean(this.form.buyingPowerDomesticList[0].nisaBuyLimitList.length)) {
        // 買付可能枠年（総合）が、今年と来年が存在する場合は"NISA投資可能枠（"+今年の買付可能枠年+"年）"を返す
        if ('annualAvailableBuyingLimit' in this.form.buyingPowerDomesticList[0].nisaBuyLimitList[0] && this.form.buyingPowerDomesticList[0].nisaBuyLimitList.some((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear())) && this.form.buyingPowerDomesticList[0].nisaBuyLimitList.some((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear() + 1))) {
          //  "NISA投資可能枠（"+今年の買付可能枠年+"年）"を返す
          return 'NISA投資可能枠（' + this.form.buyingPowerDomesticList[0].nisaBuyLimitList.filter((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear()))[0].annualAvailableBuyingLimit + '年）'
        } else { // 買付可能枠年（総合）のデータが、存在しない場合、または今年のみの場合
          return 'NISA投資可能枠'
        }
      } else { // NISA投資可能枠リストが、データなしの場合
        return 'NISA投資可能枠'
      }
    },
    // No.50 NISA投資可能枠（円貨）_今年を表示
    getNisaInvestableQuoteThisYear() {
      // NISA投資可能枠リストが、データありの場合
      if ('nisaBuyLimitList' in this.form.buyingPowerDomesticList[0] && Array.isArray(this.form.buyingPowerDomesticList[0].nisaBuyLimitList) && Boolean(this.form.buyingPowerDomesticList[0].nisaBuyLimitList.length)) {
        // 買付可能枠年（総合）が、今年のみの場合または今年と来年が存在する場合
        if ('annualAvailableBuyingLimit' in this.form.buyingPowerDomesticList[0].nisaBuyLimitList[0] && this.form.buyingPowerDomesticList[0].nisaBuyLimitList.some((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear()))) {
          // 買付可能枠年（総合）が今年の買付可能枠を返す
          return this.form.buyingPowerDomesticList[0].nisaBuyLimitList.filter((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear()))[0].availableBuyingLimit ? ifaFormatUtils.withCommaInteger(this.form.buyingPowerDomesticList[0].nisaBuyLimitList.filter((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear()))[0].availableBuyingLimit) : ''
        } else { // 買付可能枠年（総合）のデータが、存在しない場合
          return '0'
        }
      } else { // NISA投資可能枠リストが、データなしの場合
        return '0'
      }
    },
    // No.51 NISA投資可能枠（円貨）_来年［ラベル］を表示
    getNisaInvestableQuoteLabelNextYear() {
      // NISA投資可能枠リストが、データありの場合
      if ('nisaBuyLimitList' in this.form.buyingPowerDomesticList[0] && Array.isArray(this.form.buyingPowerDomesticList[0].nisaBuyLimitList) && Boolean(this.form.buyingPowerDomesticList[0].nisaBuyLimitList.length)) {
        // 買付可能枠年（総合）が、今年と来年の両方が存在する場合
        if ('annualAvailableBuyingLimit' in this.form.buyingPowerDomesticList[0].nisaBuyLimitList[0] && this.form.buyingPowerDomesticList[0].nisaBuyLimitList.some((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear())) && this.form.buyingPowerDomesticList[0].nisaBuyLimitList.some((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear() + 1))) {
          //  "NISA投資可能枠（"+来年の買付可能枠年+"年）"を返す
          return 'NISA投資可能枠（' + this.form.buyingPowerDomesticList[0].nisaBuyLimitList.filter((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear() + 1))[0].annualAvailableBuyingLimit + '年）'
        } else { // 買付可能枠年（総合）のデータが、存在しない場合、または今年のみの場合
          return ''
        }
      } else { // NISA投資可能枠リストが、データなしの場合
        return ''
      }
    },
    // No.52 NISA投資可能枠（円貨）_来年を表示
    getNisaInvestableQuoteNextYear() {
      // NISA投資可能枠リストが、データありの場合
      if ('nisaBuyLimitList' in this.form.buyingPowerDomesticList[0] && Array.isArray(this.form.buyingPowerDomesticList[0].nisaBuyLimitList) && Boolean(this.form.buyingPowerDomesticList[0].nisaBuyLimitList.length)) {
        // 買付可能枠年（総合）が、今年と来年の両方が存在する場合
        if ('annualAvailableBuyingLimit' in this.form.buyingPowerDomesticList[0].nisaBuyLimitList[0] && this.form.buyingPowerDomesticList[0].nisaBuyLimitList.some((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear())) && this.form.buyingPowerDomesticList[0].nisaBuyLimitList.some((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear() + 1))) {
          // 買付可能枠年（総合）が来年の買付可能枠を返す
          return this.form.buyingPowerDomesticList[0].nisaBuyLimitList.filter((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear() + 1))[0].availableBuyingLimit ? ifaFormatUtils.withCommaInteger(this.form.buyingPowerDomesticList[0].nisaBuyLimitList.filter((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear() + 1))[0].availableBuyingLimit, 2) : '0'
        } else { // 買付可能枠年（総合）のデータが、存在しない場合、または今年のみの場合
          return ''
        }
      } else { // NISA投資可能枠リストが、データなしの場合
        return ''
      }
    },
    // No.52 NISA投資可能枠（円貨）_来年［単位］を表示
    getNisaInvestableQuoteUnitNextYear() {
      // NISA投資可能枠リストが、データありの場合
      if ('nisaBuyLimitList' in this.form.buyingPowerDomesticList[0] && Array.isArray(this.form.buyingPowerDomesticList[0].nisaBuyLimitList) && Boolean(this.form.buyingPowerDomesticList[0].nisaBuyLimitList.length)) {
        // 買付可能枠年（総合）が、今年と来年の両方が存在する場合
        if ('annualAvailableBuyingLimit' in this.form.buyingPowerDomesticList[0].nisaBuyLimitList[0] && this.form.buyingPowerDomesticList[0].nisaBuyLimitList.some((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear())) && this.form.buyingPowerDomesticList[0].nisaBuyLimitList.some((obj) => obj.annualAvailableBuyingLimit === String(this.getThisYear() + 1))) {
          // 買付可能枠年（総合）が来年の買付可能枠を返す
          return '円'
        } else { // 買付可能枠年（総合）のデータが、存在しない場合、または今年のみの場合
          return ''
        }
      } else { // NISA投資可能枠リストが、データなしの場合
        return ''
      }
    },
    getThisYear() {
      //  現在の日付を取得
      const today = new Date()
      // 年を取得
      const year = today.getFullYear()
      return year
    },
    editDepositTypeListMulti(num, codeList, depositTypeListMulti) {
      // 各行に表示する預り区分を編集
      if (num === 1) {
        // 1行目に表示する預り区分
        depositTypeListMulti[num - 1].groupId = num
        depositTypeListMulti[num - 1].options = codeList
        // JrNISA－一般預り・JrNISA－特定預りを表示しない
        depositTypeListMulti[num - 1].options = depositTypeListMulti[num - 1].options.filter(cl => cl.key !== '5')
        depositTypeListMulti[num - 1].options = depositTypeListMulti[num - 1].options.filter(cl => cl.key !== '6')
      } else if (num === 2) {
        // 2行目に表示する預り区分
        depositTypeListMulti[num - 1].groupId = num
        depositTypeListMulti[num - 1].options = codeList
        // JrNISA－一般預り・JrNISA－特定預りを表示しない
        depositTypeListMulti[num - 1].options = depositTypeListMulti[num - 1].options.filter(cl => cl.key !== '1')
        depositTypeListMulti[num - 1].options = depositTypeListMulti[num - 1].options.filter(cl => cl.key !== '2')
        depositTypeListMulti[num - 1].options = depositTypeListMulti[num - 1].options.filter(cl => cl.key !== 'H')
      }
    },
    setPeriodRadio(periodType) {
      if (periodType) {
        this.form.periodRadio = '1'
      } else {
        this.form.periodRadio = '0'
      }
    },
    setDepositType(depositType) {
      if (depositType) {
        this.form.depositType = depositType
      } else {
        this.form.depositType = ''
      }
    },
    setCurrencyTypeName(currencyType) {
      if (currencyType) {
        this.form.currencyTypeName = currencyType
      } else {
        this.form.currencyTypeName = ''
      }
    },
    setPredefinedCurrencyTypeName(value) {
      this.form.currencyTypeName = value
    },
    getDigit(num) {
      const n = String(num).split('.')
      if (n[1]) return n[1].length
      return 0
    },
    round(num, digits) {
      const factor = Math.pow(10, digits)
      const result = Math.round(Number(num) * factor) / factor

      return result
    },
    responseErrorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    responseHandlerInitializeA001(response) {
      Object.assign(this.form, response.dataList[0])

      // 国籍コード・価格条件はレスポンスデータが空の場合初期値をセット
      if (!this.form.countryCode || !this.form.brandCode || !this.form.depositType) this.form.countryCode = 'US'

      this.changeTradeType()
      if (this.form.brandCode && this.form.depositType) this.$refs['ifaForeignSpotTradeOrderInputIfaForeignBrandSearch'].excecuteA003()
    },
    // タブが表示された時にフォーカスを取引種別に合わせる
    onShow(resume = false) {
      this.params = { ...this.$_getMenuParams() }
      this.initialize()
    },
    initialize() {
      this.form = new IfaForeignSpotTradeOrderInputFormModel()
      if (Object.keys(this.params).length !== 0) {
        this.form.brandCode = this.params.brandCode
        this.form.countryCode = this.params.countryCode
        this.form.depositType = this.params.depositType
        this.form.buySellTypeName = this.params.tradeKbn
      }
      this.periodType = null
      this.form.depositTypeRadio = null
      this.currencyType = null
      this.orderPriceKindList = []
      this.stopOrderPriceKindList = []
      this.engPubCheck = null
      this.isIfaButton = false

      setTimeout(() => {
        if (!this.form.brandCode) this.$refs['ifaForeignSpotTradeOrderInputIfaForeignBrandSearch'].clearSearchWord()
        this.$refs['orderForm'].clearValidate()
      }, 500)
      this.$nextTick(() => {
        document.getElementById('ifaForeignSpotTradeOrderInputInitializeA001').click()
        this.formRef = this.$refs.orderForm
      })
    },
    changeTradeType() {
      // 取引種別が 現物買付 の場合
      if (this.form.buySellTypeName === '0') {
        this.bgColor = '#fef0f0'
        // 取引種別が 現物売却 の場合
      } else if (this.form.buySellTypeName === '1') {
        this.bgColor = '#ecf5ff'
      }
    },
    stockPriceDisplayA003Pre(searchWord) {
      this.a003RequestModel.brandCode = searchWord
    },
    stockPriceDisplayA003Res(response) {
      Object.assign(this.form, response.dataList[0])
  
      // 注意銘柄のtrue/falseをString型からBoolean型に変換
      this.form.tradeLimit = (this.form.tradeLimit === 'true')

      // 価格条件をセットする
      this.setOptions(response.dataList[0])
      this.form.validPeriodList = this.form.validPeriodList.splice(0, 9)
      this.form.depositTypeRadio = null
      this.currencyType = null
      this.form.periodRadio = null
      this.form.engPubCheckbox = '1'
      this.isIfaButton = response.isIfaButton
    },
    setOptions(form) {
      this.orderPriceKindList = []
      this.stopOrderPriceKindList = []
      if (!form.orderPriceKindList[0]) form.orderPriceKindList[0] = '1'
      if (form.orderPriceKindList.includes('1')) {
        this.orderPriceKindList.push({ value: '1', label: this.$_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', '2', '1') })
      }
      if (form.orderPriceKindList.includes('2')) {
        this.orderPriceKindList.push({ value: '2', label: this.$_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', '2', '2') })
      }
      if (form.orderPriceKindList.includes('3') || form.orderPriceKindList.includes('4')) {
        this.orderPriceKindList.push({ value: '3', label: this.$_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', '2', '3') })
        if (form.orderPriceKindList.includes('3')) {
          this.stopOrderPriceKindList.push({ value: '3', label: this.$_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', '4', '3') })
        }
        if (form.orderPriceKindList.includes('4')) {
          this.stopOrderPriceKindList.push({ value: '4', label: this.$_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', '4', '4') })
        }
      }
      this.form.orderPriceKind = this.orderPriceKindList[0].value
      if (this.stopOrderPriceKindList[0]) this.form.orderPriceKindListReversePriceLimit = this.stopOrderPriceKindList[0].value
    },
    updateA005(response) {
      Object.assign(this.form, response.dataList[0])
    },
    englishDisclosureBrandAboutLinkA019() {
      const resolvedRoute = this.$router.resolve({ name: 'Ifa-Faq' })
      this.$store.commit('page/setFaqParam', '10004')
      window.open(resolvedRoute.href, '_blank')
      this.$store.commit('page/setFaqParam', '')
    },
    async orderConfirmA013(response) {
      this.orderInfo = response.dataList[0]
      await this.$nextTick() // 現在の更新を待つためのVueのメソッド
      await this.$refs.IfaForeignSpotTradeOrderConfirm.onShow()
      this.dialogConfirmVisible = true
    },
    resetForm() {
      this.$refs['orderForm'].clearValidate()
      this.initialize()
    },
    // バリデーション
    foreignQuantityValidator(rule, value, callback) {
      if (value === '' || parseInt(value) === 0) {
        callback(new Error())
        return
      }
      callback()
    },
    isCheckValidator(rule, value, callback) {
      // 選択されていない場合はエラー
      if (!this.disabledConfirmBtn) {
        if (!value) {
          callback(new Error())
          return
        }
      }
      callback()
    },
    engPubTextValidator(rule, value, callback) {
      if (!this.disabledConfirmBtn) {
        if (this.form.engPubCheckbox === '1') {
          callback(new Error())
          return
        }
      }
      callback()
    },
    checkInsiderValidator(rule, value, callback) {
      if (!this.isRecalculateBtn) {
        if (this.form.checkInsider === '0') {
          callback(new Error())
          return
        }
      }
      callback()
    },
    limitOrderPriceValidator(rule, value, callback) {
      if (this.form.orderPriceKind === '1' && (value === '' || !+value)) {
        callback(new Error())
        return
      }
      callback()
    },
    stopOrderPriceValidator(rule, value, callback) {
      if (this.form.orderPriceKind === '3' && (value === '' || !+value)) {
        callback(new Error())
        return
      }
      callback()
    },
    orderPriceKindListReversePriceLimitValidator(rule, value, callback) {
      if (this.form.orderPriceKind === '3' && this.form.orderPriceKindListReversePriceLimit === '') {
        callback(new Error())
        return
      }
      callback()
    },
    stopOrderExecutePriceValidator(rule, value, callback) {
      if (this.form.orderPriceKind === '3' &&
          this.form.orderPriceKindListReversePriceLimit === '3' &&
          (value === '' || !+value)) {
        callback(new Error())
        return
      }
      callback()
    },
    resetA016() {
      this.form.foreignQuantity = ''
      this.form.orderPriceKind = this.orderPriceKindList[0].value
      this.form.limitOrderPrice = ''
      this.form.stopOrderExecutePrice = ''
      this.form.orderPriceKindListReversePriceLimit = ''
      this.form.orderPriceKindListReversePriceLimit = this.stopOrderPriceKindList[0].value
      this.form.stopOrderPrice = ''
      this.form.hiddenOrderPriceReversePriceLimit = ''
      this.form.depositType = Object.keys(this.params).length !== 0 ? this.params.depositType : null
      this.form.depositTypeRadio = null
      this.currencyType = null
      this.form.currencyTypeName = null
      this.period = null
      this.form.periodRadio = null
      this.form.period = ''
      this.form.solicitTypeList = ''
      this.form.receiveOrderType = ''
      this.form.importantMatterType = null
      this.form.solicitationEtf = null
      this.form.engPubText = ''
      this.engPubText = ''
      this.engPubCheckbox = ''
      this.form.engPubCheckbox = '1' // 1:未説明, 0:説明済
      this.form.checkInsider = '0' // 0:未確認, 1:確認済み
      this.form.approximatePositionAmount = ''
      this.$nextTick(() => {
        if (this.$refs['engPubCheckboxParent']) this.$refs['engPubCheckboxParent'].$refs.engPubCheckbox.resetField()
        if (this.$refs['depositTypeParent']) this.$refs['depositTypeParent'].$refs.depositTypeRadio.resetField()
        this.$refs['currencyTypeNameParent'].$refs.currencyTypeName.resetField()
        this.$refs['checkInsiderParent'].$refs.checkInsider.resetField()
        this.$refs['orderForm'].clearValidate()
      })
      this.$refs['periodSelector'].resetComponent()
      if (this.form.orderPriceKind === '2') {
        this.setPeriodTypeInput(false)
      }
    },
    // リセットボタン処理
    resetAll() {
      this.form.period = ''
      this.form.stockPriceKindList = []
      this.disabledForm = false
      this.currencyDisabled = false
      this.form.securitiesType = ''
      this.resetForm()
    },
    // 注文完了画面に遷移
    async handleOrderFinish(response) {
      this.IfaForeignSpotTradeOrderConfirmResponseData = response
      this.dialogConfirmVisible = false
      this.dialogFinish = true
    },
    // 注文一覧画面に遷移
    handleMoveToOrderList() {
      this.$refs['ifaForeignSpotTradeOrderInputIfaForeignBrandSearch'].resetAll()
      this.dialogFinish = false
      this.$_startShowMenu('SUB0202_0104')
    },
    // ダイアログ：『戻る』
    handleCloseModal(emitBack) {
      if (emitBack) {
        this.dialogFinish = false
      } else {
        this.disabledForm = false
        this.currencyDisabled = false
        this.disabledBuyType = false
        this.disabledOrderBtn = false
        this.disabledConfirmBtn = false
        this.dialogConfirmVisible = false
        this.dialogFinish = false
      }
    }
  }
}
</script>

<style lang="scss">
@import '@/styles/foreignStockOrder.scss';
@import '@/styles/orderStatusList.scss';
@import '@/styles/ifa-portal-common.scss';
</style>
<style lang="scss" scoped>
.__right {
  text-align: right;
  padding-right: 0.5rem;
}

.form-area__section {
  height: auto;
  margin: 0.2rem 0;
  padding-bottom: 0.2rem;
  border-bottom: 1px solid #eee;
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
:deep(.el-form-item__error) {
  margin-right: -165px
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
:deep(.el-form-item__label) {
  font-weight: bold;
  line-height: 2;
  justify-content: flex-start;
  margin-right: 0.5rem;
  margin-left: 5rem;
}
:deep(.el-form-item__content) {
  line-height: 32px;
}
.no-label :deep(.el-form-item__content) {
  margin-left: 0 !important;
}
:deep(.el-select) {
  width: 200px;
}
.inner-link {
  vertical-align: middle;
  display: inline-block;
  padding: 3px 7px;
  cursor: pointer;
  color: #092987;
  text-decoration: underline;
  &:hover {
    opacity: 0.7;
      }
}
.custom_radio_margin :deep(.el-radio) {
  margin-right: 42px;
}
:deep(.el-radio-group) {
  margin-left: 0;
}
.links > div {
  margin-left: 30px;
  &:first-child {
    margin-left: 50px;
  }
}
:deep(.date-picker) .el-form-item__content {
  display: flex;
}
</style>
