<template>
  <!-- 画面名：国内投信 -->
  <div>
    <screen-title :text="form.screenTitle"></screen-title>
    <div class="caption_card">
      <el-form
        ref="form"
        :model="form"
        :inline="true"
        :rules="rules"
      >
        <el-row
          v-if="!orderVisible"
          type="flex"
          justify="space-between"
          align="middle"
          style="display: flex; flex-wrap: nowrap; margin: 0.5rem 0; padding-bottom: 10px;"
        >
          <div style="display: flex; flex-direction: column; align-items: center;">
            <div style="display: flex; align-items: center;">
              <div style="font-weight: bold; padding-left: 1rem; width: 290px;">
                {{ `償還優遇限度額${customerInfo.jrIsaContractType === '1'? '（総合口座）': ''}：` }}
              </div>
              <div style="text-align: right; display: flex;">
                <span style="color: red; text-align: right; width: 170px;">
                  {{ setLimitAmount(form.transfersPreferentialLimitAmountThisMonth) }}
                  {{ setLimitAmount(form.transfersPreferentialLimitAmountThisMonth) === '-' ? '' : '円' }}
                </span>
                <span style="color: red; text-align: center; width: 14px;">/</span>
                <span style="color: red; text-align: right; width: 170px;">
                  {{ setLimitAmount(form.transfersPreferentialLimitAmountNextMonth) }}
                  {{ setLimitAmount(form.transfersPreferentialLimitAmountNextMonth) === '-' ? '' : '円' }}
                </span>
                <span style="width: 120px;">(今月残 / 来月残)</span>
              </div>
            </div>
            <div
              v-if="customerInfo.jrIsaContractType === '1'"
              style="display: flex; align-items: center;"
            >
              <div style="font-weight: bold; padding-left: 1rem; width: 290px;">
                償還優遇限度額（ジュニアNISA口座）：
              </div>
              <div style="text-align: right; display: flex;">
                <span style="color: red; text-align: right; width: 170px;">
                  {{ setLimitAmount(form.switchingFavorableTreatmentLimitJuniorNisaAccountThisMonth) }}
                  {{ setLimitAmount(form.switchingFavorableTreatmentLimitJuniorNisaAccountThisMonth) === '-' ? '' : '円' }}
                </span>
                <span style="color: red; text-align: center; width: 14px;">/</span>
                <span style="color: red; text-align: right; width: 170px;">
                  {{ setLimitAmount(form.switchingFavorableTreatmentLimitJuniorNisaAccountNextMonth) }}
                  {{ setLimitAmount(form.switchingFavorableTreatmentLimitJuniorNisaAccountNextMonth) === '-' ? '' : '円' }}
                </span>
                <span style="width: 120px;">(今月残 / 来月残)</span>
              </div>
            </div>
          </div>
          <div
            v-show="form.intermediaryValue === '1'"
            style="flex: auto; padding: 0 8px; font-size: 14px; font-weight: bold;"
          >
            {{ form.fundName }}
          </div>
          <div
            v-show="form.intermediaryValue === '1'"
            class="form-wrapper-fund-buy"
          >
            <el-form-item prop="brandCode">
              <div style="display: flex; align-items: baseline; column-gap: 10px;">
                <div style="display: flex; align-items: baseline; column-gap: 10px;">
                  <div class="input-text-class">
                    <ifa-input-text
                      id="fundCodeTimes"
                      v-model="form.fundCodeTimesInput"
                      prop="fundCodeTimesInput"
                      size="small"
                      style="width:120px;"
                      :domain="IfaFixedLength4DomainModel"
                      @blur="handleUpdateFundCodeTimes"
                    ></ifa-input-text>
                  </div>
                  <span>-</span>
                  <div class="input-text-class">
                    <ifa-input-text
                      id="fundCodeIssues"
                      v-model="form.fundCodeIssuesInput"
                      prop="fundCodeIssuesInput"
                      size="small"
                      style="width:100px;"
                      :maxlength="3"
                      @blur="handleUpdateFundCodeIssues"
                    ></ifa-input-text>
                  </div>
                </div>
                <ifa-button
                  id="btnBrandSearch"
                  text="投信銘柄検索"
                  color="primary"
                  small
                  action-type="originalAction"
                  @app-action-handler="handleOpenBrandSearch"
                ></ifa-button>
              </div>
            </el-form-item>
          </div>
        </el-row>
        <el-row v-if="!orderVisible">
          <el-col v-show="form.intermediaryValue === '1'">
            <div style="width: 100%; display: flex; align-items: center; justify-content: flex-end; padding-bottom: 10px;">
              <ifa-button
                v-if="form.buyAbleValue === '1'"
                id="btnBuy"
                text="購入"
                color="buy"
                small
                action-type="originalAction"
                :form="$refs['form']"
                @app-action-handler="purchaseDirectInputA002Pre"
              ></ifa-button>
              <ifa-button
                v-if="form.accumulateAbleValue === '1'"
                name="btnAccumulate"
                text="積立"
                class="accumulation-class"
                small
                action-type="originalAction"
                :form="$refs['form']"
                @app-action-handler="handleRedirectAccumulateInput"
              ></ifa-button>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <!-- 購入・積立可能一覧 -->
      <ifa-available-domestic-investment-trust-list
        ref="ifaAvailableDomesticInvestmentTrustList"
        :is-visible="tableVisible"
        :data-list="form.detailList"
        :hit-number="form.hitNumber"
        @order="purchaseDetailA003"
        @on-redirect-accumulate="handleRedirectAccumulate($event)"
      ></ifa-available-domestic-investment-trust-list>

      <!-- 注文入力 -->
      <ifa-domestic-mutual-fund-order-input
        ref="ifaDomesticMutualFundOrderInput"
        :is-visible="orderVisible"
        :customer-info="customerInfo"
        :params="orderParams"
        :order-data="orderData"
        :list-flag="listFlag"
        @back="handleBack"
        @move-to-order-list="handleMoveToOrderList"
      ></ifa-domestic-mutual-fund-order-input>

      <!--銘柄検索ダイアログ-->
      <ifa-fund-brand-search
        ref="ifaFundBrandSearch"
        :is-visible="brandSearchIsVisible"
        @close-modal="handleCloseModal"
        @result="handleBrandResult"
      ></ifa-fund-brand-search>
    </div>
    <ifa-requester
      id="IfaDomesticMutualFundBuyAbleListA001"
      action-id="SUB0202_0401-01#A001"
      action-type="requestAction"
      @response-handler="responseHandlerInitializeA001($event)"
      @response-error-handler="errorHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaDomesticMutualFundBuyAbleListPurchaseDirectInputA002"
      action-id="SUB0202_0401-01#A002"
      action-type="requestAction"
      :request-model="A002RequestModel"
      @response-handler="purchaseDirectInputA002Res($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaDomesticMutualFundOrderInputInitializeA001"
      action-id="SUB0202_0401-02_1#A001"
      action-type="requestAction"
      :request-model="orderA001RequestModel"
      @response-handler="responseHandlerInputInitializeA001($event)"
      @response-error-handler="errorHandlerInputInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaDomesticMutualFundBuyAbleListAccumulateDirectInputA004"
      action-id="SUB0202_0401-01#A004"
      action-type="requestAction"
      :request-model="A004RequestModel"
      @response-handler="accumulateDirectInputA004Res($event)"
      @response-error-handler="errorHandlerInputInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaDomesticMutualFundBuyAbleListDirectInputSelectMFNameA007"
      action-id="SUB0202_0401-01#A007"
      action-type="requestAction"
      :request-model="A007RequestModel"
      @response-handler="handlerSelectMFNameA007($event)"
      @response-error-handler="errorHandlerSelectMFNameA007($event)"
    ></ifa-requester>

    <!-- 積立設定入力 init -->
    <ifa-requester
      id="ifaMutualFundAccumulateSettingInputInitializeA001Redirect"
      action-id="SUB0202_0403-02_1#A001"
      action-type="requestAction"
      :request-model="settingInputInitA001RedirectRequestModel"
      :disable-notification="disableNotification"
      @response-handler="settingInputInitializeA001RedirectHandler($event)"
      @response-error-handler="settingInputInitializeA001RedirectErrorHandler"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaAvailableDomesticInvestmentTrustList from './components/IfaAvailableDomesticInvestmentTrustList.vue'
