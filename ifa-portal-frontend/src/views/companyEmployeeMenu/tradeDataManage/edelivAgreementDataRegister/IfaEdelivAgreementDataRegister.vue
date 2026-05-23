<template>
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div>
      <el-row>
        <el-card
          class="content-card"
          shadow="always"
        >
          <el-row class="upload-row">
            <el-col :span="24">
              <div class="upload-action__section">
                <ifa-file-select
                  ref="fileSelect"
                  text="ファイル選択"
                  small
                  msg-title="電子交付同意データ登録"
                  :show-file-list="true"
                  @before-remove="handleClearSelectedFile"
                  @change="handleChangeFileSelection"
                ></ifa-file-select>
                <ifa-button
                  id="btnConfirm"
                  text="確認"
                  small
                  msg-title="電子交付同意データ登録"
                  :disabled="confirmButtonDisabled"
                  action-type="uploadAction"
                  action-id="SUB0504_02-01#A002"
                  :request-model="uploadFileModel"
                  @response-handler="handleConfirmResponse"
                  @response-error-handler="handleConfirmResponseError"
                ></ifa-button>
                <ifa-button
                  text="クリア"
                  color="secondary"
                  small
                  action-type="originalAction"
                  @app-action-handler="handleClear"
                ></ifa-button>
              </div>
            </el-col>
          </el-row>
          <el-row class="action-row">
            <ifa-button
              id="btnRegister"
              text="登録"
              color="primary"
              small
              msg-title="電子交付同意データ登録"
              :disabled="disabled.btnRegister"
              action-id="SUB0504_02-01#A003"
              action-type="requestAction"
              :request-model="registerRequestModel"
              @response-handler="handleRegisterResponse"
            ></ifa-button>
            <ifa-button
              id="btnNgDownload"
              text="NGダウンロード"
              color="secondary"
              small
              :disabled="disabled.btnNgDownload"
              action-type="originalAction"
              @app-action-handler="handleNgDownload"
            ></ifa-button>
          </el-row>
          <el-row>
            <div class="grid-title">電子交付同意データ一覧</div>
          </el-row>
          <grid-table
            ref="edelivAgreementGridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleGridClick"
          ></grid-table>
        </el-card>
      </el-row>
    </div>
    <ifa-edeliv-agreement-data-register-msg-list
      :is-visible="msgListDialogVisible"
      :msg-list="msgList"
      @close-modal="msgListDialogVisible = false"
    ></ifa-edeliv-agreement-data-register-msg-list>
  </div>
</template>

<script>
import * as XLSX from 'xlsx'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { IfaEdelivAgreementDataRegisterFormModel } from './js/IfaEdelivAgreementDataRegisterFormModel'
import { IfaEdelivAgreementDataRegisterA003RequestModel } from './js/IfaEdelivAgreementDataRegisterA003RequestModel'
import IfaEdelivAgreementDataRegisterMsgList from './IfaEdelivAgreementDataRegisterMsgList'

const SHEET_NAME = '電子交付同意'
const HEADER_ROW = ['部店', '口座番号', '電子交付承諾日付', '電子交付承諾区分']

export default {
  components: {
    screenTitle,
    GridTable,
    IfaEdelivAgreementDataRegisterMsgList
  },
  data() {
    return {
      form: new IfaEdelivAgreementDataRegisterFormModel(),
      uploadFile: null,
      pqGridOption: getConvertedOption(gridOptions),
      disabled: {
        btnRegister: true,
        btnNgDownload: true
      },
      msgListDialogVisible: false,
      msgList: []
    }
  },
  computed: {
    confirmButtonDisabled() {
      return this.uploadFile === null
    },
    uploadFileModel() {
      return {
        filename: 'uploadFile',
        file: this.uploadFile
      }
    },
    registerRequestModel() {
      const list = this.form.edelivAgreementDataList.map(
        ({ checkResultSetList, ...rest }) => rest
      )
      return new IfaEdelivAgreementDataRegisterA003RequestModel(list)
    }
  },
  methods: {
    setup() {
      this.handleClear()
    },
    handleChangeFileSelection(file) {
      this.uploadFile = file
    },
    handleClearSelectedFile() {
      this.uploadFile = null
    },
    handleConfirmResponse(response) {
      const list = response?.dataList?.[0]?.edelivAgreementDataList || []
      this.form.edelivAgreementDataList = list
      this.pqGridOption.dataModel.data = list
      this.$nextTick(() => {
        this.$refs.edelivAgreementGridTable?.refreshView()
      })
      this.updateButtonState(list)
      this.handleClearSelectedFile()
      if (this.$refs.fileSelect) {
        this.$refs.fileSelect.clearFiles()
      }
    },
    handleConfirmResponseError() {
      this.handleClear()
    },
    handleRegisterResponse(response) {
      const list = response?.dataList?.[0]?.edelivAgreementDataList || []
      this.form.edelivAgreementDataList = list
      this.pqGridOption.dataModel.data = list
      this.$nextTick(() => {
        this.$refs.edelivAgreementGridTable?.refreshView()
      })
      this.disabled.btnRegister = true
      this.updateButtonState(list)
    },
    handleClear() {
      this.form.edelivAgreementDataList = []
      this.pqGridOption.dataModel.data = []
      this.uploadFile = null
      this.disabled.btnRegister = true
      this.disabled.btnNgDownload = true
      this.$nextTick(() => {
        this.$refs.edelivAgreementGridTable?.refreshView()
        this.$refs.fileSelect?.clearFiles()
      })
    },
    updateButtonState(list) {
      const hasOk = list.some(item => item.checkResult === 'OK')
      const hasNg = list.some(item => item.checkResult === 'NGあり')
      this.disabled.btnRegister = !hasOk
      this.disabled.btnNgDownload = !hasNg
    },
    handleGridClick(ui) {
      if (ui.dataIndx !== 'checkResult') {
        return
      }
      const checkResult = ui.rowData.checkResult
      if (checkResult === 'NGあり') {
        this.msgList = ui.rowData.checkResultSetList || []
        this.msgListDialogVisible = true
      }
    },
    handleNgDownload() {
      const ngRows = this.form.edelivAgreementDataList.filter(
        item => item.checkResult === 'NGあり'
      )
      if (ngRows.length === 0) {
        return
      }
      const sheetData = [HEADER_ROW]
      ngRows.forEach(row => {
        sheetData.push([
          row.buten || '',
          row.accountNumber || '',
          row.edelivAgreementDate || '',
          row.edelivAgreementKbn || ''
        ])
      })
      const worksheet = XLSX.utils.aoa_to_sheet(sheetData)
      const workbook = XLSX.utils.book_new()
      XLSX.utils.book_append_sheet(workbook, worksheet, SHEET_NAME)
      const now = new Date()
      const timestamp = [
        now.getFullYear(),
        String(now.getMonth() + 1).padStart(2, '0'),
        String(now.getDate()).padStart(2, '0'),
        String(now.getHours()).padStart(2, '0'),
        String(now.getMinutes()).padStart(2, '0'),
        String(now.getSeconds()).padStart(2, '0')
      ].join('')
      XLSX.writeFile(workbook, `NG電子交付同意_${timestamp}.xlsx`)
    }
  }
}

