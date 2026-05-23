<template>
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div>
      <el-row>
        <el-card class="content-card"
                    shadow="always">
          <el-form
            id="IfaForeignBondBuyAmountInputListForm"
            name="IfaForeignBondBuyAmountInputListForm"
          >
            <el-row class="btn_area">
              <ifa-button
                id="btnNewRegister"
                name="btnNewRegister"
                text="新規登録"
                color="primary"
                width="110"
                small
                action-type="originalAction"
                @app-action-handler="newRegisterA002()"
              ></ifa-button>
              <ifa-button
                id="btnDelete"
                name="btnDelete"
                text="削除"
                color="seconday"
                width="90"
                small
                :disabled="!isTableRowSelected"
                action-type="originalAction"
                @app-action-handler="deleteDialog.visible = true"
              ></ifa-button>
            </el-row>
          </el-form>
          <grid-table
            id="ipopoGridTable"
            ref="ipopoGridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </el-row>
    </div>

    <ifa-foreign-bond-buy-amount-input-info-register
      ref="IfaForeignBondBuyAmountInputInfoRegister"
      :is-visible="foreignBondBuyAmountVisible"
      @close-modal="foreignBondBuyAmountVisible = false"
      @update-table="registerOk()"
    >
    </ifa-foreign-bond-buy-amount-input-info-register>

    <!-- A001 初期化 -->
    <ifa-requester
      id="IfaForeignBondBuyAmountInputListInitializeA001"
      action-type="requestAction"
      action-id="SUB0504_04-01#A001"
      @response-handler="initializeA001ResponseHandler($event)"
      @response-error-handler="initializeA001ResponseErrorHandler($event)"
    ></ifa-requester>
    <!-- 削除確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="deleteDialog.visible"
      :title="deleteDialog.title"
      :message="deleteDialog.message"
      @close-modal-ok="onDeleteConfirm"
      @close-modal-cancel="onDeleteCancel"
    ></ifa-ok-cancel-dialog>
    <!-- 削除時のリクエスト -->
    <ifa-requester
      id="IfaForeignBondBuyAmountInputDeleteA004"
      action-id="SUB0504_04-01#A004"
      action-type="requestAction"
      :request-model="IfaForeignBondBuyAmountInputDeleteA004RequestModel"
      @response-handler="deleteA004ResponseHandler"
      @response-error-handler="responseErrorHandlerA004($event)"
    >
    </ifa-requester>
  </div>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import GridTable from '@/components/GridTable'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { IfaForeignBondBuyAmountInputListFormModel } from './js/IfaForeignBondBuyAmountInputListFormModel'
import { IfaForeignBondBuyAmountInputDeleteA004RequestModel } from './js/IfaForeignBondBuyAmountInputDeleteA004RequestModel'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import IfaForeignBondBuyAmountInputInfoRegister from './IfaForeignBondBuyAmountInputInfoRegister'

export default {
  components: {
    GridTable,
    IfaForeignBondBuyAmountInputInfoRegister,
    screenTitle,
    IfaOkCancelDialog
  },
  emits: ['initializeError'],
  data() {
    return {
      foreignBondBuyAmountVisible: false,
      isTableRowSelected: false,
      pqGridOption: getConvertedOption(obj),
      form: new IfaForeignBondBuyAmountInputListFormModel(),
      deleteDialog: {
        visible: false,
        title: '外債買付代金入力情報削除',
        message: '外債買付代金入力情報を削除します。よろしいですか？'
      }
    }
  },
  computed: {
    IfaForeignBondBuyAmountInputDeleteA004RequestModel() {
      return new IfaForeignBondBuyAmountInputDeleteA004RequestModel(this.form)
    }
  },
  created() {
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
  },
  methods: {
    // 新規登録
    newRegisterA002() {
      this.foreignBondBuyAmountVisible = true
    },
    onShow() {
      document.getElementById('IfaForeignBondBuyAmountInputListInitializeA001').click()
    },
    // 一覧選択
    handleClick(ui) {
      this.isTableRowSelected = true
      this.form = { ...this.form, ...ui.rowData }
    },

    // A001(初期化) レスポンスハンドラー
    initializeA001ResponseHandler(event) {
      // ボタンの活性状態の初期化
      this.isTableRowSelected = false
      this.deleteDialog.visible = false
      // Grid Tableの初期化
      if (event.dataList[0]?.foreignBondBuyAmountInputList != null) {
        this.pqGridOption.dataModel.data = event.dataList[0].foreignBondBuyAmountInputList
      } else {
        this.pqGridOption.dataModel.data = []
      }

      this.$nextTick(() => {
        this.$refs['ipopoGridTable'].refreshView()
      })
    },

    // A001(初期化) レスポンスエラーハンドラー
    initializeA001ResponseErrorHandler(event) {
      const errorInfo = {
        title: this.form.title.name,
        message: event.message
      }
      this.$emit('initializeError', errorInfo)
    },
    // 削除
    onDeleteConfirm() {
      this.deleteDialog.visible = false
      this.$nextTick(() => {
        document.getElementById('IfaForeignBondBuyAmountInputDeleteA004').click()
      })
    },
    deleteA004ResponseHandler() {
      document.getElementById('IfaForeignBondBuyAmountInputListInitializeA001').click()
      this.deleteDialog.visible = false
    },
    onDeleteCancel() {
      this.deleteDialog.visible = false
    },
    registerOk() {
      this.$nextTick(() => {
        document.getElementById('IfaForeignBondBuyAmountInputListInitializeA001').click()
      })
    },
    responseErrorHandlerA004(event) {
      this.$nextTick(() => {
        document.getElementById('IfaForeignBondBuyAmountInputListInitializeA001').click()
      })
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '外債買付代金入力一覧',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  selectionModel: { type: 'row', mode: 'single' },
  topVisible: false,
  wrap: false
}
obj.colModel = [
  {
    title: '部店コード',
    dataIndx: 'butenCode',
    width: 100,
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '口座番号',
    dataIndx: 'accountNumber',
    width: 200,
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '顧客名',
    dataIndx: 'nameKanji',
    width: 200,
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '金額（USD）',
    dataIndx: 'amount',
    width: 260,
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      if (ui.rowData.amount) {
        const target = ui.rowData.amount
        return ifaFormatUtils.withCommaZeroPadding(target, 4)
      } else {
        return '-'
      }
    }
  },
  {
    title: '注文時間',
    dataIndx: 'orderTime',
    width: 200,
    editable: false,
    halign: 'center',
    align: 'center'
  },
  {
    title: '',
    dataIndx: 'baseDate',
    dataType: 'string',
    hidden: true
  },
  {
    title: '',
    dataIndx: 'ifaAccountSeqNo',
    dataType: 'string',
    hidden: true
  },
  {
    title: '',
    dataIndx: 'createTime',
    dataType: 'string',
    hidden: true
  },
  {
    title: '',
    dataIndx: 'updateTime',
    dataType: 'string',
    hidden: true
  },
  { title: '&nbsp;',
    dataType: 'string',
    dataIndx: 'space',
    halign: 'center',
    align: 'center',
    minWidth: 650,
    render: function(ui) {
      return ''
    }
  }
]
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
</script>

<style lang='scss' scoped>
.btn_area {
  margin: 0.5rem 0;
  padding: 0.5rem;
}
.content-card {
  margin: 0.5rem 1rem;
  padding: 0.2rem 0;
}
</style>
