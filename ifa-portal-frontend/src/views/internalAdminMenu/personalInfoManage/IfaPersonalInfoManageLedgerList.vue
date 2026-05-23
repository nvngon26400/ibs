<template>
  <div style="overflow-x: scroll;">
    <screen-title :text="formData.title"></screen-title>
    <el-form
      ref="form"
      :model="formData"
      :inline="true"
      style="min-width: 1000px;"
      :rules="rules"
    >
      <!-- 検索条件エリア -->
      <el-card class="content-card">
        <div class="filter-container">
          <el-row style="padding : 0 0 0.5rem 1rem; line-height : 1.8 ;">
            <el-col :span="16">
              <el-row>
                <span style="white-space: pre-wrap;">{{ explainWord }}</span>
              </el-row>
              <el-row>
                <el-icon style="height: auto; margin-right: 3px;"><Document></Document></el-icon>
                <a
                  style="text-decoration: underline; text-underline-offset:0.2em; color:#409EFF;"
                  @click="downLoadPdf"
                >個人情報管理台帳 操作マニュアル</a>
              </el-row>
            </el-col>
            <!-- バルーンアイコン -->
            <el-col
              :span="1"
              class="right_icon"
            >
              <ifa-balloon-icon
                :show-message="isInfoMessage"
                icon-type="info"
              >
                <div
                  style="white-space: pre-wrap;"
                  v-html="messageContent"
                ></div>
              </ifa-balloon-icon>
            </el-col>
          </el-row>

          <el-row>
            <!-- 処理対象期間 -->
            <el-form-item class="form_label">
              <ifa-date-range-picker
                ref="datePicker"
                v-model="formData.processTargetPeriod"
                label="処理対象期間"
                size="small"
                prop="processTargetPeriod"
                unlink-panels
                :required="true"
                :picker-options="pickerOptions"
              ></ifa-date-range-picker>
            </el-form-item>
            <ifa-button
              id="ifaPersonalInfoManageLedgerListA003"
              name="ifaPersonalInfoManageLedgerListA003"
              text="表示"
              width="90"
              search
              small
              :form="formRef"
              :request-model="IfaPersonalInfoManageLedgerListA003RequestModel"
              action-id="SUB0403-01#A003"
              action-type="requestAction"
              @pre-request-handler="preRequestHandlerA003"
              @response-handler="responseHandlerA003($event)"
            ></ifa-button>
          </el-row>
          <el-row>
            <span style="margin-left: 9.5rem;">※期間は3ヶ月以内を指定してください。（過去全ての履歴を照会いただけます。）</span>
          </el-row>
        </div>
      </el-card>
      <!-- 個人情報管理台帳一覧 -->
      <el-card class="content-card">
        <el-row>
          <div class="gridButtonArea">
            <ifa-button
              :disabled="btnDisabled"
              text="情報更新"
              width="110"
              color="primary"
              small
              action-type="originalAction"
              @app-action-handler="a004Handler"
            ></ifa-button>
            <ifa-button
              id="btnCsvDownload"
              name="btnCsvDownload"
              :disabled="btnDisabled"
              text="CSV出力"
              width="110"
              color="primary"
              small
              :request-model="IfaPersonalInfoManageLedgerListA005RequestModel"
              action-id="SUB0403-01#A005"
              action-type="outputCsvAction"
              csv-file-name="個人情報管理台帳"
              :is-check-csv-download-privacy-confirmation="false"
              @response-handler="responseHandlerA005($event)"
              @response-error-handler="responseErrorHandlerA005($event)"
            ></ifa-button>
            <!-- 内部管理責任者 -->
            <el-form-item
              label="内部管理責任者"
              class="form_label"
            >
              <div style="margin: 0.1rem 0.5rem; height: 25px; line-height: 1.7rem;">
                <span v-if="userInfo.medUsers.privId ==='1' || userInfo.medUsers.privId ==='3'">
                  {{ userInfo.medUsers.userNm }}
                </span>
              </div>
            </el-form-item>
          </div>
        </el-row>
        <grid-table
          id="ifaPersonalInfoManageLedgerListTable"
          ref="pqGrid"
          :options="pqGridOption"
          :auto-refresh="false"
        ></grid-table>
      </el-card>
    </el-form>
    <ifa-ok-cancel-dialog
      :is-visible="isOkCancelDialogVisible"
      title="更新確認"
      message="個人情報管理台帳を更新します。よろしいですか？"
      @close-modal-ok="a006Handler"
      @close-modal-cancel="isOkCancelDialogVisible = false"
    ></ifa-ok-cancel-dialog>
    <ifa-requester
      id="IfaPersonalInfoManageLedgerListInitializeA001"
      action-type="requestAction"
      action-id="SUB0403-01#A001"
      @response-handler="responseHandlerA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaPersonalInfoManageLedgerListIfaPersonalInfoManageLedgerOperateManualDownloadA002"
      action-id="SUB0403-01#A002"
      action-type="outputPdfAction"
      :request-model="IfaPersonalInfoManageLedgerListA002RequestModel"
    ></ifa-requester>
    <ifa-requester
      id="ifaPersonalInfoManageLedgerListOkA006"
      action-type="requestAction"
      action-id="SUB0403-01#A006"
      :request-model="IfaPersonalInfoManageLedgerListA006RequestModel"
      @response-handler="responseHandlerA006($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getConvertedOption, convertData } from '@/utils/pqgridHelper'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaPersonalInfoManageLedgerListFormModel } from './js/IfaPersonalInfoManageLedgerListFormModel.js'
