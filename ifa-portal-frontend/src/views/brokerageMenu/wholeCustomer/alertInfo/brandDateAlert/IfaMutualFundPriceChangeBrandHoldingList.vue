<template>
  <!-- 画面名：SUB020301_03-01_投信基準価額変動の銘柄保有一覧 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      :rules="rules"
    >
      <div>
        <el-row>
          <el-card class="content-card">
            <div class="filter-container">
              <el-row
                class="form_upper"
                style="margin-top:1rem"
              >
                <ifa-common-search
                  ref="commonSearchItem"
                  display-pattern="pt1"
                  list-pattern="pt1"
                  :form="form"
                  original-screen-id="SUB020301_03-01"
                  @mediate-user-privacy="mediateUserPrivacy"
                ></ifa-common-search>
              </el-row>
              <el-row
                class="form_upper"
              >
                <div class="form_label select_item_margin">
                  <ifa-input-select
                    v-model="form.compliantStatusType"
                    code-list-id="COMPLIANT_STATUS_TYPE"
                    prop="compliantStatusType"
                    label="ステータス"
                    size="small"
                    class="ifa-input__text-default search-form__item middle_input"
                    style="width: 180px;"
                    :disp-pattern="1"
                    :select-pattern="1"
                    :domain="IfaCompliantStatusTypeDomainModel"
                  ></ifa-input-select>
                </div>
                <el-form-item
                  class="form_label"
                >
                  <div style="height: 2rem;">
                    <ifa-input-text
                      id="investmentTrustAssociationCode"
                      v-model="form.investmentTrustAssociationCode"
                      label="投信協会コード"
                      type="text"
                      size="small"
                      prop="investmentTrustAssociationCode"
                      input-class="ifa-input__text-default middle_input"
                      style="width: 180px;"
                      :domain="IfaInvestmentAssociationCordDomainModel"
                    ></ifa-input-text>
                  </div>
                </el-form-item>
              </el-row>
              <el-row
                class="form_upper"
              >
                <el-col>
                  <ifa-date-range-picker
                    v-model="form.dateRange"
                    label="期間指定"
                    size="small"
                    unlink-panels
                    prop="dateRange"
                    :required="true"
                    :picker-options="pickerOptions"
                  ></ifa-date-range-picker>
                  <div style="margin:0 1rem 1rem 9.5rem;">{{ form.periodMsg }}</div>
                </el-col>
              </el-row>
              <el-row class="form_upper__actionBtn"
                      style="padding-left: 46px; padding-top: 10px"
              >
                <div>
                  <ifa-button
                    id="btnDisplay"
                    name="btnDisplay"
                    text="表示"
                    width="90"
                    search
                    small
                    action-id="SUB020301_03-01#A002"
                    action-type="requestAction"
                    :form="formRef"
                    :request-model="requestModelIfaMutualFundPriceChangeBrandHoldingListA002"
                    @response-handler="responseHandlerA002($event)"
                    @response-error-handler="handleErrorResponseA002($event)"
                  ></ifa-button>
                  <ifa-button
                    id="btnTopInputClear"
                    name="btnTopInputClear"
                    text="クリア"
                    width="90"
                    color="secondary"
                    small
                    action-type="originalAction"
                    @app-action-handler="handleClearClick"
                  ></ifa-button>
                </div>
                <div style="margin: 0.5rem 1rem; color: red; font-weight: bold;">{{ form.periodAlert }}</div>
              </el-row>
            </div>
          </el-card>
        </el-row>
        <el-row>
          <el-card
            class="content-card"
          >
            <div class="gridButtonArea">
              <ifa-button
                id="ifaResponseStatusUpdateInitializeA001"
                text="更新"
                :disabled="disabledUpdateBtn"
                small
                width="90"
                :form="formRef"
                action-id="SUB020301_03-01_1#A001"
                action-type="requestAction"
                :request-model="requestModelIfaResponseStatusUpdateA001"
                @response-handler="responseHandlerStatusUpdateA001($event)"
              ></ifa-button>
              <ifa-button
                id="btnCsvDownload"
                name="btnCsvDownload"
                :disabled="disabledCsvBtn"
                text="CSV出力"
                color="primary"
                width="90"
                small
                action-id="SUB020301_03-01#A004"
                :form="formRef"
                :request-model="requestModelIfaMutualFundPriceChangeBrandHoldingListA004"
                csv-file-name="投信基準価額変動の銘柄保有一覧"
                action-type="outputCsvAction"
                :is-check-csv-download-allowed="true"
                :is-check-csv-download-privacy-confirmation="true"
              ></ifa-button>

            </div>
            <grid-table
              ref="pqGrid5"
              :options="pqGridOption5per"
              :auto-refresh="false"
              @click="handleClickGrid5"
            ></grid-table>
            <br>
            <grid-table
              ref="pqGrid10"
              :options="pqGridOption10per"
              :auto-refresh="false"
              @click="handleClickGrid10"
            ></grid-table>
          </el-card>
        </el-row>
      </div>
    </el-form>
    <!--更新画面-->
    <ifa-mutual-fund-price-change-brand-holding-list-update
      ref="SUB020301_03-01_1"
      :is-visible="updateVisible"
      @close-modal="updateVisible = false"
      @update-confirm="dialogComfirmVisible=true"
      @confirm-ok="handleConfirmOk"
    ></ifa-mutual-fund-price-change-brand-holding-list-update>

    <!-- 確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="dialogComfirmVisible"
      :title="comfirmTitle"
      :message="comfirmMessage"
      @close-modal-ok="updateFinish"
      @close-modal-cancel="dialogComfirmVisible = false"
    >
    </ifa-ok-cancel-dialog>
    <ifa-requester
      id="IfaMutualFundPriceChangeBrandHoldingListA002"
      action-id="SUB020301_03-01#A002"
      action-type="requestAction"
      :request-model="requestModelIfaMutualFundPriceChangeBrandHoldingListA002IfaWholePortalHome"
      @response-handler="responseHandlerA002($event)"
      @response-error-handler="handleErrorResponseA002($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaMutualFundPriceChangeBrandHoldingListUpdate from '@/views/brokerageMenu/wholeCustomer/alertInfo/brandDateAlert/IfaMutualFundPriceChangeBrandHoldingListUpdate.vue'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog'
