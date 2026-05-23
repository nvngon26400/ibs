<template>
  <!-- 画面名：入出金明細 -->
  <div>
    <screen-title :text="form.title"></screen-title>
    <div class="ifa-search-view__main-default">
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
                  <div>
                    <el-row>
                      <el-col :span="23">
                        <ifa-common-search
                          ref="commonSearchItem"
                          :form="form"
                          display-pattern="pt1"
                          list-pattern="pt2"
                          :course-validate="true"
                          :add-internet-to-courses="'on'"
                          original-screen-id="SUB020302_0203-01"
                          @mediate-user-privacy="mediateUserPrivacy"
                        ></ifa-common-search>
                      </el-col>
                    </el-row>

                    <!--区分 /-->
                    <el-row>
                      <el-form-item
                        label="区分"
                        class="form_label"
                        style="margin-bottom: 0;"
                      >
                        <ifa-input-radio
                          v-model="form.depositWithdrawDetailType"
                          code-list-id="DEPOSIT_WITHDRAW_DETAIL_TYPE"
                          :select-pattern="1"
                          :disp-pattern="1"
                        ></ifa-input-radio>
                      </el-form-item>
                    </el-row>

                    <!-- 期間指定 -->
                    <el-row>
                      <el-form-item
                        class="form_label"
                      >
                        <ifa-date-range-picker
                          v-model="form.period"
                          unlink-panels
                          label="期間指定"
                          :picker-options="pickerOptions"
                          size="small"
                          style="position: initial;"
                          required
                          prop="period"
                        ></ifa-date-range-picker>
                      </el-form-item>
                      <span style="position: initial; margin-top: 8px">※期間は6ヶ月以内を指定してください。（過去2年間の履歴を照会いただけます。）</span>
                    </el-row>

                    <!-- 検索用ボタン -->
                    <div
                      id="indicator-display"
                      class="form_button"
                    >
                      <ifa-button
                        id="btnDisplay"
                        name="btnDisplay"
                        text="表示"
                        search
                        small
                        width="90"
                        :request-model="IfaDepositWithdrawDetailA002RequestModel"
                        :form="formRef"
                        action-id="SUB020302_0203-01#A002"
                        action-type="requestAction"
                        :pre-request-handler="preRequestHandlerDisplayA002"
                        @response-handler="responseHandlerDisplayA002($event)"
                      ></ifa-button>
                      <ifa-button
                        id="btnTopInputClear"
                        name="btnTopInputClear"
                        text="クリア"
                        small
                        width="90"
                        color="secondary"
                        action-type="originalAction"
                        @app-action-handler="clearA003"
                      ></ifa-button>
                    </div>
                  </div>
                  <div class="filter-container">
                  </div>
                </el-form>
              </el-card>
            </div>
          </div>
        </div>
      </div>

      <!-- 入出金合計 -->
      <div>
        <div>
          <div id="outputPaneMargins">
            <el-card>
              <el-row>
                <!-- 出金 -->
                <el-col
                  :span="10"
                  style="margin-right: 4rem;"
                >
                  <table
                    class="_table__body"
                    style="width: 100%; max-width: 600px;"
                  >
                    <tbody>
                      <tr>
                        <th
                          class="_table__header __left"
                          style="width: 33%;"
                        >出金額合計</th>
                        <td
                          class="_table__data __right"
                          style="width: 41%;"
                        >{{ $_withCommaInteger(form.withdrawTotalAmount) }}</td>
                        <td
                          class="_table__data __right"
                          style="width: 16%;"
                        >{{ $_withCommaInteger(form.withdrawTotalCount) }}件</td>
                      </tr>
                      <tr>
                        <th class="_table__header __left">振替出金額合計</th>
                        <td class="_table__data __right">{{ $_withCommaInteger(form.transferWithdrawTotalAmount) }}</td>
                        <td class="_table__data __right">{{ $_withCommaInteger(form.transferWithdrawTotalCount) }}件</td>
                      </tr>
                    </tbody>
                  </table>
                </el-col>

                <!-- 入金 -->
                <el-col :span="10">
                  <table
                    class="_table__body"
                    style="margin-right: 4rem; width: 100%; max-width: 600px;"
                  >
                    <tbody>
                      <tr>
                        <th
                          class="_table__header __left"
                          style="width: 33%;"
                        >入金額合計</th>
                        <td
                          class="_table__data __right"
                          style="width: 50%;"
                        >{{ $_withCommaInteger(form.depositTotalAmount) }}</td>
                        <td
                          class="_table__data __right"
                          style="width: 16%;"
                        >{{ $_withCommaInteger(form.depositTotalCount) }}件</td>
                      </tr>
                      <tr>
                        <th class="_table__header __left">振替入金額合計</th>
                        <td class="_table__data __right">{{ $_withCommaInteger(form.transferDepositTotalAmount) }}</td>
                        <td class="_table__data __right">{{ $_withCommaInteger(form.transferDepositTotalCount) }}件</td>
                      </tr>
                    </tbody>
                  </table>
                </el-col>
              </el-row>
            </el-card>
          </div>
        </div>
      </div>

      <div id="paneDivider">
        <div id="paneBottom">
          <div>
            <div id="outputPaneMargins">
              <el-card id="outputTable">
                <div class="gridButtonArea">
                  <ifa-button
                    id="btmCustomerPortal"
                    name="btmCustomerPortal"
                    :disabled="activeBtn"
                    text="顧客別メニュー"
                    color="primary"
                    small
                    action-type="originalAction"
                    @app-action-handler="customerMenuA005"
                  ></ifa-button>
                  <ifa-button
                    id="btnCsvDownload"
                    text="CSV出力"
                    :disabled="csvbtn"
                    small
                    :request-model="IfaDepositWithdrawDetailA004RequestModel"
                    :form="formRef"
                    action-id="SUB020302_0203-01#A004"
                    action-type="outputCsvAction"
                    csv-file-name="入出金明細"
                    :is-check-csv-download-allowed="true"
                    :is-check-csv-download-privacy-confirmation="true"
                  ></ifa-button>
                </div>
                <div class="pq-grid-title">入出金明細</div>
                <grid-table
                  ref="gridTable"
                  :options="pqGridOption"
                  :auto-refresh="false"
                  @click="handleClick"
                ></grid-table>
              </el-card>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getDefaultOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaDepositWithdrawDetailFormModel } from './js/IfaDepositWithdrawDetailFormModel.js'
