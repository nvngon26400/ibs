<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :close-on-click-modal="false"
      :show-close="false"
      width="1444px"
      @open="bindForm"
    >
      <!-- 戻るボタン -->
      <el-row>
        <ifa-button
          class="form-button__wrapper"
          color="secondary"
          text="戻る"
          padding="100px 10px"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-row>
      <!-- BB訂正・取消  -->
      <caption-card
        caption="BB申込訂正・取消入力"
        text-size="20px"
        text-color="#0058a2"
        background-color="Menu"
      >
        <el-form
          ref="form"
          label-position="right"
          label-width="208px"
          :model="form"
        >
          <el-row>
            <!-- 銘柄情報 -->
            <el-row class="info_section">
              <el-row class="section_title">
                <div><label>銘柄情報</label></div>
              </el-row>
              <el-card
                style="padding-top: 15px; box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px;"
                class="buy-background-color_card"
              >
                <el-row
                  type="flex"
                  class="brand_info"
                  justify="space-around"
                >
                  <el-col
                    :span="9"
                    class="input-col"
                  >
                    <el-form-item label="銘柄"
                                  class="colon"
                    >
                      <span>{{ $_out(form.brand) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8"
                          :offset="1"
                          class="input-col"
                  >
                  </el-col>
                  <el-col :span="6"
                          class="input-col"
                  ></el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="brand_info"
                  justify="space-around"
                >
                  <el-col
                    :span="9"
                    class="input-col"
                  >
                    <el-form-item label="申込単位"
                                  class="colon"
                    >
                      <span>{{ $_out($_withCommaInteger(form.applyUnit)) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8"
                          :offset="1"
                          class="input-col"
                  >
                    <el-form-item label="仮条件"
                                  class="colon"
                    >
                      <span>{{ $_out(form.assumeCondition) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col
                    :span="6"
                    class="input-col"
                  ></el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="brand_info"
                  justify="space-around"
                >
                  <el-col
                    :span="9"
                    class="input-col"
                  >
                    <el-form-item label="BB申込期間"
                                  class="colon"
                    >
                      <span>{{ $_out(form.bookBuildingApplyPeriod) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col
                    :span="8"
                    :offset="1"
                    class="input-col"
                  >
                    <el-form-item label="抽選日"
                                  class="colon"
                    >
                      <span>{{ $_out(form.lotDate) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col
                    :span="6"
                    class="input-col"
                  ></el-col>
                </el-row>
                <el-row v-if="form.periodUpdateMessage">
                  <el-col class="input-col">
                    <span style="white-space: pre; color: red; font-weight: bold">{{ form.periodUpdateMessage }}</span>
                  </el-col>
                </el-row>
                <el-row v-if="form.priceUpdateMessage">
                  <el-col class="input-col">
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
                style="padding-top: 15px; box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px;"
                class="buy-background-color_card"
              >
                <el-row
                  type="flex"
                  class="customer_info"
                  justify="space-around"
                >
                  <el-col
                    :span="8"
                    class="input-col"
                  >
                    <el-form-item label="部店－口座番号"
                                  class="colon"
                    >
                      <span>{{ form.butenCode }}-{{ $_zeroPadding(form.accountNumber,7) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8"
                          :offset="1"
                          class="input-col"
                  >
                    <el-form-item label="顧客名"
                                  class="colon"
                    >
                      <span>{{ $_out(form.customerName) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col
                    :span="7"
                    class="input-col"
                  ></el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="customer_info"
                  justify="space-around"
                >
                  <el-col
                    :span="8"
                    class="input-col"
                  >
                    <el-form-item label="口座開設年月日"
                                  class="colon"
                    >
                      <span>{{ $_out($_getFormattedDateValue(form.openAcctDate)) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8"
                          :offset="1"
                          class="input-col"
                  >
                    <el-form-item label="投資方針"
                                  class="colon"
                    >
                      <span>{{ investmentPolicy }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col
                    :span="7"
                    class="input-col"
                  >
                    <el-form-item label="本年の年間裁量配分割当回数"
                                  class="colon"
                    >
                      <span>{{ form.discretionAlloCount ? `${$_withCommaInteger(form.discretionAlloCount)}回` : '-' }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="customer_info"
                  justify="space-around"
                >
                  <el-col
                    :span="8"
                    class="input-col"
                  >
                    <el-form-item label="職業"
                                  class="colon"
                    >
                      <span>{{ profession }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8"
                          :offset="1"
                          class="input-col"
                  >
                    <el-form-item label="投資経験年数（株式現物）"
                                  class="colon"
                    >
                      <span>{{ $_out(form.stockSpotInvestExperienceYears) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col
                    :span="7"
                    class="input-col"
                  >
                    <el-form-item label="本年の年間裁量配分可能回数"
                                  class="colon"
                    >
                      <span>{{ form.discretionAllocateAbleTimes ? `${$_withCommaInteger(form.discretionAllocateAbleTimes)}回` : '-' }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="customer_info"
                  justify="space-around"
                >
                  <el-col
                    :span="8"
                    class="input-col"
                  >
                    <el-form-item label="勤務先名"
                                  class="colon"
                    >
                      <span>{{ isCorporation ? '-' : $_out(form.officeName) }}</span>

                    </el-form-item>
                  </el-col>
                  <el-col :span="8"
                          :offset="1"
                          class="input-col"
                  >
                    <el-form-item label="金融資産"
                                  class="colon"
                    >
                      <span v-if="isCorporation">-</span>
                      <span v-else-if="form.corporationType === '0'">{{ financialAssets }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col
                    :span="7"
                    class="input-col"
                  >
                    <el-form-item label="買付余力"
                                  class="colon"
                    >
                      <span>{{ form.buyingPower ? `${$_withCommaInteger(form.buyingPower)}円` : '-' }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="customer_info"
                  justify="space-around"
                >
                  <el-col
                    :span="8"
                    class="input-col"
                  >
                    <el-form-item label="コンプラランク"
                                  class="colon"
                    >
                      <span>{{ form.complianceRank }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8"
                          :offset="1"
                          class="input-col"
                  >
                    <el-form-item label="預り資産額"
                                  class="colon"
                    >
                      <span>{{ $_out($_withCommaInteger(form.depositAssetAmount)) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col
                    :span="7"
                    class="input-col"
                  ></el-col>
                </el-row>
                <el-row
                  type="flex"
                  class="customer_info"
                  justify="space-around"
                >
                  <el-col
                    :span="8"
                    class="input-col"
                  >
                    <el-form-item label="電子交付同意"
                                  class="colon"
                    >
                      <span>{{ $_out($_getFormattedDateValue(form.edelivAgreementDate)) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8"
                          :offset="1"
                          class="input-col"
                  >
                    <el-form-item label="目論見書閲覧"
                                  class="colon"
                    >
                      <span>{{ $_out($_getFormattedDateTimeValue(form.readTime,'datetime12')) }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col
                    :span="7"
                    class="input-col"
                  ></el-col>
                </el-row>
              </el-card>
            </el-row>
          </el-row>
        </el-form>
        <el-form
          ref="formInput"
          :model="form"
          :inline="true"
          :rules="rules"
          label-position="right"
          label-width="180px"
        >
          <!-- BB申込内容(入力フォーム) -->
          <el-row
            class="info_section"
            style="margin-top: 0px;"
          >
            <el-row class="section_title">
              <div><label>BB申込内容</label></div>
            </el-row>
            <el-card
              style="box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px;"
              class="buy-background-color_card"
            >
              <el-row class="input_field">
                <el-col
                  :span="12"
                  class="input-col"
                >
                  <ifa-input-quantity
                    id="bbVolume"
                    :key="form.sellBuyUnitType"
                    v-model="form.quantity"
                    label="数量"
                    prop="quantity"
                    size="small"
                    support
                    :step="form.unit"
                    :max="9999999999"
                    :min="form.unit"
                    :unit="form.sellBuyUnitType"
                    :initial-step="form.unit"
                    input-class="price_input"
                    style="width:15.75rem;"
                    :required="true"
                    :domain="IfaStocks10DomainModel"
                    label-class="must-input"
                  >
                  </ifa-input-quantity>
                </el-col>
                <el-col
                  :span="12"
                  class="input-col"
                >
                  <el-form-item
                    label="申込日時"
                    style="margin-left: 10px;"
                  >
                    <span>{{ $_out($_getFormattedDateTimeValue(form.bbCreateDate)) }}</span>
                  </el-form-item>
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
                    :disabled="marketOrderDisabled"
                    label-class="must-input market-checkbox"
                    :required="true"
                    @change="marketOrderChk"
                  >
                  </ifa-input-check>
                  <!-- 成行チェックボックス=チェックあり非活性（空表示）-->
                  <ifa-input-select
                    v-if="form.issuePriceType === '1'"
                    v-model="form.price"
                    :disabled="form.marketOrder === '1'"
                    :code-list-id="'original'"
                    :original-list="priceList"
                    :clearable="false"
                    prop="bbDiscountOrBBPriceSelect"
                    :domain="IfaSelectboxGroupDomainModel"
                  >
                  </ifa-input-select>
                  <ifa-input-select
                    v-else
                    v-model="form.discountRate"
                    :disabled="form.marketOrder === '1'"
                    :code-list-id="'original'"
                    :original-list="discountList"
                    :clearable="false"
                    prop="bbDiscountOrBBPriceSelect"
                    :domain="IfaSelectboxGroupDomainModel"
                  >
                  </ifa-input-select>
                </el-col>
              </el-row>
              <el-row class="input_field">
                <el-col>
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
                </el-col>
              </el-row>
              <el-row class="input_field">
                <el-col>
                  <ifa-input-quantity
                    id="discretionQuantity"
                    :key="form.sellBuyUnitType"
                    v-model="form.discretionQuantity"
                    label="裁量希望数量"
                    :unit="form.sellBuyUnitType"
                    size="small"
                    support
                    :step="form.unit"
                    :max="9999999999"
                    :min="form.unit"
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
                  >
                  </ifa-input-select>
                  <el-tag
                    type="danger"
                    style="margin-left:10px;"
                  >※訂正時は必ず選択してください。</el-tag>
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
                  >
                  </ifa-input-select>
                  <el-tag
                    type="danger"
                    style="margin-left:10px;"
                  >※訂正時は必ず選択してください。</el-tag>
                </el-col>
              </el-row>
              <el-row>
                <el-form
                  ref="remark"
                  :model="form"
                >
                  <el-col>
                    <ifa-input-text
                      id="remark400"
                      v-model="form.bbRemark"
                      name="summaryAny"
                      type="textarea"
                      size="small"
                      style="width: 83em;"
                      prop="bbRemark"
                      original-screen-id="SUB0204_02-02_1"
                      :domain="IfaText400DomainModel"
                      label="備考"
                    >
                    </ifa-input-text>
                  </el-col>
                </el-form>
              </el-row>
            </el-card>
          </el-row>
          <el-row>
            <ifa-button
              text="訂正"
              action-type="requestAction"
              action-id="SUB0204_02-02_1#A002"
              :disabled="correctCancelDisable"
              :request-model="ifaBbApplyCorrectCancelInputA002RequestModel"
              :pre-request-handler="preCorrect"
              :form="$refs.remark"
              @response-handler="correctA002ResponseHandler($event)"
              @response-error-handler="responseErrorHandlerA002($event)"
            ></ifa-button>
            <ifa-button
              text="申込取消"
              action-type="requestAction"
              action-id="SUB0204_02-02_1#A003"
              :disabled="correctCancelDisable"
              :request-model="ifaBbApplyCorrectCancelInputA003RequestModel"
              :pre-request-handler="preCancle"
              :form="$refs.remark"
              @response-handler="cancleA003ResponseHandler($event)"
              @response-error-handler="responseErrorHandlerA003($event)"
            ></ifa-button>
          </el-row>
        </el-form>
      </caption-card>
    </el-dialog>
    <ifa-bb-apply-correct-confirm
      :is-visible="bbCorrect"
      :form="form"
      @close-modal="handleBack"
      @order-finish="handleCorrectFinish"
    ></ifa-bb-apply-correct-confirm>
    <ifa-bb-apply-correct-complete
      :is-visible="bbCorrectFinish"
      :form="confirmForm"
      @close-modal="
        handleCloseModal();
        onDialogClose();
      "
    ></ifa-bb-apply-correct-complete>

    <ifa-bb-apply-cancel-confirm
      :is-visible="bbDelete"
      :form="form"
      @close-modal="handleBack"
      @order-finish="handleDeleteFinish"
    ></ifa-bb-apply-cancel-confirm>
    <ifa-bb-apply-cancel-complete
      :is-visible="bbDeleteFinish"
      :form="confirmForm"
      @close-modal="
        handleCloseModal();
        onDialogClose();
      "
    ></ifa-bb-apply-cancel-complete>
    <ifa-requester
      id="ifaBbApplyCorrectCancelInputInitializeA001"
      action-type="requestAction"
      action-id="SUB0204_02-02_1#A001"
      :request-model="ifaBbApplyCorrectCancelInputA001RequestModel"
      @response-handler="initializeA001ResponseHandler($event)"
      @response-error-handler="responseErrorHandlerA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaBbApplyCorrectCancelInputBackInitializeA005"
      action-type="requestAction"
      action-id="SUB0204_02-02_1#A005"
      :request-model="ifaBbApplyCorrectCancelInputA005RequestModel"
      @response-handler="backInitializeA005($event)"
      @response-error-handler="responseErrorHandlerA005($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard'
import IfaBbApplyCorrectConfirm from './IfaBbApplyCorrectConfirm'
import IfaBbApplyCorrectComplete from './IfaBbApplyCorrectComplete'
import IfaBbApplyCancelConfirm from './IfaBbApplyCancelConfirm'
import IfaBbApplyCancelComplete from './IfaBbApplyCancelComplete'
import IfaStocks10DomainModel from '@/resource/domain/IfaStocks10DomainModel.json'
import IfaVolume10DomainModel from '@/resource/domain/IfaVolume10DomainModel.json'
import IfaText400DomainModel from '@/resource/domain/IfaText400DomainModel.json'
import IfaSelectboxGroupDomainModel from '@/resource/domain/IfaSelectboxGroupDomainModel.json'
import { IfaBbApplyCancelConfirmFormModel } from './js/IfaBbApplyCancelConfirmFormModel'
import { IfaBbApplyCorrectCancelInputA001RequestModel } from './js/IfaBbApplyCorrectCancelInputA001RequestModel'
import { IfaBbApplyCorrectCancelInputA002RequestModel } from './js/IfaBbApplyCorrectCancelInputA002RequestModel'
import { IfaBbApplyCorrectCancelInputA003RequestModel } from './js/IfaBbApplyCorrectCancelInputA003RequestModel'
import { IfaBbApplyCorrectCancelInputA005RequestModel } from './js/IfaBbApplyCorrectCancelInputA005RequestModel'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    captionCard,
    IfaBbApplyCorrectConfirm,
    IfaBbApplyCorrectComplete,
    IfaBbApplyCancelConfirm,
    IfaBbApplyCancelComplete
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    }
  },
  emits: ['open-modal', 'close-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      dialogConfirmVisible: false,
      IfaStocks10DomainModel: IfaStocks10DomainModel,
      IfaVolume10DomainModel: IfaVolume10DomainModel,
      IfaText400DomainModel: IfaText400DomainModel,
      IfaSelectboxGroupDomainModel,
      form: new IfaBbApplyCancelConfirmFormModel(),
      disabled: {
        choseReason: false
      },
      bbCorrect: false,
      bbCorrectFinish: false,
      bbDelete: false,
      bbDeleteFinish: false,
      operationTitle: '',
      operationProcess: '',
      rules: {
        bbDiscountOrBBPriceSelect: [{ required: true, trigger: 'change', validator: this.bbDiscountOrBBPriceSelectValidator }],
        selectReason: [{ trigger: 'change', validator: this.selectReasonValidator }],
        receiveMethod: [{ trigger: 'submit', validator: this.receiveMethodValidator }],
        kanyuKbn: [{ trigger: 'submit', validator: this.kanyuKbnValidator }]
      },
      marketOrderList: [{ key: '0', value: '成行' }, { key: '1', value: '成行' }],
      formRef: {},
      confirmForm: {}
    }
  },
  computed: {
    ifaBbApplyCorrectCancelInputA001RequestModel() {
      return new IfaBbApplyCorrectCancelInputA001RequestModel(this.form)
    },
    ifaBbApplyCorrectCancelInputA002RequestModel() {
      return new IfaBbApplyCorrectCancelInputA002RequestModel(this.form)
    },
    ifaBbApplyCorrectCancelInputA003RequestModel() {
      return new IfaBbApplyCorrectCancelInputA003RequestModel(this.form)
    },
    ifaBbApplyCorrectCancelInputA005RequestModel() {
      return new IfaBbApplyCorrectCancelInputA005RequestModel(this.form)
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
    // 投資方針区分取得する
    investmentPolicy() {
      return this.form.investmentPolicyType ? this.$_out(this.$_getCodeValue('INVESTMENT_POLICY_TYPE', 1, this.form.investmentPolicyType)) : '-'
    },
    // 金融資産区分取得する
    financialAssets() {
      return this.form.financialAssetsType ? this.$_out(this.$_getCodeValue('FINANCIAL_ASSETS', 1, this.form.financialAssetsType)) : '-'
    },
    // 職業区分取得する
    profession() {
      return this.form.professionType ? this.$_out(this.$_getCodeValue('PROFESSION', 1, this.form.professionType)) : '-'
    },
    // 選択理由区分取得する
    selectReasonList() {
      return this.$_getCodeList('DISCRETION_SELECT_REASON', 1, 1)
    },
    isCorporation() {
      return this.form.corporationType === '1'
    },
    selectReasonDisable() {
      return this.form.discretionQuantity === '0' || !this.form.discretionQuantity
    },
    correctCancelDisable() {
      return this.form.complianceRank === 'Z'
    },
    investorAttributePullDownList() {
      return this.form.investorAttributePullDownList.map((e, i) => { return { key: e.investorAttributeName, value: e.investorAttributeName } })
    },
    correctValidateResult() {
      let quantity
      if (Number.isInteger(+this.form.quantity)) {
        if (+this.form.unit <= +this.form.quantity && +this.form.quantity <= 9999999999) {
          quantity = true
        } else {
          quantity = false
        }
      } else {
        quantity = false
      }
      let bbDiscountOrBBPriceSelect
      if (this.form.marketOrder === '0') {
        if (!this.form.price && !this.form.discountRate) {
          bbDiscountOrBBPriceSelect = false
        } else {
          bbDiscountOrBBPriceSelect = true
        }
      } else {
        bbDiscountOrBBPriceSelect = true
      }
      let selectReason
      if ((this.form.discretionQuantity !== '0' && this.form.discretionQuantity) && !this.form.selectReason) {
        selectReason = false
      } else {
        selectReason = true
      }
      return {
        quantity,
        bbDiscountOrBBPriceSelect,
        selectReason,
        receiveMethod: this.form.receiveMethod,
        kanyuKbn: this.form.kanyuKbn
      }
    },
    cancleValidateResult() {
      let quantity
      if (Number.isInteger(+this.form.quantity)) {
        if (+this.form.unit <= +this.form.quantity && +this.form.quantity <= 9999999999) {
          quantity = true
        } else {
          quantity = false
        }
      } else {
        quantity = false
      }
      let bbDiscountOrBBPriceSelect
      if (this.form.marketOrder === '0') {
        if (!this.form.price && !this.form.discountRate) {
          bbDiscountOrBBPriceSelect = false
        } else {
          bbDiscountOrBBPriceSelect = true
        }
      } else {
        bbDiscountOrBBPriceSelect = true
      }
      let selectReason
      if ((this.form.discretionQuantity !== '0' && this.form.discretionQuantity) && !this.form.selectReason) {
        selectReason = false
      } else {
        selectReason = true
      }
      return {
        quantity,
        bbDiscountOrBBPriceSelect,
        selectReason
      }
    },
    marketOrderDisabled() {
      return this.form.marketOrderStrikePrice === '0'
    }
  },
  watch: {
    'form.investorAttributeName': {
      handler(newValue) {
        this.form['investorAttributeValue'] = this.form.investorAttributePullDownList.find(e => e.investorAttributeName === newValue).investorAttributeValue
      },
      flush: 'post'
    },
    'form.discretionQuantity'(newValue) {
      if (!newValue) {
        this.form.selectReason = ''
      }
    }
  },
  methods: {
    preCorrect() {
      this.formRef.clearValidate()
      this.formRef.validateField('bbDiscountOrBBPriceSelect').catch(() => {})
      this.formRef.validateField('selectReason').catch(() => {})
      this.formRef.validateField('quantity').catch(() => {})
      this.formRef.validateField('receiveMethod').catch(() => {})
      this.formRef.validateField('kanyuKbn').catch(() => {})
      this.$refs.remark.validateField('bbRemark').catch(() => {})
      for (const key in this.correctValidateResult) {
        if (!this.correctValidateResult[key]) {
          throw new Error()
        }
      }
      if (this.form.marketOrder === '0') {
        if (this.form.issuePriceType === '1') {
          this.ifaBbApplyCorrectCancelInputA002RequestModel.price = this.form.price.substring(0, this.form.price.length - 1).replaceAll(',', '')
        } else {
          this.ifaBbApplyCorrectCancelInputA002RequestModel.discountRate = this.form.discountRate.substring(0, this.form.discountRate.length - 1)
        }
      }
      this.ifaBbApplyCorrectCancelInputA002RequestModel.selectReason = this.$_getCodeValue('DISCRETION_SELECT_REASON', 1, this.form.selectReason)
      this.ifaBbApplyCorrectCancelInputA002RequestModel.selectReasonBeforeCorrect = this.$_getCodeValue('DISCRETION_SELECT_REASON', 1, this.form.selectReasonBeforeCorrect)
    },
    preCancle() {
      this.formRef.clearValidate()
      this.formRef.validateField('bbDiscountOrBBPriceSelect').catch(() => {})
      this.formRef.validateField('selectReason').catch(() => {})
      this.formRef.validateField('quantity').catch(() => {})
      this.$refs.remark.validateField('bbRemark').catch(() => {})
      for (const key in this.cancleValidateResult) {
        if (!this.cancleValidateResult[key]) {
          throw new Error()
        }
      }
      if (this.form.marketOrder === '0') {
        if (this.form.issuePriceType === '1') {
          this.ifaBbApplyCorrectCancelInputA003RequestModel.price = this.form.price.substring(0, this.form.price.length - 1).replaceAll(',', '')
        } else {
          this.ifaBbApplyCorrectCancelInputA003RequestModel.discountRate = this.form.discountRate.substring(0, this.form.discountRate.length - 1)
        }
      }
      this.ifaBbApplyCorrectCancelInputA003RequestModel.selectReasonBeforeCorrect = this.$_getCodeValue('DISCRETION_SELECT_REASON', 1, this.form.selectReasonBeforeCorrect)
    },
    bindForm() {
      this.formRef = { ...this.$refs.formInput }
    },
    onShow(data) {
      Object.assign(this.form, data)
      document.querySelector('#ifaBbApplyCorrectCancelInputInitializeA001').click()
    },
    // 完了画面クローズ
    handleCloseModal() {
      this.bbCorrectFinish = false
      this.bbDeleteFinish = false
    },
    // 確認画面で戻るボタン押下
    handleBack() {
      this.bbCorrect = false
      this.bbDelete = false
      document.querySelector('#ifaBbApplyCorrectCancelInputBackInitializeA005').click()
      // this.$emit('open-modal')
    },
    // 訂正完了画面オープン
    handleCorrectFinish(confirmForm) {
      this.confirmForm = confirmForm
      this.bbCorrect = false
      this.bbCorrectFinish = true
    },
    // 取消完了画面オープン
    handleDeleteFinish(confirmForm) {
      this.confirmForm = confirmForm
      this.bbDelete = false
      this.bbDeleteFinish = true
    },
    onDialogClose() {
      this.$refs['formInput'].resetFields()
      this.$emit('close-modal')
    },
    // 裁量選定理由のチェック
    selectReasonValidator(rule, value, callback) {
      if ((this.form.discretionQuantity !== '0' && this.form.discretionQuantity) && !this.form.selectReason) {
        callback(getMessage('errors.required', ['裁量選定理由']))
      } else {
        callback()
      }
    },
    receiveMethodValidator(rule, value, callback) {
      if (this.form.receiveMethod) {
        callback()
      } else {
        callback(getMessage('errors.required', ['受注方法']))
      }
    },
    kanyuKbnValidator(rule, value, callback) {
      if (this.form.kanyuKbn) {
        callback()
      } else {
        callback(getMessage('errors.required', ['勧誘区分']))
      }
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
    // 成行チェク
    marketOrderChk(checked) {
      if (checked === '1') {
        this.form.price = ''
        this.form.discountRate = ''
      }
      this.$refs['formInput'].validateField('bbDiscountOrBBPriceSelect')
    },
    initializeA001ResponseHandler(data) {
      this.$_logDebug(data)
      if (data.dataList[0]) {
        Object.keys(data.dataList[0]).forEach(key => {
          this.form[key] = data.dataList[0][key] || ''
        })
      }
      this.form.quantityBeforeCorrect = this.form.quantity
      this.form.marketOrderBeforeCorrect = this.form.marketOrder
      if (this.form.marketOrder === '0') {
        // 価格
        if (this.form.issuePriceType === '1') {
          this.form.priceBeforeCorrect = this.form.priceDiscountRate
          this.form.price = this.$_withCommaNoneZeroPadding(this.form.priceDiscountRate) + '円'
        }
        // ディスカウント率
        if (this.form.issuePriceType === '2') {
          this.form.discountRateBeforeCorrect = this.form.priceDiscountRate
          this.form.discountRate = this.$_withCommaNoneZeroPadding(this.form.priceDiscountRate) + '%'
        }
      }
      this.form.investorAttributeValueBeforeCorrect = this.form.investorAttributeValue
      this.form.discretionQuantityBeforeCorrect = this.form.discretionQuantity
      this.form.selectReasonBeforeCorrect = this.selectReasonList.find(e => e.value === this.form.selectReason)?.key
      this.form.bbRemarkBeforeCorrect = this.form.bbRemark
      this.form.selectReason = this.selectReasonList.find(e => e.value === this.form.selectReason)?.key
      this.$emit('open-modal')
      // 投資家属性の初期化
      this.form.investorAttributeName = this.form.investorAttributePullDownList.find(e => e.investorAttributeValue === this.form.investorAttributeValue).investorAttributeName
      this.form.investorAttributeNameBeforeCorrect = this.form.investorAttributeName
    },
    responseErrorHandlerA001(error) {
      this.$_logError(error)
      this.vmIsVisible = false
    },
    correctA002ResponseHandler(data) {
      this.$_logDebug(data)
      if (data.dataList[0]) {
        Object.assign(this.form, data.dataList[0])
      }
      this.bbCorrect = true
    },
    responseErrorHandlerA002(error) {
      this.$_logError(error)
    },
    cancleA003ResponseHandler(data) {
      this.$_logDebug(data)
      if (data.dataList[0]) {
        Object.assign(this.form, data.dataList[0])
      }
      this.form.kanyuKbn = ''
      this.form.receiveMethod = ''
      this.bbDelete = true
    },
    responseErrorHandlerA003(error) {
      this.$_logError(error)
    },
    backInitializeA005(data) {
      this.$_logDebug(data)
      if (data.dataList[0]) {
        Object.keys(data.dataList[0]).forEach(key => {
          this.form[key] = data.dataList[0][key] || ''
        })
        if (this.form.marketOrder === '0') {
        // 価格
          if (this.form.issuePriceType === '1') {
            this.form.price = this.$_withCommaNoneZeroPadding(this.form.price) + '円'
          }
          // ディスカウント率
          if (this.form.issuePriceType === '2') {
            this.form.discountRate = this.$_withCommaNoneZeroPadding(this.form.discountRate) + '%'
          }
        }
        this.form.selectReasonBeforeCorrect = this.selectReasonList.find(e => e.value === this.form.selectReasonBeforeCorrect)?.key
        this.form.selectReason = this.selectReasonList.find(e => e.value === this.form.selectReason)?.key
        this.form.investorAttributeName = this.form.investorAttributePullDownList.find(e => e.investorAttributeValue === this.form.investorAttributeValue).investorAttributeName
      }
    },
    responseErrorHandlerA005(error) {
      this.$_logError(error)
    }
  }
}

</script>

<style lang="scss" scoped>
@import "@/styles/ipopobbInputForm.scss";
.buy-background-color_card {
  background-color: #fef0f0;
}
:deep(.price_input) {
  width: 220px !important;
}
:deep(.colon .el-form-item__label::after) {
  content: "：";
  font-weight: bold;
  display: flex;
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  vertical-align: middle;

}
.colon {
  position: relative;
  left: 0;
}
.input-col {
  margin-left: 0px !important;
}
</style>
