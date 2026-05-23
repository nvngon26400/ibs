<template>
  <div>
    <screen-title :text="form.titleModel.name"></screen-title>
    <el-row>
      <el-card
        class="content-card"
        shadow="always"
      >
        <div class="filter-container">
          <!-- ここに検索条件用のコンテンツを記述する -->
          <el-row :gutter="20">
            <el-col
              :span="5"
              style="margin-left:1rem"
            >
              <ifa-button
                id="btnNewRegist"
                name="btnNewRegist"
                text="新規登録"
                action-type="originalAction"
                small
                @app-action-handler="newRegistHandlerA008"
              ></ifa-button>
            </el-col>
          </el-row>
        </div>
      </el-card>
    </el-row>
    <el-row>
      <el-card class="content-card">
        <grid-table
          ref="gridTable"
          :options="pqGridOption"
          :auto-refresh="false"
          @click="handleClick"
        ></grid-table>
      </el-card>
    </el-row>
    <!-- ダイアログ -->

    <!-- プレビュー ダイアログ -->
    <el-dialog
      v-model="complianceInfoPreview.visible"
      :close-on-click-modal="false"
      :show-close="false"
      class="complicance-report-dialog"
      width="1200px"
    >
      <el-row>
        <page-caption text="コンプライアンス通信"></page-caption>
      </el-row>
      <el-row>
        <ifa-button
          class="form-button__wrapper"
          color="secondary"
          text="戻る"
          action-type="originalAction"
          @app-action-handler="complianceInfoPreview.visible = false"
        ></ifa-button>
      </el-row>
      <ifa-compliance-report
        ref="IfaComplianceReport"
      ></ifa-compliance-report>
    </el-dialog>

    <!-- 新規作成 ダイアログ -->
    <ifa-compliance-report-info-register
      ref="IfaComplianceReportInfoRegister"
      :is-visible="infoRegistrationInputDialog.visible"
      @close-modal="infoRegistrationInputDialog.visible = false"
      @compliance-info-registered="onComplianceInfoRegistered"
    ></ifa-compliance-report-info-register>
    <!-- 変更 ダイアログ -->
    <ifa-compliance-report-info-update
      ref="IfaComplianceReportInfoUpdate"
      :is-visible="infoRegistrationUpdateDialog.visible"
      @open-modal="infoRegistrationUpdateDialog.visible = true"
      @close-modal="infoRegistrationUpdateDialog.visible = false"
      @compliance-info-registered="onComplianceInfoRegistered"
    ></ifa-compliance-report-info-update>
    <!-- 状態変更 ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="changeStatusDialog.visible"
      :title="changeStatusDialog.title"
      :message="changeStatusDialog.message"
      :enable-ok="changeStatusDialog.enableOk"
      :enable-cancel="changeStatusDialog.enableCancel"
      @close-modal-ok="changeCloseModalOk"
      @close-modal-cancel="changeCloseModalCancel"
    ></ifa-ok-cancel-dialog>

    <!-- 削除 ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="deleteDialog.visible"
      :title="deleteDialog.title"
      :message="deleteDialog.message"
      :enable-ok="deleteDialog.enableOk"
      :enable-cancel="deleteDialog.enableCancel"
      @close-modal-ok="deleteCloseModalOk"
      @close-modal-cancel="deleteCloseModalCancel"
    ></ifa-ok-cancel-dialog>
  </div>

  <ifa-requester
    id="ifaComplianceReportInfoRegisterManagerInitializeA001"
    action-id="SUB0505_01-01#A001"
    action-type="requestAction"
    @response-handler="responseHandlerInitializeA001($event)"
    @response-error-handler="errorHandlerInitializeA001($event)"
  ></ifa-requester>

  <ifa-requester
    id="ifaComplianceReportInfoRegisterManagerStateA003"
    action-id="SUB0505_01-01#A003"
    action-type="requestAction"
    :request-model="IfaComplianceReportInfoRegisterManagerA003RequestModel"
    @response-handler="responseHandlerStateA003($event)"
  ></ifa-requester>

  <ifa-requester
    id="ifaComplianceReportInfoRegisterManagerDeleteA010"
    action-id="SUB0505_01-01#A010"
    action-type="requestAction"
    :request-model="IfaComplianceReportInfoRegisterManagerA010RequestModel"
    @response-handler="responseHandlerDeleteA010($event)"
  ></ifa-requester>

</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaComplianceReportInfoRegister from '@/views/companyEmployeeMenu/complianceReport/ComplianceReportInfoRegister/IfaComplianceReportInfoRegister.vue'
import IfaComplianceReportInfoUpdate from '@/views/companyEmployeeMenu/complianceReport/ComplianceReportInfoRegister/IfaComplianceReportInfoUpdate.vue'

