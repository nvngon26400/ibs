<template>
  <div> <!-- 画面：SUB0513_01-01 共同募集契約マスタ -->
    <screen-title :text="form.pageTitle.name"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
    >
      <el-row>
        <el-card
          class="content-card"
          shadow="always"
          style="width: 100%;"
        >
          <div class="filter-container">
            <!-- ここに検索条件用のコンテンツを記述する -->
            <el-row style="margin-bottom:1rem; align-items: center;">
              <ifa-input-text
                id="brokerCode"
                v-model="form.brokerCode"
                input-class="broker-code-input"
                label="仲介業者コード"
                prop="brokerCode"
                size="small"
                name="brokerCode"
                :domain="IfaBrokerCodeDomainModel"
              ></ifa-input-text>
              <div style="margin-left: 92px;"></div>
              <ifa-input-text
                id="brokerName"
                v-model="form.brokerName"
                input-class="broker-name-input"
                label="仲介業者名"
                prop="brokerName"
                size="small"
                name="brokerName"
                original-screen-id="SUB0513_01-01"
                :domain="IfaBrokerNameDomainModel"
              ></ifa-input-text>
              <div style="margin-left: 0.5rem">（部分検索）</div>
            </el-row>
            <el-row>
              <ifa-input-radio
                v-model="form.jointContract"
                label="共募契約"
                code-list-id="original"
                :original-list="jointContractList"
                input-class="form_label"
                prop="jointContract"
                request
                :required="true"
                :disabled="false"
              ></ifa-input-radio>
            </el-row>
          </div>
          <div
            class="form_button"
            style="margin-top: 1rem"
          >
            <ifa-button
              id="allBrokerNameDisplay"
              text="全仲介業者名表示"
              msg-title="共同募集契約マスタ"
              :search="true"
              small
              :request-model="A002RequestModel"
              :form="formRef"
              action-type="requestAction"
              action-id="SUB0513_01-01#A002"
              :pre-request-handler="A002PreRequestHandler"
              @response-handler="A002ResponseHandler($event)"
              @response-error-handler="errorHandler($event)"
            ></ifa-button>
            <ifa-button
              id="searchDisplay"
              text="表示"
              msg-title="共同募集契約マスタ"
              :search="true"
              width="90"
              small
              :request-model="A003RequestModel"
              :form="formRef"
              action-type="requestAction"
              action-id="SUB0513_01-01#A003"
              :pre-request-handler="A003PreRequestHandler"
              @response-handler="A003ResponseHandler($event)"
              @response-error-handler="errorHandler($event)"
            ></ifa-button>
          </div>
        </el-card>
      </el-row>
      <el-row>
        <el-card
          class="content-card"
          style="width: 100%;"
        >
          <!-- 操作部エリア -->
          <el-row>
            <div class="gridButtonArea">
              <ifa-button
                id="btnNewRegister"
                name="btnNewRegister"
                text="新規登録"
                action-type="originalAction"
                small
                @app-action-handler="A004NewRegisterHandler"
              ></ifa-button>
            </div>
          </el-row>
          <!-- 一覧表示部エリア -->
          <el-row>
            <grid-table
              ref="ifaJointContractMasterGridTable"
              class="ifaJointContractMasterGridTable"
              :options="pqGridOption"
              :auto-refresh="false"
              @click="handleClick"
            ></grid-table>
          </el-row>
        </el-card>
      </el-row>
    </el-form>
    <!-- 変更 ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="updateConfirmVisible"
      title="変更の確認"
      message="仲介業者の共募契約を変更します。よろしいですか？"
      @close-modal-ok="updateCloseModalOk"
      @close-modal-cancel="updateConfirmVisible = false"
    ></ifa-ok-cancel-dialog>
    <!-- 削除 ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="cancelConfirmVisible"
      title="削除の確認"
      message="共同募集契約マスタを削除します。よろしいですか？"
      @close-modal-ok="deleteCloseModalOk"
      @close-modal-cancel="cancelConfirmVisible = false"
    ></ifa-ok-cancel-dialog>
    <!-- A002　全仲介業者名表示ボタン押下 -->
    <ifa-requester
      id="displayA002"
      action-id="SUB0513_01-01#A002"
      msg-title="共同募集契約マスタ"
      action-type="requestAction"
      :request-model="A002RequestModel"
      :pre-request-handler="A002PreRequestHandler"
      @response-handler="A002ResponseHandler($event)"
    ></ifa-requester>
    <!-- A004 新規登録画面のポップアップ -->
    <ifa-joint-contract-master-new-register
      ref="IfaJointContractMasterNewRegister"
      :is-visible="infoRegistrationVisible"
      @close-modal="infoRegistrationVisible = false"
      @joint-contract-registered="onJointContractMasterNewRegistered"
      @joint-contract-registered-error="onJointContractMasterNewRegisteredError"
    ></ifa-joint-contract-master-new-register>
    <!-- A006　変更確認 のOKボタン押下 -->
    <ifa-requester
      id="updateA006"
      action-id="SUB0513_01-01#A006"
      msg-title="共同募集契約マスタ"
      action-type="requestAction"
      :request-model="A006RequestModel"
      @response-handler="A006ResponseHandlerUpdate($event)"
      @response-error-handler="errorHandlerUpdate($event)"
    ></ifa-requester>
    <!-- A008　削除確認 のOKボタン押下 -->
    <ifa-requester
      id="deleteA008"
      action-id="SUB0513_01-01#A008"
      msg-title="共同募集契約マスタ"
      action-type="requestAction"
      :request-model="A008RequestModel"
      @response-handler="A008ResponseHandlerDelete($event)"
      @response-error-handler="errorHandlerUpdate($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import calculateTableHeight from '@/utils/calculateTableHeight.js'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction.js'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaBrokerCodeDomainModel from '@/resource/domain/IfaBrokerCodeDomainModel.json'
