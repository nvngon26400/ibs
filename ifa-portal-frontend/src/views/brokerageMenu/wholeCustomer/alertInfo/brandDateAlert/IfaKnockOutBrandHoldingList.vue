<template>
  <!-- 画面名：ノックアウト銘柄 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
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
                  ref="commonSearch"
                  :form="form"
                  display-pattern="pt1"
                  list-pattern="pt2"
                  :broker-code-validate="false"
                  :emp-code-validate="false"
                  :course-validate="true"
                  original-screen-id="SUB020301_03-03"
                  @mediate-user-privacy="handleMediateUserPrivacy"
                ></ifa-common-search>
              </el-row>
              <el-row style="padding-left: 46px; padding-top: 10px">
                <ifa-button
                  id="btnDisplay"
                  icon="Search"
                  text="表示"
                  width="90"
                  small
                  :request-model="IfaKnockOutBrandHoldingListA002RequestModel"
                  :form="formRef"
                  action-id="SUB020301_03-03#A002"
                  action-type="requestAction"
                  @response-handler="displayA002($event)"
                ></ifa-button>
                <ifa-button
                  text="クリア"
                  width="90"
                  color="secondary"
                  small
                  action-type="originalAction"
                  @app-action-handler="clearA003"
                ></ifa-button>
              </el-row>
            </div>
          </el-card>
        </el-row>
        <el-row>
        </el-row>
        <el-row>
          <el-card
            class="content-card"
          >
            <grid-table
              ref="pqGrid"
              :auto-refresh="false"
              :options="pqGridOption"
              @click="handleClick"
            ></grid-table>
          </el-card>
        </el-row>
      </div>
    </el-form>
    <ifa-requester
      id="IfaKnockOutBrandHoldingListA002"
      :request-model="IfaKnockOutBrandHoldingListA002RequestModelIfaWholePortalHome"
      action-id="SUB020301_03-03#A002"
      action-type="requestAction"
      @response-handler="displayA002($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaKnockOutBrandHoldingListA004"
      action-id="SUB020301_03-03#A004"
      action-type="outputPdfAction"
      :request-model="IfaKnockOutBrandHoldingListA004RequestModel"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaKnockOutBrandHoldingListFormModel } from './js/IfaKnockOutBrandHoldingListFormModel'
