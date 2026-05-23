<template>
  <!-- 画面名：社員用メニュー > コンプライアンス通信 > コンプライアンス通信閲覧状況照会 -->
  <div>
    <screen-title :text="form.titleModel.name"></screen-title>
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
          <!-- ここに検索条件用のコンテンツを記述する -->
          <el-row>
            <el-form-item style="width: 415px">
              <ifa-input-select
                v-model="form.complianceReportTitle"
                label="タイトル"
                :model-value="form.complianceReportTitle"
                :code-list-id="'original'"
                :original-list="form.complianceReportList"
                style="width: 250px;"
                :domain="IfaText255DomainModel"
              >
              </ifa-input-select>
            </el-form-item>
            <el-form-item prop="brokerName">
              <ifa-input-text
                id="brokerName"
                v-model="form.brokerName"
                prop="brokerName"
                label="仲介業者名"
                size="small"
                style="width:200px;"
                original-screen-id="SUB0505_02-01"
                :domain="IfaBrokerNameDomainModel"
              ></ifa-input-text>
            </el-form-item>
            <div style="padding-top:7px">（部分検索）</div>
            <el-form-item prop="employeeName">
              <ifa-input-text
                id="brokerChargeName"
                v-model="form.brokerChargeName"
                prop="brokerChargeName"
                label="営業員名"
                size="small"
                style="width:200px;"
                original-screen-id="SUB0505_02-01"
                :domain="IfaBrokerChargeNameDomainModel"
              ></ifa-input-text>
            </el-form-item>
            <div style="padding-top:7px">（部分検索）</div>
          </el-row>

          <el-row>
            <el-form-item style="width: 415px">
              <ifa-input-radio
                v-model="form.viewStatus"
                label="閲覧状況"
                :code-list-id="'original'"
                :original-list="viewStatusTypeList"
                :domain="IfaClassRadioDomainModel"
                prop="viewStatus"
              ></ifa-input-radio>
            </el-form-item>
            <ifa-input-radio
              v-model="form.viewNecessity"
              label="閲覧要否"
              :code-list-id="'original'"
              :original-list="viewTargetList"
              :domain="IfaClassRadioDomainModel"
              prop="viewNecessity"
            ></ifa-input-radio>
          </el-row>
        </div>
        <el-row class="form_button" style="padding-left: 46px; padding-top: 10px; padding-bottom: 10px; display: block;">
          <ifa-button
            id="btnDisplay"
            name="btnDisplay"
            text="表示"
            width="90"
            search
            small
            :form="formRef"
            action-id="SUB0505_02-01#A002"
            :request-model="IfaComplianceReportViewStatusLookupManagerA002RequestModel"
            :set-param-func="paramFunctionA002"
            action-type="requestAction"
            @response-handler="responseHandlerA002($event)"
            @response-error-handler="responseErrorHandlerA002($event)"
          ></ifa-button>
          <span style="color:#ff1e00; font-weight: bold; float:right; padding-top: 5px; padding-right: 100px;">
            ※「閲覧不要設定」、「閲覧報告分」は内部管理統括部で行います。<br>
            内部管理統括部以外の方は変更しないようご留意ください。
          </span>
        </el-row>
      </el-card>
      <el-row>
        <el-card
          class="content-card"
        >
          <el-row>
            <div class="gridButtonArea">
              <ifa-button id="btnCsvDownload"
                          name="btnCsvDownload"
                          :disabled="csvbtn"
                          text="CSV出力"
                          width="90"
                          color="primary"
                          small=""
                          :request-model="IfaComplianceReportViewStatusLookupManagerA003RequestModel"
                          action-id="SUB0505_02-01#A003"
                          :csv-file-name="form.titleModel.name"
                          action-type="outputCsvAction"
                          :is-check-csv-download-allowed="true"
                          :is-check-csv-download-privacy-confirmation="false"
              ></ifa-button>
            </div>
          </el-row>
          <grid-table ref="gridTable"
                      :options="pqGridOption"
                      :auto-refresh="false"
                      @click="handleClick"
          ></grid-table>
        </el-card>
      </el-row>
    </el-form>
  </div>
  <ifa-requester
    id="initializeA001"
    action-id="SUB0505_02-01#A001"
    action-type="requestAction"
    @response-handler="responseHandlerInitializeA001($event)"
    @response-error-handler="errorHandlerInitializeA001($event)"
  ></ifa-requester>
  <ifa-requester
    id="searchDisplayPortalA007"
    action-id="SUB0505_02-01#A007"
    action-type="requestAction"
    :request-model="IfaComplianceReportViewStatusLookupManagerA007RequestModel"
    @response-handler="responseHandlerSearchDisplayPortalA007($event)"
    @response-error-handler="responseErrorHandlerSearchDisplayPortalA007($event)"
  ></ifa-requester>
  <ifa-requester
    id="ifaComplianceReportViewStatusLookupManagerViewReportA008"
    action-id="SUB0505_02-01#A008"
    action-type="requestAction"
    :request-model="IfaComplianceReportViewStatusLookupManagerA008RequestModel"
    @response-handler="responseHandlerViewReportA008($event)"
  ></ifa-requester>
  <ifa-requester
    id="ifaComplianceReportViewStatusLookupManagerDisplayA002"
    action-id="SUB0505_02-01#A002"
    action-type="requestAction"
    :request-model="IfaComplianceReportViewStatusLookupManagerA002RequestModel"
    :set-param-func="paramFunctionA002"
    @response-handler="responseHandlerA002($event)"
    @response-error-handler="responseErrorHandlerA002($event)"
  ></ifa-requester>
