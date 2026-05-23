<template>
  <!-- 画面名：出金入力 SUB0202_0601-01 -->
  <div>
    <screen-title :text="inputForm.title"></screen-title>
    <div class="caption_card">
      <!-- 振込先銀行口座エリア -->
      <el-card class="bank_card">
        <el-row class="card_row">
          <grid-table ref="gridTableByAccount"
                      :options="pqGridOptionByAccount"
                      :auto-refresh="false"
                      class="grid_table_class"
          ></grid-table>
        </el-row>
      </el-card>
      <!-- 出金入力エリア -->
      <el-card class="input_card">
        <div>
          <el-form
            ref="inputForm"
            :model="inputForm"
            label-width="150px"
            label-position="left"
            class="form-main"
            style="padding-top: 1rem;"
          >
            <el-row>
              <el-col class="clear_class"
                      :span="24"
              >
                <ifa-button
                  id="btnClear"
                  text="リセット"
                  color="secondary"
                  action-type="originalAction"
                  @app-action-handler="handleClear"
                ></ifa-button>
              </el-col>
            </el-row>
            <el-row>
              <el-col class="error_width_class"
                      :span="20"
                      :offset="2"
              >
                <div style="display: inline-flex;">
                  <ifa-date-picker
                    v-model="inputForm.payDate"
                    prop="payDate"
                    label="出金日"
                    date-class="pay_date_class"
                    required
                    blur-check="true"
                    placeholder="blank"
                    @input="handleInputPayDate($event)"
                  ></ifa-date-picker>
                  <span
                    v-if="isPayMentShow"
                    class="payment_day"
                  >入力可能日：{{ $_out(inputForm.paymentDayFrom) }} ～ {{ $_out(inputForm.paymentDayTo) }}</span>
                </div>
              </el-col>
            </el-row>
            <el-row class="ac_balance_row">
              <el-col :span="10"
                      :offset="2"
                      style="margin-bottom: 10px"
              >
                <span class="ac_balance_label">出金可能額</span>
                <span class="ac_balance_content">{{ $_withCommaInteger(inputForm.acBalance) }}<span style="font-size: 15px;"> 円</span></span>
              </el-col>
            </el-row>
            <el-row>
              <el-col class="error_width_class"
                      :span="10"
                      :offset="2"
              >
                <ifa-input-amount
                  v-model="inputForm.payAmount"
                  prop="payAmount"
                  :min="1"
                  :support="false"
                  label="出金額"
                  unit="円"
                  required
                  :domain="IfaYen120DomainModel"
                  input-class="pay_amount_class"
                ></ifa-input-amount>
              </el-col>
            </el-row>
            <el-row class="row_top">
              <el-col class="button_bottom"
                      :span="2"
              >
                <ifa-button
                  :form="formRef"
                  text="出金確認"
                  action-id="SUB0202_0601-01#A003"
                  action-type="requestAction"
                  :request-model="A003RequestModel"
                  @response-handler="drawalsConfirmA003($event)"
                ></ifa-button>
                <button
                  id="drawals-cancel-button"
                  type="button"
                  value=""
                  hidden="true"
                  @click="handleDrawalsCancel"
                ></button>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </el-card>
    </div>
    <!-- 出金明細一覧エリア -->
    <screen-title :text="listForm.title"></screen-title>
    <div class="caption_card">
      <el-card class="withdraw_card">
        <el-row class="card_row">
          <grid-table
            ref="gridTableByDrawals"
            :options="pqGridOptionByDrawals"
            :auto-refresh="false"
            class="grid_table_class"
          ></grid-table>
        </el-row>
      </el-card>
    </div>
    <!-- 出金確認画面ダイアログ -->
    <ifa-withdraw-execute-confirm
      ref="ifaWithdrawExecuteConfirm"
      :is-visible="dialogExecuteVisible"
      :form-data="confirmData"
      :customer-info="customerInfo"
      :append-to-body="true"
      @close-modal="handleCloseModal"
      @drawals-finish="handleDrawalsFinish"
    ></ifa-withdraw-execute-confirm>
    <!-- 出金取消確認画面ダイアログ -->
    <ifa-withdraw-cancel-confirm
      ref="ifaWithdrawCancelConfirm"
      :is-visible="dialogCancelVisible"
      :form-data="cancelConfirmData"
      :customer-info="customerInfo"
      :append-to-body="true"
      @close-modal="handleCloseModal"
      @cancel-finish="handleCancelFinish"
    ></ifa-withdraw-cancel-confirm>
    <!-- 出金完了画面ダイアログ -->
    <ifa-withdraw-accept-complete
      ref="ifaWithdrawAcceptComplete"
      :is-visible="dialogFinish"
      :form-data="confirmData"
      :customer-info="customerInfo"
      :append-to-body="true"
      @close-modal="handleCloseModal"
      @move-to-drawals-list="handleMoveToDrawals"
    ></ifa-withdraw-accept-complete>
    <!-- 出金取消完了画面ダイアログ -->
    <ifa-withdraw-cancel-complete
      ref="ifaWithdrawCancelComplete"
      :is-visible="dialogCancelFinish"
      :form-data="confirmData"
      :customer-info="customerInfo"
      :append-to-body="true"
      @close-modal="handleCloseModal"
      @move-to-drawals-list="handleMoveToDrawals"
    ></ifa-withdraw-cancel-complete>
    <!-- 初期化リクエスト -->
    <ifa-requester
      id="ifaWithdrawInputInitializeA001"
      action-id="SUB0202_0601-01#A001"
      action-type="requestAction"
      :request-model="A001RequestModel"
      @response-handler="handlerInitializeA001($event)"
      @response-error-handler="errorInitializeHandler($event)"
    ></ifa-requester>
    <!-- 出金可能額表示リクエスト -->
    <ifa-requester
      id="ifaWithdrawInputGetAcBalanceA002"
      action-id="SUB0202_0601-01#A002"
      action-type="requestAction"
      :request-model="A002RequestModel"
      @response-handler="handlerGetAcBalanceA002($event)"
    ></ifa-requester>
    <!-- 出金取消確認リクエスト -->
    <ifa-requester
      id="ifaWithdrawCancelCheckA004"
      action-id="SUB0202_0601-01#A004"
      action-type="requestAction"
      @response-handler="handlerCancelCheckA004($event)"
    ></ifa-requester>

  </div>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import GridTable from '@/components/GridTable'
