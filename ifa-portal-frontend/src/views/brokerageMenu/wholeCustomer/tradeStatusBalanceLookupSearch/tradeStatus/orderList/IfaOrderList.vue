<template>
  <!-- 画面名：注文一覧 -->
  <div>
    <screen-title :text="form.titleModel.name"></screen-title>
    <div
      class="ifa-search-view__main-default"
    >
      <div id="top-component2">
        <div>
          <div id="contentAreaInput">
            <div id="inputArea">
              <el-card>
                <el-form
                  ref="form"
                  :model="form"
                  :inline="true"
                  :rules="rules"
                >
                  <el-row>
                    <el-col :span="23">
                      <ifa-common-search
                        ref="commonSearch"
                        :form="form"
                        display-pattern="pt1"
                        list-pattern="pt2"
                        :add-internet-to-courses="'on'"
                        original-screen-id="SUB020302_0101-01"
                        @mediate-user-privacy="getAutoDisplay"
                      ></ifa-common-search>
                    </el-col>
                    <el-col
                      :span="1"
                      class="right_icon"
                    >
                      <ifa-balloon-icon
                        v-if="form.orderListComment"
                        icon-type="info"
                      >
                        <div v-html="form.orderListComment">
                        </div>
                      </ifa-balloon-icon>
                    </el-col>
                  </el-row>
                  <div>
                    <el-row>
                      <el-col>
                        <el-row>
                          <div
                            style="display: inline-block;"
                            class="order_select_margin"
                          >
                            <order-select
                              ref="orderSelect"
                              id-string="orderList"
                              :required="true"
                              class="form_label"
                              label="商品"
                              prop="orderSelected"
                              :domain="IfaOrderListSecurityTypeDomainModel"
                              @change-selected="form.orderSelected = $event"
                            ></order-select>
                          </div>
                          <el-form-item class="bottom_margin">
                            <ifa-input-text
                              id="brandCode"
                              v-model="form.brandCode"
                              :disabled="isBrandCodeDisabled"
                              label="銘柄コード"
                              size="small"
                              label-class="brand_margin"
                              class="ifa-input__text-default search-form__item middle_input"
                              style="width: 180px;"
                              :domain="IfaBrandCodeDomainModel"
                            ></ifa-input-text>
                          </el-form-item>
                        </el-row>
                      </el-col>
                    </el-row>
                    <el-row>
                      <el-col :span="8">
                        <ifa-date-range-picker
                          ref="datePicker"
                          v-model="form.period"
                          label="期間指定"
                          :picker-options="pickerOptions"
                          size="small"
                          prop="period"
                          class="form_label"
                          :required="true"
                        ></ifa-date-range-picker>
                      </el-col>
                    </el-row>
                    <el-row>
                      <el-col style="margin: 1rem 0 0.5rem 9.5rem;">
                        <span>※期間は12ヶ月以内を指定してください。（国内株式（IPO/PO）注文の場合、上場日を過ぎると参照不可となります）</span>
                      </el-col>
                    </el-row>
                  </div>
                  <el-row style="margin-top:1rem"></el-row>
                  <div
                    id="indicator-display"
                    class="form_button"
                  >
                    <ifa-button
                      id="btnDisplay"
                      name="btnDisplay"
                      text="表示"
                      width="90"
                      color="primary"
                      :form="formRef"
                      search
                      small
                      action-type="requestAction"
                      :pre-request-handler="searchHandler"
                      :request-model="ifaOrderListA002RequestModel"
                      action-id="SUB020302_0101-01#A002"
                      @response-handler="responseA002Handler"
                      @response-error-handler="responseA002ErrorHandler"
                    ></ifa-button>
                    <ifa-button
                      id="btnTopInputClear"
                      name="btnTopInputClear"
                      text="クリア"
                      width="90"
                      color="secondary"
                      small
                      action-type="originalAction"
                      @app-action-handler="clearSearchCondition"
                    ></ifa-button>
                  </div>
                </el-form>
              </el-card>
            </div>
          </div>
        </div>
      </div>
      <div id="paneDivider">
        <div id="paneBottom">
          <div>
            <div id="outputPaneMargins">
              <el-card id="outputTable">
                <div
                  id="gridButtonArea"
                  name="gridButtonArea"
                >
                  <div id="indicator-target">
                    <ifa-button
                      id="btmCustomerPortal"
                      name="btmCustomerPortal"
                      :disabled="disableBtn"
                      text="注文状況一覧へ"
                      color="primary"
                      small
                      action-type="originalAction"
                      @app-action-handler="handleMoveOrderStatusList"
                    ></ifa-button>
                    <ifa-button
                      id="btnCsvDownload"
                      name="btnCsvDownload"
                      :disabled="csvbtn"
                      text="CSV出力"
                      width="90"
                      color="primary"
                      small
                      csv-file-name="注文一覧"
                      action-id="SUB020302_0101-01#A005"
                      :request-model="ifaOrderListA005RequestModel"
                      :is-check-csv-download-allowed="true"
                      :is-check-csv-download-privacy-confirmation="true"
                      :pre-request-handler="preCsvDownload"
                      action-type="outputCsvAction"
                      @response-handler="responseHandlerA005($event)"
                      @response-error-handler="responseA005ErrorHandler($event)"
                    ></ifa-button>
                    <button
                      id="other-info-button"
                      type="button"
                      value=" "
                      hidden="true"
                      @click="handleOtherInfo"
                    ></button>
                  </div>
                </div>

                <!-- 国内株式（現物・信用・現引現渡） -->
                <el-row
                  v-if="showDomesticStockGrid"
                  id="DomesticStockGridRow"
                  style="margin-top:1rem"
                >
                  <grid-table
                    v-if="showDomesticStockGrid"
                    ref="domesticStockGrid"
                    :options="pqGridOption"
                    :auto-refresh="false"
                    @click="handleClick($event, '0')"
                  ></grid-table>
                </el-row>

                <!-- 国内投資信託 -->
                <el-row
                  v-if="showDomesticInvestmentTrustisGrid"
                  id="DomesticInvestmentTrustisGridRow"
                  style="margin-top:1rem"
                >
                  <grid-table
                    v-if="showDomesticInvestmentTrustisGrid"
                    ref="domesticInvestmentTrustisGrid"
                    :options="pqGridOption2"
                    :auto-refresh="false"
                    @click="handleClick($event, '1')"
                  ></grid-table>
                </el-row>

                <!-- 国内投資信託 （定期積立） -->
                <el-row
                  v-if="showDomesticMutualFundRegularRecruitmentGrid"
                  id="DomesticMutualFundRegularRecruitmentGridRow"
                  style="margin-top:1rem"
                >
                  <grid-table
                    v-if="showDomesticMutualFundRegularRecruitmentGrid"
                    ref="domesticMutualFundRegularRecruitmentGrid"
                    :options="pqGridOption8"
                    :auto-refresh="false"
                    @click="handleClick($event, '5')"
                  ></grid-table>
                </el-row>

                <!-- 募集注文 -->
                <el-row
                  v-if="showDomesticRecruitGrid"
                  id="DomesticRecruitGridRow"
                  style="margin-top:1rem"
                >
                  <grid-table
                    v-if="showDomesticRecruitGrid"
                    ref="domesticRecruitGrid"
                    :options="pqGridOption3"
                    :auto-refresh="false"
                    @click="handleClick($event, '2')"
                  ></grid-table>
                </el-row>

                <!-- 外国株式（委託注文） -->
                <el-row
                  v-if="showForeignCommitGrid"
                  id="ForeignCommitGridRow"
                  style="margin-top:1rem"
                >
                  <grid-table
                    v-if="showForeignCommitGrid"
                    ref="foreignCommitGrid"
                    :options="pqGridOption4"
                    :auto-refresh="false"
                    @click="handleClick($event, '3')"
                  ></grid-table>
                </el-row>

                <!-- 外国株式（店頭注文） -->
                <el-row
                  v-if="showForeignStoreGrid"
                  id="ForeignStoreGridRow"
                  style="margin-top:1rem"
                >
                  <grid-table
                    v-if="showForeignStoreGrid"
                    ref="foreignStoreGrid"
                    :options="pqGridOption5"
                    :auto-refresh="false"
                    @click="handleClick($event,'4')"
                  ></grid-table>
                </el-row>

                <!-- 外貨建MMF -->
                <!-- <el-row
                  v-if="showForeignCurrencyGrid"
                  style="margin-top:1rem"
                >
                  <grid-table
                    v-if="showForeignCurrencyGrid"
                    :options="pqGridOption6"
                    @click="handleClick"
                  ></grid-table>
                </el-row> -->
              </el-card>
            </div>
          </div>
        </div>
      </div>

      <!-- TODO コンプラ項目詳細ダイアログ実装する後にVUEファイルを使う -->
      <ifa-domestic-mutual-fund-order-other-info
        v-model="otherInfoVisible"
        :order-other-info="orderOtherInfo"
        @close-dialog="otherInfoVisible = false"
      >
      </ifa-domestic-mutual-fund-order-other-info>

    </div>
    <ifa-requester
      id="ifaOrderListInitializeA001"
      action-type="requestAction"
      action-id="SUB020302_0101-01#A001"
      @response-handler="initializeA001ResponseHandler($event)"
      @response-error-handler="responseErrorHandlerA001($event)"
    ></ifa-requester>
    <!-- コンプラ項目詳細 初期化 -->
    <ifa-requester
      id="ifaDomesticMutualFundOrderOtherInfoInitializeA001"
      action-type="requestAction"
      action-id="SUB020302_0101-03#A001"
      :request-model="requestModelA001"
      @response-handler="orderOtherInfoInitializeA001ResponseHandler($event)"
      @response-error-handler="responseErrorHandlerA001($event)"
    ></ifa-requester>
  </div>
