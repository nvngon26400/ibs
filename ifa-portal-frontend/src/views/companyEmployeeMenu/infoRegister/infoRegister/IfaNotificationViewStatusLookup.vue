<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      class="inqInfoViewStatus"
      width="1200px"
      @open="onShow"
    >
      <el-row style="margin-bottom: 1rem;">
        <el-col
          :span="22"
          :offset="1"
        >
          <page-caption
            text="お知らせ閲覧状況照会"
            background-color="rgba(190, 190, 190, 0.35)"
          ></page-caption>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="23"
          class="close-button"
        >
          <ifa-button
            text="戻る"
            width="90"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-card class="infoRegistration">
            <el-row style="margin: 0.5rem;">
              <el-col
                :span="2"
                style="font-weight: bold;"
              >タイトル</el-col>
              <el-col :span="20">{{ selectedInfo.title }}</el-col>
            </el-row>
            <el-row style="margin: 0.5rem;">
              <el-col
                :span="2"
                style="font-weight: bold;"
              >登録日</el-col>
              <el-col :span="20">{{ $_getFormattedDateValue(selectedInfo.registerDayTime) }}</el-col>
            </el-row>
            <grid-table
              ref="gridTable"
              :options="pqGridOption"
              :auto-refresh="false"
              @click="handleClick"
            ></grid-table>
            <!-- ダウンロードボタン (A006) -->
            <el-row>
              <ifa-button
                id="btnCsvDownload"
                name="btnCsvDownload"
                text="CSV出力"
                :disabled="disabled.downloadButton"
                color="primary"
                small
                width="90"
                csv-file-name="お知らせ閲覧状況照会"
                :form="formRef"
                :request-model="A006RequestModel"
                style="margin-top: 10px;"
                action-id="SUB0501_01-05#A006"
                action-type="outputCsvAction"
              ></ifa-button>
            </el-row>
          </el-card>
        </el-col>
      </el-row>
    </el-dialog>

    <!-- 開封・削除 確認ダイアログ -->
    <el-dialog
      v-model="dialogComfirmVisible"
      width="400px"
      :center="true"
      :close-on-click-modal="false"
      :show-close="false"
      style="margin-top: 20vh;"
      class="confirm-dialog"
    >
      <!-- タイトル -->
      <template #header>
        <span v-if="operation === 'open'"><page-caption
          text="開封の確認"
          background-color="rgba(190, 190, 190, 0.35)"
        ></page-caption></span>
        <span v-else-if="operation === 'delete'"><page-caption
          text="削除の確認"
          background-color="rgba(190, 190, 190, 0.35)"
        ></page-caption></span>
      </template>
      <!-- メッセージ -->
      <div class="dialog-body">
        <span v-if="operation === 'open'">開封します。よろしいですか。</span>
        <span v-else-if="operation === 'delete'">削除します。よろしいですか。</span>
      </div>
      <div class="dialog-footer">
        <ifa-button
          text="OK"
          width="90"
          color="primary"
          small
          action-type="originalAction"
          @app-action-handler="doOperation"
        ></ifa-button>
        <ifa-button
          text="キャンセル"
          width="100"
          color="secondary"
          small
          action-type="originalAction"
          @app-action-handler="dialogComfirmVisible = false"
        ></ifa-button>
      </div>
    </el-dialog>
    <ifa-requester
      id="ifaNotificationViewStatusLookupInitializeA001"
      action-id="SUB0501_01-05#A001"
      action-type="requestAction"
      :request-model="A001RequestModel"
      @response-handler="initializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaNotificationViewStatusLookupProxyOpeningOkA003"
      action-id="SUB0501_01-05#A003"
      action-type="requestAction"
      :request-model="A003RequestModel"
      @response-handler="openingOkA003($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaNotificationViewStatusLookupDeleteOkA005"
      action-id="SUB0501_01-05#A005"
      action-type="requestAction"
      :request-model="A005RequestModel"
      @response-handler="deleteOkA005($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import GridTable from '@/components/GridTable'
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { IfaNotificationViewStatusLookupA001RequestModel } from './js/IfaNotificationViewStatusLookupA001RequestModel.js'
import { IfaNotificationViewStatusLookupA003RequestModel } from './js/IfaNotificationViewStatusLookupA003RequestModel.js'
import { IfaNotificationViewStatusLookupA005RequestModel } from './js/IfaNotificationViewStatusLookupA005RequestModel.js'
import { IfaNotificationViewStatusLookupA006RequestModel } from './js/IfaNotificationViewStatusLookupA006RequestModel.js'
import { IfaNotificationViewStatusLookupFormModel } from './js/IfaNotificationViewStatusLookupFormModel.js'

