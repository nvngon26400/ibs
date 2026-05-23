<template>
  <div class="main">
    <h3>●ボタンテスト</h3>

    <hr>
    <h4>
      【originalAction】
      <ifa-button
        text="テスト"
        small
        action-type="originalAction"
        @app-action-handler="result.originalAction = 'OK'"
      ></ifa-button>
      <span class="label">チェック:</span><span class="value">{{ result.originalAction }}</span>
    </h4>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4>
      【requestAction】
      <ifa-button
        text="テスト"
        small
        :disabled="disabled"
        action-type="requestAction"
        action-id="Navbar#A004"
        :pre-request-handler="() => { result.requestAction = 'PRE' }"
        @response-handler="result.requestAction = 'OK'"
        @response-error-handler="result.requestAction = 'ERROR'"
      ></ifa-button>
      <span class="label">チェック:</span><span class="value">{{ result.requestAction }}</span>
    </h4>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4>
      【outputCsvAction】
      <ifa-button
        text="テスト"
        small
        :disabled="disabled"
        action-type="outputCsvAction"
        action-id="SUB020302_0302-01#A004"
        :request-model="pqGridOption"
        csv-file-name="信用建玉一覧（国内）"
        :is-check-csv-download-allowed="true"
        :is-check-csv-download-privacy-confirmation="true"
        :pre-request-handler="() => { result.outputCsvAction = 'PRE' }"
        @response-handler="result.outputCsvAction = 'OK'"
        @response-error-handler="result.outputCsvAction = 'ERROR'"
      ></ifa-button>
      <span class="label">チェック:</span><span class="value">{{ result.outputCsvAction }}</span>
    </h4>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4>
      【outputCsvFromGridAction】
      <ifa-button
        id="ifaButtonTest"
        text="テスト"
        small
        :disabled="disabled"
        action-type="outputCsvFromGridAction"
        action-id="SUB020302_0302-01#A004"
        :request-model="pqGridOption"
        csv-file-name="信用建玉一覧（国内）"
        :is-check-csv-download-allowed="true"
        :is-check-csv-download-privacy-confirmation="true"
        :pre-request-handler="() => { result.outputCsvGridAction = 'PRE' }"
        @response-handler="result.outputCsvGridAction = 'OK'"
        @response-error-handler="result.outputCsvGridAction = 'ERROR'"
      ></ifa-button>
      <span class="label">チェック:</span><span class="value">{{ result.outputCsvGridAction }}</span>
    </h4>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4>
      【fetchApiAction】
      <ifa-button
        text="テスト"
        search
        small
        :disabled="disabled"
        action-type="fetchApiAction"
        action-id="SUB020302_0302-01#A002"
        :request-model="A002RequestModel1"
        :set-param-func="paramFunctionA002"
        :pre-request-handler="() => { result.fetchApiAction = 'PRE' }"
        @response-handler="result.fetchApiAction = 'OK'"
        @response-error-handler="result.fetchApiAction = 'ERROR'"
      ></ifa-button>
      <span class="label">チェック:</span><span class="value">{{ result.fetchApiAction }}</span>
    </h4>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4>
      【requestAction (form)】
      <el-form
        ref="form"
        :model="form"
        :inline="true"
      >
        <ifa-button
          text="テスト"
          small
          :disabled="disabled"
          action-type="requestAction"
          action-id="Navbar#A004"
          :form="formRef"
          :pre-request-handler="() => { result.requestActionForm = 'PRE' }"
          @response-handler="result.requestActionForm = 'OK'"
          @response-error-handler="result.requestActionForm = 'ERROR'"
        ></ifa-button>
        <span class="label">チェック:</span><span class="value">{{ result.requestActionForm }}</span>
        <ifa-input-text
          v-model="form.text"
          prop="text"
          label="テキスト"
          size="small"
          required
        ></ifa-input-text>
      </el-form>
    </h4>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4>
      【IfaRquester】
      <ifa-button
        ref="request"
        text="RUN"
        small
        :disabled="disabled"
        action-type="originalAction"
        @app-action-handler="runRequester"
      ></ifa-button>
    </h4>
    <div class="result">
      <ifa-requester
        id="request"
        ref="request"
        action-type="requestAction"
        action-id="IfaCcsLink#A001"
        @response-handler="requester = 'OK'"
      ></ifa-requester>
      <span class="label">← hidden コンポーネントのため可視化されません｡　セレクト:</span><span class="value">{{ requester }}</span>
    </div>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4>
      【uploadAction】
      <div class="upload-action__section">
        <ifa-file-select
          ref="select"
          text="ファイル選択"
          small
          msg-title="ファイル選択"
          @change="handleChangeSingleFileSelection"
        ></ifa-file-select>
        <ifa-button
          text="確認"
          small
          :disabled="disabled"
          msg-title="アップロード"
          action-type="uploadAction"
          action-id="SUB0204_01-03_1#A003"
          :request-model="uploadSingleFileModel"
          :pre-request-handler="() => { result.uploadSingleFileAction = 'PRE' }"
          @response-handler="handleResponseUploadSingleFile"
          @response-error-handler="result.uploadSingleFileAction = 'ERROR'"
        ></ifa-button>
        <ifa-button
          text="クリア"
          color="secondary"
          small
          action-type="originalAction"
          @app-action-handler="handleClearSingleSelectedFile"
        ></ifa-button>
        <span class="label">ファイル:</span><span class="value">{{ uploadSingleFile }}</span>
      </div>
      <div style="margin-top: 1.5rem;">
        <span class="label">チェック:</span><span class="value">{{ result.uploadSingleFileAction }}</span>
      </div>
    </h4>

    <hr>
    <span
      v-if="disabled"
      style="color: red; background: yellow;"
    >※Development environment only.<br></span>
    <h4>
      【uploadAction (複数ファイル)】
      <div class="upload-action__section">
        <ifa-file-select
          ref="select1"
          text="ファイル1選択"
          small
          msg-title="ファイル1選択"
          @change="handleChangeMultipleFilesSelection($event, 0)"
        ></ifa-file-select>
        <span class="label">ファイル:</span><span class="value">{{ uploadMultipleFiles[0] }}</span>
      </div>
      <div class="upload-action__section">
        <ifa-file-select
          ref="select2"
          text="ファイル2選択"
          small
          msg-title="ファイル2選択"
          @change="handleChangeMultipleFilesSelection($event, 1)"
        ></ifa-file-select>
        <span class="label">ファイル:</span><span class="value">{{ uploadMultipleFiles[1] }}</span>
      </div>
      <div class="upload-action__section">
        <ifa-file-select
          ref="select3"
          text="ファイル3選択"
          small
          msg-title="ファイル3選択"
          @change="handleChangeMultipleFilesSelection($event, 2)"
        ></ifa-file-select>
        <span class="label">ファイル:</span><span class="value">{{ uploadMultipleFiles[2] }}</span>
      </div>
      <div class="upload-action__section">
        <ifa-file-select
          ref="select4"
          text="ファイル4選択"
          small
          msg-title="ファイル4選択"
          @change="handleChangeMultipleFilesSelection($event, 3)"
        ></ifa-file-select>
        <span class="label">ファイル:</span><span class="value">{{ uploadMultipleFiles[3] }}</span>
      </div>
      <div class="upload-action__section">
        <ifa-button
          text="アップロード"
          small
          :disabled="disabled"
          msg-title="アップロード"
          action-type="uploadAction"
          action-id="SUB0204_01-03_1#A003"
          :request-model="uploadMultipleFilesModel"
          :pre-request-handler="() => { result.uploadMultipleFilesAction = 'PRE' }"
          @response-handler="handleResponseUploadMultipleFiles"
          @response-error-handler="result.uploadMultipleFilesAction = 'ERROR'"
        ></ifa-button>
        <ifa-button
          text="クリア"
          color="secondary"
          small
          action-type="originalAction"
          @app-action-handler="handleClearMultipleSelectedFiles"
        ></ifa-button>
      </div>
      <div style="margin-top: 1.5rem;">
        <span class="label">チェック:</span><span class="value">{{ result.uploadMultipleFilesAction }}</span>
      </div>
    </h4>

  </div>