</template>
<script>
import { getFormattedDateTimeValue, getFormattedDateValue, monthsBefore } from '@/components/Date/js/IfaDatePickerFunction.js'
import GridTable from '@/components/GridTable'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction.js'
import OrderSelect from '@/components/MultiSelect/OrderSelect'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import IfaBrandCodeDomainModel from '@/resource/domain/IfaBrandCodeDomainModel.json'
import IfaOrderListSecurityTypeDomainModel from '@/resource/domain/IfaOrderListSecurityTypeDomainModel.json'
import { getMessage } from '@/utils/errorHandler'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getConvertedOption } from '@/utils/pqgridHelper'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaOrderListA002RequestModel } from './js/IfaOrderListA002RequestModel'
import { IfaOrderListA004RequestModel } from './js/IfaOrderListA004RequestModel'
import { IfaOrderListA005RequestModel } from './js/IfaOrderListA005RequestModel'
import { IfaOrderListFormModel } from './js/IfaOrderListFormModel'
import IfaDomesticMutualFundOrderOtherInfo from './IfaDomesticMutualFundOrderOtherInfo'
import { validateDateRangeFromTo, validateDateRangeBeforeMonths } from '@/components/Date/js/IfaDatePickerFunction.js'

export default {
  components: { GridTable, OrderSelect, IfaCommonSearch,
    screenTitle, IfaDomesticMutualFundOrderOtherInfo },
  data() {
    return {
      formRef: {},
      IfaBrandCodeDomainModel: IfaBrandCodeDomainModel,
      IfaOrderListSecurityTypeDomainModel: IfaOrderListSecurityTypeDomainModel,
      showDomesticStockGrid: true,
      showDomesticInvestmentTrustisGrid: true,
      showDomesticMutualFundRegularRecruitmentGrid: true,
      showForeignCurrencyGrid: true,
      showDomesticRecruitGrid: true,
      showForeignCommitGrid: true,
      showForeignStoreGrid: true,
      otherInfoVisible: false,
      pqGridOption: getConvertedOption(obj),
      pqGridOption2: getConvertedOption(obj2),
      pqGridOption3: getConvertedOption(obj4),
      pqGridOption4: getConvertedOption(obj5),
      pqGridOption5: getConvertedOption(obj6),
      // pqGridOption6: getConvertedOption(obj3),
      pqGridOption8: getConvertedOption(obj8),
      pqGridSelectedInfo: {},
      OrderListQueryForm: {
        period: [],
        brandCode: '',
        customerNameKanjiKanaTerms: '3'
      },
      disableBtn: true,
      orderType: 'buy',
      rules: {
        orderSelected: { required: true, trigger: 'blur', validator: this.orderSelectedValidator },
        period: { required: true, trigger: 'change', validator: this.periodValidator }
      },
      pickerOptions: {
        shortcuts: [
          {
            text: '当日日付',
            value: () => {
              const startDate = new Date(this.$store.getters.requestedTime)
              const endDate = new Date(this.$store.getters.requestedTime)
              return [startDate, endDate]
            }
          }, {
            text: '1ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 1)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }, {
            text: '3ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 3)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }, {
            text: '6ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 6)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }, {
            text: '1年前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 12)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }
        ]
      },
      requestModelA001: {},
      orderOtherInfo: {},
      form: new IfaOrderListFormModel()
    }
  },
  computed: {
    isBrandCodeDisabled() {
      const orderList = this.form.orderSelected
      if (orderList[0].isSelected === false) {
        this.resetBrandCode()
        return true
      }
      for (let i = 1; i < orderList.length; i++) {
        if (orderList[i].isSelected === true) {
          this.resetBrandCode()
          return true
        }
      }
      return false
    },
    csvbtn() {
      if (this.pqGridOption.dataModel.data.length || this.pqGridOption2.dataModel.data.length ||
      this.pqGridOption3.dataModel.data.length || this.pqGridOption4.dataModel.data.length ||
      this.pqGridOption5.dataModel.data.length || this.pqGridOption8.dataModel.data.length) {
        return false
      }
      return true
    },
    ifaOrderListA002RequestModel() {
      return new IfaOrderListA002RequestModel(this.form)
    },
    ifaOrderListA005RequestModel() {
      return new IfaOrderListA005RequestModel(this.form)
    }
  },
  watch: {
    'form.period': {
      handler(newValue) {
        this.form.periodYmFrom = newValue[0]
        this.form.periodYmTo = newValue[1]
      }
    }
  },
  created() {
    this.pqGridOption.wrap = true
    this.pqGridOption2.wrap = true
    this.pqGridOption3.wrap = true
    this.pqGridOption4.wrap = true
    this.pqGridOption5.wrap = true
    this.pqGridOption8.wrap = true
  },
  activated() {
    this.disableBtn = true
    this.$nextTick(() => {
      this.pqGridOption.dataModel.data = []
      this.pqGridOption2.dataModel.data = []
      this.pqGridOption3.dataModel.data = []
      this.pqGridOption4.dataModel.data = []
      this.pqGridOption5.dataModel.data = []
      this.pqGridOption8.dataModel.data = []
      this.$refs['domesticStockGrid']?.refreshView(true)
      this.$refs['domesticInvestmentTrustisGrid']?.refreshView(true)
      this.$refs['domesticMutualFundRegularRecruitmentGrid']?.refreshView(true)
      this.$refs['domesticRecruitGrid']?.refreshView(true)
      this.$refs['foreignCommitGrid']?.refreshView(true)
      this.$refs['foreignStoreGrid']?.refreshView(true)
    })
  },
  mounted() {
    this.formRef = this.$refs.form
    this.form.customerNameKanjiKanaTerms = '3'
    let defaultDate = new Date(this.$store.getters.requestedTime)
    defaultDate = this.formatDate(defaultDate)
    this.form.period = [defaultDate, defaultDate]
    this.form.chkBrokerCodeExclude = false
    Object.keys(this.OrderListQueryForm).forEach(key => {
      this.OrderListQueryForm[key] = this.form[key]
    })
    document.querySelector('#ifaOrderListInitializeA001').click()
  },
  methods: {
    searchHandler() {
      this.disableBtn = true
      this.$nextTick(() => {
        this.pqGridOption.dataModel.data = []
        this.pqGridOption2.dataModel.data = []
        this.pqGridOption3.dataModel.data = []
        this.pqGridOption4.dataModel.data = []
        this.pqGridOption5.dataModel.data = []
        this.$refs['domesticStockGrid']?.refreshView(true)
        this.$refs['domesticInvestmentTrustisGrid']?.refreshView(true)
        this.$refs['domesticMutualFundRegularRecruitmentGrid']?.refreshView(true)
        this.$refs['domesticRecruitGrid']?.refreshView(true)
        this.$refs['foreignCommitGrid']?.refreshView(true)
        this.$refs['foreignStoreGrid']?.refreshView(true)
      })
      this.disableBtn = true
      this.ifaOrderListA002RequestModel.course = this.form.courseSelected.filter(e => e.isSelected).map(e => e.id).join(',')
      this.ifaOrderListA002RequestModel.orderSelected = this.form.orderSelected.filter(e => e.isSelected).map(e => e.id).join(',')
      this.ifaOrderListA002RequestModel.chkBrokerCodeExclude = this.form.chkBrokerCodeExclude
      this.ifaOrderListA002RequestModel.periodYmFrom = (this.form.periodYmFrom.replace('/', '年').replace('/', '月') + '日')
      this.ifaOrderListA002RequestModel.periodYmTo = (this.form.periodYmTo.replace('/', '年').replace('/', '月') + '日')
      this.ifaOrderListA002RequestModel.customerNameKanjiKanaTerms = this.form.customerNameKanjiKanaTerms - 1
    },
    clearSearchCondition() {
      this.$refs['form'].clearValidate()
      this.$refs.commonSearch.formClear()
      this.$refs.orderSelect.clearHandler()
      Object.assign(this.form, this.OrderListQueryForm)
    },
    handleClick({ rowData }, orderType) {
      rowData.gridOrderType = orderType
      this.pqGridSelectedInfo = rowData

      // 注文状況一覧へボタンの活性/非活性の判定
      let isBeforeYesterday = false
      let isOrderStatusCancelled = false

      const checkBeforeYesterday = (date) => {
        // 引数：dateがDate型ではない または invalidated dataの場合は、falseを返却する
        if (!(date instanceof Date) || Number.isNaN(date.getTime())) {
          return false
        }

        const year = date.getFullYear()
        const month = date.getMonth()
        const day = date.getDate()
        const today = new Date(this.$store.getters.requestedTime)
        const yearToday = today.getFullYear()
        const monthToday = today.getMonth()
        const dayToday = today.getDate()

        if (year !== yearToday) {
          return year < yearToday
        } else if (month !== monthToday) {
          return month < monthToday
        } else {
          return day < dayToday
        }
      }

      switch (orderType) {
        case '0': { // 国内株式（現物、信用、現引・現渡）
          const acceptLimit = !rowData.acceptLimit || new Date(this.$_getFormattedDateValue(rowData.acceptLimit))
          isBeforeYesterday = checkBeforeYesterday(acceptLimit)
          isOrderStatusCancelled = (rowData.orderStatus === '2') // 国内株式_注文状況 = 取消
          break
        }
        case '1': { // 国内投信
          const orderPlacementDate = !rowData.orderPlacementDate || new Date(this.$_getFormattedDateValue(rowData.orderPlacementDate))
          isBeforeYesterday = checkBeforeYesterday(orderPlacementDate)
          isOrderStatusCancelled = (rowData.orderStatus === '2')
          break
        }
        case '5': { // 国内投資信託 （定期積立）
          isBeforeYesterday = true
          isOrderStatusCancelled = true
          break
        }
        case '2': // 国内株式（IPO/PO） fall-through
        case '3': // 外国株式委託 fall-through
        case '4': // 外国株式店頭 fall-through
        default: {
          // do nothing
          break
        }
      }
      this.disableBtn = isBeforeYesterday || isOrderStatusCancelled
    },
    preCsvDownload() {
      this.ifaOrderListA005RequestModel.course = this.form.courseSelected.filter(e => e.isSelected).map(e => e.id).join(',')
      this.ifaOrderListA005RequestModel.orderSelected = this.form.orderSelected.filter(e => e.isSelected).map(e => e.id).join(',')
      this.ifaOrderListA005RequestModel.chkBrokerCodeExclude = this.form.chkBrokerCodeExclude
      this.ifaOrderListA005RequestModel.periodYmFrom = (this.form.periodYmFrom.replace('/', '年').replace('/', '月') + '日')
      this.ifaOrderListA005RequestModel.periodYmTo = (this.form.periodYmTo.replace('/', '年').replace('/', '月') + '日')
      this.ifaOrderListA005RequestModel.customerNameKanjiKanaTerms = this.form.customerNameKanjiKanaTerms - 1
    },
    orderSelectedValidator(rule, value, callback) {
      const selected = this.form.orderSelected.filter(e => e.isSelected)
      if (selected.length) {
        callback()
      } else {
        callback('商品を選択してください。')
      }
    },
    periodValidator(rule, value, callback) {
      if (!this.form.period.length) {
        callback(getMessage('errors.required', ['期間指定']))
        return
      }

      // 以下の条件の時エラー
      // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が12ヶ月より大きい
      if (validateDateRangeFromTo(this.form.period, 12)) {
        callback(getMessage('errors.dateRange', ['期間指定', '12ヶ月']))
        return
      }
      // リクエスト.期間指定（From）がシステム日付-12ヶ月より小さい　または
      // リクエスト.期間指定（To）がシステム日付-12ヶ月より小さい
      if (validateDateRangeBeforeMonths(this.form.period, 12)) {
        callback(getMessage('errors.dateRange', ['期間指定', '12ヶ月']))
        return
      }

      // OK
      callback()
    },
    // 日付を文字列に変換する
    formatDate(date, f = false) {
      date = date || new Date()
      return date.getFullYear() + '/' +
        (('0' + (date.getMonth() + 1)).slice(-2)) + '/' +
        ('0' + date.getDate()).slice(-2)
    },
    selectShowGrid() {
      this.showDomesticStockGrid = false
      this.showDomesticInvestmentTrustisGrid = false
      this.showDomesticMutualFundRegularRecruitmentGrid = false
      this.showForeignCurrencyGrid = false
      this.showDomesticRecruitGrid = false
      this.showForeignCommitGrid = false
      this.showForeignStoreGrid = false

      // orderListOptions のvalueに対応した表を表示させる
      if (this.form.orderSelected[0].isSelected === true) {
        // 国内株式（現物・信用・現引現渡）
        this.showDomesticStockGridRow = true
        this.showDomesticStockGrid = true
      }
      if (this.form.orderSelected[1].isSelected === true) {
        // 国内投資信託
        this.showDomesticInvestmentTrustisGridRow = true
        this.showDomesticInvestmentTrustisGrid = true
      }
      if (this.form.orderSelected[2].isSelected === true) {
        // 国内投資信託 （定期積立）
        this.showDomesticMutualFundRegularRecruitmentGridRow = true
        this.showDomesticMutualFundRegularRecruitmentGrid = true
      }
      if (this.form.orderSelected[3].isSelected === true) {
        // 募集注文
        this.showDomesticRecruitGridRow = true
        this.showDomesticRecruitGrid = true
      }
      if (this.form.orderSelected[4].isSelected === true) {
        // 外国株式（委託注文）
        this.showForeignCommitGridRow = true
        this.showForeignCommitGrid = true
      }
      if (this.form.orderSelected[5].isSelected === true) {
        // 外国株式（店頭注文）
        this.showForeignStoreGridRow = true
        this.showForeignStoreGrid = true
      }
      // if (this.OrderListQueryForm.orderSelected[5].isSelected === true) {
      //   // 外貨建MMF
      //   this.showForeignCurrencyGridRow = true
      //   this.showForeignCurrencyGrid = true
      // }
    },
    handleOtherInfo() {
      this.$nextTick(() => {
        const otherInfo = JSON.parse(decodeURI(document.getElementById('other-info-button').value))
        this.requestModelA001 = {
          ifaOrderNo: otherInfo.ifaOrderNo,
          ifaOrderSubNo: otherInfo.ifaOrderSubNo
        }
        document.getElementById('ifaDomesticMutualFundOrderOtherInfoInitializeA001').click()
      })
    },
    // コンプラ項目詳細　初期化A001
    orderOtherInfoInitializeA001ResponseHandler(response) {
      this.orderOtherInfo = response.dataList[0]
      this.otherInfoVisible = true
    },
    // 注文状況一覧
    handleMoveOrderStatusList() {
      const rowData = this.pqGridSelectedInfo
      const param = new IfaOrderListA004RequestModel({})
      param.securityType = rowData.gridOrderType // 商品区分
      if (rowData.gridOrderType === '0') { // 商品区分 = "0":国内株式
        param.ecOrderNo = rowData.ecOrderNo // EC受注番号（国内株式注文）
        // 発注区分（国内株式注文）
        if (rowData.tradeCd === '1' || rowData.tradeCd === '2') {
          param.hattyuuKbn = '1'
        } else if (rowData.tradeCd === '3' || rowData.tradeCd === '4' || rowData.tradeCd === '5' || rowData.tradeCd === '6') {
          param.hattyuuKbn = '2'
        } else if (rowData.tradeCd === '7' || rowData.tradeCd === '8') {
          param.hattyuuKbn = '3'
        }
      } else if (rowData.gridOrderType === '1') { // 商品区分 = "1":国内投信
        param.domesticMutualFundOrderStatusEcOrderNo = rowData.ecOrderNo // EC受注番号（国内投信注文）
        param.orderType = rowData.orderType // 商品区分（国内投信）
      } else if (rowData.gridOrderType === '2') { // 商品区分 = "2":募集
        param.brandCode = rowData.brandCode // 銘柄コード（募集注文）
        param.depositType = rowData.depositType // 預り区分（募集注文）
      } else if (rowData.gridOrderType === '3') { // 商品区分 = "3":外国株式委託
        param.orderNumber = rowData.acceptOrderNumber // 注文番号（外株委託注文）
        param.orderSubNumber = rowData.acceptOrderSubNumber // 注文SUB番号（外株委託注文）
      } else if (rowData.gridOrderType === '4') { // 商品区分 = "4":外国株式店頭
        param.manageNumber = rowData.manageNumber // 管理番号（店頭取引注文）
      }
      param.sourceScreenId = 'SUB020302_0101'
      param.nextActionId = 'A002' // 遷移先ActionId
      param.butenCode = rowData.butenCode || rowData.buten || '' // 部店コード
      param.accountNumber = rowData.accountNumber || '' // 口座番号
      param.userName = rowData.customerNameKanji
      this.$_startShowMenu('SUB0202_0104', param)
    },
    resetBrandCode() {
      this.form.brandCode = ''
    },
    // 共通検索条件.営業員コード自動設定判定
    getAutoDisplay(data) {
      this.form.empCodeAutoDispFlag = data.empCodeAutoDispFlag
      const hideBrokerInfo = (data.empCodeAutoDispFlag !== '0')

      obj.colModel.forEach(e => {
        if (e.dataIndx === 'brokerCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerageBranchCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerBranchName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerChargeCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'employeeName') {
          e.hidden = hideBrokerInfo
        }
      })
      obj2.colModel.forEach(e => {
        if (e.dataIndx === 'brokerCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerageBranchCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerBranchName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerChargeCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'employeeName') {
          e.hidden = hideBrokerInfo
        }
      })
      obj4.colModel.forEach(e => {
        if (e.dataIndx === 'brokerCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerageBranchCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerBranchName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerChargeCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'employeeName') {
          e.hidden = hideBrokerInfo
        }
      })
      obj5.colModel.forEach(e => {
        if (e.dataIndx === 'brokerCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerageBranchCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerBranchName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerChargeCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'employeeName') {
          e.hidden = hideBrokerInfo
        }
      })
      obj6.colModel.forEach(e => {
        if (e.dataIndx === 'brokerCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerageBranchCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerBranchName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerChargeCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'employeeName') {
          e.hidden = hideBrokerInfo
        }
      })
      obj8.colModel.forEach(e => {
        if (e.dataIndx === 'brokerCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerBranchCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerBranchName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerChargeCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'employeeName') {
          e.hidden = hideBrokerInfo
        }
      })
    },
    initializeA001ResponseHandler(data) {
      this.$_logDebug(data)
      if (data.dataList.length) {
        this.form.orderListComment = data.dataList[0].orderListComment
      }
      this.selectShowGrid()
    },
    responseErrorHandlerA001(error) {
      this.$_logError(error)
    },
    responseA002Handler(data) {
      this.$_logDebug(data)
      this.pqGridOption.dataModel.data = (data.dataList[0] ? data.dataList[0].ifaOrderListA002ResponseDtoDomesticStockList : []) || []
      this.pqGridOption2.dataModel.data = (data.dataList[0] ? data.dataList[0].ifaOrderListA002ResponseDtoDomesticMutualFundList : []) || []
      this.pqGridOption3.dataModel.data = (data.dataList[0] ? data.dataList[0].ifaOrderListA002ResponseDtoSubscriptOrderList : []) || []
      this.pqGridOption4.dataModel.data = (data.dataList[0] ? data.dataList[0].ifaOrderListA002ResponseDtoForeignStockEntrustOrderList : []) || []
      this.pqGridOption5.dataModel.data = (data.dataList[0] ? data.dataList[0].ifaOrderListA002ResponseDtoForeignStockCounterOrderList : []) || []
      this.pqGridOption8.dataModel.data = (data.dataList[0] ? data.dataList[0].ifaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitmentList : []) || []
      this.$nextTick(() => {
        this.$refs['domesticStockGrid']?.refreshView(true)
        this.$refs['domesticInvestmentTrustisGrid']?.refreshView(true)
        this.$refs['domesticMutualFundRegularRecruitmentGrid']?.refreshView(true)
        this.$refs['domesticRecruitGrid']?.refreshView(true)
        this.$refs['foreignCommitGrid']?.refreshView(true)
        this.$refs['foreignStoreGrid']?.refreshView(true)
      })
      this.selectShowGrid()
    },
    responseA002ErrorHandler(error) {
      this.$_logError(error)
    },
    responseHandlerA005(data) {
      this.$_logDebug(data)
    },
    responseA005ErrorHandler(error) {
      this.$_logError(error)
    }
  }
}

// 商品：『国内株式（現物・信用・現引・現渡）』選択時の一覧
const obj = {
  width: 0,
  height: 0,
  title: '国内株式（現物・信用・現引・現渡）',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false
}
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
obj.colModel = [
  {
    title: '部店',
    dataIndx: 'butenCode',
    minWidth: 60,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.accountNumber ? ifaFormatUtils.zeroPadding(rowData.accountNumber, 7) : '-'
    }
  },
  {
    title: '取引コース',
    dataIndx: 'customerAttribute',
    minWidth: 230,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.customerAttribute ? getCodeValue('PRE_CONTRACT_DOC_CODE', 1, rowData.customerAttribute) : '-'
    }
  },
  {
    title: '顧客名(漢字)',
    dataIndx: 'customerNameKanji',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '顧客名(カナ)',
    dataIndx: 'customerNameKana',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: 'EC受注番号',
    dataIndx: 'ecOrderNo',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '銘柄コード',
    dataIndx: 'brandCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '銘柄名',
    dataIndx: 'brandName',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '市場',
    dataIndx: 'market',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      if (rowData.tradeCd === '7' || rowData.tradeCd === '8') {
        return '-'
      }
      return rowData.market ? getCodeValue('SELECT_MARKET', 2, rowData.market) : '-'
    }
  },
  {
    title: '訂正/取消区分',
    dataIndx: 'orderStatus',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.orderStatus ? getCodeValue('LIST_ORDER_STATUS', 1, rowData.orderStatus) : '-'
    }
  },
  {
    title: '取引種別',
    dataIndx: 'tradeCd',
    minWidth: 140,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.tradeCd ? getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, rowData.tradeCd) + rowData.paymentDeadlineCalculation : '-' + rowData.paymentDeadlineCalculation
    }
  },
  {
    title: '注文種別',
    dataIndx: 'orderClassList',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      if (rowData.tradeCd === '7' || rowData.tradeCd === '8') {
        return '-'
      }
      return rowData.orderClassList ? getCodeValue('LIST_ORDER_CLASS', 1, rowData.orderClassList) : '-'
    }
  },
  {
    title: '条件詳細',
    dataIndx: 'orderTerms',
    minWidth: 500,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      if (rowData.tradeCd === '7' || rowData.tradeCd === '8') {
        return '-'
      }
      return rowData.orderTerms || '-'
    }
  },
  {
    title: '預り区分/手数料',
    dataIndx: 'depositType',
    minWidth: 130,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      if (rowData.tradeCd === '7' || rowData.tradeCd === '8') {
        return getCodeValue('DOMESTIC_DEPOSIT_TYPE', 3, rowData.depositType) || '-'
      }
      const str1 = getCodeValue('DOMESTIC_DEPOSIT_TYPE', 3, rowData.depositType) ? getCodeValue('DOMESTIC_DEPOSIT_TYPE', 3, rowData.depositType) : '-'
      const str2 = getCodeValue('COMM_TYPE', 1, rowData.comIdR) ? getCodeValue('COMM_TYPE', 1, rowData.comIdR) : '-'
      return str1 + '/' + str2
    }
  },
  {
    title: '受注日時',
    dataIndx: 'orderDayTimeCalculation',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render({ rowData }) {
      return getFormattedDateTimeValue(rowData.orderDayTimeCalculation, 'datetime14') || '-'
    }
  },
  {
    title: '期間',
    dataIndx: 'yukoKigen',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      if (rowData.tradeCd === '7' || rowData.tradeCd === '8') {
        return '-'
      }
      return getFormattedDateValue(rowData.yukoKigen, 'date8') || '-'
    }
  },
  {
    title: '数量',
    dataIndx: 'quantity',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function({ rowData }) {
      return ifaFormatUtils.withCommaInteger(rowData.quantity) || '-'
    }
  },
  {
    title: '価格',
    dataIndx: 'sashine',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function({ rowData }) {
      if (rowData.tradeCd === '7' || rowData.tradeCd === '8') {
        return '-'
      } else {
        if (rowData.sasinariKbn === ' ' || rowData.sasinariKbn === 'Z' ||
        rowData.sasinariKbn === 'I' || rowData.sasinariKbn === 'F' || rowData.sasinariKbn === 'P') {
          return getCodeValue('LIMIT_MARKET_TYPE', 1, rowData.sasinariKbn) + ' ' + ifaFormatUtils.withCommaNoneZeroPadding(rowData.sashine) || '-'
        } else {
          return getCodeValue('LIMIT_MARKET_TYPE', 1, rowData.sasinariKbn) || '-'
        }
      }
    }
  },
  {
    title: '現在値',
    dataIndx: 'currentPrice',
    minWidth: 100,
    dataType: 'integer',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function({ rowData }) {
      return ifaFormatUtils.withCommaNoneZeroPadding(rowData.currentPrice) || '-'
    }
  },
  {
    title: '勧誘区分',
    dataIndx: 'kanyuKbn',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.kanyuKbn ? getCodeValue('INVITATION_TYPE', 1, rowData.kanyuKbn) : '-'
    }
  },
  {
    title: '受注方法',
    dataIndx: 'orderMethod',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.orderMethod ? getCodeValue('ORDER_METHOD_TYPE', 1, rowData.orderMethod) : '-'
    }
  },
  {
    title: '発注者',
    dataIndx: 'userName',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店コード',
    dataIndx: 'brokerageBranchCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店名',
    dataIndx: 'brokerBranchName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員コード',
    dataIndx: 'brokerChargeCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'employeeName',
    minWidth: 130,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '閲覧可能部店',
    dataIndx: 'viewAblrButenCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '商品区分',
    dataIndx: 'securityType',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    hidden: true,
    render: function() {
      return '0'
    }
  }
]

