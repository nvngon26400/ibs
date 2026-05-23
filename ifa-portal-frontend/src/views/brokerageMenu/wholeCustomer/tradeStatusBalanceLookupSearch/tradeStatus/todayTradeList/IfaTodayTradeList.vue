<template>
  <!-- 画面名：国内株当日約定一覧 -->
  <div>
    <screen-title :text="IfaTodayTradeListFormModel.title.name"></screen-title>
    <div class="ifa-search-view__main-default">
      <div id="top-component2">
        <div>
          <div id="contentAreaInput">
            <div id="inputArea">
              <el-card>
                <el-form ref="IfaTodayTradeListFormModel"
                         :model="IfaTodayTradeListFormModel"
                         :inline="true"
                         :rules="rules"
                >
                  <ifa-common-search
                    ref="commonSearchItem"
                    display-pattern="pt4"
                    list-pattern="pt4"
                    :form="IfaTodayTradeListFormModel"
                    :broker-code-validate="true"
                    :emp-code-validate="true"
                    :course-validate="true"
                    original-screen-id="SUB020302_0102-01"
                    @mediate-user-privacy="mediateUserPrivacy"
                  ></ifa-common-search>
                  <div>
                    <el-row>
                      <el-col :span="18">
                        <el-row>
                          <el-form-item
                            label="銘柄コード"
                            class="form_label"
                          >
                            <ifa-input-text
                              id="brandCode"
                              v-model="IfaTodayTradeListFormModel.brandCode"
                              type="text"
                              name="brandCode"
                              size="small"
                              maxlength="5"
                              style="width: 180px; margin-left: -0.5px"
                              :domain="IfaBrandCodeDomainModel"
                              prop="brandCode"
                              class="ifa-input__text-default search-form__item middle_input"
                            ></ifa-input-text>
                          </el-form-item>
                        </el-row>
                      </el-col>
                    </el-row>
                  </div>
                  <div id="indicator-display"
                       class="form_button"
                  >
                    <ifa-button id="btnDisplay"
                                name="btnDisplay"
                                text="表示"
                                width="90"
                                search
                                small
                                :form="formRef"
                                action-id="SUB020302_0102-01#A002"
                                :request-model="ifaTodayTradeListA002RequestModel"
                                action-type="fetchApiAction"
                                :set-param-func="setParam"
                                :pre-request-handler="preRequestHandlerA002"
                                @response-handler="responseHandlerA002"
                                @response-error-handler="responseErrorHandlerA002($event)"
                    ></ifa-button>
                    <ifa-button id="btnTopInputClear"
                                name="btnTopInputClear"
                                text="クリア"
                                width="90"
                                color="white"
                                small=""
                                action-type="originalAction"
                                @app-action-handler="formClear"
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
                <div id="gridButtonArea"
                     name="gridButtonArea"
                >
                  <div id="indicator-target">
                    <ifa-button id="btnCustomerPortal"
                                name="btnCustomerPortal"
                                :disabled="activeBtn"
                                text="顧客別メニュー"
                                color="primary"
                                small=""
                                action-type="originalAction"
                                @app-action-handler="handleMoveCustomerPortalClick"
                    ></ifa-button>
                    <ifa-button id="btnCsvDownload"
                                name="btnCsvDownload"
                                :disabled="csvbtn"
                                text="CSV出力"
                                width="90"
                                color="primary"
                                small=""
                                :form="formRef"
                                :request-model="ifaTodayTradeListA005RequestModel"
                                action-id="SUB020302_0102-01#A005"
                                csv-file-name="国内株当日約定一覧"
                                action-type="outputCsvAction"
                                :set-param-func="setParamCsvDownload"
                                :pre-request-handler="preRequestHandlerA005"
                                :is-check-csv-download-allowed="true"
                                :is-check-csv-download-privacy-confirmation="true"
                                @response-handler="responseHandlerA005($event)"
                                @response-error-handler="responseErrorHandlerA005($event)"
                    ></ifa-button>
                  </div>
                </div>
                <grid-table ref="gridTable"
                            :options="pqGridOption"
                            :auto-refresh="false"
                            @click="handleGridClick"
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
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaTodayTradeListFormModel } from './js/IfaTodayTradeListFormModel'
import { IfaTodayTradeListA002RequestModel } from './js/IfaTodayTradeListA002RequestModel'
import { IfaTodayTradeListA004RequestModel } from './js/IfaTodayTradeListA004RequestModel'
import { IfaTodayTradeListA005RequestModel } from './js/IfaTodayTradeListA005RequestModel'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { generateCsvFromGrid } from '@/utils/csvUtils'
import IfaBrandCodeDomainModel from '@/resource/domain/IfaBrandCodeDomainModel.json'
import { getMessage, notifyWrapper, notifyMessage } from '@/utils/errorHandler'
import { isAccessible } from '@/utils/controlAuth'