</template>

<script>
import { IfaCommonSearchTestFormModel } from '@/views/sandbox/IfaCommonSearchTestFormModel.js'
import { A002RequestModel1 } from '@/views/sandbox/A002RequestModel1'
import { A002RequestModel2 } from '@/views/sandbox/A002RequestModel2'
import { getDefaultOption } from '@/utils/pqgridHelper'
import { notifyWrapper } from '@/utils/errorHandler'
const testGridData = require('./resources/testGridData')

export default {
  data() {
    return {
      result: {
        originalAction: '',
        requestAction: '',
        outputCsvAction: '',
        outputCsvGridAction: '',
        fetchApiAction: '',
        requestActionForm: '',
        uploadSingleFileAction: '',
        uploadMultipleFilesAction: ''
      },
      form: {
        text: ''
      },
      formRef: {},
      pqGridOption: null,
      IfaCommonSearchTestFormModel: new IfaCommonSearchTestFormModel(),
      requester: '',
      uploadSingleFile: null,
      uploadMultipleFiles: [null, null, null, null]
    }
  },
  computed: {
    A002RequestModel1() { return new A002RequestModel1(this.IfaCommonSearchTestFormModel) },
    uploadSingleFileModel() {
      return {
        filename: 'uploadFile',
        file: this.uploadSingleFile
      }
    },
    uploadMultipleFilesModel() {
      return this.uploadMultipleFiles.map((file, index) => {
        return {
          filename: `f${index + 1}`,
          file: file
        }
      })
    },
    filesSelected() {
      return this.uploadMultipleFiles.filter(obj => obj)?.length || 0
    },
    disabled() {
      return process.env.NODE_ENV !== 'development'
    }
  },
  created() {
    this.pqGridOption = getDefaultOption(columns1)
  },
  mounted() {
    this.formRef = this.$refs['form']
  },
  methods: {
    onShow() {
      this.pqGridOption.dataModel.data = testGridData.get()
    },
    paramFunctionA002(data) {
      this.$_logDebug('fetch api action: set-param-func', data)
      if (data) {
        return JSON.parse(JSON.stringify(new A002RequestModel2({
          butenCode: data.butenCode,
          accountNumber: data.accountNumber,
          domesticMarginPositionListBrandCode: data.domesticMarginPositionListBrandCode
        })))
      } else {
        return null
      }
    },
    runRequester() {
      document.getElementById('request').click()
    },
    handleChangeSingleFileSelection(file) {
      this.uploadSingleFile = file
      this.result.uploadSingleFileAction = file ? `Selected: ${file.name} (${file.size})` : ''
    },
    handleResponseUploadSingleFile(response, numberOfFiles) {
      notifyWrapper({
        title: 'アップロード完了',
        message: numberOfFiles + '件のデータが登録されました。',
        type: 'success'
      })
      this.handleClearSingleSelectedFile()
      this.result.uploadSingleFileAction = 'OK'
    },
    handleClearSingleSelectedFile() {
      this.$refs['select'].clearFiles()
      this.uploadSingleFile = null
      this.result.uploadSingleFileAction = 'CLEARED'
    },
    handleChangeMultipleFilesSelection(file, index) {
      this.uploadMultipleFiles[index] = file
      this.result.uploadMultipleFilesAction = `${this.filesSelected} File(s) selected`
    },
    handleResponseUploadMultipleFiles(response, numberOfFiles) {
      notifyWrapper({
        title: 'アップロード完了',
        message: numberOfFiles + '件のデータが登録されました。',
        type: 'success'
      })
      this.handleClearMultipleSelectedFiles()
      this.result.uploadMultipleFilesAction = 'OK'
    },
    handleClearMultipleSelectedFiles() {
      this.$refs['select1'].clearFiles()
      this.$refs['select2'].clearFiles()
      this.$refs['select3'].clearFiles()
      this.$refs['select4'].clearFiles()
      this.uploadMultipleFiles = [null, null, null, null]
      this.result.uploadMultipleFilesAction = 'CLEARED'
    }
  }
}
const codeValue = {
  codeListId: 'INVITATION_TYPE',
  dispPattern: 1
}
const columns1 = [
  { title: 'Column 01', dataType: 'string', dataIndx: 'col01', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 02', dataType: 'string', dataIndx: 'col02', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 03', dataType: 'string', dataIndx: 'col03', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 04', dataType: 'string', dataIndx: 'col04', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 05', dataType: 'string', dataIndx: 'col05', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 06', dataType: 'string', dataIndx: 'col06', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 07', dataType: 'string', dataIndx: 'col07', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 08', dataType: 'string', dataIndx: 'col08', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 09', dataType: 'string', dataIndx: 'col09', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 10', dataType: 'string', dataIndx: 'col10', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 11', dataType: 'string', dataIndx: 'col11', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 12', dataType: 'string', dataIndx: 'col12', minWidth: 80, halign: 'center', align: 'center', codeValue },
  { title: 'Column 13', dataType: 'string', dataIndx: 'col13', minWidth: 80, halign: 'center', align: 'center', codeValue }
]
</script>

<style lang="scss">
.main {
  margin: 2rem;
}
.label {
  padding-left: 6.5rem;
  padding-right: 1rem;
}
.value {
  padding: 0.4rem;
  background-color: white;
  border: 1px solid blue;
}
.upload-action__section {
  display: flex;
  margin: 0.5em;
}
</style>