// // 商品：『国内投資信託』選択時の一覧
const obj2 = {
  width: 0,
  height: 0,
  title: '国内投資信託',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false
}
obj2.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
obj2.colModel = [
  {
    title: '部店',
    dataIndx: 'butenCode',
    minWidth: 60,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.accountNumber ? ifaFormatUtils.zeroPadding(rowData.accountNumber, 7) : '-'
    }
  },
  {
    title: '取引コース',
    dataIndx: 'customerAttribute',
    minWidth: 230,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.customerAttribute ? getCodeValue('PRE_CONTRACT_DOC_CODE', 1, rowData.customerAttribute) : '-'
    }
  },
  {
    title: '顧客名(漢字)',
    dataIndx: 'customerNameKanji',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '顧客名(カナ)',
    dataIndx: 'customerNameKana',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: 'EC受注番号',
    dataIndx: 'ecOrderNo',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '銘柄コード',
    dataIndx: 'fundCodeCalculation',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: 'ファンド名',
    dataIndx: 'fundName',
    minWidth: 200,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '取消区分',
    dataIndx: 'orderStatus',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.orderStatus ? getCodeValue('LIST_ORDER_STATUS', 1, rowData.orderStatus) : '-'
    }
  },
  {
    title: '取引種別',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'tradeCd',
    render: ({ rowData }) => {
      return rowData.tradeCd ? getCodeValue('DOMESTIC_MUTUAL_FUND_TRADE_CLASS', 1, rowData.tradeCd.trim()) : '-'
    }
  },
  {
    title: '預り区分',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'depositType',
    render: ({ rowData }) => {
      if (rowData.tradeKbn === 'K') {
        // 預り区分=(5:買付時：Jr特定/Jr一般　または △:特定/一般) であり、かつ (特定口座区分=(1:特定口座(代行納付) または 2:特定口座(確定申告)) でない)場合
        if ((rowData.depositType === '5' || rowData.depositType === ' ') && !(rowData.specificAccountType === '1' || rowData.specificAccountType === '2')) {
          return rowData.depositType ? getCodeValue('MUTUAL_FUND_DEPOSIT_TYPE', 7, rowData.depositType) : '-'
          // 上記以外
        } else {
          return rowData.depositType ? getCodeValue('MUTUAL_FUND_DEPOSIT_TYPE', 2, rowData.depositType) : '-'
        }
      } else {
        return rowData.depositType ? getCodeValue('MUTUAL_FUND_DEPOSIT_TYPE', 1, rowData.depositType) : '-'
      }
    }
  },
  {
    title: '受注日時',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'orderDayTimeCalculation',
    render({ rowData }) {
      return getFormattedDateTimeValue(rowData.orderDayTimeCalculation, 'datetime14') || '-'
    }
  },
  {
    title: '口数/金額',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'unitAmountCalculation'
  },
  {
    title: 'ポイント種別',
    minWidth: 140,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'pointType',
    render: ({ rowData }) => {
      return rowData.pointType ? getCodeValue('POINT_TYPE', 1, rowData.pointType) : '-'
    }
  },
  {
    title: '利用ポイント',
    render: function({ rowData }) {
      if (rowData.orderPoint) {
        return ifaFormatUtils.withCommaInteger(rowData.orderPoint)
      } else {
        return '0'
      }
    },
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'orderPoint'
  },
  {
    title: '分配金受取方法',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'distributionReceiveMethodDesignation',
    render: ({ rowData }) => {
      return rowData.distributionReceiveMethodDesignation ? getCodeValue('DISTRIBUTION_RECEIVE_METHOD', 4, rowData.distributionReceiveMethodDesignation) : '-'
    }
  },
  {
    title: '勧誘区分',
    dataIndx: 'kanyuKbn',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.kanyuKbn ? getCodeValue('INVITATION_TYPE', 1, rowData.kanyuKbn) : '-'
    }
  },
  {
    title: '受注方法',
    dataIndx: 'orderMethod',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.orderMethod ? getCodeValue('ORDER_METHOD_TYPE', 1, rowData.orderMethod) : '-'
    }
  },
  {
    title: 'コンプラ項目',
    dataIndx: 'orderOtherInfo',
    render: function(ui) {
      const v = JSON.stringify({
        ifaOrderNo: ui.rowData.ifaOrderNo,
        ifaOrderSubNo: ui.rowData.ifaOrderSubNo
      })
      return '<button type="button" class="el-button ifa-button el-button--default el-button--mini secondary-class" onclick="const btn = document.getElementById(\'other-info-button\');btn.value = \'' + encodeURI(v) + '\';  btn.click()" ><span class="__adjust_button_text">詳細</span></button>'
    },
    minWidth: 130,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店コード',
    dataIndx: 'brokerageBranchCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店名',
    dataIndx: 'brokerBranchName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員コード',
    dataIndx: 'brokerChargeCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'employeeName',
    minWidth: 130,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '閲覧可能部店',
    dataIndx: 'viewAblrButenCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '商品区分',
    dataIndx: 'securityType',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    hidden: true,
    render: function() {
      return '1'
    }
  },
  {
    title: '商品区分_国内投信',
    dataIndx: 'orderType',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    hidden: true
  },
  {
    title: '特定口座区分',
    dataIndx: 'specificAccountType',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    hidden: true
  }
]

