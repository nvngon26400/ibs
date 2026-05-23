<template>
  <!-- 国内投信注文入力 -->
  <div v-if="isVisible">
    <el-card
      class="box-card"
      style="background-color: #eee; margin-bottom: 0.5rem;"
    >
      <!-- 銘柄情報 -->
      <div
        class="__border"
        style="margin: 0 1rem;"
      >
        <el-row
          type="flex"
          align="middle"
        >
          <el-col
            :span="1"
            class="info-item__header __right"
          >
            <span style="font-size: 15px;">銘柄:</span>
          </el-col>
          <el-col
            :span="3"
            class="info-item__value __left"
            style="font-size: 15px; padding-left: 1rem;"
          >
            <span>［{{ form.brandCode }}］</span>
          </el-col>
          <el-col
            :span="20"
            class="info-item__value __left"
            style="font-size: 15px; height: auto;"
          >
            <span>{{ form.brand.brandName }}</span>
          </el-col>
        </el-row>
      </div>

      <div
        class="__border"
        style="margin: 0 1rem;"
      >
        <el-row>
          <el-col
            :span="2"
            :offset="2"
          >
            <el-row>
              <span class="info-item__header __left">基準価額：</span>
            </el-row>
            <el-row>
              <span class="info-item__header __left">締切時刻：</span>
            </el-row>
            <el-row>
              <span class="info-item__header __left">償還優遇率：</span>
            </el-row>
          </el-col>
          <el-col :span="6">
            <el-row>
              <el-col
                :span="6"
                class="info-item__value __right"
              >
                <span>{{ $_out($_withCommaZeroPadding(form.brand.basePrice)) }}</span>
              </el-col>
              <el-col
                :span="18"
                class="info-item__value __left"
              >
                <span
                  v-if="form.brand.basePrice !== ''"
                  style="padding-left: 0.5rem;"
                >円</span>
                <span
                  v-if="form.brand.priceDate"
                  style="padding-left: 0.5rem;"
                >（{{ $_getFormattedDateValue(form.brand.priceDate) }}現在）</span>
              </el-col>
            </el-row>
            <el-row>
              <el-col
                :span="6"
                class="info-item__value __right"
              >
                <span
                  v-if="form.brand.deadlines !== ''"
                >{{ $_getFormattedTimeValue(form.brand.deadlines, 'time4') }}</span>
                <span v-else>--:--</span>
              </el-col>
            </el-row>
            <el-row>
              <el-col
                :span="6"
                class="info-item__value __right"
              >
                <span>{{ $_withCommaZeroPadding(form.switchingFavorableTreatmentRate, 2) }}</span>
              </el-col>
              <el-col
                :span="18"
                class="info-item__value __left"
              >
                <span style="padding-left: 0.5rem;">%</span>
              </el-col>
            </el-row>
          </el-col>
          <el-col
            :span="5"
            class=" __right"
          >
            <span class="info-item__header __right">{{ commRateWord }}</span>
          </el-col>
          <el-col
            v-if="form.commRateList.length === 1 && (form.tradeCd === '7' || form.tradeCd === '8')"
            :span="3"
          >
            <el-row class="info-item__value __right">
              <span>一律</span>
            </el-row>
          </el-col>
          <el-col
            v-if="form.commRateList.length > 1"
            :span="4"
          >
            <el-row class="info-item__value __right">
              <span>{{ commRateConditionsLess }}</span>
            </el-row>
            <el-row
              v-for="item in commRateConditionsLessOrMore"
              :key="item"
              class="info-item__value __right"
            >
              <span>{{ item }}</span>
            </el-row>
            <el-row class="info-item__value __right">
              <span>{{ commRateConditionsMore }}</span>
            </el-row>
          </el-col>
          <el-col :span="2">
            <el-row
              v-for="item in commRateDisplay"
              :key="item"
              class="info-item__value __right"
            >
              <span>{{ item }}</span>
            </el-row>
          </el-col>

          <!-- 詳細表示ボタン -->
          <el-col
            :span="(form.commRateList.length === 1 && (form.tradeCd === '7' || form.tradeCd === '8')) ? 3 : (form.commRateList.length > 1 ? 2 : 6)"
            :offset="1"
            style="text-align: right;"
          >
            <ifa-button
              id="btnDetailDisplay"
              text="詳細"
              icon="Document"
              small
              class="detail-button__wrapper"
              action-type="requestAction"
              action-id="SUB0202_0401-03#A001"
              :request-model="IfaMutualFundDetailInfoA001RequestModel"
              @app-action-handler="handleInfoDialogOpenA003"
              @response-handler="detailDisplayA003ResponseHandler($event)"
            ></ifa-button>
          </el-col>
        </el-row>
      </div>

      <!-- 銘柄情報: 購入注文の場合のみ表示する項目 -->
      <div
        v-if="form.tradeCd === '0' || form.tradeCd === '1'"
        style="margin: 0 1rem;"
      >
        <!-- 買付余力(総合口座) -->
        <el-row>
          <el-col
            :span="3"
            :offset="2"
            class="info-item__header __left buying_power_label"
          >
            <span>{{ customerInfo.jrIsaContractType === '1' ? '買付余力（総合口座）：' : '買付余力：' }}</span>
          </el-col>
          <el-col
            :span="4"
            class="info-item__value __right"
          >
            <span>{{ form.buyingPower.wholeAccount <= 0 ? '0' : $_withCommaZeroPadding(form.buyingPower.wholeAccount) }} 円</span>
          </el-col>
        </el-row>

        <!-- 買付余力(ジュニアNISA口座) -->
        <el-row v-if="customerInfo.jrIsaContractType === '1'">
          <el-col
            :span="4"
            :offset="2"
            class="info-item__header __left buying_power_label"
          >
            <span>買付余力（ジュニアNISA口座）：</span>
          </el-col>
          <el-col
            :span="3"
            class="info-item__value __right"
          >
            <span>{{ form.buyingPower.jrNisaAccount <= 0 ? '0' : $_withCommaZeroPadding(form.buyingPower.jrNisaAccount) }} 円</span>
          </el-col>
        </el-row>

        <!-- 償還優遇枠限度額(総合口座) -->
        <el-row>
          <el-col
            :span="5"
            :offset="2"
          >
            <span class="info-item__header __left buying_power_label">{{ customerInfo.jrIsaContractType === '1' ? '償還優遇限度額（総合口座）：' : '償還優遇限度額：' }}</span>
          </el-col>
          <el-col
            :span="5"
            class="info-item__value __left"
            style="display: flex; align-items: flex-start; justify-content: space-between;"
          >
            <div style="display: inline-block; min-width: 70px; text-align: left;">（今月残）</div>
            <div style="display: inline-block; text-align: right;">{{ form.switchingFavorableTreatmentLimit.wholeAccountThisMonth <= 0 ? '0' : $_withCommaZeroPadding(form.switchingFavorableTreatmentLimit.wholeAccountThisMonth) }} 円</div>
          </el-col>
          <el-col
            :span="5"
            class="info-item__value __left"
            style="display: flex; align-items: flex-start; justify-content: space-between;"
          >
            <div style="display: inline-block; min-width: 70px; text-align: left;">（来月残）</div>
            <div style="display: inline-block; text-align: right;">{{ form.switchingFavorableTreatmentLimit.wholeAccountNextMonth <= 0 ? '0' : $_withCommaZeroPadding(form.switchingFavorableTreatmentLimit.wholeAccountNextMonth) }} 円</div>
          </el-col>
        </el-row>

        <!-- 償還優遇枠限度額(ジュニアNISA口座) -->
        <el-row v-if="customerInfo.jrIsaContractType === '1'">
          <el-col
            :span="5"
            :offset="2"
            class="info-item__header __left buying_power_label"
          >
            <span>償還優遇枠限度額（ジュニアNISA口座）:</span>
          </el-col>
          <el-col
            :span="5"
            class="info-item__value __left"
            style="display: flex; align-items: flex-start; justify-content: space-between;"
          >
            <div style="display: inline-block; min-width: 70px; text-align: left;">（今月残）</div>
            <div style="display: inline-block; text-align: right;">{{ form.switchingFavorableTreatmentLimit.jrNisaAccountThisMonth <= 0 ? '0' : $_withCommaZeroPadding(form.switchingFavorableTreatmentLimit.jrNisaAccountThisMonth) }} 円</div>
          </el-col>
          <el-col
            :span="5"
            class="info-item__value __left"
            style="display: flex; align-items: flex-start; justify-content: space-between;"
          >
            <div style="display: inline-block; min-width: 70px; text-align: left;">（来月残）</div>
            <div style="display: inline-block; text-align: right;">{{ form.switchingFavorableTreatmentLimit.jrNisaAccountNextMonth <= 0 ? '0' : $_withCommaZeroPadding(form.switchingFavorableTreatmentLimit.jrNisaAccountNextMonth) }} 円</div>
          </el-col>
        </el-row>

        <el-row
          v-if="(form.tradeCd === '0' || form.tradeCd === '1') && (form.selectAccountType !== '1') && (form.point.pointShowAreaFlag !== '0')"
          class="brand_area_text_color"
        >
          <el-col
            :span="3"
            :offset="2"
            class="info-item__header __left buying_power_label"
          >
            <span v-if="form.point.pointNameDisplayFlag === '1'">
              保有
              <ifa-text
                code-list-id="POINT_TYPE"
                :disp-pattern="1"
                :code-key="form.point.pointClass"
              ></ifa-text>
              ：
            </span>
            <span v-else>保有ポイント：</span>
          </el-col>
          <el-col
            v-if="form.point.pointCountDisplayFlag === '1' || form.point.pointCountDisplayFlag === '2'"
            :span="4"
            class="info-item__value __right"
            style="margin-left: -1rem;"
          >
            <span v-if="form.point.pointCountDisplayFlag === '1'">{{ form.point.pointCount === '0' ? '0' : $_withCommaZeroPadding(form.point.pointCount) }}</span>
            <span v-else>-</span>
          </el-col>
          <el-col
            v-if="form.point.pointCountDisplayFlag === '1' || form.point.pointCountDisplayFlag === '2'"
            :span="2"
            class="info-item__value __left"
          >
            <span style="padding-left: 0.5rem;">
              <ifa-text
                code-list-id="POINT_TYPE"
                :disp-pattern="2"
                :code-key="form.point.pointClass"
              ></ifa-text>
            </span>
          </el-col>
          <el-col
            v-if="form.point.fixedPeriodPointDisplayFlag === '1' || form.point.fixedPeriodPointDisplayFlag === '2'"
            :span="3"
            class="info-item__value __left nowrap"
          >
            <span v-if="form.point.pointNameDisplayFlag === '1'">
              (うち期間固定
              <ifa-text
                code-list-id="POINT_TYPE"
                :disp-pattern="1"
                :code-key="form.point.pointClass"
              ></ifa-text>
            </span>
            <span v-else>（うち期間固定ポイント</span>
          </el-col>
          <el-col
            v-if="form.point.fixedPeriodPointDisplayFlag === '1' || form.point.fixedPeriodPointDisplayFlag === '2'"
            :span="3"
            class="info-item__value __right"
          >
            <span v-if="form.point.fixedPeriodPointDisplayFlag === '1'">{{ form.point.fixedPeriodPoint === '0' ? '0' : $_withCommaZeroPadding(form.point.fixedPeriodPoint) }}</span>
            <span v-else>-</span>
          </el-col>
          <el-col
            v-if="form.point.fixedPeriodPointDisplayFlag === '1' || form.point.fixedPeriodPointDisplayFlag === '2'"
            :span="2"
            class="info-item__value __left"
          >
            <span style="padding-left: 0.5rem;">
              <ifa-text
                code-list-id="POINT_TYPE"
                :disp-pattern="2"
                :code-key="form.point.pointClass"
              ></ifa-text>
              ）
            </span>
          </el-col>
          <el-col
            v-if="form.point.shortestExpirationDateDisplayFlag === '1' || form.point.shortestExpirationDateDisplayFlag === '2'"
            :span="4"
            class="info-item__value __left"
          >
            <span>最短有効期限：</span>
            <span
              v-if="form.point.shortestExpirationDateDisplayFlag === '1'"
              style="padding-left: 1.5rem;"
            >
              {{ form.point.shortestExpirationDate === '' ? '----/--/--' : $_getFormattedDateValue(form.point.shortestExpirationDate) }}
            </span>
            <span v-else>----/--/--</span>
          </el-col>
        </el-row>
      </div>

      <!-- 銘柄情報: 売却注文の場合のみ表示する項目 -->
      <div
        v-if="form.tradeCd === '7' || form.tradeCd === '8'"
        style="margin: 0 1rem;"
      >
        <el-row>
          <el-col
            :span="2"
            :offset="2"
            class="info-item__header __left"
          >
            <span>売却可能：</span>
          </el-col>
          <el-col
            :span="5"
            class="info-item__value __right"
          >
            <span
              v-if="form.tradeCd === '7'"
            >
              {{ $_withCommaZeroPadding(form.depositInfo.sellAbleLot) + '口' }}
            </span>
            <span
              v-if="form.tradeCd === '8'"
            >
              {{ $_withCommaZeroPadding(form.depositInfo.sellAbleAmount) + '円/' + $_withCommaZeroPadding(form.depositInfo.sellAbleLot) + '口' }}
            </span>
          </el-col>
          <el-col
            :span="6"
            class="info-item__header __right"
          >
            <span>概算評価金額：</span>
          </el-col>
          <el-col
            :span="6"
            class="info-item__value __right"
          >
            <span>{{ $_withCommaZeroPadding(form.depositInfo.approximateValuation) + '円' }}</span>
            <span>{{ '（' + $_getFormattedDateValue(form.depositInfo.approximateValuationDate) + '現在）' }}</span>
          </el-col>
        </el-row>
      </div>

      <!-- 年度別NISA保有口数 -->
      <div
        v-if="form.depositType === '4' || form.depositType === '8' || form.depositType === 'H' || form.depositType === 'I' || form.depositType === 'J'
          || form.depositType === '7'"
        style="margin: 0 1rem;"
      >
        <el-row>
          <el-col
            :span="9"
            class="info-item__value __right"
          >
            <ifa-link
              :disp-name="'年度別NISA保有口数'"
              :url-id="44"
            ></ifa-link>
          </el-col>
        </el-row>
      </div>
    </el-card>

    <el-card
      class="box-card"
      :style="{ 'background-color': bgColor }"
    >
      <!-- 入力フォーム -->
      <div class="form-container">
        <el-form
          ref="formModel"
          :model="form"
          :rules="rules"
          label-width="180px"
          label-position="left"
          class="form-main"
          :style="{ 'background-color': bgColor }"
        >

          <!-- フォーム(購入): 口座 -->
          <div class="form-area__section">
            <el-row>
              <el-col
                v-if="customerInfo.jrIsaContractType === '1' && (form.tradeCd === '0' || form.tradeCd === '1')"
                :span="19"
              >
                <ifa-input-radio
                  v-model="form.selectAccountType"
                  label="口座"
                  :required="customerInfo.jrIsaContractType === '1' && (form.tradeCd === '0' || form.tradeCd === '1')"
                  prop="selectAccountType"
                  code-list-id="ACCOUNT_TYPE"
                  :disp-pattern="1"
                  :select-pattern="2"
                  @change="requestTypeA005"
                ></ifa-input-radio>
              </el-col>
              <el-col
                v-else
                :span="19"
              >
                <el-form-item
                  label="口座"
                  style="font-weight: 700;"
                >
                  <span
                    v-if="form.selectAccountType === '1'"
                    style="font-weight: 100;"
                  >
                    ジュニアNISA口座
                  </span>
                  <span
                    v-else
                    style="font-weight: 100;"
                  >
                    総合口座
                  </span>
                </el-form-item>
              </el-col>
              <el-col
                :span="5"
                style="text-align: right"
              >
                <!-- 戻るボタン -->
                <!-- リセットボタン -->
                <ifa-button
                  id="btnReset"
                  text="リセット"
                  color="secondary"
                  action-type="originalAction"
                  @app-action-handler="resetForm('form')"
                ></ifa-button>
                <template v-if="isShowBackButton">
                  <ifa-button
                    id="btnBack"
                    text="戻る"
                    color="secondary"
                    action-type="originalAction"
                    @app-action-handler="handleClose"
                  ></ifa-button>
                </template>
              </el-col>
            </el-row>
          </div>

          <!-- フォーム(購入/売却): 取引 -->
          <div class="form-area__section">
            <el-form-item
              label="取引種別"
              style="font-weight: 700;"
            >
              <el-col :span="12">
                <span :class="form.tradeCd === '1' ? 'font-color__plus bold' : form.tradeCd === '2' ? 'font-color__minus bold' : ''">
                  <ifa-text
                    :style="form.tradeCd === '7' || form.tradeCd === '8' ? 'color: #00847F; font-weight: bold;' : 'color: #E5004C; font-weight: bold;'"
                    code-list-id="DOMESTIC_MUTUAL_FUND_TRADE_CLASS"
                    :disp-pattern="3"
                    :select-pattern="1"
                    :code-key="form.tradeCd"
                  ></ifa-text>
                </span>
              </el-col>
            </el-form-item>
          </div>

          <!-- フォーム(売却): 売却方法 -->
          <div
            v-if="form.tradeCd === '7' || form.tradeCd === '8'"
            class="form-area__section"
          >
            <el-form-item
              label="売却方法"
              :required="customerInfo.corporationType === '1' && form.brand.buyMethodSelect === '2'"
              style="font-weight: 700;"
            >
              <el-row>
                <el-col
                  :span="12"
                  style="font-weight: 100;"
                >
                  <span
                    v-if="customerInfo.corporationType !== '1'"
                  >解約</span>
                  <ifa-input-radio
                    v-else
                    v-model="buyMethodSelectedValue"
                    prop="saleMethod"
                    :disabled="buyMethodSelectDisabled"
                    code-list-id="MUTUAL_FUND_SELL_METHOD"
                    :disp-pattern="1"
                    :select-pattern="1"
                  ></ifa-input-radio>
                </el-col>
              </el-row>
            </el-form-item>
          </div>

          <!-- フォーム(購入/一般): 償還優遇 -->
          <div
            v-if="form.transfersPreferentialQuotaApplication === '項目表示' || form.transfersPreferentialQuotaApplication === '項目非活性'"
            class="form-area__section"
            required
          >
            <ifa-input-radio
              v-model="form.transfersPreferentialQuotaApplicationSelect"
              label="償還優遇"
              prop="transfersPreferentialQuotaApplicationSelect"
              code-list-id="TRANSFERS_PREFERENTIAL_QUOTA_APPLICATION"
              :disp-pattern="1"
              :select-pattern="1"
              :disabled="form.transfersPreferentialQuotaApplication === '項目非活性'"
              @change="requestTypeA006"
            ></ifa-input-radio>
          </div>

          <!-- フォーム(売却): 売却指定 -->
          <div
            v-if="form.tradeCd === '8'"
            class="form-area__section"
          >
            <el-row>
              <el-col :span="7">
                <ifa-input-select
                  v-model="form.sellDesignatedWord"
                  label="売却指定"
                  prop="sellDesignatedWord"
                  required
                  select-class="form-area__input_select"
                  code-list-id="DESIGNATED_FOR_SALE"
                  :disp-pattern="1"
                  :select-pattern="1"
                  style="width: 200px;"
                ></ifa-input-select>
              </el-col>
              <el-col :span="12">
                <ifa-link
                  v-if="form.sellDesignatedWord === '3'"
                  style="position: relative; top: 4px;"
                  :url-id="34"
                  :disp-name="'全額売却時の注意事項'"
                ></ifa-link>
              </el-col>
            </el-row>
          </div>

          <!-- フォーム(購入/累投): 金額 -->
          <div
            v-if="form.tradeCd === '1' || (form.tradeCd === '8' && form.sellDesignatedWord === '1')"
            class="form-area__section"
          >
            <el-row>
              <el-col style="display: flex; align-items: flex-start;">
                <div style="display: inline-block; min-width: 590px;">
                  <ifa-input-amount
                    v-model="form.amount"
                    label="金額"
                    prop="amount"
                    inline-message="true"
                    :min="initialStepAmount > 0 ? initialStepAmount : 1"
                    :max="99999999999"
                    :step="form.tradeCd === '1' ? form.brand.salesTradeUnitAmount : form.brand.cancelTradeUnitAmount"
                    :initial-step="initialStepAmount"
                    support
                    unit="円"
                    :domain="IfaYen110DomainModel"
                    @input="handleChangePrice"
                  ></ifa-input-amount>
                </div>
                <div
                  v-if="form.tradeCd === '1'"
                  class="origin_position"
                >
                  （{{ $_withCommaZeroPadding(form.brand.minSalesUnitAmount) }} 円以上 {{ $_withCommaZeroPadding(form.brand.salesTradeUnitAmount) }} 円単位）
                </div>
                <div
                  v-if="form.tradeCd === '8'"
                  class="origin_position"
                >
                  （{{ $_withCommaZeroPadding(form.brand.cancelTradeUnitAmount) }} 円単位）
                </div>
              </el-col>
            </el-row>
          </div>

          <!-- フォーム(購入/一般): 口数 -->
          <div
            v-if="form.tradeCd === '0' || form.tradeCd === '7' || (form.tradeCd === '8' && form.sellDesignatedWord === '2')"
            class="form-area__section"
          >
            <el-row>
              <el-col style="display: flex; align-items: flex-start;">
                <div style="display: inline-block; min-width: 590px;">
                  <ifa-input-amount
                    v-model="form.unit"
                    label="口数"
                    prop="unit"
                    :min="initialStepUnitLot > 0 ? initialStepUnitLot : 1"
                    :max="99999999999"
                    :step="form.tradeCd === '0' ? form.brand.salesUnitLot : form.brand.cancelTradeUnitLot"
                    :initial-step="initialStepUnitLot"
                    support
                    unit="口"
                    :domain="IfaUnits11DomainModel"
                    @input="handleChangeVolume"
                  ></ifa-input-amount>
                </div>
                <div
                  v-if="form.tradeCd === '0'"
                  class="origin_position"
                >
                  （{{ $_withCommaZeroPadding(form.brand.minSalesLot) }} 口以上 {{ $_withCommaZeroPadding(form.brand.salesUnitLot) }} 口単位）
                </div>
                <div
                  v-if="form.tradeCd === '7' || form.tradeCd === '8'"
                  class="origin_position"
                >
                  （{{ $_withCommaZeroPadding(form.brand.cancelTradeUnitLot) }} 口単位）
                </div>
              </el-col>
            </el-row>
          </div>

          <!-- フォーム(購入/累投): ポイント -->
          <div
            v-if="form.selectAccountType !== '1' && form.point.pointUseAreaFlag === '1'"
            class="form-area__section"
          >
            <el-row>
              <el-col style="display: flex; align-items: flex-start;">
                <!-- フォーム: 執行方法選択 @価格/執行方法 -->
                <div style="display: inline-block;">
                  <ifa-input-select
                    v-model="form.usingType"
                    label="ポイント"
                    :prop="form.tradeCd === '1' ? 'usingType' : ''"
                    inline-message="true"
                    class="form-area__select-auto"
                    :required="form.tradeCd === '1'"
                    :disabled="form.tradeCd !== '1'"
                    code-list-id="USE_OF_POINTS_SELECT"
                    :disp-pattern="1"
                    :select-pattern="form.point.usePointUnit === '1' ? 1 : 2"
                    style="width: 200px;"
                  ></ifa-input-select>
                </div>
                <div style="display: inline-block; min-width: 420px;">
                  <ifa-input-amount
                    v-model="form.pointInput"
                    prop="pointInput"
                    :min="form.point.minUsePointCount"
                    :max="form.point.pointCount"
                    :step="form.point.usePointUnit"
                    :initial-step="form.point.minUsePointCount"
                    support
                    :unit="$_getCodeValue('POINT_TYPE', 2, form.point.pointClass)"
                    :disabled="form.usingType !== '1'"
                    :domain="IfaPoint9DomainModel"
                  ></ifa-input-amount>
                </div>
                <div
                  v-if="form.usingType === '1' && form.point.pointClass === '6'"
                  style="color: red;"
                  class="origin_position"
                >
                  ※1ポイント＝0.5円相当として、2000ポイント単位でご利用ください。
                </div>
              </el-col>
            </el-row>
          </div>

          <!-- フォーム(購入): 預り区分 -->
          <div class="form-area__section">
            <div v-if="form.depositTypeList.length === 0">
              <el-form-item
                label="預り区分"
                style="font-weight: 700;"
                :required="form.depositTypeList.length > 0"
              >
                <el-col
                  :span="22"
                  style="font-weight: 100;"
                >
                  <ifa-text
                    v-model="form.selectDepositType"
                    code-list-id="MUTUAL_FUND_DEPOSIT_TYPE"
                    :disp-pattern="mutualDepositTypeDisplayPattern"
                    :select-pattern="1"
                    :code-key="form.selectDepositType"
                  ></ifa-text>
                </el-col>
              </el-form-item>
            </div>
            <el-col
              v-else
              :span="22"
              style="font-weight: 100;"
            >
              <ifa-input-radio
                v-model="form.selectDepositType"
                label="預り区分"
                prop="form.depositType"
                :required="form.depositTypeList.length > 0"
                :code-list-id="'original'"
                :original-list="depositTypeListDisplay"
                :code-key="form.selectDepositType"
                @change="requestTypeA009"
              ></ifa-input-radio>
            </el-col>
            <el-row>
              <el-col :span="8"></el-col>
              <el-col
                v-if="form.tradeCd === '1'"
                :span="12"
              >
                <div
                  v-if="form.selectDepositType === 'H'"
                  style="color: red;"
                >
                  {{ form.nisaBuy }}
                </div>
              </el-col>
            </el-row>

          </div>

          <!-- フォーム(購入): 勧誘区分 -->
          <div class="form-area__section">
            <ifa-input-select
              v-model="form.solicitType"
              label="勧誘区分"
              prop="solicitType"
              class="form-area__select"
              required
              style="width: 200px;"
              code-list-id="INVITATION_TYPE"
              :disp-pattern="1"
              :select-pattern="1"
            ></ifa-input-select>
          </div>

          <!-- フォーム(購入): 受注方法 -->
          <div class="form-area__section">
            <ifa-input-select
              v-model="form.receiveOrderType"
              label="受注方法"
              prop="receiveOrderType"
              class="form-area__select"
              required
              style="width: 200px;"
              code-list-id="ORDER_METHOD_TYPE"
              :disp-pattern="1"
              :select-pattern="1"
            ></ifa-input-select>
          </div>

          <!-- レバレッジ投資信託 -->
          <div
            v-if="form.tradeCd === '0' || form.tradeCd === '1'"
            class="form-area__section radio_width"
          >
            <ifa-input-radio
              v-model="form.leverageInvestTrust"
              label="レバレッジ投資信託"
              prop="leverageInvestTrust"
              required
              code-list-id="LEVERAGED_INVESTMENT_TRUST"
              :disp-pattern="1"
              :select-pattern="1"
            ></ifa-input-radio>
          </div>

          <!-- 乗換勧誘 -->
          <div
            v-if="form.tradeCd === '0' || form.tradeCd === '1'"
            class="form-area__section radio_width"
          >
            <ifa-input-radio
              v-model="form.solicitingTransfers"
              label="乗換勧誘"
              prop="solicitingTransfers"
              required
              code-list-id="SOLICITING_TRANSFERS"
              :disp-pattern="1"
              :select-pattern="1"
            ></ifa-input-radio>
          </div>

          <!-- 同一通貨/同一国の乗換 -->
          <div class="form-area__section radio_width">
            <ifa-input-radio
              v-model="form.sameCurrencySameCountryTransfers"
              label="同一通貨/同一国の乗換"
              prop="sameCurrencySameCountryTransfers"
              required
              code-list-id="SAME_CURRENCY_SAME_COUNTRY_TRANSFERS"
              :disp-pattern="1"
              :select-pattern="1"
            ></ifa-input-radio>
          </div>

          <!-- 他社間投信乗換勧誘 -->
          <div class="form-area__section radio_width">
            <ifa-input-radio
              v-model="form.intercompanyMutualFundTransferSolicitation"
              label="他社間投信乗換勧誘"
              prop="intercompanyMutualFundTransferSolicitation"
              required
              code-list-id="INTERCOMPANY_MUTUAL_FUND_TRANSFER_SOLICITATION"
              :disp-pattern="1"
              :select-pattern="1"
            ></ifa-input-radio>
          </div>

          <!-- 短期売却確認 -->
          <div
            v-if="form.tradeCd === '7' || form.tradeCd === '8'"
            class="form-area__section radio_width"
          >
            <ifa-input-radio
              v-model="form.shortTermSaleConfirmSelect"
              label="短期売却確認"
              prop="shortTermSaleConfirmSelect"
              required
              :code-list-id="'original'"
              :original-list="shortTermSaleConfirmList"
            ></ifa-input-radio>
          </div>

          <!-- 償還前売却確認 -->
          <div
            v-if="form.tradeCd === '7' || form.tradeCd === '8'"
            class="form-area__section radio_width"
          >
            <ifa-input-radio
              v-model="form.preRedemptionSellConfirm"
              label="償還前売却確認"
              prop="preRedemptionSellConfirm"
              class="el-link"
              required
              :code-list-id="'original'"
              :original-list="preRedemptionSellConfirmSelectList"
              link
              @click-label="onLabelClickA011"
            ></ifa-input-radio>
          </div>

          <!-- 分配金受取方法 -->
          <div
            v-if="form.tradeCd === '0' || form.tradeCd === '1'"
            class="form-area__section"
          >
            <el-row>
              <el-form-item
                label="分配金受取方法"
                style="font-weight: 700;"
              >
                <el-row>
                  <span
                    v-if="form.tradeCd === '1' && form.brand.fundType === '2' && form.brand.reInvestmentClassification === '2'"
                    style="margin-right: 1rem;"
                  >
                    <ifa-input-select
                      v-model="form.distributionReceivingMethod"
                      :required="form.tradeCd === '1'"
                      size="small"
                      prop="distributionReceivingMethod"
                      code-list-id="DISTRIBUTION_RECEIVE_METHOD"
                      :disp-pattern="4"
                      :select-pattern="1"
                      style="width: 200px;"
                    ></ifa-input-select>
                  </span>
                  <span
                    v-if="form.tradeCd === '1'"
                    style="font-weight: 100;"
                  >現在の設定：{{ form.distributionReceiveMethodWord }}</span>
                  <span
                    v-if="form.tradeCd === '0'"
                    style="font-weight: 100;"
                  >現在の設定：受取</span>
                </el-row>
              </el-form-item>
            </el-row>
          </div>

          <!-- フォーム(購入): 目論見書の交付方法 -->
          <div
            v-if="form.tradeCd === '0' || form.tradeCd === '1'"
            class="form-area__section"
          >
            <el-row>
              <el-col :span="12">
                <ifa-input-select
                  v-model="form.prospectusIssueMethod"
                  label="目論見書の交付方法"
                  prop="prospectusIssueMethod"
                  size="small"
                  code-list-id="PROSPECTUS_ISSUE_METHOD"
                  :disp-pattern="1"
                  :select-pattern="1"
                  style="width: 200px;"
                ></ifa-input-select>
              </el-col>
            </el-row>
          </div>

          <!-- 利益相反可能性の説明-->
          <div
            v-if="form.tradeCd === '0' || form.tradeCd === '1'"
            class="form-area__section"
          >
            <el-row>
              <el-col :span="12">
                <ifa-input-check
                  v-model="form.conflictOfInterestExplain"
                  label="利益相反可能性の説明"
                  prop="conflictOfInterestExplain"
                  inline-message="true"
                  class="form-area__checkbox"
                  code-list-id="CONFLICT_OF_INTEREST_EXPLAIN"
                  :disp-pattern="2"
                  :select-pattern="2"
                ></ifa-input-check>
              </el-col>
            </el-row>
          </div>
          <!-- フォーム(購入): 重要事項確認 -->
          <div v-if="form.tradeCd === '0' || form.tradeCd === '1'">
            <el-row>
              <el-col
                :span="12"
              >
                <ifa-input-check
                  v-model="form.prospectusSupplementaryDocConfirm"
                  label="確認項目"
                  prop="agree1"
                  inline-message="true"
                  class="form-area__checkbox"
                  code-list-id="PROSPECTUS_SUPPLEMENTARY_DOC_CONFIRM"
                  :disp-pattern="2"
                  :select-pattern="2"
                ></ifa-input-check>
              </el-col>
            </el-row>

            <el-form-item
              v-if="form.brand.brandSpecialClassification === '1'"
              label=" "
              inline-message="true"
              class="form-area__checkbox"
            >
              <el-row>
                <el-col :span="12">
                  <ifa-input-check
                    v-model="form.windowSpaceFundPrecautionsConsent"
                    code-list-id="WINDOW_SPACE_FUND_PRECAUTIONS_CONSENT"
                    prop="agree2"
                    :disp-pattern="2"
                    :select-pattern="2"
                  ></ifa-input-check>
                </el-col>
              </el-row>
            </el-form-item>

            <!-- 確認事項３ -->
            <el-form-item
              label=" "
              inline-message="true"
              class="form-area__checkbox"
            >
              <el-row>
                <el-col :span="12">
                  <ifa-input-check
                    v-model="form.costExplained"
                    code-list-id="COST_EXPLAINED"
                    prop="agree3"
                    :disp-pattern="2"
                    :select-pattern="2"
                  ></ifa-input-check>
                </el-col>
              </el-row>
            </el-form-item>

            <!-- 確認事項４ -->
            <el-form-item
              label=" "
              inline-message="true"
              class="form-area__checkbox"
            >
              <el-row>
                <el-col :span="12">
                  <ifa-input-check
                    v-model="form.multipleTradeClearlyCommStated"
                    code-list-id="MULTIPLE_TRADE_CLEARLY_COMM_STATED"
                    prop="agree4"
                    :disp-pattern="2"
                    :select-pattern="2"
                  ></ifa-input-check>
                </el-col>
              </el-row>
            </el-form-item>
          </div>

          <!-- 注文確認ボタン -->
          <!-- Note: タブ移動での順番の関係でリセットボタンよりも前に配置する必要あり -->
          <ifa-button
            id="btnDomesticMutualFundOrderConfirm"
            class="form-order-button__wrapper"
            :form="formRef"
            text="注文確認"
            action-id="SUB0202_0401-02_1#A010"
            action-type="requestAction"
            :request-model="A010RequestModel"
            @response-handler="orderConfirmA010($event)"
          ></ifa-button>

        </el-form>

      </div>
    </el-card>
    <!-- 投信詳細情報ダイアログ -->
    <ifa-mutual-fund-detail-info
      :is-visible="infomationDialogVisible"
      :info="dialogInfo"
      @close-modal="handleCloseModal"
    ></ifa-mutual-fund-detail-info>

    <!-- 国内投信注文受付確認ダイアログ -->
    <ifa-domestic-mutual-fund-order-confirm
      ref="IfaDomesticMutualFundOrderConfirm"
      :is-visible="confirmDialogVisible"
      :form="form"
      :form-data="confirmData"
      :customer-info="customerInfo"
      :append-to-body="true"
      @dialog-close="handleConfirmDialogClose"
      @order-finish="handleConfirmDialogFinish"
    ></ifa-domestic-mutual-fund-order-confirm>

    <!-- 国内投信注文受付確認ダイアログ -->
    <ifa-domestic-mutual-fund-order-complete
      :is-visible="finishDialogVisible"
      :form-data="confirmResponseData"
      :customer-info="customerInfo"
      :append-to-body="true"
      @dialog-close="handleDialogClose()"
      @move-to-order-list="handleMoveToOrderList()"
    ></ifa-domestic-mutual-fund-order-complete>

    <!-- 全額売却時の注意事項ダイアログ -->
    <el-dialog
      v-model="noticeDialogVisible"
      width="30%"
      title="投資信託 全額売（解約）注文時のご注意事項"
      :show-close="false"
      :before-close="handleNoticeDialogCloseA002"
      :close-on-click-modal="false"
      append-to-body
    >

      <!-- 戻るボタン -->
      <el-row>
        <ifa-button
          id="btnBack"
          class="dialog-back-button__wrapper"
          text="戻る"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="handleNoticeDialogCloseA002"
        ></ifa-button>
      </el-row>

      <el-card class="box-card">
        <el-row>
          <el-col>
            <span>ここに｢全額売（解約）注文時のご注意事項｣が表示されます｡</span>
          </el-col>
        </el-row>
      </el-card>
    </el-dialog>
    <ifa-requester
      id="ifaDomesticMutualFundOrderInputResetA004"
      action-id="SUB0202_0401-02_1#A004"
      action-type="requestAction"
      :request-model="A004RequestModel"
      @response-handler="responseHandlerInitializeA004($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaDomesticMutualFundOrderInputAccountSelectionA005"
      action-id="SUB0202_0401-02_1#A005"
      action-type="requestAction"
      :request-model="A005RequestModel"
      @response-handler="responseHandlerInitializeA005($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaDomesticMutualFundOrderInputTransferPreferentialFrameChangeA006"
      action-id="SUB0202_0401-02_1#A006"
      action-type="requestAction"
      :request-model="A006RequestModel"
      @response-handler="responseHandlerInitializeA006($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaDomesticMutualFundOrderInputDepositCategoryChangeA009"
      action-id="SUB0202_0401-02_1#A009"
      action-type="requestAction"
      :request-model="A009RequestModel"
      @response-handler="responseHandlerInitializeA009($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaYen110DomainModel from '@/resource/domain/IfaYen110DomainModel.json'
import IfaDomesticMutualFundOrderComplete from './IfaDomesticMutualFundOrderComplete'
import IfaDomesticMutualFundOrderConfirm from './IfaDomesticMutualFundOrderConfirm'
import IfaMutualFundDetailInfo from './IfaMutualFundDetailInfo'
import { IfaDomesticMutualFundOrderInputA004RequestModel } from './js/IfaDomesticMutualFundOrderInputA004RequestModel'
import { IfaDomesticMutualFundOrderInputA005RequestModel } from './js/IfaDomesticMutualFundOrderInputA005RequestModel'
import { IfaDomesticMutualFundOrderInputA006RequestModel } from './js/IfaDomesticMutualFundOrderInputA006RequestModel'
import { IfaDomesticMutualFundOrderInputA009RequestModel } from './js/IfaDomesticMutualFundOrderInputA009RequestModel'
import { IfaDomesticMutualFundOrderInputA010RequestModel } from './js/IfaDomesticMutualFundOrderInputA010RequestModel'
import { IfaDomesticMutualFundOrderInputFormModel } from './js/IfaDomesticMutualFundOrderInputFormModel'
import { IfaMutualFundDetailInfoA001RequestModel } from './js/IfaMutualFundDetailInfoA001RequestModel'
import { IfaMutualFundDetailInfoFormModel } from './js/IfaMutualFundDetailInfoFormModel.js'
import IfaUnits11DomainModel from '@/resource/domain/IfaUnits11DomainModel.json'
import IfaPoint9DomainModel from '@/resource/domain/IfaPoint9DomainModel.json'
export default {
  components: {
    IfaMutualFundDetailInfo,
    IfaDomesticMutualFundOrderConfirm,
    IfaDomesticMutualFundOrderComplete
  },
  props: {
    isVisible: { type: Boolean, required: true },
    customerInfo: { type: Object, required: true },
    orderData: { type: Object, required: true },
    listFlag: { type: Boolean, required: true }
  },
  emits: ['move-to-order-list', 'back'],
  data() {
    return {
      IfaUnits11DomainModel: IfaUnits11DomainModel,
      IfaYen110DomainModel: IfaYen110DomainModel,
      IfaPoint9DomainModel: IfaPoint9DomainModel,
      confirmDialogVisible: false,
      finishDialogVisible: false,
      infomationDialogVisible: false,
      noticeDialogVisible: false,
      jrNisaAccountNumber: '',
      param: null,
      confirmData: {},
      formRef: {},
      form: new IfaDomesticMutualFundOrderInputFormModel(),
      confirmResponseData: {
        a001aRequest: {
          tradeCd: ''
        }
      },
      dialogInfo: new IfaMutualFundDetailInfoFormModel(),
      buyMethodSelectedValue: '1',
      isShowBackButton: true,
      params: null,
      rules: {
        selectAccountType: { required: true, message: '口座を入力してください。', trigger: 'blur' },
        transfersPreferentialQuotaApplicationSelect: { required: true, message: '償還優遇を入力してください。', trigger: 'change' },
        unit: [{ required: true, type: 'number', trigger: 'blur', validator: this.purchaseVolumeValidator }],
        amount: [{ required: true, type: 'number', trigger: 'blur', validator: this.purchasePriceValidator }],
        usingType: { required: true, message: 'ポイントを選択してください。', trigger: 'blur' },
        saleVolume: [{ required: true, type: 'number', trigger: 'blur', validator: this.saleVolumeValidator }],
        salePrice: [{ required: true, type: 'number', trigger: 'blur', validator: this.salePriceValidator }],
        conflictOfInterestExplain: [{ required: true, type: 'boolean', trigger: 'blur', validator: this.conflictOfInterestExplainValidator }],
        agree1: [{ required: true, type: 'boolean', trigger: 'blur', validator: this.agree1Validator }],
        agree2: [{ required: false, type: 'boolean', trigger: 'blur', validator: this.agree2Validator }],
        agree3: [{ required: false, type: 'boolean', trigger: 'blur', validator: this.agree3Validator }],
        agree4: [{ required: false, type: 'boolean', trigger: 'blur', validator: this.agree4Validator }],
        solicitType: { required: true, message: '勧誘区分を入力してください。', trigger: 'blur' },
        distributionReceivingMethod: { required: true, message: '分配金受取方法を入力してください。', trigger: 'blur' },
        receiveOrderType: { required: true, message: '受注方法を入力してください。', trigger: 'blur' },
        saleMethod: { required: true, trigger: 'change', validator: this.saleMethodRuleValidator },
        sellDesignatedWord: { required: true, message: '売却指定を指定してください。', trigger: 'blur' },
        leverageInvestTrust: { required: true, message: 'レバレッジ投資信託を入力してください。', trigger: 'blur' },
        solicitingTransfers: { required: true, message: '乗換勧誘を入力してください。', trigger: 'blur' },
        sameCurrencySameCountryTransfers: { required: true, message: '同一通貨/同一国の乗換を入力してください。', trigger: 'blur' },
        intercompanyMutualFundTransferSolicitation: { required: true, message: '他社間投信乗換勧誘を入力してください。', trigger: 'blur' },
        prospectusIssueMethod: { required: true, message: '目論見書の交付方法を入力してください。', trigger: 'blur' },
        shortTermSaleConfirmSelect: { required: true, message: '短期売却確認を入力してください。', trigger: 'blur' },
        preRedemptionSellConfirm: { required: true, message: '償還前売却確認を入力してください。', trigger: 'blur' },
        financialCharacter: { required: true, message: '資金性格を入力してください。', trigger: 'blur' },
        pointInput: [{ required: true, type: 'number', trigger: 'blur', validator: this.tpointValidator }]
      }
    }
  },
  computed: {
    A004RequestModel() {
      return new IfaDomesticMutualFundOrderInputA004RequestModel(this.form)
    },
    A005RequestModel() {
      return new IfaDomesticMutualFundOrderInputA005RequestModel(this.form)
    },
    A006RequestModel() {
      return new IfaDomesticMutualFundOrderInputA006RequestModel(this.form)
    },
    A009RequestModel() {
      return new IfaDomesticMutualFundOrderInputA009RequestModel(this.form)
    },
    A010RequestModel() {
      return new IfaDomesticMutualFundOrderInputA010RequestModel(this.form, this.buyMethodSelectedValue)
    },
    commRateWord() {
      if (this.form.tradeCd === '7' || this.form.tradeCd === '8') {
        switch (this.form.selectDepositType) {
          case '4':
            return '手数料率(旧NISA)(税込)：'
          case '8':
            return '手数料率(つみたてNISA)(税込)：'
          case 'H':
            return '手数料率(NISA成長投資枠)(税込)：'
          case 'I':
            return '一律　手数料率(NISAつみたて投資枠)(税込)：'
          case 'J':
            return '手数料率（継続管理勘定)(税込)：'
          case '7':
            return '手数料率(旧NISA)(税込)：'
          default:
            return '手数料率（税込）：'
        }
      } else if ((this.form.tradeCd === '0' || this.form.tradeCd === '1') && this.form.selectDepositType === 'H') {
        return '手数料率(NISA成長投資枠)(税込)：'
      } else {
        return '手数料率（税込）：'
      }
    },
    initialStepAmount() {
      let valueStr = ''
      if (this.form.tradeCd === '1') { // 購入（累投）
        valueStr = this.form.brand.minSalesUnitAmount
      } else if (this.form.tradeCd === '8') { // 解約（累投）
        valueStr = this.form.brand.cancelTradeUnitAmount
      }
      const valueNumber = Number(valueStr)
      return !isNaN(valueNumber) ? valueNumber : 0
    },
    initialStepUnitLot() {
      let valueStr = ''
      if (this.form.tradeCd === '0') { // 購入（一般）
        valueStr = this.form.brand.minSalesLot
      } else if (this.form.tradeCd === '7' || this.form.tradeCd === '8') { // 解約（一般）または　解約（累投）
        valueStr = this.form.brand.cancelTradeUnitLot
      }
      const valueNumber = Number(valueStr)
      return !isNaN(valueNumber) ? valueNumber : 0
    },
    commRateConditionsLess() {
      if (this.form.tradeCd === '0') {
        return this.$_withCommaZeroPadding(this.form.commRateList[0].commRateConditions) + '口未満'
      } else {
        return this.$_withCommaZeroPadding(this.form.commRateList[0].commRateConditions) + '円未満'
      }
    },
    commRateConditionsLessOrMore() {
      const arrayList = []
      const listLength = this.form.commRateList.length
      for (let i = 1; i < listLength - 1; i++) {
        if (this.form.tradeCd === '0') {
          arrayList.push(this.$_withCommaZeroPadding(this.form.commRateList[i - 1].commRateConditions) + '口以上' + this.$_withCommaZeroPadding(this.form.commRateList[i].commRateConditions) + '口未満')
        } else {
          arrayList.push(this.$_withCommaZeroPadding(this.form.commRateList[i - 1].commRateConditions) + '円以上' + this.$_withCommaZeroPadding(this.form.commRateList[i].commRateConditions) + '円未満')
        }
      }
      return arrayList
    },
    commRateConditionsMore() {
      const listLength = this.form.commRateList.length
      if (this.form.tradeCd === '0') {
        return this.$_withCommaZeroPadding(this.form.commRateList[listLength - 1].commRateConditions) + '口以上'
      } else {
        return this.$_withCommaZeroPadding(this.form.commRateList[listLength - 1].commRateConditions) + '円以上'
      }
    },
    commRateDisplay() {
      const arrayList = []
      const listLength = this.form.commRateList.length
      for (let i = 0; i < listLength; i++) {
        if (this.form.commRateList[i].commRate === 0) {
          arrayList.push('なし')
        } else {
          arrayList.push(this.$_withCommaZeroPadding(this.form.commRateList[i].commRate) + '%')
        }
      }
      return arrayList
    },
    shortTermSaleConfirmList() {
      return [
        { key: '1', value: '該当する（' + this.form.shortTermSaleConfirm + '）' },
        { key: '2', value: '該当しない' }
      ]
    },
    preRedemptionSellConfirmSelectList() {
      return [
        { key: '1', value: '該当する（' + this.form.preRedemptionSellConfirmSelect + '）' },
        { key: '2', value: '該当しない' }
      ]
    },
    // 購入と売却の背景色を返す処理
    bgColor() {
      if (this.form.tradeCd === '0' || this.form.tradeCd === '1') {
        return '#fef0f0'
      } else if (this.form.tradeCd === '7' || this.form.tradeCd === '8') {
        return '#ecf5ff'
      }
      return '#fdf6ec'
    },
    // 預り区分固定
    mutualDepositTypeDisplayPattern() {
      // 顧客共通情報.ジュニアISA契約区分=1：契約　の場合
      if (this.customerInfo.jrIsaContractType === '1') {
        // 取引種別=購入（累投）　または　購入（一般）　の場合
        if (this.form.tradeCd === '1' || this.form.tradeCd === '0') {
          // ジュニアNISA口座を選択　かつ (顧客共通情報.ジュニア特定口座区分=(1:特定口座(代行納付) または 2:特定口座(確定申告)) でない)場合
          if (this.form.selectAccountType === '1' && !(this.customerInfo.jrTokuteiKouzaKbn === '1' || this.customerInfo.jrTokuteiKouzaKbn === '2')) {
            return 6
          // 総合口座を選択　かつ (顧客共通情報.特定口座区分= (1:特定口座(代行納付) または 2:特定口座(確定申告)) でない)場合
          } else if (this.form.selectAccountType === ' ' && !(this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2')) {
            return 6
          // 上記以外
          } else {
            return 3
          }
        // 取引種別=解約（累投）　または　解約（一般）　の場合
        } else if (this.form.tradeCd === '8' || this.form.tradeCd === '7') {
          return 4
        }
      // 上記以外
      } else {
        // 顧客共通情報.特定口座区分=(1:特定口座(代行納付) または 2:特定口座(確定申告)) でない場合
        if (!(this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2')) {
          return 7
        // 上記以外
        } else {
          return 5
        }
      }
    },
    // ラジオボタンで表示する預り区分リスト
    depositTypeListDisplay() {
      const depositTypeList = []
      const displayPattern = this.mutualDepositTypeDisplayPattern
      const list = this.$_getCodeList('MUTUAL_FUND_DEPOSIT_TYPE', displayPattern, 1)
      for (const depositType of this.form.depositTypeList) {
        const value = list.filter((item) => item['key'] === depositType)[0]
        if (value) {
          depositTypeList.push(value)
        }
      }
      return depositTypeList
    },
    preferentialRate() {
      return this.customerInfo.preferentialRate !== ''
        ? parseFloat(this.customerInfo.preferentialRate).toFixed(1)
        : '適用外'
    },
    IfaMutualFundDetailInfoA001RequestModel() {
      return new IfaMutualFundDetailInfoA001RequestModel(
        {
          'fundCodeTimes': this.form.fundCodeTimes,
          'fundCodeIssues': this.form.fundCodeIssues
        }
      )
    },
    buyMethodSelectDisabled() {
      if (this.form.brand.buyMethodSelect === '1' || this.form.brand.buyMethodSelect === '3') {
        return true
      } else {
        this.buyMethodSelectedInitialValue()
        return false
      }
    }
  },
  watch: {
    // isVisibleが更新された場合のみformRefにセットする
    // （呼び出し元では「onShow()呼び出し → isVisibleをTrueに変更」という処理を行っているため、onShow()実行時にはrefs要素を参照できないため)
    isVisible(newVal) {
      if (newVal) {
        this.$nextTick(() => {
          this.formRef = this.$refs.formModel
        })
      }
    }
  },
  methods: {
    onShow() {
      this.form = new IfaDomesticMutualFundOrderInputFormModel()
      Object.assign(this.form, this.orderData)
      this.transfersPreferentialQuotaApplicationInitialValue(this.form)

      // 保有商品一覧からの遷移の場合は「戻る」ボタンを表示しない
      this.params = this.$store.getters.pageInfo.params
      this.isShowBackButton = false

      if (this.params && this.params.source === 'IfaHoldingSecurityList') {
        // 保有商品一覧画面から
        this.form.dispatchId = this.params.orderData?.dispatchId
      } else {
        // 国内投信購入・積立可能一覧から
        this.form.dispatchId = this.orderData?.dispatchId
        this.isShowBackButton = true
      }
      this.form.distributionReceivingMethod = ' '

      // バリデーションメッセージのクリア（初回onShow()実行時には実施しない）
      if (this.formRef && Object.keys(this.formRef).length !== 0) {
        this.formRef.clearValidate()
      }
    },
    buyMethodSelectedInitialValue() {
      this.buyMethodSelectedValue = ''
    },
    transfersPreferentialQuotaApplicationInitialValue(form) {
      if (form.transfersPreferentialQuotaApplication === '項目非活性') {
        form.transfersPreferentialQuotaApplicationSelect = '0'
      } else {
        form.transfersPreferentialQuotaApplicationSelect = ''
      }
    },
    onLabelClickA011() {
      const resolvedRoute = this.$router.resolve({ name: 'Ifa-Faq' })
      this.$store.commit('page/setFaqParam', '10002')
      window.open(resolvedRoute.href, '_blank')
      this.$store.commit('page/setFaqParam', '')
    },
    responseHandlerInitializeA004(response) {
      Object.assign(this.form, response.dataList[0])
      this.transfersPreferentialQuotaApplicationInitialValue(this.form)
    },
    responseHandlerInitializeA005(response) {
      Object.assign(this.form, response.dataList[0])
      this.transfersPreferentialQuotaApplicationInitialValue(this.form)
    },
    responseHandlerInitializeA006(response) {
      this.form.nisaBuy = response.dataList[0].nisaBuy
    },
    responseHandlerInitializeA009(response) {
      this.form.commRateList = response.dataList[0].commRateList
    },
    requestTypeA004() {
      document.getElementById('ifaDomesticMutualFundOrderInputResetA004').click()
    },
    requestTypeA005() {
      document.getElementById('ifaDomesticMutualFundOrderInputAccountSelectionA005').click()
    },
    requestTypeA006() {
      document.getElementById('ifaDomesticMutualFundOrderInputTransferPreferentialFrameChangeA006').click()
    },
    requestTypeA009() {
      document.getElementById('ifaDomesticMutualFundOrderInputDepositCategoryChangeA009').click()
    },
    // 戻るボタン
    handleClose() {
      this.$emit('back')
    },
    // 投信詳細情報を開く
    handleInfoDialogOpenA003() {
      this.dialogInfo.code = this.form.brandCode
      this.dialogInfo.type = this.form.tradeCd
      this.dialogInfo.name = this.form.brandName
    },
    // A003 投信詳細情報　レスポンスハンドラー
    detailDisplayA003ResponseHandler(event) {
      Object.assign(this.dialogInfo, event.dataList[0])
      this.infomationDialogVisible = true
    },
    // 投信詳細情報を閉じる
    handleCloseModal() {
      this.infomationDialogVisible = false
    },
    // 全額売却時の注意事項を閉じる
    handleNoticeDialogCloseA002() {
      this.noticeDialogVisible = false
    },
    // 国内投信注文受付確認ダイアログを閉じる
    handleConfirmDialogClose() {
      this.confirmDialogVisible = false
      this.form.agree1 = false
      this.form.agree2 = false
      this.form.agree3 = false
      this.form.agree4 = false
    },
    handleConfirmDialogFinish(response) {
      this.confirmResponseData = response
      this.confirmDialogVisible = false
      this.finishDialogVisible = true
    },
    // 国内投信注文受付完了ダイアログを閉じる
    handleDialogClose() {
      this.finishDialogVisible = false
      this.handleClose()
    },
    // 国内投信注文受付完了ダイアログを閉じる
    handleMoveToOrderList() {
      this.finishDialogVisible = false
      this.$emit('move-to-order-list')
    },
    // 国内投信注文受付確認ダイアログを開く
    async orderConfirmA010(response) {
      if (response.errorLevel !== 0 && response.errorLevel !== 1 && response.errorLevel !== 2) {
        this.onShow()
      } else {
        this.confirmData = response.dataList[0]
        await this.$nextTick() // 現在の更新を待つためのVueのメソッド
        await this.$refs.IfaDomesticMutualFundOrderConfirm.onShow()
        this.confirmDialogVisible = true
      }
    },
    // リセットボタン処理
    resetForm(formName) {
      // this.onShow()
      if (this.customerInfo.jrIsaContractType === '1' && (this.form.tradeCd === '0' || this.form.tradeCd === '1')) {
        this.form.selectAccountType = ''
      }
      this.form.sellDesignatedWord = ''
      this.form.amount = ''
      this.form.unit = ''
      this.form.usingType = ''
      this.form.pointInput = ''
      this.form.solicitType = ''
      this.form.receiveOrderType = ''
      this.form.leverageInvestTrust = ''
      this.form.solicitingTransfers = ''
      this.form.sameCurrencySameCountryTransfers = ''
      this.form.intercompanyMutualFundTransferSolicitation = ''
      this.form.shortTermSaleConfirmSelect = ''
      this.form.preRedemptionSellConfirm = ''
      this.form.distributionReceivingMethod = ' '
      this.form.prospectusIssueMethod = ''
      this.form.conflictOfInterestExplain = '0'
      this.form.prospectusSupplementaryDocConfirm = '0'
      this.form.windowSpaceFundPrecautionsConsent = '0'
      this.form.costExplained = '0'
      this.form.multipleTradeClearlyCommStated = '0'
      this.requestTypeA004()
      this.formRef.clearValidate()
    },
    // 口数が更新された処理
    handleChangeVolume(value, oldValue) {
      if (this.form.tradeCd === '0' && this.form.unit > 0 &&
          this.form.minPurchaseUnit > 0 && value < this.form.minPurchaseUnit) {
        this.$nextTick(() => {
          this.form.unit = this.form.minPurchaseUnit
        })
      } else if (this.form.tradeCd === '1' && this.form.saleVolume > 0 &&
          this.form.minSaleUnit > 0 && value < this.form.minSaleUnit) {
        this.$nextTick(() => {
          this.form.saleVolume = this.form.minSaleUnit
        })
      }
    },
    // 金額が更新された処理
    handleChangePrice(value, oldValue) {
      if (this.form.tradeCd === '0' && this.form.amount > 0 &&
          this.form.minPurchasePriceUnit > 0 && value < this.form.minPurchasePriceUnit) {
        this.$nextTick(() => {
          this.form.amount = this.form.minPurchasePriceUnit
        })
      } else if (this.form.tradeCd === '1' && this.form.salePrice > 0 &&
          this.form.minSalePriceUnit > 0 && value < this.form.minSalePriceUnit) {
        this.$nextTick(() => {
          this.form.salePrice = this.form.minSalePriceUnit
        })
      }
    },
    // 購入数量のバリデーションチェック処理
    purchaseVolumeValidator(rule, value, callback) {
      // 購入､一般の場合だけチェックする
      if (this.form.tradeCd === '0' || this.form.tradeCd === '7' || this.form.tradeCd === '8') {
        // 数量が0の場合はエラー
        if (this.form.unit === '' || parseInt(this.form.unit) === 0) {
          callback('数量を入力してください｡')
          return
        }
      }
      // OK
      callback()
    },
    // 購入金額のバリデーションチェック処理
    purchasePriceValidator(rule, value, callback) {
      // 購入､累投の場合だけチェックする
      if (this.form.tradeCd === '1' || this.form.tradeCd === '8') {
        // 数量が0の場合はエラー
        if (this.form.amount === '' || parseInt(this.form.amount) === 0) {
          callback('金額を入力してください｡')
          return
        }
      }
      // OK
      callback()
    },
    // 売却数量のバリデーションチェック処理
    saleVolumeValidator(rule, value, callback) {
      // 売却､一般の場合だけチェックする
      if (this.form.tradeCd === '7') {
        // 数量が0の場合はエラー
        if (this.form.saleVolume === '' || parseInt(this.form.saleVolume) === 0) {
          callback('数量を入力してください｡')
          return
        }
      }
      // OK
      callback()
    },
    // 売却金額のバリデーションチェック処理
    salePriceValidator(rule, value, callback) {
      // 売却､累投の場合だけチェックする
      if (this.form.tradeCd === '8') {
        // 全額売却の場合はチェックしない
        if (this.form.salePriceType === '0') {
          // OK
          callback()
          return
        }
        // 数量が0の場合はエラー
        if (this.form.salePrice === '' || parseInt(this.form.salePrice) === 0) {
          callback('金額を入力してください｡')
          return
        }

        // 数量が単元単位でない場合はエラー
        if (parseInt(this.form.salePrice) % this.form.salePriceUnit !== 0) {
          callback('金額は' + this.form.brand.cancelTradeUnitAmount + '円単位で入力してください｡')
          return
        }
      }
      // OK
      callback()
    },
    // ポイントのバリデーションチェック処理
    tpointValidator(rule, value, callback) {
      // 購入､累投の場合だけチェックする
      if (this.form.tradeCd === '1') {
        // 使用しないまたは全部使用の場合はチェックしない
        if (this.form.usingType !== '1') {
          // OK
          callback()
          return
        }

        // ポイントが入力されていないまたは0の場合はエラー
        if (this.form.pointInput === '' || parseInt(this.form.pointInput) === 0) {
          callback('利用するポイント数を入力してください｡')
          return
        }

        // ポイント数がポイント残高を超えている時はエラー
        if (parseInt(this.form.pointInput) > this.form.point.pointCount) {
          callback('ポイント残高(' + value.point + ')以内で入力してください｡')
          return
        }
      }
      // OK
      callback()
    },
    saleMethodRuleValidator(rule, value, callback) {
      if (this.buyMethodSelectedValue === '') {
        callback('売却方法を入力してください。')
        return
      }
      // OK
      callback()
    },
    // 利益相反可能性の説明のバリデーションチェック処理
    conflictOfInterestExplainValidator(rule, value, callback) {
      // 購入の場合だけチェックする
      if ((this.form.tradeCd === '0' || this.form.tradeCd === '1') && this.form.conflictOfInterestExplain === '0') {
        callback('利益相反可能性の説明を選択してください。')
        return
      }
      // OK
      callback()
    },
    // 目論見書のバリデーションチェック処理
    agree1Validator(rule, value, callback) {
      // 購入の場合だけチェックする
      if ((this.form.tradeCd === '0' || this.form.tradeCd === '1') && this.form.prospectusSupplementaryDocConfirm === '0') {
        callback('確認項目を選択してください。')
        return
      }
      // OK
      callback()
    },
    // 窓空きファンドの注意事項に同意のバリデーションチェック処理
    agree2Validator(rule, value, callback) {
      // 購入の場合だけチェックする
      if ((this.form.tradeCd === '0' || this.form.tradeCd === '1') && this.form.windowSpaceFundPrecautionsConsent === '0') {
        callback('確認項目を選択してください。')
        return
      }
      // OK
      callback()
    },
    // 確認項目３：窓空きファンドの注意事項に同意のバリデーションチェック処理
    agree3Validator(rule, value, callback) {
      // 購入の場合だけチェックする
      if ((this.form.tradeCd === '0' || this.form.tradeCd === '1') && this.form.costExplained === '0') {
        callback('確認項目を選択してください。')
        return
      }
      // OK
      callback()
    },
    // 確認項目４：所属金融商品取引業者が複数ある場合
    agree4Validator(rule, value, callback) {
      // 購入の場合だけチェックする
      if ((this.form.tradeCd === '0' || this.form.tradeCd === '1') && this.form.multipleTradeClearlyCommStated === '0') {
        callback('確認項目を選択してください。')
        return
      }
      // OK
      callback()
    }
  }
}
</script>

<style lang="scss" scoped>
.back-button__wrapper {
  float: right;
  margin: 0 0.5rem 0 0;
}
.dialog-back-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -46px 1.5rem 0 auto;
}
.detail-button__wrapper {
  float: right;
  margin: 0.5rem 0.5rem 0 0;
}
.reset-button__wrapper {
  float: right;
  margin: 0 1.5rem 0 0;
}
.form-order-button__wrapper {
  margin-top: 0.5rem;
  margin-left: 0.5rem;
}
.form-container {
  display: flex;
  justify-content: space-between;
}
.form-main {
  width: 100%;
  margin-left: 2%;
}
.form-main :deep(.el-form-item__content) {
--ifa-input-validation-error-width: 400px;
}
.form-area__section {
  height: auto;
  padding-top: 0.5rem;
  padding-bottom: 1.0rem;
  border-bottom: 1px solid #eee;
  white-space: pre-wrap;
}
.form-radio__auto {
  width: auto;
}
.form-area__select {
  width: 9rem;
}
.form-area__select-auto {
  width: calc(100% - 1rem);
}
.info-item__header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  white-space: pre-wrap;
}
.info-item__value {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  height: 25px;
  line-height: 25px;
}
.__border {
  border-bottom: 1px solid #bfcbd9;
}
:deep(.el-form-item__label) {
  padding-right: 1.0rem;
}
.custom-loading-text {
  color: white;
  font-size: 20px;
}
.nowrap {
  white-space: nowrap;
}
:deep(.__right) {
  text-align: right;
  justify-content: right;
}
.origin_position {
  top: 10px;
  display: inline-block;
  position: relative;
  right: 40px;
}
.radio_width :deep(.el-radio) {
  width: 295px;
}
.brand_area_text_color :deep(.el-text) {
  color: #606266;
}
.buying_power_label {
  white-space: nowrap !important;
}
</style>
