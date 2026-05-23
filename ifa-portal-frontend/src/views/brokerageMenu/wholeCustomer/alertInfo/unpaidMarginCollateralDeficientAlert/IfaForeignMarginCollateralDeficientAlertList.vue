<template>
  <!-- 画面名：米株信用担保不足アラート一覧 -->
  <div>
    <screen-title text="米株信用担保不足アラート一覧"></screen-title>
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
                  original-screen-id="SUB020301_01-04"
                  @mediate-user-privacy="getAutoDisplay"
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
                  :request-model="ifaForeignMarginCollateralDeficientAlertListA002RequestModel"
                  action-id="SUB020301_01-04#A002"
                  @response-handler="responseA002Handler"
                  @response-error-handler="responseA002ErrorHandler"
                ></ifa-button>
                <ifa-button
                  id="btnTopInputClear"
                  name="btnTopInputClear"
                  text="クリア"
                  width="90"
                  color="white"
                  small
                  action-type="originalAction"
                  @app-action-handler="handleClear"
                ></ifa-button>
                <span style="color:#ff1e00; font-weight: bold; float:right; line-height: 30px;">※外貨最低保証金、外貨追加保証金アラートにつきましては仮計算値が8:00頃、確定値が20:00頃の反映となります。リアルタイムの情報は、詳細リンクを押下し遷移先画面をご確認下さい。</span>
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
      id="ifaForeignMarginCollateralDeficientAlertListA002"
      action-type="requestAction"
      :request-model="ifaForeignMarginCollateralDeficientAlertListA002RequestModelIfaWholePortalHome"
      action-id="SUB020301_01-04#A002"
      @response-handler="responseA002Handler"
      @response-error-handler="responseA002ErrorHandler"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { colDefine } from '@/views/brokerageMenu/wholeCustomer/alertInfo/IfaRemainPowerAlertGridTable'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaForeignMarginCollateralDeficientAlertListA002RequestModel } from './js/IfaForeignMarginCollateralDeficientAlertListA002RequestModel'
export default {
  components: {
    GridTable,
    IfaCommonSearch,
    screenTitle
  },
  data() {
    return {
      formRef: {},
      foreignCurrencyCreditCapacityAlert: [],
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
    ifaForeignMarginCollateralDeficientAlertListA002RequestModel() {
      return new IfaForeignMarginCollateralDeficientAlertListA002RequestModel(this.form)
    },
    ifaForeignMarginCollateralDeficientAlertListA002RequestModelIfaWholePortalHome() {
      return new IfaForeignMarginCollateralDeficientAlertListA002RequestModel(this.serchFormIfaWholePortalHome)
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
          document.getElementById('ifaForeignMarginCollateralDeficientAlertListA002').click()
        })
      }
    },
    preSearch() {
      this.onShow()
      this.ifaForeignMarginCollateralDeficientAlertListA002RequestModel.customerNameKanjiKanaTerms = this.form.customerNameKanjiKanaTerms - 1
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
    handleClear() {
      this.$refs.form.clearValidate()
      this.$refs.commonSearchItem.formClear()
    },
    handleClick({ rowData: { buten, accountNumber }, dataIndx }) {
      if (dataIndx === 'detail') {
        this.$_startShowMenu('SUB0202_010304', { butenCode: buten, accountNumber })
      }
    },
    responseA002Handler(data) {
      this.$_logDebug(data)
      const dataList = data.dataList || []
      this.pqGridOption.dataModel.data = dataList.map(e => {
        return {
          accuralDate: e.standardDate,
          brokerCode: e.brokerCode,
          brokerName: e.brokerName,
          branchCode: e.subBrokerId,
          branchName: e.brokerBranchName,
          brokerChargeCode: e.brokerChargeCode,
          brokerChargeName: e.employeeName,
          buten: e.buten,
          accountNumber: e.accountNumber,
          course: e.customerAttributeName,
          customerName: e.customerNameKanji,
          customerNameKana: e.customerNameKana,
          currency: e.currency,
          beforeDeposit: e.acceptDeposit,
          beforeDetentionRate: e.maintenanceRate,
          marginDemandAmount: e.marginDemandAmount,
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

// 外国信用余力アラート
const foreignCurrencyCreditCapacityAlert = [
  colDefine.accuralDate, // 発生日
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
  colDefine.currency, // 通貨
  colDefine.foreignBeforeDeposit, // 前日保証金残高
  colDefine.beforeDetentionRateForeign, // 前日維持率
  colDefine.foreignMarginDemandAmount, // 追証請求額
  colDefine.detail // 詳細
]

const obj = {
  width: 0, // set later
  height: 'flex',
  title: '米株信用担保不足アラート一覧',
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

obj.colModel = foreignCurrencyCreditCapacityAlert

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