import IfaYen120DomainModel from '@/resource/domain/IfaYen120DomainModel.json'
import IfaWithdrawExecuteConfirm from './IfaWithdrawExecuteConfirm'
import IfaWithdrawAcceptComplete from './IfaWithdrawAcceptComplete'
import IfaWithdrawCancelConfirm from './IfaWithdrawCancelConfirm'
import IfaWithdrawCancelComplete from './IfaWithdrawCancelComplete'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import ifaUtils from '@/utils/ifaUtils.js'
import { IfaWithdrawInputFormModel } from './js/IfaWithdrawInputFormModel'
import { IfaWithdrawListFormModel } from './js/IfaWithdrawListFormModel'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { IfaWithdrawInputA001RequestModel } from './js/IfaWithdrawInputA001RequestModel'
import { IfaWithdrawInputA002RequestModel } from './js/IfaWithdrawInputA002RequestModel'
import { IfaWithdrawInputA003RequestModel } from './js/IfaWithdrawInputA003RequestModel'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'

export default {
  components: {
    screenTitle,
    GridTable,
    IfaWithdrawExecuteConfirm,
    IfaWithdrawAcceptComplete,
    IfaWithdrawCancelConfirm,
    IfaWithdrawCancelComplete
  },
  emits: ['initializeError'],
  data() {
    return {
      inputForm: new IfaWithdrawInputFormModel(),
      listForm: new IfaWithdrawListFormModel(),
      A001RequestModel: new IfaWithdrawInputA001RequestModel(),
      IfaYen120DomainModel: IfaYen120DomainModel,
      pqGridOptionByAccount: getConvertedOption(objAccount),
      pqGridOptionByDrawals: getConvertedOption(objDrawals),
      formRef: {},
      dialogExecuteVisible: false,
      dialogFinish: false,
      dialogCancelVisible: false,
      dialogCancelFinish: false,
      confirmData: {},
      cancelConfirmData: {},
      minPayDate: '',
      isPayMentShow: false
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    A002RequestModel() {
      return new IfaWithdrawInputA002RequestModel(this.inputForm)
    },
    A003RequestModel() {
      return new IfaWithdrawInputA003RequestModel(this.inputForm)
    }
  },
  mounted() {
    this.formRef = this.$refs['inputForm']
  },
  created() {
    this.pqGridOptionByAccount.scrollModel = {
      autoFit: true,
      horizontal: true
    }
    this.pqGridOptionByDrawals.scrollModel = {
      autoFit: true,
      horizontal: true
    }
  },
  methods: {
    // 出金入力は初期表示する時のリクエスト
    onShow(resume) {
      this.isPayMentShow = false
      this.inputForm = new IfaWithdrawInputFormModel()
      this.A001RequestModel = new IfaWithdrawInputA001RequestModel()
      this.handleClear()
      this.$nextTick(() => {
        document.getElementById('ifaWithdrawInputInitializeA001').click()
      })
    },
    // 出金入力は初期表示する時のリスポンス
    handlerInitializeA001(response) {
      const res = response.dataList[0]
      // 出金可能日From
      this.inputForm.paymentDayFrom = res?.paymentDayFrom
      // 出金可能日To
      this.inputForm.paymentDayTo = res?.paymentDayTo
      this.isPayMentShow = false
      if (this.inputForm.paymentDayFrom && this.inputForm.paymentDayTo) {
        this.isPayMentShow = true
      }
      // 振込先銀行口座
      this.pqGridOptionByAccount.dataModel.data = res?.bankAccountInfo
      this.$refs['gridTableByAccount'].refreshView()
      // 出金明細一覧
      this.pqGridOptionByDrawals.dataModel.data = res?.payEstimateData
      this.$refs['gridTableByDrawals'].refreshView()
      // 完了から出金に遷移する時、出金可能額は再取得
      this.inputForm.acBalance = res?.acBalance
    },
    // 出金日はロストフォーカスする時のリクエスト
    handleInputPayDate(value) {
      this.inputForm.acBalance = ''
      if (value) {
        this.$nextTick(() => {
          document.getElementById('ifaWithdrawInputGetAcBalanceA002').click()
        })
      }
    },
    // 出金日はロストフォーカスする時のリスポンス
    handlerGetAcBalanceA002(response) {
      this.inputForm.acBalance = response.dataList[0]?.acBalance
    },
    getBankName() {
      if (this.pqGridOptionByAccount.dataModel.data.length) {
        return this.pqGridOptionByAccount.dataModel.data[0].bankKanji
      } else {
        return ''
      }
    },
    getBankInfo() {
      let bankInfo = ''
      const tempIfo = this.pqGridOptionByAccount.dataModel.data
      if (tempIfo.length) {
        let deposit = ''
        if (tempIfo[0].bankDepositClass === '1') {
          deposit = '普通'
        } else if (tempIfo[0].bankDepositClass === '2') {
          deposit = '当座'
        } else if (tempIfo[0].bankDepositClass === '4') {
          deposit = '貯蓄'
        } else if (tempIfo[0].bankDepositClass === '9') {
          deposit = 'その他'
        } else {
          deposit = '-'
        }
        let bankBranchKanji = tempIfo[0].bankBranchKanji
        if (bankBranchKanji === null || bankBranchKanji === undefined || bankBranchKanji === '') {
          bankBranchKanji = '-'
        }
        let bankAccount = tempIfo[0].bankAccount
        if (bankAccount === null || bankAccount === undefined || bankAccount === '') {
          bankAccount = '-'
        } else {
          bankAccount = this.$_zeroPadding(bankAccount)
        }
        bankInfo = bankBranchKanji + '　' + deposit + '　' + bankAccount
      } else {
        bankInfo = ''
      }
      return bankInfo
    },
    // 出金確認画面に遷移する時のリスポンス
    async drawalsConfirmA003(response) {
      const res = response.dataList[0]
      this.confirmData.payDate = this.inputForm.payDate
      this.confirmData.payAmount = this.inputForm.payAmount
      this.confirmData.acBalance = res?.acBalance
      this.confirmData.acBalanceAfter = res?.acBalanceAfter
      this.confirmData.bankKanji = this.getBankName()
      this.confirmData.bankInfo = this.getBankInfo()
      this.confirmData.noticeInfoAlert = res?.noticeInfoAlert
      this.confirmData.noticeAlert = res?.noticeAlert
      await this.$nextTick()
      await this.$refs['ifaWithdrawExecuteConfirm'].onShow()
      this.dialogExecuteVisible = true
    },
    // 取消確認画面に遷移する時のリクエスト
    handleDrawalsCancel() {
      const cancelData = JSON.parse(unescape(document.getElementById('drawals-cancel-button').value))
      this.cancelConfirmData.payDate = cancelData.pay_date
      this.cancelConfirmData.payAmount = cancelData.pay_amount
      this.cancelConfirmData.rpNumber = cancelData.rp_number
      this.$nextTick(() => {
        document.getElementById('ifaWithdrawCancelCheckA004').click()
      })
    },
    // 取消確認画面に遷移する時のリスポンス
    async handlerCancelCheckA004(response) {
      const res = response.dataList[0]
      this.cancelConfirmData.bankKanji = this.getBankName()
      this.cancelConfirmData.bankInfo = this.getBankInfo()
      this.cancelConfirmData.noticeInfoAlert = res?.noticeInfoAlert
      this.cancelConfirmData.noticeAlert = res?.noticeAlert
      await this.$nextTick()
      await this.$refs['ifaWithdrawCancelConfirm'].onShow()
      this.dialogCancelVisible = true
    },
    // 出金完了画面に遷移する時のリスポンス
    async handleDrawalsFinish(response) {
      this.confirmData.payDate = this.inputForm.payDate
      this.confirmData.payAmount = this.inputForm.payAmount
      this.confirmData.acBalance = response.acBalance
      this.confirmData.acBalanceAfter = response.acBalanceAfter
      this.minPayDate = response.minPayDate
      this.confirmData.bankKanji = this.getBankName()
      this.confirmData.bankInfo = this.getBankInfo()
      this.dialogExecuteVisible = false
      await this.$nextTick()
      await this.$refs['ifaWithdrawAcceptComplete'].onShow()
      this.dialogFinish = true
    },
    // 出金取消完了画面に遷移する時のリスポンス
    async handleCancelFinish(response) {
      this.confirmData.payDate = this.cancelConfirmData.payDate
      this.confirmData.payAmount = this.cancelConfirmData.payAmount
      this.confirmData.acBalance = response.acBalance
      this.confirmData.acBalanceAfter = response.acBalanceAfter
      this.minPayDate = response.minPayDate
      this.confirmData.bankKanji = this.getBankName()
      this.confirmData.bankInfo = this.getBankInfo()
      this.dialogCancelVisible = false
      await this.$nextTick()
      await this.$refs['ifaWithdrawCancelComplete'].onShow()
      this.dialogCancelFinish = true
    },
    // 完了画面から、出金画面に遷移する時のリクエスト
    handleMoveToDrawals(minPayDate) {
      this.handleCloseModal()
      // 完了から出金に遷移する時、出金額クリア
      this.inputForm.payAmount = ''
      this.inputForm.acBalance = ''
      this.$refs['inputForm']?.clearValidate()
      this.inputForm.payDate = minPayDate
      this.A001RequestModel = new IfaWithdrawInputA001RequestModel(minPayDate)
      this.$nextTick(() => {
        document.getElementById('ifaWithdrawInputInitializeA001').click()
      })
    },
    // 初期化エラー
    errorInitializeHandler(response) {
      const errorInfo = {
        title: this.inputForm.title,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    // クリア
    handleClear() {
      this.minPayDate = ''
      this.$refs['inputForm']?.clearValidate()
      this.inputForm.payDate = ''
      this.inputForm.payAmount = ''
      this.inputForm.acBalance = ''
    },
    // 出金画面に遷移、ダイアログを閉じる
    handleCloseModal() {
      this.dialogExecuteVisible = false
      this.dialogFinish = false
      this.dialogCancelVisible = false
      this.dialogCancelFinish = false
    }
  }
}

const objAccount = {
  width: 0,
  height: 0,
  title: '振込先銀行口座',
  flexHeight: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  wrap: false,
  postRenderInterval: 100
}
objAccount.colModel = [
  { title: '振替区分', minWidth: 150, dataType: 'string', dataIndx: 'bankKbn', editable: false, halign: 'center', align: 'left',
    render: function(ui) { return '銀行振込(送金)' }
  },
  { title: '振込先金融機関', minWidth: 300, dataType: 'string', dataIndx: 'bankCode', editable: false, halign: 'center', align: 'left',
    render: function(ui) { return '（' + ifaUtils.nullToMinus(ui.rowData.bankCode) + '）' + ifaUtils.nullToMinus(ui.rowData.bankKanji) }
  },
  { title: '本店・支店', minWidth: 200, dataType: 'string', dataIndx: 'bankBranchCode', editable: false, halign: 'center', align: 'left',
    render: function(ui) { return '（' + ifaUtils.nullToMinus(ui.rowData.bankBranchCode) + '）' + ifaUtils.nullToMinus(ui.rowData.bankBranchKanji) }
  },
  { title: '預金種別', minWidth: 100, dataType: 'string', dataIndx: 'bankDepositClass', editable: false, halign: 'center', align: 'left',
    render: function(ui) {
      if (ui.rowData.bankDepositClass === '1') {
        return '普通'
      } else if (ui.rowData.bankDepositClass === '2') {
        return '当座'
      } else if (ui.rowData.bankDepositClass === '4') {
        return '貯蓄'
      } else if (ui.rowData.bankDepositClass === '9') {
        return 'その他'
      } else {
        return '-'
      }
    }
  },
  { title: '口座番号', minWidth: 150, dataType: 'string', dataIndx: 'bankAccount', editable: false, halign: 'center', align: 'left',
    render: function(ui) { return ui.rowData.bankAccount ? ifaFormatUtils.zeroPadding(ui.rowData.bankAccount, 7) : '-' }
  },
  { title: '口座名義人', minWidth: 200, dataType: 'string', dataIndx: 'bankAccountName', editable: false, halign: 'center', align: 'left'
  }
]
objAccount.pageModel = {}

const objDrawals = {
  width: 0,
  height: 0,
  title: '出金明細一覧（顧客勘定からの出金）',
  flexHeight: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  wrap: false,
  postRenderInterval: 100
}
objDrawals.colModel = [
  { title: 'EC No.', minWidth: 200, dataType: 'string', dataIndx: 'rp_number', editable: false, halign: 'center', align: 'left', hidden: true
  },
  { title: '出金日', minWidth: 100, dataType: 'string', dataIndx: 'pay_date', editable: false, halign: 'center', align: 'left',
    render: function(ui) { return ui.rowData.pay_date ? getFormattedDateValue(ui.rowData.pay_date) : '-' }
  },
  { title: '金額', minWidth: 200, dataType: 'string', dataIndx: 'pay_amount', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.pay_amount ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.pay_amount) + ' 円' : '-' }
  },
  { title: '取消', minWidth: 100, dataType: 'string', dataIndx: 'cancel_id', editable: false, halign: 'center', align: 'center',
    render: function(ui) {
      if (ui.rowData.cancel_id === '1') {
        const v = JSON.stringify(ui.rowData)
        return "<button type='button' class='el-button ifa-button el-button--default el-button--mini secondary-class' onclick='const btn = document.getElementById(\"drawals-cancel-button\"); btn.value = \"" + escape(v) + "\"; btn.click()'><span class='__adjust_button_text'>取消</span></button>"
      } else if (ui.rowData.cancel_id === '2') {
        return '取消済'
      } else {
        return "<div style='color: red;'>取消不可</div>"
      }
    }
  }
]
objDrawals.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}