// 商品：『国内株式（IPO/PO）』選択時の一覧
const obj4 = {
  width: 0,
  height: 0,
  title: '国内株式（IPO/PO）',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false
}
obj4.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
obj4.colModel = [
  {
    title: '部店',
    dataIndx: 'buten',
    minWidth: 60,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.accountNumber ? ifaFormatUtils.zeroPadding(rowData.accountNumber, 7) : '-'
    }
  },
  {
    title: '取引コース',
    dataIndx: 'customerAttribute',
    minWidth: 230,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.customerAttribute ? getCodeValue('PRE_CONTRACT_DOC_CODE', 1, rowData.customerAttribute) : '-'
    }
  },
  {
    title: '顧客名(漢字)',
    dataIndx: 'customerNameKanji',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '顧客名(カナ)',
    dataIndx: 'customerNameKana',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '銘柄コード',
    dataIndx: 'brandCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '銘柄名',
    dataIndx: 'brandName',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '注文状況',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'orderStatus',
    render: ({ rowData }) => {
      return rowData.orderStatus ? getCodeValue('SUBSCRIPT_ORDER_STATUS', 1, rowData.orderStatus) : '-'
    }
  },
  {
    title: '預り区分',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'depositType',
    render: ({ rowData }) => {
      return rowData.depositType ? getCodeValue('FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE', 1, rowData.depositType) : '-'
    }
  },
  {
    title: '受注日時',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'recruitmentOrderDate',
    render({ rowData }) {
      return getFormattedDateTimeValue(rowData.recruitmentOrderDate, 'datetime14') || '-'
    }
  },
  {
    title: '数量',
    render: function({ rowData }) {
      return ifaFormatUtils.withCommaInteger(rowData.quantity) || '-'
    },
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'quantity'
  },
  {
    title: '価格',
    render: function({ rowData }) {
      return ifaFormatUtils.withCommaNoneZeroPadding(rowData.issueBbPrice) || '-'
    },
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'issueBbPrice'
  },
  {
    title: '勧誘区分',
    dataIndx: 'kanyuKbn',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.kanyuKbn ? getCodeValue('INVITATION_TYPE', 1, rowData.kanyuKbn) : '-'
    }
  },
  {
    title: '受注方法',
    dataIndx: 'orderMethod',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.orderMethod ? getCodeValue('ORDER_METHOD_TYPE', 1, rowData.orderMethod) : '-'
    }
  },
  {
    title: '重要事項の説明',
    dataIndx: 'mostImportantMatter',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.mostImportantMatter ? getCodeValue('IMPORTANT_MATTERS_EXPLAIN', 1, rowData.mostImportantMatter) : '-'
    }
  },
  {
    title: 'ワーニング申請取引',
    dataIndx: 'warningApplyArranged',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.warningApplyArranged ? getCodeValue('WARNING_APPLICATION_TRAD', 1, rowData.warningApplyArranged) : '-'
    }
  },
  {
    title: '目論見書交付方法',
    render: function({ rowData }) {
      return rowData.prospectus ? getCodeValue('PROSPECTUS_ISSUE_METHOD', 2, rowData.prospectus) : '-'
    },
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'prospectus'
  },
  {
    title: '発注者',
    dataIndx: 'userName',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店コード',
    dataIndx: 'brokerageBranchCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店名',
    dataIndx: 'brokerBranchName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員コード',
    dataIndx: 'brokerChargeCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'employeeName',
    minWidth: 130,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '閲覧可能部店',
    dataIndx: 'viewAblrButenCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '商品区分',
    dataIndx: 'securityType',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    hidden: true,
    render: function() {
      return '2'
    }
  }
]

