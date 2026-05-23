<template>
  <div>
    <screen-title text="SBI証券からのご連絡一覧"></screen-title>
    <!-- SBI証券からのご連絡一覧 -->
    <div id="contentAreaInput">
      <el-card>
        <el-form
          ref="form"
          :model="form"
          :inline="true"
        >
          <div>
            <el-row>
              <ifa-date-range-picker
                v-model="form.period"
                label="検索年月日"
                class="form_label"
                unlink-panels
                :picker-options="pickerOptions"
                size="small"
                style="position: initial;"
                label-class="validation-error-width"
              ></ifa-date-range-picker>
              <ifa-input-check
                id="pastDateExcrude"
                v-model="form.pastDateExcrude"
                :code-list-id="'original'"
                :original-list="dateExcrudeList"
                style="margin-left: 5px;"
                class="form_label"
              ></ifa-input-check>
            </el-row>
          </div>
          <div
            id="indicator-display"
            class="form_button"
          >
            <el-row style="margin-left: 1.5rem; margin-bottom: 1rem;">
              <ifa-button
                id="btnSearch"
                text="検索"
                width="110"
                search
                small
                action-id="SUB0602-01#A002"
                action-type="requestAction"
                :request-model="A002RequestModel"
                @response-handler="responseHandlerDisplayA002($event)"
              ></ifa-button>
              <ifa-button
                id="btnTopInputClear"
                text="クリア"
                color="secondary"
                width="110"
                small
                action-type="originalAction"
                @app-action-handler="handleClear"
              ></ifa-button>
            </el-row>
          </div>
        </el-form>
      </el-card>
    </div>
    <div id="paneBottom">
      <div id="outputPaneMargins">
        <el-card id="outputTable">
          <div
            id="gridButtonArea"
            name="gridButtonArea"
          >
            <div id="indicator-target">
              <ifa-button
                id="btnRegisterInfo"
                text="新規登録"
                width="110"
                color="primary"
                small
                action-type="originalAction"
                @app-action-handler="newInputVisible = true"
              ></ifa-button>
              <ifa-button
                id="btnRegisterInfo"
                text="更新"
                width="110"
                color="primary"
                small
                :disabled="disabledButton"
                action-type="originalAction"
                @app-action-handler="update"
              ></ifa-button>
              <ifa-button
                id="btnRegisterInfo"
                text="削除"
                width="110"
                color="seconday"
                small
                :disabled="disabledButton"
                action-type="originalAction"
                @app-action-handler="cancelComfirmVisible = true"
              ></ifa-button>
            </div>
          </div>
          <grid-table
            ref="gridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </div>
    </div>

    <!-- 新規作成 ダイアログ -->
    <ifa-portal-notification-new-register
      :is-visible="newInputVisible"
      @update-table="handleUpdateTable"
      @close-modal="newInputVisible = false"
    ></ifa-portal-notification-new-register>

    <!-- 更新 ダイアログ -->
    <ifa-portal-notification-update
      :is-visible="updateVisible"
      :selected-info="selectedInfo"
      @update-table="handleUpdateTable"
      @close-modal="updateVisible = false"
    ></ifa-portal-notification-update>

    <!-- 削除確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="cancelComfirmVisible"
      title="削除確認"
      :message="message"
      @close-modal-ok="deleteRowData()"
      @close-modal-cancel="cancelComfirmVisible = false"
    ></ifa-ok-cancel-dialog>
    <ifa-requester
      id="ifaPortalNotificationManagerLookupDisplayA002"
      action-id="SUB0602-01#A002"
      action-type="requestAction"
      :request-model="A002RequestModel"
      @response-handler="responseHandlerDisplayA002($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaPortalNotificationManagerLookupPortalNoticeDeletionConfirmOkA008"
      action-id="SUB0602-01#A008"
      action-type="requestAction"
      :request-model="A008RequestModel"
    ></ifa-requester>
    <ifa-requester
      id="ifaPortalNotificationUpdateInitializeA001"
      action-id="SUB0602-04_1#A001"
      action-type="requestAction"
      :request-model="updateA001RequestModel"
      @response-handler="responseHandlerUpdateInitializeA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaPortalNotificationNewRegister from './IfaPortalNotificationNewRegister'
