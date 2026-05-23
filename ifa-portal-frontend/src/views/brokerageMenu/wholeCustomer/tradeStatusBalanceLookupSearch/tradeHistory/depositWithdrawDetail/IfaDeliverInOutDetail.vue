<template>
  <!-- 画面名：入出庫明細 -->
  <div>
    <screen-title :text="form.title"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      :rules="rules"
    >
      <el-card
        class="content-card"
        shadow="always"
      >
        <div class="filter-container">
          <el-row>
            <el-col
              :span="23"
            >
              <ifa-common-search
                ref="commonSearchItem"
                :form="form"
                display-pattern="pt1"
                list-pattern="pt2"
                :course-validate="true"
                :add-internet-to-courses="'on'"
                original-screen-id="SUB020302_0204-01"
                @mediate-user-privacy="mediateUserPrivacy"
              ></ifa-common-search>
            </el-col>
          </el-row>
          <el-row>
            <!-- 期間指定 /-->
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
            <span
              style="position: initial; padding-top: 9px;"
            >
              ※期間は6ヶ月以内を指定してください。（過去2年間の履歴を照会いただけます。）
            </span>
          </el-row>
        </div>

        <!-- 検索用ボタン -->
        <el-row>
          <el-col
            :span="8"
            class="search_btn-area"
          >
            <ifa-button
              id="btnDisplay"
              name="btnDisplay"
              text="表示"
              search
              small
              width="90"
              :request-model="IfaDeliverInOutDetailA002RequestModel"
              :form="formRef"
              action-id="SUB020302_0204-01#A002"
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
          </el-col>
        </el-row>
        <!-- /検索用ボタン -->
      </el-card>
      <el-row>
        <el-card class="content-card">
          <el-row>
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
                :request-model="IfaDeliverInOutDetailA004RequestModel"
                :form="formRef"
                action-id="SUB020302_0204-01#A004"
                action-type="outputCsvAction"
                csv-file-name="入出庫明細"
                :is-check-csv-download-allowed="true"
                :is-check-csv-download-privacy-confirmation="true"
              ></ifa-button>
            </div>
          </el-row>
          <div class="pq-grid-title">入出庫明細</div>
          <grid-table
            ref="gridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getDefaultOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaDeliverInOutDetailFormModel } from './js/IfaDeliverInOutDetailFormModel.js'