</template>

<script>
import { getMessage } from '@/utils/errorHandler'
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getConvertedOption } from '@/utils/pqgridHelper'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaText255DomainModel from '@/resource/domain/IfaText255DomainModel.json'
import IfaBrokerNameDomainModel from '@/resource/domain/IfaBrokerNameDomainModel.json'
import IfaBrokerChargeNameDomainModel from '@/resource/domain/IfaBrokerChargeNameDomainModel.json'
import IfaClassRadioDomainModel from '@/resource/domain/IfaClassRadioDomainModel.json'
import { IfaComplianceReportViewStatusLookupManagerFormModel } from './js/IfaComplianceReportViewStatusLookupManagerFormModel'
import { IfaComplianceReportViewStatusLookupManagerA002RequestModel } from './js/IfaComplianceReportViewStatusLookupManagerA002RequestModel'
import { IfaComplianceReportViewStatusLookupManagerA003RequestModel } from './js/IfaComplianceReportViewStatusLookupManagerA003RequestModel'
import { IfaComplianceReportViewStatusLookupManagerA005RequestModel } from './js/IfaComplianceReportViewStatusLookupManagerA005RequestModel'
import { IfaComplianceReportViewStatusLookupManagerA007RequestModel } from './js/IfaComplianceReportViewStatusLookupManagerA007RequestModel'
import { IfaComplianceReportViewStatusLookupManagerA008RequestModel } from './js/IfaComplianceReportViewStatusLookupManagerA008RequestModel'
import { notifyWrapper } from '@/utils/errorHandler'

