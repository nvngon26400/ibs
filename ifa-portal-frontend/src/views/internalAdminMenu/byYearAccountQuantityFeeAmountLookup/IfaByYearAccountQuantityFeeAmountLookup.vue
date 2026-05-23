<template>
  <div>
    <screen-title :text="form.screenTitle.name"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      :rules="rules"
    >
      <el-card class="content-card">
        <div class="filter-container">
          <el-row>
            <el-col>
              <div style="display: flex; align-items: center; gap: 16px; margin-bottom: 0.8rem;">
                <div style="display: flex; gap: 16px; flex: 1;">
                  <div id="ifaInputWidth">
                    <ifa-input-text
                      v-if="brokerCodeMultipleInputsFlag"
                      id="brokerCode"
                      :model-value="form.brokerCode"
                      label="仲介業者コード"
                      prop="brokerCode"
                      size="small"
                      name="brokerCode"
                      :input-class="[
                        {
                          'input-readonly': brokerCodeReadonly
                        }
                      ]"
                      :domain="IfaBrokerCodeArrayDomainModel"
                      :tooltip-enabled="!form.brokerCode.length < 1"
                      :tooltip-content="form.brokerCode"
                      :control-auth-enabled="false"
                      tooltip-placement="bottom"
                      tooltip-effect="light"
                      :readonly="brokerCodeReadonly"
                      :rules="rules.brokerCode"
                      @blur="getClosingMonthA005"
                      @keydown="handleKeyDown"
                      @input="handleBrokerCodeModified"
                    ></ifa-input-text>
                    <ifa-input-text
                      v-else-if="!brokerCodeMultipleInputsFlag"
                      id="brokerCode"
                      v-model="form.brokerCode"
                      label="仲介業者コード"
                      prop="brokerCode"
                      type="text"
                      size="small"
                      name="brokerCode"
                      :input-class="[
                        {
                          'input-readonly': brokerCodeReadonly
                        }
                      ]"
                      :control-auth-enabled="false"
                      :domain="IfaBrokerCodeDomainModel"
                      :readonly="brokerCodeReadonly"
                      :rules="rules.brokerCode"
                      @blur="getClosingMonthA005"
                    ></ifa-input-text>
                  </div>
                  <div>
                    <div
                      style="line-height: 32px; width: 132px; display: flex; position: relative;"
                    >
                      <span class="_label">決算月</span>
                      <span style="margin: auto;"> {{ $_getCodeValue('CLOSING_MONTH', 1, form.closingMonth) ?? '-' }}</span>
                    </div>
                    <div v-if="form.errorMessage"
                         style="position: absolute; top: 2.3rem; color: red;"
                    >
                      <span>{{ message }}</span>
                    </div>
                  </div>
                  <div>
                    <ifa-button
                      :disabled="isDisabledButton"
                      text="決算月設定"
                      small
                      action-type="originalAction"
                      @app-action-handler="settleMonthSetting"
                    ></ifa-button>
                  </div>
                </div>
                <div style="margin-left: auto;">
                  <ifa-input-check
                    v-if="chkBrokerCodeExcludeIsVisible"
                    id="brokerCodeExclude"
                    v-model="form.chkBrokerCodeExclude"
                    code-list-id="original"
                    :original-list="brokerCodeExcludeOptions"
                  ></ifa-input-check>
                </div>
              </div>
            </el-col>
            <el-col>
              <ifa-input-select
                v-model="form.settleYear"
                label="決算年"
                size="small"
                required
                style="width: 180px; margin-bottom: 5px;"
                code-list-id="original"
                :original-list="settleYearList"
                :clearable="false"
              ></ifa-input-select>
            </el-col>
            <el-col
              style="margin: 0rem 0 0.5rem 9.5rem;"
            >
              <span>※「全て」を選択した場合、当決算年を含む最大5決算年分の情報を表示します。</span>
            </el-col>
          </el-row>
        </div>
        <div
          id="indicator-display"
          class="form_button"
        >
          <ifa-button
            id="btnDisplay"
            name="btnDisplay"
            text="表示"
            width="90"
            color="primary"
            :form="formRef"
            search
            small
            action-id="SUB0406-01#A002"
            action-type="requestAction"
            :request-model="IfaByYearAccountQuantityFeeAmountLookupA002RequestModel"
            :pre-request-handler="preRequestA002Handler"
            @response-handler="responseA002Handler($event)"
          ></ifa-button>
          <ifa-button
            id="btnTopInputClear"
            name="btnTopInputClear"
            text="クリア"
            width="90"
            color="secondary"
            small
            action-type="originalAction"
            @app-action-handler="clearSearchCondition"
          ></ifa-button>
        </div>
      </el-card>
    </el-form>
  </div>
  <el-row>
    <el-card class="content-card">
      <div class="grid_button_area">
        <el-row v-if="isDisabledButton">
          <el-col>
            <div style="display: flex; gap: 16px;">
              <div class="gridButtonArea"
                   style="display: flex; gap: 16px; flex: 1;"
              >
                <ifa-button
                  id="btnCsvDownload"
                  text="CSV出力"
                  width="90"
                  small
                  :disabled="csvBtnDisabled"
                  :form="formRef"
                  :request-model="IfaByYearAccountQuantityFeeAmountLookupA004RequestModel"
                  action-id="SUB0406-01#A004"
                  action-type="outputCsvAction"
                  csv-file-name="年度別口座数・報酬額"
                  :is-check-csv-download-privacy-confirmation="false"
                  :pre-request-handler="preRequestA004Handler"
                  @response-handler="responseHandlerA004($event)"
                  @response-error-handler="responseErrorHandlerA004($event)"
                ></ifa-button>
              </div>
              <div>
                <ifa-balloon-icon
                  v-if="checkComment"
                  class="right_icon"
                  icon-type="info"
                  placement="left"
                  :message="form.comment"
                ></ifa-balloon-icon>
              </div>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <grid-table
            ref="gridTable"
            :options="pqGridOption"
            :auto-refresh="false"
          ></grid-table>
        </el-row>
      </div>
    </el-card>
  </el-row>
  <ifa-broker-code-closing-month-login
    :is-visible="brokerCodeClosingMonthLoginisVisible"
    @close-modal="handleCloseModalbrokerCodeClosingMonthLogin"
    @update-modal="handleUpdateModalbrokerCodeClosingMonthLogin"
  >
  </ifa-broker-code-closing-month-login>

  <ifa-requester
    id="IfaByYearAccountQuantityFeeAmountLookupInitializeA001"
    action-id="SUB0406-01#A001"
    action-type="requestAction"
    @response-handler="responseHandlerInitializeA001($event)"
  ></ifa-requester>

  <ifa-requester
    id="getClosingMonthA005"
    action-id="SUB0406-01#A005"
    action-type="requestAction"
    :request-model="requestModelA005"
    @response-handler="responseHandlerGetClosingMonthA005($event)"
  ></ifa-requester>

  <button
    id="csv-download-button"
    hidden
    @click="csvHandler"
  ></button>

  <ifa-button
    id="btnCsvDownloadA007"
    hidden
    text="年度別口座数・報酬額照会（媒介口座明細）"
    action-id="SUB0406-01#A007"
    action-type="outputCsvAction"
    small
    :request-model="IfaByYearAccountQuantityFeeAmountLookupA007RequestModel"
    :csv-file-name="csvFileName"
    :is-check-csv-download-privacy-confirmation="false"
    :pre-request-handler="preRequestHandlerA007"
    @response-handler="responseHandlerA007($event)"
    @response-error-handler="responseErrorHandlerA007($event)"
  ></ifa-button>

  <ifa-ok-cancel-dialog
    :is-visible="confirmVisible"
    title="個人情報について"
    message="管理系メニューの個人情報管理台帳作成画面にて、個人情報管理の記述をお願いします。※内部管理責任者メニュー＞個人情報管理＞個人情報管理台帳をクリックすると「個人情報管理台帳作成画面」が表示されます。"
    @close-modal-ok="handleConfirm"
    @close-modal-cancel="confirmVisible=false"
  ></ifa-ok-cancel-dialog>

