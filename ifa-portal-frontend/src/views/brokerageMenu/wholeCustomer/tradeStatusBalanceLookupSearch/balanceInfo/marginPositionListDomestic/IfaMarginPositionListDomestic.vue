<template>
  <!-- 画面名：信用建玉一覧（国内） -->
  <div>
    <screen-title :text="form.title"></screen-title>
    <el-card
      class="content-card"
      shadow="always"
    >
      <div class="filter-container">
        <el-form
          ref="searchForm"
          :model="form"
          :inline="true"
        >
          <div>
            <ifa-common-search
              ref="commonSearchItem"
              display-pattern="pt4"
              list-pattern="pt4"
              :form="form"
              :broker-code-validate="true"
              :emp-code-validate="true"
              :course-validate="true"
              original-screen-id="SUB020302_0302-01"
              @mediate-user-privacy="mediateUserPrivacy"
            ></ifa-common-search>
            <!-- 銘柄コード /-->
            <el-form-item
              label="銘柄コード"
              class="form_label"
            >
              <ifa-input-text
                id="brandCode"
                v-model="form.brandCode"
                type="text"
                name="brandCode"
                style="width: 180px;"
                :domain="IfaBrandCodeFixed5DomainModel"
                class="ifa-input__text-default middle_input"
              ></ifa-input-text>
            </el-form-item>
          </div>
          <div
            id="indicator-display"
            class="form_button"
          >
            <ifa-button
              id="btnDisplay"
              name="btnDisplay"
              text="表示"
              width="90"
              search
              small
              :form="formRef"
              :request-model="A002aRequestModel"
              :set-param-func="paramFunctionA002"
              action-id="SUB020302_0302-01#A002"
              action-type="fetchApiAction"
              @response-handler="responseHandlerA002"
            ></ifa-button>
            <ifa-button
              id="btnTopInputClear"
              name="btnTopInputClear"
              text="クリア"
              width="90"
              color="white"
              small
              action-type="originalAction"
              @app-action-handler="clearA003"
            ></ifa-button>
          </div>
        </el-form>
      </div>
    </el-card>
    <el-card
      id="outputTable"
      class="content-card"
      style="width: auto !important;"
    >
      <div
        id="gridButtonArea"
        name="gridButtonArea"
      >
        <div id="indicator-target">
          <ifa-button
            id="btmCustomerPortal"
            name="btmCustomerPortal"
            :disabled="activeBtn"
            text="顧客別メニュー"
            color="primary"
            small
            action-type="originalAction"
            @app-action-handler="customerMenuA006"
          ></ifa-button>
          <ifa-button
            id="btnCsvDownload"
            name="btnCsvDownload"
            :disabled="csvbtn"
            text="CSV出力"
            width="90"
            color="primary"
            small
            action-id="SUB020302_0302-01#A004"
            :request-model="A004aRequestModel"
            csv-file-name="信用建玉一覧（国内）"
            action-type="outputCsvAction"
            :is-check-csv-download-allowed="true"
            :is-check-csv-download-privacy-confirmation="true"
          ></ifa-button>
          <!-- outputCsvAction化 -->
        </div>
      </div>
      <grid-table
        ref="gridTable"
        :options="pqGridOption"
        :auto-refresh="false"
        @click="handleClick"
      ></grid-table>
    </el-card>

    <!-- ダイアログ -->
    <!-- 建玉詳細 -->
    <ifa-domestic-position-detail
      :is-visible="dialogDetailVisible"
      :form-data="domesticPositionDetailModel"
      @close-modal="handleCloseModal"
    ></ifa-domestic-position-detail>
    <ifa-requester
      id="IfaMarginPositionListDomesticA005"
      action-id="SUB07-05#X001"
      action-type="requestAction"
      :request-model="A005RequestModel"
      @response-handler="responseHandlerA005"
    ></ifa-requester>
  </div>
</template>
<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import IfaDomesticPositionDetail from '@/views/common/IfaDomesticPositionDetail'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'

import { IfaMarginPositionListDomesticA002aRequestModel } from './js/IfaMarginPositionListDomesticA002aRequestModel.js'
import { IfaMarginPositionListDomesticA002bRequestModel } from './js/IfaMarginPositionListDomesticA002bRequestModel.js'
import { IfaMarginPositionListDomesticA004aRequestModel } from './js/IfaMarginPositionListDomesticA004aRequestModel.js'
import { IfaMarginPositionListDomesticA005RequestModel } from './js/IfaMarginPositionListDomesticA005RequestModel.js'
import { IfaMarginPositionListDomesticA006RequestModel } from './js/IfaMarginPositionListDomesticA006RequestModel.js'
import { IfaMarginPositionListDomesticFormModel } from './js/IfaMarginPositionListDomesticFormModel.js'

import IfaBrandCodeFixed5DomainModel from '@/resource/domain/IfaBrandCodeFixed5DomainModel.json'
import { generateCsvFromGrid } from '@/utils/csvUtils'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'
import { notifyWrapper, getMessage, notifyMessage } from '@/utils/errorHandler'
import { isAccessible } from '@/utils/controlAuth'