export default {
  components: {
    GridTable,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      form: new IfaComplianceReportViewStatusLookupManagerFormModel(),
      formRef: {},
      pqGridSelectedInfo: {},
      pqGridOption: getConvertedOption(obj),
      csvbtn: true,
      rowData: [], // クリックをした列情報
      rules: {
        viewStatus: [{ required: true, trigger: 'change', message: getMessage('errors.required', ['閲覧状況']) }],
        viewNecessity: [{ required: true, trigger: 'change', message: getMessage('errors.required', ['閲覧要否']) }]
      },
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
      IfaBrokerNameDomainModel,
      IfaBrokerChargeNameDomainModel,
      IfaText255DomainModel,
      IfaClassRadioDomainModel
    }
  },
  computed: {
    IfaComplianceReportViewStatusLookupManagerA002RequestModel() {
      return new IfaComplianceReportViewStatusLookupManagerA002RequestModel(this.form)
    },
    IfaComplianceReportViewStatusLookupManagerA003RequestModel() {
      return new IfaComplianceReportViewStatusLookupManagerA003RequestModel(this.form)
    },
    IfaComplianceReportViewStatusLookupManagerA005RequestModel() {
      return new IfaComplianceReportViewStatusLookupManagerA005RequestModel(this.form)
    },
    IfaComplianceReportViewStatusLookupManagerA007RequestModel() {
      return new IfaComplianceReportViewStatusLookupManagerA007RequestModel(this.form)
    },
    IfaComplianceReportViewStatusLookupManagerA008RequestModel() {
      return new IfaComplianceReportViewStatusLookupManagerA008RequestModel(this.form)
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  created() {
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
    this.pqGridOption.wrap = true
  },
  methods: {
    onShow() {
      this.form.viewStatus = '0'
      this.form.viewNecessity = '9'
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
      this.$nextTick(() => {
        document.getElementById('initializeA001').click()
      })
      this.formRef = this.$refs.form
      const params = this.$store.getters.pageInfo.params
      if (typeof params !== 'undefined' && params.source === 'SUB01-01') {
        // タイトル（当月） hidden
        this.form.titleThisMonth = params.data.titleThisMonth
        // タイトル（過去月） hidden
        this.form.titleLastMonth = params.data.titleLastMonth
        this.form.viewStatus = '3'
        this.form.viewNecessity = '0'
        this.$nextTick(() => {
          document.getElementById('searchDisplayPortalA007').click()
        })
      }
    },
    responseHandlerInitializeA001(data) {
      this.$_logDebug('responseHandlerA001-OK')
      const len = data.dataList[0].complianceReportList.length
      this.form.complianceReportList = []
      for (let i = 0; i < len; i++) {
        this.form.complianceReportList.push({ key: data.dataList[0].complianceReportList[i].titleDesc, value: data.dataList[0].complianceReportList[i].titleValue })
      }
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.titleModel.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    responseHandlerA002(data) {
      this.csvbtn = true
      if (data.dataList.length > 0 && data.dataList[0].complianceReportListList.length !== 0) {
        // 表示ボタン押下後に活性
        this.csvbtn = false // CSV出力ボタンの活性化
        this.pqGridOption.dataModel.data = data.dataList[0].complianceReportListList
        this.$refs['gridTable'].refreshView(true)
      } else {
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView(true)
      }
    },
    responseErrorHandlerA002(error) {
      this.$_logDebug('responseErrorHandlerA002-Error', error)
      this.csvbtn = true
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
    },
    paramFunctionA002(data) {
      if (data) {
        return JSON.parse(JSON.stringify(new IfaComplianceReportViewStatusLookupManagerA002RequestModel(this.form)))
      } else {
        return null
      }
    },
    responseHandlerSearchDisplayPortalA007(data) {
      this.$_logDebug('responseHandlerA007-OK')
      this.csvbtn = true
      if (data.length !== 0) {
        this.$_logDebug(data.dataList[0])
        const len = data.dataList[0].complianceReportList.length
        this.form.complianceReportList = []
        for (let i = 0; i < len; i++) {
          this.form.complianceReportList.push({ key: data.dataList[0].complianceReportList[i].titleDesc, value: data.dataList[0].complianceReportList[i].titleValue })
        }
        // 表示ボタン押下後に活性
        this.csvbtn = false // CSV出力ボタンの活性化
        this.pqGridOption.dataModel.data = data.dataList[0].complianceReportListList
        this.$refs['gridTable'].refreshView(true)
      }
    },
    responseErrorHandlerSearchDisplayPortalA007(response) {
      const errorInfo = {
        title: this.form.titleModel.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    responseHandlerViewReportA008(response) {
      const viewReportFlag = response.dataList[0]?.viewReportFlag
      if (viewReportFlag) {
        this.pqGridOption.dataModel.data[this.rowData.rowIndx].viewReportFlag = viewReportFlag
        
        this.$nextTick(() => {
          this.$refs['gridTable'].refreshView(true)
        })
      }

      this.$nextTick(() => {
        document.getElementById('ifaComplianceReportViewStatusLookupManagerDisplayA002').click()
      })
    },
    // 一覧選択
    handleClick(row, rowIndex) {
      // 閲覧不要設定
      if (row.dataIndx === 'corBrowseFlag' && row.colIndx === 8) { // 「閲覧不要設定」をクリックした場合
        this.rowData = row
        this.noNeedToViewSettingA005()
      // 閲覧報告分
      } else if (
        (row.dataIndx === 'viewReportFlag' && row.colIndx === 9) // 「閲覧報告分」をクリックした場合 かつ
        && ( // リンク表示になっている場合
            (row.rowData.codeName === '確認済' && row.rowData.viewReportFlag === '0')
            || (row.rowData.codeName !== '確認済' && (row.rowData.viewReportFlag === '0' || row.rowData.viewReportFlag === '1'))
          )
      ) {
        this.rowData = row
        this.viewReportA008()
      }
    },
    noNeedToViewSettingA005() {
      if (!this.rowData.rowData.corBrowseFlag || this.rowData.rowData.corBrowseFlag === '0') {
        this.pqGridOption.dataModel.data[this.rowData.rowIndx].corBrowseFlag = '1'
      } else {
        this.pqGridOption.dataModel.data[this.rowData.rowIndx].corBrowseFlag = '0'
      }
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView(true)
      })
      this.form.brokerCode = this.rowData.rowData.brokerCode
      this.form.empCode = this.rowData.rowData.employeeId
      this.form.userId = this.rowData.rowData.userId
      this.form.viewExcludeSetting = this.rowData.rowData.corBrowseFlag
      this.$_request('SUB0505_02-01#A005', this.IfaComplianceReportViewStatusLookupManagerA005RequestModel)
      this.instantlyShowMessage('コンプライアンス通信閲覧状況の閲覧要否を変更しました。')
    },
    viewReportA008() {
      this.form.userId = this.rowData.rowData.userId // 選択行.ユーザーID
      this.form.lectureId = this.rowData.rowData.lectureId // 選択行.LECTURE_ID
      this.form.viewReportFlag = this.rowData.rowData.viewReportFlag // 選択行.閲覧報告フラグ
      
      this.$nextTick(() => {
        document.getElementById('ifaComplianceReportViewStatusLookupManagerViewReportA008').click()
      })
    },
    // 一時的にメッセージを表示する
    instantlyShowMessage(message) {
      notifyWrapper({
        title: 'コンプライアンス通信閲覧状況照会',
        message: message,
        type: 'info'
      })
    }
  }
}

// Grid表示用データ(デモ画面用定義)
// 列の定義(まとめて定義すべき)
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
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      const grid = this
      const confirmDateTime = ui.rowData.confirmDateTime
      if (confirmDateTime) {
        grid.addClass({ rowIndx: ui.rowIndx, colIndx: 0, cls: 'pq-cell-highlighted' })
        return getFormattedDateValue(confirmDateTime, 'date8')
      }
    }
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
    dataIndx: 'branchNameOfBranch',
    minWidth: 300,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '営業員コード',
    dataIndx: 'employeeId',
    minWidth: 120,
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
    align: 'left'
  },
  {
    title: 'タイトル',
    dataIndx: 'title',
    minWidth: 500,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
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
      const codeName = ui.rowData.codeName // コンプライアンス通信一覧リスト.コード名称
      const viewReportFlag = ui.rowData.viewReportFlag // コンプライアンス通信一覧リスト.閲覧報告フラグ
      let displayText = ''
    
      if (!codeName) { // コード名称がNULLの場合
        displayText = '未確認'
      } else { // コード名称がNULLでない場合
        if (viewReportFlag === '0' && codeName === '確認済') { // 閲覧報告フラグ="0"（報告済）かつ コード名称＝"確認済"の場合
          displayText = '閲覧報告'
        } else { // 上記以外の場合
          displayText = codeName
        }
      }

      if (displayText === '未確認') { // "未確認"の場合、フォントを赤にする
        return '<span style="color:red">' + displayText + '</span>'
      } else {
        return displayText
      }
    }
  },
  {
    title: '閲覧要否',
    dataIndx: 'corBrowseFlag',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const corBrowseFlag = ui.rowData.corBrowseFlag
      if (corBrowseFlag === null || corBrowseFlag === '0') {
        return '閲覧必要'
      } else if (corBrowseFlag === '1') {
        return '<span style="color:red">閲覧不要</span>'
      }
    }
  },
  {
    title: '閲覧不要設定',
    dataIndx: 'corBrowseFlag',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const corBrowseFlag = ui.rowData.corBrowseFlag
      if (corBrowseFlag === null || corBrowseFlag === '0') {
        return `<span class='grid-link'>閲覧不要</span>`
      } else if (corBrowseFlag === '1') {
        return `<span class='grid-link'>登録解除</span>`
      }
    }
  },
  {
    title: '閲覧報告分',
    dataIndx: 'viewReportFlag',
    minWidth: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const codeName = ui.rowData.codeName // コンプライアンス通信一覧リスト.コード名称
      const viewReportFlag = ui.rowData.viewReportFlag // コンプライアンス通信一覧リスト.閲覧報告フラグ
      let displayText = '' // 表示テキスト

      if (codeName === '確認済') { // コード名称＝確認済の場合
        if (viewReportFlag === '0') { // 閲覧報告フラグ＝ "0"（報告済）の場合
          displayText =  '未閲覧へ戻す'
        } else { // 上記以外の場合
          displayText =  '-'
        }
      } else { // 上記以外の場合
        if (viewReportFlag === '0') { // 閲覧報告フラグが、"0"（報告済）の場合
          displayText =  '未閲覧へ戻す'
        } else if (viewReportFlag === '1') { // 閲覧報告フラグが、"1"（未報告）の場合
          displayText =  '閲覧報告'
        } else { // 上記以外の場合
          displayText =  '-'
        }
      }

      if (displayText !== '-') { // '-'以外の場合リンク表示
        return `<span class='grid-link'>` + displayText + `</span>`
      } else {
        return displayText
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
:deep(.form_label.w300) .el-form-item__content {
  width: 300px;
}
:deep(.grid-link) {
  text-decoration: underline;
  color: blue;
  cursor: pointer;
}
:deep(.el-dialog) .el-dialog__body .el-row:nth-child(2){
  white-space: pre;
}
</style>
