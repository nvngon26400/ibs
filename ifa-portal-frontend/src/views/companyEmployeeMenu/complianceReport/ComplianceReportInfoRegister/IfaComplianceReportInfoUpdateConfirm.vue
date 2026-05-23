<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      left
      :close-on-click-modal="false"
      :show-close="false"
      width="1000px"
      @open="setOkCancelDialogParams"
    >
      <div>
        <el-row>
          <el-col>
            <page-caption
              text="コンプライアンス通信登録（変更）確認画面"
              background-color="rgba(190, 190, 190, 0.35)"
            ></page-caption>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <div class="form-cancel-button__wrapper">
              <ifa-button
                text="戻る"
                color="secondary"
                small
                action-type="originalAction"
                @app-action-handler="onDialogClose"
              ></ifa-button>
            </div>
          </el-col>
        </el-row>
      </div>

      <div style="margin-top:0.5rem;">
        <table
          id="t1"
          style="margin-bottom: 0.5rem;width:100%"
        >
          <tbody>
            <tr>
              <th class="_table__header __left">通信年月</th>
              <td class="_table__data __left">{{ formModel.yearMonth }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">タイトル</th>
              <td class="_table__data __left">{{ formModel.title }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">概要</th>
              <td class="_table__data __left">{{ formModel.overview }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">ファイル1</th>
              <td class="_table__data __left">{{ formModel.file1.file?.name }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">ファイル1コメント</th>
              <td class="_table__data __left">{{ formModel.file1.comment }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">ファイル2</th>
              <td class="_table__data __left">{{ formModel.file2.file?.name }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">ファイル2コメント</th>
              <td class="_table__data __left">{{ formModel.file2.comment }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">ファイル3</th>
              <td class="_table__data __left">{{ formModel.file3.file?.name }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">ファイル3コメント</th>
              <td class="_table__data __left">{{ formModel.file3.comment }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">コンテンツファイル</th>
              <td class="_table__data __left">{{ formModel.contentFile.file?.name }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">コンテンツコメント</th>
              <td class="_table__data __left">{{ formModel.contentFile.comment }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <el-row style="margin-top: 20px;">
        <el-col
          :offset="0"
          :span="22"
          style="text-align: left;"
        >
          <ifa-button
            :text="formModel.isUpdateMode ? '更新' : '登録'"
            color="primary"
            action-type="originalAction"
            style="margin-left: -2px"
            @app-action-handler="openOkCancelDialog"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>
    <!-- 更新 ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="okCancelDialog.visible"
      :title="okCancelDialog.title"
      :message="okCancelDialog.message"
      :enable-ok="okCancelDialog.enableOk"
      :enable-cancel="okCancelDialog.enableCancel"
      @close-modal-ok="handleCloseModalOk"
      @close-modal-cancel="handleCloseModalCancel"
    ></ifa-ok-cancel-dialog>
    <ifa-requester
      id="ifaComplianceReportInfoUpdateA007a"
      :key="ifaComplianceReportInfoUpdateA007aRequestModel"
      action-id="SUB0505_01-04_1#A007a"
      action-type="uploadAction"
      :request-model="files"
      @response-handler="responseA007aHandler"
      @response-error-handler="responseA007aErrorHandler"
    ></ifa-requester>
    <ifa-requester
      id="ifaComplianceReportInfoUpdateA007b"
      action-id="SUB0505_01-04_1#A007b"
      action-type="requestAction"
      :request-model="ifaComplianceReportInfoUpdateA007bRequestModel"
      @response-handler="responseA007bHandler"
      @response-error-handler="responseA007bErrorHandler"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog'
import { useVModel } from 'vue-composable'
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption'
import { IfaComplianceReportInfoUpdateA007aRequestModel } from './js/IfaComplianceReportInfoUpdateA007aRequestModel'
import { IfaComplianceReportInfoUpdateA007bRequestModel } from './js/IfaComplianceReportInfoUpdateA007bRequestModel'
import { notifyWrapper } from '@/utils/errorHandler'

export default {
  components: {
    IfaOkCancelDialog,
    pageCaption
  },
  props: {
    isVisible: {
      type: Boolean,
      reqdatared: true
    },
    formModel: { type: Object, required: true }
  },
  emits: ['close-modal', 'update:isVisible', 'compliance-info-registered'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      // 項目名
      okCancelDialog: {
        visible: false,
        title: '',
        message: '',
        enableOk: true,
        enableCancel: true
      }
    }
  },
  computed: {
    // アプロードファイルリスト
    files() {
      // 更新
      return Object.entries(this.ifaComplianceReportInfoUpdateA007aRequestModel).map(([key, value]) => ({ filename: key, file: value.file }))
    },
    // 更新
    ifaComplianceReportInfoUpdateA007aRequestModel() {
      return new IfaComplianceReportInfoUpdateA007aRequestModel(this.formModel)
    },
    ifaComplianceReportInfoUpdateA007bRequestModel() {
      return new IfaComplianceReportInfoUpdateA007bRequestModel(this.formModel)
    }
  },
  methods: {
    setOkCancelDialogParams() {
      this.okCancelDialog.title = '更新確認'
      this.okCancelDialog.message = 'コンプライアンス通信情報を更新します。よろしいですか？'
    },
    openOkCancelDialog() {
      this.okCancelDialog.visible = true
    },
    // 戻る
    onDialogClose() {
      this.$emit('close-modal')
    },
    // スタートアプロード
    handleCloseModalOk() {
      this.okCancelDialog.visible = false
      if (this.files.find(file => file.file)) {
        document.querySelector('#ifaComplianceReportInfoUpdateA007a').click()
      } else {
        document.querySelector('#ifaComplianceReportInfoUpdateA007b').click()
      }
    },
    handleCloseModalCancel() {
      this.okCancelDialog.visible = false
    },
    // 更新アプロード正常終了
    responseA007aHandler(data) {
      this.$_logDebug(data)
      if (data.returnCode === 'errors.processingFailed') {
        notifyWrapper({
          title: this.$store.getters.pageInfo?.label ?? '',
          message: 'コンプライアンス通信情報の更新が失敗しました。',
          type: 'error'
        })
      } else {
        Object.assign(this.ifaComplianceReportInfoUpdateA007bRequestModel, data.dataList[0])
        document.querySelector('#ifaComplianceReportInfoUpdateA007b').click()
      }
    },
    responseA007aErrorHandler(error) {
      this.$_logError(error)
    },
    // 更新完了
    responseA007bHandler(data) {
      this.$_logDebug(data)
      this.$emit('compliance-info-registered', this.formModel.isUpdateMode)
    },
    responseA007bErrorHandler(error) {
      this.$_logError(error)
    }
  }
}
</script>

<style lang="scss" scoped>
.__bold {
  font-weight: bold;
}
.clickable:hover {
  cursor: pointer
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-start;
  padding: 0.5rem 0 0.2rem 0;
}
.form-cancel-button__wrapper {
  display: flex;
  justify-content: flex-end;
  padding: 0.5rem 0 0.2rem 0;
}
.form-area__input-number {
  width: 18rem;
  margin-left: 0.1rem;
}
#t1 tr { line-height: 40px; }
:deep(.el-dialog__title) {
   font-weight: bold;
}
</style>
