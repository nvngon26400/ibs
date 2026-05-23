<template>
  <!-- 画面名：円貨未入金・赤残アラート一覧 -->
  <div>
    <screen-title text="円貨未入金・赤残アラート一覧"></screen-title>
    <el-form
      ref="form"
      :model="searchForm"
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
                  :form="searchForm"
                  display-pattern="pt1"
                  list-pattern="pt2"
                  original-screen-id="SUB020301_01-01"
                  @mediate-user-privacy="getAutoDisplay"
                ></ifa-common-search>
              </el-row>
              <el-row style="padding-left: 46px; padding-top: 10px; display: block;">
                <ifa-button
                  id="btnDisplay"
                  name="btnDisplay"
                  text="表示"
                  width="90"
                  color="primary"
                  search
                  small
                  action-type="requestAction"
                  :form="formRef"
                  :pre-request-handler="preSearch"
                  :request-model="ifaJpyAmountUnpaidOverdraftAlertListA002RequestModel"
                  action-id="SUB020301_01-01#A002"
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
                <span style="color:#ff1e00; font-weight: bold; float:right; line-height: 30px;">※下記アラートは、全て前営業日基準です。リアルタイムの情報は、詳細リンクを押下し遷移先画面をご確認下さい。</span>
              </el-row>
            </div>
          </el-card>
        </el-row>
        <el-card
          class="content-card"
          style="width: auto !important;"
        >
          <grid-table
            ref="pqGrid"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </div>
    </el-form>
    <ifa-requester
      id="ifaJpyAmountUnpaidOverdraftAlertListA002"
      action-id="SUB020301_01-01#A002"
      action-type="requestAction"
      :request-model="ifaJpyAmountUnpaidOverdraftAlertListA002RequestModelIfaWholePortalHome"
      @response-handler="responseA002Handler"
      @response-error-handler="responseA002ErrorHandler"
    ></ifa-requester>
    <ifa-requester
      id="ifaJpyAmountUnpaidOverdraftAlertListA004"
      action-id="SUB020301_01-01#A004"
      action-type="requestAction"
      :request-model="ifaJpyAmountUnpaidOverdraftAlertListA004RequestModel"
      @response-handler="responseA004Handler"
      @response-error-handler="responseA004ErrorHandler"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { colDefine } from '@/views/brokerageMenu/wholeCustomer/alertInfo/IfaRemainPowerAlertGridTable'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaJpyAmountUnpaidOverdraftAlertListFormModel } from './js/IfaJpyAmountUnpaidOverdraftAlertListFormModel'
import { IfaJpyAmountUnpaidOverdraftAlertListA002RequestModel } from './js/IfaJpyAmountUnpaidOverdraftAlertListA002RequestModel'
import { IfaJpyAmountUnpaidOverdraftAlertListA004RequestModel } from './js/IfaJpyAmountUnpaidOverdraftAlertListA004RequestModel'
export default {
  components: {
    GridTable,
    IfaCommonSearch,
    screenTitle
  },
  data() {
    return {
      formRef: {},
      domesticPurchaseCapacityAlert: [],
      searchForm: {
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
      form: new IfaJpyAmountUnpaidOverdraftAlertListFormModel(),
      pqGridOption: getConvertedOption(obj),
      ifaJpyAmountUnpaidOverdraftAlertListA004RequestModel: new IfaJpyAmountUnpaidOverdraftAlertListA004RequestModel({})
    }
  },
  computed: {
    ifaJpyAmountUnpaidOverdraftAlertListA002RequestModel() {
      return new IfaJpyAmountUnpaidOverdraftAlertListA002RequestModel(this.searchForm)
    },
    ifaJpyAmountUnpaidOverdraftAlertListA002RequestModelIfaWholePortalHome() {
      return new IfaJpyAmountUnpaidOverdraftAlertListA002RequestModel(this.serchFormIfaWholePortalHome)
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
        const updatedCourseSelected = this.searchForm.courseSelected.map(course => ({
          ...course,
          isSelected: true
        }))
        this.serchFormIfaWholePortalHome.courseSelected = updatedCourseSelected
        this.$nextTick(() => {
          document.getElementById('ifaJpyAmountUnpaidOverdraftAlertListA002').click()
        })
      }
    },
    handleClear() {
      this.$refs.form.clearValidate()
      this.$refs.commonSearchItem.formClear()
    },
    preSearch() {
      this.onShow()
      this.ifaJpyAmountUnpaidOverdraftAlertListA002RequestModel.customerNameKanjiKanaTerms = this.searchForm.customerNameKanjiKanaTerms - 1
    },
    handleClick({ rowData: { buten, accountNumber }, dataIndx }) {
      if (dataIndx === 'detail') {
        this.ifaJpyAmountUnpaidOverdraftAlertListA004RequestModel.butenCode = buten
        this.ifaJpyAmountUnpaidOverdraftAlertListA004RequestModel.accountNumber = accountNumber
        document.querySelector('#ifaJpyAmountUnpaidOverdraftAlertListA004').click()
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
        } else if (e.dataIndx === 'space') {
          e.width = 470
        }
      })
    },
    responseA002Handler(data) {
      this.$_logDebug(data)
      const dataList = data.dataList || []
      this.pqGridOption.dataModel.data = dataList.map(e => {
        return {
          accuralDate: e.stlDate0,
          brokerCode: e.brokerCode,
          brokerName: e.brokerName,
          branchCode: e.subBrokerId,
          branchName: e.brokerBranchName,
          brokerChargeCode: e.brokerChargeCode,
          brokerChargeName: e.employeeName,
          buten: e.butenCode,
          accountNumber: e.accountNumber,
          course: e.customerAttributeName,
          customerName: e.customerNameKanji,
          customerNameKana: e.customerNameKana,
          remainPower: e.chargeAmount0,
          detail: '詳細'
        }
      })
      this.$nextTick(() => {
        this.$refs['pqGrid'].refreshView(true)
      })
    },
    responseA002ErrorHandler(error) {
      this.$_logError(error)
    },
    responseA004Handler(data) {
      const resA004 = data.dataList[0]
      console.log(resA004)
      if (resA004.creditAccountKbn === '1') {
        this.$_startShowMenu('SUB0202_010302', { butenCode: resA004.butenCode, accountNumber: resA004.accountNumber })
      } else {
        this.$_startShowMenu('SUB0202_010301', { butenCode: resA004.butenCode, accountNumber: resA004.accountNumber })
      }
    },
    responseA004ErrorHandler(error) {
      this.$_logError(error)
    }
  }
}

// 国内買付余力アラート
const domesticPurchaseCapacityAlert = [
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
  colDefine.notPaymentInfo, // 預り金赤残
  colDefine.detail // 詳細
]

const obj = {
  width: 0, // set later
  height: 'flex',
  title: '円貨未入金・赤残アラート一覧',
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

obj.colModel = domesticPurchaseCapacityAlert

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