// 商品：『外国株式（委託注文）』選択時の一覧
const obj5 = {
  width: 0,
  height: '800px',
  title: '外国株式（委託注文）',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false
}
obj5.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
obj5.colModel = [
  {
    title: '部店',
    dataIndx: 'butenCode',
    minWidth: 60,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.accountNumber ? ifaFormatUtils.zeroPadding(rowData.accountNumber, 7) : '-'
    }
  },
  {
    title: '取引コース',
    dataIndx: 'customerAttribute',
    minWidth: 230,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.customerAttribute ? getCodeValue('PRE_CONTRACT_DOC_CODE', 1, rowData.customerAttribute) : '-'
    }
  },
  {
    title: '顧客名(漢字)',
    dataIndx: 'customerNameKanji',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '顧客名(カナ)',
    dataIndx: 'customerNameKana',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '注文番号',
    dataIndx: 'acceptOrderNumber',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: 'ティッカー/銘柄コード',
    minWidth: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'tickerCode'
  },
  {
    title: '銘柄名',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'brandName'
  },
  {
    title: '市場',
    dataIndx: 'marketCode',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.marketCode ? getCodeValue('FOREIGN_MARKET_TYPE', 1, rowData.marketCode) : '-'
    }
  },
  {
    title: '訂正/取消区分',
    dataIndx: 'securiytClassType',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.securiytClassType ? getCodeValue('LIST_ORDER_STATUS', 1, rowData.securiytClassType) : '-'
    }
  },
  {
    title: '取引種別',
    dataIndx: 'tradeCdCalculation',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.tradeCdCalculation ? getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, rowData.tradeCdCalculation) : '-'
    }
  },
  {
    title: '注文種別',
    dataIndx: 'priceConditionsType',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.priceConditionsType ? getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', 5, rowData.priceConditionsType) : '-'
    }
  },
  {
    title: '条件詳細',
    dataIndx: 'conditionDetailsCalculation',
    minWidth: 500,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '決済方法',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'settlementType',
    render: ({ rowData }) => {
      return rowData.settlementType ? getCodeValue('SETTLEMENT_TYPE', 1, rowData.settlementType) : '-'
    }
  },
  {
    title: '預り区分',
    dataIndx: 'depositType',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.depositType ? getCodeValue('FOREIGN_DEPOSIT_TYPE', 1, rowData.depositType) : '-'
    }
  },
  {
    title: '国内注文日時',
    dataIndx: 'orderTime',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render({ rowData }) {
      return getFormattedDateTimeValue(rowData.orderTime, 'datetime14') || '-'
    }
  },
  {
    title: '期間',
    dataIndx: 'orderDeadlineDate',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render({ rowData }) {
      return getFormattedDateValue(rowData.orderDeadlineDate, 'date8') || '-'
    }
  },
  {
    title: '数量',
    dataIndx: 'orderQuantity',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function({ rowData }) {
      return ifaFormatUtils.withCommaInteger(rowData.orderQuantity) || '-'
    }
  },
  {
    title: '価格',
    dataIndx: 'sashine',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function({ rowData }) {
      if (rowData.priceConditionsType === '2' || rowData.priceConditionsType === '4') {
        return '成行'
      } else if (rowData.priceConditionsType === '1' || rowData.priceConditionsType === '3') {
        return ifaFormatUtils.withCommaNoneZeroPadding(rowData.sashine) + (rowData.currencyCode || '')
      } else {
        return '-'
      }
    }
  },
  {
    title: '勧誘区分',
    dataIndx: 'kanyuKbn',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.kanyuKbn ? getCodeValue('FOREIGN_STOCK_INVITATION_TYPE', 1, rowData.kanyuKbn) : '-'
    }
  },
  {
    title: '受注方法',
    dataIndx: 'jutyuKbn',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.jutyuKbn ? getCodeValue('FOREIGN_STOCK_ORDER_METHOD_TYPE', 1, rowData.jutyuKbn) : '-'
    }
  },
  {
    title: '英文開示銘柄',
    dataIndx: 'engPubType',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.engPubType ? getCodeValue('ISSUES_DISCLOSED_IN_ENGLISH_BRAND', 3, rowData.engPubType) : '-'
    }
  },
  {
    title: '重要事項の説明',
    dataIndx: 'importantMatterTypeClass',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.importantMatterTypeClass ? getCodeValue('IMPORTANT_MATTERS_EXPLAIN', 1, rowData.importantMatterTypeClass) : '-'
    }
  },
  {
    title: 'ワーニング申請取引',
    dataIndx: 'warningApplyTrade',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.warningApplyTrade ? getCodeValue('WARNING_APPLICATION_TRAD', 1, rowData.warningApplyTrade) : '-'
    }
  },
  {
    title: '乗換え勧誘(ETF)',
    dataIndx: 'solicitationEtfType',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.solicitationEtfType ? getCodeValue('ETF_SOLICITING_TRANSFERS', 1, rowData.solicitationEtfType) : '-'
    }
  },
  {
    title: '発注者',
    dataIndx: 'userName',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店コード',
    dataIndx: 'brokerageBranchCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店名',
    dataIndx: 'brokerBranchName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員コード',
    dataIndx: 'brokerChargeCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'employeeName',
    minWidth: 130,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '閲覧可能部店',
    dataIndx: 'viewAblrButenCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '商品区分',
    dataIndx: 'securityType',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    hidden: true,
    render: function() {
      return '3'
    }
  },
  {
    title: '注文SUB番号_外株委託',
    dataIndx: 'acceptOrderSubNumber',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center',
    hidden: true
  }
]

