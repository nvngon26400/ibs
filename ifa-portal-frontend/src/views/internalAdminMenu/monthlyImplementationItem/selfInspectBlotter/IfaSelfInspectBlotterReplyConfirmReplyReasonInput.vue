<template>
  <div>
    <el-dialog
      :model-value="isVisible"
      align-center
      center
      width="1280px"
      style="min-width: 1280px;"
      title="自己点検記録簿回答確認・回答理由入力"
      :before-close="handleClose"
      :show-close="false"
      :close-on-click-modal="false"
      @open="handleDialogOpen"
    >
      <el-row style="padding-bottom: 20px;">
        <el-col :span="21">
          <span>回答を確認の上、要確認にチェックがついている項目の回答理由を入力してください。</span>
        </el-col>
      </el-row>
      <br>
      <el-row>
        <grid-table
          ref="ifaSelfInspectBlotterReplyConfirmReplyReasonInputGridTable"
          :options="options"
          :auto-refresh="false"
        ></grid-table>
      </el-row>
      <el-row style="padding: 20px 0 0 0;">
        <ifa-button
          text="登録"
          color="primary"
          action-type="originalAction"
          @app-action-handler="handleComplete"
        ></ifa-button>
      </el-row>
    </el-dialog>

    <!-- A002: 完了 -->
    <ifa-requester
      id="ifaSelfInspectBlotterReplyConfirmReplyReasonInputA002"
      action-id="SUB0401_02-03#A002"
      action-type="requestAction"
      :request-model="ifaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestModel"
      @response-handler="responseHandlerA002"
      @response-error-handler="responseErrorHandlerA002"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getDefaultOption, convertData } from '@/utils/pqgridHelper'
import { IfaSelfInspectBlotterReplyConfirmReplyReasonInputFormModel } from './js/IfaSelfInspectBlotterReplyConfirmReplyReasonInputFormModel'
import { IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestModel } from './js/IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestModel'

export default {
  components: {
    GridTable
  },
  props: {
    isVisible: { type: Boolean, required: true },
    model: { type: Object, required: true }
  },
  emits: ['registered'],
  data() {
    return {
      form: new IfaSelfInspectBlotterReplyConfirmReplyReasonInputFormModel(),
      options: null
    }
  },
  computed: {
    ifaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestModel() {
      return new IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestModel(this.form)
    }
  },
  created() {
    this.options = getDefaultOption(columns, true)
    // 1ページ当たりの表示件数30。
    this.options.pageModel.rPPOptions = [30]
    this.options.wrap = true
  },
  updated() {
    this.$nextTick(() => {
      // このコンポーネントのインスタンスにアクセス可能
      const gridInstance = this
      // テキストエリアのinputイベントをキャッチしてテーブルを更新
      if (this.$refs.ifaSelfInspectBlotterReplyConfirmReplyReasonInputGridTable) {
        this.$refs.ifaSelfInspectBlotterReplyConfirmReplyReasonInputGridTable.$el.addEventListener('input', function(event) {
          if (event.target && event.target.tagName === 'TEXTAREA') {
            const newValue = event.target.value
            const rowIndx = event.target.getAttribute('data-rowindx')
            // グリッドのデータの更新
            gridInstance.options.dataModel.data[rowIndx].answerReason = newValue
          }
        })
      }
    })
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.model)
      this.options.dataModel.data = convertData(this.form.selfAssessmentList, this.options.colModel).map(item => ({
        ...item,
        confirmation: item.reasonRequiredFlag === '0' ? item.confirmation : '-'
      }))
    },
    handleDialogOpen() {
      this.$refs['ifaSelfInspectBlotterReplyConfirmReplyReasonInputGridTable'].refreshView()
    },
    // A002: 完了アクション
    handleComplete() {
      // グリッドテーブルの変更はリアクティブでないため computed が働かない､
      // そのため登録ボタン押下時にグリッドテーブルから自己点検リストを再構築する
      this.form.requestModelA002 = this.options.dataModel.data.map(item => {
        return {
          selfCheckItemId: item.selfCheckItemId,
          answerReason: item.answerReason,
          answerResult: item.answerResult
        }
      })
      this.$nextTick(() => {
        document.getElementById('ifaSelfInspectBlotterReplyConfirmReplyReasonInputA002').click()
      })
    },
    // A002: 完了レスポンス
    responseHandlerA002(response) {
      this.handleClose()
    },
    // A002: 完了エラーレスポンス
    responseErrorHandlerA002(response) {
      this.$_logDebug(response)
    },
    handleClose() {
      this.$emit('registered')
    }
  }
}
const columns = [
  {
    title: 'チェック項目', dataType: 'string', dataIndx: 'selfAssessmentItemName', width: 710, halign: 'center', editable: false,
    render: function(ui) {
      return { style: 'white-space: wrap;' }
    }
  },
  {
    title: '確認', dataType: 'string', dataIndx: 'confirmation', width: 150, halign: 'center', editable: false,
    render: function(ui) {
    // 理由必須フラグ ＝ "0" の場合のみセルデータを表示
      if (ui.rowData.reasonRequiredFlag === '0') {
        return { text: ui.rowData.cellData }
      }

      // 上記以外の場合、"-" を表示しセルをグレーアウト
      return {
        text: '-',
        style: 'background-color: #C0C0C0;'
      }
    },
    codeList: { codeListId: 'SELF_INSPECT_CONFIRM', dispPattern: 1, selectPattern: 1 }
  },
  { title: '要確認', dataType: 'string', dataIndx: 'answerResult', width: 80, halign: 'center', align: 'center', editable: false,
    render: function(ui) {
      if (ui.rowData.answerResult === '0') {
        return {
          text: '✓',
          style: 'color: red' }
      } else {
        return ''
      }
    }
  },
  {
    title: '回答理由', dataType: 'string', dataIndx: 'answerReason', width: 240, halign: 'center',
    render: function(ui) {
    // 回答結果 = "0" の場合、テキストエリアを表示し入力可
      if (ui.rowData.answerResult === '0') {
        // answerReasonがnullの場合は空文字を設定
        const answerReason = ui.rowData.answerReason !== null ? ui.rowData.answerReason : ''
        return `<textarea placeholder="入力してください"
                 style="width: 100%;"
                 data-rowindx="${ui.rowIndx}">${answerReason}</textarea>`
      }

      // 理由必須フラグ ＝ "1" の場合、回答理由を表示（テキストボックスは表示しない）
      if (ui.rowData.reasonRequiredFlag === '1') {
        return {
          text: ui.cellData
        }
      }

      // 上記以外の場合、"-" を表示しセルをグレーアウト
      return {
        text: '-',
        style: 'background-color: #C0C0C0;',
        disabled: true
      }
    },
    editable: false
  },
  { title: '自己点検項目ID', dataType: 'string', dataIndx: 'selfCheckItemId', hidden: true },
  { title: '登録年月', dataType: 'string', dataIndx: 'registerDate', hidden: true },
  { title: '理由必須', dataType: 'string', dataIndx: 'reasonRequiredFlag', hidden: true },
  { title: '回答結果', dataType: 'string', dataIndx: 'answerResult', hidden: true },
  { title: '自己点検ID', dataType: 'string', dataIndx: 'nextSelfCheckId', hidden: true }
]
</script>