export default {
  components: { IfaDomesticPositionDetail, GridTable, IfaCommonSearch,
    screenTitle },
  data() {
    return {
      form: new IfaMarginPositionListDomesticFormModel(),
      IfaBrandCodeFixed5DomainModel,
      generateCsvFromGrid: generateCsvFromGrid,
      pqGridOption: getConvertedOption(obj),
      pqGridSelectedInfo: {},
      dialogDetailVisible: false,
      dialogTitle: '',
      activeBtn: true,
      csvbtn: true,
      showMessage: false,
      delFlag: false,
      formRef: {},
      A005RequestModel: {},
      domesticPositionDetailModel: {}
    }
  },
  computed: {
    A002aRequestModel() { return new IfaMarginPositionListDomesticA002aRequestModel(this.form) },
    A004aRequestModel() { return new IfaMarginPositionListDomesticA004aRequestModel(this.form) }
  },
  watch: {
    // courseSelectedが更新されたときに呼び出される関数
    'form.courseSelected': function(newCourseSelected) {
      this.form.course = newCourseSelected
        .filter(item => item.isSelected)
        .map(item => item.id)
    }
  },
  mounted() {
    this.formRef = this.$refs.searchForm
    this.showMessage = true
  },
  methods: {
    onShow() {
      this.activeBtn = true
      this.csvbtn = true
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
    },
    paramFunctionA002(data) {
      if (data) {
        return JSON.parse(JSON.stringify(new IfaMarginPositionListDomesticA002bRequestModel(data)))
      } else {
        return null
      }
    },
    responseHandlerA002: function(data, cancel, isSecondRequest) {
      if (data.length !== 0) {
        this.csvbtn = false // CSV出力ボタンの活性化
        // 一覧へのデータの反映
        const array = []
        for (let index = 0; index < data.length; index++) {
          const element = data[index].marginPositionListDomesticList
          element.forEach(function(item) {
            array.push(item)
          })
        }
        this.form.marginPositionListDomesticList = []
        Object.assign(this.form.marginPositionListDomesticList, array)
        this.pqGridOption.dataModel.data = this.form.marginPositionListDomesticList
        this.$refs['gridTable'].refreshView(true)
      } else {
        this.csvbtn = true
        this.activeBtn = true
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView(true)
        if (!cancel && isSecondRequest) {
          notifyWrapper({
            title: this.form.title,
            message: getMessage('info.holdMarginList.notfound'),
            type: 'info'
          })
        }
      }
    },
    mediateUserPrivacy(data) {
      if (data.empCodeAutoDispFlag === '0') {
        obj.colModel.forEach(e => {
          switch (e.dataIndx) {
            case 'brokerCode':
            case 'brokerName':
            case 'branchCode':
            case 'branchName':
            case 'empCode':
            case 'brokerChargeName':
              e.hidden = false
          }
        })
      } else {
        obj.colModel.forEach(e => {
          switch (e.dataIndx) {
            case 'brokerCode':
            case 'brokerName':
            case 'branchCode':
            case 'branchName':
            case 'empCode':
            case 'brokerChargeName':
              e.hidden = true
          }
        })
      }
    },
    handleClick(param) {
      this.pqGridSelectedInfo = param
      this.activeBtn = false
      if (param.colIndx === 18) {
        // 諸経費等モーダルが開く
        this.A005RequestModel = new IfaMarginPositionListDomesticA005RequestModel(param.rowData)
        this.$nextTick(() => {
          document.getElementById('IfaMarginPositionListDomesticA005').click()
        })
      }
    },
    responseHandlerA005(response) {
      this.domesticPositionDetailModel = Object.assign(this.domesticPositionDetailModel, response.dataList[0])
      this.dialogDetailVisible = true
    },
    clearA003() {
      this.$refs['searchForm'].clearValidate()
      this.$refs.commonSearchItem.formClear()
      this.form.brandCode = ''
    },
    customerMenuA006() {
      const param = new IfaMarginPositionListDomesticA006RequestModel(this.pqGridSelectedInfo.rowData)

      if (isAccessible(this.$customerMenuAccessCheckScreenId)) {
        this.$_startShowMenu(this.$customerMenuInitialScreenId, param)
      } else {
        notifyMessage(
          -1,
          getMessage('errors.cmn.loginUsers.insufficientPrivilege'),
          this.form.title
        )
      }
    },
    handleCloseModal() {
    // ダイアログ閉じる
      this.dialogDetailVisible = false
      this.dialogVisible = false
      this.dialogBrandDetailVisible = false
    },
    brokerCodeValidator(rule, value, callback) {
      return this.$refs.commonSearchItem.brokerCodeValidator(rule, value, callback)
    },
    empCodeValidator(rule, value, callback) {
      return this.$refs.commonSearchItem.empCodeValidator(rule, value, callback)
    },
    courseSelectedValidator(rule, value, callback) {
      return this.$refs.commonSearchItem.courseSelectedValidator(rule, value, callback)
    }
  }
}

