<template>
  <!-- 画面名：内部管理責任者メニュー > マンスリー実施項目 > コンプライアンス通信閲覧状況照会 -->
  <div>
    <screen-title :text="form.titleModel.name"></screen-title>
    <div>
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
              <el-form-item style="width: 415px">
                <ifa-input-select
                  v-model="form.title"
                  label="タイトル"
                  :model-value="form.title"
                  :code-list-id="'original'"
                  :original-list="form.titleList"
                  style="width: 250px;"
                >
                </ifa-input-select>
              </el-form-item>
              <el-form-item>
                <ifa-input-text
                  id="brokerName"
                  v-model="form.brokerName"
                  prop="brokerName"
                  original-screen-id="SUB0401_01-01"
                  label="仲介業者名"
                  size="small"
                  style="width:200px;"
                  :domain="IfaBrokerNameDomainModel"
                ></ifa-input-text>
              </el-form-item>
              <div style="padding-top:7px">（部分検索）</div>
              <el-form-item>
                <ifa-input-text
                  id="chargeName"
                  v-model="form.employeeName"
                  prop="employeeName"
                  original-screen-id="SUB0401_01-01"
                  label="営業員名"
                  size="small"
                  style="width:200px;"
                  :domain="IfaTextFullHalf80DomainModel"
                ></ifa-input-text>
              </el-form-item>
              <div style="padding-top: 7px">（部分検索）</div>
            </el-row>
            <el-row>
              <el-form-item style="width: 415px">
                <ifa-input-radio
                  v-model="form.viewStatusType"
                  label="閲覧状況"
                  :code-list-id="'original'"
                  :original-list="viewStatusTypeList"
                  prop="viewStatusType"
                ></ifa-input-radio>
              </el-form-item>
              <ifa-input-radio
                v-model="form.viewTarget"
                label="閲覧対象"
                :code-list-id="'original'"
                :original-list="viewTargetList"
                prop="viewTarget"
              ></ifa-input-radio>
            </el-row>
          </div>
          <el-row>
            <ifa-button
              id="btnDisplay"
              name="btnDisplay"
              text="表示"
              width="90"
              search
              small
              :form="formRef"
              action-id="SUB0401_01-01#A002"
              :request-model="IfaComplianceReportViewStatusLookupInternalAdminA002RequestModel"
              :set-param-func="paramFunctionA002"
              action-type="requestAction"
              @response-handler="responseHandlerA002($event)"
              @response-error-handler="responseErrorHandlerA002($event)"
            ></ifa-button>
          </el-row>
        </el-card>
        <el-row>
          <el-card class="content-card">
            <el-row>
              <div class="gridButtonArea">
                <ifa-button id="btnCsvDownload"
                            name="btnCsvDownload"
                            :disabled="csvbtn"
                            text="CSV出力"
                            width="90"
                            color="primary"
                            small=""
                            :request-model="IfaComplianceReportViewStatusLookupInternalAdminA003RequestModel"
                            action-id="SUB0401_01-01#A003"
                            :csv-file-name="form.titleModel.name"
                            action-type="outputCsvAction"
                            :is-check-csv-download-allowed="true"
                            :is-check-csv-download-privacy-confirmation="false"
                            @response-handler="responseHandlerCsvOutputA003($event)"
                            @response-error-handler="responseErrorHandlerCsvOutputA003($event)"
                ></ifa-button>
              </div>
            </el-row>
            <grid-table ref="gridTable"
                        :options="pqGridOption"
                        :auto-refresh="false"
                        @click="handleGridClick"
            ></grid-table>
          </el-card>
        </el-row>
      </el-form>
    </div>
  </div>

  <ifa-requester
    id="initializeA001"
    action-id="SUB0401_01-01#A001"
    action-type="requestAction"
    @response-handler="responseHandlerInitializeA001($event)"
    @response-error-handler="errorHandlerInitializeA001($event)"
  ></ifa-requester>

  <ifa-requester
    id="searchDisplayPortalA004"
    action-id="SUB0401_01-01#A004"
    action-type="requestAction"
    :request-model="IfaComplianceReportViewStatusLookupInternalAdminA004RequestModel"
    @response-handler="responseHandlerSearchDisplayPortalA004($event)"
    @response-error-handler="errorHandlerSearchDisplayPortalA004($event)"
  ></ifa-requester>

