<template>
  <div>  <!-- 画面：SUB0206_01-01 共同募集　顧客管理 -->
    <screen-title :text="form.screenTitle"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      :rules="rules"
    >
      <el-card
        class="content-card"
        shadow="always"
      >
        <div>     <!-- 検索エリア -->
          <el-row class="ifa-search__align-items">
            <el-col
              :span="24"
            >
              <ifa-joint-subscript-search
                ref="jointSubscriptSearchItem"
                :form="form"
                display-pattern="pt1"
                :is-display-info="checkCommComment"
              ></ifa-joint-subscript-search>
            </el-col>
          </el-row>
          <el-row class="ifa-search__align-items">
            <ifa-input-select
              id="editStatus"
              v-model="form.editStatus"
              select-class="search-form__item"
              label="手続状況"
              code-list-id="JOINT_SUBSCRIPT_CUSTOMER_PROCEDURE_STATUS"
              :disp-pattern="2"
              :select-pattern="2"
              :clearable="false"
            ></ifa-input-select>
          </el-row>
        </div>
        <el-row>  <!-- 検索用ボ各ボタンの操作部エリア -->
          <el-col
            :span="8"
            class="search_btn-area"
          >
            <ifa-button
              id="btnSearch"
              name="btnSearch"
              text="表示"
              search
              small
              width="90"
              :request-model="IfaJointSubscriptCustomerManageA002RequestModel"
              :form="formRef"
              action-id="SUB0206_01-01#A002"
              action-type="requestAction"
              @response-handler="responseHandlerActionA002($event)"
            ></ifa-button>
            <ifa-button
              id="btnClear"
              name="btnClear"
              text="クリア"
              small
              width="90"
              color="secondary"
              action-type="originalAction"
              @app-action-handler="clearA009"
            ></ifa-button>
          </el-col>
        </el-row>
      </el-card>
      <el-row>
        <el-card class="content-card">
          <el-row>  <!-- 各ボタンの操作部エリア -->
            <div class="gridButtonArea">
              <ifa-button
                id="btnAddNewJointCustomer"
                name="btnAddNewJointCustomer"
                text="新規登録"
                action-id="SUB0206_01-02#A001"
                action-type="requestAction"
                small
                width="90"
                :request-model="IfaJointSubscriptCustomerRegisterA001RequestModel"
                @response-handler="responseHandlerActionA003"
              ></ifa-button>
              <ifa-button
                id="btnApproval"
                name="btnApproval"
                :disabled="disableBtnApprove"
                text="承認"
                small
                width="90"
                action-type="originalAction"
                @app-action-handler="preApproveConfirm"
              ></ifa-button>
              <ifa-button
                id="btnCorrection"
                name="btnCorrection"
                :disabled="disableBtnCorrect"
                text="更新"
                small
                width="90"
                action-type="originalAction"
                @app-action-handler="preCorrectDisplay"
              ></ifa-button>
              <ifa-button
                v-if="isShowBtnCsvDownload()"
                id="btnCsvDownload"
                name="btnCsvDownload"
                text="CSV出力"
                :disabled="disableBtnCsvDownload"
                small
                width="90"
                :request-model="IfaJointSubscriptCustomerManageA006RequestModel"
                :form="formRef"
                action-id="SUB0206_01-01#A006"
                action-type="outputCsvAction"
                csv-file-name="共募顧客検索"
                :is-check-csv-download-allowed="true"
                :is-check-csv-download-privacy-confirmation="true"
              ></ifa-button>
              <ifa-button
                id="btnDeleteEntry"
                name="btnDeleteEntry"
                :disabled="disableBtnDelete"
                text="削除"
                small
                width="90"
                action-type="originalAction"
                @app-action-handler="preDeleteConfirm"
              ></ifa-button>
            </div>
          </el-row>
          <el-row>  <!-- 一覧表示部エリア -->
            <grid-table
              ref="gridTable"
              :options="pqGridOption"
              :auto-refresh="false"
              @click="selectRecord($event)"
            ></grid-table>
          </el-row>
        </el-card>
      </el-row>
    </el-form>
  </div>
  <!-- 以下：アクション処理に対応する画面コンポーネントを実装する -->
  <!-- A001アクションのリクエスト処理:画面初期化 -->
  <ifa-requester
    id="ifaJointSubscriptCustomerManageA001"
    action-id="SUB0206_01-01#A001"
    action-type="requestAction"
    @response-handler="responseHandlerActionA001($event)"
    @response-error-handler="errorHandlerActionA001($event)"
  ></ifa-requester>
  <!-- A003アクションのSUB0206_01-02 新規登録画面のポップアップ -->
  <ifa-joint-subscript-customer-register
    ref="IfaJointSubscriptCustomerRegister"
    :is-visible="isRegisterInputVisible"
    @close-modal="isRegisterInputVisible = false"
    @joint-subscript-customer-registered="onActionA003Success"
  ></ifa-joint-subscript-customer-register>
  <!-- A004アクションのSUB0206_01-03 更新画面のリクエスト処理:更新(入力) -->
  <ifa-requester
    id="ifaJointSubscriptCustomerManageA004"
    action-id="SUB0206_01-03#A001"
    action-type="requestAction"
    :request-model="IfaJointSubscriptCustomerCorrectA001RequestModel"
    @response-handler="responseHandlerActionA004($event)"
  ></ifa-requester>
  <!-- A004アクションのSUB0206_01-03 更新画面のポップアップ -->
  <ifa-joint-subscript-customer-correct
    ref="IfaJointSubscriptCustomerCorrect"
    :is-visible="isCorrectInputVisible"
    :form-data="IfaJointSubscriptCustomerCorrectA001RequestModel"
    @close-modal="isCorrectInputVisible = false"
    @joint-subscript-customer-corrected="onActionA004Success"
  ></ifa-joint-subscript-customer-correct>
  <!-- A005アクションのリクエスト処理:承認確認 -->
  <ifa-requester
    id="ifaJointSubscriptCustomerManageA005"
    action-id="SUB0206_01-01#A005"
    action-type="requestAction"
    :request-model="IfaJointSubscriptCustomerManageA005RequestModel"
    @response-handler="responseHandlerActionA005($event)"
  ></ifa-requester>
  <!-- A005アクション 承認確認のコンファームダイアログ -->
  <ifa-approve-joint-subscript-customer-dialog
    :is-visible="isApproveConfirmVisible"
    title="共同募集　顧客情報の承認"
    message="口座情報を承認します。よろしいですか？"
    @close-modal-ok="approveJointSubscriptCustomer"
    @close-modal-cancel="isApproveConfirmVisible = false"
  ></ifa-approve-joint-subscript-customer-dialog>
  <!-- A007アクションのリクエスト処理:削除確認 -->
  <ifa-requester
    id="ifaJointSubscriptCustomerManageA007"
    action-id="SUB0206_01-01#A007"
    action-type="requestAction"
    :request-model="IfaJointSubscriptCustomerManageA007RequestModel"
    @response-handler="responseHandlerActionA007($event)"
  ></ifa-requester>
  <!-- A007アクションの削除確認のコンファームダイアログ -->
  <ifa-delete-joint-subscript-customer-dialog
    :is-visible="isDeleteConfirmVisible"
    title="共同募集　顧客情報の削除"
    message="口座情報を削除します。よろしいですか？"
    @close-modal-ok="deleteJointSubscriptCustomer"
    @close-modal-cancel="isDeleteConfirmVisible = false, disableAllButton(false, false, false, false)"
  ></ifa-delete-joint-subscript-customer-dialog>
  <!-- A008アクションのリクエスト処理:顧客情報詳細 -->
  <ifa-requester
    id="ifaJointSubscriptCustomerManageA008"
    action-id="SUB0206_01-01#A008"
    action-type="requestAction"
    :request-model="IfaJointSubscriptCustomerManageA008RequestModel"
    @response-handler="responseHandlerActionA008($event)"
  ></ifa-requester>
  <!-- A008アクションの顧客情報詳細のダイアログ -->
  <ifa-joint-subscript-customer-detail
    :is-visible="customerDetailVisible"
    :customer-detail-form="a008ResModel"
    @close-modal="customerDetailVisible = false"
  ></ifa-joint-subscript-customer-detail>