export default {
  components: { GridTable, pageCaption },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    selectedInfo: {
      type: Object,
      required: true
    }
  },
  emits: ['close-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaNotificationViewStatusLookupFormModel(),
      pqGridOption: null,
      operation: '',
      dataIndex: '',
      dialogComfirmVisible: false,
      disabled: {
        downloadButton: true
      }
    }
  },
  computed: {
    A001RequestModel() {
      return new IfaNotificationViewStatusLookupA001RequestModel(this.selectedInfo)
    },
    A003RequestModel() {
      return new IfaNotificationViewStatusLookupA003RequestModel(this.form)
    },
    A005RequestModel() {
      return new IfaNotificationViewStatusLookupA005RequestModel(this.form)
    },
    A006RequestModel() {
      return new IfaNotificationViewStatusLookupA006RequestModel(this.selectedInfo)
    }
  },
  created() {
    this.pqGridOption = getConvertedOption(obj)
    this.pqGridOption.wrap = true
  },
  methods: {
    onShow() {
      document.getElementById('ifaNotificationViewStatusLookupInitializeA001').click()
    },
    initializeA001(response) {
      let tableData = response.dataList[0].notificationViewStatusLookupList
      for (let i = 0; i < tableData.length; i++) {
        tableData = tableData.map((obj) => {
          return {
            ...obj,
            corReferenceCondition: response.dataList[0].notificationList[0].corReferenceCondition,
            notificationId: response.dataList[0].notificationList[0].notificationId,
            readDate: obj.t5nReadFlg === null || obj.t5nReadFlg === '0' ? '-' : getFormattedDateValue(obj.readDate, 'date8')
          }
        })
      }
      this.pqGridOption.dataModel.data = tableData
      this.$refs['gridTable'].refreshView()
      // リストが1件以上の場合、ダウンロードボタンを活性にする
      if (tableData.length > 0) {
        this.disabled.downloadButton = false
      } else {
        this.disabled.downloadButton = true
      }
    },
    handleClick(ui) {
      this.form.loginId = ui.rowData.loginId
      this.form.notificationId = ui.rowData.notificationId
      if (ui.dataIndx === 'proxyOpening' && (ui.rowData.t5nReadFlg === null || ui.rowData.t5nReadFlg === '0')) {
        // 開封
        this.dataIndex = ui.rowIndx
        this.operation = 'open'
        this.dialogComfirmVisible = true
      } else if (ui.dataIndx === 'corReferenceCondition' && ui.rowData.corReferenceCondition === '3') {
        // 削除
        this.dataIndex = ui.rowIndx
        this.operation = 'delete'
        this.dialogComfirmVisible = true
      }
    },
    // 開封・削除処理
    doOperation() {
      if (this.operation === 'open') {
        document.getElementById('ifaNotificationViewStatusLookupProxyOpeningOkA003').click()
      } else if (this.operation === 'delete') {
        document.getElementById('ifaNotificationViewStatusLookupDeleteOkA005').click()
      }
      this.dialogComfirmVisible = false
      this.form.notificationList = []
    },
    onDialogClose() {
      this.form.notificationList = []
      this.$emit('close-modal')
    },
    openingOkA003(response) {
      document.getElementById('ifaNotificationViewStatusLookupInitializeA001').click()
      this.$refs['gridTable'].refreshView()
    },
    deleteOkA005(response) {
      document.getElementById('ifaNotificationViewStatusLookupInitializeA001').click()
      this.$refs['gridTable'].refreshView()
    }
  }
}

