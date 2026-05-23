<!-- eslint-disable no-undef -->
<template>
  <!-- 画面名：信用建玉一覧（米株） -->
  <div>
    <screen-title :text="IfaMarginPositionListForeignFormModel.title"></screen-title>

    <el-card
      class="content-card"
      shadow="always"
    >
      <div class="filter-container">
        <el-form
          ref="IfaMarginPositionListForeignFormModel"
          :model="IfaMarginPositionListForeignFormModel"
          :inline="true"
        >
          <div>
            <ifa-common-search
              ref="commonSearchItem"
              display-pattern="pt4"
              list-pattern="pt4"
              :form="IfaMarginPositionListForeignFormModel"
              :broker-code-validate="true"
              :emp-code-validate="true"
              :course-validate="true"
              original-screen-id="SUB020302_0303-01"
            ></ifa-common-search>
            <!-- ティッカー /-->
            <el-form-item
              label="ティッカー"
              class="form_label"
            >
              <ifa-input-text
                id="IfaMarginPositionListForeignFormModel"
                v-model="IfaMarginPositionListForeignFormModel.marginPositionListForeignTicker"
                type="text"
                style="width: 180px;"
                name="marginPositionListForeignTicker"
                :domain="IfaTickerDomainModel"
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
              :pre-request-handler="gridClear"
              :form="formRef"
              :request-model="IfaMarginPositionListForeignA002aRequestModel"
              :set-param-func="paramFunctionA002"
              action-id="SUB020302_0303-01#A002"
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
              @app-action-handler="formClear"
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
            small=""
            action-type="originalAction"
            @app-action-handler="handleMoveCustomerPortalClick"
          ></ifa-button>
          <ifa-button
            id="btnCsvDownload"
            name="btnCsvDownload"
            :disabled="csvbtn"
            text="CSV出力"
            width="90"
            color="primary"
            small
            action-id="SUB020302_0303-01#A006"
            :request-model="IfaMarginPositionListForeignA006aRequestModel"
            csv-file-name="信用建玉一覧（米株）"
            action-type="outputCsvAction"
            :is-check-csv-download-allowed="true"
            :is-check-csv-download-privacy-confirmation="true"
            @response-handler="responseHandlerA006a($event)"
            @response-error-handler="responseErrorHandlerA006a($event)"
          ></ifa-button>
        </div>
      </div>
      <grid-table
        ref="gridTable"
        :options="pqGridOption"
        :auto-refresh="false"
        @click="handleClick"
      ></grid-table>

      <ifa-requester
        id="IfaMarginPositionListForeignA005"
        action-id="SUB020302_0303-01#A005"
        action-type="requestAction"
        :request-model="IfaMarginPositionListForeignA005RequestModel"
        @response-handler="responseHandlerA005"
      ></ifa-requester>

    </el-card>

    <!-- ダイアログ -->
    <!-- 信用建玉詳細 -->
    <ifa-foreign-position-detail
      :is-visible="dialogDetailVisible"
      :form-data="foreignPositionDetailModel"
      @close-modal="handleCloseModal"
    ></ifa-foreign-position-detail>
  </div>
</template>
<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaForeignPositionDetail from '@/views/common/IfaForeignPositionDetail.vue'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { notifyWrapper, getMessage, notifyMessage } from '@/utils/errorHandler'

import { IfaMarginPositionListForeignA002aRequestModel } from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/balanceInfo/marginPositionListForeign/js/IfaMarginPositionListForeignA002aRequestModel'
import { IfaMarginPositionListForeignA002bRequestModel } from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/balanceInfo/marginPositionListForeign/js/IfaMarginPositionListForeignA002bRequestModel'
import { IfaMarginPositionListForeignA004RequestModel } from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/balanceInfo/marginPositionListForeign/js/IfaMarginPositionListForeignA004RequestModel'
import { IfaMarginPositionListForeignA005RequestModel } from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/balanceInfo/marginPositionListForeign/js/IfaMarginPositionListForeignA005RequestModel'
import { IfaMarginPositionListForeignA006aRequestModel } from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/balanceInfo/marginPositionListForeign/js/IfaMarginPositionListForeignA006aRequestModel'
import { IfaMarginPositionListForeignFormModel } from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/balanceInfo/marginPositionListForeign/js/IfaMarginPositionListForeignFormModel.js'

import IfaTickerDomainModel from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/balanceInfo/marginPositionListForeign/js/IfaTickerDomainModel.json'
import { generateCsvFromGrid } from '@/utils/csvUtils'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'
import { isAccessible } from '@/utils/controlAuth'

