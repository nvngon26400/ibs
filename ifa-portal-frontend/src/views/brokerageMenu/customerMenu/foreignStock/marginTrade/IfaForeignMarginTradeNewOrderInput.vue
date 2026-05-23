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
          v-if="form.tradeCd === '3' && form.quantityAvailableForSale === '0'"
        >
          <div style="color: red; margin: 1rem 0">注文可能な数量が不足しているため、注文できません。</div>
        </el-row>
        <el-row
          v-show="form.tradeCd === ''"
        >
          <el-card class="top-card">
            <el-form-item
              label="取引種別"
              prop="tradeCd"
              style="margin-top: 1rem;"
            >
              <el-radio
                v-for="item in form.tradeCdList"
                :id="getIDforTopElement(item.index)"
                :key="item.key"
                v-model="form.tradeCd"
                :label="item.key"
                class="form-radio"
                @change="changeTradeType"
              >
                {{ item.value }}
              </el-radio>
            </el-form-item>
          </el-card>
        </el-row>

        <!-- 銘柄検索 -->
        <el-row>
          <ifa-foreign-brand-search
            ref="IfaForeignBrandSearch"
            :trade-type="form.tradeCd"
            :form="form"
            :a003-request-model="a003RequestModel"
            :a005-request-model="a005RequestModel"
            :a011-request-model="a011RequestModel"
            @a003-pre="stockPriceDisplayA003Pre"
            @a003-res="stockPriceDisplayA003Res"
            @a005="updateA005"
            @reset-brand-search="initialize"
          ></ifa-foreign-brand-search>
        </el-row>
        <!-- /銘柄検索 -->

        <el-row>
          <el-card
            v-if="form.brandCode !== ''"
            style="background-color: #eee; margin-bottom: 0.5rem;"
          >
            <el-row style="padding-top: 0.5rem;">
              <el-row>
                <el-col
                  :offset="1"
                  style="font-size :14px; display:flex; align-items: center;"
                >
                  <el-icon><WarningFilled></WarningFilled></el-icon>
                  <span>米国株式&emsp;ご注意事項</span>
                </el-col>
              </el-row>
              <el-row
                class="links"
                style="padding-top: 0.5rem; margin-left: 4.1666666667%;"
              >
                <ifa-link
                  disp-name="・本日の注意銘柄"
                  :param-url="form.tradeLimitUrl"
                  link-icon-image="externalLink"
                ></ifa-link>
                <ifa-link
                  disp-name="・休場日"
                  :param-url="form.closedDay"
                  link-icon-image="externalLink"
                ></ifa-link>
                <ifa-link
                  disp-name="・円貨決済停止日"
                  :param-url="form.yenClosed"
                  link-icon-image="externalLink"
                ></ifa-link>
                <ifa-link
                  disp-name="・取扱銘柄一覧"
                  :param-url="form.usequityList"
                  link-icon-image="externalLink"
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

        <!-- 信用建余力 -->
        <el-row>
          <el-card style="background-color: #eee; margin-bottom: 0.5rem;">
            <el-row
              :gutter="20"
              style="padding-top: 0.5rem;"
            >
              <el-col
                :offset="1"
                :span="3"
              >
                <span class="info-item__header">信用建余力</span>
              </el-col>
              <el-col
                :span="4"
                class="__right"
                style="padding-right: 0;"
              >
                <span
                  v-if="form.brandCode !== ''"
                  class="info-item__header"
                >{{ $_out($_withCommaZeroPadding(form.foreignMarginPositionPower), 2) }}</span>
                <span
                  v-else
                  class="info-item__header"
                >-</span>
              </el-col>
              <el-col
                :span="9"
                style="padding: 0;"
              >
                <span
                  v-if="form.brandCode !== ''"
                  class="info-item__header"
                >&nbsp;{{ $_out(form.tradeCurrency) }}</span>
                <span
                  v-else
                  class="info-item__header"
                ></span>
              </el-col>
            </el-row>

            <!-- 参考信用建余力 -->
            <el-row
              :gutter="20"
              style="padding-top: 0.5rem;"
            >
              <el-col
                :offset="1"
                :span="3"
              >
                <span class="info-item__header">参考信用建余力</span>
              </el-col>
              <el-col
                :span="4"
                class="__right"
                style="padding-right: 0;"
              >
                <span
                  v-if="form.brandCode !== ''"
                  class="info-item__header"
                >
                  <span v-if="!showReference">
                    <ifa-button
                      id="btnDisplay"
                      text="表示する"
                      tabindex="-1"
                      :small="true"
                      style="padding-right: 0;"
                      action-type="requestAction"
                      action-id="SUB0202_0303-01_1#A019"
                      :request-model="{}"
                      @response-handler="displayReferenceMarginBalanceA019($event)"
                    ></ifa-button>
                  </span>
                  <span v-else-if="form.referenceMarginPower === '0'">--</span>
                  <span v-else>{{ $_out($_withCommaZeroPadding(form.referenceMarginPower), 2) }}</span>
                </span>
                <span
                  v-else
                  class="info-item__header"
                >-</span>
              </el-col>
              <el-col
                :span="9"
                style="padding: 0;"
              >
                <span
                  v-if="form.brandCode !== '' && showReference"
                  class="info-item__header"
                >&nbsp;{{ $_out(form.tradeCurrency) }}</span>
                <span
                  v-else
                  class="info-item__header"
                ></span>
              </el-col>
            </el-row>

            <!-- 委託保証金率 -->
            <el-row
              :gutter="20"
              style="padding-top: 0.5rem;"
            >
              <el-col
                :offset="1"
                :span="3"
              >
                <span class="info-item__header">委託保証金率</span>
              </el-col>
              <el-col
                :span="4"
                class=" __right"
                style="padding-right: 0;"
              >
                <span
                  v-if="form.brandCode !== ''"
                  class="info-item__header"
                >{{ $_withCommaZeroPadding($_out(form.collateralTransferMarginDepositRateAfter), 2) }}</span>
                <span
                  v-else
                  class="info-item__header"
                >-</span>
              </el-col>
              <el-col
                :span="9"
                style="padding: 0;"
              >
                <span class="info-item__header">&nbsp;%</span>
              </el-col>
            </el-row>
          </el-card>
        </el-row>
        <!-- /余力情報 -->

        <!-- 通常注文タブ -->
        <el-card
          class="box-card"
          :style="{'background-color': bgColor}"
        >
          <!-- フォーム: 取引種別 -->
          <el-row class="form-area__section">
            <el-col :span="20">
              <el-form-item
                label="取引種別"
                style="margin-top: 1rem;"
              >
                <span
                  class="bold"
                  :class="{'font-color__plus': form.tradeCd === '2', 'font-color__minus': form.tradeCd === '3'}"
                >{{ $_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', '1', form.tradeCd) }}</span>
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
                @app-action-handler="resetA015"
              ></ifa-button>
            </el-col>
          </el-row>
          <!-- /フォーム: 取引種別 -->

          <!-- 在庫状況 -->
          <div
            v-if="form.tradeCd === '3'"
            class="form-area__section"
          >
            <el-form-item label="売建可能数量">
              <span>{{ $_withCommaNoneZeroPadding($_out(form.quantityAvailableForSale)) }} 株</span>
            </el-form-item>
          </div>
          <!-- /在庫状況 -->

          <!-- フォーム: 数量 -->
          <el-row class="form-area__section">
            <div style="width: 640px;">
              <ifa-input-quantity
                id="quantity"
                v-model="form.foreignQuantity"
                label="数量"
                style="width: 200px;"
                prop="foreignQuantity"
                :disabled="editDisable"
                :min="form.tradeLowerLimitQuantity"
                :max="maxOrderQuantity"
                :step="form.tradeUnit"
                :initial-step="Math.max(form.tradeLowerLimitQuantity, form.tradeUnit)"
                :support="true"
                :placeholder="''"
                unit="株"
                step-strictly
                class="form-area__input-number"
                :domain="IfaVolume10DomainModel"
              >
              </ifa-input-quantity>
            </div>
            <el-col
              :span="6"
              style="margin-top: 0.7rem;"
            >
              <span v-if="form.brandCode !== ''">（{{ $_out(form.tradeLowerLimitQuantity) }}株以上{{ $_out(form.tradeUnit) }}株単位）</span>
            </el-col>
          </el-row>
          <!-- /フォーム: 数量 -->

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
                      @change="changeOrderPriceKindA022"
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

                <!-- フォーム: 価格/執行方法/指値の条件 -->
                <div
                  v-if="form.orderPriceKind === '1'"
                  style="width: 850px;"
                >
                  <div style="display: inline-flex;">
                    <ifa-input-amount
                      id="hiddenOrderPrice"
                      v-model="form.hiddenOrderPrice"
                      label-width="0"
                      prop="hiddenOrderPrice"
                      :disabled="editDisable"
                      :min="0"
                      :step="0.01"
                      :initial-step="initialStepPrice"
                      :max="maxOrderableQuantity"
                      :support="true"
                      :unit="form.tradeCurrency"
                      :placeholder="' '"
                      :domain="IfaUnitPriceForeign122DomainModel"
                    >
                    </ifa-input-amount>
                  </div>
                </div>
                <!-- /フォーム: 価格/執行方法/指値の条件 -->

                <!-- フォーム: 価格/執行方法/逆指値の条件 -->
                <div
                  v-else-if="form.orderPriceKind === '3'"
                  style="width: 1000px;"
                >
                  <div style="display: inline-flex;">
                    <span style="padding: 0.6rem 30px 0 0">現在値が</span>
                    <ifa-input-amount
                      id="orderInputAreaPriceTermsreversePriceLimitStopOrderPrice"
                      v-model="form.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice"
                      prop="orderInputAreaPriceTermsreversePriceLimitStopOrderPrice"
                      :min="0"
                      :step="0.01"
                      :initial-step="initialStepPrice"
                      :max="maxOrderableQuantity"
                      :support="true"
                      :unit="form.tradeCurrency"
                      :placeholder="' '"
                      class="form-area__input-number"
                      :domain="IfaUnitPriceForeign122DomainModel"
                    >
                    </ifa-input-amount>
                    <span style="width: 200px; padding: 0.6rem 0 0 0"> {{ form.tradeCd === '2' ? '以上' : '以下' }}になった時点で</span>
                  </div>

                  <!-- フォーム: /価格/執行方法/逆指値の執行方法  -->
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
                    <div
                      v-show="form.orderPriceKindListReversePriceLimit === '3'"
                      style="margin-left: 20px; width: 350px;"
                    >
                      <ifa-input-amount
                        id="hiddenOrderPriceReversePriceLimit"
                        v-model="form.hiddenOrderPriceReversePriceLimit"
                        prop="hiddenOrderPriceReversePriceLimit"
                        :min="0"
                        :step="0.01"
                        :initial-step="initialStepPrice"
                        :max="maxOrderableQuantity"
                        :support="true"
                        :unit="form.tradeCurrency"
                        :placeholder="' '"
                        :domain="IfaUnitPriceForeign122DomainModel"
                      >
                      </ifa-input-amount>
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
                  </el-row>
                </div>
              </el-col>
            </el-row>
          </div>
          <!-- /フォーム: 価格 -->

          <!-- フォーム: 概算建代金 -->
          <div class="form-area__section">
            <el-form-item
              label="概算建代金"
            >
              <span style="display: inline-block; width: 190px;">
                <span>{{ $_withCommaZeroPadding($_out(form.approximatePositionAmount), 2) }} {{ form.approximatePositionAmount.length ? form.tradeCurrency : '' }}</span>
              </span>
              <span style="display: inline-block;">
                <ifa-button
                  id="btnRecalculate"
                  :disabled="editDisable || (form.tradeCd === '3' && form.quantityAvailableForSale === '0')"
                  text="再計算"
                  :form="formRef"
                  action-type="originalAction"
                  @app-action-handler="approximatePositionAmountA020()"
                ></ifa-button>
              </span>
            </el-form-item>
          </div>
          <!-- /フォーム: 概算建代金 -->

          <!-- フォーム: 期間 -->
          <el-row class="form-area__section">
            <div style="width: 680px;">
              <ifa-period-selector
                ref="periodSelector"
                v-model:period="form.period"
                v-model:period-type="form.periodType"
                :button-options="buttonOptions"
                class="date-picker"
                label="期間"
                required
                prop="periodType"
                :picker-options="dateOptions"
                :disabled="editDisable"
                @update:period-type="setPeriodRadio($event)"
              ></ifa-period-selector>
            </div>
          </el-row>
          <!-- /フォーム: 期間 -->

          <!-- フォーム: 預り区分 -->
          <div class="form-area__section">
            <el-form-item
              label="預り区分"
              :tabindex="-1"
            >
              <ifa-text
                code-list-id="FOREIGN_DEPOSIT_TYPE"
                :disp-pattern="2"
                :code-key="form.depositType"
              ></ifa-text>
            </el-form-item>
          </div>
          <!-- /フォーム: 預り区分 -->

          <!-- フォーム: 決済方法 -->
          <div class="form-area__section">
            <el-form-item
              label="決済方法"
              :tabindex="-1"
            >
              <ifa-text
                code-list-id="SETTLEMENT_TYPE"
                :disp-pattern="1"
                :code-key="form.kessaiHoho"
              >
              </ifa-text>
            </el-form-item>
          </div>
          <!-- /フォーム: 決済方法 -->

          <!-- フォーム: 期限 -->
          <div class="form-area__section">
            <el-form-item
              label="期限"
              :tabindex="-1"
            >
              <ifa-text
                code-list-id="MARGIN_DUE_DATE"
                :disp-pattern="1"
                :code-key="form.marginDueDate"
              ></ifa-text>
            </el-form-item>
          </div>
          <!-- /フォーム: 期限 -->

          <!-- フォーム: 勧誘区分 -->
          <div class="form-area__section">
            <div style="width:520px;">
              <ifa-input-select
                v-model="form.kanyuKbn"
                label="勧誘区分"
                prop="kanyuKbn"
                :disabled="editDisable"
                code-list-id="FOREIGN_STOCK_INVITATION_TYPE"
                :disp-pattern="1"
                :select-pattern="1"
                size="small"
                placeholder=""
              >
              </ifa-input-select>
            </div>
          </div>
          <!-- /フォーム: 勧誘区分 -->

          <!-- フォーム: 受注方法 -->
          <div class="form-area__section">
            <div style="width:520px;">
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
            </div>
          </div>
          <!-- /フォーム: 受注方法 -->

          <!-- フォーム: 重要事項の説明 -->
          <div class="form-area__section">
            <el-col
              :span="15"
            >
              <el-form-item
                label="重要事項の説明"
                prop="importantMatterType"
              >
                <el-radio
                  v-for="item in form.importantMatterTypeList"
                  :key="item.key"
                  v-model="form.importantMatterType"
                  :disabled="editDisable"
                  class="form-radio"
                  :label="item.key"
                >
                  {{ item.value }}
                </el-radio>
              </el-form-item>
            </el-col>
          </div>
          <!-- /フォーム: 重要事項の説明 -->

          <!-- 英文開示銘柄 -->
          <el-row
            v-if="form.engJudge === '1'"
            class="form-area__section"
          >
            <div>
              <ifa-input-check
                ref="engPubText"
                v-model="engPubText"
                label="英文開示銘柄"
                prop="engPubText"
                :disabled="editDisable"
                code-list-id="ISSUES_DISCLOSED_IN_ENGLISH_BRAND"
                :disp-pattern="1"
                :select-pattern="2"
                @update:model-value="setEngPubText($event)"
              >
              </ifa-input-check>
            </div>
            <el-col
              :span="8"
              style="margin-left: 30px; margin-top: 0.4rem;"
            >
              <span
                class="inner-link"
                @click="englishDisclosureBrandAboutLinkA021"
              >英文開示銘柄について</span>
            </el-col>
          </el-row>
          <!-- /英文開示銘柄 -->

          <!-- フォーム: 確認項目 -->
          <div class="form-area__section">
            <el-col :span="10">
              <ifa-input-check
                ref="checkInsider"
                v-model="checkInsider"
                label="確認項目"
                prop="checkInsider"
                :disabled="editDisable"
                code-list-id="INSIDER_CONFIRM"
                :disp-pattern="2"
                :select-pattern="2"
                @update:model-value="setCheckInsider($event)"
              ></ifa-input-check>
            </el-col>
          </div>
          <!-- フォーム: 注文確認 -->
          <div class="form-area__section">
            <ifa-button
              id="btnOrderConfirm"
              :disabled="editDisable || (form.tradeCd === '3' && form.quantityAvailableForSale === '0')"
              text="注文確認"
              color="primary"
              action-type="originalAction"
              @app-action-handler="orderConfirmA012()"
            ></ifa-button>
          </div>

        </el-card>
      <!-- /通常注文タブ -->
      </el-form>
    </div>

    <!-- ダイアログ：外国株信用取引新規注文確認 -->
    <ifa-foreign-margin-trade-new-order-confirm
      ref="ifaForeignMarginTradeNewOrderConfirm"
      :is-visible="dialogConfirmVisible"
      :form-data="orderInfo"
      :customer-info="customerInfo"
      @close-modal="handleCloseModal(false)"
      @order-finish="handleOrderFinish"
    ></ifa-foreign-margin-trade-new-order-confirm>
    <!-- /ダイアログ：外国株信用取引新規注文確認 -->

    <!-- ダイアログ：外国株信用取引新規注文完了 -->
    <ifa-foreign-margin-trade-new-order-complete
      :is-visible="dialogFinish"
      :form-data="confirmResponseData"
      :customer-info="customerInfo"
      @close-modal="
        handleCloseModal(true);
        initialize();
      "
      @move-to-order-list="handleMoveToOrderList"
    ></ifa-foreign-margin-trade-new-order-complete>
    <!-- /ダイアログ：外国株信用取引新規注文完了 -->
  </div>
  <ifa-requester
    id="IfaForeignMarginTradeNewOrderInputA001"
    action-id="SUB0202_0303-01_1#A001"
    action-type="requestAction"
    @response-error-handler="errorHandlerInitializeA001($event)"
  ></ifa-requester>
  <ifa-requester
    id="IfaForeignMarginTradeNewOrderInputOrderConfirmA012"
    :form="formRef"
    action-type="requestAction"
    action-id="SUB0202_0303-01_1#A012"
    :request-model="a012RequestModel"
    :pre-request-handler="preConfirm"
    @response-handler="responseHandlerA012($event)"
  ></ifa-requester>
  <ifa-requester
    id="IfaForeignMarginTradeNewOrderInputA020"
    :form="formRef"
    action-type="requestAction"
    action-id="SUB0202_0303-01_1#A020"
    :request-model="a020RequestModel"
    @response-handler="approximatePositionAmountA020res($event)"
  ></ifa-requester>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import IfaForeignMarginTradeNewOrderConfirm from './IfaForeignMarginTradeNewOrderConfirm'
import IfaForeignMarginTradeNewOrderComplete from './IfaForeignMarginTradeNewOrderComplete'
import IfaForeignBrandSearch from './components/IfaForeignBrandSearch'
import { getMessage } from '@/utils/errorHandler'
import { IfaForeignMarginTradeNewOrderInputFormModel } from './js/IfaForeignMarginTradeNewOrderInputFormModel'
import { IfaForeignMarginTradeNewOrderInputA003RequestModel } from './js/IfaForeignMarginTradeNewOrderInputA003RequestModel'
import { IfaForeignMarginTradeNewOrderInputA005RequestModel } from './js/IfaForeignMarginTradeNewOrderInputA005RequestModel'
import { IfaForeignMarginTradeNewOrderInputA011RequestModel } from './js/IfaForeignMarginTradeNewOrderInputA011RequestModel'
import { IfaForeignMarginTradeNewOrderInputA012RequestModel } from './js/IfaForeignMarginTradeNewOrderInputA012RequestModel'
import { IfaForeignMarginTradeNewOrderInputA020RequestModel } from './js/IfaForeignMarginTradeNewOrderInputA020RequestModel'
import IfaVolume10DomainModel from '@/resource/domain/IfaVolume10DomainModel.json'
import IfaUnitPriceForeign122DomainModel from '@/resource/domain/IfaUnitPriceForeign122DomainModel.json'

export default {
  components: {
    screenTitle,
    IfaForeignMarginTradeNewOrderConfirm,
    IfaForeignMarginTradeNewOrderComplete,
    IfaForeignBrandSearch
  },
  emits: ['back', 'show-tab-by-name', 'initializeError'],
  data() {
    return {
      IfaVolume10DomainModel,
      IfaUnitPriceForeign122DomainModel,
      form: new IfaForeignMarginTradeNewOrderInputFormModel(),
      formRef: {},
      bgColor: '#fdf6ec',
      orderInfo: {},
      orderPriceKindList: [],
      stopOrderPriceKindList: [],
      isRecalculateBtn: false,
      confirmResponseData: {},
      engPubText: '1',
      checkInsider: '0',

      // 参考信用建余力の表示制御
      showReference: false,
      dialogFinish: false,
      dialogTableVisible: false,
      dialogConfirmVisible: false,
      dialogFinishVisible: false,
      maxOrderableQuantity: 999999999.99,
      buttonOptions: {
        today: '',
        period: ''
      },

      // 入力チェック
      rules: {
        // 取引種別
        tradeCd: [
          { required: true }
        ],
        // 数量
        foreignQuantity: [
          { required: true, trigger: 'blur', validator: this.foreignQuantityValidator, message: getMessage('errors.required', ['数量']) }
        ],
        // 価格条件
        orderPriceKindList: [
          { required: true, trigger: 'change', message: getMessage('errors.selected', ['価格']) }
        ],
        // 指値執行価格
        hiddenOrderPrice: [
          { required: true, trigger: 'blur', validator: this.hiddenOrderPriceValidator, message: getMessage('errors.required', ['注文単価（指値）']) }
        ],
        // 逆指値条件価格
        orderInputAreaPriceTermsreversePriceLimitStopOrderPrice: [
          { required: true, trigger: 'blur', validator: this.orderInputAreaPriceTermsreversePriceLimitStopOrderPriceValidator, message: getMessage('errors.required', ['発火条件価格（逆指値）']) }
        ],
        // 逆指値/価格条件
        orderPriceKindListReversePriceLimit: [
          { required: true, trigger: 'change', validator: this.orderPriceKindListReversePriceLimitValidator, message: getMessage('errors.selected', ['価格']) }
        ],
        // 逆指値/指値執行価格
        hiddenOrderPriceReversePriceLimit: [
          { required: true, trigger: 'blur', validator: this.hiddenOrderPriceReversePriceLimitValidator, message: getMessage('errors.required', ['注文単価（逆指値）']) }
        ],
        // 勧誘区分
        kanyuKbn: [
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
        // 英文開示銘柄
        engPubText: [
          { required: true, trigger: 'change', validator: this.engPubTextValidator, message: getMessage('errors.selected', ['英文開示銘柄']) }
        ],
        // 確認項目
        checkInsider: [
          { required: true, trigger: 'change', validator: this.checkInsiderValidator, message: getMessage('errors.selected', ['確認項目']) }
        ]
      },

      sroaVisible: false,
      styleSeparator: {
        height: '20px',
        background: '#ffd985',
        fontWeight: 'bold',
        marginBottom: '10px'
      }
    }
  },
  computed: {
    a003RequestModel() {
      return new IfaForeignMarginTradeNewOrderInputA003RequestModel(this.form)
    },
    a005RequestModel() {
      return new IfaForeignMarginTradeNewOrderInputA005RequestModel(this.form)
    },
    a011RequestModel() {
      return new IfaForeignMarginTradeNewOrderInputA011RequestModel(this.form)
    },
    a012RequestModel() {
      return new IfaForeignMarginTradeNewOrderInputA012RequestModel(this.form)
    },
    a020RequestModel() {
      return new IfaForeignMarginTradeNewOrderInputA020RequestModel(this.form)
    },
    editDisable() {
      return this.form.brandCode === ''
    },
    maxOrderQuantity() {
      if (this.form.tradeCd === '3') {
        const maxValue = Math.min(this.form.tradeUpperLimitQuantity, this.form.quantityAvailableForSale)
        return maxValue || '9999999999'
      } else {
        return this.form.tradeUpperLimitQuantity === '0' ? '9999999999' : this.form.tradeUpperLimitQuantity
      }
    },
    initialStepPrice() {
      console.log(this.form.priceBasicInfo)
      console.log(this.form.priceBasicInfo[0]?.currentPrice)
      const currentPrice = Number(this.form.priceBasicInfo[0]?.currentPrice)
      console.log(currentPrice)
      if (currentPrice !== 0 && !isNaN(currentPrice)) {
        return currentPrice
      }

      const last = Number(this.form.priceBasicInfo[0]?.last)
      console.log(this.form.priceBasicInfo[0]?.last)
      console.log(last)
      if (last !== 0 && !isNaN(last)) {
        return last
      }

      return 0
    },
    dateOptions() {
      const dateList = this.form.periodDate
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
            return dateList[8]
          }
        }]
      }
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    }
  },
  mounted() {
    this.formRef = this.$refs.orderForm
  },
  methods: {
    onShow() {
      this.initialize()
      document.getElementById('IfaForeignMarginTradeNewOrderInputA001').click()
    },
    initialize() {
      this.form = new IfaForeignMarginTradeNewOrderInputFormModel()
      this.bgColor = '#fdf6ec'
      this.showReference = false
      this.orderPriceKindList = []
      this.stopOrderPriceKindList = []
      this.engPubText = '1'
      this.checkInsider = '0'
      this.initialSetUp()
      setTimeout(() => {
        this.$refs['IfaForeignBrandSearch'].clearSearchWord()
        this.$refs['orderForm'].clearValidate()
      }, 500)
    },
    initialSetUp() {
      this.form.tradeCdList = this.$_getCodeList('FOREIGN_STOCK_TRADE_CLASS', 1, 2)
      this.form.importantMatterTypeList = this.$_getCodeList('IMPORTANT_MATTERS_EXPLAIN', 1, 1)
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    stockPriceDisplayA003Pre(searchWord) {
      this.form.tickerBrandCode = searchWord
    },
    stockPriceDisplayA003Res(response) {
      Object.assign(this.form, response.dataList[0])
      this.setOptions(response.dataList[0])
      this.form.periodDate = this.form.periodDate.splice(0, 9)
      if (response.dataList[0].depositType.includes('2')) {
        this.form.depositType = '2'
      } else {
        this.form.depositType = '1'
      }
      this.form.kessaiHoho = this.form.currencyTypeName[0]
      if (!response.dataList[0].periodRadio.includes('0')) {
        this.buttonOptions.today = 'hide'
      }
      if (!response.dataList[0].periodRadio.includes('1')) {
        this.buttonOptions.period = 'hide'
      }
      this.form.periodRadio = ''
      this.form.engJudge = this.form.engPubText
      this.form.engPubText = '1'
      this.changeOrderPriceKindA022(this.form.orderPriceKind)
    },
    updateA005(response) {
      Object.assign(this.form, response.dataList[0])
    },
    orderConfirmA012() {
      this.setOrderPriceKindList()
      document.getElementById('IfaForeignMarginTradeNewOrderInputOrderConfirmA012').click()
    },
    preConfirm() {
      if (this.form.engJudge !== '1') {
        this.a012RequestModel.engPubText = ''
      }
    },
    async responseHandlerA012(response) {
      this.orderInfo = response.dataList[0]
      await this.$nextTick() // 現在の更新を待つためのVueのメソッド
      await this.$refs.ifaForeignMarginTradeNewOrderConfirm.onShow(this.form.tradeCd)
      this.dialogConfirmVisible = true
    },
    periodClearCancelA014() {
      if (this.form.periodRadio === '1') {
        this.form.period = ''
      }
    },
    resetA015() {
      this.form.foreignQuantity = ''
      this.form.orderPriceKind = this.orderPriceKindList[0].value
      this.form.hiddenOrderPrice = ''
      this.form.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice = ''
      this.form.orderPriceKindListReversePriceLimit = ''
      this.form.orderPriceKindListReversePriceLimit = this.stopOrderPriceKindList[0].value
      this.form.hiddenOrderPriceReversePriceLimit = ''
      this.form.periodType = null
      this.form.periodRadio = ''
      this.form.period = ''
      this.form.kanyuKbn = ''
      this.form.receiveOrderType = ''
      this.form.importantMatterType = '1'
      this.form.engPubText = '1'
      this.engPubText = '1'
      this.form.checkInsider = '0'
      this.checkInsider = '0'
      this.form.approximatePositionAmount = ''
      this.$nextTick(() => {
        if (this.$refs['engPubText']) {
          // 英文開示銘柄チェックボックスが表示されている時
          this.$refs['engPubText'].$refs.engPubText.resetField()
        }
        this.$refs['checkInsider'].$refs.checkInsider.resetField()
        this.$refs['orderForm'].clearValidate()
        this.$refs['periodSelector'].resetComponent()
        this.changeOrderPriceKindA022(this.form.orderPriceKind)
      })
    },
    displayReferenceMarginBalanceA019(response) {
      Object.assign(this.form, response.dataList[0])
      this.showReference = true
    },
    approximatePositionAmountA020() {
      this.isRecalculateBtn = true
      this.setOrderPriceKindList()
      document.getElementById('IfaForeignMarginTradeNewOrderInputA020').click()
    },
    approximatePositionAmountA020res(response) {
      Object.assign(this.form, response.dataList[0])
      this.isRecalculateBtn = false
    },
    englishDisclosureBrandAboutLinkA021() {
      const resolvedRoute = this.$router.resolve({ name: 'Ifa-Faq' })
      this.$store.commit('page/setFaqParam', '10004')
      window.open(resolvedRoute.href, '_blank')
      this.$store.commit('page/setFaqParam', '')
    },
    changeOrderPriceKindA022(value) {
      if (this.buttonOptions.period !== 'hide') {
        if (value === '2') {
          this.buttonOptions.period = 'disabled'
        } else {
          this.buttonOptions.period = ''
          this.$refs['periodSelector'].resetComponent()
        }
      }
      this.$nextTick(() => {
        if (value === '2' && this.$refs['periodSelector']) {
          this.form.periodType = false
          this.$refs['periodSelector'].periodTypeInput = this.form.periodType
          this.$refs['periodSelector'].$emit('update:periodType', this.form.periodType)
        } else {
          this.$refs['periodSelector'].resetComponent()
        }
      })
    },
    setOptions(dataList) {
      if (dataList.orderPriceKindList.includes('1')) {
        this.orderPriceKindList.push({ value: '1', label: this.$_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', '2', '1') })
      }
      if (dataList.orderPriceKindList.includes('2')) {
        this.orderPriceKindList.push({ value: '2', label: this.$_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', '2', '2') })
      }
      if (dataList.orderPriceKindList.includes('3') || dataList.orderPriceKindList.includes('4')) {
        this.orderPriceKindList.push({ value: '3', label: this.$_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', '2', '3') })
        if (dataList.orderPriceKindList.includes('3')) {
          this.stopOrderPriceKindList.push({ value: '3', label: this.$_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', '4', '3') })
        }
        if (dataList.orderPriceKindList.includes('4')) {
          this.stopOrderPriceKindList.push({ value: '4', label: this.$_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', '4', '4') })
        }
      }
      this.form.orderPriceKind = this.orderPriceKindList[0].value
      this.form.orderPriceKindListReversePriceLimit = this.stopOrderPriceKindList[0].value
    },
    setOrderPriceKindList() {
      if (this.form.orderPriceKind === '3') {
        this.form.orderPriceKindList = this.form.orderPriceKindListReversePriceLimit
      } else {
        this.form.orderPriceKindList = this.form.orderPriceKind
      }
    },
    backClick() {
      if (this.disabledForm === true && this.disabledOrderBtn === false && this.disabledConfirmBtn === true) {
        this.disabledForm = false
        this.disabledConfirmBtn = false
        this.disabledOrderBtn = true
      } else if (this.disabledForm === true && this.disabledOrderBtn === true && this.disabledConfirmBtn === true) {
        this.$router.push({
          name: 'WholePortal',
          path: '/wholePortal'
        })
      } else {
        this.$router.push({
          name: 'WholePortal',
          path: '/wholePortal'
        })
      }
    },

    // 取引種別が選択/変更された
    changeTradeType() {
      // 取引種別が 信新買 の場合
      if (this.form.tradeCd === '2') {
        this.bgColor = '#fef0f0'
        this.form.marginDueDate = '0'
      // 取引種別が 信新売 の場合
      } else if (this.form.tradeCd === '3') {
        this.bgColor = '#ecf5ff'
        this.form.marginDueDate = '1'
      }
    },
    setEngPubText(value) {
      this.form.engPubText = value[0]
    },
    setCheckInsider(value) {
      this.form.checkInsider = value[0]
    },
    setPeriodRadio(periodType) {
      if (periodType) {
        this.form.periodRadio = '1'
      } else {
        this.form.periodRadio = '0'
      }
    },
    // ダイアログ：『戻る』
    handleCloseModal(emitBack) {
      if (emitBack) {
        this.dialogFinish = false
        this.$emit('back')
      } else {
        this.form.structuredBonds = false
        this.form.solicitation = false
        this.form.transfer = false
        this.form.shortTermSale = false
        this.form.informationDelivered = true
        this.form.documentDelivered = true
        this.dialogConfirmVisible = false
      }
    },

    // 注文一覧画面に遷移
    handleMoveToOrderList() {
      this.$refs['IfaForeignBrandSearch'].resetAll()
      this.dialogFinish = false
      // 注文一覧画面に遷移
      this.$_startShowMenu('SUB0202_0104')
    },
    // 注文完了画面に遷移
    async handleOrderFinish(response) {
      this.confirmResponseData = response
      this.dialogConfirmVisible = false
      this.dialogFinish = true
    },
    // 保証金振替画面へ遷移
    guaranteeTransfer() {
      this.$emit('show-tab-by-name', 'tab-foreign-stocks:ifa-foreign-stock-credit-guarantee-transfer-order')
    },

    // バリデーション
    foreignQuantityValidator(rule, value, callback) {
      if (value === '' || parseInt(value) === 0) {
        callback(new Error())
        return
      }
      callback()
    },
    hiddenOrderPriceValidator(rule, value, callback) {
      if (this.form.orderPriceKind === '1' && (value === '' || !+value)) {
        callback(new Error())
        return
      }
      callback()
    },
    orderInputAreaPriceTermsreversePriceLimitStopOrderPriceValidator(rule, value, callback) {
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
    hiddenOrderPriceReversePriceLimitValidator(rule, value, callback) {
      if (this.form.orderPriceKind === '3' &&
          this.form.orderPriceKindListReversePriceLimit === '3' &&
          (value === '' || !+value)) {
        callback(new Error())
        return
      }
      callback()
    },
    engPubTextValidator(rule, value, callback) {
      if (!this.isRecalculateBtn) {
        if (this.form.engPubText === '1') {
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
    isCheckValidator(rule, value, callback) {
      // 選択されていない場合はエラー
      if (!this.isRecalculateBtn) {
        if (!value) {
          callback(new Error())
          return
        }
      }
      callback()
    },
    getIDforTopElement(tradeType) {
      if (tradeType === 0) {
        return this.form.tradeType === '' || this.form.tradeType === '0' ? 'ff1' : 'x1'
      } else if (tradeType === 1) {
        return this.form.tradeType === '1' ? 'ff1' : 'x1'
      }
      return 'x1'
    }
  }
}
</script>

<style lang="scss">
@import '@/styles/foreignStockOrder.scss';
@import '@/styles/orderStatusList.scss';
</style>

<style scoped lang="scss">
:deep(.el-card) {
  width: 100%;
}
.__right {
  text-align: right;
  padding-right: 0.5rem;
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
:deep(.date-picker) .el-form-item__content {
  display: flex;
}
.menu-container {
  height: 100%;
}
.menu-pane {
  width: 100%;
  height: 50%;
  border: 1px solid black;
  margin-top: -1px;
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
.top-card {
  background-color: #eee;
  margin-bottom: 0.5rem;
  width: 100%;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
.links > div {
  margin-left: 30px;
  &:first-child {
    margin-left: 50px;
  }
}
.clickable {
  cursor: pointer;
  color: #092987;
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
</style>