// 商品：『外国株式（店頭注文）』選択時の一覧
const obj6 = {
  width: 0,
  height: 0,
  title: '外国株式（店頭注文）',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false
}
obj6.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
obj6.colModel = [
  {
    title: '部店',
    dataIndx: 'buten',
    minWidth: 60,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    minWidth: 100,
    dataType: 'integer',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.accountNumber ? ifaFormatUtils.zeroPadding(rowData.accountNumber, 7) : '-'
    }
  },
  {
    title: '取引コース',
    dataIndx: 'customerAttribute',
    minWidth: 230,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: ({ rowData }) => {
      return rowData.customerAttribute ? getCodeValue('PRE_CONTRACT_DOC_CODE', 1, rowData.customerAttribute) : '-'
    }
  },
  {
    title: '顧客名(漢字)',
    dataIndx: 'customerNameKanji',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '顧客名(カナ)',
    dataIndx: 'customerNameKana',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '管理番号',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'manageNumber'
  },
  {
    title: 'ティッカー',
    dataIndx: 'brandCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '銘柄名',
    dataIndx: 'brandName',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: 'ステータス',
    render: function({ rowData }) {
      return rowData.status ? getCodeValue('FOREIGN_STOCK_COUNTER_ORDER_STATUS', 1, rowData.status) : '-'
    },
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'status'
  },
  {
    title: '取消理由',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'cancelReason'
  },
  {
    title: '取引種別',
    dataIndx: 'tradeCdCalculation',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function({ rowData }) {
      return rowData.tradeCdCalculation ? getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, rowData.tradeCdCalculation) : '-'
    }
  },
  {
    title: '預り区分',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'depositType',
    render: function({ rowData }) {
      return rowData.depositType ? getCodeValue('FOREIGN_DEPOSIT_TYPE', 1, rowData.depositType) : '-'
    }
  },
  {
    title: '注文日時',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'orderDate',
    render({ rowData }) {
      return getFormattedDateTimeValue(rowData.orderDate, 'datetime14') || '-'
    }
  },
  {
    title: '約定日時',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'tradeTime',
    render({ rowData }) {
      return getFormattedDateTimeValue(rowData.tradeTime, 'datetime14') || '-'
    }
  },
  {
    title: '数量',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'orderQuantity',
    render: function({ rowData }) {
      return ifaFormatUtils.withCommaInteger(rowData.orderQuantity) || '-'
    }
  },
  {
    title: '価格',
    render: function({ rowData }) {
      return ifaFormatUtils.withCommaNoneZeroPadding(rowData.tradePrice) || '-'
    },
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'tradePrice'
  },
  {
    title: '勧誘区分',
    dataIndx: 'kanyuKbn',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.kanyuKbn ? getCodeValue('FOREIGN_STOCK_INVITATION_TYPE', 1, rowData.kanyuKbn) : '-'
    }
  },
  {
    title: '受注方法',
    dataIndx: 'orderMethod',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.orderMethod ? getCodeValue('FOREIGN_STOCK_ORDER_METHOD_TYPE', 1, rowData.orderMethod) : '-'
    }
  },
  {
    title: '英文開示銘柄',
    dataIndx: 'engPubBrand',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.engPubBrand ? getCodeValue('ISSUES_DISCLOSED_IN_ENGLISH_BRAND', 3, rowData.engPubBrand) : '-'
    }
  },
  {
    title: '重要事項の説明',
    dataIndx: 'importantMatter',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.importantMatter ? getCodeValue('IMPORTANT_MATTERS_EXPLAIN', 1, rowData.importantMatter) : '-'
    }
  },
  {
    title: 'ワーニング申請取引',
    dataIndx: 'warningTrade',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.warningTrade ? getCodeValue('WARNING_APPLICATION_TRAD', 1, rowData.warningTrade) : '-'
    }
  },
  {
    title: '外国証券情報交付方法',
    render: function({ rowData }) {
      return rowData.foreignSecurityInfoIssueMethod ? getCodeValue('FOREIGN_SECURITY_INFO_ISSUE_METHOD', 1, rowData.foreignSecurityInfoIssueMethod) : '-'
    },
    minWidth: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'foreignSecurityInfoIssueMethod'
  },
  {
    title: '乗換え勧誘(ETF)',
    dataIndx: 'switchingSolicitationEtf',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function({ rowData }) {
      return rowData.switchingSolicitationEtf ? getCodeValue('ETF_SOLICITING_TRANSFERS', 1, rowData.switchingSolicitationEtf) : '-'
    }
  },
  {
    title: '発注者',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'userName'
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店コード',
    dataIndx: 'brokerageBranchCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店名',
    dataIndx: 'brokerBranchName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員コード',
    dataIndx: 'brokerChargeCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'employeeName',
    minWidth: 130,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '閲覧可能部店',
    dataIndx: 'viewAblrButenCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '商品区分',
    dataIndx: 'securityType',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    hidden: true,
    render: function() {
      return '4'
    }
  }
]