import { IfaDeliverInOutDetailA002RequestModel } from './js/IfaDeliverInOutDetailA002RequestModel.js'
import { IfaDeliverInOutDetailA004RequestModel } from './js/IfaDeliverInOutDetailA004RequestModel.js'
import { IfaDeliverInOutDetailA005RequestModel } from './js/IfaDeliverInOutDetailA005RequestModel.js'
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
      form: new IfaDeliverInOutDetailFormModel(),
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
      pqGridOption: null,
      pqGridSelectedInfo: {},
      activeBtn: true,
      csvbtn: true,
      formRef: {},
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
    IfaDeliverInOutDetailA002RequestModel() {
      return new IfaDeliverInOutDetailA002RequestModel(this.form)
    },
    IfaDeliverInOutDetailA004RequestModel() {
      return new IfaDeliverInOutDetailA004RequestModel(this.form)
    },
    IfaDeliverInOutDetailA005RequestModel() {
      return new IfaDeliverInOutDetailA005RequestModel(this.form)
    },
    column() {
      const columnValue = [
        { title: '仲介業者名', dataType: 'string', dataIndx: 'brokerName', width: '300', editable: false, halign: 'center', align: 'left' },
        { title: '営業員コード', dataType: 'string', dataIndx: 'empCode', width: '120', halign: 'center', align: 'left' },
        { title: '営業員名', dataType: 'string', dataIndx: 'brokerChargeName', width: '100', halign: 'center', align: 'left' },
        { title: '部店', dataType: 'string', dataIndx: 'buten', width: '100', halign: 'center', align: 'left' },
        { title: '口座番号', dataType: 'string', dataIndx: 'accountNumber', width: '100', halign: 'center', align: 'left' },
        { title: '取引コース', dataType: 'string', dataIndx: 'course', width: '180', halign: 'center', align: 'left',
          codeValue: { codeListId: 'PRE_CONTRACT_DOC_CODE', dispPattern: 1 }
        },
        { title: '顧客名（漢字）', dataType: 'string', dataIndx: 'customerNameKanji', width: '250', halign: 'center', align: 'left' },
        { title: '顧客名（カナ）', dataType: 'string', dataIndx: 'customerNameKana', width: '250', halign: 'center', align: 'left' },
        { title: '銘柄名', dataType: 'string', dataIndx: 'brandName', width: '300', halign: 'center', align: 'left' },
        { title: '商品区分', dataType: 'string', dataIndx: 'securityType', width: '100', halign: 'center', align: 'left',
          codeValue: { codeListId: 'DELIVER_IN_OUT_SECURITY_TYPE', dispPattern: 1 }
        },
        { title: '入出庫区分', dataType: 'string', dataIndx: 'deliverInOutClassification', width: '120', halign: 'center', align: 'left',
          codeValue: { codeListId: 'DELIVER_IN_OUT_TYPE', dispPattern: 1 }
        },
        { title: '数量', dataType: 'string', dataIndx: 'quantity', width: '200', halign: 'center', align: 'right',
          render: function(ui) {
            if (ui.rowData.quantity) {
              return ifaFormatUtils.withCommaInteger(ui.rowData.quantity)
            } else {
              return '-'
            }
          }
        },
        { title: '入出庫日時価', dataType: 'string', dataIndx: 'checkInOutPrice', width: '200', halign: 'center', align: 'right',
          render: function(ui) {
            if (ui.rowData.checkInOutPrice) {
              return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.checkInOutPrice)
            } else {
              return '-'
            }
          }
        },
        { title: '合計金額', dataType: 'string', dataIndx: 'totalAmount', width: '200', halign: 'center', align: 'right',
          render: function(ui) {
            if (ui.rowData.totalAmount) {
              return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.totalAmount)
            } else {
              return '-'
            }
          }
        },
        { title: '入出庫日', dataType: 'string', dataIndx: 'checkInOutDay', width: '200', halign: 'center', align: 'left',
          render: function(ui) {
            if (ui.rowData.checkInOutDay) {
              return getFormattedDateValue(ui.rowData.checkInOutDay, 'date8')
            } else {
              return '-'
            }
          }
        },
        { title: '仲介業者コード', dataType: 'string', dataIndx: 'brokerCode', width: '120', halign: 'center', align: 'left' },
        { title: '支店コード', dataType: 'string', dataIndx: 'branchCode', width: '100', halign: 'center', align: 'left' },
        { title: '支店名', dataType: 'string', dataIndx: 'branchName', width: '200', halign: 'center', align: 'left' }
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
        this.form.period = [this.previousBusinessDay, this.previousBusinessDay]
      }
      this.isInfoMessage = true
      setTimeout(() => {
        this.isInfoMessage = false
      }
      , 3000
      )
      this.pqGridOption.dataModel.data = []
    },
    onHide() {
      this.isInfoMessage = false
    },
    clearA003() {
      this.$refs.form.clearValidate()
      this.$refs.commonSearchItem.formClear()
      this.form.isDepositWithdraw = true
      this.form.period = [this.todayDate, this.todayDate]
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
      if (response.dataList.length > 0 && response.dataList[0].deliverInOutDetail.length > 0) {
        // CSVボタン活性化の権限
        //  - 権限1(SBI証券本店)
        //  - 権限2(SBI証券支店)
        //  - 権限3(仲介業者　-　内部管理責任者)
        if (this.userInfo.medUsers.privId === '1' || this.userInfo.medUsers.privId === '2' || this.userInfo.medUsers.privId === '3') {
          this.csvbtn = false
        }
        this.pqGridOption.dataModel.data = response.dataList[0].deliverInOutDetail
        this.$refs['gridTable'].refreshView(true)
      // レスポンスが0件
      } else {
        // CSVボタン非活性化 ＆ テーブルの中身を空に
        this.csvbtn = true
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView(true)
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
:deep(.search-form__item) {
  margin: 0 0.2rem 0 0.2rem;
  width: 160px;
}
.filter-container {
  margin-bottom:1rem;
  margin-top:1rem;
}
.content-card {
  margin: 0.5rem 1rem;
  padding: 1rem 0;
}

.middle_input {
  width: 120px;
}

.gridButtonArea {
  margin-bottom: 10px;
}

:deep(.form_label) .el-form-item__label {
  width: 120px;
}

:deep(.el-form-item__label) {
  margin-right: 1rem;
  padding-right: 0px;
}

.swithArea {
  padding-left: 50px;
  margin-bottom: 20px;
  margin-top: 15px;
}

:deep(.el-switch__label) * {
  font-weight: bold;
}

/* :deep(.el-select>.el-input) {
  width: 22em;
} */
.search_btn-area {
  padding: 10px 10px;;
}
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