import { IfaComplianceReportInfoRegisterManagerFormModel } from './js/IfaComplianceReportInfoRegisterManagerFormModel'
import { IfaComplianceReportInfoRegisterManagerA003RequestModel } from './js/IfaComplianceReportInfoRegisterManagerA003RequestModel'
import { IfaComplianceReportInfoRegisterManagerA010RequestModel } from './js/IfaComplianceReportInfoRegisterManagerA010RequestModel'
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption.vue'
import IfaComplianceReport from '@/views/complianceReport/IfaComplianceReport.vue'

export default {
  components: {
    GridTable,
    IfaOkCancelDialog,
    IfaComplianceReport,
    IfaComplianceReportInfoRegister,
    screenTitle,
    IfaComplianceReportInfoUpdate,
    pageCaption
  },
  emits: ['initializeError'],
  data() {
    return {
      form: new IfaComplianceReportInfoRegisterManagerFormModel(),
      pqGridOption: getConvertedOption(obj),
      isInfoMessage: false,
      csvbtn: true,
      isThisMonth: true,
      targetRow: {},
      changeStatusDialog: {
        visible: false,
        title: '状態の変更',
        message: '状態の変更は内部管理統括部で行います。内部管理統括部以外の方は必ず「キャンセル」を押してください。※変更希望の場合は内部管理統括部にご連絡ください。',
        enableOk: true,
        enableCancel: true
      },
      deleteDialog: {
        visible: false,
        title: '登録情報の削除',
        message: 'コンプライアンス通信の削除は内部管理統括部で行います。内部管理統括部以外の方は必ず「キャンセル」を押してください。※変更希望の場合は内部管理統括部にご連絡ください。',
        enableOk: true,
        enableCancel: true
      },
      infoRegistrationInputDialog: {
        visible: false
      },
      infoRegistrationUpdateDialog: {
        visible: false
      },
      complianceInfoPreview: {
        visible: false,
        title: ''
      },
      lectureId: ''
    }
  },
  computed: {
    IfaComplianceReportInfoRegisterManagerA003RequestModel() {
      return new IfaComplianceReportInfoRegisterManagerA003RequestModel(this.form)
    },
    IfaComplianceReportInfoRegisterManagerA010RequestModel() {
      return new IfaComplianceReportInfoRegisterManagerA010RequestModel(this.form)
    }
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
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView()
      this.$nextTick(() => {
        document.getElementById('ifaComplianceReportInfoRegisterManagerInitializeA001').click()
      })
    },
    responseHandlerInitializeA001(data) {
      this.pqGridOption.dataModel.data = this.editGridData(data.dataList[0].complianceReportList)
      this.$refs['gridTable'].refreshView()
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.titleModel.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    // 状態変更確認 ① 状態変更確認ダイアログを表示する。（確認ダイアログ表示フラグ = true）
    changeA002(lectureId, disclosureFlag) {
      this.changeStatusDialog.visible = true
      this.form.lectureId = lectureId
      this.form.disclosureFlag = disclosureFlag
    },
    changeCloseModalOk() {
      this.$nextTick(() => {
        document.getElementById('ifaComplianceReportInfoRegisterManagerStateA003').click()
      })
    },
    changeCloseModalCancel() {
      this.changeStatusDialog.visible = false
      this.form.lectureId = ''
      this.form.disclosureFlag = ''
    },
    responseHandlerStateA003(data) {
      this.changeStatusDialog.visible = false
      this.pqGridOption.dataModel.data = this.editGridData(data.dataList[0].complianceReportList)
      this.$refs['gridTable'].refreshView()
    },
    // 削除確認 ① 削除確認ダイアログを表示する。（確認ダイアログ表示フラグ = true）
    deleteA007(lectureId, fileName1, fileName2, fileName3, contentsFileName) {
      this.deleteDialog.visible = true
      // 機能ID "M2" を設定
      this.form.functionId = 'M2'
      // カテゴリID "0" を設定
      this.form.t9nInfoCat = '0'
      this.form.lectureId = lectureId
      this.form.fileName1 = fileName1
      this.form.fileName2 = fileName2
      this.form.fileName3 = fileName3
      this.form.contentsFileName = contentsFileName
    },
    deleteCloseModalOk() {
      this.$nextTick(() => {
        document.getElementById('ifaComplianceReportInfoRegisterManagerDeleteA010').click()
      })
    },
    deleteCloseModalCancel() {
      this.deleteDialog.visible = false
      // 機能ID "M2" を設定
      this.form.functionId = ''
      // カテゴリID "0" を設定
      this.form.t9nInfoCat = ''
      this.form.lectureId = ''
      this.form.fileName1 = ''
      this.form.fileName2 = ''
      this.form.fileName3 = ''
      this.form.contentsFileName = ''
    },
    responseHandlerDeleteA010(data) {
      this.deleteDialog.visible = false
      this.pqGridOption.dataModel.data = this.editGridData(data.dataList[0].complianceReportList)
      this.$refs['gridTable'].refreshView()
    },
    onComplianceInfoRegistered(value) {
      if (value) {
        this.infoRegistrationUpdateDialog.visible = false
      } else {
        this.infoRegistrationInputDialog.visible = false
      }
      this.onShow()
    },
    // 一覧選択
    handleClick(row, rowIndex) {
      if (row.dataIndx === 'status') {
        const lectureId = row.rowData.lectureId
        const disclosureFlag = row.rowData.disclosureFlag
        this.changeA002(lectureId, disclosureFlag)
      } else if (row.dataIndx === 'preview') {
        this.complianceInfoPreview.visible = true
        this.lectureId = row.rowData.lectureId
        this.$nextTick(() => {
          this.$refs['IfaComplianceReport'].form.slctYmList = [{ key: this.lectureId, value: this.$_getFormattedDateValue(row.rowData.yearMonth + '01', 'date8').substr(0, 7) }]
          this.$refs['IfaComplianceReport'].form.lecId = this.lectureId
          this.$refs['IfaComplianceReport'].communicationDateSearch()
        })
      } else if (row.dataIndx === 'change') {
        this.$nextTick(() => {
          this.$refs['IfaComplianceReportInfoUpdate'].setup(row)
        })
      } else if (row.dataIndx === 'delete') {
        const lectureId = row.rowData.lectureId
        const fileName1 = row.rowData.fileName1
        const fileName2 = row.rowData.fileName2
        const fileName3 = row.rowData.fileName3
        const contentsFileName = row.rowData.contentsFileName
        this.deleteA007(lectureId, fileName1, fileName2, fileName3, contentsFileName)
      }
    },
    newRegistHandlerA008() {
      this.infoRegistrationInputDialog.visible = true
    },
    editGridData(data) {
      return data.map(item => ({
        ...item,
        status: item.disclosureFlag
      }))
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '登録一覧',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: false,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false
}

obj.colModel = [
  {
    title: '状態',
    dataIndx: 'status',
    minWidth: 60,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const disclosureFlag = ui.rowData.disclosureFlag
      if (disclosureFlag === '1') {
        return chengeColorBorderBottom('本番', 'blue')
      } else if (disclosureFlag === '0') {
        return chengeColorBorderBottom('仮登録', 'blue')
      }
    }
  },
  {
    title: 'Preview',
    dataIndx: 'preview',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    sortable: false,
    render: function(ui) {
      const rowData = ui.rowData
      const preview = rowData.preview
      if (preview === '') {
        return null
      } else {
        return "<img src='./page_find.png'/>"
      }
    }
  },
  {
    title: 'タイトル',
    dataIndx: 'title',
    minWidth: 650,
    dataType: 'string',
    editable: false,
    halign: 'center'
  },
  {
    title: '変更',
    dataIndx: 'change',
    minWidth: 70,
    dataType: 'string',
    editable: false,
    halign: 'center',
    sortable: false,
    align: 'center',
    render: function(ui) {
      return chengeColorBorderBottom('変更', 'blue')
    }
  },
  {
    title: '削除',
    dataIndx: 'delete',
    minWidth: 70,
    dataType: 'string',
    editable: false,
    halign: 'center',
    sortable: false,
    align: 'center',
    render: function(ui) {
      return chengeColorBorderBottom('削除', 'blue')
    }
  },
  {
    title: '最終更新日',
    dataIndx: 'lastUpdateDate',
    minWidth: 110,
    dataType: 'string',
    editable: false,
    halign: 'center',
    render: function(ui) {
      const lastUpdateDate = ui.rowData.lastUpdateDate
      return getFormattedDateValue(lastUpdateDate, 'date8')
    }
  },
  {
    title: 'LECTURE_ID',
    dataIndx: 'lectureId',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    hidden: true
  },
  {
    title: '公開フラグ',
    dataIndx: 'disclosureFlag',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    hidden: true
  },
  {
    title: 'ファイル名1',
    dataIndx: 'fileName1',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    hidden: true
  },
  {
    title: 'ファイル名2',
    dataIndx: 'fileName2',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    hidden: true
  },
  {
    title: 'ファイル名3',
    dataIndx: 'fileName3',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    hidden: true
  },
  {
    title: 'コンテンツファイル名',
    dataIndx: 'contentsFileName',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    hidden: true
  }
]

obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}

function chengeColorBorderBottom(val, color) {
  let result = "<span style='color:"
  result += color
  result += '; border-bottom:solid 1px '
  result += color
  result += ";'>"
  result += val
  result += '</span>'
  return result
}
</script>
<style scoped>
.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 200px;
}
.filter-container {
  margin-top:1rem;
}
.content-card {
  margin: 0.5rem 1rem;
}

.form_button {
  padding-left: 20px;
}

.gridButtonArea {
  margin-bottom: 10px;
}

:deep(.form_label) .el-form-item__label {
  width: 110px;
}

:deep(.label_period) .el-form-item__label {
  width: 135px;
}

.el-icon-info {
  font-size: 30px;
}

:deep(.el-form-item) {
  margin-bottom: 1.2rem;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -30px 0.5rem 0 auto;
}
:deep(.complicance-report-dialog .el-dialog__header) {
  display: none !important;
}
</style>
