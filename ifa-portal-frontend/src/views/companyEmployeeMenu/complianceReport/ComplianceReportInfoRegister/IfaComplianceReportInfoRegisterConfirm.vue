<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      left
      :close-on-click-modal="false"
      :show-close="false"
      width="1000px"
      @open="setOkCancelDialogParams"
      @close="onDialogClose"
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
            style="margin-left: -2px"
            action-type="originalAction"
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
      id="ifaComplianceReportInfoRegisterA006a"
      action-id="SUB0505_01-03_1#A006a"
      action-type="uploadAction"
      :request-model="files"
      @response-handler="responseA006aHandler"
      @response-error-handler="responseA006aErrorHandler"
    ></ifa-requester>
    <ifa-requester
      id="ifaComplianceReportInfoRegisterA006b"
      action-id="SUB0505_01-03_1#A006b"
      action-type="requestAction"
      :request-model="ifaComplianceReportInfoRegisterA006bRequestModel"
      @response-handler="responseA006bHandler"
      @response-error-handler="responseA006bErrorHandler"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog'
import { useVModel } from 'vue-composable'
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption'
import { IfaComplianceReportInfoRegisterA006bRequestModel } from './js/IfaComplianceReportInfoRegisterA006bRequestModel'
import { IfaComplianceReportInfoRegisterA006aRequestModel } from './js/IfaComplianceReportInfoRegisterA006aRequestModel'
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
      // 登録
      return Object.entries(this.ifaComplianceReportInfoRegisterA006aRequestModel).map(([key, value]) => ({ filename: key, file: value.file }))
    },
    // 登録
    ifaComplianceReportInfoRegisterA006bRequestModel() {
      return new IfaComplianceReportInfoRegisterA006bRequestModel(this.formModel)
    },
    ifaComplianceReportInfoRegisterA006aRequestModel() {
      return new IfaComplianceReportInfoRegisterA006aRequestModel(this.formModel)
    }
  },
  methods: {
    setOkCancelDialogParams() {
      this.okCancelDialog.title = '登録確認'
      this.okCancelDialog.message = 'コンプライアンス通信情報を登録します。よろしいですか？'
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
      document.querySelector('#ifaComplianceReportInfoRegisterA006a').click()
    },
    handleCloseModalCancel() {
      this.okCancelDialog.visible = false
    },
    // 登録アプロード正常終了
    responseA006aHandler(data) {
      this.$_logDebug(data)
      if (data.returnCode === 'errors.processingFailed') {
        notifyWrapper({
          title: this.$store.getters.pageInfo?.label ?? '',
          message: 'コンプライアンス通信情報の登録が失敗しました。',
          type: 'error'
        })
      } else {
        Object.assign(this.ifaComplianceReportInfoRegisterA006bRequestModel, data.dataList[0])
        document.querySelector('#ifaComplianceReportInfoRegisterA006b').click()
      }
    },
    responseA006aErrorHandler(error) {
      this.$_logError(error)
    },
    // 登録完了
    responseA006bHandler(data) {
      this.$_logDebug(data)
      this.$emit('compliance-info-registered', this.formModel.isUpdateMode)
    },
    responseA006bErrorHandler(error) {
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