const gridOptions = {
  showTop: false,
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: false,
  numberCell: { show: false },
  selectionModel: { type: 'row', mode: 'single' },
  topVisible: false,
  wrap: false,
  reactive: true,
  locale: 'en',
  height: 'flex',
  columnTemplate: { width: 120 },
  colModel: colModel,
  dataModel: {
    data: []
  },
  maxHeight: 750,
  editable: false
}

const colModel = [
  {
    title: '部店',
    dataIndx: 'buten',
    width: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    width: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '電子交付承諾日付',
    dataIndx: 'edelivAgreementDate',
    width: 140,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render(ui) {
      const val = ui.rowData.edelivAgreementDate
      return val || '-'
    }
  },
  {
    title: '電子交付承諾区分',
    dataIndx: 'edelivAgreementKbnKanji',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render(ui) {
      if (ui.rowData.edelivAgreementKbnKanji) {
        return ui.rowData.edelivAgreementKbnKanji
      }
      const kbn = ui.rowData.edelivAgreementKbn
      if (kbn === '1') return '承諾済'
      if (kbn === '0') return '未承諾'
      return kbn || '-'
    }
  },
  {
    title: 'チェック結果',
    dataIndx: 'checkResult',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render(ui) {
      const checkResult = ui.rowData.checkResult || '-'
      const checkResultSetList = ui.rowData.checkResultSetList || []
      if (checkResult === 'NGあり') {
        return `<a class="el-link el-link--primary"><span class="el-link--inner">${checkResult}</span><sup class="el-badge__content item">${checkResultSetList.length}</sup></a>
        <style>
        .item {
          position: absolute;
          top: 0;
          right: 10px;
          transform: translateY(-50%) translateX(100%);
          background-color: #f56c6c;
          border-radius: 10px;
          color: #fff;
          display: inline-block;
          height: 18px;
          padding: 0 6px;
          text-align: center;
          white-space: nowrap;
          border: 1px solid #fff;
          margin-top: 5px;
          margin-right: -10px;
        }
        </style>`
      }
      return checkResult
    }
  },
  {
    title: 'チェック結果セット',
    dataIndx: 'checkResultSetList',
    dataType: 'string',
    hidden: true
  },
  {
    title: 'メッセージを表示する',
    dataIndx: 'displayMessageFlag',
    dataType: 'string',
    hidden: true
  },
  {
    title: '電子交付承諾区分コード',
    dataIndx: 'edelivAgreementKbn',
    dataType: 'string',
    hidden: true
  }
]
</script>

<style lang="scss" scoped>
.content-card {
  margin: 0.5rem;
}
.upload-row {
  margin-bottom: 1rem;
}
.action-row {
  margin-bottom: 1rem;
  display: flex;
  gap: 0.5rem;
}
.upload-action__section {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 0.5rem;
}
.grid-title {
  font-weight: bold;
  text-align: center;
  margin: 0.5rem 0 1rem;
  width: 100%;
}
:deep(.el-upload-list.el-upload-list--text) {
  width: 42rem !important;
}
:deep(.upload-action__section > .ifa-file-select__wrapper) {
  width: 133px !important;
}
:deep(.el-link) {
  text-decoration: underline !important;
}
</style>