import IfaInvestmentAssociationCordDomainModel from '@/resource/domain/IfaInvestmentAssociationCordDomainModel.json'
import IfaCompliantStatusTypeDomainModel from '@/resource/domain/IfaCompliantStatusTypeDomainModel.json'
import { IfaMutualFundPriceChangeBrandHoldingListFormModel } from './js/IfaMutualFundPriceChangeBrandHoldingListFormModel'
import { IfaMutualFundPriceChangeBrandHoldingListA002RequestModel } from './js/IfaMutualFundPriceChangeBrandHoldingListA002RequestModel'
import { IfaMutualFundPriceChangeBrandHoldingListA004RequestModel } from './js/IfaMutualFundPriceChangeBrandHoldingListA004RequestModel'
import { IfaResponseStatusUpdateA001RequestModel } from './js/IfaResponseStatusUpdateA001RequestModel'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'
import { validateDateRangeFromTo, validateDateRangeBeforeMonths } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    GridTable,
    IfaCommonSearch,
    screenTitle,
    IfaMutualFundPriceChangeBrandHoldingListUpdate,
    IfaOkCancelDialog
  },
  data() {
    return {
      form: new IfaMutualFundPriceChangeBrandHoldingListFormModel(),
      formRef: {},
      IfaCompliantStatusTypeDomainModel: IfaCompliantStatusTypeDomainModel,
      IfaInvestmentAssociationCordDomainModel: IfaInvestmentAssociationCordDomainModel,
      disabledCsvBtn: true,
      disabledUpdateBtn: true,
      updateVisible: false,
      dialogComfirmVisible: false,
      comfirmTitle: '更新確認',
      comfirmMessage: '対応状況を更新します。よろしいですか。',
      rules: {
        dateRange: [
          {
            validator: this.periodDateValidator,
            trigger: 'blur'
          }
        ]
      },
      pickerOptions: {
        shortcuts: [
          {
            text: '前営業日',
            value: () => {
              return [this.previousBusinessDay, this.previousBusinessDay]
            }
          },
          {
            text: '当月',
            value: () => {
              // startDateに当月の月初を設定
              const startDate = new Date()
              startDate.setDate(1)
              startDate.setHours(0, 0, 0, 0)
              // endDateに前営業日を設定
              // 前営業日が前月の場合には、当月初日を設定する
              const businessDate = new Date(this.previousBusinessDay)
              const endDate = businessDate.getMonth() !== startDate.getMonth() ? startDate : this.previousBusinessDay
              return [startDate, endDate]
            }
          }, {
            text: '前月',
            value: () => {
              // startDateに前月の月初を設定
              const startDate = new Date()
              startDate.setHours(0, 0, 0, 0)
              startDate.setMonth(startDate.getMonth() - 1, 1)
              // endDateに前月の月末を設定
              const endDate = new Date()
              endDate.setHours(0, 0, 0, 0)
              endDate.setDate(0)
              return [startDate, endDate]
            }
          }
        ]
      },
      pqGridOption5per: getConvertedOption(obj5per),
      pqGridOption10per: getConvertedOption(obj10per),
      serchFormIfaWholePortalHome: {
        brokerCode: '',
        chkBrokerCodeExclude: false,
        branchCode: '',
        empCode: '',
        butenCode: '',
        accountNumber: '',
        customerNameKanjiKana: '',
        customerNameKanjiKanaTerms: '',
        courseSelected: [],
        compliantStatusType: '$NULL',
        investmentTrustAssociationCode: '',
        dateRange: ['', '']
      }
    }
  },
  computed: {
    previousBusinessDay() {
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    },
    requestModelIfaMutualFundPriceChangeBrandHoldingListA002() {
      return new IfaMutualFundPriceChangeBrandHoldingListA002RequestModel(
        this.form
      )
    },
    requestModelIfaMutualFundPriceChangeBrandHoldingListA002IfaWholePortalHome() {
      return new IfaMutualFundPriceChangeBrandHoldingListA002RequestModel(
        this.serchFormIfaWholePortalHome
      )
    },
    requestModelIfaMutualFundPriceChangeBrandHoldingListA004() {
      return new IfaMutualFundPriceChangeBrandHoldingListA004RequestModel(
        this.form
      )
    },
    requestModelIfaResponseStatusUpdateA001() {
      return new IfaResponseStatusUpdateA001RequestModel(
        this.form.pqGrid5perSelectedInfo
      )
    },
    userInfo() {
      return this.$store.getters.userAccount
    }
  },
  created() {
    this.pqGridOption5per.wrap = true
    this.pqGridOption10per.wrap = true
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow(resume, isRouting) {
      this.form.dateRange = [//  期間指定_入力 【初期値】前営業日の日付（From,Toどちらも）
        this.$_getFormattedDateValue(this.previousBusinessDay),
        this.$_getFormattedDateValue(this.previousBusinessDay)
      ]
      // 初期化
      this.disabledUpdateBtn = true
      this.disabledCsvBtn = true
      this.pqGridOption5per.dataModel.data = []
      this.pqGridOption10per.dataModel.data = []
      this.$refs['pqGrid5'].refreshView(true)
      this.$refs['pqGrid10'].refreshView(true)

      // 総合ポータル_ホームからの遷移
      if (isRouting) {
        const dateRange = this.$store.getters.pageInfo.params
        this.serchFormIfaWholePortalHome.dateRange = dateRange
        const updatedCourseSelected = this.form.courseSelected.map(course => ({
          ...course,
          isSelected: true
        }))
        this.serchFormIfaWholePortalHome.courseSelected = updatedCourseSelected
        this.$nextTick(() => {
          document.getElementById('IfaMutualFundPriceChangeBrandHoldingListA002').click()
        })
      }
    },
    handleClearClick() {
      // クリアボタン押下
      this.$refs.commonSearchItem.formClear()
      this.form.customerNameKanjiKanaTerms = '3' // 顧客名（漢字／カナ）_条件
      this.form.compliantStatusType = '$NULL' // ステータス_入力 【初期値】"NULL：(未設定)"
      this.form.investmentTrustAssociationCode = '' // 投信協会コード_入力 【初期値】""
      this.form.dateRange = [this.previousBusinessDay, this.previousBusinessDay] //  期間指定_入力 【初期値】前営業日の日付（From,Toどちらも）
      // バリエーションクリア
      this.$refs['form'].clearValidate()
    },
    responseHandlerA002(response) {
      // 一覧へのデータの反映
      if (response.dataList.length > 0 &&
        (response.dataList[0].compare5ParcentDeclineBrandList.length > 0 || response.dataList[0].oneMonth10PercentDeclineBrandList.length > 0)
      ) {
        this.disabledCsvBtn = false
        this.pqGridOption5per.dataModel.data = response.dataList[0].compare5ParcentDeclineBrandList || []
        this.pqGridOption10per.dataModel.data = response.dataList[0].oneMonth10PercentDeclineBrandList || []
      } else {
        this.disabledUpdateBtn = true
        this.disabledCsvBtn = true
        this.pqGridOption5per.dataModel.data = []
        this.pqGridOption10per.dataModel.data = []
      }
      this.$refs['pqGrid5'].refreshView(true)
      this.$refs['pqGrid10'].refreshView(true)
    },
    handleErrorResponseA002(response) {
      this.pqGridOption5per.dataModel.data = []
      this.pqGridOption10per.dataModel.data = []
      this.$refs['pqGrid5'].refreshView(true)
      this.$refs['pqGrid10'].refreshView(true)
    },
    responseHandlerStatusUpdateA001(response) {
      this.updateVisible = true
      this.$nextTick(() => {
        this.$refs['SUB020301_03-01_1'].onShow(response.dataList[0])
      })
    },
    handleClickGrid5(ui) {
      // 明細押下
      if ((ui.rowData.statusClassification === '0' || ui.rowData.statusClassification === '2') ||
        (ui.rowData.statusClassification === '1' &&
          (this.userInfo.medUsers?.privId === '1' ||
          this.userInfo.medUsers?.privId === '2' ||
          this.userInfo.medUsers?.privId === '3' ||
          this.userInfo.medUsers?.privId === '6')
        )
      ) {
        this.disabledUpdateBtn = false
      } else {
        this.disabledUpdateBtn = true
      }

      // 前日比5%下落銘柄一覧 の明細を選択した場合に、明細に紐づくA005のリクエスト値を設定
      this.form.pqGrid5perSelectedInfo.declineRateKbn = ui.rowData.declineRateKbn
      this.form.pqGrid5perSelectedInfo.butenCode = ui.rowData.butenCode
      this.form.pqGrid5perSelectedInfo.accountNumber = ui.rowData.accountNumber
      this.form.pqGrid5perSelectedInfo.investmentTrustAssociationCode = ui.rowData.investmentTrustAssociationCode
      this.form.pqGrid5perSelectedInfo.standardDateTo = ui.rowData.standardDateTo

      // 行選択時に1ヶ月10%下落銘柄一覧のデータグリッドの行が既に選択されていた場合、1ヶ月10%下落銘柄一覧のデータグリッドの行選択を解除する。
      const objIndex = this.pqGridOption10per.dataModel.data.findIndex(obj => obj?.pq_rowselect === true)
      if (objIndex !== -1) {
        this.$nextTick(() => {
          // eslint-disable-next-line camelcase
          this.pqGridOption10per.dataModel.data[objIndex].pq_rowselect = false
          this.$refs['pqGrid10'].refreshView(true)
        })
      }
    },
    handleClickGrid10() {
      this.disabledUpdateBtn = true

      // A005のリクエスト値を初期化
      this.form.pqGrid5perSelectedInfo.declineRateKbn = ''
      this.form.pqGrid5perSelectedInfo.butenCode = ''
      this.form.pqGrid5perSelectedInfo.accountNumber = ''
      this.form.pqGrid5perSelectedInfo.investmentTrustAssociationCode = ''
      this.form.pqGrid5perSelectedInfo.standardDateTo = ''

      // 行選択時に前日比5%下落銘柄一覧のデータグリッドの行が既に選択されていた場合、前日比5%下落銘柄一覧のデータグリッドの行選択を解除する。
      const objIndex = this.pqGridOption5per.dataModel.data.findIndex(obj => obj?.pq_rowselect === true)
      if (objIndex !== -1) {
        this.$nextTick(() => {
          // eslint-disable-next-line camelcase
          this.pqGridOption5per.dataModel.data[objIndex].pq_rowselect = false
          this.$refs['pqGrid5'].refreshView(true)
        })
      }
    },
    updateFinish() {
      this.dialogComfirmVisible = false
      this.updateVisible = false
    },
    mediateUserPrivacy(data) {
      // 共通検索条件.営業員コード自動設定判定 = '0':自動設定なし の場合のみカラムを表示する。
      if (data.empCodeAutoDispFlag === '0') {
        this.setHidden('brokerCode', false)
        this.setHidden('brokerName', false)
        this.setHidden('brokerageBranchCode', false)
        this.setHidden('brokerBranchName', false)
        this.setHidden('brokerChargeCode', false)
        this.setHidden('employeeName', false)
      } else {
        this.setHidden('brokerCode', true)
        this.setHidden('brokerName', true)
        this.setHidden('brokerageBranchCode', true)
        this.setHidden('brokerBranchName', true)
        this.setHidden('brokerChargeCode', true)
        this.setHidden('employeeName', true)
      }
      this.$refs['pqGrid5'].refreshView(true)
      this.$refs['pqGrid10'].refreshView(true)
    },
    setHidden(dataIndx, value) {
      const colModel5per = this.pqGridOption5per.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel5per === 'object') {
        colModel5per.hidden = value
      }

      const colModel10per = this.pqGridOption10per.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel10per === 'object') {
        colModel10per.hidden = value
      }
    },
    handleConfirmOk() {
      // 更新完了後に再検索
      this.$nextTick(() => {
        document.querySelector('#btnDisplay').click()
      })
    },
    // 期間指定のバリデーションチェック処理
    periodDateValidator(rule, value, callback) {
      // 以下の条件の時エラー
      // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい
      if (validateDateRangeFromTo(this.form.dateRange, 6)) {
        callback(getMessage('errors.dateRange', ['期間指定', '6ヶ月']))
        return
      }
      // リクエスト.期間指定（From）がシステム日付-6ヶ月より小さい　または
      // リクエスト.期間指定（To）がシステム日付-6ヶ月より小さい
      if (validateDateRangeBeforeMonths(this.form.dateRange, 6)) {
        callback(getMessage('errors.dateRange', ['期間指定', '6ヶ月']))
        return
      }
      // OK
      callback()
    }
  }
}