import { IfaKnockOutBrandHoldingListA002RequestModel } from './js/IfaKnockOutBrandHoldingListA002RequestModel'
import { IfaKnockOutBrandHoldingListA004RequestModel } from './js/IfaKnockOutBrandHoldingListA004RequestModel'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
export default {
  components: {
    GridTable,
    IfaCommonSearch,
    screenTitle
  },
  data() {
    return {
      formRef: {},
      pqGridOption: getConvertedOption(obj),
      form: new IfaKnockOutBrandHoldingListFormModel(),
      selectPdfNoticeUrl: '',
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
      }
    }
  },
  computed: {
    IfaKnockOutBrandHoldingListA002RequestModel() {
      return new IfaKnockOutBrandHoldingListA002RequestModel(this.form)
    },
    IfaKnockOutBrandHoldingListA002RequestModelIfaWholePortalHome() {
      return new IfaKnockOutBrandHoldingListA002RequestModel(this.serchFormIfaWholePortalHome)
    },
    IfaKnockOutBrandHoldingListA004RequestModel() {
      return new IfaKnockOutBrandHoldingListA004RequestModel({ 'pdfNoticeUrl': this.selectPdfNoticeUrl })
    }
  },
  created() {
    this.pqGridOption.wrap = true
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow(resume, isRouting) {
      // 検索結果を初期化
      this.pqGridOption.dataModel.data = []
      this.$refs['pqGrid'].refreshView(true)
      // 総合ポータル_ホームからの遷移
      if (isRouting) {
        const updatedCourseSelected = this.form.courseSelected.map(course => ({
          ...course,
          isSelected: true
        }))
        this.serchFormIfaWholePortalHome.courseSelected = updatedCourseSelected
        this.$nextTick(() => {
          document.getElementById('IfaKnockOutBrandHoldingListA002').click()
        })
      }
    },
    displayA002(response) {
      if (response.dataList.length > 0) {
        this.pqGridOption.dataModel.data = response.dataList[0].knockOutBrandHoldingList
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.$refs['pqGrid'].refreshView()
    },
    clearA003() {
      this.$refs['form']?.clearValidate()
      this.$refs.commonSearch.formClear()
    },
    // 一覧選択
    handleClick(ui) {
      if (ui.dataIndx === 'pdfNoticeUrl') {
        // A004
        if (ui.rowData.pdfNoticeUrl) {
          this.selectPdfNoticeUrl = ui.rowData.pdfNoticeUrl
          this.$nextTick(() => {
            document.getElementById('IfaKnockOutBrandHoldingListA004').click()
          })
        }
      }
    },
    handleMediateUserPrivacy(data) {
      // 共通検索条件.営業員コード自動設定判定 = '0':自動設定なし の場合のみカラムを表示する。
      if (data.empCodeAutoDispFlag === '0') {
        this.setHidden('brokerCode', false)
        this.setHidden('brokerName', false)
        this.setHidden('brokerageBranchCode', false)
        this.setHidden('brokerBranchName', false)
        this.setHidden('brokerChargeCode', false)
        this.setHidden('brokerChargeName', false)
      } else {
        this.setHidden('brokerCode', true)
        this.setHidden('brokerName', true)
        this.setHidden('brokerageBranchCode', true)
        this.setHidden('brokerBranchName', true)
        this.setHidden('brokerChargeCode', true)
        this.setHidden('brokerChargeName', true)
      }
      this.$refs['pqGrid'].refreshView(true)
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: 'ノックアウト銘柄保有一覧',
  flexHeight: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  wrap: false
}

obj.colModel = [
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
      return ui.rowData.holdingQuantity ? ifaFormatUtils.zeroPadding(ui.rowData.accountNumber, 7) : '-'
    }
  },
  {
    title: '取引コース',
    dataIndx: 'customerAttribute',
    width: 150,
    editable: false,
    halign: 'center',
    align: 'left',
    codeValue: { codeListId: 'PRE_CONTRACT_DOC_CODE', dispPattern: 1 }
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
    width: 300,
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
  { title: '支店名',
    dataIndx: 'brokerBranchName',
    editable: false,
    halign: 'center',
    align: 'left',
    width: 200
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
    editable: false,
    halign: 'center',
    dataIndx: 'brokerChargeName',
    align: 'left'
  },
  {
    title: '銘柄コード',
    width: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'sbmBondCode',
    align: 'left'
  },
  {
    title: '銘柄名',
    width: 450,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'brandName',
    align: 'left'
  },
  {
    title: '保有数量',
    width: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'holdingQuantity',
    align: 'right',
    render: function(ui) {
      return ui.rowData.holdingQuantity ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.holdingQuantity) : '-'
    }
  },
  {
    title: '判定銘柄',
    width: 250,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'judgmentBrandName',
    align: 'left',
    sortable: false,
    render: function(ui) {
      if (ui.rowData.judgmentBrandName) {
        const splitData = ui.rowData.judgmentBrandName.split(',')
        const formattedVlaue = splitData.map(data => {
          return data === ' ' ? '-' : data
        })
        return formattedVlaue.join('<br>')
      } else {
        return '-'
      }
    }
  },
  {
    title: '時価',
    width: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'price',
    align: 'right',
    sortable: false,
    render: function(ui) {
      if (ui.rowData.price) {
        const splitData = ui.rowData.price.split(',')
        const formattedVlaue = splitData.map(data => {
          if (data < 0) {
            return '-'
          } else {
            return ifaFormatUtils.withCommaNoneZeroPadding(data)
          }
        })
        return formattedVlaue.join('<br>')
      } else {
        return '-'
      }
    }
  },
  {
    title: '早期償還水準価格',
    width: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'earlyRedemptionLevelPrice',
    align: 'right',
    hidden: false,
    sortable: false,
    render: function(ui) {
      if (ui.rowData.earlyRedemptionLevelPrice) {
        const splitData = ui.rowData.earlyRedemptionLevelPrice.split(',')
        const formattedVlaue = splitData.map(data => {
          if (data < 0) {
            return '-'
          } else {
            return ifaFormatUtils.withCommaNoneZeroPadding(data)
          }
        })
        return formattedVlaue.join('<br>')
      } else {
        return '-'
      }
    }
  },
  {
    title: '早期償還発生日',
    width: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'earlyRedemptionDeterminationDate',
    align: 'center',
    hidden: false,
    sortable: false,
    render: function(ui) {
      if (ui.rowData.earlyRedemptionDeterminationDate) {
        const splitData = ui.rowData.earlyRedemptionDeterminationDate.split(',')
        const formattedVlaue = splitData.map(data => {
          return data === ' ' ? '-' : getFormattedDateValue(data)
        })
        return formattedVlaue.join('<br>')
      } else {
        return '-'
      }
    }
  },
  {
    title: 'ダウンロード',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    dataIndx: 'pdfNoticeUrl',
    align: 'center',
    render: function(ui) {
      if (ui.rowData.pdfNoticeUrl) {
        return `<span class='grid-link'><a>` + 'DL' + `</a></span> 
          <style>
        .grid-link a {
          color: #092987;
          text-decoration: underline;
         &:focus, &:hover {
          color: #092987;
          text-decoration: underline;
          cursor: pointer;
          opacity: 0.7;
         }
        }
        </style>`
      } else {
        return
      }
    }
  }
]

obj.pageModel = { type: 'local', rPP: 30, rPPOptions: [] }

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