</template>

<script>
/* 以下は各々コンポーネントをエクスポートする */
// エラーメッセージコンポーネント
import { notifyMessage, getMessage } from '@/utils/errorHandler'
// pqGridコンポーネント
import GridTable from '@/components/GridTable'
// 仲介業メニュー系の画面タイトル
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
// pqGridコンポーネントの設定オプション
import { getConvertedOption } from '@/utils/pqgridHelper'
// 日付文字列フォーマット
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
// 数値フォーマット
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
// 画面共通部品_CC020_共同募集検索条件（一覧画面）
import IfaJointSubscriptSearch from '@/components/SearchCondition/IfaJointSubscriptSearch.vue'
// 画面：SUB0206_01-02 共同募集　顧客管理　新規登録(入力)
import IfaJointSubscriptCustomerRegister from './IfaJointSubscriptCustomerRegister'
// 画面：SUB0206_01-03 共同募集　顧客管理　更新(入力)
import IfaJointSubscriptCustomerCorrect from './IfaJointSubscriptCustomerCorrect'
// 画面：SUB0206_01-04 顧客情報詳細
import IfaJointSubscriptCustomerDetail from './IfaJointSubscriptCustomerDetail.vue'
// SUB0206_01-01共同募集　顧客管理のフォームモデル
import { IfaJointSubscriptCustomerManageFormModel } from './js/IfaJointSubscriptCustomerManageFormModel.js'
// 共同募集　顧客管理 A002検索(表示)のリクエストモデル
import { IfaJointSubscriptCustomerManageA002RequestModel } from './js/IfaJointSubscriptCustomerManageA002RequestModel.js'
// 共同募集　顧客管理 A005承認確認のリクエストモデル
import { IfaJointSubscriptCustomerManageA005RequestModel } from './js/IfaJointSubscriptCustomerManageA005RequestModel.js'
// 共同募集　顧客管理 A006CSV出力のリクエストモデル
import { IfaJointSubscriptCustomerManageA006RequestModel } from './js/IfaJointSubscriptCustomerManageA006RequestModel.js'
// 共同募集　顧客管理 A007承認のリクエストモデル
import { IfaJointSubscriptCustomerManageA007RequestModel } from './js/IfaJointSubscriptCustomerManageA007RequestModel.js'
// 共同募集　顧客管理 A008承認取消のリクエストモデル
import { IfaJointSubscriptCustomerManageA008RequestModel } from './js/IfaJointSubscriptCustomerManageA008RequestModel.js'
// 共同募集　顧客管理 A008顧客情報詳細ののレスポンスモデル
import { IfaJointSubscriptCustomerManageA008ResponseModel } from './js/IfaJointSubscriptCustomerManageA008ResponseModel.js'
// 共同募集　顧客管理 新規登録 A001初期化のリクエストモデル
import { IfaJointSubscriptCustomerRegisterA001RequestModel } from './js/IfaJointSubscriptCustomerRegisterA001RequestModel.js'
// 共同募集　顧客管理 更新 A001初期化のリクエストモデル
import { IfaJointSubscriptCustomerCorrectA001RequestModel } from './js/IfaJointSubscriptCustomerCorrectA001RequestModel.js'
// A005アクションの承認確認のコンファームダイアログ
import IfaApproveJointSubscriptCustomerDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
// A007アクションの削除確認のコンファームダイアログ
import IfaDeleteJointSubscriptCustomerDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
// 画面：SUB0206_01-02 共同募集　顧客管理 自動解約理由
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'

