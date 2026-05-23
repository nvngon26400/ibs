<template>
  <div>
    <screen-title text="自己点検記録簿"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
    >
      <el-card class="content-card">
        <div class="filter-container">
          <div style="display: inline-flex; flex-flow: row;">
            <div>
              <ifa-input-select
                v-model="form.assignMonth"
                label="表示年月"
                size="small"
                class="form_label"
                code-list-id="original"
                :original-list="registerDateList"
                required
                :clearable="false"
                @change="handleChangeSelected"
              ></ifa-input-select>
            </div>
            <div>
              <div style="line-height: 35px;">最終更新日時：{{ form.confirmationDate }}</div>
            </div>
          </div>
        </div>
      </el-card>
      <el-card
        class="content-card"
        style="width: auto !important;"
      >
        <el-row style="margin-bottom: 0.5rem;">
          <!-- A004: 登録確認 -->
          <!-- グリッドテーブルの更新内容を自己点検リストに反映させるため一度 originalAction でハンドラを実行 -->
          <ifa-button
            :disabled="isDisabledButton"
            text="登録確認"
            width="110"
            small
            action-type="originalAction"
            @app-action-handler="handleRegisterConfirmButton"
          ></ifa-button>
        </el-row>
        <grid-table
          ref="gridTable"
          :options="options"
          :auto-refresh="false"
        ></grid-table>
      </el-card>
    </el-form>
    <!--登録確認ダイアログ-->
    <ifa-self-inspect-blotter-register-confirm
      ref="ifaSelfInspectBlotterRegisterConfirm"
      :is-visible="isVisible"
      :model="form"
      @register="handleRegister"
      @back="handleBack"
    ></ifa-self-inspect-blotter-register-confirm>
    <!--自己点検記録簿回答確認・回答理由入力ダイアログ-->
    <ifa-self-inspect-blotter-reply-confirm-reply-reason-input
      ref="ifaSelfInspectBlotterReplyConfirmReplyReasonInput"
      :is-visible="isFinishVisible"
      :model="form"
      @registered="handleRegistered"
    ></ifa-self-inspect-blotter-reply-confirm-reply-reason-input>

    <!-- A001: 初期表示 -->
    <ifa-requester
      id="ifaSelfInspectBlotterA001"
      action-id="SUB0401_02-01#A001"
      action-type="requestAction"
      :request-model="ifaSelfInspectBlotterA001RequestModel"
      @response-handler="responseHandlerA001"
      @response-error-handler="responseErrorHandlerA001"
    ></ifa-requester>
    <!-- A002: 年月表示変更 -->
    <ifa-requester
      id="ifaSelfInspectBlotterA002"
      action-id="SUB0401_02-01#A002"
      action-type="requestAction"
      :request-model="ifaSelfInspectBlotterA002RequestModel"
      @response-handler="responseHandlerA002"
      @response-error-handler="responseErrorHandlerA002"
    ></ifa-requester>
    <!-- A003: TOP遷移 -->
    <ifa-requester
      id="ifaSelfInspectBlotterA003"
      action-id="SUB0401_02-01#A003"
      action-type="requestAction"
      :request-model="ifaSelfInspectBlotterA003RequestModel"
      @response-handler="responseHandlerA003"
      @response-error-handler="responseErrorHandlerA003"
    ></ifa-requester>
    <!-- A004: 登録確認 -->
    <ifa-requester
      id="ifaSelfInspectBlotterA004"
      action-id="SUB0401_02-01#A004"
      action-type="requestAction"
      :request-model="ifaSelfInspectBlotterA004RequestModel"
      @response-handler="responseHandlerA004"
      @response-error-handler="responseErrorHandlerA004"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getDefaultOption, convertData } from '@/utils/pqgridHelper'