const changeColorBorderBottom = (item) => {
  return `<a><span style="color: #092987;">` + item + `</span></a> 
        <style>
        a {
          color: #092987;
          text-decoration: underline;
        }
        a:focus, a:hover {
          color: #092987;
          text-decoration: underline;
          cursor: pointer;
          opacity: 0.7;
        }
        </style>`
}

const obj = {
  width: 0,
  height: 0,
  title: '信用建玉一覧',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false
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
    render: function(ui) {
      if (ui.rowData.accountNumber) {
        return ifaFormatUtils.zeroPadding(ui.rowData.accountNumber, 7)
      } else {
        return '-'
      }
    }
  },
  {
    title: '取引コース',
    dataIndx: 'course',
    minWidth: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      if (ui.rowData.course) {
        return getCodeValue('PRE_CONTRACT_DOC_CODE', 1, ui.rowData.course)
      } else {
        return '-'
      }
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
    title: '維持率(%)',
    dataIndx: 'domesticMarginPositionListActualGrntRate',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      if (ui.rowData.domesticMarginPositionListActualGrntRate) {
        return ifaFormatUtils.withCommaZeroPadding(ui.rowData.domesticMarginPositionListActualGrntRate, 1) + '%'
      } else {
        return '-'
      }
    }
  },
  {
    title: '銘柄コード',
    dataIndx: 'brandCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '銘柄名',
    dataIndx: 'brandName',
    minWidth: 120,
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
    halign: 'center'
  },
  {
    title: '建区分(期限)',
    dataIndx: 'openTradeKbn',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '新規建日',
    dataIndx: 'openTradeDate',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      return getFormattedDateValue(ui.rowData.openTradeDate)
    }
  },
  {
    title: '返済期限',
    dataIndx: 'lastTradeDate',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      return getFormattedDateValue(ui.rowData.lastTradeDate)
    }
  },
  {
    title: '預り区分',
    dataIndx: 'depositFullHalf13',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '建株数',
    dataIndx: 'contPositionTotal',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ifaFormatUtils.withCommaInteger(ui.rowData.contPositionTotal)
    }
  },
  {
    title: '注文中',
    dataIndx: 'unactualQuantity',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ifaFormatUtils.withCommaInteger(ui.rowData.unactualQuantity)
    }
  },
  {
    title: '建単価',
    dataIndx: 'unitPriceForeign',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.unitPriceForeign)
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
    render: function(ui) {
      return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.currentPrice)
    }
  },
  {
    title: '建代金',
    dataIndx: 'openAmount',
    minWidth: 100,
    dataType: 'integer',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ifaFormatUtils.withCommaInteger(ui.rowData.openAmount)
    }
  },
  {
    title: '諸経費等合計',
    dataIndx: 'cost',
    minWidth: 100,
    dataType: 'integer',
    editable: false,
    halign: 'center',
    align: 'right',
    position: 'relative',
    render: function(ui) {
      if (!ui.rowData.cost) {
        return changeColorBorderBottom('-')
      } else {
        const cost = ifaFormatUtils.withCommaInteger(ui.rowData.cost)
        return changeColorBorderBottom(cost)
      }
    }
  },
  {
    title: '評価損益',
    dataIndx: 'domesticPositionValuation',
    minWidth: 100,
    dataType: 'integer',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      const grid = this
      const data = ui.rowData.domesticPositionValuation
      if (data) {
        if (Number(data) >= 0) {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__plus bold' })
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__minus bold' })
        }
        return ifaFormatUtils.signedWithCommaInteger(data)
      } else {
        return '-'
      }
    }
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    width: 120,
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    width: 180,
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '支店コード',
    dataIndx: 'branchCode',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '支店名',
    dataIndx: 'branchName',
    editable: false,
    halign: 'center',
    align: 'left',
    width: 200
  },
  {
    title: '営業員コード',
    dataIndx: 'empCode',
    width: 110,
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '営業員名',
    dataIndx: 'brokerChargeName',
    width: 150,
    editable: false,
    halign: 'center',
    align: 'left'
  }
]
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
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
.el-icon-info {
  font-size: 30px;
}
:deep(.el-icon-info:hover){
  color: #409eff;
}
:deep(.el-dialog__wrapper) {
  z-index: 3000 !important;
}
:deep(.el-dialog__title) {
  font-size: 16px;
  margin: 0px;
  font-weight: 600;
  padding: 0px;
}
:deep(.el-dialog__body) {
  color: #303133;
}
:deep(.el-dialog) {
  width: 500px;
}
:deep(.pq-grid-link) {
  color:#409EFF;
  text-decoration: underline;
  text-underline-offset:0.2em;
  cursor: pointer;
}
.right_icon {
  text-align:right;
  margin-left:auto;
  display: inline-block;
  position: absolute;
  right: 20px;
  top: 0px;
}
.content-card {
  margin: 0.5rem 1rem;
}
.filter-container {
  margin-bottom:1rem;
  margin-top:1rem;
}
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