import IfaPortalNotificationUpdate from './IfaPortalNotificationUpdate'
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { IfaPortalNotificationManagerLookupA002RequestModel } from './js/IfaPortalNotificationManagerLookupA002RequestModel'
import { IfaPortalNotificationManagerLookupA008RequestModel } from './js/IfaPortalNotificationManagerLookupA008RequestModel'
import { IfaPortalNotificationManagerLookupFormModel } from './js/IfaPortalNotificationManagerLookupFormModel'
import { getFormattedDateValue, getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import { IfaPortalNotificationUpdateA001RequestModel } from './js/IfaPortalNotificationUpdateA001RequestModel'

export default {
  components: {
    GridTable,
    IfaPortalNotificationNewRegister,
    screenTitle,
    IfaOkCancelDialog,
    IfaPortalNotificationUpdate
  },
  data() {
    return {
      pqGridOption: getConvertedOption(obj),
      form: new IfaPortalNotificationManagerLookupFormModel(),
      selectedInfo: {
        notificationId: '',
        displayPeriodFrom: '',
        displayPeriodTo: '',
        notificationContent: '',
        importantNotification: '',
        nonDisplay: ''
      },
      message: '',
      newInputVisible: false,
      updateVisible: false,
      cancelComfirmVisible: false,
      disabledButton: true,
      pickerOptions: {
        shortcuts: [
          {
            text: '本日',
            value: () => {
              return [new Date(), new Date()]
            }
          }, {
            text: '前営業日',
            value: () => {
              return [this.previousBusinessDay, this.previousBusinessDay]
            }
          }, {
            text: '当月',
            value: () => {
              // startDateに当月の月初を設定
              const startDate = new Date()
              startDate.setDate(1)
              startDate.setHours(0, 0, 0, 0)
              // endDateに前営業日を設定
              // 前営業日が前月の場合には、当月初日を設定する
              const businessDate = new Date(this.previousBusinessDay)
              const endDate =
               businessDate.getMonth() !== startDate.getMonth() ? startDate : this.previousBusinessDay
              return [startDate, endDate]
            }
          },
          {
            text: '前月',
            value: () => {
              // startDateに前月の月初を設定
              const startDate = new Date()
              startDate.setHours(0, 0, 0, 0)
              startDate.setMonth(startDate.getMonth() - 1, 1)
              // endDateに前月の月末を設定
              const endDate = new Date()
              endDate.setHours(0, 0, 0, 0)
              endDate.setDate(0)
              return [startDate, endDate]
            }
          }]
      }
    }
  },
  computed: {
    dateExcrudeList() {
      return [
        { key: false, value: '' },
        { key: true, value: '表示期間が過去日除く' }
      ]
    },
    A002RequestModel() {
      return new IfaPortalNotificationManagerLookupA002RequestModel(this.form)
    },
    A008RequestModel() {
      return new IfaPortalNotificationManagerLookupA008RequestModel(this.form)
    },
    updateA001RequestModel() {
      return new IfaPortalNotificationUpdateA001RequestModel(this.selectedInfo)
    },
    previousBusinessDay() {
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    }
  },
  created() {
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
    this.pqGridOption.wrap = true
  },
  methods: {
    onShow() {
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView()
      this.disabledButton = true
    },
    update() {
      document.getElementById('ifaPortalNotificationUpdateInitializeA001').click()
    },
    responseHandlerDisplayA002(response) {
      this.disabledButton = true
      if (response.dataList.length !== 0) {
        this.pqGridOption.dataModel.data = response.dataList[0].portalNotificationList
        this.pqGridOption.dataModel.data = this.pqGridOption.dataModel.data.map((obj) => {
          const appearPeriod = []
          appearPeriod.push(obj.displayPeriodFrom)
          appearPeriod.push(obj.displayPeriodTo)
          return {
            ...obj,
            'appearPeriod': appearPeriod
          }
        })
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.$refs['gridTable'].refreshView()
    },
    responseHandlerUpdateInitializeA001(response) {
      Object.assign(this.selectedInfo, response.dataList[0])
      this.updateVisible = true
    },
    handleClick(ui) {
      this.disabledButton = false
      this.selectedInfo.notificationId = ui.rowData.notificationId
      this.form.notificationId = ui.rowData.notificationId
      if (ui.rowData.notification.length > 200) {
        this.message = '『' + ui.rowData.notification.slice(0, 200) + ' <...> 』\n\n上記お知らせを削除してもよろしいですか？'
      } else {
        this.message = '『' + ui.rowData.notification.slice(0, 200) + '』\n\n上記お知らせを削除してもよろしいですか？'
      }
    },
    // 削除
    deleteRowData() {
      document.getElementById('ifaPortalNotificationManagerLookupPortalNoticeDeletionConfirmOkA008').click()
      document.getElementById('ifaPortalNotificationManagerLookupDisplayA002').click()
      this.cancelComfirmVisible = false
    },
    // テーブル更新
    handleUpdateTable() {
      document.getElementById('ifaPortalNotificationManagerLookupDisplayA002').click()
      this.$refs['gridTable'].refreshView()
    },
    // クリアボタン
    handleClear() {
      this.form.period = []
      this.form.pastDateExcrude = true
    }
  }
}

const obj = {
  width: 0, // Sizes will be set after table has been initialized by outputGridResizingSetup
  height: 0,
  title: 'SBI証券からのご連絡一覧',
  flexHeight: false,
  collapsible: false,
  showTitle: true,
  selectionModel: { type: 'row', mode: 'single' },
  numberCell: { show: false },
  wrap: false
}
obj.colModel = [
  {
    title: '登録日時',
    minWidth: 200,
    dataType: 'string',
    dataIndx: 'registerDayTime',
    editable: false,
    halign: 'center',
    render: function(ui) {
      return getFormattedDateTimeValue(ui.rowData.registerDayTime, 'datetime14') || '-'
    }
  },
  {
    title: '表示期間',
    minWidth: 200,
    dataType: 'string',
    dataIndx: 'appearPeriod',
    editable: false,
    halign: 'center',
    render: function(ui) {
      if (ui.rowData.appearPeriod[0] || ui.rowData.appearPeriod[1]) {
        return {
          text: `<div>${getFormattedDateValue(ui.rowData.appearPeriod[0], 'date8')}～${getFormattedDateValue(ui.rowData.appearPeriod[1], 'date8')}</div>`
        }
      } else {
        return '-'
      }
    }
  },
  {
    title: 'ご連絡内容',
    minWidth: 600,
    dataType: 'string',
    halign: 'center',
    align: 'left',
    dataIndx: 'notification',
    editable: false
  },
  {
    title: '重要',
    minWidth: 100,
    dataType: 'string',
    halign: 'center',
    align: 'center',
    dataIndx: 'importantFlag',
    editable: false,
    render: function(ui) {
      const importantFlag = ui.rowData.importantFlag
      switch (importantFlag) {
        case '1':
          return '○'
        default:
          return '-'
      }
    }
  },
  {
    title: '表示/非表示',
    minWidth: 140,
    dataType: 'string',
    halign: 'center',
    align: 'center',
    dataIndx: 'nonDisplayFlag',
    editable: false,
    render: function(ui) {
      const deleteFlag = ui.rowData.nonDisplayFlag
      switch (deleteFlag) {
        case '1':
          return '非表示'
        default:
          return '表示'
      }
    }
  },
  {
    title: '&nbsp;',
    dataIndx: '',
    width: 600,
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function() {
      return ''
    }
  }
]
obj.pageModel = {
  type: 'local',
  rPP: 30,
  rPPOptions: []
}

</script>

<style lang="scss" scoped>
:deep(a) {
  text-decoration: underline;
  text-underline-offset: 0.1em;
}
:deep(.form_label) .el-form-item__label {
  width: 160px;
  line-height: 2
}
:deep(.el-form-item__error) {
  white-space: nowrap;
  margin-top: 1.8rem;
}
:deep(.el-card__body) {
  padding-top: 0.8rem
}
.form_button {
  margin: 0.2rem 2rem 0.8rem 2.5rem;
  padding: 0;
}
#gridButtonArea {
  margin-bottom: 0.5rem;
}
#contentAreaInput, #outputPaneMargins {
  margin: 0.5rem 0.8rem;
}
:deep(.pq-grid-link) {
  color:#409EFF;
  text-decoration: underline;
  text-underline-offset:0.2em;
  cursor: pointer;
}
:deep(.el-dialog) {
  padding: 30px 10px;
}
:deep(.el-dialog__header) {
  color: #18181A;
  padding: 0px;
}
:deep(.el-dialog__header) span{
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
}
:deep(.el-dialog__title) {
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
  padding: 0px;
}
:deep(.el-dialog__body) {
  padding: 0px;
  margin-bottom: 1rem;
}
// 確認ダイアログ
:deep(.confirm-dialog) .el-dialog{
  padding: 30px 30px 10px 30px;
}
:deep(.confirm-dialog) .el-dialog__header span{
  font-size: 16px;
  margin: 0px;
  font-weight: bold;
}
:deep(.confirm-dialog) .dialog-body{
  padding: 25px 0 25px 0;
}
:deep(.confirm-dialog) .dialog-footer{
  margin-left: 120px
}
</style>