const obj5per = {
  width: 0,
  height: 0,
  title: '前日比5%下落銘柄一覧',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' }
}
obj5per.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}

obj5per.colModel = [
  {
    title: 'ステータス',
    width: 120,
    dataType: 'string',
    dataIndx: 'statusClassification',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      // ⑩重要マークを算出する。
      const value = ui.rowData.statusClassification
      if (value) {
        const codeValue = getCodeValue('COMPLIANT_STATUS_TYPE', 1, value)
        if (codeValue === '未対応' || codeValue === '外務員対応済') {
          return '<sapn style="color: red;">' + codeValue + '</sapn>'
        } else {
          return codeValue
        }
      } else {
        return '-'
      }
    }
  },
  {
    title: '対応方法',
    width: 120,
    dataType: 'string',
    dataIndx: 'responseMethodClassification',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const value = ui.rowData.responseMethodClassification
      return value ? getCodeValue('MEANS_TYPE', 1, value) : '-'
    }
  },
  {
    title: '対応方法「その他」の内容',
    width: 200,
    dataType: 'string',
    dataIndx: 'otherContentsClassification',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      const value = ui.rowData.otherContentsClassification
      return value ? getCodeValue('METHOD_OTHER_CONTENTS', 1, value) : '-'
    }
  },
  {
    title: '「その他」の詳細（自由記入）',
    width: 250,
    dataType: 'string',
    dataIndx: 'otherDetail',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '顧客対応日',
    width: 120,
    dataType: 'string',
    dataIndx: 'customerResponseDate',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      return ui.rowData.customerResponseDate ? getFormattedDateValue(ui.rowData.customerResponseDate) : '-'
    }
  },
  {
    title: '対応完了確認日',
    width: 120,
    dataType: 'string',
    dataIndx: 'responseFinishConfirmDate',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      return ui.rowData.responseFinishConfirmDate ? getFormattedDateValue(ui.rowData.responseFinishConfirmDate) : '-'
    }
  },
  {
    title: '部店',
    width: 60,
    dataType: 'string',
    dataIndx: 'butenCode',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '口座番号',
    width: 100,
    dataType: 'string',
    dataIndx: 'accountNumber',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      return ui.rowData.accountNumber ? ifaFormatUtils.zeroPadding(ui.rowData.accountNumber, 7) : '-'
    }
  },
  {
    title: '取引コース',
    dataIndx: 'customerAttribute',
    width: 150,
    editable: false,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      const value = ui.rowData.customerAttribute
      return value ? getCodeValue('PRE_CONTRACT_DOC_CODE', 1, value) : '-'
    }
  },
  {
    title: '顧客名(漢字)',
    width: 180,
    dataType: 'string',
    dataIndx: 'customerNameKanji',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '顧客名(カナ)',
    width: 180,
    dataType: 'string',
    dataIndx: 'customerNameKana',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '扱者コード',
    width: 90,
    dataType: 'string',
    dataIndx: 'dealerNumber',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '仲介業者コード',
    width: 120,
    dataType: 'string',
    dataIndx: 'brokerCode',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '仲介業者名',
    width: 250,
    dataType: 'string',
    dataIndx: 'brokerName',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '支店コード',
    dataIndx: 'brokerageBranchCode',
    editable: false,
    halign: 'center',
    align: 'left',
    width: 90
  },
  {
    title: '支店名',
    dataIndx: 'brokerBranchName',
    editable: false,
    halign: 'center',
    align: 'left',
    width: 250
  },
  {
    title: '営業員コード',
    dataIndx: 'brokerChargeCode',
    editable: false,
    halign: 'center',
    align: 'left',
    width: 110
  },
  {
    title: '営業員名',
    width: 180,
    dataType: 'string',
    dataIndx: 'employeeName',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '投信協会コード',
    width: 110,
    dataType: 'string',
    dataIndx: 'investmentTrustAssociationCode',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '銘柄名',
    width: 400,
    dataType: 'string',
    dataIndx: 'mutualFundBrandName',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '基準日',
    width: 110,
    dataType: 'string',
    dataIndx: 'standardDateTo',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      return ui.rowData.standardDateTo ? getFormattedDateValue(ui.rowData.standardDateTo) : '-'
    }
  },
  {
    title: '基準価額',
    width: 110,
    dataType: 'string',
    dataIndx: 'basePriceTo',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ui.rowData.basePriceTo ? ifaFormatUtils.withCommaInteger(ui.rowData.basePriceTo) : '-'
    }
  },
  {
    title: '前日比',
    width: 110,
    dataType: 'string',
    dataIndx: 'diff',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      const grid = this
      const data = ui.rowData.diff
      if (data) {
        if (Number(data) >= 0) {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__plus bold' })
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__minus bold' })
        }
        return ifaFormatUtils.signedWithCommaInteger(data) + '円'
      } else {
        return '-'
      }
    }
  },
  {
    title: '下落率',
    width: 110,
    dataType: 'string',
    dataIndx: 'rateOfDecline',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ui.rowData.rateOfDecline ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.rateOfDecline, 2) + '%' : '-'
    }
  }
]

