<template>
  <div>
    <el-form ref="form"
             :model="form"
             :inline="true"
    >
      <el-card class="content-card">
        <div class="filter-container">
          <el-row>
            <el-col>
              <el-form-item class="form_label">
                <ifa-input-select v-model="form.lecId"
                                  :model-value="form.lecId"
                                  :code-list-id="'original'"
                                  label="表示年月"
                                  prop="lecId"
                                  :original-list="form.slctYmList"
                                  style="width: 140px;"
                                  :required="true"
                                  :clearable="false"
                                  @change="communicationDateSearch()"
                >
                </ifa-input-select>
              </el-form-item>
            </el-col>
          </el-row>
        </div>

        <div class="content-area">
          <el-row>
            <el-col class="area-border-color"
                    style="padding: 1em 1.5em ; "
            >
              <span>{{ $_out(form.corTitle) }}</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col class="area-border-color"
                    style="padding: 4.5em 1.5em ; white-space: pre-wrap;"
            >
              <span>{{ $_out(form.corBrief) }}</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col class="area-border-color"
                    style="padding: 1em 1.5em ;"
            >
              <div style="color:#ff1e00;">※「コンプライアンス通信コンテンツ」をクリックすると、本文ＰＤＦファイルが表示されます。<br>
                内容をご確認後、ファイルを閉じたあとに「確認しました」ボタンが活性化されますので、<br>
                必ずボタンを押下してください。（ボタン押下後はボタンの表示が「確認済み」と変わります）<br>
              </div>
              <div style="padding: 1em 1.5em ;">
                <span v-if="confirmDisplay">
                  <ifa-button id="btnConfirm"
                              name="btnConfirm"
                              text="確認しました"
                              :disabled="btnConfirmDisabled"
                              width="110"
                              small=""
                              action-id="SUB0302-01#A003"
                              action-type="requestAction"
                              :request-model="a003RequestModel"
                              @response-handler="confirmButtonPressA003($event)"
                              @response-error-handler="responseHandlerA003($event)"
                  ></ifa-button>
                </span>
                <span v-if="isConfirmedDisplay">
                  <ifa-button id="btnIsConfirmed"
                              name="btnIsConfirmed"
                              text="確認済"
                              width="110"
                              small=""
                              :disabled="btnIsConfirmedDisabled"
                  ></ifa-button>
                </span>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <el-icon style="height: auto; margin-right: 3px;"><Document></Document></el-icon>
                <a
                  style="text-decoration: underline; text-underline-offset:0.2em; color:#409EFF;"
                  @click="downloadFile()"
                >
                  {{ $_out(form.corContents || form.corContentsFileName) }}
                </a>
              </div>
            </el-col>
          </el-row>
          <el-row v-if="form.corFileName1 || form.corFileName2 || form.corFileName3">
            <el-col style="padding: 1em 1.5em ;">
              <div v-if="form.corFileName1">
                <el-icon style="height: auto; margin-right: 3px;"><Document></Document></el-icon>
                <a
                  style="text-decoration: underline; text-underline-offset:0.2em; color:#409EFF;"
                  @click="downloadFile1()"
                >
                  {{ $_out(form.corFileDesc1 || form.corFileName1) }}
                </a><br>
              </div>
              <div v-if="form.corFileName2">
                <el-icon style="height: auto; margin-right: 3px;"><Document></Document></el-icon>
                <a
                  style="text-decoration: underline; text-underline-offset:0.2em; color:#409EFF;"
                  @click="downloadFile2()"
                >
                  {{ $_out(form.corFileDesc2 || form.corFileName2) }}
                </a><br>
              </div>
              <div v-if="form.corFileName3">
                <el-icon style="height: auto; margin-right: 3px;"><Document></Document></el-icon>
                <a
                  style="text-decoration: underline; text-underline-offset:0.2em; color:#409EFF;"
                  @click="downloadFile3()"
                >
                  {{ $_out(form.corFileDesc3 || form.corFileName3) }}
                </a><br>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-card>
    </el-form>
  </div>

  <ifa-requester
    id="ifaComplianceReportInitializeA001"
    action-id="SUB0302-01#A001"
    action-type="requestAction"
    @response-handler="responseHandlerInitializeA001($event)"
    @response-error-handler="errorHandlerInitializeA001($event)"
  ></ifa-requester>

  <ifa-requester
    id="ifaComplianceReportDisplayYearMonthChangeX001"
    action-id="SUB0302-01#X001"
    action-type="requestAction"
    :request-model="x001RequestModel"
    @response-handler="displayYearMonthChangeX001($event)"
    @response-error-handler="responseHandlerX001($event)"
  ></ifa-requester>

  <ifa-requester
    id="ifaComplianceReportContentFileA004"
    action-id="SUB0302-01#A004"
    action-type="outputPdfAction"
    :request-model="a004RequestModel"
    @response-handler="contentFileA004($event)"
    @response-error-handler="responseHandlerA004($event)"
  ></ifa-requester>

  <ifa-requester
    id="ifaComplianceReportAttachFileA005"
    action-id="SUB0302-01#A005"
    action-type="outputPdfAction"
    :request-model="a005RequestModel"
  ></ifa-requester>