import { IfaPersonalInfoManageLedgerListA003RequestModel } from './js/IfaPersonalInfoManageLedgerListA003RequestModel.js'
import { IfaPersonalInfoManageLedgerListA005RequestModel } from './js/IfaPersonalInfoManageLedgerListA005RequestModel.js'
import { notifyMessage } from '@/utils/errorHandler'
import { getFormattedDateValue, monthsBefore } from '@/components/Date/js/IfaDatePickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { getCodeList } from '@/components/Input/js/IfaCodeListFunction'
import { validateDateRangeFromTo } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    GridTable,
    IfaOkCancelDialog,
    screenTitle
  },
  data() {
    return {
      explainWord: '・個人情報管理台帳は、ＩＦＡポータルの顧客情報をダウンロードした場合に自動的に生成されます。\n　ダウンロードした情報の用途や、情報管理のステータスを入力し、適時情報更新を行って下さい。',
      messageContent: '※事務ミス等による不要なダウンロード/印刷をした場合は、入力先頭項目の「保管/送付媒体」で「保管/送付しない」を選択して下さい。\nその場合はそれ以外の項目はすべて「－」を選択してください。\nなお、当該ファイルは直ちに処分することとし、外部への持ち出しはもちろんのこと、紙やデータでの保管も禁止といたします。',
      errorMes: '更新対象データが選択されていません。\n　更新したデータがある場合は、該当するデータ入力画面右端の「更新データ」チェックボックスにチェックを入れてから、再度「情報更新」ボタンを押してください。',
      formData: new IfaPersonalInfoManageLedgerListFormModel(),
      IfaPersonalInfoManageLedgerListA002RequestModel: {},
      IfaPersonalInfoManageLedgerListA006RequestModel: {},
      formRef: {},
      isOkCancelDialogVisible: false,
      isInfoMessage: false,
      pqGridOption: getConvertedOption(obj, true),
      rules: {
        processTargetPeriod: [
          {
            validator: this.periodDateValidator,
            trigger: 'blur'
          }
        ]
      },
      pickerOptions: {
        shortcuts: [
          {
            text: '当月',
            value: () => {
              // startDateに当月の月初を設定
              const startDate = new Date(this.$store.getters.requestedTime)
              startDate.setDate(1)
              startDate.setHours(0, 0, 0, 0)
              // endDateに当日を設定
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setHours(0, 0, 0, 0)
              return [startDate, endDate]
            }
          }, {
            text: '1ケ月前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 1)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }, {
            text: '2ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 2)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }, {
            text: '3ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 3)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }
        ]
      }
    }
  },
  computed: {
    IfaPersonalInfoManageLedgerListA003RequestModel() {
      return new IfaPersonalInfoManageLedgerListA003RequestModel(
        this.formData
      )
    },
    IfaPersonalInfoManageLedgerListA005RequestModel() {
      return new IfaPersonalInfoManageLedgerListA005RequestModel(
        this.formData
      )
    },
    btnDisabled() {
      if (this.pqGridOption.dataModel.data.length) {
        return false
      }
      return true
    },
    userInfo() {
      return this.$store.getters.userAccount
    },
    colStorageSendingMedium() {
      return obj.colModel.find(e => e.dataIndx === 'storageSendingMedium').cl
    },
    colDepositDestinations() {
      return obj.colModel.find(e => e.dataIndx === 'depositDestinations').cl
    },
    colDestination() {
      return obj.colModel.find(e => e.dataIndx === 'destination').cl
    },
    colStorageSpace() {
      return obj.colModel.find(e => e.dataIndx === 'storageSpace').cl
    },
    colDisposeMethod() {
      return obj.colModel.find(e => e.dataIndx === 'disposeMethod').cl
    },
    colNotDispose() {
      return obj.colModel.find(e => e.dataIndx === 'notDispose').cl
    },
    dataModel() {
      return this.pqGridOption.dataModel.data.map((item, index) => {
        return { k: item.storageSendingMedium, index }
      })
    }
  },
  created() {
    this.pqGridOption.wrap = true
  },
  mounted() {
    this.formRef = this.$refs.form
    // 本日までの3か月間を取得
    const endDate = new Date(this.$store.getters.requestedTime)
    const startDate = new Date(this.$store.getters.requestedTime)
    startDate.setMonth(startDate.getMonth() - 3)
    startDate.setDate(startDate.getDate() + 1)
    this.formData.processTargetPeriod = [this.formatDate(startDate), this.formatDate(endDate)]

    // グリッド
    // このコンポーネントのインスタンスにアクセス可能
    const gridInstance = this
    // グリッドテーブル内のselectのchangeイベントをキャッチしてテーブルを更新
    this.$refs.pqGrid.$el.addEventListener('change', function(event) {
      if (event.target && event.target.tagName === 'SELECT') {
        const newValue = event.target.value
        const rowIndx = event.target.getAttribute('data-rowindx')
        const id = event.target.id

        // Vueのメソッドを呼び出す
        gridInstance.handleDropdownChange(newValue, rowIndx, id)
      }
    })
    // テキストボックスのinputイベントをキャッチしてテーブルを更新
    this.$refs.pqGrid.$el.addEventListener('input', function(event) {
      if (event.target && event.target.tagName === 'INPUT') {
        const newValue = event.target.value
        const rowIndx = event.target.getAttribute('data-rowindx')
        const id = event.target.id

        // Vueのメソッドを呼び出す
        gridInstance.handleTextBoxInput(newValue, rowIndx, id)
      }
    })
    // テキストボックスのchangeイベントをキャッチしてテーブルを更新
    this.$refs.pqGrid.$el.addEventListener('change', function(event) {
      if (event.target && event.target.tagName === 'INPUT') {
        const newValue = event.target.value
        const rowIndx = event.target.getAttribute('data-rowindx')
        const id = event.target.id

        // Vueのメソッドを呼び出す
        gridInstance.handleTextBoxChange(newValue, rowIndx, id)
      }
    })
  },
  methods: {
    onShow() {
      this.isInfoMessage = true
      setTimeout(() => {
        this.isInfoMessage = false
      }
      , 3000
      )
      this.$nextTick(() => {
        document.getElementById('IfaPersonalInfoManageLedgerListInitializeA001').click()
      })
    },
    responseHandlerA001(response) {
      this.formData.fileDirectory = response.dataList[0].fileDirectory
      if (response.errorLevel === 0) {
        this.$nextTick(() => {
          document.getElementById('ifaPersonalInfoManageLedgerListA003').click()
        })
      }
    },
    downLoadPdf() {
      this.$nextTick(() => {
        this.IfaPersonalInfoManageLedgerListA002RequestModel = {
          fileDirectory: this.formData.fileDirectory
        }
        document.getElementById('ifaPersonalInfoManageLedgerListIfaPersonalInfoManageLedgerOperateManualDownloadA002').click()
      })
    },
    preRequestHandlerA003() {
      this.pqGridOption.dataModel.data = []
      this.$refs['pqGrid'].refreshView()
    },
    // A003 レスポンスハンドラー
    responseHandlerA003(response) {
      this.formData.personalInfoManageLedgerListInfo = []
      Object.assign(this.formData, response.dataList[0])
      this.formData.personalInfoManageLedgerListInfo.forEach(item => {
        item.storageSendingMedium = item.storageSendingMedium || ' '
        item.depositDestinations = item.depositDestinations || ' '
        item.destination = item.destination || ' '
        item.storageSpace = item.storageSpace || ' '
        item.disposeMethod = item.disposeMethod || ' '
        item.notDispose = item.notDispose || ' '
      })
      this.pqGridOption.dataModel.data = convertData(this.formData.personalInfoManageLedgerListInfo, this.pqGridOption.colModel)
      // this.pqGridOption.dataModel.data = this.formData.personalInfoManageLedgerListInfo
      this.$nextTick(() => {
        this.$refs['pqGrid'].refreshView()
      })
    },
    // A004 確認ダイアログ表示
    a004Handler() {
      this.isOkCancelDialogVisible = true
    },
    a006Handler() {
      this.isOkCancelDialogVisible = false
      this.formData.personalInfoManageLedger = this.pqGridOption.dataModel.data
        .filter(item => item.infoUpdateCheckBox === '1')
        .map(item => {
          return {
            personalInfoManageId: item.personalInfoManageId,
            storageSendingMedium: this.colStorageSendingMedium.find(e => e.value === (item.storageSendingMedium || ' ')).key,
            depositDestinations: this.colDepositDestinations.find(e => e.value === (item.depositDestinations || ' ')).key,
            destination: this.colDestination.find(e => e.value === (item.destination || ' ')).key,
            storageSpace: this.colStorageSpace.find(e => e.value === (item.storageSpace || ' ')).key,
            preservePeriod: item.preservePeriod || '',
            disposeMethod: this.colDisposeMethod.find(e => e.value === (item.disposeMethod || ' ')).key,
            notDispose: this.colNotDispose.find(e => e.value === (item.notDispose || ' ')).key,
            disposeDateYmd: item.disposeDateYmd === '-' ? item.disposeDateYmd : getFormattedDateValue(item.disposeDateYmd) || (item.disposeDateYmd || ''),
            corDepositOutline: item.corDepositOutline || '',
            corDonationOutline: item.corDonationOutline || '',
            corDepositoryOutline: item.corDepositoryOutline || ''
          }
        })
      if (this.formData.personalInfoManageLedger.length === 0) {
        notifyMessage(-1, this.errorMes, this.formData.title, null)
        return
      }
      this.$nextTick(() => {
        this.IfaPersonalInfoManageLedgerListA006RequestModel = {
          personalInfoManageLedger: this.formData.personalInfoManageLedger
        }
        document.getElementById('ifaPersonalInfoManageLedgerListOkA006').click()
      })
    },
    responseHandlerA006(response) {
    },
    // 日付(yyyy/MM/dd形式)を Date に変換する
    parseDate(dateStr) {
      const date = new Date()
      const params = (String(dateStr)).split('/')
      const params2 = (String(params[2])).split(' ')
      date.setFullYear(params[0], params[1] - 1, params2[0])
      date.setHours(0, 0, 0, 0)
      return date
    },
    responseHandlerA005(response) {
      this.$_logDebug(response)
    },
    responseErrorHandlerA005(response) {
      this.$_logError(response)
    },
    formatDate(date, f = false) {
      date = date || new Date()
      return date.getFullYear() + '/' +
            (('0' + (date.getMonth() + 1)).slice(-2)) + '/' +
            ('0' + date.getDate()).slice(-2) +
            (f ? ' ' + ('0' + date.getHours()).slice(-2) + ':' +
              ('0' + date.getMinutes()).slice(-2) : '')
    },
    clickShowMessage() {
      this.isInfoMessage = !this.isInfoMessage
    },
    handleTextBoxInput(newValue, rowIndx, id) {
      // データモデルに対して変更を保存
      if (id) {
        this.pqGridOption.dataModel.data[rowIndx][id] = newValue
      }
    },
    handleTextBoxChange(newValue, rowIndx, id) {
      if (id === 'disposeDateYmd') {
        this.$nextTick(() => {
          this.$refs['pqGrid'].refresh()
        })
      }
    },
    handleDropdownChange(newValue, rowIndx, id) {
      if (id) {
        this.pqGridOption.dataModel.data[rowIndx][id] = newValue
      }

      if (id === 'storageSendingMedium' && newValue === '保管なし/送付しない') {
        this.pqGridOption.dataModel.data[rowIndx].depositDestinations = '-'
        this.pqGridOption.dataModel.data[rowIndx].destination = '-'
        this.pqGridOption.dataModel.data[rowIndx].storageSpace = '-'
        this.pqGridOption.dataModel.data[rowIndx].preservePeriod = '-'
        this.pqGridOption.dataModel.data[rowIndx].disposeMethod = '-'
        this.pqGridOption.dataModel.data[rowIndx].notDispose = '-'
        this.pqGridOption.dataModel.data[rowIndx].disposeDateYmd = '-'
        this.pqGridOption.dataModel.data[rowIndx].corDepositOutline = '-'
        this.pqGridOption.dataModel.data[rowIndx].corDonationOutline = '-'
        this.pqGridOption.dataModel.data[rowIndx].corDepositoryOutline = '-'
        this.pqGridOption.dataModel.data[rowIndx].infoUpdateCheckBox = '1'
      }

      this.$nextTick(() => {
        this.$refs['pqGrid'].refresh()
      })
    },
    // 処理対象期間のバリデーションチェック処理
    periodDateValidator(rule, value, callback) {
      // 以下の条件の時エラー
      // リクエスト.処理対象期間（From）とリクエスト.処理対象期間（To）の差が3ヶ月より大きい
      if (validateDateRangeFromTo(this.formData.processTargetPeriod, 3)) {
        callback(getMessage('errors.dateRange', ['処理対象期間', '3ヶ月']))
        return
      }
      // OK
      callback()
    }
  }
}
const obj = {
  width: 0,
  height: 0,
  title: '個人情報管理台帳一覧',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' }
}
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
obj.colModel = [
  { title: '処理日時', dataType: 'string', dataIndx: 'processDayTime', minWidth: '170', editable: false, halign: 'center' },
  { title: '名前', dataType: 'string', dataIndx: 'name', width: '50', editable: false, align: 'center',
    render: function(ui) {
    }
  },
  { title: '住所', dataType: 'string', dataIndx: 'adress', width: '50', editable: false, align: 'center',
    render: function(ui) {
    }
  },
  { title: 'TEL', dataType: 'string', dataIndx: 'tel', width: '50', editable: false, align: 'center',
    render: function(ui) {
    }
  },
  { title: '勤務先', dataType: 'string', dataIndx: 'office', width: '55', editable: false, align: 'center',
    render: function(ui) {
    }
  },
  { title: '生年月日', dataType: 'string', dataIndx: 'corBirthFlg', width: '70', editable: false, align: 'center',
    render: function(ui) {
    }
  },
  { title: '性別', dataType: 'string', dataIndx: 'gender', width: '50', editable: false, align: 'center',
    render: function(ui) {
    }
  },
  { title: '資産', dataType: 'string', dataIndx: 'assets', width: '50', editable: false, align: 'center',
    render: function(ui) {
    }
  },
  { title: '職業', dataType: 'string', dataIndx: 'profession', width: '50', editable: false, align: 'center',
    render: function(ui) {
    }
  },
  { title: '部店口座', dataType: 'string', dataIndx: 'butenAccount', width: '70', editable: false, align: 'center',
    render: function(ui) {
    }
  },
  { title: 'Eメール', dataType: 'string', dataIndx: 'email', width: '65', editable: false, align: 'center',
    render: function(ui) {
    }
  },
  { title: '出金口座', dataType: 'string', dataIndx: 'withdrawAccount', width: '70', editable: false, align: 'center',
    render: function(ui) {
    }
  },
  { title: '書類名・ファイル名', dataType: 'string', dataIndx: 'docNameFileName', width: '160', editable: false, halign: 'center' },
  { title: '取得データ顧客数', dataType: 'string', dataIndx: 'acquireDataCustomerCount', minWidth: '125', editable: false, align: 'right', halign: 'center',
    render: function(ui) {
      return ui.rowData.acquireDataCustomerCount ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.acquireDataCustomerCount) : '-'
    }
  },
  { title: '処理内容', dataType: 'string', dataIndx: 'processContent', width: '90', editable: false, align: 'center' },
  { title: '個人情報取得者', dataType: 'string', dataIndx: 'personalInfoAcquire', width: '170', editable: false, halign: 'center' },
  { title: 'ログインID', dataType: 'string', dataIndx: 'loginId', minWidth: '90', editable: false, halign: 'center' },
  { title: '営業員名', dataType: 'string', dataIndx: 'chargeName', width: '170', editable: false, halign: 'center' },
  // 以降編集可能項目
  { title: '保管/送付媒体', dataType: 'string', dataIndx: 'storageSendingMedium', width: '180', editable: false, halign: 'center',
    render: function(ui) {
      // 区分値一覧を表示
      const codeList = getCodeList('STORAGE_SENDING_MEDIUM', 1, 1)
      const options = codeList.map(item => item.value)
      const cellData = ui.cellData
      // ドロップダウンリストを生成
      return `<select id="storageSendingMedium" data-rowindx="${ui.rowIndx}" style="width: 100%;">
            ${options.map(option => `<option value="${option}" ${option === cellData ? 'selected' : ''}>${option}</option>`).join('')}
          </select>`
    },
    codeList: { codeListId: 'STORAGE_SENDING_MEDIUM', dispPattern: 1, selectPattern: 1 }
  },
  { title: '預託先', width: 70, dataIndx: 'depositDestinations', editable: false, halign: 'center',
    render: function(ui) {
      // 区分値一覧を表示
      const codeList = getCodeList('DEPOSIT_DESTINATIONS', 1, 1)
      const options = codeList.map(item => item.value)
      const cellData = ui.cellData
      // ドロップダウンリストを生成
      return `<select id="depositDestinations" data-rowindx="${ui.rowIndx}" style="width: 100%;">
            ${options.map(option => `<option value="${option}" ${option === cellData ? 'selected' : ''}>${option}</option>`).join('')}
          </select>`
    },
    codeList: { codeListId: 'DEPOSIT_DESTINATIONS', dispPattern: 1, selectPattern: 1 }
  },
  { title: '提供先', width: 100, dataIndx: 'destination', editable: false, halign: 'center',
    render: function(ui) {
      // 区分値一覧を表示
      const codeList = getCodeList('DESTINATION', 1, 1)
      const options = codeList.map(item => item.value)
      const cellData = ui.cellData
      // ドロップダウンリストを生成
      return `<select id="destination" data-rowindx="${ui.rowIndx}" style="width: 100%;">
            ${options.map(option => `<option value="${option}" ${option === cellData ? 'selected' : ''}>${option}</option>`).join('')}
          </select>`
    },
    codeList: { codeListId: 'DESTINATION', dispPattern: 1, selectPattern: 1 }
  },
  { title: '保管場所', width: 240, dataIndx: 'storageSpace', editable: false, halign: 'center',
    render: function(ui) {
      // 区分値一覧を表示
      const codeList = getCodeList('STORAGE_SPACE', 1, 1)
      const options = codeList.map(item => item.value)
      const cellData = ui.cellData
      // ドロップダウンリストを生成
      return `<select id="storageSpace" data-rowindx="${ui.rowIndx}" style="width: 100%;">
            ${options.map(option => `<option value="${option}" ${option === cellData ? 'selected' : ''}>${option}</option>`).join('')}
          </select>`
    },
    codeList: { codeListId: 'STORAGE_SPACE', dispPattern: 1, selectPattern: 1 }
  },
  { title: '保管期間', width: 90, dataType: 'string', dataIndx: 'preservePeriod', editable: false, halign: 'center',
    render: function(ui) {
      // nullの場合は空文字を設定
      const preservePeriod = ui.rowData.preservePeriod !== null ? ui.rowData.preservePeriod : ''
      return `<input id="preservePeriod"
                 style="width: 100%;" type="text" data-rowindx="${ui.rowIndx}"
                 value="${preservePeriod}"
                 @input="handleTextBoxInput($event, ${ui.rowIndx}, 'preservePeriod')">`
    }
  },
  { title: '廃棄方法', width: 210, dataIndx: 'disposeMethod', editable: false, halign: 'center',
    render: function(ui) {
      // 区分値一覧を表示
      const codeList = getCodeList('DISPOSE_METHOD', 1, 1)
      const options = codeList.map(item => item.value)
      const cellData = ui.cellData
      // ドロップダウンリストを生成
      return `<select id="disposeMethod" data-rowindx="${ui.rowIndx}" style="width: 100%;">
            ${options.map(option => `<option value="${option}" ${option === cellData ? 'selected' : ''}>${option}</option>`).join('')}
          </select>`
    },
    codeList: { codeListId: 'DISPOSE_METHOD', dispPattern: 1, selectPattern: 1 }
  },
  { title: '破棄しない', width: 85, dataIndx: 'notDispose', editable: false, halign: 'center',
    render: function(ui) {
      // 区分値一覧を表示
      const codeList = getCodeList('NOT_DISPOSE', 1, 1)
      const options = codeList.map(item => item.value)
      const cellData = ui.cellData
      // ドロップダウンリストを生成
      return `<select id="notDispose" data-rowindx="${ui.rowIndx}" style="width: 100%;">
            ${options.map(option => `<option value="${option}" ${option === cellData ? 'selected' : ''}>${option}</option>`).join('')}
          </select>`
    },
    codeList: { codeListId: 'NOT_DISPOSE', dispPattern: 1, selectPattern: 1 }
  },
  { title: '廃棄した年月日', minWidth: 110, dataType: 'string', dataIndx: 'disposeDateYmd', editable: false, halign: 'center',
    render: function(ui) {
      // nullの場合は空文字を設定
      let disposeDateYmd = getFormattedDateValue(ui.rowData.disposeDateYmd) ? getFormattedDateValue(ui.rowData.disposeDateYmd) : ui.rowData.disposeDateYmd
      disposeDateYmd = disposeDateYmd !== null ? disposeDateYmd : ''
      return `<input id="disposeDateYmd"
                 style="width: 100%;" type="text" data-rowindx="${ui.rowIndx}"
                 value="${disposeDateYmd}"
                 @input="handleTextBoxInput($event, ${ui.rowIndx}, 'disposeDateYmd')"
                 @change="handleTextBoxChange($event, ${ui.rowIndx}, 'disposeDateYmd')">`
    }
  },
  { title: '摘要(預託先詳細)', width: 130, dataType: 'string', dataIndx: 'corDepositOutline', editable: false, halign: 'center',
    render: function(ui) {
      // nullの場合は空文字を設定
      const corDepositOutline = ui.rowData.corDepositOutline !== null ? ui.rowData.corDepositOutline : ''
      return `<input id="corDepositOutline"
                 style="width: 100%;" type="text" data-rowindx="${ui.rowIndx}"
                 value="${corDepositOutline}"
                 @input="handleTextBoxInput($event, ${ui.rowIndx}, 'corDepositOutline')">`
    }
  },
  { title: '摘要(提供先詳細)', width: 130, dataType: 'string', dataIndx: 'corDonationOutline', editable: false, halign: 'center',
    render: function(ui) {
      // nullの場合は空文字を設定
      const corDonationOutline = ui.rowData.corDonationOutline !== null ? ui.rowData.corDonationOutline : ''
      return `<input id="corDonationOutline"
                 style="width: 100%;" type="text" data-rowindx="${ui.rowIndx}"
                 value="${corDonationOutline}"
                 @input="handleTextBoxInput($event, ${ui.rowIndx}, 'corDonationOutline')">`
    }
  },
  { title: '摘要(保管場所詳細)', width: 180, dataType: 'string', dataIndx: 'corDepositoryOutline', editable: false, halign: 'center',
    render: function(ui) {
      // nullの場合は空文字を設定
      const corDepositoryOutline = ui.rowData.corDepositoryOutline !== null ? ui.rowData.corDepositoryOutline : ''
      return `<input id="corDepositoryOutline"
                 style="width: 100%;" type="text" data-rowindx="${ui.rowIndx}"
                 value="${corDepositoryOutline}"
                 @input="handleTextBoxInput($event, ${ui.rowIndx}, 'corDepositoryOutline')">`
    }
  },
  { title: '情報更新', width: 70, dataIndx: 'infoUpdateCheckBox', editable: true, align: 'center', resizable: false,
    type: 'checkbox', cb: { check: '1', uncheck: '0' }, editor: false,
    render: function(ui) {
    }
  },
  { title: '個人情報管理ID', dataType: 'string', dataIndx: 'personalInfoManageId', hidden: true }
]

</script>
<style scoped>
.content-card {
  margin: 0.5rem 1rem;
}
.filter-container {
  margin: 1rem 0 1rem;
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
}
.form_button {
  padding: 0.4rem 2rem 1.2rem 2rem;
}
.gridButtonArea {
  padding: 0.5rem 0.1rem;
}
.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 250px;
}
:deep(.pq-grid-cell.editable) {
  background-color: #ffffd3!important;
}
:deep(.pq-grid-row.pq-state-select .pq-grid-cell.editable) {
  background-color: #deeefa!important;
}
:deep(.pq-grid-header) .check-kari {
  background-color: #ffffd3;
  color: #676B74;
}
.right_icon {
  text-align:right;
  margin-left:auto;
  display: inline-block;
  position: absolute;
  right: 20px;
  top: 10px;
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
.item {
    font-size: 15px;
}
:deep(.el-card:has(.pq-grid), .el-card__body:has(.pq-grid), .pq-grid) {
  width: auto!important;
}
</style>