export default {
  components: { IfaForeignPositionDetail, GridTable, IfaCommonSearch,
    screenTitle },

  data() {
    return {
      IfaMarginPositionListForeignFormModel: new IfaMarginPositionListForeignFormModel(),
      IfaTickerDomainModel: IfaTickerDomainModel,
      generateCsvFromGrid: generateCsvFromGrid,
      pqGridOption: getConvertedOption(obj),
      pqGridSelectedInfo: {},
      dialogDetailVisible: false,
      activeBtn: true,
      csvbtn: true,
      showMessage: false,
      delFlag: false,
      formRef: {},
      foreignPositionDetailModel: {}
    }
  },
  computed: {
    IfaMarginPositionListForeignA002aRequestModel() { return new IfaMarginPositionListForeignA002aRequestModel(this.IfaMarginPositionListForeignFormModel) },
    IfaMarginPositionListForeignA005RequestModel() {
      return new IfaMarginPositionListForeignA005RequestModel(this.pqGridSelectedInfo)
    },
    IfaMarginPositionListForeignA006aRequestModel() {
      return new IfaMarginPositionListForeignA006aRequestModel(this.IfaMarginPositionListForeignFormModel)
    }
  },
  mounted() {
    this.formRef = this.$refs.IfaMarginPositionListForeignFormModel
    this.showMessage = true
  },
  created() {
    this.pqGridOption.wrap = true
  },
  methods: {
    onShow() {
      this.activeBtn = true
      this.csvbtn = true
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
    },
    gridClear() {
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
    },
    paramFunctionA002(data) {
      if (data) {
        return JSON.parse(JSON.stringify(new IfaMarginPositionListForeignA002bRequestModel({
          butenCode: data.butenCode,
          accountNumber: data.accountNumber,
          customerNameKanji: data.customerNameKanji,
          customerNameKana: data.customerNameKana,
          course: data.course,
          customerAttribute: data.customerAttribute,
          apiProfile: data.apiProfile,
          empCode: data.empCode,
          brokerChargeName: data.brokerChargeName,
          brokerCode: data.brokerCode,
          brokerName: data.brokerName,
          branchCode: data.branchCode,
          branchName: data.branchName,
          ticker: this.IfaMarginPositionListForeignFormModel.marginPositionListForeignTicker
        })))
      } else {
        return null
      }
    },
    handleClick(param) {
      this.pqGridSelectedInfo = param.rowData
      this.activeBtn = false
      if (param.colIndx === 18) {
        this.$nextTick(() => {
          document.getElementById('IfaMarginPositionListForeignA005').click()
        })
      }
    },
    responseHandlerA005(response) {
      this.foreignPositionDetailModel = Object.assign(this.foreignPositionDetailModel, response.dataList[0])
      // 諸経費等モーダルが開く
      this.dialogDetailVisible = true
    },
    formClear() {
      this.$refs['IfaMarginPositionListForeignFormModel'].clearValidate()
      this.$refs.commonSearchItem.formClear()
      this.IfaMarginPositionListForeignFormModel.marginPositionListForeignTicker = ''
    },
    handleCloseModal() {
    // ダイアログ閉じる
      this.dialogDetailVisible = false
      this.dialogVisible = false
      this.dialogBrandDetailVisible = false
    },
    handleMoveCustomerPortalClick() {
      const param = new IfaMarginPositionListForeignA004RequestModel(this.pqGridSelectedInfo)

      if (isAccessible(this.$customerMenuAccessCheckScreenId)) {
        this.$_startShowMenu(this.$customerMenuInitialScreenId, param)
      } else {
        notifyMessage(
          -1,
          getMessage('errors.cmn.loginUsers.insufficientPrivilege'),
          this.IfaMarginPositionListForeignFormModel.title
        )
      }
    },
    responseHandlerA002: function(data, cancel, isSecondRequest) {
      // APIレスポンス正常時業務処理（必須）
      this.activeBtn = true // ボタンの非活性化
      this.csvbtn = true // CSV出力ボタンの非活性化
      const empCodeAutoDispFlag = this.$refs.commonSearchItem.empCodeAutoDispFlag
      obj.colModel.forEach(function(item, index) {
      // 営業員コード
        if (item.dataIndx === 'empCode') {
          obj.colModel[index].hidden = empCodeAutoDispFlag
        }
        // 営業員名
        if (item.dataIndx === 'brokerChargeName') {
          obj.colModel[index].hidden = empCodeAutoDispFlag
        }
        // 仲介業者コード
        if (item.dataIndx === 'brokerCode') {
          obj.colModel[index].hidden = empCodeAutoDispFlag
        }
        // 仲介業者名
        if (item.dataIndx === 'brokerName') {
          obj.colModel[index].hidden = empCodeAutoDispFlag
        }
        // 支店コード
        if (item.dataIndx === 'branchCode') {
          obj.colModel[index].hidden = empCodeAutoDispFlag
        }
        // 支店名
        if (item.dataIndx === 'branchName') {
          obj.colModel[index].hidden = empCodeAutoDispFlag
        }
      })
      if (data.length !== 0) {
        this.csvbtn = false // CSV出力ボタンの活性化
        // 一覧へのデータの反映
        const array = []
        for (let index = 0; index < data.length; index++) {
          const element = data[index].marginPositionListForeignList
          element.forEach(function(item) {
            array.push(item)
          })
        }
        this.IfaMarginPositionListForeignFormModel.marginPositionListForeignList = []
        Object.assign(this.IfaMarginPositionListForeignFormModel.marginPositionListForeignList, array)
        this.pqGridOption.dataModel.data = this.IfaMarginPositionListForeignFormModel.marginPositionListForeignList.map(item => ({
          ...item,
          branchCode: item.brokerName ? item.branchCode : '-',
          branchName: item.brokerName ? item.branchName : '-',
          empCode: item.brokerName ? item.empCode : '-',
          brokerChargeName: item.brokerName ? item.brokerChargeName : '-'
        }))
        this.$refs['gridTable'].refreshView(true)
      } else {
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView(true)
        if (!cancel && isSecondRequest) {
          notifyWrapper({
            title: this.IfaMarginPositionListForeignFormModel.title,
            message: getMessage('info.holdMarginList.notfound'),
            type: 'info'
          })
        }
      }
    },
    responseHandlerA006a(data) {
      this.$_logDebug(data)
    },
    responseErrorHandlerA006a(error) {
      this.$_logError(error)
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
    dataIndx: 'buten',
    minWidth: 60,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      if (ui.rowData.buten) {
        return ui.rowData.buten
      } else {
        return '-'
      }
    }
  },
  {
    title: '口座番号',
    dataIndx: 'account',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      if (ui.rowData.account) {
        const num = ui.rowData.account
        return ifaFormatUtils.zeroPadding(num, 7)
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
    halign: 'center',
    render: function(ui) {
      if (ui.rowData.customerNameKanji) {
        return ui.rowData.customerNameKanji
      } else {
        return '-'
      }
    }
  },
  {
    title: '顧客名(カナ)',
    dataIndx: 'customerNameKana',
    minWidth: 250,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      if (ui.rowData.customerNameKana) {
        return ui.rowData.customerNameKana
      } else {
        return '-'
      }
    }
  },
  {
    title: 'ティッカー',
    dataIndx: 'ticker',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      if (ui.rowData.ticker) {
        return ui.rowData.ticker
      } else {
        return '-'
      }
    }
  },
  {
    title: '銘柄名',
    dataIndx: 'brandName',
    minWidth: 250,
    maxWidth: 2300,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      if (ui.rowData.brandName) {
        return ui.rowData.brandName
      } else {
        return '-'
      }
    }
  },
  {
    title: '市場',
    dataIndx: 'market',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      if (ui.rowData.market) {
        return ui.rowData.market
      } else {
        return '-'
      }
    }
  },
  {
    title: '建区分(期限)',
    dataIndx: 'openTradeKbn',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      if (ui.rowData.openTradeKbn) {
        return ui.rowData.openTradeKbn
      } else {
        return '-'
      }
    }

  },
  {
    title: '新規建日',
    dataIndx: 'openTradeDate',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      if (ui.rowData.openTradeDate) {
        return getFormattedDateValue(ui.rowData.openTradeDate)
      } else {
        return '-'
      }
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
      if (ui.rowData.lastTradeDate) {
        return getFormattedDateValue(ui.rowData.lastTradeDate)
      } else {
        return '-'
      }
    }
  },
  {
    title: '預り区分',
    dataIndx: 'depositType',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      if (ui.rowData.depositType) {
        return getCodeValue('FOREIGN_DEPOSIT_TYPE', 1, ui.rowData.depositType)
      } else {
        return '-'
      }
    }
  },
  {
    title: '担保',
    dataIndx: 'security',
    minWidth: 50,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      if (ui.rowData.security) {
        return ui.rowData.security
      } else {
        return '-'
      }
    }
  },
  {
    title: '建株数',
    dataIndx: 'contPositionTotal',
    minWidth: 170,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      if (ui.rowData.contPositionTotal) {
        const target = ui.rowData.contPositionTotal
        return ifaFormatUtils.withCommaInteger(target) + '株'
      } else {
        return '-'
      }
    }
  },
  {
    title: '注文中',
    dataIndx: 'unactualQuantity',
    minWidth: 170,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      if (ui.rowData.unactualQuantity) {
        const target = ui.rowData.unactualQuantity
        return ifaFormatUtils.withCommaInteger(target) + '株'
      } else {
        return '-'
      }
    }
  },
  {
    title: '建単価',
    dataIndx: 'positionPrice',
    minWidth: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      if (ui.rowData.positionPrice) {
        const target = ui.rowData.positionPrice
        return ifaFormatUtils.withCommaZeroPadding(target, 2) + 'USD'
      } else {
        return '-'
      }
    }
  },
  {
    title: '現在値',
    dataIndx: 'currentPrice',
    minWidth: 200,
    dataType: 'integer',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      if (ui.rowData.currentPrice) {
        const target = ui.rowData.currentPrice
        return ifaFormatUtils.withCommaZeroPadding(target, 2) + 'USD'
      } else {
        return '-'
      }
    }
  },
  {
    title: '建代金',
    dataIndx: 'openAmount',
    minWidth: 200,
    dataType: 'integer',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      if (ui.rowData.openAmount) {
        const target = ui.rowData.openAmount
        return ifaFormatUtils.withCommaZeroPadding(target, 2) + 'USD'
      } else {
        return '-'
      }
    }
  },
  {
    title: '諸経費等合計',
    dataIndx: 'totalExpensesEtc',
    minWidth: 200,
    dataType: 'integer',
    editable: false,
    halign: 'center',
    align: 'right',
    position: 'relative',
    render: function(ui) {
      if (!ui.rowData.totalExpensesEtc) {
        return changeColorBorderBottom('-')
      } else {
        const target = ui.rowData.totalExpensesEtc
        const totalExpensesEtc = ifaFormatUtils.withCommaZeroPadding(target, 2) + 'USD'
        return changeColorBorderBottom(totalExpensesEtc)
      }
    }
  },
  {
    title: '評価損益',
    dataIndx: 'customerListProfitAndLoss',
    minWidth: 200,
    dataType: 'integer',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      const grid = this
      const data = ui.rowData.customerListProfitAndLoss
      if (data) {
        if (Number(data.replace(/,/g, '')) >= 0) {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__plus bold' })
          return ifaFormatUtils.signedWithCommaZeroPadding(data, 2, 'blue') + 'USD'
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__minus bold' })
          return ifaFormatUtils.signedWithCommaZeroPadding(data, 2, 'blue') + 'USD'
        }
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
    align: 'left',
    hidden: false,
    render: function(ui) {
      if (ui.rowData.brokerCode) {
        return ui.rowData.brokerCode
      } else {
        return '-'
      }
    }
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    width: 180,
    editable: false,
    halign: 'center',
    align: 'left',
    hidden: false,
    render: function(ui) {
      if (ui.rowData.brokerName) {
        return ui.rowData.brokerName
      } else {
        return '-'
      }
    }
  },
  {
    title: '支店コード',
    dataIndx: 'branchCode',
    editable: false,
    halign: 'center',
    align: 'left',
    hidden: false
  },
  {
    title: '支店名',
    dataIndx: 'branchName',
    editable: false,
    halign: 'center',
    align: 'left',
    width: 200,
    hidden: false
  },
  {
    title: '営業員コード',
    dataIndx: 'empCode',
    width: 110,
    editable: false,
    halign: 'center',
    align: 'left',
    hidden: false
  },
  {
    title: '営業員名',
    dataIndx: 'brokerChargeName',
    width: 150,
    editable: false,
    halign: 'center',
    align: 'left',
    hidden: false
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
  line-height: 2;
}
.form_button {
  margin: 0 2rem 0.8rem 46px;
  padding: 0;
}
#gridButtonArea {
  margin: 0.5rem 0;
}
#contentAreaInput,
#outputPaneMargins {
  margin: 0.5rem;
}
:deep(.el-form-item__error) {
  white-space: nowrap;
}
:deep(.el-form-item) {
  margin-bottom: 1.2rem;
}
.el-icon-info {
  font-size: 30px;
}
:deep(.el-icon-info:hover) {
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
  color: #409eff;
  text-decoration: underline;
  text-underline-offset: 0.2em;
  cursor: pointer;
}
.right_icon {
  text-align: right;
  margin-left: auto;
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