export default {
  components: { GridTable, IfaCommonSearch, screenTitle },
  data() {
    return {
      IfaBrandCodeDomainModel: IfaBrandCodeDomainModel,
      IfaTodayTradeListFormModel: new IfaTodayTradeListFormModel(),
      generateCsvFromGrid: generateCsvFromGrid,
      pqGridOption: getConvertedOption(obj),
      pqGridSelectedInfo: {},
      activeBtn: true,
      csvbtn: true,
      isVisible: false,
      delFlag: false,
      ifaFormatUtils: ifaFormatUtils,
      formRef: {},
      rules: {}
    }
  },
  computed: {
    ifaTodayTradeListA002RequestModel() {
      return new IfaTodayTradeListA002RequestModel(this.IfaTodayTradeListFormModel)
    },
    ifaTodayTradeListA005RequestModel() {
      return new IfaTodayTradeListA005RequestModel(this.IfaTodayTradeListFormModel)
    }
  },
  mounted() {
    this.formRef = this.$refs.IfaTodayTradeListFormModel
  },
  methods: {
    onShow() {
      this.activeBtn = true
      this.csvbtn = true
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
    },
    setParam(data) {
      return data
    },
    // 自動入力フラグを受取り、カラムの表示制御を行う
    mediateUserPrivacy(data) {
      if (data.empCodeAutoDispFlag === '0') {
        this.setHidden('empCode', false)
        this.setHidden('brokerChargeName', false)
        this.setHidden('brokerCode', false)
        this.setHidden('brokerName', false)
        this.setHidden('branchCode', false)
        this.setHidden('branchName', false)
      } else {
        this.setHidden('empCode', true)
        this.setHidden('brokerChargeName', true)
        this.setHidden('brokerCode', true)
        this.setHidden('brokerName', true)
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
    preRequestHandlerA002() {
      if (this.IfaTodayTradeListFormModel.courseSelected) {
        let courseStr = ''
        for (let i = 0; i < this.IfaTodayTradeListFormModel.courseSelected.length; i++) {
          const course = this.IfaTodayTradeListFormModel.courseSelected[i]
          if (course.isSelected === true) {
            courseStr = course.id + ',' + courseStr
          }
        }
        this.ifaTodayTradeListA002RequestModel.course = courseStr.substring(0, courseStr.lastIndexOf(','))
      }
      // APIリクエスト前業務処理（任意）
      this.$_logDebug('preRequestHandlerA002-OK')
      this.activeBtn = false
    },
    responseHandlerA002(data, cancel, isSecondRequest) {
      // APIレスポンス正常時業務処理（必須）
      this.$_logDebug('responseHandlerA002-OK')
      this.activeBtn = true
      this.csvbtn = true
      const dataModel = []
      data.forEach(todayTradeList => {
        todayTradeList.todayTradeList.forEach(todayTrade => {
          dataModel.push(todayTrade)
        })
      })
      this.pqGridOption.dataModel.data = dataModel
      this.$refs['gridTable'].refreshView(true)
      if (dataModel.length) {
        this.csvbtn = false // CSV出力ボタンの活性化
      } else {
        if (!cancel && isSecondRequest) {
          notifyWrapper({
            title: this.IfaTodayTradeListFormModel.title.name,
            message: getMessage('info.cmn.contract.notFound'),
            type: 'info'
          })
        }
      }
    },
    responseErrorHandlerA002(error) {
      // APIレスポンス異常時業務処理（必須）
      this.$_logWarn('responseErrorHandlerA002-Error', error)
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
      this.activeBtn = true
      this.csvbtn = true
    },
    setParamCsvDownload(data) {
      return data
    },
    preRequestHandlerA005() {
      this.ifaTodayTradeListA005RequestModel.csvData = this.pqGridOption.dataModel.data
    },
    responseHandlerA005(data) {
      this.$_logDebug('responseHandlerA005-OK')
    },
    responseErrorHandlerA005(error) {
      this.$_logWarn('responseErrorHandlerA005-Error', error)
    },
    handleGridClick(param) {
      this.pqGridSelectedInfo = param
      this.activeBtn = false
    },
    formClear() {
      this.$refs.commonSearchItem.formClear()
      this.$refs['IfaTodayTradeListFormModel'].clearValidate()
      this.IfaTodayTradeListFormModel.brandCode = ''
    },
    handleMoveCustomerPortalClick() {
      const param = new IfaTodayTradeListA004RequestModel(this.pqGridSelectedInfo.rowData)
      if (isAccessible(this.$customerMenuAccessCheckScreenId)) {
        this.$_startShowMenu(this.$customerMenuInitialScreenId, param)
      } else {
        notifyMessage(
          -1,
          getMessage('errors.cmn.loginUsers.insufficientPrivilege'),
          this.IfaTodayTradeListFormModel.title.name
        )
      }
    }
  }
}
const obj = {
  width: 0,
  height: 0,
  title: '国内株当日約定一覧',
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
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '取引コース',
    dataIndx: 'course',
    minWidth: 200,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      const course = getCodeValue('PRE_CONTRACT_DOC_CODE', 1, ui.rowData.course) || '-'
      return course
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
    title: '銘柄名',
    dataIndx: 'brandName',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      const brandName = ui.rowData.brandName
      if (brandName === null || brandName === '' || brandName === ' ') {
        return '<span style="color:red">約定がありません</span>'
      } else {
        return brandName
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
    title: '取引',
    dataIndx: 'openTradeKbn',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      const openTradeKbn = getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 3, ui.rowData.openTradeKbn)  || '-'
      return openTradeKbn
    }
  },
  {
    title: '預り区分',
    dataIndx: 'depositType',
    minWidth: 110,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      const depositType = getCodeValue('DOMESTIC_DEPOSIT_TYPE', 5, ui.rowData.depositType)  || '-'
      return depositType
    }
  },
  {
    title: '約定日',
    dataIndx: 'tradeDate',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      const tradeDate = getFormattedDateValue(ui.rowData.tradeDate, 'datetime8')
      return tradeDate || '-'
    }
  },
  {
    title: '受渡日',
    dataIndx: 'settlementDate',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      const settlementDate = getFormattedDateValue(ui.rowData.settlementDate, 'datetime8')
      return settlementDate || '-'
    }
  },
  {
    title: '約定株数',
    dataIndx: 'contractStock',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      const contractStock = ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.contractStock)
      return contractStock || '-'
    }
  },
  {
    title: '平均約定単価',
    dataIndx: 'averageTradePrice',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      const averageTradePrice = ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.averageTradePrice)
      return averageTradePrice || '-'
    }
  },
  {
    title: '営業員コード',
    dataIndx: 'empCode',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'brokerChargeName',
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
    dataIndx: 'branchCode',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '支店名',
    dataIndx: 'branchName',
    minWidth: 240,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  }
]
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
</script>
<style scoped="">
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
  margin: 0 2rem 0.8rem 2.5rem;
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
.right_icon {
  text-align:right;
  margin-left:auto;
  display: inline-block;
  position: absolute;
  right: 20px;
  top: 0px;
}
.el-select, .el-select .el-input, .el-select .el-input__inner {
  height: 32px;
}

:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