import IfaDomesticMutualFundOrderInput from './IfaDomesticMutualFundOrderInput.vue'
import ScreenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaRequester from '@/components/Button/IfaRequester.vue'
import IfaButton from '@/components/Button/IfaButton.vue'
import IfaInputText from '@/components/Input/IfaInputText.vue'
import IfaFundBrandSearch from '@/views/common/IfaFundBrandSearch.vue'
import { notifyMessage, getMessage } from '@/utils/errorHandler'
import { IfaDomesticMutualFundBuyAbleListFormModel } from './js/IfaDomesticMutualFundBuyAbleListFormModel'
import { IfaDomesticMutualFundBuyAbleListA002RequestModel } from './js/IfaDomesticMutualFundBuyAbleListA002RequestModel'
import { IfaDomesticMutualFundBuyAbleListA003RequestModel } from './js/IfaDomesticMutualFundBuyAbleListA003RequestModel'
import { IfaDomesticMutualFundBuyAbleListA004RequestModel } from './js/IfaDomesticMutualFundBuyAbleListA004RequestModel'
import { IfaDomesticMutualFundBuyAbleListA007RequestModel } from './js/IfaDomesticMutualFundBuyAbleListA007RequestModel'
import IfaFixedLength3DomainModel from '@/resource/domain/IfaFixedLength3DomainModel.json'
import IfaFixedLength4DomainModel from '@/resource/domain/IfaFixedLength4DomainModel.json'
import { IfaDomesticMutualFundOrderInputA001RequestModel } from './js/IfaDomesticMutualFundOrderInputA001RequestModel'
import { IfaMutualFundAccumulateSettingInputA001RequestModel } from './js/IfaMutualFundAccumulateSettingInputA001RequestModel'
import { IfaMutualFundAccumulateSettingInputInitA001RequestModel } from '@/views/brokerageMenu/customerMenu/investmentTrust/mutualFundAccumulate/js/IfaMutualFundAccumulateSettingInputInitA001RequestModel'

