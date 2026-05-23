<template>
  <div>
    <el-dialog
      :model-value="isVisible"
      align-center
      center
      width="1000px"
      style="min-width: 1000px;"
      title="登録確認"
      :show-close="false"
      :before-close="handleCloseA002"
      :close-on-click-modal="false"
    >
      <el-row style="padding-bottom: 20px;">
        <el-col :span="21">
          <span>下記の内容にて登録します。よろしいですか？</span>
        </el-col>
        <el-col
          :span="3"
          style="text-align: right;"
        >
          <!-- A002: 戻るボタン -->
          <ifa-button
            text=" 戻る"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="handleCloseA002"
          ></ifa-button>
        </el-col>
      </el-row>
      <el-row>
        <grid-table ref="gridTable"
                    :options="options"
                    :auto-refresh="false"
        ></grid-table>
      </el-row>
      <el-row style="padding: 20px 0 0 0;">
        <!-- A003: 登録ボタン -->
        <ifa-button
          text=" 登録"
          color="primary"
          action-type="originalAction"
          @app-action-handler="handleRegisterA003"
        ></ifa-button>
      </el-row>
    </el-dialog>

    <!-- A003: 登録 -->
    <ifa-requester
      id="ifaSelfInspectBlotterRegisterConfirmA003"
      action-id="SUB0401_02-02#A003"
      action-type="requestAction"
      :request-model="ifaSelfInspectBlotterRegisterConfirmA003RequestModel"
      @response-handler="responseHandlerA003"
      @response-error-handler="responseErrorHandlerA003"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getDefaultOption, convertData } from '@/utils/pqgridHelper'
import { IfaSelfInspectBlotterRegisterConfirmFormModel } from './js/IfaSelfInspectBlotterRegisterConfirmFormModel'
import { IfaSelfInspectBlotterRegisterConfirmA003RequestModel } from './js/IfaSelfInspectBlotterRegisterConfirmA003RequestModel'

export default {
  components: {
    GridTable
  },
  props: {
    isVisible: { type: Boolean, required: true },
    model: { type: Object, required: true }
  },
  emits: ['register', 'back'],
  data() {
    return {
      form: new IfaSelfInspectBlotterRegisterConfirmFormModel(),
      options: null
    }
  },
  computed: {
    ifaSelfInspectBlotterRegisterConfirmA003RequestModel() {
      return new IfaSelfInspectBlotterRegisterConfirmA003RequestModel(this.form)
    }
  },
  created() {
    this.options = getDefaultOption(columns)
    // 1ページ当たりの表示件数30。
    this.options.pageModel.rPPOptions = [30]
    this.options.wrap = true
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.model)
      this.options.dataModel.data = convertData(this.form.selfAssessmentList, this.options.colModel)
      // GridTableをリフレッシュ
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView()
      })
    },
    // A002: 戻るボタンアクション
    handleCloseA002() {
      this.$emit('back')
    },
    // A003: 登録ボタンアクション
    handleRegisterA003() {
      this.$nextTick(() => {
        document.getElementById('ifaSelfInspectBlotterRegisterConfirmA003').click()
      })
    },
    // A003: 登録レスポンス
    responseHandlerA003(response) {
      this.$emit('register', response)
    },
    // A003: 登録エラーレスポンス
    responseErrorHandlerA003(response) {
      this.$_logDebug(response)
    }
  }
}
const columns = [
  {
    title: 'チェック項目', dataType: 'string', dataIndx: 'selfAssessmentItemName', width: 710, halign: 'center',
    render: function(ui) {
      return { style: 'white-space: wrap;' }
    }
  },
  {
    title: '確認', dataType: 'string', dataIndx: 'confirmation', width: 80, halign: 'center',
    render: function(ui) {
      // 理由必須フラグが '0' でない場合、"-" を表示してグレーアウト
      if (ui.rowData.reasonRequiredFlag !== '0') {
        return {
          text: '-',
          style: ui.rowData.pq_rowselect ? '' : 'background-color: #C0C0C0;'
        }
      }

      // 理由必須フラグが '0' の場合は通常のセルデータを表示
      return ui.cellData
    },
    codeList: { codeListId: 'SELF_INSPECT_CONFIRM', dispPattern: 1, selectPattern: 1 }
  },
  {
    title: '回答理由', dataType: 'string', dataIndx: 'answerReason', width: 110, halign: 'center',
    render: function(ui) {
      // 回答理由がNULLや空白の場合、"-"を表示しグレーアウト
      if (!ui.rowData.answerReason || ui.rowData.answerReason.trim() === '') {
        return {
          text: '-',
          style: ui.rowData.pq_rowselect ? '' : 'background-color: #C0C0C0;',
          disabled: true
        }
      }

      // 通常の表示処理
      return ui.cellData
    }
  },
  { title: '自己点検項目ID', dataType: 'string', dataIndx: 'selfCheckItemId', hidden: true },
  { title: '回答', dataType: 'string', dataIndx: 'answer', hidden: true },
  { title: '理由必須', dataType: 'string', dataIndx: 'reasonRequiredFlag', hidden: true },
  { title: '回答回数', dataType: 'string', dataIndx: 'answerCount', hidden: true },
  { title: '回答結果', dataType: 'string', dataIndx: 'answerResult', hidden: true }
]

</script>