const changeColorBorderBottom = (item) => {
  return `<span class="grid-link pq-grid-link"><a>` + item + `</a></span>
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
}
const obj = {
  width: 0,
  height: 0,
  title: 'お知らせ閲覧状況照会',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  // Selected single
  selectionModel: { type: 'row', mode: 'single' },
  topVisible: false,
  wrap: false
}
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
obj.colModel = [
  {
    title: '仲介業者名',
    minWidth: 350,
    dataType: function(val1, val2) {
      return
    },
    halign: 'center',
    align: 'left',
    dataIndx: 'branchName',
    editable: false
  },
  {
    title: '営業員名',
    minWidth: 260,
    dataType: function(val1, val2) {
      return
    },
    halign: 'center',
    align: 'left',
    dataIndx: 'employeeName',
    editable: false
  },
  {
    title: '閲覧状況',
    minWidth: 100,
    dataType: function(val1, val2) {
      return
    },
    halign: 'center',
    align: 'center',
    dataIndx: 't5nReadFlg',
    editable: false,
    resizable: true,
    render: function(ui) {
      const codeValue = getCodeValue('NOTIFICATION_VIEW_STATUS', 1, ui.rowData.t5nReadFlg ?? '$NULL')
      if (ui.rowData.t5nReadFlg === null || ui.rowData.t5nReadFlg === '0') {
        return `<span style="color: red;">` + codeValue + `</span>`
      } else {
        return codeValue
      }
    }
  },
  {
    title: '閲覧日',
    minWidth: 120,
    halign: 'center',
    align: 'center',
    dataIndx: 'readDate',
    editable: false,
    resizable: true
  },
  {
    title: '代理開封',
    minWidth: 120,
    halign: 'center',
    align: 'center',
    editable: false,
    sortable: false,
    resizable: true,
    dataIndx: 'proxyOpening',
    render: function(ui) {
      if (ui.rowData.t5nReadFlg === null || ui.rowData.t5nReadFlg === '0') {
        return changeColorBorderBottom('開封')
      } else {
        return ''
      }
    },
    className: 'OpenColumn'
  },
  {
    title: '閲覧者設定',
    minWidth: 120,
    dataType: function(val1, val2) {
      return
    },
    halign: 'center',
    align: 'center',
    dataIndx: 'corReferenceCondition',
    editable: false,
    resizable: true,
    render: function(ui) {
      // Only possible to relete personal items
      if (ui.rowData.corReferenceCondition === '3') {
        return changeColorBorderBottom('削除')
      } else {
        return ''
      }
    }
  },
  {
    title: '',
    minWidth: 150,
    dataType: 'string',
    halign: 'center',
    align: 'center',
    editable: false,
    dataIndx: 'loginId',
    hidden: true
  },
  {
    title: '',
    minWidth: 150,
    dataType: 'string',
    halign: 'center',
    align: 'center',
    editable: false,
    dataIndx: 'notificationId',
    hidden: true
  }
]
</script>

<style lang="scss" scoped>
.infoRegistration {
  margin-bottom: 0.3rem;
  background-color: rgb(253, 253, 253);
}
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}
:deep(.inqInfoViewStatus) .el-dialog {
  margin-top: 10vh !important;
  width: 1400px;
  padding: 30px 10px;
}
:deep(.el-dialog__header) {
  padding: 0px;
}
:deep(.el-dialog__header) span{
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
}
:deep(.el-dialog__title) {
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
  padding: 0px;
}
:deep(.el-dialog__body) {
  padding: 0px;
  margin-bottom: 1rem;
}
// 確認ダイアログ
:deep(.confirm-dialog) .el-dialog{
  padding: 30px 30px 10px 30px;
}
:deep(.confirm-dialog) .el-dialog__header span{
  font-size: 16px;
  margin: 0px;
  font-weight: bold;
}
:deep(.confirm-dialog) .dialog-body{
  padding: 25px 0 25px 0;
}
:deep(.confirm-dialog) .dialog-footer{
  margin-left: 80px
}
// 完了ダイアログ
:deep(.finish-dialog) .el-dialog{
  padding: 30px 30px 10px 30px;
}
:deep(.finish-dialog) .el-dialog__header span{
  font-size: 16px;
  margin: 0px;
  font-weight: bold;
}
:deep(.finish-dialog) .dialog-body{
  padding: 25px 0 15px 0;
}
:deep(.finish-dialog) .dialog-footer{
  margin-left: 180px
}
</style>