// 商品：『外貨建MMF』選択時の一覧
// const obj3 = {
//   width: 0,
//   height: 0,
//   title: '外貨建MMF',
//   flexHeight: false,
//   flexWidth: false,
//   collapsible: false,
//   showTitle: true,
//   numberCell: { show: false },
//   topVisible: false,
//   selectionModel: { type: 'row', mode: 'single' },
//   wrap: false
// }

// obj3.pageModel = {
//   type: 'local',
//   curPage: 1,
//   rPP: 30,
//   rPPOptions: []
// }

// obj3.colModel = [
//   {
//     title: '部店',
//     dataIndx: 'butenCode',
//     minWidth: 60,
//     dataType: 'string',
//     editable: false,
//     halign: 'center',
//     align: 'center'
//   },
//   {
//     title: '口座番号',
//     dataIndx: 'accountNumber',
//     minWidth: 100,
//     dataType: 'integer',
//     editable: false,
//     halign: 'center'
//   },
//   {
//     title: '取引コース',
//     dataIndx: 'customerAttribute',
//     minWidth: 200,
//     dataType: 'string',
//     editable: false,
//     halign: 'center'
//   },
//   {
//     title: '顧客名(漢字)',
//     dataIndx: 'nameKanji',
//     minWidth: 250,
//     dataType: 'string',
//     editable: false,
//     halign: 'center'
//   },
//   {
//     title: '注文番号',
//     dataIndx: 'orderNo',
//     minWidth: 120,
//     dataType: 'string',
//     editable: false,
//     halign: 'center',
//     render: function(ui) {
//       this.$_logDebug(ui)
//     }
//   },
//   {
//     title: '銘柄コード',
//     render: function(ui) {
//       return '0Z41-0405469'
//     },
//     minWidth: 120,
//     dataType: 'string',
//     editable: false,
//     halign: 'center',
//     align: 'left'
//   },
//   {
//     title: 'ファンド名',
//     render: function(ui) {
//       return '南アフリカランドMMF'
//     },
//     minWidth: 200,
//     dataType: 'string',
//     editable: false,
//     halign: 'center'
//   },
//   {
//     title: '取消区分',
//     dataIndx: 'correctCancelKbn',
//     minWidth: 100,
//     dataType: 'string',
//     editable: false,
//     halign: 'center'
//   },
//   {
//     title: '売・買',
//     dataIndx: 'buyType',
//     minWidth: 100,
//     dataType: 'string',
//     editable: false,
//     halign: 'center'
//   },
//   {
//     title: '注文口数',
//     render: function(ui) {
//       return '500,000'
//     },
//     minWidth: 80,
//     dataType: 'string',
//     editable: false,
//     halign: 'center'
//   },
//   {
//     title: '注文金額',
//     render: function(ui) {
//       return '10,000'
//     },
//     minWidth: 120,
//     dataType: 'string',
//     editable: false,
//     halign: 'center'
//   },
//   {
//     title: '口座区分',
//     render: function(ui) {
//       return '特定'
//     },
//     minWidth: 120,
//     dataType: 'string',
//     editable: false,
//     halign: 'center'
//   },
//   {
//     title: '注文日時',
//     render: function(ui) {
//       return '2021/11/15  10:15:00'
//     },
//     minWidth: 120,
//     dataType: 'string',
//     editable: false,
//     halign: 'center'
//   },
//   {
//     title: '決済方法',
//     render: function(ui) {
//       return '円貨決算'
//     },
//     minWidth: 120,
//     dataType: 'string',
//     editable: false,
//     halign: 'center'
//   },
//   {
//     title: '勧誘区分',
//     dataIndx: 'invitationType',
//     minWidth: 150,
//     dataType: 'string',
//     editable: false,
//     halign: 'center',
//     align: 'left',
//     render: function(ui) {
//       let text = ''
//       if (ui.cellData === '0') {
//         text = '勧誘あり'
//       }
//       if (ui.cellData === '1') {
//         text = '勧誘なし'
//       }