import IfaSelfInspectBlotterRegisterConfirm from './IfaSelfInspectBlotterRegisterConfirm.vue'
import IfaSelfInspectBlotterReplyConfirmReplyReasonInput from './IfaSelfInspectBlotterReplyConfirmReplyReasonInput.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaSelfInspectBlotterFormModel } from './js/IfaSelfInspectBlotterFormModel'
import { IfaSelfInspectBlotterA001RequestModel } from './js/IfaSelfInspectBlotterA001RequestModel'
import { IfaSelfInspectBlotterA002RequestModel } from './js/IfaSelfInspectBlotterA002RequestModel'
import { IfaSelfInspectBlotterA003RequestModel } from './js/IfaSelfInspectBlotterA003RequestModel'
import { IfaSelfInspectBlotterA004RequestModel } from './js/IfaSelfInspectBlotterA004RequestModel'
import { getCodeList } from '@/components/Input/js/IfaCodeListFunction'
// import { notifyWrapper } from '@/utils/errorHandler'
export default {
  components: {
    GridTable,
    IfaSelfInspectBlotterRegisterConfirm,
    IfaSelfInspectBlotterReplyConfirmReplyReasonInput,
    screenTitle
  },
  data() {
    return {
      isVisible: false,
      isFinishVisible: false,
      form: new IfaSelfInspectBlotterFormModel(),
      options: null
    }
  },
  computed: {
    ifaSelfInspectBlotterA001RequestModel() {
      return new IfaSelfInspectBlotterA001RequestModel(this.form)
    },
    ifaSelfInspectBlotterA002RequestModel() {
      return new IfaSelfInspectBlotterA002RequestModel(this.form)
    },
    ifaSelfInspectBlotterA003RequestModel() {
      return new IfaSelfInspectBlotterA003RequestModel(this.form)
    },
    ifaSelfInspectBlotterA004RequestModel() {
      return new IfaSelfInspectBlotterA004RequestModel(this.form)
    },
    // 取得した自己点検記録簿が以下パターン①～③の何れかに合致する場合、登録確認ボタンを非活性にする。
    //    |   理由必須   |    確認     | 回答結果 | 回答理由 | 回答回数
    // ①: | 1 (理由必須) | NULL       | NULL    | ブランク以外 | ≧2
    // ②: | 0 (理由不用) | ブランク以外 | 1 (正答) | NULL
    // ③: | 0 (理由不用) | ブランク以外 | 0 (誤答) | ブランク以外
    isDisabledButton() {
      return this.form.selfAssessmentList.every(sa =>
        (sa.reasonRequiredFlag === '1' && sa.confirmation === null && sa.answerResult === null && sa.answerReason && Number(sa.answerCount) >= 2) ||
        (sa.reasonRequiredFlag === '0' && sa.confirmation && sa.answerResult === '1' && sa.answerReason === null) ||
        (sa.reasonRequiredFlag === '0' && sa.confirmation && sa.answerResult === '0' && sa.answerReason)
      )
    },
    // 登録年月リストから IfaInputSelect のオプションを生成する
    // 表示形式: YYYY年MM月 (date6YearMonthKanji)
    registerDateList() {
      return this.form.registerDateList.map(date => {
        return {
          key: date.codeId,
          value: date.codeName
        }
      })
    }
  },
  created() {
    this.options = getDefaultOption(columns, true)
    // 1ページ当たりの表示件数30。
    this.options.pageModel.rPPOptions = [30]
    // 単一行選択 (デフォルトのため設定不用)
    // this.options.selectionModel = { type: 'row', mode: 'single' }
    this.options.scrollModel = {
      autoFit: true,
      horizontal: true
    }
    this.options.wrap = true
  },
  mounted() {
    // このコンポーネントのインスタンスにアクセス可能
    const gridInstance = this
    // グリッドテーブル内のselectのchangeイベントをキャッチしてテーブルを更新
    this.$refs.gridTable.$el.addEventListener('change', function(event) {
      if (event.target && event.target.tagName === 'SELECT') {
        const newValue = event.target.value
        const rowIndx = event.target.getAttribute('data-rowindx')

        // Vueのメソッドを呼び出す
        gridInstance.handleDropdownChange(newValue, rowIndx)
      }
    })
    // テキストエリアのinputイベントをキャッチしてテーブルを更新
    this.$refs.gridTable.$el.addEventListener('input', function(event) {
      if (event.target && event.target.tagName === 'TEXTAREA') {
        const newValue = event.target.value
        const rowIndx = event.target.getAttribute('data-rowindx')

        // Vueのメソッドを呼び出す
        gridInstance.updateAnswerReason(newValue, rowIndx)
      }
    })
  },
  methods: {
    onShow(resume, isRouting) {
      // 総合ポータル_ホームからの遷移
      if (isRouting) {
        const param = this.$store.getters.pageInfo.params
        this.form.assignMonth = param.assignMonth
        document.getElementById('ifaSelfInspectBlotterA002').click()
      } else {
      // タブ遷移
      // 当月(YYYYMM)を取得する
        this.form.assignMonth = this.$_getFormattedMonthValue(this.$store.getters.requestedTime, 'date6YearMonthNoSlash')
        this.handleSearchA001()
      }
    },
    // A001: 初期化
    handleSearchA001() {
      this.options.dataModel.data = []
      this.$refs['gridTable'].refreshView()
      this.$nextTick(() => {
        document.getElementById('ifaSelfInspectBlotterA001').click()
      })
    },
    // A002: 表示年月変更
    handleChangeSelected() {
      this.options.dataModel.data = []
      this.$refs['gridTable'].refreshView()
      this.$nextTick(() => {
        document.getElementById('ifaSelfInspectBlotterA002').click()
      })
    },
    // A004: 登録確認
    // グリッドテーブルの変更はリアクティブでないため computed が働かない､
    // そのため登録確認ボタン押下時にグリッドテーブルから自己点検リストを再構築する
    handleRegisterConfirmButton() {
      this.form.selfAssessmentList = this.options.dataModel.data.map(item => {
        return {
          selfAssessmentItemName: item.selfAssessmentItemName,
          confirmation: this.valueToKey(item.confirmation, item.reasonRequiredFlag),
          answerReason: item.answerReason,
          selfCheckItemId: item.selfCheckItemId,
          answer: item.answer,
          reasonRequiredFlag: item.reasonRequiredFlag,
          answerCount: item.answerCount,
          answerResult: item.answerResult
        }
      })
      this.$nextTick(() => {
        document.getElementById('ifaSelfInspectBlotterA004').click()
      })
    },
    // 自己点検記録簿登録確認のA002アクション
    handleBack() {
      this.isVisible = false
    },
    // 自己点検記録簿登録確認のA003アクション
    handleRegister(response) {
      Object.assign(this.form, response.dataList[0])
      this.isVisible = false
      this.$nextTick(() => {
        this.$refs['ifaSelfInspectBlotterReplyConfirmReplyReasonInput'].onShow()
        this.isFinishVisible = true
      })
    },
    // 自己点検記録簿回答確認・回答理由入力のA002アクション
    handleRegistered() {
      this.isFinishVisible = false
      // notifyWrapper({
      //   title: '自己点検記録簿回答確認・回答理由入力',
      //   message: '自己点検項目を登録しました。',
      //   type: 'success'
      // })
      this.$nextTick(() => {
        document.getElementById('ifaSelfInspectBlotterA002').click()
      })
    },
    // A001: 初期化レスポンス
    responseHandlerA001(response) {
      Object.assign(this.form, response.dataList[0])
      this.options.dataModel.data = convertData(this.form.selfAssessmentList, this.options.colModel)
      this.$refs['gridTable'].refreshView()
    },
    // A001: 初期化エラーレスポンス
    responseErrorHandlerA001(response) {
      this.$_logDebug(response)
    },
    // A002: 年月表示変更レスポンス
    responseHandlerA002(response) {
      Object.assign(this.form, response.dataList[0])
      this.options.dataModel.data = convertData(this.form.selfAssessmentList, this.options.colModel)
      this.$refs['gridTable'].refreshView()
    },
    // A002: 年月表示変更エラーレスポンス
    responseErrorHandlerA002(response) {
      this.$_logDebug(response)
    },
    // A003: TOP遷移レスポンス
    responseHandlerA003(response) {
      Object.assign(this.form, response.dataList[0])
      this.options.dataModel.data = convertData(this.form.selfAssessmentList, this.options.colModel)
      this.$refs['gridTable'].refreshView()
    },
    // A003: TOP遷移エラーレスポンス
    responseErrorHandlerA003(response) {
      this.$_logDebug(response)
    },
    // A004: 登録確認レスポンス
    responseHandlerA004(response) {
      this.isVisible = true
      this.$refs['ifaSelfInspectBlotterRegisterConfirm'].onShow()
    },
    // A004: 登録確認エラーレスポンス
    responseErrorHandlerA004(response) {
      this.$_logDebug(response)
    },
    valueToKey(value, reasonRequiredFlag) {
      let key = this.options.colModel[1].cl.find(c => c.value === value).key
      if (key === '$NULL') {
        key = reasonRequiredFlag === '1' ? '-' : ''
      }
      return key
    },
    updateAnswerReason(newValue, rowIndx) {
      // データモデルに対して変更を保存
      this.options.dataModel.data[rowIndx].answerReason = newValue
    },
    handleDropdownChange(newValue, rowIndx) {
      this.options.dataModel.data[rowIndx]['confirmation'] = newValue
    }
  }
}
const columns = [
  {
    title: 'チェック項目', dataType: 'string', dataIndx: 'selfAssessmentItemName', width: 1100, halign: 'center', editable: false,
    render: function(ui) {
      return { style: 'white-space: wrap;' }
    }
  },
  {
    title: '確認', dataType: 'string', dataIndx: 'confirmation', width: 226, minWidth: 160, halign: 'center',
    render: function(ui) {
      // 理由必須フラグ ＝ "1"（理由必須）の場合、"-"を表示しセルはグレーアウト
      if (ui.rowData.reasonRequiredFlag === '1') {
        return {
          value: '-',
          style: 'background-color: #C0C0C0;'
        }
      }

      // 理由必須フラグ ＝ "0": 理由不要の場合、ドロップダウンリストを表示。
      // 区分値一覧を表示
      const codeList = getCodeList('SELF_INSPECT_CONFIRM', 1, 1)
      const options = codeList.filter(item => item.value !== '-').map(item => item.value)
      // 回答回数 ≧ 1 で入力不可
      const isDisabled = Number(ui.rowData.answerCount) >= 1 ? 'disabled ' : ''
      const cellData = ui.cellData === '-' ? '' : ui.cellData
      // ドロップダウンリストを生成
      return `<select data-rowindx="${ui.rowIndx}" style="width: 100%; "${isDisabled}">
            <option value="" selected disabled>選択してください</option>
            ${options.map(option => `<option value="${option}" ${option === cellData ? 'selected' : ''}>${option}</option>`).join('')}
          </select>`
    },
    editable: false,
    codeList: { codeListId: 'SELF_INSPECT_CONFIRM', dispPattern: 1, selectPattern: 1 }
  },
  {
    title: '回答理由', dataType: 'string', dataIndx: 'answerReason', width: 240, minWidth: 160, halign: 'center',
    editor: { type: 'textarea' },
    render: function(ui) {
      // 理由必須フラグ ＝ "1" の場合、テキストエリアを表示
      if (ui.rowData.reasonRequiredFlag === '1') {
        // 回答回数 ≧ 2 で入力不可
        const isDisabled = Number(ui.rowData.answerCount) >= 2 ? 'disabled' : ''
        // answerReasonがnullの場合は空文字を設定
        const answerReason = ui.rowData.answerReason !== null ? ui.rowData.answerReason : ''
        const style = isDisabled ? 'width: 100%; color: light-dark(graytext, rgb(170, 170, 170)); opacity: 0.7;' : 'width: 100%;'

        return `<textarea placeholder="入力してください"
                 style="${style}" ${isDisabled}
                 data-rowindx="${ui.rowIndx}"
                 @input="updateAnswerReason($event, ${ui.rowIndx})">${answerReason}</textarea>`
      }
      // 理由必須フラグ ＝ "0" の場合
      if (ui.rowData.reasonRequiredFlag === '0') {
        // 回答理由が空白やNULL以外の場合、回答理由を表示
        if (ui.rowData.answerReason && ui.rowData.answerReason.trim() !== '') {
          return {
            text: ui.rowData.answerReason,
            disabled: false
          }
        } else {
          // 上記以外の場合、"-"を表示しセルはグレーアウト
          return {
            text: '-',
            style: 'background-color: #C0C0C0;', // グレーアウト
            disabled: true
          }
        }
      }
    },
    editable: false
  },
  { title: '自己点検項目ID', dataType: 'string', dataIndx: 'selfCheckItemId', hidden: true },
  { title: '回答', dataType: 'string', dataIndx: 'answer', hidden: true },
  { title: '理由必須', dataType: 'string', dataIndx: 'reasonRequiredFlag', hidden: true },
  { title: '回答回数', dataType: 'string', dataIndx: 'answerCount', hidden: true },
  { title: '回答結果', dataType: 'string', dataIndx: 'answerResult', hidden: true }
]
</script>

<style scoped>
.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 200px;
}
.el-form-item__label :deep(.form_label) {
  width: 135px;
}
.form_button {
  padding: 0.4rem 2rem 1.2rem 2rem;
}
.content-card {
  margin: 0.5rem 1rem;
}
.filter-container {
  margin: 1rem 0 1rem;
}
.gridButtonArea {
  padding: 0.5rem 0.1rem;
}

:deep(.el-dialog__title) {
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
  padding: 0px;
}
:deep(.el-dialog__header) {
  padding-bottom: 0px;
  padding-top: 30px;
}
:deep(.el-dialog__body) {
  padding-left: 40px;
  padding-right: 40px;
}
.ifa-pq-grid-cell--bg-color {
  background-color: #FFFFD3;
}
</style>