const SOURCE_CODE = 'SUB0202_0401'
const STEP_CODE = 'SUB0202_0403'

export default {
  components: {
    IfaAvailableDomesticInvestmentTrustList,
    IfaDomesticMutualFundOrderInput,
    ScreenTitle,
    IfaFundBrandSearch,
    IfaRequester,
    IfaButton,
    IfaInputText
  },
  emits: ['show-tab-by-name', 'initializeError'],
  data() {
    return {
      IfaFixedLength3DomainModel,
      IfaFixedLength4DomainModel,
      form: new IfaDomesticMutualFundBuyAbleListFormModel(),
      listFlag: false,
      brandSearchIsVisible: false,
      rules: {
        fundCodeTimesInput: {
          trigger: 'blur',
          validator: this.checkFundNameValidator
        },
        fundCodeIssuesInput: {
          trigger: 'blur',
          validator: this.checkFundNameValidator
        },
        brandCode: [
          {
            required: false,
            type: 'array',
            trigger: 'blur',
            validator: this.brandCodeValidator,
            message: getMessage('errors.required', ['銘柄コード'])
          }
        ]
      },
      orderParams: {},
      accumulateSettingParams: {},
      tableVisible: false,
      orderVisible: false,
      orderData: {},
      settingInputInitA001Info: null,
      disableNotification: false
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    A002RequestModel() {
      return new IfaDomesticMutualFundBuyAbleListA002RequestModel(this.form)
    },
    A003RequestModel() {
      return new IfaDomesticMutualFundBuyAbleListA003RequestModel(this.form)
    },
    A004RequestModel() {
      return new IfaDomesticMutualFundBuyAbleListA004RequestModel(this.form)
    },
    A007RequestModel() {
      return new IfaDomesticMutualFundBuyAbleListA007RequestModel(this.form)
    },
    accumulateSettingA001RequestModel() {
      return new IfaMutualFundAccumulateSettingInputA001RequestModel(this.form)
    },
    orderA001RequestModel() {
      return new IfaDomesticMutualFundOrderInputA001RequestModel(this.form, true)
    },
    settingInputInitA001RedirectRequestModel() {
      return new IfaMutualFundAccumulateSettingInputInitA001RequestModel(this.settingInputInitA001Info)
    }
  },
  methods: {
    onShow() {
      this.orderParams = {}
      this.orderData = {}
      this.listFlag = false
      this.settingInputInitA001Info = null
      this.disableNotification = false
      const params = this.$store.getters.pageInfo.params
      if (params && params.source === 'IfaHoldingSecurityList') {
        this.orderParam = params.data
        this.orderData = params.orderData
        this.$nextTick(() => {
          this.$refs.ifaDomesticMutualFundOrderInput.onShow()
          this.$nextTick(() => {
            this.form.screenTitle = '国内投信注文入力'
            this.orderVisible = true
            this.tableVisible = false
          })
        })
      } else if (params && params.source === 'SUB0202_0403') {
        if (params?.errorMsg) {
          notifyMessage(params?.errorLevel, params?.errorMsg, this.form.screenTitle)
        }
        this.form = new IfaDomesticMutualFundBuyAbleListFormModel()
        this.listFlag = params.listFlag
        if (!this.listFlag) {
          this.form.fundCodeTimes = params.fundCodeTimes
          this.form.fundCodeIssues = params.fundCodeIssues

          this.form.fundCodeTimesInput = this.form.fundCodeTimes
          this.form.fundCodeIssuesInput = this.form.fundCodeIssues

          this.$nextTick(() => {
            document.getElementById('ifaDomesticMutualFundBuyAbleListDirectInputSelectMFNameA007').click()
          })
        }
        this.$nextTick(() => {
          document.getElementById('IfaDomesticMutualFundBuyAbleListA001').click()
        })
        this.handleBack()
        this.form.brandCode = ['', '']
        this.$refs['form'].clearValidate()
      } else {
        this.form = new IfaDomesticMutualFundBuyAbleListFormModel()
        document.getElementById('IfaDomesticMutualFundBuyAbleListA001').click()
        this.handleBack()
        this.form.brandCode = ['', '']
        this.$refs['form'].clearValidate()
      }
    },
    responseHandlerInitializeA001(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      setTimeout(() => {
        this.$refs['ifaAvailableDomesticInvestmentTrustList'].setTableData()
        this.tableVisible = true
      }, 300)
    },
    responseHandlerInputInitializeA001(response) {
      this.orderData.dispatchId = response.dataList[0]?.dispatchId
      this.orderData = Object.assign(this.orderData, response.dataList[0])
      this.$nextTick(() => {
        this.$refs.ifaDomesticMutualFundOrderInput.onShow()
        this.$nextTick(() => {
          this.form.screenTitle = '国内投信注文入力'
          this.orderVisible = true
          this.tableVisible = false
        })
      })
    },
    errorHandlerInputInitializeA001() {
      this.orderParams = {}
      this.orderData = {}
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    purchaseDirectInputA002Pre() {
      // フォームのサブミット前に入力値チェックを行う
      this.$refs['form'].validate(valid => {
        if (!valid) {
          return
        } else {
          // 銘柄コード直接入力エリアに入力されているファンドコード（回数）およびファンドコード（号）が
          // 明細にあり　かつ　購入可以外の場合、エラーを返す
          this.form.fundCodeTimes = this.form.fundCodeTimesInput
          this.form.fundCodeIssues = this.form.fundCodeIssuesInput
          this.orderParams = this.A002RequestModel
          this.$nextTick(() => {
            document.getElementById('IfaDomesticMutualFundBuyAbleListPurchaseDirectInputA002').click()
          })
        }
      })
    },
    handleRedirectAccumulateInput() {
      this.form.fundCodeTimes = this.form.fundCodeTimesInput
      this.form.fundCodeIssues = this.form.fundCodeIssuesInput
      const formEl = this.$refs?.form

      formEl.validate((valid) => {
        if (valid) {
          this.$nextTick(() => {
            document.getElementById('IfaDomesticMutualFundBuyAbleListAccumulateDirectInputA004').click()
          })
        }
      })
    },
    purchaseDirectInputA002Res(response) {
      this.orderData = {}
      this.form.fundCodeIssues = this.form.fundCodeIssues.padStart(3)
      this.form.dispatchId = response.dataList[0]?.prospectusFlag
      this.$nextTick(() => {
        document.getElementById('ifaDomesticMutualFundOrderInputInitializeA001').click()
      })
      this.orderParams = this.A002RequestModel
    },
    purchaseDetailA003(param) {
      this.orderData = {}
      this.$nextTick(() => {
        document.getElementById('ifaDomesticMutualFundOrderInputInitializeA001').click()
      })
      this.form.fundCodeTimes = param.fundCodeTimes
      this.form.fundCodeIssues = param.fundCodeIssues.padStart(3)
      this.form.dispatchId = param.dispatchId
      this.orderParams = this.A003RequestModel
      this.listFlag = param.listFlag
    },
    handleRedirectAccumulate(param) {
      this.form.fundCodeTimes = param.fundCodeTimes
      this.form.fundCodeIssues = param.fundCodeIssues.padStart(3)
      this.form.kyoukaiCd = param.kyoukaiCd
      this.listFlag = param.listFlag

      this.accumulateSettingParams = {
        ...this.accumulateSettingA001RequestModel,
        ...param
      }

      const { kyoukaiCd, fundCodeTimes, fundCodeIssues } = this.accumulateSettingParams

      this.settingInputInitA001Info = {
        fundCode: kyoukaiCd,
        mfgo: fundCodeIssues,
        mfkaisu: fundCodeTimes,
        listFlag: param.listFlag,
        source: SOURCE_CODE,
        step: STEP_CODE
      }

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingInputInitializeA001Redirect').click()
      })
    },

    accumulateDirectInputA004Res(res) {
      this.form.fundCodeIssues = this.form.fundCodeIssues.padStart(3)
      this.accumulateSettingParams = {
        ...this.accumulateSettingA001RequestModel,
        ...res?.dataList?.[0]
      }

      const { kyoukaiCd, fundCodeTimes, fundCodeIssues } = this.accumulateSettingParams

      this.settingInputInitA001Info = {
        fundCode: kyoukaiCd,
        mfgo: fundCodeIssues,
        mfkaisu: fundCodeTimes,
        source: SOURCE_CODE,
        step: STEP_CODE
      }

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingInputInitializeA001Redirect').click()
      })
    },

    settingInputInitializeA001RedirectHandler(res) {
      this.$_logDebug(res)

      const { dataList, ...rest } = res

      // warning msg was not display in the current page
      if (rest?.errorLevel === 2) {
        this.disableNotification = true
      }

      this.$_startShowMenu('SUB0202_0403', {
        source: SOURCE_CODE,
        data: {
          data: dataList?.[0] ?? {},
          ...rest,
          ...this.accumulateSettingParams
        }
      })
    },

    settingInputInitializeA001RedirectErrorHandler(error) {
      this.$_logError(error)
    },
    // 償還優遇限度額表示分岐
    setLimitAmount(value) {
      if (Number(value) <= 0) {
        return 0
      } else {
        return this.$_out(this.$_withCommaInteger(value))
      }
    },
    // 注文入力,投信積立設定入力から購入可能一覧に切り替え
    handleBack() {
      if (this.listFlag) {
        this.form.fundCodeTimes = ''
        this.form.fundCodeIssues = ''
        this.form.fundCodeTimesInput = ''
        this.form.fundCodeIssuesInput = ''
        this.listFlag = false
      } else {
        this.form.fundCodeIssues = this.form.fundCodeIssues.replace(/\s+/g, '')
        this.form.fundCodeIssuesInput = this.form.fundCodeIssues
      }
      this.form.screenTitle = '国内投信購入・積立可能一覧'
      this.orderVisible = false
      this.tableVisible = true
    },
    // ダイアログを閉じる
    handleCloseModal() {
      this.brandSearchIsVisible = false
    },
    handleOpenBrandSearch() {
      this.$refs['ifaFundBrandSearch'].clear()
      this.brandSearchIsVisible = true
    },
    handleBrandResult(result) {
      this.$refs['form']?.clearValidate('fundCodeTimesInput')
      this.$refs['form']?.clearValidate('fundCodeIssuesInput')
      this.form.fundCodeTimesInput = result.fundKaisu
      this.form.fundCodeIssuesInput = result.fundGo.padStart(3, ' ')
      this.form.fundCodeTimes = this.form.fundCodeTimesInput
      this.form.fundCodeIssues = this.form.fundCodeIssuesInput
      this.form.fundName = result.fundName
    },
    // 注文一覧画面に遷移する
    handleMoveToOrderList() {
      this.$_startShowMenu('SUB0202_0104')
      this.orderVisible = false
    },
    // 銘柄コードのバリデーション
    brandCodeValidator(rule, value, callback) {
      if (this.form.fundCodeTimesInput.length === 0 || this.form.fundCodeIssuesInput.length === 0) {
        callback(new Error())
        return
      }
      // OK
      callback()
    },
    checkFundNameValidator(rule, value, callback) {
      if (value && rule.field === 'fundCodeIssuesInput') {
        const dateRegex = '^[ 0-9a-zA-Z]+$'
        if (String(value).match(new RegExp(dateRegex)) === null) {
          callback(getMessage('errors.required', ['英数字']))
          return
        }
      }
      callback()
    },
    handleUpdateFundCodeTimes(value) {
      this.form.fundCodeTimes = value
      this.handlerFundNameSearch()
    },
    handleUpdateFundCodeIssues(value) {
      this.form.fundCodeIssues = value
      this.handlerFundNameSearch()
    },
    // A007-投信銘柄情報取得
    handlerFundNameSearch() {
      if (this.form.fundCodeIssuesInput && this.form.fundCodeIssuesInput.length === 1) {
        this.form.fundCodeIssuesInput = ' 0' + this.form.fundCodeIssuesInput
      } else if (this.form.fundCodeIssuesInput && this.form.fundCodeIssuesInput.length === 2) {
        this.form.fundCodeIssuesInput = ' ' + this.form.fundCodeIssuesInput
      }
      this.form.fundCodeTimes = this.form.fundCodeTimesInput
      this.form.fundCodeIssues = this.form.fundCodeIssuesInput
      this.form.fundName = ''
      if (this.form.fundCodeTimes && this.form.fundCodeIssues) {
        this.$refs?.form.validateField('fundCodeTimesInput', (checkResult) => {
          if (checkResult) {
            this.$refs?.form.validateField('fundCodeIssuesInput', (checkOk) => {
              if (checkOk) {
                this.$nextTick(() => {
                  document.getElementById('ifaDomesticMutualFundBuyAbleListDirectInputSelectMFNameA007').click()
                })
              }
            })
          }
        })
      }
    },
    handlerSelectMFNameA007(res) {
      this.form.fundName = res.dataList[0]?.kyoukaiName
    },
    errorHandlerSelectMFNameA007(_res) {
      this.form.fundName = ''
    }

  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/table.scss";
.search-button__wrapper {
  float: right;
  margin-right: 1rem;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
:deep(.form_label) .el-form-item__label {
  width: 85px;
}
:deep(.el-form-item) {
  margin-right: 10px;
}
:deep(.el-form-item__label) {
  margin: 0;
}
:deep(.el-form-item__content) {
  flex-wrap: nowrap;
}
:deep(.el-form-item__error) {
  left:85px;
}
::v-deep .accumulation-class .el-button {
  background-color: #70ad47 !important;
  border-color: #70ad47 !important;
}
.fund-connect {
  margin:0px 10px 0px 0px;
  font-size: 18px;
  margin-top: 15px;
}

:deep(.input-text-class .el-form-item) {
  margin-right: 0 !important;
}

:deep(.form-wrapper-fund-buy .el-form-item) {
  margin-right: 0 !important;
  margin-bottom: 0 !important;
}
</style>