import IfaBrokerNameDomainModel from '@/resource/domain/IfaBrokerNameDomainModel.json'
import IfaJointContractMasterNewRegister from './IfaJointContractMasterNewRegister.vue'
import { IfaJointContractMasterFormModel } from './js/IfaJointContractMasterFormModel'
import { IfaJointContractMasterA002RequestModel } from './js/IfaJointContractMasterA002RequestModel'
import { IfaJointContractMasterA003RequestModel } from './js/IfaJointContractMasterA003RequestModel'
import { IfaJointContractMasterA006RequestModel } from './js/IfaJointContractMasterA006RequestModel'
import { IfaJointContractMasterA008RequestModel } from './js/IfaJointContractMasterA008RequestModel'

export default {
  components: {
    GridTable,
    screenTitle,
    IfaOkCancelDialog,
    IfaJointContractMasterNewRegister
  },
  data() {
    return {
      IfaBrokerCodeDomainModel: IfaBrokerCodeDomainModel,
      IfaBrokerNameDomainModel: IfaBrokerNameDomainModel,
      form: new IfaJointContractMasterFormModel(),
      formRef: {},
      pqGridOption: getConvertedOption(obj),
      // 共募契約 区分値
      jointContractList: [
        { key: ' ', value: '全て' },
        { key: '1', value: '契約' },
        { key: '0', value: '未契約' }
      ],
      // パラメータ
      detailParam: {
        // 一覧.仲介業者コード
        brokerCodeParam: ''
      },
      infoRegistrationVisible: false,
      // 削除確認ダイアログ
      cancelConfirmVisible: false,
      // 変更確認ダイアログ
      updateConfirmVisible: false,
      // 新規登録ダイアログ
      isRegisterVisible: false
    }
  },
  computed: {
    A002RequestModel() {
      return new IfaJointContractMasterA002RequestModel(this.form)
    },
    A003RequestModel() {
      return new IfaJointContractMasterA003RequestModel(this.form)
    },
    A006RequestModel() {
      return new IfaJointContractMasterA006RequestModel(this.detailParam)
    },
    A008RequestModel() {
      return new IfaJointContractMasterA008RequestModel(this.detailParam)
    }
  },
  created() {
    this.pqGridOption.wrap = true
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
  },
  methods: {
    onShow() {
      this.pqGridOption.dataModel.data = []
      this.$refs['ifaJointContractMasterGridTable'].refreshView()
      this.form = new IfaJointContractMasterFormModel()
      this.form.jointContract = ' ' // 共募契約:【初期値】全て
      this.$nextTick(() => {
        this.formRef = this.$refs.form
      })
    },
    A002PreRequestHandler() {
      this.pqGridOption.dataModel.data = []
    },
    // 全仲介業者名表示ボタンの処理
    A002ResponseHandler(response) {
      // 仲介業者コードをクリアする
      this.form.brokerCode = ''
      // 仲介業者名をクリアする
      this.form.brokerName = ''
      // 画面一覧を表示する
      this.pqGridOption.dataModel.data = response.dataList
      this.pqGridOption.maxHeight = calculateTableHeight.getDynamicTableHeight()
      this.$refs['ifaJointContractMasterGridTable'].refreshView(true)
    },
    A003PreRequestHandler() {
      this.pqGridOption.dataModel.data = []
    },
    // 表示ボタンの処理
    A003ResponseHandler(response) {
      // 画面一覧を表示する
      this.pqGridOption.dataModel.data = response.dataList
      this.pqGridOption.maxHeight = calculateTableHeight.getDynamicTableHeight()
      this.$refs['ifaJointContractMasterGridTable'].refreshView(true)
    },
    A004NewRegisterHandler(response) {
      // 新規登録　表示フラグ
      this.infoRegistrationVisible = true
    },
    onJointContractMasterNewRegistered(value) {
      // 新規登録　表示フラグ
      this.infoRegistrationVisible = false
      // 画面再検索
      document.getElementById('searchDisplay').click()
    },
    // 新規登録　異常
    onJointContractMasterNewRegisteredError(value) {
      // 新規登録　表示フラグ
      this.infoRegistrationVisible = false
    },
    // 変更確認
    updateCloseModalOk() {
      this.$nextTick(() => {
        document.getElementById('updateA006').click()
      })
    },
    // 変更確認のレスポンス
    A006ResponseHandlerUpdate(response) {
      // 確認ダイアログ表示フラグ
      this.updateConfirmVisible = false

      // 画面再検索
      document.getElementById('searchDisplay').click()
    },
    // 削除確認
    deleteCloseModalOk() {
      this.$nextTick(() => {
        document.getElementById('deleteA008').click()
      })
    },
    // 削除確認のレスポンス
    A008ResponseHandlerDelete(response) {
      // 確認ダイアログ表示フラグ
      this.cancelConfirmVisible = false
      // 画面再検索
      document.getElementById('searchDisplay').click()
    },
    // 一覧選択
    handleClick(row) {
      if (row.dataIndx === 'delete') {
        // 確認ダイアログ表示フラグ
        this.cancelConfirmVisible = true
        // 一覧.仲介業者コード
        this.detailParam.brokerCodeParam = row.rowData.brokerCode
      } else if (row.dataIndx === 'update') {
        // 確認ダイアログ表示フラグ
        this.updateConfirmVisible = true
        // 一覧.仲介業者コード
        this.detailParam.brokerCodeParam = row.rowData.brokerCode
      }
    },
    // 一覧検索　処理異常の場合
    errorHandler(response) {
      if (response.errorLevel === -1) {
        // 一覧初期化
        this.pqGridOption.dataModel.data = []
        this.$refs['ifaJointContractMasterGridTable'].refreshView()
      }
    },
    // 削除／変更　処理異常の場合
    errorHandlerUpdate(response) {
      if (response.errorLevel === -1) {
        // 削除確認ダイアログ
        this.cancelConfirmVisible = false
        // 変更確認ダイアログ
        this.updateConfirmVisible = false
      }
    }
  }
}
const changeColorBorderBottom = (item) => {
  return `<span class='grid-link'><a>` + item + `</a></span> 
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
}

const obj = {
  width: 0,
  height: 0,
  title: '共同募集契約マスタ',
  dataIndx: 'detail',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false,
  pageModel: {
    type: 'local',
    curPage: 1,
    rPP: 30,
    rPPOptions: []
  }
}

obj.colModel = [
  {
    title: '仲介業者コード',
    dataType: 'string',
    dataIndx: 'brokerCode',
    width: '200',
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataType: 'string',
    dataIndx: 'brokerName',
    width: '800',
    editable: false,
    halign: 'center',
    align: 'left'
  },
  {
    title: '共募契約',
    dataType: 'string',
    dataIndx: 'contractStatus',
    Width: '200',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const grid = this
      const data = ui.rowData.contractStatus
      if (data) {
        if (data === '0') {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color' })
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: '__bold' })
        }
        return getCodeValue('JOINT_CONTRACT_STATUS', 1, data)
      } else {
        return '-'
      }
    }
  },
  {
    title: '変更',
    dataType: 'string',
    dataIndx: 'update',
    Width: '200',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      return changeColorBorderBottom('変更')
    }
  },
  {
    title: '削除',
    dataType: 'string',
    dataIndx: 'delete',
    Width: '200',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      return changeColorBorderBottom('削除')
    }
  }
]

</script>

<style>
.filter-container {
  margin-top: 1rem;
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

.el-icon-info {
  font-size: 30px;
}

.broker-code-input {
  width: 150px;
}
.broker-name-input {
  width: 280px;
}
.__bold {
  font-weight: bold;
}
.font-color {
  font-weight: bold;
  color: red;
}
</style>