import { IfaDepositWithdrawDetailA002RequestModel } from './js/IfaDepositWithdrawDetailA002RequestModel.js'
import { IfaDepositWithdrawDetailA004RequestModel } from './js/IfaDepositWithdrawDetailA004RequestModel.js'
import { IfaDepositWithdrawDetailA005RequestModel } from './js/IfaDepositWithdrawDetailA005RequestModel.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getMessage } from '@/utils/errorHandler'
import { notifyMessage } from '@/utils/errorHandler'
import { isAccessible } from '@/utils/controlAuth'
import { validateDateRangeFromTo, validateDateRangeBeforeMonths } from '@/components/Date/js/IfaDatePickerFunction.js'

export default {
  components: {
    GridTable,
    IfaCommonSearch,
    screenTitle
  },
  data() {
    return {
      isInfoMessage: false,
      form: new IfaDepositWithdrawDetailFormModel(),
      formRef: {},
      pqGridOption: null,
      pqGridSelectedInfo: {},
      activeNames: 1,
      activeBtn: true,
      csvbtn: true,
      calcTotal: false,
      rules: {
        period: [
          {
            required: true,
            type: 'array',
            message: getMessage('errors.required', ['期間指定']),
            trigger: 'blur'
          },
          {
            validator: this.periodDateValidator,
            trigger: 'blur'
          }
        ]
      },
      loading: '',
      todayDate: '',
      pickerOptions: {
        shortcuts: [
          {
            text: '前日',
            value: () => {
              return [this.previousBusinessDay, this.previousBusinessDay]
            }
          }, {
            text: '当月',
            value: () => {
              // startDateに当月の月初を設定
              const startDate = new Date()
              startDate.setDate(1)
              startDate.setHours(0, 0, 0, 0)
              // endDateに前営業日を設定
              // 前営業日が前月の場合には、当月初日を設定する
              const businessDate = new Date(this.previousBusinessDay)
              const endDate =
               businessDate.getMonth() !== startDate.getMonth() ? startDate : this.previousBusinessDay
              return [startDate, endDate]
            }
          },
          {
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
      }
    }
  },
  computed: {
    userInfo() {
      return this.$store.getters.userAccount
    },
    previousBusinessDay() {
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    },
    IfaDepositWithdrawDetailA002RequestModel() {
      return new IfaDepositWithdrawDetailA002RequestModel(this.form)
    },
    IfaDepositWithdrawDetailA004RequestModel() {
      return new IfaDepositWithdrawDetailA004RequestModel(this.form)
    },
    IfaDepositWithdrawDetailA005RequestModel() {
      return new IfaDepositWithdrawDetailA005RequestModel(this.form)
    },
    column() {
      const columnValue = [
        { title: '部店', dataType: 'string', dataIndx: 'buten', width: '120', halign: 'center', align: 'left' },
        { title: '口座番号', dataType: 'string', dataIndx: 'accountNumber', width: '120', halign: 'center', align: 'left' },
        { title: '取引コース', dataType: 'string', dataIndx: 'course', width: '300', halign: 'center', align: 'left',
          codeValue: { codeListId: 'PRE_CONTRACT_DOC_CODE', dispPattern: 1 }
        },
        { title: '顧客名（漢字）', dataType: 'string', dataIndx: 'customerNameKanji', width: '120', halign: 'center', align: 'left' },
        { title: '顧客名（カナ）', dataType: 'string', dataIndx: 'customerNameKana', width: '120', halign: 'center', align: 'left' },
        { title: '入出金日', dataType: 'string', dataIndx: 'depositsAndWithdrawalsDate', width: '120', halign: 'center', align: 'left',
          render: function(ui) {
            if (ui.rowData.depositsAndWithdrawalsDate) {
              return getFormattedDateValue(ui.rowData.depositsAndWithdrawalsDate, 'date8')
            } else {
              return '-'
            }
          }
        },
        { title: '取引', dataType: 'string', dataIndx: 'openTradeKbn', width: '120', halign: 'center', align: 'left',
          codeValue: { codeListId: 'DEPOSIT_WITHDRAW_DETAIL_TYPE', dispPattern: 1 }
        },
        { title: '摘要', dataType: 'string', dataIndx: 'dispAbstract', width: '300', halign: 'center', align: 'left' },
        { title: '出金額', dataType: 'string', dataIndx: 'deliveryAmount', width: '120', halign: 'center', align: 'right',
          render: function(ui) {
            if (ui.rowData.openTradeKbn === '2') {
              if (ui.rowData.deliveryAmount) {
                return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.deliveryAmount)
              } else {
                return '-'
              }
            } else {
              return '-'
            }
          }
        },
        { title: '入金額', dataType: 'string', dataIndx: 'deliveryAmount', width: '120', halign: 'center', align: 'right',
          render: function(ui) {
            if (ui.rowData.openTradeKbn === '1') {
              if (ui.rowData.deliveryAmount) {
                return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.deliveryAmount)
              } else {
                return '-'
              }
            } else {
              return '-'
            }
          }
        },
        { title: '振替出金額', dataType: 'string', dataIndx: 'deliveryAmount', width: '120', halign: 'center', align: 'right',
          render: function(ui) {
            if (ui.rowData.openTradeKbn === '4') {
              if (ui.rowData.deliveryAmount) {
                return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.deliveryAmount)
              } else {
                return '-'
              }
            } else {
              return '-'
            }
          }
        },
        { title: '振替入金額', dataType: 'string', dataIndx: 'deliveryAmount', width: '120', halign: 'center', align: 'right',
          render: function(ui) {
            if (ui.rowData.openTradeKbn === '3') {
              if (ui.rowData.deliveryAmount) {
                return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.deliveryAmount)
              } else {
                return '-'
              }
            } else {
              return '-'
            }
          }
        },
        { title: '営業員コード', dataType: 'string', dataIndx: 'empCode', width: '120', halign: 'center', align: 'left' },
        { title: '営業員名', dataType: 'string', dataIndx: 'brokerChargeName', width: '120', halign: 'center', align: 'left' },
        { title: '仲介業者コード', dataType: 'string', dataIndx: 'brokerCode', width: '120', halign: 'center', align: 'left' },
        { title: '仲介業者名', dataType: 'string', dataIndx: 'brokerName', width: '300', editable: false, halign: 'center', align: 'left' },
        { title: '支店コード', dataType: 'string', dataIndx: 'branchCode', width: '120', halign: 'center', align: 'left' },
        { title: '支店名', dataType: 'string', dataIndx: 'branchName', width: '300', halign: 'center', align: 'left' }
      ]
      return columnValue
    }
  },
  created() {
    this.pqGridOption = getDefaultOption(this.column)
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow(resume) {
      if (!resume) {
        this.todayDate = this.$_getFormattedDateValue(this.$store.getters.requestedTime)
        this.form.period = [this.previousBusinessDay, this.todayDate]
      }
      this.isInfoMessage = true
      setTimeout(() => {
        this.isInfoMessage = false
      }
      , 3000
      )
      this.pqGridOption.dataModel.data = []
    },
    clearA003() {
      this.$refs.commonSearchItem.formClear()
      this.$refs['form'].clearValidate()
      this.form.isDepositWithdraw = true
      this.todayDate = this.$_getFormattedDateValue(this.$store.getters.requestedTime)
      this.form.period = [this.previousBusinessDay, this.todayDate]
      this.form.depositWithdrawDetailType = '0'
      this.form.withdrawTotalAmount = '0'
      this.form.withdrawTotalCount = '0'
      this.form.depositTotalAmount = '0'
      this.form.depositTotalCount = '0'
      this.form.transferWithdrawTotalAmount = '0'
      this.form.transferWithdrawTotalCount = '0'
      this.form.transferDepositTotalAmount = '0'
      this.form.transferDepositTotalCount = '0'
      this.activeBtn = true
      this.csvbtn = true
      this.$nextTick(() => {
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView(true)
      })
    },
    handleClick(param) {
      this.pqGridSelectedInfo = param
      this.activeBtn = false
    },
    customerMenuA005() {
      // 遷移先画面のアクセス権限チェック
      if (isAccessible(this.$customerMenuAccessCheckScreenId)) {
        const query = Object.assign({}, this.$route.query)
        query.userName = this.pqGridSelectedInfo.rowData.customerNameKanji
        const params = {
          accountNumber: this.pqGridSelectedInfo.rowData.accountNumber,
          butenCode: this.pqGridSelectedInfo.rowData.buten
        }
        this.$_startShowMenu(this.$customerMenuInitialScreenId, params)
      } else {
        notifyMessage(
          -1,
          getMessage('errors.cmn.loginUsers.insufficientPrivilege'),
          this.form.title
        )
      }
    },
    preRequestHandlerDisplayA002() {
      this.activeBtn = true
      this.csvbtn = true
    },
    responseHandlerDisplayA002(response) {
      // レスポンスが1件以上
      if (response.dataList.length > 0 && response.dataList[0].depositWithdrawDetail.length > 0) {
        // CSVボタン活性化の権限
        //  - 権限1(SBI証券本店)
        //  - 権限2(SBI証券支店)
        //  - 権限3(仲介業者　-　内部管理責任者)
        if (this.userInfo.medUsers.privId === '1' || this.userInfo.medUsers.privId === '2' || this.userInfo.medUsers.privId === '3') {
          this.csvbtn = false
        }
        Object.assign(this.form, response.dataList[0])
        this.pqGridOption.dataModel.data = this.form.depositWithdrawDetail
        this.$refs['gridTable'].refreshView(true)
      // レスポンスが0件
      } else {
        // CSVボタン非活性化 ＆ テーブルの中身を空に
        this.csvbtn = true
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView(true)
        this.form.withdrawTotalAmount = '0'
        this.form.withdrawTotalCount = '0'
        this.form.depositTotalAmount = '0'
        this.form.depositTotalCount = '0'
        this.form.transferWithdrawTotalAmount = '0'
        this.form.transferWithdrawTotalCount = '0'
        this.form.transferDepositTotalAmount = '0'
        this.form.transferDepositTotalCount = '0'
      }
    },
    // 自動入力フラグを受取り、カラムの表示制御を行う
    mediateUserPrivacy(data) {
      if (data.empCodeAutoDispFlag === '0') {
        this.setHidden('brokerName', false)
        this.setHidden('empCode', false)
        this.setHidden('brokerChargeName', false)
        this.setHidden('brokerCode', false)
        this.setHidden('branchCode', false)
        this.setHidden('branchName', false)
      } else {
        this.setHidden('brokerName', true)
        this.setHidden('empCode', true)
        this.setHidden('brokerChargeName', true)
        this.setHidden('brokerCode', true)
        this.setHidden('branchCode', true)
        this.setHidden('branchName', true)
      }
      this.$refs['gridTable'].refreshView(true)
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    },
    // 期間指定のバリデーションチェック処理
    periodDateValidator(rule, value, callback) {
      // 以下の条件の時エラー
      // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい
      if (validateDateRangeFromTo(this.form.period, 6)) {
        callback(getMessage('errors.dateRange', ['期間指定', '過去2年間の範囲で6ヶ月']))
        return
      }
      // リクエスト.期間指定（From）がシステム日付-2年より小さい　または
      // リクエスト.期間指定（To）がシステム日付-2年より小さい
      if (validateDateRangeBeforeMonths(this.form.period, 24)) {
        callback(getMessage('errors.dateRange', ['期間指定', '過去2年間の範囲で6ヶ月']))
        return
      }
      // OK
      callback()
    }
  }
}
</script>
<style scoped>
.search-form__item {
  margin: 0 0.2rem;
  width: 180px;
}
.middle_input {
  width: 120px;
}
:deep(.form_label) .el-form-item__label {
  width: 120px;
}
:deep(.el-form-item__content) {
  vertical-align: middle;
}
.form_button {
  margin: 0 2rem 0.8rem 2.5rem;
  padding: 0;
}
.gridButtonArea {
  margin-bottom: 10px;
}
#contentAreaInput, #outputPaneMargins {
  margin: 0.5rem;
}
:deep(.el-form-item__error) {
  white-space: nowrap
}
:deep(.el-form-item) {
  margin-bottom: 1rem;
}
.el-icon-info {
  font-size: 30px;
}
:deep(.el-icon-info:hover) {
  color: #409eff;
}
._table__body {
  width: 1000px;
  float: left;
  margin: 0.5rem;
  font-size: 12px;
}
._table__header {
  padding: 4px 14px 2px;
}
._main_header {
  background-color: #bcbdc4;
}
._sub_header {
  background-color: #d2d4db;
}
._border {
  border: 2px solid #000000;
}
._border-left {
  border-left: 2px solid #000000;
}
._border-right {
  border-right: 2px solid #000000;
}
._slim-border {
   border: 1px solid black;
}
._slim-border_horizontal {
   border-bottom: 1px solid black;
   border-top: 1px solid black;
}
._slim-border_right {
   border-right: 1px solid black;
}
.none_border-top {
  border-top: 0px solid black;
}
._table__data {
  background-color: white;
}
.filter-container {
  padding: 0 1rem;
}
.detail-search {
  padding: 1rem;
}
.el-select, .el-select .el-input, .el-select .el-input__inner {
  height: 32px;
}
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
