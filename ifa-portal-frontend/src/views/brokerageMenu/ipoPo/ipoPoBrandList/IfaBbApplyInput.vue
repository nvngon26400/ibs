<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :close-on-click-modal="false"
      :show-close="false"
      width="1444px"
      :before-close="onDialogClose"
      @open="openDialog"
    >
      <!-- 戻るボタン -->
      <el-row>
        <ifa-button
          class="form-button__wrapper"
          color="secondary"
          text="戻る"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-row>
      <!-- BB申込  -->
      <caption-card
        caption="BB申込入力"
        text-size="20px"
        text-color="#0058a2"
        background-color="Menu"
      >
        <el-form
          ref="form"
          label-position="right"
          label-width="208px"
          :model="form"
          :inline="true"
          :rules="rules"
        >
          <el-row>
            <!-- 銘柄情報 -->
            <el-row class="info_section">
              <el-row class="section_title">
                <div><label>銘柄情報</label></div>
              </el-row>
              <el-card
                style="padding: 15px 0px 0px 20px; box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px; background-color: #ccc;"
                class="buy-background-color_card"
              >
                <el-row
                  class="brand_info"
                  justify="space-evenly"
                >
                  <el-col v-if="!visible.brandNameInput">
                    <el-form-item label="銘柄">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.brand) }}</span>
                    </el-form-item>
                  </el-col>

                  <el-col v-else-if="visible.brandNameInput">
                    <ifa-input-select
                      v-model="form.brandCode"
                      style="width: 400px"
                      prop="brandCode"
                      :original-list="brandList"
                      :clearable="false"
                      label="銘柄"
                      code-list-id="original"
                      :required="true"
                      label-class="must-input brand-code"
                      @change="brandSelect"
                    >
                    </ifa-input-select>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="brand_info"
                  justify="space-evenly"
                >
                  <el-col :span="9">
                    <el-form-item label="申込単位">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.applyUnit) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="仮条件">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.assumeCondition) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7"></el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="brand_info"
                  justify="space-evenly"
                >
                  <el-col :span="9">
                    <el-form-item label="BB申込期間">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.bookBuildingApplyPeriod) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col
                    :span="8"
                  >
                    <el-form-item label="抽選日">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.lotDate) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="7"></el-col>
                </el-row>
                <el-row v-if="form.periodUpdateMessage">
                  <el-col>
                    <span style="white-space: pre; color: red; font-weight: bold">{{ form.periodUpdateMessage }}</span>
                  </el-col>
                </el-row>
                <el-row v-if="form.priceUpdateMessage">
                  <el-col>
                    <span style="white-space: pre; color: red; font-weight: bold;">{{ form.priceUpdateMessage }}</span>
                  </el-col>
                </el-row>
              </el-card>
            </el-row>
            <!-- 顧客情報 -->
            <el-row class="info_section">
              <el-row class="section_title">
                <div><label>顧客情報</label></div>
              </el-row>
              <el-card
                style="padding: 15px 0px 0px 20px; box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px;"
                class="buy-background-color_card"
              >
                <el-row
                  id="butenCode_accountNumber_row"
                  style="margin-bottom: 0px;"
                >
                  <el-col
                    v-if="!visible.butenAccInput"
                    :span="8"
                  >
                    <el-form-item label="部店－口座番号">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.butenCode) }}-{{ form.accountNumber ? $_zeroPadding(form.accountNumber,7) : '-' }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col
                    v-else-if="visible.butenAccInput"
                    :span="8"
                  >
                    <el-form-item
                      label="部店－口座番号"
                      style="margin-right: 0px"
                      class="must-input buten-account-area"
                      required
                    >
                      <span class="colon">：</span>
                      <ifa-input-text
                        id="butenCode"
                        v-model="form.butenCode"
                        size="small"
                        style="width:55px; margin-bottom: 0px; margin-right: 10px; border-radius: 5px;"
                        input-class="buten-account"
                        :domain="IfaButenCodeDomainModel"
                        prop="butenCode"
                        @blur="sendA004()"
                      >
                      </ifa-input-text>
                      <ifa-input-text
                        id="accountNumber"
                        v-model="form.accountNumber"
                        size="small"
                        input-class="buten-account"
                        :domain="IfaAccountNumberDomainModel"
                        prop="accountNumber"
                        style="width: 117px; margin-bottom: 0px; border-radius: 5px;"
                        @blur="sendA004()"
                      >
                      </ifa-input-text>
                    </el-form-item>
                  </el-col>
                  <el-col
                    v-if="visible.butenAccInput === false"
                    :span="8"
                  >
                    <el-form-item label="顧客名">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.customerName) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col
                    v-else-if="visible.butenAccInput === true"
                    :span="8"
                  >
                    <el-form-item label="顧客名">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.customerName) }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  justify="space-evenly"
                >
                  <el-col :span="8">
                    <el-form-item label="口座開設年月日">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.openAcctDate) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="投資方針">
                      <span class="colon">：</span>
                      <span>{{ investmentPolicy }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="本年の年間裁量配分割当回数">
                      <span class="colon">：</span>
                      <span>{{ form.discretionAlloCount ? `${$_withCommaInteger(form.discretionAlloCount)}回` : '-' }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  justify="space-evenly"
                >
                  <el-col :span="8">
                    <el-form-item label="職業">
                      <span class="colon">：</span>
                      <span>{{ profession }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="投資経験年数（株式現物）">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.stockSpotInvestExperienceYears) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="本年の年間裁量配分可能回数">
                      <span class="colon">：</span>
                      <span>{{ form.discretionAllocateAbleTimes ? `${$_withCommaInteger(form.discretionAllocateAbleTimes)}回` : '-' }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  justify="space-evenly"
                >
                  <el-col :span="8">
                    <el-form-item label="勤務先名">
                      <span class="colon">：</span>
                      <span v-if="form.corporationType === '0'">{{ $_out(form.officeName) }}</span>
                      <span v-else-if="form.corporationType === '1'">-</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="金融資産">
                      <span class="colon">：</span>
                      <span v-if="form.corporationType === '0'">{{ financialAssets }}</span>
                      <span v-else-if="form.corporationType === '1'">-</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="買付余力">
                      <span class="colon">：</span>
                      <span>{{ form.buyingPower ? `${$_withCommaInteger(form.buyingPower)}円` : '-' }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  justify="space-evenly"
                >
                  <el-col :span="8">
                    <el-form-item label="コンプラランク">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.complianceRank) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="預り資産額">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.depositAssetAmount) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8"></el-col>
                </el-row>
                <el-row
                  type="flex"
                  justify="space-evenly"
                >
                  <el-col :span="8">
                    <el-form-item label="電子交付同意">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.edelivAgreementDate) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="目論見書閲覧">
                      <span class="colon">：</span>
                      <span>{{ $_out(form.readTime) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8"></el-col>
                </el-row>
              </el-card>
            </el-row>
          </el-row>
          <!-- BB申込内容(入力フォーム) -->
          <el-row>
            <el-row
              class="info_section"
              style="margin-top: 0px;"
            >
              <el-row class="section_title">
                <div><label>BB申込内容</label></div>
              </el-row>
              <el-card
                style="padding: 15px 0px 0px 20px; box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px;"
                class="buy-background-color_card"
              >
                <el-row class="input_field">
                  <el-col :span="12">
                    <ifa-input-quantity
                      :key="form.sellBuyUnitType"
                      v-model="form.quantity"
                      label="数量"
                      prop="quantity"
                      size="small"
                      support
                      :step="form.unit"
                      :max="9999999999"
                      :min="form.unit"
                      style="width:15.75rem;"
                      :domain="IfaVolume10DomainModel"
                      :unit="form.sellBuyUnitType"
                      :initial-step="form.unit"
                      label-class="must-input"
                      :required="true"
                    >
                    </ifa-input-quantity>
                  </el-col>
                  <el-col :span="12">
                  </el-col>
                </el-row>
                <el-row class="input_field">
                  <el-col :span="13">
                    <ifa-input-check
                      v-model="form.marketOrder"
                      label="価格/ディスカウント率"
                      style="margin-right: 10px"
                      code-list-id="original"
                      :original-list="marketOrderList"
                      :is-button="false"
                      label-class="must-input market-checkbox"
                      :required="true"
                      :disabled="marketOrderDisabled"
                      @change="marketOrderChk"
                    >
                    </ifa-input-check>
                    <!-- 成行チェックボックス=チェックあり非活性（空表示）-->
                    <ifa-input-select
                      v-if="form.issuePriceType === '1'"
                      v-model="price"
                      :disabled="form.marketOrder === '1'"
                      :code-list-id="'original'"
                      :original-list="priceList"
                      :clearable="false"
                      prop="bbDiscountOrBBPriceSelect"
                    >
                    </ifa-input-select>
                    <ifa-input-select
                      v-else
                      v-model="discount"
                      :disabled="form.marketOrder === '1'"
                      :code-list-id="'original'"
                      :original-list="discountList"
                      :clearable="false"
                      prop="bbDiscountOrBBPriceSelect"
                    >
                    </ifa-input-select>
                  </el-col>
                </el-row>
                <el-row class="input_field">
                  <el-col>
                    <el-form-item>
                      <ifa-input-select
                        v-model="form.investorAttributeName"
                        :code-list-id="'original'"
                        :original-list="investorAttributePullDownList"
                        :clearable="false"
                        label="投資家属性"
                        :required="true"
                        style="width: 150px; margin-right: 60px"
                        label-class="must-input"
                      >
                      </ifa-input-select>
                      <el-tag
                        type="danger"
                        style="margin-left:10px;"
                      >※法人の場合は必ず変更してください。</el-tag>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row class="input_field">
                  <el-col>
                    <ifa-input-quantity
                      :key="form.sellBuyUnitType"
                      v-model="form.discretionQuantity"
                      label="裁量希望数量"
                      :unit="form.sellBuyUnitType"
                      size="small"
                      support
                      :step="form.unit"
                      :max="9999999999"
                      :min="discretionQuantityMin"
                      style="width:15.75rem;"
                      :domain="IfaVolume10DomainModel"
                      :initial-step="initialStepDiscretionQuantity"
                      prop="discretionQuantity"
                    >
                    </ifa-input-quantity>
                  </el-col>
                </el-row>
                <el-row class="input_field">
                  <el-col>
                    <ifa-input-radio
                      :key="refreshKey"
                      v-model="form.selectReason"
                      label="裁量選定理由"
                      prop="selectReason"
                      code-list-id="DISCRETION_SELECT_REASON"
                      :disp-pattern="1"
                      :select-pattern="1"
                      :is-button="true"
                      :disabled="selectReasonDisable"
                    >
                    </ifa-input-radio>
                  </el-col>
                </el-row>
                <el-row class="input_field">
                  <el-col>
                    <ifa-input-select
                      v-model="form.kanyuKbn"
                      prop="kanyuKbn"
                      code-list-id="INVITATION_TYPE"
                      :disp-pattern="1"
                      :select-pattern="1"
                      :clearable="false"
                      style="width: 180px"
                      label="勧誘区分"
                      :required="true"
                      label-class="must-input"
                    >
                    </ifa-input-select>
                  </el-col>
                </el-row>
                <el-row class="input_field">
                  <el-col>
                    <ifa-input-select
                      v-model="form.receiveMethod"
                      code-list-id="ORDER_METHOD_TYPE"
                      prop="receiveMethod"
                      :disp-pattern="1"
                      :select-pattern="1"
                      :clearable="false"
                      style="width: 100px"
                      label="受注方法"
                      label-class="must-input"
                      :required="true"
                    >
                    </ifa-input-select>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col>
                    <el-form-item label="備考">
                      <ifa-input-text
                        id="remark400"
                        v-model="form.bbRemark"
                        name="summaryAny"
                        type="textarea"
                        size="small"
                        style="width: 83em;"
                        prop="bbRemark"
                        original-screen-id="SUB0204_01-02_1"
                        :domain="IfaText400DomainModel"
                      >
                      </ifa-input-text>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-card>
            </el-row>
          </el-row>
          <el-row>
            <ifa-button
              text="申込確認"
              action-type="requestAction"
              action-id="SUB0204_01-02_1#A005"
              :form="formRef"
              :request-model="ifaBbApplyInputA005RequestModel"
              :pre-request-handler="saveApplyInput"
              :disabled="!applyConfirm"
              @response-handler="a005ResponseHandler($event)"
              @response-error-handler="responseErrorHandlerA005($event)"
            ></ifa-button>
          </el-row>
        </el-form>
      </caption-card>
    </el-dialog>

    <ifa-bb-apply-confirm
      :is-visible="dialogConfirmVisible"
      :apply-input-form="form"
      @close-modal="handleBack"
      @order-finish="handleOrderFinish"
    ></ifa-bb-apply-confirm>
    <ifa-bb-apply-complete
      :is-visible="dialogFinishVisible"
      :form="form"
      @close-modal="
        handleCloseModal();
        onDialogClose();
      "
    ></ifa-bb-apply-complete>
    <ifa-requester
      id="IfaBbApplyInputInitializeIpoPoListTransitionA001"
      action-type="requestAction"
      action-id="SUB0204_01-02_1#A001"
      :request-model="ifaBbApplyInputA001RequestModel"
      @response-handler="initializeA001ResponseHandler($event)"
      @response-error-handler="responseErrorHandlerA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaBbApplyInputInitializeCustomerListTransitionA002"
      action-type="requestAction"
      action-id="SUB0204_01-02_1#A002"
      :request-model="ifaBbApplyInputA002RequestModel"
      @response-handler="a002ResponseHandler($event)"
      @response-error-handler="responseErrorHandlerA002($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaBbApplyInputBrandChangeA003"
      action-type="requestAction"
      action-id="SUB0204_01-02_1#A003"
      :request-model="ifaBbApplyInputA003RequestModel"
      @response-handler="a003ResponseHandler($event)"
      @response-error-handler="responseErrorHandlerA003($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaBbApplyInputBranchAccountNumberInputA004"
      action-type="requestAction"
      action-id="SUB0204_01-02_1#A004"
      :request-model="ifaBbApplyInputA004RequestModel"
      @response-handler="a004ResponseHandler($event)"
      @response-error-handler="responseErrorHandlerA004($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { getMessage } from '@/utils/errorHandler'
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard'
import IfaBbApplyConfirm from './IfaBbApplyConfirm'
import IfaBbApplyComplete from './IfaBbApplyComplete'
import IfaBrandCode12DomainModel from '@/resource/domain/IfaBrandCode12DomainModel.json'
import IfaVolume10DomainModel from '@/resource/domain/IfaVolume10DomainModel.json'
import IfaText400DomainModel from '@/resource/domain/IfaText400DomainModel.json'
import IfaAccountNumberDomainModel from '@/resource/domain/IfaAccountNumberDomainModel.json'
import IfaButenCodeDomainModel from '@/resource/domain/IfaButenCodeDomainModel.json'
import { IfaBbApplyInputA001RequestModel } from './js/IfaBbApplyInputA001RequestModel.js'
import { IfaBbApplyInputA002RequestModel } from './js/IfaBbApplyInputA002RequestModel.js'
import { IfaBbApplyInputA003RequestModel } from './js/IfaBbApplyInputA003RequestModel.js'
import { IfaBbApplyInputA004RequestModel } from './js/IfaBbApplyInputA004RequestModel.js'
import { IfaBbApplyInputA005RequestModel } from './js/IfaBbApplyInputA005RequestModel.js'
import { IfaBbApplyInputFormModel } from './js/IfaBbApplyInputFormModel.js'
export default {
  components: {
    captionCard,
    IfaBbApplyConfirm,
    IfaBbApplyComplete
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    }
  },
  emits: [
    'open-modal',
    'close-modal'
  ],
  data() {
    return {
      IfaVolume10DomainModel,
      IfaText400DomainModel,
      IfaBrandCode12DomainModel,
      dialogConfirmVisible: false,
      dialogFinishVisible: false,
      IfaAccountNumberDomainModel: IfaAccountNumberDomainModel,
      IfaButenCodeDomainModel: IfaButenCodeDomainModel,

      form: new IfaBbApplyInputFormModel(),
      visible: {
        butenAccInput: true,
        brandNameInput: false
      },
      vmIsVisible: false,
      confirmMessage: false,
      rules: {
        butenCode: [{ required: true, trigger: 'blur', validator: this.butenCodeValidator }],
        accountNumber: [{ required: true, trigger: 'blur', validator: this.accountNumberValidator }],
        bbDiscountOrBBPriceSelect: [{ required: true, trigger: 'change', validator: this.bbDiscountOrBBPriceSelectValidator }],
        selectReason: [{ trigger: 'change', validator: this.selectReasonValidator }]
      },
      price: '', // 価格 + 円
      discount: '', // ディスカウント率　+ %
      refreshKey: '',
      formRef: {},
      applyConfirm: true,
      bbApplyInputContent: {
        price: '',
        quantity: '',
        marketOrder: '0',
        discountRate: '',
        investorAttributeValue: '',
        investorAttributeName: '',
        bbRemark: '',
        discretionQuantity: '',
        selectReason: '',
        kanyuKbn: '',
        receiveMethod: ''
      },
      marketOrderList: [{ key: '0', value: '成行' }, { key: '1', value: '成行' }]
    }
  },
  computed: {
    ifaBbApplyInputA001RequestModel() {
      return new IfaBbApplyInputA001RequestModel(this.form)
    },
    ifaBbApplyInputA002RequestModel() {
      return new IfaBbApplyInputA002RequestModel(this.form)
    },
    ifaBbApplyInputA003RequestModel() {
      return new IfaBbApplyInputA003RequestModel(this.form)
    },
    ifaBbApplyInputA004RequestModel() {
      return new IfaBbApplyInputA004RequestModel(this.form)
    },
    ifaBbApplyInputA005RequestModel() {
      return new IfaBbApplyInputA005RequestModel(this.form)
    },
    // 銘柄リスト
    brandList() {
      const brandList = []
      this.form.brandPullDownList.forEach((item, index) => {
        brandList.push({
          key: this.form.brandCodeList[index],
          value: item
        })
      })
      return brandList
    },
    // 価格リスト
    priceList() {
      const priceList = []
      if (!this.form.startPriceDisplay && !this.form.finishPriceDisplay) {
        return priceList
      }
      const start = +this.form.startPriceDisplay
      const step = +this.form.priceDisplayTick
      const finish = +this.form.finishPriceDisplay
      let temp = finish
      priceList.push({ key: this.$_withCommaNoneZeroPadding(start) + '円', value: this.$_withCommaNoneZeroPadding(start) + '円' })
      let i = 1
      while (start + step * i < finish) {
        temp = start + step * i++
        priceList.push({ key: this.$_withCommaNoneZeroPadding(temp) + '円', value: this.$_withCommaNoneZeroPadding(temp) + '円' })
      }
      priceList.push({ key: this.$_withCommaNoneZeroPadding(finish) + '円', value: this.$_withCommaNoneZeroPadding(finish) + '円' })
      priceList.reverse()
      return priceList
    },
    // ディスカウント率
    discountList() {
      const discountList = []
      if (!this.form.startDiscountRate && !this.form.finishDiscountRate) {
        return discountList
      }
      const start = +this.form.startDiscountRate
      const finish = +this.form.finishDiscountRate
      const step = +this.form.discountRateTick
      let temp = start
      discountList.push({ key: this.$_withCommaNoneZeroPadding(start) + '%', value: this.$_withCommaNoneZeroPadding(start) + '%' })
      let i = 1
      while (start + step * i < finish) {
        temp = start + step * i++
        discountList.push({ key: this.$_withCommaNoneZeroPadding(temp) + '%', value: this.$_withCommaNoneZeroPadding(temp) + '%' })
      }
      discountList.push({ key: this.$_withCommaNoneZeroPadding(finish) + '%', value: this.$_withCommaNoneZeroPadding(finish) + '%' })
      return discountList
    },
    // 裁量希望数量 未入力時初期増加量
    initialStepDiscretionQuantity() {
      const quantity = Number(this.form.quantity)
      if (quantity !== 0 && !isNaN(quantity)) {
        return quantity
      }

      const unit = Number(this.form.unit)
      if (unit !== 0 && !isNaN(unit)) {
        return unit
      }

      return 0
    },
    // 金融資産区分取得する
    financialAssets() {
      return this.form.financialAssetsType ? this.$_out(this.$_getCodeValue('FINANCIAL_ASSETS', 1, this.form.financialAssetsType)) : '-'
    },
    // 職業区分取得する
    profession() {
      return this.form.profession ? this.$_out(this.$_getCodeValue('PROFESSION', 1, this.form.profession)) : '-'
    },
    // 選択理由区分取得する
    selectReasonList() {
      return this.$_getCodeList('DISCRETION_SELECT_REASON', 1, 1)
    },
    // 投資方針区分取得する
    investmentPolicy() {
      return this.form.investmentPolicyType ? this.$_out(this.$_getCodeValue('INVESTMENT_POLICY_TYPE', 1, this.form.investmentPolicyType)) : '-'
    },
    getFirstInvestorAttributeValue() {
      return this.form.investorAttributeValueList && this.form.investorAttributeValueList.length > 0 ? this.form.investorAttributeValueList[0] : ''
    },
    getFirstInvestorAttributePullDown() {
      return this.form.investorAttributePullDownList && this.form.investorAttributePullDownList.length > 0 ? this.form.investorAttributePullDownList[0] : ''
    },
    investorAttributePullDownList() {
      return this.form.investorAttributePullDownList.map((e, i) => { return { key: e, value: e } })
    },
    selectReasonDisable() {
      return this.form.discretionQuantity === '0' || !this.form.discretionQuantity
    },
    discretionQuantityMin() {
      return this.form.discretionQuantity ? this.form.unit : ''
    },
    marketOrderDisabled() {
      return this.form.marketOrderStrikePrice === '0'
    }
  },
  watch: {
    // price(+円)を選択する時、formのpriceを更新
    price(newValue) {
      newValue = newValue.replaceAll(',', '')
      this.form.price = newValue.substring(0, newValue.length - 1)
    },
    // discount(+%)を選択する時、formのdiscountRateを更新
    discount(newValue) {
      this.form.discountRate = newValue.substring(0, newValue.length - 1)
    },
    'form.investorAttributeValueList': {
      deep: true,
      handler(newValue) {
        if (newValue.length) {
          if (this.form['investorAttributeName'] === this.getFirstInvestorAttributePullDown) {
            this.form['investorAttributeValue'] = this.getFirstInvestorAttributeValue
          }
        }
      }
    },
    'form.selectReason'() {
      this.refreshKey = Date.now()
    },
    'form.investorAttributeName'(newValue) {
      this.form['investorAttributeValue'] = this.form.investorAttributeValueList[this.form['investorAttributePullDownList'].findIndex(investorAttribute => investorAttribute === newValue)]
    },
    'form.price'(price) {
      if (this.form.marketOrder === '0') {
        if (parseInt(price) <= parseInt(this.form.finishPriceDisplay) && parseInt(this.form.startPriceDisplay) <= parseInt(price)) {
          this.applyConfirm = true
        } else {
          this.applyConfirm = false
        }
      } else {
        this.applyConfirm = true
      }
    },
    'form.discountRate'(discountRate) {
      if (this.form.marketOrder === '0') {
        if (parseFloat(discountRate) <= parseFloat(this.form.finishDiscountRate) && parseFloat(this.form.startDiscountRate) <= parseFloat(discountRate)) {
          this.applyConfirm = true
        } else {
          this.applyConfirm = false
        }
      } else {
        this.applyConfirm = true
      }
    },
    selectReasonDisable(disable) {
      if (disable) {
        this.form.selectReason = ''
      }
    }
  },
  methods: {
    handleBack(transitionSourceScreen, requsetModel0, requsetModel1) {
      Object.assign(this.form, requsetModel0, requsetModel1)
      // IPO/POの申込入力へ戻る
      if (transitionSourceScreen === 'SUB0204_01-01') {
        document.querySelector('#IfaBbApplyInputInitializeIpoPoListTransitionA001').click()
        document.querySelector('#IfaBbApplyInputBranchAccountNumberInputA004').click()
      } else if (transitionSourceScreen === 'SUB0201_01-01') { // 顧客一覧の申込入力へ戻る
        document.querySelector('#IfaBbApplyInputInitializeCustomerListTransitionA002').click()
        document.querySelector('#IfaBbApplyInputBrandChangeA003').click()
      }
      Object.assign(this.form, this.bbApplyInputContent)
      // 確認画面クローズ
      this.dialogConfirmVisible = false
      this.$emit('open-modal')
    },
    saveApplyInput() {
      Object.keys(this.bbApplyInputContent).forEach(key => {
        this.bbApplyInputContent[key] = this.form[key]
      })
    },
    // BB申込確認
    handleOrderFinish() {
      this.dialogConfirmVisible = false
      this.dialogFinishVisible = true
    },
    // BB申込完了
    handleCloseModal() {
      // 完了画面「BB申込一覧へ」
      this.dialogFinishVisible = false
      this.$_startShowMenu('SUB0204_02', { brandCode12: '' })
    },
    onDialogClose() {
      // 入力画面「戻る」、完了画面「BB申込一覧へ」
      this.$refs['form'].clearValidate()
      this.vmIsVisible = false
      this.clearBbApplyInputContent()
    },
    // 初期化
    init() {
      // 更新form
      this.form = new IfaBbApplyInputFormModel()
      this.discount = ''
      this.price = ''
      this.form.marketOrder = '0'
      // 投資家属性初期値
      this.form['investorAttributeName'] = this.getFirstInvestorAttributePullDown
    },
    // IPO・PO銘柄一覧からデータ受け渡し
    setupFromIpopoList(ui) {
      this.init()
      Object.assign(this.ifaBbApplyInputA001RequestModel, ui)
      this.visible.butenAccInput = true
      this.visible.brandNameInput = false
      document.querySelector('#IfaBbApplyInputInitializeIpoPoListTransitionA001').click()
    },
    openDialog() {
      this.formRef = this.$refs['form']
    },
    // 顧客一覧からデータ受け渡し
    setupFromCustomerList(ui) {
      this.init()
      this.visible.butenAccInput = false
      this.visible.brandNameInput = true
      Object.assign(this.ifaBbApplyInputA002RequestModel, ui)
      document.querySelector('#IfaBbApplyInputInitializeCustomerListTransitionA002').click()
    },
    bbDiscountOrBBPriceSelectValidator(rule, value, callback) {
      // 成り行きがチェックされていないときに価格が選択されていないときはエラー
      if (this.form.marketOrder === '0') {
        if (!value) {
          if (!this.form.price && !this.form.discountRate) {
            callback(getMessage('errors.required', ['価格/ディスカウント率']))
          } else {
            callback()
          }
        } else {
          callback()
        }
      } else {
        // OK
        callback()
      }
    },
    // 銘柄選択した時に銘柄情報、価格/ディスカウント率、投資家属性を取得
    brandSelect(data) {
      if (data) {
        this.clearBbApplyInputContent()
        document.querySelector('#IfaBbApplyInputBrandChangeA003').click()
      }
    },
    // 部店－口座番号フォーカスアウト時
    butenCodeValidator(rule, value, callback) {
      if (!this.form.butenCode.length) {
        callback(getMessage('errors.required', ['部店']))
      } else {
        callback()
      }
    },
    accountNumberValidator(rule, value, callback) {
      if (!this.form.accountNumber.length) {
        callback(getMessage('errors.required', ['口座番号']))
      } else {
        callback()
      }
    },
    // 裁量選定理由のチェック
    selectReasonValidator(rule, value, callback) {
      if ((this.form.discretionQuantity !== '0' && this.form.discretionQuantity) && !this.form.selectReason) {
        callback(getMessage('errors.required', ['裁量選定理由']))
      } else {
        callback()
      }
    },
    // 申込入力内容をクリア
    clearBbApplyInputContent() {
      Object.keys(this.bbApplyInputContent).forEach(key => {
        if (key === 'investorAttributeName') {
          this.bbApplyInputContent[key] = this.getFirstInvestorAttributePullDown
        } else if (key === 'investorAttributeValue') {
          this.bbApplyInputContent[key] = this.getFirstInvestorAttributeValue
        } else if (key === 'marketOrder') {
          this.bbApplyInputContent[key] = '0'
        } else {
          this.bbApplyInputContent[key] = ''
        }
      })
      Object.assign(this.form, this.bbApplyInputContent)
    },
    // 部店コードと口座番号を入力して、リクエスト
    sendA004() {
      if (this.form.accountNumber.length && this.form.butenCode.length && /^\d+$/.test(this.form.accountNumber)) {
        this.clearBbApplyInputContent()
        if (this.form.issuePriceType === '1') {
          this.price = this.$_withCommaNoneZeroPadding(this.form.finishPriceDisplay) + '円'
          this.form.price = this.form.finishPriceDisplay
        } else {
          this.discount = this.$_withCommaNoneZeroPadding(this.form.startDiscountRate) + '%'
          this.form.discountRate = this.form.startDiscountRate
        }
        document.querySelector('#IfaBbApplyInputBranchAccountNumberInputA004').click()
      }
    },
    // 成行チェク
    marketOrderChk(checked) {
      if (checked === '1') {
        this.price = ''
        this.discount = ''
      } else {
        this.discount = this.$_withCommaNoneZeroPadding(this.form.startDiscountRate) + '%'
        this.price = this.$_withCommaNoneZeroPadding(this.form.finishPriceDisplay) + '円'
      }
    },
    // A001Response
    initializeA001ResponseHandler(data) {
      if (data.dataList) {
        Object.keys(data.dataList[0]).forEach(key => {
          this.form[key] = data.dataList[0][key] || ''
        })
      }
      if (this.bbApplyInputContent.marketOrder === '0') {
        if (this.form.issuePriceType === '1') {
          if (this.bbApplyInputContent.price) {
            this.price = this.$_withCommaNoneZeroPadding(this.bbApplyInputContent.price) + '円'
            this.form.price = this.bbApplyInputContent.price
          } else {
            this.price = this.$_withCommaNoneZeroPadding(this.form.finishPriceDisplay) + '円'
            this.form.price = this.form.finishPriceDisplay
          }
        } else {
          if (this.bbApplyInputContent.discountRate) {
            this.discount = this.$_withCommaNoneZeroPadding(this.bbApplyInputContent.discountRate) + '%'
            this.form.discountRate = this.bbApplyInputContent.discountRate
          } else {
            this.discount = this.$_withCommaNoneZeroPadding(this.form.startDiscountRate) + '%'
            this.form.discountRate = this.form.startDiscountRate
          }
        }
      }
      this.form['investorAttributeName'] = this.getFirstInvestorAttributePullDown
      this.vmIsVisible = true
    },
    responseErrorHandlerA001(error) {
      this.$_logWarn('responseErrorHandlerA001-Error', error)
      this.vmIsVisible = false
    },
    a002ResponseHandler(data) {
      if (data.dataList) {
        Object.keys(data.dataList[0]).forEach(key => {
          this.form[key] = data.dataList[0][key] || ''
        })
      }
      this.vmIsVisible = true
    },
    responseErrorHandlerA002(error) {
      this.$_logWarn('responseErrorHandlerA002-Error', error)
      this.vmIsVisible = false
    },
    a003ResponseHandler(data) {
      if (data.dataList) {
        Object.keys(data.dataList[0]).forEach(key => {
          this.form[key] = data.dataList[0][key] || ''
        })
      }
      if (this.bbApplyInputContent.marketOrder === '0') {
        if (this.form.issuePriceType === '1') {
          if (this.bbApplyInputContent.price) {
            this.price = this.$_withCommaNoneZeroPadding(this.bbApplyInputContent.price) + '円'
            this.form.price = this.bbApplyInputContent.price
          } else {
            this.price = this.$_withCommaNoneZeroPadding(this.form.finishPriceDisplay) + '円'
            this.form.price = this.form.finishPriceDisplay
          }
        } else {
          if (this.bbApplyInputContent.discountRate) {
            this.discount = this.$_withCommaNoneZeroPadding(this.bbApplyInputContent.discountRate) + '%'
            this.form.discountRate = this.bbApplyInputContent.discountRate
          } else {
            this.discount = this.$_withCommaNoneZeroPadding(this.form.startDiscountRate) + '%'
            this.form.discountRate = this.form.startDiscountRate
          }
        }
      }
      this.form['investorAttributeName'] = this.getFirstInvestorAttributePullDown
      this.applyConfirm = true
    },
    responseErrorHandlerA003(error) {
      this.$_logWarn('responseErrorHandlerA003-Error', error)
      if (error.returnCode === 'errors.insertDataExist') {
        this.applyConfirm = false
      }
    },
    a004ResponseHandler(data) {
      if (data.dataList) {
        Object.keys(data.dataList[0]).forEach(key => {
          this.form[key] = data.dataList[0][key] || ''
        })
      }
      this.applyConfirm = true
    },
    responseErrorHandlerA004(error) {
      this.$_logWarn('responseErrorHandlerA004-Error', error)
      if (error.returnCode === 'errors.cmn.selectedAccount.outOfService') {
        this.applyConfirm = false
      }
    },
    a005ResponseHandler(data) {
      this.dialogConfirmVisible = true
      if (data.dataList) {
        Object.keys(data.dataList[0]).forEach(key => {
          this.form[key] = data.dataList[0][key] || ''
        })
      }
    },
    responseErrorHandlerA005(error) {
      this.$_logWarn('responseErrorHandlerA005-Error', error)
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/ipopobbInputForm.scss";
/* 部店-口座番号エリア */
:deep(.buten-account-area .el-form-item) {
  vertical-align: top;
}
:deep(.buten-account-area .colon) {
  left: -22px;
}
</style>
