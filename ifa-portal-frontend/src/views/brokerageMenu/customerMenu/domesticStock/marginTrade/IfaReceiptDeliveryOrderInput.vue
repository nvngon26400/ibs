<template>
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="caption_card">
      <el-card style="background-color: #eee; margin-bottom: 0.5rem;">
        <el-row
          style="display: flex; padding: 0 1rem"
        >
          <el-col :span="19">
            <!-- 銘柄検索 -->
            <ifa-brand-search
              ref="ifaReceiptDeliveryOrderInputBrandSearch"
              :is-code-lock="true"
              :trading-type="'1'"
              :market-list="newOpenMarket"
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
              small=""
              action-id="SUB0202_0208-02#A001"
              action-type="requestAction"
              :request-model="IfaStockDetailInfoA001RequestModel"
              @response-handler="responseHandlerDetailDisplayA002"
            ></ifa-button>
            <ifa-button
              id="btnUpdate"
              text="更新"
              :small="true"
              icon="RefreshRight"
              action-type="requestAction"
              action-id="SUB0202_0212-08_1#A003"
              :request-model="IfaReceiptDeliveryOrderInputA003RequestModel"
              @response-handler="updateA003($event)"
            ></ifa-button>
          </el-col>
        </el-row>
        <!-- 時価情報 -->
        <ifa-brand-price-info
          ref="ifaReceiptDeliveryOrderInputBrandPriceInfo"
          :brand-code="form.brandCode"
          :market="form.newOpenMarket"
          @change="handleChangePrice"
        ></ifa-brand-price-info>
      </el-card>

      <el-row>
        <el-card
          v-show="form.openTradeKbn === '8'"
          style="background-color: #eee; margin-bottom: 0.5rem;"
        >
          <div class="info-content">
            <div class="info_xs">
              <span class="info-item__header __left">現引可能額</span>
            </div>
            <div style="width: 1000px">
              <el-row :gutter="20">
                <el-col :span="5">
                  <div class="info_xs">
                    <div class="info-item__header __right">{{ form.deliveryDateT2 ? $_getFormattedDateValue(form.deliveryDateT2) : '----/--/--' }}(2営業日後)</div>

                  </div>
                </el-col>
                <el-col :span="5">
                  <div class="info_xs">
                    <div class="info-item__header __right">{{ form.deliveryDateT3 ? $_getFormattedDateValue(form.deliveryDateT3) : '----/--/--' }}(3営業日後)</div>
                  </div>
                </el-col>
              </el-row>

              <el-row
                :gutter="20"
                style="padding-top: 0.5rem;"
              >
                <el-col :span="5">
                  <div class="info_xs">
                    <div class="info-item__value __right">{{ form.cashOnDeliveryT2 ? `${$_out($_withCommaInteger(form.cashOnDeliveryT2))}円`: '-' }}</div>
                  </div>
                </el-col>
                <el-col :span="5">
                  <div class="info_xs">
                    <div class="info-item__value __right">{{ form.cashOnDeliveryT3 ? `${$_out($_withCommaInteger(form.cashOnDeliveryT3))}円`: '-' }}</div>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-card>
      </el-row>

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
            label-width="180px"
            label-position="left"
          >

            <el-row :gutter="20">
              <!-- フォーム: 取引種別 -->
              <el-row class="form-area__section">
                <el-col :span="18">
                  <el-form-item
                    label="取引種別"
                    prop="tradeType"
                    class="label-style"
                  >
                    <span v-if="form.openTradeKbn">
                      <ifa-text
                        code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                        :style="{'color': fontColor}"
                        style="font-weight:bold"
                        :code-key="form.openTradeKbn"
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
                    action-type="originalAction"
                    @app-action-handler="resetForm('form')"
                  ></ifa-button>
                </el-col>
              </el-row>

              <!-- 売却可能数量 -->
              <el-row class="form-area__section">
                <el-form-item
                  class="label-style"
                  label="注文可能数量"
                >
                  <span>
                    <span style="margin-left:0.2rem">{{ form.maxOrderableQuantity ? `${$_out($_withCommaInteger(form.maxOrderableQuantity))}株`: '-' }}</span>

                  </span>
                </el-form-item>
              </el-row>

              <!-- フォーム: 数量 -->
              <el-row class="form-static-area__section">
                <div
                  style="flex: 0 0 645px"
                >
                  <ifa-input-quantity
                    id="quantity"
                    v-model="form.quantity"
                    label="数量"
                    prop="quantity"
                    :min="form.quantityTradeUnit"
                    :max="form.maxOrderableQuantity"
                    :initial-step="form.quantityTradeUnit"
                    :step="form.quantityTradeUnit"
                    :support="true"
                    unit="株"
                    placeholder=" "
                    style="width: 200px;"
                    class="form-area__input-number"
                    :domain="IfaStocks15DomainModel"
                    width="10px;"
                  >
                  </ifa-input-quantity>
                </div>
                <el-col
                  class="el-form-item"
                  style="flex: 1 0 300px"
                >
                  <span>売買単位：{{ form.quantityTradeUnit ? `${$_out($_withCommaInteger(form.quantityTradeUnit))}株`: '-' }} </span>
                </el-col>
              </el-row>

              <!-- フォーム: 信用取引区分 -->
              <el-row class="form-static-area__section">
                <el-form-item
                  label="信用取引区分"
                  prop="marginTransactionType"
                  class="label-style"
                >
                  <span>{{ $_out(form.paymentDeadlineCalculation) }}</span>
                </el-form-item>
              </el-row>
              <!-- フォーム: 特定・一般区分 -->
              <el-row class="form-static-area__section">
                <el-form-item
                  label="預り区分"
                  prop="accountType"
                  class="label-style"
                >
                  <ifa-input-radio
                    v-model="form.accountType"
                    code-list-id="DOMESTIC_DEPOSIT_TYPE"
                    :disp-pattern="customerInfo.specificAccountType === '1' || customerInfo.specificAccountType === '2' ? 2 : 9"
                    :select-pattern="customerInfo.specificAccountType === '1' || customerInfo.specificAccountType === '2' ? 3 : 6"
                    style="width: 165px;"
                  ></ifa-input-radio>
                </el-form-item>
              </el-row>
              <!-- フォーム: 建市場 -->
              <el-row class="form-static-area__section">
                <el-form-item
                  label="建市場"
                  prop="newOpenMarket"
                  class="label-style"
                >
                  <span v-if="form.newOpenMarket">
                    <ifa-text
                      code-list-id="NEW_MARKET"
                      :code-key="form.newOpenMarket"
                      :disp-pattern="1"
                    ></ifa-text>
                  </span>
                  <span v-else>-</span>
                </el-form-item>
              </el-row>
              <!-- フォーム: 新規建日 -->
              <el-row class="form-static-area__section">
                <el-form-item
                  class="label-style"
                  label="新規建日"
                  prop="constructionDate"
                >
                  <span>{{ $_out($_getFormattedDateValue(form.constructionDate)) }}</span>
                </el-form-item>
              </el-row>
              <!-- フォーム: 親株新規約定日 -->
              <el-row class="form-static-area__section">
                <el-form-item
                  class="label-style"
                  label="親株新規約定日"
                  prop="parentStockConstructionDate"
                >
                  <span>{{ $_out($_getFormattedDateValue(form.parentStockConstructionDate)) }}</span>
                </el-form-item>
              </el-row>
              <!-- フォーム: 建単価 -->
              <el-row class="form-static-area__section">
                <el-form-item
                  class="label-style"
                  label="建単価"
                  prop="newPrice"
                >
                  <div>{{ form.newPrice ? `${$_out($_withCommaNoneZeroPadding(form.newPrice, 2))}円`: '-' }}</div>
                </el-form-item>
              </el-row>
              <!-- フォーム: 建玉No -->
              <el-row class="form-static-area__section">
                <el-form-item
                  class="label-style"
                  label="建玉No"
                  prop="newOpenInterestNumber"
                >
                  <span>{{ $_out($_zeroPadding(form.newOpenInterestNumber, 5)) }}</span>
                </el-form-item>
              </el-row>

              <!-- フォーム: 勧誘区分 -->
              <el-row class="form-static-area__section">
                <el-form-item
                  class="label-style"
                  label="勧誘区分"
                  prop="kanyuKbn"
                >
                  <ifa-input-select
                    v-model="form.kanyuKbn"
                    code-list-id="INVITATION_TYPE"
                    :disp-pattern="1"
                    :select-pattern="1"
                    style="width: 200px;"
                  ></ifa-input-select>
                </el-form-item>
              </el-row>

              <!-- フォーム: 受注方法 -->
              <el-row class="form-static-area__section">
                <el-form-item
                  class="label-style"
                  label="受注方法"
                  prop="receiveOrderType"
                >
                  <ifa-input-select
                    v-model="form.receiveOrderType"
                    code-list-id="ORDER_METHOD_TYPE"
                    :disp-pattern="1"
                    :select-pattern="1"
                    style="width: 200px;"
                  ></ifa-input-select>
                </el-form-item>
              </el-row>

              <!-- フォーム: 重要事項確認 -->
              <el-row class="form-static-area__section">
                <el-form-item
                  class="label-style"
                  label="確認項目"
                  prop="insiderConfirmCheckBox"
                >
                  <ifa-input-check
                    v-model="form.insiderConfirmCheckBox"
                    code-list-id="INSIDER_CONFIRM"
                    :select-pattern="2"
                    :disp-pattern="3"
                  ></ifa-input-check>
                </el-form-item>
              </el-row>
            </el-row>
            <!-- フォーム: 注文確認/リセット -->
            <div
              class="form-button__wrapper"
              style="margin-top:1rem"
            >
              <ifa-button
                id="btnConfirm"
                text="注文確認"
                color="primary"
                name="btnConfirm"
                action-type="requestAction"
                action-id="SUB0202_0212-08_1#A006"
                :form="formRef"
                :request-model="IfaReceiptDeliveryOrderInputA006RequestModel"
                @response-handler="orderConfirmA006($event)"
              ></ifa-button>
            </div>

          </el-form>
        </el-card>

        <!-- ダイアログ -->
        <ifa-stock-detail-info
          :is-visible="displayStockBoard"
          :form-data="detailInfo"
          @close-modal="displayStockBoard = false"
          @price-select="updateNum"
        ></ifa-stock-detail-info>

        <ifa-receipt-delivery-order-confirm
          ref="IfaReceiptDeliveryOrderConfirm"
          :is-visible="dialogConfirmVisible"
          :form-data="orderInfo"
          :customer-info="customerInfo"
          @close-modal="handleCloseModal(false)"
          @order-finish="handleOrderFinish"
        ></ifa-receipt-delivery-order-confirm>

        <ifa-receipt-delivery-order-complete
          :is-visible="dialogCompleteVisible"
          :form-data="IfaReceiptDeliveryOrderConfirmResponseData"
          :customer-info="customerInfo"
          @close-modal="handleCloseModal(true); resetForm('form')"
          @move-to-order-list="handleMoveToOrderList"
        ></ifa-receipt-delivery-order-complete>

        <!--初期化-->
        <ifa-requester
          id="receiptDeliveryOrderInputX001"
          action-id="SUB0202_0212-08_1#X001"
          action-type="requestAction"
          :request-model="IfaReceiptDeliveryOrderInputX001RequestModel"
          @response-handler="responseHandlerInitializeX001($event)"
          @response-error-handler="responseErrorHandlerInitializeX001($event)"
        ></ifa-requester>
      </el-row>
    </div>
  </div>