/* 以下はVUEコンポーネントのプロパティ：エクスポートされたオブジェクトを指定する */
export default {
  /* 使用可能な子コンポーネントを定義する */
  components: {
    GridTable,
    screenTitle,
    IfaJointSubscriptSearch,
    IfaJointSubscriptCustomerRegister,
    IfaJointSubscriptCustomerCorrect,
    IfaDeleteJointSubscriptCustomerDialog,
    IfaApproveJointSubscriptCustomerDialog,
    IfaJointSubscriptCustomerDetail
  },
  /* 以下はVUEコンポーネントのプロパティ：イベントを宣言する */
  emits: ['initializeError'],
  /* 以下はVUEコンポーネントのプロパティ：画面変数を初期化する */
  data() {
    return {
      // フォームモデルを初期化
      form: new IfaJointSubscriptCustomerManageFormModel(),
      // フォームの検証ルール
      rules: {
        period: [{ required: true, type: 'Object', validator: this.dateValidator }]
      },
      // pqGridコンポーネントの設定オプション
      pqGridOption: getConvertedOption(gridTableObj),
      // pgGridの複数選択可能のオプション
      pgGridSelectionOption: { type: 'row', method: 'getSelection' },
      // pqGridコンポーネントの単一選択されたレコードの格納オブジェクト
      pqGridSelectedInfo: {},
      // pqGridコンポーネントの複数選択されたレコードの格納配列
      pgGridMultiSelAry: [],
      // 修正ボタン 非活性
      disableBtnCorrect: true,
      // 承認ボタン 非活性
      disableBtnApprove: true,
      // CSV出力ボタン 非活性
      disableBtnCsvDownload: true,
      // 削除ボタン 非活性
      disableBtnDelete: true,
      // フォーム参照オブジェクト
      formRef: {},
      // コメントを表示するかどうか
      isInfoMessage: false,
      // 新規登録(入力)ダイアログ表示するかどうか
      isRegisterInputVisible: false,
      // 更新(入力)ダイアログ表示するかどうか
      isCorrectInputVisible: false,
      // 承認確認ダイアログ表示するかどうか
      isApproveConfirmVisible: false,
      // 削除確認ダイアログ表示するかどうか
      isDeleteConfirmVisible: false,
      // 顧客詳細ダイアログ表示するかどうか
      customerDetailVisible: false,
      // 検索を実行したかどうかを判断するフラグ
      isSearched: false,
      // 顧客詳細のフォーム
      a008ResModel: new IfaJointSubscriptCustomerManageA008ResponseModel()
    }
  },
  /* 以下はVUEコンポーネントのプロパティ：計算プロパティ */
  computed: {
    // A002アクション 検索(表示)のリクエストモデル
    IfaJointSubscriptCustomerManageA002RequestModel() {
      return new IfaJointSubscriptCustomerManageA002RequestModel(this.form)
    },
    // A005アクション 承認確認のリクエストモデル
    IfaJointSubscriptCustomerManageA005RequestModel() {
      return new IfaJointSubscriptCustomerManageA005RequestModel(this.pgGridMultiSelAry)
    },
    // A006アクション CSV出力のリクエストモデル
    IfaJointSubscriptCustomerManageA006RequestModel() {
      return new IfaJointSubscriptCustomerManageA006RequestModel(this.form)
    },
    // A007アクション 削除確認のリクエストモデル
    IfaJointSubscriptCustomerManageA007RequestModel() {
      return new IfaJointSubscriptCustomerManageA007RequestModel(this.pgGridMultiSelAry)
    },
    // A008アクション 顧客情報詳細のリクエストモデル
    IfaJointSubscriptCustomerManageA008RequestModel() {
      return new IfaJointSubscriptCustomerManageA008RequestModel(this.pqGridSelectedInfo)
    },
    // SUB0206_01-02新規登録の A001アクション 初期化のリクエストモデル
    IfaJointSubscriptCustomerRegisterA001RequestModel() {
      return new IfaJointSubscriptCustomerRegisterA001RequestModel(this.form)
    },
    // SUB0206_01-03更新の A001アクション 初期化のリクエストモデル
    IfaJointSubscriptCustomerCorrectA001RequestModel() {
      return new IfaJointSubscriptCustomerCorrectA001RequestModel(this.pqGridSelectedInfo)
    },
    checkCommComment() {
      if (this.form.commComment) {
        return this.form.commComment.replace(/\s/g, '').replace(/　/g, '') !== ''
      } else {
        return false
      }
    }
  },
  /* 以下はVUEコンポーネントのプロパティ：DOMのマウントを呼び出し */
  mounted() {
    this.formRef = this.$refs.form
    this.form.editStatus = ' '
  },
  /* 以下はVUEコンポーネントのプロパティ： 使用される関数を定義する */
  methods: {
    /**
     * 関数：A001アクションの画面初期化処理
     * @returns なし
     */
    onShow() {
      this.disableAllButton(true, true, true, true)
      this.isShowAutoCancellationReason()
      this.pqGridOption.dataModel.data = []
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView(true)
        document.getElementById('ifaJointSubscriptCustomerManageA001').click()
      })
    },
    /**
     * 関数：承認、削除、更新、CSV出力 四つボタンの非活性化設定
     * trueの場合、非活性化
     * falseの場合、活性化
     * @param appr 承認ボタンの非活性化するかどうかフラグ
     * @param dele 削除ボタンの非活性化するかどうかフラグ
     * @param corr 更新ボタンの非活性化するかどうかフラグ
     * @param csvd CSV出力ボタンの非活性化するかどうかフラグ
     * @returns なし
     */
    disableAllButton(appr, dele, corr, csvd) {
      this.disableBtnApprove = appr
      this.disableBtnDelete = dele
      this.disableBtnCorrect = corr
      this.disableBtnCsvDownload = csvd
    },
    // 関数：CSV出力ボタンの表示するかどうか設定
    isShowBtnCsvDownload() {
      // 本店,支店,仲介業者内管以外非表示
      return this.$store.getters.userAccount.medUsers.privId === '1' ||
         this.$store.getters.userAccount.medUsers.privId === '2' ||
         this.$store.getters.userAccount.medUsers.privId === '3'
    },
    /**
     * 関数：顧客情報詳細一覧の列「自動解約理由」の表示するか、設定する
     *   ・権限: 1:本店, 2:支店場合、列「自動解約理由」を表示する
     *   ・上記以外、列「自動解約理由」を表示しない
     */
    isShowAutoCancellationReason() {
      if (this.$store.getters.userAccount.medUsers.privId === '1' || this.$store.getters.userAccount.medUsers.privId === '2') {
        // 権限: 1:本店, 2:支店
        this.setHidden('autoCancellationReason', false) // 顧客情報詳細一覧の列「自動解約顧客」を表示する
      } else {
        // 権限: 上記以外
        this.setHidden('autoCancellationReason', true) // 顧客情報詳細一覧の列「自動解約顧客」を表示しない
      }
    },
    /**
     * 指定している列を表示するかどうか、設定します
     * @param dataIndx 指定列のID
     * @param value true : 表示  false 非表示
    */
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    },
    /**
     * 関数：A001アクションのレスポンス処理:画面初期化
     * @param response レスポンス
     * @returns なし
     */
    responseHandlerActionA001(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      this.requestedTime = response.requestedTime.split(' ')[0]
    },
    errorHandlerActionA001(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    /**
     * 関数：A002アクションのレスポンス処理:検索(表示)
     * @param response レスポンス
     * @returns なし
     */
    responseHandlerActionA002(response) {
      // flg = 検索操作が実行ずみ
      this.isSearched = true
      // 承認、削除、更新、CSV出力 四つボタンの非活性化設定
      this.disableAllButton(true, true, true, true)
      // 共同募集 顧客管理一覧情報があれば、CSV出力ボタン制御を解除し、一覧を表示する
      if (response.dataList.length > 0) {
        this.pqGridOption.dataModel.data = response.dataList.map(item => ({ ...item }))
        // 操作部のボタンを制御する
        this.disableAllButton(true, true, true, false)
      } else {
        this.pqGridOption.dataModel.data = []
      }
      // 一覧グリッドを更新する
      this.$refs['gridTable'].refreshView(true)
    },
    /**
     * 関数：A003アクションのレスポンス処理: 新規登録(入力)画面を表示する
     * @param response レスポンス
     * @returns なし
     */
    responseHandlerActionA003(response) {
      this.isRegisterInputVisible = true
    },
    // 関数：A003アクションの新規登録成功された後の処理
    onActionA003Success() {
      this.isRegisterInputVisible = false
      // 検索処理が実行された場合、一覧グリッドを更新する
      if (this.isSearched) {
        this.refreshGridTable()
      }
    },
    /**
     * 関数：A004アクションの更新処理：更新画面表示前、エラーチェックを行う。
     * @returns なし
     */
    preCorrectDisplay() {
      const selectRows = this.$refs.gridTable.grid.selection(this.pgGridSelectionOption)
      // 1件以外のデータを選択した場合、エラーメッセージを表示し、処理を終了。
      if (selectRows.length !== 1) {
        notifyMessage(-1, getMessage('errors.joint.data.selection.required', ['1件']), this.form.screenTitle)
        return
      } else {
        document.getElementById('ifaJointSubscriptCustomerManageA004').click()
      }
    },
    /**
     * 関数：A004アクションのレスポンス処理: 更新(入力)画面を表示する
     * @param response レスポンス
     * @returns なし
     */
    responseHandlerActionA004(response) {
      this.isCorrectInputVisible = true
    },
    // 関数：A004アクションの新規登録成功された後の処理
    onActionA004Success() {
      this.isCorrectInputVisible = false
      this.refreshGridTable()
    },
    /**
     * 関数：A005アクションの承認処理：承認確認ダイアログ表示前、エラーチェックを行う。
     * @returns なし
     */
    preApproveConfirm() {
      const selectRows = this.$refs.gridTable.grid.selection(this.pgGridSelectionOption)
      // 1.データが選択されていない場合、エラーメッセージを表示し、処理を終了。
      if (selectRows.length < 1) {
        notifyMessage(-1, getMessage('errors.data.selection.required', ['1件以上']), this.form.screenTitle)
        return
      }
      // 選択したデータをリストへ格納する
      this.pgGridMultiSelAry = []
      selectRows.forEach(row => {
        this.pgGridMultiSelAry.push(row.rowData)
      })
      // 2.選択されたデータの中に、「手続状況」が「承認」であるデータがある場合、エラーメッセージを表示し、処理を終了。
      if (this.pgGridMultiSelAry.some(model => model.editStatus === '2')) {
        notifyMessage(-1, getMessage('errors.joint.data.selection.approved'), this.form.screenTitle)
        return
      }
      // 承認確認ダイアログへ
      this.isApproveConfirmVisible = true
    },
    /**
     * 関数：A005アクションの承認処理：承認へ
     * @returns なし
     */
    approveJointSubscriptCustomer() {
      this.isApproveConfirmVisible = false
      document.getElementById('ifaJointSubscriptCustomerManageA005').click()
    },
    /**
     * 関数：A005アクションのレスポンス処理：一覧グリッドを更新する
     * @param response レスポンス
     * @returns なし
     */
    responseHandlerActionA005(response) {
      this.refreshGridTable()
    },
    // 関数： A007アクションの削除処理：確認ダイアログ表示前、エラーチェックを行う。
    preDeleteConfirm() {
      const selectRows = this.$refs.gridTable.grid.selection(this.pgGridSelectionOption)
      // 1件以外のデータを選択した場合、エラーメッセージを表示し、処理を終了。
      if (selectRows.length !== 1) {
        notifyMessage(-1, getMessage('errors.joint.data.selection.required', ['1件']), this.form.screenTitle)
        return
      }
      // 選択した1件のデータをリストへ格納する
      this.pgGridMultiSelAry = []
      this.pgGridMultiSelAry.push(selectRows[0].rowData)
      // 削除確認ダイアログへ
      this.isDeleteConfirmVisible = true
    },
    /**
     * 関数：A007アクションの削除処理：削除へ
     * @returns なし
     */
    deleteJointSubscriptCustomer() {
      this.isDeleteConfirmVisible = false
      document.getElementById('ifaJointSubscriptCustomerManageA007').click()
    },
    /**
     * 関数：A007アクションのレスポンス処理：一覧グリッドを更新する
     * @param response レスポンス
     * @returns なし
     */
    responseHandlerActionA007(response) {
      this.refreshGridTable()
    },
    /**
     * 関数：A008アクションのレスポンス処理:顧客情報詳細
     * @param response レスポンス
     * @returns なし
     */
    responseHandlerActionA008(response) {
      this.a008ResModel = Object.assign(this.a008ResModel, response.dataList[0])
      this.customerDetailVisible = true
    },
    /**
     * 関数：A009アクションの処理:クリア
     * @returns なし
     */
    clearA009() {
      // 画面検索条件をクリアする
      this.$refs.jointSubscriptSearchItem.formClear()
      this.$refs['form']?.clearValidate()
      this.form.editStatus = ' '
      // flg = 検索操作が実行待ち
      this.isSearched = false
    },
    /**
     * 関数：一覧明細レコードの選択処理
     * @param row 選択されたの一覧行目のオブジェクト
     * @param rowIndex 選択されたの一覧行目のindex
     * @returns なし
     */
    selectRecord(row, rowIndex) {
      this.disableBtnApprove = false
      this.disableBtnCorrect = false
      this.disableBtnDelete = false

      Object.assign(this.pqGridSelectedInfo, row.rowData)
      // 顧客名のリンククリック処理
      if (row.dataIndx === 'nameKanji' &&
        row.rowData.editStatus &&
        row.rowData.editStatus === '2' &&
        row.rowData.nameKanji) {
        this.$nextTick(() => {
          document.getElementById('ifaJointSubscriptCustomerManageA008').click()
        })
      }
    },
    /**
     * 関数：一覧グリッドを更新する
     * @returns なし
     */
    refreshGridTable() {
      document.getElementById('btnSearch').click()
      this.disableAllButton(true, true, true, true)
    }
  }
}

