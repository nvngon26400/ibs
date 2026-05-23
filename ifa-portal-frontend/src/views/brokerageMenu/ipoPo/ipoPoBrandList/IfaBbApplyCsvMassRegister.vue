<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :close-on-click-modal="false"
      :show-close="false"
      width="1400px"
      :before-close="onDialogClose"
      @open="onOpen"
    >
      <!-- 戻るボタン -->
      <el-row>
        <el-col
          :span="23"
          class="form-button__wrapper"
          style="margin-right: 0px;"
        >
          <ifa-button
            color="secondary"
            text="戻る"
            action-type="originalAction"
            @app-action-handler="beforeClose"
          ></ifa-button>
        </el-col>
      </el-row>
      <!-- BB申込(一括登録)  -->
      <caption-card
        style="margin: 1rem 0 1rem 0;"
        :caption="formModel.title.name"
        text-size="20px"
        text-color="#0058a2"
        background-color="Menu"
      >
        <el-form
          ref="form"
          label-position="right"
          label-width="208px"
          :inline="true"
        >
          <!-- ファイル操作エリア -->
          <el-row style="height: 150px">
            <el-col>
              <el-row style="margin-bottom: 20px;">
                <el-row>
                  <el-col
                    :span="1"
                    class="right_icon"
                  >
                    <el-popover
                      :visible="isInfoMessage"
                      placement="bottom-end"
                      width="620"
                      transition=""
                    >
                      <div v-html="comment.txaComment"></div>
                      <template #reference>
                        <el-icon
                          class="el-icon-info"
                          @click="clickShowMessage"
                        ><InfoFilled></InfoFilled></el-icon>
                      </template>
                    </el-popover>
                  </el-col>
                </el-row>
                <el-row class="fileupload_form">
                  <el-col :span="23">
                    <div class="upload-action__section">
                      <ifa-file-select
                        ref="select"
                        text="ファイルを選択"
                        small
                        msg-title="ファイルを選択"
                        :show-file-list="true"
                        @before-remove="handleClearSingleSelectedFile"
                        @change="handleChangeSingleFileSelection"
                      ></ifa-file-select>
                      <ifa-button
                        id="btnConfirm"
                        name="btnConfirm"
                        text="確認"
                        small
                        msg-title="BB申込(一括登録)"
                        :disabled="uploadSingleFileButtonDisabled"
                        action-type="uploadAction"
                        action-id="SUB0204_01-03_1#A003"
                        :request-model="uploadSingleFileModel"
                        :pre-request-handler="() => { $_logDebug('call pre-request-handler') }"
                        @response-handler="handleUploadSingleFileResponse"
                        @response-error-handler="handleUploadSingleFileResponseError"
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
              </el-row>
            </el-col>
          </el-row>
          <!-- BB申込一覧エリア -->
          <el-row>
            <grid-table
              ref="bbApplyGridTable"
              :options="optionss"
              :auto-refresh="false"
              @click="handleClick"
            ></grid-table>
          </el-row>
          <!-- 操作エリア -->
          <el-row style="margin-top: 10px;">
            <ifa-button
              id="btnRegister"
              name="btnRegister"
              text="登録"
              msg-title="BB申込(一括登録)"
              :disabled="disabled.btnRegister"
              color="primary"
              action-id="SUB0204_01-03_1#A007"
              action-type="requestAction"
              :request-model="ifaBbApplyCsvMassRegisterA007RequestModel"
              @response-handler="register"
            ></ifa-button>
          </el-row>
        </el-form>
      </caption-card>
    </el-dialog>
    <ifa-ok-cancel-dialog
      :is-visible="isOkCancelDialogVisible"
      :title="formModel.title.name"
      message="未登録のデータがありますが、よろしいですか？"
      @close-modal-ok="onDialogClose"
      @close-modal-cancel="isOkCancelDialogVisible = false"
    ></ifa-ok-cancel-dialog>
    <!-- BB申込(一括登録)メッセージ一覧 -->
    <ifa-bb-apply-csv-mass-register-msg-list
      :is-visible="dialogbbApplyCsvBatchUploadMsgListVisible"
      :msg-list="msgList"
      @close-modal="dialogbbApplyCsvBatchUploadMsgListVisible = false"
    ></ifa-bb-apply-csv-mass-register-msg-list>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import GridTable from '@/components/GridTable'
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction.js'
import { IfaBbApplyCsvMassRegisterFormModel } from './js/IfaBbApplyCsvMassRegisterFormModel'
import { IfaBbApplyCsvMassRegisterA007RequestModel } from './js/IfaBbApplyCsvMassRegisterA007RequestModel'
import IfaBbApplyCsvMassRegisterMsgList from './IfaBbApplyCsvMassRegisterMsgList'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
export default {
  components: {
    captionCard,
    GridTable,
    IfaBbApplyCsvMassRegisterMsgList,
    IfaOkCancelDialog
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    comment: {
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
      closeOk: true,
      isOkCancelDialogVisible: false,
      formModel: new IfaBbApplyCsvMassRegisterFormModel(),
      dialogbbApplyCsvBatchUploadMsgListVisible: false,
      optionss: {
        showTop: false,
        flexHeight: false,
        flexWidth: false,
        collapsible: false,
        showTitle: true,
        numberCell: { show: false },
        // Selected single
        selectionModel: { type: 'row', mode: 'single' },
        topVisible: false,
        wrap: false,
        reactive: true,
        locale: 'en',
        height: 'flex',
        columnTemplate: { width: 100 },
        colModel: colModel,
        dataModel: {
          data: []
        },
        maxHeight: 750,
        editable: false
      },
      disabled: {
        btnConfirm: true,
        btnRegister: true
      },
      isInfoMessage: false,
      // fileListの初期設定（1つファイルをアップロードする場合）
      uploadSingleFile: null,
      msgList: [],
      uploadDataList: []
    }
  },
  computed: {
    uploadSingleFileButtonDisabled() {
      return this.uploadSingleFile === null
    },
    uploadSingleFileModel() {
      return {
        filename: 'uploadFile',
        file: this.uploadSingleFile
      }
    },
    ifaBbApplyCsvMassRegisterA007RequestModel() {
      const bbApplyListA007 = this.formModel.bbApplyList.map(({ checkResultSetList, ...rest }) => rest)
      const ifaBbApplyCsvMassRegisterA007RequestModel = new IfaBbApplyCsvMassRegisterA007RequestModel(bbApplyListA007)
      return ifaBbApplyCsvMassRegisterA007RequestModel
    }
  },
  created() {
    this.optionss.wrap = true
  },
  methods: {
    onOpen() {
      this.refresh()
      this.optionss.dataModel.data = []
      this.$refs['bbApplyGridTable'].refreshView()
    },
    beforeClose() {
      if (this.closeOk) {
        this.onDialogClose()
      } else {
        this.isOkCancelDialogVisible = true
      }
    },
    onDialogClose() {
      this.refresh()
      this.isOkCancelDialogVisible = false
      this.optionss.dataModel.data = []
      this.$emit('close-modal')
    },
    // 初期表示時に通知を表示
    firstShowMessage() {
      this.$nextTick(() => {
        if (this.comment.txaComment.length > 0) {
          this.isInfoMessage = true
          setTimeout(() => {
            this.isInfoMessage = false
          }, 2000)
        }
      })
    },
    // 通知の表示/非表示を切り替える
    clickShowMessage() {
      if (this.comment.txaComment.length > 0) {
        this.isInfoMessage = !this.isInfoMessage
      } else {
        this.isInfoMessage = false
      }
    },
    refresh() {
      this.handleClearSingleSelectedFile()
      this.closeOk = true
      this.disabled.btnRegister = true
      this.isInfoMessage = false
    },
    // クリアボタン押下
    handleClear() {
      this.optionss.dataModel.data = []
      this.$refs['bbApplyGridTable'].refreshView()
      this.closeOk = true
      this.disabled.btnRegister = true
    },
    // 登録ボタン
    register(response) {
      this.optionss.dataModel.data = response.dataList[0].bbApplyList
      this.$nextTick(() => {
        this.$refs['bbApplyGridTable'].refreshView()
      })
      this.closeOk = true
      this.disabled.btnRegister = true
    },
    handleClick(ui) {
      const checkResult = ui.rowData.checkResult
      if (ui.dataIndx === 'checkResult') {
        if (checkResult === 'NGあり') {
          this.msgList = ui.rowData.checkResultSetList
          this.dialogbbApplyCsvBatchUploadMsgListVisible = true
        } else if (checkResult === '警告あり') {
          this.msgList = ui.rowData.checkResultSetList
          this.dialogbbApplyCsvBatchUploadMsgListVisible = true
        }
      }
    },
    // 1つファイルアップロード
    handleChangeSingleFileSelection(file) {
      this.uploadSingleFile = file
    },
    handleUploadSingleFileResponse(response, numberOfFiles) {
      Object.assign(this.formModel.bbApplyList, response.dataList[0].bbApplyList)
      if (response.dataList[0].bbApplyList?.length > 1000) {
        this.optionss.dataModel.data = response.dataList[0].bbApplyList.slice(0, 1000)
      } else {
        this.optionss.dataModel.data = response.dataList[0].bbApplyList
      }
      this.$nextTick(() => {
        this.$refs['bbApplyGridTable'].refreshView()
      })
      if (response.dataList[0].bbApplyList.some(item => item.checkResult === 'OK') || response.dataList[0].bbApplyList.some(item => item.checkResult === '警告あり')) {
        this.disabled.btnRegister = false
        this.closeOk = false
      } else {
        this.disabled.btnRegister = true
        this.closeOk = true
      }
      this.handleClearSingleSelectedFile()
    },
    handleUploadSingleFileResponseError(response) {
      this.$_logDebug('handleSingleUploadResponseError')
    },
    handleClearSingleSelectedFile() {
      this.$refs['select'].clearFiles()
      this.uploadSingleFile = null
    }
  }

}
const colModel = [
  {
    title: '銘柄コード',
    dataIndx: 'brandCode',
    width: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '銘柄名',
    dataIndx: 'brandName',
    width: 250,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '部店',
    dataIndx: 'butenCode',
    width: 65,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    minWidth: 80,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '顧客名',
    dataIndx: 'customerName',
    width: 135,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '希望株数',
    dataIndx: 'bbQuantity',
    width: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ui.rowData.bbQuantity ? ifaFormatUtils.withCommaInteger(ui.rowData.bbQuantity) : '-'
    }
  },
  {
    title: '申込価格',
    dataIndx: 'bbPrice',
    minWidth: 100,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      if (ui.rowData.bbGestureValue === '1') {
        return ui.rowData.bbPrice ? ifaFormatUtils.withCommaInteger(ui.rowData.bbPrice) : '-' + '円'
      } else if (ui.rowData.bbGestureValue === '2') {
        return ui.rowData.bbPrice ? ifaFormatUtils.withCommaInteger(ui.rowData.bbPrice) : '-' + '%'
      } else {
        return ui.rowData.bbPrice ? ui.rowData.bbPrice : '-'
      }
    }
  },
  {
    title: '発行価格区分',
    dataIndx: 'bbGestureValue',
    dataType: 'string',
    hidden: true
  },
  {
    title: '投資家属性',
    dataIndx: 'bbInvestorAttName',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '投資家属性順序',
    dataIndx: 'bbSeq',
    dataType: 'string',
    hidden: true
  },
  {
    title: '裁量希望株数',
    dataIndx: 'quantitySairyou',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ui.rowData.quantitySairyou ? ifaFormatUtils.withCommaInteger(ui.rowData.quantitySairyou) : '-'
    }
  },
  {
    title: '裁量選定理由',
    dataIndx: 'choseReason',
    width: 145,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      if (ui.rowData.choseReason === '' || ui.rowData.choseReason === null) {
        return '-'
      }
      return getCodeValue('DISCRETION_SELECT_REASON', 1, ui.rowData.choseReason) || '-'
    }
  },
  {
    title: '備考',
    dataIndx: 'bbRemark',
    width: 155,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '勧誘区分',
    dataIndx: 'kanyuKbn',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      if (ui.rowData.kanyuKbn === '' || ui.rowData.kanyuKbn === null) {
        return '-'
      }
      return getCodeValue('INVITATION_TYPE', 1, ui.rowData.kanyuKbn) || '-'
    }
  },
  {
    title: '受注方法',
    dataIndx: 'receiveOrderType',
    width: 120,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'left',
    render: function(ui) {
      if (ui.rowData.receiveOrderType === '' || ui.rowData.receiveOrderType === null) {
        return '-'
      }
      return getCodeValue('ORDER_METHOD_TYPE', 1, ui.rowData.receiveOrderType) || '-'
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
    render: function(ui) {
      const checkResult = ui.rowData.checkResult ? ui.rowData.checkResult : '-'
      const checkResultSetList = ui.rowData.checkResultSetList ? ui.rowData.checkResultSetList : []
      if (checkResult === 'NGあり') {
        return `<a class="el-link el-link--primary"><span class="el-link--inner">` + checkResult + `</span><sup class="el-badge__content item">` + checkResultSetList.length + `</sup></a>
        <style>
        .item {
          position: absolute;
          top: 0;
          right: 10px;
          -webkit-transform: translateY(-50%) translateX(100%);
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
      } else if (checkResult === '警告あり') {
        return `<a class="el-link el-link--primary"><span class="el-link--inner">` + checkResult + `</span><sup class="el-badge__content item">` + checkResultSetList.length + `</sup></a>
        <style>
        .item {
          position: absolute;
          top: 0;
          right: 10px;
          -webkit-transform: translateY(-50%) translateX(100%);
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
      } else {
        return checkResult
      }
    }
  },
  {
    title: 'チェック結果セット',
    dataIndx: 'checkResultSetList',
    dataType: 'string',
    hidden: true
  }
]
</script>

<style lang="scss" scoped>
@import '@/styles/bbApplyCsvBatchUpload.scss';
.upload-action__section {
  display: flex;
  .selected-file-info {
    display: flex;
    border: 1px solid blue;
    align-items: center;
    min-width: 1000px;
    height: 2em;
    margin-left: 0.5em;
  }
}
:deep(.el-overlay:nth-child(2) .el-dialog) {
  width: 470px!important;
}
:deep(.el-upload-list.el-upload-list--text) {
  width: 42rem!important;
}
:deep(.upload-action__section>.ifa-file-select__wrapper) {
  width: 133px!important;
}
:deep(.el-link) {
  text-decoration: underline !important;
}
:deep(.el-link:hover) {
  opacity: 0.7;
}
</style>