</template>

<script>
import IfaBrandSearch from '@/components/SearchCondition/IfaBrandSearch'
import IfaBrandPriceInfo from '@/components/Info/IfaBrandPriceInfo'
import IfaReceiptDeliveryOrderConfirm from './IfaReceiptDeliveryOrderConfirm'
import IfaReceiptDeliveryOrderComplete from './IfaReceiptDeliveryOrderComplete'
import IfaStockDetailInfo from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/IfaStockDetailInfo'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import IfaStocks15DomainModel from '@/resource/domain/IfaStocks15DomainModel.json'
import { IfaReceiptDeliveryOrderInputX001RequestModel } from './js/IfaReceiptDeliveryOrderInputX001RequestModel.js'
import { IfaReceiptDeliveryOrderInputA003RequestModel } from './js/IfaReceiptDeliveryOrderInputA003RequestModel.js'
import { IfaReceiptDeliveryOrderInputA006RequestModel } from './js/IfaReceiptDeliveryOrderInputA006RequestModel.js'
import { IfaReceiptDeliveryOrderInputFormModel } from './js/IfaReceiptDeliveryOrderInputFormModel.js'
import { IfaStockDetailInfoA001RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/js/IfaStockDetailInfoA001RequestModel'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    IfaBrandSearch,
    IfaBrandPriceInfo,
    IfaReceiptDeliveryOrderConfirm,
    IfaReceiptDeliveryOrderComplete,
    IfaStockDetailInfo,
    screenTitle
  },
  props: {
    params: { type: Object, required: true }
  },
  emits: ['initializeError'],
  data() {
    return {
      IfaReceiptDeliveryOrderConfirmResponseData: {},
      formRef: {},
      newOpenMarket: [{ key: '', value: '' }],
      IfaStocks15DomainModel: IfaStocks15DomainModel,
      form: new IfaReceiptDeliveryOrderInputFormModel(),
      source: 'other',
      tradingType: '',
      dialogConfirmVisible: false,
      dialogCompleteVisible: false,
      sroaVisible: false,
      qualifiedInstitutionalInvestor: '',
      displayStockBoard: false,
      fontColor: '#00847F',
      priceInfo: {},
      bgColor: '#ecf5ff',
      detailInfo: {},
      rules: {
        quantity: [
          { required: true, trigger: 'blur', validator: this.quantityValidator, message: getMessage('errors.required', ['数量']) }
        ],
        accountType: [
          { required: true, trigger: 'change', message: getMessage('errors.selected', ['預り区分']) }
        ],
        insiderConfirmCheckBox: [
          { required: true, trigger: 'change', validator: this.insiderConfirmCheckBoxValidator, message: getMessage('errors.selected', ['確認項目']) }
        ],
        kanyuKbn: [
          { required: true, message: getMessage('errors.selected', ['勧誘区分']), trigger: 'change' }
        ],
        receiveOrderType: [
          { required: true, message: getMessage('errors.selected', ['受注方法']), trigger: 'change' }
        ]
      },
      orderInfo: {}

    }
  },
  computed: {
    IfaReceiptDeliveryOrderInputX001RequestModel() {
      return new IfaReceiptDeliveryOrderInputX001RequestModel(this.params)
    },
    IfaReceiptDeliveryOrderInputA003RequestModel() {
      return new IfaReceiptDeliveryOrderInputA003RequestModel(this.form)
    },
    IfaReceiptDeliveryOrderInputA006RequestModel() {
      return new IfaReceiptDeliveryOrderInputA006RequestModel(this.form)
    },
    // 株式詳細情報 A001リクエストモデル
    IfaStockDetailInfoA001RequestModel() {
      return new IfaStockDetailInfoA001RequestModel(
        {
          brandCode: this.form.brandCode,
          market: this.form.newOpenMarket
        }
      )
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    }
  },
  methods: {
    changeTradeType() {
      // 取引種別が 現引 の場合
      if (this.form.openTradeKbn === '8') {
        this.bgColor = '#fef0f0'
        this.fontColor = '#E5004C'
        // 取引種別が 現渡 の場合
      } else {
        this.bgColor = '#ecf5ff'
        this.fontColor = '#00847F'
      }
    },
    updateA003(response) {
      Object.assign(this.form, response.dataList[0])
      this.$refs['ifaReceiptDeliveryOrderInputBrandPriceInfo'].updateRequest()
      this.changeTradeType()
    },
    responseErrorHandlerInitializeX001(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    onShow() {
      // 初期化
      this.$nextTick(() => {
        document.getElementById('receiptDeliveryOrderInputX001').click()
        this.formRef = this.$refs.form
      }
      )
    },
    responseHandlerInitializeX001(data) {
      // ⑧画面の初期表示を行う
      Object.assign(this.form, data.dataList[0])
      // 新規売買区分に応じた背景色・フォントの設定
      this.changeTradeType()

      // IfaBrandSearchのプロパティ：対象市場リストのデータ型を合わせ配列を設定
      this.newOpenMarket[0].key = this.IfaReceiptDeliveryOrderInputX001RequestModel.newOpenMarket
      // ⑨銘柄検索エリア.銘柄検索を初期化する。
      this.$refs['ifaReceiptDeliveryOrderInputBrandSearch'].resetAll()
      this.$refs['ifaReceiptDeliveryOrderInputBrandSearch'].handleRowClick(data.dataList[0])
    },
    async orderConfirmA006(response) {
      this.orderInfo = response.dataList[0]
      await this.$nextTick() // 現在の更新を待つためのVueのメソッド
      await this.$refs.IfaReceiptDeliveryOrderConfirm.onShow()
      this.dialogConfirmVisible = true
    },

    // 注文完了画面に遷移
    async handleOrderFinish(response) {
      this.IfaReceiptDeliveryOrderConfirmResponseData = response
      this.dialogConfirmVisible = false
      this.dialogCompleteVisible = true
    },
    // 注文状況一覧画面に遷移
    handleMoveToOrderList() {
      this.resetForm('form')
      this.dialogCompleteVisible = false
      this.$_startShowMenu('SUB0202_0104')
    },
    // 注文確認画面 > 戻るボタン押下時
    async handleCloseModal(moveToSummary) {
      this.dialogConfirmVisible = false
      this.dialogCompleteVisible = false
      this.sroaVisible = false

      if (moveToSummary) {
        // 建玉一覧サマリー画面へ遷移
        this.$_startShowMenu('SUB0202_010202-01')
      }
    },
    // 株式詳細情報のデータをセット
    responseHandlerDetailDisplayA002(response) {
      this.detailInfo = Object.assign(this.detailInfo, response.dataList[0])
      this.displayStockBoard = true
    },
    // 銘柄検索(子コンポーネント)からのイベント処理
    handleChange(param) {
      this.resetForm('form')
      this.$refs['ifaReceiptDeliveryOrderInputBrandPriceInfo'].updateRequest()
    },
    // 銘柄時価情報(子コンポーネント)からのイベント処理
    handleChangePrice(param) {
      this.form.quantityTradeUnit = param.unit
    },
    // // 数量のバリデーションチェック処理
    quantityValidator(rule, value, callback) {
      if (value === '' || parseInt(value) === 0) {
        callback(new Error())
        return
      }
      callback()
    },
    // 確認項目のバリデーションチェック処理
    insiderConfirmCheckBoxValidator(rule, value, callback) {
      if (!Number(this.form.insiderConfirmCheckBox)) {
        callback(new Error())
        return
      }
      callback()
    },
    // 板情報から価格を選択したときの処理
    updateNum(value) {
      // 株式詳細情報のポップアップを非表示にする
      this.displayStockBoard = false
    },
    // リセット処理
    resetForm(formName) {
      this.form.quantity = ''
      this.form.accountType = ''
      this.form.insiderConfirmCheckBox = ''
      this.form.kanyuKbn = ''
      this.form.receiveOrderType = ''
      this.$refs[formName]?.clearValidate()
    }
  }
}
</script>

<style lang="scss" scoped>
@import '@/styles/orderStatusList.scss';

:deep(.el-tabs_full_content) .el-tabs__content {
  padding: 0;
}
.info-content {
  display: flex;
  margin-left: 4rem;
  padding-top: 0.5rem;
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
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.form-area__section {
  border-bottom: 1px solid #eee;
  margin: 0.5rem 0 0 0;
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
  padding-right: 1rem;
  text-align: right;
}
.form-area__input-number {
  width: 18rem;
}
:deep(.label-style) .el-form-item__label {
  font-weight: bold;
  padding: 0.2rem 4rem 0.2rem 8rem;
}
:deep(.el-form-item__content) {
  margin: 0 0 0 0;
  width: 72%;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
</style>
