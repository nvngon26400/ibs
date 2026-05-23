<template>
  <!-- 画面名：米株信用建玉期日アラート -->
  <div>
    <screen-title text="米株信用建玉期日アラート一覧"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
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
                  :form="form"
                  display-pattern="pt1"
                  list-pattern="pt2"
                  original-screen-id="SUB020301_02-02"
                ></ifa-common-search>
              </el-row>
              <el-row style="padding-left: 46px; padding-top: 10px; display: block;">
                <ifa-button
                  id="btnDisplay"
                  name="btnDisplay"
                  text="表示"
                  width="90"
                  search
                  small
                  action-type="requestAction"
                  :form="formRef"
                  :pre-request-handler="preSearch"
                  :request-model="ifaForeignMarginPositionDueDateAlertA002RequestModel"
                  action-id="SUB020301_02-02#A002"
                  @response-handler="responseA002Handler"
                  @response-error-handler="responseA002ErrorHandler"
                ></ifa-button>
                <ifa-button
                  id="btnTopInputClear"
                  name="btnTopInputClear"
                  text="クリア"
                  width="90"
                  color="white"
                  action-type="originalAction"
                  small
                  @app-action-handler="handleClear"
                ></ifa-button>
                <span style="color:#ff1e00; font-weight: bold; float:right; padding-top: 5px;">※下記アラートは、全て前営業日基準です。リアルタイムの情報は、詳細リンクを押下し遷移先画面をご確認下さい。<br>
                  ※表示される建玉の返済期限が当日のため、速やかに顧客への連絡をお願いいたします。</span>
              </el-row>
            </div>
          </el-card>
        </el-row>
        <el-row>
          <el-card class="content-card">
            <grid-table
              ref="pqGrid"
              :options="pqGridOption"
              :auto-refresh="false"
              @click="handleClick"
            ></grid-table>
          </el-card>
        </el-row>
      </div>
    </el-form>
    <ifa-requester
      id="ifaForeignMarginPositionDueDateAlertA002"
      action-type="requestAction"
      :request-model="ifaForeignMarginPositionDueDateAlertA002RequestModelmIfaWholePortalHome"
      action-id="SUB020301_02-02#A002"
      @response-handler="responseA002Handler"
      @response-error-handler="responseA002ErrorHandler"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { colDefine } from '@/views/brokerageMenu/wholeCustomer/alertInfo/IfaRemainPowerAlertGridTable'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import { IfaForeignMarginPositionDueDateAlertA002RequestModel } from './js/IfaForeignMarginPositionDueDateAlertA002RequestModel.js'