// 一覧用GridTableの設定
const gridTableObj = {
  width: 0,
  height: 0,
  title: '共同募集　顧客管理',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'block' } // 複数行は選択可能です
}
gridTableObj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
gridTableObj.colModel = [
  { title: '仲介業者コード', dataType: 'string', dataIndx: 'brokerCode', width: '110px', halign: 'center', align: 'center' },
  { title: '仲介業者名', dataType: 'string', dataIndx: 'brokerName', width: '200px', halign: 'center', align: 'left' },
  { title: '部店', dataType: 'string', dataIndx: 'butenCode', width: '70px', halign: 'center', align: 'center' },
  { title: '口座番号', dataType: 'string', dataIndx: 'accountNumber', width: '90px', halign: 'center', align: 'left' },
  { title: '取引コース', dataType: 'string', dataIndx: 'customerAttributeName', width: '140', halign: 'center', align: 'left',
    // 手続状況は”2:承認”以外の場合、空値を表示する
    render: function(ui) {
      if (ui.rowData.editStatus && ui.rowData.editStatus === '2') {
        return ui.rowData.customerAttributeName
      } else {
        return '-'
      }
    }
  },
  { title: '顧客名(漢字)', dataType: 'string', dataIndx: 'nameKanji', width: '200px', halign: 'center', align: 'left',
    // 手続状況は”2:承認”以外の場合、空値を表示する
    render: function(ui) {
      if (ui.rowData.editStatus && ui.rowData.editStatus === '2' && ui.rowData.nameKanji) {
        // リスト作成
        return changeNameKanjiColor(ui.rowData.nameKanji, 'blue')
      } else {
        return '-'
      }
    }
  },
  { title: '共募支店コード', dataType: 'string', dataIndx: 'jointBranchCode', width: '120px', halign: 'center', align: 'center' },
  { title: '共募支店名', dataType: 'string', dataIndx: 'jointBranchName', width: '220px', halign: 'center', align: 'left' },
  { title: '契約締結日', dataType: 'string', dataIndx: 'contractDate', width: '100px', halign: 'center', align: 'left',
    render: function(ui) {
      if (ui.rowData.contractDate) {
        return getFormattedDateValue(ui.rowData.contractDate, 'date8')
      } else {
        return '-'
      }
    }
  },
  { title: '同意日', dataType: 'string', dataIndx: 'startDate', width: '120px', halign: 'center', align: 'left',
    render: function(ui) {
      if (ui.rowData.startDate) {
        return getFormattedDateValue(ui.rowData.startDate, 'date8')
      } else {
        return '-'
      }
    }
  },
  { title: '終了日', dataType: 'string', dataIndx: 'endDate', width: '120px', halign: 'center', align: 'left',
    render: function(ui) {
      if (ui.rowData.endDate) {
        return getFormattedDateValue(ui.rowData.endDate, 'date8')
      } else {
        return '-'
      }
    }
  },
  { title: '支払率', dataType: 'string', dataIndx: 'jointRewardRate', width: '120px', halign: 'center', align: 'right',
    render: function(ui) {
      if (ui.rowData.jointRewardRate) {
        return ifaFormatUtils.noneWithCommaNoneZeroPadding(ui.rowData.jointRewardRate)
      } else {
        return '-'
      }
    }
  },
  { title: '手続状況', dataType: 'string', dataIndx: 'editStatusName', width: '120px', halign: 'center', align: 'left' },
  { title: '営業員コード', dataType: 'string', dataIndx: 'brokerChargeCode', width: '100px', halign: 'center', align: 'center' },
  { title: '営業員名', dataType: 'string', dataIndx: 'brokerChargeName', width: '250px', halign: 'center', align: 'left' },
  { title: '自動解約理由', dataType: 'string', dataIndx: 'autoCancellationReason', width: '250px', halign: 'center', align: 'left', hidden: true,
    render: function(ui) {
      if (ui.rowData.autoCancellationReason) {
        return getCodeValue('JOINT_USER_REASON_CODE', 1, ui.rowData.autoCancellationReason)
      } else {
        return '-'
      }
    }
  }
]

function changeNameKanjiColor(val, color) {
  let result = "<span style='color:"
  result += color
  result += '; border-bottom:solid 1px '
  result += color
  result += ";'>"
  result += val
  result += '</span>'
  return result
}
</script>
<style lang="scss" scoped>
:deep(.search-form__item) {
  width: 180px;
}
.content-card {
  margin: 0.5rem 1rem;
}
.gridButtonArea {
  margin: 0.2rem 0.1rem 0.7rem 0rem;
}
:deep(.el-form-item) {
    margin-bottom: 1rem;
    margin-right: 2rem;
}
:deep(.el-form-item__label) {
  margin-right: 1rem;
  padding-right: 0px;
}
.swithArea {
  padding-left: 50px;
  margin-bottom: 20px;
  margin-top: 15px;
}
:deep(.el-switch__label) * {
  font-weight: bold;
}
.search_btn-area {
  padding: 10px 10px 13px 46px;
}
.ifa-search__align-items {
  display: flex;
  flex-wrap: wrap;
  align-items: top;
}
:deep(.el-form-item__error) {
  display: flex;
  word-break: keep-all;
  white-space: nowrap;
  overflow-wrap: break-word;
}
</style>