</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getMessage } from '@/utils/errorHandler'
import { IfaComplianceReportViewStatusLookupInternalAdminFormModel } from './js/IfaComplianceReportViewStatusLookupInternalAdminFormModel'
import { IfaComplianceReportViewStatusLookupInternalAdminA002RequestModel } from './js/IfaComplianceReportViewStatusLookupInternalAdminA002RequestModel'
import { IfaComplianceReportViewStatusLookupInternalAdminA003RequestModel } from './js/IfaComplianceReportViewStatusLookupInternalAdminA003RequestModel'
import { IfaComplianceReportViewStatusLookupInternalAdminA004RequestModel } from './js/IfaComplianceReportViewStatusLookupInternalAdminA004RequestModel'
import IfaBrokerNameDomainModel from '@/resource/domain/IfaBrokerNameDomainModel.json'
import IfaTextFullHalf80DomainModel from '@/resource/domain/IfaTextFullHalf80DomainModel.json'

export default {
  components: {
    GridTable,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      IfaBrokerNameDomainModel: IfaBrokerNameDomainModel,
      IfaTextFullHalf80DomainModel: IfaTextFullHalf80DomainModel,
      form: new IfaComplianceReportViewStatusLookupInternalAdminFormModel(),
      rules: {
        brokerName: [
          { trigger: 'change', validator: this.brokerNameRuleValidator }
        ],
        employeeName: [
          { trigger: 'blur', validator: this.employeeNameRuleValidator }
        ],
        viewStatusType: [{ required: true, trigger: 'change', message: getMessage('errors.required', ['閲覧状況']) }],
        viewTarget: [{ required: true, trigger: 'change', message: getMessage('errors.required', ['閲覧対象']) }]
      },
      formRef: {},
      viewStatusTypeList: [
        { key: '0', value: '全て' },
        { key: '1', value: '確認済' },
        { key: '3', value: '未確認' }
      ],
      viewTargetList: [
        { key: '9', value: '全て' },
        { key: '0', value: '閲覧必須' },
        { key: '1', value: '閲覧任意' }
      ],
      pqGridOption: getConvertedOption(obj),
      pqGridSelectedInfo: {}
    }
  },
  computed: {
    IfaComplianceReportViewStatusLookupInternalAdminA002RequestModel() {
      return new IfaComplianceReportViewStatusLookupInternalAdminA002RequestModel(this.form)
    },
    IfaComplianceReportViewStatusLookupInternalAdminA003RequestModel() {
      return new IfaComplianceReportViewStatusLookupInternalAdminA003RequestModel(this.form)
    },
    IfaComplianceReportViewStatusLookupInternalAdminA004RequestModel() {
      return new IfaComplianceReportViewStatusLookupInternalAdminA004RequestModel(this.form)
    },
    csvbtn() {
      return !this.pqGridOption.dataModel.data.length
    }
  },
  created() {
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
    this.pqGridOption.wrap = true
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow() {
      this.form.viewStatusType = '0'
      this.form.viewTarget = '9'
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView()
      this.$nextTick(() => {
        document.getElementById('initializeA001').click()
      })
      const params = this.$store.getters.pageInfo.params
      if (typeof params !== 'undefined' && params.source === 'SUB01-01') {
        // タイトル（当月） hidden
        this.form.titleThisMonth = params.data.titleThisMonth
        // タイトル（過去月） hidden
        this.form.titleLastMonth = params.data.titleLastMonth
        this.form.viewStatusType = '3'
        this.form.viewTarget = '0'
        this.$nextTick(() => {
          document.getElementById('searchDisplayPortalA004').click()
        })
      }
    },
    responseHandlerInitializeA001(data) {
      const len = data.dataList[0].complianceReportList.length
      this.form.titleList = []
      for (let i = 0; i < len; i++) {
        this.form.titleList.push({ key: data.dataList[0].complianceReportList[i].titleDesc, value: data.dataList[0].complianceReportList[i].titleValue })
      }
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.titleModel.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    paramFunctionA002(data) {
      if (data) {
        return JSON.parse(JSON.stringify(new IfaComplianceReportViewStatusLookupInternalAdminA002RequestModel(this.form)))
      } else {
        return null
      }
    },
    responseHandlerA002(data) {
      this.$_logDebug('responseHandlerA002-OK')
      if (data.dataList[0]?.complianceReportList) {
        this.pqGridOption.dataModel.data = data.dataList[0].complianceReportList
        this.$refs['gridTable'].refreshView()
      } else {
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView()
      }
    },
    responseErrorHandlerA002(error) {
      this.$_logDebug('responseErrorHandlerA002-Error', error)
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView()
    },
    responseHandlerCsvOutputA003(data) {
      this.$_logDebug('responseHandlerA003-OK')
    },
    responseErrorHandlerCsvOutputA003(error) {
      this.$_logDebug('responseErrorHandlerA003-Error', error)
    },
    responseHandlerSearchDisplayPortalA004(data) {
      this.$_logDebug('responseHandlerA004-OK')
      if (data.length !== 0) {
        const len = data.dataList[0].complianceReportTitleList.length
        this.form.titleList = []
        for (let i = 0; i < len; i++) {
          this.form.titleList.push({ key: data.dataList[0].complianceReportTitleList[i].titleDesc, value: data.dataList[0].complianceReportTitleList[i].titleValue })
        }
        this.pqGridOption.dataModel.data = data.dataList[0].complianceReportList
        this.$refs['gridTable'].refreshView()
      }
    },
    responseErrorHandlerSearchDisplayPortalA004(response) {
      const errorInfo = {
        title: this.form.titleModel.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    handleGridClick(param) {
      this.pqGridSelectedInfo = param
    },
    brokerNameRuleValidator(rule, value, callback) {
      if (this.form.brokerName.length > 80) {
        callback(getMessage('errors.size', ['仲介業者名', '0', '80']))
        return
      }
      // OK
      callback()
    },
    employeeNameRuleValidator(rule, value, callback) {
      if (this.form.employeeName.length > 80) {
        callback(getMessage('errors.size', ['営業員名', '0', '80']))
        return
      }
      // OK
      callback()
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: 'コンプライアンス通信閲覧状況一覧',
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
    title: '確認日',
    dataIndx: 'confirmDateTime',
    minWidth: 60,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const grid = this
      const confirmDateTime = ui.rowData.confirmDateTime
      if (confirmDateTime !== '') {
        grid.addClass({ rowIndx: ui.rowIndx, colIndx: 0, cls: 'pq-cell-highlighted' })
        return getFormattedDateValue(confirmDateTime, 'date8')
      }
    }
  },
  {
    title: '仲介業者コード',
    dataIndx: 'brokerCode',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerBranchName',
    minWidth: 300,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員コード',
    dataIndx: 'employeeId',
    minWidth: 150,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '営業員名',
    dataIndx: 'employeeName',
    minWidth: 300,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: 'タイトル',
    dataIndx: 'title',
    minWidth: 300,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '状態',
    dataIndx: 'codeName',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const codeName = ui.rowData.codeName || '未確認'
      if (codeName === '未確認') {
        return '<span style="color:red">' + codeName + '</span>'
      } else {
        return codeName
      }
    }
  },
  {
    title: '閲覧対象',
    dataIndx: 'corBrowseFlag',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const corBrowseFlag = ui.rowData.corBrowseFlag
      if (corBrowseFlag === null || corBrowseFlag === '0') {
        return '閲覧必須'
      } else if (corBrowseFlag === '1') {
        return '閲覧任意'
      }
    }
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
.filter-container {
  margin-top:1rem;
}
.content-card {
  margin: 0.5rem 1rem;
}

.checkmark {
  margin-top: 10px;
  margin-right: 5px;
}

:deep(.el-card) .pq-cell-highlighted {
  background-color: pink;
}

:deep(.el-checkbox__label) {
  font-weight: bold;
}

:deep(.checkmark) .el-checkbox＿label {
  font-weight:bold;
  text-align : right;
}

.form_button {
  padding-left: 20px;
}

:deep(.el-textarea__inner) {
  width:82%;
  margin-left:85px;
  resize: none;
}

.gridButtonArea {
  margin-bottom: 10px;
}

.el-icon-info {
  font-size: 30px;
}

:deep(.el-form-item) {
  margin-bottom: 1.2rem;
  margin-right: 10px;
}
:deep(.el-form-item):first-child {
    margin-left: 0;
}
:deep(.form_label.w250) .el-form-item__content {
  width: 250px;
}
</style>