</template>

<script>
import { IfaComplianceReportFormModel } from './js/IfaComplianceReportFormModel'
import { IfaComplianceReportX001RequestModel } from './js/IfaComplianceReportX001RequestModel'
import { IfaComplianceReportA003RequestModel } from './js/IfaComplianceReportA003RequestModel'
import { IfaComplianceReportA004RequestModel } from './js/IfaComplianceReportA004RequestModel'
import { getMessage, notifyWrapper } from '@/utils/errorHandler'

export default {
  emits: ['initializeError'],
  data() {
    return {
      form: new IfaComplianceReportFormModel(),
      btnConfirmDisabled: true,
      btnIsConfirmedDisabled: true,
      confirmDisplay: true,
      isConfirmedDisplay: false,
      a004RequestModel: null,
      a005RequestModel: {}
    }
  },
  computed: {
    x001RequestModel() {
      return new IfaComplianceReportX001RequestModel(this.form)
    },
    a003RequestModel() {
      return new IfaComplianceReportA003RequestModel(this.form)
    }
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('ifaComplianceReportInitializeA001').click()
      })
    },
    responseHandlerInitializeA001(data) {
      const len = data.dataList[0].slctYmList.length
      for (let i = 0; i < len; i++) {
        this.form.slctYmList.push({ key: data.dataList[0].slctYmList[i].lecId, value: data.dataList[0].slctYmList[i].communicationDate })
      }
      this.form.slctYm = data.dataList[0].slctYm // 表示年月
      this.form.corCommunicationDate = data.dataList[0].corCommunicationDate // 通信年月
      this.form.fileDir = data.dataList[0].fileDir // ファイルディレクトリ
      this.form.corTitle = data.dataList[0].corTitle // タイトル
      this.form.corBrief = data.dataList[0].corBrief // 概要
      this.form.corContentsFileName = data.dataList[0].corContentsFileName
      this.form.corFileName1 = data.dataList[0].corFileName1
      this.form.corFileName2 = data.dataList[0].corFileName2
      this.form.corFileName3 = data.dataList[0].corFileName3
      this.form.confirmFlg = data.dataList[0].confirmFlg || '0' // 確認フラグ
      this.form.corBrowseFlagInd = data.dataList[0].corBrowseFlagInd // 個別閲覧要否フラグ
      this.form.corBrowseFlagBul = data.dataList[0].corBrowseFlagBul // 一括閲覧要否フラグ
      this.form.confirmBtn = data.dataList[0].confirmBtn // 確認ボタン

      const reportInfo = this.form.slctYmList.find(report => report.value === this.form.slctYm)
      this.form.lecId = reportInfo.key // LECTURE_ID
      this.form.corFileDesc1 = data.dataList[0].corFileDesc1
      this.form.corFileDesc2 = data.dataList[0].corFileDesc2
      this.form.corFileDesc3 = data.dataList[0].corFileDesc3
      this.form.corContents = data.dataList[0].corContents
      if (this.form.confirmBtn === '1') {
        // 確認ボタン＝１：確認済状態（Actionのレスポンス）の場合、非活性、ボタン文言＝「確認済」
        this.confirmDisplay = false
        this.isConfirmedDisplay = true
        this.btnConfirmDisabled = true
        this.btnIsConfirmedDisabled = true
      } else {
        // 確認ボタン＝１：確認済状態（Actionのレスポンス）以外の場合、非活性、ボタン文言＝「確認しました」、コンテンツファイル表示後に活性
        this.confirmDisplay = true
        this.isConfirmedDisplay = false
        this.btnConfirmDisabled = true
        this.btnIsConfirmedDisabled = true
      }
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    displayYearMonthChangeX001(data) {
      this.form.fileDir = data.dataList[0].fileDir // ファイルディレクトリ
      this.form.corTitle = data.dataList[0].corTitle // タイトル
      this.form.corBrief = data.dataList[0].corBrief // 概要
      this.form.corContentsFileName = data.dataList[0].corContentsFileName
      this.form.corFileName1 = data.dataList[0].corFileName1
      this.form.corFileName2 = data.dataList[0].corFileName2
      this.form.corFileName3 = data.dataList[0].corFileName3
      this.form.confirmFlg = data.dataList[0].confirmFlg || '0' // 確認フラグ
      this.form.corBrowseFlagInd = data.dataList[0].corBrowseFlagInd // 個別閲覧要否フラグ
      this.form.corBrowseFlagBul = data.dataList[0].corBrowseFlagBul // 一括閲覧要否フラグ
      this.form.confirmBtn = data.dataList[0].confirmBtn // 確認ボタン
      this.form.corFileDesc1 = data.dataList[0].corFileDesc1
      this.form.corFileDesc2 = data.dataList[0].corFileDesc2
      this.form.corFileDesc3 = data.dataList[0].corFileDesc3
      this.form.corContents = data.dataList[0].corContents
      if (this.form.confirmBtn === '1') {
        // 確認ボタン＝１：確認済状態（Actionのレスポンス）の場合、非活性、ボタン文言＝「確認済」
        this.confirmDisplay = false
        this.isConfirmedDisplay = true
        this.btnConfirmDisabled = true
        this.btnIsConfirmedDisabled = true
      } else {
        // 確認ボタン＝１：確認済状態（Actionのレスポンス）以外の場合、非活性、ボタン文言＝「確認しました」、コンテンツファイル表示後に活性
        this.confirmDisplay = true
        this.isConfirmedDisplay = false
        this.btnConfirmDisabled = true
        this.btnIsConfirmedDisabled = true
      }
    },
    responseHandlerX001(data) {
    },
    contentFileA004(data) {
      this.btnConfirmDisabled = false
      // コンプライアンス通信確認.確認フラグ ＝ '1'（確認済）以外の場合
      if (this.form.confirmFlg !== '1') {
        this.btnConfirmDisabled = false
      }
      // コンプライアンス通信確認.確認フラグ ＝ '1'（確認済）の場合
      if (this.form.confirmFlg === '1') {
        this.btnConfirmDisabled = true
      }
    },
    responseHandlerA004(data) {
    },
    communicationDateSearch() {
      // X001呼び出し
      document.getElementById('ifaComplianceReportDisplayYearMonthChangeX001').click()
    },
    downloadFile() {
      const obj = {
        filename: this.form.corContentsFileName, // ファイル名
        directory: this.form.fileDir, // ファイルパス
        confirmFlg: this.form.confirmFlg // 確認フラグ
      }
      this.a004RequestModel = new IfaComplianceReportA004RequestModel(obj)
      this.$nextTick(() => {
        document.getElementById('ifaComplianceReportContentFileA004').click()
      })
    },
    downloadFile1() {
      this.a005RequestModel = {
        filename: this.form.corFileName1, // ファイル名
        directory: this.form.fileDir // ファイルパス
      }
      this.$nextTick(() => {
        document.getElementById('ifaComplianceReportAttachFileA005').click()
      })
    },
    downloadFile2() {
      this.a005RequestModel = {
        filename: this.form.corFileName2, // ファイル名
        directory: this.form.fileDir // ファイルパス
      }
      this.$nextTick(() => {
        document.getElementById('ifaComplianceReportAttachFileA005').click()
      })
    },
    downloadFile3() {
      this.a005RequestModel = {
        filename: this.form.corFileName3, // ファイル名
        directory: this.form.fileDir // ファイルパス
      }
      this.$nextTick(() => {
        document.getElementById('ifaComplianceReportAttachFileA005').click()
      })
    },
    confirmButtonPressA003(data) {
      // 確認フラグ
      this.form.confirmFlg = data.dataList[0].confirmFlg
      this.btnConfirmDisabled = true
      this.btnIsConfirmedDisabled = true
      this.confirmDisplay = false
      this.isConfirmedDisplay = true
      notifyWrapper({
        title: this.form.complianceReporTitle.name,
        message: getMessage('info.ita.Confirmation'),
        type: 'success'
      })
    },
    responseHandlerA003(data) {
      this.$_logDebug('responseHandlerA003')
    }
  }
}
</script>

<style scoped>
.content-card {
  margin: 0.5rem 1rem;
}
.content-area {
  margin : 1em 1.5em ;
    line-height : 1.8 ;
    border : solid 1px rgb(189, 189, 189) ;
}
.area-border-color {
  border-bottom: solid 1px rgb(189, 189, 189);
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
}
.form_button {
  padding: 0.4rem 2rem 1.2rem 0;
}
.content-card {
  margin: 0.5rem 1rem;
}
.filter-container {
  margin: 1rem 0 1rem;
}
a, a:focus, a:hover {
          cursor: pointer;
        }
.item {
    font-size: 15px;
  }

.select-style {
  font-size: 14px;
}
</style>