</script>

<style lang="scss" scoped>
:deep(.grid_table_class .pq-grid-cont-outer .pq-grid-row) {
  height: 40px;
}
:deep(.pay_date_class) {
  width: 203px !important;
}
:deep(.pay_date_class.el-input input) {
  text-align: center;
}
:deep(.pay_amount_class.el-input input) {
  width: 160px;
}
:deep(.error_width_class .el-form-item__content .el-form-item__error) {
  width: 210px !important;
}
.caption_card {
  margin: 0.5rem;
}
.bank_card {
  background-color: #eee;
  margin-bottom: 0.5rem;
  padding: 0rem 1rem 0rem 1rem;
}
.withdraw_card {
  margin-bottom: 0.5rem;
  padding: 0 1rem 0 1rem;
}
.card_row {
  padding: 0.5rem 0rem;
}
.clear_class {
  text-align: right;
  margin-bottom: 1rem;
}
.row_top {
  margin-top: 5px;
}
.ac_balance_row {
  margin-top: 3px;
}
.ac_balance_label {
  font-weight: bold;
  margin-left: 18px;
}
.ac_balance_content {
  display: inline-block;
  width: 232px;
  text-align: right;
  font-size: 16px;
  margin-top: 5px;
  margin-left: 38px;
}
.button_bottom {
  margin-bottom: 1rem;
}
.input_card {
  background-color: #fef0f0;
  padding: 0 1rem 0 1rem;
}
.__adjust_button_text {
  height: 24px;
  line-height: 16px !important;
  white-space: nowrap;
}
.payment_day{
  line-height: 2.5;
  padding-left: 30px;
}
</style>