</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaByYearAccountQuantityFeeAmountLookupFromModel } from './js/IfaByYearAccountQuantityFeeAmountLookupFormModel'
import { IfaByYearAccountQuantityFeeAmountLookupA002RequestModel } from './js/IfaByYearAccountQuantityFeeAmountLookupA002RequestModel'
import { IfaByYearAccountQuantityFeeAmountLookupA004RequestModel } from './js/IFaByYearAccountQuantityFeeAmountLookupA004RequestModel'
import { IfaByYearAccountQuantityFeeAmountLookupA007RequestModel } from './js/IfaByYearAccountQuantityFeeAmountLookupA007RequestModel'
import IfaBrokerCodeArrayDomainModel from '@/resource/domain/IfaBrokerCodeArrayDomainModel.json'
import IfaBrokerCodeDomainModel from '@/resource/domain/IfaBrokerCodeDomainModel.json'
import IfaBrokerCodeClosingMonthLogin from './IfaBrokerCodeClosingMonthLogin.vue'
import { getMessage } from '@/utils/errorHandler'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getFormattedMonthValue } from '@/components/Date/js/IfaMonthPickerFunction.js'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'

export default {
  components: {
    GridTable,
    screenTitle,
    IfaBrokerCodeClosingMonthLogin,
    IfaOkCancelDialog
  },
  props: {
  },
  emits: ['initializeError'],
  data() {
    return {
      rules: {
        brokerCode: [{ required: true, trigger: 'blur', validator: this.brokerCodeValidator }]
      },
      form: new IfaByYearAccountQuantityFeeAmountLookupFromModel(),
      formRef: {},
      chkBrokerCodeExcludeIsVisible: true,
      brokerCodeReadonly: false,
      brokerCodeMultipleInputsFlag: true,
      brokerCodeClosingMonthLoginisVisible: false,
      brokerCodeAutoDispFlag: false,
      brokerCodeExcludeOptions: [
        { key: false, value: '' },
        { key: true, value: '仲介業者除外' }
      ],
      year: new Date().getFullYear(),
      IfaBrokerCodeArrayDomainModel: IfaBrokerCodeArrayDomainModel,
      IfaBrokerCodeDomainModel: IfaBrokerCodeDomainModel,
      pqGridOption: getConvertedOption(obj),
      csvFileName: '',
      confirmVisible: false
    }
  },
  computed: {
    isDisabledButton() {
      if (this.$store.getters.userAccount) {
        return !(this.$store.getters.userAccount.medUsers.privId === '3')
      }
      return true
    },
    isCheckBrokerCode() {
      return /^\d+$/.test(this.form.brokerCode)
    },
    getClosingMonth() {
      return { brokerCode: this.form.brokerCode, chkBrokerCodeExclude: this.form.chkBrokerCodeExclude }
    },
    requestModelA005() {
      return { brokerCode: this.form.brokerCode }
    },
    settleYearList() {
      return [
        { key: '0000', value: '全て' },
        { key: this.year + 1, value: this.year + 1 + '年' },
        { key: this.year, value: this.year + '年' },
        { key: this.year - 1, value: this.year - 1 + '年' },
        { key: this.year - 2, value: this.year - 2 + '年' },
        { key: this.year - 3, value: this.year - 3 + '年' }
      ]
    },
    csvBtnDisabled() {
      if (this.pqGridOption.dataModel.data.length) {
        return false
      }
      return true
    },
    message() {
      return getMessage(this.form.errorMessage)
    },
    IfaByYearAccountQuantityFeeAmountLookupA002RequestModel() {
      return new IfaByYearAccountQuantityFeeAmountLookupA002RequestModel(this.form)
    },
    IfaByYearAccountQuantityFeeAmountLookupA004RequestModel() {
      return new IfaByYearAccountQuantityFeeAmountLookupA004RequestModel(this.form)
    },
    IfaByYearAccountQuantityFeeAmountLookupA007RequestModel() {
      return new IfaByYearAccountQuantityFeeAmountLookupA007RequestModel(this.form)
    },
    checkComment() {
      if (this.form.comment) {
        return (
          this.form.comment.replace(/\s/g, '').replace(/　/g, '') !== ''
        )
      } else {
        return false
      }
    }
  },
  watch: {
    'form.chkBrokerCodeExclude'(value) {
      if (this.form.brokerCode.length === 4 && this.isCheckBrokerCode && !value) {
        document.getElementById('getClosingMonthA005').click()
      } else {
        this.form.closingMonth = ''
        this.form.errorMessage = ''
      }
    }
  },
  created() {
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
    this.pqGridOption.wrap = true
    if (this.$store.getters.userAccount.medUsers.privId === '3') {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === 'downloadFlg')
      colModel.hidden = true
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable']?.refreshView(true)
        this.form.chkBrokerCodeExclude = false
        this.form.settleYear = '0000'
        document.getElementById('IfaByYearAccountQuantityFeeAmountLookupInitializeA001').click()
      })
    },
    responseHandlerInitializeA001(data) {
      this.brokerCodeAutoDispFlag = data.dataList[0]?.brokerCodeAutoDispFlag === '1'
      this.brokerCodeReadonly = false
      this.form.comment = data.dataList[0]?.comment
      this.form.closingMonth = data.dataList[0]?.closingMonth
      this.form.errorMessage = data.dataList[0]?.errorMessage
      if (this.brokerCodeAutoDispFlag) {
        this.brokerCodeReadonly = true
        this.form.brokerCode = this.$store.getters.userAccount.broker.brokerCode
        this.brokerCodeMultipleInputsFlag = false
      } else {
        this.form.closingMonth = ''
        this.form.errorMessage = ''
      }
    },
    getClosingMonthA005() {
      if (this.form.brokerCode.length === 4 && this.isCheckBrokerCode && !this.form.chkBrokerCodeExclude && !this.brokerCodeReadonly) {
        document.getElementById('getClosingMonthA005').click()
      }
    },
    preRequestA002Handler() {
      if (this.form.settleYear === '0000') {
        this.IfaByYearAccountQuantityFeeAmountLookupA002RequestModel.settleYearList =
        [
          this.settleYearList[1].key,
          this.settleYearList[2].key,
          this.settleYearList[3].key,
          this.settleYearList[4].key,
          this.settleYearList[5].key
        ]
      } else {
        this.IfaByYearAccountQuantityFeeAmountLookupA002RequestModel.settleYearList = [this.form.settleYear]
      }
    },
    responseA002Handler(response) {
      if (response.dataList[0]?.ifaByYearAccountQuantityFeeAmountLookupA002List) {
        this.pqGridOption.dataModel.data = response.dataList[0].ifaByYearAccountQuantityFeeAmountLookupA002List.map(item => ({
          ...item,
          csvBtnEnabled: this.$store.getters.userAccount.medUsers.privId === '3'
        }))
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.$refs['gridTable'].refreshView(true)
    },
    clearSearchCondition() {
      this.$refs['form'].clearValidate()
      if (this.brokerCodeReadonly === false) {
        this.form.brokerCode = ''
        this.form.closingMonth = ''
        this.form.errorMessage = ''
      }
      this.form.chkBrokerCodeExclude = false
      this.form.settleYear = '0000'
    },
    responseHandlerGetClosingMonthA005(data) {
      this.form.closingMonth = data.dataList[0]?.closingMonth
      this.form.errorMessage = data.dataList[0]?.errorMessage
      this.count = 0
    },
    settleMonthSetting() {
      this.brokerCodeClosingMonthLoginisVisible = true
    },
    handleCloseModalbrokerCodeClosingMonthLogin() {
      this.brokerCodeClosingMonthLoginisVisible = false
    },
    handleUpdateModalbrokerCodeClosingMonthLogin() {
      if (this.form.brokerCode.length === 4 && this.isCheckBrokerCode && !this.form.chkBrokerCodeExclude) {
        document.getElementById('getClosingMonthA005').click()
      }
    },
    preRequestA004Handler() {
      if (this.form.settleYear === '0000') {
        this.IfaByYearAccountQuantityFeeAmountLookupA004RequestModel.settleYearList =
        [
          this.settleYearList[1].key,
          this.settleYearList[2].key,
          this.settleYearList[3].key,
          this.settleYearList[4].key,
          this.settleYearList[5].key
        ]
      } else {
        this.IfaByYearAccountQuantityFeeAmountLookupA004RequestModel.settleYearList = [this.form.settleYear]
      }
    },
    responseHandlerA004(data) {
      this.$_logDebug(data)
    },
    responseErrorHandlerA004(error) {
      this.$_logError(error)
    },
    csvHandler() {
      // 個人情報管理台帳ダウンロード要否：trueの場合、個人情報管理台帳へ登録
      if (this.$store.getters.userAccount.csvDownloadPrivacyConfirmationRequired) {
        this.confirmVisible = true
      } else {
        // CSVダウンロード
        document.getElementById('btnCsvDownloadA007').click()
      }
    },
    handleConfirm() {
      this.$nextTick(() => {
        document.getElementById('btnCsvDownloadA007').click()
      })
    },
    preRequestHandlerA007() {
      const selRowData = JSON.parse(decodeURIComponent(document.getElementById('csv-download-button').value))
      this.IfaByYearAccountQuantityFeeAmountLookupA007RequestModel.brokerCode = selRowData.brokerCode
      this.IfaByYearAccountQuantityFeeAmountLookupA007RequestModel.closingYearMonth = selRowData.closingYearMonth
      this.IfaByYearAccountQuantityFeeAmountLookupA007RequestModel.brokerName = selRowData.brokerName
      this.IfaByYearAccountQuantityFeeAmountLookupA007RequestModel.mediateProprietyKBN = selRowData.mediateProprietyKBN
      this.csvFileName = '媒介口座明細' + '_' + selRowData.closingYearMonth + '_' + selRowData.brokerCode
    },
    responseHandlerA007(data) {
      this.confirmVisible = false
    },
    responseErrorHandlerA007(error) {
      this.$_logError(error)
    },
    // キー入力
    handleKeyDown(event) {
      switch (event.key) {
        case 'Delete': // event.keyCode: 46
          this.delFlag = true
          break
        case 'Backspace': // event.keyCode: 8
          this.delFlag = false
          break
      }
    },
    // 仲介業者コードが編集された
    handleBrokerCodeModified(newValue) {
      if (newValue.match(/[^\d,]/g) !== null) {
        this.form.brokerCode = newValue
        return
      }
      const newText = newValue.replace(/[^\d]/g, '')
      const currentText = this.form.brokerCode.replace(/[^\d]/g, '')
      this.form.brokerCode = newText.match(/\d{1,4}/g)?.join() || ''
      const obj = document.getElementById('brokerCode')
      let pos = obj.selectionStart
      if (newText !== currentText) {
        if (newText.length > currentText.length) {
          // 文字が追加された
          if (this.form.brokerCode.length - pos === 1) {
            // カーソル位置が最後尾かつコンマが追加されたときは､1文字進める (1111|[2] -> 1111,[2]| )
            pos += 1
          } else {
            const m = newValue.match(/\d{5,}/)
            if (m) {
              const i = m[0].length + m.index
              if (pos === i && newValue.charAt(i) === ',') {
                // コンマの前に文字が挿入された場合､1文字進める (1111,9999|[2],222 -> 1111,9999,[2]|222)
                pos += 1
              }
            }
          }
        }
      } else {
        // Backspace キーまたはDEL キーでコンマが削除されたとき
        if (this.delFlag) {
          // DEL キーで削除された場合
          if (pos > 0 && pos % 5 === 4) {
            // コンマが削除されても復活するのでカーソルだけ1文字進める (1111|,2222 -> 1111,|2222)
            pos += 1
          }
        } else {
          // Backspace キーで削除された場合
          if (pos > 0 && pos % 5 === 0) {
            // コンマが削除されても復活するのでカーソルだけ1文字戻す (1111,|2222 -> 1111|,2222)
            pos -= 1
          }
        }
      }
      this.$nextTick(() => {
        this.$nextTick(() => {
          // カーソルを確実に移動するために $nextTick を2回重ねる (1回だと reactive が効いてカーソルが最後尾に移動してしまう)
          obj.selectionStart = pos
          obj.selectionEnd = pos
        })
      })
    },
    brokerCodeValidator(rule, value, callback) {
      if (!this.form?.brokerCode && !this.brokerCodeReadonly) {
        this.form.closingMonth = ''
        this.form.errorMessage = ''
        callback('仲介業者コードを入力してください。')
        return
      }
      if (this.form.brokerCode.length !== 4) {
        this.form.closingMonth = ''
        this.form.errorMessage = ''
      }
      callback()
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '年度別口座数・報酬額照会',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false
}

obj.colModel = [
  { title: '決算', dataIndx: 'closingKbn', width: 60, dataType: 'string', editable: false, halign: 'center', align: 'center',
    render: function(ui) {
      return ui.rowData.closingKbn ? getCodeValue('CLOSING_KBN', 1, ui.rowData.closingKbn) : '-'
    }
  },
  { title: '決算年月', dataIndx: 'closingYearMonth', minWidth: 100, dataType: 'string', editable: false, halign: 'center', align: 'center',
    render: function(ui) {
      return ui.rowData.closingYearMonth ? getFormattedMonthValue(ui.rowData.closingYearMonth) : '-'
    }
  },
  { title: '仲介業者コード', dataIndx: 'brokerCode', minWidth: 120, dataType: 'string', editable: false, halign: 'center', align: 'center' },
  { title: '仲介業者名', dataIndx: 'brokerName', minWidth: 280, dataType: 'string', editable: false, halign: 'center', align: 'left' },
  { title: '前期末口座数', dataIndx: 'previousNumberOfAccounts', minWidth: 110, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) {
      return ui.rowData.previousNumberOfAccounts ? ifaFormatUtils.withCommaInteger(ui.rowData.previousNumberOfAccounts) : '-'
    }
  },
  { title: '当期末口座数', dataIndx: 'numberOfAccounts', minWidth: 110, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) {
      return ui.rowData.numberOfAccounts ? ifaFormatUtils.withCommaInteger(ui.rowData.numberOfAccounts) : '-'
    }
  },
  { title: '口座数増減', dataIndx: 'numberOfAccountsIncreaseDecrease', minWidth: 110, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) {
      return ui.rowData.numberOfAccountsIncreaseDecrease ? ifaFormatUtils.signedWithCommaInteger(ui.rowData.numberOfAccountsIncreaseDecrease) : '-'
    }
  },
  { title: 'うち期中に媒介を行った口座数', dataIndx: 'numberOfActiveAccounts', minWidth: 110, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) {
      return ui.rowData.numberOfActiveAccounts ? ifaFormatUtils.withCommaInteger(ui.rowData.numberOfActiveAccounts) : '-'
    }
  },
  {
    title: '媒介手数料（円）',
    colModel: [
      { title: '媒介手数料（千円）', minWidth: 180, sortable: false, align: 'left', halign: 'center',
        render: (ui) => ({
          cls: 'pg-grid-cell',
          text: `
        <div style="display:flex; flex-direction:column; height: 100%; justify-content: center; width:100%; box-sizing:border-box;">
          <div class="mediateCom" style="width:100%; display: block; border-bottom: 1px solid #ccc; padding-bottom: 4px; box-sizing:border-box; text-align:right;padding-right: 5px;min-height: 25px;">
            ${ui.rowData.mediateCom ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.mediateCom) : '-'}
          </div>
          <div class="mediateComThousand" style="width:100%; display: block; padding-top: 4px; box-sizing:border-box; text-align:right;padding-right: 5px;min-height: 25px;">
            ${ui.rowData.mediateComThousand ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.mediateComThousand) : '-'}
          </div>
        </div>`
        }) }
    ],
    minWidth: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right'
  },
  {
    title: 'その他受入手数料（円）',
    colModel: [
      { title: 'その他受入手数料（千円）', minWidth: 180, sortable: false, align: 'left', halign: 'center',
        render: (ui) => ({
          cls: 'pg-grid-cell',
          text: `
      <div style="display:flex; flex-direction:column; height: 100%; justify-content: center; width:100%; box-sizing:border-box;">
        <div class="otherCom" style="width:100%; display: block; border-bottom: 1px solid #ccc; padding-bottom: 4px; box-sizing:border-box; text-align:right;padding-right: 5px;min-height: 25px;">
          ${ui.rowData.otherCom ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.otherCom) : '-'}
        </div>
        <div class="otherComThousand" style="width:100%; display: block; padding-top: 4px; box-sizing:border-box; text-align:right;padding-right: 5px;min-height: 25px;">
          ${ui.rowData.otherComThousand ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.otherComThousand) : '-'}
        </div>
      </div>`
        })
      }
    ],
    minWidth: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right'
  },
  {
    title: '計（円）',
    colModel: [
      { title: '計（千円）', minWidth: 180, sortable: false, align: 'left', halign: 'center',
        render: (ui) => ({
          cls: 'pg-grid-cell',
          text: `
      <div style="display:flex; flex-direction:column; height: 100%; justify-content: center; width:100%; box-sizing:border-box;">
        <div class="total" style="width:100%; display: block; border-bottom: 1px solid #ccc; padding-bottom: 4px; box-sizing:border-box; text-align:right;padding-right: 5px;min-height: 25px;">
          ${ui.rowData.total ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.total) : '-'}
        </div>
        <div class="totalThousand" style="width:100%; display: block; padding-top: 4px; box-sizing:border-box; text-align:right;padding-right: 5px;min-height: 25px;">
          ${ui.rowData.totalThousand ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.totalThousand) : '-'}
        </div>
      </div>`
        })
      }
    ],
    minWidth: 180,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right'
  },
  { title: '当期末口座明細', dataIndx: 'downloadFlg', dataType: 'string', minWidth: 120, editable: false, halign: 'center', align: 'center',
    render: function(ui) {
      if (ui.rowData.downloadFlg === '0' && !ui.rowData.csvBtnEnabled) {
        const csv = JSON.stringify(ui.rowData)
        return "<button type='button' class='el-button ifa-button el-button--small secondary-class' onclick='const btn = document.getElementById(\"csv-download-button\"); btn.value = \"" + encodeURIComponent(csv) + "\"; btn.click(); this.blur();'><span class='__adjust_button_text'>CSV出力</span></button>"
      } else {
        return ''
      }
    }
  },
  { title: '媒介可否区分', dataIndx: 'mediateProprietyKBN', dataType: 'string', hidden: true,
    render: function(ui) {
      return ui.rowData.mediateProprietyKBN ? ifaFormatUtils.signedWithCommaInteger(ui.rowData.mediateProprietyKBN) : '-'
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

<style lang="scss" scoped>
.content-card {
  margin: 0.5rem 1rem;
}
:deep(.el-input) {
    --el-input-width: 200px;
  }
.filter-container {
  margin: 1rem 0 1rem;
}
.grid_button_area {
  margin-top: 1rem;
}
.form_button {
  margin: 0 2rem 0.8rem 46px;
  padding: 0;
}
.gridButtonArea {
  margin: 0 0 20px 0;
}
.right_icon {
  text-align:right;
  margin: 0 32px 16px auto;
  display: inline-block;
  position: absolute;
  right: 20px;
}
._label {
  font-weight: 700;
  color: #18181A
}
#ifaInputWidth {
  width: 383px;
}
:deep(.el-form-item__error) {
  white-space: nowrap;
}
:deep(.pg-grid-cell) {
  height: 25px;
  padding-left: 0;
  padding-right: 0;
  border-bottom:1px solid #ccc;

}
:deep(.pg-grid-header-cell) {
  height: 19px;
  border-bottom:1px solid #ccc;
  padding-bottom:4px;
  box-sizing:border-box;
}

:deep(tr.pq-grid-row:not(.pq-grid-oddRow)) {
    background-color: #f5f5f5; /* 浅灰色背景 */
}

</style>
