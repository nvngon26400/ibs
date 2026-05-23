<template>
  <div>
    <!-- A001: 初期化 -->
    <ifa-requester
      id="ifaSelfInspectItemManageInitializeA001"
      action-id="SUB0506_02-01#A001"
      action-type="requestAction"
      @response-handler="responseHandlerInitializeA001"
    ></ifa-requester>
    <!-- A002: 表示年月変更 -->
    <ifa-requester
      id="ifaSelfInspectItemManageDisplayYearMonthChangeA002"
      action-id="SUB0506_02-01#A002"
      action-type="requestAction"
      :request-model="ifaSelfInspectItemManageA002RequestModel"
      @response-handler="responseHandlerDisplayYearMonthChangeA002"
    ></ifa-requester>
    <!-- A003: 検索表示 -->
    <ifa-requester
      id="ifaSelfInspectItemManageSearchDisplayA003"
      action-id="SUB0506_02-01#A003"
      action-type="requestAction"
      :request-model="ifaSelfInspectItemManageA003RequestModel"
      @response-handler="responseHandlerSearchDisplayA003"
    ></ifa-requester>
    <!-- A009: 更新 -->
    <ifa-requester
      id="ifaSelfInspectItemManageUpdateA009"
      action-id="SUB0506_02-01#A009"
      action-type="requestAction"
      :request-model="ifaSelfInspectItemManageA009RequestModel"
      @response-handler="responseHandlerUpdateA009"
    ></ifa-requester>
    <!-- A010: 削除 -->
    <ifa-requester
      id="ifaSelfInspectItemManageDeleteA010"
      action-id="SUB0506_02-01#A010"
      action-type="requestAction"
      :request-model="ifaSelfInspectItemManageA010RequestModel"
    ></ifa-requester>
    <!-- A012: ファイル取り込み -->
    <ifa-requester
      id="ifaSelfInspectItemManageFileImportA012"
      action-id="SUB0506_02-01#A012"
      action-type="requestAction"
      :request-model="ifaSelfInspectItemManageA012RequestModel"
      @response-handler="responseHandlerFileImportA012"
    ></ifa-requester>
    <screen-title :text="form.title.name"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      :rules="rules"
    >
      <!-- 表示部 -->
      <el-card
        class="content-card"
        shadow="always"
      >
        <div class="filter-container">
          <el-row>
            <ifa-input-select
              id="assignMonthList"
              v-model="form.assignMonthList"
              label="登録年月"
              prop="assignMonthList"
              :required="true"
              code-list-id="original"
              :original-list="form.registerDateListSelectBox"
              size="small"
              style="width:175px;"
              @change="displayYearMonthChangeA002()"
            ></ifa-input-select>
          </el-row>
          <el-row>
            <table
              class="_table__horizontal _table__body"
              style="width: 300px; margin-left: 8rem; margin-bottom: 1rem;"
            >
              <thead>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width:150px;"
                  >業者区分</th>
                  <th
                    class="_table__header __center"
                    style="width:150px;"
                  >登録状況</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(data, index) in form.brokerTypeList"
                  :key="index"
                >
                  <td class="_table__data __left">{{ data.classificationName }}</td>
                  <td :class="[ratioColor(data.registerStatus)]">{{ data.registerStatus }}</td>
                </tr>
              </tbody>
            </table>
          </el-row>
          <el-row>
            <ifa-input-select
              id="brokerType"
              v-model="form.brokerType"
              label="業者区分"
              code-list-id="original"
              :original-list="form.brokerTypeListSelectBox"
              size="small"
              style="width:175px;"
              @change="searchDisplayA003()"
            ></ifa-input-select>
          </el-row>
        </div>
      </el-card>
    </el-form>
    <!-- 操作部 -->
    <el-row>
      <el-card
        class="content-card"
        shadow="always"
      >
        <el-row>
          <div class="gridButtonArea">
            <ifa-button
              text="項目追加"
              small
              action-type="originalAction"
              :disabled="!isNextMonth(form.assignMonthList)"
              @app-action-handler="addRowA004"
            ></ifa-button>
            <ifa-button
              text="更新"
              small
              action-type="originalAction"
              :disabled="!isNextMonth(form.assignMonthList)"
              @app-action-handler="updateConfirmA005"
            ></ifa-button>
            <ifa-button
              text="アップロード"
              width="110"
              color="primary"
              small
              action-type="originalAction"
              :disabled="!isNextMonth(form.assignMonthList)"
              @app-action-handler="uploadA006"
            ></ifa-button>
          </div>
        </el-row>
        <grid-table
          ref="gridTable"
          :auto-refresh="false"
          :options="pqGridOption"
          @click="deleteConfirmA007"
        ></grid-table>
      </el-card>
    </el-row>
    <!-- 削除確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="deleteDialog.visible"
      :title="deleteDialog.title"
      :message="deleteDialog.message"
      @close-modal-ok="deleteA010"
      @close-modal-cancel="deleteCloseDialogA010"
    ></ifa-ok-cancel-dialog>
    <!-- 更新確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="updateDialog.visible"
      :title="updateDialog.title"
      :message="updateDialog.message"
      @close-modal-ok="updateA009"
      @close-modal-cancel="updateCloseDialogA009"
    ></ifa-ok-cancel-dialog>
    <!-- アップロードダイアログ -->
    <el-dialog
      v-model="uploadDialog.visible"
      :close-on-click-modal="false"
      :show-close="false"
      width="600px"
    >
      <!-- 戻るボタン -->
      <el-row>
        <el-col
          :span="24"
          class="close-button"
        >
          <ifa-button
            color="secondary"
            text="戻る"
            action-type="originalAction"
            @app-action-handler="backA014"
          ></ifa-button>
        </el-col>
      </el-row>
      <caption-card
        :caption="uploadDialog.title"
        text-size="20px"
        text-color="#0058a2"
        background-color="Menu"
      >
        <el-form
          ref="uploadFileForm"
          :model="uploadFileForm"
          :inline="true"
          style="margin-top: 0.8rem"
        >
          <el-form-item>
            <ifa-file-select
              ref="select1"
              class="file_select"
              text="ファイルを選択"
              color="secondary"
              small
              width="130"
              msg-title="ファイルを選択"
              :show-file-list="true"
              @change="selectFileA011($event)"
              @before-remove="filePathDeleteA013"
            ></ifa-file-select>
          </el-form-item>
        </el-form>
        <ifa-button
          id="btnDisplay"
          name="btnDisplay"
          text="取込"
          width="100"
          color="primary"
          small
          style="margin-top: 0.3rem"
          :disabled="uploadDialog.disabledImportButton"
          action-type="originalAction"
          @app-action-handler="fileImportA012"
        ></ifa-button>
      </caption-card>
    </el-dialog>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaSelfInspectItemManageFormModel } from './js/IfaSelfInspectItemManageFormModel'