const obj10per = {
  width: 0,
  height: 0,
  title: '1ヶ月10%下落銘柄一覧',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' }
}
obj10per.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
obj10per.colModel = [
  {
    title: '部店',
    width: 60,
    dataType: 'string',
    dataIndx: 'butenCode',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '口座番号',
    width: 100,
    dataType: 'string',
    dataIndx: 'accountNumber',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      return ui.rowData.accountNumber ? ifaFormatUtils.zeroPadding(ui.rowData.accountNumber, 7) : '-'
    }
  },
  {
    title: '取引コース',
    dataIndx: 'customerAttribute',
    width: 150,
    editable: false,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      const value = ui.rowData.customerAttribute
      return value ? getCodeValue('PRE_CONTRACT_DOC_CODE', 1, value) : '-'
    }
  },
  {
    title: '顧客名(漢字)',
    width: 180,
    dataType: 'string',
    dataIndx: 'customerNameKanji',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '顧客名(カナ)',
    width: 180,
    dataType: 'string',
    dataIndx: 'customerNameKana',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '扱者コード',
    width: 90,
    dataType: 'string',
    dataIndx: 'dealerNumber',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '仲介業者コード',
    width: 120,
    dataType: 'string',
    dataIndx: 'brokerCode',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '仲介業者名',
    width: 250,
    dataType: 'string',
    dataIndx: 'brokerName',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '支店コード',
    dataIndx: 'brokerageBranchCode',
    editable: false,
    halign: 'center',
    align: 'left',
    width: 90
  },
  {
    title: '支店名',
    dataIndx: 'brokerBranchName',
    editable: false,
    halign: 'center',
    align: 'left',
    width: 250
  },
  {
    title: '営業員コード',
    dataIndx: 'brokerChargeCode',
    editable: false,
    halign: 'center',
    align: 'left',
    width: 110
  },
  {
    title: '営業員名',
    width: 180,
    dataType: 'string',
    dataIndx: 'employeeName',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '投信協会コード',
    width: 110,
    dataType: 'string',
    dataIndx: 'investmentTrustAssociationCode',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '銘柄名',
    width: 400,
    dataType: 'string',
    dataIndx: 'mutualFundBrandName',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '基準日（F）',
    width: 110,
    dataType: 'string',
    dataIndx: 'standardDateFrom',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      return ui.rowData.standardDateFrom ? getFormattedDateValue(ui.rowData.standardDateFrom) : '-'
    }
  },
  {
    title: '基準価額（F）',
    width: 110,
    dataType: 'string',
    dataIndx: 'basePriceFrom',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ui.rowData.basePriceFrom ? ifaFormatUtils.withCommaInteger(ui.rowData.basePriceFrom) : '-'
    }
  },
  {
    title: '基準日（T）',
    width: 110,
    dataType: 'string',
    dataIndx: 'standardDateTo',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      return ui.rowData.standardDateTo ? getFormattedDateValue(ui.rowData.standardDateTo) : '-'
    }
  },
  {
    title: '基準価額（T）',
    width: 110,
    dataType: 'string',
    dataIndx: 'basePriceTo',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ui.rowData.basePriceTo ? ifaFormatUtils.withCommaInteger(ui.rowData.basePriceTo) : '-'
    }
  },
  {
    title: '前日比',
    width: 110,
    dataType: 'string',
    dataIndx: 'diff',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      const grid = this
      const data = ui.rowData.diff
      if (data) {
        if (Number(data) >= 0) {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__plus bold' })
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__minus bold' })
        }
        return ifaFormatUtils.signedWithCommaInteger(data) + '円'
      } else {
        return '-'
      }
    }
  },
  {
    title: '下落率',
    width: 110,
    dataType: 'string',
    dataIndx: 'rateOfDecline',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ui.rowData.rateOfDecline ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.rateOfDecline, 2) + '%' : '-'
    }
  }
]
</script>

<style scoped>
.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 200px;
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
}
.content-card {
  margin: 0.5rem 1rem;
}
.filter-container {
  margin-bottom:1rem;
  margin-top:1rem;
}
.gridButtonArea {
  margin-bottom: 10px;
}
:deep(.form_upper_label) .el-form-item__label {
  width: 135px;
}
.form_upper__actionBtn{
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.select_item_margin :deep(.el-form-item) {
  margin-right: 10px !important;
  margin-bottom: 1.2rem;
}
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