export default {
  components: {
    GridTable,
    screenTitle,
    IfaCommonSearch
  },
  data() {
    return {
      formRef: {},
      foreignCreditOpenInterestDueDateAlert: [],
      form: {
        brokerCode: '',
        chkBrokerCodeExclude: false,
        branchCode: '',
        empCode: '',
        butenCode: '',
        accountNumber: '',
        customerNameKanjiKana: '',
        customerNameKanjiKanaTerms: '3',
        courseSelected: []
      },
      serchFormIfaWholePortalHome: {
        brokerCode: '',
        chkBrokerCodeExclude: false,
        branchCode: '',
        empCode: '',
        butenCode: '',
        accountNumber: '',
        customerNameKanjiKana: '',
        customerNameKanjiKanaTerms: '',
        courseSelected: []
      },
      pqGridOption: getConvertedOption(obj)
    }
  },
  computed: {
    ifaForeignMarginPositionDueDateAlertA002RequestModel() {
      return new IfaForeignMarginPositionDueDateAlertA002RequestModel(this.form)
    },
    ifaForeignMarginPositionDueDateAlertA002RequestModelmIfaWholePortalHome() {
      return new IfaForeignMarginPositionDueDateAlertA002RequestModel(this.serchFormIfaWholePortalHome)
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow(resume, isRouting) {
      this.pqGridOption.dataModel.data = []
      this.$nextTick(() => {
        this.$refs['pqGrid'].refreshView(true)
      })
      // 総合ポータル_ホームからの遷移
      if (isRouting) {
        const updatedCourseSelected = this.form.courseSelected.map(course => ({
          ...course,
          isSelected: true
        }))
        this.serchFormIfaWholePortalHome.courseSelected = updatedCourseSelected
        this.$nextTick(() => {
          document.getElementById('ifaForeignMarginPositionDueDateAlertA002').click()
        })
      }
    },
    handleClear() {
      this.$refs.form.clearValidate()
      this.$refs.commonSearchItem.formClear()
    },
    preSearch() {
      this.onShow()
      this.ifaForeignMarginPositionDueDateAlertA002RequestModel.customerNameKanjiKanaTerms = this.form.customerNameKanjiKanaTerms - 1
    },
    handleClick({ rowData: { buten, accountNumber }, dataIndx }) {
      if (dataIndx === 'detail') {
        this.$_startShowMenu('SUB0202_010203', { butenCode: buten, accountNumber })
      }
    },
    getAutoDisplay({ empCodeAutoDispFlag }) {
      const hideBrokerInfo = (empCodeAutoDispFlag !== '0')
      obj.colModel.forEach(e => {
        if (e.dataIndx === 'brokerCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'branchCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'branchName') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerChargeCode') {
          e.hidden = hideBrokerInfo
        } else if (e.dataIndx === 'brokerChargeName') {
          e.hidden = hideBrokerInfo
        }
      })
    },
    responseA002Handler(data) {
      this.$_logDebug(data)
      const dataList = data.dataList || []
      this.pqGridOption.dataModel.data = dataList.map(e => {
        return {
          brokerCode: e.brokerCode,
          brokerName: e.brokerName,
          branchCode: e.brokerageBranchCode,
          branchName: e.brokerBranchName,
          brokerChargeCode: e.empCode,
          brokerChargeName: e.brokerChargeName,
          buten: e.butenCode,
          accountNumber: e.accountNumber,
          course: e.courseName,
          customerName: e.customerName,
          customerNameKana: e.customerNameKana,
          brandCode: e.brandCode,
          brandName: e.brandName,
          amount: e.contractStandardDeposit,
          repaymentDate: e.recentRepayDeadline,
          detail: '詳細'
        }
      })
      this.$nextTick(() => {
        this.$refs['pqGrid'].refreshView(true)
      })
    },
    responseA002ErrorHandler(error) {
      this.$_logError(error)
    }
  }
}

// 米株信用建玉期日アラート
const foreignCreditOpenInterestDueDateAlert = [
  colDefine.brokerCode, // 仲介業者コード
  colDefine.brokerName, // 仲介業者名
  colDefine.branchCode, // 支店コード
  colDefine.branchName, // 支店名
  colDefine.brokerChargeCode, // 営業員コード
  colDefine.brokerChargeName, // 営業員名
  colDefine.buten, // 部店
  colDefine.accountNumber, // 口座番号
  colDefine.course, // コース
  colDefine.customerName, // 顧客名（漢字）
  colDefine.customerNameKana, // 顧客名（カナ）
  colDefine.ticker, // ティッカー
  colDefine.brandName, // 銘柄名
  colDefine.amount, // 数量
  colDefine.repaymentDate, // 返済期限
  colDefine.detail // 詳細
]

const obj = {
  width: 0, // set later
  height: 'flex',
  title: '米株信用建玉期日アラート一覧',
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  columnTemplate: { width: 100 },
  showTop: false,
  reactive: true,
  locale: 'en',
  maxHeight: 750,
  selectionModel: { type: 'row', mode: 'single' },
  editable: false,
  pageModel: {
    type: 'local',
    rPP: 30,
    rPPOptions: [30, 50, 100, 200, 500],
    layout: ['strDisplay', '|', 'prev', 'next']
  }
}

obj.dataModel = {
  data: []
}

obj.colModel = foreignCreditOpenInterestDueDateAlert

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
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