import IfaRequester from '@/components/Button/IfaRequester.vue'
import { IfaSelfInspectItemManageA002RequestModel } from './js/IfaSelfInspectItemManageA002RequestModel'
import { IfaSelfInspectItemManageA003RequestModel } from './js/IfaSelfInspectItemManageA003RequestModel'
import { IfaSelfInspectItemManageA009RequestModel } from './js/IfaSelfInspectItemManageA009RequestModel'
import { IfaSelfInspectItemManageA010RequestModel } from './js/IfaSelfInspectItemManageA010RequestModel'
import { IfaSelfInspectItemManageA012RequestModel } from './js/IfaSelfInspectItemManageA012RequestModel'
import { getMessage } from '@/utils/errorHandler'
import { notifyMessage } from '@/utils/errorHandler'
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard.vue'
import Logger from '@/utils/ifaLog.js'
import * as XLSX from 'xlsx'

export default {
  components: {
    GridTable,
    IfaOkCancelDialog,
    screenTitle,
    IfaRequester,
    captionCard
  },
  data() {
    return {
      form: new IfaSelfInspectItemManageFormModel(),
      formRef: {},
      pqGridOption: null,
      uploadDialog: { // アップロードダイアログ
        visible: false,
        title: 'ファイルアップロード',
        disabledImportButton: true
      },
      deleteDialog: { // 削除確認ダイアログ
        visible: false,
        title: '削除確認',
        message: '選択した自己点検項目を削除します。よろしいですか？'
      },
      updateDialog: { // 更新確認ダイアログ
        visible: false,
        title: '更新確認',
        message: '自己点検項目を更新します。よろしいですか。'
      },
      deleteItem: {
        rowIndex: -1, // 削除対象行番号
        selfCheckItemId: '' // 削除対象行の自己点検項目ID
      },
      rules: {
        assignMonthList: { required: true, trigger: 'change', message: getMessage('errors.selected', ['登録年月']) }
      },
      fileJsonData: null // Excelファイル→Jsonデータ変換後のJsonデータ
    }
  },
  computed: {
    ifaSelfInspectItemManageA002RequestModel() {
      return new IfaSelfInspectItemManageA002RequestModel(this.form)
    },
    ifaSelfInspectItemManageA003RequestModel() {
      return new IfaSelfInspectItemManageA003RequestModel(this.form)
    },
    ifaSelfInspectItemManageA009RequestModel() {
      return new IfaSelfInspectItemManageA009RequestModel(this.form)
    },
    ifaSelfInspectItemManageA010RequestModel() {
      return new IfaSelfInspectItemManageA010RequestModel(
        {
          'brokerType': this.form.assignMonthList,
          'selfCheckItemId': this.deleteItem.selfCheckItemId
        }
      )
    },
    ifaSelfInspectItemManageA012RequestModel() {
      return new IfaSelfInspectItemManageA012RequestModel(this.form)
    }
  },
  created() {
    this.pqGridOption = this.getDefaultOption(columns1)
    this.pqGridOption.wrap = true
  },
  methods: {
    onShow() {
      // リクエスト発行：A001 初期化
      this.$nextTick(() => {
        document.getElementById('ifaSelfInspectItemManageInitializeA001').click()
      })
      this.formRef = this.$refs.form
    },
    responseHandlerInitializeA001(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      if (this.form.registerDateList.length > 0) {
        // 登録年月のセレクトボックス用にリスト化
        this.form.registerDateListSelectBox = response.dataList[0].registerDateList.map(item => {
          return {
            key: item.dateId,
            value: item.dateName
          }
        })
        // A001レスポンス.登録年月リストの翌月（0番地）を検索条件部.登録年月とする
        this.form.assignMonthList = this.form.registerDateList[0].dateId
      }
      if (this.form.brokerTypeList.length > 0) {
        // 業者区分のセレクトボックス用にリスト化
        this.form.brokerTypeListSelectBox = response.dataList[0].brokerTypeList.map(item => {
          return {
            key: item.brokerType,
            value: item.classificationName
          }
        })
        // 未選択状態を許可
        this.form.brokerTypeListSelectBox.unshift(
          {
            key: '',
            value: ''
          }
        )
      }
      // 検索条件部.業者区分を初期化
      this.form.brokerType = ''
      // 自己点検項目一覧を初期化
      this.resetSelfAssessmentItemList()
    },
    responseHandlerDisplayYearMonthChangeA002(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      // 検索条件部.業者区分を初期化
      this.form.brokerType = ''
      // 自己点検項目一覧を初期化
      this.resetSelfAssessmentItemList()
    },
    displayYearMonthChangeA002() {
      // リクエスト発行：A002 表示年月変更
      this.$nextTick(() => {
        document.getElementById('ifaSelfInspectItemManageDisplayYearMonthChangeA002').click()
      })
    },
    searchDisplayA003() {
      // 登録年月の必須チェック
      this.formRef.validate(valid => {
        // 登録年月が未選択の場合、業者区分を変更させない
        if (!valid) {
          this.form.brokerType = ''
          return
        }

        // 業者区分が未選択の場合、自己点検項目一覧を初期化
        if (this.form.brokerType === null || this.form.brokerType === '') {
          this.resetSelfAssessmentItemList()
          return
        }

        // リクエスト発行：A003 検索表示
        this.$nextTick(() => {
          document.getElementById('ifaSelfInspectItemManageSearchDisplayA003').click()
        })
      })
    },
    responseHandlerSearchDisplayA003(response) {
      if (response.dataList.length > 0) {
        this.form = Object.assign(this.form, response.dataList[0])
        this.pqGridOption.dataModel.data = this.form.selfAssessmentItemList.map(item => {
          return {
            ...item,
            'answer': this.convertAnswer(item.answer, true), // 回答 0/1→true/false
            'reasonRequiredFlag': this.convertReasonRequiredFlag(item.reasonRequiredFlag, true), // 理由必須フラグ 0/1→true/false
            'isNextMonth': this.isNextMonth(item.registerDate), // A003レスポンス.自己点検項目リスト.登録年月が来月かのチェック用フラグ
            'isNew': false // 新規追加行かのチェック用フラグ
          }
        })
      } else {
        this.form.selfAssessmentItemList = []
        this.pqGridOption.dataModel.data = []
      }

      this.$refs['gridTable'].refreshView()
    },
    addRowA004() {
      this.pqGridOption.dataModel.data.push(
        {
          sortNumber: '',
          selfAssessmentItemName: '',
          answer: 'はい',
          reasonRequiredFlag: false,
          delete: '削除',
          selfCheckItemId: '',
          isNextMonth: true,
          isNew: true // 新規追加行のためtrueを設定
        }
      )
      this.$refs['gridTable'].refreshView()
      this.setFormSelfAssessmentItemList() // form.自己点検項目リストを更新
    },
    updateConfirmA005() {
      this.updateDialog.visible = true
    },
    uploadA006() {
      this.uploadDialog.visible = true
    },
    deleteConfirmA007(row) {
      // 押下したセルが「削除」かつ活性状態の場合削除確認ダイアログを表示
      if (row.dataIndx === 'delete' && row.rowData.isNextMonth) {
        this.deleteDialog.visible = true
        this.deleteItem.rowIndex = row.rowIndx // 削除対象行をセット
        this.deleteItem.selfCheckItemId = row.rowData.selfCheckItemId // 削除対象の自己点検項目IDをセット
      }
    },
    updateA009() {
      this.updateDialog.visible = false
      // 入力チェック
      const errorMsg = this.updateCheckA009()
      if (errorMsg !== null) {
        notifyMessage(-1, errorMsg, this.form.title.name)
        return
      }
      this.setFormSelfAssessmentItemList() // form.自己点検項目リストを更新
      // リクエスト発行：A009 更新
      this.$nextTick(() => {
        document.getElementById('ifaSelfInspectItemManageUpdateA009').click()
      })
    },
    updateCheckA009() {
      // 自己点検項目一覧（グリッドテーブル）内の入力項目チェック
      const seen = new Set()
      for (const item of this.pqGridOption.dataModel.data) {
        // 必須入力チェック
        // 「表示順」
        if (item.sortNumber === undefined || item.sortNumber === null || item.sortNumber === '') {
          return getMessage('errors.required', ['表示順'])
        }
        // 「チェック項目」
        if (item.selfAssessmentItemName === undefined || item.selfAssessmentItemName === null || item.selfAssessmentItemName === '') {
          return getMessage('errors.required', ['チェック項目'])
        }

        // 桁数チェック
        // 「表示順」
        if (item.sortNumber.length > 3) {
          return getMessage('errors.maxSize', ['表示順', '3'])
        }

        // 「チェック項目」
        if (item.selfAssessmentItemName.length > 1000) {
          return getMessage('errors.maxSize', ['チェック項目', '1000'])
        }

        // 入力形式チェック「表示順」
        if (!/^[0-9]+$/.test(item.sortNumber)) {
          return getMessage('errors.cmn.notHalfWidthNumber', ['表示順'])
        }

        // 同一登録年月・業者区分の表示順重複チェック
        // 「登録年月」「業者区分」「表示順」の組み合わせが一意でなければチェックエラー
        const key = `${this.form.assignMonthList}-${this.form.brokerType}-${item.sortNumber}`
        if (seen.has(key)) {
          return getMessage('errors.emp.loginUsers.exist#2', ['表示順'])
        }
        seen.add(key)
      }

      // チェックOK
      return null
    },
    responseHandlerUpdateA009(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      // 検索条件部.業者区分を初期化
      this.form.brokerType = ''
      // 自己点検項目一覧を初期化
      this.resetSelfAssessmentItemList()
    },
    deleteA010() {
      this.deleteDialog.visible = false
      if (!this.pqGridOption.dataModel.data[this.deleteItem.rowIndex]['isNew']) { // 新規に追加された行であれば更新チェック＆リクエスト発行は行わない
        // 更新チェック（グリッドテーブル内の入力可能項目が入力前/入力後で一致しているか）
        if (this.form.selfAssessmentItemList[this.deleteItem.rowIndex].sortNumber !== this.pqGridOption.dataModel.data[this.deleteItem.rowIndex]['sortNumber'] ||
            this.form.selfAssessmentItemList[this.deleteItem.rowIndex].selfAssessmentItemName !== this.pqGridOption.dataModel.data[this.deleteItem.rowIndex]['selfAssessmentItemName'] ||
            this.form.selfAssessmentItemList[this.deleteItem.rowIndex].answer !== this.convertAnswer(this.pqGridOption.dataModel.data[this.deleteItem.rowIndex]['answer'], false) ||
            this.form.selfAssessmentItemList[this.deleteItem.rowIndex].reasonRequiredFlag !== this.convertReasonRequiredFlag(this.pqGridOption.dataModel.data[this.deleteItem.rowIndex]['reasonRequiredFlag'], false)
        ) {
          notifyMessage(-1, getMessage('errors.corSelfCheck.detail.unregistered'), this.form.title.name)
          return
        }
        // リクエスト発行：A010 削除
        this.$nextTick(() => {
          document.getElementById('ifaSelfInspectItemManageDeleteA010').click()
        })
      }
      // グリッドテーブルから削除対象行を削除
      this.pqGridOption.dataModel.data.splice(this.deleteItem.rowIndex, 1)
      this.$refs['gridTable'].refreshView()
      this.setFormSelfAssessmentItemList() // form.自己点検項目リストを更新

      // 自己点検項目情報が0件となった場合、A010リクエスト.業者区分に紐付く業者区分リスト.登録状況に"未登録"を設定
      if (this.form.selfAssessmentItemList.length === 0) {
        const index = this.form.brokerTypeList.findIndex(item => item.brokerType === this.form.brokerType)
        if (index !== -1) {
          this.form.brokerTypeList[index].registerStatus = '未登録'
        }
      }
    },
    selectFileA011(file) {
      this.form.attachFile = file
      this.uploadDialog.disabledImportButton = false
    },
    async fileImportA012() {
      const errorMsg = await this.fileImportCheckA012() // Excelファイルの入力値チェック
      if (errorMsg !== null) { // エラーがある場合はポップアップエラー表示
        notifyMessage(-1, errorMsg, this.form.title.name)
      } else { // エラーがない場合はA012リクエスト発行
        this.form.selfAssessmentItemListExcel = this.fileJsonData.map(row => {
          return {
            'registerDate': row[0].toString(), // 登録年月
            'brokerType': row[1].toString(), // 業者区分
            'sortNumber': row[2].toString(), // ソート番号
            'selfAssessmentItemName': row[3], // 自己点検項目名
            'answer': row[4] != null || row[4] === '' ? row[4].toString() : null, // 回答（未入力を許容）
            'reasonRequiredFlag': row[5].toString() // 理由必須フラグ
          }
        })
        // リクエスト発行：A012 ファイル取込
        this.$nextTick(() => {
          document.getElementById('ifaSelfInspectItemManageFileImportA012').click()
        })
      }
    },
    async fileImportCheckA012() {
      // Excelファイル内項目の入力チェック
      const reader = new FileReader()
      return new Promise((resolve, reject) => {
        // ファイルパスの拡張子チェック
        if (!this.form.attachFile.name.endsWith('.xls') && !this.form.attachFile.name.endsWith('.xlsx')) {
          return resolve(getMessage('errors.notExcelFile'))
        }
        reader.onload = (e) => {
          try {
            const data = new Uint8Array(e.target.result)
            const workbook = XLSX.read(data, { type: 'array' })

            // 最初のシートを取得
            const firstSheetName = workbook.SheetNames[0]
            const worksheet = workbook.Sheets[firstSheetName]
            // シートの内容をJSON形式に変換
            const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 1 })

            // Excelファイルの行数チェック（1行以下）
            if (!jsonData || jsonData.length < 1) {
              return resolve(getMessage('errors.excelOneMoreData'))
            }

            const seen = new Set()
            for (let i = 1; i < jsonData.length; i++) { // ヘッダ行を除いて1行目から開始
              const row = jsonData[i]
              for (let j = 0; j < row.length; j++) {
                const cellValue = row[j]
                if (j !== 4) { // 「回答」は必須入力＆桁数チェックの対象外
                  // 必須入力チェック
                  if (cellValue === undefined || cellValue === null || cellValue.toString() === '') {
                    return resolve(getMessage('errors.required', [this.excelHeader(j)]))
                  }

                  // 桁数チェック
                  if (cellValue.toString().length > this.excelMaxLength(j)) {
                    return resolve(getMessage('errors.maxSize', [this.excelHeader(j), this.excelMaxLength(j)]))
                  }
                }

                if (j !== 3) { // 「チェック項目」は入力形式チェックの対象外
                  // 入力形式チェック（「回答」は空文字を許容する）
                  if (cellValue !== undefined && cellValue !== null && cellValue.toString() !== '' && !/^[0-9]+$/.test(cellValue)) {
                    return resolve(getMessage('errors.cmn.notHalfWidthNumber', [this.excelHeader(j)]))
                  }
                }
              }
              // 登録年月の日付チェック
              if (!this.isNextMonth(row[0])) {
                return resolve(getMessage('errors.corSelfCheck.corEnforceDate.nextMonth'))
              }

              // 入力値チェック（0/1）
              // 「回答」※ 必須入力項目ではないので、空文字を許容する
              if (row[4] !== undefined &&
                  row[4] !== null &&
                  row[4] !== '' &&
                  row[4] !== 0 &&
                  row[4] !== 1 &&
                  row[4] !== '0' &&
                  row[4] !== '1'
              ) {
                return resolve(getMessage('errors.type', [this.excelHeader(4), '0または1']))
              }
              // 「理由必須」
              if (row[5] !== 0 &&
                  row[5] !== 1 &&
                  row[5] !== '0' &&
                  row[5] !== '1'
              ) {
                return resolve(getMessage('errors.type', [this.excelHeader(5), '0または1']))
              }

              // 同一登録年月・業者区分の表示順重複チェック
              // 「登録年月」「業者区分」「表示順」の組み合わせが一意でなければチェックエラー
              const key = `${row[0]}-${row[1]}-${row[2]}`
              if (seen.has(key)) {
                return resolve(getMessage('errors.emp.loginUsers.exist#2', ['表示順']))
              }
              seen.add(key)
            }
            // Excelファイルから変換したJSONデータを設定
            this.fileJsonData = jsonData.slice(1) // ヘッダ行を除く
          } catch (error) {
            if (error.message.includes('password-protected')) {
              // Excelファイルのパスワード設定チェック
              return resolve(getMessage('errors.hasPassword'))
            } else {
              // ファイル読み込み失敗の場合はシステムエラー
              Logger.error(error)
              return resolve(getMessage('errors.systemError'))
            }
          }
          // チェックOK
          return resolve(null)
        }
        reader.readAsArrayBuffer(this.form.attachFile.raw)
      })
    },
    responseHandlerFileImportA012(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      this.uploadDialog.visible = false
      // 検索条件部.業者区分を初期化
      this.form.brokerType = ''
      // 自己点検項目一覧を初期化
      this.resetSelfAssessmentItemList()
    },
    filePathDeleteA013(response) {
      this.form.attachFile = null
      this.uploadDialog.disabledImportButton = true
    },
    backA014() {
      this.uploadDialog.visible = false
    },
    ratioColor(status) {
      return status === '未登録' ? '_table__data __left __red' : '_table__data __left'
    },
    // 入力値（YYYYMM形式）が翌月であるかを判定
    isNextMonth(targetMonth) {
      if (typeof targetMonth !== 'string') {
        targetMonth = String(targetMonth)
      }
      if (!targetMonth.match(/^\d{4}\d{2}$/)) {
        return false
      }
      const currentDate = new Date(this.$store.getters.requestedTime)
      const currentYear = currentDate.getFullYear()
      const currentMonth = currentDate.getMonth() + 1

      let nextYear = currentYear
      let nextMonth = currentMonth + 1

      if (nextMonth > 12) {
        nextMonth = 1
        nextYear += 1
      }

      const formattedNextMonth = `${nextYear}${nextMonth.toString().padStart(2, '0')}`

      return targetMonth === formattedNextMonth
    },
    updateCloseDialogA009() {
      this.updateDialog.visible = false
    },
    deleteCloseDialogA010() {
      this.deleteDialog.visible = false
    },
    getDefaultOption(colModel) {
      return {
        showTop: false,
        reactive: true,
        locale: 'en',
        height: 'flex',
        numberCell: {
          show: false
        },
        columnTemplate: { width: 100 },
        pageModel: {
          type: 'local',
          rPP: 30,
          rPPOptions: [30, 50, 100, 200, 500],
          layout: ['strDisplay', '|', 'prev', 'next']
        },
        colModel: colModel,
        dataModel: {
          data: []
        },
        wrap: false,
        maxHeight: 750,
        width: 900,
        selectionModel: { type: 'row', mode: 'single' }
      }
    },
    setFormSelfAssessmentItemList() {
      // グリッドテーブル；自己点検項目リスト内の値をFormModelに格納する
      this.form.selfAssessmentItemList = this.pqGridOption.dataModel.data.map(item => {
        return {
          'selfCheckItemId': item.selfCheckItemId,
          'sortNumber': item.sortNumber,
          'selfAssessmentItemName': item.selfAssessmentItemName,
          'answer': this.convertAnswer(item.answer, false),
          'reasonRequiredFlag': this.convertReasonRequiredFlag(item.reasonRequiredFlag, false)
        }
      })
    },
    // 自己点検項目一覧.回答のフォーマット相互変換処理
    convertAnswer(value, isInput) {
      if (isInput) {
        switch (value) {
          case '0':
            return 'いいえ'
          case '1':
            return 'はい'
          default:
            return '-'
        }
      } else {
        switch (value) {
          case 'いいえ':
            return '0'
          case 'はい':
            return '1'
          case '-':
            return null
          default:
            return null
        }
      }
    },
    // 自己点検項目一覧.理由必須のフォーマット相互変換処理
    convertReasonRequiredFlag(value, isInput) {
      if (isInput) {
        switch (value) {
          case '0':
            return false
          case '1':
            return true
          default:
            return false
        }
      } else {
        switch (value) {
          case false:
            return '0'
          case true:
            return '1'
          default:
            return '0'
        }
      }
    },
    // Excelの列番号に対応するヘッダ（項目名）を返却
    excelHeader(rowNo) {
      switch (rowNo) {
        case 0:
          return '登録年月'
        case 1:
          return '業者区分'
        case 2:
          return '表示順'
        case 3:
          return 'チェック項目'
        case 4:
          return '回答'
        case 5:
          return '理由必須'
      }
    },
    // Excelの列番号に対応する最大入力桁数を返却
    excelMaxLength(rowNo) {
      switch (rowNo) {
        case 0:
          return 6 // 登録年月
        case 1:
          return 1 // 業者区分
        case 2:
          return 2 // 表示順
        case 3:
          return 1000 // チェック項目
        case 4:
          return 1 // 回答
        case 5:
          return 1 // 理由必須
      }
    },
    // グリッドテーブルの自己点検項目一覧を初期化
    resetSelfAssessmentItemList() {
      this.form.selfAssessmentItemList = []
      this.pqGridOption.dataModel.data.splice(0, this.pqGridOption.dataModel.data.length)
      this.$refs['gridTable'].refreshView()
    }
  }
}
// Grid表示用データ
const columns1 = [
  { title: '表示順', dataType: 'string', dataIndx: 'sortNumber', width: '100', align: 'right', halign: 'center',
    render: function(ui) {
      // 活性/非活性判定
      if (ui.rowData.isNextMonth) {
        return { style: 'background-color: #ffffd3' }
      } else {
        return { style: 'background-color: #ffffd3; cursor: not-allowed;' }
      }
    },
    editable: function(ui) { return (ui.rowData.isNextMonth) }
  },
  { title: 'チェック項目', dataType: 'string', dataIndx: 'selfAssessmentItemName', width: '600', align: 'left', halign: 'center',
    render: function(ui) {
      // 活性/非活性判定
      if (ui.rowData.isNextMonth) {
        return { style: 'background-color: #ffffd3' }
      } else {
        return { style: 'background-color: #ffffd3; cursor: not-allowed;' }
      }
    },
    editable: function(ui) { return (ui.rowData.isNextMonth) }
  },
  {
    title: '回答', dataIndx: 'answer', width: 100, dataType: 'string', type: 'select',
    sortable: false, align: 'center', halign: 'center',
    editor: { type: 'select', options: ['いいえ', 'はい', '-'] },
    render: function(ui) {
      // 活性/非活性判定
      if (ui.rowData.reasonRequiredFlag) {
        // 理由必須フラグ＝1（true）の場合、非活性＆入力値をクリア
        ui.rowData.answer = '-'
        return { text: '-', style: 'background-color: #ffffd3; cursor: not-allowed;' }
      } else if (!ui.rowData.isNextMonth) {
        return { style: 'background-color: #ffffd3; cursor: not-allowed;' }
      } else {
        return { style: 'background-color: #ffffd3' }
      }
    },
    editable: function(ui) { return (!ui.rowData.reasonRequiredFlag && ui.rowData.isNextMonth) }
  },
  { title: '理由必須', dataIndx: 'reasonRequiredFlag', width: '100', align: 'center', halign: 'center',
    type: 'checkbox', dataType: 'bool',
    render: function(ui) {
      // 活性/非活性判定
      if (ui.rowData.isNextMonth) {
        return { style: 'background-color: #ffffd3' }
      } else {
        return {
          style: 'background-color: #ffffd3',
          text: `<input type="checkbox" disabled style="cursor: not-allowed" ` + (ui.rowData.reasonRequiredFlag === true ? 'checked' : '') + `>` }
      }
    },
    editable: function(ui) { return (ui.rowData.isNextMonth) }
  },
  { title: '削除', dataType: 'string', dataIndx: 'delete', width: '100', align: 'center', halign: 'center',
    render: function(ui) {
      // 活性/非活性判定
      if (ui.rowData.isNextMonth) {
        return `<span class='grid-link'><a>` + '削除' + `</a></span> 
        <style>
        .grid-link a {
          color: #092987;
          text-decoration: underline;
         &:focus, &:hover {
          color: #092987;
          text-decoration: underline;
          cursor: pointer;
          opacity: 0.7;
           }
         }
        </style>`
      } else {
        return `<a disabled style="cursor: not-allowed; color: #a8abb2; text-decoration: underline;" >` + '削除' + `</a>`
      }
    },
    editable: function(ui) { return (ui.rowData.isNextMonth) }
  },
  {
    title: '&nbsp;', dataIndx: 'blank', minWidth: 810, dataType: 'string', editable: false, halign: 'center', align: 'center',
    render: function() {
      return ''
    }
  },
  { title: '自己点検項目ID', dataType: 'string', dataIndx: 'selfCheckItemId', hidden: true },
  { title: '登録年月判定フラグ', dataType: 'string', dataIndx: 'isNextMonth', hidden: true }, // 実装のため追加（活性/非活性の判定に使用）
  { title: '新規追加判定フラグ', dataType: 'string', dataIndx: 'isNew', hidden: true } // 実装のため追加（新規追加行かのチェック用フラグ）
]
</script>
<style lang="scss" scoped>
:deep(.el-dialog__header) {
  padding: 0;
}
.file_select{
  :deep(.el-upload-list){
    width: 100%;
    min-width: 300px;
  }
}
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
.close-button {
  margin-bottom: 1rem;
  text-align: right;
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
.__red {
  color: red;
}
</style>