//       return {
//         text: text
//       }
//     }
//   },
//   {
//     title: '受注方法',
//     dataIndx: 'orderMethod',
//     minWidth: 150,
//     dataType: 'string',
//     editable: false,
//     halign: 'center',
//     align: 'left',
//     render: function(ui) {
//       let text = ''
//       if (ui.cellData === '0') {
//         text = '店頭'
//       }
//       if (ui.cellData === '1') {
//         text = '訪問'
//       }
//       if (ui.cellData === '2') {
//         text = '電話他'
//       }

//       return {
//         text: text
//       }
//     }
//   },
//   {
//     title: '営業員コード',
//     dataIndx: 'intermediaryEmpCd',
//     minWidth: 100,
//     dataType: 'string',
//     editable: false,
//     halign: 'center',
//     align: 'center'
//   },
//   {
//     title: '営業員名',
//     dataIndx: 'brokerChargeName',
//     minWidth: 130,
//     dataType: 'string',
//     editable: false,
//     halign: 'center',
//     align: 'center'
//   },
//   {
//     title: '仲介業者コード',
//     dataIndx: 'brokerCode',
//     minWidth: 120,
//     dataType: 'string',
//     editable: false,
//     halign: 'center',
//     align: 'center'
//   },
//   {
//     title: '仲介業者名',
//     dataIndx: 'brokerName',
//     minWidth: 240,
//     dataType: 'string',
//     editable: false,
//     halign: 'center',
//     align: 'center'
//   },
//   {
//     title: '支店コード',
//     dataIndx: 'branchCode',
//     minWidth: 120,
//     dataType: 'string',
//     editable: false,
//     halign: 'center',
//     align: 'center'
//   },
//   {
//     title: '支店名',
//     dataIndx: 'branchName',
//     minWidth: 240,
//     dataType: 'string',
//     editable: false,
//     halign: 'center',
//     align: 'center'
//   },
//   {
//     title: '閲覧可能部店',
//     dataIndx: 'viewableButen',
//     minWidth: 100,
//     dataType: 'string',
//     editable: false,
//     halign: 'center',
//     align: 'center'
//   }
// ]

// 商品：『国内投資信託 （定期積立）』選択時の一覧
const obj8 = {
  width: 0,
  height: 0,
  title: '国内投資信託（定期積立）',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false
}
obj8.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
obj8.colModel = [
  {
    title: '部店',
    dataIndx: 'buten',
    minWidth: 60,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: ({ rowData }) => {
      return rowData.accountNumber ? ifaFormatUtils.zeroPadding(rowData.accountNumber, 7) : '-'
    }
  },
  {
    title: '取引コース',
    dataIndx: 'customerAttribute',
    minWidth: 230,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: ({ rowData }) => {
      return rowData.customerAttribute ? getCodeValue('PRE_CONTRACT_DOC_CODE', 1, rowData.customerAttribute) : '-'
    }
  },
  {
    title: '顧客名(漢字)',
    dataIndx: 'customerNameKanji',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '顧客名(カナ)',
    dataIndx: 'customerNameKana',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '銘柄コード',
    dataIndx: 'brandCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: 'ファンド名',
    dataIndx: 'fundName',
    minWidth: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '変更/解除区分',
    dataIndx: 'modifyCancelClassification',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: ({ rowData }) => {
      return rowData.modifyCancelClassification ? getCodeValue('FUND_RESERVE_MODIFY_KBN', 1, rowData.modifyCancelClassification) : '-'
    }
  },
  {
    title: '取引種別',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'tradeCd'
  },
  {
    title: '預り区分',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'depositType',
    render: ({ rowData }) => {
      return rowData.depositType ? getCodeValue('RESERVE_TRADE_DEPOSIT_TYPE', 3, rowData.depositType) : '-'
    }
  },
  {
    title: '決済方法',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'paymentMethod',
    render: ({ rowData }) => {
      return rowData.paymentMethod ? getCodeValue('FUND_RESERVE_PAYMENT_METHOD', 1, rowData.paymentMethod) : '-'
    }
  },
  {
    title: '受注日時',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'orderDayTimeCalculation',
    render: ({ rowData }) => {
      return getFormattedDateTimeValue(rowData.orderDayTimeCalculation, 'datetime14') || '-'
    }
  },
  {
    title: '積立コース',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'accumulateCourse'
  },
  {
    title: '設定金額',
    minWidth: 100,
    dataType: 'number',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'settingAmount',
    render: function({ rowData }) {
      return rowData.settingAmount ? ifaFormatUtils.withCommaInteger(rowData.settingAmount) + '円' : '-'
    }
  },
  {
    title: 'ボーナス月の設定',
    minWidth: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'bonusMonthSetting'
  },
  {
    title: 'NISA枠ぎりぎり注文',
    minWidth: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'nisaBarelyBuyingKbn',
    render: ({ rowData }) => {
      return rowData.nisaBarelyBuyingKbn ? getCodeValue('FUND_RESERVE_NISA_BARELY_BUYING_KBN', 1, rowData.nisaBarelyBuyingKbn) : '-'
    }
  },
  {
    title: '課税シフト注文',
    minWidth: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'nisaExcessBuyingKbn',
    render: ({ rowData }) => {
      return rowData.nisaExcessBuyingKbn ? getCodeValue('FUND_RESERVE_TAX_SHIFT_KBN', 1, rowData.nisaExcessBuyingKbn) : '-'
    }
  },
  {
    title: '1カ月あたりの積立金額',
    minWidth: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'oneMonthSumAmount',
    render: function({ rowData }) {
      return rowData.oneMonthSumAmount ? ifaFormatUtils.withCommaInteger(rowData.oneMonthSumAmount) + '円' : '-'
    }
  },
  {
    title: '次回発注予定日',
    minWidth: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    dataIndx: 'nextReserveDate',
    render({ rowData }) {
      return getFormattedDateValue(rowData.nextReserveDate, 'date8') || '-'
    }
  },
  {
    title: '発注者',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    dataIndx: 'client'
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店コード',
    dataIndx: 'brokerBranchCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店名',
    dataIndx: 'brokerBranchName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員コード',
    dataIndx: 'brokerChargeCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'employeeName',
    minWidth: 130,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '閲覧可能部店',
    dataIndx: 'visibleButenCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  }
]

</script>
<style lang="scss" scoped>
.search-form__item {
  margin: 0 0.2rem;
  width: 180px;
}
.middle_input {
  width: 120px;
}
.large_input {
  width: 330px;
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
  line-height: 2
}
.form_button {
  margin: 0 2rem 0.8rem 46px;
  padding: 0;
}
#gridButtonArea {
  margin: 0.5rem 0;
}
#contentAreaInput, #outputPaneMargins {
  margin: 0.5rem;
}
:deep(.el-form-item__error) {
  white-space: nowrap
}
:deep(.el-form-item) {
  margin-bottom: 1.2rem;
}
:deep(.el-dialog__wrapper) {
  z-index: 3000 !important;
}
:deep(.el-dialog__header) {
  text-align: center;
}
:deep(.el-dialog__title) {
  font-size: 20px;
  margin: 0px;
  font-weight: 600;
  padding: 0px;
}
:deep(.el-dialog__body) {
  color: #303133;
}
:deep(.detail-modal) .el-dialog {
  width: 800px;
  &__body table {
    width: 90%;
    margin: 0 auto;
    font-size: 16px;
    line-height: 24px;
  }
}
:deep(.loading-modal) .el-dialog {
  width: 500px;
}
.right_icon {
  text-align:right;
  margin-left:auto;
  display: inline-block;
  position: absolute;
  right: 20px;
  top: 10px;
}
:deep(.el-popove) {
  width: auto;
}
.statusOption.is-disabled  {
  color: #606266;
  cursor: default;
}
.__left {
  text-align: left;
}

:deep(.pq-grid) {
  width: 100% !important;
}
:deep(.pq-grid-title-row div) {
  width: 100%;
}
.el-icon {
  color: #005FCC;
  font-size: 30px;
}
.el-icon:hover {
  color: #409eff;
}
:deep(.form_label) .el-form-item__label[for="iOrderSelectorderList"] {
  width: 135px;
}
.order_select_margin :deep(.el-form-item) {
  margin-right: 0px;
  height: 32px;
}
.bottom_margin :deep(.el-form-item) {
  margin-bottom: 0px;
}
.brand_margin :deep(.el-form-item__label) {
  margin-left: 0px;
  width: 130px;
}

:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
